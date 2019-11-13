package de.westlb.mgb.client.mask.ctrl;

import java.io.Serializable;

import de.westlb.mgb.client.application.ApplicationComponentBox;
import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.application.MayBeRefreshSupport;
import de.westlb.mgb.client.mask.model.shared.TradeMultiModel;
import de.westlb.mgb.client.mask.view.shared.TradeMultiView;
import de.westlb.mgb.client.ui.idlistmodel.IdListModel;
import de.westlb_systems.xaf.application.ContentSet;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.MultiViewCtrl;
import de.westlb_systems.xaf.ui.event.MultiViewEvent;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.MultiView;
import de.westlb_systems.xaf.ui.view.View;

/**
 * Kontollklasse fuer den Kunde(Multi)View
 */
public class TradeMultiCtrl extends MultiViewCtrl {

	private Serializable selectedTradeId;
	/**
	 * Component box that creates all contentSets requested by the 
	 * trade control.
	 */
	ApplicationComponentBox compBox = new ApplicationComponentBox();

	/**
	 * Default Konstruktor
	 */
	public TradeMultiCtrl() {
	}

	/**
	 * Handels user events fired by the TradeView
	 */
	@Override
    public void actionRequested(UserEvent ev) {
		if (!(ev.getSource() instanceof TradeMultiView)) {
			return;
		}

		TradeMultiView view = (TradeMultiView) ev.getSource();
		TradeMultiModel model = view.getTradeMultiModel();

		switch (ev.getAction()) {
			case TradeMultiView.EV_NEXT_LIST_ITEM :
				moveRelative(view, 1);
				break;
			case TradeMultiView.EV_PREV_LIST_ITEM :
				moveRelative(view, -1);
				break;
			case TradeMultiView.EV_NEW_MANUAL_STATE :
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_NEW_TRADE_STATE, model.getSelectedId()));
				break;
			case TradeMultiView.EV_NEW_RECLAMATION_STATE :
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_NEW_TRADE_RECLAMATION, model.getSelectedId()));
				break;
		}
	}

	public void moveRelative(TradeMultiView view, int i) {
		IdListModel idList = view.getTradeMultiModel().getTradeIdListModel();
		int newIndex = idList.getSelectedIndex() + i;
		if (idList.moveTo(newIndex)) {
			View currentView = (View) view.getComponentAt(view.getSelectedIndex());
			Model model = currentView.getModel();
			if (model != null) {
				//old model.setBusinessObject(selectedTradeId);
				//old selectedTradeId = idList.getSelectedId();
				// New clear set current ContentSet and force recreate
				// by setting selected index to the current selected index
				view.setContentSetAt(view.getSelectedIndex(), null);
				view.setSelectedIndex(view.getSelectedIndex());
			}
		}

		// fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_TRADE_OVERVIEW, idList));
	}

	/**
	 * Implementes the abstract method of the super class.
	 * Creates a content set with the parameter send by the MultiView
	 */
	@Override
    public ContentSet createContentSet(MultiViewEvent ev) {
		TradeMultiView view = (TradeMultiView) ev.getSource();
		// Add this to the user event listeners to get next and previous
		// events.
		view.addUserEventListener(this);
		TradeMultiModel model = view.getTradeMultiModel();
		selectedTradeId = model.getSelectedId();
		return compBox.createContentSet(ev.getSelectedContentId(), model.getSelectedId(), view);
	}

	/**
	 * Insert the method's description here.
	 * 
	 */
//	private boolean showDialog(String contentID, Object parameter, TradeMultiView view) {
//		ApplicationComponentBox box = new ApplicationComponentBox();
//
//		// Dialog starten
//		if (contentID != null) {
//			ContentSet set = box.createContentSet(contentID, parameter, view);
//			if (set != null && set.getViewDialog() instanceof DialogViewer) {
//				return ((DialogViewer) set.getViewDialog()).showDialog();
//			}
//		}
//		return false;
//	}
	/**
	 * @see de.westlb_systems.xaf.ui.event.MultiViewListener#selectionChanged(MultiViewEvent)
	 * 
	 * Function is called by the framework whenever the selected is to be changed.
	 * Additionally to the standard functionality it sends a mayBeRefresh message to
	 * the newly selected view.
	 */
	@Override
    public void selectionChanged(MultiViewEvent ev) {
		super.selectionChanged(ev);

		MultiView view = (MultiView) ev.getSource();
		ContentSet contentSet = view.getContentSetAt(ev.getSelectedIndex());

		boolean refreshed = false;

		// If the tradeId of the view model has changed in a subview of the multiview, 
		// change the tradeId of the new selected subview.
		if (contentSet.getViewComponent() instanceof View) {
			View tabView = (View) contentSet.getViewComponent();
			Model tabModel = tabView.getModel();
			if (tabModel != null && tabModel.getBusinessObject() != null) {
				if (!tabModel.getBusinessObject().equals(selectedTradeId)) {
					tabModel.setBusinessObject(selectedTradeId);
					refreshed = true;
				}
			}
		}
		// Check if a refresh is necessary because data changed
		if (!refreshed && contentSet.getViewComponent() instanceof MayBeRefreshSupport) {
			MayBeRefreshSupport mayBeRefreshSupport = (MayBeRefreshSupport) contentSet.getViewComponent();
			mayBeRefreshSupport.mayBeRefresh();
			refreshed = true;
		}

	}

}
