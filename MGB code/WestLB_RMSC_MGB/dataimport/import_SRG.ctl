-- skip the header line
OPTIONS ( SKIP=1, ERRORS=999999 )
LOAD DATA
APPEND
INTO TABLE load_summit_repo
WHEN ((1-1)!='#')
FIELDS TERMINATED BY ';' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(
    load_time                   SYSDATE,
    load_status                 CONSTANT "NEW",
    record_number               RECNUM,
    trade_id	                CHAR,
    status                  	CHAR "RTRIM(:status)",
   	version					    INTEGER EXTERNAL NULLIF version=BLANKS,
    underlying_sub_type         CHAR "RTRIM(:underlying_sub_type)",
    market_type                	CHAR "RTRIM(:market_type)",
    openend_flag                CHAR,
    underlying_category			CHAR,
    instrument                  CHAR,
    isin                        CHAR,
    trader_id                   CHAR,
    book_id                     CHAR,
    counterparty                CHAR,
    customer_type               CHAR,
    start_date                  DATE 'YYYYMMDD' NULLIF start_date='00000000',
    end_date                    DATE 'YYYYMMDD' NULLIF end_date='OPEN',
    days                        FLOAT EXTERNAL NULLIF days=BLANKS "TO_NUMBER(:days, '999999990', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    trade_time                  DATE 'DDMMYYYY HH24:MI:SS' NULLIF trade_time=BLANKS,
	done_date					DATE 'YYYYMMDD' NULLIF done_date=BLANKS,
	verify_date					DATE 'YYYYMMDD' NULLIF verify_date=BLANKS,
	amend_date					DATE 'YYYYMMDD' NULLIF amend_date=BLANKS,
    notional_amount             FLOAT EXTERNAL NULLIF notional_amount=BLANKS "NVL(TO_NUMBER(:notional_amount, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    startcash                   FLOAT EXTERNAL NULLIF startcash=BLANKS "NVL(TO_NUMBER(:startcash, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    endcash                     FLOAT EXTERNAL NULLIF endcash=BLANKS "NVL(TO_NUMBER(:endcash, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    currency                    CHAR,
    yieldcurve                  CHAR,
    foreign_exchange_rate       FLOAT EXTERNAL NULLIF foreign_exchange_rate=BLANKS "TO_NUMBER(:foreign_exchange_rate, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    repo_rate                   FLOAT EXTERNAL NULLIF repo_rate=BLANKS "TO_NUMBER(:repo_rate, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    yield_curve_rate            FLOAT EXTERNAL DEFAULTIF yield_curve_rate=BLANKS "DECODE(GREATEST(-LENGTH(:yield_curve_rate),-22),-22,0,TO_NUMBER(:yield_curve_rate, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''))",
    funding_spread	            FLOAT EXTERNAL NULLIF funding_spread=BLANKS "NVL(TO_NUMBER(:funding_spread, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
	market_price_underlying		FLOAT EXTERNAL NULLIF market_price_underlying=BLANKS "NVL(TO_NUMBER(:market_price_underlying, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
	deal_accrued_interest		FLOAT EXTERNAL NULLIF deal_accrued_interest=BLANKS "NVL(TO_NUMBER(:deal_accrued_interest, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
	start_price					FLOAT EXTERNAL NULLIF start_price=BLANKS "NVL(TO_NUMBER(:start_price, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
	bond_accrued_interest		FLOAT EXTERNAL NULLIF bond_accrued_interest=BLANKS "NVL(TO_NUMBER(:bond_accrued_interest, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
	npv							FLOAT EXTERNAL NULLIF npv=BLANKS "NVL(TO_NUMBER(:npv, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    trade_type		            CONSTANT 'REPO',
    underlying_instrument_type  CONSTANT 'REPO',
    SOURCE_SYSTEM               CONSTANT "SRG"                                                        
)
