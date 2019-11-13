package de.westlb.mgb.client.ui.selection_list;

import de.westlb.mgb.client.server.vo.StateCodeVo;
import de.westlb.mgb.client.server.vo.TradeOverviewVo;

/**
 * Selection list for state codes used by automatic checking.
 *
 * @author Manfred Boerner
 */
public abstract class StateCodeList extends AbstractSelectionList {
    /**
     * Creates a new SourceSystemList
     */
    public StateCodeList() {
        super();
    }
        
    /**
	 * Calculates the reclamation state code for a given autoStateCode and manualStateCode
	 * of an trade.
	 */
	public String calculateInitialReclamationReason(TradeOverviewVo tradeOverviewVo) {
		String reason = null;
		String autoStateCode = tradeOverviewVo.getAutomaticStateCode();
		String manualStateCode = tradeOverviewVo.getManualStateCode();
		if (manualStateCode == null && autoStateCode != null) {
			StateCodeVo stateCodeVo = getStateCodeVo(autoStateCode);
			if (stateCodeVo.getReclamationCode() != null) {
				reason = getStateCodeVo(stateCodeVo.getReclamationCode()).getLongDescription();
			}
		} else if (manualStateCode != null) {
			StateCodeVo stateCodeVo = getStateCodeVo(manualStateCode);	
			if (stateCodeVo.getReclamationCode() != null) {
				reason = getStateCodeVo(stateCodeVo.getReclamationCode()).getLongDescription();
			}
		}
		
		return reason;	
	}

	/**
	 * Gets the state code value object for an stateCode.
	 */
	public StateCodeVo getStateCodeVo(String stateCode) {
		Object item = get(stateCode);
		
		if (!(item instanceof StateCodeItem)) {
			return null;
		}

		return ((StateCodeItem)item).getStateCodeVo();
	}

}