package de.westlb.mgb.client.mask.view.equity;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.equity.EquityTradeOvParameterPanelModel;
import de.westlb.mgb.client.mask.view.shared.AbstractView;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;


/**
 * @author M. Boerner
 *
 * View to display the parameters for an equity trade.
 */
public class EquityTradeOvParameterPanelView extends AbstractView {
	/**
     * 
     */
    private static final long serialVersionUID = 2662297557707964064L;

    private String RESOURCE_BASE = getResourceBase();

	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	// UI - Components
	private SLabel lCategory				= new SLabel();
	private SLabel lPriceToleranceAbsolute	= new SLabel();
	private SLabel lPriceTolerancePercent	= new SLabel();
	private SLabel lTimeTolerance			= new SLabel();
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
	public EquityTradeOvParameterPanelView() {
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
	public EquityTradeOvParameterPanelView(Model model) {
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
		lPriceToleranceAbsolute.setText	(getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE_ABSOLUTE"));
		lPriceTolerancePercent.setText	(getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE_PERCENT"));
		lTimeTolerance.setText			(getResourceString(RESOURCE_BASE + "L_TIME_TOLERANCE"));
		lAddonName.setText				(getResourceString(RESOURCE_BASE + "L_ADDON_NAME"));
		lHintPriceAddon.setText			(getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE_ADDON"));
		lHintTimeAddon.setText			(getResourceString(RESOURCE_BASE + "L_TIME_TOLERANCE_ADDON"));
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayout() {		
		// Line 0
		add(lCategory, 				new GridBagConstraints2(0, 0, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lPriceToleranceAbsolute, new GridBagConstraints2(1, 0, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lPriceTolerancePercent, new GridBagConstraints2(2, 0, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lTimeTolerance,	 		new GridBagConstraints2(3, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8808, 0, 0));
	
		// Line 1
		add(tfCategory,	 			new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		add(tfPriceToleranceAbsolute,new GridBagConstraints2(1, 1, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	
		add(tfPriceTolerancePercent,new GridBagConstraints2(2, 1, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	
		add(tfTimeTolerance, 		new GridBagConstraints2(3, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4808, 0, 0));

		// Line 2
		add(lAddonName,				new GridBagConstraints2(0, 2, 2, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		add(lHintPriceAddon,	 	new GridBagConstraints2(2, 2, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		add(lHintTimeAddon,	 		new GridBagConstraints2(3, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4808, 0, 0));

		// Line 3
		add(tfAddonName,			new GridBagConstraints2(0, 3, 2, 1, 1.0, 1.0, NWEST, HORIZ, insets4880, 0, 0));	
		add(tfPriceToleranceAddon,	new GridBagConstraints2(2, 3, 1, 1, 0.0, 1.0, NWEST, HORIZ, insets4880, 0, 0));	
		add(tfTimeToleranceAddon,	new GridBagConstraints2(3, 3, 1, 1, 1.0, 1.0, NWEST, HORIZ, insets4888, 0, 0));	
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
		
		propertyHandler.add(EquityTradeOvParameterPanelModel.CATEGORY_NAME,				tfCategory, ro);
		propertyHandler.add(EquityTradeOvParameterPanelModel.PRICE_TOLERANCE_ABSOLUTE,		tfPriceToleranceAbsolute, ro);
		propertyHandler.add(EquityTradeOvParameterPanelModel.PRICE_TOLERANCE_PERCENT,		tfPriceTolerancePercent, ro);
		propertyHandler.add(EquityTradeOvParameterPanelModel.TIME_TOLERANCE,			tfTimeTolerance, ro);
		propertyHandler.add(EquityTradeOvParameterPanelModel.ADDON_NAME,				tfAddonName, ro);
		propertyHandler.add(EquityTradeOvParameterPanelModel.TIME_TOLERANCE_ADDON,		tfTimeToleranceAddon, ro);
		propertyHandler.add(EquityTradeOvParameterPanelModel.PRICE_TOLERANCE_ADDON,		tfPriceToleranceAddon, ro);
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof EquityTradeOvParameterPanelModel))
			throw new IllegalArgumentException("Model not instance of EquityTradeOvParameterPanelModel!");
		propertyHandler.setModel(newModel);
		
		super.setModel(newModel);
		fillView();
	}

}
