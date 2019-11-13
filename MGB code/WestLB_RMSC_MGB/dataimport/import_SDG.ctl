-- skip the header line
OPTIONS ( SKIP=1, ERRORS=999999 )
LOAD DATA
APPEND
INTO TABLE load_summit_derivative
WHEN ((1-1)!='#')
FIELDS TERMINATED BY ';' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(
    load_time                 SYSDATE,
    load_status               CONSTANT "NEW",
    record_number             RECNUM,
    as_of_date				  DATE 'DD/MM/YY',
    trade_type				  CHAR "RTRIM(:trade_type)",
    trade_sub_type			  CHAR "RTRIM(:trade_sub_type)",
    trade_id				  CHAR "RTRIM(:trade_id)",
    deal_id					  CHAR "RTRIM(:deal_id)",
    version					  CHAR "RTRIM(:version)",
    audit_current			  CHAR "RTRIM(:audit_current)",
    structure_id			  CHAR "RTRIM(:structure_id)",
    trade_status			  CHAR "RTRIM(:trade_status)",
    trade_currency			  CHAR "RTRIM(:trade_currency)",
    asset_id				  CHAR "RTRIM(:asset_id)",
    call_put				  CHAR "RTRIM(:call_put)",
    pay_currency			  CHAR,
    pay_notional			  FLOAT EXTERNAL NULLIF pay_notional=BLANKS "NVL(TO_NUMBER(:pay_notional, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    receive_currency		  CHAR,
    receive_notional		  FLOAT EXTERNAL NULLIF receive_notional=BLANKS "NVL(TO_NUMBER(:receive_notional, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    start_date				  DATE 'YYYY-MM-DD' NULLIF start_date='0-00-00',
    maturity_date			  DATE 'YYYY-MM-DD' NULLIF maturity_date='0-00-00',
    pay_index_basis			  CHAR "RTRIM(:pay_index_basis)",
    pay_rate_spread			  FLOAT EXTERNAL NULLIF pay_rate_spread=BLANKS "NVL(TO_NUMBER(:pay_rate_spread, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    pay_discount_curve		  CHAR "RTRIM(:pay_discount_curve)",
    receive_index_basis		  CHAR "RTRIM(:receive_index_basis)",
    receive_rate_spread		  FLOAT EXTERNAL NULLIF receive_rate_spread=BLANKS "NVL(TO_NUMBER(:receive_rate_spread, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    receive_discount_curve	  CHAR "RTRIM(:receive_discount_curve)",
    cap_floor				  CHAR "RTRIM(:cap_floor)",
    buy_sell				  CHAR "RTRIM(:buy_sell)",
    quantity				  FLOAT EXTERNAL NULLIF quantity=BLANKS "NVL(TO_NUMBER(:quantity, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    underlying				  CHAR,
    strike					  FLOAT EXTERNAL NULLIF strike=BLANKS "NVL(TO_NUMBER(:strike, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    style					  CHAR,
    premium_amount			  FLOAT EXTERNAL NULLIF premium_amount=BLANKS "NVL(TO_NUMBER(:premium_amount, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    premium_currency		  CHAR,
    expiry_date				  DATE 'YYYY-MM-DD' NULLIF expiry_date=BLANKS,
    fixing_date				  DATE 'YYYY-MM-DD' NULLIF fixing_date=BLANKS,
    payment_date			  DATE 'YYYY-MM-DD' NULLIF payment_date=BLANKS,
    first_coupon_date		  DATE 'YYYY-MM-DD' NULLIF first_coupon_date=BLANKS,
    model					  CHAR "RTRIM(:model)",
    instrument_style		  CHAR "RTRIM(:instrument_style)",
    formula					  CHAR "RTRIM(:formula)",
    product_name			  CHAR "RTRIM(:product_name)",
    prod_data				  CHAR "RTRIM(:prod_data)",
    npv						  FLOAT EXTERNAL NULLIF npv='NaN' "NVL(DECODE(LEAST(LENGTH(:npv),30),30,0,TO_NUMBER(:npv, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')), 0)",
    market_rate_ir			  FLOAT EXTERNAL NULLIF market_rate_ir=BLANKS "NVL(TO_NUMBER(:market_rate_ir, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    market_rate_vola		  FLOAT EXTERNAL NULLIF market_rate_vola=BLANKS "NVL(TO_NUMBER(:market_rate_vola, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    fx_rate					  FLOAT EXTERNAL NULLIF fx_rate='Inf' "NVL(TO_NUMBER(:fx_rate, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    delta					  FLOAT EXTERNAL NULLIF delta='NaN' "NVL(DECODE(LEAST(LENGTH(:delta),30),30,0,TO_NUMBER(:delta, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')), 0)",
    delta_currency			  CHAR,
    vega					  FLOAT EXTERNAL NULLIF vega=BLANKS "NVL(TO_NUMBER(:vega, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,'''), 0)",
    vega_currency			  CHAR,
    exercised				  CHAR "RTRIM(:exercised)",
    done_date				  DATE 'YYYY-MM-DD HH24:MI:SS' NULLIF done_date=BLANKS,
    verify_date				  DATE 'YYYY-MM-DD HH24:MI:SS' NULLIF verify_date=BLANKS,
    amend_date				  DATE 'YYYY-MM-DD HH24:MI:SS' NULLIF amend_date=BLANKS,
    cancel_date				  DATE 'YYYY-MM-DD HH24:MI:SS' NULLIF cancel_date=BLANKS,
    comment1				  CHAR "RTRIM(:comment1)",
    comment2				  CHAR "RTRIM(:comment2)",
    counterparty			  CHAR "RTRIM(:counterparty)",
    internal_external		  CHAR "RTRIM(:internal_external)",
    book					  CHAR "RTRIM(NVL(:book,'MGB_DUMMY_DERIVATIVE_BOOK'))",
    desk					  CHAR "RTRIM(:desk)",
    company					  CHAR "RTRIM(:company)",
    trader					  CHAR "RTRIM(:trader)",
    trade_date				  DATE 'YYYY-MM-DD' NULLIF done_date=BLANKS,
    amend_user				  CHAR "RTRIM(:amend_user)",
    audit_date				  DATE 'YYYY-MM-DD HH24:MI:SS' NULLIF done_date=BLANKS,
    audit_user				  CHAR "RTRIM(:audit_user)",
    term_assign_status		  CHAR "RTRIM(:term_assign_status)",
    term_trade_date			  DATE 'YYYY-MM-DD' NULLIF term_trade_date=BLANKS,
    term_input_date			  DATE 'YYYY-MM-DD' NULLIF term_input_date=BLANKS,
    term_eff_date			  DATE 'YYYY-MM-DD' NULLIF term_eff_date=BLANKS,    
    reset_remark			  CHAR "RTRIM(:reset_remark)",
    SOURCE_SYSTEM             CONSTANT "SDG"                                                        
)	 
	 