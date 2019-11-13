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

SELECT '"'||nt_id||'",'||
       DECODE(start_date, NULL, NULL, '"'||TO_CHAR(start_date,'DD/MM/YYYY')||'"')||','||
       ',"'||nt_id||'",1,0,'||
       DECODE(firstname, NULL, NULL, '"'||firstname||'"')||','||
       DECODE(lastname, NULL, NULL, '"'||lastname||'"')||',,,'|| 
       DECODE(last_login_date, NULL, NULL, '"'||TO_CHAR(last_login_date,'DD/MM/YYYY')||'"')       
       AS mgb_identity
  FROM v03_guard_identity
 ORDER BY nt_id;

SPOOL OFF

exit
