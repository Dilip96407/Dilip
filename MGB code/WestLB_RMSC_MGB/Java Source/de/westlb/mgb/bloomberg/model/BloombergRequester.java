
package de.westlb.mgb.bloomberg.model;
import java.text.Format;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.ibm.bridge2java.ComException;

import de.westlb.mgb.bloomberg.bbapi.BlpData;
import de.westlb.mgb.bloomberg.bbapi._BlpDataEvents;
import de.westlb.mgb.bloomberg.bbapi._BlpDataEvents_DataEvent;
import de.westlb.mgb.bloomberg.bbapi._BlpDataEvents_StatusEvent;
import de.westlb.mgb.client.application.MgbProperty;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.model.impl.HistoricPriceImpl;
import de.westlb.mgb.model.impl.IntervalPriceImpl;
import de.westlb.mgb.util.DateTimeUtils;
import de.westlb_systems.xaf.util.PropertyFactory;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class BloombergRequester implements PricingRequester {

	static Logger logger = Logger.getLogger(BloombergRequester.class);

	private final String[] DEFAULT_HIGH_LOW_REQUEST_FIELDS = { "PX_LOW", "PX_HIGH" };

	private final String[] DEFAULT_HISTORIC_REQUEST_FIELDS = { "LAST_PRICE" };

	private final String[] DEFAULT_GENERIC_REQUEST_FIELDS = { "PX_LAST" };

	private final String[] DEFAULT_BID_ASK_REQUEST_FIELDS = { "BID", "ASK" };

	private final int[] DEFAULT_REQUEST_INTERVALS = { 1, 5, 60 };
	
	private final double ORDER_OF_MAGNITUDE = 10.0;

	
	private static final String BLOOMBERG_TICKER_FORMAT = "Invalid Bloomberg ticker (Format: <name> [exchange] <yellow key> [type])";

	private static final String BLOOMBERG_TIMEOUT_HEXSTRING = "ffffffff80004005";

	private static HashMap<String, String> BLOOMBERG_MASSAGES = new HashMap<String, String>();
	static {
		BLOOMBERG_MASSAGES.put("#N/A Fld", "Invalid field mnemonic is specified");
		BLOOMBERG_MASSAGES.put("#N/A Tim", "The request for this field has timed out");
		BLOOMBERG_MASSAGES.put("#N/A Com", "The connection between DDE server and local bbcomm is lost/unavailable");
		BLOOMBERG_MASSAGES.put("#N/A Auth", "Not authorized for Bloomberg data");
		BLOOMBERG_MASSAGES.put("#N/A Security", "Invalid security");
		BLOOMBERG_MASSAGES.put("#N/A Intraday", "No intraday is available");
		BLOOMBERG_MASSAGES.put("#N/A History", "No history is available");
		BLOOMBERG_MASSAGES.put("#N/A N.A.", "The data for the specified derived field is not available");
		BLOOMBERG_MASSAGES.put("#N/A N Ap", "The field is not applicable for the security");
		BLOOMBERG_MASSAGES.put("#N/A Neg", "The field is a numeric field that cannot be negative but the calculated values is negative");
		BLOOMBERG_MASSAGES.put("#N/A Sec", "Security unknown/not recognized");
		BLOOMBERG_MASSAGES.put("#N/A Trd", "The security for which realtime data was requested has not traded for more than 30 days");
		BLOOMBERG_MASSAGES.put("#N/A Rl Tim", "Realtime not available for the given field/security");
		BLOOMBERG_MASSAGES.put("#N/A Rl Perm", "The user does not have the permission to access the specified field");
		BLOOMBERG_MASSAGES.put("#N/A Dberr", "Bloomberg database error");
		BLOOMBERG_MASSAGES.put("#N/A Sec Tp", "The specified security type is not among the valid types");
		BLOOMBERG_MASSAGES.put("#N/A Unknown", "Unknown problem with the requests for the realtime fields. Will occur only when we add new error codes in between new DDE version releases");
		BLOOMBERG_MASSAGES.put("#N/A Hist Fld", "Not an applicable history field");
		BLOOMBERG_MASSAGES.put("#N/A Rte", "API monitor.rte file not configured correctly, contact Bloomberg Technical Support");
		BLOOMBERG_MASSAGES.put("#N/A RTbl", "API monitor.rte file not configured correctly, contact Bloomberg Technical Support");
	}

	/**
	 *  This member holds the number of seconds from 01.01.1900 to 01.01.1971.
	 */
	private final long SECONDS_1900_TO_1970 = 2208988800L;

	/**
	 *  This member holds the number of days that indicate the difference between a Bloomberg date
	 *  represented in days from 01.01.1900 and the converted java.util.Date.
	 *  The value is set to 2.
	 */
	private final int BLOOMBERG_DAY_DIFFERENCE = 2;
	
	/**
	 *  This member holds the number seconds after a syncron Bloomberg request is timed out.
	 */
	private final int BLOOMBERG_TIMEOUT_SECONDS = 15;

	private int bloombergTimeoutSeconds = BLOOMBERG_TIMEOUT_SECONDS;
	
    private Map<String,Double> requestMapping;

	private static final double EQUAL_TOLERANCE = 0.0001d;

	private final String GMT = "GMT+0";

	private static class DataEventHandler implements _BlpDataEvents {
		/**
		 * This event occurs if data is received from a asyncron Bloomberg request.
		 *
		 * @see bbapi._BlpDataEvents#Data(_BlpDataEvents_DataEvent)
		 */
		@Override
        public void Data(_BlpDataEvents_DataEvent event) {
			logger.debug("cookie_id:" + event.get_cookie());
			event.get_Data();
		}

		/**
		 * This event occurs if any Bloomberg status changes. 
		 *  
		 * <br>
		 * The following status codes are possible:
		 *	<table border="1">
		 *	<tr><th>#Define Name</th><th> Return/Error(-) Code </th><th>Description </th>
		 *	<tr><td>secInvalidDate </td><td>-8 </td><td>Error in loading the data dictionary. </td></tr>
		 *	<tr><td>ExitFAILOPENFILE </td><td>-1901 </td><td>Error in loading the data dictionary. </td></tr>
		 *	<tr><td>ExitFAILSETPEERFLAGS </td><td>-1904 </td><td>Invalid peer flag value(s). Bloomberg internal purposes only. </td></tr>
		 *	<tr><td>ExitFAILCONNECT </td><td>-1905 </td><td>Failure to connect to address thread on bbcomm socket; bbcomm may need to be started on PC. </td></tr>
		 *	<tr><td>ExitFAILCONNECTION </td><td>-1907 </td><td>1) Returned if the upstream bbcomm terminated its connection.<br>
		 *	2) Connection parameter does not point to a valid bbcomm connection; most likely bb_connect() could not establish a connection to a bbcomm server and returned NULL<br>
		 *	3) bb_connect() returns NULL if a connection could not be established to a bbcomm server. Checking the return value is your responsibility. If a NULL connection pointer is passed to any other API routine, it will return ExitFAILCONNECTION and fail. </td></tr>
		 *	<tr><td>ExitFAILGETDATA </td><td>-1913 </td><td>1) bb_getdatax() request is improperly formatted (or is blocked -- NOGETDATA is defined) <br>
		 *	2) Indicates an erroneous parameter was sent to bb_getdatax(). </td></tr>
		 *	<tr><td>ExitFAILGETHEADER </td><td>-1914 </td><td>bb_getheaderx() request is improperly formatted (e.g. Up to 10 ids/request) </td></tr>
		 *	<tr><td>ExitFAILMALLOC </td><td>-1915 </td><td>Indicates that the Bloomberg API was unable to allocate sufficient memory to make this request. </td></tr>
		 *	<tr><td>ExitFAILTKLOOKUP </td><td>-1919 </td><td>Bad product key or security name in tklookup request. </td></tr>
		 *	<tr><td>ExitFAILRECEIVE </td><td>-1930 </td><td>Failure to receive a packet from a Comm Server socket; indicates a broken connection. </td></tr>
		 *	<tr><td>ExitFAILRECEIVEBUFFER </td><td>-1931 </td><td>Invalid or insufficient buffer specification for data request. </td></tr>
		 *	<tr><td>ExitFAILROUTE </td><td>-1935 </td><td>Invalid route, missing routing table, or failed to find available route. </td></tr>
		 *	<tr><td>ExitFAILROUTELOAD </td><td>-1936 </td><td>Route description string for route control operations (add, delete, replace, file) is improperly formatted. </td></tr>
		 *	<tr><td>ExitFAILSENDBUFFERFULL </td><td>-1939 </td><td>Buffer overflow and not enough memory to extend buffer. </td></tr>
		 *	<tr><td>ExitFAILSEND </td><td>-1940 </td><td>No packet to send or invalid send queue (fourth parameter of the bMonitorSend function). </td></tr>
		 *	<tr><td>ExitFAILSERVICE </td><td>-1943 </td><td>1) Fail to register or dispatch message because of invalid service code. <br>
		 *	2) The service code specified is outside of the range BB_SVC_MIN to BB_SVC_MAX. </td></tr>
		 *	<tr><td>ExitFAILSOCKET </td><td>-1945 </td><td>No socket connection. </td></tr>
		 *	<tr><td>ExitFAILSTOPMNTR </td><td>-1947 </td><td>No securities to stop monitoring or stopmntr request exceeds the limit for number of securities (Up to 500 IDs). </td></tr>
		 *	<tr><td>ExitFAILSERVICECODE </td><td>-1948 </td><td>Service code is unknown.  </td></tr>
		 *	<tr><td>ExitFAILSERVICEPARM </td><td>-1949 </td><td>The connection pointer could cause this error if it becomes corrupt while being used by any bb_*() function that requires a connection pointer identifying a connection to bbcomm. Bloomberg internal purposes only. </td></tr>
		 *	<tr><td>ExitFAILTICKMNTR </td><td>-1950 </td><td>No securities to monitor or monitor request exceeds the limit for the number of securities (Up to 1000 IDs). </td></tr>
		 *	<tr><td>ExitFAILGETTIMESERIES </td><td>-1951 </td><td>No security for bb_gettimeseriesx() request. </td></tr>
		 *	<tr><td>ExitFAILTIMESERIESBARS </td><td>-1952 </td><td>Invalid bar parameters for bb_gettimeseriesx() request. </td></tr>
		 *	<tr><td>ExitFAILTIMEOUT </td><td>-1963 </td><td>Time-out on a link to the Bloomberg Server; may indicate a broken connection.  </td></tr>
		 *	<tr><td>ExitFAILTOOMANYFIELDS </td><td>-1965 </td><td>1) Indicates that too many fields or overrides have been selected with this request.</td></tr>
		 *	2) bb_getdatax() request is too large (Greater than 2048 bytes). 
		 *	<tr><td>ExitFAILREQUESTTOOLONG </td><td>-1967 </td><td>bb_tklookup() request is too large (greater than 2048 bytes). </td></tr>
		 *	<tr><td>ExitFAILHISTORY </td><td>-1977 </td><td>No handler has been registered for service. </td></tr>
		 *	<tr><td>ExitFAILFLUSH </td><td>-1983 </td><td>1) Unable to flush socket. <br>
		 *	2) Send queue is not empty and send was unsuccessful for over a minute .
		 *	Bloomberg internal purposes only. </td></tr>
		 *	<tr><td>ExitFAILCOMPRESS </td><td>-1997 </td><td>Unsuccessful attempt to decompress intraday tick buffer (e.g. Unknown compression type passed to bb_rcvdata() from a gethistory request). </td></tr>
		 *	<tr><td>ExitFAILPEERLOOKUP </td><td>-1999 </td><td>1) Internal problem with API data structures.<br>
		 *	2) Reference to client or server is not known. </td></tr>
		 *	</table>
		 *
		 * @see bbapi._BlpDataEvents#Status(_BlpDataEvents_StatusEvent)
		 */
		@Override
        public void Status(_BlpDataEvents_StatusEvent event) {
			logger.debug("status descr:" + event.get_StatusDescription().toString());
			logger.debug("status id:" + event.get_Status());
			logger.debug("substatus id:" + event.get_SubStatus());
		}
	};

	private final Format dateTimeFormat = new DateFormat(DateFormat.DATETIME_FORMAT_LONG);

	private String[] highLowRequestFields = null;
	private String[] historicRequestFields = null;
	private String[] genericRequestFields = null;
	private String[] bidAskRequestFields = null;
	private int[] requestIntervals = null;

	private String[] lastRequestedSourceArray = null;
	private String lastRequestedString = null;

	/**
	 *  This member holds the connection to the Bloomberg Data Control service.
	 */
	private BlpData bloombergDataControlService;

	public BloombergRequester() {
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

	/**
	 *  This method initializes the COM-environment and connects to the Bloomberg Data Control service.
	 * 
	 * <br>
		*  A EventListener is added to the connection and the timeout for syncon requests is set to bloombergTimeoutSeconds.
		*  The private member holds the connection and is used for the following requests.
	 *
	 *@exception  PricingRequestException  Is thrown if an internal com.ibm.bridge2java.ComException occurs and the connection failed.
	 *@see  model.BloombergRequester#bloombergDataControlService        bloombergDataControlService Bloomberg Data Control connection
	 *@see  model.BloombergRequester#bloombergTimeoutSeconds        bloombergTimeoutSeconds
	 */
	public void connect() throws Exception {
		logger.info("Connecting Bloomberg...");
		try {
			com.ibm.bridge2java.OleEnvironment.Initialize();
			bloombergDataControlService = new BlpData();
			bloombergDataControlService.add_BlpDataEventsListener(new DataEventHandler());
			bloombergDataControlService.set_Timeout(bloombergTimeoutSeconds * 1000);
		} catch (ComException e) {
		    logger.error("Connected to Bloomberg failed (" + Long.toHexString(e.getHResult()) + ")", e);
			throw new PricingRequestException("COM Exception(" + Long.toHexString(e.getHResult()) + "): " + e.getMessage());
		} catch (Exception e) {
            logger.error("Connected to Bloomberg failed", e);
			throw e;
		}
		logger.info("Connected to Bloomberg");
	}

	/**
	 *  This Method releases the bound recources and resets the COM-environment.
	 */
	public void disconnect() {
		logger.info("Disconnecting Bloomberg...");
		if (bloombergDataControlService != null) {
			bloombergDataControlService.ReleaseObject();
		}
		com.ibm.bridge2java.OleEnvironment.UnInitialize();
		logger.info("Disconnected from Bloomberg");
	}

	/**
	 *  Converts the fractional number of days since 01.01.1900 to corresponding the number of milliseconds since 01.01.1970.
	 * 
	 * <br>
		*  The milliseconds are the basis for java.util.Date.<br>
		*  Example: 37853.380057870374 becomes 22.08.2003 11:07:17.
	 *
	 *@param  daysSince1900     A double that holds the days since 01.01.1900.
	 *@return                   A Date that represents the the date "01.01.1900 + daysSince1900".
	 *@see  java.util.Calendar
	 *@see  java.util.Date
	 */
	private Calendar daysFromNineteenHundredToDate(double daysSince1900) {
		long secondsSince1900 = Double.valueOf(daysSince1900 * 24.0 * 60.0 * 60.0).longValue();
		long milliSecondsSince1970 = (secondsSince1900 - SECONDS_1900_TO_1970) * 1000;
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(milliSecondsSince1970);

//      logger.debug("Days2Date: '" + daysSince1900 + "' => '" + milliSecondsSince1970 + "' => '" + dateTimeFormat.format(new Date(milliSecondsSince1970)) + "'");

		return cal;
	}
	/**
	 *  Converts a java.util.Calendar (representing the number of milliseconds since 01.01.1970) into the fractional number of days since 01.01.1900.
	 * 
	 *  <br>
		*  Example: 22.08.2003 11:07:17 becomes 37853.380057870374.
	 *
	 *@param  date     A date representing the number of milliseconds since 01.01.1970.
	 *@return                  The number of days since 01.01.1900.
	 *@see  java.util.Calendar
	 *@see  java.util.Date
	 */
	private double dateToDaysFromNineteenHundred(Calendar cal) {
		long milliSecondsSince1970 = cal.getTimeInMillis();
		double milliSecondsSince1900 = Long.valueOf(milliSecondsSince1970 + (SECONDS_1900_TO_1970 * 1000)).doubleValue();
		double daysSince1900 = milliSecondsSince1900 / (1000.0 * 24.0 * 60.0 * 60.0);

//		logger.debug("Date2Days: '" + dateTimeFormat.format(cal.getTime()) + "' => '" + milliSecondsSince1970 + "' => '" + daysSince1900 + "'");

		return daysSince1900;
	}

	/**
	 *  Converts a Bloomberg date to a date represented in the local timezone.
	 * 
	 * <br>
	 *  Bloomberg dates are GMT timeszone. 
	 *  The conversion also substracts a magic number of BLOOMBERG_DAY_DIFFERENCE (=2) days. The differnce is not fully understood, but is works.<br>
	 *  Example: 22.08.2003 11:07:17 GMT becomes 20.08.2003 09:07:17 CET.
	 *
	 *@param  date     A GMT Bloomberg date.
	 *@return          The date for local timezone minus a constant of BLOOMBERG_DAY_DIFFERENCE days.
	 *@see  java.util.Date
	 *@see  java.util.GregorianCalendar
	 *@see  java.util.TimeZone
	 *@see  model.BloombergRequester#BLOOMBERG_DAY_DIFFERENCE        BLOOMBERG_DAY_DIFFERENCE
	 */
	private Calendar convertBloombergDateToLocalDate(Calendar calendar) {
		GregorianCalendar blbCal = new GregorianCalendar();
		blbCal.setTimeZone(TimeZone.getTimeZone(GMT));
		blbCal.setTime(calendar.getTime());

		GregorianCalendar localCal = new GregorianCalendar();
		localCal.setTimeZone(TimeZone.getDefault());
		localCal.set(Calendar.YEAR, blbCal.get(Calendar.YEAR));
		localCal.set(Calendar.MONTH, blbCal.get(Calendar.MONTH));
		localCal.set(Calendar.DAY_OF_MONTH, blbCal.get(Calendar.DAY_OF_MONTH));
		localCal.set(Calendar.HOUR_OF_DAY, blbCal.get(Calendar.HOUR_OF_DAY));
		localCal.set(Calendar.MINUTE, blbCal.get(Calendar.MINUTE));
		localCal.set(Calendar.SECOND, blbCal.get(Calendar.SECOND));
		localCal.set(Calendar.MILLISECOND, blbCal.get(Calendar.MILLISECOND));
		localCal.add(Calendar.DAY_OF_YEAR, -BLOOMBERG_DAY_DIFFERENCE);
		
//		logger.debug("Blb2Local: '" + dateTimeFormat.format(calendar.getTime()) + "' => '" +dateTimeFormat.format(localCal.getTime())+ "'");
		
		return localCal;
	}

	/**
	 *  Converts a date represented in the local timezone to a Bloomberg date.
	 * 
	 * <br>
	 *  Bloomberg dates are GMT timeszone. 
	 *  The conversion also adds a magic number of BLOOMBERG_DAY_DIFFERENCE (=2) days. The differnce is not fully understood, but is works.<br>
	 *  Example: 20.08.2003 09:07:17 CET becomes 22.08.2003 11:07:17 GMT.
	 *
	 *@param  date     A date with local timezone.
	 *@return          The GMT Bloomberg date after adding a constant of BLOOMBERG_DAY_DIFFERENCE days.
	 *@see  java.util.Date
	 *@see  java.util.GregorianCalendar
	 *@see  java.util.TimeZone
	 *@see  model.BloombergRequester#BLOOMBERG_DAY_DIFFERENCE        BLOOMBERG_DAY_DIFFERENCE
	 */
	private Calendar convertLocalDateToBloombergDate(Calendar calendar) {
		GregorianCalendar blbCal = new GregorianCalendar();
		blbCal.setTimeZone(TimeZone.getTimeZone(GMT));
		blbCal.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		blbCal.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		blbCal.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
		blbCal.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		blbCal.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		blbCal.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
		blbCal.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND));
		blbCal.add(Calendar.DAY_OF_YEAR, BLOOMBERG_DAY_DIFFERENCE);
		
//		logger.debug("Local2Blb: '" + dateTimeFormat.format(calendar.getTime()) + "' => '" +dateTimeFormat.format(blbCal.getTime())+ "'");

		return blbCal;
	}

	private double dateToTruncatedDouble(Calendar calendar) {
		double d = dateToDaysFromNineteenHundred(calendar)+ BLOOMBERG_DAY_DIFFERENCE;
		// assuming a big difference between d and floor(d)
		// means a truncated date is internally represented as last day about 23:00 or 22:00
		// due to timezone-effects
		if (d - Math.floor(d) > 0.9) {
			return Math.floor(d)+1;
		}
        return Math.floor(d);
	}

	private double dateToDouble(Calendar calendar) { 
		return dateToDaysFromNineteenHundred(convertLocalDateToBloombergDate(calendar));
	}
	
	private Calendar doubleToDate(double dbl) { 
		return convertBloombergDateToLocalDate(daysFromNineteenHundredToDate(dbl));
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
				price = performHistoricRequest(lastRequestedString, requestCal, currency, startCal, stopCal, referencePrice);
				logger.debug("Price[" + k + "] (" + 2 * requestIntervals[i] + " min interval) ("+sources[k]+"): " + price);
				if (price != null) {
//					String bestSource = sources[k];
//					for (int m = k; m > 0; m--) {
//						sources[m] = sources[m-1];
//					}
//					sources[0] = bestSource;
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
			price = performHistoricRequest(requestString, requestCal, currency, startCal, stopCal, referencePrice);
			logger.debug("Price (" + 2 * requestIntervals[i] + " min interval): " + price);
			if (price != null) {
				logger.info("Price (" + 2 * requestIntervals[i] + " min interval): " + price);
			}
		}

		if (price == null) {
			Calendar startDayCal = DateTimeUtils.startOfDayCal(requestCal);

			Calendar stopDayCal = DateTimeUtils.endOfDayCal(requestCal);

			// request data for the whole day from 00:00 to 23:59
			price = performHistoricRequest(requestString, requestCal, currency, startDayCal, stopDayCal, referencePrice);
			logger.debug("Price (24 h interval): " + price);
		}

		return price;
	}

	/**
	 * This method performs a historic request to Bloomberg.
	 * 
	 * <br> 
		* The method looks for a price of the instrument that is closest to a given time.
		* The request is restricted to a time interval.
		* 
		*
	 *@param  requestString     A Bloomberg request ticker format described above.
	 *@param  requestDate       A date Bloomberg tries to find a matching price.
	 *@param  startDate         The lower bound of the time interval  Bloomberg tries to find the matching price.
	 *@param  stopDate          The upper bound of the time interval  Bloomberg tries to find the matching price.
	 *@return                   a HistoricPrice. It is null if no price was found.
	 *@exception  PricingRequestException  Is thrown if no connection to Bloomberg is available or if any error occures during the request.
	 *@see  model.BloombergRequester#performHistoricRequest(String, Date)
		*
		*/
	private HistoricPriceImpl performHistoricRequest(String requestString, Calendar requestDate, String currency, Calendar startDate, Calendar stopDate, double referencePrice)
		throws PricingRequestException {

		if (bloombergDataControlService == null) {
			throw new PricingRequestComunicationException("Not connected to Bloomberg (run BloombergRequester.connect() successfully first)");
		}

		double requestDateAsDouble = dateToDouble(requestDate);
		double startDateAsDouble = dateToDouble(startDate);
		double stopDateAsDouble = dateToDouble(stopDate);

		// Products: An String array of ISIN codes
		String[] products = new String[] { requestString };

		Integer barSize = Integer.valueOf(0);

		logger.debug(
			"Start requesting: '"
				+ requestString
				+ "' with currency '"
				+ currency
				+ "' between "
				+ dateTimeFormat.format(startDate.getTime())
				+ " ("
				+ startDateAsDouble
				+ ") and "
				+ dateTimeFormat.format(stopDate.getTime()) 
                + " ("
                + stopDateAsDouble
                + ") for "+getBloombergFieldArray(historicRequestFields));

		// Call the subscribe method on this object, returning a 3-D array
		try {
			Object returnedValues = null;

			returnedValues =
				bloombergDataControlService.get_BLPGetHistoricalData(
					products,
					historicRequestFields,
					startDate.getTime(),
					stopDate.getTime(),
					barSize);

			logger.debug("Finished requesting: '" + requestString + "'");

			// Work with the returned data
			// Assuption: the returned data is sorted in time!
			if (returnedValues != null && returnedValues instanceof Object[]) {
				double lastPriceTime = 0.0;
				Double lastPriceValue = Double.valueOf(0.0);

				Object[] bbgTimeStampsArray = (Object[]) returnedValues;
				for (int h = 0; h < bbgTimeStampsArray.length; h++) {
					Object[] bbgProductsAtTimeStampArray = (Object[]) bbgTimeStampsArray[h];
					for (int i = 0; i < bbgProductsAtTimeStampArray.length; i++) {
						Object[] bbgResults = (Object[]) bbgProductsAtTimeStampArray[i];
						if (logger.isDebugEnabled()) {
							dumpBloombergResultArray(bbgResults);
						}

						if (bbgResults != null && bbgResults.length > historicRequestFields.length) {

							Double currentDateAsDouble = parseDouble(bbgResults[0]);
							Double currentPrice = parseDouble(bbgResults[1]);
							
					        boolean isPlausible = isPlausiblePrice(referencePrice, convertPriceFromSubUnitToBaseUnit(currency, requestString, currentPrice));

							if (isPlausible && currentDateAsDouble != null && currentPrice != null) {
								double currentPriceTime = currentDateAsDouble.doubleValue();
								if (bbgTimeStampsArray.length == 1) {
									// if only one record is returned, it is taken as the last price too
									lastPriceTime = currentPriceTime;
									lastPriceValue = currentPrice;								
								}
								if (Math.abs(currentPriceTime - requestDateAsDouble) < Math.abs(lastPriceTime - requestDateAsDouble) && h == bbgTimeStampsArray.length-1) {
									// if the last of the returned records is the closest, it is taken as the last price too
									lastPriceTime = currentPriceTime;
									lastPriceValue = currentPrice;								
								}
								if (Math.abs(currentPriceTime - requestDateAsDouble) < Math.abs(lastPriceTime - requestDateAsDouble)) {
									// current price is closer
									lastPriceTime = currentPriceTime;
									lastPriceValue = currentPrice;
								} else {
									if (lastPriceTime > 0.0) {
										// Bloomberg sometime returns values outside the requested range
										if (lastPriceTime < stopDateAsDouble+EQUAL_TOLERANCE && 
												lastPriceTime > startDateAsDouble-EQUAL_TOLERANCE ) { 
										    try {
										        double lastPriceValueInBaseUnit = convertPriceFromSubUnitToBaseUnit(currency, requestString, lastPriceValue); 
										        if (isPlausiblePrice(referencePrice, lastPriceValueInBaseUnit)) {
													return new HistoricPriceImpl(doubleToDate(lastPriceTime), lastPriceValueInBaseUnit);
										        }
                                                logger.error("Price is not plausible: " + lastPriceValue + " ("+referencePrice + ")");
                                            } catch (NumberFormatException nfe) {
                                                logger.error("Invalid data found: " + lastPriceTime + " " + lastPriceValue + ": "+nfe.getMessage());
                                            }
										} else {
											logger.error("Data is outside the time interval: " + lastPriceTime + " ("+startDateAsDouble+ " - " + EQUAL_TOLERANCE + ", " +stopDateAsDouble+ " + " + EQUAL_TOLERANCE + ") " + lastPriceValue);											
										}
									} else {
										logger.debug("No data with valid timestamp found: "+ lastPriceTime + " " + lastPriceValue);
										return null;
									}
								}
							} else {
								logger.error("Invalid data found: " + currentDateAsDouble + " " + currentPrice);
							}
						} else {
							logger.error("Bloomberg result have an unexpected size.");
						}
					}
				}
			} else {
				logger.error("No data found (" + returnedValues + ")");
			}
		} catch (ComException e) {
			if (BLOOMBERG_TIMEOUT_HEXSTRING.equals(Long.toHexString(e.getHResult()))) {
				throw new PricingRequestTimeOutException("Time out after " + bloombergTimeoutSeconds + " seconds. (" + e.getMessage() + ")");
			}
            throw new PricingRequestComunicationException("COM Exception(" + Long.toHexString(e.getHResult()) + "): " + e.getMessage());
		} catch (Exception e) {
			logger.error(e);
			throw new PricingRequestException(e.getMessage());
		}
		return null;
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
//					String bestSource = sources[k];
//					for (int m = k; m > 0; m--) {
//						sources[m] = sources[m-1];
//					}
//					sources[0] = bestSource;
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
	
	   public IntervalPriceImpl performBidAskRequest_old(String requestString, String currency, Calendar requestCalendar, String[] sources, double referencePrice) throws PricingRequestException {

	        if (sources == null){
	            return performBidAskRequest(requestString, currency, requestCalendar, referencePrice);
	        }

	        Calendar requestCal = (Calendar) requestCalendar.clone();
	        requestCal.setTimeZone(TimeZone.getDefault());

	        validateBloombergTicker(requestString);

	        IntervalPriceImpl price = null;
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
	                price = performBidAskRequest(lastRequestedString, requestCal, currency, startCal, stopCal, referencePrice);
	                logger.debug("Price[" + k + "] (" + 2 * requestIntervals[i] + " min interval) ("+sources[k]+"): " + price);
	                if (price != null) {
//	                  String bestSource = sources[k];
//	                  for (int m = k; m > 0; m--) {
//	                      sources[m] = sources[m-1];
//	                  }
//	                  sources[0] = bestSource;
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
	 *@see  model.BloombergRequester#performHistoricRequest(String, Date)
		*
		*/
	private IntervalPriceImpl performBidAskRequest(String requestString, Calendar requestDate, String currency, Calendar startDate, Calendar stopDate, double referencePrice)
		throws PricingRequestException {

		if (bloombergDataControlService == null) {
			throw new PricingRequestComunicationException("Not connected to Bloomberg (run BloombergRequester.connect() successfully first)");
		}

		double requestDateAsDouble = dateToDouble(requestDate);
		double startDateAsDouble = dateToDouble(startDate);
		double stopDateAsDouble = dateToDouble(stopDate);

		// Products: An String array of ISIN codes
		String[] products = new String[] { requestString };

		Integer barSize = Integer.valueOf(0);

		logger.debug(
			"Start requesting: '"
				+ requestString
				+ "' with currency '"
				+ currency
				+ "' between "
				+ dateTimeFormat.format(startDate.getTime())
				+ " ("
				+ startDateAsDouble
				+ ") and "
				+ dateTimeFormat.format(stopDate.getTime())
				+ " ("
				+ stopDateAsDouble
				+ ") for "+getBloombergFieldArray(bidAskRequestFields));

		// Call the subscribe method on this object, returning a 3-D array
		try {
			Object returnedValues = null;

			returnedValues =
				bloombergDataControlService.get_BLPGetHistoricalData(
					products,
					bidAskRequestFields,
					startDate.getTime(),
					stopDate.getTime(),
					barSize);

			logger.debug("Finished requesting: '" + requestString + "'");

			// Work with the returned data
			// Assuption: the returned data is sorted in time!
			if (returnedValues != null && returnedValues instanceof Object[]) {
				double lastPriceTime = 0.0;
				Double lastPrice1Value = new Double(0.0);
				Double lastPrice2Value = new Double(0.0);

				Object[] bbgTimeStampsArray = (Object[]) returnedValues;
				boolean found = false;
				for (int h = 0; h < bbgTimeStampsArray.length; h++) {
					Object[] bbgProductsAtTimeStampArray = (Object[]) bbgTimeStampsArray[h];
					for (int i = 0; i < bbgProductsAtTimeStampArray.length; i++) {
						Object[] bbgResults = (Object[]) bbgProductsAtTimeStampArray[i];
						if (logger.isDebugEnabled()) {
							dumpBloombergResultArray(bbgResults);
						}

						if (bbgResults != null && bbgResults.length > bidAskRequestFields.length) {

							Double currentDateAsDouble = parseDouble(bbgResults[0]);
							Double currentPrice1 = parseDouble(bbgResults[1]);
							Double currentPrice2 = parseDouble(bbgResults[2]);
							
					        boolean isPlausible = isPlausiblePrice(referencePrice, 
					        		convertPriceFromSubUnitToBaseUnit(currency, requestString, currentPrice1), 
					        		convertPriceFromSubUnitToBaseUnit(currency, requestString, currentPrice2));

							if (isPlausible && currentDateAsDouble != null && (currentPrice1 != null || currentPrice2 != null)) {
								double currentPriceTime = currentDateAsDouble.doubleValue();
								if (bbgTimeStampsArray.length == 1) {
									logger.debug("only one record is returned");
									// if only one record is returned, it is taken as the last price too
									lastPriceTime = currentPriceTime;
									if (currentPrice1 != null) {
										lastPrice1Value = currentPrice1;								
										lastPrice2Value = currentPrice1;
									}
									if (currentPrice2 != null) {
										lastPrice1Value = currentPrice2;								
										lastPrice2Value = currentPrice2;
									}
									found = true;
								}
								if (Math.abs(currentPriceTime - requestDateAsDouble) <= Math.abs(lastPriceTime - requestDateAsDouble) 
										&& h == bbgTimeStampsArray.length-1) {
									logger.debug("the last of the returned records is the closest");
									// if the last of the returned records is the closest, it is taken as the last price too
									lastPriceTime = currentPriceTime;
									if (lastPrice1Value == null) {
										lastPrice1Value = currentPrice1;
									}
									if (lastPrice2Value == null) {
										lastPrice2Value = currentPrice2;								
									}									
									found = true;
								}
								if (Math.abs(currentPriceTime - requestDateAsDouble) < Math.abs(lastPriceTime - requestDateAsDouble)) {
									logger.debug("current price is closer");
									// current price is closer
									lastPriceTime = currentPriceTime;
									lastPrice1Value = currentPrice1;
									lastPrice2Value = currentPrice2;
								} else if (currentPriceTime == lastPriceTime) {
									logger.debug("the same timestamp indicated that the second part of the bid/ask price is received");
									// the same timestamp indicated that the second part of the bid/ask price is received
									if (lastPrice1Value == null) {
										lastPrice1Value = currentPrice1;
									}
									if (lastPrice2Value == null) {
										lastPrice2Value = currentPrice2;								
									}									
								} else {
									found = true;
								}
								if (lastPriceTime > 0.0) {
									if (found) {
										if (lastPrice1Value == null) {
											lastPrice1Value = lastPrice2Value;
										}
										if (lastPrice2Value == null) {
											lastPrice2Value = lastPrice1Value;								
										}									

										logger.debug("the last price is saved since the current is not closer");
										// Bloomberg sometime returns values outside the requested range
										if (lastPriceTime < stopDateAsDouble+EQUAL_TOLERANCE && 
												lastPriceTime > startDateAsDouble-EQUAL_TOLERANCE ) {  
										    try {
										        double lastPrice1ValueInBaseUnit = convertPriceFromSubUnitToBaseUnit(currency, requestString, lastPrice1Value);
										        double lastPrice2ValueInBaseUnit = convertPriceFromSubUnitToBaseUnit(currency, requestString, lastPrice2Value);
										        if (isPlausiblePrice(referencePrice, lastPrice1ValueInBaseUnit, lastPrice2ValueInBaseUnit)) {
													return new IntervalPriceImpl(
														doubleToDate(lastPriceTime),
														lastPrice1ValueInBaseUnit,
														lastPrice2ValueInBaseUnit);
										        }
                                                logger.error("Price is not plausible: " + lastPrice1ValueInBaseUnit +" "+lastPrice2ValueInBaseUnit + " ("+referencePrice + ")");
                                            } catch (NumberFormatException nfe) {
                                                logger.error("Invalid data found: " + lastPriceTime + " " + lastPrice1Value +" "+lastPrice2Value+": "+nfe.getMessage());
                                            }
										} else {
											logger.error("Data is outside the time interval: " + lastPriceTime + " ("+startDateAsDouble+ " - " + EQUAL_TOLERANCE + ", " +stopDateAsDouble+ " + " + EQUAL_TOLERANCE + ") " + lastPrice1Value + " " + lastPrice2Value);											
										}
									}
								} else {
									logger.debug("No data with valid timestamp found: "+ lastPriceTime + " " + lastPrice1Value + " " + lastPrice2Value);
									return null;
								}
							} else {
								logger.error("Invalid data found: " + currentDateAsDouble + " " + currentPrice1 + " " + currentPrice2);
							}
						} else {
							logger.error("Bloomberg result have an unexpected size.");
						}
					}
				}
			} else {
				logger.error("No data found (" + returnedValues + ")");
			}
		} catch (ComException e) {
			if (BLOOMBERG_TIMEOUT_HEXSTRING.equals(Long.toHexString(e.getHResult()))) {
				throw new PricingRequestTimeOutException("Time out after " + bloombergTimeoutSeconds + " seconds. (" + e.getMessage() + ")");
			}
            throw new PricingRequestComunicationException("COM Exception(" + Long.toHexString(e.getHResult()) + "): " + e.getMessage());
		} catch (Exception e) {
			logger.error(e);
			throw new PricingRequestException(e.getMessage());
		}
		return null;
	}

	/**
	 * This method performs a high_low request to Bloomberg.
	 * 
	 * <br>
		* The method looks for the highest and lowest price of the instrument at a given date.
		* <br>
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
	 *@param  requestDate       A date Bloomberg tries to find the highest and lowest price.
	 *@param  sources       	An array of Bloomberg sources e.g. [&quot;ING&quot;,&quot;EURX&quot;].
	 *@return                   a HistoricPrice. It is null if no price was found.
	 *@exception  PricingRequestException  Is thrown if no connection to Bloomberg is available or if any error occures during the request.
		*/
	@Override
    public IntervalPriceImpl performHighLowRequest(String requestString, String currency, Calendar requestDate, String[] sources, double referencePrice) throws PricingRequestException {
		if (sources == null){
			return performHighLowRequest(requestString, currency, requestDate, referencePrice);
		}

		for (int k = 0; k < sources.length; k++) {
			lastRequestedString = addSourceToRequestString(requestString, sources[k]);
			IntervalPriceImpl price = performHighLowRequest(lastRequestedString, currency, requestDate, referencePrice);
			if (price != null) {
//				String bestSource = sources[k];
//				for (int m = k; m > 0; m--) {
//					sources[m] = sources[m-1];
//				}
//				sources[0] = bestSource;
				lastRequestedSourceArray = sources;
				logger.info("Price[" + k + "] (H/L) ("+sources[k]+"): " + price);
				return price;
			}
		}
		return null;
	}

		
	/**
	 * This method performs a high_low request to Bloomberg.
	 * 
	 * <br>
		* The method looks for the highest and lowest price of the instrument at a given date.
		* <br>
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
	 *@param  requestDate       A date Bloomberg tries to find the highest and lowest price.
	 *@return                   a HistoricPrice. It is null if no price was found.
	 *@exception  PricingRequestException  Is thrown if no connection to Bloomberg is available or if any error occures during the request.
		*/
	public IntervalPriceImpl performHighLowRequest(String requestString, String currency, Calendar requestDate, double referencePrice) throws PricingRequestException {

		if (bloombergDataControlService == null) {
			throw new PricingRequestComunicationException("Not connected to Bloomberg (run BloombergRequester.connect() successfully first)");
		}

		validateBloombergTicker(requestString);

		// Products: An Object array of ISIN codes
		String[] products = new String[] { requestString };

		// HighLow prices have 00:00:00 as time so the double without decimal part need to be compared
		double requestDateAsDouble = dateToTruncatedDouble(requestDate);
		
		Calendar startDate = requestDate;

		Calendar stopDate = requestDate;

		logger.debug("Start requesting: '" + requestString + "' with currency + '" + currency + "' on " + dateTimeFormat.format(requestDate.getTime()) + " ("+getBloombergFieldArray(highLowRequestFields)+")");

		// Call the subscribe method on this object, returning a 3-D array
		try {
			Object returnedValues = null;

			returnedValues =
				bloombergDataControlService.get_BLPGetHistoricalData(
					products,
					highLowRequestFields,
					startDate.getTime(),
					stopDate.getTime());

			logger.debug("Finished requesting: '" + requestString + "'");

			// Work with the returned data
			if (returnedValues != null && returnedValues instanceof Object[]) {
				Object[] bbgTimeStampsArray = (Object[]) returnedValues;
				for (int h = 0; h < bbgTimeStampsArray.length; h++) {
					Object[] bbgProductsAtTimeStampArray = (Object[]) bbgTimeStampsArray[h];
					for (int i = 0; i < bbgProductsAtTimeStampArray.length; i++) {
						Object[] bbgResults = (Object[]) bbgProductsAtTimeStampArray[i];
						if (logger.isDebugEnabled()) {
							dumpBloombergResultArray(bbgResults);
						}
						if (bbgResults != null && bbgResults.length > highLowRequestFields.length) {
							Double dateAsDouble = parseDouble(bbgResults[0]);
							Double lowPrice = parseDouble(bbgResults[1]);
							Double highPrice = parseDouble(bbgResults[2]);
							if (dateAsDouble != null && lowPrice != null && highPrice != null) {
								// Bloomberg sometime returns values outside the requested range
								if (dateAsDouble.doubleValue() < requestDateAsDouble+EQUAL_TOLERANCE && 
										dateAsDouble.doubleValue() > requestDateAsDouble-EQUAL_TOLERANCE ) {
                                    try {
                                        double lowPriceInBaseUnit = convertPriceFromSubUnitToBaseUnit(currency, requestString, lowPrice); 
                                        double highPriceInBaseUnit = convertPriceFromSubUnitToBaseUnit(currency, requestString, highPrice); 
                                        if (isPlausiblePrice(referencePrice, lowPriceInBaseUnit, highPriceInBaseUnit)) {
											return new IntervalPriceImpl(doubleToDate(dateAsDouble.doubleValue()), lowPriceInBaseUnit, highPriceInBaseUnit);
                                        }
                                        logger.error("Price is not plausible: " + lowPriceInBaseUnit + " " + highPriceInBaseUnit + " ("+referencePrice + ")");
                                    } catch (NumberFormatException nfe) {
                                        logger.error("Invalid data found: " + dateAsDouble + " " + lowPrice + " " + highPrice+ ": "+nfe.getMessage());
                                    }
								} else {
									logger.error("Data is outside the time interval: " + dateAsDouble + " ("+requestDateAsDouble+ " +/- " + EQUAL_TOLERANCE + ") " + lowPrice + " " + highPrice);									
								}
							} else {
								logger.error("Invalid data found: " + dateAsDouble + " " + lowPrice + " " + highPrice);
							}
						} else {
							logger.error("Bloomberg result has an unexpected size.");
						}
					}
				}
			} else {
				logger.error("No data found (" + returnedValues + ")");
			}

		} catch (ComException e) {
			if (BLOOMBERG_TIMEOUT_HEXSTRING.equals(Long.toHexString(e.getHResult()))) {
				throw new PricingRequestTimeOutException("Time out after " + bloombergTimeoutSeconds + " seconds. (" + e.getMessage() + ")");
			}
            throw new PricingRequestComunicationException("COM Exception(" + Long.toHexString(e.getHResult()) + "): " + e.getMessage());
		} catch (Exception e) {
			logger.error(e);
			throw new PricingRequestException(e.getMessage());
		}
		return null;
	}

	/**
	 * @param bbgResults
	 */
	private void dumpBloombergResultArray(Object[] bbgResults) {
		if (bbgResults != null) {
			for (int i = 0; i < bbgResults.length; i++) {
				logger.debug(" Bloomberg result(" + i + ") = " + bbgResults[i]);
			}
		}
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

	@Override
    public HistoricPriceImpl performRequest(String requestString, String currency, Calendar requestDate, int daysGoBack, double referencePrice) throws PricingRequestException {
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

	/**
	 * This method performs a  request to Bloomberg.
	 * 
	 * <br>
		* The method looks for the price of the given field of the instrument at a given date.
		* <br>
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
	 *@param  requestField     A Bloomberg field.
	 *@param  currency			The currency for the requested price.
	 *@param  requestDate       A date Bloomberg tries to find the requested price.
	 *@return                   a HistoricPrice. It is null if no price was found.
	 *@exception  PricingRequestException  Is thrown if no connection to Bloomberg is available or if any error occures during the request.
		*/
	@Override
    public HistoricPriceImpl performRequest(String requestString, String currency, Calendar requestDate, double referencePrice) throws PricingRequestException {

		if (bloombergDataControlService == null) {
			throw new PricingRequestComunicationException("Not connected to Bloomberg (run BloombergRequester.connect() successfully first)");
		}

		validateBloombergTicker(requestString);

		// Products: An Object array of ISIN codes
		String[] products = new String[] { requestString };

		// BloombergFields: A String array of fields we want Bloomberg to calculate values for
		String[] bloombergFields = null;
		bloombergFields = genericRequestFields;

		// daily field have 00:00:00 as time so the doublwe without decimal part need to be compared
		double requestDateAsDouble = Math.floor(dateToDouble(requestDate));

		Calendar startDate = requestDate;

		Calendar stopDate = requestDate;

		logger.debug("Start requesting: '" + requestString + "' with currency + '" + currency + "' on " + dateTimeFormat.format(requestDate.getTime()) + " (" + requestDateAsDouble + ") for "+getBloombergFieldArray(bloombergFields));

		// Call the subscribe method on this object, returning a 3-D array
		try {
			Object returnedValues = null;

			returnedValues =
				bloombergDataControlService.get_BLPGetHistoricalData(
					products,
					bloombergFields,
					startDate.getTime(),
					stopDate.getTime());

			logger.debug("Finished requesting: '" + requestString + "'");

			// Work with the returned data
			if (returnedValues != null && returnedValues instanceof Object[]) {
				Object[] bbgTimeStampsArray = (Object[]) returnedValues;
				for (int h = 0; h < bbgTimeStampsArray.length; h++) {
					Object[] bbgProductsAtTimeStampArray = (Object[]) bbgTimeStampsArray[h];
					for (int i = 0; i < bbgProductsAtTimeStampArray.length; i++) {
						Object[] bbgResults = (Object[]) bbgProductsAtTimeStampArray[i];
						if (bbgResults != null && bbgResults.length > bloombergFields.length) {
							if (logger.isDebugEnabled()) {
								dumpBloombergResultArray(bbgResults);
							}
							Double dateAsDouble = parseDouble(bbgResults[0]);
							Double price = parseDouble(bbgResults[1]);
							if (dateAsDouble != null && price != null) {
								// Bloomberg sometime returns values outside the requested range
								if (dateAsDouble.doubleValue() < requestDateAsDouble+EQUAL_TOLERANCE && 
										dateAsDouble.doubleValue() > requestDateAsDouble-EQUAL_TOLERANCE ) {
                                    try {
                                        double priceInBaseUnit = convertPriceFromSubUnitToBaseUnit(currency, requestString, price); 
                                        if (isPlausiblePrice(referencePrice, priceInBaseUnit)) {
											return new HistoricPriceImpl(doubleToDate(dateAsDouble.doubleValue()), priceInBaseUnit);
                                        }
                                        logger.error("Price is not plausible: " + price + " ("+priceInBaseUnit + ")");
                                    } catch (NumberFormatException nfe) {
                                        logger.error("Invalid data found: " + dateAsDouble + " " + price + ": " + nfe.getMessage());
                                    }
								} else {
									logger.error("Data is outside the time interval: " + dateAsDouble + " ("+requestDateAsDouble+ " +/- " + EQUAL_TOLERANCE + ") " + price);									
								}
							} else {
								logger.error("Invalid data found: " + dateAsDouble + " " + price);
							}
						} else {
							logger.error("Bloomberg result have an unexpected size.");
						}
					}
				}
			} else {
				logger.error("No data found (" + returnedValues + ")");
			}

		} catch (ComException e) {
			if (BLOOMBERG_TIMEOUT_HEXSTRING.equals(Long.toHexString(e.getHResult()))) {
				throw new PricingRequestTimeOutException("Time out after " + bloombergTimeoutSeconds + " seconds. (" + e.getMessage() + ")");
			}
            throw new PricingRequestComunicationException("COM Exception(" + Long.toHexString(e.getHResult()) + "): " + e.getMessage());
		} catch (Exception e) {
			logger.error(e);
			throw new PricingRequestException(e.getMessage());
		}
		return null;
	}

	/**
	 * @param requestString
	 */
	private void validateBloombergTicker(String requestString) {
		if (!isValidBloombergTicker(requestString)) {
			//			throw new PricingRequestException("Invalid Bloomberg ticker (Format: <name> [exchange] <yellow key> [type]) :" + requestString);
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

	/**
	 * Returns the bloombergTimeoutSeconds.
	 * @return int
	 */
	public int getBloombergTimeoutSeconds() {
		return bloombergTimeoutSeconds;
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

	/**
	 * Sets the bloombergTimeoutSeconds.
	 * @param bloombergTimeoutSeconds The bloombergTimeoutSeconds to set
	 */
	public void setBloombergTimeoutSeconds(int bloombergTimeoutSeconds) {
		this.bloombergTimeoutSeconds = bloombergTimeoutSeconds;
	}

	private Double parseDouble(Object object) {
		try {
		    if (object != null && object.toString().startsWith("#")) {
				logger.error("Bloomberg: " + BLOOMBERG_MASSAGES.get(object) + "("+object+")");		        
		        return null;
		    }
			if (object != null) {
				return (Double) object;
			}
		} catch (ClassCastException cce) {
			logger.error("Invalid Double found (ClassCastException): " + object);
			try {
				String s = (String) object;
				return new Double(Double.parseDouble(s));
			} catch (ClassCastException cce1) {
				logger.error("Invalid Double found (ClassCastException): " + object);
			} catch (NumberFormatException nfe) {
				logger.error("Invalid Double found (NumberFormatException): " + (String) object);
			}
		}
		return null;
	}

	private double convertPriceFromSubUnitToBaseUnit(String isoCurrency, String requestString, Double price) throws NumberFormatException {
		if (price == null) {
			return 0;
		}
		if (requestMapping.containsKey(requestString)) {
		    double factor = requestMapping.get(requestString);
            logger.debug("Multiplying the Bloomberg price with "+factor+" to get the price in the correct unit.");
		    return price * factor; 
		}
		if (("GBP".equalsIgnoreCase(isoCurrency) || "ZAR".equalsIgnoreCase(isoCurrency)) && requestString.indexOf("Equity")>=0 ) {
			logger.debug("Deviding the Bloomberg price by 100 to get the price in the requested currency '" + isoCurrency + "' and not in the sub-unit.");
			return price.doubleValue() / 100.0;
		}
        return price.doubleValue();
	}

	/**
	 * @return Returns the lastRequestetSourceArray.
	 */
	@Override
    public String[] getLastRequestedSourceArray() {
		return lastRequestedSourceArray;
	}

	@Override
    public String getLastRequestedString() {
		return lastRequestedString;
	}
	
	String addSourceToRequestString(String requestString, String source){
		int sourcePosStart = requestString.indexOf("@");
		if (sourcePosStart < 0) {
			sourcePosStart = requestString.indexOf(' ');
		}
		int sourcePosStop = requestString.indexOf(" ", sourcePosStart);
		
		if (sourcePosStart > -1 && sourcePosStop > -1) {
			return requestString.substring(0,sourcePosStart)+ "@"+source+requestString.substring(sourcePosStop);
		}
        return requestString;
	}

	
   /**
    * Checks id the reference price and the price are in the same order of magnitude.
    * Both prices must be different from zero.
    *  
    * @param referencePrice
    * @param price
    * @return
    */
	private boolean isPlausiblePrice(double referencePrice, double price) {
		return referencePrice == 0d || (price != 0d && Math.abs(price/referencePrice) > 1/ORDER_OF_MAGNITUDE && Math.abs(price/referencePrice) < ORDER_OF_MAGNITUDE);
	}

    /**
     * Checks id the reference price and either price1 or price2 are in the same order of magnitude.
     * The refernce price and either price1 or price2 must be different from zero.
     * 
     * @param referencePrice
     * @param price1
     * @param price2
     * @return
     */
    private boolean isPlausiblePrice(double referencePrice, double price1, double price2) {
        if (price1 != 0d) {
            if ( price2 != 0d) {
                return isPlausiblePrice(referencePrice, (price1+price2)*0.5);
            }
            return isPlausiblePrice(referencePrice, price1);
        }
        if ( price2 != 0d) {
            return isPlausiblePrice(referencePrice, price2);
        }
        return false;
    }

    public void setRequestMapping(Map<String,Double> requestMapping) {
        this.requestMapping = requestMapping;
    }

}