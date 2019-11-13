package de.westlb.mgb.model.definition;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MarketDataRequestTypeDef {

	public static final String NO_REQUEST = "NO_REQUEST";

	public static final String HIGH_LOW_REQUEST = "HIGH_LOW_REQUEST";
	public static final String COMBI_HIGH_LOW_REQUEST = "COMBI_HL_REQUEST";

	public static final String HISTORIC_REQUEST = "HISTORIC_REQUEST";
	public static final String COMBI_HISTORIC_REQUEST = "COMBI_HIST_REQUEST";

	
// Request types that do Corp-requests in Blomberg using different sources 
	public static final String HIGH_LOW_SRC_REQUEST = "HIGH_LOW_SRC_REQUEST";
	public static final String HISTORIC_SRC_REQUEST = "HISTORIC_SRC_REQUEST";
	public static final String BID_ASK_SRC_REQUEST = "BID_ASK_SRC_REQUEST";
	public static final String COMBI_SRC_REQUEST = "COMBI_SRC_REQUEST";

// Request types that do requests in from the EUWAX web-page 
    public static final String HIGH_LOW_WEB_REQUEST = "HIGH_LOW_WEB_REQUEST";
    public static final String HISTORIC_WEB_REQUEST = "HISTORIC_WEB_REQUEST";

    
    public static final String GENERIC_REQUEST = "GENERIC_REQUEST";

    
//    public static final String[] BLOOMBERG_SOURCE_REQUEST_TYPES = {COMBI_SRC_REQUEST, HISTORIC_SRC_REQUEST, HIGH_LOW_SRC_REQUEST, BID_ASK_SRC_REQUEST};
////    public static final String[] BLOOMBERG_SOURCE_REQUEST_TYPES = {COMBI_SRC_REQUEST, HISTORIC_SRC_REQUEST, HIGH_LOW_SRC_REQUEST, BID_ASK_SRC_REQUEST, HIGH_LOW_WEB_REQUEST, HISTORIC_WEB_REQUEST};
//
//    public static final String[] BLOOMBERG_REQUEST_TYPES = {HIGH_LOW_REQUEST, COMBI_HIGH_LOW_REQUEST, HISTORIC_REQUEST, COMBI_HISTORIC_REQUEST, HIGH_LOW_SRC_REQUEST, HISTORIC_SRC_REQUEST, BID_ASK_SRC_REQUEST, COMBI_SRC_REQUEST, GENERIC_REQUEST};
////    public static final String[] BLOOMBERG_REQUEST_TYPES = {HIGH_LOW_REQUEST, COMBI_HIGH_LOW_REQUEST, HISTORIC_REQUEST, COMBI_HISTORIC_REQUEST, HIGH_LOW_SRC_REQUEST, HISTORIC_SRC_REQUEST, BID_ASK_SRC_REQUEST, COMBI_SRC_REQUEST, GENERIC_REQUEST, HIGH_LOW_WEB_REQUEST, HISTORIC_WEB_REQUEST};
//
//	public static final String[] EUWAX_REQUEST_TYPES = {HIGH_LOW_WEB_REQUEST, HISTORIC_WEB_REQUEST};

    public static boolean isBloombergRequest(String requestType) {
        return isBloombergPlainRequest(requestType) || isBloombergSourceRequest(requestType);
    }

    public static boolean isBloombergPlainRequest(String requestType) {
        return HIGH_LOW_REQUEST.equals(requestType) 
            || COMBI_HIGH_LOW_REQUEST.equals(requestType) 
            || HISTORIC_REQUEST.equals(requestType) 
            || COMBI_HISTORIC_REQUEST.equals(requestType)
            || GENERIC_REQUEST.equals(requestType);
    }

    public static boolean isBloombergSourceRequest(String requestType) {
	    return COMBI_SRC_REQUEST.equals(requestType) 
	        || HISTORIC_SRC_REQUEST.equals(requestType) 
	        || HIGH_LOW_SRC_REQUEST.equals(requestType) 
	        || BID_ASK_SRC_REQUEST.equals(requestType);
	}

	public static boolean isWebRequest(String requestType) {
	    return HIGH_LOW_WEB_REQUEST.equals(requestType) 
	    || HISTORIC_WEB_REQUEST.equals(requestType);
	}

}
