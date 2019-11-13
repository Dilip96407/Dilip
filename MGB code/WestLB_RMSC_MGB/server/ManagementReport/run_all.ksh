#!/usr/bin/ksh
#

# set environment
. ~/.profile

# go to work directory
cd $(dirname "$0")

. set_statistic_env.ksh

echo "run all reports at $(date)"
for SQL_RESULTS_LINE in $(
{
print "SET PAGESIZE 0 FEEDBACK OFF VERIFY OFF ECHO OFF SERVEROUT ON TIME OFF TIMING OFF"
print "select t121_id from t121_statistic_report where t121_cron is null or t121_cron like '%,'||to_number(to_char(sysdate,'DDD'))||',%';"
print "exit sql.sqlcode"
} | sqlplus -S $CONNECT
)
do
  rc=$?

  if [[ $rc != 0 ]]
  then
    echo "SQLPLUS returned an error $rc"
    exit 1
  fi

  echo "running statistic with id = $SQL_RESULTS_LINE"
  run_statistic.ksh "$SQL_RESULTS_LINE"
done

echo "finished all reports at $(date)"
exit 0
