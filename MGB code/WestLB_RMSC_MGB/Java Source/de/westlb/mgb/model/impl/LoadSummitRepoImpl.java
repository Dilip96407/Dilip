package de.westlb.mgb.model.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Calendar;

/**
 * @author D055625
 */
public class LoadSummitRepoImpl extends DataLoadImpl {

	private String tradeId;
	private String tradeType;
	private String tradeStatus;
	private int tradeVersion;
	private String book;
	private String instrument;
	private String traderId;
	private String isin;
	private String underlyingInstrumentType;
	private String underlyingSubType;
	private String currency;
	private String yieldCurve;
	private Calendar tradeTime;
	private Calendar start;
	private Calendar end;
	private String dayCount;
	private double repoRate;
	private double yieldCurveRate;
	private double startCash;
	private double endCash;
	private double days;
	private double rateDiff;
	private double profitLossDiff;
	private double foreignExchangeRate;
	private String openEndFlag;
	private String counterparty;	
	private String underlyingCategory;
	private double notionalAmount;
	private double fundingSpread;
	private String customerType;
	private Calendar doneDate;
	private Calendar verifyDate;
	private Calendar amendDate;
	private double marketPriceUnderlying;
	private double dealAccruedInterest;
	private double startPrice;
	private double bondAccruedInterest;
	private double npv;
	private String repoMarketType;

	@Override
    public Serializable getId() {
		return getTradeId();
	}

	@Override
    public void setNullId() {
		setTradeId(null);
	}

	@Override
    public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer s = new StringBuffer(this.getClass().getName() + ": ");
		for (int i = 0; i < fields.length; i++) {
			try {
				s.append(
					"["
						+ fields[i].getName()
						+ ": "
						+ fields[i].get(this)
						+ "] ");
			} catch (IllegalAccessException e) {
			}
		}
		return s.toString();
	}


	/**
	 * Returns the bookId.
	 * @return String
	 */
	public String getBook() {
		return book;
	}

	/**
	 * Returns the currency.
	 * @return String
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Returns the dayCount.
	 * @return String
	 */
	public String getDayCount() {
		return dayCount;
	}

	/**
	 * Returns the days.
	 * @return double
	 */
	public double getDays() {
		return days;
	}

	/**
	 * Returns the end.
	 * @return Date
	 */
	public Calendar getEnd() {
		return end;
	}

	/**
	 * Returns the endCash.
	 * @return double
	 */
	public double getEndCash() {
		return endCash;
	}

	/**
	 * Returns the foreignExchangeRate.
	 * @return double
	 */
	public double getForeignExchangeRate() {
		return foreignExchangeRate;
	}

	/**
	 * Returns the instrument.
	 * @return String
	 */
	public String getInstrument() {
		return instrument;
	}


	/**
	 * Returns the isin.
	 * @return String
	 */
	public String getIsin() {
		return isin;
	}

	/**
	 * Returns the mccStatus.
	 * @return String
	 */
	public String getTradeStatus() {
		return tradeStatus;
	}

	/**
	 * Returns the openEndFlag.
	 * @return int
	 */
	public String getOpenEndFlag() {
		return openEndFlag;
	}

	/**
	 * Returns the profitLossDifference.
	 * @return double
	 */
	public double getProfitLossDiff() {
		return profitLossDiff;
	}


	/**
	 * Returns the rateDiff.
	 * @return double
	 */
	public double getRateDiff() {
		return rateDiff;
	}

	/**
	 * Returns the repoRateYieldCurveRate.
	 * @return double
	 */
	public double getRepoRate() {
		return repoRate;
	}

	/**
	 * Returns the start.
	 * @return Calendar
	 */
	public Calendar getStart() {
		return start;
	}

	/**
	 * Returns the startCash.
	 * @return double
	 */
	public double getStartCash() {
		return startCash;
	}


	/**
	 * Returns the traderId.
	 * @return String
	 */
	public String getTraderId() {
		return traderId;
	}

	/**
	 * Returns the tradeTime.
	 * @return Calendar
	 */
	public Calendar getTradeTime() {
		return tradeTime;
	}

	/**
	 * Returns the underlyingInstrumentType.
	 * @return String
	 */
	public String getUnderlyingInstrumentType() {
		return underlyingInstrumentType;
	}


	/**
	 * Returns the yieldCurve.
	 * @return String
	 */
	public String getYieldCurve() {
		return yieldCurve;
	}

	/**
	 * Sets the bookId.
	 * @param bookId The bookId to set
	 */
	public void setBook(String bookId) {
		this.book = bookId;
	}

	/**
	 * Sets the currency.
	 * @param currency The currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * Sets the dayCount.
	 * @param dayCount The dayCount to set
	 */
	public void setDayCount(String dayCount) {
		this.dayCount = dayCount;
	}

	/**
	 * Sets the days.
	 * @param days The days to set
	 */
	public void setDays(double days) {
		this.days = days;
	}

	/**
	 * Sets the end.
	 * @param end The end to set
	 */
	public void setEnd(Calendar end) {
		this.end = end;
	}

	/**
	 * Sets the endCash.
	 * @param endCash The endCash to set
	 */
	public void setEndCash(double endCash) {
		this.endCash = endCash;
	}

	/**
	 * Sets the foreignExchangeRate.
	 * @param foreignExchangeRate The foreignExchangeRate to set
	 */
	public void setForeignExchangeRate(double foreignExchangeRate) {
		this.foreignExchangeRate = foreignExchangeRate;
	}

	/**
	 * Sets the instrument.
	 * @param instrument The instrument to set
	 */
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}


	/**
	 * Sets the isin.
	 * @param isin The isin to set
	 */
	public void setIsin(String isin) {
		this.isin = isin;
	}

	/**
	 * Sets the mccStatus.
	 * @param mccStatus The mccStatus to set
	 */
	public void setTradeStatus(String status) {
		this.tradeStatus = status;
	}

	/**
	 * Sets the openEndFlag.
	 * @param openEndFlag The openEndFlag to set
	 */
	public void setOpenEndFlag(String openEndFlag) {
		this.openEndFlag = openEndFlag;
	}

	/**
	 * Sets the profitLossDifference.
	 * @param profitLossDifference The profitLossDifference to set
	 */
	public void setProfitLossDiff(double profitLossDifference) {
		this.profitLossDiff = profitLossDifference;
	}

	/**
	 * Sets the rateDiff.
	 * @param rateDiff The rateDiff to set
	 */
	public void setRateDiff(double rateDiff) {
		this.rateDiff = rateDiff;
	}

	/**
	 * Sets the repoRateYieldCurveRate.
	 * @param repoRateYieldCurveRate The repoRateYieldCurveRate to set
	 */
	public void setRepoRate(double repoRateYieldCurveRate) {
		this.repoRate = repoRateYieldCurveRate;
	}

	/**
	 * Sets the start.
	 * @param start The start to set
	 */
	public void setStart(Calendar start) {
		this.start = start;
	}

	/**
	 * Sets the startCash.
	 * @param startCash The startCash to set
	 */
	public void setStartCash(double startCash) {
		this.startCash = startCash;
	}

	/**
	 * Sets the traderId.
	 * @param traderId The traderId to set
	 */
	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}

	/**
	 * Sets the tradeTime.
	 * @param tradeTime The tradeTime to set
	 */
	public void setTradeTime(Calendar tradeTime) {
		this.tradeTime = tradeTime;
	}

	/**
	 * Sets the underlyingInstrumentType.
	 * @param underlyingInstrumentType The underlyingInstrumentType to set
	 */
	public void setUnderlyingInstrumentType(String underlyingInstrumentType) {
		this.underlyingInstrumentType = underlyingInstrumentType;
	}

	/**
	 * Sets the yieldCurve.
	 * @param yieldCurve The yieldCurve to set
	 */
	public void setYieldCurve(String yieldCurve) {
		this.yieldCurve = yieldCurve;
	}

	/**
	 * Returns the yieldCurveRate.
	 * @return double
	 */
	public double getYieldCurveRate() {
		return yieldCurveRate;
	}

	/**
	 * Sets the yieldCurveRate.
	 * @param yieldCurveRate The yieldCurveRate to set
	 */
	public void setYieldCurveRate(double yieldCurveRate) {
		this.yieldCurveRate = yieldCurveRate;
	}

	/**
	 * @return Returns the tradeId.
	 */
	public String getTradeId() {
		return tradeId;
	}
	/**
	 * @param tradeId The tradeId to set.
	 */
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	/**
	 * @return Returns the underlyingCategory.
	 */
	public String getUnderlyingCategory() {
		return underlyingCategory;
	}
	/**
	 * @param underlyingCategory The underlyingCategory to set.
	 */
	public void setUnderlyingCategory(String underlyingCategory) {
		this.underlyingCategory = underlyingCategory;
	}
	/**
	 * @return Returns the underlyingSubType.
	 */
	public String getUnderlyingSubType() {
		return underlyingSubType;
	}
	/**
	 * @param underlyingSubType The underlyingSubType to set.
	 */
	public void setUnderlyingSubType(String underlyingSubType) {
		this.underlyingSubType = underlyingSubType;
	}
	/**
	 * @return Returns the tradeVersion.
	 */
	public int getTradeVersion() {
		return tradeVersion;
	}
	/**
	 * @param tradeVersion The tradeVersion to set.
	 */
	public void setTradeVersion(int tradeVersion) {
		this.tradeVersion = tradeVersion;
	}
	/**
	 * @return Returns the tradeType.
	 */
	public String getTradeType() {
		return tradeType;
	}
	/**
	 * @param tradeType The tradeType to set.
	 */
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
    /**
     * @return Returns the counterparty.
     */
    public String getCounterparty() {
        return counterparty;
    }
    /**
     * @param counterparty The counterparty to set.
     */
    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }
    /**
     * @return Returns the notionalAmount.
     */
    public double getNotionalAmount() {
        return notionalAmount;
    }
    /**
     * @param notionalAmount The notionalAmount to set.
     */
    public void setNotionalAmount(double notionalAmount) {
        this.notionalAmount = notionalAmount;
    }
    /**
     * @return Returns the fundingSpread.
     */
    public double getFundingSpread() {
        return fundingSpread;
    }
    /**
     * @param fundingSpread The fundingSpread to set.
     */
    public void setFundingSpread(double fundingSpread) {
        this.fundingSpread = fundingSpread;
    }
	public Calendar getAmendDate() {
		return amendDate;
	}
	public void setAmendDate(Calendar amendDate) {
		this.amendDate = amendDate;
	}
	public double getBondAccruedInterest() {
		return bondAccruedInterest;
	}
	public void setBondAccruedInterest(double bondAccruedInterest) {
		this.bondAccruedInterest = bondAccruedInterest;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public double getDealAccruedInterest() {
		return dealAccruedInterest;
	}
	public void setDealAccruedInterest(double dealAccruedInterest) {
		this.dealAccruedInterest = dealAccruedInterest;
	}
	public Calendar getDoneDate() {
		return doneDate;
	}
	public void setDoneDate(Calendar doneDate) {
		this.doneDate = doneDate;
	}
	public double getMarketPriceUnderlying() {
		return marketPriceUnderlying;
	}
	public void setMarketPriceUnderlying(double marketPriceUnderlying) {
		this.marketPriceUnderlying = marketPriceUnderlying;
	}
	public double getNpv() {
		return npv;
	}
	public void setNpv(double npv) {
		this.npv = npv;
	}
	public double getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}
	public Calendar getVerifyDate() {
		return verifyDate;
	}
	public void setVerifyDate(Calendar verifyDate) {
		this.verifyDate = verifyDate;
	}
	
	public String getRepoMarketType() {
		return repoMarketType;
	}
	public void setRepoMarketType(String repoMarketType) {
		this.repoMarketType = repoMarketType;
	}
}
