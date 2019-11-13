package de.westlb.mgb.client.mask.view.mmk;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.mmk.MmkTradePrmParameterPanelModel;
import de.westlb.mgb.client.mask.view.shared.AbstractView;
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
 * View to display the parameter of an instrument.
 */
public class MmkTradePrmParameterPanelView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 168663817091095728L;

	private String RESOURCE_BASE = getResourceBase();

	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	// UI - Components
	private SLabel lCategory				= new SLabel();
	private SLabel lPriceTolerancePercent	= new SLabel();
	private SLabel lPriceToleranceAbsolute	= new SLabel();
	
	private STextField tfCategory			= new STextField(DEF_COL_08);
	private STextField tfPriceTolerancePercent 	= new VDecimalField();
	private STextField tfPriceToleranceAbsolute	= new VDecimalField();
	
	/**
	 */
	public MmkTradePrmParameterPanelView() {
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
	public MmkTradePrmParameterPanelView(Model model) {
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
		lPriceTolerancePercent.setText			(getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE_PERCENT"));
		lPriceToleranceAbsolute.setText			(getResourceString(RESOURCE_BASE + "L_PRICE_TOLERANCE_ABSOLUTE"));
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayout() {		
		add(lCategory, 						new GridBagConstraints2(0, 0, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets8808, 0, 0));
		add(tfCategory,	 					new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4808, 0, 0));
		add(lPriceTolerancePercent, 		new GridBagConstraints2(0, 2, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4808, 0, 0));
		add(tfPriceTolerancePercent,		new GridBagConstraints2(0, 3, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4808, 0, 0));	
		add(lPriceToleranceAbsolute, 		new GridBagConstraints2(0, 5, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets4808, 0, 0));
		add(tfPriceToleranceAbsolute,		new GridBagConstraints2(0, 6, 1, 1, 1.0, 1.0, NWEST, HORIZ, insets4808, 0, 0));			
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
		
		propertyHandler.add(MmkTradePrmParameterPanelModel.P_MMK_CATEGORY,				tfCategory, ro);
		propertyHandler.add(MmkTradePrmParameterPanelModel.P_MMK_PRICE_TOLERANCE_PERCENT,	tfPriceTolerancePercent, ro);
		propertyHandler.add(MmkTradePrmParameterPanelModel.P_MMK_PRICE_TOLERANCE_ABSOLUTE,	tfPriceToleranceAbsolute, ro);
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof MmkTradePrmParameterPanelModel))
			throw new IllegalArgumentException("Model not instance of MmkTradePrmParameterPanelModel!");
		propertyHandler.setModel(newModel);
		
		super.setModel(newModel);
		fillView();
	}

}
