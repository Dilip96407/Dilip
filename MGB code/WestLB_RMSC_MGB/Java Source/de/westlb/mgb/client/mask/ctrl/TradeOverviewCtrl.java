package de.westlb.mgb.client.mask.ctrl;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.view.equity.EquityTradeOverviewView;
import de.westlb.mgb.client.server.vo.SendMailParamsVo;
import de.westlb.mgb.model.definition.MailTypeDef;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;

/**
 * @author WSY4148
 *
 * Handels user events fired by TradeOverviewView
 */
public class TradeOverviewCtrl extends Ctrl {
     @Override
    public void actionRequested(UserEvent ue) {
     	switch (ue.getAction()) {
        	case EquityTradeOverviewView.NEW_MANUAL_STATE:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_NEW_TRADE_STATE, ue.getParameters()));
        		break;
			case EquityTradeOverviewView.NEW_RECLAMATION_STATE:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_NEW_TRADE_RECLAMATION, ue.getParameters()));
				break;
			case EquityTradeOverviewView.CLOSE_RECL:
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_CLOSE_RECLAMATION, ue.getParameters()));
				break;
			case EquityTradeOverviewView.SEND_RECL_MAIL:
				SendMailParamsVo params = new SendMailParamsVo();
				params.setType(MailTypeDef.CONTROLLER_RECLAMATION);
				params.setTradeId((Long)ue.getParameters());
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_NEW_MAIL, params));
				break;
        	default:
        		break;
        }
    }
}
