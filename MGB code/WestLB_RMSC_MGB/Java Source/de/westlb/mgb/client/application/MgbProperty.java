package de.westlb.mgb.client.application;

/**
 * Created on 20.08.2003 
 * 
 * MgbProperty.java 
 * 
 * Copyright (c) 2003, WestLB.
 * 
 * All rights reserved This information contained herin may not be 
 * used in whole or in part without the expressed written
 * consent of WestLB Systems 
 * 
 * Description: Interface which contains the names and values of properties
 * loaded by the PropertyFactory. 
 * 
 * @version 1.0
 * @author M. Boerner
 */
public interface MgbProperty {
	/** A logical name of the service provider instantiated by the 
	 *  MgbServiceFactory. */
    public final static String MGB_SVC_PROVIDER = "mgb.svc_provider";
    public final static String MGB_ENDPOINT = "mgb.endpoint";
	
	public final static String V_MGB_SVC_PROVIDER_SOAP_CLIENT = "SoapClient";
	public final static String V_MGB_SVC_PROVIDER_SERVER_IMPL = "ServerImpl";
	public final static String V_MGB_SVC_PROVIDER_SERVLET_CLIENT = "ServletClient";

	public final static String MGB_SOAP_TIMEOUT = "mgb.soap.timeout";
	
	public final static String MARKET_DATA_USE_PROXY = "mgb.marketData.useProxy";
	public final static String MARKET_DATA_USE_DATABASE_DATA_VIA_PROXY = "mgb.marketData.useDatabaseDataProxy";
	public final static String MARKET_DATA_GENERATE_DUMMY_VALUES = "mgb.marketData.generateDummyValues";
	public final static String MARKET_DATA_DUMMY_VALUES_PRICE_PERCENTAGE_DEVIATION = "mgb.marketData.dummyValuesPricePercentageDeviation";
	public final static String MARKET_DATA_DUMMY_VALUES_TIME_MINUTES_DEVIATION = "mgb.marketData.dummyValuesTimeMinutesDeviation";
	public final static String MARKET_DATA_MAX_RETRY = "mgb.marketData.bloomberg.maxRetry";

	public final static String LOGGING_ON_LOG4J_PROPERTIES = "mgb.logging_on.log4j.properties";
	public final static String LOGGING_OFF_LOG4J_PROPERTIES = "mgb.logging_off.log4j.properties";
	
	public final static String BLOOMBERG_REQUEST_INTERVALS = "mgb.bloombergRequester.requestIntervals";
	public final static String BLOOMBERG_HISTORIC_REQUEST_FIELDS = "mgb.bloombergRequester.historicRequestFields";
	public final static String BLOOMBERG_GENERIC_REQUEST_FIELDS = "mgb.bloombergRequester.genericRequestFields";
	public final static String BLOOMBERG_HIGH_LOW_REQUEST_FIELDS = "mgb.bloombergRequester.highLowRequestFields";
	public final static String BLOOMBERG_BID_ASK_REQUEST_FIELDS = "mgb.bloombergRequester.bidAskRequestFields";
	
	public final static String EUWAX_REQUEST_INTERVALS = "mgb.euwaxRequester.requestIntervals";
	public final static String EUWAX_HOST = "mgb.euwaxRequester.host";
	public final static String EUWAX_URL = "mgb.euwaxRequester.url";
	public final static String EUWAX_WESTLB_PROXY_HOST = "mgb.euwaxRequester.wlbProxy.host";
	public final static String EUWAX_WESTLB_PROXY_PORT = "mgb.euwaxRequester.wlbProxy.port";
	public final static String EUWAX_REQUEST_PARAM_WKN = "mgb.euwaxRequester.requestParam.wkn";
	public final static String EUWAX_REQUEST_PARAM_DAY = "mgb.euwaxRequester.requestParam.day";
	public final static String EUWAX_REQUEST_PARAM_MONTH = "mgb.euwaxRequester.requestParam.month";
	public final static String EUWAX_REQUEST_PARAM_YEAR = "mgb.euwaxRequester.requestParam.year";
	public final static String EUWAX_REQUEST_PARAM_BEGIN_HOUR = "mgb.euwaxRequester.requestParam.beginHour";
	public final static String EUWAX_REQUEST_PARAM_END_HOUR = "mgb.euwaxRequester.requestParam.endHour";
	public final static String EUWAX_REQUEST_PARAM_BEGIN_MINUTE = "mgb.euwaxRequester.requestParam.beginMinute";
    public final static String EUWAX_REQUEST_PARAM_END_MINUTE = "mgb.euwaxRequester.requestParam.endMinute";
	public final static String EUWAX_REQUEST_DELAY_MILLIES = "mgb.euwaxRequester.request.delayMillies";

	public final static String EUWAX_RESPONSE_DATE_FORMAT = "mgb.euwaxRequester.response.dateFormat";
	public final static String EUWAX_RESPONSE_DECIMAL_SEPARATOR = "mgb.euwaxRequester.response.decimalSeparator";
	public final static String EUWAX_RESPONSE_TIME_HEADER = "mgb.euwaxRequester.response.timeHeader";
	public final static String EUWAX_RESPONSE_MARKET_HEADER = "mgb.euwaxRequester.response.marketHeader";
	public final static String EUWAX_RESPONSE_HEADER_LINE_OFFSET = "mgb.euwaxRequester.response.headerLineOffset";	
	public final static String EUWAX_RESPONSE_IDX_TIME = "mgb.euwaxRequester.response.idxTime";
	public final static String EUWAX_RESPONSE_IDX_BID = "mgb.euwaxRequester.response.idxBid";
	public final static String EUWAX_RESPONSE_IDX_ASK = "mgb.euwaxRequester.response.idxAsk";
    public final static String EUWAX_RESPONSE_TRAILER_LINES = "mgb.euwaxRequester.response.trailerLines";   
    public final static String EUWAX_RESPONSE_NO_DATA_YET_MESSAGE = "mgb.euwaxRequester.response.noDataYetMessage";
    public final static String EUWAX_RESPONSE_DISPLAY_LIMIT = "mgb.euwaxRequester.response.displayLimit";

}
