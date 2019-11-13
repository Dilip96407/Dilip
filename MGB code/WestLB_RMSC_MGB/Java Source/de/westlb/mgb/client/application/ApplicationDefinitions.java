package de.westlb.mgb.client.application;

import java.io.File;

import de.westlb_systems.xaf.application.ActionSet;
import de.westlb_systems.xaf.swing.SIcon;
import de.westlb_systems.xaf.ui.misc.IconKatalog;

public class ApplicationDefinitions {
	private static final String ICON_PATH			= "images/";
	public static final String LABEL_PATH			= "label/";

	public final static String PATH_MGB				= System.getProperty("user.home")+ File.separatorChar + "mgb" +  File.separatorChar;
	public final static String PATH_MGB_TEMP		= PATH_MGB +  File.separatorChar + "tmp" +  File.separatorChar;

	public static final String MENUBAR_RESSOURCE 	= LABEL_PATH + "S3MenuBarResource";
	public static final String FOLDERBAR_RESSOURCE	= "FolderBarResource";
	public static final String DIALOGVIEW_RESSOURCE	= LABEL_PATH + "DialogViewerResource";
	public static final String ERRORBOX_RESOURCE	= "ErrorBoxResource";
	public static final String SC_RESOURCE			= "SCResource";

	private static IconKatalog.IconUrl iconUrls[] =
		new IconKatalog.IconUrl[] {
			IconKatalog.createIconUrl("LOGIN_LOGO", ICON_PATH, "login_logo.gif"),
			IconKatalog.createIconUrl("MGB_LOGO", ICON_PATH, "mgb_logo.jpg"),
			IconKatalog.createIconUrl("MGB_HOMEPAGE", ICON_PATH, "mgb_homepage.jpg"),
			IconKatalog.createIconUrl("MGB_HOMEPAGE_REPO", ICON_PATH, "mgb_homepage_repo.jpg"),
			IconKatalog.createIconUrl("MGB_HOMEPAGE_EQUITY", ICON_PATH, "mgb_homepage_equity.jpg"),
			IconKatalog.createIconUrl("MGB_HOMEPAGE_MONEYMARKET", ICON_PATH, "mgb_homepage_moneymarket.jpg"),
			IconKatalog.createIconUrl("MGB_HOMEPAGE_BOND", ICON_PATH, "mgb_homepage_bond.jpg"),
			IconKatalog.createIconUrl("MGB_HOMEPAGE_DERIVATIVE", ICON_PATH, "mgb_homepage_derivative.jpg"),
			IconKatalog.createIconUrl("BACK", ICON_PATH, "back_16.gif"),
			IconKatalog.createIconUrl("FORWARD", ICON_PATH, "forward_16.gif"),
			IconKatalog.createIconUrl("PREV_LIST_ITEM", ICON_PATH, "prev_list_item_16.gif"),
			IconKatalog.createIconUrl("NEXT_LIST_ITEM", ICON_PATH, "next_list_item_16.gif"),
			IconKatalog.createIconUrl("HOME", ICON_PATH, "home_16.gif"),
			IconKatalog.createIconUrl("HARDCOPY", ICON_PATH, "hardcopy_16.gif"),
			IconKatalog.createIconUrl("FOLDERBAR_ITEM", ICON_PATH, "folderbar_item.gif"),
			IconKatalog.createIconUrl("FOLDERBAR_ITEM_AKTIV", ICON_PATH, "folderbar_item_aktiv.gif"),
			IconKatalog.createIconUrl("WARNING", ICON_PATH, "warning_16.gif"),
			IconKatalog.createIconUrl("QUESTION", ICON_PATH, "question_16.gif"),
			IconKatalog.createIconUrl("EXCEL_DOK", ICON_PATH, "excel_dok_16.gif"),
			IconKatalog.createIconUrl("ATTACHMENT", ICON_PATH, "crm_attachment_16.gif"),
			IconKatalog.createIconUrl("ATTACHMENT_DOC", ICON_PATH, "attachment_doc.gif"),
			IconKatalog.createIconUrl("ATTACHMENT_XLS", ICON_PATH, "attachment_xls.gif"),
			IconKatalog.createIconUrl("ATTACHMENT_ZIP", ICON_PATH, "attachment_zip.gif"),
			IconKatalog.createIconUrl("ATTACHMENT_PPT", ICON_PATH, "attachment_ppt.gif"),
			IconKatalog.createIconUrl("ATTACHMENT_GIF", ICON_PATH, "attachment_jgp.gif"),
			IconKatalog.createIconUrl("ATTACHMENT_JPG", ICON_PATH, "attachment_jpg.gif"),
			IconKatalog.createIconUrl("ATTACHMENT_BMP", ICON_PATH, "attachment_jpg.gif"),
			IconKatalog.createIconUrl("ATTACHMENT_NULL", ICON_PATH, "attachment_null.gif"),
			IconKatalog.createIconUrl("EMPLOYEE_CONTROLLER", ICON_PATH, "mgb_controller_16.gif"),
			IconKatalog.createIconUrl("EMPLOYEE_TRADER", ICON_PATH, "mgb_trader_16.gif"),			
			IconKatalog.createIconUrl("OPEN", ICON_PATH, "oeffnen_16.gif"),
			IconKatalog.createIconUrl("SAVE", ICON_PATH, "speichern_16.gif"),
			IconKatalog.createIconUrl("PRINT", ICON_PATH, "drucken_16.gif"),
			IconKatalog.createIconUrl("RELOAD", ICON_PATH, "refresh_16.gif"),
			IconKatalog.createIconUrl("EDIT", ICON_PATH, "edit_16.gif"),
			IconKatalog.createIconUrl("FOLDERBAR_FOLDER", ICON_PATH, "folderbar_folder.gif"),
			IconKatalog.createIconUrl("FOLDERBAR_FOLDER_AKTIV", ICON_PATH, "folderbar_folder_aktiv.gif"),
			IconKatalog.createIconUrl("STATE_MANUAL", ICON_PATH, "state_manual_16.gif"),
			IconKatalog.createIconUrl("STATE_SAMPLE", ICON_PATH, "state_sample_16.gif"),
			IconKatalog.createIconUrl("STATE_AUTOMATIC", ICON_PATH, "state_automatic_16.gif"),
			IconKatalog.createIconUrl("STATE_RECLAMATION", ICON_PATH, "state_reclamation_16.gif"),
			IconKatalog.createIconUrl("EMAIL", ICON_PATH, "email_16.gif"),
			IconKatalog.createIconUrl("EMAIL_GREEN", ICON_PATH, "email_green_16.gif"),
			IconKatalog.createIconUrl("EMAIL_RED", ICON_PATH, "email_red_16.gif"),
			IconKatalog.createIconUrl("COMPLETE", ICON_PATH, "complete_16.gif"),
			
	};


	/** DOCUMENT ME! */
	private static final String PUSH = ActionSet.PUSH;
	private static final String TOGG = ActionSet.TOGGLE;
	private static final String MENU = ActionSet.MENU;
	private static final String SUBM = ActionSet.SUBM;

	/** DOCUMENT ME! */
	private static final boolean ENABLED = ActionSet.ENABLED;
	private static final boolean DISABLED = ActionSet.DISABLED;
	private static final boolean UNSELECTED = ActionSet.UNSELECTED;

	/** DOCUMENT ME! */
	private static final IconKatalog iconKatalog = IconKatalog.getInstance(iconUrls);

	/** DOCUMENT ME! */
	public static final SIcon icon_print = iconKatalog.getIcon("PRINT");
	public static final SIcon icon_printscreen = iconKatalog.getIcon("HARDCOPY");
	public static final SIcon icon_export = iconKatalog.getIcon("EXCEL_DOK");
	public static final SIcon icon_prev = iconKatalog.getIcon("BACK");
	public static final SIcon icon_next = iconKatalog.getIcon("FORWARD");
	public static final SIcon icon_next_list_item = iconKatalog.getIcon("NEXT_LIST_ITEM");
	public static final SIcon icon_prev_list_item = iconKatalog.getIcon("PREV_LIST_ITEM");
	public static final SIcon icon_home = iconKatalog.getIcon("HOME");
	public static final SIcon icon_refresh = iconKatalog.getIcon("RELOAD");
	public static final SIcon icon_save = iconKatalog.getIcon("SAVE");
	public static final SIcon icon_open = iconKatalog.getIcon("OPEN");

	/** DOCUMENT ME! */
	public static final ActionSet file					= ActionSet.defaultAction(ActionCommands.FILE, MENU, ENABLED);
	public static final ActionSet file_save				= ActionSet.defaultAction(ActionCommands.FILE_SAVE, PUSH, icon_save, DISABLED);
	public static final ActionSet file_open				= ActionSet.defaultAction(ActionCommands.FILE_OPEN, PUSH, icon_open, DISABLED);
	public static final ActionSet file_print			= ActionSet.defaultAction(ActionCommands.FILE_PRINT, PUSH, icon_print, DISABLED);
	public static final ActionSet file_screen			= ActionSet.defaultAction(ActionCommands.FILE_PRINT_SCREEN, PUSH, icon_printscreen, ENABLED);
	public static final ActionSet file_export			= ActionSet.defaultAction(ActionCommands.FILE_EXPORT, PUSH, icon_export, DISABLED);
	public static final ActionSet file_exit				= ActionSet.defaultAction(ActionCommands.FILE_EXIT, PUSH, ENABLED);

	/** DOCUMENT ME! */
	public static final ActionSet edit					= ActionSet.defaultAction("EDIT", MENU, ENABLED);
	public static final ActionSet edit_cut				= ActionSet.defaultAction("EDIT_CUT", PUSH, DISABLED);
	public static final ActionSet edit_copy				= ActionSet.defaultAction("EDIT_COPY", PUSH, ENABLED);
	public static final ActionSet edit_paste			= ActionSet.defaultAction("EDIT_PASTE", PUSH, DISABLED);
	public static final ActionSet edit_edit				= ActionSet.defaultAction("EDIT_EDIT", PUSH, DISABLED);
	public static final ActionSet edit_delete			= ActionSet.defaultAction("EDIT_DELETE", PUSH, DISABLED);
	public static final ActionSet edit_new				= ActionSet.defaultAction(ActionCommands.EDIT_NEW, SUBM, ENABLED);
	public static final ActionSet edit_new_manual_state	= ActionSet.defaultAction(ActionCommands.EDIT_NEW_MANUAL_STATE, PUSH, DISABLED);
	public static final ActionSet edit_new_employee		= ActionSet.defaultAction(ActionCommands.EDIT_NEW_EMPLOYEE, PUSH, ENABLED);
	public static final ActionSet edit_new_exchange		= ActionSet.defaultAction(ActionCommands.EDIT_NEW_EXCHANGE, PUSH, ENABLED);
	public static final ActionSet edit_new_addon		= ActionSet.defaultAction(ActionCommands.EDIT_NEW_ADDON, PUSH, ENABLED);	
	public static final ActionSet edit_new_recl_state	= ActionSet.defaultAction(ActionCommands.EDIT_NEW_RECLAMATION_STATE, PUSH, DISABLED);

	/** DOCUMENT ME! */
	public static final ActionSet view					= ActionSet.defaultAction(ActionCommands.VIEW, MENU, ENABLED);
	public static final ActionSet view_refresh			= ActionSet.defaultAction(ActionCommands.VIEW_REFRESH, PUSH, icon_refresh, ENABLED);
	public static final ActionSet view_toolbar			= ActionSet.defaultAction(ActionCommands.VIEW_TOOLBAR, TOGG, ENABLED, UNSELECTED);
	public static final ActionSet view_largeIcons 		= ActionSet.defaultAction(ActionCommands.VIEW_LARGEICONS, TOGG, ENABLED, UNSELECTED);
	public static final ActionSet view_s3bar			= ActionSet.defaultAction(ActionCommands.VIEW_S3BAR, TOGG, ENABLED, UNSELECTED);
	public static final ActionSet view_status			= ActionSet.defaultAction(ActionCommands.VIEW_STATUSBAR, TOGG, ENABLED, UNSELECTED);

	/** DOCUMENT ME! */
	public static final ActionSet go 					= ActionSet.defaultAction(ActionCommands.GO, MENU, ENABLED);
	public static final ActionSet go_prev 				= ActionSet.defaultAction(ActionCommands.GO_PREV, PUSH, icon_prev, ENABLED);
	public static final ActionSet go_next 				= ActionSet.defaultAction(ActionCommands.GO_NEXT, PUSH, icon_next, ENABLED);
	public static final ActionSet go_next_litem			= ActionSet.defaultAction(ActionCommands.GO_NEXT_LITEM, PUSH,  icon_next_list_item, DISABLED);
	public static final ActionSet go_prev_litem 		= ActionSet.defaultAction(ActionCommands.GO_PREV_LITEM, PUSH,  icon_prev_list_item, DISABLED);

	public static final ActionSet go_home 					= ActionSet.defaultAction(ActionCommands.GO_HOME, PUSH, icon_home, ENABLED);

	public static final ActionSet go_analyse				= ActionSet.defaultAction(ActionCommands.GO_ANALYSE, SUBM, ENABLED);
	public static final ActionSet go_analyse_blb_auto		= ActionSet.defaultAction(ActionCommands.GO_ANALYSE_BLB_AUTOMATIC, PUSH, ENABLED);
	public static final ActionSet go_analyse_euwax_auto		= ActionSet.defaultAction(ActionCommands.GO_ANALYSE_EUWAX_AUTOMATIC, PUSH, ENABLED);
	public static final ActionSet go_analyse_combi_auto		= ActionSet.defaultAction(ActionCommands.GO_ANALYSE_COMBI_AUTOMATIC, PUSH, ENABLED);
	public static final ActionSet go_analyse_consistancy	= ActionSet.defaultAction(ActionCommands.GO_ANALYSE_CONSISTANCY, PUSH, ENABLED);
	public static final ActionSet go_analyse_sample			= ActionSet.defaultAction(ActionCommands.GO_ANALYSE_SAMPLE, PUSH, ENABLED);
	public static final ActionSet go_analyse_manual			= ActionSet.defaultAction(ActionCommands.GO_ANALYSE_MANUAL, PUSH, ENABLED);
	public static final ActionSet go_analyse_trade_reclamation = ActionSet.defaultAction(ActionCommands.GO_ANALYSE_TRADE_RECLAMATION, PUSH, ENABLED);
	
	public static final ActionSet go_analyse_trades_oprecl 	= ActionSet.defaultAction(ActionCommands.GO_ANALYSE_OPRECL, PUSH, ENABLED);
	public static final ActionSet go_analyse_jobs		 	= ActionSet.defaultAction(ActionCommands.GO_ANALYSE_JOBS, PUSH, ENABLED);

	/** DOCUMENT ME! */
	public static final ActionSet go_report 				= ActionSet.defaultAction(ActionCommands.GO_STATISTIC, SUBM, ENABLED);
	public static final ActionSet go_statistic_business_day	= ActionSet.defaultAction(ActionCommands.GO_STATISTIC_BUSINESS_DAY, PUSH, ENABLED);
	public static final ActionSet go_statistic_business_month = ActionSet.defaultAction(ActionCommands.GO_STATISTIC_BUSINESS_MONTH, PUSH, ENABLED);
	public static final ActionSet go_statistic_limit_unit	= ActionSet.defaultAction(ActionCommands.GO_STATISTIC_LIMIT_UNIT, PUSH, ENABLED);
	public static final ActionSet go_statistic_business_unit	= ActionSet.defaultAction(ActionCommands.GO_STATISTIC_BUSINESS_UNIT, PUSH, ENABLED);
	public static final ActionSet go_statistic_location		= ActionSet.defaultAction(ActionCommands.GO_STATISTIC_LOCATION, PUSH, ENABLED);
	public static final ActionSet go_statistic_trade_type 	= ActionSet.defaultAction(ActionCommands.GO_STATISTIC_TRADE_TYPE, PUSH, ENABLED);
    public static final ActionSet go_statistic_trade_category_bond  = ActionSet.defaultAction(ActionCommands.GO_STATISTIC_TRADE_CATEGORY_BOND, PUSH, ENABLED);
    public static final ActionSet go_statistic_trade_category_derivative  = ActionSet.defaultAction(ActionCommands.GO_STATISTIC_TRADE_CATEGORY_DERIVATIVE, PUSH, ENABLED);
	public static final ActionSet go_statistic_trade_category_equity 	= ActionSet.defaultAction(ActionCommands.GO_STATISTIC_TRADE_CATEGORY_EQUITY, PUSH, ENABLED);
    public static final ActionSet go_statistic_trade_category_repo  = ActionSet.defaultAction(ActionCommands.GO_STATISTIC_TRADE_CATEGORY_REPO, PUSH, ENABLED);
    public static final ActionSet go_statistic_trade_category_mm  = ActionSet.defaultAction(ActionCommands.GO_STATISTIC_TRADE_CATEGORY_MM, PUSH, ENABLED);
    public static final ActionSet go_statistic_trade_type_equity    = ActionSet.defaultAction(ActionCommands.GO_STATISTIC_TRADE_TYPE_EQUITY, PUSH, ENABLED);
    public static final ActionSet go_statistic_instrument   = ActionSet.defaultAction(ActionCommands.GO_STATISTIC_INSTRUMENT, PUSH, ENABLED);
	public static final ActionSet go_statistic_trader		= ActionSet.defaultAction(ActionCommands.GO_STATISTIC_TRADER, PUSH, ENABLED);
	public static final ActionSet go_statistic_job			= ActionSet.defaultAction(ActionCommands.GO_STATISTIC_JOB, PUSH, ENABLED);
	public static final ActionSet go_statistic_book			= ActionSet.defaultAction(ActionCommands.GO_STATISTIC_BOOK, PUSH, ENABLED);
	public static final ActionSet go_analyse_trader_reclamation = ActionSet.defaultAction(ActionCommands.GO_STATISTIC_TRADER_RECLAMATION, PUSH, ENABLED);

	public static final ActionSet go_admin					= ActionSet.defaultAction(ActionCommands.GO_ADMIN,				SUBM, ENABLED);
	public static final ActionSet go_admin_employee			= ActionSet.defaultAction(ActionCommands.GO_ADMIN_EMPLOYEE,		PUSH, ENABLED);
	public static final ActionSet go_admin_instrument 		= ActionSet.defaultAction(ActionCommands.GO_ADMIN_INSTRUMENT,	PUSH, ENABLED);
	public static final ActionSet go_admin_addon			= ActionSet.defaultAction(ActionCommands.GO_ADMIN_ADDON,		PUSH, ENABLED);
	public static final ActionSet go_admin_exchange			= ActionSet.defaultAction(ActionCommands.GO_ADMIN_EXCHANGE,		PUSH, ENABLED);
	public static final ActionSet go_admin_price_check_category	= ActionSet.defaultAction(ActionCommands.GO_ADMIN_PRICE_CHECK_CATEGORIE,	PUSH, ENABLED);
	public static final ActionSet go_admin_state_codes		= ActionSet.defaultAction(ActionCommands.GO_ADMIN_STATE_CODES,	PUSH, ENABLED);
	public static final ActionSet go_admin_mgb_configuration= ActionSet.defaultAction(ActionCommands.GO_ADMIN_MGB_CONFIGURATION,	PUSH, ENABLED);

	/** DOCUMENT ME! */
	public static final ActionSet extra 					= ActionSet.defaultAction(ActionCommands.EXTRA,						MENU, ENABLED);
	public static final ActionSet extra_admin_login			= ActionSet.defaultAction(ActionCommands.EXTRA_ADMIN_LOGIN,			PUSH, ENABLED);
	public static final ActionSet extra_new_instruments		= ActionSet.defaultAction(ActionCommands.EXTRA_NEW_INSTRUMENTS,		PUSH, ENABLED);
	public static final ActionSet extra_new_locations		= ActionSet.defaultAction(ActionCommands.EXTRA_NEW_LOCATIONS,		PUSH, ENABLED);
	public static final ActionSet extra_new_traders			= ActionSet.defaultAction(ActionCommands.EXTRA_NEW_TRADERS,			PUSH, ENABLED);
	public static final ActionSet extra_search_trades		= ActionSet.defaultAction(ActionCommands.EXTRA_SEARCH_TRADES,		PUSH, ENABLED);
	public static final ActionSet extra_dual_control_jobs	= ActionSet.defaultAction(ActionCommands.EXTRA_DUAL_CONTROL_JOBS,	PUSH, ENABLED);
	public static final ActionSet extra_app_statistic		= ActionSet.defaultAction(ActionCommands.EXTRA_APP_STATISTIC,		PUSH, ENABLED);
	public static final ActionSet extra_refresh_selection_lists = ActionSet.defaultAction(ActionCommands.EXTRA_REFRESH_SELECTION_LISTS, PUSH, ENABLED);
	public static final ActionSet extra_debug				= ActionSet.defaultAction(ActionCommands.EXTRA_DEBUG, TOGG, ENABLED, UNSELECTED);
	
	/** DOCUMENT ME! */
	public static final ActionSet help						= ActionSet.defaultAction(ActionCommands.HELP, MENU, ENABLED);
	public static final ActionSet help_help					= ActionSet.defaultAction(ActionCommands.HELP_HELP, PUSH, ENABLED);
	public static final ActionSet help_problem_db			= ActionSet.defaultAction(ActionCommands.HELP_PROBLEM_DB, PUSH, ENABLED);
	public static final ActionSet help_about				= ActionSet.defaultAction(ActionCommands.HELP_ABOUT, PUSH, ENABLED);

	/** DOCUMENT ME! */
	public static final ActionSet separator = ActionSet.defaultAction(null, ActionSet.SEPARATOR, ENABLED);

	/** DOCUMENT ME! */
	public static final ActionSet popSubMenu = ActionSet.defaultAction(null, ActionSet.POPM, ENABLED);

	/** DOCUMENT ME! */
	public static final ActionSet space = ActionSet.defaultAction(null, ActionSet.SPACE, ENABLED);

	/**
	 * Creates a new ActionHandler object.
	 */
	public ApplicationDefinitions() {

	}

	public ActionSet[] getActionSets() {
		ActionSet[] defaultActionsArray = { 
			file, 
				file_open, 
				file_save, 
				file_print, 
				file_screen, 
				file_export, 
				file_exit, 
			edit, 
				edit_cut, 
				edit_copy, 
				edit_paste, 
				edit_edit, 
				edit_delete,
				edit_new, 
				edit_new_addon,
				edit_new_employee,							
				edit_new_exchange,
				edit_new_manual_state,
				edit_new_recl_state,
			view, 
				view_refresh, 
				view_toolbar, 
				view_largeIcons, 
				view_s3bar, 
				view_status,
			extra, 
				extra_admin_login, 
				extra_app_statistic, 
				extra_new_instruments,
				extra_new_locations,
				extra_new_traders, 
				extra_search_trades, 
				extra_dual_control_jobs,
				extra_refresh_selection_lists,
				extra_debug,
			go, 
				go_prev, 
				go_next, 
				go_prev_litem,
				go_next_litem,
				go_home,  
			help, 
				help_help, 
				help_problem_db,
				help_about
			};

		return defaultActionsArray;
	}

}
