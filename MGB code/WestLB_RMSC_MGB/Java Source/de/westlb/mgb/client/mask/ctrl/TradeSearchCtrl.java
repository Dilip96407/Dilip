package de.westlb.mgb.client.mask.ctrl;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.view.shared.TradeSearchDialog;
import de.westlb.mgb.client.server.vo.TradeSearchParamsVo;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;

/**
 * Control class for the check state view
 *
 * @author: Manfred Boerner
 *
 */
public class TradeSearchCtrl extends Ctrl {
	/**
	 * UserEvent bearbeiten
	 *
	 * Creation date: (18.07.00 11:12:08)
	 * @param: event UserEvent
	 *
	 */
	@Override
    public void actionRequested(UserEvent event) {
		switch (event.getAction()) {
			case TradeSearchDialog.TRADELIST :
				if (event.getParameters() instanceof TradeSearchParamsVo) {
					TradeSearchParamsVo params = (TradeSearchParamsVo) event.getParameters();
					fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_TRADE_LIST, params));
				}
				break;
			default :
				break;
		}
		return;
	}
}
