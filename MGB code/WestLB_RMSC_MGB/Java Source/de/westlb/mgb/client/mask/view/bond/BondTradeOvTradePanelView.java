package de.westlb.mgb.client.mask.view.bond;

import java.awt.Dimension;

import de.westlb.mgb.client.mask.model.bond.BondTradeOvTradePanelModel;
import de.westlb.mgb.client.mask.view.shared.AbstractView;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.client.ui.util.VDateField;
import de.westlb.mgb.client.ui.util.VDecimalField;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SCheckBox;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.STextArea;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;


/**
 * @author M. Boerner
 *
 * View to display a trade overview in equity mandant.
 */
public class BondTradeOvTradePanelView extends AbstractView {
	/**
     * 
     */
    private static final long serialVersionUID = -7741206721984475141L;

    private String RESOURCE_BASE = getResourceBase();

	private static final Dimension descriptionSize = new Dimension(200,40);

	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	private SLabel  lInstrumentName	= new SLabel();
	private SLabel  lVolume			= new SLabel();
	private SLabel  lPrice			= new SLabel();
	private SLabel  lTradeTime		= new SLabel();
	private SLabel  lSmallCustomer	= new SLabel();
	private SLabel  lLateDeal 		= new SLabel();
	private SLabel  lVivaldisTrade	= new SLabel();
	private SLabel  lIsin 			= new SLabel();
	private SLabel  lCurrency	  	= new SLabel();
	private SLabel  lTurnover 		= new SLabel();
	private SLabel  lSystemTime		= new SLabel();
	private SLabel  lCancelation  	= new SLabel();
	private SLabel  lLowTurnOver  	= new SLabel();
	private SLabel  lStructure 		= new SLabel();
	private SLabel  lMaturityTime	= new SLabel();
	private SLabel  lTradeId 		= new SLabel();
	private SLabel  lTraderName		= new SLabel();
	private SLabel  lHighTurnOver	= new SLabel();
	private SLabel	lTradeCategory	= new SLabel();
	private SLabel	lCounterparty	= new SLabel();
	private SLabel	lCounterpartyRef= new SLabel();
	private SLabel	lDescription	= new SLabel();
	private SLabel	lTradeState 	= new SLabel();
	private SLabel	lTraderId 		= new SLabel();
	private SLabel  lSubType 		= new SLabel();
	private SLabel  lBook			= new SLabel();
	private SLabel  lFreeText 		= new SLabel();
    private SLabel  lUpdatedBy      = new SLabel();
    private SLabel  lMarketer       = new SLabel();
    private SLabel  lTraderLocation = new SLabel();
    private SLabel  lReportLocation = new SLabel();

	// UI - Components
	private STextField  tfInstrumentName	= new STextField(DEF_COL_10);
	private STextField  tfVolume 			= new VDecimalField();
	private STextField  tfPrice 			= new VDecimalField(VDecimalField.DEFAULT_COLUMNS,4);
	private STextField  tfTradeTime			= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);
	private STextField  tfIsin 				= new STextField(DEF_COL_20);
	private STextField  tfCurrency			= new STextField(DEF_COL_10);
	private STextField  tfTurnover			= new VDecimalField();
	private STextField  tfSystemTime		= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);
	private STextField  tfStructure 		= new STextField(DEF_COL_10);
	private STextField  tfMaturityTime		= new VDateField(DEF_COL_10, DateFormat.DATE_FORMAT_LONG);
	private STextField  tfTradeId 			= new STextField(DEF_COL_10);
	private STextField	tfTradeCategory		= new STextField(DEF_COL_10);
	private STextField	tfCounterparty		= new STextField(DEF_COL_10);
	private STextField	tfCounterpartyRef	= new STextField(DEF_COL_10);
	private STextArea	taDescription		= new STextArea();
	private STextField	tfTradeState 		= new STextField(DEF_COL_10);
	private STextField	tfTraderId 			= new STextField(DEF_COL_10);
	private STextField  tfTraderName		= new STextField(DEF_COL_10);
	private STextField  tfSubType 			= new STextField(DEF_COL_10);
	private STextField  tfBook				= new STextField(DEF_COL_10);
	private STextField  tfFreeText			= new STextField(DEF_COL_10);
    private STextField  tfUpdatedBy         = new STextField(DEF_COL_10);
    private STextField  tfMarketer          = new STextField(DEF_COL_10);
    private STextField  tfTraderLocation    = new STextField(DEF_COL_10);
    private STextField  tfReportLocation    = new STextField(DEF_COL_10);
	private SCheckBox   ckSmallCustomer		= new SCheckBox();
	private SCheckBox	ckLateDeal 	 		= new SCheckBox();
	private SCheckBox	ckCancelation		= new SCheckBox();
	private SCheckBox	ckLowTurnOver		= new SCheckBox();
	private SCheckBox	ckHighTurnOver		= new SCheckBox();
	private SCheckBox	ckVivaldisTrade		= new SCheckBox();

			
	/**
	 * Constructor for CheckStateView.
	 */
	public BondTradeOvTradePanelView() {
		try {
			initComponents();
			initLabels();
			initLayout();
			initProperties();
		} catch (Exception ex) {
			Debug.log(Debug.ERROR, this, "Exception in View-Constuctor");
			Debug.log(ex);
			ex.printStackTrace();
		}
	}

	/**
	 * Constructor for TradeOvTradePanelView.
	 * @param model
	 */
	public BondTradeOvTradePanelView(Model model) {
		super(model);
	}

	/**
	 * Felder des Views mit Werten aus dem Model fuellen
	 */
	private void fillView() {
		BondTradeOvTradePanelModel model = getTradeOvTradePanelModel();
		if (model == null) {
			return;
		}

		setAttention(tfCounterparty, model.isSparkasse());

		
//		setAttention(tfPrice, model.isPriceToleranceViolation());
//		setAttention(tfSystemTime, model.isLateEntry());
		
		propertyHandler.syncFields();
//		setInvisibleIfNull(SCheckBox.class, propertyHandler, getModel());
	}
	
	private BondTradeOvTradePanelModel getTradeOvTradePanelModel() {
		if (!(getModel() instanceof BondTradeOvTradePanelModel)) {
			return null;
		}
		
		return (BondTradeOvTradePanelModel) getModel();	
	}
	
	/**
	 * Method initComponents.
	 */
	private void initComponents() {
//		this.setBackground(UIManager.getColor("DetailsPanel.background"));
//		this.setOpaque(true);
		ckSmallCustomer.setEditable(false);
		ckLateDeal.setEditable(false); 	 	
		ckCancelation.setEditable(false);	
		ckLowTurnOver.setEditable(false);	
		ckHighTurnOver.setEditable(false);
		ckVivaldisTrade.setEditable(false);
	}

	public void setReadWrite() {
		tfInstrumentName.setEditable(true);
		tfIsin.setEditable(true);
		tfTradeId.setEditable(true);
	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		lInstrumentName.setText	(getResourceString(RESOURCE_BASE + "L_INSTRUMENT_NAME"));
		lVolume.setText			(getResourceString(RESOURCE_BASE + "L_VOLUME"));
		lPrice.setText			(getResourceString(RESOURCE_BASE + "L_PRICE"));
		lTradeTime.setText		(getResourceString(RESOURCE_BASE + "L_TRADE_TIME"));
		lSmallCustomer.setText	(getResourceString(RESOURCE_BASE + "L_SMALL_CUSTOMER"));
		lLateDeal.setText		(getResourceString(RESOURCE_BASE + "L_LATE_DEAL"));
		lIsin.setText			(getResourceString(RESOURCE_BASE + "L_ISIN"));
		lCurrency.setText		(getResourceString(RESOURCE_BASE + "L_CURRENCY"));
		lTurnover.setText		(getResourceString(RESOURCE_BASE + "L_TURNOVER"));
		lSystemTime.setText		(getResourceString(RESOURCE_BASE + "L_SYSTEM_TIME"));
		lCancelation.setText	(getResourceString(RESOURCE_BASE + "L_CANCELATION"));
		lLowTurnOver.setText	(getResourceString(RESOURCE_BASE + "L_LOW_TURNOVER"));
		lStructure.setText		(getResourceString(RESOURCE_BASE + "L_STRUCTURE"));
		lVivaldisTrade.setText	(getResourceString(RESOURCE_BASE + "L_VIVALDIS_TRADE"));
		lMaturityTime.setText	(getResourceString(RESOURCE_BASE + "L_MATURITY_TIME"));
		lTradeId.setText		(getResourceString(RESOURCE_BASE + "L_TRADE_ID"));
		lTraderName.setText		(getResourceString(RESOURCE_BASE + "L_TRADER_NAME"));
		lHighTurnOver.setText	(getResourceString(RESOURCE_BASE + "L_HIGH_TURNOVER"));
		lTradeCategory.setText 	(getResourceString(RESOURCE_BASE + "L_CATEGORY"));
		lCounterparty.setText	(getResourceString(RESOURCE_BASE + "L_COUNTERPARTY"));
		lCounterpartyRef.setText(getResourceString(RESOURCE_BASE + "L_COUNTERPARTY_REF"));
		lDescription.setText	(getResourceString(RESOURCE_BASE + "L_DESCRIPTION"));
		lTradeState.setText 	(getResourceString(RESOURCE_BASE + "L_TRADE_STATE"));
		lTraderId.setText 		(getResourceString(RESOURCE_BASE + "L_TRADER_ID"));
		lSubType.setText		(getResourceString(RESOURCE_BASE + "L_SUB_TYPE"));
		lBook.setText			(getResourceString(RESOURCE_BASE + "L_BOOK"));
		lFreeText.setText		(getResourceString(RESOURCE_BASE + "L_FREE_TEXT"));
        lUpdatedBy.setText      (getResourceString(RESOURCE_BASE + "L_UPDATED_BY"));
        lMarketer.setText       (getResourceString(RESOURCE_BASE + "L_MARKETER"));
        lTraderLocation.setText (getResourceString(RESOURCE_BASE + "L_TRADER_LOCATION"));
        lReportLocation.setText (getResourceString(RESOURCE_BASE + "L_REPORT_LOCATION"));
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayout() {

		taDescription.setMinimumSize(descriptionSize);
		taDescription.setPreferredSize(descriptionSize);

		int col = 0;
		int row = 0;
		

		// Column 0
		add(lInstrumentName,	new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lVolume, 			new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lPrice, 			new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lTradeTime,			new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lSmallCustomer, 	new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lLateDeal,          new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lHighTurnOver,      new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));

		col = 1;
		row = 0;
		// Column 1
		add(tfInstrumentName,	new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfVolume, 			new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfPrice, 			new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfTradeTime,		new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(ckSmallCustomer, 	new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
        add(ckLateDeal,         new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
        add(ckHighTurnOver,     new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));

		col = 2;
		row = 0;
		// Column 2
		add(lIsin, 				new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));		
		add(lCurrency,			new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lTurnover, 			new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lSystemTime,		new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lCancelation,		new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lVivaldisTrade,     new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lLowTurnOver,       new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));

		col = 3;
		row = 0;
		// Column 3
		add(tfIsin, 			new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));	
		add(tfCurrency,			new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));	
		add(tfTurnover,			new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfSystemTime,		new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(ckCancelation,		new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
        add(ckVivaldisTrade,    new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
        add(ckLowTurnOver,      new GridBagConstraints2(row++, col, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));

		col = 4;
		row = 0;
		// Column 4
		add(lStructure, 		new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));		
		add(lMaturityTime,		new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lTradeId, 			new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lTraderName,		new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lMarketer,  		new GridBagConstraints2(row++, col, 3, 1, 1.0, 0.0, WEST, HORIZ, insets8808, 0, 0));
		
		col = 5;
		row = 0;
		// Column 5
		add(tfStructure, 		new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));		
		add(tfMaturityTime,		new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfTradeId, 			new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfTraderName,		new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfMarketer, 		new GridBagConstraints2(row++, col, 3, 1, 1.0, 1.0, WEST, HORIZ, insets4808, 0, 0));

		col = 6;
		row = 0;
		// Column 6
		add(lTradeCategory, 	new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));		
		add(lBook,				new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lTradeState,		new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lTraderId, 			new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lUpdatedBy,			new GridBagConstraints2(row++, col, 3, 1, 1.0, 0.0, WEST, HORIZ, insets8808, 0, 0));
		
		col = 7;
		row = 0;
		// Column 7
		add(tfTradeCategory, 	new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));		
		add(tfBook,				new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfTradeState,		new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfTraderId,			new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfUpdatedBy,		new GridBagConstraints2(row++, col, 3, 1, 1.0, 1.0, WEST, HORIZ, insets4808, 0, 0));

		col = 8;
		row = 0;
		// Column 8
		add(lSubType, 			new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));		
		add(lCounterparty,		new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lFreeText, 			new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lCounterpartyRef,	new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lDescription,		new GridBagConstraints2(row++, col, 3, 1, 1.0, 0.0, WEST, HORIZ, insets8808, 0, 0));
		
		col = 9;
		row = 0;
		// Column 9
		add(tfSubType, 			new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));		
		add(tfCounterparty,		new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfFreeText,			new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfCounterpartyRef,	new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(taDescription,		new GridBagConstraints2(row++, col, 3, 3, 1.0, 1.0, WEST, BOTH, insets4888, 0, 0));

        col = 10;
        row = 0;
        // Column 10
        add(lTraderLocation,    new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));        
        add(lReportLocation,    new GridBagConstraints2(row++, col, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));

        col = 11;
        row = 0;
        // Column 11
        add(tfTraderLocation,   new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4880, 0, 0));     
        add(tfReportLocation,   new GridBagConstraints2(row++, col, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4880, 0, 0));
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
		
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_INSTRUMENT_NAME,	tfInstrumentName, ro);	
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_VOLUME,			tfVolume, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_PRICE,				tfPrice, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_TRADE_TIME,		tfTradeTime, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_ISIN,				tfIsin, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_CURRENCY,			tfCurrency, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_TURNOVER,			tfTurnover, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_SYSTEM_TIME,		tfSystemTime, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_STRUCTURE,			tfStructure, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_MATURITY_TIME,		tfMaturityTime, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_TRADE_ID,			tfTradeId, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_TRADER_NAME,		tfTraderName, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_SUB_TYPE,			tfSubType, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_BOOK,				tfBook, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_FREE_TEXT,			tfFreeText, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_SMALL_CUSTOMER,	ckSmallCustomer, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_VIVALDIS_TRADE,	ckVivaldisTrade, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_LATE_DEAL,			ckLateDeal, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_CANCELATION,		ckCancelation, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_LOW_TURNOVER,		ckLowTurnOver, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_TRADE_CATEGORY,	tfTradeCategory, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_COUNTERPARTY,		tfCounterparty, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_COUNTERPARTY_REF,	tfCounterpartyRef, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_DESCRIPTION,		taDescription, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_TRADE_STATE,		tfTradeState, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_TRADER_ID,			tfTraderId, ro);
		propertyHandler.add(BondTradeOvTradePanelModel.P_BND_HIGH_TURNOVER,		ckHighTurnOver, ro);
        propertyHandler.add(BondTradeOvTradePanelModel.P_BND_UPDATED_BY,        tfUpdatedBy, ro);
        propertyHandler.add(BondTradeOvTradePanelModel.P_BND_MARKETER,          tfMarketer, ro);
        propertyHandler.add(BondTradeOvTradePanelModel.P_BND_TRADER_LOCATION,   tfTraderLocation, ro);
        propertyHandler.add(BondTradeOvTradePanelModel.P_BND_REPORT_LOCATION,   tfReportLocation, ro);
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof BondTradeOvTradePanelModel))
			throw new IllegalArgumentException("Model not instance of BondTradeOvTradePanelModel!");
		
		propertyHandler.setModel(newModel);
		
		super.setModel(newModel);
		fillView();
	}

}
