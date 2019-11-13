package de.westlb.mgb.client.mask.ctrl;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.view.shared.TradeReclamationView;
import de.westlb.mgb.client.server.vo.SendMailParamsVo;
import de.westlb.mgb.model.definition.MailTypeDef;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;

/**
 * @author WSY4148
 *
 * Handels user events fired by TradeReclamationCtrl
 */
public class TradeReclamationCtrl extends Ctrl {
     @Override
    public void actionRequested(UserEvent ue) {
     	switch (ue.getAction() ) {
     		case TradeReclamationView.NEW_RECLAMATION:
     		    SendMailParamsVo params = new SendMailParamsVo();
     			params.setType(MailTypeDef.CONTROLLER_RECLAMATION);
     			params.setTradeId((Long)ue.getParameters());
				fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_NEW_MAIL, params));
				break;
			//fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_NEW_TRADE_RECLAMATION, ue.getParameters()));
			//break;
     		case TradeReclamationView.CLOSE_RECLAMATION:
	            fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_CLOSE_RECLAMATION, ue.getParameters()));
				break;
     	}				
	}
}
