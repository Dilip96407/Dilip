/* 
 * 
 */

package de.westlb.mgb.bloomberg.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.auth.AuthenticationException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.CompositeTag;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

import de.westlb.mgb.client.application.MgbProperty;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.model.impl.HistoricPriceImpl;
import de.westlb.mgb.model.impl.IntervalPriceImpl;
import de.westlb_systems.xaf.util.PropertyFactory;

public class EuwaxWebRequester implements PricingRequester {

	static Logger logger = Logger.getLogger(EuwaxWebRequester.class);

	private final Format dateTimeFormat = new DateFormat(DateFormat.DATETIME_FORMAT_LONG);

	private final int MAX_NUMBER_OF_PARSING_ERROR_PER_REQUEST = 2;
	
	private final String PROPERTIES_NAME = "euwax";
	
	private boolean waitForNextRequest = false;
	private long requestDelayMillies = 1000;
	private int [] requestIntervals = null;
	private String euwaxHost = null;
	private String euwaxUrl = null;
	private String wlbDomain = null;
	private String wlbProxyHost = null;
	private int wlbProxyPort = 8080;
	private String wlbUser = null;
	private String wlbPassword = null;
	private String localhost = null;
	private String requestParamWkn = null;
	private String requestParamDay = null;
	private String requestParamMonth = null;
	private String requestParamYear = null;
	private String requestParamBeginHour = null;
	private String requestParamEndHour = null;
	private String requestParamBeginMinute = null;
    private String requestParamEndMinute = null;
	private HttpClient httpclient = null;
	
	private String timeColumnHeader = null;
	private String marketColumnHeader = null;
	private int responseHeaderLineOffset;
	private int responseTrailerLines;
	private int responseIdxTime;
	private int responseIdxBid;
	private int responseIdxAsk;
    private String responseNoDataYetMessage = null;
    private int responseDisplayLimit;

	private SimpleDateFormat requestDateFormat = null;
	private String requestDateFormatExtensionString = "_yyyyMMdd_z";
	private SimpleDateFormat requestDateFormatExtension = new SimpleDateFormat(requestDateFormatExtensionString);
	private DecimalFormat requestNumberFormat = null;

	public EuwaxWebRequester() {
	}

	public void init(String domainUser, byte[] encodedPasswordBytes) throws Exception {
		setDomainUser(domainUser);
		setWlbPassword(encodedPasswordBytes);
		init();
	}
	
	public void init() throws Exception {
		logger.info("Initializing EuwaxWebRequester...");
		if (PropertyFactory.getProperty(MgbProperty.EUWAX_URL) == null) {
			PropertyFactory.load(PROPERTIES_NAME);
		}
		waitForNextRequest = false;
		wlbProxyHost = PropertyFactory.getProperty(MgbProperty.EUWAX_WESTLB_PROXY_HOST);
		wlbProxyPort = Integer.parseInt(PropertyFactory.getProperty(MgbProperty.EUWAX_WESTLB_PROXY_PORT));
		euwaxHost = PropertyFactory.getProperty(MgbProperty.EUWAX_HOST);
		euwaxUrl = PropertyFactory.getProperty(MgbProperty.EUWAX_URL);
		requestParamWkn = PropertyFactory.getProperty(MgbProperty.EUWAX_REQUEST_PARAM_WKN);
		requestParamDay = PropertyFactory.getProperty(MgbProperty.EUWAX_REQUEST_PARAM_DAY);
		requestParamMonth = PropertyFactory.getProperty(MgbProperty.EUWAX_REQUEST_PARAM_MONTH);
		requestParamYear = PropertyFactory.getProperty(MgbProperty.EUWAX_REQUEST_PARAM_YEAR);
		requestParamBeginHour = PropertyFactory.getProperty(MgbProperty.EUWAX_REQUEST_PARAM_BEGIN_HOUR);
		requestParamEndHour = PropertyFactory.getProperty(MgbProperty.EUWAX_REQUEST_PARAM_END_HOUR);
		requestParamBeginMinute = PropertyFactory.getProperty(MgbProperty.EUWAX_REQUEST_PARAM_BEGIN_MINUTE);
		requestParamEndMinute = PropertyFactory.getProperty(MgbProperty.EUWAX_REQUEST_PARAM_END_MINUTE);
		
		requestDelayMillies = Long.parseLong(PropertyFactory.getProperty(MgbProperty.EUWAX_REQUEST_DELAY_MILLIES));

		timeColumnHeader = PropertyFactory.getProperty(MgbProperty.EUWAX_RESPONSE_TIME_HEADER);
		marketColumnHeader = PropertyFactory.getProperty(MgbProperty.EUWAX_RESPONSE_MARKET_HEADER);
		
		responseHeaderLineOffset = PropertyFactory.getIntProperty(MgbProperty.EUWAX_RESPONSE_HEADER_LINE_OFFSET);  
		responseIdxTime = PropertyFactory.getIntProperty(MgbProperty.EUWAX_RESPONSE_IDX_TIME);
		responseIdxBid = PropertyFactory.getIntProperty(MgbProperty.EUWAX_RESPONSE_IDX_BID);
		responseIdxAsk = PropertyFactory.getIntProperty(MgbProperty.EUWAX_RESPONSE_IDX_ASK);
		responseTrailerLines = PropertyFactory.getIntProperty(MgbProperty.EUWAX_RESPONSE_TRAILER_LINES);
		responseNoDataYetMessage = PropertyFactory.getProperty(MgbProperty.EUWAX_RESPONSE_NO_DATA_YET_MESSAGE);
        responseDisplayLimit = PropertyFactory.getIntProperty(MgbProperty.EUWAX_RESPONSE_DISPLAY_LIMIT);

		String originalFormatString = PropertyFactory.getProperty(MgbProperty.EUWAX_RESPONSE_DATE_FORMAT);
		if (originalFormatString.length() < 12) {
			// assuming to date ist set in the requestDateFormat
			requestDateFormat = new SimpleDateFormat(originalFormatString + requestDateFormatExtensionString);
		} else {
			requestDateFormat = new SimpleDateFormat(originalFormatString);
		}

		requestNumberFormat = new DecimalFormat();
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(PropertyFactory.getProperty(MgbProperty.EUWAX_RESPONSE_DECIMAL_SEPARATOR).charAt(0));
		requestNumberFormat.setDecimalFormatSymbols(symbols);

		requestIntervals = getRequestIntervals();

// the localhost could be set to the clients machine but does not work for a unix server		
//		localhost = Inet4Address.getLocalHost().getHostName();
		localhost = "";

		if (wlbUser == null) {
			setDomainUser(MgbServiceFactory.getService().getDomainUser());
		}
		
		httpclient = initializeHttpClient();

		logger.info("Requesting EUWAX via "+wlbProxyHost+":"+wlbProxyPort+" with user "+wlbDomain+"/"+wlbUser+"@"+localhost);
		if (wlbPassword != null) {
			logger.info("Password has been set");
//			logger.debug(wlbPassword);
		} else {
			throw new AuthenticationException("Password has not been set, before the init() method was called.");
		}
		logger.info("ExampleURL: "+buildRequestUrl("123456",new GregorianCalendar(), new GregorianCalendar()));		
		logger.info("Response requestDateFormat: dateFormat='"+originalFormatString+"'(internally set to '"+requestDateFormat.toPattern()+"')) decimalSeparator='"+symbols.getDecimalSeparator()+"' header='"+timeColumnHeader+"','"+marketColumnHeader+"'");
	    logger.info("Initialized EuwaxWebRequester");
	}

	/**
	 * @param domainUser
	 */
	public void setDomainUser(String domainUser) {
		wlbUser = extractUserFromDomainUser(domainUser);
		wlbDomain = extractDomainFromDomainUser(domainUser);
	}

	/**
	 * 
	 */
	private HttpClient initializeHttpClient() {
		HttpClient localHttpclient = new HttpClient();
		localHttpclient.getHostConfiguration().setHost(euwaxHost);
		localHttpclient.getHostConfiguration().setProxy(wlbProxyHost, wlbProxyPort);
		
		localHttpclient.getState().setProxyCredentials(
				new AuthScope(wlbProxyHost, wlbProxyPort, null),
				new NTCredentials(wlbUser, wlbPassword, localhost, wlbDomain));
		return localHttpclient;
	}

	@Override
    public HistoricPriceImpl performHistoricRequest(String requestString, String currency, Calendar requestCalendar, String[] sources, double referencePrice) throws PricingRequestException {
		HistoricPriceImpl price = null;
		for (int i = 0; i < requestIntervals.length && price == null; i++) {

			Calendar startCal = (Calendar) requestCalendar.clone();
			startCal.setTimeZone(TimeZone.getDefault());
			startCal.add(Calendar.MINUTE, -requestIntervals[i]);

			Calendar stopCal = (Calendar) requestCalendar.clone();
			stopCal.setTimeZone(TimeZone.getDefault());
			stopCal.add(Calendar.MINUTE, requestIntervals[i]);

			logger.debug("Start requesting: '"+ requestString+ "' between "+ dateTimeFormat.format(startCal.getTime())+ " and "+ dateTimeFormat.format(stopCal.getTime()));
			
			price = request(requestString, requestCalendar, startCal, stopCal);
			if (price != null) {
				logger.info("Price (" + 2 * requestIntervals[i] + " min interval): " + price);
			} else {
	            logger.debug("Price (" + 2 * requestIntervals[i] + " min interval): " + price);
			}
		}
		
		return price;
	}

	@Override
    public IntervalPriceImpl performHighLowRequest(String requestString, String currency, Calendar requestDate, String[] sources, double referencePrice) throws PricingRequestException {
		logger.debug("Start requesting: '" + requestString + "' on " + dateTimeFormat.format(requestDate.getTime()));
		logger.debug("Request currency: '"+currency+"'");

		throw new PricingRequestException("not yet imlemented");
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

	@Override
    public HistoricPriceImpl performRequest(String requestString, String currency, Calendar requestDate, double referencePrice) throws PricingRequestException {
		return performHistoricRequest(requestString, currency, requestDate, null, referencePrice);
	}

	private TableRow[] requestEuwaxData(String wkn, Calendar startDate, Calendar stopDate) throws PricingRequestException {
		try {
			String responseHtml = null;
			boolean readDataFromUrl = true; // set to false to read a testfile 
			if (readDataFromUrl) { 
				String euwaxUrl = buildRequestUrl(wkn, startDate, stopDate);
				logger.debug("URL: "+euwaxUrl);
				if (waitForNextRequest) {
				    try {
		                logger.debug("Waiting "+requestDelayMillies+" milli seconds to start the request");
				        Thread.sleep(requestDelayMillies);
				    } catch (InterruptedException ie) {
				    }
				} else {
                    waitForNextRequest = true;				    
				}
				GetMethod httpget = null;
				
				try {
					httpget = new GetMethod(euwaxUrl);
					int status = httpclient.executeMethod(httpget);
					if (status >= 400) {
						logger.error("State: "+httpget.getStatusLine());
						throw new HttpException("State: "+httpget.getStatusLine()+"; the request was: "+euwaxUrl);
					}
	                responseHtml = stringFromReader(new BufferedReader(new InputStreamReader(httpget.getResponseBodyAsStream(), "UTF-8")));
				} finally {
					// release any connection resources used by the method
					httpget.releaseConnection();
				}
			} else {
				// just for testing purposes
	             responseHtml = stringFromReader(new FileReader("G:\\Projects\\Tradecontrol_it\\TC-IT-Support\\MGB Tools\\MGB ab 04\\concepts\\Marktdaten_webservice\\euwax-archiv2.html"));
			}
            logger.debug("HTML: "+responseHtml);
            Parser parser = new Parser();
            parser.setInputHTML(responseHtml);
			
			// Search for the result table, because there are several tables in the
			// result.
			NodeClassFilter htmlTableTagFilter = new NodeClassFilter(TableTag.class);
			// Extract the html-tables
			NodeList htmlTableList = parser.extractAllNodesThatMatch(htmlTableTagFilter);
			// Iterate over the html-tables
			for (int i = 0; i < htmlTableList.size(); i++) {	
				TableTag table = (TableTag) htmlTableList.elementAt(i);
				TableRow[] tableRows = table.getRows();
				// The current criteria is the time and and market text in the
				// first row.
				boolean foundTimeColumnHeader = false;
				boolean foundMarketColumnHeader = false;
				int numberOfCheckHeaderRows = 3;
				for (int j = 0; j < numberOfCheckHeaderRows && j < tableRows.length; j++) {
					if (!foundTimeColumnHeader) {
						foundTimeColumnHeader = tableRows[j].searchFor(timeColumnHeader).size() > 0;
					}
					if (!foundMarketColumnHeader) {
						foundMarketColumnHeader = tableRows[j].searchFor(marketColumnHeader).size() > 0;
					}
					if (foundMarketColumnHeader && foundTimeColumnHeader) {
						return tableRows;
					}
				}
			}
			logger.error("No prices found");
			if (htmlTableList.size() == 0) {
				throw new PricingRequestComunicationException("No HTML-table found in the response:"+responseHtml);
			}
			if (responseHtml.indexOf(responseNoDataYetMessage) >= 0) {
                throw new PricingRequestTimeOutException("The EUWAX response contained the following message indicating missing archive data: '"+responseNoDataYetMessage+"'");				    
			}
		} catch (HttpException he) {
			throw new PricingRequestComunicationException(he);
		} catch (IOException io) {
			throw new PricingRequestComunicationException(io);
		} catch (ParserException pe) {
			throw new PricingRequestComunicationException(pe);
		}
		return null;
	}

	private String stringFromReader(Reader reader) throws IOException {
	    int c;
	    StringBuffer buffer = new StringBuffer();  
	    try {
	        while ((c = reader.read()) != -1) {
	            buffer.append((char)c);
	        }
	    } catch (IOException e) {
	        logger.error("Error while reading data",e);
	    } finally {
	        reader.close();
	    }
	    return buffer.toString();
	}

	private HistoricPriceImpl request(String wkn, Calendar requestDate, Calendar startDate, Calendar stopDate) throws PricingRequestException {
			long startMillis = startDate.getTimeInMillis();
			long requestMillis = requestDate.getTimeInMillis();

			long lastPriceTime = startMillis;
			double lastPrice = 0;
			int parsingErrorCount = 0;

			TableRow[] tableRows = requestEuwaxData(wkn, startDate, stopDate);  			
			if (tableRows != null) {
			    int numberOfDataInResponse = tableRows.length - responseTrailerLines;
			    if ((numberOfDataInResponse-responseHeaderLineOffset) == responseDisplayLimit) {
			        throw new PricingRequestException("Review the interval sizes in "+PROPERTIES_NAME+".properties (smaller interval or larger display number for euwax). The number of returned prices reached the display level of "+responseDisplayLimit);
			    }
				for (int j = responseHeaderLineOffset; j < numberOfDataInResponse; j++) {
					TableColumn[] tableColumns = tableRows[j].getColumns();
					String time = getTextBetweenTags(tableColumns[responseIdxTime]);
					String ask = getTextBetweenTags(tableColumns[responseIdxAsk]);
					String bid = getTextBetweenTags(tableColumns[responseIdxBid]);
					logger.debug("EUWAX: " + time + " b="+ bid + " a="+ ask);
					try {
						// the following line takes the day of requestDate to include the correct timezone/daylight saving time 
						double currentPrice = parseMidPrice(ask, bid);
                        long currentPriceTime = parsePriceDate(requestDate, time);
						logger.debug("     : " + currentPriceTime + " "+ currentPrice );
						if (lastPriceTime == startMillis) {
							// initialize with the first price
							lastPriceTime = currentPriceTime;
							lastPrice = currentPrice;								
						}
						if (Math.abs(currentPriceTime - requestMillis) < Math.abs(lastPriceTime - requestMillis) && j == tableRows.length-1) {
							// if the last of the returned records is the closest, it is taken as the last price too
							lastPriceTime = currentPriceTime;
							lastPrice = currentPrice;								
						}
						if (Math.abs(currentPriceTime - requestMillis) <= Math.abs(lastPriceTime - requestMillis)) {
							// current price is closer
							lastPriceTime = currentPriceTime;
							lastPrice = currentPrice;
							// price was closer and is later than the requested -> take it
							if (lastPriceTime > requestMillis && lastPrice > 0.0) {
                                GregorianCalendar priceDate = new GregorianCalendar();
                                priceDate.setTimeInMillis(lastPriceTime);
                                return new HistoricPriceImpl(priceDate, lastPrice);
							}
                            // price was closer and is the last in the list -> take it
                            if ((j+1) == numberOfDataInResponse && lastPrice > 0.0) {
                                GregorianCalendar priceDate = new GregorianCalendar();
                                priceDate.setTimeInMillis(lastPriceTime);
                                return new HistoricPriceImpl(priceDate, lastPrice);
                            }
						} else {
							if (lastPrice > 0.0) {
								GregorianCalendar priceDate	= new GregorianCalendar();
								priceDate.setTimeInMillis(lastPriceTime);
								return new HistoricPriceImpl(priceDate, lastPrice);
							}	
						}
					} catch(ParseException pe) {
					    parsingErrorCount++;
						logger.error("Required formats: dateFormat='"+requestDateFormat.toPattern()+"'(internally extended with '"+requestDateFormatExtension.toPattern()+"')) decimalFormat='"+requestNumberFormat.toPattern()+"'. Error: "+pe.getMessage() );
						if (parsingErrorCount > MAX_NUMBER_OF_PARSING_ERROR_PER_REQUEST) {
						    throw new PricingRequestTimeOutException("The EUWAX response contained more than "+MAX_NUMBER_OF_PARSING_ERROR_PER_REQUEST+" parsing errors, and seems not to be available correctly.");
						}
					} catch(Exception e) {
						logger.error("Invalid data: "+e.getMessage());
					}
				}
			}
		return null;
	}

	/**
	 * @param requestDate
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	private long parsePriceDate(Calendar requestDate, String time) throws ParseException {
		return requestDateFormat.parse(time+requestDateFormatExtension.format(requestDate.getTime())).getTime();
	}

	/**
	 * @param ask
	 * @param bid
	 * @return
	 * @throws ParseException
	 */
	private double parseMidPrice(String ask, String bid) throws ParseException {
		if ("&nbsp".equals(ask) || "&nbsp;".equals(ask) || "-1".equals(ask)) {
			return requestNumberFormat.parse(bid).doubleValue();
		}
		return (requestNumberFormat.parse(bid).doubleValue() + requestNumberFormat.parse(ask).doubleValue())/2.0;
	}

	/*
	 * Returns the body text of the tag. Because the text is included in 
	 * <b></b> tags in some cases, it goes recursive to the leaf tag before
	 * returning the body text.
	 */
    private String getTextBetweenTags(Node tag) {
        if (tag instanceof CompositeTag) {
            CompositeTag c = (CompositeTag)tag;
            if (c.getChildCount() > 0) {
                SimpleNodeIterator it = c.children();
                while (it.hasMoreNodes()) {
                    Node node = it.nextNode();
                    if (node instanceof TextNode) {
                        String txt = getTextBetweenTags(node);
                        if (txt != null && txt.length() > 0)
                            return txt;
                    }
                }
                
            } 
        } 
        String text = tag.getText();
        if (text != null) {
            text = text.trim();
        }
        return text;
    }

    private int[] getRequestIntervals() {
		// MB. Added the possibility to modify the requested fields in client.properties for testing. 
		if (requestIntervals == null) {
			String fieldListStr = PropertyFactory.getProperty(MgbProperty.EUWAX_REQUEST_INTERVALS);
			if (fieldListStr != null) {
				String[] requestIntervalStrings = StringUtils.split(fieldListStr, ",");
				if (requestIntervalStrings != null && requestIntervalStrings.length > 0) {
					requestIntervals = new int[requestIntervalStrings.length];
					try {
						for (int i = 0; i < requestIntervalStrings.length; i++) {
							requestIntervals[i] = Integer.parseInt(requestIntervalStrings[i]);
						}
					} catch (NumberFormatException nfe) {
						logger.error("Error while parsing the parameter '"+MgbProperty.EUWAX_REQUEST_INTERVALS+"': " + nfe.getMessage());
					}
				}

			}
			if (requestIntervals == null || requestIntervals.length == 0) {
				requestIntervals = new int[] {1, 5, 60 };
				logger.warn("Using default requestIntervals");
			}
			for (int i = 0; i < requestIntervals.length; i++) {
				logger.debug("requestIntervals " + i + " = " + requestIntervals[i]);
			}
		}
		return requestIntervals;
	}

	private String buildRequestUrl(String wkn, Calendar startDate, Calendar stopDate) {
		StringBuffer sb = new StringBuffer(euwaxUrl).append("?");
		sb.append(requestParamWkn).append("=").append(wkn).append("&");
		sb.append(requestParamDay).append("=").append(startDate.get(Calendar.DAY_OF_MONTH)).append("&");
		sb.append(requestParamMonth).append("=").append(startDate.get(Calendar.MONTH)+1).append("&");
		sb.append(requestParamYear).append("=").append(startDate.get(Calendar.YEAR)).append("&");
		sb.append(requestParamBeginHour).append("=").append(startDate.get(Calendar.HOUR_OF_DAY)).append("&");
		sb.append(requestParamEndHour).append("=").append(stopDate.get(Calendar.HOUR_OF_DAY)).append("&");
		sb.append(requestParamBeginMinute).append("=").append(startDate.get(Calendar.MINUTE)).append("&");
        sb.append(requestParamEndMinute).append("=").append(stopDate.get(Calendar.MINUTE)).append("&");
		sb.append("x=11&y=10");
		return sb.toString();
	}

	private String extractUserFromDomainUser(String domainUser) {
		String userNtId = domainUser;
		if (domainUser != null) {
			int domainDelimPos = domainUser.indexOf("/"); 
			if ( domainDelimPos > -1 && domainUser.length() > domainDelimPos ) {
				userNtId = domainUser.substring(domainUser.indexOf("/")+1);
			}
			return userNtId.toLowerCase();
		}
        return null;
	}

	private String extractDomainFromDomainUser(String domainUser) {
		String domain = "GSA-WLB";
		if (domainUser != null) {
			int domainDelimPos = domainUser.indexOf("/"); 
			if ( domainDelimPos > -1) {
				domain = domainUser.substring(0,domainUser.indexOf("/"));
			}
			return domain;
		}
        return null;
	}

	@Override
    public String[] getLastRequestedSourceArray() {
		return null;
	}
	@Override
    public String getLastRequestedString() {
		return null;
	}
	
	public void setWlbPassword(byte[] encodedPasswordBytes) throws EncoderException {
		if (Base64.isArrayByteBase64(encodedPasswordBytes)) {
			this.wlbPassword = new String(Base64.decodeBase64(encodedPasswordBytes));
		} else {
			throw new EncoderException("Password was not 64base encoded.");
		}
	}
	@Override
    public IntervalPriceImpl performBidAskRequest(
			String requestString, String currency, Calendar requestDate, String[] sources, double referencePrice)
			throws PricingRequestException {
		return performHighLowRequest(requestString, currency, requestDate, sources, referencePrice);
	}

}