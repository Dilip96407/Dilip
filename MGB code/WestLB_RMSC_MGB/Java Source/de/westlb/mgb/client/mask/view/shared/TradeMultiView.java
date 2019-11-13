package de.westlb.mgb.client.mask.view.shared;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.application.ActionCommands;
import de.westlb.mgb.client.application.TabDefs;
import de.westlb.mgb.client.mask.model.shared.TradeMultiModel;
import de.westlb_systems.xaf.application.ActionSet;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.event.MultiViewEvent;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.util.Debug;

/**
 * MultiView to display the data of a trade.
 */
public class TradeMultiView extends AbstractMultiView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2987546043650416741L;
	public static final int EV_NEXT_LIST_ITEM			= 1;
	public static final int EV_PREV_LIST_ITEM			= 2;
	public static final int EV_NEW_MANUAL_STATE			= 3;
	public static final int EV_NEW_RECLAMATION_STATE	= 4;
                     
	// MenuItems

	private ActionSet goPrevlistItem = new ActionSet(ActionCommands.GO_PREV_LITEM, true);
	private ActionSet goNextlistItem = new ActionSet(ActionCommands.GO_NEXT_LITEM, true);
	private ActionSet asEditNewManualState = new ActionSet(ActionCommands.EDIT_NEW_MANUAL_STATE, true);
	private ActionSet asEditNewReclState = new ActionSet(ActionCommands.EDIT_NEW_RECLAMATION_STATE, true);

	private Listener listener = new Listener();

	private class Listener implements PropertyChangeListener {
		@Override
        public void propertyChange(PropertyChangeEvent evt) {
			handlePropertyChange(evt);
		}
	}

	/**
	 * Default constructor which creates an empty view.
	 */
	public TradeMultiView() {
		try {
			initComponents();
			initLayout();
			initLabels();
			initProperties();
		} catch (Exception ex) {
			Debug.log(Debug.ERROR, this, "Exception in View-Constuctor");
			Debug.log(ex);
		}
	}
	/**
	 * Constructor which sets a model.
	 */
	public TradeMultiView(Model newModel) {
		this();
		setModel(newModel);
	}
	
	/**
	 * Fills view with data of the assigned model.
	 */
	private void fillView() {
		if (getTradeMultiModel() == null) {
			return;
		}
		TradeMultiModel model = getTradeMultiModel();
		setHeaderTitle((String)model.getProperty(TradeMultiModel.TITLE), "");
		recalcEnabled();
	}
	
	/**
	 * Fire selected, because a tab changed.
	 */
	@Override
    protected void fireSelectionChanged(MultiViewEvent e) {
		super.fireSelectionChanged(e);
	}
	/**
	 * returns the special trade model.
	 */
	public TradeMultiModel getTradeMultiModel() {
		return getModel() instanceof TradeMultiModel ? (TradeMultiModel) getModel() : null;
	}
	
	/**
	 * handle an action from the toolbar or menu.
	 */
	@Override
    public void handleAction(String actionCommand) {
		super.handleAction(actionCommand);
	
		if (actionCommand.equals(ActionCommands.GO_NEXT_LITEM)) {
		   fireUserEvent(new UserEvent(this, EV_NEXT_LIST_ITEM, getModel().getBusinessObject()));
		} else 
		

		if (actionCommand.equals(ActionCommands.GO_PREV_LITEM)) {
		   fireUserEvent(new UserEvent(this, EV_PREV_LIST_ITEM, getModel().getBusinessObject()));
		}  else

		if (actionCommand.equals(ActionCommands.EDIT_NEW_MANUAL_STATE)) {
		   fireUserEvent(new UserEvent(this, EV_NEW_MANUAL_STATE, getModel().getBusinessObject()));
		}  else

		if (actionCommand.equals(ActionCommands.EDIT_NEW_RECLAMATION_STATE)) {
		   fireUserEvent(new UserEvent(this, EV_NEW_RECLAMATION_STATE, getModel().getBusinessObject()));
		} 
		
	}
	
	/**
	 *
	 */
	private void handlePropertyChange(PropertyChangeEvent ev) {
		if (TradeMultiModel.TITLE  == ev.getPropertyName()) {
			setHeaderTitle((String) ev.getNewValue(), "");
			recalcEnabled();
			return;
		} else 
		
		if (TradeMultiModel.TAB_DEF == ev.getPropertyName()) {
            updateTabDef();
		} else 
		
		super.propertyChange(ev);
	}
	
    private void updateTabDef() {
        init(getTradeMultiModel().getTabDef());
        updateLabels();		
    }
	
	/**
	 * Initialize all components.
	 */
	private void initComponents() {
		setHeaderColor(UIManager.getColor("TradeView.titleBackground1"), UIManager.getColor("TradeView.titleBackground2"));
		setHeaderTextColor(UIManager.getColor("TradeView.foreground"));
		setResourceStringBase("TAB_");
		
		init(TabDefs.TRADE);
	
		// Aktivieren Menu und ToolBar
		//goMailToAction.setEnabled(true);
		
		setActions(new ActionSet[] {
			goPrevlistItem,
			goNextlistItem,
			asEditNewManualState,
			asEditNewReclState,
		});
	}

	/**
	 * Initialisierung der Properties
	 */
	private void initProperties() {
	}
	
	/**
	 * Initializes the layout of the component.
	 */
	private void initLayout() throws Exception {
	}
	
	private void recalcEnabled() {
		TradeMultiModel model = getTradeMultiModel();
		if (model == null) {
			return;
		}
		
		goNextlistItem.setEnabled(model.getSelectedIndex() < model.size() - 1);
		goPrevlistItem.setEnabled(model.getSelectedIndex() > 0);
	}

	/**
	 * Ueberschreiben der Methode aus View um eine Typ-Pruefung durchzufuehren
	 * und einen ProperyHandler anzuhaengen
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof TradeMultiModel)) {
			throw new IllegalArgumentException("Model not instance of TradeMultiModel");
		}
	
		if(getModel() != null) {
			getModel().removePropertyChangeListener(listener);
		}
		
		super.setModel(newModel);
		updateTabDef();
	
		if(getModel() != null) {
			getModel().addPropertyChangeListener(listener);
		}

		fillView();
	}
	
}
