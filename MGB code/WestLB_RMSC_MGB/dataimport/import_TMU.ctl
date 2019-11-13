-- skip the header line
OPTIONS ( SKIP=1, ERRORS=999999 )
LOAD DATA
APPEND
INTO TABLE load_summit_unknown
WHEN ((1-1)!='#')
FIELDS TERMINATED BY ';' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(
	SOURCE_SYSTEM            CONSTANT "TMU",
    record_number            RECNUM,
    load_time                SYSDATE,
    trade_id                 CHAR "RTRIM(:trade_id)",
    trade_version            INTEGER EXTERNAL NULLIF trade_version=BLANKS,
    x_status                 FILLER,
    x_ccy                    FILLER,
    trade_type               CHAR "RTRIM(:trade_type)",
    sub_type                 CHAR "RTRIM(:sub_type)",
    x_mm_index               FILLER,
    x_start_date             FILLER, 
    x_maturity_date          FILLER,
    trade_date               Date 'DD.MM.YYYY HH24:MI:SS' NULLIF trade_date=BLANKS,
    x_trade_entry_date       FILLER,
    x_amend_date             FILLER,
    x_internal_rate          FILLER,
    x_spread                 FILLER,
    x_margin_points          FILLER,
    x_dealt_rate             FILLER,
    x_market_rate_1          FILLER,
    x_market_rate_2          FILLER,
    x_notional               FILLER,
    x_trader_login_id        FILLER,
    trader		             CHAR "RTRIM(:trader)",
    x_counterparty           FILLER,
    book                     CHAR "RTRIM(:book)",
    x_comment1               FILLER,
    x_structure_id           FILLER,
    x_eod_rate               FILLER,
    x_eod_rate_date          FILLER,
    x_auto_rate_reset        FILLER,
    x_eod_rate_mm            FILLER
)
