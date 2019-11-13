package de.westlb.mgb.client.mask.ctrl;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.view.shared.StatisticDataSelectionDialog;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;

/**
 * @author WSY4148
 *
 * Handels user events fired by EmployeeListView
 */
public class StatisticDataSelectionCtrl extends Ctrl {
     @Override
    public void actionRequested(UserEvent ue) {
     	if (!(ue.getSource() instanceof StatisticDataSelectionDialog)) {
     		return;
     	}
		StatisticDataSelectionDialog view = (StatisticDataSelectionDialog)ue.getSource();
     	
     	switch (ue.getAction()) {
     		case StatisticDataSelectionDialog.EV_EXECUTE:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_STATISTIC_REPORT, view.getStatisticDataSelectionModel().getBusinessObject()));
				break;
     	}
     }
}
