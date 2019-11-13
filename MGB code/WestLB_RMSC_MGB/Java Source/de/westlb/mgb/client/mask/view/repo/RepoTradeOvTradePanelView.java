package de.westlb.mgb.client.mask.view.repo;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.repo.RepoTradeOvTradePanelModel;
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
 * View to display the statistic of trade checking.
 */
public class RepoTradeOvTradePanelView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4192557195990865066L;
	private String RESOURCE_BASE = getResourceBase();
	private static final int DECIMAL_PLACES_2 = 2;

	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	// UI - Components
	private SLabel lTradeId						= new SLabel();
	private SLabel lInstrument					= new SLabel();
	private SLabel lStartCash					= new SLabel();
	private SLabel lEndCash						= new SLabel();
	private SLabel lCurrency					= new SLabel();
	private SLabel lNotional					= new SLabel();
	private SLabel lInstrumentType				= new SLabel();
	private SLabel lUnderlyingType				= new SLabel();
	private SLabel lUnderlyingValueGroup		= new SLabel();
	private SLabel lIsin						= new SLabel();
	private SLabel lStartDate					= new SLabel();
	private SLabel lEndDate						= new SLabel();
	private SLabel lMaturityDays				= new SLabel();
	private SLabel lTrader						= new SLabel();
	private SLabel lCounterParty				= new SLabel();
	private SLabel lOpenEnd						= new SLabel();
	private SLabel lTradeTime					= new SLabel();
	private SLabel lBook						= new SLabel();
	private SLabel lCustomertype				= new SLabel();
	private SLabel lVerifyDate					= new SLabel();
	private SLabel lAmendDate					= new SLabel();
	private SLabel lMarketPriceUnderlying		= new SLabel();
	private SLabel lDealAccruedInterest			= new SLabel();
	private SLabel lStartPrice					= new SLabel();
	private SLabel lBondAccruedInterest			= new SLabel();
	private SLabel lRepoMarketType				= new SLabel();
    private SLabel lTraderLocation              = new SLabel();
    private SLabel lReportLocation              = new SLabel();

	// UI - Components
	private STextField	tfTradeId				= new STextField(DEF_COL_10);	
	private STextField	tfInstrument			= new STextField(DEF_COL_10);
	private STextField	tfStartCash				= new VDecimalField(DEF_COL_10, DECIMAL_PLACES_2);
	private STextField	tfEndCash				= new VDecimalField(DEF_COL_10, DECIMAL_PLACES_2);
	private STextField	tfCurrency 				= new STextField(DEF_COL_10);
	private STextField	tfNotional				= new VDecimalField(DEF_COL_10, DECIMAL_PLACES_2);
	private STextField	tfInstrumentType		= new STextField(DEF_COL_10);
	private STextField	tfUnderlyingType		= new STextField(DEF_COL_10);
	private STextField	tfUnderlyingValueGroup	= new STextField(DEF_COL_10);
	private STextField	tfIsin 					= new STextField(DEF_COL_10);
	private STextField	tfStartDate				= new VDateField(DEF_COL_10, DateFormat.DATE_FORMAT_LONG);
	private STextField	tfEndDate				= new VDateField(DEF_COL_10, DateFormat.DATE_FORMAT_LONG);
	private STextField	tfMaturityDays			= new VDecimalField(DEF_COL_10, 0);
	private STextField	tfTrader				= new STextField(DEF_COL_10);
	private STextField	tfCounterParty			= new STextField(DEF_COL_10);
	private SCheckBox	ckOpenEnd				= new SCheckBox();
	private STextField 	tfTradeTime				= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);
	private STextField tfBook					= new STextField(DEF_COL_10);
	private STextField tfCustomerType			= new STextField(DEF_COL_10);
	private STextField tfVerifyDate				= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);
	private STextField tfAmendDate				= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);
	private STextField tfMarketPriceUnderlying	= new VDecimalField(DEF_COL_10, DECIMAL_PLACES_2);
	private STextField tfDealAccruedInterest	= new VDecimalField(DEF_COL_10, DECIMAL_PLACES_2);
	private STextField tfStartPrice				= new VDecimalField(DEF_COL_10, DECIMAL_PLACES_2);
	private STextField tfBondAccruedInterest	= new VDecimalField(DEF_COL_10, DECIMAL_PLACES_2);
	private STextField tfRepoMarketType			= new STextField(DEF_COL_10);
    private STextField tfTraderLocation         = new STextField(DEF_COL_10);
    private STextField tfReportLocation         = new STextField(DEF_COL_10);
				
	/**
	 * Constructor for CheckStateView.
	 */
	public RepoTradeOvTradePanelView() {
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
	 * Constructor for CheckStateView.
	 * @param model
	 */
	public RepoTradeOvTradePanelView(Model model) {
		super(model);
	}

	/**
	 * Felder des Views mit Werten aus dem Model fuellen
	 */
	private void fillView() {
		RepoTradeOvTradePanelModel model = getTradeOvTradePanelModel();
		if (model == null) {
			return;
		}

		propertyHandler.syncFields();
		setInvisibleIfNull(SCheckBox.class, propertyHandler, getModel());
		setAttention(tfCounterParty, model.isSparkasse());
	}
	
	private RepoTradeOvTradePanelModel getTradeOvTradePanelModel() {
		if (!(getModel() instanceof RepoTradeOvTradePanelModel)) {
			return null;
		}
		return (RepoTradeOvTradePanelModel) getModel();	
	}

	/**
	 * Method initComponents.
	 */
	private void initComponents() {
		this.setBackground(UIManager.getColor("DetailsPanel.background"));
		this.setOpaque(true);
	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		lCounterParty.setText			(getResourceString(RESOURCE_BASE +"L_COUNTER_PARTY"));
		lCurrency.setText				(getResourceString(RESOURCE_BASE +"L_CURRENCY"));
		lEndCash.setText				(getResourceString(RESOURCE_BASE +"L_END_CASH"));
		lEndDate.setText				(getResourceString(RESOURCE_BASE +"L_END_DATE"));
		lInstrument.setText				(getResourceString(RESOURCE_BASE +"L_INSTRUMENT"));
		lInstrumentType.setText			(getResourceString(RESOURCE_BASE +"L_INSTRUMENT_TYPE"));
		lIsin.setText					(getResourceString(RESOURCE_BASE +"L_ISIN"));
		lMaturityDays.setText			(getResourceString(RESOURCE_BASE +"L_MATURITY_DAYS"));
		lNotional.setText				(getResourceString(RESOURCE_BASE +"L_NOTIONAL"));		
		lOpenEnd.setText				(getResourceString(RESOURCE_BASE +"L_OPEN_END"));
		lStartCash.setText				(getResourceString(RESOURCE_BASE +"L_START_CASH"));
		lStartDate.setText				(getResourceString(RESOURCE_BASE +"L_START_DATE"));
		lTradeId.setText				(getResourceString(RESOURCE_BASE +"L_TRADE_ID"));
		lTrader.setText					(getResourceString(RESOURCE_BASE +"L_TRADER"));
		lUnderlyingType.setText			(getResourceString(RESOURCE_BASE +"L_UNDERLYING_TYPE"));
		lUnderlyingValueGroup.setText	(getResourceString(RESOURCE_BASE +"L_UNDERLYING_GROUP"));
		lTradeTime.setText				(getResourceString(RESOURCE_BASE +"L_TRADE_TIME"));
		lBook.setText					(getResourceString(RESOURCE_BASE +"L_BOOK"));
		lCustomertype.setText			(getResourceString(RESOURCE_BASE +"L_CUSTOMER_TYPE"));
		lVerifyDate.setText				(getResourceString(RESOURCE_BASE +"L_VERIFY_DATE"));
		lAmendDate.setText				(getResourceString(RESOURCE_BASE +"L_AMEND_DATE"));
		lMarketPriceUnderlying.setText	(getResourceString(RESOURCE_BASE +"L_MARKET_PRICE_UNDERLYING"));
		lDealAccruedInterest.setText	(getResourceString(RESOURCE_BASE +"L_DEAL_ACCRUED_INTEREST"));	
		lStartPrice.setText				(getResourceString(RESOURCE_BASE +"L_START_PRICE"));
		lBondAccruedInterest.setText	(getResourceString(RESOURCE_BASE +"L_BOND_ACCRUED_INTEREST"));
		lRepoMarketType.setText			(getResourceString(RESOURCE_BASE +"L_REPO_MARKET_TYPE"));
        lTraderLocation.setText (getResourceString(RESOURCE_BASE + "L_TRADER_LOCATION"));
        lReportLocation.setText (getResourceString(RESOURCE_BASE + "L_REPORT_LOCATION"));
	}

	public void setReadWrite() {
		tfInstrument.setEditable(true);
		tfIsin.setEditable(true);
		tfTradeId.setEditable(true);
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayout() {
		int l, c;
				
		l = 0; c = 0; // Column 0
	 	this.add(lTradeId,					new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lInstrument,				new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lIsin,						new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lUnderlyingType,			new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lUnderlyingValueGroup,		new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lStartCash,				new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lEndCash,					new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lNotional,					new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lStartDate,				new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lEndDate,					new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lStartPrice,				new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
//	 	this.add(lMarketPriceUnderlying,	new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8880, 0, 0));	 	
	 	this.add(lRepoMarketType,			new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
        this.add(lTraderLocation,           new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8880, 0, 0));        

		l = 0; c = 1; // Column 1
	 	this.add(tfTradeId,					new GridBagConstraints2(c, l++, 1, 1, 0.6, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	this.add(tfInstrument,				new GridBagConstraints2(c, l++, 1, 1, 0.6, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	this.add(tfIsin,					new GridBagConstraints2(c, l++, 1, 1, 0.6, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	this.add(tfUnderlyingType,			new GridBagConstraints2(c, l++, 1, 1, 0.6, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	this.add(tfUnderlyingValueGroup,	new GridBagConstraints2(c, l++, 1, 1, 0.6, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	this.add(tfStartCash,				new GridBagConstraints2(c, l++, 1, 1, 0.6, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	this.add(tfEndCash,					new GridBagConstraints2(c, l++, 1, 1, 0.6, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	this.add(tfNotional,				new GridBagConstraints2(c, l++, 1, 1, 0.6, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	this.add(tfStartDate,				new GridBagConstraints2(c, l++, 1, 1, 0.6, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	this.add(tfEndDate,					new GridBagConstraints2(c, l++, 1, 1, 0.6, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
	 	this.add(tfStartPrice,				new GridBagConstraints2(c, l++, 1, 1, 0.6, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
//	 	this.add(tfMarketPriceUnderlying,	new GridBagConstraints2(c, l++, 1, 1, 0.6, 0.0, NWEST, HORIZ, insets8880, 0, 0));	 	
	 	this.add(tfRepoMarketType,			new GridBagConstraints2(c, l++, 1, 1, 0.6, 0.0, NWEST, HORIZ, insets8800, 0, 0));	 	
        this.add(tfTraderLocation,          new GridBagConstraints2(c, l++, 1, 1, 0.6, 0.0, NWEST, HORIZ, insets8880, 0, 0));     

		l = 0; c = 2; // Column 2
	 	this.add(lTrader,					new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lCounterParty,				new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));
	 	this.add(lCustomertype,				new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));
	 	this.add(lBook,						new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));
		this.add(lTradeTime,				new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));
		this.add(lVerifyDate,				new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));
		this.add(lAmendDate,				new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));
	 	this.add(lCurrency,					new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lOpenEnd,					new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lMaturityDays,				new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lBondAccruedInterest,		new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lDealAccruedInterest,		new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
        this.add(lReportLocation,           new GridBagConstraints2(c, l++, 1, 1, 0.0, 0.0, NWEST, NONE, insets8880, 0, 0));


		l = 0; c = 3; // Column 3
	 	this.add(tfTrader,					new GridBagConstraints2(c, l++, 1, 1, 0.4, 0.0, NWEST, HORIZ, insets8808, 0, 0));	 	
	 	this.add(tfCounterParty,			new GridBagConstraints2(c, l++, 1, 1, 0.4, 0.0, NWEST, HORIZ, insets8808, 0, 0));	 	
	 	this.add(tfCustomerType,			new GridBagConstraints2(c, l++, 1, 1, 0.4, 0.0, NWEST, HORIZ, insets8808, 0, 0));	 	
	 	this.add(tfBook,					new GridBagConstraints2(c, l++, 1, 1, 0.4, 0.0, NWEST, HORIZ, insets8808, 0, 0));	 	
		this.add(tfTradeTime,				new GridBagConstraints2(c, l++, 1, 1, 0.4, 0.0, NWEST, HORIZ, insets8808, 0, 0));	 	
		this.add(tfVerifyDate,				new GridBagConstraints2(c, l++, 1, 1, 0.4, 0.0, NWEST, HORIZ, insets8808, 0, 0));	 	
		this.add(tfAmendDate,				new GridBagConstraints2(c, l++, 1, 1, 0.4, 0.0, NWEST, HORIZ, insets8808, 0, 0));	 	
	 	this.add(tfCurrency,				new GridBagConstraints2(c, l++, 1, 1, 0.4, 0.0, NWEST, HORIZ, insets8808, 0, 0));	 	
	 	this.add(ckOpenEnd,					new GridBagConstraints2(c, l++, 1, 1, 0.4, 0.0, NWEST, HORIZ, insets8808, 0, 0));
	 	this.add(tfMaturityDays,			new GridBagConstraints2(c, l++, 1, 1, 0.4, 0.0, NWEST, HORIZ, insets8808, 0, 0));	 	
	 	this.add(tfBondAccruedInterest,		new GridBagConstraints2(c, l++, 1, 1, 0.4, 0.0, NWEST, HORIZ, insets8808, 0, 0));	 	
	 	this.add(tfDealAccruedInterest,		new GridBagConstraints2(c, l++, 1, 1, 0.4, 0.0, NWEST, HORIZ, insets8808, 0, 0));	 	
        this.add(tfReportLocation,          new GridBagConstraints2(c, l++, 1, 1, 0.4, 0.0, NWEST, HORIZ, insets8888, 0, 0));
	 	
	 	this.setReadOnly(); 	
		
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
		
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_OPEN_END,					ckOpenEnd, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_COUNTERPARTY,					tfCounterParty, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_CURRENCY,						tfCurrency, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_END_CASH,					tfEndCash, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_END_DATE,					tfEndDate, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_INSTRUMENT,					tfInstrument, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_INSTRUMENT_TYPE,			tfInstrumentType, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_ISIN,							tfIsin, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_MATURITY_DAYS,			tfMaturityDays, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_START_CASH,				tfStartCash, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_START_DATE,				tfStartDate, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_TRADE_ID,						tfTradeId, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_TRADER_ID,						tfTrader, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_UNDERLYING_TYPE,			tfUnderlyingType, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_UNDERLYING_VALUE_GROUP,	tfUnderlyingValueGroup, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_TRADE_TIME,					tfTradeTime, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_VOLUME,						tfNotional, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_AMEND_DATE,				tfAmendDate, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_VERIFY_DATE,				tfVerifyDate, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_MARKET_PRICE_UNDERLYING,	tfMarketPriceUnderlying, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_DEAL_ACCRUED_INTEREST,	tfDealAccruedInterest, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_START_PRICE,				tfStartPrice, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_BOND_ACCRUED_INTEREST,	tfBondAccruedInterest, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_BOOK,							tfBook, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_CUSTOMER_TYPE,			tfCustomerType, ro);
		propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_MARKET_TYPE,				tfRepoMarketType, ro);
        propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_TRADER_LOCATION,   tfTraderLocation, ro);
        propertyHandler.add(RepoTradeOvTradePanelModel.P_REPO_REPORT_LOCATION,   tfReportLocation, ro);
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof RepoTradeOvTradePanelModel))
			throw new IllegalArgumentException("Model not instance of RepoTradeOvTradePanelModel!");
		propertyHandler.setModel(newModel);
		
		super.setModel(newModel);
		fillView();
	}

}
