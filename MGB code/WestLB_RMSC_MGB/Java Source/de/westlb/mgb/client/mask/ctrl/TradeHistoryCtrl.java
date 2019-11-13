package de.westlb.mgb.client.mask.ctrl;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.view.shared.AbstractView;
import de.westlb.mgb.client.mask.view.shared.TradeHistoryView;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;

/**
 * @author WSY4148
 *
 * Handels user events fired by TradeHistoryView
 */
public class TradeHistoryCtrl extends AbstractCtrl  {
     @Override
    public void actionRequested(UserEvent ue) {
     	switch (ue.getAction()) {
        	case TradeHistoryView.EV_NEW_MAIL:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_NEW_MAIL, ue.getParameters()));
				((AbstractView)ue.getSource()).mayBeRefresh();
        		break;
        	default:
        		break;
        }
    }
}
