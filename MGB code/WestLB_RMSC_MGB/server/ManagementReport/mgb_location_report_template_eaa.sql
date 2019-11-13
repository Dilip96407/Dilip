SET ECHO OFF
SET VERIFY OFF
SET FEEDBACK OFF

SET NEWPAGE 0
SET SPACE 0
SET LINESIZE 2000
SET PAGESIZE 9999
SET HEADING OFF
SET TRIMSPOOL ON
SET MARKUP HTML ON SPOOL ON
SET MARKUP HTML TABLE BORDER=0

SET MARKUP HTML ENTMAP OFF
select '<H2>MGB Report from '||to_char(&1,'YYYY-MM-DD')||' to '||to_char(&2,'YYYY-MM-DD')||'</H2>' from dual;
SET MARKUP HTML ENTMAP ON TABLE BORDER=1
SET HEADING ON

DEFINE start_date=&1
DEFINE stop_date=&2

COLUMN client HEADING "client name"
COLUMN all_trades HEADING "all trades"
COLUMN automatic_checked_trades HEADING "automatic checked trades"
COLUMN open_jobs_older_3 HEADING "open jobs older 3 days" 
COLUMN open_jobs_older_7 HEADING "open jobs older 7 days" 
COLUMN open_checks_older_3 HEADING "open checks older 3 days"
COLUMN open_checks_older_7 HEADING "open checks older 7 days"
COLUMN last_change HEADING "last tolerance review" 
COLUMN last_change FORMAT a21 


WITH trade_count AS
     (SELECT   COUNT (t02_id) AS all_trades,
               SUM (automatic_check) automatic_checked_trades, client
          FROM (SELECT t02_id,
                       DECODE(t10_is_manual_check_required,'N', 1, 0) AS automatic_check,
                       fk_t08_t09_mandant AS client
                  FROM t02_trade 
                  JOIN t44_book ON fk_t02_t44_book = t44_book_id
                  JOIN t120_report_configuration ON t44_location_trader = t120_configuration
                  JOIN t08_job ON fk_t02_t08_job = t08_id
                     AND TRUNC (cob) BETWEEN &start_date AND &stop_date
                     AND fk_t08_t09_mandant not in ('GLB','BNC','REC','DVC','MMC')
                  JOIN t04_trade_historie ON fk_t02_t04_auto_state = t04_id
                  JOIN t10_state_code ON fk_t04_t10_auto_state_code = t10_state_code
                     AND fk_t04_t10_auto_mandant_code = fk_t10_t09_state_mandant
                 WHERE t120_report_id = '&3'
                       )
      GROUP BY client
      HAVING COUNT(t02_id) > 0),
     open_jobs AS
     (SELECT   SUM (open_older_3) AS open_jobs_older_3,
               SUM (open_older_7) AS open_jobs_older_7, client
          FROM (SELECT CASE
                          WHEN TRUNC (SYSDATE - cob) > 7
                             THEN 1
                          ELSE 0
                       END AS open_older_7,
                       CASE
                          WHEN TRUNC (SYSDATE - cob) > 3
                             THEN 1
                          ELSE 0
                       END AS open_older_3,
                       fk_t08_t09_mandant AS client
                  FROM t08_job
                 WHERE t08_status IN ('OK', 'SPK_CLOSED')
                     AND fk_t08_t09_mandant not in ('GLB','BNC','REC','DVC','MMC')
                   AND TRUNC (cob) BETWEEN &start_date AND &stop_date)
      GROUP BY client),
     open_checks AS
     (SELECT   SUM (open_older_3) AS open_checks_older_3,
               SUM (open_older_7) AS open_checks_older_7, client
          FROM ((SELECT CASE
                           WHEN TRUNC (SYSDATE - cob) > 7
                              THEN 1
                           ELSE 0
                        END AS open_older_7,
                        CASE
                           WHEN TRUNC (SYSDATE - cob) > 3
                              THEN 1
                           ELSE 0
                        END AS open_older_3,
                        fk_t08_t09_mandant AS client,
                        t02_id
                   FROM t02_trade 
                   JOIN t44_book ON fk_t02_t44_book = t44_book_id
                   JOIN t120_report_configuration ON t44_location_trader = t120_configuration
                   JOIN t08_job ON fk_t02_t08_job = t08_id
                      AND TRUNC (cob) BETWEEN &start_date AND &stop_date
                      AND t08_status IN ('OK', 'SPK_CLOSED')
                     AND fk_t08_t09_mandant not in ('GLB','BNC','REC','DVC','MMC')
                   JOIN t04_trade_historie ON fk_t02_t04_auto_state = t04_id
                   JOIN t10_state_code ON fk_t04_t10_auto_state_code = t10_state_code
                      AND fk_t04_t10_auto_mandant_code = fk_t10_t09_state_mandant
                      AND t10_is_manual_check_required = 'Y'
                  WHERE fk_t02_t04_manual_state IS NULL
                    AND t120_report_id = '&3')
                UNION
                (SELECT CASE
                           WHEN TRUNC (SYSDATE - cob) > 7
                              THEN 1
                           ELSE 0
                        END AS open_older_7,
                        CASE
                           WHEN TRUNC (SYSDATE - cob) > 3
                              THEN 1
                           ELSE 0
                        END AS open_older_3,
                        fk_t08_t09_mandant AS client,
                        t02_id
                   FROM t02_trade t 
                   JOIN t44_book ON fk_t02_t44_book = t44_book_id
                   JOIN t120_report_configuration ON t44_location_trader = t120_configuration
                   JOIN t08_job j ON t.fk_t02_t08_job = j.t08_id
                      AND TRUNC (cob) BETWEEN &start_date AND &stop_date
                      AND j.t08_status <> 'CLOSED'
                     AND fk_t08_t09_mandant not in ('GLB','BNC','REC','DVC','MMC')
                   JOIN t04_trade_historie m ON t.fk_t02_t04_manual_state = m.t04_id
                   JOIN t10_state_code s ON m.fk_t04_t10_man_state_code = s.t10_state_code
                      AND m.fk_t04_t10_man_mandant_code = s.fk_t10_t09_state_mandant
                      AND s.t10_is_reclamation_required = 'Y'
                   LEFT OUTER JOIN t04_trade_historie r ON t.fk_t02_t04_recl_state = r.t04_id
                  WHERE r.t04_is_closed IS NULL
                    AND t120_report_id = '&3'))
      GROUP BY client),
      last_change AS
     (SELECT TO_CHAR(NVL (t42_changed_date, t42_creation_date), 'DD.MM.YYYY') AS last_changed,
             fk_t42_t09_mandant AS client
        FROM t42_mgb_configuration
       WHERE t42_key = 'LAST_PRICE_CHECK_REVIEW')
SELECT t09_name AS "client",
       NVL(all_trades,0) AS "all_trades",
       ROUND(NVL(automatic_checked_trades / all_trades * 100,0), 2) ||' %' AS "automatic checked trades",
       NVL(open_jobs_older_3,0) AS "open_jobs_older_3", 
       NVL(open_jobs_older_7,0) AS "open_jobs_older_7", 
       NVL(open_checks_older_3,0) AS "open_checks_older_3",
       NVL(open_checks_older_7,0) AS "open_checks_older_7",
       last_changed AS "last_change"
 FROM t09_mandant
 JOIN trade_count t ON t09_id = t.client
 LEFT OUTER JOIN open_jobs j ON t09_id = j.client
 LEFT OUTER JOIN open_checks c ON t09_id = c.client
 LEFT OUTER JOIN last_change l ON t09_id = l.client
WHERE t09_enabled = 'Y'
ORDER BY t09_name;
