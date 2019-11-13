package de.westlb.mgb.client.mask.view.repo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.TradeOverviewModel;
import de.westlb.mgb.client.mask.view.equity.EquityTradeOverviewView;
import de.westlb.mgb.client.mask.view.shared.AbstractView;
import de.westlb.mgb.client.mask.view.shared.TradeOvResultPanelView;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.application.event.UserEventListener;
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
public class RepoTradeOverviewMarketDataView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7702546611283653731L;

	private String RESOURCE_BASE = getResourceBase();

	/* Constant definitions in UserEvents */
	/* Should be the same in all TradeOverviews, because there is only one
	 * controller for all views
	 */
	public static final int NEW_MANUAL_STATE    	= EquityTradeOverviewView.NEW_MANUAL_STATE;
	public static final int NEW_RECLAMATION_STATE	= EquityTradeOverviewView.NEW_RECLAMATION_STATE;
	public static final int SEND_RECL_MAIL			= EquityTradeOverviewView.SEND_RECL_MAIL;
	public static final int CLOSE_RECL				= EquityTradeOverviewView.CLOSE_RECL;
		
	/** Listener for all events */
	private Listener listener = new Listener();
	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	/** Universal Listener fuer alle Events die im View auftreten */
	private class Listener implements UserEventListener, PropertyChangeListener {
		public Listener() {
		}

		/* (Kein Javadoc)
		 * @see de.westlb_systems.xaf.application.event.UserEventListener#actionRequested(de.westlb_systems.xaf.application.event.UserEvent)
		 */
		@Override
        public void actionRequested(UserEvent ev) {
			switch (ev.getAction()) {
				case TradeOvResultPanelView.EV_NEW_MANUAL_STATE:
					fireUserEvent(new UserEvent(this, NEW_MANUAL_STATE, getModel().getBusinessObject()));
					break;
				case TradeOvResultPanelView.EV_NEW_RECLAMATION_STATE:
					fireUserEvent(new UserEvent(this, NEW_RECLAMATION_STATE, getModel().getBusinessObject()));
					break;
				case TradeOvResultPanelView.EV_SEND_RECL_MAIL:
					fireUserEvent(new UserEvent(this, SEND_RECL_MAIL, getModel().getBusinessObject()));
					break;
				case TradeOvResultPanelView.EV_CLOSE_RECL:
					fireUserEvent(new UserEvent(this, CLOSE_RECL, getModel().getBusinessObject()));
					break;
				default:
					break;
			}
		}

        /**
         * @see java.beans.PropertyChangeListener#propertyChange(PropertyChangeEvent)
         */
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
        }

	}

	// UI - Components
	private TablePanel tpTrade = 						new TablePanel();
	private TablePanel tpCheckResult = 				new TablePanel();
	private TablePanel tpMarketData = 				new TablePanel();
	
	private TradeOvResultPanelView pCheckResult = 		new TradeOvResultPanelView(TradeOvResultPanelView.LAYOUT_VERTICAL);

	// Repo specific sub panels
	private TablePanel tpRepoCheckData =				new TablePanel();
	private RepoTradeOvTradePanelView pRepoTrade =		new RepoTradeOvTradePanelView(); 
	private RepoTradeOvCheckPanelView pRepoCheckData =	new RepoTradeOvCheckPanelView(); 
	private RepoTradeOvMarketDataPanelView pRepoMarketData = new RepoTradeOvMarketDataPanelView(); 
	
	/**
	 * Constructor for RepoTradeOverviewMarketDataView.
	 */
	public RepoTradeOverviewMarketDataView() {
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
	 * Constructor for RepoTradeOverviewMarketDataView.
	 * @param model
	 */
	public RepoTradeOverviewMarketDataView(Model model) {
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
	
	public TradeOverviewModel getTradeOverviewModel() {
		if (!(getModel() instanceof TradeOverviewModel)) {
			return null;
		}
			
		return (TradeOverviewModel) getModel();
	}
	
	/**
	 * Method initComponents.
	 */
	private void initComponents() {
		tpCheckResult.setTable(pCheckResult);
		// listener processes the button events of submask
		pCheckResult.addUserEventListener(listener);
		
	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		tpTrade.setText			(getResourceString(RESOURCE_BASE + "L_HEADER_TRADE"));
		tpCheckResult.setText	(getResourceString(RESOURCE_BASE + "L_HEADER_CHECKRESULT"));
		tpRepoCheckData.setText	(getResourceString(RESOURCE_BASE + "L_HEADER_REPO_CHECK_DATA"));
		tpMarketData.setText	(getResourceString(RESOURCE_BASE + "L_HEADER_MARKET_DATA"));
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
	}
	
	private void initLayout() {		
		tpTrade.setTable(pRepoTrade);
		tpRepoCheckData.setTable(pRepoCheckData);
		tpMarketData.setTable(pRepoMarketData);
						
		// Line 0
		add(tpTrade, 			new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, NWEST, BOTH, insets4808, 0, 0));
		add(tpCheckResult,		new GridBagConstraints2(1, 0, 1, 1, 1.0, 1.0, NWEST, BOTH, insets4808, 0, 0));

		// Line 1
		add(tpMarketData,		new GridBagConstraints2(0, 1, 2, 1, 1.0, 1.0, NWEST, BOTH, insets4808, 0, 0));

		// Line 2
		add(tpRepoCheckData,	new GridBagConstraints2(0, 2, 2, 1, 1.0, 1.0, NWEST, BOTH, insets4888, 0, 0));

		setReadOnly();
		setBackgroundForAllPanels(UIManager.getColor("DetailsPanel.background"));

		pRepoTrade.setReadWrite();
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof TradeOverviewModel)) {
			throw new IllegalArgumentException("Model not instance of TradeOverviewModel!");
		}
		
		super.setModel(newModel);
		propertyHandler.setModel(newModel);
		pCheckResult.setModel(newModel);
		pRepoTrade.setModel(newModel);
		pRepoCheckData.setModel(newModel);
		pRepoMarketData.setModel(newModel);
			
		fillView();
	}

}
