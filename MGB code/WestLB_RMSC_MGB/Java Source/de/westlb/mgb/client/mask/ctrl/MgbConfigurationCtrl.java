package de.westlb.mgb.client.mask.ctrl;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.view.shared.AddonListView;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;
import de.westlb_systems.xaf.ui.view.View;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MgbConfigurationCtrl extends Ctrl {
     @Override
    public void actionRequested(UserEvent ue) {
     	switch (ue.getAction()) {
     		case AddonListView.EV_NEW:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_CONFIGURATION_EDIT, ue.getParameters()));
				refresh(ue.getSource());
				break;
     		case AddonListView.EV_OPEN:
     		case AddonListView.EV_EDIT:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_CONFIGURATION_EDIT, ue.getParameters()));
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
