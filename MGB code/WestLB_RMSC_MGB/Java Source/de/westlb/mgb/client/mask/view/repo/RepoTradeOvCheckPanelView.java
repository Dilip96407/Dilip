package de.westlb.mgb.client.mask.view.repo;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.repo.RepoTradeOvCheckPanelModel;
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
public class RepoTradeOvCheckPanelView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3032100286381504811L;
	private String RESOURCE_BASE = getResourceBase();
	private static final int DECIMAL_PLACES_4 = 4;
	private static final int DECIMAL_PLACES_2 = 2;
	private static final int DECIMAL_PLACES_0 = 0;
	
	
	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	// UI - Components
	private SLabel lRepoRate					= new SLabel();
	private SLabel lMarketPrice					= new SLabel();
	private SLabel lBasePointDifference			= new SLabel();
	private SLabel lBasePointTolerance			= new SLabel();
	private SLabel lProfitLossEffect			= new SLabel();
	private SLabel lForeignExchangeRate			= new SLabel();
	private SLabel lPortfolio					= new SLabel();
	private SLabel lNpv							= new SLabel();

	// UI - Components
	private STextField	tfRepoRate				= new VDecimalField(DEF_COL_10, DECIMAL_PLACES_4);
	private STextField	tfMarketPrice			= new VDecimalField(DEF_COL_10, DECIMAL_PLACES_4);
	private STextField	tfBasePointDifference	= new VDecimalField(DEF_COL_10, DECIMAL_PLACES_0);
	private STextField	tfBasePoinTolerance		= new VDecimalField(DEF_COL_10, DECIMAL_PLACES_0);
	private STextField	tfProfitLossEffect		= new VDecimalField(DEF_COL_10, DECIMAL_PLACES_2);
	private STextField	tfForeignExchangeRate	= new VDecimalField(DEF_COL_10, DECIMAL_PLACES_2);
	private STextField	tfPortfolio				= new STextField(DEF_COL_10);	
	private STextField tfNpv					= new VDecimalField(DEF_COL_10, DECIMAL_PLACES_2);
					
	/**
	 * Constructor for CheckStateView.
	 */
	public RepoTradeOvCheckPanelView() {
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
	public RepoTradeOvCheckPanelView(Model model) {
		super(model);
	}

	/**
	 * Felder des Views mit Werten aus dem Model fuellen
	 */
	private void fillView() {
		RepoTradeOvCheckPanelModel model = getRepoTradeOvCheckPanelModel();
		if (model == null) {
			return;
		}
			
		setAttention(tfRepoRate, model.isPriceToleranceViolation());
		setEmphasized(tfProfitLossEffect);
		setEmphasized(tfMarketPrice);
		
		propertyHandler.syncFields();
	}

	private RepoTradeOvCheckPanelModel getRepoTradeOvCheckPanelModel() {
		if (!(getModel() instanceof RepoTradeOvCheckPanelModel)) {
			return null;
		}
		return (RepoTradeOvCheckPanelModel) getModel();	
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
		lBasePointDifference.setText	(getResourceString(RESOURCE_BASE +"L_BASE_POINT_DIFFERENCE"));
		lBasePointTolerance.setText		(getResourceString(RESOURCE_BASE +"L_BASE_POINT_TOLERANCE"));
		lForeignExchangeRate.setText	(getResourceString(RESOURCE_BASE +"L_FOREIGN_EXCHANGE_RATE"));
		lMarketPrice.setText			(getResourceString(RESOURCE_BASE +"L_MARKET_PRICE"));
		lPortfolio.setText				(getResourceString(RESOURCE_BASE +"L_PORTFOLIO"));
		lProfitLossEffect.setText		(getResourceString(RESOURCE_BASE +"L_PROFIT_LOSS_EFFECT"));
		lRepoRate.setText				(getResourceString(RESOURCE_BASE +"L_REPO_RATE"));
		lNpv.setText					(getResourceString(RESOURCE_BASE +"L_NPV"));
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayout() {
		int r, c;
				
		r = 0; c = 0; // Row 0
	 	this.add(lBasePointDifference,		new GridBagConstraints2(c++, r, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lBasePointTolerance,		new GridBagConstraints2(c++, r, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lForeignExchangeRate,		new GridBagConstraints2(c++, r, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lMarketPrice,				new GridBagConstraints2(c++, r, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lPortfolio,				new GridBagConstraints2(c++, r, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lProfitLossEffect,			new GridBagConstraints2(c++, r, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lNpv,						new GridBagConstraints2(c++, r, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	this.add(lRepoRate,					new GridBagConstraints2(c++, r, 1, 1, 0.0, 0.0, NWEST, NONE, insets8800, 0, 0));	 	
	 	// Dummy which extends in vertical direction

		r = 1; c = 0; // Line 1
	 	this.add(tfBasePointDifference,		new GridBagConstraints2(c++, r, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4880, 0, 0));	 	
	 	this.add(tfBasePoinTolerance,		new GridBagConstraints2(c++, r, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4480, 0, 0));	 	
	 	this.add(tfForeignExchangeRate,		new GridBagConstraints2(c++, r, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4480, 0, 0));	 	
	 	this.add(tfMarketPrice,				new GridBagConstraints2(c++, r, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4480, 0, 0));	 	
	 	this.add(tfPortfolio,				new GridBagConstraints2(c++, r, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4480, 0, 0));	 	
	 	this.add(tfProfitLossEffect,		new GridBagConstraints2(c++, r, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4480, 0, 0));	 	
	 	this.add(tfNpv,						new GridBagConstraints2(c++, r, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4480, 0, 0));	 	
	 	this.add(tfRepoRate,				new GridBagConstraints2(c++, r, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4488, 0, 0));	 	

//		r = 2; c = 0; // Line 1
//	 	this.add(new SLabel(),				new GridBagConstraints2(c++, r, 1, 1, 0.0, 1.0, NWEST, BOTH, insets8800, 0, 0));	 	
	 	
	 	this.setReadOnly(); 	
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
		
		propertyHandler.add(RepoTradeOvCheckPanelModel.P_REPO_BASE_POINT_DIFFERENCE,	tfBasePointDifference, ro);
		propertyHandler.add(RepoTradeOvCheckPanelModel.P_REPO_BASE_POINT_TOLERANCE,		tfBasePoinTolerance, ro);
		propertyHandler.add(RepoTradeOvCheckPanelModel.P_REPO_FOREIGN_EXCHANGE_RATE,	tfForeignExchangeRate, ro);
		propertyHandler.add(RepoTradeOvCheckPanelModel.P_REPO_MARKET_PRICE,				tfMarketPrice, ro);
		propertyHandler.add(RepoTradeOvCheckPanelModel.P_REPO_PORTFOLIO,				tfPortfolio, ro);
		propertyHandler.add(RepoTradeOvCheckPanelModel.P_REPO_PROFIT_LOSS_EFFECT,		tfProfitLossEffect, ro);
		propertyHandler.add(RepoTradeOvCheckPanelModel.P_REPO_NPV,						tfNpv, ro);
		propertyHandler.add(RepoTradeOvCheckPanelModel.P_REPO_RATE,						tfRepoRate, ro);
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof RepoTradeOvCheckPanelModel))
			throw new IllegalArgumentException("Model not instance of RepoTradeOvCheckPanelModel!");
		propertyHandler.setModel(newModel);
		
		super.setModel(newModel);
		fillView();
	}

}
