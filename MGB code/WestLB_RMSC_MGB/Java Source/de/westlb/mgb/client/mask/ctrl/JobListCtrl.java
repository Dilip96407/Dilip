package de.westlb.mgb.client.mask.ctrl;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.view.shared.JobListView;
import de.westlb.mgb.client.server.vo.TradeSearchParamsVo;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;
import de.westlb_systems.xaf.ui.view.View;

/**
 * @author WSY4148
 *
 * Handels user events fired by JobListView
 */
public class JobListCtrl extends Ctrl {
     @Override
    public void actionRequested(UserEvent ue) {
		TradeSearchParamsVo params = null;
     	switch (ue.getAction()) {
     		case JobListView.EV_NEW:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_JOB_NEW, ue.getParameters()));
				refresh(ue.getSource());
				break;
            case JobListView.EV_LOG_VIEW:
                fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_LOG_VIEW, ue.getParameters()));
                refresh(ue.getSource());
                break;
			case JobListView.EV_SHOW_TRADES:
				params = new TradeSearchParamsVo();
				params.setJobIds((Long[])ue.getParameters());
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_TRADE_LIST, params));
				break;
			case JobListView.EV_SHOW_PRICE_DEVIATION:
				params = new TradeSearchParamsVo();
				params.setJobIds((Long[])ue.getParameters());
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_PRICE_DEVIATION_LIST, params));
				break;
			case JobListView.EV_SHOW_PRICE_DEVIATION_STAT:
				params = new TradeSearchParamsVo();
				params.setJobIds((Long[])ue.getParameters());
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_PRICE_DEVIATION_STATISTIC_LIST, params));
				break;
			case JobListView.EV_SELECT_JOBS:
				params = new TradeSearchParamsVo();
				params.setJobIds((Long[])ue.getParameters());
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_JOBS_SELECTION_MENU, params));
				break;
			default:
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
