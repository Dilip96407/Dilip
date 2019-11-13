package de.westlb.mgb.client.mask.ctrl;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.view.shared.EmployeeOverviewView;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;

/**
 * @author WSY4148
 *
 * Handels user events fired by EmployeeListView
 */
public class EmployeeOverviewCtrl extends Ctrl {
     @Override
    public void actionRequested(UserEvent ue) {
     	switch (ue.getAction()) {
     		case EmployeeOverviewView.EV_EMPLOYEE_EDIT:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_EDIT_EMPLOYEE, ue.getParameters()));
				break;
     		case EmployeeOverviewView.EV_TRADERCODE_NEW:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_OPEN_EMPLOYEE, ue.getParameters()));
				break;
     	}
     }
}
