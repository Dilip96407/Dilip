drop table T01_EMPLOYEE cascade constraints;
drop table T02_TRADE cascade constraints;
drop table T03_EXCHANGE cascade constraints;
drop table T04_TRADE_HISTORIE cascade constraints;
drop table T05_INSTRUMENT cascade constraints;
drop table T06_REQUEST cascade constraints;
drop table T07_SOURCE_SYSTEM cascade constraints;
drop table T08_JOB cascade constraints;
drop table T09_MANDANT cascade constraints;
drop table T100_SUMMIT_MM_DUS cascade constraints;
drop table T101_SUMMIT_FX cascade constraints;
drop table T102_SUMMIT_FX_DUS cascade constraints;
drop table T103_SUMMIT_AMEND_LOAD cascade constraints;
drop table T104_SUMMIT_AMEND cascade constraints;
drop table T105_LOANIQ_BOND_LON_LOAD cascade constraints;
drop table T106_LOANIQ_BOND_LON cascade constraints;
drop table T107_SUMMIT_BOND_EAA cascade constraints;
drop table T108_SUMMIT_REPO_EAA cascade constraints;
drop table T109_SUMMIT_DERIVATIVE_EAA cascade constraints;
drop table T10_STATE_CODE cascade constraints;
drop table T110_SUMMIT_MM_LON cascade constraints;
drop table T111_SUMMIT_MM_NYC cascade constraints;
drop table T112_SUMMIT_MM_HKG cascade constraints;
drop table T113_SUMMIT_MM_EAA cascade constraints;
drop table T114_SUMMIT_FX_LON cascade constraints;
drop table T115_SUMMIT_FX_NYC cascade constraints;
drop table T116_SUMMIT_FX_HKG cascade constraints;
drop table T117_SUMMIT_FX_EAA cascade constraints;
drop table T119_REQUEST_MAPPING cascade constraints;
drop table T11_PRICE_CHECK_CATEGORY cascade constraints;
drop table T120_REPORT_CONFIGURATION cascade constraints;
drop table T12_PRICE cascade constraints;
drop table T13_ADDON cascade constraints;
drop table T14_MARKET_DATA_KEY cascade constraints;
drop table T15_REPORT_IMAGE cascade constraints;
drop table T16_DUAL_CONTROL_JOB cascade constraints;
drop table T17_USER_ROLE cascade constraints;
drop table T18_TRADER cascade constraints;
drop table T19_FIDESSA_EQUITY_LOAD cascade constraints;
drop table T20_FIDESSA_EQUITY cascade constraints;
drop table T21_EXCHANGE_MAPPING cascade constraints;
drop table T22_EXCHANGE_MAPPING_PRIORITY cascade constraints;
drop table T23_EQUITY cascade constraints;
drop table T24_PRIME_EQUITY cascade constraints;
drop table T25_BOND cascade constraints;
drop table T26_REPO cascade constraints;
drop table T27_MONEY_MARKET cascade constraints;
drop table T28_CONDITION cascade constraints;
drop table T29_ROLE cascade constraints;
drop table T30_ATLAS_BOND cascade constraints;
drop table T31_ATLAS_REPO cascade constraints;
drop table T32_SUNGARD_MONEY_MARKET cascade constraints;
drop table T33_ATLAS_REPO_LOAD cascade constraints;
drop table T34_STATE_RULES cascade constraints;
drop table T35_PRIME_EQUITY_LOAD cascade constraints;
drop table T36_SUNGARD_MONEY_MARKET_LOAD cascade constraints;
drop table T37_SUNGARD_FX_LOAD cascade constraints;
drop table T38_SUNGARD_FOREIGN_EXCHANGE cascade constraints;
drop table T39_SUNGARD_TRADE_TYPE cascade constraints;
drop table T40_BLOOMBERG_CURRENCY_CODES cascade constraints;
drop table T41_BLOOMBERG_MATURITY_CODES cascade constraints;
drop table T42_MGB_CONFIGURATION cascade constraints;
drop table T43_CHANGE_REVISION_LOG cascade constraints;
drop table T44_BOOK cascade constraints;
drop table T45_PARIS_BOOK_LOAD cascade constraints;
drop table T46_ATLAS_BOND_LOAD cascade constraints;
drop table T47_ACCESS_CONTROL cascade constraints;
drop table T48_MAIL cascade constraints;
drop table T49_RATING_CATEGORY cascade constraints;
drop table T50_RATING_CATEGORY_MAPPING cascade constraints;
drop table T51_CURRENCY_CATEGORY cascade constraints;
drop table T52_CURRNCY_CATEGORY_MAPPING cascade constraints;
drop table T53_TRADE_TYPE_CATEGORY cascade constraints;
drop table T54_TRADETYPE_CATEGORY_MAPPING cascade constraints;
drop table T55_PRIME_REPO cascade constraints;
drop table T56_PRIME_REPO_LOAD cascade constraints;
drop table T57_SUMMIT_BOND cascade constraints;
drop table T58_SUMMIT_BOND_LOAD cascade constraints;
drop table T59_SUMMIT_REPO cascade constraints;
drop table T61_TIME_PERIOD_CATEGORY cascade constraints;
drop table T62_MAIL_RECIPIENT cascade constraints;
drop table T63_MGB_TASK cascade constraints;
drop table T64_SUMMIT_BOND_DUS cascade constraints;
drop table T65_SUMMIT_BOND_LON cascade constraints;
drop table T66_SPARKASSE cascade constraints;
drop table T67_COUNTERPARTY cascade constraints;
drop table T68_SUMMIT_BOND_LUX cascade constraints;
drop table T69_SUNGARD_MM_LUX cascade constraints;
drop table T70_SUNGARD_FX_LUX cascade constraints;
drop table T71_SUNGARD_FX_LUX_LOAD cascade constraints;
drop table T72_SUNGARD_MM_LUX_LOAD cascade constraints;
drop table T73_SUMMIT_BOND_ASIA cascade constraints;
drop table T74_SUMMIT_REPO_DUS cascade constraints;
drop table T75_SUMMIT_REPO_LON cascade constraints;
drop table T76_SUNGARD_MM_LON cascade constraints;
drop table T77_SUNGARD_FX_LON cascade constraints;
drop table T78_SUNGARD_FX_LON_LOAD cascade constraints;
drop table T79_SUNGARD_MM_LON_LOAD cascade constraints;
drop table T80_SUMMIT_REPO_ASIA cascade constraints;
drop table T81_SUMMIT_BOND_HKG cascade constraints;
drop table T82_SUNGARD_MM_HKG cascade constraints;
drop table T83_SUNGARD_FX_HKG cascade constraints;
drop table T84_SUNGARD_MM_TOK cascade constraints;
drop table T85_SUNGARD_FX_TOK cascade constraints;
drop table T86_SUNGARD_MM_NYC cascade constraints;
drop table T87_SUNGARD_FX_NYC cascade constraints;
drop table T88_SUNGARD_FX_NYC_LOAD cascade constraints;
drop table T89_SUNGARD_MM_NYC_LOAD cascade constraints;
drop table T90_SUMMIT_DERIVATIVE_LOAD cascade constraints;
drop table T91_SUMMIT_DERIVATIVE cascade constraints;
drop table T92_ASSET cascade constraints;
drop table T93_SUMMIT_DERIVATIVE_DEFAULT cascade constraints;
drop table T94_SUMMIT_DERIVATIVE_EXOTIC cascade constraints;
drop table T95_SUMMIT_REPO_HKG cascade constraints;
drop table T96_MM_FX cascade constraints;
drop table T97_SUMMIT_MM_DUS_LOAD cascade constraints;
drop table T98_SUMMIT_FX_DUS_LOAD cascade constraints;
drop table T99_SUMMIT_MM cascade constraints;
drop table t118_request_mapping_load cascade constraints;
drop table t60_SUMMIT_REPO_LOAD cascade constraints;
drop sequence HIB_SEQ;
create table T01_EMPLOYEE (
    T01_ID number(19,0) not null,
    T01_NT_ID varchar2(10),
    FK_T01_T02_MANDANT VARCHAR2(3),
    T01_FIRSTNAME varchar2(255),
    T01_LASTNAME varchar2(255),
    T01_PHONE varchar2(255),
    T01_EMAIL varchar2(255),
    T01_LAST_LOGIN_DATE date,
    T01_CHANGED_DATE date,
    T01_CHANGED_BY varchar2(10),
    T01_CREATED_BY varchar2(10),
    T01_CREATION_DATE date,
    primary key (T01_ID)
);
create table T02_TRADE (
    T02_ID number(19,0) not null,
    T02_AMENDED_DATE date,
    T02_BOOK_ID varchar2(100),
    FK_T02_T44_BOOK varchar2(100),
    T02_COUNTERPARTY_ID varchar2(50),
    T02_CURRENCY varchar2(3),
    FK_T02_T04_AUTO_STATE number(19,0),
    FK_T02_T04_MANUAL_STATE number(19,0),
    FK_T02_T04_RECL_STATE number(19,0),
    FK_T02_T05_INSTRUMENT number(19,0),
    FK_T02_T08_JOB number(19,0),
    T02_ORDER_ID varchar2(50),
    T02_PRICE double precision,
    T02_SETTLEMENT_DATE date,
    FK_T02_T07_SOURCE_SYSTEM VARCHAR2(3),
    T02_SRC_BLOOMBERG_ID varchar2(255),
    T02_SRC_INSTRUMENT varchar2(50),
    T02_SRC_REUTERS_ID varchar2(255),
    T02_SRC_SYSTEM_DATE date,
    T02_SRC_TRADE_ID varchar2(255),
    FK_T02_T18_TRADER number(19,0),
    T02_SRC_TRADER_ID varchar2(255),
    T02_SRC_TRADER_NAME varchar2(255),
    T02_TRADE_DATE date,
    T02_VOLUME double precision,
    T02_TRADE_GROUP_ID varchar2(255),
    T02_IS_TRADE_GROUP char(1),
    T02_IS_LATE_DEAL char(1),
    FK_T02_T09_MANDANT VARCHAR2(3),
    FK_T02_T67_COUNTERPARTY number(19,0),
    T02_CHANGED_DATE date,
    T02_CHANGED_BY varchar2(10),
    T02_CREATED_BY varchar2(10),
    T02_CREATION_DATE date,
    primary key (T02_ID)
);
create table T03_EXCHANGE (
    T03_ID number(19,0) not null,
    T03_BLOOMBERG_ID varchar2(50),
    T03_REUTERS_ID varchar2(50),
    FK_T03_T09_MANDANT VARCHAR2(3),
    T03_CHANGED_DATE date,
    T03_CHANGED_BY varchar2(10),
    T03_CREATED_BY varchar2(10),
    T03_CREATION_DATE date,
    primary key (T03_ID)
);
create table T04_TRADE_HISTORIE (
    T04_ID number(19,0) not null,
    T04_STATE_TYPE varchar2(20) not null,
    FK_T04_T02_TRADE number(19,0),
    FK_T04_T01_CREATED_BY number(19,0),
    T04_STATE_TIME date,
    T04_COMMENT varchar2(1000),
    T04_CHANGED_DATE date,
    T04_CHANGED_BY varchar2(10),
    T04_CREATED_BY varchar2(10),
    T04_CREATION_DATE date,
    FK_T04_T10_AUTO_STATE_CODE VARCHAR2(50),
    FK_T04_T10_AUTO_MANDANT_CODE VARCHAR2(3),
    FK_T04_T10_MAN_STATE_CODE VARCHAR2(50),
    FK_T04_T10_MAN_MANDANT_CODE VARCHAR2(3),
    FK_T04_T15_REPORT_IMAGE number(19,0),
    FK_T04_T10_RECL_STATE_CODE VARCHAR2(50),
    FK_T04_T10_RECL_MANDANT_CODE VARCHAR2(3),
    T04_IS_CLOSED char(1),
    T04_CLOSED_COMMENT varchar2(255),
    T04_LEVEL number(10,0),
    primary key (T04_ID)
);
create table T05_INSTRUMENT (
    T05_ID number(19,0) not null,
    T05_INSTRUMENT_TYPE varchar2(10) not null,
    T05_INSTRUMENT varchar2(50),
    T05_INSTRUMENT_NAME varchar2(100),
    FK_T05_T09_MANDANT VARCHAR2(3),
    T05_ENABLED char(1),
    T05_CHANGED_DATE date,
    T05_CHANGED_BY varchar2(10),
    T05_CREATED_BY varchar2(10),
    T05_CREATION_DATE date,
    T05_BLB_REQUEST_SOURCES varchar2(255),
    FK_T05_T11_PRICE_CHECK number(19,0),
    primary key (T05_ID)
);
create table T06_REQUEST (
    T06_ID number(19,0) not null,
    T06_TYPE varchar2(3) not null,
    FK_T06_T13_ADDON number(19,0),
    FK_T06_T02_TRADE number(19,0),
    FK_T06_T11_PRICE_CHECK_CAT number(19,0),
    T06_REQUEST_STATE varchar2(255),
    T06_REQUEST_TYPE varchar2(20),
    T06_REQUEST_DATE date,
    T06_REQUEST_STRING varchar2(255),
    FK_T06_T12_PRICE_RESULT number(19,0),
    FK_T06_T09_MANDANT VARCHAR2(3),
    T06_CHANGED_DATE date,
    T06_CHANGED_BY varchar2(10),
    T06_CREATED_BY varchar2(10),
    T06_CREATION_DATE date,
    T06_REQUEST_FIELD varchar2(50),
    T06_REQUEST_SOURCES varchar2(255),
    primary key (T06_ID)
);
create table T07_SOURCE_SYSTEM (
    T07_ID varchar2(3) not null,
    T07_NAME varchar2(50),
    T07_HAS_SPK_TRADES char(1),
    FK_T07_T09_MANDANT VARCHAR2(3),
    T07_CHANGED_DATE date,
    T07_CHANGED_BY varchar2(10),
    T07_CREATED_BY varchar2(10),
    T07_CREATION_DATE date,
    primary key (T07_ID)
);
create table T08_JOB (
    T08_ID number(19,0) not null,
    T08_START_LOAD_TIME date,
    T08_STOP_LOAD_TIME date,
    T08_START_CONVERT_TIME date,
    T08_STOP_CONVERT_TIME date,
    T08_START_BUSINESS_TIME date,
    T08_STOP_BUSINESS_TIME date,
    T08_SYSTEM_TIME date,
    T08_STATUS varchar2(255),
    T08_TOTAL_RECORDS number(10,0),
    T08_LOAD_ERRORS number(10,0),
    T08_CONVERT_ERRORS number(10,0),
    FK_T08_T09_MANDANT VARCHAR2(3),
    FK_T08_T07_SOURCE_SYSTEM VARCHAR2(3),
    T08_CHANGED_DATE date,
    T08_CHANGED_BY varchar2(10),
    T08_CREATED_BY varchar2(10),
    T08_CREATION_DATE date,
    T08_ARCHIVED char(1),
    T08_ARCHIVE_FILE varchar2(100),
    primary key (T08_ID)
);
create table T09_MANDANT (
    T09_ID varchar2(3) not null,
    T09_NAME varchar2(50),
    T09_CHANGED_DATE date,
    T09_CHANGED_BY varchar2(10),
    T09_CREATED_BY varchar2(10),
    T09_CREATION_DATE date,
    primary key (T09_ID)
);
create table T100_SUMMIT_MM_DUS (
    T100_ID number(19,0) not null,
    primary key (T100_ID)
);
create table T101_SUMMIT_FX (
    T101_ID number(19,0) not null,
    t101_trade_version number(10,0),
    t101_status varchar2(50),
    t101_type varchar2(50),
    t101_sub_type varchar2(50),
    t101_far_date date,
    t101_spot_rate double precision,
    t101_fwd_points_near_leg double precision,
    t101_fwd_points_far_leg double precision,
    t101_margin_points double precision,
    T101_MARKET_RATE double precision,
    t101_far_amount double precision,
    t101_market_points_near_leg double precision,
    t101_market_points_far_leg double precision,
    T101_info varchar2(255),
    t101_eod_rate double precision,
    t101_eod_rate_date date,
    t101_eod_rate_fx double precision,
    T101_BAGATELLE_LIMIT double precision,
    T101_TURNOVER_LIMIT double precision,
    T101_IS_BAGATELLE char(1),
    T101_IS_OUT_OF_TURNOVER_LIMIT char(1),
    primary key (T101_ID)
);
create table T102_SUMMIT_FX_DUS (
    T102_ID number(19,0) not null,
    primary key (T102_ID)
);
create table T103_SUMMIT_AMEND_LOAD (
    T103_RECORD_NUMBER number(19,0) not null,
    T103_LOAD_STATUS varchar2(10),
    T103_LOAD_TIME date,
    T103_LOAD_MESSAGE varchar2(255),
    T103_TRADE_ID varchar2(255),
    T103_TRADE_TYPE varchar2(50),
    T103_PRODUCT_TYPE varchar2(50),
    T103_BOOK_ID varchar2(100),
    T103_TRADE_VERSION_OLD number(10,0),
    T103_TRADE_VERSION_NEW number(10,0),
    T103_FIELD_MODIFIED varchar2(50),
    T103_FIELD_VALUE_OLD varchar2(255),
    T103_FIELD_VALUE_NEW varchar2(255),
    T103_FIELD_VALUE_CHANGE varchar2(255),
    primary key (T103_RECORD_NUMBER)
);
create table T104_SUMMIT_AMEND (
    T104_ID number(19,0) not null,
    T104_TRADE_ID varchar2(255),
    T104_TRADE_VERSION_OLD number(10,0),
    T104_TRADE_ID_OLD varchar2(255),
    T104_TRADE_VERSION_NEW number(10,0),
    T104_TRADE_ID_NEW varchar2(255),
    T104_FIELD_MODIFIED varchar2(50),
    T104_FIELD_VALUE_OLD varchar2(255),
    T104_FIELD_VALUE_NEW varchar2(255),
    T104_FIELD_VALUE_CHANGE varchar2(255),
    T104_CREATION_DATE date,
    primary key (T104_ID)
);
create table T105_LOANIQ_BOND_LON_LOAD (
    T105_record_number number(19,0) not null,
    T105_LOAD_STATUS varchar2(10),
    T105_LOAD_TIME date,
    T105_LOAD_MESSAGE varchar2(255),
    T105_TRADE_ID varchar2(50),
    T105_BRANCH varchar2(10),
    T105_RISK_BOOK varchar2(100),
    T105_DEAL_NAME varchar2(100),
    T105_FACILITY_NUMBER varchar2(50),
    T105_FACILITY_NAME varchar2(50),
    T105_BUY_SELL varchar2(4),
    T105_AMOUNT double precision,
    T105_CURRENCY varchar2(3),
    T105_PRICE double precision,
    T105_TRADE_DATE date,
    T105_COUNTERPARTY varchar2(100),
    T105_CATEGORY varchar2(25),
    T105_MATURITY_DATE date,
    T105_TRADER varchar2(100),
    primary key (T105_record_number)
);
create table T106_LOANIQ_BOND_LON (
    T106_ID number(19,0) not null,
    T106_BRANCH varchar2(10),
    T106_RISK_BOOK varchar2(100),
    T106_FACILITY_NUMBER varchar2(50),
    T106_BUY_SELL varchar2(4),
    T106_CATEGORY varchar2(25),
    T106_MATURITY_DATE date,
    primary key (T106_ID)
);
create table T107_SUMMIT_BOND_EAA (
    T107_ID number(19,0) not null,
    primary key (T107_ID)
);
create table T108_SUMMIT_REPO_EAA (
    T108_ID number(19,0) not null,
    primary key (T108_ID)
);
create table T109_SUMMIT_DERIVATIVE_EAA (
    T109_ID number(19,0) not null,
    primary key (T109_ID)
);
create table T10_STATE_CODE (
    T10_STATE_CODE varchar2(50) not null,
    FK_T10_T09_STATE_MANDANT varchar2(3) not null,
    T10_STATE_TYPE varchar2(20) not null,
    T10_SHORT_DESCRIPTION varchar2(40),
    T10_LONG_DESCRIPTION varchar2(200),
    T10_COMMENT varchar2(1000),
    T10_IS_FINAL_STATE char(1),
    T10_ENABLED char(1),
    T10_CHANGED_DATE date,
    T10_CHANGED_BY varchar2(10),
    T10_CREATED_BY varchar2(10),
    T10_CREATION_DATE date,
    T10_IS_MANUAL_CHECK_REQUIRED char(1),
    T10_IS_MCC_CHECKED char(1),
    T10_MARKET_DATA_REQUEST_TYPE varchar2(20),
    T10_SAMPLE_PERCENTAGE number(10,0),
    FK_T10_T10_SAMPLE_STATE_CODE VARCHAR2(50),
    FK_T10_T10_SAMPLE_MANDANT_CODE VARCHAR2(3),
    T10_IS_RECLAMATION_REQUIRED char(1),
    FK_T10_T10_RECL_STATE_CODE VARCHAR2(50),
    FK_T10_T10_RECL_MANDANT_CODE VARCHAR2(3),
    primary key (T10_STATE_CODE, FK_T10_T09_STATE_MANDANT)
);
create table T110_SUMMIT_MM_LON (
    T110_ID number(19,0) not null,
    primary key (T110_ID)
);
create table T111_SUMMIT_MM_NYC (
    T111_ID number(19,0) not null,
    primary key (T111_ID)
);
create table T112_SUMMIT_MM_HKG (
    T112_ID number(19,0) not null,
    primary key (T112_ID)
);
create table T113_SUMMIT_MM_EAA (
    T113_ID number(19,0) not null,
    primary key (T113_ID)
);
create table T114_SUMMIT_FX_LON (
    T114_ID number(19,0) not null,
    primary key (T114_ID)
);
create table T115_SUMMIT_FX_NYC (
    T115_ID number(19,0) not null,
    primary key (T115_ID)
);
create table T116_SUMMIT_FX_HKG (
    T116_ID number(19,0) not null,
    primary key (T116_ID)
);
create table T117_SUMMIT_FX_EAA (
    T117_ID number(19,0) not null,
    primary key (T117_ID)
);
create table T119_REQUEST_MAPPING (
    T119_INSTRUMENT varchar2(50) not null,
    T119_REQUEST_STRING varchar2(255),
    T119_CONVERSION_FACTOR double precision,
    T119_CHANGED_DATE date,
    T119_CHANGED_BY varchar2(10),
    T119_CREATED_BY varchar2(10),
    T119_CREATION_DATE date,
    primary key (T119_INSTRUMENT)
);
create table T11_PRICE_CHECK_CATEGORY (
    T11_ID number(19,0) not null,
    T11_NAME varchar2(50),
    T11_TOLERANCE_PERCENT double precision,
    T11_TOLERANCE_ABSOLUTE double precision,
    T11_TOLERANCE_TIME_MINUTES number(10,0),
    FK_T11_T09_MANDANT VARCHAR2(3),
    T11_SAMPLE_PERCENTAGE number(10,0),
    FK_T11_T10_SAMPLE_STATE_CODE VARCHAR2(50),
    FK_T11_T10_SAMPLE_MANDANT_CODE VARCHAR2(3),
    T11_ENABLED char(1),
    T11_CHANGED_DATE date,
    T11_CHANGED_BY varchar2(10),
    T11_CREATED_BY varchar2(10),
    T11_CREATION_DATE date,
    primary key (T11_ID)
);
create table T120_REPORT_CONFIGURATION (
    T120_REPORT_ID varchar2(100) not null,
    T120_CONFIGURATION varchar2(4000),
    T120_CHANGED_DATE date,
    T120_CHANGED_BY varchar2(10),
    T120_CREATED_BY varchar2(10),
    T120_CREATION_DATE date,
    primary key (T120_REPORT_ID)
);
create table T12_PRICE (
    T12_ID number(19,0) not null,
    T12_TYPE varchar2(4) not null,
    FK_T12_T09_MANDANT VARCHAR2(3),
    T12_PRICE_DATE date,
    T12_CHANGED_DATE date,
    T12_CHANGED_BY varchar2(10),
    T12_CREATED_BY varchar2(10),
    T12_CREATION_DATE date,
    T12_PRICE double precision,
    T12_PRICE_MAX double precision,
    T12_PRICE_MIN double precision,
    primary key (T12_ID)
);
create table T13_ADDON (
    T13_ID number(19,0) not null,
    FK_T13_T09_MANDANT VARCHAR2(3),
    T13_CONDITION varchar2(255),
    T13_COMMENT varchar2(255),
    T13_PRICE_TOLER_PERCENT double precision,
    T13_TIME_TOLER_MINUTES number(10,0),
    T13_CHANGED_DATE date,
    T13_CHANGED_BY varchar2(10),
    T13_CREATED_BY varchar2(10),
    T13_CREATION_DATE date,
    primary key (T13_ID)
);
create table T14_MARKET_DATA_KEY (
    T14_ID number(19,0) not null,
    T14_TYPE varchar2(3) not null,
    T14_KEY varchar2(255),
    FK_T14_T09_MANDANT VARCHAR2(3),
    T14_CHANGED_DATE date,
    T14_CHANGED_BY varchar2(10),
    T14_CREATED_BY varchar2(10),
    T14_CREATION_DATE date,
    primary key (T14_ID)
);
create table T15_REPORT_IMAGE (
    T15_ID number(19,0) not null,
    T15_NAME varchar2(255),
    T15_IMAGE blob,
    primary key (T15_ID)
);
create table T16_DUAL_CONTROL_JOB (
    T16_ID number(19,0) not null,
    FK_T16_T01_COMMIT_EMPLOYEE number(19,0),
    FK_T16_T01_REQ_EMPLOYEE number(19,0),
    FK_T16_T09_MANDANT varchar2(3),
    T16_JAVA_OBJECT blob,
    FK_T16_T13_ADDON_OBJECT number(19,0),
    FK_T16_T11_PRICE_CHECK_OBJECT number(19,0),
    FK_T16_T03_EXCHANGE_OBJECT number(19,0),
    FK_T16_T21_EXCHANGE_MAP_OBJECT number(19,0),
    FK_T16_T05_INSTRUMENT_OBJECT number(19,0),
    T16_STATE varchar2(255),
    T16_ENTITY_TYPE_NAME varchar2(255),
    T16_DESCRIPTION varchar2(255),
    T16_OPERATION_TYPE varchar2(6),
    T16_CHANGED_DATE date,
    T16_CHANGED_BY varchar2(10),
    T16_CREATED_BY varchar2(10),
    T16_CREATION_DATE date,
    FK_T16_T42_MGB_CONFIG_KEY VARCHAR2(100),
    FK_T16_T42_MGB_CONFIG_MANDANT VARCHAR2(3),
    primary key (T16_ID)
);
create table T17_USER_ROLE (
    T17_ID number(19,0) not null,
    FK_T17_T01_EMPLOYEE number(19,0),
    FK_T17_T09_MANDANT VARCHAR2(3),
    FK_T17_T29_ROLE VARCHAR2(20),
    T17_LOCATION varchar2(50),
    T17_CHANGED_DATE date,
    T17_CHANGED_BY varchar2(10),
    T17_CREATED_BY varchar2(10),
    T17_CREATION_DATE date,
    primary key (T17_ID)
);
create table T18_TRADER (
    T18_ID number(19,0) not null,
    FK_T18_T01_EMPLOYEE number(19,0),
    FK_T18_T07_SOURCE_SYSTEM VARCHAR2(3),
    T18_TRADER_CODE varchar2(50),
    FK_T18_T09_MANDANT VARCHAR2(3),
    T18_CHANGED_DATE date,
    T18_CHANGED_BY varchar2(10),
    T18_CREATED_BY varchar2(10),
    T18_CREATION_DATE date,
    primary key (T18_ID)
);
create table T19_FIDESSA_EQUITY_LOAD (
    T19_RECORD_NUMBER number(19,0) not null,
    T19_SOURCE_TRADE_ID varchar2(25),
    T19_TRADE_SET_VERSION varchar2(42),
    T19_LOAD_STATUS varchar2(10),
    T19_LOAD_TIME date,
    T19_LOAD_MESSAGE varchar2(255),
    T19_ALL_TRADE_VERSIONS number(10,0),
    T19_AMENDED_DATE date,
    T19_AMENDED_TIME date,
    T19_BOOK_ID varchar2(6),
    T19_BUY_SELL varchar2(1),
    T19_CL_CONDITION_CODES varchar2(32),
    T19_COUNTERPARTY_ID varchar2(10),
    T19_COUNTERPARTY_TYPE varchar2(1),
    T19_CPTY_TYPE_QUALIFIER varchar2(1),
    T19_DESCRIPTION varchar2(255),
    T19_EXCHANGE_ID varchar2(4),
    T19_ICL_SYSDATE date,
    T19_INTERNAL_DEALS varchar2(1),
    T19_ISIN_CODE varchar2(16),
    T19_NET_GROSS_IND varchar2(1),
    T19_ORDER_ID varchar2(20),
    T19_RIC_CODES varchar2(16),
    T19_SETTLEMENT_DATE date,
    T19_STATUS varchar2(1),
    T19_TRADE_CURRENCY varchar2(3),
    T19_TRADE_DATE date,
    T19_TRADE_NOTES varchar2(80),
    T19_TRADE_PRICE double precision,
    T19_TRADER varchar2(24),
    T19_TRADE_TIME date,
    T19_TRADING_ENTITY_ID varchar2(4),
    T19_VOLUME double precision,
    T19_KUNDE_ID varchar2(10),
    T19_TPD_TRADE_ID varchar2(50),
    T19_TPD_VERSION varchar2(3),
    T19_AGGREGATION_TRADE_ID varchar2(50),
    T19_INSTRUMENT_TYPE varchar2(50),
    primary key (T19_RECORD_NUMBER)
);
create table T20_FIDESSA_EQUITY (
    T20_ID number(19,0) not null,
    T20_ALL_TRADE_VERSIONS number(10,0),
    T20_CL_CONDITION_CODES varchar2(32),
    T20_COUNTERPARTY_TYPE varchar2(1),
    T20_CPTY_TYPE_QUALIFIER varchar2(1),
    T20_DESCRIPTION varchar2(255),
    T20_STATUS varchar2(1),
    T20_TRADE_NOTES varchar2(80),
    T20_TRADE_SET_VERSION varchar2(42),
    T20_TRADING_ENTITY_ID varchar2(4),
    T20_IS_STORNO char(1),
    T20_IS_COMMISION char(1),
    primary key (T20_ID)
);
create table T21_EXCHANGE_MAPPING (
    T21_ID number(19,0) not null,
    FK_T21_T03_EXCHANGE number(19,0),
    FK_T21_T07_SOURCE_SYSTEM VARCHAR2(3),
    T21_SOURCE_SYSTEM_EXCHANGE varchar2(10),
    T21_CURRENCY varchar2(3),
    T21_ISIN varchar2(16),
    T21_ISIN_PREFIX varchar2(2),
    FK_T21_T22_MAPPING_PRIO number(10,0),
    FK_T21_T09_MANDANT VARCHAR2(3),
    T21_CHANGED_DATE date,
    T21_CHANGED_BY varchar2(10),
    T21_CREATED_BY varchar2(10),
    T21_CREATION_DATE date,
    primary key (T21_ID)
);
create table T22_EXCHANGE_MAPPING_PRIORITY (
    T22_PRIORITY number(10,0) not null,
    T22_SRC_SYS_NOT_NULL char(1),
    T22_ISIN_NOT_NULL char(1),
    T22_ISIN_PREFIX_NOT_NULL char(1),
    T22_SRC_SYS_EXCHANGE_NOT_NULL char(1),
    T22_CURRENCY_NOT_NULL char(1),
    T22_NAME varchar2(50),
    FK_T22_T09_MANDANT VARCHAR2(3),
    T22_CHANGED_DATE date,
    T22_CHANGED_BY varchar2(10),
    T22_CREATED_BY varchar2(10),
    T22_CREATION_DATE date,
    primary key (T22_PRIORITY)
);
create table T23_EQUITY (
    T23_ID number(19,0) not null,
    T23_SRC_EXCHANGE varchar2(10),
    FK_T23_T22_EXCHANGE_MAP_PRIO number(10,0),
    T23_BAGATELLE_LIMIT double precision,
    T23_TURNOVER_LIMIT double precision,
    T23_IS_BUY char(1),
    T23_IS_INTERNAL_DEAL char(1),
    T23_IS_NET char(1),
    T23_IS_OTC char(1),
    T23_IS_STORNO char(1),
    T23_IS_WARRANT char(1),
    T23_IS_FOREIGN_EXCHANGE char(1),
    T23_IS_BUY_SELL_IDENTICAL char(1),
    primary key (T23_ID)
);
create table T24_PRIME_EQUITY (
    T24_ID number(19,0) not null,
    T24_AUTOMATIC_TRADE_FLAG varchar2(1),
    T24_CREATION_DATETIME date,
    T24_INSTRUMENT_ID varchar2(50),
    T24_INSTRUMENT_NAME varchar2(50),
    T24_INTERNAL_INSTRUMENT_FLAG varchar2(1),
    T24_TRADE_TYPE varchar2(50),
    T24_UND_INSTR_ID varchar2(50),
    T24_UND_INSTR_ISIN_CODE varchar2(16),
    T24_UND_INSTR_NAME varchar2(50),
    T24_FREE_TEXT varchar2(255),
    t24_expiry_combination_date date,
    t24_expiry_option_date date,
    t24_issue_date date,
    t24_sub_type varchar2(255),
    t24_put_call_flag varchar2(1),
    t24_strike_price double precision,
    primary key (T24_ID)
);
create table T25_BOND (
    T25_ID number(19,0) not null,
    primary key (T25_ID)
);
create table T26_REPO (
    T26_ID number(19,0) not null,
    T26_INSTRUMENT_CODE varchar2(50),
    T26_INSTRUMENT_TYPE varchar2(50),
    T26_UNDERLYING_INSTRUMENT_TYPE varchar2(50),
    T26_UNDERLYING_VAL_GROUP varchar2(50),
    T26_YIELD_CURVE varchar2(50),
    T26_START_DATE date,
    T26_END_DATE date,
    T26_DAY_COUNT varchar2(20),
    T26_REPO_RATE double precision,
    T26_YIELD_CURVE_RATE double precision,
    T26_START_CASH double precision,
    T26_END_CASH double precision,
    T26_DAYS double precision,
    T26_RATE_DIFF double precision,
    T26_PROFIT_LOSS_DIFF double precision,
    T26_FOREIGN_EXCHANGE_RATE double precision,
    T26_PROFIT_LOSS_DIFF_EURO double precision,
    T26_BASEPOINT_TOLERANCE number(10,0),
    T26_MCC_STATUS varchar2(20),
    T26_OPEN_END_FLAG varchar2(1),
    T26_TRANSACTION_REFERENCE varchar2(50),
    primary key (T26_ID)
);
create table T27_MONEY_MARKET (
    T27_ID number(19,0) not null,
    T27_COUNTERPARTY_GROUP varchar2(20),
    T27_DEVIATION double precision,
    T27_GROUP_ID varchar2(10),
    T27_INFO varchar2(16),
    T27_INSTRUMENT_SUB_TYPE varchar2(2),
    T27_INSTRUMENT_TYPE varchar2(2),
    T27_MATURITY_DAYS double precision,
    T27_MARKET_RATE double precision,
    T27_MEMO varchar2(75),
    T27_NET_RATE double precision,
    T27_FX_RATE double precision,
    T27_FX_RATE_DATE date,
    T27_NET_RATE_DATETIME date,
    T27_OPENING_DATETIME date,
    T27_RECORD_TYPE varchar2(1),
    T27_REF_INSTRUMENT varchar2(24),
    T27_TRADE_PROCESS varchar2(12),
    T27_TRADE_ROLE varchar2(2),
    T27_TYPE varchar2(25),
    T27_MARGIN double precision,
    T27_DEALT_RATE double precision,
    T27_USER_FIELD varchar2(16),
    FK_T27_T39_TRADE_TYPE VARCHAR2(25),
    T27_BAGATELLE_LIMIT double precision,
    T27_TURNOVER_LIMIT double precision,
    T27_IS_BAGATELLE char(1),
    T27_IS_OUT_OF_TURNOVER_LIMIT char(1),
    T27_IS_STORNO char(1),
    T27_IS_MCC_RELEVANT_CHANGE char(1),
    primary key (T27_ID)
);
create table T28_CONDITION (
    T28_ID number(19,0) not null,
    FK_T28_T07_SOURCE_SYSTEM varchar2(3),
    T28_CONDITION_NAME varchar2(50),
    T28_CONDITION_EVALUATOR varchar2(50),
    T28_CONDITION_TYPE varchar2(100),
    primary key (T28_ID)
);
create table T29_ROLE (
    T29_ID varchar2(20) not null,
    T29_NAME varchar2(200),
    primary key (T29_ID)
);
create table T30_ATLAS_BOND (
    T30_ID number(19,0) not null,
    FK_T30_T05_BLB_INSTRUMENT number(19,0),
    T30_CODE varchar2(1),
    T30_STATUS varchar2(50),
    T30_FX_EURO_RATE double precision,
    T30_INSTRUMENT_TYPE varchar2(50),
    T30_VAL_GROUP varchar2(50),
    T30_CATEGORY varchar2(50),
    T30_START_DAY date,
    T30_EXPIRE_DAY date,
    T30_DURATION double precision,
    T30_TOLERANCE double precision,
    T30_ISSUER varchar2(50),
    T30_PORTFOLIO varchar2(50),
    T30_ALIAS varchar2(50),
    T30_UPDATED_BY varchar2(50),
    T30_ORIGINAL_TRADE_PRICE double precision,
    T30_ORIGINAL_MARKET_PRICE double precision,
    T30_ORIGINAL_THEOR_PRICE double precision,
    T30_NOW_TRADE_PRICE double precision,
    T30_TRADE_YIELD double precision,
    T30_NOW_MARKET_PRICE double precision,
    T30_MARKET_YIELD double precision,
    T30_NOW_THEOR_PRICE double precision,
    T30_DIFF_TRADE_PRICE double precision,
    T30_DIFF_MARKET_PRICE double precision,
    T30_DIFF_THEOR_PRICE double precision,
    T30_PERCENTAGE_MARKET_PRICE double precision,
    T30_PERCENTAGE_THEOR_PRICE double precision,
    T30_VAL_DIFF_MARKET_PRICE double precision,
    T30_VAL_DIFF_THEOR_PRICE double precision,
    T30_EUR_DIFF_MARKET_PRICE double precision,
    T30_EUR_DIFF_THEOR_PRICE double precision,
    T30_SIGN varchar2(1),
    T30_IS_EXCHANGE_TRADED char(1),
    T30_IS_INTRA_DAY_STORNO char(1),
    T30_IS_SMALL_CUSTOMER char(1),
    T30_IS_STORNO char(1),
    T30_IS_BAGATELLE char(1),
    T30_IS_OUT_OF_TURNOVER_LIMIT char(1),
    primary key (T30_ID)
);
create table T31_ATLAS_REPO (
    T31_ID number(19,0) not null,
    primary key (T31_ID)
);
create table T32_SUNGARD_MONEY_MARKET (
    T32_ID number(19,0) not null,
    T32_MATURITY_DATE date,
    primary key (T32_ID)
);
create table T33_ATLAS_REPO_LOAD (
    T33_record_NUMBER number(19,0) not null,
    T33_TRADE_NUMBER varchar2(50),
    T33_LOAD_STATUS varchar2(10),
    T33_LOAD_TIME date,
    T33_BASEPOINT_TOLERANCE number(10,0),
    T33_BOOK_ID varchar2(50),
    T33_CURRENCY varchar2(3),
    T33_DAY_COUNT varchar2(10),
    T33_DAYS double precision,
    T33_END date,
    T33_ENDCASH double precision,
    T33_FOREIGN_EXCHANGE_RATE double precision,
    T33_INSTRUMENT varchar2(50),
    T33_INSTRUMENT_TYPE varchar2(50),
    T33_ISIN varchar2(50),
    T33_MCC_STATUS varchar2(50),
    T33_OPENEND_FLAG varchar2(1),
    T33_PROFIT_LOSS_DIFF double precision,
    T33_PROFIT_LOSS_DIFF_EURO double precision,
    T33_RATE_DIFF double precision,
    T33_REPO_RATE double precision,
    T33_START date,
    T33_STARTCASH double precision,
    T33_TRADER_ID varchar2(255),
    T33_TRADE_TIME date,
    T33_TRANSACTION_REFERENCE varchar2(50),
    T33_UNDERLYING_INSTRUMENT_TYPE varchar2(50),
    T33_UNDERLYING_VALGROUP varchar2(50),
    T33_YIELDCURVE varchar2(50),
    T33_YIELD_CURVE_RATE double precision,
    primary key (T33_record_NUMBER)
);
create table T34_STATE_RULES (
    T34_ID number(19,0) not null,
    FK_T34_T10_FINAL_STATE_CODE VARCHAR2(50),
    FK_T34_T10_MANDANT_CODE VARCHAR2(3),
    FK_T34_T07_SOURCE_SYSTEM VARCHAR2(3),
    T34_COMMENT varchar2(255),
    T34_CONDITION_NAME varchar2(255),
    T34_PRIORITY number(10,0),
    T34_STAGE number(10,0),
    T34_CHANGED_DATE date,
    T34_CHANGED_BY varchar2(10),
    T34_CREATED_BY varchar2(10),
    T34_CREATION_DATE date,
    primary key (T34_ID)
);
create table T35_PRIME_EQUITY_LOAD (
    T35_TRADE_ID varchar2(20) not null,
    T35_LOAD_STATUS varchar2(10),
    T35_LOAD_TIME date,
    T35_LOAD_MESSAGE varchar2(255),
    T35_AMENDED_DATETIME date,
    T35_AUTOMATIC_TRADE_FLAG varchar2(1),
    T35_BLOOMBERG_ID varchar2(50),
    T35_BOOK_ID varchar2(50),
    T35_BUY_SELL_FLAG varchar2(1),
    T35_COUNTERPARTY_ID varchar2(50),
    T35_CREATION_DATETIME date,
    T35_EXCHANGE varchar2(4),
    T35_INSTRUMENT_ID varchar2(50),
    T35_INSTRUMENT_ISIN_CODE varchar2(50),
    T35_INSTRUMENT_NAME varchar2(50),
    T35_INTERNAL_DEAL_FLAG varchar2(1),
    T35_INTERNAL_INSTRUMENT_FLAG varchar2(1),
    T35_NET_GROSS_FLAG varchar2(1),
    T35_ORDER_ID varchar2(50),
    T35_OTC_FLAG varchar2(1),
    T35_REUTERS_ID varchar2(20),
    T35_SETTLEMENT_DATE date,
    T35_STORNO_FLAG varchar2(1),
    T35_SYSTEM_DATETIME date,
    T35_TRADE_CURRENCY varchar2(3),
    T35_TRADE_DATETIME date,
    T35_TRADE_PRICE double precision,
    T35_TRADER_ID varchar2(50),
    T35_TRADE_TYPE varchar2(50),
    T35_TRADE_VOLUME double precision,
    T35_UND_INSTR_ID varchar2(50),
    T35_UND_INSTR_ISIN_CODE varchar2(50),
    T35_UND_INSTR_NAME varchar2(50),
    T35_WARRANT_FLAG varchar2(1),
    T35_FREE_TEXT varchar2(255),
    T35_REFERENCE_TRADE varchar2(50),
    t35_expiry_combination_date date,
    t35_expiry_option_date date,
    t35_settlement_price double precision,
    t35_trader_name varchar2(255),
    t35_issue_date date,
    t35_sub_type varchar2(255),
    t35_put_call_flag varchar2(1),
    t35_strike_price double precision,
    primary key (T35_TRADE_ID)
);
create table T36_SUNGARD_MONEY_MARKET_LOAD (
    T36_ID number(19,0) not null,
    T36_LOAD_STATUS varchar2(10),
    T36_LOAD_TIME date,
    T36_LOAD_MESSAGE varchar2(255),
    T36_AMOUNT double precision,
    T36_BOOK varchar2(16),
    T36_COMMODITY varchar2(3),
    T36_COUNTERPARTY varchar2(22),
    T36_COUNTERPARTY_GROUP varchar2(20),
    T36_DEVIATION double precision,
    T36_GROUP_ID varchar2(10),
    T36_INFO varchar2(16),
    T36_INSTRUMENT_SUB_TYPE varchar2(2),
    T36_INSTRUMENT_TYPE varchar2(2),
    T36_MARKET_RATE double precision,
    T36_MATURITY_DATE date,
    T36_MEMO varchar2(75),
    T36_NET_RATE double precision,
    T36_NET_RATE_DATETIME date,
    T36_OPENING_DATETIME date,
    T36_RECORD_TYPE varchar2(1),
    T36_REF_INSTRUMENT varchar2(24),
    T36_START_DATE date,
    T36_TRADE_CODE varchar2(10),
    T36_TRADE_PROCESS varchar2(12),
    T36_TRADER varchar2(25),
    T36_TRADER_ID varchar2(25),
    T36_TRADE_ROLE varchar2(2),
    T36_TYPE varchar2(18),
    T36_FX_RATE double precision,
    T36_FX_RATE_DATE date,
    T36_DEALT_RATE double precision,
    T36_MARGIN double precision,
    T36_USER_FIELD varchar2(16),
    primary key (T36_ID)
);
create table T37_SUNGARD_FX_LOAD (
    T37_ID number(19,0) not null,
    T37_LOAD_STATUS varchar2(10),
    T37_LOAD_TIME date,
    T37_LOAD_MESSAGE varchar2(255),
    T37_AMOUNT double precision,
    T37_BOOK varchar2(16),
    T37_COMMODITY varchar2(7),
    T37_COUNTERPARTY varchar2(22),
    T37_COUNTERPARTY_GROUP varchar2(20),
    T37_DEVIATION double precision,
    T37_GROUP_ID varchar2(10),
    T37_INFO varchar2(16),
    T37_INSTRUMENT_SUB_TYPE varchar2(2),
    T37_INSTRUMENT_TYPE varchar2(2),
    T37_MARKET_RATE double precision,
    T37_MEMO varchar2(75),
    T37_NET_RATE double precision,
    T37_NET_RATE_DATETIME date,
    T37_OPENING_DATETIME date,
    T37_RECORD_TYPE varchar2(1),
    T37_REF_INSTRUMENT varchar2(24),
    T37_TRADE_CODE varchar2(10),
    T37_TRADE_PROCESS varchar2(12),
    T37_TRADER varchar2(25),
    T37_TRADER_ID varchar2(25),
    T37_TRADE_ROLE varchar2(2),
    T37_TYPE varchar2(20),
    T37_VALUE_DATE date,
    T37_FX_RATE double precision,
    T37_FX_RATE_DATE date,
    T37_DEALT_RATE double precision,
    T37_MARGIN double precision,
    T37_USER_FIELD varchar2(16),
    primary key (T37_ID)
);
create table T38_SUNGARD_FOREIGN_EXCHANGE (
    T38_ID number(19,0) not null,
    primary key (T38_ID)
);
create table T39_SUNGARD_TRADE_TYPE (
    T39_ID varchar2(25) not null,
    T39_SUNGARD_TRADE_TYPE_NAME varchar2(255),
    T39_INSTRUMENT_SUB_TYPE varchar2(2),
    T39_INSTRUMENT_TYPE varchar2(2),
    T39_TRADE_ROLE varchar2(2),
    T39_REF_INSTRUMENT varchar2(24),
    T39_IS_MCC_RELEVANT char(1),
    FK_T39_T09_MANDANT VARCHAR2(3),
    T39_CHANGED_DATE date,
    T39_CHANGED_BY varchar2(10),
    T39_CREATED_BY varchar2(10),
    T39_CREATION_DATE date,
    primary key (T39_ID)
);
create table T40_BLOOMBERG_CURRENCY_CODES (
    T40_ISO_CURRENCY_CODE varchar2(3) not null,
    T40_BLOOMBERG_CURRENCY_CODE varchar2(2),
    T40_CURRENCY_NAME varchar2(50),
    T40_CHANGED_DATE date,
    T40_CHANGED_BY varchar2(10),
    T40_CREATED_BY varchar2(10),
    T40_CREATION_DATE date,
    primary key (T40_ISO_CURRENCY_CODE)
);
create table T41_BLOOMBERG_MATURITY_CODES (
    T41_MATURITY_CODE varchar2(3) not null,
    T41_BLOOMBERG_MATURITY_CODE varchar2(2),
    T41_BLOOMBERG_MATURITY_FIELD varchar2(50),
    T41_MIN_MATURITY_DAYS double precision,
    T41_MAX_MATURITY_DAYS double precision,
    T41_MATURITY_NAME varchar2(50),
    T41_CHANGED_DATE date,
    T41_CHANGED_BY varchar2(10),
    T41_CREATED_BY varchar2(10),
    T41_CREATION_DATE date,
    primary key (T41_MATURITY_CODE)
);
create table T42_MGB_CONFIGURATION (
    T42_KEY varchar2(100) not null,
    FK_T42_T09_MANDANT varchar2(3) not null,
    T42_VALUE varchar2(4000),
    T42_CHANGED_DATE date,
    T42_CHANGED_BY varchar2(10),
    T42_CREATED_BY varchar2(10),
    T42_CREATION_DATE date,
    primary key (T42_KEY, FK_T42_T09_MANDANT)
);
create table T43_CHANGE_REVISION_LOG (
    T43_ID number(19,0) not null,
    FK_T43_T16_DUAL_CONTROL_JOB number(19,0),
    T43_FIELD_NAME varchar2(255),
    T43_FIELD_VALUE_OLD varchar2(255),
    T43_FIELD_VALUE_NEW varchar2(255),
    primary key (T43_ID)
);
create table T44_BOOK (
    T44_BOOK_ID varchar2(100) not null,
    T44_BOOK_TYPE varchar2(50),
    T44_BOOK_DESCRIPTION varchar2(100),
    T44_ACCOUNT number(19,0),
    T44_ACCOUNTING_SYSTEM varchar2(50),
    T44_ACCOUNTING_BRANCH number(19,0),
    T44_BUSINESS_UNIT varchar2(50),
    T44_COST_CENTER number(19,0),
    T44_COST_CENTER_NAME varchar2(50),
    T44_WERS_UNIT number(19,0),
    T44_LIMIT_POSITION varchar2(50),
    T44_MPIRE_LU_NAME varchar2(100),
    T44_DESK varchar2(50),
    T44_PCR_PL_DESK varchar2(75),
    T44_PCR_PL_GROUP varchar2(75),
    T44_FO_SYSTEM varchar2(50),
    T44_LOCATION_CONSOLD varchar2(50),
    T44_LOCATION_GL varchar2(50),
    T44_LOCATION_PL varchar2(50),
    T44_LOCATION_TRADER varchar2(50),
    T44_TRADER varchar2(100),
    T44_TRADING_HEAD varchar2(100),
    T44_TRADING_BANKING varchar2(50),
    T44_TRADING_TYPE varchar2(50),
    T44_OVERWRITE_LOCATION_TRADER varchar2(50),
    T44_CHANGED_DATE date,
    primary key (T44_BOOK_ID)
);
create table T45_PARIS_BOOK_LOAD (
    T45_BOOK_ID varchar2(100) not null,
    T45_LOAD_STATUS varchar2(10),
    T45_LOAD_TIME date,
    T45_LOAD_MESSAGE varchar2(255),
    T45_BOOK_TYPE varchar2(50),
    T45_BOOK_DESCRIPTION varchar2(100),
    T45_ACCOUNT number(19,0),
    T45_ACCOUNTING_SYSTEM varchar2(50),
    T45_ACCOUNTING_BRANCH number(19,0),
    T45_BUSINESS_UNIT varchar2(50),
    T45_COST_CENTER number(19,0),
    T45_COST_CENTER_NAME varchar2(50),
    T45_WERS_UNIT number(19,0),
    T45_LIMIT_POSITION varchar2(50),
    T45_MPIRE_LU_NAME varchar2(100),
    T45_DESK varchar2(50),
    T45_PCR_PL_DESK varchar2(75),
    T45_PCR_PL_GROUP varchar2(75),
    T45_FO_SYSTEM varchar2(50),
    T45_LOCATION_CONSOLD varchar2(50),
    T45_LOCATION_GL varchar2(50),
    T45_LOCATION_PL varchar2(50),
    T45_LOCATION_TRADER varchar2(50),
    T45_TRADER varchar2(100),
    T45_TRADING_HEAD varchar2(100),
    T45_TRADING_BANKING varchar2(50),
    T45_TRADING_TYPE varchar2(50),
    primary key (T45_BOOK_ID)
);
create table T46_ATLAS_BOND_LOAD (
    T46_TRADE_ID varchar2(50) not null,
    T46_LOAD_STATUS varchar2(10),
    T46_LOAD_TIME date,
    T46_LOAD_MESSAGE varchar2(255),
    T46_CODE varchar2(1),
    T46_CONTRACT_ID varchar2(50),
    T46_STATUS varchar2(50),
    T46_INSTRUMENT varchar2(50),
    T46_ISIN varchar2(50),
    T46_CURRENCY varchar2(3),
    T46_FX_EURO_RATE double precision,
    T46_INSTRUMENT_TYPE varchar2(50),
    T46_VAL_GROUP varchar2(50),
    T46_CATEGORY varchar2(50),
    T46_START_DAY date,
    T46_EXPIRE_DAY date,
    T46_DURATION double precision,
    T46_TOLERANCE double precision,
    T46_ISSUER varchar2(50),
    T46_PORTFOLIO varchar2(50),
    T46_NOMINAL double precision,
    T46_COUNTERPARTY varchar2(50),
    T46_ALIAS varchar2(50),
    T46_TRADER varchar2(50),
    T46_TRADE_DATE date,
    T46_TRADE_TIME date,
    T46_VALUE_DATE date,
    T46_UPDATED_BY varchar2(50),
    T46_UPDATE_TIME date,
    T46_ORIGINAL_TRADE_PRICE double precision,
    T46_ORIGINAL_MARKET_PRICE double precision,
    T46_ORIGINAL_THEOR_PRICE double precision,
    T46_NOW_TRADE_PRICE double precision,
    T46_TRADE_YIELD double precision,
    T46_NOW_MARKET_PRICE double precision,
    T46_MARKET_YIELD double precision,
    T46_NOW_THEOR_PRICE double precision,
    T46_DIFF_TRADE_PRICE double precision,
    T46_DIFF_MARKET_PRICE double precision,
    T46_DIFF_THEOR_PRICE double precision,
    T46_PERCENTAGE_MARKET_PRICE double precision,
    T46_PERCENTAGE_THEOR_PRICE double precision,
    T46_VAL_DIFF_MARKET_PRICE double precision,
    T46_VAL_DIFF_THEOR_PRICE double precision,
    T46_EUR_DIFF_MARKET_PRICE double precision,
    T46_EUR_DIFF_THEOR_PRICE double precision,
    T46_SIGN varchar2(1),
    primary key (T46_TRADE_ID)
);
create table T47_ACCESS_CONTROL (
    T47_ID number(19,0) not null,
    FK_T47_T29_ROLE VARCHAR2(20),
    T47_FUNCTION varchar2(255),
    primary key (T47_ID)
);
create table T48_MAIL (
    T48_ID number(19,0) not null,
    T48_TYPE varchar2(20),
    T48_CREATION_DATE date,
    FK_T48_T01_RECEIVER number(19,0),
    FK_T48_T01_SENDER number(19,0),
    T48_TEXT varchar2(4000),
    T48_SUBJECT varchar2(255),
    FK_T48_T48_PARENT number(19,0),
    FK_T48_T02_TRADE number(19,0),
    primary key (T48_ID)
);
create table T49_RATING_CATEGORY (
    T49_ID varchar2(25) not null,
    FK_T49_T09_MANDANT varchar2(3) not null,
    T49_NAME varchar2(50),
    T49_CHANGED_DATE date,
    T49_CHANGED_BY varchar2(10),
    T49_CREATED_BY varchar2(10),
    T49_CREATION_DATE date,
    primary key (T49_ID, FK_T49_T09_MANDANT)
);
create table T50_RATING_CATEGORY_MAPPING (
    T50_ID number(19,0) not null,
    T50_RATING varchar2(50),
    FK_T50_T49_CATEGORY VARCHAR2(25),
    FK_T50_T09_MANDANT VARCHAR2(3),
    T50_CHANGED_DATE date,
    T50_CHANGED_BY varchar2(10),
    T50_CREATED_BY varchar2(10),
    T50_CREATION_DATE date,
    primary key (T50_ID)
);
create table T51_CURRENCY_CATEGORY (
    T51_ID varchar2(25) not null,
    FK_T51_T09_MANDANT varchar2(3) not null,
    T51_NAME varchar2(50),
    T51_CHANGED_DATE date,
    T51_CHANGED_BY varchar2(10),
    T51_CREATED_BY varchar2(10),
    T51_CREATION_DATE date,
    primary key (T51_ID, FK_T51_T09_MANDANT)
);
create table T52_CURRNCY_CATEGORY_MAPPING (
    T52_ID number(19,0) not null,
    T52_CURRENCY varchar2(3),
    FK_T52_T51_CATEGORY VARCHAR2(25),
    FK_T52_T09_MANDANT VARCHAR2(3),
    T52_CHANGED_DATE date,
    T52_CHANGED_BY varchar2(10),
    T52_CREATED_BY varchar2(10),
    T52_CREATION_DATE date,
    primary key (T52_ID)
);
create table T53_TRADE_TYPE_CATEGORY (
    T53_ID varchar2(25) not null,
    FK_T53_T09_MANDANT varchar2(3) not null,
    T53_NAME varchar2(50),
    T53_CHANGED_DATE date,
    T53_CHANGED_BY varchar2(10),
    T53_CREATED_BY varchar2(10),
    T53_CREATION_DATE date,
    primary key (T53_ID, FK_T53_T09_MANDANT)
);
create table T54_TRADETYPE_CATEGORY_MAPPING (
    T54_ID number(19,0) not null,
    T54_TRADETYPE varchar2(50),
    FK_T54_T53_CATEGORY VARCHAR2(25),
    FK_T54_T09_MANDANT VARCHAR2(3),
    T54_CHANGED_DATE date,
    T54_CHANGED_BY varchar2(10),
    T54_CREATED_BY varchar2(10),
    T54_CREATION_DATE date,
    primary key (T54_ID)
);
create table T55_PRIME_REPO (
    T55_ID number(19,0) not null,
    T55_IS_BAGATELLE char(1),
    primary key (T55_ID)
);
create table T56_PRIME_REPO_LOAD (
    T56_RECORD_NUMBER number(19,0) not null,
    T56_TRADE_NUMBER varchar2(50),
    T56_LOAD_STATUS varchar2(10),
    T56_LOAD_MESSAGE varchar2(255),
    T56_LOAD_TIME date,
    T56_BASEPOINT_TOLERANCE number(10,0),
    T56_BOOK_ID varchar2(50),
    T56_CURRENCY varchar2(3),
    T56_DAY_COUNT varchar2(20),
    T56_DAYS double precision,
    T56_END date,
    T56_ENDCASH double precision,
    T56_FOREIGN_EXCHANGE_RATE double precision,
    T56_INSTRUMENT varchar2(50),
    T56_INSTRUMENT_TYPE varchar2(50),
    T56_ISIN varchar2(50),
    T56_MCC_STATUS varchar2(50),
    T56_OPENEND_FLAG varchar2(1),
    T56_PROFIT_LOSS_DIFF double precision,
    T56_PROFIT_LOSS_DIFF_EURO double precision,
    T56_RATE_DIFF double precision,
    T56_REPO_RATE double precision,
    T56_START date,
    T56_STARTCASH double precision,
    T56_TRADER_ID varchar2(255),
    T56_TRADE_TIME date,
    T56_TRANSACTION_REFERENCE varchar2(50),
    T56_UNDERLYING_INSTRUMENT_TYPE varchar2(50),
    T56_UNDERLYING_VALGROUP varchar2(50),
    T56_YIELDCURVE varchar2(50),
    T56_YIELD_CURVE_RATE double precision,
    primary key (T56_RECORD_NUMBER)
);
create table T57_SUMMIT_BOND (
    T57_ID number(19,0) not null,
    FK_T57_T05_BLB_INSTRUMENT number(19,0),
    T57_STATUS varchar2(50),
    T57_FX_EURO_RATE double precision,
    T57_TRADE_TYPE varchar2(50),
    T57_SUB_TYPE varchar2(50),
    T57_CATEGORY varchar2(50),
    T57_START_DAY date,
    T57_EXPIRE_DAY date,
    T57_INSTRUMENT_NAME varchar2(255),
    T57_INSTRUMENT_START_DAY date,
    T57_INSTRUMENT_EXPIRE_DAY date,
    T57_ISSUER varchar2(50),
    T57_ALIAS varchar2(50),
    T57_UPDATED_BY varchar2(50),
    T57_TRADE_PRICE double precision,
    T57_MARKET_PRICE double precision,
    T57_THEOR_PRICE double precision,
    T57_VERSION number(10,0),
    T57_BAGATELLE_LIMIT double precision,
    T57_TURNOVER_LIMIT double precision,
    T57_IS_EXCHANGE_TRADED char(1),
    T57_IS_INTRA_DAY_STORNO char(1),
    T57_IS_SMALL_CUSTOMER char(1),
    T57_IS_STORNO char(1),
    T57_IS_MCC_RELEVANT_CHANGE char(1),
    T57_IS_BAGATELLE char(1),
    T57_IS_OUT_OF_TURNOVER_LIMIT char(1),
    T57_IS_CURRENT_VERSION char(1),
    T57_DESCRIPTION varchar2(255),
    T57_COUNTERPARTY_REFERENCE varchar2(50),
    T57_STRUCTURE varchar2(12),
    T57_COMPANY varchar2(50),
    T57_IS_BACK_TO_BACK char(1),
    T57_IS_BACK_TO_BACK_CHECK char(1),
    T57_BONIFICATION double precision,
    T57_CUSTOMER_TYPE varchar2(50),
    T57_LEGAL_NAME varchar2(255),
    T57_MARKETER varchar2(50),
    primary key (T57_ID)
);
create table T58_SUMMIT_BOND_LOAD (
    T58_record_NUMBER number(19,0) not null,
    T58_TRADE_ID varchar2(50),
    T58_LOAD_STATUS varchar2(10),
    T58_LOAD_TIME date,
    T58_LOAD_MESSAGE varchar2(255),
    T58_VERSION number(10,0),
    T58_STATUS varchar2(50),
    T58_INSTRUMENT varchar2(50),
    T58_ISIN varchar2(50),
    T58_CURRENCY varchar2(3),
    T58_FX_EURO_RATE double precision,
    T58_TRADE_TYPE varchar2(50),
    T58_SUB_TYPE varchar2(50),
    T58_CATEGORY varchar2(50),
    T58_START_DAY date,
    T58_EXPIRE_DAY date,
    T58_INSTRUMENT_NAME varchar2(255),
    T58_INSTRUMENT_START_DAY date,
    T58_INSTRUMENT_EXPIRE_DAY date,
    T58_ISSUER varchar2(50),
    T58_BOOK varchar2(50),
    T58_NOMINAL double precision,
    T58_COUNTERPARTY varchar2(50),
    T58_ALIAS varchar2(50),
    T58_TRADER varchar2(50),
    T58_TRADE_DATE date,
    T58_TRADE_TIME date,
    T58_VALUE_DATE date,
    T58_UPDATED_BY varchar2(50),
    T58_UPDATE_TIME date,
    T58_TRADE_PRICE double precision,
    T58_MARKET_PRICE double precision,
    T58_THEOR_PRICE double precision,
    T58_DESCRIPTION varchar2(100),
    T58_COUNTERPARTY_REFERENCE varchar2(50),
    T58_STRUCTURE varchar2(12),
    T58_COMPANY varchar2(50),
    T58_DISCOUNT double precision,
    T58_CUSTOMER_TYPE varchar2(50),
    T58_LEGAL_NAME varchar2(255),
    T58_EXT_NOTE_1 varchar2(255),
    T58_EXT_NOTE_2 varchar2(255),
    T58_TRADED_YIELD double precision,
    T58_THEOR_YIELD double precision,
    T58_MARKETER varchar2(50),
    primary key (T58_record_NUMBER)
);
create table T59_SUMMIT_REPO (
    T59_ID number(19,0) not null,
    FK_T59_T05_BLB_INSTRUMENT number(19,0),
    FK_T59_T05_BOND_INSTRUMENT number(19,0),
    T59_STATUS varchar2(20),
    T59_VERSION number(10,0),
    T59_UNDERLYING_CATEGORY varchar2(50),
    T59_FUNDING_SPREAD double precision,
    T59_CUSTOMER_TYPE varchar2(50),
    T59_DONE_DATE date,
    T59_VERIFY_DATE date,
    T59_MARKET_PRICE_UNDERLYING double precision,
    T59_DEAL_ACCRUED_INTEREST double precision,
    T59_START_PRICE double precision,
    T59_BOND_ACCRUED_INTEREST double precision,
    T59_NPV double precision,
    T59_MARKET_TYPE varchar2(50),
    T59_IS_STORNO char(1),
    T59_IS_INTRA_DAY_STORNO char(1),
    T59_IS_MCC_RELEVANT_CHANGE char(1),
    T59_STORNO_PREDECESSOR char(1),
    T59_EXCEEDS_MAX_PL_DIFF char(1),
    T59_IS_POSI_DEPO char(1),
    T59_IS_BAGATELLE char(1),
    T59_IS_CURRENT_VERSION char(1),
    T59_IS_OPEN_END_TERMINATED char(1),
    primary key (T59_ID)
);
create table T61_TIME_PERIOD_CATEGORY (
    T61_CODE varchar2(25) not null,
    T61_NAME varchar2(50),
    T41_MIN_PERIOD_DAYS double precision,
    T41_MAX_PERIOD_DAYS double precision,
    FK_T61_T09_MANDANT VARCHAR2(3),
    T61_CHANGED_DATE date,
    T61_CHANGED_BY varchar2(10),
    T61_CREATED_BY varchar2(10),
    T61_CREATION_DATE date,
    primary key (T61_CODE)
);
create table T62_MAIL_RECIPIENT (
    T62_ID number(19,0) not null,
    type char(1) not null,
    FK_T62_T01_EMPLOYEE number(19,0),
    FK_T62_T48_MAIL number(19,0),
    primary key (T62_ID)
);
create table T63_MGB_TASK (
    T63_ID number(19,0) not null,
    FK_T63_T09_MANDANT VARCHAR2(3),
    T63_TASK_NAME varchar2(255),
    T63_THREAD_NAME varchar2(255),
    T63_USER varchar2(255),
    T63_START_DATE date,
    T63_STOP_DATE date,
    T63_MESSAGE varchar2(255),
    T63_STATE varchar2(255),
    T63_CLIENT varchar2(255),
    T63_CHANGED_DATE date,
    T63_CHANGED_BY varchar2(10),
    T63_CREATED_BY varchar2(10),
    T63_CREATION_DATE date,
    primary key (T63_ID)
);
create table T64_SUMMIT_BOND_DUS (
    T64_ID number(19,0) not null,
    primary key (T64_ID)
);
create table T65_SUMMIT_BOND_LON (
    T65_ID number(19,0) not null,
    primary key (T65_ID)
);
create table T66_SPARKASSE (
    T66_ID number(19,0) not null,
    T66_NAME varchar2(50),
    T66_LONGNAME varchar2(150),
    T66_ACTIVE char(1),
    T66_CLIENT_NR varchar2(20),
    T66_CHANGED_DATE date,
    T66_CHANGED_BY varchar2(10),
    T66_CREATED_BY varchar2(10),
    T66_CREATION_DATE date,
    primary key (T66_ID)
);
create table T67_COUNTERPARTY (
    T67_ID number(19,0) not null,
    FK_T67_T66_SPARKASSE number(19,0),
    T67_COUNTERPARTY_ID varchar2(50),
    T67_DEACTIVATED char(1),
    FK_T67_T07_SOURCE_SYSTEM VARCHAR2(3),
    T67_CHANGED_DATE date,
    T67_CHANGED_BY varchar2(10),
    T67_CREATED_BY varchar2(10),
    T67_CREATION_DATE date,
    primary key (T67_ID)
);
create table T68_SUMMIT_BOND_LUX (
    T68_ID number(19,0) not null,
    primary key (T68_ID)
);
create table T69_SUNGARD_MM_LUX (
    T69_ID number(19,0) not null,
    T69_MATURITY_DATE date,
    primary key (T69_ID)
);
create table T70_SUNGARD_FX_LUX (
    T70_ID number(19,0) not null,
    primary key (T70_ID)
);
create table T71_SUNGARD_FX_LUX_LOAD (
    T71_ID number(19,0) not null,
    T71_LOAD_STATUS varchar2(10),
    T71_LOAD_TIME date,
    T71_LOAD_MESSAGE varchar2(255),
    T71_AMOUNT double precision,
    T71_BOOK varchar2(16),
    T71_COMMODITY varchar2(7),
    T71_COUNTERPARTY varchar2(22),
    T71_COUNTERPARTY_GROUP varchar2(20),
    T71_DEVIATION double precision,
    T71_GROUP_ID varchar2(10),
    T71_INFO varchar2(16),
    T71_INSTRUMENT_SUB_TYPE varchar2(2),
    T71_INSTRUMENT_TYPE varchar2(2),
    T71_MARKET_RATE double precision,
    T71_MEMO varchar2(75),
    T71_NET_RATE double precision,
    T71_NET_RATE_DATETIME date,
    T71_OPENING_DATETIME date,
    T71_RECORD_TYPE varchar2(1),
    T71_REF_INSTRUMENT varchar2(24),
    T71_TRADE_CODE varchar2(10),
    T71_TRADE_PROCESS varchar2(12),
    T71_TRADER varchar2(25),
    T71_TRADER_ID varchar2(25),
    T71_TRADE_ROLE varchar2(2),
    T71_TYPE varchar2(20),
    T71_VALUE_DATE date,
    T71_FX_RATE double precision,
    T71_FX_RATE_DATE date,
    T71_DEALT_RATE double precision,
    T71_MARGIN double precision,
    T71_USER_FIELD varchar2(16),
    primary key (T71_ID)
);
create table T72_SUNGARD_MM_LUX_LOAD (
    T72_ID number(19,0) not null,
    T72_LOAD_STATUS varchar2(10),
    T72_LOAD_TIME date,
    T72_LOAD_MESSAGE varchar2(255),
    T72_AMOUNT double precision,
    T72_BOOK varchar2(16),
    T72_COMMODITY varchar2(3),
    T72_COUNTERPARTY varchar2(22),
    T72_COUNTERPARTY_GROUP varchar2(20),
    T72_DEVIATION double precision,
    T72_GROUP_ID varchar2(10),
    T72_INFO varchar2(16),
    T72_INSTRUMENT_SUB_TYPE varchar2(2),
    T72_INSTRUMENT_TYPE varchar2(2),
    T72_MARKET_RATE double precision,
    T72_MATURITY_DATE date,
    T72_MEMO varchar2(75),
    T72_NET_RATE double precision,
    T72_NET_RATE_DATETIME date,
    T72_OPENING_DATETIME date,
    T72_RECORD_TYPE varchar2(1),
    T72_REF_INSTRUMENT varchar2(24),
    T72_START_DATE date,
    T72_TRADE_CODE varchar2(10),
    T72_TRADE_PROCESS varchar2(12),
    T72_TRADER varchar2(25),
    T72_TRADER_ID varchar2(25),
    T72_TRADE_ROLE varchar2(2),
    T72_TYPE varchar2(18),
    T72_FX_RATE double precision,
    T72_FX_RATE_DATE date,
    T72_DEALT_RATE double precision,
    T72_MARGIN double precision,
    T72_USER_FIELD varchar2(16),
    primary key (T72_ID)
);
create table T73_SUMMIT_BOND_ASIA (
    T73_ID number(19,0) not null,
    primary key (T73_ID)
);
create table T74_SUMMIT_REPO_DUS (
    T74_ID number(19,0) not null,
    primary key (T74_ID)
);
create table T75_SUMMIT_REPO_LON (
    T75_ID number(19,0) not null,
    primary key (T75_ID)
);
create table T76_SUNGARD_MM_LON (
    T76_ID number(19,0) not null,
    T76_MATURITY_DATE date,
    primary key (T76_ID)
);
create table T77_SUNGARD_FX_LON (
    T77_ID number(19,0) not null,
    primary key (T77_ID)
);
create table T78_SUNGARD_FX_LON_LOAD (
    T78_ID number(19,0) not null,
    T78_LOAD_STATUS varchar2(10),
    T78_LOAD_TIME date,
    T78_LOAD_MESSAGE varchar2(255),
    T78_AMOUNT double precision,
    T78_BOOK varchar2(16),
    T78_COMMODITY varchar2(7),
    T78_COUNTERPARTY varchar2(22),
    T78_COUNTERPARTY_GROUP varchar2(20),
    T78_DEVIATION double precision,
    T78_GROUP_ID varchar2(10),
    T78_INFO varchar2(16),
    T78_INSTRUMENT_SUB_TYPE varchar2(2),
    T78_INSTRUMENT_TYPE varchar2(2),
    T78_MARKET_RATE double precision,
    T78_MEMO varchar2(75),
    T78_NET_RATE double precision,
    T78_NET_RATE_DATETIME date,
    T78_OPENING_DATETIME date,
    T78_RECORD_TYPE varchar2(1),
    T78_REF_INSTRUMENT varchar2(24),
    T78_TRADE_CODE varchar2(10),
    T78_TRADE_PROCESS varchar2(12),
    T78_TRADER varchar2(25),
    T78_TRADER_ID varchar2(25),
    T78_TRADE_ROLE varchar2(2),
    T78_TYPE varchar2(20),
    T78_VALUE_DATE date,
    T78_FX_RATE double precision,
    T78_FX_RATE_DATE date,
    T78_DEALT_RATE double precision,
    T78_MARGIN double precision,
    T78_USER_FIELD varchar2(16),
    primary key (T78_ID)
);
create table T79_SUNGARD_MM_LON_LOAD (
    T79_ID number(19,0) not null,
    T79_LOAD_STATUS varchar2(10),
    T79_LOAD_TIME date,
    T79_LOAD_MESSAGE varchar2(255),
    T79_AMOUNT double precision,
    T79_BOOK varchar2(16),
    T79_COMMODITY varchar2(3),
    T79_COUNTERPARTY varchar2(22),
    T79_COUNTERPARTY_GROUP varchar2(20),
    T79_DEVIATION double precision,
    T79_GROUP_ID varchar2(10),
    T79_INFO varchar2(16),
    T79_INSTRUMENT_SUB_TYPE varchar2(2),
    T79_INSTRUMENT_TYPE varchar2(2),
    T79_MARKET_RATE double precision,
    T79_MATURITY_DATE date,
    T79_MEMO varchar2(75),
    T79_NET_RATE double precision,
    T79_NET_RATE_DATETIME date,
    T79_OPENING_DATETIME date,
    T79_RECORD_TYPE varchar2(1),
    T79_REF_INSTRUMENT varchar2(24),
    T79_START_DATE date,
    T79_TRADE_CODE varchar2(10),
    T79_TRADE_PROCESS varchar2(12),
    T79_TRADER varchar2(25),
    T79_TRADER_ID varchar2(25),
    T79_TRADE_ROLE varchar2(2),
    T79_TYPE varchar2(18),
    T79_FX_RATE double precision,
    T79_FX_RATE_DATE date,
    T79_DEALT_RATE double precision,
    T79_MARGIN double precision,
    T79_USER_FIELD varchar2(16),
    primary key (T79_ID)
);
create table T80_SUMMIT_REPO_ASIA (
    T80_ID number(19,0) not null,
    primary key (T80_ID)
);
create table T81_SUMMIT_BOND_HKG (
    T81_ID number(19,0) not null,
    primary key (T81_ID)
);
create table T82_SUNGARD_MM_HKG (
    T82_ID number(19,0) not null,
    T82_MATURITY_DATE date,
    primary key (T82_ID)
);
create table T83_SUNGARD_FX_HKG (
    T83_ID number(19,0) not null,
    primary key (T83_ID)
);
create table T84_SUNGARD_MM_TOK (
    T84_ID number(19,0) not null,
    T84_MATURITY_DATE date,
    primary key (T84_ID)
);
create table T85_SUNGARD_FX_TOK (
    T85_ID number(19,0) not null,
    primary key (T85_ID)
);
create table T86_SUNGARD_MM_NYC (
    T86_ID number(19,0) not null,
    T86_MATURITY_DATE date,
    primary key (T86_ID)
);
create table T87_SUNGARD_FX_NYC (
    T87_ID number(19,0) not null,
    primary key (T87_ID)
);
create table T88_SUNGARD_FX_NYC_LOAD (
    T88_ID number(19,0) not null,
    T88_LOAD_STATUS varchar2(10),
    T88_LOAD_TIME date,
    T88_LOAD_MESSAGE varchar2(255),
    T88_AMOUNT double precision,
    T88_BOOK varchar2(16),
    T88_COMMODITY varchar2(7),
    T88_COUNTERPARTY varchar2(22),
    T88_COUNTERPARTY_GROUP varchar2(20),
    T88_DEVIATION double precision,
    T88_GROUP_ID varchar2(10),
    T88_INFO varchar2(16),
    T88_INSTRUMENT_SUB_TYPE varchar2(2),
    T88_INSTRUMENT_TYPE varchar2(2),
    T88_MARKET_RATE double precision,
    T88_MEMO varchar2(75),
    T88_NET_RATE double precision,
    T88_NET_RATE_DATETIME date,
    T88_OPENING_DATETIME date,
    T88_RECORD_TYPE varchar2(1),
    T88_REF_INSTRUMENT varchar2(24),
    T88_TRADE_CODE varchar2(10),
    T88_TRADE_PROCESS varchar2(12),
    T88_TRADER varchar2(25),
    T88_TRADER_ID varchar2(25),
    T88_TRADE_ROLE varchar2(2),
    T88_TYPE varchar2(20),
    T88_VALUE_DATE date,
    T88_FX_RATE double precision,
    T88_FX_RATE_DATE date,
    T88_DEALT_RATE double precision,
    T88_MARGIN double precision,
    T88_USER_FIELD varchar2(16),
    primary key (T88_ID)
);
create table T89_SUNGARD_MM_NYC_LOAD (
    T89_ID number(19,0) not null,
    T89_LOAD_STATUS varchar2(10),
    T89_LOAD_TIME date,
    T89_LOAD_MESSAGE varchar2(255),
    T89_AMOUNT double precision,
    T89_BOOK varchar2(16),
    T89_COMMODITY varchar2(3),
    T89_COUNTERPARTY varchar2(22),
    T89_COUNTERPARTY_GROUP varchar2(20),
    T89_DEVIATION double precision,
    T89_GROUP_ID varchar2(10),
    T89_INFO varchar2(16),
    T89_INSTRUMENT_SUB_TYPE varchar2(2),
    T89_INSTRUMENT_TYPE varchar2(2),
    T89_MARKET_RATE double precision,
    T89_MATURITY_DATE date,
    T89_MEMO varchar2(75),
    T89_NET_RATE double precision,
    T89_NET_RATE_DATETIME date,
    T89_OPENING_DATETIME date,
    T89_RECORD_TYPE varchar2(1),
    T89_REF_INSTRUMENT varchar2(24),
    T89_START_DATE date,
    T89_TRADE_CODE varchar2(10),
    T89_TRADE_PROCESS varchar2(12),
    T89_TRADER varchar2(25),
    T89_TRADER_ID varchar2(25),
    T89_TRADE_ROLE varchar2(2),
    T89_TYPE varchar2(18),
    T89_FX_RATE double precision,
    T89_FX_RATE_DATE date,
    T89_DEALT_RATE double precision,
    T89_MARGIN double precision,
    T89_USER_FIELD varchar2(16),
    primary key (T89_ID)
);
create table T90_SUMMIT_DERIVATIVE_LOAD (
    T90_RECORD_NUMBER number(19,0) not null,
    t90_LOAD_STATUS varchar2(10),
    t90_LOAD_TIME date,
    T90_LOAD_MESSAGE varchar2(255),
    T90_AS_OF_DATE date,
    T90_TRADE_TYPE varchar2(50),
    T90_TRADE_SUB_TYPE varchar2(50),
    T90_TRADE_ID varchar2(50),
    T90_DEAL_ID varchar2(50),
    T90_VERSION number(10,0),
    T90_AUDIT_CURRENT varchar2(1),
    T90_STRUCTURE_ID varchar2(50),
    T90_TRADE_STATUS varchar2(50),
    T90_TRADE_CURRENCY varchar2(3),
    T90_ASSET_ID varchar2(50),
    T90_CALL_PUT varchar2(4),
    T90_PAY_CURRENCY varchar2(3),
    T90_PAY_NOTIONAL double precision,
    T90_RECEIVE_CURRENCY varchar2(3),
    T90_RECEIVE_NOTIONAL double precision,
    T90_START_DATE date,
    T90_MATURITY_DATE date,
    T90_PAY_INDEX_BASIS varchar2(50),
    T90_PAY_RATE_SPREAD double precision,
    T90_PAY_DISCOUNT_CURVE varchar2(50),
    T90_RECEIVE_INDEX_BASIS varchar2(50),
    T90_RECEIVE_RATE_SPREAD double precision,
    T90_RECEIVE_DISCOUNT_CURVE varchar2(50),
    T90_CAP_FLOOR varchar2(5),
    T90_BUY_SELL varchar2(4),
    T90_QUANTITY double precision,
    T90_UNDERLYING varchar2(255),
    T90_STRIKE double precision,
    T90_STYLE varchar2(50),
    T90_PREMIUM_AMOUNT double precision,
    T90_PREMIUM_CURRENCY varchar2(3),
    T90_EXPIRY_DATE date,
    T90_FIXING_DATE date,
    T90_PAYMENT_DATE date,
    T90_FIRST_COUPON_DATE date,
    T90_MODEL varchar2(255),
    T90_INSTRUMENT_STYLE varchar2(255),
    T90_FORMULA varchar2(50),
    T90_PRODUCT_NAME varchar2(60),
    T90_PROD_DATA varchar2(50),
    T90_NPV double precision,
    T90_MARKET_RATE_IR double precision,
    T90_MARKET_RATE_VOLA double precision,
    T90_FX_RATE double precision,
    T90_DELTA double precision,
    T90_DELTA_CURRENCY varchar2(3),
    T90_VEGA double precision,
    T90_VEGA_CURRENCY varchar2(3),
    T90_EXERCISED varchar2(50),
    T90_DONE_DATE date,
    T90_VERIFY_DATE date,
    T90_AMEND_DATE date,
    T90_CANCEL_DATE date,
    T90_COMMENT varchar2(100),
    T90_COMMENT2 varchar2(100),
    T90_COUNTERPARTY varchar2(50),
    T90_INTERNAL_EXTERNAL varchar2(10),
    T90_BOOK varchar2(50),
    T90_DESK varchar2(50),
    T90_COMPANY varchar2(50),
    T90_TRADER varchar2(50),
    t90_trade_date date,
    t90_amend_user varchar2(50),
    t90_audit_date date,
    t90_audit_user varchar2(50),
    t90_term_assign_status varchar2(50),
    t90_term_trade_date date,
    t90_term_input_date date,
    t90_term_eff_date date,
    t90_reset_remark varchar2(50),
    primary key (T90_RECORD_NUMBER)
);
create table T91_SUMMIT_DERIVATIVE (
    T91_ID number(19,0) not null,
    T91_AUDIT_CURRENT varchar2(1),
    T91_AS_OF_DATE date,
    T91_TRADE_TYPE varchar2(50),
    T91_VERSION number(10,0),
    T91_STRUCTURE_ID varchar2(50),
    T91_TRADE_STATUS varchar2(50),
    T91_DONE_DATE date,
    T91_VERIFY_DATE date,
    T91_CANCEL_DATE date,
    T91_COMMENT varchar2(100),
    T91_INTERNAL_EXTERNAL varchar2(1),
    T91_DESK varchar2(50),
    T91_COMPANY varchar2(50),
    T91_VEGA double precision,
    T91_DELTA double precision,
    T91_PREMIUM double precision,
    T91_EXERCISED varchar2(255),
    T91_AMEND_USER varchar2(50),
    T91_AUDIT_USER varchar2(50),
    T91_TERM_ASSIGN_STATUS varchar2(255),
    T91_TERM_TRADE_DATE date,
    T91_TERM_INPUT_DATE date,
    T91_TERM_EFF_DATE date,
    T91_DEVIATION double precision,
    T91_TURNOVER double precision,
    T91_IS_BAGATELLE char(1),
    T91_IS_HIGH_TURNOVER char(1),
    T91_IS_STANDRARD char(1),
    T91_IS_MCC_RELEVANT_CHANGE char(1),
    t91_reset_remark varchar2(50),
    T91_AMENDMENT_NPV_CHANGE double precision,
    T91_AMENDMENT_NPV double precision,
    primary key (T91_ID)
);
create table T92_ASSET (
    T92_ID number(19,0) not null,
    FK_T92_T02_TRADE number(19,0),
    FK_T92_T09_MANDANT VARCHAR2(3),
    T92_TRADE_SUB_TYPE varchar2(50),
    T92_ASSET_ID varchar2(50),
    T92_CALL_PUT varchar2(1),
    T92_PAY_CURRENCY varchar2(3),
    T92_PAY_NOTIONAL double precision,
    T92_RECEIVE_CURRENCY varchar2(3),
    T92_RECEIVE_NOTIONAL double precision,
    T92_START_DATE date,
    T92_MATURITY_DATE date,
    T92_PAY_INDEX_BASIS varchar2(50),
    T92_PAY_RATE_SPREAD double precision,
    T92_PAY_DISCOUNT_CURVE varchar2(50),
    T92_RECEIVE_INDEX_BASIS varchar2(50),
    T92_RECEIVE_RATE_SPREAD double precision,
    T92_RECEIVE_DISCOUNT_CURVE varchar2(50),
    T92_CAP_FLOOR varchar2(1),
    T92_BUY_SELL varchar2(1),
    T92_QUANTITY double precision,
    T92_UNDERLYING varchar2(255),
    T92_STRIKE double precision,
    T92_STYLE varchar2(50),
    T92_PREMIUM_AMOUNT double precision,
    T92_PREMIUM_CURRENCY varchar2(3),
    T92_EXPIRY_DATE date,
    T92_FIXING_DATE date,
    T92_PAYMENT_DATE date,
    T92_FIRST_COUPON_DATE date,
    T92_MODEL varchar2(255),
    T92_INSTRUMENT_STYLE varchar2(255),
    T92_FORMULA varchar2(50),
    T92_PRODUCT_NAME varchar2(60),
    T92_PROD_DATA varchar2(50),
    T92_NPV double precision,
    T92_MARKET_RATE_IR double precision,
    T92_MARKET_RATE_VOLA double precision,
    T92_FX_RATE double precision,
    T92_DELTA double precision,
    T92_DELTA_CURRENCY varchar2(3),
    T92_VEGA double precision,
    T92_VEGA_CURRENCY varchar2(3),
    T92_CHANGED_DATE date,
    T92_CHANGED_BY varchar2(10),
    T92_CREATED_BY varchar2(10),
    T92_CREATION_DATE date,
    primary key (T92_ID)
);
create table T93_SUMMIT_DERIVATIVE_DEFAULT (
    T93_ID number(19,0) not null,
    primary key (T93_ID)
);
create table T94_SUMMIT_DERIVATIVE_EXOTIC (
    T94_ID number(19,0) not null,
    primary key (T94_ID)
);
create table T95_SUMMIT_REPO_HKG (
    T95_ID number(19,0) not null,
    primary key (T95_ID)
);
create table T96_MM_FX (
    T96_ID number(19,0) not null,
    primary key (T96_ID)
);
create table T97_SUMMIT_MM_DUS_LOAD (
    T97_RECORD_NUMBER number(19,0) not null,
    T97_LOAD_STATUS varchar2(10),
    T97_LOAD_TIME date,
    T97_LOAD_MESSAGE varchar2(255),
    T97_trade_id varchar2(50),
    T97_trade_version number(10,0),
    T97_status varchar2(50),
    T97_ccy varchar2(3),
    T97_type varchar2(50),
    T97_sub_type varchar2(50),
    T97_index varchar2(50),
    T97_start_date date,
    T97_maturity_date date,
    T97_trade_date date,
    T97_trade_entry_date date,
    T97_amend_date date,
    T97_internal_rate double precision,
    T97_spread double precision,
    T97_margin_points double precision,
    T97_dealt_rate double precision,
    T97_market_rate_1 double precision,
    T97_market_rate_2 double precision,
    T97_notional double precision,
    T97_trader_login_id varchar2(50),
    T97_trader_name varchar2(50),
    T97_counterparty varchar2(50),
    T97_book varchar2(50),
    T97_comment varchar2(255),
    T97_structure_id varchar2(50),
    T97_eod_rate double precision,
    T97_eod_rate_date date,
    T97_auto_rate_reset varchar2(10),
    T97_eod_rate_mm double precision,
    primary key (T97_RECORD_NUMBER)
);
create table T98_SUMMIT_FX_DUS_LOAD (
    T98_RECORD_NUMBER number(19,0) not null,
    T98_LOAD_STATUS varchar2(10),
    T98_LOAD_TIME date,
    T98_LOAD_MESSAGE varchar2(255),
    t98_trade_id varchar2(50),
    t98_trade_version number(10,0),
    t98_status varchar2(50),
    t98_ccy_pair varchar2(7),
    t98_type varchar2(50),
    t98_sub_type varchar2(50),
    t98_near_date date,
    t98_far_date date,
    t98_trade_date date,
    t98_trade_entry_date date,
    t98_amend_date date,
    t98_spot_rate double precision,
    t98_fwd_points_near_leg double precision,
    t98_fwd_points_far_leg double precision,
    t98_margin_points double precision,
    t98_margin_rate double precision,
    t98_near_amount_ccy1 double precision,
    t98_far_amount_ccy1 double precision,
    t98_market_rate_spot double precision,
    t98_market_points_near_leg double precision,
    t98_market_points_far_leg double precision,
    t98_market_rate_fwd double precision,
    t98_trader_login_id varchar2(50),
    t98_trader_name varchar2(50),
    t98_counterparty varchar2(50),
    t98_book varchar2(50),
    t98_comment varchar2(255),
    t98_eod_rate double precision,
    t98_eod_rate_date date,
    t98_eod_rate_fx double precision,
    primary key (T98_RECORD_NUMBER)
);
create table T99_SUMMIT_MM (
    T99_ID number(19,0) not null,
    T99_trade_version number(10,0),
    T99_status varchar2(50),
    T99_type varchar2(50),
    T99_sub_type varchar2(50),
    T99_index varchar2(50),
    T99_info varchar2(255),
    T99_maturity_date date,
    T99_margin_points double precision,
    T99_MARKET_RATE double precision,
    T99_eod_rate double precision,
    T99_eod_rate_date date,
    T99_auto_rate_reset char(1),
    T99_eod_rate_mm double precision,
    T99_BAGATELLE_LIMIT double precision,
    T99_TURNOVER_LIMIT double precision,
    T99_IS_BAGATELLE char(1),
    T99_IS_OUT_OF_TURNOVER_LIMIT char(1),
    primary key (T99_ID)
);
create table t118_request_mapping_load (
    T118_INSTRUMENT varchar2(50) not null,
    T118_REQUEST_STRING varchar2(255),
    T118_CONVERSION_FACTOR double precision,
    T118_LOAD_STATUS varchar2(10),
    T118_LOAD_TIME date,
    T118_LOAD_MESSAGE varchar2(255),
    primary key (T118_INSTRUMENT)
);
create table t60_SUMMIT_REPO_LOAD (
    T60_record_NUMBER number(19,0) not null,
    t60_TRADE_ID varchar2(50),
    t60_LOAD_STATUS varchar2(10),
    t60_LOAD_TIME date,
    T60_LOAD_MESSAGE varchar2(255),
    t60_VERSION number(10,0),
    t60_STATUS varchar2(50),
    t60_INSTRUMENT varchar2(50),
    t60_BOOK_ID varchar2(50),
    t60_TRADE_TYPE varchar2(50),
    t60_TRADER_ID varchar2(255),
    t60_ISIN varchar2(50),
    t60_UNDERLYING_INSTRUMENT_TYPE varchar2(50),
    t60_UNDERLYING_SUB_TYPE varchar2(50),
    t60_UNDERLYING_CATEGORY varchar2(50),
    t60_CURRENCY varchar2(3),
    t60_YIELDCURVE varchar2(50),
    t60_TRADE_TIME date,
    t60_START date,
    t60_END date,
    t60_DAY_COUNT varchar2(10),
    t60_REPO_RATE double precision,
    t60_YIELD_CURVE_RATE double precision,
    t60_STARTCASH double precision,
    t60_ENDCASH double precision,
    t60_DAYS double precision,
    t60_FOREIGN_EXCHANGE_RATE double precision,
    t60_OPENEND_FLAG varchar2(1),
    t60_COUNTERPARTY varchar2(50),
    t60_NOTIONAL_AMOUNT double precision,
    T60_FUNDING_SPREAD double precision,
    T60_CUSTOMER_TYPE varchar2(50),
    T60_AMEND_DATE date,
    T60_DONE_DATE date,
    T60_VERIFY_DATE date,
    T60_MARKET_PRICE_UNDERLYING double precision,
    T60_DEAL_ACCRUED_INTEREST double precision,
    T60_START_PRICE double precision,
    T60_BOND_ACCRUED_INTEREST double precision,
    T60_NPV double precision,
    T60_MARKET_TYPE varchar2(50),
    primary key (T60_record_NUMBER)
);
create index I01_EMPLOYEE on T01_EMPLOYEE (T01_NT_ID);
alter table T01_EMPLOYEE 
    add constraint FKAE687658B1E30346 
    foreign key (FK_T01_T02_MANDANT) 
    references T09_MANDANT;
create index I02_RECL_STATE on T02_TRADE (FK_T02_T04_RECL_STATE);
create index I02_book on T02_TRADE (FK_T02_T44_BOOK);
create index I02_TRADE_DATE on T02_TRADE (T02_TRADE_DATE);
create index I02_SRC_TRADE_ID on T02_TRADE (T02_SRC_TRADE_ID);
create index I02_JOB on T02_TRADE (FK_T02_T08_JOB);
create index I02_MAN_STATE on T02_TRADE (FK_T02_T04_MANUAL_STATE);
create index I02_TRADE_GROUP_ID on T02_TRADE (T02_TRADE_GROUP_ID);
create index I02_AUTO_STATE on T02_TRADE (FK_T02_T04_AUTO_STATE);
create index I02_COUNTERPARTY on T02_TRADE (FK_T02_T67_COUNTERPARTY);
alter table T02_TRADE 
    add constraint FK1C86C51BF14FB9AA 
    foreign key (FK_T02_T18_TRADER) 
    references T18_TRADER;
alter table T02_TRADE 
    add constraint FK1C86C51BA29425EE 
    foreign key (FK_T02_T04_MANUAL_STATE) 
    references T04_TRADE_HISTORIE;
alter table T02_TRADE 
    add constraint FK1C86C51B6279985A 
    foreign key (FK_T02_T04_RECL_STATE) 
    references T04_TRADE_HISTORIE;
alter table T02_TRADE 
    add constraint FK1C86C51B97F30700 
    foreign key (FK_T02_T04_AUTO_STATE) 
    references T04_TRADE_HISTORIE;
alter table T02_TRADE 
    add constraint FK1C86C51BAE4512CE 
    foreign key (FK_T02_T09_MANDANT) 
    references T09_MANDANT;
alter table T02_TRADE 
    add constraint FK1C86C51B7E865DA7 
    foreign key (FK_T02_T44_BOOK) 
    references T44_BOOK;
alter table T02_TRADE 
    add constraint FK1C86C51B4BFDD088 
    foreign key (FK_T02_T67_COUNTERPARTY) 
    references T67_COUNTERPARTY;
alter table T02_TRADE 
    add constraint FK1C86C51B9BA39E0D 
    foreign key (FK_T02_T08_JOB) 
    references T08_JOB;
alter table T02_TRADE 
    add constraint FK1C86C51B59911DBD 
    foreign key (FK_T02_T07_SOURCE_SYSTEM) 
    references T07_SOURCE_SYSTEM;
alter table T02_TRADE 
    add constraint FK1C86C51B1938469D 
    foreign key (FK_T02_T05_INSTRUMENT) 
    references T05_INSTRUMENT;
alter table T03_EXCHANGE 
    add constraint FKC672000B9CC8194F 
    foreign key (FK_T03_T09_MANDANT) 
    references T09_MANDANT;
create index I04_TRADE on T04_TRADE_HISTORIE (FK_T04_T02_TRADE);
create index I04_RECL_STATE on T04_TRADE_HISTORIE (FK_T04_T10_RECL_STATE_CODE, FK_T04_T10_RECL_MANDANT_CODE);
create index I04_AUTO_STATE on T04_TRADE_HISTORIE (FK_T04_T10_AUTO_STATE_CODE, FK_T04_T10_AUTO_MANDANT_CODE);
create index I04_MAN_STATE on T04_TRADE_HISTORIE (FK_T04_T10_MAN_STATE_CODE, FK_T04_T10_MAN_MANDANT_CODE);
alter table T04_TRADE_HISTORIE 
    add constraint FK89F152C3FF2FF7D0 
    foreign key (FK_T04_T10_MAN_STATE_CODE, FK_T04_T10_MAN_MANDANT_CODE) 
    references T10_STATE_CODE;
alter table T04_TRADE_HISTORIE 
    add constraint FK89F152C34D0750AE 
    foreign key (FK_T04_T01_CREATED_BY) 
    references T01_EMPLOYEE;
alter table T04_TRADE_HISTORIE 
    add constraint FK89F152C397E1D05D 
    foreign key (FK_T04_T10_AUTO_STATE_CODE, FK_T04_T10_AUTO_MANDANT_CODE) 
    references T10_STATE_CODE;
alter table T04_TRADE_HISTORIE 
    add constraint FK89F152C32920B717 
    foreign key (FK_T04_T02_TRADE) 
    references T02_TRADE;
alter table T04_TRADE_HISTORIE 
    add constraint FK89F152C3F15C20C1 
    foreign key (FK_T04_T10_RECL_STATE_CODE, FK_T04_T10_RECL_MANDANT_CODE) 
    references T10_STATE_CODE;
alter table T04_TRADE_HISTORIE 
    add constraint FK89F152C3A489F198 
    foreign key (FK_T04_T15_REPORT_IMAGE) 
    references T15_REPORT_IMAGE;
create index I05_INSTRUMENT on T05_INSTRUMENT (T05_INSTRUMENT);
alter table T05_INSTRUMENT 
    add constraint FK1E14428D74BD62CB 
    foreign key (FK_T05_T11_PRICE_CHECK) 
    references T11_PRICE_CHECK_CATEGORY;
alter table T05_INSTRUMENT 
    add constraint FK1E14428D79CE2651 
    foreign key (FK_T05_T09_MANDANT) 
    references T09_MANDANT;
create index I06_PRICE_RESULT on T06_REQUEST (FK_T06_T12_PRICE_RESULT);
create index I06_TRADE on T06_REQUEST (FK_T06_T02_TRADE);
alter table T06_REQUEST 
    add constraint FKE7E96C8AF1319871 
    foreign key (FK_T06_T13_ADDON) 
    references T13_ADDON;
alter table T06_REQUEST 
    add constraint FKE7E96C8A68512CD2 
    foreign key (FK_T06_T09_MANDANT) 
    references T09_MANDANT;
alter table T06_REQUEST 
    add constraint FKE7E96C8A65B31BE3 
    foreign key (FK_T06_T11_PRICE_CHECK_CAT) 
    references T11_PRICE_CHECK_CATEGORY;
alter table T06_REQUEST 
    add constraint FKE7E96C8A52EA1C99 
    foreign key (FK_T06_T02_TRADE) 
    references T02_TRADE;
alter table T06_REQUEST 
    add constraint FKE7E96C8A3065A0BE 
    foreign key (FK_T06_T12_PRICE_RESULT) 
    references T12_PRICE;
alter table T07_SOURCE_SYSTEM 
    add constraint FK48045AEF56D43353 
    foreign key (FK_T07_T09_MANDANT) 
    references T09_MANDANT;
create index I08_START_BUSINESS_TIME on T08_JOB (T08_START_BUSINESS_TIME);
create index I08_STOP_BUSINESS_TIME on T08_JOB (T08_STOP_BUSINESS_TIME);
alter table T08_JOB 
    add constraint FKB0B5823A2B8F6843 
    foreign key (FK_T08_T07_SOURCE_SYSTEM) 
    references T07_SOURCE_SYSTEM;
alter table T08_JOB 
    add constraint FKB0B5823A455739D4 
    foreign key (FK_T08_T09_MANDANT) 
    references T09_MANDANT;
alter table T100_SUMMIT_MM_DUS 
    add constraint FK9229A773C1EFD3A5 
    foreign key (T100_ID) 
    references T99_SUMMIT_MM;
alter table T101_SUMMIT_FX 
    add constraint FK5CF5C843958DCD2F 
    foreign key (T101_ID) 
    references T96_MM_FX;
alter table T102_SUMMIT_FX_DUS 
    add constraint FK3A8992279B2D5EDE 
    foreign key (T102_ID) 
    references T101_SUMMIT_FX;
create index I104_TRADE_ID_NEW on T104_SUMMIT_AMEND (T104_TRADE_ID_NEW);
alter table T106_LOANIQ_BOND_LON 
    add constraint FKA4F8CEDAC14F8221 
    foreign key (T106_ID) 
    references T25_BOND;
alter table T107_SUMMIT_BOND_EAA 
    add constraint FKB9242E6022DC2C8D 
    foreign key (T107_ID) 
    references T57_SUMMIT_BOND;
alter table T108_SUMMIT_REPO_EAA 
    add constraint FK7AE73D9093F5D21B 
    foreign key (T108_ID) 
    references T59_SUMMIT_REPO;
alter table T109_SUMMIT_DERIVATIVE_EAA 
    add constraint FK9D594C669B40C30F 
    foreign key (T109_ID) 
    references T91_SUMMIT_DERIVATIVE;
alter table T10_STATE_CODE 
    add constraint FK871C6F47F1F867BD 
    foreign key (FK_T10_T09_STATE_MANDANT) 
    references T09_MANDANT;
alter table T10_STATE_CODE 
    add constraint FK871C6F4718B92F37 
    foreign key (FK_T10_T10_RECL_STATE_CODE, FK_T10_T10_RECL_MANDANT_CODE) 
    references T10_STATE_CODE;
alter table T10_STATE_CODE 
    add constraint FK871C6F4785EDA22A 
    foreign key (FK_T10_T10_SAMPLE_STATE_CODE, FK_T10_T10_SAMPLE_MANDANT_CODE) 
    references T10_STATE_CODE;
alter table T110_SUMMIT_MM_LON 
    add constraint FK73BBA29BC1FDEB26 
    foreign key (T110_ID) 
    references T99_SUMMIT_MM;
alter table T111_SUMMIT_MM_NYC 
    add constraint FKCD971589C1FE5F85 
    foreign key (T111_ID) 
    references T99_SUMMIT_MM;
alter table T112_SUMMIT_MM_HKG 
    add constraint FK27726796C1FED3E4 
    foreign key (T112_ID) 
    references T99_SUMMIT_MM;
alter table T113_SUMMIT_MM_EAA 
    add constraint FK814DC558C1FF4843 
    foreign key (T113_ID) 
    references T99_SUMMIT_MM;
alter table T114_SUMMIT_FX_LON 
    add constraint FKCFD261D19B3C5F1D 
    foreign key (T114_ID) 
    references T101_SUMMIT_FX;
alter table T115_SUMMIT_FX_NYC 
    add constraint FK29ADD4BF9B3CD37C 
    foreign key (T115_ID) 
    references T101_SUMMIT_FX;
alter table T116_SUMMIT_FX_HKG 
    add constraint FK838926CC9B3D47DB 
    foreign key (T116_ID) 
    references T101_SUMMIT_FX;
alter table T117_SUMMIT_FX_EAA 
    add constraint FKDD64848E9B3DBC3A 
    foreign key (T117_ID) 
    references T101_SUMMIT_FX;
alter table T11_PRICE_CHECK_CATEGORY 
    add constraint FKE7831E36544F886C 
    foreign key (FK_T11_T10_SAMPLE_STATE_CODE, FK_T11_T10_SAMPLE_MANDANT_CODE) 
    references T10_STATE_CODE;
alter table T11_PRICE_CHECK_CATEGORY 
    add constraint FKE7831E36A19FD5EC 
    foreign key (FK_T11_T09_MANDANT) 
    references T09_MANDANT;
alter table T12_PRICE 
    add constraint FK842FB1DF9022DC6D 
    foreign key (FK_T12_T09_MANDANT) 
    references T09_MANDANT;
alter table T13_ADDON 
    add constraint FKB83C1E177EA5E2EE 
    foreign key (FK_T13_T09_MANDANT) 
    references T09_MANDANT;
alter table T14_MARKET_DATA_KEY 
    add constraint FK23484A056D28E96F 
    foreign key (FK_T14_T09_MANDANT) 
    references T09_MANDANT;
alter table T16_DUAL_CONTROL_JOB 
    add constraint FKA26C305E52EF5EDB 
    foreign key (FK_T16_T11_PRICE_CHECK_OBJECT) 
    references T11_PRICE_CHECK_CATEGORY;
alter table T16_DUAL_CONTROL_JOB 
    add constraint FKA26C305E7B9BE900 
    foreign key (FK_T16_T21_EXCHANGE_MAP_OBJECT) 
    references T21_EXCHANGE_MAPPING;
alter table T16_DUAL_CONTROL_JOB 
    add constraint FKA26C305ED3893D1D 
    foreign key (FK_T16_T03_EXCHANGE_OBJECT) 
    references T03_EXCHANGE;
alter table T16_DUAL_CONTROL_JOB 
    add constraint FKA26C305EF566900E 
    foreign key (FK_T16_T01_REQ_EMPLOYEE) 
    references T01_EMPLOYEE;
alter table T16_DUAL_CONTROL_JOB 
    add constraint FKA26C305EEEA38401 
    foreign key (FK_T16_T01_COMMIT_EMPLOYEE) 
    references T01_EMPLOYEE;
alter table T16_DUAL_CONTROL_JOB 
    add constraint FKA26C305E4A2EF671 
    foreign key (FK_T16_T09_MANDANT) 
    references T09_MANDANT;
alter table T16_DUAL_CONTROL_JOB 
    add constraint FKA26C305EA53C5E5F 
    foreign key (FK_T16_T05_INSTRUMENT_OBJECT) 
    references T05_INSTRUMENT;
alter table T16_DUAL_CONTROL_JOB 
    add constraint FKA26C305EC58D9FE0 
    foreign key (FK_T16_T13_ADDON_OBJECT) 
    references T13_ADDON;
alter table T16_DUAL_CONTROL_JOB 
    add constraint FKA26C305E394235DF 
    foreign key (FK_T16_T42_MGB_CONFIG_KEY, FK_T16_T42_MGB_CONFIG_MANDANT) 
    references T42_MGB_CONFIGURATION;
create index I17_EMPLOYEE on T17_USER_ROLE (FK_T17_T01_EMPLOYEE);
alter table T17_USER_ROLE 
    add constraint FK1C791FE59EDC742C 
    foreign key (FK_T17_T01_EMPLOYEE) 
    references T01_EMPLOYEE;
alter table T17_USER_ROLE 
    add constraint FK1C791FE538B1FCF2 
    foreign key (FK_T17_T09_MANDANT) 
    references T09_MANDANT;
alter table T17_USER_ROLE 
    add constraint FK1C791FE5F03D0736 
    foreign key (FK_T17_T29_ROLE) 
    references T29_ROLE;
create index I18_SOURCE_TRADER on T18_TRADER (FK_T18_T07_SOURCE_SYSTEM, T18_TRADER_CODE);
alter table T18_TRADER 
    add constraint FK77DD5AD280BA3DCB 
    foreign key (FK_T18_T01_EMPLOYEE) 
    references T01_EMPLOYEE;
alter table T18_TRADER 
    add constraint FK77DD5AD213313EA2 
    foreign key (FK_T18_T07_SOURCE_SYSTEM) 
    references T07_SOURCE_SYSTEM;
alter table T18_TRADER 
    add constraint FK77DD5AD227350373 
    foreign key (FK_T18_T09_MANDANT) 
    references T09_MANDANT;
alter table T20_FIDESSA_EQUITY 
    add constraint FK999F4CB4456D4C54 
    foreign key (T20_ID) 
    references T23_EQUITY;
alter table T21_EXCHANGE_MAPPING 
    add constraint FK8EDB0FBE837D9F8B 
    foreign key (FK_T21_T09_MANDANT) 
    references T09_MANDANT;
alter table T21_EXCHANGE_MAPPING 
    add constraint FK8EDB0FBE81CA935B 
    foreign key (FK_T21_T03_EXCHANGE) 
    references T03_EXCHANGE;
alter table T21_EXCHANGE_MAPPING 
    add constraint FK8EDB0FBE5B2A68BA 
    foreign key (FK_T21_T07_SOURCE_SYSTEM) 
    references T07_SOURCE_SYSTEM;
alter table T21_EXCHANGE_MAPPING 
    add constraint FK8EDB0FBEA7BBCBC0 
    foreign key (FK_T21_T22_MAPPING_PRIO) 
    references T22_EXCHANGE_MAPPING_PRIORITY;
alter table T22_EXCHANGE_MAPPING_PRIORITY 
    add constraint FKBB8C71C67200A60C 
    foreign key (FK_T22_T09_MANDANT) 
    references T09_MANDANT;
alter table T23_EQUITY 
    add constraint FKEB1E4CAFA08099A2 
    foreign key (T23_ID) 
    references T02_TRADE;
alter table T23_EQUITY 
    add constraint FKEB1E4CAF363B19CE 
    foreign key (FK_T23_T22_EXCHANGE_MAP_PRIO) 
    references T22_EXCHANGE_MAPPING_PRIORITY;
alter table T24_PRIME_EQUITY 
    add constraint FK6A2C28AE456F1DD0 
    foreign key (T24_ID) 
    references T23_EQUITY;
alter table T25_BOND 
    add constraint FKCAA5106BA0818260 
    foreign key (T25_ID) 
    references T02_TRADE;
alter table T26_REPO 
    add constraint FKCC6109B9A081F6BF 
    foreign key (T26_ID) 
    references T02_TRADE;
alter table T27_MONEY_MARKET 
    add constraint FK87A9B1A1FAE8E94 
    foreign key (T27_ID) 
    references T96_MM_FX;
alter table T27_MONEY_MARKET 
    add constraint FK87A9B1A1A4589807 
    foreign key (FK_T27_T39_TRADE_TYPE) 
    references T39_SUNGARD_TRADE_TYPE;
alter table T28_CONDITION 
    add constraint FK6FBA0096FAD31501 
    foreign key (FK_T28_T07_SOURCE_SYSTEM) 
    references T07_SOURCE_SYSTEM;
alter table T30_ATLAS_BOND 
    add constraint FKD87275653B78E693 
    foreign key (T30_ID) 
    references T25_BOND;
alter table T30_ATLAS_BOND 
    add constraint FKD8727565E3A770CA 
    foreign key (FK_T30_T05_BLB_INSTRUMENT) 
    references T05_INSTRUMENT;
alter table T31_ATLAS_REPO 
    add constraint FKE02B3B73AC928C21 
    foreign key (T31_ID) 
    references T26_REPO;
alter table T32_SUNGARD_MONEY_MARKET 
    add constraint FK8F6723EE6D4289BC 
    foreign key (T32_ID) 
    references T27_MONEY_MARKET;
alter table T34_STATE_RULES 
    add constraint FKDB0F5FFF8589EA5D 
    foreign key (FK_T34_T10_FINAL_STATE_CODE, FK_T34_T10_MANDANT_CODE) 
    references T10_STATE_CODE;
alter table T34_STATE_RULES 
    add constraint FKDB0F5FFFABCB645C 
    foreign key (FK_T34_T07_SOURCE_SYSTEM) 
    references T07_SOURCE_SYSTEM;
alter table T38_SUNGARD_FOREIGN_EXCHANGE 
    add constraint FK2EDA89BB6D4543F6 
    foreign key (T38_ID) 
    references T27_MONEY_MARKET;
alter table T39_SUNGARD_TRADE_TYPE 
    add constraint FK4F6C39E1D9739D32 
    foreign key (FK_T39_T09_MANDANT) 
    references T09_MANDANT;
alter table T42_MGB_CONFIGURATION 
    add constraint FK47D72D9235BC394A 
    foreign key (FK_T42_T09_MANDANT) 
    references T09_MANDANT;
alter table T43_CHANGE_REVISION_LOG 
    add constraint FK414A083CD797887 
    foreign key (FK_T43_T16_DUAL_CONTROL_JOB) 
    references T16_DUAL_CONTROL_JOB;
create index I44_LOCATION_TRADER on T44_BOOK (T44_LOCATION_TRADER);
alter table T47_ACCESS_CONTROL 
    add constraint FKD1F200CAAEEB1F79 
    foreign key (FK_T47_T29_ROLE) 
    references T29_ROLE;
create index I48_TRADE on T48_MAIL (FK_T48_T02_TRADE);
alter table T48_MAIL 
    add constraint FK3994DBBE16F57FCF 
    foreign key (FK_T48_T01_RECEIVER) 
    references T01_EMPLOYEE;
alter table T48_MAIL 
    add constraint FK3994DBBE6A137170 
    foreign key (FK_T48_T48_PARENT) 
    references T48_MAIL;
alter table T48_MAIL 
    add constraint FK3994DBBEF7315ED5 
    foreign key (FK_T48_T01_SENDER) 
    references T01_EMPLOYEE;
alter table T48_MAIL 
    add constraint FK3994DBBE9B7A1797 
    foreign key (FK_T48_T02_TRADE) 
    references T02_TRADE;
alter table T49_RATING_CATEGORY 
    add constraint FKFFD6FEFABB5166D1 
    foreign key (FK_T49_T09_MANDANT) 
    references T09_MANDANT;
alter table T50_RATING_CATEGORY_MAPPING 
    add constraint FKC03B4E7F9214C061 
    foreign key (FK_T50_T49_CATEGORY, FK_T50_T09_MANDANT) 
    references T49_RATING_CATEGORY;
alter table T51_CURRENCY_CATEGORY 
    add constraint FK478A10DD2916FC68 
    foreign key (FK_T51_T09_MANDANT) 
    references T09_MANDANT;
alter table T52_CURRNCY_CATEGORY_MAPPING 
    add constraint FKB98463C82505D51E 
    foreign key (FK_T52_T51_CATEGORY, FK_T52_T09_MANDANT) 
    references T51_CURRENCY_CATEGORY;
alter table T53_TRADE_TYPE_CATEGORY 
    add constraint FK11837EDB61D096A 
    foreign key (FK_T53_T09_MANDANT) 
    references T09_MANDANT;
alter table T54_TRADETYPE_CATEGORY_MAPPING 
    add constraint FKBE9A9FDA86A1F31B 
    foreign key (FK_T54_T53_CATEGORY, FK_T54_T09_MANDANT) 
    references T53_TRADE_TYPE_CATEGORY;
alter table T55_PRIME_REPO 
    add constraint FK2F00087DACB08C9F 
    foreign key (T55_ID) 
    references T26_REPO;
alter table T57_SUMMIT_BOND 
    add constraint FK3C2CAACC52463865 
    foreign key (FK_T57_T05_BLB_INSTRUMENT) 
    references T05_INSTRUMENT;
alter table T57_SUMMIT_BOND 
    add constraint FK3C2CAACC3B98442E 
    foreign key (T57_ID) 
    references T25_BOND;
alter table T59_SUMMIT_REPO 
    add constraint FK1939D87DB4090289 
    foreign key (FK_T59_T05_BOND_INSTRUMENT) 
    references T05_INSTRUMENT;
alter table T59_SUMMIT_REPO 
    add constraint FK1939D87D2189E523 
    foreign key (FK_T59_T05_BLB_INSTRUMENT) 
    references T05_INSTRUMENT;
alter table T59_SUMMIT_REPO 
    add constraint FK1939D87DACB25E1B 
    foreign key (T59_ID) 
    references T26_REPO;
alter table T61_TIME_PERIOD_CATEGORY 
    add constraint FK96EC4C7AAF4C607 
    foreign key (FK_T61_T09_MANDANT) 
    references T09_MANDANT;
alter table T62_MAIL_RECIPIENT 
    add constraint FK4D5ECB80F6D09756 
    foreign key (FK_T62_T01_EMPLOYEE) 
    references T01_EMPLOYEE;
alter table T62_MAIL_RECIPIENT 
    add constraint FK4D5ECB808AA16F45 
    foreign key (FK_T62_T48_MAIL) 
    references T48_MAIL;
alter table T63_MGB_TASK 
    add constraint FKFF255FEAE7FAD309 
    foreign key (FK_T63_T09_MANDANT) 
    references T09_MANDANT;
alter table T64_SUMMIT_BOND_DUS 
    add constraint FK7FC2E60B9D31349F 
    foreign key (T64_ID) 
    references T57_SUMMIT_BOND;
alter table T65_SUMMIT_BOND_LON 
    add constraint FKD06CE1559D31A8FE 
    foreign key (T65_ID) 
    references T57_SUMMIT_BOND;
create index I67_SOURCE_SYSTEM on T67_COUNTERPARTY (FK_T67_T07_SOURCE_SYSTEM);
alter table T67_COUNTERPARTY 
    add constraint FK291CFC541163B04C 
    foreign key (FK_T67_T66_SPARKASSE) 
    references T66_SPARKASSE;
alter table T67_COUNTERPARTY 
    add constraint FK291CFC54CBB00CBC 
    foreign key (FK_T67_T07_SOURCE_SYSTEM) 
    references T07_SOURCE_SYSTEM;
alter table T68_SUMMIT_BOND_LUX 
    add constraint FKC26A7C1C9D33061B 
    foreign key (T68_ID) 
    references T57_SUMMIT_BOND;
alter table T69_SUNGARD_MM_LUX 
    add constraint FK6B31195F6D6FFED8 
    foreign key (T69_ID) 
    references T27_MONEY_MARKET;
alter table T70_SUNGARD_FX_LUX 
    add constraint FKC26340BB6D79FF02 
    foreign key (T70_ID) 
    references T27_MONEY_MARKET;
alter table T73_SUMMIT_BOND_ASIA 
    add constraint FK81B0F5639D3ED7C1 
    foreign key (T73_ID) 
    references T57_SUMMIT_BOND;
alter table T74_SUMMIT_REPO_DUS 
    add constraint FKB56DF959E587D4F 
    foreign key (T74_ID) 
    references T59_SUMMIT_REPO;
alter table T75_SUMMIT_REPO_LON 
    add constraint FK617F4A3E58F1AE 
    foreign key (T75_ID) 
    references T59_SUMMIT_REPO;
alter table T76_SUNGARD_MM_LON 
    add constraint FK17255CFF6D7CB93C 
    foreign key (T76_ID) 
    references T27_MONEY_MARKET;
alter table T77_SUNGARD_FX_LON 
    add constraint FKED6051106D7D2D9B 
    foreign key (T77_ID) 
    references T27_MONEY_MARKET;
alter table T80_SUMMIT_REPO_ASIA 
    add constraint FKB3B2A578E64C354 
    foreign key (T80_ID) 
    references T59_SUMMIT_REPO;
alter table T81_SUMMIT_BOND_HKG 
    add constraint FK16E91E089D4C0684 
    foreign key (T81_ID) 
    references T57_SUMMIT_BOND;
alter table T82_SUNGARD_MM_HKG 
    add constraint FKE187B3FD6D88FF41 
    foreign key (T82_ID) 
    references T27_MONEY_MARKET;
alter table T83_SUNGARD_FX_HKG 
    add constraint FKB7C2A80E6D8973A0 
    foreign key (T83_ID) 
    references T27_MONEY_MARKET;
alter table T84_SUNGARD_MM_TOK 
    add constraint FKA4AB9D476D89E7FF 
    foreign key (T84_ID) 
    references T27_MONEY_MARKET;
alter table T85_SUNGARD_FX_TOK 
    add constraint FK7AE691586D8A5C5E 
    foreign key (T85_ID) 
    references T27_MONEY_MARKET;
alter table T86_SUNGARD_MM_NYC 
    add constraint FK67CF43AD6D8AD0BD 
    foreign key (T86_ID) 
    references T27_MONEY_MARKET;
alter table T87_SUNGARD_FX_NYC 
    add constraint FK3E0A37BE6D8B451C 
    foreign key (T87_ID) 
    references T27_MONEY_MARKET;
alter table T91_SUMMIT_DERIVATIVE 
    add constraint FK9EEBE1C6A0E2556B 
    foreign key (T91_ID) 
    references T02_TRADE;
create index I92_TRADE on T92_ASSET (FK_T92_T02_TRADE);
alter table T92_ASSET 
    add constraint FKC266527EB971984A 
    foreign key (FK_T92_T02_TRADE) 
    references T91_SUMMIT_DERIVATIVE;
alter table T92_ASSET 
    add constraint FKC266527E449621EC 
    foreign key (FK_T92_T02_TRADE) 
    references T02_TRADE;
alter table T92_ASSET 
    add constraint FKC266527E9F112965 
    foreign key (FK_T92_T09_MANDANT) 
    references T09_MANDANT;
alter table T93_SUMMIT_DERIVATIVE_DEFAULT 
    add constraint FKDC79E62A15BEB487 
    foreign key (T93_ID) 
    references T91_SUMMIT_DERIVATIVE;
alter table T94_SUMMIT_DERIVATIVE_EXOTIC 
    add constraint FK35054EE815BF28E6 
    foreign key (T94_ID) 
    references T91_SUMMIT_DERIVATIVE;
alter table T95_SUMMIT_REPO_HKG 
    add constraint FK8F3BA95AE7520B0 
    foreign key (T95_ID) 
    references T59_SUMMIT_REPO;
alter table T96_MM_FX 
    add constraint FK96A5CC83A0E49B46 
    foreign key (T96_ID) 
    references T02_TRADE;
alter table T99_SUMMIT_MM 
    add constraint FKC87DEAE710121BD9 
    foreign key (T99_ID) 
    references T96_MM_FX;
create sequence HIB_SEQ;
