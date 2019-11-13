package de.westlb.mgb.model.impl;

import java.util.Calendar;

import de.westlb.mgb.util.DateTimeUtils;
import de.westlb.mgb.util.PriceUtils;

public class SummitMoneyMarketImpl extends AbstractMoneyMarketImpl {

    private static final long serialVersionUID = 7812465043731177120L;

    private static final int INFO_LENTH = 255;
    
    private int tradeVersion;
    private String status;
    private String type;
    private String subType;
    private String index;
    private Calendar maturityDate;
    private double marketRate;
    private double marginPoints;
    private String info;
    private double eodRate;
    private Calendar eodRateDate;
    private boolean autoRateReset;
    private double eodRateMM;
    private double bagatelleLimit;
    private double turnoverLimit;
    private boolean isBagatelle;
    private boolean isOutOfTurnoverLimit;
    private boolean isMccRelevantChange = true;

    public SummitMoneyMarketImpl() {
	}

    public SummitMoneyMarketImpl(SourceSystemImpl sourceSystemImpl) {
		setSourceSystem(sourceSystemImpl);
	}

	public SummitMoneyMarketImpl(LoadSummitMoneyMarketImpl summitData, SourceSystemImpl sourceSystemImpl) {
		setSourceSystem(sourceSystemImpl);
		convert(summitData);
	}

	public void convert(LoadSummitMoneyMarketImpl summitData) {
	    setAmendedDate(summitData.getAmendDate());
		setBookId(summitData.getBook());
        setSourceSystemInstrument(summitData.getCcy());
        setCurrency(summitData.getCcy());
		setCounterpartyId(summitData.getCounterparty());
		setEodRate(summitData.getEodRate());
		setEodRateDate(summitData.getEodRateDate());
		setTradeGroupId(summitData.getTradeId());
		StringBuffer infoString = new StringBuffer();
        if (summitData.getComment() != null) {
            infoString.append(summitData.getComment());
        }
        if (summitData.getStructureID() != null) {
            if (infoString.length() > 0) {
                infoString.append("; ");
            }
            infoString.append(summitData.getStructureID());
        }
        setInfo(infoString.toString().substring(0, Math.min(INFO_LENTH, infoString.toString().length())));
        setType(summitData.getType());
		setSubType(summitData.getSubType());
        setIndex(summitData.getIndex());
        setMarketRate(PriceUtils.calculateMidPrice(summitData.getMarketRate1(), summitData.getMarketRate2()));            
		setMarginPoints(summitData.getMarginPoints());
		setMaturityDate(summitData.getMaturityDate());
		setPrice(summitData.getDealtRate());
		setSettlementDate(summitData.getStartDate());
		setStatus(summitData.getStatus());
		setSystemDate(summitData.getTradeEntryDate());
		setTradeDate(summitData.getTradeDate());
		setTradeId(summitData.getTradeId()+"_"+summitData.getTradeVersion());
		setTradeVersion(summitData.getTradeVersion());
        setTraderId(summitData.getTraderName());
        setTraderName(summitData.getTraderName());
		setVolume(summitData.getNotional());
		setAutoRateReset("1".equals(summitData.getAutoRateReset()));
		setEodRateMM(summitData.getEodRateMM());
		setIsLateDeal( (getSystemDate().getTimeInMillis() - getTradeDate().getTimeInMillis()) > DateTimeUtils.MILLIS_PER_DAY );
	}

	
    @Override
    public String getTradeCategory() {
        if (getSubType() != null) {
            return getSubType()+"_"+getIndex();
        }
        return getSubType();
    }

	@Override
    public double getReutersMarketRate() {
	    return getMarketRate();
	}

    @Override
    public double getEodMarketRate() {
        return getEodRateMM();
    }

    @Override
    public double getFrontOfficeMarketRate() {
        if (getMarketRate() != 0.0) {
            return getMarketRate();
        }
        return getEodMarketRate(); 
    }
    
	@Override
    public double calculateTurnover(double marketPrice) {
		double fx = 1.0;
		if (getEodRate() != 0) {
			fx = 1.0/getEodRate();
		}
		return fx*(marketPrice - getPrice()) * getVolume() * AS_PER_CENT * getMaturityDays() / INTEREST_DAYS_PER_YEAR;
	}

	public double getMaturityDays() {
	    return Math.max(0.0, DateTimeUtils.differenceInDays(getSettlementDate(), getMaturityDate())); 
	}

	public boolean isAmend() {
        return getSystemDate() != null && getAmendedDate() != null && getSystemDate().before(getAmendedDate());
    }

	public boolean isNew() {
        return !isAmend();
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

    public boolean isFloating() {
        return !"FIXED".equals(getIndex());
    }

    public boolean isLoan() {
        return "LOAN".equals(getSubType());
    }

    public boolean isDeposit() {
        return "DEPOSIT".equals(getSubType());
    }

    public boolean isCallLoan() {
        return "CLOAN".equals(getSubType());
    }

    public boolean isCallDeposit() {
        return "CDEPO".equals(getSubType());
    }

    public boolean isCallAccount() {
        return isCallLoan() || isCallDeposit();
    }

    public boolean isBagatelle() {
        return isBagatelle;
    }
    
    public boolean isOutOfTurnoverLimit() {
        return isOutOfTurnoverLimit;
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

    
    public String getIndex() {
        return index;
    }

    
    public void setIndex(String index) {
        this.index = index;
    }
   
    public Calendar getMaturityDate() {
        return maturityDate;
    }

    
    public void setMaturityDate(Calendar maturityDate) {
        this.maturityDate = maturityDate;
    }

    
    public double getMarginPoints() {
        return marginPoints;
    }

    
    public void setMarginPoints(double marginPoints) {
        this.marginPoints = marginPoints;
    }
    
    public double getEodRate() {
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

    
    public boolean isAutoRateReset() {
        return autoRateReset;
    }

    
    public boolean getAutoRateReset() {
        return autoRateReset;
    }

    
    public void setAutoRateReset(boolean autoRateReset) {
        this.autoRateReset = autoRateReset;
    }

    
    public double getEodRateMM() {
        return eodRateMM;
    }

    
    public void setEodRateMM(double eodRateMM) {
        this.eodRateMM = eodRateMM;
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

    
	public boolean isMccRelevantChange() {
		return isMccRelevantChange;
	}
	
	public boolean getIsMccRelevantChange() {
		return isMccRelevantChange;
	}
	
	public void setIsMccRelevantChange(boolean isMccRelevantChange) {
		this.isMccRelevantChange = isMccRelevantChange;
	}

	
	public double getMarketRate() {
        return marketRate;
    }

    
    public void setMarketRate(double marketRate) {
        this.marketRate = marketRate;
    }


}
