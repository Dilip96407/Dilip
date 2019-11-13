/*
 * Created on Dec 16, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.util;

import java.util.Arrays;

import de.westlb.mgb.model.impl.SummitRepoImpl;

/**
 * @author WSY4148
 *
 */
public class MgbSummitRepoMethodFilter implements MethodFilter {

	private static final MethodFilter instance = new MgbSummitRepoMethodFilter();
	
	private String[] excludedTradeAccessors = {
			"getAmendedDate",
			"getAutoCheckConfig",
			"getAutoCheckPrice",
			"getBasePointTolerance",
			"getBloombergInstrument",
			"getBloombergRequests",
//			"getBondAccruedInterest",
			"getBook",
			"getBookId",
			"getCBloombergTradeId",
			"getCBonification",
			"getChangedBy",
			"getChangedDate",
			"getClass",
			"getCounterparty",
			"getCounterpartyId",
			"getCreatedBy",
			"getCreationDate",
//			"getCurrency",
			"getCurrentAutoState",
			"getCurrentAutoStateHistEntry",
			"getCurrentManualState",
			"getCurrentManualStateHistEntry",
			"getCurrentReclLevel",
			"getCurrentReclStateHistEntry",
			"getCurrentReclamationState",
			"getCustomerType",
			"getDayCount",
			"getDays",
			"getDealAccruedInterest",
			"getDoneDate",
//			"getEndCash",
//			"getEndDate",
			"getEuwaxRequests",
			"getExceedsMaxPlDiff",
			"getExternalRequests",
			"getForeignExchangeRate",
		    "getFrontOfficeMarketRate",
//			"getFundingSpread",
			"getHistoryEntries",
			"getId",
//			"getInstrument",
			"getInstrumentCode",
			"getInstrumentType",
			"getIsBagatelle",
			"getIsCurrentVersion",
			"getIsIntraDayStorno",
			"getIsLateDeal",
			"getIsMccRelevantChange",
            "getIsOpenEndTerminated",
			"getIsPosiDepo",
			"getIsStorno",
			"getJob",
            "getLocationTrader",
			"getLongId",
			"getMandant",
			"getMarketPriceUnderlying",
			"getMccStatus",
			"getModifiedBy",
			"getModifiedDate",
//			"getNpv",
//			"getOpenEndFlag",
			"getOrderId",
			"getPredecessorInStornoChain",
			"getPrice",
			"getProfitLossDiff",
			"getProfitLossDiffEuro",
			"getRateDiff",
			"getRepoMarketType",
//			"getRepoRate",
			"getRequests",
			"getReutersRequests",
			"getSettlementDate",
			"getSourceSystem",
			"getSourceSystemBloombergId",
			"getSourceSystemInstrument",
			"getSourceSystemReutersId",
//			"getStartCash",
//			"getStartDate",
//			"getStartPrice",
			"getStatus",
			"getSystemDate",
            "getSystemTimeString",
            "getTradeCategory",
			"getTradeDate",
			"getTradeGroupId",
			"getTradeId",
			"getTrader",
			"getTraderId",
			"getTraderName",
			"getTransactionReference",
			"getUnderlyingCategory",
			"getUnderlyingInstrumentType",
//			"getUnderlyingValGroup",
			"getVerifyDate",
			"getVersion",
//			"getVolume",
			"getYieldCurve",
			"getYieldCurveRate",
	};
	
    private MgbSummitRepoMethodFilter() {
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
    	if (!(object instanceof SummitRepoImpl)) {
    		return true;
    	}
        return Arrays.binarySearch(excludedTradeAccessors, methodName) < 0;
    }
    
    @Override
    public boolean translate(Object object, String methodName) {
    	return false;
    }

}
