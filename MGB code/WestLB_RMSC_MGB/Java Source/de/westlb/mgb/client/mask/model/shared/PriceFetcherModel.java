package de.westlb.mgb.client.mask.model.shared;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import de.westlb.mgb.bloomberg.model.BlpApiBloombergRequester;
import de.westlb.mgb.bloomberg.model.PricingRequestComunicationException;
import de.westlb.mgb.bloomberg.model.PricingRequestException;
import de.westlb.mgb.bloomberg.model.PricingRequestTimeOutException;
import de.westlb.mgb.bloomberg.model.PricingRequesterFactory;
import de.westlb.mgb.client.application.MgbProperty;
import de.westlb.mgb.client.mask.view.shared.ProgressDialog;
import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.VoFactory;
import de.westlb.mgb.client.server.vo.AutoCheckVo;
import de.westlb.mgb.client.server.vo.PriceVo;
import de.westlb.mgb.client.server.vo.RequestVo;
import de.westlb.mgb.client.server.vo.StateCodeVo;
import de.westlb.mgb.client.server.vo.StateRulesVo;
import de.westlb.mgb.client.server.vo.TradeHistoryEntryVo;
import de.westlb.mgb.client.task.MgbAutoCheckTask;
import de.westlb.mgb.model.definition.MarketDataRequestStateDef;
import de.westlb.mgb.model.definition.MarketDataRequestTypeDef;
import de.westlb.mgb.model.definition.MarketDataSourceDef;
import de.westlb.mgb.model.definition.MgbConfigurationDef;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb.mgb.model.impl.BloombergRequestImpl;
import de.westlb.mgb.model.impl.EuwaxRequestImpl;
import de.westlb.mgb.model.impl.PriceImpl;
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
public class PriceFetcherModel extends AbstractModel {

	static Logger logger = Logger.getLogger(PriceFetcherModel.class.getName());

	public static final String RESOURCE_BASE = "PRICE_FETCHER_";

	private int maxRetry = 0;

    private int maxResults = 0;

	private ProgressDialog progress = null;
	
	private boolean takeMgbServiceFactory = true;

	private Mgb getService() {
		if (takeMgbServiceFactory) {
			return MgbServiceFactory.getService();
		}
        return MgbAutoCheckTask.getService();
	}

	public PriceFetcherModel() {
		this(true);
	}

	/**
	 * DateiHandler constructor comment.
	 */
	public PriceFetcherModel(boolean takeMgbServiceFactory) {
		super();
		this.takeMgbServiceFactory = takeMgbServiceFactory;
        try {
        	String maxResultStr = getService().getMgbConfigurationValue(MgbConfigurationDef.MAX_AUTOCHECK_TRADES);
        	if (maxResultStr != null) {
        		maxResults = Integer.parseInt(maxResultStr);
        	}
        } catch (Exception e) {
        	logger.error("Unable to read "+MgbConfigurationDef.MAX_AUTOCHECK_TRADES+" from DB.", e);
        }

        maxRetry = Math.max(1, Integer.parseInt(PropertyFactory.getProperty(MgbProperty.MARKET_DATA_MAX_RETRY)));
		if ("true".equals(PropertyFactory.getProperty(MgbProperty.MARKET_DATA_GENERATE_DUMMY_VALUES))) {
			double decimalPercent = 0.02d;
			int minutes = 15;
			try {
				decimalPercent = Double.parseDouble(PropertyFactory.getProperty(MgbProperty.MARKET_DATA_DUMMY_VALUES_PRICE_PERCENTAGE_DEVIATION));
				if (decimalPercent >= 1.0) {
					decimalPercent = decimalPercent * 0.01;
				}
				minutes = Integer.parseInt(PropertyFactory.getProperty(MgbProperty.MARKET_DATA_DUMMY_VALUES_TIME_MINUTES_DEVIATION));
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			PricingRequesterFactory.setDummyRequester(decimalPercent, minutes);
		}
	}	
		
	public boolean fetchEuwaxPrices(String password) throws RemoteException {
		try {
			if (progress != null) {

				progress.setProgress(1l, 2l);
				return getService().fetchPrices(EuwaxRequestImpl.class.getName(), Base64.encodeBase64(password.getBytes()));
			}
		} catch (Exception e) {
			throw new RemoteException(e.getMessage(), e);
		}
		return false;
	}

    public boolean fetchBloombergPrices() throws RemoteException {
		try {
			if (progress != null) {

				RequestVo[] requests = getService().getUnsolvedRequests(BloombergRequestImpl.class.getName());
				logger.debug("Found "+requests.length+" requests to be processed");
				if (progress.isCanceled()) {
					return false;
				}
				if (!PricingRequesterFactory.isDummyRequester()) {
				    BlpApiBloombergRequester bloombergRequester = (BlpApiBloombergRequester)PricingRequesterFactory.getRequester(MarketDataSourceDef.BLOOMBERG);
				    bloombergRequester.setRequestMapping((Map<String,Double>)getService().getRequestPriceConversionFactorMap());
				}
				int retryCount = 0;
				while( retryCount < maxRetry && requests.length > 0) {
					if (requests.length < maxResults || maxResults <= 0) {
						retryCount++;
					}
					progress.setInfo(getResourceString(RESOURCE_BASE + "HEADER_01") + " (" + retryCount + ". attempt)");
					PriceVo[] prices = new PriceVo[requests.length];
					progress.setDetails(getResourceString(RESOURCE_BASE + "I_002"));
					progress.setProgress(1l, 2l);

					for (int i = 0; i < requests.length; i++) {
						try {
							logger.info(
								"Processing request "
									+ requests[i].getRequestId()
									+ ". ("
									+ requests[i].getRequestString()
									+ " at "
									+ requests[i].getPriceDate().getTime()
									+ ").");
							progress.setProgress(i, requests.length);
							PriceVo priceVo = null;
							if (requests[i].getRequestString() == null || requests[i].getPriceDate() == null) {
								throw new PricingRequestException(
									"Empty request string or date (" + requests[i].getRequestString() + " at " + requests[i].getPriceDate().getTime() + ").");
							}

							if (PricingRequesterFactory.isDummyRequester()) {
								logger.warn("### You are generating a dummy price. ###");
							}

							PriceImpl price = null;
							if (MarketDataRequestTypeDef.HIGH_LOW_REQUEST.equals(requests[i].getRequestType())) {
								price =	PricingRequesterFactory.getRequester(requests[i].getPriceSource()).performHighLowRequest(requests[i].getRequestString(), requests[i].getIsoCurrency(), requests[i].getPriceDate(), null, requests[i].getTradePrice());
							} else if (MarketDataRequestTypeDef.HISTORIC_REQUEST.equals(requests[i].getRequestType())) {
								price = PricingRequesterFactory.getRequester(requests[i].getPriceSource()).performHistoricRequest(requests[i].getRequestString(), requests[i].getIsoCurrency(), requests[i].getPriceDate(), null, requests[i].getTradePrice());
							} else if (MarketDataRequestTypeDef.COMBI_HISTORIC_REQUEST.equals(requests[i].getRequestType())) {
								price = PricingRequesterFactory.getRequester(requests[i].getPriceSource()).performHistoricRequest(requests[i].getRequestString(), requests[i].getIsoCurrency(), requests[i].getPriceDate(), null, requests[i].getTradePrice());
								if (price == null) {
									price = PricingRequesterFactory.getRequester(requests[i].getPriceSource()).performRequest(requests[i].getRequestString(), requests[i].getIsoCurrency(), requests[i].getPriceDate(), requests[i].getTradePrice());
								}
							} else if (MarketDataRequestTypeDef.COMBI_HIGH_LOW_REQUEST.equals(requests[i].getRequestType())) {
								price =	PricingRequesterFactory.getRequester(requests[i].getPriceSource()).performHighLowRequest(requests[i].getRequestString(), requests[i].getIsoCurrency(), requests[i].getPriceDate(), null, requests[i].getTradePrice());
								if (price == null) {
									price = PricingRequesterFactory.getRequester(requests[i].getPriceSource()).performRequest(requests[i].getRequestString(), requests[i].getIsoCurrency(), requests[i].getPriceDate(), requests[i].getTradePrice());
								}
							} else if (MarketDataRequestTypeDef.COMBI_SRC_REQUEST.equals(requests[i].getRequestType())) {
								// first ask the high low to check if a price for any source is available
								// then check for a historic price
								
								price = PricingRequesterFactory.getRequester(requests[i].getPriceSource()).performHistoricRequest(requests[i].getRequestString(), requests[i].getIsoCurrency(), requests[i].getPriceDate(), requests[i].getRequestSources(), requests[i].getTradePrice());
								if (price == null) {
									price = PricingRequesterFactory.getRequester(requests[i].getPriceSource()).performHighLowRequest(requests[i].getRequestString(), requests[i].getIsoCurrency(), requests[i].getPriceDate(), requests[i].getRequestSources(), requests[i].getTradePrice());
								}
								requests[i].setRequestSources(PricingRequesterFactory.getRequester(requests[i].getPriceSource()).getLastRequestedSourceArray());
								requests[i].setRequestString(PricingRequesterFactory.getRequester(requests[i].getPriceSource()).getLastRequestedString());
							} else if (MarketDataRequestTypeDef.BID_ASK_SRC_REQUEST.equals(requests[i].getRequestType())) {
								price = PricingRequesterFactory.getRequester(requests[i].getPriceSource()).performBidAskRequest(requests[i].getRequestString(), requests[i].getIsoCurrency(), requests[i].getPriceDate(), requests[i].getRequestSources(), requests[i].getTradePrice());
								if (price == null) {
									price = PricingRequesterFactory.getRequester(requests[i].getPriceSource()).performHighLowRequest(requests[i].getRequestString(), requests[i].getIsoCurrency(), requests[i].getPriceDate(), requests[i].getRequestSources(), requests[i].getTradePrice());
								}
								requests[i].setRequestSources(PricingRequesterFactory.getRequester(requests[i].getPriceSource()).getLastRequestedSourceArray());
								requests[i].setRequestString(PricingRequesterFactory.getRequester(requests[i].getPriceSource()).getLastRequestedString());
							} else if (MarketDataRequestTypeDef.HIGH_LOW_SRC_REQUEST.equals(requests[i].getRequestType())) {
								price = PricingRequesterFactory.getRequester(requests[i].getPriceSource()).performHighLowRequest(requests[i].getRequestString(), requests[i].getIsoCurrency(), requests[i].getPriceDate(), requests[i].getRequestSources(), requests[i].getTradePrice());
								requests[i].setRequestSources(PricingRequesterFactory.getRequester(requests[i].getPriceSource()).getLastRequestedSourceArray());
								requests[i].setRequestString(PricingRequesterFactory.getRequester(requests[i].getPriceSource()).getLastRequestedString());
							} else if (MarketDataRequestTypeDef.HISTORIC_SRC_REQUEST.equals(requests[i].getRequestType())) {
								price = PricingRequesterFactory.getRequester(requests[i].getPriceSource()).performHistoricRequest(requests[i].getRequestString(), requests[i].getIsoCurrency(), requests[i].getPriceDate(), requests[i].getRequestSources(), requests[i].getTradePrice());
								requests[i].setRequestSources(PricingRequesterFactory.getRequester(requests[i].getPriceSource()).getLastRequestedSourceArray());
								requests[i].setRequestString(PricingRequesterFactory.getRequester(requests[i].getPriceSource()).getLastRequestedString());
							} else {
								throw new RemoteException("Unknown request type "+requests[i].getRequestType());
								//price = PricingRequesterFactory.getRequester(requests[i].getPriceSource()).performRequest(requests[i].getRequestString(), requests[i].getIsoCurrency(), requests[i].getPriceDate(), requests[i].getTradePrice());
							}
							priceVo = buildPriceVo(requests[i], price);

							getService().savePrice(requests[i], priceVo);

							requests[i].setRequestState(MarketDataRequestStateDef.OK_PRICE_UNVALIDATED);
							prices[i] = priceVo;

							requests[i].setRequestDate(new GregorianCalendar());
						
						} catch (PricingRequestException pre) {
							if (pre instanceof PricingRequestTimeOutException) {
								logger.error(MarketDataRequestStateDef.TIMEOUT + ": " + pre.getMessage(), pre);
								requests[i].setRequestState(MarketDataRequestStateDef.TIMEOUT + ": " + pre.getMessage());
							} else if (pre instanceof PricingRequestComunicationException){
								logger.error(MarketDataRequestStateDef.COMERROR + ": " + pre.getMessage(), pre);
								requests[i].setRequestState(MarketDataRequestStateDef.COMERROR + ": " + pre.getMessage());
							} else {
								logger.error(MarketDataRequestStateDef.ERROR + ": " + pre.getMessage());
								prices[i] = new PriceVo();
								prices[i].setRequestId(requests[i].getRequestId());
								requests[i].setRequestState(MarketDataRequestStateDef.ERROR + ": " + pre.getMessage());
							}
						}

						getService().updateRequest(requests[i]);

						if (progress.isCanceled()) {
							break;
						}
					}

					progress.setDetails(getResourceString(RESOURCE_BASE + "I_012"));
					progress.setProgress(1l, 2l);
					getService().runAndSaveAutomaticCheck(prices);

					progress.setProgress(1l, 1l);

					if (progress.isCanceled()) {
						return false;
					}

					progress.setDetails(getResourceString(RESOURCE_BASE + "I_001"));
					requests = getService().getUnsolvedRequests(BloombergRequestImpl.class.getName());
	                logger.debug("Found "+requests.length+" requests to be processed");
				}
			}
			return true;
		} catch (Exception e) {
			throw new RemoteException(e.getMessage(), e);
		} finally {
			PricingRequesterFactory.destroy();
		}
	}

	public boolean checkInconsistantRequests(String requestClassName) throws RemoteException {
		try {
			progress.setInfo(getResourceString(RESOURCE_BASE + "HEADER_01"));
			progress.setDetails(getResourceString(RESOURCE_BASE + "I_001"));

			RequestVo[] requests = getService().getUnvalidatedRequests(requestClassName);
			PriceVo[] prices = new PriceVo[requests.length];

			progress.setDetails(getResourceString(RESOURCE_BASE + "I_006"));
			for (int i = 0; i < requests.length; i++) {
			    prices[i] = getService().getPrice(requests[i]);
			    if (prices[i] == null) {
			        prices[i] = new PriceVo();
			        prices[i].setRequestId(requests[i].getRequestId());
			    }
				progress.setProgress(i, requests.length);
				if (progress.isCanceled()) {
					return false;
				}
			}
			progress.setDetails(getResourceString(RESOURCE_BASE + "I_012"));
			progress.setProgress(1l, 2l);
			getService().runAndSaveAutomaticCheck(prices);
			progress.setProgress(1l, 1l);

			return true;
		} catch (Exception e) {
			throw new RemoteException(e.getMessage(), e);
		}
	}

	public static PriceVo buildPriceVo(RequestVo request, PriceImpl price) throws PricingRequestException {
		if (price == null) {
			throw new PricingRequestException(
				"No price returned for request string " + request.getRequestString() + " (id=" + request.getRequestId() + ").");
		}
		logger.info("Received price for request string " + request.getRequestString() + " (id=" + request.getRequestId() + "): " + price.toString() + ".");
		return VoFactory.createPriceVo(price, request.getRequestId());
	}

	
	/**
	 * @deprecated no local check
	 */
	public boolean autoCheck() throws RemoteException {
		try {
			if (progress != null) {
				StateRulesVo[] stateRules = getService().getStateRules();

				AutoCheckVo[] autoChecks = getService().getAutoCheckTrades();
				logger.debug("Fetched " + autoChecks.length + " trades to be checked.");
				progress.setDetails(getResourceString(RESOURCE_BASE + "I_012"));
				for (int i = 0; i < autoChecks.length; i++) {
					logger.debug("Processing trade " + autoChecks[i].getTradeId() + ".");
					progress.setProgress(i, autoChecks.length);

					StateCodeVo finalState = null;
					String comment = "";
					for (int j = 0; j < stateRules.length && finalState == null; j++) {
						if (stateRules[j].getSourceSystemCode() != null
							&& stateRules[j].getSourceSystemCode().equalsIgnoreCase(autoChecks[i].getSourceSystemCode())) {
							logger.debug("Checking rule " + stateRules[j].getConditionName() + " for trade " + autoChecks[i].getTradeId() + ".");
							boolean result = invokeMethod(stateRules[j], autoChecks[i]);
							comment = comment + stateRules[j].getConditionName() + "=" + result + " ";
							if (result || stateRules[j].getConditionEvaluator() == null || stateRules[j].getConditionEvaluator().trim().length() == 0) {
								finalState = stateRules[j].getState();
								TradeHistoryEntryVo state = new TradeHistoryEntryVo();
								state.setDate(new GregorianCalendar());
								state.setInternalTradeId(autoChecks[i].getTradeId());
								state.setStateCode(finalState.getStateCode());
								state.setType(StateCodeTypeDef.AUTO);
								state.setComment(comment);
								logger.debug("Saving state " + finalState.getStateCode() + " for trade " + autoChecks[i].getTradeId() + ".");
								getService().saveTradeState(state, null);
							}
						}
					}
					if (progress.isCanceled()) {
						return false;
					}
				}
			}
			return true;
		} catch (NoSuchMethodException e) {
            throw new RemoteException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RemoteException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new RemoteException(e.getMessage(), e);
        }
	}

	/**
	 * @deprecated no local check
	 */
	private boolean invokeMethod(StateRulesVo stateRule, AutoCheckVo autoCheck)
		throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (stateRule != null && stateRule.getConditionEvaluator() != null) {
			Method method = this.getClass().getMethod(stateRule.getConditionEvaluator(), new Class[] { AutoCheckVo.class });
			return ((Boolean) method.invoke(this, new Object[] { autoCheck })).booleanValue();
		}
        return false;
	}

	/**
	 * @deprecated no local check
	 * @return true if at least one PriceVo is null
	 */
	public boolean hasNoPrice(AutoCheckVo autoCheck) {
		RequestVo[] requests = autoCheck.getRequests();
		for (int i = 0; i < requests.length; i++) {
			if (autoCheck.getPrices() == null || autoCheck.getPrices()[i] == null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @deprecated no local check
	 * @return false if the first price inside the range is found
	 */
	public boolean isOutOfTimeRange(AutoCheckVo autoCheck) {
		RequestVo[] requests = autoCheck.getRequests();
		for (int i = 0; i < requests.length; i++) {
			if (autoCheck.getPrices() != null
				&& autoCheck.getPrices()[i] != null
				&& autoCheck.getPrices()[i].getPriceDate() != null
				&& requests[i] != null
				&& requests[i].getRequestDate() != null) {
				long millisDiff = 0l;
				if (MarketDataRequestTypeDef.HIGH_LOW_REQUEST.equals(requests[i].getRequestType())) {
					// important is the same day
					millisDiff = DateTimeUtils.convertDaysToMilliseconds(
							Math.abs(autoCheck.getPrices()[i].getPriceDate().get(Calendar.DAY_OF_YEAR) - requests[i].getPriceDate().get(Calendar.DAY_OF_YEAR)));
					logger.debug(
						"blbdate day: "
							+ autoCheck.getPrices()[i].getPriceDate().get(Calendar.DAY_OF_YEAR)
							+ "; tradedate day: "
							+ requests[i].getPriceDate().get(Calendar.DAY_OF_YEAR));
				} else {
					millisDiff = Math.abs(autoCheck.getPrices()[i].getPriceDate().getTimeInMillis() - requests[i].getPriceDate().getTimeInMillis());
					logger.debug("blbdate  =" + autoCheck.getPrices()[i].getPriceDate().getTime().toString());
					logger.debug("tradedate=" + requests[i].getPriceDate().getTime().toString());
				}
				long millisTolerance = 60000L * autoCheck.getTimeToleranceMinutes()[i].intValue();
				logger.debug("millisDiff: " + millisDiff + "; millisTolerance: " + millisTolerance + ")");
				if (millisDiff == 0 || millisDiff < millisTolerance) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	  * Setzen des DateiHandlerDialogs
	  * <p>
	  * Wird benoetigt fuer ProgressBar
	  *
	  * @param newDialog der DateiHandlerDialog des Models
	  */
	public void setProgress(ProgressDialog newProgress) {
		progress = newProgress;
	}

}
