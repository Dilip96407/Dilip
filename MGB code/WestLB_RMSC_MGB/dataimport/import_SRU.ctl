-- skip the header line
OPTIONS ( SKIP=1, ERRORS=999999 )
LOAD DATA
APPEND
INTO TABLE load_summit_unknown
WHEN ((1-1)!='#')
FIELDS TERMINATED BY ';' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(
    SOURCE_SYSTEM               CONSTANT "SRU",
    load_time                   SYSDATE,
    record_number               RECNUM,
    trade_id	                CHAR,
    x_status                  	FILLER,
   	trade_version				INTEGER EXTERNAL NULLIF trade_version=BLANKS,
    x_underlying_sub_type       FILLER,
    x_market_type               FILLER,
    x_openend_flag              FILLER,
    x_underlying_category		FILLER,
    x_instrument                FILLER,
    x_isin                      FILLER,
    trader		                CHAR,
    book  	                	CHAR,
    x_counterparty              FILLER,
    x_customer_type             FILLER,
    x_start_date                FILLER,
    x_end_date                  FILLER,
    x_days                      FILLER,
    trade_date                  DATE 'DDMMYYYY HH24:MI:SS' NULLIF trade_date=BLANKS,
	x_done_date					FILLER,
	x_verify_date				FILLER,
	x_amend_date				FILLER,
    x_notional_amount           FILLER,
    x_startcash                 FILLER,
    x_endcash                   FILLER,
    x_currency                  FILLER,
    x_yieldcurve                FILLER,
    x_foreign_exchange_rate     FILLER,
    x_repo_rate                 FILLER,
    x_yield_curve_rate          FILLER,
    x_funding_spread	        FILLER,
	x_market_price_underlying	FILLER,
	x_deal_accrued_interest		FILLER,
	x_start_price				FILLER,
	x_bond_accrued_interest		FILLER,
	x_npv						FILLER,
    trade_type		            CONSTANT 'REPO',
    sub_type					CONSTANT 'REPO'
)
