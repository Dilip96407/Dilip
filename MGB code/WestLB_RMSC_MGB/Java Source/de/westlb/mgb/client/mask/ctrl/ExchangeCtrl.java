package de.westlb.mgb.client.mask.ctrl;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.view.shared.ExchangeView;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;
import de.westlb_systems.xaf.ui.view.View;

/**
 * @author WSY4148
 *
 * Handels user events fired by EmployeeListView
 */
public class ExchangeCtrl extends Ctrl {
     @Override
    public void actionRequested(UserEvent ue) {
     	switch (ue.getAction()) {
     		case ExchangeView.EV_EXCHANGE_EDIT:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_EXCHANGE_EDIT, ue.getParameters()));
				refreshMask(ue);
				break;
     		case ExchangeView.EV_MAPPING_NEW:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_EXCHANGE_MAPPING_EDIT, ue.getParameters()));
				refreshMask(ue);
				break;
     		case ExchangeView.EV_MAPPING_EDIT:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_EXCHANGE_MAPPING_EDIT, ue.getParameters()));
				refreshMask(ue);
				break;
     	}
     }
     
     private void refreshMask(UserEvent ue) {
		View view = (View)ue.getSource();     	
    	if (view.getModel() != null) {
    		view.getModel().reload();	
    	} 	
     }
}
