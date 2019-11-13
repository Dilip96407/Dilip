package de.westlb.mgb.client.mask.view.mmk;

import java.awt.Color;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.TradeParameterModel;
import de.westlb.mgb.client.mask.view.shared.AbstractView;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.ui.view.TablePanel;
import de.westlb_systems.xaf.util.Debug;


/**
 * @author M. Boerner
 *
 * View to display the most important information that
 * the trade controller needs for the trade checking.
 */
public class MmkTradeParameterView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4838678940588450259L;

	private String RESOURCE_BASE = getResourceBase();

	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();


	// UI - Components
	private TablePanel tpParameter = new TablePanel();
	private MmkTradePrmParameterPanelView pParameter = 	new MmkTradePrmParameterPanelView();

	private TablePanel tpTrade = new TablePanel();
	private MmkTradePrmTradePanelView pTrade = new MmkTradePrmTradePanelView();
	
	// 
	/**
	 * Constructor for MmkTradeParameterView.
	 */
	public MmkTradeParameterView() {
		try {
			initComponents();
			initLayout();
			initLabels();
			initProperties();
		} catch (Exception ex) {
			Debug.log(Debug.ERROR, this, "Exception in View-Constuctor");
			Debug.log(ex);
			ex.printStackTrace();
		}
	}

	/**
	 * Constructor for MmkTradeParameterView.
	 * @param model
	 */
	public MmkTradeParameterView(Model model) {
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
		Color bgColor = UIManager.getColor("DetailsPanel.background");
		setBackground(bgColor);
		
//		pParameter.setBackground(bgColor);
//		pTrade.setBackground(bgColor);
		
		tpParameter.setTable(pParameter);
		tpTrade.setTable(pTrade);
	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		tpParameter.setText				(getResourceString(RESOURCE_BASE + "L_HEADER_PARAMETER"));
		tpTrade.setText					(getResourceString(RESOURCE_BASE + "L_HEADER_TRADE"));
	}

	/**
	 * Method initLayout.
	 */
	private void initLayout() {
		add(tpTrade, 					new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, NWEST, BOTH, insets4880, 0, 0));
		add(tpParameter, 				new GridBagConstraints2(1, 0, 1, 1, 1.0, 1.0, NWEST, BOTH, insets4888, 0, 0));
		setReadOnly();
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof TradeParameterModel))
			throw new IllegalArgumentException("Model not instance of TradeParameterModel!");
		propertyHandler.setModel(newModel);
		pParameter.setModel(newModel);
		pTrade.setModel(newModel);
		super.setModel(newModel);
		fillView();
	}

}
