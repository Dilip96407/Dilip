package de.westlb.mgb.client.mask.ctrl;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.view.shared.StornoGroupView;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;

/**
 * @author WSY4148
 *
 * Handels user events fired by EmployeeListView
 */
public class StornoGroupCtrl extends Ctrl {
     @Override
    public void actionRequested(UserEvent ue) {
     	switch (ue.getAction()) {
     		case StornoGroupView.EV_OPEN:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_TRADE_MULTIVIEW, ue.getParameters()));
				break;
     	}
     }
     
 
}
