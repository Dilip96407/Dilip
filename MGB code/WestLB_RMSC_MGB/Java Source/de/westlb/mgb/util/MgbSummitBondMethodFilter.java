/*
 * Created on Dec 16, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.util;

import java.util.Arrays;

import de.westlb.mgb.model.impl.SummitBondImpl;

/**
 * @author WSY4148
 *
 */
public class MgbSummitBondMethodFilter implements MethodFilter {

	private static final MethodFilter instance = new MgbSummitBondMethodFilter();
	
	private String[] excludedTradeAccessors = {
			"getAlias",
			"getAmendedDate",
			"getAutoCheckConfig",
			"getAutoCheckPrice",
			"getBagatelleLimit",
			"getBloombergInstrument",
			"getBloombergRequests",
			"getBonification",
			"getBook",
			"getBookId",
			"getCBloombergTradeId",
			"getCBonification",
			"getCategory",
			"getChangedBy",
			"getChangedDate",
			"getClass",
			"getCompany",
			"getCounterparty",
			"getCounterpartyId",
			"getCounterpartyReference",
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
			"getDescription",
			"getEuwaxRequests",
//			"getExpireDay",
			"getExternalRequests",
		    "getFrontOfficeMarketRate",
			"getFxEuroRate",
			"getHistoryEntries",
			"getId",
			"getInstrument",
			"getInstrumentExpireDay",
			"getInstrumentName",
			"getInstrumentStartDay",
			"getIsBackToBack",
			"getIsBagatelle",
			"getIsCategoryWithBloombergPrices",
			"getIsCategoryWithHighLowBloombergPrices",
			"getIsCurrentVersion",
			"getIsExchangeTraded",
			"getIsIntraDayStorno",
			"getIsLateDeal",
			"getIsMccRelevantChange",
			"getIsOutOfTurnoverLimit",
			"getIsSmallCustomer",
			"getIsStorno",
			"getIssuer",
			"getJob",
			"getLegalName",
            "getLocationTrader",
			"getLongId",
			"getMandant",
            "getMarketPrice",
            "getMarketer",
			"getModifiedBy",
			"getModifiedDate",
			"getOrderId",
			"getPrice",
			"getRequests",
			"getReutersRequests",
			"getSettlementDate",
			"getSourceSystem",
			"getSourceSystemBloombergId",
//			"getSourceSystemInstrument",
			"getSourceSystemReutersId",
//			"getStartDay",
			"getStatus",
			"getStructure",
			"getSubType",
			"getSystemDate",
			"getSystemTimeString",
			"getTheoreticalPrice",
            "getTradeCategory",
//			"getTradeDate",
			"getTradeGroupId",
			"getTradeId",
//			"getTradePrice",
			"getTradeType",
			"getTrader",
			"getTraderId",
			"getTraderName",
			"getTurnoverLimit",
			"getUpdatedBy",
			"getVersion",
			"getVolume",
	};
	
    private MgbSummitBondMethodFilter() {
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
    	if (!(object instanceof SummitBondImpl)) {
    		return true;
    	}
        return Arrays.binarySearch(excludedTradeAccessors, methodName) < 0;
    }

    @Override
    public boolean translate(Object object, String methodName) {
    	return "getTradeDate".equals(methodName) && object instanceof SummitBondImpl;
    }
}
