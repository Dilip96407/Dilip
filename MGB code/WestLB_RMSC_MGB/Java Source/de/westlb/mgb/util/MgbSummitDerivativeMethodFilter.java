/*
 * Created on Dec 16, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.util;

import java.util.Arrays;

import de.westlb.mgb.model.impl.SummitDerivativeImpl;

/**
 * @author WSY4148
 *
 */
public class MgbSummitDerivativeMethodFilter implements MethodFilter {

	private static final MethodFilter instance = new MgbSummitDerivativeMethodFilter();
	
	private String[] excludedTradeAccessors = {
	        "getAmendUser",
	        "getAmendedDate",
	        "getAsOfDate",
	        "getAssets",
//	        "getAssetPayCurrencys",
//	        "getAssetReceiveCurrencys",
//	        "getAssetStartDates",
//	        "getAssetMaturityDates",
//	        "getAssetPayIndexBasis",
//	        "getAssetReceiveIndexBasis",
//	        "getAssetProductNames",
	        "getAuditCurrent",
	        "getAuditUser",
	        "getAutoCheckConfig",
	        "getAutoCheckPrice",
	        "getBloombergRequests",
	        "getBook",
	        "getBookId",
	        "getCBloombergTradeId",
	        "getCBonification",
	        "getCancelDate",
	        "getChangedBy",
	        "getChangedDate",
	        "getClass",
	        "getComment",
	        "getCompany",
	        "getCounterparty",
	        "getCounterpartyId",
	        "getCreatedBy",
	        "getCreationDate",
	        "getCurrency",
	        "getCurrentAutoState",
	        "getCurrentAutoStateHistEntry",
	        "getCurrentManualState",
	        "getCurrentManualStateHistEntry",
	        "getCurrentReclLevel",
	        "getCurrentReclStateHistEntry",
	        "getCurrentReclamationState",
	        "getDelta",
	        "getDesk",
	        "getDeviation",
	        "getDoneDate",
	        "getEuwaxRequests",
	        "getExercised",
	        "getExternalRequests",
	        "getFrontOfficeMarketRate",
	        "getHistoryEntries",
	        "getId",
	        "getInstrument",
	        "getInternalExternal",
	        "getIsBagatelle",
	        "getIsHighTurnover",
	        "getIsLateDeal",
	        "getIsMccRelevantChange",
	        "getIsStandard",
	        "getJob",
            "getLocationTrader",
	        "getLongId",
	        "getMandant",
	        "getModifiedBy",
	        "getModifiedDate",
	        "getOrderId",
	        "getPremium",
	        "getPrice",
	        "getRequests",
	        "getReutersRequests",
	        "getSettlementDate",
	        "getSingleAsset",
	        "getSourceSystem",
	        "getSourceSystemBloombergId",
	        "getSourceSystemInstrument",
	        "getSourceSystemReutersId",
	        "getStructureId",
	        "getSystemDate",
            "getSystemTimeString",
	        "getTermAssignStatus",
	        "getTermEffDate",
	        "getTermInputDate",
	        "getTermTradeDate",
            "getTradeCategory",
	        //"getTradeDate",
	        "getTradeGroupId",
	        "getTradeId",
	        "getTradeStatus",
	        "getTradeSubType",
	        "getTradeType",
	        "getTrader",
	        "getTraderId",
	        "getTraderName",
	        "getTurnover",
	        "getVega",
	        "getVerifyDate",
	        "getVersion",
	        "getVolume",
	};
	
    private MgbSummitDerivativeMethodFilter() {
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
    	if (!(object instanceof SummitDerivativeImpl)) {
    		return true;
    	}
        return Arrays.binarySearch(excludedTradeAccessors, methodName) < 0;
    }

    @Override
    public boolean translate(Object object, String methodName) {
    	return false;
    }

}
