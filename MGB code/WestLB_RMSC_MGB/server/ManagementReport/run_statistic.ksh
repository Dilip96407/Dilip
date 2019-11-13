#!/usr/bin/ksh
#
#
# parameter:
#

stat_id=$1

# set environment
. ~/.profile

# go to work directory
cd $(dirname "$0")

. set_statistic_env.ksh

echo "report started at $(date)"

###### read the details for the statistic
SQL_RESULTS_LINE=$(
{
print "SET PAGESIZE 0 FEEDBACK OFF VERIFY OFF ECHO OFF SERVEROUT ON TIME OFF TIMING OFF"
print "SET COLSEP ';' LINE 32767"
print "select T121_NAME, T121_MAIL_LIST, nvl(T121_SQL_FILE_NAME,'NONE'), nvl(T121_SQL_TEXT,'NONE'), T121_SQL_START_DATE, T121_SQL_STOP_DATE, nvl(T121_SQL_PARAM,'NONE'), nvl(T121_OUTPUT_FILE,'NONE')"
print "  from t121_statistic_report where t121_id = $stat_id;"
print "exit sql.sqlcode"
} | sqlplus -S $CONNECT
)
rc=$?

if [[ $rc != 0 ]]
then
  echo "SQLPLUS returned an error $rc"
  exit 1
fi

if [[ X$SQL_RESULTS_LINE == X ]]
then
  echo "No such statistic in DB for id=$stat_id"
  exit 1
fi

SUBJECT=$(echo $SQL_RESULTS_LINE | cut -d ";" -f 1 | tr -d " ")
RECEIPIENTS=$(echo $SQL_RESULTS_LINE | cut -d ";" -f 2 | tr -d " ")
CC_RECEIPIENTS="d055625@westlb.de"
FILE_NAME=$(echo $SQL_RESULTS_LINE | cut -d ";" -f 3 | tr -d " ")
SQL_TEXT=$(echo $SQL_RESULTS_LINE | cut -d ";" -f 4)
START_DATE=$(echo $SQL_RESULTS_LINE | cut -d ";" -f 5 | tr -d " ")
STOP_DATE=$(echo $SQL_RESULTS_LINE | cut -d ";" -f 6 | tr -d " ")
PARAM=$(echo $SQL_RESULTS_LINE | cut -d ";" -f 7)
OUTPUT_FILE=$(echo $SQL_RESULTS_LINE | cut -d ";" -f 8 | tr -d " ")

echo "SUBJECT=$SUBJECT"
echo "RECEIPIENTS=$RECEIPIENTS"
echo "FILE_NAME=$FILE_NAME"
echo "START_DATE=$START_DATE"
echo "STOP_DATE=$STOP_DATE"
echo "OUTPUT_FILE=$OUTPUT_FILE"
echo "PARAM=$PARAM"
echo "SQL=$SQL_TEXT"

ARCHIVE_NAME="$(date +'%Y%m%d_%H%M%S')-$SUBJECT-$$"
ARCHIVE_DIR="./archive"
SQL_FILE="$ARCHIVE_NAME.sql"

###### write the sql text to a local file
if [[ "$FILE_NAME" = NONE ]]
then
  echo "SET PAGESIZE 0 FEEDBACK OFF VERIFY OFF ECHO OFF SERVEROUT ON TIME OFF TIMING OFF TRIMSPOOL ON COLSEP ';' LINE 32767" > "$SQL_FILE"
  echo "$SQL_TEXT" >> "$SQL_FILE"
  echo ";" >> "$SQL_FILE"
else
  cp "$FILE_NAME" "$SQL_FILE" 
fi

###### check if an attached file is created or if the result is imbedded into the mail
if [[ "$OUTPUT_FILE" = NONE ]]
then
  OUTPUT_FILE="report_$stat_id_$(date +'%Y%m%d_%H%M%S').txt"
  echo "extracting mgb data"
  sqlplus -s "$CONNECT" <<EOSQL > "$OUTPUT_FILE"
  start $SQL_FILE $START_DATE $STOP_DATE $PARAM;
  exit sql.sqlcode
EOSQL
  rc=$?

  if [[ $rc != 0 ]]
  then
    echo "SQLPLUS returned an error $rc"
    exit 1
  fi

  if [[ ! -s "$OUTPUT_FILE" ]]
  then
    echo "SQLPLUS has generated no output"
    exit 1
  fi

  echo "mailing embedded data to $RECEIPIENTS"
  (
    echo "To: $RECEIPIENTS"
    echo "From: MGB-Report"
    echo "Cc: $CC_RECEIPIENTS"
    echo "Subject: $SUBJECT"
    echo "MIME-Version: 1.0"
    echo "Content-Type: text/html"
    echo "Content-Disposition: inline"
    head -40000 "$OUTPUT_FILE"  | perl -pe 's,^-.*,"-" x 140,e' | perl -pe 's,[\f],"",e' | grep -v ^SQL
  ) | sendmail -t

else
  echo "extracting mgb data"
  sqlplus -s "$CONNECT" <<EOSQL > "$OUTPUT_FILE"
  start $SQL_FILE $START_DATE $STOP_DATE $PARAM;
  exit sql.sqlcode
EOSQL
  rc=$?

  if [[ $rc != 0 ]]
  then
    echo "SQLPLUS returned an error $rc"
    exit 1
  fi

  if [[ ! -s "$OUTPUT_FILE" ]]
  then
    echo "SQLPLUS has generated no output"
    exit 1
  fi

  mv "$OUTPUT_FILE" "$OUTPUT_FILE.tmp"
  sed 's/\s+;/;/' "$OUTPUT_FILE.tmp" > "$OUTPUT_FILE"

  echo "mailing attached file to $RECEIPIENTS"
  (
    echo "To: $RECEIPIENTS"
    echo "From: MGB-Report"
    echo "Cc: $CC_RECEIPIENTS"
    echo "Subject: $SUBJECT"
    echo "MIME-Version: 1.0"
    echo "Content-Type: text/plain"
    uuencode "$OUTPUT_FILE" "$OUTPUT_FILE"
  ) | sendmail -t

fi

echo "archiving files"
mv "$OUTPUT_FILE" "$ARCHIVE_DIR/$ARCHIVE_NAME.csv"
mv "$SQL_FILE" "$ARCHIVE_DIR/$ARCHIVE_NAME.sql"

echo "report finished at $(date)"
exit 0
