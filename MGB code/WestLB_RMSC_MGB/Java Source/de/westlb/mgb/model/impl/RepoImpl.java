package de.westlb.mgb.model.impl;

import java.util.Calendar;

/**
 * 
 */
public class RepoImpl extends TradeImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2444192665887744948L;

	private String instrumentCode;

	private String instrumentType;

	private String underlyingInstrumentType;

	private String underlyingValGroup;

	private String yieldCurve;

	private Calendar startDate;
	
	private Calendar endDate;

	private String dayCount;

	private double repoRate;

	private double yieldCurveRate;

	private double startCash;

	private double endCash;

	private double days;

	private double rateDiff;

	private double profitLossDiff;

	private double foreignExchangeRate;

	private double profitLossDiffEuro;

	private int basePointTolerance;

	private String mccStatus;

	private String openEndFlag;

	private String transactionReference;


    @Override
    public String getTradeCategory() {
        return getInstrumentType();
    }

    @Override
    public double getFrontOfficeMarketRate() {
        return getYieldCurveRate();
    }



	/**
	 * Returns the basePointTolerance.
	 * @return int
	 */
	public int getBasePointTolerance() {
		return basePointTolerance;
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
	 * @return Calendar
	 */
	public Calendar getEndDate() {
		return endDate;
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
	public String getInstrumentCode() {
		return instrumentCode;
	}

	/**
	 * Returns the instrumentType.
	 * @return String
	 */
	public String getInstrumentType() {
		return instrumentType;
	}

	/**
	 * Returns the mccStatus.
	 * @return String
	 */
	public String getMccStatus() {
		return mccStatus;
	}

	/**
	 * Returns the openEndFlag.
	 * @return String
	 */
	public String getOpenEndFlag() {
		return openEndFlag;
	}

	/**
	 * Returns the profitLossDiff.
	 * @return double
	 */
	public double getProfitLossDiff() {
		return profitLossDiff;
	}

	/**
	 * Returns the profitLossDiffEuro.
	 * @return double
	 */
	public double getProfitLossDiffEuro() {
		return profitLossDiffEuro;
	}

	/**
	 * Returns the rateDiff.
	 * @return double
	 */
	public double getRateDiff() {
		return rateDiff;
	}

	/**
	 * Returns the repoRate.
	 * @return double
	 */
	public double getRepoRate() {
		return repoRate;
	}

	/**
	 * Returns the start.
	 * @return Calendar
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * Returns the startCash.
	 * @return double
	 */
	public double getStartCash() {
		return startCash;
	}

	/**
	 * Returns the transactionReference.
	 * @return String
	 */
	public String getTransactionReference() {
		return transactionReference;
	}

	/**
	 * Returns the underlyingInstrumentType.
	 * @return String
	 */
	public String getUnderlyingInstrumentType() {
		return underlyingInstrumentType;
	}

	/**
	 * Returns the underlyingValGroup.
	 * @return String
	 */
	public String getUnderlyingValGroup() {
		return underlyingValGroup;
	}

	/**
	 * Returns the yieldCurve.
	 * @return String
	 */
	public String getYieldCurve() {
		return yieldCurve;
	}

	/**
	 * Returns the yieldCurveRate.
	 * @return double
	 */
	public double getYieldCurveRate() {
		return yieldCurveRate;
	}

	/**
	 * Sets the basePointTolerance.
	 * @param basePointTolerance The basePointTolerance to set
	 */
	public void setBasePointTolerance(int basePointTolerance) {
		this.basePointTolerance = basePointTolerance;
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
	public void setEndDate(Calendar end) {
		this.endDate = end;
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
	public void setInstrumentCode(String instrumentCode) {
		this.instrumentCode = instrumentCode;
	}

	/**
	 * Sets the instrumentType.
	 * @param instrumentType The instrumentType to set
	 */
	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}

	/**
	 * Sets the mccStatus.
	 * @param mccStatus The mccStatus to set
	 */
	public void setMccStatus(String mccStatus) {
		this.mccStatus = mccStatus;
	}

	/**
	 * Sets the openEndFlag.
	 * @param openEndFlag The openEndFlag to set
	 */
	public void setOpenEndFlag(String openEndFlag) {
		this.openEndFlag = openEndFlag;
	}

	/**
	 * Sets the profitLossDiff.
	 * @param profitLossDiff The profitLossDiff to set
	 */
	public void setProfitLossDiff(double profitLossDiff) {
		this.profitLossDiff = profitLossDiff;
	}

	/**
	 * Sets the profitLossDiffEuro.
	 * @param profitLossDiffEuro The profitLossDiffEuro to set
	 */
	public void setProfitLossDiffEuro(double profitLossDiffEuro) {
		this.profitLossDiffEuro = profitLossDiffEuro;
	}

	/**
	 * Sets the rateDiff.
	 * @param rateDiff The rateDiff to set
	 */
	public void setRateDiff(double rateDiff) {
		this.rateDiff = rateDiff;
	}

	/**
	 * Sets the repoRate.
	 * @param repoRate The repoRate to set
	 */
	public void setRepoRate(double repoRate) {
		this.repoRate = repoRate;
	}

	/**
	 * Sets the start.
	 * @param start The start to set
	 */
	public void setStartDate(Calendar start) {
		this.startDate = start;
	}

	/**
	 * Sets the startCash.
	 * @param startCash The startCash to set
	 */
	public void setStartCash(double startCash) {
		this.startCash = startCash;
	}

	/**
	 * Sets the transactionReference.
	 * @param transactionReference The transactionReference to set
	 */
	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}

	/**
	 * Sets the underlyingInstrumentType.
	 * @param underlyingInstrumentType The underlyingInstrumentType to set
	 */
	public void setUnderlyingInstrumentType(String underlyingInstrumentType) {
		this.underlyingInstrumentType = underlyingInstrumentType;
	}

	/**
	 * Sets the underlyingValGroup.
	 * @param underlyingValGroup The underlyingValGroup to set
	 */
	public void setUnderlyingValGroup(String underlyingValGroup) {
		this.underlyingValGroup = underlyingValGroup;
	}

	/**
	 * Sets the yieldCurve.
	 * @param yieldCurve The yieldCurve to set
	 */
	public void setYieldCurve(String yieldCurve) {
		this.yieldCurve = yieldCurve;
	}

	/**
	 * Sets the yieldCurveRate.
	 * @param yieldCurveRate The yieldCurveRate to set
	 */
	public void setYieldCurveRate(double yieldCurveRate) {
		this.yieldCurveRate = yieldCurveRate;
	}

	public boolean isOpenEnd() {
		return "1".equals(this.openEndFlag) || "Y".equals(this.openEndFlag);
	}


}

