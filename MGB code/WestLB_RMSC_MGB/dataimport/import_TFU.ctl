-- skip the header line
OPTIONS ( SKIP=1, ERRORS=9 )
LOAD DATA
APPEND
INTO TABLE load_summit_unknown
WHEN ((1-1)!='#')
FIELDS TERMINATED BY ';' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(
    SOURCE_SYSTEM            CONSTANT "TFU",
    record_number            RECNUM,
    load_time                SYSDATE,
    trade_id                 CHAR "RTRIM(:trade_id)",
    trade_version            INTEGER EXTERNAL NULLIF trade_version=BLANKS,
    x_status                 FILLER,
    x_ccy_pair               FILLER,
    trade_type               CHAR "RTRIM(:trade_type)",
    sub_type                 CHAR "RTRIM(:sub_type)",
    x_near_date              FILLER, 
    x_far_date               FILLER,
    trade_date               Date 'DD.MM.YYYY HH24:MI:SS' NULLIF trade_date=BLANKS,      
    x_trade_entry_date       FILLER,
    x_amend_date             FILLER,,      
    x_spot_rate              FILLER,
    x_fwd_points_near_leg    FILLER,
    x_fwd_points_far_leg     FILLER,
    x_margin_points          FILLER,
    x_margin_rate            FILLER,
    x_near_amount_ccy1       FILLER,
    x_far_amount_ccy1        FILLER,
    x_market_rate_spot       FILLER,
    x_market_points_near_leg FILLER,
    x_market_points_far_leg  FILLER,
    x_market_rate_fwd        FILLER,
    x_trader_login_id        FILLER,
    trader              	 CHAR "RTRIM(:trader)",    
    x_counterparty           FILLER,
    book                     CHAR "RTRIM(:book)",           
    x_comment1               FILLER,        
    x_eod_rate               FILLER,
    x_eod_rate_date          FILLER,
    x_eod_rate_fx            FILLER
)