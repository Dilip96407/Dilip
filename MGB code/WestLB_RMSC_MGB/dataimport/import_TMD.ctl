-- skip the header line
OPTIONS ( SKIP=1, ERRORS=999999 )
LOAD DATA
APPEND
INTO TABLE load_summit_mm
WHEN ((1-1)!='#')
FIELDS TERMINATED BY ';' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(
    record_number            RECNUM,
    load_time                SYSDATE,
    load_status              CONSTANT "NEW",
    trade_id                 CHAR "RTRIM(:trade_id)",
    trade_version            INTEGER EXTERNAL NULLIF trade_version=BLANKS,
    status                   CHAR "RTRIM(:status)",
    ccy                      CHAR,
    mm_type                  CHAR "RTRIM(:mm_type)",
    sub_type                 CHAR "RTRIM(:sub_type)",
    mm_index                 CHAR "RTRIM(:mm_index)",
    start_date               Date 'DD.MM.YYYY' NULLIF start_date=BLANKS, 
    maturity_date            Date 'DD.MM.YYYY' NULLIF maturity_date='00.00.0000',
    trade_date               Date 'DD.MM.YYYY HH24:MI:SS' NULLIF trade_date=BLANKS,
    trade_entry_date         DATE 'DD.MM.YYYY HH24:MI:SS' NULLIF trade_entry_date=BLANKS,
    amend_date               DATE 'DD.MM.YYYY HH24:MI:SS' NULLIF amend_date=BLANKS,
    internal_rate            FLOAT EXTERNAL NULLIF internal_rate=BLANKS "TO_NUMBER(:internal_rate, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    spread                   FLOAT EXTERNAL NULLIF spread=BLANKS "TO_NUMBER(:spread, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    margin_points            FLOAT EXTERNAL NULLIF margin_points=BLANKS "TO_NUMBER(:margin_points, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    dealt_rate               FLOAT EXTERNAL NULLIF dealt_rate=BLANKS "TO_NUMBER(:dealt_rate, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    market_rate_1            FLOAT EXTERNAL NULLIF market_rate_1=BLANKS "TO_NUMBER(:market_rate_1, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    market_rate_2            FLOAT EXTERNAL NULLIF market_rate_2=BLANKS "TO_NUMBER(:market_rate_2, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    notional                 FLOAT EXTERNAL NULLIF notional=BLANKS "TO_NUMBER(:notional, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    trader_login_id          CHAR "RTRIM(:trader_login_id)",
    trader_name              CHAR "RTRIM(:trader_name)",
    counterparty             CHAR "RTRIM(:counterparty)",
    book                     CHAR "RTRIM(:book)",
    comment1                 CHAR "RTRIM(:comment1)",
    structure_id             CHAR "RTRIM(:structure_id)",
    eod_rate                 FLOAT EXTERNAL NULLIF eod_rate=BLANKS "TO_NUMBER(:eod_rate, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    eod_rate_date            Date 'DD.MM.YYYY' NULLIF eod_rate_date=BLANKS,
    auto_rate_reset          CHAR "RTRIM(:auto_rate_reset)",
    eod_rate_mm              FLOAT EXTERNAL NULLIF eod_rate_mm=BLANKS "NVL(DECODE(:eod_rate_mm,'Inf',0,DECODE(LEAST(LENGTH(:eod_rate_mm),30),30,0,TO_NUMBER(:eod_rate_mm, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''))), 0)",
    SOURCE_SYSTEM            CONSTANT "TMD"
)
