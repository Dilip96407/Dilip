package de.westlb.mgb.client.application;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Enumeration;

import javax.swing.UIManager;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.mask.ctrl.AddonListCtrl;
import de.westlb.mgb.client.mask.ctrl.CheckStateCtrl;
import de.westlb.mgb.client.mask.ctrl.DataSelectionCtrl;
import de.westlb.mgb.client.mask.ctrl.DualControlJobInfoCtrl;
import de.westlb.mgb.client.mask.ctrl.EmployeeListCtrl;
import de.westlb.mgb.client.mask.ctrl.EmployeeOverviewCtrl;
import de.westlb.mgb.client.mask.ctrl.ExchangeCtrl;
import de.westlb.mgb.client.mask.ctrl.ExchangeListCtrl;
import de.westlb.mgb.client.mask.ctrl.InstrumentListCtrl;
import de.westlb.mgb.client.mask.ctrl.JobListCtrl;
import de.westlb.mgb.client.mask.ctrl.MgbConfigurationCtrl;
import de.westlb.mgb.client.mask.ctrl.NewInstrumentInfoCtrl;
import de.westlb.mgb.client.mask.ctrl.NewLocationInfoCtrl;
import de.westlb.mgb.client.mask.ctrl.NewTraderInfoCtrl;
import de.westlb.mgb.client.mask.ctrl.PriceCheckCategoryListCtrl;
import de.westlb.mgb.client.mask.ctrl.ReclamationEmployeeListCtrl;
import de.westlb.mgb.client.mask.ctrl.StateCodeListCtrl;
import de.westlb.mgb.client.mask.ctrl.StatisticDataSelectionCtrl;
import de.westlb.mgb.client.mask.ctrl.StatisticListCtrl;
import de.westlb.mgb.client.mask.ctrl.StornoGroupCtrl;
import de.westlb.mgb.client.mask.ctrl.TradeHistoryCtrl;
import de.westlb.mgb.client.mask.ctrl.TradeListCtrl;
import de.westlb.mgb.client.mask.ctrl.TradeMultiCtrl;
import de.westlb.mgb.client.mask.ctrl.TradeOverviewCtrl;
import de.westlb.mgb.client.mask.ctrl.TradeReclamationCtrl;
import de.westlb.mgb.client.mask.ctrl.TradeReclamationListCtrl;
import de.westlb.mgb.client.mask.ctrl.TradeSearchCtrl;
import de.westlb.mgb.client.mask.model.shared.AbstractModel;
import de.westlb.mgb.client.mask.model.shared.AddonEditModel;
import de.westlb.mgb.client.mask.model.shared.AddonListModel;
import de.westlb.mgb.client.mask.model.shared.AppStatisticModel;
import de.westlb.mgb.client.mask.model.shared.CheckNewEmployeeModel;
import de.westlb.mgb.client.mask.model.shared.CheckStateModel;
import de.westlb.mgb.client.mask.model.shared.CloseReclamationModel;
import de.westlb.mgb.client.mask.model.shared.DataSelectionModel;
import de.westlb.mgb.client.mask.model.shared.DualControlJobEditModel;
import de.westlb.mgb.client.mask.model.shared.DualControlJobInfoModel;
import de.westlb.mgb.client.mask.model.shared.EmployeeEditModel;
import de.westlb.mgb.client.mask.model.shared.ExchangeEditModel;
import de.westlb.mgb.client.mask.model.shared.ExchangeListModel;
import de.westlb.mgb.client.mask.model.shared.ExchangeMappingEditModel;
import de.westlb.mgb.client.mask.model.shared.InstrumentEditModel;
import de.westlb.mgb.client.mask.model.shared.InstrumentListModel;
import de.westlb.mgb.client.mask.model.shared.JobListModel;
import de.westlb.mgb.client.mask.model.shared.LogViewModel;
import de.westlb.mgb.client.mask.model.shared.LoginModel;
import de.westlb.mgb.client.mask.model.shared.MgbConfigurationEditModel;
import de.westlb.mgb.client.mask.model.shared.MgbConfigurationListModel;
import de.westlb.mgb.client.mask.model.shared.NewInstrumentInfoModel;
import de.westlb.mgb.client.mask.model.shared.NewJobModel;
import de.westlb.mgb.client.mask.model.shared.NewLocationInfoModel;
import de.westlb.mgb.client.mask.model.shared.NewMailModel;
import de.westlb.mgb.client.mask.model.shared.NewTradeReclamationModel;
import de.westlb.mgb.client.mask.model.shared.NewTradeStateModel;
import de.westlb.mgb.client.mask.model.shared.NewTraderInfoModel;
import de.westlb.mgb.client.mask.model.shared.PriceCheckCategoryListModel;
import de.westlb.mgb.client.mask.model.shared.PriceDeviationListModel;
import de.westlb.mgb.client.mask.model.shared.PriceDeviationStatisticListModel;
import de.westlb.mgb.client.mask.model.shared.ReclamationEmployeeListModel;
import de.westlb.mgb.client.mask.model.shared.StateCodeEditModel;
import de.westlb.mgb.client.mask.model.shared.StateCodeListModel;
import de.westlb.mgb.client.mask.model.shared.StatisticDataSelectionModel;
import de.westlb.mgb.client.mask.model.shared.StatisticListModel;
import de.westlb.mgb.client.mask.model.shared.StornoGroupModel;
import de.westlb.mgb.client.mask.model.shared.TradeHistoryModel;
import de.westlb.mgb.client.mask.model.shared.TradeListModel;
import de.westlb.mgb.client.mask.model.shared.TradeMultiModel;
import de.westlb.mgb.client.mask.model.shared.TradeOverviewModel;
import de.westlb.mgb.client.mask.model.shared.TradeParameterModel;
import de.westlb.mgb.client.mask.model.shared.TradeReclamationListModel;
import de.westlb.mgb.client.mask.model.shared.TradeReclamationModel;
import de.westlb.mgb.client.mask.model.shared.TradeSearchModel;
import de.westlb.mgb.client.mask.view.shared.AddonEditDialog;
import de.westlb.mgb.client.mask.view.shared.AddonListView;
import de.westlb.mgb.client.mask.view.shared.AppStatisticDialog;
import de.westlb.mgb.client.mask.view.shared.CheckNewEmployeeDialog;
import de.westlb.mgb.client.mask.view.shared.CheckStateView;
import de.westlb.mgb.client.mask.view.shared.CloseReclamationDialog;
import de.westlb.mgb.client.mask.view.shared.DataSelectionView;
import de.westlb.mgb.client.mask.view.shared.DualControlJobEditDialog;
import de.westlb.mgb.client.mask.view.shared.DualControlJobInfoDialog;
import de.westlb.mgb.client.mask.view.shared.EmployeeEditDialog;
import de.westlb.mgb.client.mask.view.shared.EmployeeListView;
import de.westlb.mgb.client.mask.view.shared.EmployeeOverviewView;
import de.westlb.mgb.client.mask.view.shared.ExchangeEditDialog;
import de.westlb.mgb.client.mask.view.shared.ExchangeListView;
import de.westlb.mgb.client.mask.view.shared.ExchangeMappingEditDialog;
import de.westlb.mgb.client.mask.view.shared.ExchangeView;
import de.westlb.mgb.client.mask.view.shared.FileHandler;
import de.westlb.mgb.client.mask.view.shared.InstrumentEditDialog;
import de.westlb.mgb.client.mask.view.shared.InstrumentListView;
import de.westlb.mgb.client.mask.view.shared.JobListView;
import de.westlb.mgb.client.mask.view.shared.JobSelectionDialog;
import de.westlb.mgb.client.mask.view.shared.LogViewDialog;
import de.westlb.mgb.client.mask.view.shared.LoginDialog;
import de.westlb.mgb.client.mask.view.shared.MgbConfigurationEditDialog;
import de.westlb.mgb.client.mask.view.shared.MgbConfigurationListView;
import de.westlb.mgb.client.mask.view.shared.NewInstrumentInfoDialog;
import de.westlb.mgb.client.mask.view.shared.NewJobDialog;
import de.westlb.mgb.client.mask.view.shared.NewLocationInfoDialog;
import de.westlb.mgb.client.mask.view.shared.NewMailDialog;
import de.westlb.mgb.client.mask.view.shared.NewTradeReclamationDialog;
import de.westlb.mgb.client.mask.view.shared.NewTradeStateDialog;
import de.westlb.mgb.client.mask.view.shared.NewTraderInfoDialog;
import de.westlb.mgb.client.mask.view.shared.PriceCheckCategoryListView;
import de.westlb.mgb.client.mask.view.shared.PriceDeviationListView;
import de.westlb.mgb.client.mask.view.shared.PriceDeviationStatisticListView;
import de.westlb.mgb.client.mask.view.shared.ReclamationEmployeeListView;
import de.westlb.mgb.client.mask.view.shared.StartView;
import de.westlb.mgb.client.mask.view.shared.StateCodeEditDialog;
import de.westlb.mgb.client.mask.view.shared.StateCodeListView;
import de.westlb.mgb.client.mask.view.shared.StatisticDataSelectionDialog;
import de.westlb.mgb.client.mask.view.shared.StatisticListView;
import de.westlb.mgb.client.mask.view.shared.StornoGroupView;
import de.westlb.mgb.client.mask.view.shared.TradeHistoryView;
import de.westlb.mgb.client.mask.view.shared.TradeListView;
import de.westlb.mgb.client.mask.view.shared.TradeMultiView;
import de.westlb.mgb.client.mask.view.shared.TradeReclamationListView;
import de.westlb.mgb.client.mask.view.shared.TradeReclamationView;
import de.westlb.mgb.client.mask.view.shared.TradeSearchDialog;
import de.westlb.mgb.client.reports.Report;
import de.westlb.mgb.client.reports.ReportException;
import de.westlb.mgb.client.reports.ReportManager;
import de.westlb.mgb.client.server.vo.JobSearchParamsVo;
import de.westlb.mgb.client.server.vo.StatisticReportParamsVo;
import de.westlb.mgb.client.ui.util.MgbErrorMessageExtenderImpl;
import de.westlb.mgb.model.definition.StatisticReportNameDef;
import de.westlb_systems.xaf.application.ActionHandler;
import de.westlb_systems.xaf.application.ActionSet;
import de.westlb_systems.xaf.application.ApplicationPanel;
import de.westlb_systems.xaf.application.Client;
import de.westlb_systems.xaf.application.ContentSet;
import de.westlb_systems.xaf.application.MenuBar;
import de.westlb_systems.xaf.application.S3Bar;
import de.westlb_systems.xaf.application.StatusBar;
import de.westlb_systems.xaf.application.ToolBar;
import de.westlb_systems.xaf.swing.SImageComponent;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.ui.misc.IconKatalog;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.DialogViewer;
import de.westlb_systems.xaf.ui.view.View;
import de.westlb_systems.xaf.util.Debug;
import de.westlb_systems.xaf.util.SResourceBundle;
import de.westlb_systems.xaf.util.table.AbstractTableProvider;
import de.westlb_systems.xaf.util.table.TablePool;

/**
 * ComponentBox zur Erzeugung aller ContentSets die durch die  ApplicationControl gestartet werden
 * 
 * @author Manfred Boerner
 */
public class ApplicationComponentBox
    extends de.westlb_systems.xaf.application.ApplicationComponentBox {
    /** DOCUMENT ME! */
    private static final int S3BAR_WIDTH = 200;
    private SResourceBundle dialogViewerResourceBundle;
    private boolean isLoggingOn = false;

    private static Logger logger = Logger.getLogger(ApplicationComponentBox.class);

    /**
     * Default constructor
     */
    public ApplicationComponentBox() {
        super();
        dialogViewerResourceBundle = new SResourceBundle(ApplicationDefinitions.DIALOGVIEW_RESSOURCE);
        ActionHandler.getInstance().setResourceName(ApplicationDefinitions.MENUBAR_RESSOURCE);
    }

    /**
     * Creates a new application panel containing all control elementes
     * (S3Bar, Menu, Status bar, etc.)
     * 
     * @return DOCUMENT ME!
     */
    @Override
    public ApplicationPanel createApplicationPanel() {
        return new ApplicationPanel();
    }

    /**
     * Erzeugen des MenuBars
     * 
     * @param parameters DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    private ContentSet createMenuBar(Object parameters) {
		MenuBar menuBar = (MenuBar)UIManager.get("MenuBar");      
        menuBar.setForeground(UIManager.getColor("S3Bar.foreground"));
        menuBar.setBackground(UIManager.getColor("S3Bar.background"));
        return new ContentSet(menuBar, null, null, null);
    }

    /**
     * DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    private ContentSet createS3Bar() {
		S3Bar s3Bar = (S3Bar)UIManager.get("S3Bar");      

        // Parametrierung der S3Bar:
        s3Bar.setMinimumSize(new Dimension(S3BAR_WIDTH, 100));
        s3Bar.setPreferredSize(new Dimension(S3BAR_WIDTH, 100));
        s3Bar.setBackground(UIManager.getColor("S3Bar.background"));
        s3Bar.setTextColor(UIManager.getColor("S3Bar.foreground"));

        //
        return new ContentSet(s3Bar, null, null, null);
    }
    
	/**
	  * Overwrites method from superclass, because MGB needs application more meaningful
	  * message display if creation of ContentSet fails, e.g. Access Denied Message.
	  * 
	  * @param contentID ID des ContentSet
	  * @param parameters  zur Erzeugung benoetigte Parameter
	  * @param dialogOwner  Parent Komponente bei Dialogen
	  *
	  * @return  new content set or gray panel if an error occures
	  */
	 @Override
    public ContentSet createContentSet(String contentID, Object parameters, 
										Component dialogOwner) {
		 try {
			 return createSet(contentID, parameters, dialogOwner);
		 } catch (Exception ex) {
			 Debug.log(Debug.ERROR, this, "Exception in createContentSet()");
			 ex.printStackTrace();
			 Debug.log(ex);
			// Client.doCancelledHandling(ex, dialogOwner);

			 //
			 SPanel panel = new SPanel();
			 MgbErrorMessageExtenderImpl messageExtender = new MgbErrorMessageExtenderImpl();
			 String msg = messageExtender.extendMsg(ex, "Error in createContentSet(" + contentID + ")");
			 panel.add(new SLabel(msg));

			 return new ContentSet(panel, null, null, null);
		 }
	 }

    /**
     * Erzeugen eines durch die ContentID identifizierten ContentSets. 
     * 
     * @param contentID contentID ID des ContentSet
     * @param parameters parameters zur Erzeugung benoetigte Parameter
     * @param dialogOwner dialogOwner Parent Komponente bei Dialogen
     * 
     * @return: der neue ContentSet, oder "BluePanel" bei unbekannter ID
     */
    @Override
    protected ContentSet createSet(String contentID, Object parameters, Component dialogOwner) {
        ContentSet contentSet = null;
        long startmillis = System.currentTimeMillis();

        logger.debug("START "+contentID);
        if ("BLANK".equals(contentID)) { // no Parameters needed
            contentSet = new ContentSet();
        } else 
        
        if (ContentID.S3LOGO.equals(contentID)) { // no Parameters needed
            SPanel logo = new SImageComponent(IconKatalog.getInstance().getImage("MGB_LOGO"));
			// logo.setPreferredSize(new Dimension(0, 100));
            contentSet = new ContentSet(logo, null, null, null);
        } else 
        
        if (ContentID.S3ABOUT.equals(contentID)) {
            AboutDialog view = new AboutDialog(dialogOwner);
            contentSet = new ContentSet(view, null, null, null);
        } else 
        
        if (ContentID.S3TOOLBAR.equals(contentID)) {
            contentSet = createToolBar();
        } else 
        
        if (ContentID.S3BAR.equals(contentID)) {
            contentSet = createS3Bar();
        } else 
        
        if (ContentID.S3MENU.equals(contentID)) {
            contentSet = createMenuBar(parameters);
        } else 
        
        if (ContentID.S3FASTSEARCH.equals(contentID)) {
			contentSet = createDataSelectionView();
        }  else 
        
        if (ContentID.S3ABOUT.equals(contentID)) {
            AboutDialog view = new AboutDialog(dialogOwner);
            contentSet = new ContentSet(view, null, null, null);
        } else

        if (ContentID.S3START.equals(contentID)) { // no Parameters needed
            StartView view = new StartView();
            contentSet = new ContentSet(view, view, null, null);        
        } else 
        
        if (ContentID.S3TICKER.equals(contentID)) {
                contentSet = new ContentSet(null, null, null, null);
        } else 
        
        if (ContentID.S3STATUSBAR.equals(contentID)) {
            StatusBar statusbar = new StatusBar();
            contentSet = new ContentSet(statusbar, null, null, null);
        } else 
        
        if (ContentID.URLVIEW.equals(contentID)) {
            Client.openURL((String)parameters);
        } else 
              
        if (ContentID.SENDMAILVIEW.equals(contentID)) {
            Client.sendMail((String)parameters);
        } else 
        
        if (ContentID.MGB_VIEW_FILE_CONTENT.equals(contentID)) {
            // contentSet remains null to persist actual content.
            if (parameters instanceof Object[]) {
            	Object[] paramArray = (Object[])parameters;
	            new FileHandler(dialogOwner).viewFile((byte[])paramArray[0], (String)paramArray[1]);
            }
        } else 

        if (ContentID.MGB_REPORT.equals(contentID)) {
            // contentSet remains null to persist actual content.
            if (parameters instanceof Object[]) {
            	Object[] paramArray = (Object[])parameters;
            	int reportId = ((Integer)paramArray[0]).intValue();
            	Object reportParams = paramArray[1];
	            try {
	            	ReportManager reportManager = ReportManager.getInstance();
	            	Report report = reportManager.createReport(reportId, reportParams);
	            	byte[] pdfReportContent = reportManager.exportReport(report);
	            	new FileHandler(dialogOwner).viewFile(pdfReportContent, "test.pdf");
	            } catch (ReportException re) {
	            	re.printStackTrace();
	            }
            }
        } else 
        
        if (ContentID.MGB_CHECKSTATE.equals(contentID)) {
            CheckStateModel model = new CheckStateModel();
            CheckStateView view = new CheckStateView();
            CheckStateCtrl ctrl = new CheckStateCtrl();
            view.setModel(model);
            view.addUserEventListener(ctrl);
            contentSet = new ContentSet(view, view, ctrl, model);        
        } else 
        
        if (ContentID.MGB_JOBS_SELECTION_MENU.equals(contentID)) {
            CheckStateModel model = new CheckStateModel();
            model.setBusinessObject(parameters);
            CheckStateView view = new CheckStateView();
            CheckStateCtrl ctrl = new CheckStateCtrl();
            view.setModel(model);
            view.addUserEventListener(ctrl);
            contentSet = new ContentSet(view, view, ctrl, model);        
        } else 

        if (ContentID.MGB_TRADE_LIST.equals(contentID)) {
            TradeListView view = new TradeListView();
            TradeListModel model = new TradeListModel(parameters);
            TradeListCtrl ctrl = new TradeListCtrl();
            view.addUserEventListener(ctrl);
            view.setModel(model);
            contentSet = new ContentSet(view, view, ctrl, model);        
        } else 
 
        if (ContentID.MGB_TRADE_MULTIVIEW.equals(contentID)) {
            TradeMultiModel model = new TradeMultiModel(parameters);
            TradeMultiView view = new TradeMultiView(model);
            TradeMultiCtrl ctrl = new TradeMultiCtrl();
            view.addUserEventListener(ctrl); 
            view.addMultiViewListener(ctrl);
            contentSet = new ContentSet(view, view, ctrl, model);        
        } else 

         if (ContentID.MGB_TRADE_OVERVIEW.equals(contentID)) {
            TradeOverviewModel model = new TradeOverviewModel();
            TradeOverviewCtrl ctrl = new TradeOverviewCtrl();
            model.setBusinessObject(parameters);
            View view = null;
            if (model.isRepoTrade()) {
            	view = getViewFromUIManager("RepoTradeOverviewView");
            } else if (model.isDoubleTrade()) {
                view = getViewFromUIManager("DoubleTradeOverviewView");
            } else {
               	view = getViewFromUIManager("TradeOverviewView");
            }
            view.setModel(model);
            view.addUserEventListener(ctrl);
            contentSet = new ContentSet(view, view, ctrl, model);        
        } else 

         if (ContentID.MGB_TRADE_RECLAMATION.equals(contentID)) {
            TradeReclamationView view = new TradeReclamationView();
            TradeReclamationCtrl ctrl = new TradeReclamationCtrl();
            view.addUserEventListener(ctrl);
            TradeReclamationModel model = new TradeReclamationModel();
            model.setBusinessObject(parameters);
            view.setModel(model);
            contentSet = new ContentSet(view, view, ctrl, model);        
        } else 

         if (ContentID.MGB_TRADE_HISTORY.equals(contentID)) {
            TradeHistoryView view = new TradeHistoryView();
            TradeHistoryModel model = new TradeHistoryModel();
            TradeHistoryCtrl ctrl = new TradeHistoryCtrl();
            model.setBusinessObject(parameters);
            view.setModel(model);
            view.addUserEventListener(ctrl);
            contentSet = new ContentSet(view, view, ctrl, model);        
        } else 

         if (ContentID.MGB_TRADE_PARAMETER.equals(contentID)) {
			View view = getViewFromUIManager("TradeParameterView");
            TradeParameterModel model = new TradeParameterModel();
            model.setBusinessObject(parameters);
            view.setModel(model);
            contentSet = new ContentSet(view, view, null, model);        
        } else 

		if (ContentID.MGB_NEW_TRADE_STATE.equals(contentID)) 
        {
            NewTradeStateModel model = new NewTradeStateModel();
            model.setBusinessObject(parameters);
            NewTradeStateDialog view = new NewTradeStateDialog(model);
            DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
            contentSet = new ContentSet(viewer, view, null, model);
        } else

		if (ContentID.MGB_NEW_TRADE_RECLAMATION.equals(contentID)) 
        {
            NewTradeReclamationModel model = new NewTradeReclamationModel();
            model.setBusinessObject(parameters);
            NewTradeReclamationDialog view = new NewTradeReclamationDialog(model);
            DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
            contentSet = new ContentSet(viewer, view, null, model);
        } else

		if (ContentID.MGB_CLOSE_RECLAMATION.equals(contentID)) 
        {
            CloseReclamationModel model = new CloseReclamationModel();
            model.setBusinessObject(parameters);
            CloseReclamationDialog view = new CloseReclamationDialog(model);
            DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
            contentSet = new ContentSet(viewer, view, null, model);
        } else

		if (ContentID.MGB_TRADER_INFO_NEW.equals(contentID)) 
        {
            NewTraderInfoModel model = new NewTraderInfoModel();
            boolean forceShow = Boolean.TRUE.equals(parameters);
              if (forceShow || model.getSize() > 0) {
    	        NewTraderInfoDialog view = new NewTraderInfoDialog(model);
    	        NewTraderInfoCtrl ctrl = new NewTraderInfoCtrl();
    	        view.addUserEventListener(ctrl);
        	    DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
            	contentSet = new ContentSet(viewer, view, ctrl, model);
            } 
        } else

		if (ContentID.MGB_INSTRUMENT_INFO_NEW.equals(contentID)) 
        {
            NewInstrumentInfoModel model = new NewInstrumentInfoModel();
            boolean forceShow = Boolean.TRUE.equals(parameters);
            if (forceShow || model.getSize() > 0) {
		        NewInstrumentInfoDialog view = new NewInstrumentInfoDialog();
	            NewInstrumentInfoCtrl ctrl = new NewInstrumentInfoCtrl();
	            view.setModel(model);
	            view.addUserEventListener(ctrl);
	        	DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
	           	contentSet = new ContentSet(viewer, view, ctrl, model);
            }
        } else
        	
    	if (ContentID.MGB_LOCATION_INFO_NEW.equals(contentID)) 
        {
            NewLocationInfoModel model = new NewLocationInfoModel();
            boolean forceShow = Boolean.TRUE.equals(parameters);
            if (forceShow || model.getSize() > 0) {
            	NewLocationInfoDialog view = new NewLocationInfoDialog();
	            NewLocationInfoCtrl ctrl = new NewLocationInfoCtrl();
	            view.setModel(model);
	            view.addUserEventListener(ctrl);
	        	DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
	           	contentSet = new ContentSet(viewer, view, ctrl, model);
            }
        } else
                                    
		if (ContentID.MGB_SEARCH_NEW_EMPLOYEE.equals(contentID)) 
        {
            CheckNewEmployeeModel model = new CheckNewEmployeeModel();
            model.setBusinessObject(parameters);
	        CheckNewEmployeeDialog view = new CheckNewEmployeeDialog(model);
    	    DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
        	contentSet = new ContentSet(viewer, view, null, model);
        } else

		if (ContentID.MGB_EDIT_EMPLOYEE.equals(contentID)) 
        {
            EmployeeEditModel model = new EmployeeEditModel();
            model.setBusinessObject(parameters);
	        EmployeeEditDialog view = new EmployeeEditDialog(model);
    	    DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
        	contentSet = new ContentSet(viewer, view, null, model);
        } else

		if (ContentID.MGB_OPEN_EMPLOYEE.equals(contentID)) 
        {
            EmployeeEditModel model = new EmployeeEditModel();
            model.setBusinessObject(parameters);
	        EmployeeOverviewView view = new EmployeeOverviewView(model);
            EmployeeOverviewCtrl ctrl = new EmployeeOverviewCtrl();
            view.setModel(model);
            view.addUserEventListener(ctrl);
        	contentSet = new ContentSet(view, view, ctrl, model);
        } else

		if (ContentID.MGB_EMPLOYEE_LIST.equals(contentID)) 
        {
	        EmployeeListView view = new EmployeeListView();
            CheckNewEmployeeModel model = new CheckNewEmployeeModel();
            model.setBusinessObject(parameters);
            EmployeeListCtrl ctrl = new EmployeeListCtrl();
            view.setModel(model);
            view.addUserEventListener(ctrl);
        	contentSet = new ContentSet(view, view, ctrl, model);
        } else

		if (ContentID.MGB_INSTRUMENT_EDIT.equals(contentID)) 
        {
            InstrumentEditModel model = new InstrumentEditModel();
            model.setBusinessObject(parameters);
	        InstrumentEditDialog view = new InstrumentEditDialog(model);
    	    DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
        	contentSet = new ContentSet(viewer, view, null, model);
        } else

		if (ContentID.MGB_DUAL_CONTROL_INFO.equals(contentID)) 
        {
            DualControlJobInfoModel model = new DualControlJobInfoModel();
            boolean forceShow = Boolean.TRUE.equals(parameters);
            if (forceShow || model.getSize() > 0) {
		        DualControlJobInfoDialog view = new DualControlJobInfoDialog();
	            DualControlJobInfoCtrl ctrl = new DualControlJobInfoCtrl();
	            view.setModel(model);
	            view.addUserEventListener(ctrl);
	        	DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
	           	contentSet = new ContentSet(viewer, view, ctrl, model);
            }
        } else

		if (ContentID.MGB_DUAL_CONTROL_JOB_EDIT.equals(contentID)) 
        {
            DualControlJobEditModel model = new DualControlJobEditModel();
            model.setBusinessObject(parameters);
	        DualControlJobEditDialog view = new DualControlJobEditDialog(model);
    	    DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
        	contentSet = new ContentSet(viewer, view, null, model);
        } else

		if (ContentID.MGB_ADMIN_LOGIN.equals(contentID)) 
        {
            LoginModel model = new LoginModel();
	        LoginDialog view = new LoginDialog(model);
    	    DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
        	contentSet = new ContentSet(viewer, view, null, model);	
        } else

		if (ContentID.MGB_EMPLOYEE_RECLAMATION_LIST.equals(contentID)) 
        {
	        ReclamationEmployeeListView view = new ReclamationEmployeeListView();
            ReclamationEmployeeListModel model = new ReclamationEmployeeListModel();
            ReclamationEmployeeListCtrl ctrl = new ReclamationEmployeeListCtrl();
            view.setModel(model);
            view.addUserEventListener(ctrl);
        	contentSet = new ContentSet(view, view, ctrl, model);
        } else
        
		if (ContentID.MGB_TRADE_RECLAMATION_LIST.equals(contentID)) 
		{
			TradeReclamationListView view = new TradeReclamationListView();
			TradeReclamationListModel model = new TradeReclamationListModel();
			TradeReclamationListCtrl ctrl = new TradeReclamationListCtrl();
			view.setModel(model);
			view.addUserEventListener(ctrl);
			contentSet = new ContentSet(view, view, ctrl, model);
		} else

		if (ContentID.MGB_STATE_CODE_LIST.equals(contentID)) 
        {
	        StateCodeListView view = new StateCodeListView();
            StateCodeListModel model = new StateCodeListModel();
            StateCodeListCtrl ctrl = new StateCodeListCtrl();
            view.setModel(model);
            view.addUserEventListener(ctrl);
        	contentSet = new ContentSet(view, view, ctrl, model);
        } else

		if (ContentID.MGB_STATE_CODE_EDIT.equals(contentID)) 
        {
            StateCodeEditModel model = new StateCodeEditModel();
            model.setBusinessObject(parameters);
            StateCodeEditDialog view = new StateCodeEditDialog(model);
            DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
            contentSet = new ContentSet(viewer, view, null, model);
        } else

		if (ContentID.MGB_EXCHANGE_LIST.equals(contentID)) 
        {
            ExchangeListModel model = new ExchangeListModel();
            ExchangeListView view = new ExchangeListView(model);
            ExchangeListCtrl ctrl = new ExchangeListCtrl();
            view.addUserEventListener(ctrl);
        	contentSet = new ContentSet(view, view, ctrl, model);
        } else

		if (ContentID.MGB_EXCHANGE_VIEW.equals(contentID)) 
        {
            ExchangeEditModel model = new ExchangeEditModel();
            model.setBusinessObject(parameters);
            ExchangeView view = new ExchangeView(model);
            ExchangeCtrl ctrl = new ExchangeCtrl();
            view.addUserEventListener(ctrl);
        	contentSet = new ContentSet(view, view, ctrl, model);
        } else

		if (ContentID.MGB_EXCHANGE_MAPPING_EDIT.equals(contentID)) 
        {
            ExchangeMappingEditModel model = new ExchangeMappingEditModel();
            model.setBusinessObject(parameters);
	        ExchangeMappingEditDialog view = new ExchangeMappingEditDialog(model);
    	    DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
        	contentSet = new ContentSet(viewer, view, null, model);
        } else

		if (ContentID.MGB_EXCHANGE_EDIT.equals(contentID)) 
        {
            ExchangeEditModel model = new ExchangeEditModel();
            model.setBusinessObject(parameters);
	        ExchangeEditDialog view = new ExchangeEditDialog(model);
    	    DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
        	contentSet = new ContentSet(viewer, view, null, model);
        } else

		if (ContentID.MGB_ADDON_LIST.equals(contentID)) 
        {
            AddonListModel model = new AddonListModel();
	        AddonListView view = new AddonListView(model);
	        AddonListCtrl ctrl = new AddonListCtrl();
	       	view.addUserEventListener(ctrl);
        	contentSet = new ContentSet(view, view, ctrl, model);
        } else
        
        if (ContentID.MGB_PRICE_CHECK_CATEGORY_LIST.equals(contentID)) 
        {
            PriceCheckCategoryListModel model = new PriceCheckCategoryListModel();
	        PriceCheckCategoryListView view = new PriceCheckCategoryListView(model);
	        PriceCheckCategoryListCtrl ctrl = new PriceCheckCategoryListCtrl();
	       	view.addUserEventListener(ctrl);
        	contentSet = new ContentSet(view, view, ctrl, model);
        } else

        if (ContentID.MGB_CONFIGURATION_LIST.equals(contentID)) 
        {
            MgbConfigurationListModel model = new MgbConfigurationListModel();
	        MgbConfigurationListView view = new MgbConfigurationListView(model);
	        MgbConfigurationCtrl ctrl = new MgbConfigurationCtrl();
	       	view.addUserEventListener(ctrl);
        	contentSet = new ContentSet(view, view, ctrl, model);
        } else

        if (ContentID.MGB_CONFIGURATION_EDIT.equals(contentID)) 
        {
            MgbConfigurationEditModel model = new MgbConfigurationEditModel();
           	model.setBusinessObject(parameters);
	        MgbConfigurationEditDialog view = new MgbConfigurationEditDialog(model);
    	    DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
        	contentSet = new ContentSet(viewer, view, null, model);
        } else
        
        if (ContentID.MGB_ADDON_EDIT.equals(contentID)) 
        {
            AddonEditModel model = new AddonEditModel();
            model.setBusinessObject(parameters);
	        AddonEditDialog view = new AddonEditDialog(model);
    	    DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
        	contentSet = new ContentSet(viewer, view, null, model);
        } else
        
		if (ContentID.MGB_TRADE_STORNO.equals(contentID)) 
        {
            StornoGroupView view = new StornoGroupView();
  			StornoGroupModel model = new StornoGroupModel();
            StornoGroupCtrl ctrl = new StornoGroupCtrl();
            model.setBusinessObject(parameters);
            view.setModel(model);
            view.addUserEventListener(ctrl);
            contentSet = new ContentSet(view, view, ctrl, model);        
        } else
        
		if (ContentID.MGB_PRICE_CHECK_CATEGORY_EDIT.equals(contentID)) 
        {
			Model model = getModelFromUIManager("PriceCheckCategoryEditModel");
            model.setBusinessObject(parameters);
			View view = getViewFromUIManager("PriceCheckCategoryEditDialog");
			view.setModel(model);
    	    DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
        	contentSet = new ContentSet(viewer, view, null, model);
        } else

		if (ContentID.MGB_NOT_YET_IMPLEMENTED.equals(contentID)) 
        {
            SPanel panel = new SPanel();
            panel.setBackground(Color.blue);

            SLabel lab = new SLabel("Not yet implemented");
            lab.setForeground(Color.yellow);
            panel.add(lab);
            contentSet = new ContentSet(panel, null, null, null);
        } else

		if (ContentID.MGB_INSTRUMENT_LIST.equals(contentID)) 
        {
	        InstrumentListView view = new InstrumentListView();
            InstrumentListModel model = new InstrumentListModel();
            model.setBusinessObject(parameters);
            InstrumentListCtrl ctrl = new InstrumentListCtrl();
            view.setModel(model);
            view.addUserEventListener(ctrl);
        	contentSet = new ContentSet(view, view, ctrl, model);
        } else

		if (ContentID.MGB_JOBS_SELECTION_DIALOG.equals(contentID)) 
        {
			AbstractModel model = (AbstractModel)parameters;
	        JobSelectionDialog view = new JobSelectionDialog(model);
    	    DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
        	contentSet = new ContentSet(viewer, view, null, model);
        } else

		if (ContentID.MGB_JOB_LIST.equals(contentID)) 
		{
			JobListModel model = new JobListModel();
			JobSearchParamsVo param = new JobSearchParamsVo();
			param.setNumberOfJobs(Integer.valueOf(20));
			model.setBusinessObject(param);
			JobListView view = new JobListView(model);
			JobListCtrl ctrl = new JobListCtrl();
			view.addUserEventListener(ctrl);
			contentSet = new ContentSet(view, view, ctrl, model);
		} else

		if (ContentID.MGB_JOB_NEW.equals(contentID)) 
		{
			NewJobModel model = new NewJobModel();
			model.setBusinessObject(parameters);
			NewJobDialog view = new NewJobDialog(model);
			DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
			contentSet = new ContentSet(viewer, view, null, model);
		} else

	    if (ContentID.MGB_LOG_VIEW.equals(contentID)) 
	    {
            LogViewModel model = new LogViewModel();
            model.setBusinessObject(parameters);
            LogViewDialog view = new LogViewDialog(model);
            DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
            contentSet = new ContentSet(viewer, view, null, model);
        } else

	    if (ContentID.MGB_PRICE_DEVIATION_LIST.equals(contentID)) 
		{
			PriceDeviationListModel model = new PriceDeviationListModel();
			model.setBusinessObject(parameters);
			PriceDeviationListView view = new PriceDeviationListView();
            view.setModel(model);
        	contentSet = new ContentSet(view, view, null, model);
		} else

		if (ContentID.MGB_PRICE_DEVIATION_STATISTIC_LIST.equals(contentID)) 
		{
			PriceDeviationStatisticListModel model = new PriceDeviationStatisticListModel();
			model.setBusinessObject(parameters);
			PriceDeviationStatisticListView view = new PriceDeviationStatisticListView();
            view.setModel(model);
        	contentSet = new ContentSet(view, view, null, model);
		} else

		if (ContentID.MGB_APP_STATISTIC.equals(contentID)) 
		{
			AppStatisticModel model = new AppStatisticModel();
			AppStatisticDialog view = new AppStatisticDialog(model);
			DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
			contentSet = new ContentSet(viewer, view, null, model);
		} else

		if (ContentID.MGB_REFRESH_SELECTION_LIST.equals(contentID)) 
		{
			refreshSelectionList(parameters);
		} else
		
		if (ContentID.MGB_STATISTIC_BUSINESS_DAY.equals(contentID)) 
		{
            contentSet = createStatisticContentSet(StatisticReportNameDef.BUSINESS_DAY, dialogOwner);
		} else
		
		if (ContentID.MGB_STATISTIC_BUSINESS_MONTH.equals(contentID)) 
		{
			contentSet = createStatisticContentSet(StatisticReportNameDef.BUSINESS_MONTH, dialogOwner);
		} else
		
		if (ContentID.MGB_STATISTIC_JOB.equals(contentID)) 
		{
			contentSet = createStatisticContentSet(StatisticReportNameDef.JOB, dialogOwner);
		} else
		
		if (ContentID.MGB_STATISTIC_BUSINESS_UNIT.equals(contentID)) 
		{
			contentSet = createStatisticContentSet(StatisticReportNameDef.BUSINESS_UNIT, dialogOwner);
		} else

		if (ContentID.MGB_STATISTIC_LOCATION.equals(contentID)) 
		{
			contentSet = createStatisticContentSet(StatisticReportNameDef.LOCATION, dialogOwner);
		} else

		if (ContentID.MGB_STATISTIC_LIMIT_UNIT.equals(contentID)) 
		{
			contentSet = createStatisticContentSet(StatisticReportNameDef.LIMIT_UNIT, dialogOwner);
		} else

		if (ContentID.MGB_STATISTIC_TRADE_TYPE.equals(contentID)) 
		{
			contentSet = createStatisticContentSet(StatisticReportNameDef.TRADE_TYPE, dialogOwner);
		} else

		if (ContentID.MGB_STATISTIC_TRADE_CATEGORY_BOND.equals(contentID)) 
		{
			contentSet = createStatisticContentSet(StatisticReportNameDef.TRADE_CATEGORY_BOND, dialogOwner);
		} else

        if (ContentID.MGB_STATISTIC_TRADE_CATEGORY_DERIVATIVE.equals(contentID)) 
        {
            contentSet = createStatisticContentSet(StatisticReportNameDef.TRADE_CATEGORY_DERIVATIVE, dialogOwner);
        } else

        if (ContentID.MGB_STATISTIC_TRADE_CATEGORY_REPO.equals(contentID)) 
		{
			contentSet = createStatisticContentSet(StatisticReportNameDef.TRADE_CATEGORY_REPO, dialogOwner);
		} else

	    if (ContentID.MGB_STATISTIC_TRADE_CATEGORY_MM.equals(contentID)) 
	    {
	        contentSet = createStatisticContentSet(StatisticReportNameDef.TRADE_CATEGORY_MM, dialogOwner);
	    } else

	    if (ContentID.MGB_STATISTIC_TRADE_CATEGORY_EQUITY.equals(contentID)) 
		{
			contentSet = createStatisticContentSet(StatisticReportNameDef.TRADE_CATEGORY_EQUITY, dialogOwner);
		} else

        if (ContentID.MGB_STATISTIC_TRADE_TYPE_EQUITY.equals(contentID)) 
        {
            contentSet = createStatisticContentSet(StatisticReportNameDef.TRADE_TYPE_EQUITY, dialogOwner);
        } else
            
        if (ContentID.MGB_STATISTIC_INSTRUMENT.equals(contentID)) 
        {
            contentSet = createStatisticContentSet(StatisticReportNameDef.INSTRUMENT, dialogOwner);
        } else

        if (ContentID.MGB_STATISTIC_TRADER.equals(contentID)) 
		{
			contentSet = createStatisticContentSet(StatisticReportNameDef.TRADER, dialogOwner);
		} else

		if (ContentID.MGB_STATISTIC_BOOK.equals(contentID)) 
		{
			contentSet = createStatisticContentSet(StatisticReportNameDef.BOOK, dialogOwner);
		} else

		if (ContentID.MGB_STATISTIC_REPORT.equals(contentID)) 
		{
			StatisticListModel model = new StatisticListModel();
			StatisticListCtrl ctrl = new StatisticListCtrl();
			model.setBusinessObject(parameters);
			StatisticListView view = new StatisticListView(model);
			view.addUserEventListener(ctrl);
			contentSet = new ContentSet(view, view, ctrl, model);
		} else
		
		if (ContentID.MGB_NEW_MAIL.equals(contentID)) 
		{
			NewMailModel model = new NewMailModel();
			model.setBusinessObject(parameters);
			NewMailDialog view = new NewMailDialog(model);
			DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
			contentSet = new ContentSet(viewer, view, null, model);
		} else

		if (ContentID.MGB_TRADE_SEARCH_DIALOG.equals(contentID)) 
		{
			TradeSearchModel model = new TradeSearchModel();
			model.setBusinessObject(parameters);
			TradeSearchDialog view = new TradeSearchDialog(model);
			DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
			TradeSearchCtrl ctrl = new TradeSearchCtrl();
			view.addUserEventListener(ctrl);
			contentSet = new ContentSet(viewer, view, ctrl, model);
		} else

		if (ContentID.MGB_DEBUG.equals(contentID)) 
		{
			isLoggingOn = !isLoggingOn;
			AbstractClient.initLog4j(this, isLoggingOn);     
	        ApplicationDefinitions.extra_debug.setSelected(isLoggingOn);
		} else

        {
            SPanel panel = new SPanel();
            panel.setBackground(Color.blue);

            SLabel lab = new SLabel("undefined View:" + contentID);
            lab.setForeground(Color.yellow);
            panel.add(lab);
            contentSet = new ContentSet(panel, null, null, null);
        }
        logger.debug("STOP "+contentID+ " ("+ (System.currentTimeMillis()-startmillis) + " millis)");
        return contentSet;
    }
    
    private ContentSet createStatisticContentSet(String reportName, Component dialogOwner) {
        ContentSet contentSet;
		StatisticDataSelectionModel model = new StatisticDataSelectionModel();
        StatisticReportParamsVo params = new StatisticReportParamsVo();
        params.setReportName(reportName);
        model.setBusinessObject(params);

		StatisticDataSelectionDialog view = new StatisticDataSelectionDialog(model);
		StatisticDataSelectionCtrl ctrl = new StatisticDataSelectionCtrl();
		view.addUserEventListener(ctrl);
		DialogViewer viewer = new DialogViewer(view, dialogOwner, dialogViewerResourceBundle);
		contentSet = new ContentSet(viewer, view, ctrl, model);

        return contentSet;
    }
    
    @SuppressWarnings("rawtypes")
    private void refreshSelectionList(Object selectionListClass) {
		// clear requested selection list or all selectionlist if parameters == null
		Enumeration tableProvider = TablePool.getTableProviders();
		while(tableProvider != null && tableProvider.hasMoreElements()) {
			Object provider = tableProvider.nextElement();
			if (selectionListClass == null || provider.getClass() == selectionListClass) {
				if( provider instanceof AbstractTableProvider) {
					((AbstractTableProvider)provider).clearCache();
				}
			}
		}
    }

	private ContentSet createDataSelectionView() {
		DataSelectionModel model = new DataSelectionModel();
		DataSelectionView view = new DataSelectionView();
		DataSelectionCtrl ctrl = new DataSelectionCtrl();
		view.setModel(model);
		view.addUserEventListener(ctrl);
		

        view.setBackgroundColor(UIManager.getColor("S3Bar.background"));
        view.setTextColor(UIManager.getColor("S3Bar.foreground"));
		
		Dimension dim = view.getPreferredSize();
		dim.width = S3BAR_WIDTH;
		view.setMinimumSize(dim);
		view.setPreferredSize(dim);
		ContentSet contentSet = new ContentSet(view, view, ctrl, view.getModel());
		return contentSet;
	}

    /**
     * DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    private ContentSet createToolBar() {
        // Parametrierung der einzelnen Items:
        // boolean largeIcons = de.westlb_systems.webvis.util.PropertyFactory.isTrueProperty("toolBar.largeIcons", false);
        boolean largeIcons = false;
        ApplicationDefinitions.view_refresh.setEnabled(true);
 
        ApplicationDefinitions.view_largeIcons.setSelected(largeIcons);

        // Erzeugen des ToolBars
        ToolBar toolBar = new ToolBar(
                                  new ActionSet[] {
            ApplicationDefinitions.separator, 
            ApplicationDefinitions.go_prev, 
            ApplicationDefinitions.go_next, 
            ApplicationDefinitions.view_refresh, 
            ApplicationDefinitions.go_home, 
            ApplicationDefinitions.separator, 
			ApplicationDefinitions.go_prev_litem,             
            ApplicationDefinitions.go_next_litem, 
        }, largeIcons);


        // Parametrierung des ToolBars:
        toolBar.setBackground(UIManager.getColor("ToolBar.background"));

        return new ContentSet(toolBar, null, null, null);
    }
 
	public Model getModelFromUIManager(String modelName) {
		Model model = null;
		String name = (String)UIManager.get(modelName);
		try {
			model = (Model)Class.forName(name).newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return model;
	}
	   
    public View getViewFromUIManager(String viewName) {
    	View view = null;
	 	String name = (String)UIManager.get(viewName);
		try {
			view = (View)Class.forName(name).newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return view;
    }
}