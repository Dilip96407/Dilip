package de.westlb.mgb.client.mask.view.derivative;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.derivative.DerivativeTradeOvMarketDataPanelModel;
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
 * View to display the statistic of trade checking.
 */
public class DerivativeTradeOvMarketDataPanelView extends AbstractView {
	/**
     * 
     */
    private static final long serialVersionUID = 8312568901116652240L;

    private String RESOURCE_BASE = getResourceBase();

	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();
	
	// UI - Components
    private SLabel lNpv         = new SLabel();
    private SLabel lAmendmentNpvChange = new SLabel();
	private SLabel lVega 		= new SLabel();
	private SLabel lDelta		= new SLabel();
	private SLabel lPremium		= new SLabel();
	private SLabel lVolume		= new SLabel();
	private SLabel lTurnover 	= new SLabel();
	private SLabel lDeviation	= new SLabel();
	private SLabel lTolerance	= new SLabel();
	
    private STextField tfNpv        = new VDecimalField(VDecimalField.DEFAULT_COLUMNS,0);
    private STextField tfAmendmentNpvChange  = new VDecimalField(VDecimalField.DEFAULT_COLUMNS,0);
	private STextField tfVega		= new VDecimalField(VDecimalField.DEFAULT_COLUMNS,0);
	private STextField tfDelta		= new VDecimalField(VDecimalField.DEFAULT_COLUMNS,0);
	private STextField tfPremium	= new VDecimalField(VDecimalField.DEFAULT_COLUMNS,0);
	private STextField tfVolume		= new VDecimalField(VDecimalField.DEFAULT_COLUMNS,0);
	private STextField tfTurnover	= new VDecimalField(VDecimalField.DEFAULT_COLUMNS,0);
	private STextField tfDeviation	= new VDecimalField(VDecimalField.DEFAULT_COLUMNS,2);
	private STextField tfTolerance	= new VDecimalField(VDecimalField.DEFAULT_COLUMNS,2);
	
	/**
	 * Constructor for CheckStateView.
	 */
	public DerivativeTradeOvMarketDataPanelView() {
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
	public DerivativeTradeOvMarketDataPanelView(Model model) {
		super(model);
	}

	/**
	 * Felder des Views mit Werten aus dem Model fuellen
	 */
	private void fillView() {
		if (getModel() == null)
			return;
		
		setAttention(tfDeviation, getTradeOvMarketDataPanelModel().isPriceToleranceViolation());

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
        lNpv.setText        (getResourceString(RESOURCE_BASE + "L_NPV"));
        lAmendmentNpvChange.setText        (getResourceString(RESOURCE_BASE + "L_AMENDMENT_NPV_CHANGE"));
		lVega.setText		(getResourceString(RESOURCE_BASE + "L_VEGA"));
		lDelta.setText		(getResourceString(RESOURCE_BASE + "L_DELTA"));
		lPremium.setText	(getResourceString(RESOURCE_BASE + "L_PREMIUM"));
		lVolume.setText		(getResourceString(RESOURCE_BASE + "L_VOLUME"));
		lTurnover.setText	(getResourceString(RESOURCE_BASE + "L_TURNOVER"));
		lDeviation.setText	(getResourceString(RESOURCE_BASE + "L_DEVIATION"));
		lTolerance.setText	(getResourceString(RESOURCE_BASE + "L_TOLERANCE"));
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayout() {		
		int c = 0;
		
		// Line 1
		add(lNpv, 			new GridBagConstraints2(c++, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lVega,			new GridBagConstraints2(c++, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lDelta,			new GridBagConstraints2(c++, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lPremium,		new GridBagConstraints2(c++, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lVolume,		new GridBagConstraints2(c++, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lTurnover,		new GridBagConstraints2(c++, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lDeviation,		new GridBagConstraints2(c++, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
		add(lTolerance,		new GridBagConstraints2(c++, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8808, 0, 0));
	
		c = 0;		
		// Line 2
		add(tfNpv, 			new GridBagConstraints2(c++, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	
		add(tfVega,			new GridBagConstraints2(c++, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		add(tfDelta,		new GridBagConstraints2(c++, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	
		add(tfPremium,		new GridBagConstraints2(c++, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		add(tfVolume,		new GridBagConstraints2(c++, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		add(tfTurnover,		new GridBagConstraints2(c++, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));	
		add(tfDeviation,	new GridBagConstraints2(c++, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		add(tfTolerance,	new GridBagConstraints2(c++, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4808, 0, 0));

        c = 0;      
	      // Line 3
        add(lAmendmentNpvChange, new GridBagConstraints2(c++, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));

        c = 0;      
        // Line 3
        add(tfAmendmentNpvChange, new GridBagConstraints2(c++, 3, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));

}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
        propertyHandler.add(DerivativeTradeOvMarketDataPanelModel.P_DVD_NPV,        tfNpv, ro);
        propertyHandler.add(DerivativeTradeOvMarketDataPanelModel.P_DVD_AMENDMENT_NPV_CHANGE,        tfAmendmentNpvChange, ro);
		propertyHandler.add(DerivativeTradeOvMarketDataPanelModel.P_DVD_VEGA,		tfVega, ro);
		propertyHandler.add(DerivativeTradeOvMarketDataPanelModel.P_DVD_DELTA,		tfDelta, ro);
		propertyHandler.add(DerivativeTradeOvMarketDataPanelModel.P_DVD_PREMIUM,	tfPremium, ro);
		propertyHandler.add(DerivativeTradeOvMarketDataPanelModel.P_DVD_VOLUME,		tfVolume, ro);
		propertyHandler.add(DerivativeTradeOvMarketDataPanelModel.P_DVD_TURNOVER,	tfTurnover, ro);
		propertyHandler.add(DerivativeTradeOvMarketDataPanelModel.P_DVD_DEVIATION,	tfDeviation, ro);
		propertyHandler.add(DerivativeTradeOvMarketDataPanelModel.P_DVD_TOLERANCE,	tfTolerance, ro);
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof DerivativeTradeOvMarketDataPanelModel))
			throw new IllegalArgumentException("Model not instance of DerivativeTradeOvMarketDataPanelModel!");
		propertyHandler.setModel(newModel);
		
		super.setModel(newModel);
		fillView();
	}

	private DerivativeTradeOvMarketDataPanelModel getTradeOvMarketDataPanelModel() {
		if (!(getModel() instanceof DerivativeTradeOvMarketDataPanelModel)) {
			return null;
		}
		
		return (DerivativeTradeOvMarketDataPanelModel) getModel();	
	}

}
