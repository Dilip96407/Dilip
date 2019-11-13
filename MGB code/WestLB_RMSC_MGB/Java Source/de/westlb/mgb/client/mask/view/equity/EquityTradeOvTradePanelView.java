package de.westlb.mgb.client.mask.view.equity;

import de.westlb.mgb.client.mask.model.equity.EquityTradeOvTradePanelModel;
import de.westlb.mgb.client.mask.view.shared.AbstractView;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.client.ui.util.VDateField;
import de.westlb.mgb.client.ui.util.VDecimalField;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SCheckBox;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;


/**
 * @author M. Boerner
 *
 * View to display a trade overview in equity mandant.
 */
public class EquityTradeOvTradePanelView extends AbstractView {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String RESOURCE_BASE = getResourceBase();

	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	// UI - Components
	private SLabel lInstrument = new SLabel();
	private SLabel lPrice = new SLabel();
	private SLabel lVolume = new SLabel();
	private SLabel lCurrency = new SLabel();
	private SLabel lExchange = new SLabel();
	private SLabel lNet = new SLabel();
	private SLabel lInternal = new SLabel();
	private SLabel lAutomatic = new SLabel();
	private SLabel lBuy = new SLabel();
	private SLabel lIsin = new SLabel();
	private SLabel lTradeId = new SLabel();
	private SLabel lSettlementTime = new SLabel();
	private SLabel lSystemTime = new SLabel();
    private SLabel lTraderId = new SLabel();
    private SLabel lTraderName = new SLabel();
	private SLabel lStorno = new SLabel();
	private SLabel lMischPrice = new SLabel();
    private SLabel lTradeTime = new SLabel();
    private SLabel lBook = new SLabel();
    private SLabel lTurnOver = new SLabel();
    private SLabel lReferenceTrade = new SLabel();
    private SLabel lFreeText = new SLabel();
    private SLabel lCounterparty = new SLabel();
    private SLabel lContractSize = new SLabel();
    private SLabel lQuoteType = new SLabel();
    private SLabel lFxRate = new SLabel();
    private SLabel  lTraderLocation = new SLabel();
    private SLabel  lReportLocation = new SLabel();

	// UI - Components
	private STextField	tfInstrument	= new STextField(DEF_COL_10);
	private STextField	tfPrice 		= new VDecimalField();
	private STextField	tfVolume		= new VDecimalField();
	private STextField	tfCurrency 		= new STextField(DEF_COL_08);
	private STextField	tfExchange		= new STextField(DEF_COL_08);
	private SCheckBox	ckNet 			= new SCheckBox();
	private SCheckBox	ckInternal 		= new SCheckBox();
	private SCheckBox	ckAutomatic 	= new SCheckBox();
	private SCheckBox	ckBuy		 	= new SCheckBox();
	private STextField	tfIsin 			= new STextField(DEF_COL_10);
    private STextField  tfTradeId       = new STextField(DEF_COL_10);
	private STextField	tfSettlementTime= new VDateField(DEF_COL_10, DateFormat.DATE_FORMAT_LONG);
	private STextField	tfSystemTime	= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);
    private STextField  tfTraderId      = new STextField(DEF_COL_08);
    private STextField  tfTraderName    = new STextField(DEF_COL_08);
	private SCheckBox	ckStorno		= new SCheckBox();
	private SCheckBox	ckMischPrice	= new SCheckBox();
	private STextField	tfTradeTime		= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);
    private STextField  tfBook          = new STextField(DEF_COL_10);
    private STextField  tfTurnOver      = new VDecimalField();
    private STextField  tfReferenceTrade= new STextField(DEF_COL_10);
    private STextField  tfFreeText      = new STextField(DEF_COL_10);
    private STextField  tfCounterparty  = new STextField(DEF_COL_10);
    private STextField  tfContractSize  = new VDecimalField();
    private STextField  tfQuoteType     = new STextField(DEF_COL_10);
    private STextField  tfFxRate        = new VDecimalField();
    private STextField  tfTraderLocation    = new STextField(DEF_COL_10);
    private STextField  tfReportLocation    = new STextField(DEF_COL_10);
			
	/**
	 * Constructor for CheckStateView.
	 */
	public EquityTradeOvTradePanelView() {
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
	public EquityTradeOvTradePanelView(Model model) {
		super(model);
	}

	/**
	 * Felder des Views mit Werten aus dem Model fuellen
	 */
	private void fillView() {
		EquityTradeOvTradePanelModel model = getTradeOvTradePanelModel();
		if (model == null) {
			return;
		}
		
		setAttention(tfPrice, model.isPriceToleranceViolation());
		setAttention(tfSystemTime, model.isLateEntry());
		
		propertyHandler.syncFields();
		setInvisibleIfNull(SCheckBox.class, propertyHandler, getModel());
	}
	
	private EquityTradeOvTradePanelModel getTradeOvTradePanelModel() {
		if (!(getModel() instanceof EquityTradeOvTradePanelModel)) {
			return null;
		}
		
		return (EquityTradeOvTradePanelModel) getModel();	
	}
	
	/**
	 * Method initComponents.
	 */
	private void initComponents() {
//		this.setBackground(UIManager.getColor("DetailsPanel.background"));
//		this.setOpaque(true);
		ckAutomatic.setEditable(false);		
		ckInternal.setEditable(false);
		ckNet.setEditable(false);
		ckMischPrice.setEditable(false);		
		ckStorno.setEditable(false);		
		ckBuy.setEditable(false);		
	}

	public void setReadWrite() {
		tfInstrument.setEditable(true);
		tfIsin.setEditable(true);
		tfTradeId.setEditable(true);
		tfPrice.setEditable(true);
		tfVolume.setEditable(true);
		tfSettlementTime.setEditable(true);
		tfSystemTime.setEditable(true);
        tfTraderId.setEditable(true);
        tfTraderName.setEditable(true);
		tfTradeTime.setEditable(true);
		tfBook.setEditable(true);
		tfReferenceTrade.setEditable(true);
		tfFreeText.setEditable(true);
		tfCounterparty.setEditable(true);
		tfContractSize.setEditable(true);
        tfQuoteType.setEditable(true);
        tfFxRate.setEditable(true);
	}
	
	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		lInstrument.setText	(getResourceString(RESOURCE_BASE + "L_INSTRUMENT"));
		lPrice.setText		(getResourceString(RESOURCE_BASE + "L_PRICE"));
		lVolume.setText		(getResourceString(RESOURCE_BASE + "L_VOLUME"));
		lCurrency.setText	(getResourceString(RESOURCE_BASE + "L_CURRENCY"));
		lExchange.setText	(getResourceString(RESOURCE_BASE + "L_EXCHANGE"));
		lNet.setText		(getResourceString(RESOURCE_BASE + "L_NETTO"));
		lInternal.setText	(getResourceString(RESOURCE_BASE + "L_INTERNAL"));
		lAutomatic.setText	(getResourceString(RESOURCE_BASE + "L_AUTOMATIC"));
		lIsin.setText		(getResourceString(RESOURCE_BASE + "L_ISIN"));
		lBuy.setText		(getResourceString(RESOURCE_BASE + "L_BUY"));
		lSettlementTime.setText(getResourceString(RESOURCE_BASE + "L_SETTLEMENT_TIME"));
		lSystemTime.setText	(getResourceString(RESOURCE_BASE + "L_SYSTEM_TIME"));
        lTraderId.setText   (getResourceString(RESOURCE_BASE + "L_TRADER_ID"));
        lTraderName.setText (getResourceString(RESOURCE_BASE + "L_TRADER_NAME"));
		lStorno.setText		(getResourceString(RESOURCE_BASE + "L_STORNO"));
		lMischPrice.setText	(getResourceString(RESOURCE_BASE + "L_MISCH_PRICE"));
		lTradeId.setText	(getResourceString(RESOURCE_BASE + "L_TRADE_ID"));
        lTradeTime.setText  (getResourceString(RESOURCE_BASE + "L_TRADE_TIME"));
        lBook.setText  (getResourceString(RESOURCE_BASE + "L_BOOK"));
        lReferenceTrade.setText  (getResourceString(RESOURCE_BASE + "L_REFERENCE_TRADE"));
        lFreeText.setText  (getResourceString(RESOURCE_BASE + "L_FREE_TEXT"));
        lTurnOver.setText  (getResourceString(RESOURCE_BASE + "L_TURNOVER"));
        lCounterparty.setText  (getResourceString(RESOURCE_BASE + "L_COUNTERPARTY"));
        lContractSize.setText  (getResourceString(RESOURCE_BASE + "L_CONTRACT_SIZE"));
        lQuoteType.setText  (getResourceString(RESOURCE_BASE + "L_QUOTE_TYPE"));
        lFxRate.setText  (getResourceString(RESOURCE_BASE + "L_FX_RATE"));
        lTraderLocation.setText (getResourceString(RESOURCE_BASE + "L_TRADER_LOCATION"));
        lReportLocation.setText (getResourceString(RESOURCE_BASE + "L_REPORT_LOCATION"));
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayout() {		
		// Line 0
		add(lInstrument,		new GridBagConstraints2(0, 0, 2, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lExchange,          new GridBagConstraints2(2, 0, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lContractSize,      new GridBagConstraints2(3, 0, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lQuoteType,         new GridBagConstraints2(4, 0, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lNet,               new GridBagConstraints2(5, 0, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lInternal,          new GridBagConstraints2(6, 0, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lAutomatic,         new GridBagConstraints2(7, 0, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8808, 0, 0));

		// Line 1
		add(tfInstrument,		new GridBagConstraints2(0, 1, 2, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
        add(tfExchange,         new GridBagConstraints2(2, 1, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
        add(tfContractSize,     new GridBagConstraints2(3, 1, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
        add(tfQuoteType,        new GridBagConstraints2(4, 1, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
        add(ckNet,              new GridBagConstraints2(5, 1, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
        add(ckInternal,         new GridBagConstraints2(6, 1, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
        add(ckAutomatic,        new GridBagConstraints2(7, 1, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4808, 0, 0));

		// Line 2
		add(lIsin, 				new GridBagConstraints2(0, 2, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));		
		add(lPrice, 			new GridBagConstraints2(1, 2, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lVolume, 			new GridBagConstraints2(2, 2, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lCurrency, 			new GridBagConstraints2(3, 2, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lFxRate, 			new GridBagConstraints2(4, 2, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lStorno,            new GridBagConstraints2(5, 2, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lMischPrice,        new GridBagConstraints2(6, 2, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lBuy,               new GridBagConstraints2(7, 2, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8808, 0, 0));
		
		// Line 3
		add(tfIsin, 			new GridBagConstraints2(0, 3, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));	
		add(tfPrice, 			new GridBagConstraints2(1, 3, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfVolume, 			new GridBagConstraints2(2, 3, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfCurrency, 		new GridBagConstraints2(3, 3, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfFxRate,    		new GridBagConstraints2(4, 3, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
        add(ckStorno,           new GridBagConstraints2(5, 3, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4880, 0, 0));
        add(ckMischPrice,       new GridBagConstraints2(6, 3, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4880, 0, 0));
        add(ckBuy,              new GridBagConstraints2(7, 3, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4888, 0, 0));

		// Line 4
		add(lSettlementTime, 	new GridBagConstraints2(0, 4, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lTradeId,			new GridBagConstraints2(1, 4, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lTradeTime,			new GridBagConstraints2(2, 4, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lSystemTime,		new GridBagConstraints2(3, 4, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lTraderId,          new GridBagConstraints2(4, 4, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lTraderName,        new GridBagConstraints2(5, 4, 3, 1, 1.0, 0.0, WEST, HORIZ, insets8808, 0, 0));

		// Line 5
		add(tfSettlementTime, 	new GridBagConstraints2(0, 5, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));	
		add(tfTradeId,			new GridBagConstraints2(1, 5, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));	
		add(tfTradeTime,		new GridBagConstraints2(2, 5, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfSystemTime,		new GridBagConstraints2(3, 5, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
        add(tfTraderId,         new GridBagConstraints2(4, 5, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
        add(tfTraderName,       new GridBagConstraints2(5, 5, 3, 1, 1.0, 1.0, WEST, HORIZ, insets4808, 0, 0));

	    // Line 6
        add(lReferenceTrade,    new GridBagConstraints2(0, 6, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));      
        add(lBook,              new GridBagConstraints2(1, 6, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lTurnOver,          new GridBagConstraints2(2, 6, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lCounterparty,      new GridBagConstraints2(3, 6, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lFreeText,          new GridBagConstraints2(4, 6, 4, 1, 0.0, 0.0, WEST, HORIZ, insets8808, 0, 0));

        // Line 7
        add(tfReferenceTrade,   new GridBagConstraints2(0, 7, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));  
        add(tfBook,             new GridBagConstraints2(1, 7, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
        add(tfTurnOver,         new GridBagConstraints2(2, 7, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
        add(tfCounterparty,     new GridBagConstraints2(3, 7, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));  
        add(tfFreeText,         new GridBagConstraints2(4, 7, 4, 1, 1.0, 1.0, WEST, HORIZ, insets4808, 0, 0));  

        // Column 8
        add(lTraderLocation,    new GridBagConstraints2(0, 8, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));        
        add(lReportLocation,    new GridBagConstraints2(1, 8, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));

        // Column 9
        add(tfTraderLocation,   new GridBagConstraints2(0, 9, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4880, 0, 0));     
        add(tfReportLocation,   new GridBagConstraints2(1, 9, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4880, 0, 0));

	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
		
		propertyHandler.add(EquityTradeOvTradePanelModel.P_AUTOMATIC,			ckAutomatic, ro);
		propertyHandler.add(EquityTradeOvTradePanelModel.P_CURRENCY,			tfCurrency, ro);
		propertyHandler.add(EquityTradeOvTradePanelModel.P_EXCHANGE,			tfExchange, ro);
		propertyHandler.add(EquityTradeOvTradePanelModel.P_INSTRUMENT,			tfInstrument, ro);
		propertyHandler.add(EquityTradeOvTradePanelModel.P_INTERNAL,			ckInternal, ro);
		propertyHandler.add(EquityTradeOvTradePanelModel.P_ISIN,				tfIsin, ro);
		propertyHandler.add(EquityTradeOvTradePanelModel.P_MISCH_PRICE,			ckMischPrice, ro);
		propertyHandler.add(EquityTradeOvTradePanelModel.P_NET,					ckNet, ro);
		propertyHandler.add(EquityTradeOvTradePanelModel.P_BUY,					ckBuy, ro);
		propertyHandler.add(EquityTradeOvTradePanelModel.P_PRICE,				tfPrice, ro);
		propertyHandler.add(EquityTradeOvTradePanelModel.P_STORNO,				ckStorno, ro);		
		propertyHandler.add(EquityTradeOvTradePanelModel.P_SETTLEMENT_TIME,		tfSettlementTime, ro);
		propertyHandler.add(EquityTradeOvTradePanelModel.P_SYSTEM_TIME,			tfSystemTime, ro);
        propertyHandler.add(EquityTradeOvTradePanelModel.P_TRADER_ID,           tfTraderId, ro);
        propertyHandler.add(EquityTradeOvTradePanelModel.P_TRADER_NAME,         tfTraderName, ro);
		propertyHandler.add(EquityTradeOvTradePanelModel.P_VOLUME,				tfVolume, ro);
		propertyHandler.add(EquityTradeOvTradePanelModel.P_TRADE_ID,			tfTradeId, ro);
        propertyHandler.add(EquityTradeOvTradePanelModel.P_TRADE_TIME,          tfTradeTime, ro);
        propertyHandler.add(EquityTradeOvTradePanelModel.P_GROUP_ID,            tfReferenceTrade, ro);
        propertyHandler.add(EquityTradeOvTradePanelModel.P_BOOK,                tfBook, ro);
        propertyHandler.add(EquityTradeOvTradePanelModel.P_FREE_TEXT,           tfFreeText, ro);
        propertyHandler.add(EquityTradeOvTradePanelModel.P_TURNOVER,            tfTurnOver, ro);
        propertyHandler.add(EquityTradeOvTradePanelModel.P_COUNTERPARTY,        tfCounterparty, ro);
        propertyHandler.add(EquityTradeOvTradePanelModel.P_CONTRACT_SIZE,       tfContractSize, ro);
        propertyHandler.add(EquityTradeOvTradePanelModel.P_QUOTE_TYPE,          tfQuoteType, ro);
        propertyHandler.add(EquityTradeOvTradePanelModel.P_FX_RATE,             tfFxRate, ro);
        propertyHandler.add(EquityTradeOvTradePanelModel.P_TRADER_LOCATION,   tfTraderLocation, ro);
        propertyHandler.add(EquityTradeOvTradePanelModel.P_REPORT_LOCATION,   tfReportLocation, ro);
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof EquityTradeOvTradePanelModel))
			throw new IllegalArgumentException("Model not instance of EquityTradeOvTradePanelModel!");
		
		propertyHandler.setModel(newModel);
		
		super.setModel(newModel);
		fillView();
	}

}
