package de.westlb.mgb.client.mask.ctrl;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.view.shared.StatisticListView;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;

/**
 * @author WSY4148
 *
 * Handels user events fired by StatisticListView
 */
public class StatisticListCtrl extends Ctrl {
     @Override
    public void actionRequested(UserEvent ue) {
     	switch (ue.getAction()) {
     		case StatisticListView.EV_DRILL_DOWN:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_STATISTIC_REPORT, ue.getParameters()));
				break;
			case StatisticListView.EV_SHOW_TRADES:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_TRADE_LIST, ue.getParameters()));
				break;
			default:
     	}
     }
     
}
