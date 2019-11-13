package de.westlb.mgb.client.mask.view.mmk;

import de.westlb.mgb.client.mask.model.mmk.MmkDoubleTradeOvTradePanelModel;
import de.westlb.mgb.client.mask.model.mmk.MmkTradeOvTradePanelModel;
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
 * View to display a trade overview in money market mandant.
 */
public class MmkDoubleTradeOvTradePanelView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4443089976600112866L;

	private String RESOURCE_BASE = getResourceBase();

	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	// UI - Components
	private SLabel lInstrument = new SLabel();
    private SLabel lTradeType = new SLabel();
	private SLabel lPrice = new SLabel();
    private SLabel lPriceTolerance = new SLabel();
    private SLabel lPriceDifference = new SLabel();
    private SLabel lNearDate = new SLabel();
    private SLabel lNearAmount = new SLabel();
    private SLabel lFwdPointsNearLeg = new SLabel();
    private SLabel lMarketPointsNearLeg = new SLabel();
    private SLabel lNearTurnover = new SLabel();
    private SLabel lFarDate = new SLabel();
    private SLabel lFarAmount = new SLabel();
    private SLabel lFwdPointsFarLeg = new SLabel();
    private SLabel lMarketPointsFarLeg = new SLabel();
    private SLabel lFarTurnover = new SLabel();
    private SLabel lTradeId = new SLabel();
    private SLabel lRecordType = new SLabel();
    private SLabel lTradeTime = new SLabel();
    private SLabel lSystemTime = new SLabel();
    private SLabel lTurnover = new SLabel();
	private SLabel lTraderName = new SLabel();
	private SLabel lCounterparty = new SLabel();
	private SLabel lBookId = new SLabel();
    private SLabel lMargin = new SLabel();
    private SLabel lFxRate = new SLabel();
    private SLabel lAmendDate = new SLabel();
	private SLabel lMemo = new SLabel();

	// UI - Components
	private STextField	tfInstrument		= new STextField(DEF_COL_10);
    private STextField  tfTradeType         = new STextField(DEF_COL_08);
	private STextField	tfPrice 			= new VDecimalField(VDecimalField.DEFAULT_COLUMNS,4);
    private STextField  tfPriceTolerance    = new VDecimalField(VDecimalField.DEFAULT_COLUMNS,4);
    private STextField  tfPriceDifference   = new VDecimalField(VDecimalField.DEFAULT_COLUMNS,4);
    private STextField  tfNearDate          = new VDateField(DEF_COL_10, DateFormat.DATE_FORMAT_LONG);
    private STextField  tfNearAmount        = new VDecimalField(VDecimalField.DEFAULT_COLUMNS,4);
    private STextField  tfFwdPointsNearLeg  = new VDecimalField(VDecimalField.DEFAULT_COLUMNS,4);
    private STextField  tfMarketPointNearLeg= new VDecimalField(VDecimalField.DEFAULT_COLUMNS,4);
    private STextField  tfNearTurnover      = new VDecimalField(VDecimalField.DEFAULT_COLUMNS,0);
    private STextField  tfFarDate           = new VDateField(DEF_COL_10, DateFormat.DATE_FORMAT_LONG);
    private STextField  tfFarAmount         = new VDecimalField(VDecimalField.DEFAULT_COLUMNS,4);
    private STextField  tfFwdPointsFarLeg   = new VDecimalField(VDecimalField.DEFAULT_COLUMNS,4);
    private STextField  tfMarketPointFarLeg = new VDecimalField(VDecimalField.DEFAULT_COLUMNS,4);
    private STextField  tfFarTurnover       = new VDecimalField(VDecimalField.DEFAULT_COLUMNS,0);
    private STextField  tfTradeId           = new STextField(DEF_COL_10);
    private STextField  tfRecordType        = new STextField(DEF_COL_10);
	private STextField	tfTradeTime			= new VDateField(DEF_COL_10, DateFormat.DATE_FORMAT_LONG);
    private STextField  tfSystemTime        = new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);
    private STextField  tfTurnover          = new VDecimalField(VDecimalField.DEFAULT_COLUMNS,0);
	private STextField	tfTraderName		= new STextField(DEF_COL_08);
    private STextField  tfCounterparty      = new STextField(DEF_COL_08);
	private STextField	tfMargin			= new VDecimalField(VDecimalField.DEFAULT_COLUMNS,4);
    private STextField  tfBookId            = new STextField(DEF_COL_10);
    private STextField  tfFxRate            = new VDecimalField(VDecimalField.DEFAULT_COLUMNS,4);
    private STextField  tfAmendDate         = new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);
	private STextField	tfMemo				= new STextField(DEF_COL_20);
			
	/**
	 * Constructor for CheckStateView.
	 */
	public MmkDoubleTradeOvTradePanelView() {
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
	public MmkDoubleTradeOvTradePanelView(Model model) {
		super(model);
	}

	/**
	 * Felder des Views mit Werten aus dem Model fuellen
	 */
	private void fillView() {
		MmkTradeOvTradePanelModel model = getTradeOvTradePanelModel();
		if (model == null) {
			return;
		}
		
		setAttention(tfPrice, model.isPriceToleranceViolation());
		setAttention(tfSystemTime, model.isLateEntry());
		propertyHandler.syncFields();
		setInvisibleIfNull(SCheckBox.class, propertyHandler, getModel());
	}
	
	private MmkTradeOvTradePanelModel getTradeOvTradePanelModel() {
		if (!(getModel() instanceof MmkTradeOvTradePanelModel)) {
			return null;
		}
		return (MmkTradeOvTradePanelModel) getModel();	
	}
	
	/**
	 * Method initComponents.
	 */
	private void initComponents() {
	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		lInstrument.setText		(getResourceString(RESOURCE_BASE + "L_INSTRUMENT"));
        lTradeType.setText      (getResourceString(RESOURCE_BASE + "L_TRADE_TYPE"));
		lPrice.setText			(getResourceString(RESOURCE_BASE + "L_PRICE"));
        lPriceTolerance.setText (getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE"));
        lPriceDifference.setText(getResourceString(RESOURCE_BASE + "L_PRICE_DIFFERENCE"));
        lNearDate.setText       (getResourceString(RESOURCE_BASE + "L_NEAR_DATE"));
        lNearAmount.setText     (getResourceString(RESOURCE_BASE + "L_NEAR_AMOUNT"));
        lFwdPointsNearLeg.setText(getResourceString(RESOURCE_BASE + "L_FWD_POINTS_NEAR_LEG"));
        lMarketPointsNearLeg.setText(getResourceString(RESOURCE_BASE + "L_MARKET_POINTS_NEAR_LEG"));
        lNearTurnover.setText   (getResourceString(RESOURCE_BASE + "L_NEAR_TURNOVER"));
        lFarDate.setText        (getResourceString(RESOURCE_BASE + "L_FAR_DATE"));
        lFarAmount.setText      (getResourceString(RESOURCE_BASE + "L_FAR_AMOUNT"));
        lFwdPointsFarLeg.setText(getResourceString(RESOURCE_BASE + "L_FWD_POINTS_FAR_LEG"));
        lMarketPointsFarLeg.setText(getResourceString(RESOURCE_BASE + "L_MARKET_POINTS_FAR_LEG"));
        lFarTurnover.setText	(getResourceString(RESOURCE_BASE + "L_FAR_TURNOVER"));
        lTradeId.setText        (getResourceString(RESOURCE_BASE + "L_TRADE_ID"));
        lRecordType.setText     (getResourceString(RESOURCE_BASE + "L_RECORD_TYPE"));
        lTradeTime.setText      (getResourceString(RESOURCE_BASE + "L_TRADE_TIME"));
        lSystemTime.setText     (getResourceString(RESOURCE_BASE + "L_SYSTEM_TIME"));
        lTurnover.setText		(getResourceString(RESOURCE_BASE + "L_TURNOVER"));
		lTraderName.setText		(getResourceString(RESOURCE_BASE + "L_TRADER_NAME"));
		lCounterparty.setText	(getResourceString(RESOURCE_BASE + "L_COUNTERPARTY"));
		lBookId.setText			(getResourceString(RESOURCE_BASE + "L_BOOK_ID"));
        lMargin.setText         (getResourceString(RESOURCE_BASE + "L_MARGIN"));
        lFxRate.setText         (getResourceString(RESOURCE_BASE + "L_FX_RATE"));
        lAmendDate.setText      (getResourceString(RESOURCE_BASE + "L_AMEND_DATE"));
		lMemo.setText			(getResourceString(RESOURCE_BASE + "L_MEMO"));
	}

	public void setReadWrite() {
		tfTradeId.setEditable(true);
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayout() {		
		// Line 1
		add(lInstrument,	     	new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lTradeType, 		    new GridBagConstraints2(1, 0, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lPrice,                 new GridBagConstraints2(2, 0, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lPriceTolerance,        new GridBagConstraints2(3, 0, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lPriceDifference,       new GridBagConstraints2(4, 0, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8808, 0, 0));
		
		// Line 2
		add(tfInstrument,		   new GridBagConstraints2(0, 1, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfTradeType, 		   new GridBagConstraints2(1, 1, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfPrice, 			   new GridBagConstraints2(2, 1, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
        add(tfPriceTolerance,      new GridBagConstraints2(3, 1, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
        add(tfPriceDifference,     new GridBagConstraints2(4, 1, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4808, 0, 0));

		// Line 3
		add(lNearDate,			   new GridBagConstraints2(0, 2, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lNearAmount, 		   new GridBagConstraints2(1, 2, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lFwdPointsNearLeg,	   new GridBagConstraints2(2, 2, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lMarketPointsNearLeg,  new GridBagConstraints2(3, 2, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lNearTurnover,    	   new GridBagConstraints2(4, 2, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8808, 0, 0));

		// Line 4
		add(tfNearDate,			   new GridBagConstraints2(0, 3, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));	
		add(tfNearAmount, 		   new GridBagConstraints2(1, 3, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfFwdPointsNearLeg,	   new GridBagConstraints2(2, 3, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfMarketPointNearLeg,  new GridBagConstraints2(3, 3, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfNearTurnover,	       new GridBagConstraints2(4, 3, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4808, 0, 0));

		// Line 5
		add(lFarDate,			   new GridBagConstraints2(0, 4, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lFarAmount, 		   new GridBagConstraints2(1, 4, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lFwdPointsFarLeg,	   new GridBagConstraints2(2, 4, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lMarketPointsFarLeg,   new GridBagConstraints2(3, 4, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lFarTurnover,		   new GridBagConstraints2(4, 4, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8808, 0, 0));

		// Line 6
		add(tfFarDate,			   new GridBagConstraints2(0, 5, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));	
		add(tfFarAmount, 		   new GridBagConstraints2(1, 5, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfFwdPointsFarLeg,	   new GridBagConstraints2(2, 5, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfMarketPointFarLeg,   new GridBagConstraints2(3, 5, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfFarTurnover,		   new GridBagConstraints2(4, 5, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4808, 0, 0));

		// Line 7
        add(lTradeId,              new GridBagConstraints2(0, 6, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lRecordType,           new GridBagConstraints2(1, 6, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lTradeTime,            new GridBagConstraints2(2, 6, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lSystemTime,		   new GridBagConstraints2(3, 6, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lTurnover,		       new GridBagConstraints2(4, 6, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8808, 0, 0));

		// Line 8
        add(tfTradeId,             new GridBagConstraints2(0, 7, 1, 1, 1.0, 1.0, WEST, HORIZ, insets8880, 0, 0));          
        add(tfRecordType,          new GridBagConstraints2(1, 7, 1, 1, 1.0, 1.0, WEST, HORIZ, insets8880, 0, 0));          
        add(tfTradeTime,           new GridBagConstraints2(2, 7, 1, 1, 1.0, 1.0, WEST, HORIZ, insets8880, 0, 0));          
		add(tfSystemTime,		   new GridBagConstraints2(3, 7, 1, 1, 1.0, 1.0, WEST, HORIZ, insets8880, 0, 0));			
		add(tfTurnover,		       new GridBagConstraints2(4, 7, 1, 1, 1.0, 1.0, WEST, HORIZ, insets8888, 0, 0));			

	    // Line 9
        add(lTraderName,           new GridBagConstraints2(0, 8, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lCounterparty,         new GridBagConstraints2(1, 8, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lBookId,               new GridBagConstraints2(2, 8, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lMargin,               new GridBagConstraints2(3, 8, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lFxRate,               new GridBagConstraints2(4, 8, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8808, 0, 0));

        // Line 10
        add(tfTraderName,          new GridBagConstraints2(0, 9, 1, 1, 1.0, 1.0, WEST, HORIZ, insets8880, 0, 0));          
        add(tfCounterparty,        new GridBagConstraints2(1, 9, 1, 1, 1.0, 1.0, WEST, HORIZ, insets8880, 0, 0));          
        add(tfBookId,              new GridBagConstraints2(2, 9, 1, 1, 1.0, 1.0, WEST, HORIZ, insets8880, 0, 0));          
        add(tfMargin,              new GridBagConstraints2(3, 9, 1, 1, 1.0, 1.0, WEST, HORIZ, insets8880, 0, 0));           
        add(tfFxRate,              new GridBagConstraints2(4, 9, 1, 1, 1.0, 1.0, WEST, HORIZ, insets8888, 0, 0));           

        // Line 11
        add(lAmendDate,            new GridBagConstraints2(0, 10, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));

        // Line 12
        add(tfAmendDate,           new GridBagConstraints2(0, 11, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));          
        
        // Line 13
        add(lMemo,                 new GridBagConstraints2(0, 12, 5, 1, 1.0, 0.0, WEST, HORIZ, insets8808, 0, 0));

        // Line 14
        add(tfMemo,                new GridBagConstraints2(0, 13, 5, 1, 1.0, 1.0, WEST, HORIZ, insets8888, 0, 0));           
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_INSTRUMENT,          tfInstrument, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_TRADE_TYPE,			tfTradeType, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_PRICE,               tfPrice, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_PRICE_DIFFERENCE,    tfPriceDifference, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_PRICE_TOLERANCE,     tfPriceTolerance, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_NEAR_DATE,           tfNearDate, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_NEAR_AMOUNT,         tfNearAmount, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_FWD_POINTS_NEAR_LEG, tfFwdPointsNearLeg, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_MARKET_POINTS_NEAR_LEG,   tfMarketPointNearLeg, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_NEAR_TURNOVER,       tfNearTurnover, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_FAR_DATE,            tfFarDate, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_FAR_AMOUNT,          tfFarAmount, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_FWD_POINTS_FAR_LEG,  tfFwdPointsFarLeg, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_MARKET_POINTS_FAR_LEG,    tfMarketPointFarLeg, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_FAR_TURNOVER,        tfFarTurnover, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_TRADE_ID,            tfTradeId, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_RECORD_TYPE,         tfRecordType, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_TRADE_TIME,          tfTradeTime, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_SYSTEM_TIME,         tfSystemTime, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_TURNOVER,			tfTurnover, ro);
		propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_TRADER_NAME,		    tfTraderName, ro);
		propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_COUNTERPARTY,		tfCounterparty, ro);
		propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_BOOK_ID,			    tfBookId, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_MARGIN,              tfMargin, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_FX_RATE,             tfFxRate, ro);
        propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_AMEND_DATE,          tfAmendDate, ro);
		propertyHandler.add(MmkDoubleTradeOvTradePanelModel.P_MMK2_MEMO,				tfMemo, ro);
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof MmkTradeOvTradePanelModel))
			throw new IllegalArgumentException("Model not instance of MmkTradeOvTradePanelModel!");
		propertyHandler.setModel(newModel);
		
		super.setModel(newModel);
		fillView();
	}

}
