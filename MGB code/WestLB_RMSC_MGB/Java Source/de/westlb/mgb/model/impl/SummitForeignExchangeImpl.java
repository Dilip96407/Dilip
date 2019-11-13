package de.westlb.mgb.model.impl;

import java.util.Calendar;

import de.westlb.mgb.util.DateTimeUtils;

public class SummitForeignExchangeImpl extends AbstractMoneyMarketImpl {

    private static final long serialVersionUID = 3790370138635969403L;

    private int tradeVersion;
    private String status;
    private String type;
    private String subType;
    private Calendar farDate;
    private double spotRate;
    private double fwdPointsNearLeg;
    private double fwdPointsFarLeg;
    private double marketRate;
    private double marginPoints;
    private double farAmount;
    private double marketPointsNearLeg;
    private double marketPointsFarLeg;
    private String info;
    private double eodRate;
    private Calendar eodRateDate;
    private double eodRateFX;
    private double bagatelleLimit;
    private double turnoverLimit;
    private boolean isBagatelle;
    private boolean isOutOfTurnoverLimit;

    public SummitForeignExchangeImpl() {
	}

    public SummitForeignExchangeImpl(SourceSystemImpl sourceSystemImpl) {
		setSourceSystem(sourceSystemImpl);
	}

	public SummitForeignExchangeImpl(LoadSummitForeignExchangeImpl summitData, SourceSystemImpl sourceSystemImpl) {
		setSourceSystem(sourceSystemImpl);
		convert(summitData);
	}

    public void convert(LoadSummitForeignExchangeImpl summitData) {
	    setAmendedDate(summitData.getAmendDate());
		setBookId(summitData.getBook());
		setSourceSystemInstrument(summitData.getCcyPair());
		setCounterpartyId(summitData.getCounterparty());
		setEodRate(summitData.getEodRate());
		setEodRateDate(summitData.getEodRateDate());
        setInfo(summitData.getComment());
		setSubType(summitData.getSubType());
		setType(summitData.getType());
        setStatus(summitData.getStatus());
		setSpotRate(summitData.getSpotRate());
		setMarginPoints(summitData.getMarginPoints());
        setFwdPointsNearLeg(summitData.getFwdPointsNearLeg());
        setFwdPointsFarLeg(summitData.getFwdPointsFarLeg());
        // bad naming of the loader data
        setMarketPointsNearLeg(summitData.getMarketPointsNearLeg());
        setMarketPointsFarLeg(summitData.getMarketPointsFarLeg());
        if (isFxSpot()) {
            setMarketRate(summitData.getMarketRateSpot());            
        } else {
            setMarketRate(summitData.getMarketRateFwd());
        }
		setPrice(summitData.getMarginRate());
        setSettlementDate(summitData.getNearDate());
        setFarDate(summitData.getFarDate());
		setSystemDate(summitData.getTradeEntryDate());
		setTradeDate(summitData.getTradeDate());
		setTradeId(summitData.getTradeId()+"_"+summitData.getTradeVersion());
		setTradeVersion(summitData.getTradeVersion());
		setTradeGroupId(summitData.getTradeId());
        setTraderId(summitData.getTraderName());
        setTraderName(summitData.getTraderName());
		setType(summitData.getType());
        setVolume(summitData.getNearAmount());
        setFarAmount(summitData.getFarAmount());
        setEodRateFX(summitData.getEodRateFX());
		setIsLateDeal( (getSystemDate().getTimeInMillis() - getTradeDate().getTimeInMillis()) > DateTimeUtils.MILLIS_PER_DAY );
	}

    public double getMarketRateNear() {
        return getMarketPointsNearLeg();
    }
    
    public double getMarketRateForward() {
        return getMarketPointsFarLeg();
    }

    @Override
    public double getReutersMarketRate() {
        return getMarketRate();
    }

    @Override
    public double getEodMarketRate() {
        return getEodRateFX();
    }

    @Override
    public String getTradeCategory() {
        if (getSubType() != null) {
            return getType()+"_"+getSubType();
        }
        return getType();
    }

    /**
     * Like {@link AbstractMoneyMarketImpl#isOutOfReutersPrice}, but checks spot
     * rate. This is useful for FXSWAP trades (not for other types really) and
     * was requested by C. Kampelmann (Mantis #55444)
     */
    public boolean isNearLegOutOfReutersPrice()
    {
        return isOutOfPrice(getMarketRateNear(),getSpotRate());
    }
    
	@Override
    public double calculateTurnover(double marketRate) {
		return calculateFarTurnover(marketRate)+calculateNearTurnover(marketRate);
	}

	public double calculateFarTurnover(double marketRate) {
	    if (isFxSpot()) {
	        return 0.0;
	    }
	    if (isFxSwap()) {
	        return (marketRate - getPrice())*getFarAmount() /getEodRate();
	    }
	    return (marketRate - getPrice())*getVolume() /getEodRate();
	}

	public double calculateNearTurnover(double marketRate) {
        if (isFxForward()) {
            return 0.0;
        }
        if (isFxSwap()) {
            if (getMarketRateNear() != 0) {
                return (getMarketRateNear() - getSpotRate())*getVolume() /getEodRate();
            }
            return (marketRate - getSpotRate())*getVolume() /getEodRate();
        } 
        return (marketRate - getPrice())*getVolume() /getEodRate();
    }

	public boolean isAmend() {
        return getSystemDate() != null && getAmendedDate() != null && getSystemDate().before(getAmendedDate());
    }

	public boolean isNew() {
        return !isAmend();
    }

	public boolean isEuroCommodity() {
		return getSourceSystemInstrument() != null && getSourceSystemInstrument().indexOf("EUR") > -1;
	}

    public boolean isBagatelle() {
        return isBagatelle;
    }
    
    public boolean isOutOfTurnoverLimit() {
        return isOutOfTurnoverLimit;
    }

	public boolean isDone() {
        return "DONE".equals(getStatus());
    }
	
    public boolean isVerified() {
        return "VER".equals(getStatus());
    }

    public boolean isCancelled() {
        return "CANC".equals(getStatus());
    }

    public boolean isFxSwap() {
        return "FXSWAP".equals(getType());
    }

    public boolean isFxForward() {
        return "FXFWD".equals(getType());
    }

    public boolean isFxSpot() {
        return "FXSPOT".equals(getType());
    }
    
    public boolean isMitteBook() {
		return "SGMITTE_BOOK".equals(getBookId());
	}

	public boolean isFxForwardWithTimeOption() {
	    return isTimeOption() || isDrawnDown();
	}

	public boolean isTimeOption() {
	    return "TIME_OPT".equals(getSubType());
	}

	public boolean isDrawnDown() {
	    return "cDRAWDOWN".equals(getSubType());
	}

    public int getTradeVersion() {
        return tradeVersion;
    }

    
    public void setTradeVersion(int tradeVersion) {
        this.tradeVersion = tradeVersion;
    }

    
    public String getStatus() {
        return status;
    }

    
    public void setStatus(String status) {
        this.status = status;
    }

     
    public String getType() {
        return type;
    }

    
    public void setType(String type) {
        this.type = type;
    }

    
    public String getSubType() {
        return subType;
    }

    
    public void setSubType(String subType) {
        this.subType = subType;
    }

       
    public Calendar getFarDate() {
        return farDate;
    }

    
    public void setFarDate(Calendar farDate) {
        this.farDate = farDate;
    }

    
    public double getSpotRate() {
        return spotRate;
    }

    
    public void setSpotRate(double spotRate) {
        this.spotRate = spotRate;
    }

    
    public double getFwdPointsNearLeg() {
        return fwdPointsNearLeg;
    }

    
    public void setFwdPointsNearLeg(double fwdPointsNearLeg) {
        this.fwdPointsNearLeg = fwdPointsNearLeg;
    }

    
    public double getFwdPointsFarLeg() {
        return fwdPointsFarLeg;
    }

    
    public void setFwdPointsFarLeg(double fwdPointsFarLeg) {
        this.fwdPointsFarLeg = fwdPointsFarLeg;
    }

    
    public double getMarginPoints() {
        return marginPoints;
    }

    
    public void setMarginPoints(double marginPoints) {
        this.marginPoints = marginPoints;
    }

    
    public double getFarAmount() {
        return farAmount;
    }

    
    public void setFarAmount(double farAmount) {
        this.farAmount = farAmount;
    }

    
    public double getMarketPointsNearLeg() {
        return marketPointsNearLeg;
    }

    
    public void setMarketPointsNearLeg(double marketPointsNearLeg) {
        this.marketPointsNearLeg = marketPointsNearLeg;
    }

    
    public double getMarketPointsFarLeg() {
        return marketPointsFarLeg;
    }

    
    public void setMarketPointsFarLeg(double marketPointsFarLeg) {
        this.marketPointsFarLeg = marketPointsFarLeg;
    }

    
    public double getEodRate() {
        if (eodRate == 0) {
            return 1.0;
        }
        return eodRate;
    }

    
    public void setEodRate(double eodRate) {
        this.eodRate = eodRate;
    }

    
    public Calendar getEodRateDate() {
        return eodRateDate;
    }

    
    public void setEodRateDate(Calendar eodRateDate) {
        this.eodRateDate = eodRateDate;
    }

    
    public double getEodRateFX() {
        return eodRateFX;
    }

    
    public void setEodRateFX(double eodRateFX) {
        this.eodRateFX = eodRateFX;
    }

    
    public String getInfo() {
        return info;
    }

    
    public void setInfo(String info) {
        this.info = info;
    }
    
    
    public double getBagatelleLimit() {
        return bagatelleLimit;
    }

    
    public void setBagatelleLimit(double bagatelleLimit) {
        this.bagatelleLimit = bagatelleLimit;
    }

    
    public double getTurnoverLimit() {
        return turnoverLimit;
    }

    
    public void setTurnoverLimit(double turnoverLimit) {
        this.turnoverLimit = turnoverLimit;
    }

    
    public boolean getIsBagatelle() {
        return isBagatelle;
    }

    
    public void setIsBagatelle(boolean isBagatelle) {
        this.isBagatelle = isBagatelle;
    }

    
    public boolean getIsOutOfTurnoverLimit() {
        return isOutOfTurnoverLimit;
    }

    
    public void setIsOutOfTurnoverLimit(boolean isOutOfTurnoverLimit) {
        this.isOutOfTurnoverLimit = isOutOfTurnoverLimit;
    }


    
    public double getMarketRate() {
        return marketRate;
    }


    
    public void setMarketRate(double marketRate) {
        this.marketRate = marketRate;
    }


}
