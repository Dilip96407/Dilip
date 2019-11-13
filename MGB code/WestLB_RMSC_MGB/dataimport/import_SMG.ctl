-- skip the header line
OPTIONS ( SKIP=1, ERRORS=999999 )
LOAD DATA
APPEND
INTO TABLE load_summit_bond
WHEN ((1-1)!='#')
FIELDS TERMINATED BY ';' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(
    load_time                 SYSDATE,
    load_status               CONSTANT "NEW",
    record_number             RECNUM,
   	trade_id				  CHAR "RTRIM(:trade_id)",
   	version					  INTEGER EXTERNAL NULLIF version=BLANKS,
	status					  CHAR "RTRIM(:status)",
	instrument				  CHAR "RTRIM(:instrument)",
	isin					  CHAR "RTRIM(:isin)",
    currency  				  CHAR,
    fx_euro_rate  			  FLOAT EXTERNAL NULLIF fx_euro_rate=BLANKS "TO_NUMBER(:fx_euro_rate, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    trade_type				  CHAR "RTRIM(:trade_type)",
    sub_type  				  CHAR "RTRIM(:sub_type)",
    category  				  CHAR "RTRIM(:category)",
--    start_day  				  DATE 'YYYY-MM-DD' NULLIF start_day='0-00-00',
--    expire_day  			  DATE 'YYYY-MM-DD' NULLIF expire_day='0-00-00',
    start_day  				  DATE 'DD.MM.YYYY' NULLIF start_day='00.00.00',
    expire_day  			  DATE 'DD.MM.YYYY' NULLIF expire_day='00.00.00',
	instrument_name			  CHAR,
--    instrument_start_day	  DATE 'YYYY-MM-DD' NULLIF instrument_start_day='0-00-00',
--    instrument_expire_day	  DATE 'YYYY-MM-DD' NULLIF instrument_expire_day='0-00-00',
    instrument_start_day	  DATE 'DD.MM.YYYY' NULLIF instrument_start_day='00.00.00',
    instrument_expire_day	  DATE 'DD.MM.YYYY' NULLIF instrument_expire_day='00.00.00',
    issuer  				  CHAR,
    book	  				  CHAR "RTRIM(:book)",
    nominal  				  FLOAT EXTERNAL NULLIF nominal=BLANKS "TO_NUMBER(:nominal, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    counterparty  			  CHAR,
    counterparty_reference    CHAR,  				    
    description               CHAR,
    alias		  			  CHAR,
    trader  				  CHAR "RTRIM(:trader)",
    trade_date  			  DATE 'YYYY-MM-DD"T"HH24:MI:SS' NULLIF trade_date=BLANKS,
    trade_time  			  DATE 'YYYY-MM-DD"T"HH24:MI:SS' NULLIF trade_time=BLANKS,
    value_date  			  DATE 'YYYYMMDD' NULLIF value_date=BLANKS,
    update_time  			  DATE 'YYYY-MM-DD"T"HH24:MI:SS' NULLIF update_time=BLANKS,
    trade_price  			  FLOAT EXTERNAL "NVL(TO_NUMBER(:trade_price, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    market_price  			  FLOAT EXTERNAL "NVL(TO_NUMBER(:market_price, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    theor_price   			  FLOAT EXTERNAL "NVL(TO_NUMBER(:theor_price, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    updated_by 				  CHAR,
    structure	  			  CHAR,
    company		  			  CHAR,
    discount	  			  FLOAT EXTERNAL "NVL(TO_NUMBER(:discount, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
	customer_type             CHAR,
	legal_name                CHAR,
	ext_note_1                CHAR,
	ext_note_2                CHAR,
    traded_yield   			  FLOAT EXTERNAL NULLIF traded_yield='NaN' "NVL(DECODE(LEAST(LENGTH(:traded_yield),25),25,0,TO_NUMBER(:traded_yield, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')), 0)",
    theor_yield				  FLOAT EXTERNAL NULLIF theor_yield='NaN' "NVL(DECODE(LEAST(LENGTH(:theor_yield),25),25,0,TO_NUMBER(:theor_yield, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')), 0)",
    wlb_yield                 FILLER,
    wlb_price                 FILLER,
    market_yield              FILLER,
    marketer				  CHAR,
    SOURCE_SYSTEM             CONSTANT "SMG"
)	 
	 