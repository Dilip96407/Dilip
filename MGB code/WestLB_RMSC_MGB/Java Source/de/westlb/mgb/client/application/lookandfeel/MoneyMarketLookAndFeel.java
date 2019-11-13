package de.westlb.mgb.client.application.lookandfeel;

import javax.swing.UIDefaults;

import de.westlb.mgb.client.application.ApplicationDefinitions;
import de.westlb_systems.xaf.application.ActionSet;
import de.westlb_systems.xaf.application.FolderBar;
import de.westlb_systems.xaf.application.MenuBar;
import de.westlb_systems.xaf.application.S3Bar;
import de.westlb_systems.xaf.ui.misc.IconKatalog;

/**
 * @author wsy4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MoneyMarketLookAndFeel extends LookAndFeel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4250111771578532275L;

	private IconKatalog iconKatalog = IconKatalog.getInstance();
	
    /**
     * @see javax.swing.plaf.basic.BasicLookAndFeel#initComponentDefaults(UIDefaults)
     */
    @Override
    protected void initComponentDefaults(UIDefaults table) {
        super.initComponentDefaults(table);
		
		table.put("MandantName", "MoneyMarket");
        table.put("MenuBar", getMenuBar());
        table.put("S3Bar", getS3Bar());        
		table.put("HompageImage", iconKatalog.getImage("MGB_HOMEPAGE_MONEYMARKET"));
        table.put("TradeOverviewView", "de.westlb.mgb.client.mask.view.mmk.MmkTradeOverviewView"); 
        table.put("DoubleTradeOverviewView", "de.westlb.mgb.client.mask.view.mmk.MmkDoubleTradeOverviewView"); 
		table.put("TradeParameterView", "de.westlb.mgb.client.mask.view.mmk.MmkTradeParameterView");
		table.put("PriceCheckCategoryEditDialog", "de.westlb.mgb.client.mask.view.mmk.MmkPriceCheckCategoryEditDialog"); 
		table.put("PriceCheckCategoryEditModel", "de.westlb.mgb.client.mask.model.mmk.MmkPriceCheckCategoryEditModel");
		table.put("PriceCheckCategoryTableModel.resourceName", "label/MmkPriceCheckCategoryTableModel");
		table.put("PriceCheckCategoryListView.isImportEnabled", Boolean.TRUE);
		table.put("TradeTableModel.resourceName", "label/TradeTableModel");
		table.put("TradeTableModelLongFormat.resourceName", "label/TradeTableModel");
		table.put("MgbConfigurationTableModel.resourceName", "label/MgbConfigurationTableModel");
    }
    
    private S3Bar getS3Bar() {
        return new S3Bar("FolderBarResource", "", new Object[][] {
            { ApplicationDefinitions.go_analyse, FolderBar.FOLDERMENU }, 
//				{ ApplicationDefinitions.go_analyse_auto, FolderBar.FOLDERITEM }, 
	            { ApplicationDefinitions.go_analyse_manual, FolderBar.FOLDERITEM }, 
			{ ApplicationDefinitions.go_analyse_trade_reclamation, FolderBar.FOLDERITEM }, 
	            { ApplicationDefinitions.go_analyse_trades_oprecl, FolderBar.FOLDERITEM },
				{ ApplicationDefinitions.go_analyse_trader_reclamation, FolderBar.FOLDERITEM },
				{ ApplicationDefinitions.go_analyse_jobs, FolderBar.FOLDERITEM }, 
            { ApplicationDefinitions.go_report, FolderBar.FOLDERMENU }, 
				{ ApplicationDefinitions.go_statistic_business_month, FolderBar.FOLDERITEM }, 
	            { ApplicationDefinitions.go_statistic_business_day, FolderBar.FOLDERITEM }, 
				{ ApplicationDefinitions.go_statistic_job, FolderBar.FOLDERITEM }, 
				{ ApplicationDefinitions.go_statistic_trade_category_mm, FolderBar.FOLDERITEM }, 
				{ ApplicationDefinitions.go_statistic_location, FolderBar.FOLDERITEM }, 
				{ ApplicationDefinitions.go_statistic_business_unit, FolderBar.FOLDERITEM }, 
				{ ApplicationDefinitions.go_statistic_limit_unit, FolderBar.FOLDERITEM }, 
				{ ApplicationDefinitions.go_statistic_book, FolderBar.FOLDERITEM }, 
				{ ApplicationDefinitions.go_statistic_trader, FolderBar.FOLDERITEM }, 
            { ApplicationDefinitions.go_admin, FolderBar.FOLDERMENU },             
	            { ApplicationDefinitions.go_admin_employee, FolderBar.FOLDERITEM },             
	            { ApplicationDefinitions.go_admin_state_codes, FolderBar.FOLDERITEM },             
	            { ApplicationDefinitions.go_admin_price_check_category, FolderBar.FOLDERITEM },             
 	            { ApplicationDefinitions.go_admin_mgb_configuration, FolderBar.FOLDERITEM }, 
           { null, FolderBar.FOLDERSEPARATOR }, 
        });
    }
    
    private MenuBar getMenuBar() {
        return new MenuBar(
                          new ActionSet[] {
            ApplicationDefinitions.file, 
            	ApplicationDefinitions.file_print, 
            	ApplicationDefinitions.file_export, 
            	ApplicationDefinitions.separator, 
            	ApplicationDefinitions.file_open, 
            	ApplicationDefinitions.file_save, 
            	ApplicationDefinitions.separator, 
            	ApplicationDefinitions.file_exit, 
            ApplicationDefinitions.edit, 
				ApplicationDefinitions.edit_cut,
				ApplicationDefinitions.edit_copy,
				ApplicationDefinitions.edit_paste,
				ApplicationDefinitions.separator,      
            	ApplicationDefinitions.edit_edit, 
            	ApplicationDefinitions.edit_delete, 
            	ApplicationDefinitions.edit_new, 
            	ApplicationDefinitions.edit_new_employee,
            	ApplicationDefinitions.edit_new_manual_state,
            	ApplicationDefinitions.edit_new_recl_state,
            ApplicationDefinitions.view,
            	ApplicationDefinitions.view_refresh,
            	ApplicationDefinitions.view_toolbar,
            	ApplicationDefinitions.view_s3bar,
            	ApplicationDefinitions.view_status,
            ApplicationDefinitions.extra, 
            	ApplicationDefinitions.extra_new_instruments,
            	ApplicationDefinitions.extra_new_locations,
            	ApplicationDefinitions.extra_new_traders, 
            	ApplicationDefinitions.extra_dual_control_jobs,
            	ApplicationDefinitions.separator, 
				ApplicationDefinitions.extra_search_trades,
				ApplicationDefinitions.separator, 
				ApplicationDefinitions.extra_admin_login, 
				ApplicationDefinitions.extra_app_statistic,
				ApplicationDefinitions.extra_refresh_selection_lists, 
				ApplicationDefinitions.extra_debug, 
            ApplicationDefinitions.go, 
            	ApplicationDefinitions.go_next, 
            	ApplicationDefinitions.go_prev, 
            	ApplicationDefinitions.go_next_litem, 
            	ApplicationDefinitions.go_prev_litem, 
            ApplicationDefinitions.help, 
            	ApplicationDefinitions.help_help, 
            	ApplicationDefinitions.help_problem_db,             	
            	ApplicationDefinitions.separator, 
            	ApplicationDefinitions.help_about, 
        });
    	
    }

}
