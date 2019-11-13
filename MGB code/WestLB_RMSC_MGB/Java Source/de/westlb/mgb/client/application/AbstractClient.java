package de.westlb.mgb.client.application;


import java.awt.Component;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

import javax.swing.UIManager;

import org.apache.log4j.PropertyConfigurator;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.MgbSessionSingleton;
import de.westlb.mgb.client.ui.selection_list.AbstractSelectionList;
import de.westlb.mgb.client.ui.selection_list.AllManualStateCodeList;
import de.westlb.mgb.client.ui.selection_list.AllSourceSystemList;
import de.westlb.mgb.client.ui.selection_list.AllStateCodeList;
import de.westlb.mgb.client.ui.selection_list.AutoStateCodeList;
import de.westlb.mgb.client.ui.selection_list.BloombergCurrencyCodeList;
import de.westlb.mgb.client.ui.selection_list.BloombergMaturityCodeList;
import de.westlb.mgb.client.ui.selection_list.DbBloombergCurrencyCodeDictionaryProvider;
import de.westlb.mgb.client.ui.selection_list.DbBloombergMaturityCodeDictionaryProvider;
import de.westlb.mgb.client.ui.selection_list.DbMandantDictionaryProvider;
import de.westlb.mgb.client.ui.selection_list.DbPriceCheckCategoryDicionaryProvider;
import de.westlb.mgb.client.ui.selection_list.DbSourceSystemDictionaryProvider;
import de.westlb.mgb.client.ui.selection_list.DbStateCodeDictionaryProvider;
import de.westlb.mgb.client.ui.selection_list.DbTimePeriodDictionaryProvider;
import de.westlb.mgb.client.ui.selection_list.MailTypeList;
import de.westlb.mgb.client.ui.selection_list.MandantList;
import de.westlb.mgb.client.ui.selection_list.ManualSampleStateCodeList;
import de.westlb.mgb.client.ui.selection_list.ManualStateCodeList;
import de.westlb.mgb.client.ui.selection_list.MarketDataRequestTypeList;
import de.westlb.mgb.client.ui.selection_list.MoneyMarketTimePeriodList;
import de.westlb.mgb.client.ui.selection_list.PriceCheckCategoryList;
import de.westlb.mgb.client.ui.selection_list.PropertyFileDictionaryProvider;
import de.westlb.mgb.client.ui.selection_list.ReclamationStateCodeList;
import de.westlb.mgb.client.ui.selection_list.SourceSystemList;
import de.westlb.mgb.client.ui.util.JacobExcelExporter;
import de.westlb.mgb.client.ui.util.MgbErrorMessageExtenderImpl;
import de.westlb.mgb.model.definition.MgbConfigurationDef;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb.mgb.util.MgbSystemUtils;
import de.westlb_systems.xaf.application.ApplicationPanel;
import de.westlb_systems.xaf.application.ComponentBox;
import de.westlb_systems.xaf.application.ContentSet;
import de.westlb_systems.xaf.application.MenuBar;
import de.westlb_systems.xaf.application.S3Bar;
import de.westlb_systems.xaf.application.StatusBar;
import de.westlb_systems.xaf.application.ToolBar;
import de.westlb_systems.xaf.application.event.LogMessageEvent;
import de.westlb_systems.xaf.help.HelpBroker;
import de.westlb_systems.xaf.swing.STable;
import de.westlb_systems.xaf.swing.STableInitializer;
import de.westlb_systems.xaf.swing.SUIManager;
import de.westlb_systems.xaf.ui.view.ErrorBox;
import de.westlb_systems.xaf.ui.view.PopUpDialog;
import de.westlb_systems.xaf.util.PropertyFactory;
import de.westlb_systems.xaf.util.SLocale;
import de.westlb_systems.xaf.util.SResourceBundle;
import de.westlb_systems.xaf.util.table.DictionaryProvider;


/**
 * Insert the type's description here. 
 * 
 * @author: Manfred Boerner
 */
public abstract class AbstractClient extends de.westlb_systems.xaf.application.AbstractClient {


	Thread keepAliveThread = null;
	private StatusBar statusBar = null;
	/** Name der zu startenden Content Id */
	private String startContentID ;
	private Object startContentParameters;

    /**
     * AbstractClient constructor comment.
     */
    public AbstractClient() {
        this(ContentID.S3START);
    }
    
	/**
	 * AbstractClient constructor comment.
	 */
	public AbstractClient(String startContentID, Object startContentParameters) {
		super();
		this.startContentID = startContentID == null ? ContentID.S3START : startContentID;
		this.startContentParameters = startContentParameters;
	}
    
        /**
     * AbstractClient constructor comment.
     */
    public AbstractClient(String startContentID) {
        this(startContentID, null);
    }
    
    public void recreateComponents() {
    	try {
            String lookAndFeel = MgbServiceFactory.getService().getMgbConfigurationValue(MgbConfigurationDef.LOOK_AND_FEEL);
			UIManager.setLookAndFeel(lookAndFeel);
        } catch (Exception e) {
    		e.printStackTrace();
            System.err.println("Unable to set LookAndFeel");
        }

		ComponentBox appCompBox = new ApplicationComponentBox();
	    ToolBar toolBar = (ToolBar)appCompBox.createContentSet("S3TOOLBAR", null).getViewComponent();
	    S3Bar s3Bar 	= (S3Bar)appCompBox.createContentSet("S3BAR", null).getViewComponent();
	    statusBar = (StatusBar)appCompBox.createContentSet("S3STATUSBAR", null).getViewComponent();
        ContentSet fastSearch = appCompBox.createContentSet("S3FASTSEARCH", null);
	
	    // In der Applikationskontrolle werden Properties für die MenüBar-Generation eingelesen.
	    // Deshalb menuBar erst hier erzeugen.
	    MenuBar menuBar = (MenuBar)appCompBox.createContentSet("S3MENU", null).getViewComponent(); 
	
	    applicationControl.setMenuBar(menuBar);
	    applicationControl.setToolBar(toolBar);
	    applicationControl.setFolderBar(s3Bar);
  		((ApplicationControl)applicationControl).setFastSearch(fastSearch);
        applicationControl.connectContent(fastSearch); // Damit LogMessage-Nachrichten sichtbar werden.
	    applicationControl.init();
	
	    applicationControl.startContent(startContentID, startContentParameters);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    protected de.westlb_systems.xaf.application.ApplicationControl createComponents() {
        ComponentBox appCompBox = new ApplicationComponentBox();

        ToolBar toolBar = (ToolBar)appCompBox.createContentSet("S3TOOLBAR", null).getViewComponent();
        S3Bar s3Bar 	= (S3Bar)appCompBox.createContentSet("S3BAR", null).getViewComponent();
        Component ticker = appCompBox.createContentSet("S3TICKER", null).getViewComponent();
        statusBar = (StatusBar)appCompBox.createContentSet("S3STATUSBAR", null).getViewComponent();

        ContentSet fastSearch = appCompBox.createContentSet("S3FASTSEARCH", null);

        ApplicationPanel appPanel = ((ApplicationComponentBox)appCompBox).createApplicationPanel();
        appPanel.setLogo(appCompBox.createContentSet("S3LOGO", null).getViewComponent());

        HelpBroker hb = new HelpBroker(); 
        hb.enableHelpKey(appPanel, "HOME");

        STable.setCounterLimit(1000);
		STable.setInitializer(new STableInitializer() {
			SResourceBundle bundle = new SResourceBundle(ApplicationDefinitions.SC_RESOURCE);
   			@Override
            public void initializeTable(STable table) {
   				table.setResourceBundle(bundle);
   				table.setColumnMargin(table.getColumnMargin()+3);
   			}
		});
		
		/* Jacob is the road to 32-bit *and* 64-bit support. For the time being,
		 * play it safe and enable Jacob only in 64-bit mode as I have little
		 * experience with productive deployment of JacobExcelExporter.
		 * -- RSt 2015-01-19 */
		STable.setExportProvider(JacobExcelExporter.getInstance());
		
		ErrorBox.setDefaultErrorMessageExtender(new MgbErrorMessageExtenderImpl());
		
        ApplicationControl appControl = new ApplicationControl(this);
        appControl.setApplicationPanel(appPanel);

        // In der Applikationskontrolle werden Properties für die MenüBar-Generation eingelesen.
        // Deshalb menuBar erst hier erzeugen.
        MenuBar menuBar = (MenuBar)appCompBox.createContentSet("S3MENU", null).getViewComponent(); 

        appControl.setMenuBar(menuBar);
        appControl.setToolBar(toolBar);
        appControl.setTicker(ticker);
        appControl.setStatusBar(statusBar);
  		appControl.setFastSearch(fastSearch);

        appControl.connectContent(fastSearch); // Damit LogMessage-Nachrichten sichtbar werden.
        appControl.setFolderBar(s3Bar);
        appControl.addUserEventListener(this);

        appControl.start(startContentID, startContentParameters);

        return appControl;
    }

    /**
     * DOCUMENT ME!
     * 
     * @param owner DOCUMENT ME!
     */
    protected void init(Component owner, String serverName, String userId, String mandantCode) {
        
		// The XAF components are using the SLocale and we want to have
		// the language settings operating systems dependend.
		SLocale.setDefaultLocale(Locale.getDefault());

		// Load properties from client.properties
        PropertyFactory.load("client");

		// Initializing Logging        
		initLog4j(this, false);

		showDefaults();

        // creating Server Connection
        showStatus("connecting Server...");

        // Check Server - Client Versions
        // showStatus("checking Server Version...");

        // Login. Create server site session
        showStatus("logging in... userId = " + userId);

        String lookAndFeel = null;
        try {
        	Mgb mgbService = MgbServiceFactory.getService();
            mgbService.login(userId, mandantCode, MgbSystemUtils.getSystemProperties());
			String proxyDir = mgbService.getMgbConfigurationValue(MgbConfigurationDef.MARKET_DATA_PROXY_DIRECTORY);
			mgbService.setMarketDataProxyDirectory(proxyDir);
			lookAndFeel = mgbService.getMgbConfigurationValue(MgbConfigurationDef.LOOK_AND_FEEL);
            UIManager.setLookAndFeel(lookAndFeel);
			/* Sometimes when restarting the applet, the singleton instance
			 * is not reset, so we have to do it here. Otherwise we'd have
			 * old source system information. */
            MgbSessionSingleton.getInstance(true);
        } catch (Exception e) {
    		e.printStackTrace();
    		if (e instanceof RemoteException)
    		showLoginErrorDialog((RemoteException) e, owner);
        	showErrorStatus("Error: Unable to login!");
            dispose(e.getMessage());
            return;
        }
        
        // initializing static Data
        initializeTablePools(userId, mandantCode);

        // creating Components
        applicationControl = createComponents();
  		setStatusTextAt(StatusBar.VERSION,	Version.instance().getVersionString());
  		setStatusTextAt(StatusBar.DATABASE, PropertyFactory.getProperty("mgb.environment.name")); 	

		// Start keepAliveThread
  		keepAliveThread = new KeepAliveThread();
		keepAliveThread.start();
		
        // starting Application Control
        showStatus("running...");
     }
  
    protected void dispose(String status) {
        super.dispose();
    }

    
    private void showLoginErrorDialog(RemoteException e, Component owner) {
      	if (e.getMessage() == null) {
      		return;
      	}
       	System.err.println("errorMessage = <" + e.getMessage() + ">");

		if (e.getMessage().indexOf("NotAuthorized") >= 0) {
			PopUpDialog dialog = new PopUpDialog(owner);
			dialog.showMessage(
				ApplicationResourceHandler.getInstance().getResourceString("E_NOT_AUTHORIZED"),
				LogMessageEvent.INFO
			);
		} else if (e.getMessage().indexOf("Connection refused") >= 0) {
			PopUpDialog dialog = new PopUpDialog(owner);
			dialog.showMessage(
				ApplicationResourceHandler.getInstance().getResourceString("E_CONNECTION_REFUSED"),
				LogMessageEvent.INFO
			);
				
		}
    }

    /**
     * Tablepool initialisieren
     */
    public void initializeTablePools(String userId, String mandantCode) {
    	DictionaryProvider provider = null;
    	
    	// PropertyFileDicionaryProvider is the default provider for all selection lists.
		provider = new PropertyFileDictionaryProvider();
		AbstractSelectionList.setDictionaryProvider(AbstractSelectionList.class, provider);

		provider = new PropertyFileDictionaryProvider();
		AbstractSelectionList.setDictionaryProvider(MailTypeList.class, provider);

		provider = new PropertyFileDictionaryProvider();
		AbstractSelectionList.setDictionaryProvider(MarketDataRequestTypeList.class, provider);

		provider = new DbStateCodeDictionaryProvider(StateCodeTypeDef.ALL_MANUAL);
		AllManualStateCodeList.setDictionaryProvider(AllManualStateCodeList.class, provider);

		provider = new DbStateCodeDictionaryProvider(StateCodeTypeDef.MANUAL);
		ManualStateCodeList.setDictionaryProvider(ManualStateCodeList.class, provider);

		provider = new DbStateCodeDictionaryProvider(StateCodeTypeDef.SAMPLE);
		ManualSampleStateCodeList.setDictionaryProvider(ManualSampleStateCodeList.class, provider);

		provider = new DbStateCodeDictionaryProvider(StateCodeTypeDef.AUTO);
		AutoStateCodeList.setDictionaryProvider(AutoStateCodeList.class, provider);

		provider = new DbStateCodeDictionaryProvider(StateCodeTypeDef.RECLAMATION);
		ReclamationStateCodeList.setDictionaryProvider(ReclamationStateCodeList.class, provider);

		provider = new DbStateCodeDictionaryProvider(null);
		AllStateCodeList.setDictionaryProvider(AllStateCodeList.class, provider);
	
		provider = new DbMandantDictionaryProvider(userId);
		MandantList.setDictionaryProvider(MandantList.class, provider);

 		provider = new DbSourceSystemDictionaryProvider();
		SourceSystemList.setDictionaryProvider(SourceSystemList.class, provider);

		provider = new DbSourceSystemDictionaryProvider(true);
		AllSourceSystemList.setDictionaryProvider(AllSourceSystemList.class, provider);

 		provider = new DbPriceCheckCategoryDicionaryProvider();
 		PriceCheckCategoryList.setDictionaryProvider(PriceCheckCategoryList.class, provider);
		
		provider = new DbBloombergCurrencyCodeDictionaryProvider();
		BloombergCurrencyCodeList.setDictionaryProvider(BloombergCurrencyCodeList.class, provider);
		
		provider = new DbBloombergMaturityCodeDictionaryProvider();
		BloombergMaturityCodeList.setDictionaryProvider(BloombergMaturityCodeList.class, provider);

		provider = new DbTimePeriodDictionaryProvider(mandantCode);
        MoneyMarketTimePeriodList.setDictionaryProvider(MoneyMarketTimePeriodList.class, provider);
	    	
    }
 
    
    public void setStatusTextAt(int pos, String text) {
    	if (statusBar == null) {
    		return;
    	}
    	String newText = " " + text + " ";
    	statusBar.setTextAt(pos, newText);
    }

   public static void initLog4j(Object object, boolean on) {
      String propertyName = on ? MgbProperty.LOGGING_ON_LOG4J_PROPERTIES : MgbProperty.LOGGING_OFF_LOG4J_PROPERTIES;
	  String	log4jConfigFile = PropertyFactory.getProperty(propertyName);

      if (log4jConfigFile == null) {
        System.out.println("ERROR: Parameter "+ propertyName +" not found in client.properties.");
      } else {
        System.out.println("Taking the file "+log4jConfigFile+" as log4j configuration.");
        try {
	        System.out.println("Loading log4j configuration...");
            Properties log4jProperties = new Properties();
            InputStream in = object.getClass().getResourceAsStream("/" + log4jConfigFile);
            if (in != null) {
	            log4jProperties.load(in);
	 			PropertyConfigurator.configure(log4jProperties);
		        System.out.println("Loaded log4j configuration.");
            } else {
		        System.out.println("ERROR: log4j configuration file "+log4jConfigFile+" not found.");
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
      }
   }

   private void showDefaults() {
    System.out.println("Locale   : "+Locale.getDefault());
    System.out.println("TimeZone : "+TimeZone.getDefault());
   }
   
/** (non-Javadoc)
 * @see de.westlb_systems.xaf.application.AbstractClient#dispose()
 */
@Override
public void dispose() {
	if (keepAliveThread != null) {
		keepAliveThread.interrupt();
	}
	super.dispose();
	SUIManager.reset();
}


}