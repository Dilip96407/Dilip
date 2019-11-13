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
SET COLSEP ';'

SET TAB OFF

SPOOL &1

SELECT   'UserID;UserID im System;Name;Vorname;Abteilungsnummer;Profilname;Beschreibung des Profils;Sperrkennzeichen;Letzter Login;Ende-Datum;Mandantenkennzeichen'
    FROM dual;

SELECT   nt_id ||';;'|| lastname ||';'|| firstname||';;'|| role ||';'|| role ||';0;;;'|| mandant
    FROM v02_guard_export
ORDER BY nt_id;

SPOOL OFF

exit
