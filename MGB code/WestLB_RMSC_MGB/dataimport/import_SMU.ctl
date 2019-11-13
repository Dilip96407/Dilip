-- skip the header line
OPTIONS ( SKIP=1, ERRORS=999999 )
LOAD DATA
APPEND
INTO TABLE load_summit_unknown
WHEN ((1-1)!='#')
FIELDS TERMINATED BY ';' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(
    SOURCE_SYSTEM             CONSTANT "SMU",
    load_time                 SYSDATE,
    record_number             RECNUM,
   	trade_id				  CHAR "RTRIM(:trade_id)",
   	trade_version			  INTEGER EXTERNAL NULLIF trade_version=BLANKS,
	x_status				  FILLER,
	x_instrument			  FILLER,
	x_isin					  FILLER,
    x_currency  			  FILLER,
    x_fx_euro_rate  		  FILLER,
    trade_type				  CHAR "RTRIM(:trade_type)",
    sub_type  				  CHAR "RTRIM(:sub_type)",
    x_category  			  FILLER,
    X_start_day  			  FILLER,
    x_expire_day  			  FILLER,
	x_instrument_name		  FILLER,
    x_instrument_start_day	  FILLER,
    x_instrument_expire_day	  FILLER,
    x_issuer  				  FILLER,
    book	  				  CHAR "RTRIM(:book)",
    x_nominal  				  FILLER,
    x_counterparty  		  FILLER,
    x_counterparty_reference  FILLER, 				    
    x_description             FILLER,
    x_alias		  			  FILLER,
    trader  				  CHAR "RTRIM(:trader)",
    trade_date  			  DATE 'YYYY-MM-DD"T"HH24:MI:SS' NULLIF trade_date=BLANKS,
    x_trade_time  			  FILLER,
    x_value_date  			  FILLER,
    x_update_time  			  FILLER,
    x_trade_price  			  FILLER,
    x_market_price  		  FILLER,
    x_theor_price   		  FILLER,
    x_updated_by 			  FILLER,
    x_structure	  			  FILLER,
    x_company		  		  FILLER,
    x_discount	  			  FILLER,
	x_customer_type           FILLER,
	x_legal_name              FILLER,
	x_ext_note_1              FILLER,
	x_ext_note_2              FILLER,
    x_traded_yield   		  FILLER,
    x_theor_yield			  FILLER,
    x_wlb_yield               FILLER,
    x_wlb_price               FILLER,
    x_market_yield            FILLER,
    X_marketer				  FILLER
)	 
	 