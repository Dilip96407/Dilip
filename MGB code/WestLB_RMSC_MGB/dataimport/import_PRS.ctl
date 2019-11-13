OPTIONS ( SKIP=1, ERRORS=999999 )
Load DATA
APPEND
INTO TABLE LOAD_PARIS_BOOK
FIELDS TERMINATED BY ";" OPTIONALLY ENCLOSED BY '"' 
TRAILING NULLCOLS 
(                                                                                                                                                           
       load_time                                 SYSDATE,
       load_status                               CONSTANT "NEW",
	   record_number            				 RECNUM,
       BOOK_ID                                   CHAR,                                                      -- COLUMN BOOK_CODE                                                                       
       BOOK_DESCRIPTION                          CHAR,                                                      -- COLUMN BOOK_DESCRIPTION                                                                       
       ACCOUNT                                   INTEGER	EXTERNAL NULLIF ACCOUNT='[n/a]',                -- COLUMN ACCOUNT
       ACCOUNTING_BRANCH                         INTEGER	EXTERNAL NULLIF ACCOUNTING_BRANCH='[n/a]',      -- COLUMN ACCOUNTING_BRANCH                                                                      
       ACCOUNTING_SYSTEM                         CHAR,                                                      -- COLUMN ACCOUNTING_SYSTEM                                                                      
       BOOK_TYPE                                 CHAR,                                                      -- COLUMN BOOK_TYPE                                                                      
       BUSINESS_UNIT                             CHAR,                                                      -- COLUMN BUSINESS_UNIT                                                                         
       COST_CENTER                               INTEGER	EXTERNAL NULLIF COST_CENTER='[n/a]',            -- COLUMN COST_CENTER                                                                       
       COST_CENTER_NAME                          CHAR,                                                      -- COLUMN COST_CENTER_NAME                                                                        
       DESK                                      CHAR,                                                      -- COLUMN DESK                                                                      
       FO_SYSTEM                                 CHAR,                                                      -- COLUMN FO_SYSTEM                                                                        
       LIMIT_POSITION                            CHAR,                                                      -- COLUMN LIMIT_POSITION                                                                         
       LOCATION_CONSOLD                          CHAR,                                                      -- COLUMN LOCATION_CONSOLIDATION                                                                        
       LOCATION_GL                               CHAR,                                                      -- COLUMN LOCATION_GL                                                                        
       LOCATION_PL                               CHAR,                                                      -- COLUMN LOCATION_PL                                                                        
       LOCATION_TRADER                           CHAR,                                                      -- COLUMN LOCATION_TRADER                                                                        
       MPIRE_LU_NAME                             CHAR,                                                      -- COLUMN MPIRE_LU_NAME                                                                        
       PCR_PL_DESK                               CHAR,                                                      -- COLUMN  PCR_PL_DESK                                                                       
       PCR_PL_GROUP                              CHAR,                                                      -- COLUMN  PCR_PL_GROUP                                                                        
       TRADER                                    CHAR,                                                      -- COLUMN TRADER                                                                        
       TRADING_BANKING                           CHAR,                                                      -- COLUMN TRADING_BANKING                                                                        
       TRADING_HEAD                              CHAR,                                                      -- COLUMN TRADING_HEAD                                                                        
       TRADING_TYPE                              CHAR,                                                      -- COLUMN TRADING_TYPE                                                                        
	   WERS_UNIT                                 INTEGER	EXTERNAL NULLIF WERS_UNIT='[n/a]',              -- COLUMN WERS_UNIT     
	   SOURCE_SYSTEM                             CONSTANT "PRS"                                                        
)