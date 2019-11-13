SET ECHO OFF
SET NEWPAGE 0
SET SPACE 0
SET LINESIZE 999
SET PAGESIZE 0
SET FEEDBACK OFF
SET HEADING ON
SET MARKUP HTML OFF SPOOL OFF
SET TRIMSPOOL ON
SET TERMOUT OFF
SET COLSEP ','

SET TAB OFF

SPOOL &1

SELECT '"'||nt_id||'","'||profile||'",'||DECODE(start_date, NULL, NULL, '"'||TO_CHAR(start_date,'DD/MM/YYYY')||'"')||',,"'||nt_id||'","'||description||'"' AS mgb_previlege 
  FROM v04_guard_previlege
 ORDER BY nt_id, profile;

SPOOL OFF

exit
