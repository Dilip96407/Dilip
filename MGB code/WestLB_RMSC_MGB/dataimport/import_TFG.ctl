-- skip the header line
OPTIONS ( SKIP=1, ERRORS=9 )
LOAD DATA
APPEND
INTO TABLE load_summit_fx
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
    ccy_pair                 CHAR "RTRIM(:ccy_pair)",
    type                     CHAR "RTRIM(:type)",
    sub_type                 CHAR "RTRIM(:sub_type)",
    near_date                Date 'DD.MM.YYYY' NULLIF near_date=BLANKS, 
    far_date                 Date 'DD.MM.YYYY' NULLIF far_date=BLANKS, 
    trade_date               Date 'DD.MM.YYYY HH24:MI:SS' NULLIF trade_date=BLANKS,      
    trade_entry_date         DATE 'DD.MM.YYYY HH24:MI:SS' NULLIF trade_entry_date=BLANKS,
    amend_date               DATE 'DD.MM.YYYY HH24:MI:SS' NULLIF amend_date=BLANKS,      
    spot_rate                FLOAT EXTERNAL NULLIF spot_rate=BLANKS "TO_NUMBER(:spot_rate, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    fwd_points_near_leg      FLOAT EXTERNAL NULLIF fwd_points_near_leg=BLANKS "TO_NUMBER(:fwd_points_near_leg, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    fwd_points_far_leg       FLOAT EXTERNAL NULLIF fwd_points_far_leg=BLANKS "TO_NUMBER(:fwd_points_far_leg, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    margin_points            FLOAT EXTERNAL NULLIF margin_points=BLANKS "NVL(DECODE(LEAST(LENGTH(:margin_points),30),30,0,TO_NUMBER(:margin_points, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')), 0)",
    margin_rate              FLOAT EXTERNAL NULLIF margin_rate=BLANKS "TO_NUMBER(:margin_rate, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    near_amount_ccy1         FLOAT EXTERNAL NULLIF near_amount_ccy1=BLANKS "TO_NUMBER(:near_amount_ccy1, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    far_amount_ccy1          FLOAT EXTERNAL NULLIF far_amount_ccy1=BLANKS "TO_NUMBER(:far_amount_ccy1, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    market_rate_spot         FLOAT EXTERNAL NULLIF market_rate_spot=BLANKS "TO_NUMBER(:market_rate_spot, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    market_points_near_leg   FLOAT EXTERNAL NULLIF market_points_near_leg=BLANKS "TO_NUMBER(:market_points_near_leg, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    market_points_far_leg    FLOAT EXTERNAL NULLIF market_points_far_leg=BLANKS "TO_NUMBER(:market_points_far_leg, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    market_rate_fwd          FLOAT EXTERNAL NULLIF market_rate_fwd=BLANKS "TO_NUMBER(:market_rate_fwd, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    trader_login_id          CHAR "RTRIM(:trader_login_id)",
    trader_name              CHAR "RTRIM(:trader_name)",    
    counterparty             CHAR "RTRIM(:counterparty)",
    book                     CHAR "RTRIM(:book)",           
    comment1                 CHAR "RTRIM(:comment1)",        
    eod_rate                 FLOAT EXTERNAL NULLIF eod_rate=BLANKS "TO_NUMBER(:eod_rate, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    eod_rate_date            Date 'DD.MM.YYYY' NULLIF eod_rate_date=BLANKS,
    eod_rate_fx              FLOAT EXTERNAL NULLIF eod_rate_fx=BLANKS "TO_NUMBER(:eod_rate_fx, '9999999999999999999990D0999999999999999999999', 'NLS_NUMERIC_CHARACTERS=''.,''')",
    SOURCE_SYSTEM            CONSTANT "TFG"                                                        
)