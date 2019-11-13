package de.westlb.mgb.client.mask.view.shared;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.westlb.mgb.client.mask.model.shared.TradeSearchModel;
import de.westlb.mgb.client.ui.util.VArrayField;
import de.westlb.mgb.client.ui.util.VDateFieldSupportingPopup;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SBorder;
import de.westlb_systems.xaf.swing.SBorderFactory;
import de.westlb_systems.xaf.swing.SCheckBox;
import de.westlb_systems.xaf.swing.SComboBox;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.swing.STabbedPane;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.EditDialog;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author WSY4148
 *
 * Dialog to enter a new manual state of a trade.
 */
public class TradeSearchDialog extends AbstractView implements EditDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6572580471262205843L;

	/* Constant definitions in UserEvents */
	public static final int TRADELIST    = 1;

	public static final int JOB_SEARCH_MODE    = 0;
	public static final int DATE_INTERVAL_SEARCH_MODE    = 1;
	public static final int TRADE_ID_SEARCH_MODE    = 2;

	private String RESOURCE_BASE = getResourceBase();

	/** gui components */
	private STabbedPane tbSearchTrade = new STabbedPane();

	private SPanel pJobSelection				= new SPanel();
	private SPanel pTradeSelection				= new SPanel();
	private SPanel pDateIntervalSelection		= new SPanel();
	
    private SLabel lTradeIds                            = new SLabel();
    private SLabel lInstruments1                        = new SLabel();
    private SLabel lInstruments2                        = new SLabel();
	private SLabel lJobIds								= new SLabel();
	private SLabel lFromDate							= new SLabel();
	private SLabel lToDate								= new SLabel();
	private SLabel lStateCode1							= new SLabel();
	private SLabel lIsManualCheckRequired1				= new SLabel();
	private SLabel lIsManualCheckRequiredNotHandled1	= new SLabel();
	private SLabel lIsMarketDataRequestRequired1		= new SLabel();
	private SLabel lIsSmallCustomer1					= new SLabel();
	private SLabel lIsStorno1							= new SLabel();
	private SLabel lIsLateEntry1						= new SLabel();
	private SLabel lHasPreDecessor1						= new SLabel();
	private SLabel lHasLowTurnover1						= new SLabel();
	private SLabel lStateCode2							= new SLabel();
	private SLabel lIsManualCheckRequired2				= new SLabel();
	private SLabel lIsManualCheckRequiredNotHandled2	= new SLabel();
	private SLabel lIsMarketDataRequestRequired2		= new SLabel();
	private SLabel lIsSmallCustomer2					= new SLabel();
	private SLabel lIsStorno2							= new SLabel();
	private SLabel lIsLateEntry2						= new SLabel();
	private SLabel lHasPreDecessor2						= new SLabel();
	private SLabel lHasLowTurnover2						= new SLabel();
	
    private VArrayField tfIstruments1                       = new VArrayField(DEF_COL_20,",");
    private VArrayField tfIstruments2                       = new VArrayField(DEF_COL_20,",");
    private VArrayField tfTradeIds                          = new VArrayField(DEF_COL_20,",");
	private VArrayField	tfJobIds							= new VArrayField(DEF_COL_20,",",true);
//	private VDateField	tfFromDate							= new VDateField();
//	private VDateField	tfToDate							= new VDateField();
	private VDateFieldSupportingPopup	tfFromDate		= new VDateFieldSupportingPopup();
	private VDateFieldSupportingPopup	tfToDate		= new VDateFieldSupportingPopup();
	private SComboBox	cbStateCode1						= new SComboBox();
	private SCheckBox	ckIsManualCheckRequired1			= new SCheckBox();
	private SCheckBox	ckIsManualCheckRequiredNotHandled1	= new SCheckBox();
	private SCheckBox	ckIsMarketDataRequestRequired1		= new SCheckBox();
	private SCheckBox	ckIsStorno1							= new SCheckBox();
	private SCheckBox	ckIsSmallCustomer1					= new SCheckBox();
	private SCheckBox	ckIsLateEntry1						= new SCheckBox();
	private SCheckBox 	ckHasPreDecessor1					= new SCheckBox();
	private SCheckBox	ckHasLowTurnover1					= new SCheckBox();
	private SComboBox	cbStateCode2						= new SComboBox();
	private SCheckBox	ckIsManualCheckRequired2			= new SCheckBox();
	private SCheckBox	ckIsManualCheckRequiredNotHandled2	= new SCheckBox();
	private SCheckBox	ckIsMarketDataRequestRequired2		= new SCheckBox();
	private SCheckBox	ckIsStorno2							= new SCheckBox();
	private SCheckBox	ckIsSmallCustomer2					= new SCheckBox();
	private SCheckBox	ckIsLateEntry2						= new SCheckBox();
	private SCheckBox 	ckHasPreDecessor2					= new SCheckBox();
	private SCheckBox	ckHasLowTurnover2					= new SCheckBox();
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	/** Listener fuer alle Events */
	private Listener listener = new Listener();
	
	/**
	 * Listener fuer alle Events
	 */
	private static class Listener implements ActionListener, PropertyChangeListener {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
        }
 
        /**
         * @see java.beans.PropertyChangeListener#propertyChange(PropertyChangeEvent)
         */
        @Override
        public void propertyChange(PropertyChangeEvent event) {
       }
	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public TradeSearchDialog() {
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
	public TradeSearchDialog(Model model) {
		this();
		setModel(model);
	}

    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getTradeSearchModel() == null) {
            return;
        }
        propertyHandler.syncFields();
    }
       
    /**
	 * Return casted model
	 * 
	 */
	public TradeSearchModel getTradeSearchModel() {
		return getModel() instanceof TradeSearchModel ? (TradeSearchModel)getModel() : null;
	}

    /**
     * Method initProperties.
     */
    private void initProperties() {
        propertyHandler.add(TradeSearchModel.P_TRADE_IDS,                           tfTradeIds);                        
        propertyHandler.add(TradeSearchModel.P_INSTRUMENTS,                         tfIstruments1);                        
        propertyHandler.add(TradeSearchModel.P_INSTRUMENTS,                         tfIstruments2);                        
		propertyHandler.add(TradeSearchModel.P_JOB_IDS,		   						tfJobIds);							
		propertyHandler.add(TradeSearchModel.P_FROM_DATE,							tfFromDate);						
		propertyHandler.add(TradeSearchModel.P_TO_DATE,		   						tfToDate);
		propertyHandler.add(TradeSearchModel.P_STATE_CODE_CB_MODEL1,				cbStateCode1);						
		propertyHandler.add(TradeSearchModel.P_STATE_CODE,							cbStateCode1);						
		propertyHandler.add(TradeSearchModel.P_IS_MANUALCHECK_REQUIRED, 			ckIsManualCheckRequired1);
		propertyHandler.add(TradeSearchModel.P_IS_MANUALCHECK_REQUIRED_NOTHANDLED,	ckIsManualCheckRequiredNotHandled1);
		propertyHandler.add(TradeSearchModel.P_IS_MARKETDATA_REQUEST_REQUIRED,		ckIsMarketDataRequestRequired1);
		propertyHandler.add(TradeSearchModel.P_IS_SMALL_CUSTOMER,	 				ckIsSmallCustomer1);
		propertyHandler.add(TradeSearchModel.P_IS_STORNO,							ckIsStorno1);
		propertyHandler.add(TradeSearchModel.P_IS_LATE_ENTRY,		 				ckIsLateEntry1);
		propertyHandler.add(TradeSearchModel.P_HAS_PRE_DECESSOR,		 			ckHasPreDecessor1);
		propertyHandler.add(TradeSearchModel.P_HAS_LOW_TURNOVER,		 			ckHasLowTurnover1);
		propertyHandler.add(TradeSearchModel.P_STATE_CODE_CB_MODEL2,				cbStateCode2);						
		propertyHandler.add(TradeSearchModel.P_STATE_CODE,							cbStateCode2);						
		propertyHandler.add(TradeSearchModel.P_IS_MANUALCHECK_REQUIRED, 			ckIsManualCheckRequired2);
		propertyHandler.add(TradeSearchModel.P_IS_MANUALCHECK_REQUIRED_NOTHANDLED,	ckIsManualCheckRequiredNotHandled2);
		propertyHandler.add(TradeSearchModel.P_IS_MARKETDATA_REQUEST_REQUIRED,		ckIsMarketDataRequestRequired2);
		propertyHandler.add(TradeSearchModel.P_IS_SMALL_CUSTOMER,	 				ckIsSmallCustomer2);
		propertyHandler.add(TradeSearchModel.P_IS_STORNO,							ckIsStorno2);
		propertyHandler.add(TradeSearchModel.P_IS_LATE_ENTRY,		 				ckIsLateEntry2);
		propertyHandler.add(TradeSearchModel.P_HAS_PRE_DECESSOR,		 			ckHasPreDecessor2);
		propertyHandler.add(TradeSearchModel.P_HAS_LOW_TURNOVER,		 			ckHasLowTurnover2);
   }

    /**
     * Method initLabels.
     */
    private void initLabels() {
       	tbSearchTrade.setTitleAt(JOB_SEARCH_MODE,getResourceString(RESOURCE_BASE + "T_JOB_SELECTION"));
       	tbSearchTrade.setTitleAt(DATE_INTERVAL_SEARCH_MODE,getResourceString(RESOURCE_BASE + "T_DATE_INTERVAL_SELECTION"));
       	tbSearchTrade.setTitleAt(TRADE_ID_SEARCH_MODE,getResourceString(RESOURCE_BASE + "T_TRADE_SELECTION"));

        lTradeIds.setText                           (getResourceString(RESOURCE_BASE + "L_TRADE_IDS"));
        lInstruments1.setText                       (getResourceString(RESOURCE_BASE + "L_INSTRUMENTS"));
        lInstruments2.setText                       (getResourceString(RESOURCE_BASE + "L_INSTRUMENTS"));
    	lJobIds.setText								(getResourceString(RESOURCE_BASE + "L_JOB_IDS"));
    	lFromDate.setText							(getResourceString(RESOURCE_BASE + "L_FROM_DATE"));
    	lToDate.setText								(getResourceString(RESOURCE_BASE + "L_TO_DATE"));
    	lStateCode1.setText							(getResourceString(RESOURCE_BASE + "L_STATE_CODE"));
    	lIsManualCheckRequired1.setText				(getResourceString(RESOURCE_BASE + "L_IS_MANUALCHECK_REQUIRED"));
		lIsManualCheckRequiredNotHandled1.setText	(getResourceString(RESOURCE_BASE + "L_IS_MANUALCHECK_REQUIRED_NOTHANDLED"));
    	lIsMarketDataRequestRequired1.setText		(getResourceString(RESOURCE_BASE + "L_IS_MARKETDATA_REQUEST_REQUIRED"));
    	lIsStorno1.setText							(getResourceString(RESOURCE_BASE + "L_IS_SMALL_CUSTOMER"));
    	lIsSmallCustomer1.setText					(getResourceString(RESOURCE_BASE + "L_IS_STORNO"));
    	lIsLateEntry1.setText						(getResourceString(RESOURCE_BASE + "L_IS_LATE_ENTRY"));
    	lHasPreDecessor1.setText					(getResourceString(RESOURCE_BASE + "L_HAS_PRE_DECESSOR"));
    	lHasLowTurnover1.setText					(getResourceString(RESOURCE_BASE + "L_HAS_LOW_TURNOVER"));
    	lStateCode2.setText							(getResourceString(RESOURCE_BASE + "L_STATE_CODE"));
    	lIsManualCheckRequired2.setText				(getResourceString(RESOURCE_BASE + "L_IS_MANUALCHECK_REQUIRED"));
		lIsManualCheckRequiredNotHandled2.setText	(getResourceString(RESOURCE_BASE + "L_IS_MANUALCHECK_REQUIRED_NOTHANDLED"));
    	lIsMarketDataRequestRequired2.setText		(getResourceString(RESOURCE_BASE + "L_IS_MARKETDATA_REQUEST_REQUIRED"));
    	lIsStorno2.setText							(getResourceString(RESOURCE_BASE + "L_IS_SMALL_CUSTOMER"));
    	lIsSmallCustomer2.setText					(getResourceString(RESOURCE_BASE + "L_IS_STORNO"));
    	lIsLateEntry2.setText						(getResourceString(RESOURCE_BASE + "L_IS_LATE_ENTRY"));
    	lHasPreDecessor2.setText					(getResourceString(RESOURCE_BASE + "L_HAS_PRE_DECESSOR"));
    	lHasLowTurnover2.setText					(getResourceString(RESOURCE_BASE + "L_HAS_LOW_TURNOVER"));
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		SBorder emptyBorder = SBorderFactory.createEmptyBorder(insets4088);
		
		tbSearchTrade.addTab(null, pJobSelection);
		tbSearchTrade.addTab(null, pDateIntervalSelection);
		tbSearchTrade.addTab(null, pTradeSelection);
		tbSearchTrade.setSelectedIndex(JOB_SEARCH_MODE);

		pJobSelection.setBorder(emptyBorder);
		pTradeSelection.setBorder(emptyBorder);
		pDateIntervalSelection.setBorder(emptyBorder);

		int l, c;

		SPanel jobPanel = new SPanel();
		pJobSelection.add(jobPanel,						new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));
		l = 0; c = 0; // Column 0
	 	jobPanel.add(lJobIds,							new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
        jobPanel.add(lStateCode1,                       new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));       
        jobPanel.add(lInstruments1,                     new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));       
	 	jobPanel.add(lIsManualCheckRequired1,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	jobPanel.add(lIsManualCheckRequiredNotHandled1,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	jobPanel.add(lIsMarketDataRequestRequired1,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		jobPanel.add(lIsStorno1,						new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 		 	
	 	jobPanel.add(lIsSmallCustomer1,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	jobPanel.add(lIsLateEntry1,						new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	jobPanel.add(lHasPreDecessor1,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	jobPanel.add(lHasLowTurnover1,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
		l = 0; c = 1; // Column 1
	 	jobPanel.add(tfJobIds,							new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	jobPanel.add(cbStateCode1,						new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	jobPanel.add(tfIstruments1,                     new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));       
	 	jobPanel.add(ckIsManualCheckRequired1,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	jobPanel.add(ckIsManualCheckRequiredNotHandled1,new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	jobPanel.add(ckIsMarketDataRequestRequired1,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
		jobPanel.add(ckIsStorno1,						new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	jobPanel.add(ckIsSmallCustomer1,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	jobPanel.add(ckIsLateEntry1,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	jobPanel.add(ckHasPreDecessor1,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	jobPanel.add(ckHasLowTurnover1,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	

		SPanel datePanel = new SPanel();
		pDateIntervalSelection.add(datePanel,			new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));
		l = 0; c = 0; // Column 0
	 	datePanel.add(lFromDate,						new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	datePanel.add(lToDate,							new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	datePanel.add(lStateCode2,						new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	datePanel.add(lInstruments2,                    new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));       
	 	datePanel.add(lIsManualCheckRequired2,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	datePanel.add(lIsManualCheckRequiredNotHandled2,new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	datePanel.add(lIsMarketDataRequestRequired2,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		datePanel.add(lIsStorno2,						new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 		 	
	 	datePanel.add(lIsSmallCustomer2,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	datePanel.add(lIsLateEntry2,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	datePanel.add(lHasPreDecessor2,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	datePanel.add(lHasLowTurnover2,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
		l = 0; c = 1; // Column 1
	 	datePanel.add(tfFromDate,						new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	datePanel.add(tfToDate,							new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	datePanel.add(cbStateCode2,						new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
        datePanel.add(tfIstruments2,                    new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));       
	 	datePanel.add(ckIsManualCheckRequired2,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	datePanel.add(ckIsManualCheckRequiredNotHandled2,new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	datePanel.add(ckIsMarketDataRequestRequired2,	new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
		datePanel.add(ckIsStorno2,						new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	datePanel.add(ckIsSmallCustomer2,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	datePanel.add(ckIsLateEntry2,					new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	datePanel.add(ckHasPreDecessor2,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
	 	datePanel.add(ckHasLowTurnover2,				new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	

		SPanel tradePanel = new SPanel();
		pTradeSelection.add(tradePanel,					new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));
		l = 0; c = 0; // Column 0
	 	tradePanel.add(lTradeIds,						new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	
		l = 0; c = 1; // Column 1
	 	tradePanel.add(tfTradeIds,						new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	 	

		this.add(tbSearchTrade,    						new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));
    }

    /**
     * Method initComponents.
     */
    private void initComponents() {    	
    }
    
	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
        if (!(newModel instanceof TradeSearchModel)) {
            throw new IllegalArgumentException("Model not instance of TradeSearchModel");
        }
        propertyHandler.setModel(newModel);
        if (getModel() != null)
            getModel().removePropertyChangeListener(listener);
        super.setModel(newModel);
        if (getModel() != null)
            getModel().addPropertyChangeListener(listener);

        fillView();
	}
	
	/**
	 * Updates all language dependend labels
	 */
	@Override
    public void updateLabels() {
		initLabels();
	}

	@Override
    public boolean cancelData() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see de.westlb_systems.xaf.application.ViewContent#saveData()
	 */
	@Override
    public boolean saveData() {
		getTradeSearchModel().setSearchMode(getSearchMode());
		boolean saved = super.saveData(); 
		fireUserEvent(new UserEvent(this, TRADELIST, getTradeSearchModel().getTradeSearchParamsVo()));
		return saved;
	}
	
	public int getSearchMode() {
		if (tbSearchTrade != null) {
			return tbSearchTrade.getSelectedIndex();
		}
        return -1;
	}
}
