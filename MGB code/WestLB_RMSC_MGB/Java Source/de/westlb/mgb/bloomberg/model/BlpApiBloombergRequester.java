/*
 * Created on 15.11.2013
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.bloomberg.model;

import java.io.IOException;
import java.text.Format;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.bloomberglp.blpapi.Datetime;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Event;
import com.bloomberglp.blpapi.Message;
import com.bloomberglp.blpapi.MessageIterator;
import com.bloomberglp.blpapi.Request;
import com.bloomberglp.blpapi.Service;
import com.bloomberglp.blpapi.Session;
import com.bloomberglp.blpapi.SessionOptions;

import de.westlb.mgb.client.application.MgbProperty;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.model.Price;
import de.westlb.mgb.model.impl.HistoricPriceImpl;
import de.westlb.mgb.model.impl.IntervalPriceImpl;
import de.westlb.mgb.util.DateTimeUtils;
import de.westlb_systems.xaf.util.PropertyFactory;

public class BlpApiBloombergRequester implements PricingRequester {
    
	static Logger logger = Logger.getLogger(BlpApiBloombergRequester.class);

	private Session session; 

    private Map<String,Double> requestMapping;
    
    private final String[] DEFAULT_HIGH_LOW_REQUEST_FIELDS = { "PX_LOW", "PX_HIGH" };

    private final String[] DEFAULT_HISTORIC_REQUEST_FIELDS = { "TRADE" };

    private final String[] DEFAULT_GENERIC_REQUEST_FIELDS = { "PX_LAST" };

    private final String[] DEFAULT_BID_ASK_REQUEST_FIELDS = { "BID", "ASK" };

    private final int[] DEFAULT_REQUEST_INTERVALS = { 1, 5, 60 };
    
    private final double ORDER_OF_MAGNITUDE = 10.0;

    private static final long EQUAL_TOLERANCE = 1000l;

    private static final String BLOOMBERG_TICKER_FORMAT = "Invalid Bloomberg ticker (Format: <name> [exchange] <yellow key> [type])";

	private static final String GMT = "GMT+0";

    private final Format dateTimeFormat = new DateFormat(DateFormat.DATETIME_FORMAT_LONG);
    private final Format compactDateFormat = new DateFormat(DateFormat.COMPACT_DATE_FORMAT);
   
    private final static String BLOOMBERG_HOST = "localhost";
    
    private final static int BLOOMBERG_PORT = 8194; 

    private final static String BLOOMBERG_SERVICE = "//blp/refdata";

    private String[] highLowRequestFields = null;
    private String[] historicRequestFields = null;
    private String[] genericRequestFields = null;
    private String[] bidAskRequestFields = null;
    private int[] requestIntervals = null;

    private String[] lastRequestedSourceArray = null;
    private String lastRequestedString = null;

    public BlpApiBloombergRequester() {
        // BloombergFields: A String array of fields we want Bloomberg to calculate values for historic bis ask request
        bidAskRequestFields = getBidAskRequestFields();

        // BloombergFields: A String array of fields we want Bloomberg to calculate values for HighLow request
        highLowRequestFields = getHighLowRequestFields();

        // BloombergFields: A String array of fields we want Bloomberg to calculate values for Historic request
        historicRequestFields = getHistoricRequestFields();

        // BloombergFields: A String array of fields we want Bloomberg to calculate values for Generic request
        genericRequestFields = getGenericRequestFields();

        // An int array of minute values that build up the interval around the requeasted price timestamp.
        // Bloomberg can only return prices in an interval but not next to a timestamp. Since Bloomberg return a
        // limited number of prices and not all in the interval, it is nessecary the slowly increase the interval size.
        // (e.g. setting the value to { 5, 45 } )
        requestIntervals = getRequestIntervals();
    }

    public void connect() throws Exception {
        logger.info("Connecting Bloomberg...");
        try {
            SessionOptions sessionOptions = new SessionOptions();
            sessionOptions.setServerHost(BLOOMBERG_HOST);
            sessionOptions.setServerPort(BLOOMBERG_PORT);

            session = new Session(sessionOptions);
            if (!session.start()) {
                logger.error("Failed to start Bloomberg session.");
                throw new PricingRequestException("Failed to start Bloomberg session.");
            }
            if (!session.openService(BLOOMBERG_SERVICE)) {
                logger.error("Failed to open "+BLOOMBERG_SERVICE);
                throw new PricingRequestException("Failed to open "+BLOOMBERG_SERVICE);
            }
        } catch (Exception e) {
            logger.error("Connecting to Bloomberg failed", e);
            throw e;
        }
        logger.info("Connected to Bloomberg");
    }

    public void disconnect() {
        logger.info("Disconnecting Bloomberg...");
        if (session != null) {
            try {
                session.stop(Session.StopOption.ASYNC);
            } catch (InterruptedException e) {
                logger.error("Error while stopping connecting to Bloomberg", e);
            }
        }
        logger.info("Disconnected from Bloomberg");
    }

	/**
	 *  Converts a Bloomberg Datetime to a date represented in the local timezone.
	 * 
	 * <br>
	 *  Bloomberg dates are GMT timeszone. 
	 *
	 *@param  datetime     A GMT Bloomberg date.
	 *@return          The date for local timezone .
	 */
	private Calendar convertBloombergDateToLocalDate(Datetime datetime) {
		GregorianCalendar localCal = new GregorianCalendar(datetime.year(), datetime.month()-1, datetime.dayOfMonth(), datetime.hour(), datetime.minute(), datetime.second());
		localCal.setTimeZone(TimeZone.getTimeZone(GMT));
		localCal.set(Calendar.MILLISECOND, datetime.milliSecond());
		return localCal;
	}

	/**
	 *  Converts a date represented in the local timezone to a Bloomberg Datetime.
	 * 
	 * <br>
	 *  Bloomberg dates are GMT timeszone. 
	 *
	 *@param  calendar     A date with local timezone.
	 *@return          The GMT Bloomberg Datetime.
	 */
	private Datetime convertLocalDateToBloombergDate(Calendar calendar) {
		Datetime datetime = new Datetime();
		datetime.setDatetimeTz(calendar);
		return datetime;
	}

    public void setRequestMapping(Map<String,Double> requestMapping) {
        this.requestMapping = requestMapping;
    }

    private boolean isInsideInterval(double referencePrice, IntervalPriceImpl interval) {
        return interval != null && interval.getPriceMax() > referencePrice && interval.getPriceMin() < referencePrice; 
    }
    
    private boolean isCloserInterval(double referencePrice, IntervalPriceImpl oldInterval, IntervalPriceImpl newInterval) {
        if (newInterval == null) {
            return false;
        }
        if (oldInterval == null) {
            return true;
        }
        double oldDiff = Math.abs(referencePrice - (oldInterval.getPriceMax() + oldInterval.getPriceMin())/2.0d);
        double newDiff = Math.abs(referencePrice - (newInterval.getPriceMax() + newInterval.getPriceMin())/2.0d);
        
        return newDiff < oldDiff; 
    }

    private void validateBloombergTicker(String requestString) {
        if (!isValidBloombergTicker(requestString)) {
            //          throw new PricingRequestException("Invalid Bloomberg ticker (Format: <name> [exchange] <yellow key> [type]) :" + requestString);
            logger.warn(BLOOMBERG_TICKER_FORMAT+" :" + requestString);
        }
    }

    private boolean isValidBloombergTicker(String ticker) {
        boolean result = true;
        StringTokenizer t = new StringTokenizer(ticker);
        if (t.hasMoreTokens()) {
            // read name
            t.nextToken();
            if (t.hasMoreTokens()) {
                String exchange = t.nextToken();
                String yellowKey = null;
                if (exchange.length() != 2) {
                    yellowKey = exchange;
                } else {
                    if (t.hasMoreTokens()) {
                        yellowKey = t.nextToken();
                    } else {
                        result = false;
                        logger.error("No yellow key");
                    }
                }
                if (yellowKey != null
                    && !"Equity".equals(yellowKey)
                    && !"Govt".equals(yellowKey)
                    && !"Corp".equals(yellowKey)
                    && !"Mtge".equals(yellowKey)
                    && !"M-Mkt".equals(yellowKey)
                    && !"Muni".equals(yellowKey)
                    && !"Pfd".equals(yellowKey)
                    && !"Comdty".equals(yellowKey)
                    && !"Index".equals(yellowKey)
                    && !"Curncy".equals(yellowKey)
                    && !"Client".equals(yellowKey)) {
                    result = false;
                    logger.error("Invalid yellow key: " + yellowKey);
                }
                if (t.hasMoreTokens()) {
                    String type = t.nextToken();
                    if (!"ISIN".equals(type)
                        && !"CUSIP".equals(type)
                        && !"ISMA".equals(type)
                        && !"SEDOL".equals(type)
                        && !"SEDOL1".equals(type)
                        && !"SEDOL2".equals(type)
                        && !"CEDEL".equals(type)
                        && !"WERTP".equals(type)
                        && !"VALOREN".equals(type)
                        && !"FRENCH".equals(type)
                        && !"COMNUM".equals(type)
                        && !"JAPAN".equals(type)) {
                        result = false;
                        logger.error("Invalid type: " + type);
                    }
                }
            }
        }
        return result;
    }

    private String[] getHighLowRequestFields() {
        // MB. Added the possibility to modify the requested fields in client.properties for testing. 
        if (highLowRequestFields == null) {
            String fieldListStr = PropertyFactory.getProperty(MgbProperty.BLOOMBERG_HIGH_LOW_REQUEST_FIELDS);
            if (fieldListStr != null) {
                highLowRequestFields = StringUtils.split(fieldListStr, ",");
            }
            if (highLowRequestFields == null || highLowRequestFields.length == 0) {
                highLowRequestFields = DEFAULT_HIGH_LOW_REQUEST_FIELDS;
                logger.warn("Using default highLowRequestFields");
            }
            for (int i = 0; i < highLowRequestFields.length; i++) {
                logger.debug("highLowRequestFields " + i + " = " + highLowRequestFields[i]);
            }
        }
        return highLowRequestFields;
    }

    
    private String[] getBidAskRequestFields() {
        // MB. Added the possibility to modify the requested fields in client.properties for testing. 
        if (bidAskRequestFields == null) {
            String fieldListStr = PropertyFactory.getProperty(MgbProperty.BLOOMBERG_BID_ASK_REQUEST_FIELDS);
            if (fieldListStr != null) {
                bidAskRequestFields = StringUtils.split(fieldListStr, ",");
            }
            if (bidAskRequestFields == null || bidAskRequestFields.length == 0) {
                bidAskRequestFields = DEFAULT_BID_ASK_REQUEST_FIELDS;
                logger.warn("Using default bidAskRequestFields");
            }
            for (int i = 0; i < bidAskRequestFields.length; i++) {
                logger.debug("bidAskRequestFields " + i + " = " + bidAskRequestFields[i]);
            }
        }
        return bidAskRequestFields;
    }

    private String[] getHistoricRequestFields() {
        // MB. Added the possibility to modify the requested fields in client.properties for testing. 
        if (historicRequestFields == null) {
            String fieldListStr = PropertyFactory.getProperty(MgbProperty.BLOOMBERG_HISTORIC_REQUEST_FIELDS);
            if (fieldListStr != null) {
                historicRequestFields = StringUtils.split(fieldListStr, ",");
            }
            if (historicRequestFields == null || historicRequestFields.length == 0) {
                historicRequestFields = DEFAULT_HISTORIC_REQUEST_FIELDS;
                logger.warn("Using default historicRequestFields");
            }
            for (int i = 0; i < historicRequestFields.length; i++) {
                logger.debug("historicRequestFields " + i + " = " + historicRequestFields[i]);
            }
        }
        return historicRequestFields;
    }

    
    private String[] getGenericRequestFields() {
        // MB. Added the possibility to modify the requested fields in client.properties for testing. 
        if (genericRequestFields == null) {
            String fieldListStr = PropertyFactory.getProperty(MgbProperty.BLOOMBERG_GENERIC_REQUEST_FIELDS);
            if (fieldListStr != null) {
                genericRequestFields = StringUtils.split(fieldListStr, ",");
            }
            if (genericRequestFields == null || genericRequestFields.length == 0) {
                genericRequestFields = DEFAULT_GENERIC_REQUEST_FIELDS;
                logger.warn("Using default genericRequestFields");
            }
            for (int i = 0; i < genericRequestFields.length; i++) {
                logger.debug("genericRequestFields " + i + " = " + genericRequestFields[i]);
            }
        }
        return genericRequestFields;
    }

    
    private int[] getRequestIntervals() {
        // MB. Added the possibility to modify the requested fields in client.properties for testing. 
        if (requestIntervals == null) {
            String fieldListStr = PropertyFactory.getProperty(MgbProperty.BLOOMBERG_REQUEST_INTERVALS);
            if (fieldListStr != null) {
                String[] requestIntervalStrings = StringUtils.split(fieldListStr, ",");
                if (requestIntervalStrings != null && requestIntervalStrings.length > 0) {
                    requestIntervals = new int[requestIntervalStrings.length];
                    try {
                        for (int i = 0; i < requestIntervalStrings.length; i++) {
                            requestIntervals[i] = Integer.parseInt(requestIntervalStrings[i]);
                        }
                    } catch (NumberFormatException nfe) {
                        logger.error("Error while parsing the parameter '"+MgbProperty.BLOOMBERG_REQUEST_INTERVALS+"': " + nfe.getMessage());
                    }
                }

            }
            if (requestIntervals == null || requestIntervals.length == 0) {
                requestIntervals = DEFAULT_REQUEST_INTERVALS;
                logger.warn("Using default requestIntervals");
            }
            for (int i = 0; i < requestIntervals.length; i++) {
                logger.debug("requestIntervals " + i + " = " + requestIntervals[i]);
            }
        }
        return requestIntervals;
    }

    private String getBloombergFieldArray(String[] bbgFields) {
        if (bbgFields != null) {
            StringBuffer s = new StringBuffer();
            if (bbgFields.length > 0) {
                s.append(bbgFields[0]);
            }
            for (int i = 1; i < bbgFields.length; i++) {
                s.append(", ").append(bbgFields[i]);
            }
            return "Fields: "+s.toString();
        }
        return "";
    }

    
    private Request buildEodRequest(String requestString, Calendar requestDate, String[] requestFields, String currency)
            throws PricingRequestComunicationException {
        if (session == null) {
            throw new PricingRequestComunicationException("Not connected to Bloomberg (run NewBloombergRequester.connect() successfully first)");
        }

        validateBloombergTicker(requestString);

        Service refDataService = session.getService(BLOOMBERG_SERVICE);
        Request request = refDataService.createRequest("HistoricalDataRequest");

        Element securities = request.getElement("securities");
        securities.appendValue(requestString);

        Element fields = request.getElement("fields");
        for (String field : requestFields) {
            fields.appendValue(field);
        }
        request.set("startDate", compactDateFormat.format(requestDate.getTime()));
        request.set("endDate", compactDateFormat.format(requestDate.getTime()));
        request.set("currency", currency);
        return request;
    }

    private Request buildIntraDayRequest(String requestString, Calendar startDate, Calendar endDate, String[] requestFields)
            throws PricingRequestComunicationException {
        if (session == null) {
            throw new PricingRequestComunicationException("Not connected to Bloomberg (run NewBloombergRequester.connect() successfully first)");
        }

        validateBloombergTicker(requestString);

        Service refDataService = session.getService(BLOOMBERG_SERVICE);
        Request request = refDataService.createRequest("IntradayTickRequest");

        request.set("security", requestString);

        Element fields = request.getElement("eventTypes");
        for (String field : requestFields) {
            fields.appendValue(field);
        }
        request.set("startDateTime", convertLocalDateToBloombergDate(startDate));
        request.set("endDateTime", convertLocalDateToBloombergDate(endDate));
        request.set("includeConditionCodes", true);
        request.set("includeNonPlottableEvents", true);
        return request;
    }

    private HistoricPriceImpl handleIntraDayResponseEvent(Event event, String requestString, 
            String currency, double referencePrice, Calendar requestDate, Calendar startDate, Calendar stopDate) throws Exception {
        long requestDateMillis = requestDate.getTimeInMillis();
        MessageIterator iter = event.messageIterator();
        Message message = null;
        while (iter.hasNext()) {
            message = iter.next();
            Element referenceDataResponse = message.asElement();
            if (referenceDataResponse.hasElement("responseError")) {
                String responseErrorMessage = referenceDataResponse.getElement("responseError").getElementAsString("message");
                int code = referenceDataResponse.getElement("responseError").getElementAsInt32("code");
                if( code == 40){
                    logger.error("ResponseError (code="+ code +") when requesting '"+requestString+"' : " + responseErrorMessage);
                    return null;
                }
                else{
                    throw new PricingRequestException("ResponseError code=("+ code +") when requesting '"+requestString+"' : " + responseErrorMessage);
                }
            }
            Element tickData1 = referenceDataResponse.getElement("tickData");
            Element tickDataArray = tickData1.getElement("tickData");
            int numItems = tickDataArray.numValues();
            Calendar lastPriceDate = null;
            long lastPriceDateMillis = 0l;
            double lastPriceValue = 0.0;

            for (int i = 0; i < numItems; ++i) {
                Element tickData = tickDataArray.getValueAsElement(i);
                Calendar currentPriceDate = convertBloombergDateToLocalDate(tickData.getElementAsDatetime("time"));
                long currentPriceDateMillis = currentPriceDate.getTimeInMillis(); 
                double currentPrice = tickData.getElementAsFloat64("value");
//                logger.debug("tick: " + tickData);
                boolean isPlausible = isPlausiblePrice(referencePrice, convertPriceFromSubUnitToBaseUnit(currency, requestString, currentPrice));
                
                if (isPlausible) {
                    if (numItems == 1) {
                        // if only one record is returned, it is taken as the last price too
                        lastPriceDate = currentPriceDate;
                        lastPriceDateMillis = currentPriceDateMillis;
                        lastPriceValue = currentPrice;                              
                    }
                    if (Math.abs(currentPriceDateMillis - requestDateMillis) < Math.abs(lastPriceDateMillis - requestDateMillis) && i == numItems-1) {
                        // if the last of the returned records is the closest, it is taken as the last price too
                        lastPriceDate = currentPriceDate;
                        lastPriceDateMillis = currentPriceDateMillis;
                        lastPriceValue = currentPrice;                              
                    }
                    if (Math.abs(currentPriceDateMillis - requestDateMillis) < Math.abs(lastPriceDateMillis - requestDateMillis)) {
                        // current price is closer
                        lastPriceDate = currentPriceDate;
                        lastPriceDateMillis = currentPriceDateMillis;
                        lastPriceValue = currentPrice;
                    } else {
                        if (lastPriceDateMillis > 0l) {
                            // Bloomberg sometime returns values outside the requested range
                            if (lastPriceDateMillis < stopDate.getTimeInMillis()+EQUAL_TOLERANCE && 
                                    lastPriceDateMillis > startDate.getTimeInMillis()-EQUAL_TOLERANCE ) { 
                                try {
                                    double lastPriceValueInBaseUnit = convertPriceFromSubUnitToBaseUnit(currency, requestString, lastPriceValue); 
                                    if (isPlausiblePrice(referencePrice, lastPriceValueInBaseUnit)) {
                                        return new HistoricPriceImpl(lastPriceDate, lastPriceValueInBaseUnit);
                                    }
                                    logger.error("Price is not plausible: " + lastPriceValue + " ("+referencePrice + ")");
                                } catch (NumberFormatException nfe) {
                                    logger.error("Invalid data found: " + lastPriceDate + " " + lastPriceValue + ": "+nfe.getMessage());
                                }
                            } else {
                                logger.error("Data is outside the time interval: " + lastPriceDate.getTime() + " ("+startDate.getTime()+ " - " + EQUAL_TOLERANCE + ", " +stopDate.getTime()+ " + " + EQUAL_TOLERANCE + ") " + lastPriceValue);                                            
                            }
                        } else {
                            logger.debug("No data with valid timestamp found: "+ tickData);
                            return null;
                        }
                    }
                } else {
                    logger.error("Invalid data found: " + tickData);
                }
            }            
        }
        logger.debug("message: " + message);
        return null;
    }

    private Price handleEndOfDayResponseEvent(Event event,
            String requestString, String currency, String[] fieldList,
            double referencePrice) throws Exception {
        MessageIterator iter = event.messageIterator();
        Message message = null;
        while (iter.hasNext()) {
            message = iter.next();
            Element referenceDataResponse = message.asElement();
            if (referenceDataResponse.hasElement("responseError")) {
                int code = referenceDataResponse.getElement("responseError").getElementAsInt32("code");
                String responseErrorMessage = referenceDataResponse.getElement("responseError").getElementAsString("message");
                throw new PricingRequestException("ResponseError (code="+ code +") when requesting '"+requestString+"' : " + responseErrorMessage);
            }
            Element securityData = referenceDataResponse.getElement("securityData");
            if (securityData.hasElement("securityError")) {
                int code = referenceDataResponse.getElement("securityError").getElementAsInt32("code");
                String securityErrorMessage = securityData.getElement("securityError").getElementAsString("message");
                throw new PricingRequestException("SecurityError (code="+ code +") when requesting '"+requestString+"' : " + securityErrorMessage);
            } else {
                Element fieldDataArray = securityData.getElement("fieldData");
                int fieldCount = fieldDataArray.numValues();
                for (int i = 0; i < fieldCount; i++) {
                    Element fieldData = fieldDataArray.getValueAsElement(i);
//                  logger.debug("fieldData: " + fieldData);
                    Datetime pDate = fieldData.getElementAsDatetime("date");
                    if (fieldList.length == 2) {
                        double pMin = fieldData
                                .getElementAsFloat64(fieldList[0]);
                        double pMax = fieldData
                                .getElementAsFloat64(fieldList[1]);
                        double minPriceInBaseUnit = convertPriceFromSubUnitToBaseUnit(
                                currency, requestString, pMin);
                        double maxPriceInBaseUnit = convertPriceFromSubUnitToBaseUnit(
                                currency, requestString, pMax);
                        if (isPlausiblePrice(referencePrice,
                                minPriceInBaseUnit, maxPriceInBaseUnit)) {
                            return new IntervalPriceImpl(pDate.calendar(),
                                    minPriceInBaseUnit, maxPriceInBaseUnit);
                        }
                    }else if (fieldList.length == 1) {
                        double price = fieldData
                                .getElementAsFloat64(fieldList[0]);
                        double priceInBaseUnit = convertPriceFromSubUnitToBaseUnit(
                                currency, requestString, price);
                        if (isPlausiblePrice(referencePrice,
                                priceInBaseUnit )) {
                            return new HistoricPriceImpl(pDate.calendar(),
                                    priceInBaseUnit);
                        }                       
                    }else {
                        throw new IllegalArgumentException("Incorrect number of request fields. Should be 1 or 2 fields.");
                    }
                }
            }
        }
        logger.debug("message: " + message);
        return null;
    }

    private void handleOtherEvent(Event event) throws Exception {
        logger.info("EventType=" + event.eventType());
        MessageIterator iter = event.messageIterator();
        while (iter.hasNext()) {
            Message message = iter.next();
            logger.info("correlationID=" + message.correlationID());
            logger.info("messageType=" + message.messageType());
            logger.info(message.toString());
            if (Event.EventType.Constants.SESSION_STATUS == event.eventType().intValue()
                    && "SessionTerminated".equals(message.messageType().toString())) {
                logger.info("Terminating: " + message.messageType());
                throw new PricingRequestException("Terminating: " + message.messageType());
            }
        }
    }


    @Override
    public HistoricPriceImpl performHistoricRequest(String requestString, String currency, Calendar requestCalendar, String[] sources, double referencePrice) throws PricingRequestException {

        if (sources == null){
            return performHistoricRequest(requestString, currency, requestCalendar, referencePrice);
        }

        Calendar requestCal = (Calendar) requestCalendar.clone();
        requestCal.setTimeZone(TimeZone.getDefault());

        validateBloombergTicker(requestString);

        HistoricPriceImpl price = null;
        for (int i = 0; i < requestIntervals.length; i++) {

            Calendar startCal = (Calendar) requestCalendar.clone();
            startCal.setTimeZone(TimeZone.getDefault());
            startCal.add(Calendar.MINUTE, -requestIntervals[i]);

            Calendar stopCal = (Calendar) requestCalendar.clone();
            stopCal.setTimeZone(TimeZone.getDefault());
            stopCal.add(Calendar.MINUTE, requestIntervals[i]);

            // just request on a interval as a first attempt
            for (int k = 0; k < sources.length; k++) {
                lastRequestedString = addSourceToRequestString(requestString, sources[k]);
                price = performHistoricRequest(lastRequestedString, historicRequestFields, requestCal, currency, startCal, stopCal, referencePrice);
                logger.debug("Price[" + k + "] (" + 2 * requestIntervals[i] + " min interval) ("+sources[k]+"): " + price);
                if (price != null) {
                    lastRequestedSourceArray = sources;
                    logger.info("Price[" + k + "] (" + 2 * requestIntervals[i] + " min interval) ("+sources[k]+"): " + price);
                    return price;
                }
            }
        }
        return null;
    }

    /**
     * This method performs a historic request to Bloomberg.
     * 
     * <br> 
        * The method looks for a price of the instrument that is closest to a given time.
        * The Bloomberg request string has the following syntax:
        * &lt;NAME&gt; [Exchange] &lt;Yellow Key&gt; [Type]
        * <br>
     * In this syntax, only the NAME and Yellow key values are required 
     * and the other two aspects are used for qualifications and are optional.
     * <br>
     * The NAME value specifies the identifier of the security. 
     * In the absence of a Type value, 
     * this name is assumed to be the ticker name (e.g. IBM). 
     * However, the name could also present a different form of identification 
     * for the same instrument as defined by the Type (e.g. ISIN). 
     * <br>
     * The exchange is a 2 character mnemonic for the exchange on which the instrument is traded.
     * <br>
     * The yellow key (as its name suggests) is a text equivalent for the Bloomberg yellow function keys
     * (e.g. Equity)
     * <br>
        * Example: 
        * &quot;DE0005557508 GR Equity ISIN&quot;
        * <br>
        * For more information see the Bloomberg API help 
        * (Look for the help-file BB_API_Help.chm or type &quot;API&quot;&lt;GO&gt; in a Bloomberg terminal).
        *
     *@param  requestString     A Bloomberg request ticker format described above.
     *@param  requestDate       A date Bloomberg tries to find a matching price.
     *@return                   A HistoricPrice. It is null if no price was found.
     *@exception  PricingRequestException  Is thrown if no connection to Bloomberg is available or if any error occures during the request.
        */
    public HistoricPriceImpl performHistoricRequest(String requestString, String currency, Calendar requestCalendar, double referencePrice) throws PricingRequestException {

        validateBloombergTicker(requestString);

        Calendar requestCal = (Calendar) requestCalendar.clone();
        requestCal.setTimeZone(TimeZone.getDefault());

        HistoricPriceImpl price = null;
        for (int i = 0; i < requestIntervals.length && price == null; i++) {

            Calendar startCal = (Calendar) requestCalendar.clone();
            startCal.setTimeZone(TimeZone.getDefault());
            startCal.add(Calendar.MINUTE, -requestIntervals[i]);

            Calendar stopCal = (Calendar) requestCalendar.clone();
            stopCal.setTimeZone(TimeZone.getDefault());
            stopCal.add(Calendar.MINUTE, requestIntervals[i]);

            // just request on a interval as a first attempt
            price = performHistoricRequest(requestString, historicRequestFields, requestCal, currency, startCal, stopCal, referencePrice);
            logger.debug("Price (" + 2 * requestIntervals[i] + " min interval): " + price);
            if (price != null) {
                logger.info("Price (" + 2 * requestIntervals[i] + " min interval): " + price);
            }
        }

        if (price == null) {
            Calendar startDayCal = DateTimeUtils.startOfDayCal(requestCal);

            Calendar stopDayCal = DateTimeUtils.endOfDayCal(requestCal);

            // request data for the whole day from 00:00 to 23:59
            price = performHistoricRequest(requestString, historicRequestFields, requestCal, currency, startDayCal, stopDayCal, referencePrice);
            logger.debug("Price (24 h interval): " + price);
        }

        return price;
    }

    private HistoricPriceImpl performHistoricRequest(String requestString, String[] fieldList, Calendar requestDate, String currency, Calendar startDate, Calendar stopDate, double referencePrice)
            throws PricingRequestException {
        HistoricPriceImpl price = null;
        try {
            logger.debug(
                    "Start requesting: '"
                        + requestString
                        + "' with currency '"
                        + currency
                        + "' between "
                        + dateTimeFormat.format(startDate.getTime())
                        + " and "
                        + dateTimeFormat.format(stopDate.getTime()) 
                        + " for "+getBloombergFieldArray(fieldList));

            Request request = buildIntraDayRequest(requestString, startDate, stopDate, fieldList);
            session.sendRequest(request, null);

            boolean continueToLoop = true;
            while (continueToLoop) {
                Event event = session.nextEvent();
                switch (event.eventType().intValue()) {
                case Event.EventType.Constants.RESPONSE: // final response
                    continueToLoop = false; // fall through
                case Event.EventType.Constants.PARTIAL_RESPONSE:
                    price = handleIntraDayResponseEvent(event, requestString, currency, referencePrice, requestDate, startDate, stopDate);
                    break;
                default:
                    handleOtherEvent(event);
                    break;
                }
            }
        } catch (IOException e) {
            logger.error(e);
            throw new PricingRequestException(e);
        } catch (InterruptedException e) {
            logger.error(e);
            throw new PricingRequestException(e);
        } catch (Exception e) {
            logger.error(e);
            throw new PricingRequestException(e);
        }
        return price;
	}

    

	/**
	 * This method performs a historic request to Bloomberg.
	 * 
	 * <br> 
		* The method looks for a price of the instrument that is closest to a given time.
		* The Bloomberg request string has the following syntax:
		* &lt;NAME&gt;[@source] [Exchange] &lt;Yellow Key&gt; [Type]
		* <br>
	 * In this syntax, only the NAME and Yellow key values are required 
	 * and the other two aspects are used for qualifications and are optional.
	 * <br>
	 * The NAME value specifies the identifier of the security. 
	 * In the absence of a Type value, 
	 * this name is assumed to be the ticker name (e.g. IBM). 
	 * However, the name could also present a different form of identification 
	 * for the same instrument as defined by the Type (e.g. ISIN). 
	 * <br>
	 * The source is a shortcut for the price provider.
	 * <br>
	 * The exchange is a 2 character mnemonic for the exchange on which the instrument is traded.
	 * <br>
	 * The yellow key (as its name suggests) is a text equivalent for the Bloomberg yellow function keys
	 * (e.g. Equity)
	 * <br>
		* Example: 
		* &quot;DE0005557508 GR Equity ISIN&quot;
		* <br>
		* For more information see the Bloomberg API help 
		* (Look for the help-file BB_API_Help.chm or type &quot;API&quot;&lt;GO&gt; in a Bloomberg terminal).
		*
	 *@param  requestString     A Bloomberg request ticker format described above.
	 *@param  requestDate       A date Bloomberg tries to find a matching price.
	 *@param  sources       	An arraz of source strings. 
	 *@return                   A HistoricPrice. It is null if no price was found.
	 *@exception  PricingRequestException  Is thrown if no connection to Bloomberg is available or if any error occures during the request.
		*/
	@Override
    public IntervalPriceImpl performBidAskRequest(String requestString, String currency, Calendar requestCalendar, String[] sources, double referencePrice) throws PricingRequestException {

		if (sources == null){
			return performBidAskRequest(requestString, currency, requestCalendar, referencePrice);
		}

		Calendar requestCal = (Calendar) requestCalendar.clone();
		requestCal.setTimeZone(TimeZone.getDefault());

		validateBloombergTicker(requestString);

        IntervalPriceImpl bestPrice = null;
        String bestRequestedString = null;
        IntervalPriceImpl lastPrice = null;
        int numberOfPrices = 0;
		for (int i = 0; i < requestIntervals.length && lastPrice == null; i++) {

			Calendar startCal = (Calendar) requestCalendar.clone();
			startCal.setTimeZone(TimeZone.getDefault());
			startCal.add(Calendar.MINUTE, -requestIntervals[i]);

			Calendar stopCal = (Calendar) requestCalendar.clone();
			stopCal.setTimeZone(TimeZone.getDefault());
			stopCal.add(Calendar.MINUTE, requestIntervals[i]);

			// just request on a interval as a first attempt
			for (int s = 0; s < sources.length; s++) {
				lastRequestedString = addSourceToRequestString(requestString, sources[s]);
				lastPrice = performBidAskRequest(lastRequestedString, requestCal, currency, startCal, stopCal, referencePrice);
				if (lastPrice != null) {
				    numberOfPrices++;
				}
				logger.debug("Price[" + s + "] (" + 2 * requestIntervals[i] + " min interval) ("+sources[s]+"): " + lastPrice);
				// check, if the last price is inside the interval and take this price 
				if (isInsideInterval(referencePrice, lastPrice)) {
					lastRequestedSourceArray = sources;
					logger.info("Price[" + s + "] (" + 2 * requestIntervals[i] + " min interval) ("+sources[s]+"): " + lastPrice);
					return lastPrice;
				}
				// check, if the last price is closer, and remember the last as best
				if (isCloserInterval(referencePrice, bestPrice, lastPrice)) {
                    lastRequestedSourceArray = sources;
                    logger.info("Price[" + s + "] (" + 2 * requestIntervals[i] + " min interval) ("+sources[s]+") is better: " + lastPrice);
                    bestRequestedString = lastRequestedString;
				    bestPrice = lastPrice;
				}
			}
			// take the best price if at least 2 prices where found,
			// otherwise continue with a wider interval
			if (bestPrice != null && numberOfPrices > 1) {
                logger.info("Price (" + 2 * requestIntervals[i] + " min interval): " + bestPrice);
                lastRequestedString = bestRequestedString;
                return bestPrice;			    
			}
		}
        lastRequestedString = bestRequestedString;
		return bestPrice;
	}

	/**
	 * This method performs a historic request to Bloomberg.
	 * 
	 * <br> 
		* The method looks for a price of the instrument that is closest to a given time.
		* The Bloomberg request string has the following syntax:
		* &lt;NAME&gt; [Exchange] &lt;Yellow Key&gt; [Type]
		* <br>
	 * In this syntax, only the NAME and Yellow key values are required 
	 * and the other two aspects are used for qualifications and are optional.
	 * <br>
	 * The NAME value specifies the identifier of the security. 
	 * In the absence of a Type value, 
	 * this name is assumed to be the ticker name (e.g. IBM). 
	 * However, the name could also present a different form of identification 
	 * for the same instrument as defined by the Type (e.g. ISIN). 
	 * <br>
	 * The exchange is a 2 character mnemonic for the exchange on which the instrument is traded.
	 * <br>
	 * The yellow key (as its name suggests) is a text equivalent for the Bloomberg yellow function keys
	 * (e.g. Equity)
	 * <br>
		* Example: 
		* &quot;DE0005557508 GR Equity ISIN&quot;
		* <br>
		* For more information see the Bloomberg API help 
		* (Look for the help-file BB_API_Help.chm or type &quot;API&quot;&lt;GO&gt; in a Bloomberg terminal).
		*
	 *@param  requestString     A Bloomberg request ticker format described above.
	 *@param  requestDate       A date Bloomberg tries to find a matching price.
	 *@return                   A HistoricPrice. It is null if no price was found.
	 *@exception  PricingRequestException  Is thrown if no connection to Bloomberg is available or if any error occures during the request.
		*/
	public IntervalPriceImpl performBidAskRequest(String requestString, String currency, Calendar requestCalendar, double referencePrice) throws PricingRequestException {

		validateBloombergTicker(requestString);

		Calendar requestCal = (Calendar) requestCalendar.clone();
		requestCal.setTimeZone(TimeZone.getDefault());

		IntervalPriceImpl price = null;
		for (int i = 0; i < requestIntervals.length && price == null; i++) {

			Calendar startCal = (Calendar) requestCalendar.clone();
			startCal.setTimeZone(TimeZone.getDefault());
			startCal.add(Calendar.MINUTE, -requestIntervals[i]);

			Calendar stopCal = (Calendar) requestCalendar.clone();
			stopCal.setTimeZone(TimeZone.getDefault());
			stopCal.add(Calendar.MINUTE, requestIntervals[i]);

			// just request on a interval as a first attempt
			price = performBidAskRequest(requestString, requestCal, currency, startCal, stopCal, referencePrice);
			logger.debug("Price (" + 2 * requestIntervals[i] + " min interval): " + price);
			if (price != null) {
				logger.info("Price (" + 2 * requestIntervals[i] + " min interval): " + price);
			}
		}

		if (price == null) {
			Calendar startDayCal = DateTimeUtils.startOfDayCal(requestCal);

			Calendar stopDayCal = DateTimeUtils.endOfDayCal(requestCal);

			// request data for the whole day from 00:00 to 23:59
			price = performBidAskRequest(requestString, requestCal, currency, startDayCal, stopDayCal, referencePrice);
			logger.debug("Price (24 h interval): " + price);
		}

		return price;
	}

	
	/**
	 * This method performs a historic request to Bloomberg.
	 * 
	 * <br> 
		* The method looks for a bid and ask price of the instrument that is closest to a given time.
		* The request is restricted to a time interval.
		* 
		*
	 *@param  requestString     A Bloomberg request ticker format described above.
	 *@param  requestDate       A date Bloomberg tries to find a matching price.
	 *@param  startDate         The lower bound of the time interval  Bloomberg tries to find the matching price.
	 *@param  stopDate          The upper bound of the time interval  Bloomberg tries to find the matching price.
	 *@return                   a HistoricPrice. It is null if no price was found.
	 *@exception  PricingRequestException  Is thrown if no connection to Bloomberg is available or if any error occures during the request.
	 *@see  BlpApiBloombergRequester.NewBloombergRequester#performHistoricRequest(String, Date)
		*
		*/
	private IntervalPriceImpl performBidAskRequest(String requestString,
			Calendar requestDate, String currency, Calendar startDate,
			Calendar stopDate, double referencePrice)
			throws PricingRequestException {

		logger.debug("Start requesting: '" + requestString
				+ "' with currency '" + currency + "' between "
				+ dateTimeFormat.format(startDate.getTime()) + " and "
				+ dateTimeFormat.format(stopDate.getTime()) + " for "
				+ getBloombergFieldArray(bidAskRequestFields));

		HistoricPriceImpl bidResult = performHistoricRequest(requestString,
				Arrays.copyOfRange(bidAskRequestFields, 0, 1), requestDate,
				currency, startDate, stopDate, referencePrice);
		HistoricPriceImpl askResult = performHistoricRequest(requestString,
				Arrays.copyOfRange(bidAskRequestFields, 1, 2), requestDate,
				currency, startDate, stopDate, referencePrice);
		
		if(bidResult == null || askResult == null)
			return null;
		
		long requestDateMillis = requestDate.getTimeInMillis();
		long bidMillis = bidResult.getPriceDate().getTimeInMillis();
		long askMillis = askResult.getPriceDate().getTimeInMillis();
		Calendar priceDate = new GregorianCalendar();

		if (Math.abs(requestDateMillis - bidMillis) > Math
				.abs(requestDateMillis - askMillis)) {
			priceDate.setTimeInMillis(bidMillis);
		} else {
			priceDate.setTimeInMillis(askMillis);
		}
		return new IntervalPriceImpl(priceDate, bidResult.getPrice(),
				askResult.getPrice());
	}
	
    @Override
    public IntervalPriceImpl performHighLowRequest(String requestString, String currency, Calendar requestDate,
            String[] sources, double referencePrice) throws PricingRequestException {
        if (sources == null) { return performHighLowRequest(requestString, currency, requestDate, referencePrice); }

        for (int k = 0; k < sources.length; k++) {
            lastRequestedString = addSourceToRequestString(requestString, sources[k]);
            IntervalPriceImpl price = performHighLowRequest(lastRequestedString, currency, requestDate, referencePrice);
            if (price != null) {
                lastRequestedSourceArray = sources;
                logger.info("Price[" + k + "] (H/L) (" + sources[k] + "): " + price);
                return price;
            }
        }
        return null;
    }

    public IntervalPriceImpl performHighLowRequest(String requestString, String currency, Calendar requestDate, double referencePrice) throws PricingRequestException {
        IntervalPriceImpl price = null;
        try {
            logger.debug("Start requesting: '" + requestString + "' with currency + '" + currency + "' on " + dateTimeFormat.format(requestDate.getTime()) + " ("+getBloombergFieldArray(highLowRequestFields)+")");

            Request request = buildEodRequest(requestString, requestDate, highLowRequestFields, currency);
            session.sendRequest(request, null);

            boolean continueToLoop = true;
            while (continueToLoop) {
                Event event = session.nextEvent();
                switch (event.eventType().intValue()) {
                case Event.EventType.Constants.RESPONSE: // final response
                    continueToLoop = false; // fall through
                case Event.EventType.Constants.PARTIAL_RESPONSE:
                    price = (IntervalPriceImpl)handleEndOfDayResponseEvent(event, requestString, currency, highLowRequestFields, referencePrice);
                    break;
                default:
                    handleOtherEvent(event);
                    break;
                }
            }
        } catch (IOException e) {
            logger.error(e);
            throw new PricingRequestException(e);
        } catch (InterruptedException e) {
            logger.error(e);
            throw new PricingRequestException(e);
        } catch (Exception e) {
            logger.error(e);
            throw new PricingRequestException(e);
        }
		return price;
	}

	
	@Override
	public HistoricPriceImpl performRequest(String requestString,
			String currency, Calendar requestDate,
			double referencePrice) throws PricingRequestException {

		HistoricPriceImpl price = null;
	        try {
	            logger.debug("Start requesting: '" + requestString + "' with currency + '" + currency + "' on " + dateTimeFormat.format(requestDate.getTime()) + " ("+getBloombergFieldArray(genericRequestFields)+")");

	            Request request = buildEodRequest(requestString, requestDate, genericRequestFields, currency);
	            session.sendRequest(request, null);

	            boolean continueToLoop = true;
	            while (continueToLoop) {
	                Event event = session.nextEvent();
	                switch (event.eventType().intValue()) {
	                case Event.EventType.Constants.RESPONSE: // final response
	                    continueToLoop = false; // fall through
	                case Event.EventType.Constants.PARTIAL_RESPONSE:
	                    price = (HistoricPriceImpl)handleEndOfDayResponseEvent(event, requestString, currency, genericRequestFields, referencePrice);
	                    break;
	                default:
	                    handleOtherEvent(event);
	                    break;
	                }
	            }
	        } catch (IOException e) {
	            logger.error(e);
	            throw new PricingRequestException(e);
	        } catch (InterruptedException e) {
	            logger.error(e);
	            throw new PricingRequestException(e);
	        } catch (Exception e) {
	            logger.error(e);
	            throw new PricingRequestException(e);
	        }
			return price;
		}

	@Override
	public HistoricPriceImpl performRequest(String requestString,
			String currency, Calendar requestDate,
			int daysGoBack, double referencePrice)
			throws PricingRequestException {
		int  i = 0;
		HistoricPriceImpl result = null;
		while (i <= daysGoBack) {
			Calendar cal = (Calendar)requestDate.clone();
			cal.add(Calendar.DAY_OF_YEAR,-i);
			result = performRequest(requestString, currency, cal, referencePrice);
			i++;
		}
		return result;
	}

	@Override
	public String[] getLastRequestedSourceArray() {
		return lastRequestedSourceArray;
	}

	@Override
	public String getLastRequestedString() {
		return lastRequestedString;
	}

    private String addSourceToRequestString(String requestString, String source) {
        int sourcePosStart = requestString.indexOf("@");
        if (sourcePosStart < 0) {
            sourcePosStart = requestString.indexOf(' ');
        }
        int sourcePosStop = requestString.indexOf(" ", sourcePosStart);

        if (sourcePosStart > -1 && sourcePosStop > -1) { return requestString.substring(0, sourcePosStart) + "@"
                + source + requestString.substring(sourcePosStop); }
        return requestString;
    }

    private double convertPriceFromSubUnitToBaseUnit(String isoCurrency, String requestString, Double price)
            throws NumberFormatException {
        if (price == null) { return 0; }
        if (requestMapping != null && requestMapping.containsKey(requestString)) {
            double factor = requestMapping.get(requestString);
            logger.debug("Multiplying the Bloomberg price with " + factor + " to get the price in the correct unit.");
            return price * factor;
        }
        if (("GBP".equalsIgnoreCase(isoCurrency) || "ZAR".equalsIgnoreCase(isoCurrency))
                && requestString.indexOf("Equity") >= 0) {
            logger.debug("Deviding the Bloomberg price by 100 to get the price in the requested currency '"
                    + isoCurrency + "' and not in the sub-unit.");
            return price.doubleValue() / 100.0;
        }
        return price.doubleValue();
    }

    /**
     * Checks id the reference price and the price are in the same order of
     * magnitude. Both prices must be different from zero.
     * 
     * @param referencePrice
     * @param price
     * @return
     */
    private boolean isPlausiblePrice(double referencePrice, double price) {
        return referencePrice == 0d
                || (price != 0d && Math.abs(price / referencePrice) > 1 / ORDER_OF_MAGNITUDE && Math.abs(price
                        / referencePrice) < ORDER_OF_MAGNITUDE);
    }

    /**
     * Checks id the reference price and either price1 or price2 are in the same
     * order of magnitude. The refernce price and either price1 or price2 must
     * be different from zero.
     * 
     * @param referencePrice
     * @param price1
     * @param price2
     * @return
     */
    private boolean isPlausiblePrice(double referencePrice, double price1, double price2) {
        if (price1 != 0d) {
            if (price2 != 0d) { return isPlausiblePrice(referencePrice, (price1 + price2) * 0.5); }
            return isPlausiblePrice(referencePrice, price1);
        }
        if (price2 != 0d) { return isPlausiblePrice(referencePrice, price2); }
        return false;
    }
}
