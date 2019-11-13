package de.westlb.mgb.client.mask.view.mmk;

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
import de.westlb_systems.xaf.swing.SButton;
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
public class MmkTradeOverviewView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4812837658307076576L;

	private String RESOURCE_BASE = getResourceBase();

	/* Constant definitions in UserEvents */
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
	private class Listener implements PropertyChangeListener, UserEventListener {
		public Listener() {
		}
		
        /**
         * @see java.beans.PropertyChangeListener#propertyChange(PropertyChangeEvent)
         */
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
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

	}


	// UI - Components
	private TablePanel tpTrade							= new TablePanel();
	private TablePanel tpMarketData						= new TablePanel();
	private TablePanel tpCheckResult					= new TablePanel();
	private MmkTradeOvTradePanelView pTrade				= new MmkTradeOvTradePanelView();
	
	private MmkTradeOvMarketDataPanelView pMarketData	= new MmkTradeOvMarketDataPanelView();
	private TradeOvResultPanelView pCheckResult			= new TradeOvResultPanelView();

 	SButton bNewResult = new SButton();

	/**
	 * Constructor for MmkTradeOverviewView.
	 */
	public MmkTradeOverviewView() {
		try {
			initComponents();
			initLayoutInitial();
			initLabels();
			initProperties();
		} catch (Exception ex) {
			Debug.log(Debug.ERROR, this, "Exception in View-Constuctor");
			Debug.log(ex);
			ex.printStackTrace();
		}
	}

	/**
	 * Constructor for MmkTradeOverviewView.
	 * @param model
	 */
	public MmkTradeOverviewView(Model model) {
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
		tpTrade.setTable(pTrade);
		tpMarketData.setTable(pMarketData);
		tpCheckResult.setTable(pCheckResult);
		// listener processes the button events of submask
		pCheckResult.addUserEventListener(listener);
	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		tpTrade.setText			(getResourceString(RESOURCE_BASE + "L_HEADER_TRADE"));
		tpMarketData.setText	(getResourceString(RESOURCE_BASE + "L_HEADER_MARKETDATA"));
		tpCheckResult.setText	(getResourceString(RESOURCE_BASE + "L_HEADER_CHECKRESULT"));
		bNewResult.setText		(getResourceString(RESOURCE_BASE + "B_NEW_RESULT"));
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
	}
	
	/**
	 * Method initLayout.
	 */
	private void initLayoutInitial() {	
//		SGridLayout gridLayout = new SGridLayout();
//		gridLayout.setHorizontalSplitPosition(0);
//		gridLayout.setHgap(GAP);
//		pButtons.setLayout(gridLayout);
//		pButtons.add(bNewResult);

		add(tpTrade, 			new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4808, 0, 0));
		add(tpMarketData,		new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4808, 0, 0));
		add(tpCheckResult,		new GridBagConstraints2(0, 2, 1, 1, 1.0, 1.0, NWEST, HORIZ, insets4888, 0, 0));
		
		setReadOnly();
		setBackgroundForAllPanels(UIManager.getColor("DetailsPanel.background"));

		pTrade.setReadWrite();
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
		pMarketData.setModel(newModel);
		pCheckResult.setModel(newModel);
		pTrade.setModel(newModel);
		
		fillView();
	}
}
