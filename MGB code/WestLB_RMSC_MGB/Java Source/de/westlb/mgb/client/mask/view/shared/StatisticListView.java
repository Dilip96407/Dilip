package de.westlb.mgb.client.mask.view.shared;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.UIManager;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.mask.model.shared.StatisticListModel;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.MgbSessionSingleton;
import de.westlb.mgb.client.server.vo.MandantVo;
import de.westlb.mgb.model.definition.MandantDef;
import de.westlb.mgb.model.definition.StatisticReportNameDef;
import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.application.event.LogMessageEvent;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SMenuItem;
import de.westlb_systems.xaf.swing.SPopupMenu;
import de.westlb_systems.xaf.swing.SPopupProvider;
import de.westlb_systems.xaf.swing.STable;
import de.westlb_systems.xaf.swing.STableSelectionEvent;
import de.westlb_systems.xaf.swing.STableSelectionListener;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.ui.view.TablePanel;
import de.westlb_systems.xaf.ui.view.TitlePanel;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author WSY4148
 *
 */
public class StatisticListView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1033833075944854011L;

	private static Logger log = Logger.getLogger(StatisticListView.class);
	 
	private String RESOURCE_BASE = getResourceBase();
	// Contains a map from menu items to successor report names
	private HashMap<SMenuItem, String> successorReportNames; 

	public final static int EV_DRILL_DOWN		= 1;
	public final static int EV_SHOW_TRADES		= 2;
	
	/** gui components */
	private TitlePanel pTitle = new TitlePanel();
	private TablePanel tpResultPanel	= new TablePanel();
	private STable tResult				= new STable();
	private SMenuItem pItemShowTrades	= new SMenuItem();
	private SMenuItem pItemFormat       = new SMenuItem();

	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	/** Listener fuer alle Events */
	private Listener listener = new Listener();
	
	/**
	 * Listener fuer alle Events
	 */
	private class Listener implements STableSelectionListener, ActionListener {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
		@Override
        public void actionPerformed(ActionEvent ae) {
			Object source = ae.getSource();
			if (source == pItemShowTrades) {
				doShowTrades(tResult.getSelectedRow());
			}
			else if(source == pItemFormat)
			{
			    doSwitchTableFormat();
			} else if (successorReportNames.containsKey(source)) {
				doDrillDown(tResult.getSelectedRow(), successorReportNames.get(source));
			}
		}

		/**
         * @see de.westlb_systems.xaf.swing.STableSelectionListener#selectionChanged(STableSelectionEvent)
         */
        @Override
        public void selectionChanged(STableSelectionEvent e) {
			if (e.isDoubleClicked()) {
				doDrillDownOrShowTrades(e.getRow());
			}
        }
	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public StatisticListView() {
		try {
			initComponents();
			initLayout();
			initLabels();
			initProperties();
		} catch (Exception ex) {
			Debug.log(Debug.ERROR, this, "Exception in View-Constuctor");
			Debug.log(ex);
			ex.printStackTrace();
		}
	}
	
	/**
	 * Constructor which sets the model
	 */
	public StatisticListView(Model model) {
		this();
		setModel(model);
	}
	
	/**
	 * Performs a drill down if successor report exists or shows trade list if  no
	 * successor exists.
	 * 
	 * @param row
	 * @return true, if action succeeded.
	 */
	private boolean doDrillDownOrShowTrades(int row) {
		if (row == -1 )  {
			logMessage(new LogMessageEvent(this, LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_NOTHING_SELECTED")));
			return false;
		}	
		
		StatisticListModel model = getStatisticListModel();
		if (model == null) {
			return false;		
		}	
		
		String firstSuccessorReport = getStatisticListModel().getFirstSuccessorReportName();
		if (firstSuccessorReport != null && !model.isPredecessorReport(firstSuccessorReport)) {
			return doDrillDown(row, model.getFirstSuccessorReportName());
		}
        return doShowTrades(row);
	}

	/**
	 * 
	 */
	private boolean doDrillDown(int row, String reportName) {
		if (row == -1 )  {
			logMessage(new LogMessageEvent(this, LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_NOTHING_SELECTED")));
			return false;
		}			
		if (getStatisticListModel() == null) {
			return false;		
		}
				
		fireUserEvent(EV_DRILL_DOWN, getStatisticListModel().getSuccessorParams(row, reportName));
        return true;
	}
	
	/**
	 * @param i
	 */
	private boolean doShowTrades(int row) {
		if (row == -1 )  {
			logMessage(new LogMessageEvent(this, LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_NOTHING_SELECTED")));
			return false;
		}
		if (getStatisticListModel() == null) {
			return false;		
		}
		
		Object trades = getStatisticListModel().getSuccessorParams(row, null);

		fireUserEvent(EV_SHOW_TRADES, trades);
		return true;
	}
	
    public void doSwitchTableFormat() {
        StatisticListModel model = getStatisticListModel();
        if (model == null) {
            return;
        }
        
        int newFormat;
        if (model.getTableFormat() == StatisticListModel.TABLE_FORMAT_CUBE) {
            newFormat = StatisticListModel.TABLE_FORMAT_REGULAR;
            pItemFormat.setText(getResourceString(RESOURCE_BASE + "M_CUBE_FORMAT"));
            setPopupActionEntriesEnabled(true);
        } else {
            newFormat = StatisticListModel.TABLE_FORMAT_CUBE; 
            pItemFormat.setText(getResourceString(RESOURCE_BASE + "M_REGULAR_FORMAT"));
            setPopupActionEntriesEnabled(false);
        }
        boolean oldC = Synchronizer.setWaitCursor(true, this);
        model.setTableFormat(newFormat);
        Synchronizer.setWaitCursor(oldC, this);
    }

    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getStatisticListModel() == null) {
            return;
        }
        propertyHandler.syncFields();
        
        // Update title
        String newTitle = getStatisticListModel().getTitle();
        setTitle(newTitle);
        pTitle.setHeaderTitel(newTitle);
    }
    
    /**
	 * Return casted model
	 * 
	 */
	public StatisticListModel getStatisticListModel() {
		return getModel() instanceof StatisticListModel ? (StatisticListModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(StatisticListModel.P_STATISTIC_TABLE_MODEL, 	tResult);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
		pTitle.setHeaderTitel	(getResourceString(RESOURCE_BASE + "T_001"));
		tpResultPanel.setText	(getResourceString(RESOURCE_BASE + "T_RESULT"));
		pItemShowTrades.setText	(getResourceString(RESOURCE_BASE + "M_SHOW_TRADES"));
        pItemFormat.setText     (getResourceString(RESOURCE_BASE + "M_CUBE_FORMAT"));		
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;
					
		// Layout total view
		l = 0; c = 0; 
	 	this.add(pTitle,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));	 	
	 	this.add(tpResultPanel,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 1.0, CENTER, BOTH, insets8888, 0, 0));	 	

		setBackgroundForAllPanels(UIManager.getColor("DetailsPanel.background"));

    }	
    
    
    private void addAll(SPopupMenu menu, Collection<SMenuItem> menuItems) {
    	Iterator<SMenuItem> it = menuItems.iterator();
    	while (it.hasNext()) {
    		menu.add(it.next());
    	}
    }
    
    /**
     * Enables or disables the "Drill Down" and "Successor Report" menu entries.
     * That's because the cube view doesn't support those features at the
     * moment, so they shouldn't be enabled when it's switched on. (In fact, the
     * cube view wouldn't give an error but apply them to the selected
     * instrument regardless of selected state, so it would be a bit irritiating
     * to leave it enabled.)
     */
    private void setPopupActionEntriesEnabled(boolean enable)
    {
        pItemShowTrades.setEnabled(enable);
        for(SMenuItem it : getSuccessorMenuItems())
        {
            it.setEnabled(enable);
        }
    }

    /**
     * Method initComponents.
     */
    private void initComponents() {
    	
		pTitle.setHeaderTitleColor(UIManager.getColor("StateCodeList.foreground"));
		pTitle.setColorProgress(
			UIManager.getColor("StateCodeList.titleBackground1"), 
			UIManager.getColor("StateCodeList.titleBackground2"));
		
		tResult.setSelectionMode(STable.MULTIPLE_SELECTION);
		tpResultPanel.setTable(tResult);
		tResult.addSTableSelectionListener(listener);
		tResult.setGridEnabled(true);
    }
    
    private void initContextMenu() {
		successorReportNames = new HashMap<SMenuItem, String>();
		// Create the menu items for the successor reports
		createSuccessorMenuItems();

		pItemShowTrades.addActionListener(listener);
		pItemFormat.addActionListener(listener);

		SPopupProvider provider = tResult.getPopupProvider();
		SPopupMenu menu = provider.getPopupMenu();
		Component[] items = menu.getComponents();	
		menu.removeAll();	
		menu.add(pItemShowTrades);
		
		if (!(getSuccessorMenuItems().isEmpty())) {
			menu.addSeparator();
			addAll(menu, getSuccessorMenuItems());
		}
		menu.addSeparator();
        menu.add(pItemFormat);
        menu.addSeparator();
		
		if (items!=null) {
			for (int i=0;i<items.length;i++) {
				menu.add(items[i]);
			}
		}
    }
    
    private void createSuccessorMenuItems() {
		StatisticListModel model = getStatisticListModel();
		if (model == null || model.getSuccessorReportNames() == null) {
			return; 
		}
		
		// Create a SMenuItem for every successor report and store
		// the report name for the SMenuItem in a HashMap
		for (int i=0; i < model.getSuccessorReportNames().length; i++) {
			String reportName = model.getSuccessorReportNames()[i];
			
			try {

				String product = null;
				MandantVo[] mandants = MgbServiceFactory.getService().findAllMandants();
	            for (MandantVo mandant : mandants) {
	            	if (MgbSessionSingleton.getMandantCode().equals(mandant.getMandantCode()))
	            	{
	            		product = mandant.getProductClass();
	            	}
	            }

				// Do not provide trade type report in Bond client
				if (MandantDef.PRODUCT_BOND.equals(product)) {
					if (StatisticReportNameDef.TRADE_TYPE.equals(reportName)) {
						continue;
					}
				} 
			} catch (RemoteException e) {
				log.error("Could not determine if we are in bond client", e);
			}
			
			SMenuItem item = new SMenuItem();
			item.addActionListener(listener);
			item.setText(getResourceString(RESOURCE_BASE + StatisticListModel.REPORT_PREFIX + reportName));
			successorReportNames.put(item, reportName);
		}
    }
    
    private Collection<SMenuItem> getSuccessorMenuItems() {
    	return successorReportNames.keySet();
    }
    

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof StatisticListModel)) {
	        throw new IllegalArgumentException("Model not instance of StatisticListModel");
	    }
	    propertyHandler.setModel(newModel);
	    super.setModel(newModel);
	    fillView();
	    initContextMenu();
        if (getStatisticListModel() != null && getStatisticListModel().getTableFormat() == StatisticListModel.TABLE_FORMAT_CUBE) {
            pItemFormat.setText(getResourceString(RESOURCE_BASE + "M_REGULAR_FORMAT"));
            setPopupActionEntriesEnabled(false);
        } else {
            pItemFormat.setText(getResourceString(RESOURCE_BASE + "M_CUBE_FORMAT"));
            setPopupActionEntriesEnabled(true);
        }
	}

     
     /**
      * Update the current content
      */
    @Override
    public void refresh() {
        fillView();
    }	
	
	/**
	 * Updates all language dependend labels
	 */
	@Override
    public void updateLabels() {
		initLabels();
	}

}
