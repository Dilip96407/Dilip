package de.westlb.mgb.client.mask.view.shared;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.TradeOvMarketDataPanelModel;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.client.ui.util.VDateField;
import de.westlb.mgb.client.ui.util.VDecimalField;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
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
public class TradeOvMarketDataPanelView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8454101781955455507L;

	private String RESOURCE_BASE = getResourceBase();

	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();
	
	// UI - Components
	private SLabel lCurrency 		= new SLabel();
	private SLabel lPriceMin		= new SLabel();
	private SLabel lPriceMax		= new SLabel();
	private SLabel lRequestDate 	= new SLabel();
	private SLabel lRequestString	= new SLabel();
	private SLabel lSource			= new SLabel();
	private SLabel lTime			= new SLabel();
	
	private STextField tfCurrency		= new STextField(DEF_COL_08);
	private STextField tfPriceMin		= new VDecimalField();
	private STextField tfPriceMax		= new VDecimalField();
	private STextField tfRequestDate	= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);	
	private STextField tfRequestString	= new STextField(DEF_COL_12);
	private STextField tfSource			= new STextField(DEF_COL_10);
	private STextField tfTime			= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);	
	
	/**
	 * Constructor for CheckStateView.
	 */
	public TradeOvMarketDataPanelView() {
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
	public TradeOvMarketDataPanelView(Model model) {
		super(model);
	}

	/**
	 * Felder des Views mit Werten aus dem Model fuellen
	 */
	private void fillView() {
		setEmphasized(tfPriceMin);
		setEmphasized(tfPriceMax);

		if (getModel() == null)
			return;
		propertyHandler.syncFields();
	}

	/**
	 * Method initComponents.
	 */
	private void initComponents() {
		this.setBackground(UIManager.getColor("DetailsPanel.background"));
		this.setOpaque(false);
	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		lCurrency.setText		(getResourceString(RESOURCE_BASE +"L_CURRENCY"));
		lPriceMin.setText		(getResourceString(RESOURCE_BASE +"L_PRICEMIN"));
		lPriceMax.setText		(getResourceString(RESOURCE_BASE + "L_PRICEMAX"));
		lRequestDate.setText	(getResourceString(RESOURCE_BASE + "L_REQUEST_DATE"));
		lRequestString.setText	(getResourceString(RESOURCE_BASE + "L_REQUEST_STRING"));
		lSource.setText			(getResourceString(RESOURCE_BASE + "L_SOURCE"));
		lTime.setText			(getResourceString(RESOURCE_BASE + "L_TIME"));
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayout() {		
		int c;
		
		// Line 1
		add(lSource, 		new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lRequestString,	new GridBagConstraints2(1, 0, 4, 1, 1.0, 0.0, NWEST, HORIZ, insets8808, 0, 0));
	
		// Line 2
		add(tfSource, 		new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	
		add(tfRequestString,new GridBagConstraints2(1, 1, 4, 1, 1.0, 0.0, NWEST, HORIZ, insets4808, 0, 0));

		c = 0;		
		// Line 3
		add(lTime,	 		new GridBagConstraints2(c++, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lPriceMin, 		new GridBagConstraints2(c++, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lPriceMax, 		new GridBagConstraints2(c++, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lCurrency, 		new GridBagConstraints2(c++, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lRequestDate, 	new GridBagConstraints2(c++, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8808, 0, 0));

		c = 0;
		// Line 4
		add(tfTime,	 		new GridBagConstraints2(c++, 3, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));
		add(tfPriceMin,		new GridBagConstraints2(c++, 3, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));
		add(tfPriceMax,		new GridBagConstraints2(c++, 3, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));
		add(tfCurrency, 	new GridBagConstraints2(c++, 3, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));
		add(tfRequestDate, 	new GridBagConstraints2(c++, 3, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4888, 0, 0));
//		add(new SLabel(), 	new GridBagConstraints2(5, 3, 1, 1, 1.0, 1.0, NWEST, BOTH,  insets8800, 0, 0));
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
		propertyHandler.add(TradeOvMarketDataPanelModel.MD_CURRENCY,		tfCurrency, ro);
		propertyHandler.add(TradeOvMarketDataPanelModel.MD_PRICEMIN,		tfPriceMin, ro);
		propertyHandler.add(TradeOvMarketDataPanelModel.MD_PRICEMAX,		tfPriceMax, ro);
		propertyHandler.add(TradeOvMarketDataPanelModel.MD_REQUESTDATE,		tfRequestDate, ro);
		propertyHandler.add(TradeOvMarketDataPanelModel.MD_REQUESTSTRING,	tfRequestString, ro);
		propertyHandler.add(TradeOvMarketDataPanelModel.MD_SOURCE,			tfSource, ro);
		propertyHandler.add(TradeOvMarketDataPanelModel.MD_TIME,			tfTime, ro);
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof TradeOvMarketDataPanelModel))
			throw new IllegalArgumentException("Model not instance of TradeOvMarketDataPanelModel!");
		propertyHandler.setModel(newModel);
		
		super.setModel(newModel);
		fillView();
	}

}
