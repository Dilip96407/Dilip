package de.westlb.mgb.client.mask.view.shared;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.TradePrmParameterPanelModel;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;



/**
 * @author M. Boerner
 *
 * View to display the parameter of an instrument.
 */
public class TradePrmParameterPanelView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3091114639510080390L;

	private String RESOURCE_BASE = getResourceBase();

	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	// UI - Components
	private SLabel lCategory				= new SLabel();
	private SLabel lPriceTolerance			= new SLabel();
	private SLabel lPriceToleranceAbsolute	= new SLabel();
	private SLabel lPriceTolerancePercent	= new SLabel();
	private SLabel lTimeTolerance			= new SLabel();
	private SLabel lAddon					= new SLabel();
	private SLabel lAddonName				= new SLabel();
	private SLabel lHintPriceAddon			= new SLabel();
	private SLabel lHintTimeAddon			= new SLabel();
	
	private STextField tfCategory				= new STextField(DEF_COL_08);
	private STextField tfPriceToleranceAbsolute	= new STextField(DEF_COL_08);
	private STextField tfPriceTolerancePercent	= new STextField(DEF_COL_08);
	private STextField tfTimeTolerance			= new STextField(DEF_COL_08);
	private STextField tfAddonName				= new STextField(DEF_COL_08);
	private STextField tfTimeToleranceAddon		= new STextField(DEF_COL_08);
	private STextField tfPriceToleranceAddon	= new STextField(DEF_COL_08);
	
	/**
	 * Constructor for CheckStateView.
	 */
	public TradePrmParameterPanelView() {
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
	public TradePrmParameterPanelView(Model model) {
		super(model);
	}

	/**
	 * Felder des Views mit Werten aus dem Model fuellen
	 */
	private void fillView() {
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
		lCategory.setText				(getResourceString(RESOURCE_BASE + "L_CATEGORY"));
		lPriceTolerance.setText			(getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE"));
		lPriceTolerancePercent.setText	(getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE_PERCENT"));
		lPriceToleranceAbsolute.setText	(getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE_ABSOLUTE"));
		lTimeTolerance.setText			(getResourceString(RESOURCE_BASE + "L_TIME_TOLERANCE"));
		lAddon.setText					(getResourceString(RESOURCE_BASE + "L_ADDON"));
		lAddonName.setText				(getResourceString(RESOURCE_BASE + "L_ADDON_NAME"));
		lHintPriceAddon.setText			(getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE_ADDON"));
		lHintTimeAddon.setText			(getResourceString(RESOURCE_BASE + "L_TIME_TOLERANCE_ADDON"));
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayout() {		
		int NWEST = GridBagConstraints2.NORTHWEST;
		
		// Line 1
		add(lCategory, 				new GridBagConstraints2(0, 0, 3, 1, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		
		// Line 2
		add(tfCategory,	 			new GridBagConstraints2(0, 1, 3, 1, 0.0, 0.0, NWEST, HORIZ, insets4808, 0, 0));

		// Line 3
		add(lPriceTolerance, 		new GridBagConstraints2(0, 2, 2, 1, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lTimeTolerance,	 		new GridBagConstraints2(2, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
	
		// Line 4
		add(lPriceToleranceAbsolute,new GridBagConstraints2(0, 3, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets0800, 0, 0));	
		add(lPriceTolerancePercent,	new GridBagConstraints2(1, 3, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets0800, 0, 0));	
		
		// Line 5
		add(tfPriceToleranceAbsolute,	new GridBagConstraints2(0, 4, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	
		add(tfPriceTolerancePercent,	new GridBagConstraints2(1, 4, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	
		add(tfTimeTolerance, 		new GridBagConstraints2(2, 4, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4808, 0, 0));

		// Line 6
		add(lAddon,				 	new GridBagConstraints2(0, 5, 2, 1, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));

		// Line 7
		add(lAddonName,			 	new GridBagConstraints2(0, 6, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets0800, 0, 0));
		add(lHintPriceAddon,	 	new GridBagConstraints2(1, 6, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets0800, 0, 0));
		add(lHintTimeAddon,	 		new GridBagConstraints2(2, 6, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets0800, 0, 0));

		// Line 8
		add(tfAddonName,			new GridBagConstraints2(0, 7, 1, 1, 0.0, 1.0, NWEST, HORIZ, insets4800, 0, 0));	
		add(tfTimeToleranceAddon,	new GridBagConstraints2(1, 7, 1, 1, 0.0, 1.0, NWEST, HORIZ, insets4800, 0, 0));	
		add(tfPriceToleranceAddon,	new GridBagConstraints2(2, 7, 1, 1, 1.0, 1.0, NWEST, HORIZ, insets4808, 0, 0));	
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
		
		propertyHandler.add(TradePrmParameterPanelModel.TPP_CATEGORY,				tfCategory, ro);
		propertyHandler.add(TradePrmParameterPanelModel.TPP_PRICE_TOLERANCE_ABSOLUTE,	tfPriceToleranceAbsolute, ro);
		propertyHandler.add(TradePrmParameterPanelModel.TPP_PRICE_TOLERANCE_PERCENT,	tfPriceTolerancePercent, ro);
		propertyHandler.add(TradePrmParameterPanelModel.TPP_TIME_TOLERANCE,			tfTimeTolerance, ro);
		propertyHandler.add(TradePrmParameterPanelModel.TPP_ADDON_NAME,				tfAddonName, ro);
		propertyHandler.add(TradePrmParameterPanelModel.TPP_TIME_TOLERANCE_ADDON,	tfTimeToleranceAddon, ro);
		propertyHandler.add(TradePrmParameterPanelModel.TPP_PRICE_TOLERANCE_ADDON,	tfPriceToleranceAddon, ro);
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof TradePrmParameterPanelModel))
			throw new IllegalArgumentException("Model not instance of InstrumentParameterPanelModel!");
		propertyHandler.setModel(newModel);
		
		super.setModel(newModel);
		fillView();
	}

}
