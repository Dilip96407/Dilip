package de.westlb.mgb.client.mask.view.mmk;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.mmk.MmkTradePrmTradePanelModel;
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
 * View to display the parameter of an instrument.
 */
public class MmkTradePrmTradePanelView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5049061194521016500L;

	private String RESOURCE_BASE = getResourceBase();

	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	// UI - Components
	private SLabel lInstName			= new SLabel();
	private SLabel lTradeType			= new SLabel();
	private SLabel lTickerUsed			= new SLabel();
	
	private STextField tfInstName		= new STextField(DEF_COL_08);
	private STextField tfTradeType		= new STextField(DEF_COL_08);
	private STextField tfTickerUsed 	= new STextField(DEF_COL_08);
	
	/**
	 * Constructor for CheckStateView.
	 */
	public MmkTradePrmTradePanelView() {
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
	public MmkTradePrmTradePanelView(Model model) {
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
		this.setOpaque(true);
	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		lInstName.setText				(getResourceString(RESOURCE_BASE + "L_INST_NAME"));
		lTradeType.setText				(getResourceString(RESOURCE_BASE + "L_TRADE_TYPE"));
		lTickerUsed.setText				(getResourceString(RESOURCE_BASE + "L_TICKER_USED"));
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayout() {		
		add(lInstName, 					new GridBagConstraints2(0, 0, 1, 1, 0.5, 0.0, NWEST, HORIZ, insets8808, 0, 0));
		add(tfInstName,	 				new GridBagConstraints2(0, 1, 1, 1, 0.5, 0.0, NWEST, HORIZ, insets4808, 0, 0));
		add(lTradeType, 				new GridBagConstraints2(0, 2, 1, 1, 0.5, 0.0, NWEST, HORIZ, insets4808, 0, 0));
		add(tfTradeType, 				new GridBagConstraints2(0, 3, 1, 1, 0.5, 0.0, NWEST, HORIZ, insets4808, 0, 0));
		add(lTickerUsed, 				new GridBagConstraints2(0, 4, 1, 1, 0.5, 0.0, NWEST, HORIZ, insets4808, 0, 0));
		add(tfTickerUsed, 				new GridBagConstraints2(0, 5, 1, 1, 0.5, 1.0, NWEST, HORIZ, insets4808, 0, 0));
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
		propertyHandler.add(MmkTradePrmTradePanelModel.P_MMK_INSTR_NAME, tfInstName, ro);
		propertyHandler.add(MmkTradePrmTradePanelModel.P_MMK_TRADE_TYPE, tfTradeType, ro);
		propertyHandler.add(MmkTradePrmTradePanelModel.P_MMK_TICKER_USED, tfTickerUsed, ro);
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof MmkTradePrmTradePanelModel))
			throw new IllegalArgumentException("Model not instance of MmkTradePrmTradePanelModel!");
		propertyHandler.setModel(newModel);
		
		super.setModel(newModel);
		fillView();
	}

}
