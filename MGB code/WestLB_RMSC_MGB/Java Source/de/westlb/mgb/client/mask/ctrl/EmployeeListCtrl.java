package de.westlb.mgb.client.mask.ctrl;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.view.shared.EmployeeListView;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;
import de.westlb_systems.xaf.ui.view.View;

/**
 * @author WSY4148
 *
 * Handels user events fired by EmployeeListView
 */
public class EmployeeListCtrl extends Ctrl {
     @Override
    public void actionRequested(UserEvent ue) {
     	switch (ue.getAction()) {
     		case EmployeeListView.EV_EMPLOYEE_NEW:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_EDIT_EMPLOYEE, null));
				refresh(ue.getSource());
				break;
     		case EmployeeListView.EV_EMPLOYEE_EDIT:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_EDIT_EMPLOYEE, ue.getParameters()));
				refresh(ue.getSource());
				break;
     		case EmployeeListView.EV_EMPLOYEE_OPEN:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_OPEN_EMPLOYEE, ue.getParameters()));
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
