/*
 * Created on Dec 16, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.util;

import java.util.Arrays;

import de.westlb.mgb.model.impl.TradeImpl;

/**
 * @author WSY4148
 *
 */
public class MgbMethodFilter implements MethodFilter {

	private static final MethodFilter instance = new MgbMethodFilter();
	
	private String[] excludedTradeAccessors = {
		"getAssets",
		"getAutoCheckConfig",
		"getAutoCheckPrice",
		"getBloombergRequests",
		"getBook",
		"getChangedBy",
		"getChangedDate",
		"getCreatedBy",		
		"getCreationDate",
		"getCurrentAutoState",
		"getCurrentAutoStateHistEntry",
		"getCurrentManualState",
		"getCurrentManualStateHistEntry",
		"getCurrentReclamationState",
		"getCurrentReclStateHistEntry",
		"getHistoryEntries",
		"getId",
		"getJob",		
        "getLocationTrader",
		"getLongId",		
		"getRequests",
		"getReutersRequests",
		"getSystemDate",
        "getSystemTimeString",
		"getTrader",
	};
	
    private MgbMethodFilter() {
        super();
        Arrays.sort(excludedTradeAccessors);
    }
    
    public static synchronized MethodFilter getInstance() {
    	return instance;
    }

    /* (Kein Javadoc)
     * @see de.westlb.mgb.util.MethodFilter#accept(java.lang.Class, java.lang.String)
     */
    @Override
    public boolean accept(Object object, String methodName) {
    	if (!(object instanceof TradeImpl)) {
    		return true;
    	}
        return Arrays.binarySearch(excludedTradeAccessors, methodName) < 0;
    }

    @Override
    public boolean translate(Object object, String methodName) {
    	return false;
    }

}
