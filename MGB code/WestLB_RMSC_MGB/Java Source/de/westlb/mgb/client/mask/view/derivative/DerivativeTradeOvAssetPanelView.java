package de.westlb.mgb.client.mask.view.derivative;

import de.westlb.mgb.client.mask.model.derivative.DerivativeTradeOvAssetPanelModel;
import de.westlb.mgb.client.mask.view.shared.AbstractView;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.STable;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;


/**
 * @author M. Boerner
 *
 * View to display a trade overview in equity mandant.
 */
public class DerivativeTradeOvAssetPanelView extends AbstractView {
	/**
     * 
     */
    private static final long serialVersionUID = -3067596626318536853L;

	private STable tResult				= new STable();

	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

			
	/**
	 * Constructor for CheckStateView.
	 */
	public DerivativeTradeOvAssetPanelView() {
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
	 * Constructor for DerivativeTradeOvAssetPanelView.
	 * @param model
	 */
	public DerivativeTradeOvAssetPanelView(Model model) {
		super(model);
	}

	/**
	 * Felder des Views mit Werten aus dem Model fuellen
	 */
	private void fillView() {
		if (getAssetListModel() == null) {
			return;
		}
	
		propertyHandler.syncFields();
	}
	
	public DerivativeTradeOvAssetPanelModel getAssetListModel() {
		return getModel() instanceof DerivativeTradeOvAssetPanelModel ? (DerivativeTradeOvAssetPanelModel)getModel() : null;
	}
	
	/**
	 * Method initComponents.
	 */
	private void initComponents() {
		tResult.setGridEnabled(true);
	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayout() {
		add(tResult,	new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets0000, 0, 0));
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		propertyHandler.add(DerivativeTradeOvAssetPanelModel.P_ASSET_TABLE_MODEL, 	tResult);
		
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof DerivativeTradeOvAssetPanelModel)) {
	        throw new IllegalArgumentException("Model not instance of AssetListModel");
	    }
	    propertyHandler.setModel(newModel);
	    super.setModel(newModel);
	    fillView();
	}

}
