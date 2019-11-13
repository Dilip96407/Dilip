package de.westlb.mgb.model.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.server.VoFactory;
import de.westlb.mgb.client.server.vo.PriceVo;
import de.westlb.mgb.converter.MgbConverter;
import de.westlb.mgb.converter.SummitBondConverter;
import de.westlb.mgb.converter.SummitDerivativeConverter;
import de.westlb.mgb.converter.SummitRepoConverter;
import de.westlb.mgb.model.definition.MarketDataRequestStateDef;
import de.westlb.mgb.model.definition.MarketDataRequestTypeDef;
import de.westlb.mgb.model.definition.MgbConfigurationDef;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.util.DateTimeUtils;

/**
 * @author D055625
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
/**
 * 
 * @modelguid {56C7AFFC-C2A7-4B1C-8A73-F9B133DAF6B2}
 */
public abstract class TradeImpl extends EntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7374007140119548925L;

    protected final static double INTEREST_DAYS_PER_YEAR = 360.0d;

    protected final static double AS_PER_CENT = 0.01d;

	static Logger logger = Logger.getLogger(TradeImpl.class);

	private String sourceSystemBloombergId;

	private String sourceSystemReutersId;

	private BookImpl book;

	private String bookId;

	private String tradeId;

	private String tradeGroupId;

	private boolean isTradeGroup;

	private boolean isLateDeal;

	private String orderId;
	
	private CounterpartyImpl counterparty;

	private String counterpartyId;

	/** Dates of the trade 
	 */
	private Calendar amendedDate;

	private Calendar settlementDate;

	private Calendar systemDate;

    // Field derived by systemDate containg the system time in 
    // format 'HH24MI' for query purpose only 
    private String systemTimeString;

	private Calendar tradeDate;

	private String currency;

	private PriceCheckInstrumentImpl instrument;

	private String sourceSystemInstrument;

	private JobImpl job;

	private Set<RequestImpl> requests;

	private Set<RequestImpl> externalRequests;

	private Set<ReutersRequestImpl> reutersRequests;

	private Set<EuwaxRequestImpl> euwaxRequests;

	private Set<BloombergRequestImpl> bloombergRequests;

	private SourceSystemImpl sourceSystem;

	private String traderId;

	private String traderName;

	private TraderImpl trader;

	private double volume;

	private double price;

	private Set<TradeHistEntryImpl> historyEntries;

	private AutoStateHistEntryImpl currentAutoStateHistEntry;

	private ManualStateHistEntryImpl currentManualStateHistEntry;

	private ReclStateHistEntryImpl currentReclStateHistEntry;

	private HashMap<Long, PriceVo> autoCheckPrices;

	private HashMap<String, Object> autoCheckConfig;
	
	@Override
    public Long getLongId() {
		return super.getLongId();
	}

	protected Calendar lonToDusCalendar(Calendar lonCal) {
		if (lonCal != null) {
		// 	changing LON to DUS time
			Calendar dusCal = (Calendar)lonCal.clone();
			dusCal.add(Calendar.HOUR_OF_DAY,1);
			if (lonCal.getTimeZone().inDaylightTime(lonCal.getTime())) {
				dusCal.add(Calendar.HOUR_OF_DAY,1);				
			}
			return dusCal;
		}
        return lonCal;
	}

    public abstract double getFrontOfficeMarketRate();

    public abstract String getTradeCategory();

	public double calculateTurnover(double marketPrice) {
		return (marketPrice - getPrice())* getVolume();
	}

	public double calculatePriceDifference(double minPrice, double maxPrice, boolean isIntervalPrice) { 
		if (isIntervalPrice) {
		    if (minPrice == 0.0) {
		        return getPrice() - maxPrice;
		    } else if (maxPrice == 0.0) {
                return getPrice() - minPrice;
		    } else {
		        return Math.max(0.0,Math.max(getPrice() - maxPrice, minPrice - getPrice()));
		    }
		}
        return getPrice() - minPrice;
	}

	/**
	 * @param autoCheckPrices
	 *            The autoCheckPrices to set.
	 */
	public void addAutoCheckPrices(PriceVo autoCheckPrice) {
		if (this.autoCheckPrices == null) {
			this.autoCheckPrices = new HashMap<Long, PriceVo>();
		}
		this.autoCheckPrices.put(autoCheckPrice.getRequestId(), autoCheckPrice);
	}

	public PriceVo getAutoCheckPrice(Long requestId) {
		if (this.autoCheckPrices != null && requestId != null) {
		    return this.autoCheckPrices.get(requestId); 
		}
		return null;
	}

	/**
	 * @return Returns the autoCheckConfig.
	 */
	public Object getAutoCheckConfig(String autoCheckConfigKey) {
		if (this.autoCheckConfig != null) { 
		    return this.autoCheckConfig.get(autoCheckConfigKey); 
		}
		return null;
	}

	/**
	 * @param autoCheckConfig
	 *            The autoCheckConfig to set.
	 */
	public void addAutoCheckConfig(Object autoCheckConfigObject, String autoCheckConfigKey) {
		if (this.autoCheckConfig == null) {
			this.autoCheckConfig = new HashMap<String, Object>();
		}
		this.autoCheckConfig.put(autoCheckConfigKey, autoCheckConfigObject);
	}

	/**
	 * @param autoCheckConfig
	 *            The autoCheckConfig to set.
	 */
	public void setAutoCheckConfig(HashMap<String, Object> autoCheckConfig) {
		this.autoCheckConfig = autoCheckConfig;
	}

	/**  
	 * @return the value of the flag isManualCheckRequired of the currentAutoState. null if no currentAutoState is available.
	 */
	public Boolean isManualCheckRequired() throws PersistenceException {
		if (getCurrentAutoState() != null) {
			return Boolean.valueOf(getCurrentAutoState().isManualCheckRequired());
		}
        return Boolean.TRUE;
	}

	/**  
	 * @return the value of the flag isReclamationRequired of the currentManualState if the currentReclamationState is not null. Else null.
	 */
	public Boolean isReclamationRequiredOpen() throws PersistenceException {
		if (getCurrentManualState() != null) {
			return Boolean.valueOf(getCurrentManualState().isReclamationRequired() && getCurrentReclamationState() == null);
		}
        return Boolean.TRUE;
	}

	/**  
	 * @return the value of the flag isClosed of the currentReclStateHistEntry. null if no currentReclStateHistEntry is available.
	 */
	public Boolean isReclamationClosed() throws PersistenceException {
		if (getCurrentReclamationState() != null) {
			return Boolean.valueOf(getCurrentReclStateHistEntry().isClosed());
		}
        return Boolean.FALSE;
	}

	/**  
	 * @return true if the tradeState is not final and no manual check is required. null if no currentAutoState is available.
	 */
	public Boolean isOpenAutomaticCheck() throws PersistenceException {
		if (getCurrentAutoState() != null) {
			return Boolean.valueOf(!getCurrentAutoState().getFinalState() && !getCurrentAutoState().isManualCheckRequired());
		}
        return Boolean.TRUE;
	}

	public Boolean isManualCheckRequiredNotHandled() throws PersistenceException {
		if (getCurrentAutoState() != null) {
			return Boolean.valueOf(getCurrentAutoState().isManualCheckRequired() && getCurrentManualState() == null);
		}
        return Boolean.TRUE;
	}

	@SuppressWarnings("unused")
    private boolean addCurrentState(TradeHistEntryImpl state) {
		state.setStateTime(new GregorianCalendar());
		state.setTrade(this);
		if (historyEntries != null && historyEntries.size() > 0) {
			return historyEntries.add(state);
		}
        TreeSet<TradeHistEntryImpl> set = new TreeSet<TradeHistEntryImpl>();
        boolean result = set.add(state);
        setHistoryEntries(set);
        return result;
	}

	public boolean hasNoPrice() {
		return autoCheckPrices == null || autoCheckPrices.isEmpty();
	}

	/**
	 * @return false if the first price inside the range is found
	 */
	public boolean isOutOfTimeRange() {
        Iterator<RequestImpl> requests = getRequests().iterator();
		while (requests.hasNext()) {
			RequestImpl request = requests.next();
			PriceVo autoCheckPrice = getAutoCheckPrice(request.getLongId());
			if (autoCheckPrices != null && autoCheckPrice != null && autoCheckPrice.getPriceDate() != null) {
				long millisDiff = 0l;
				if (MarketDataRequestTypeDef.HIGH_LOW_REQUEST.equals(request.getRequestType()) || MarketDataRequestTypeDef.COMBI_HIGH_LOW_REQUEST.equals(request.getRequestType())) {
				// important is the same day
					millisDiff = DateTimeUtils.convertDaysToMilliseconds(
							Math.abs(autoCheckPrice.getPriceDate().get(Calendar.DAY_OF_YEAR)
									- getTradeDate().get(Calendar.DAY_OF_YEAR)));
					logger.debug("blbdate day: " + autoCheckPrice.getPriceDate().get(Calendar.DAY_OF_YEAR) + "(" + autoCheckPrice.getPriceDate().getTime().toString()
					        + "); tradedate day: " + getTradeDate().get(Calendar.DAY_OF_YEAR) + "(" + getTradeDate().getTime().toString() + ")");
					// this a temporary solution, due to the strange behavior in production
					return false;
				} else if (MarketDataRequestTypeDef.COMBI_HISTORIC_REQUEST.equals(request.getRequestType()) && autoCheckPrice.getPriceDate().get(Calendar.HOUR_OF_DAY) == 0) {
					int dayDiff = Math.abs(autoCheckPrice.getPriceDate().get(Calendar.DAY_OF_YEAR) - getTradeDate().get(Calendar.DAY_OF_YEAR));
					int i = 1;
					while (getTradeDate().get(Calendar.DAY_OF_WEEK)-i == Calendar.SATURDAY || getTradeDate().get(Calendar.DAY_OF_WEEK)-i == Calendar.SUNDAY) {
						// substract weekend
						dayDiff = dayDiff -1;
					}
					// accept yesterdays price
					dayDiff = dayDiff -1;
					millisDiff = 24 * 60 * 60 * 1000;
					logger.debug("blbdate day: " + autoCheckPrice.getPriceDate().get(Calendar.DAY_OF_YEAR) + "(" + autoCheckPrice.getPriceDate().getTime().toString()
					        + "); tradedate day: " + getTradeDate().get(Calendar.DAY_OF_YEAR) + "(" + getTradeDate().getTime().toString() + ")");
				} else {
					millisDiff = Math.abs(autoCheckPrice.getPriceDate().getTimeInMillis()
							- getTradeDate().getTimeInMillis());
					logger.debug("blbdate  =" + autoCheckPrice.getPriceDate().getTime().toString());
					logger.debug("tradedate=" + getTradeDate().getTime().toString());
				}
				long addonMinutes = 0L;
				if (request.getAddon() != null) {
				    addonMinutes = request.getAddon().getTimeToleranceMinutes();
				}
				long toleranceMinutes = 0L;
				if (request.getPriceCheckCategory() != null) {
				    toleranceMinutes = request.getPriceCheckCategory().getTimeToleranceMinutes();
				}
				long millisTolerance = 60000L * (addonMinutes + toleranceMinutes); 
				logger.debug("millisDiff: " + millisDiff + "; millisTolerance: "+ millisTolerance);
				if (millisDiff == 0 || millisDiff < millisTolerance) { 
					return false;
				}
			} else {
				logger.debug("No price date for autoCheckPrice");		    
			}
		}
		return true;
	}

	/**
	 * @return false if the first price inside the range is found
	 */
	public boolean isOutOfPriceRange() {
		double bloombergPriceToleranceDeviationFactor = 1.0;
		if (getAutoCheckConfig(MgbConfigurationDef.BLOOMBERG_INTERVAL_PRICE_DEVIATION_TOLERANCE_FACTOR) != null
				&& getAutoCheckConfig(MgbConfigurationDef.BLOOMBERG_INTERVAL_PRICE_DEVIATION_TOLERANCE_FACTOR) instanceof Double) {
			bloombergPriceToleranceDeviationFactor = ((Double) getAutoCheckConfig(MgbConfigurationDef.BLOOMBERG_INTERVAL_PRICE_DEVIATION_TOLERANCE_FACTOR))
					.doubleValue();
		}
//        Iterator requests = getExternalRequests().iterator();
        Iterator<RequestImpl> requests = getRequests().iterator();
		while (requests.hasNext()) {
			RequestImpl request = requests.next();
			if (request != null) {
    			PriceVo autoCheckPrice = getAutoCheckPrice(request.getLongId());
    			if (autoCheckPrice == null && request.getPriceResult() != null) {
    			    autoCheckPrice = VoFactory.createPriceVo(request.getPriceResult(),request.getLongId()); 
    			}
    			if (autoCheckPrice != null && request.getPriceCheckCategory() != null) {
    	             double addonTolerancePercent = 0;
    	             if (request.getAddon() != null) {
    	                 addonTolerancePercent = request.getAddon().getPriceTolerancePercent();
    	             }
    				if (isTradePriceInRange(getPrice(), autoCheckPrice.getMinPrice(), autoCheckPrice.getMaxPrice(),
    						bloombergPriceToleranceDeviationFactor
    								* (addonTolerancePercent + request.getPriceCheckCategory().getPriceTolerancePercent()),
    						bloombergPriceToleranceDeviationFactor
    								* request.getPriceCheckCategory().getPriceToleranceAbsolute())) { 
    				    return false; 
    				}
    			}
			}
		}
		return true;
	}

	protected boolean isRequestTimeOut(String requestType) {
		Iterator<RequestImpl> requests = getExternalRequests().iterator();
		while (requests.hasNext()) {
			RequestImpl request = requests.next();
			if (request.getRequestState() != null && request.getRequestState().startsWith(MarketDataRequestStateDef.TIMEOUT)
					&& requestType.equals(request.getRequestType())) { return true; }
		}
		return false;
	}

	public boolean isHistoricBloombergTimeOut() {
		return isRequestTimeOut(MarketDataRequestTypeDef.HISTORIC_REQUEST);
	}

	public boolean isHighlowBloombergTimeOut() {
		return isRequestTimeOut(MarketDataRequestTypeDef.HIGH_LOW_REQUEST);
	}

	public boolean isHistoricSourceBloombergTimeOut() {
	    return isRequestTimeOut(MarketDataRequestTypeDef.HISTORIC_SRC_REQUEST);
	}

	public static boolean isTradePriceInRange(double tradePrice, double minRangePrice, double maxRangePrice,
			double percentageDeviation, double absoluteDeviation) {
	    if (minRangePrice == 0.0) {
	        minRangePrice = maxRangePrice;
	    }
	    if (maxRangePrice == 0.0) {
	        maxRangePrice = minRangePrice;
	    }

		double minTheshold;
		double maxTheshold;
		if (absoluteDeviation != 0) {
			minTheshold = minRangePrice - absoluteDeviation;
			maxTheshold = maxRangePrice + absoluteDeviation;
			logger.debug("price: " + tradePrice + "; minTheshold: " + minTheshold + "; maxTheshold: " + maxTheshold + " (absolute)");			
		} else {
			minTheshold = minRangePrice * (1 - percentageDeviation / 100.0);
			maxTheshold = maxRangePrice * (1 + percentageDeviation / 100.0);
			logger.debug("price: " + tradePrice + "; minTheshold: " + minTheshold + "; maxTheshold: " + maxTheshold+ " (relative)");
		}
		return minTheshold < tradePrice && tradePrice < maxTheshold;
	}

	public boolean isOutOfPriceAndTimeRange() {
		return isOutOfPriceRange() && isOutOfTimeRange();
	}

	public String getLocationTrader() {
	    return getBook() == null ? null : getBook().getLocationTrader(); 
	}
	
	/**
	 * Returns the amendedDate.
	 * @return Date
	 */
	public Calendar getAmendedDate() {
		return amendedDate;
	}

	/**
	 * Returns the bookId.
	 * @return char
	 */
	public String getBookId() {
		return bookId;
	}

	/**
	 * Returns the counterpartyId.
	 * @return String
	 */
	public String getCounterpartyId() {
		return counterpartyId;
	}

	/**
	 * Returns the currency.
	 * @return String
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Returns the instrument.
	 * @return InstrumentImpl
	 */
	public PriceCheckInstrumentImpl getInstrument() {
		return instrument;
	}

	/**
	 * Returns the sourceSystemInstrument.
	 * @return String
	 */
	public String getSourceSystemInstrument() {
		return sourceSystemInstrument;
	}

	/**
	 * Returns the job.
	 * @return JobImpl
	 */
	public JobImpl getJob() {
		return job;
	}

	/**
	 * Returns the orderId.
	 * @return String
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * Returns the price.
	 * @return double
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Returns the settlementDate.
	 * @return Calendar
	 */
	public Calendar getSettlementDate() {
		return settlementDate;
	}

	/**
	 * Returns the sourceSystemBloombergId.
	 * @return String
	 */
	public String getSourceSystemBloombergId() {
		return sourceSystemBloombergId;
	}

	/**
	 * Returns the sourceSystemReutersId.
	 * @return String
	 */
	public String getSourceSystemReutersId() {
		return sourceSystemReutersId;
	}

	/**
	 * Returns the systemDate.
	 * @return Calendar
	 */
	public Calendar getSystemDate() {
		return systemDate;
	}

	/**
	 * Returns the traderId.
	 * @return String
	 */
	public String getTraderId() {
		return traderId;
	}

	/**
	 * Returns the volume.
	 * @return double
	 */
	public double getVolume() {
		return volume;
	}

	/**
	 * Sets the amendedDate.
	 * @param amendedDate The amendedDate to set
	 */
	public void setAmendedDate(Calendar amendedDate) {
		this.amendedDate = amendedDate;
	}

	/**
	 * Sets the bookId.
	 * @param bookId The bookId to set
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	/**
	 * Sets the counterpartyId.
	 * @param counterpartyId The counterpartyId to set
	 */
	public void setCounterpartyId(String counterpartyId) {
		this.counterpartyId = counterpartyId;
	}

	/**
	 * Sets the currency.
	 * @param currency The currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * Sets the instrument.
	 * @param instrument The instrument to set
	 */
	public void setInstrument(PriceCheckInstrumentImpl instrument) {
		this.instrument = instrument;
	}

	/**
	 * Sets the sourceSystemInstrument.
	 * @param sourceSystemInstrument The sourceSystemInstrument to set
	 */
	public void setSourceSystemInstrument(String sourceSystemInstrument) {
		this.sourceSystemInstrument = sourceSystemInstrument;
	}

	/**
	 * Sets the job.
	 * @param job The job to set
	 */
	public void setJob(JobImpl job) {
		this.job = job;
	}

	/**
	 * Sets the orderId.
	 * @param orderId The orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * Sets the price.
	 * @param price The price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Sets the settlementDate.
	 * @param settlementDate The settlementDate to set
	 */
	public void setSettlementDate(Calendar settlementDate) {
		this.settlementDate = settlementDate;
	}

	/**
	 * Sets the sourceSystemBloombergId.
	 * @param sourceSystemBloombergId The sourceSystemBloombergId to set
	 */
	public void setSourceSystemBloombergId(String sourceSystemBloombergId) {
		this.sourceSystemBloombergId = sourceSystemBloombergId;
	}

	/**
	 * Sets the sourceSystemReutersId.
	 * @param sourceSystemReutersId The sourceSystemReutersId to set
	 */
	public void setSourceSystemReutersId(String sourceSystemReutersId) {
		this.sourceSystemReutersId = sourceSystemReutersId;
	}

	/**
	 * Sets the systemDate.
	 * @param systemDate The systemDate to set
	 */
	public void setSystemDate(Calendar systemDate) {
		this.systemDate = systemDate;
	}

	/**
	 * Sets the traderId.
	 * @param traderId The traderId to set
	 */
	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}

	/**
	 * Sets the volume.
	 * @param volume The volume to set
	 */
	public void setVolume(double volume) {
		this.volume = volume;
	}

	/**
	 * Returns the requests.
	 * @return Collection
	 */
	public Collection<RequestImpl> getRequests() {
		return requests;
	}

	/**
	 * Returns the sourceSystem.
	 * @return SourceSystemImpl
	 */
	public SourceSystemImpl getSourceSystem() {
		return sourceSystem;
	}

	/**
	 * Sets the sourceSystem.
	 * @param sourceSystem The sourceSystem to set
	 */
	public void setSourceSystem(SourceSystemImpl sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	/**
	 * Returns the tradeDate.
	 * @return Date
	 */
	public Calendar getTradeDate() {
		return tradeDate;
	}

	/**
	 * Returns the tradeId.
	 * @return String
	 */
	public String getTradeId() {
		return tradeId;
	}

	/**
	 * Sets the tradeId.
	 * @param tradeId The tradeId to set
	 */
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	/**
	 * Sets the requests.
	 * @param requests The requests to set
	 */
	@SuppressWarnings("unused")
    private void setRequests(Set<RequestImpl> requests) {
		this.requests = requests;
	}

	public boolean addRequest(RequestImpl request) {
		if (request != null) {
	        request.setTrade(this);
	        if (requests == null) {
	            requests = new TreeSet<RequestImpl>();
	        }
	        return requests.add(request);
	    }
	    return false;
	}

	@SuppressWarnings("unused")
    private boolean removeRequest(RequestImpl request) {
		if (requests != null && requests.size() > 0) {
			return requests.remove(request);
		}
        return false;
	}

	/**
	 * Sets the tradeDate.
	 * @param tradeDate The tradeDate to set
	 */
	public void setTradeDate(Calendar tradeDate) {
		this.tradeDate = tradeDate;
	}

	/**
	 * Returns the historyEntries.
	 * @return TradeHistEntryImpl
	 */
	public Collection<TradeHistEntryImpl> getHistoryEntries() {
		return historyEntries;
	}

	/**
	 * Sets the historyEntries.
	 * @param historyEntries The historyEntries to set
	 */
	public void setHistoryEntries(Set<TradeHistEntryImpl> historyEntries) {
		this.historyEntries = historyEntries;
	}

	/**
	 * Returns the bloombergRequests.
	 * @return Collection
	 */
	public Collection<BloombergRequestImpl> getBloombergRequests() {
		return bloombergRequests;
	}

	/**
	 * Returns the reutersRequests.
	 * @return Collection
	 */
	public Collection<ReutersRequestImpl> getReutersRequests() {
		return reutersRequests;
	}

	/**
	 * Sets the bloombergRequests.
	 * @param bloombergRequests The bloombergRequests to set
	 */
	public void setBloombergRequests(Set<BloombergRequestImpl> bloombergRequests) {
		this.bloombergRequests = bloombergRequests;
	}

	/**
	 * Sets the reutersRequests.
	 * @param reutersRequests The reutersRequests to set
	 */
	public void setReutersRequests(Set<ReutersRequestImpl> reutersRequests) {
		this.reutersRequests = reutersRequests;
	}

	/**
	 * Returns the trader.
	 * @return TraderImpl
	 */
	public TraderImpl getTrader() {
		return trader;
	}

	/**
	 * Sets the trader.
	 * @param trader The trader to set
	 */
	public void setTrader(TraderImpl trader) {
		this.trader = trader;
	}

	/**
	 * Returns the currentAutoStateHistEntry.
	 * @return AutoStateHistEntryImpl
	 */
	public AutoStateHistEntryImpl getCurrentAutoStateHistEntry() {
		return currentAutoStateHistEntry;
	}

	/**
	 * Returns the currentManualStateHistEntry.
	 * @return ManualStateHistEntryImpl
	 */
	public ManualStateHistEntryImpl getCurrentManualStateHistEntry() {
		return currentManualStateHistEntry;
	}

	/**
	 * Returns the currentReclStateHistEntry.
	 * @return ReclStateHistEntryImpl
	 */
	public ReclStateHistEntryImpl getCurrentReclStateHistEntry() {
		return currentReclStateHistEntry;
	}

	/**
	 * Sets the currentAutoStateHistEntry.
	 * @param currentAutoStateHistEntry The currentAutoStateHistEntry to set
	 */
	public void setCurrentAutoStateHistEntry(AutoStateHistEntryImpl currentAutoStateHistEntry) {
		this.currentAutoStateHistEntry = currentAutoStateHistEntry;
	}

	/**
	 * Sets the currentManualStateHistEntry.
	 * @param currentManualStateHistEntry The currentManualStateHistEntry to set
	 */
	public void setCurrentManualStateHistEntry(ManualStateHistEntryImpl currentManualStateHistEntry) {
		this.currentManualStateHistEntry = currentManualStateHistEntry;
	}

	/**
	 * Sets the currentReclStateHistEntry.
	 * @param currentReclStateHistEntry The currentReclStateHistEntry to set
	 */
	public void setCurrentReclStateHistEntry(ReclStateHistEntryImpl currentReclStateHistEntry) {
		this.currentReclStateHistEntry = currentReclStateHistEntry;
	}

	public void setCurrentStateHistEntry(TradeHistEntryImpl currentTradeHistEntry) {
		if (currentTradeHistEntry instanceof AutoStateHistEntryImpl) {
			this.currentAutoStateHistEntry = (AutoStateHistEntryImpl) currentTradeHistEntry;
		} else if (currentTradeHistEntry instanceof ManualStateHistEntryImpl) {
			this.currentManualStateHistEntry = (ManualStateHistEntryImpl) currentTradeHistEntry;
		} else if (currentTradeHistEntry instanceof ReclStateHistEntryImpl) {
			this.currentReclStateHistEntry = (ReclStateHistEntryImpl) currentTradeHistEntry;
		}
	}

	public AutomaticStateImpl getCurrentAutoState() {
		if (currentAutoStateHistEntry != null) {
			return currentAutoStateHistEntry.getAutomaticState();
		}
        return null;
	}

	/**
	 * Returns the currentManualStateHistEntry.
	 * @return ManualStateHistEntryImpl
	 */
	public AbstractManualState getCurrentManualState() {
		if (currentManualStateHistEntry != null) {
			return currentManualStateHistEntry.getManualState();
		}
        return null;
	}

	/**
	 * Returns the currentReclStateHistEntry.
	 * @return ReclStateHistEntryImpl
	 */
	public ReclamationStateImpl getCurrentReclamationState() {
		if (currentReclStateHistEntry != null) {
			return currentReclStateHistEntry.getReclamationState();
		}
        return null;
	}

	/**
     * @return the version of the same trade that was previously seen by MGB, OR
     *         the ID of the trade without the version suffix. This varies
     *         between source systems. {@link SummitRepoConverter},
     *         {@link SummitDerivativeConverter}, and
     *         {@link SummitBondConverter} use the former interpretation.
     */
	public String getTradeGroupId() {
		return tradeGroupId;
	}

	/**
	 * Sets the tradeGroupId.
	 * 
	 * @param tradeGroupId
	 *            The tradeGroupId to set
	 */
	public void setTradeGroupId(String tradeGroupId) {
		this.tradeGroupId = tradeGroupId;
	}

	/**
     * @return true If this trade is a newer (amended) version of an older
     *         trade. Note that this does not necessarily mean that
     *         this old version was also seen by MGB. See
     *         {@link MgbConverter#setTradeAmendGroups()}.
     */
	public boolean isTradeGroup() {
		return isTradeGroup;
	}

	@SuppressWarnings("unused")
    private boolean getIsTradeGroup() {
		return isTradeGroup;
	}

	/**
	 * Sets the isTradeGroup.
	 * 
	 * @param isTradeGroup
	 *            The isTradeGroup to set
	 */
	public void setIsTradeGroup(boolean isTradeGroup) {
		this.isTradeGroup = isTradeGroup;
	}

	/**
	 * Returns the tradeName.
	 * 
	 * @return String
	 */
	public String getTraderName() {
		return traderName;
	}

	/**
	 * Sets the tradeName.
	 * 
	 * @param tradeName
	 *            The tradeName to set
	 */
	public void setTraderName(String tradeName) {
		this.traderName = tradeName;
	}

	public Set<EuwaxRequestImpl> getEuwaxRequests() {
		return euwaxRequests;
	}
	public void setEuwaxRequests(Set<EuwaxRequestImpl> euwaxRequests) {
		this.euwaxRequests = euwaxRequests;
	}
	
	public Set<RequestImpl> getExternalRequests() {
		return externalRequests;
	}
	public void setExternalRequests(Set<RequestImpl> externalRequests) {
		this.externalRequests = externalRequests;
	}
	/**
	 * @return
	 */
	public BookImpl getBook() {
		return book;
	}

	/**
	 * @param impl
	 */
	public void setBook(BookImpl impl) {
		book = impl;
	}

	/**
	 * @return Returns the isLateDeal.
	 */
	public boolean isLateDeal() {
		return isLateDeal;
	}

	public boolean getIsLateDeal() {
		return isLateDeal;
	}

	public boolean isInternalDeal() {
	    return false;
	}

	/**
	 * @param isLateDeal
	 *            The isLateDeal to set.
	 */
	public void setIsLateDeal(boolean isLateDeal) {
		this.isLateDeal = isLateDeal;
	}
	
	/**
	 * Returnes the current reclamation level. If no reclamatione exists 0 is returned.
	 * 
	 * @author WSY4148
	 */
	public int getCurrentReclLevel() {
		if (getCurrentReclStateHistEntry() == null) {
			return 0; 
		}
		return getCurrentReclStateHistEntry().getLevel();
	}

	public boolean isZeroPrice() {
		return getPrice() == 0.0d;
	}
	
	/**
	 * @return Returns the counterparty.
	 */
	public CounterpartyImpl getCounterparty() {
		return counterparty;
	}
	/**
	 * @param counterparty The counterparty to set.
	 */
	public void setCounterparty(CounterpartyImpl counterparty) {
		this.counterparty = counterparty;
	}	
	
	/**
	 * Returns true if the frontoffice delivers a bloomberg id. The 
	 * default implementation in this class returns
	 * false. This method should be overwritten in subclasses
	 * if appropriate.
	 */
	public boolean hasBloombergTradeId() {
		return false;
	}
	
	/**
	 * Returns true if the trade has a bonification property. Depends
	 * on the type of the trade.
	 */
	public boolean hasBonification() {
		return false;
	}
	
	/**
	 * Returns the bloomberg id that has been delivered by the
	 * front office system. The  default implementation in this class returns
	 * null. This method should be overwritten in subclasses
	 * if appropriate.
	 */
	public String getCBloombergTradeId() {
		return null;
	}
	
	public double getCBonification() {
		return 0d;
	}

    
    public String getSystemTimeString() {
        return systemTimeString;
    }

    
    public void setSystemTimeString(String systemTimeString) {
        this.systemTimeString = systemTimeString;
    }

}