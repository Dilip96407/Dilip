-- skip the header line
OPTIONS ( SKIP=1, ERRORS=999999 )
LOAD DATA
APPEND
INTO TABLE LOAD_SUMMIT_AMEND
WHEN ((1-1)!='#')
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
TRAILING NULLCOLS
(
    load_time                SYSDATE,
    load_status              CONSTANT "NEW",
    record_number            RECNUM,
   	trade_id				 CHAR "RTRIM(:trade_id)",
   	trade_type				 CHAR "RTRIM(:trade_type)",
   	product_type			 CHAR "RTRIM(:product_type)",
   	book_id   				 CHAR "RTRIM(:book_id)",
   	order_id                 FILLER,
   	trade_version_new		 INTEGER EXTERNAL NULLIF trade_version_new=BLANKS,
   	trade_version_old		 INTEGER EXTERNAL NULLIF trade_version_old=BLANKS,
   	field_modified			 CHAR "RTRIM(:field_modified)",
   	field_value_new		     CHAR "RTRIM(:field_value_new)",
   	field_value_old		     CHAR "RTRIM(:field_value_old)",
   	field_value_change		 CHAR "RTRIM(:field_value_change)",
    SOURCE_SYSTEM            CONSTANT "SAM"                                                        
)	 
	 