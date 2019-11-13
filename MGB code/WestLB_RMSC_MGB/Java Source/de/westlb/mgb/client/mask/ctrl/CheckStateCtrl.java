package de.westlb.mgb.client.mask.ctrl;

import java.rmi.RemoteException;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.view.shared.CheckStateView;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.DataSelectionVo;
import de.westlb.mgb.client.server.vo.JobVo;
import de.westlb.mgb.client.server.vo.MandantVo;
import de.westlb.mgb.client.server.vo.TradeSearchParamsVo;
import de.westlb.mgb.model.definition.MandantDef;
import de.westlb.mgb.model.definition.StateCodeDef;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;

/**
 * Control class for the check state view
 *
 * @author: Manfred Boerner
 *
 */
public class CheckStateCtrl extends Ctrl {
	/**
	 * UserEvent bearbeiten
	 *
	 * Creation date: (18.07.00 11:12:08)
	 * @param: event UserEvent
	 *
	 */
	@Override
    public void actionRequested(UserEvent event) {
		TradeSearchParamsVo params = new TradeSearchParamsVo();
		try {
			// getCurrentDataSelection does not throw an exception. But axis generater needs the signature.
			DataSelectionVo selection = MgbServiceFactory.getService().getCurrentDataSelection();
			params.setMandantCode(selection.getMandantCode());
			JobVo[] jobs = selection.getSelectedJobs();
			Long[] jobIds = new Long[jobs.length];
			for (int i = 0; i < jobIds.length; i++) {
				jobIds[i] = jobs[i].getJobId(); 
			}
			params.setJobIds(jobIds);
			switch (event.getAction()) {
				case CheckStateView.TRADELIST :
					
					String stateCode = (String) event.getParameters();
					if (StateCodeDef.MAN_REQUIRED_BUT_NOT_HANDLED_YET.equals(stateCode)) {
						params.setIsManualCheckRequiredButNotHandled(Boolean.TRUE);
					} else {
						params.setStateCode(stateCode);
					}

					String product = null;
					MandantVo[] mandants = MgbServiceFactory.getService().findAllMandants();
		            for (MandantVo mandant : mandants) {
		            	if (selection.getMandantCode().equals(mandant.getMandantCode()))
		            	{
		            		product = mandant.getProductClass();
		            	}
		            }
					if (MandantDef.PRODUCT_BOND.equals(product)) {
					    fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_TRADE_SEARCH_DIALOG, params));
					} else {
						fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_TRADE_LIST, params));				    
					}
					
					break;
				case CheckStateView.LIST_ALL_TRADES :
					fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_TRADE_LIST, params));
					break;
				case CheckStateView.LIST_AUTO_TRADES :
					params.setIsManualCheckRequired(Boolean.FALSE);
					fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_TRADE_LIST, params));
					break;
				case CheckStateView.LIST_MANUAL_TRADES :
					params.setIsManualCheckRequired(Boolean.TRUE);
					fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_TRADE_LIST, params));
					break;
				case CheckStateView.LIST_RECL_REQUIRED_TRADES :
					params.setIsReclamationRequiredButNotHandled(Boolean.TRUE);
					fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_TRADE_LIST, params));
					break;
				case CheckStateView.LIST_RECL_OPEN_TRADES :
					params.setIsReclamationClosed(Boolean.FALSE);
					fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_TRADE_LIST, params));
					break;
				case CheckStateView.LIST_RECL_CLOSED_TRADES :
					params.setIsReclamationClosed(Boolean.TRUE);
					fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_TRADE_LIST, params));
					break;
				case CheckStateView.LIST_TRADES_LATE_ENTERED:
					params.setIsLateEntry(Boolean.TRUE);
					fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_TRADE_LIST, params));
				default :
					break;
			}
		} catch (RemoteException re) {
		}
		return;
	}
}
