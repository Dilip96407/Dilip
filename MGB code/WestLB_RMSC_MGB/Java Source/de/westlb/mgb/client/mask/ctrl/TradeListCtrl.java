package de.westlb.mgb.client.mask.ctrl;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.model.shared.TradeListModel;
import de.westlb.mgb.client.mask.view.shared.TradeListView;
import de.westlb.mgb.client.ui.idlistmodel.TradeIdListModel;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;

public class TradeListCtrl extends AbstractCtrl {

	public TradeListCtrl() {
	}
	/**
	 * Handles the action request events fired by the view
	 * @param: 
	 */
	@Override
    public void actionRequested(UserEvent ue) {

		if (!(ue.getSource() instanceof TradeListView)) {
			return;
		}

		switch (ue.getAction()) {
			case TradeListView.EV_EDIT_TRADE :
				editTrade(ue);
				break;
			case TradeListView.EV_NEW_MANUAL_STATE :
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_NEW_TRADE_STATE, ue.getParameters()));
				refresh(ue.getSource());
				break;
			default :
				}
	}

	private void editTrade(UserEvent ue) {
		TradeListView view = (TradeListView) ue.getSource();
		TradeListModel model = (TradeListModel) view.getModel();
		if (model == null) {
			return;
		}

		int selectedRow = ((Integer) ue.getParameters()).intValue();
		Object[] list = model.getSearchResult();
		
		// generate an ordered list from the view instead of taking the list of the model
		Object[] orderedList = new Object[list.length];
		for (int i = 0; i < list.length; i++) {
			orderedList[view.convertRowIndexToView(i)] = list[i];
		}
		selectedRow = view.convertRowIndexToView(selectedRow);
		TradeIdListModel idList = new TradeIdListModel(orderedList);	

		idList.moveTo(selectedRow);
		fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_TRADE_MULTIVIEW, idList));
	}

}
