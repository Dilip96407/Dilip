package de.westlb.mgb.client.mask.view.shared;

import java.awt.Color;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.TradeParameterModel;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.swing.STextField;
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
public class TradeParameterView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7460559724818056697L;
	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();


	// UI - Components
	private TablePanel tpParameter = new TablePanel();
	private TradePrmParameterPanelView pParameter = 	new TradePrmParameterPanelView();

	private TablePanel tpTrade = new TablePanel();
	private SPanel pTrade = new SPanel();
	
	// UI Components trade panel
	private SLabel lInstName					= new SLabel();
	private SLabel lInstIsin					= new SLabel();
	private STextField tfInstName 				= new STextField(DEF_COL_10);
	private STextField tfInstIsin				= new STextField(DEF_COL_10);
	
	private SLabel lTickerUsed					= new SLabel();
	private STextField tfTickerUsed			= new STextField(DEF_COL_12);	
	
	// 
	/**
	 * Constructor for TradeParameterView.
	 */
	public TradeParameterView() {
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
	 * Constructor for TradeParameterView.
	 * @param model
	 */
	public TradeParameterView(Model model) {
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
		
		pParameter.setBackground(bgColor);
		pTrade.setBackground(bgColor);
		
		tpParameter.setTable(pParameter);
		tpTrade.setTable(pTrade);
	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		tpParameter.setText				(getResourceString("TPV_L_HEADER_PARAMETER"));
		tpTrade.setText					(getResourceString("TPV_L_HEADER_TRADE"));
		lInstIsin.setText				(getResourceString("TPV_L_INST_ISIN"));
		lInstName.setText				(getResourceString("TPV_L_INST_NAME"));
		lTickerUsed.setText				(getResourceString("TPV_L_TICKER_USED"));
	}

	/**
	 * Method initLayout.
	 */
	private void initLayout() {
		pTrade.add(lInstName, 					new GridBagConstraints2(0, 0, 1, 1, 0.5, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		pTrade.add(tfInstName,	 				new GridBagConstraints2(0, 1, 1, 1, 0.5, 0.0, NWEST, HORIZ, insets4808, 0, 0));
		
		pTrade.add(lInstIsin, 					new GridBagConstraints2(0, 2, 1, 1, 0.5, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		pTrade.add(tfInstIsin, 					new GridBagConstraints2(0, 3, 1, 1, 0.5, 0.0, NWEST, HORIZ, insets4808, 0, 0));

		pTrade.add(lTickerUsed, 				new GridBagConstraints2(0, 4, 1, 1, 0.5, 0.0, NWEST, HORIZ, insets4800, 0, 0));
		pTrade.add(tfTickerUsed, 				new GridBagConstraints2(0, 5, 1, 1, 0.5, 1.0, NWEST, HORIZ, insets4808, 0, 0));

		
		add(tpTrade, 							new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, NWEST, BOTH, insets4880, 0, 0));
		add(tpParameter, 						new GridBagConstraints2(1, 0, 1, 1, 1.0, 1.0, NWEST, BOTH, insets4880, 0, 0));

		setReadOnly();
		setBackgroundForAllPanels(UIManager.getColor("DetailsPanel.background"));
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;

		propertyHandler.add(TradeParameterModel.TPM_INST_ISIN,				tfInstIsin, ro);
		propertyHandler.add(TradeParameterModel.TPM_INST_NAME,				tfInstName, ro);
		propertyHandler.add(TradeParameterModel.TPM_TRADE_TICKER_USED,		tfTickerUsed, ro);
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
		super.setModel(newModel);
		fillView();
	}

}
