package de.westlb.mgb.client.mask.ctrl;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.view.shared.ExchangeListView;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;
import de.westlb_systems.xaf.ui.view.View;

/**
 * @author WSY4148
 *
 * Handels user events fired by EmployeeListView
 */
public class ExchangeListCtrl extends Ctrl {
     @Override
    public void actionRequested(UserEvent ue) {
     	switch (ue.getAction()) {
     		case ExchangeListView.EV_NEW:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_EXCHANGE_EDIT, ue.getParameters()));
				refresh(ue.getSource());
				break;
     		case ExchangeListView.EV_OPEN:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_EXCHANGE_VIEW, ue.getParameters()));
				refresh(ue.getSource());
				break;
     		case ExchangeListView.EV_EDIT:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_EXCHANGE_EDIT, ue.getParameters()));
				refresh(ue.getSource());
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
