package de.westlb.mgb.client.mask.view.shared;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.CheckStateModel;
import de.westlb_systems.xaf.application.event.LogMessageEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.application.event.UserEventListener;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.STable;
import de.westlb_systems.xaf.swing.STableEditorEvent;
import de.westlb_systems.xaf.swing.STableEditorListener;
import de.westlb_systems.xaf.swing.STableSelectionEvent;
import de.westlb_systems.xaf.swing.STableSelectionListener;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.ui.view.TablePanel;
import de.westlb_systems.xaf.ui.view.TitlePanel;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author M. Boerner
 *
 * View to display the statistic of trade checking.
 */
public class CheckStateView extends AbstractView {
	/**
     * 
     */
    private static final long serialVersionUID = 3386668245079670103L;
    /* Constant definitions in UserEvents */
	public static final int TRADELIST    = 1;
	public static final int LIST_ALL_TRADES = CheckStateOverviewPanelView.LIST_ALL_TRADES;
	public static final int LIST_AUTO_TRADES = CheckStateOverviewPanelView.LIST_AUTO_TRADES;
	public static final int LIST_MANUAL_TRADES = CheckStateOverviewPanelView.LIST_MANUAL_TRADES;
	public static final int LIST_RECL_REQUIRED_TRADES = CheckStateOverviewPanelView.LIST_RECL_REQUIRED_TRADES;
	public static final int LIST_RECL_OPEN_TRADES = CheckStateOverviewPanelView.LIST_RECL_OPEN_TRADES;
	public static final int LIST_RECL_CLOSED_TRADES = CheckStateOverviewPanelView.LIST_RECL_CLOSED_TRADES;
	public static final int LIST_TRADES_LATE_ENTERED = CheckStateOverviewPanelView.LIST_TRADES_LATE_ENTERED;

	
	/** Listener for all events */
	private Listener listener = new Listener();
	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	/** Universal Listener fuer alle Events die im View auftreten */
	private class Listener implements UserEventListener, STableSelectionListener, STableEditorListener, ActionListener {
		public Listener() {
		}
		@Override
        public void selectionChanged(STableSelectionEvent e) {
			if (e.isDoubleClicked()) {
				rowSelected((Component)e.getSource(), e.getRow());
			}
		}
		@Override
        public void invokeEditor(STableEditorEvent e) {
			if (STableEditorEvent.OPEN == e.getActionCommand()) {
				rowSelected((Component)e.getSource(), e.getRow());
			} 
		}

		@Override
        public void actionPerformed(ActionEvent ae) {
		}
        /**
         * @see de.westlb_systems.xaf.application.event.UserEventListener#actionRequested(UserEvent)
         */
        @Override
        public void actionRequested(UserEvent ev) {
        	if (ev.getSource() == pCheckStateOverview) {
        		fireUserEvent(ev.getAction(), ev.getParameters());
        	}
        }

	}

	// UI - Components
	private TitlePanel pTitle = new TitlePanel();
	private TablePanel tpCheckStateOverview = new TablePanel();
	private CheckStateOverviewPanelView pCheckStateOverview = new CheckStateOverviewPanelView();
	
	private TablePanel tpStateSumAuto = new TablePanel();
	private STable tStateSumAuto = new STable();
	private TablePanel tpStateSumManual = new TablePanel();
	private STable tStateSumManual = new STable();
	

	/**
	 * Constructor for CheckStateView.
	 */
	public CheckStateView() {
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
	 * Constructor for CheckStateView.
	 * @param model
	 */
	public CheckStateView(Model model) {
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
		pTitle.setHeaderTitleColor(UIManager.getColor("CheckStateView.foreground"));
		pTitle.setColorProgress(
			UIManager.getColor("CheckStateView.titleBackground1"), 
			UIManager.getColor("CheckStateView.titleBackground2"));

		tpCheckStateOverview.setTable(pCheckStateOverview);
		pCheckStateOverview.addUserEventListener(listener);
		
		tpStateSumAuto.setTable(tStateSumAuto);
		tStateSumAuto.setGridEnabled(false);
		tStateSumAuto.setRowCounterVisible(false);
		tStateSumAuto.setPopupProvider(STable.OPEN_PROVIDER);
		tStateSumAuto.addSTableSelectionListener(listener);
		tStateSumAuto.addTableEditorListener(listener);
	
		tpStateSumManual.setTable(tStateSumManual);
		tStateSumManual.setGridEnabled(false);
		tStateSumManual.setRowCounterVisible(false);
		tStateSumManual.setPopupProvider(STable.OPEN_PROVIDER);
		tStateSumManual.addSTableSelectionListener(listener);
		tStateSumManual.addTableEditorListener(listener);
		
	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		setTitle(getResourceString("L_TITLE"));
		tpCheckStateOverview.setText(getResourceString("L_HEADER_STATE_SUM_MISC"));
		tpStateSumAuto.setText(getResourceString("L_HEADER_STATE_SUM_AUTO"));
		tpStateSumManual.setText(getResourceString("L_HEADER_STATE_SUM_MANUAL"));
	}

	/**
	 * Method initLayout.
	 */
	private void initLayout() {
		add(pTitle, 				new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));
		add(tpCheckStateOverview,	new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.0, CENTER, BOTH, insets8888, 0, 0));
		add(tpStateSumAuto,			new GridBagConstraints2(0, 2, 1, 1, 1.0, 1.0, CENTER, BOTH, insets8888, 0, 0));
		add(tpStateSumManual,		new GridBagConstraints2(0, 3, 1, 1, 1.0, 1.0, CENTER, BOTH, insets8888, 0, 0));
		setBackgroundForAllPanels(UIManager.getColor("DetailsPanel.background"));
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
		propertyHandler.add(CheckStateModel.AUTO_STATE_TABLE_MODEL,  tStateSumAuto, ro);		
		propertyHandler.add(CheckStateModel.MANUAL_STATE_TABLE_MODEL,  tStateSumManual, ro);		
	}
	
	private void rowSelected(Component source, int row) {
		if (!(getModel() instanceof CheckStateModel)) {
			return;
		}
		CheckStateModel model = (CheckStateModel) getModel();
	
		if(row >= 0) {
			Object stateId = null;
			if (source == tStateSumAuto) {
				stateId = model.getAutoStateId(row);
			} else {
				stateId = model.getManualStateId(row);
			}
			if (stateId != null) {
				fireUserEvent(new UserEvent(this, TRADELIST, stateId));
			}
		} else {
			logMessage(new LogMessageEvent(this, LogMessageEvent.ERROR, getResourceString("CHECKSTATE_E_001")));
		}	
	}
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof CheckStateModel))
			throw new IllegalArgumentException("Model not instance of CheckStateModel!");
		propertyHandler.setModel(newModel);
		pCheckStateOverview.setModel(newModel);
		
		super.setModel(newModel);
		
		if (newModel.getErrorDetails() != null) {
			showError(getModel().getErrorDetails());
		}
		
		fillView();
	}

	/**
	 * Titel setzen
	 *
	 */
	@Override
    public void setTitle(String title) {
		pTitle.setHeaderTitel(title);
		super.setTitle(title);
	}

}
