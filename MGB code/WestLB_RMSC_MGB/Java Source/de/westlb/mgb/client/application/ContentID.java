package de.westlb.mgb.client.application;

/**
 * @author M. Boerner
 *
 * Contains all constants to identify application components.
 */
public interface ContentID {
   // Standard application panel components
   public static final String BLANK								= "BLANK";
   public static final String S3LOGO							= "S3LOGO";
   public static final String S3START							= "S3START";
   public static final String S3TOOLBAR							= "S3TOOLBAR";
   public static final String S3BAR								= "S3BAR";
   public static final String S3MENU							= "S3MENU";
   public static final String S3TICKER							= "S3TICKER";
   public static final String S3STATUSBAR						= "S3STATUSBAR";
   public static final String S3FASTSEARCH						= "S3FASTSEARCH";
   public static final String S3ABOUT							= "S3ABOUT";
   
   public static final String APP_ACTION_LOCK					= "APP_ACTION_LOCK";
   public static final String APP_ACTION_UNLOCK					= "APP_ACTION_UNLOCK";
   public static final String VIEW_REFRESH						= "REFRESH";
   public static final String URLVIEW							= "URLVIEW";
   public static final String SENDMAILVIEW						= "SENDMAILVIEW";
   public static final String DATEIOPEN							= "DATEIOPEN";
   
   public static final String HISTORY_HOME						= "HISTORY_HOME";
   public static final String HISTORY_NEXT						= "HISTORY_NEXT";
   public static final String HISTORY_PREV						= "HISTORY_PREV";
   public static final String HISTORY_INDEX						= "HISTORY_INDEX";
   
   // MGB components
   public static final String MGB_VIEW_FILE_CONTENT 			= "MGB_VIEW_FILE_CONTENT";
   public static final String MGB_REPORT 						= "MGB_REPORT";

   public static final String MGB_STATISTIC_BUSINESS_DAY		= "MGB_STATISTIC_BUSINESS_DAY";
   public static final String MGB_STATISTIC_BUSINESS_MONTH		= "MGB_STATISTIC_BUSINESS_MONTH";
   public static final String MGB_STATISTIC_JOB					= "MGB_STATISTIC_JOB";
   public static final String MGB_STATISTIC_TRADER				= "MGB_STATISTIC_TRADER";
   public static final String MGB_STATISTIC_TRADE_TYPE			= "MGB_STATISTIC_TRADE_TYPE";
   public static final String MGB_STATISTIC_TRADE_CATEGORY_REPO = "MGB_STATISTIC_TRADE_CATEGORY_REPO";
   public static final String MGB_STATISTIC_TRADE_CATEGORY_MM = "MGB_STATISTIC_TRADE_CATEGORY_MM";
   public static final String MGB_STATISTIC_TRADE_CATEGORY_EQUITY	= "MGB_STATISTIC_TRADE_CATEGORY_EQUITY";
   public static final String MGB_STATISTIC_TRADE_CATEGORY_BOND = "MGB_STATISTIC_TRADE_CATEGORY_BOND";
   public static final String MGB_STATISTIC_TRADE_CATEGORY_DERIVATIVE = "MGB_STATISTIC_TRADE_CATEGORY_DERIVATIVE";
   public static final String MGB_STATISTIC_TRADE_TYPE_EQUITY = "MGB_STATISTIC_TRADE_TYPE_EQUITY";
   public static final String MGB_STATISTIC_INSTRUMENT          = "MGB_STATISTIC_INSTRUMENT";
   public static final String MGB_STATISTIC_BUSINESS_UNIT		= "MGB_STATISTIC_BUSINESS_UNIT";
   public static final String MGB_STATISTIC_LIMIT_UNIT			= "MGB_STATISTIC_LIMIT_UNIT";
   public static final String MGB_STATISTIC_REPORT				= "MGB_STATISTIC_REPORT";
   public static final String MGB_STATISTIC_BOOK				= "MGB_STATISTIC_BOOK";
   public static final String MGB_STATISTIC_LOCATION			= "MGB_STATISTIC_LOCATION";
   
   public static final String MGB_CHECKSTATE 					= "MGB_CHECKSTATE";
   public static final String MGB_TRADE_LIST 					= "MGB_TRADE_LIST";
   public static final String MGB_TRADE_MULTIVIEW 				= "MGB_TRADE_TAB";
   public static final String MGB_TRADE_OVERVIEW 				= "MGB_TRADE_OVERVIEW";
   public static final String MGB_TRADE_RECLAMATION 			= "MGB_TRADE_RECLAMATION";
   public static final String MGB_TRADE_HISTORY 				= "MGB_TRADE_HISTORY";
   public static final String MGB_TRADE_PARAMETER 				= "MGB_TRADE_PARAMETER";
   public static final String MGB_TRADE_STORNO	 				= "MGB_TRADE_STORNO";
   public static final String MGB_NEW_TRADE_STATE 				= "MGB_NEW_TRADE_STATE";
   public static final String MGB_NEW_TRADE_RECLAMATION 		= "MGB_NEW_TRADE_RECLAMATION";
   public static final String MGB_CLOSE_RECLAMATION 			= "MGB_CLOSE_RECLAMATION";
   public static final String MGB_TRADER_INFO_NEW 				= "MGB_NEW_TRADER_INFO";
   public static final String MGB_SEARCH_NEW_EMPLOYEE 			= "MGB_SEARCH_NEW_EMPLOYEE";
   public static final String MGB_EDIT_EMPLOYEE 				= "MGB_EDIT_EMPLOYEE";
   public static final String MGB_OPEN_EMPLOYEE 				= "MGB_OPEN_EMPLOYEE";
   public static final String MGB_EMPLOYEE_LIST 				= "MGB_EMPLOYEE_LIST";
   public static final String MGB_AUTO_CHECK 					= "MGB_AUTO_CHECK";
   public static final String MGB_INSTRUMENT_INFO_NEW 			= "MGB_NEW_INSTRUMENTS_INFO";
   public static final String MGB_LOCATION_INFO_NEW 			= "MGB_NEW_LOCATION_INFO";
   public static final String MGB_INSTRUMENT_EDIT 				= "MGB_INSTRUMENT_EDIT";
   public static final String MGB_INSTRUMENT_LIST 				= "MGB_INSTRUMENT_LIST";   
   public static final String MGB_DUAL_CONTROL_INFO 			= "MGB_DUAL_CONTROL_INFO";
   public static final String MGB_DUAL_CONTROL_JOB_EDIT 		= "MGB_DUAL_CONTROL_JOB_EDIT";
   public static final String MGB_ADMIN_LOGIN 					= "MGB_ADMIN_LOGIN";
   public static final String MGB_EMPLOYEE_RECLAMATION_LIST 	= "MGB_EMPLOYEE_RECLAMATION_LIST";
   public static final String MGB_TRADE_RECLAMATION_LIST		= "MGB_TRADE_RECLAMATION_LIST";
   public static final String MGB_STATE_CODE_LIST 				= "MGB_STATE_CODE_LIST";
   public static final String MGB_STATE_CODE_EDIT				= "MGB_STATE_CODE_EDIT";
   public static final String MGB_EXCHANGE_EDIT 				= "MGB_EXCHANGE_EDIT";
   public static final String MGB_EXCHANGE_LIST 				= "MGB_EXCHANGE_LIST";
   public static final String MGB_EXCHANGE_VIEW 				= "MGB_EXCHANGE_VIEW";
   public static final String MGB_EXCHANGE_MAPPING_EDIT 		= "MGB_EXCHANGE_MAPPING_EDIT";
   public static final String MGB_ADDON_LIST			 		= "MGB_ADDON_LIST";
   public static final String MGB_ADDON_EDIT			 		= "MGB_ADDON_EDIT";
   public static final String MGB_PRICE_CHECK_CATEGORY_LIST   	= "MGB_PRICE_CHECK_CATEGORY_LIST";
   public static final String MGB_PRICE_CHECK_CATEGORY_EDIT		= "MGB_PRICE_CHECK_CATEGORY_EDIT";
   public static final String MGB_PRICE_DEVIATION_LIST			= "MGB_PRICE_DEVIATION_LIST";
   public static final String MGB_PRICE_DEVIATION_STATISTIC_LIST= "MGB_PRICE_DEVIATION_STATISTIC_LIST";
   public static final String MGB_CHANGE_MANDANT				= "MGB_CHANGE_MANDANT";
   public static final String MGB_JOBS_SELECTION_MENU			= "MGB_JOBS_EDIT_MENU";
   public static final String MGB_JOBS_SELECTION_DIALOG			= "MGB_JOBS_EDIT_DIALOG";
   public static final String MGB_JOB_LIST						= "MGB_JOB_LIST";
   public static final String MGB_JOB_NEW						= "MGB_JOB_NEW";
   public static final String MGB_APP_STATISTIC					= "MGB_APP_STATISTIC";
   public static final String MGB_CONFIGURATION_LIST			= "MGB_CONFIGURATION_LIST";
   public static final String MGB_CONFIGURATION_EDIT			= "MGB_CONFIGURATION_EDIT";
   public static final String MGB_REFRESH_SELECTION_LIST		= "MGB_REFRESH_SELECTION_LIST";
   public static final String MGB_OPEN_JAVA_HELP				= "MGB_OPEN_JAVA_HELP";
   public static final String MGB_NEW_MAIL						= "MGB_NEW_MAIL";
   public static final String MGB_TRADE_SEARCH_DIALOG			= "MGB_TRADE_SEARCH_DIALOG";
   public static final String MGB_DEBUG							= "MGB_DEBUG";
   public static final String MGB_SAMPLE_CHECK					= "MGB_SAMPLE_CHECK";
   public static final String MGB_LOG_VIEW                      = "MGB_LOG_VIEW";

   public static final String MGB_NOT_YET_IMPLEMENTED			= "MGB_NOT_YET_IMPLEMENTED";
   
      
}
