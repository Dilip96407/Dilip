package de.westlb.mgb.client.mask.ctrl;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.view.shared.TradeReclamationListView;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;
import de.westlb_systems.xaf.ui.view.View;

/**
 * @author WSY4148
 *
 * Handels user events fired by EmployeeListView
 */
public class TradeReclamationListCtrl extends Ctrl {
     @Override
    public void actionRequested(UserEvent ue) {
     	switch (ue.getAction()) {
     		case TradeReclamationListView.EV_PREVIEW:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_NEW_MAIL, ue.getParameters()));
				refresh(ue.getSource());
				break;
     		case TradeReclamationListView.EV_EDIT_EMPLOYEE:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_EDIT_EMPLOYEE, ue.getParameters()));
				refresh(ue.getSource());
				break;
			case TradeReclamationListView.EV_SHOW_TRADE:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_TRADE_MULTIVIEW, ue.getParameters()));
				break;
     	}
     }
     
     public void refresh(Object source) {
		if (source instanceof View) {
			View view = (View)source;
			if (view.getModel() != null) {
				view.getModel().reload();
				view.refresh();
			}
		}
     	
     }
}
