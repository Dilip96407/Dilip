package de.westlb.mgb.client.mask.view.mmk;

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
public class MmkTradeOvTradePanelView extends AbstractView {
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
	private SLabel lPrice = new SLabel();
	private SLabel lVolume = new SLabel();
	private SLabel lTradeType = new SLabel();
	private SLabel lTurnover = new SLabel();
	private SLabel lTradeId = new SLabel();
	private SLabel lTradeTime = new SLabel();
	private SLabel lTraderName = new SLabel();
	private SLabel lPriceDifference = new SLabel();
	private SLabel lPriceTolerance = new SLabel();
	private SLabel lMargin = new SLabel();
	private SLabel lFxRate = new SLabel();
	private SLabel lAmendDate = new SLabel();
	private SLabel lCounterparty = new SLabel();
	private SLabel lMaturityTime = new SLabel();
	private SLabel lRecordType = new SLabel();
	private SLabel lBookId = new SLabel();
	private SLabel lGroupId = new SLabel();
	private SLabel lMemo = new SLabel();
	private SLabel lSystemTime = new SLabel();
    private SLabel lTraderLocation = new SLabel();
    private SLabel lReportLocation = new SLabel();

	// UI - Components
	private STextField	tfInstrument		= new STextField(DEF_COL_10);
	private STextField	tfPrice 			= new VDecimalField();
	private STextField	tfVolume			= new VDecimalField();
	private STextField	tfTradeType 		= new STextField(DEF_COL_08);
	private STextField	tfTurnover			= new VDecimalField();
	private STextField	tfTradeId			= new STextField(DEF_COL_10);
	private STextField	tfTradeTime			= new VDateField(DEF_COL_10, DateFormat.DATE_FORMAT_LONG);
	private STextField	tfTraderName		= new STextField(DEF_COL_08);
	private STextField	tfMargin			= new VDecimalField();
    private STextField  tfFxRate            = new VDecimalField();
    private STextField  tfAmendDate         = new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);
	private STextField	tfPriceTolerance	= new VDecimalField();
	private STextField	tfPriceDifference	= new VDecimalField();
	private STextField	tfCounterparty		= new STextField(DEF_COL_08);
	private STextField	tfMaturityTime		= new VDateField(DEF_COL_10, DateFormat.DATE_FORMAT_LONG);
	private STextField	tfRecordType		= new STextField(DEF_COL_10);
	private STextField	tfBookId			= new STextField(DEF_COL_10);
	private STextField	tfGroupId			= new STextField(DEF_COL_10);
	private STextField	tfMemo				= new STextField(DEF_COL_20);
	private STextField	tfSystemTime		= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);
    private STextField  tfTraderLocation    = new STextField(DEF_COL_10);
    private STextField  tfReportLocation    = new STextField(DEF_COL_10);
			
	/**
	 * Constructor for CheckStateView.
	 */
	public MmkTradeOvTradePanelView() {
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
	public MmkTradeOvTradePanelView(Model model) {
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
		lPrice.setText			(getResourceString(RESOURCE_BASE + "L_PRICE"));
		lVolume.setText			(getResourceString(RESOURCE_BASE + "L_VOLUME"));
		lTradeType.setText		(getResourceString(RESOURCE_BASE + "L_TRADE_TYPE"));
		lTurnover.setText		(getResourceString(RESOURCE_BASE + "L_TURNOVER"));
		lTradeTime.setText		(getResourceString(RESOURCE_BASE + "L_TRADE_TIME"));
		lTraderName.setText		(getResourceString(RESOURCE_BASE + "L_TRADER_NAME"));
		lTradeId.setText		(getResourceString(RESOURCE_BASE + "L_TRADE_ID"));
		lPriceTolerance.setText	(getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE"));
		lPriceDifference.setText(getResourceString(RESOURCE_BASE + "L_PRICE_DIFFERENCE"));
		lMargin.setText			(getResourceString(RESOURCE_BASE + "L_MARGIN"));
        lFxRate.setText         (getResourceString(RESOURCE_BASE + "L_FX_RATE"));
        lAmendDate.setText      (getResourceString(RESOURCE_BASE + "L_AMEND_DATE"));
		lCounterparty.setText	(getResourceString(RESOURCE_BASE + "L_COUNTERPARTY"));
		lMaturityTime.setText	(getResourceString(RESOURCE_BASE + "L_MATURITY_TIME"));
		lRecordType.setText		(getResourceString(RESOURCE_BASE + "L_RECORD_TYPE"));
		lBookId.setText			(getResourceString(RESOURCE_BASE + "L_BOOK_ID"));
		lGroupId.setText		(getResourceString(RESOURCE_BASE + "L_GROUP_ID"));
		lMemo.setText			(getResourceString(RESOURCE_BASE + "L_MEMO"));
		lSystemTime.setText		(getResourceString(RESOURCE_BASE + "L_SYSTEM_TIME"));
        lTraderLocation.setText (getResourceString(RESOURCE_BASE + "L_TRADER_LOCATION"));
        lReportLocation.setText (getResourceString(RESOURCE_BASE + "L_REPORT_LOCATION"));
	}

	public void setReadWrite() {
		tfGroupId.setEditable(true);
		tfTradeId.setEditable(true);
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayout() {		
		// Line 1
		add(lInstrument,		new GridBagConstraints2(0, 0, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lTradeType, 		new GridBagConstraints2(1, 0, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lPrice, 			new GridBagConstraints2(2, 0, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lVolume, 			new GridBagConstraints2(3, 0, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lTurnover, 			new GridBagConstraints2(4, 0, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8808, 0, 0));
		
		// Line 2
		add(tfInstrument,		new GridBagConstraints2(0, 1, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfTradeType, 		new GridBagConstraints2(1, 1, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfPrice, 			new GridBagConstraints2(2, 1, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfVolume, 			new GridBagConstraints2(3, 1, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfTurnover, 		new GridBagConstraints2(4, 1, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4808, 0, 0));

		// Line 3
		add(lTradeId,			new GridBagConstraints2(0, 2, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lTraderName, 		new GridBagConstraints2(1, 2, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lTradeTime,			new GridBagConstraints2(2, 2, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lPriceTolerance,	new GridBagConstraints2(3, 2, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lPriceDifference,	new GridBagConstraints2(4, 2, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8808, 0, 0));

		// Line 4
		add(tfTradeId,			new GridBagConstraints2(0, 3, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));	
		add(tfTraderName, 		new GridBagConstraints2(1, 3, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfTradeTime,		new GridBagConstraints2(2, 3, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfPriceTolerance,	new GridBagConstraints2(3, 3, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfPriceDifference,	new GridBagConstraints2(4, 3, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4808, 0, 0));

		// Line 5
		add(lGroupId,			new GridBagConstraints2(0, 4, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lRecordType, 		new GridBagConstraints2(1, 4, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lSystemTime,		new GridBagConstraints2(2, 4, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lCounterparty,		new GridBagConstraints2(3, 4, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lBookId,			new GridBagConstraints2(4, 4, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8808, 0, 0));

		// Line 6
		add(tfGroupId,			new GridBagConstraints2(0, 5, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));	
		add(tfRecordType, 		new GridBagConstraints2(1, 5, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfSystemTime,		new GridBagConstraints2(2, 5, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfCounterparty,		new GridBagConstraints2(3, 5, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfBookId,			new GridBagConstraints2(4, 5, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4808, 0, 0));

		// Line 7
		add(lMemo,				new GridBagConstraints2(0, 6, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lTraderLocation,    new GridBagConstraints2(1, 6, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));        
        add(lReportLocation,    new GridBagConstraints2(2, 6, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lMargin,			new GridBagConstraints2(3, 6, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lMaturityTime,		new GridBagConstraints2(4, 6, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8808, 0, 0));

		// Line 8
		add(tfMemo,				new GridBagConstraints2(0, 7, 1, 1, 1.0, 1.0, WEST, HORIZ, insets8800, 0, 0));			
        add(tfTraderLocation,   new GridBagConstraints2(1, 7, 1, 1, 1.0, 1.0, WEST, HORIZ, insets8800, 0, 0));     
        add(tfReportLocation,   new GridBagConstraints2(2, 7, 1, 1, 1.0, 1.0, WEST, HORIZ, insets8800, 0, 0));
		add(tfMargin,			new GridBagConstraints2(3, 7, 1, 1, 1.0, 1.0, WEST, HORIZ, insets8800, 0, 0));			
		add(tfMaturityTime,		new GridBagConstraints2(4, 7, 1, 1, 1.0, 1.0, WEST, HORIZ, insets8808, 0, 0));			

	    // Line 9
        add(lFxRate,            new GridBagConstraints2(0, 8, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
        add(lAmendDate,         new GridBagConstraints2(1, 8, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));        

        // Line 10
        add(tfFxRate,           new GridBagConstraints2(0, 9, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));          
        add(tfAmendDate,        new GridBagConstraints2(1, 9, 1, 1, 1.0, 1.0, WEST, HORIZ, insets4800, 0, 0));     
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_TRADE_TYPE,			tfTradeType, ro);
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_TURNOVER,			tfTurnover, ro);
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_INSTRUMENT,			tfInstrument, ro);
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_PRICE,				tfPrice, ro);
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_TRADE_TIME,			tfTradeTime, ro);
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_TRADER_NAME,		tfTraderName, ro);
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_VOLUME,				tfVolume, ro);
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_TRADE_ID,			tfTradeId, ro);
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_PRICE_DIFFERENCE,	tfPriceDifference, ro);
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_PRICE_TOLERANCE,	tfPriceTolerance, ro);
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_COUNTERPARTY,		tfCounterparty, ro);
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_BOOK_ID,			tfBookId, ro);
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_GROUP_ID,			tfGroupId, ro);
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_MATURITY_DATE,		tfMaturityTime, ro);
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_RECORD_TYPE,		tfRecordType, ro);
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_MEMO,				tfMemo, ro);
		propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_SYSTEM_TIME,		tfSystemTime, ro);
        propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_MARGIN,             tfMargin, ro);
        propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_FX_RATE,            tfFxRate, ro);
        propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_AMEND_DATE,         tfAmendDate, ro);
        propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_TRADER_LOCATION,    tfTraderLocation, ro);
        propertyHandler.add(MmkTradeOvTradePanelModel.P_MMK_REPORT_LOCATION,    tfReportLocation, ro);
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
