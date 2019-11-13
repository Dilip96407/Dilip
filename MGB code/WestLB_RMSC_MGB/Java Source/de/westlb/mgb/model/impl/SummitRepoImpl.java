package de.westlb.mgb.model.impl;

import java.util.Calendar;
import java.util.Iterator;

import de.westlb.mgb.client.server.vo.PriceVo;
import de.westlb.mgb.model.definition.MarketDataRequestStateDef;
import de.westlb.mgb.model.definition.PriceDef;
import de.westlb.mgb.util.DateTimeUtils;

public class SummitRepoImpl extends RepoImpl {

    /**
     * 
     */
    private static final long serialVersionUID = -6674073458161859633L;

    public static final String REPO_MARKET_TYPE_AUTOMATIC_LEIHE = "cSAVB_AUTO";

    public static final String VER = "VER";
    public static final String CANC = "CANC";
    public static final String DONE = "DONE";
    
    public static final String REPO = "REPO";
    public static final String REV = "REV";
    public static final String SECL = "SECL";
    public static final String SECB = "SECB";
    public static final String BSBK = "BSBK";
    public static final String SBBK = "SBBK";
    public static final String COLL = "COLL";
    public static final String NOCL = "NOCL";

    public static final String SPARKASSE = "SPARKASSE";
    public static final String WMBINT = "WMBINT";

    /* #####################################################################
     * 
     * If a new field is added specify the getter method in the filterclass
     * de.westlb.mgb.util.MgbSummitRepoMethodFilter
     * 
     * #####################################################################
     */

	private boolean isStorno;
	private boolean isIntraDayStorno;
	private boolean isMccRelevantChange;
	private boolean predecessorInStornoChain;
	private boolean isBagatelle;
	private boolean exceedsMaxPlDiff;
	private boolean isPosiDepo;
	private boolean isCurrentVersion;
	private String status;
	private int version;
	private String underlyingCategory;
	private double fundingSpread;

	private String customerType;
	
	private Calendar doneDate;

	private Calendar verifyDate;
	
	private double marketPriceUnderlying;
	
	private double dealAccruedInterest;
	
	private double startPrice;
	
	private double bondAccruedInterest;
	
	private double npv;

	private String repoMarketType;
	
	private InstrumentImpl bloombergInstrument;

	private PriceCheckInstrumentImpl bondInstrument;

	private boolean isOpenEndTerminated;

    public SummitRepoImpl() {
	}

    public SummitRepoImpl(SourceSystemImpl sourceSystemImpl) {
		setSourceSystem(sourceSystemImpl);
	}

	public SummitRepoImpl(LoadSummitRepoImpl summitData, SourceSystemImpl sourceSystemImpl) {
		setSourceSystem(sourceSystemImpl);
		convert(summitData);
	}
	
	public InstrumentImpl getBloombergInstrument() {
		return bloombergInstrument;
	}
	public void setBloombergInstrument(InstrumentImpl bloombergInstrument) {
		this.bloombergInstrument = bloombergInstrument;
	}
	
	public void convert(LoadSummitRepoImpl summitData) {
		setUnderlyingValGroup(summitData.getUnderlyingSubType());

		setAmendedDate(summitData.getAmendDate());
		setBookId(summitData.getBook());
		setBondAccruedInterest(summitData.getBondAccruedInterest());
		setCurrency(summitData.getCurrency());
		setCounterpartyId(summitData.getCounterparty());
		setCustomerType(summitData.getCustomerType());
		setDayCount(summitData.getDayCount());
		setDealAccruedInterest(summitData.getDealAccruedInterest());
		setDoneDate(summitData.getDoneDate());
		setEndDate(summitData.getEnd());
		setEndCash(summitData.getEndCash());
		setForeignExchangeRate(summitData.getForeignExchangeRate());
		setFundingSpread(summitData.getFundingSpread());
		setInstrumentCode(summitData.getInstrument());
		setInstrumentType(summitData.getUnderlyingCategory());
		setSourceSystemInstrument(summitData.getIsin());
		setIsTradeGroup(false);
		setStatus(summitData.getTradeStatus());
		setMarketPriceUnderlying(summitData.getMarketPriceUnderlying());
		setNpv(summitData.getNpv());
		setOpenEndFlag(summitData.getOpenEndFlag());
		setRepoMarketType(summitData.getRepoMarketType());
	    setRepoRate(summitData.getRepoRate());

	    setPrice(summitData.getStartPrice());

		setStartDate(summitData.getStart());
		setStartCash(summitData.getStartCash());
		setStartPrice(summitData.getStartPrice());
		if (summitData.getTradeTime() != null) {
			Calendar tradeTime = (Calendar)summitData.getTradeTime().clone();
			tradeTime.add(Calendar.HOUR_OF_DAY,1);
			setSystemDate(tradeTime);
			setTradeDate(tradeTime);
			setSettlementDate(tradeTime);
		}
		setTradeId(summitData.getTradeId()+"_"+summitData.getTradeVersion());
		setSourceSystemReutersId(summitData.getTradeId());
		setTraderId(summitData.getTraderId());
		setTraderName(summitData.getTraderId());
		setUnderlyingInstrumentType(summitData.getUnderlyingInstrumentType());
		setUnderlyingCategory(summitData.getUnderlyingCategory());
		setVolume(summitData.getNotionalAmount());
		setVerifyDate(summitData.getVerifyDate());
		setVersion(summitData.getTradeVersion());
		setYieldCurve(summitData.getYieldCurve());
		setYieldCurveRate(summitData.getYieldCurveRate());

		// Open end repo are calculated with a preset maturity
		if (summitData.getEnd() != null && summitData.getStart() != null) {
			setDays( DateTimeUtils.differenceInDays(summitData.getStart(), summitData.getEnd()));
		}

		setIsLateDeal( (getSystemDate().getTimeInMillis() - getTradeDate().getTimeInMillis()) > DateTimeUtils.MILLIS_PER_DAY );
		
		setIsMccRelevantChange(true);
		//TODO
		setIsPosiDepo(false);
		setIsStorno(CANC.equals(getStatus()));
		setIsIntraDayStorno(false);
		setPredecessorInStornoChain(false);
		setIsCurrentVersion(false);
	}

	@Override
    public double calculatePriceDifference(double minPrice, double maxPrice, boolean isIntervalPrice) {
		if (isIntervalPrice) {
			double minReferencePrice = getVolume() * (getDealAccruedInterest()+minPrice)*0.01;
			double minDiff = (minReferencePrice-Math.abs(getStartCash()))/minReferencePrice*100;
			double maxReferencePrice = getVolume() * (getDealAccruedInterest()+maxPrice)*0.01;
			double maxDiff = (Math.abs(getStartCash())-maxReferencePrice)/maxReferencePrice*100;
			logger.debug("CALC: "+getVolume()+"*("+getDealAccruedInterest()+"+"+minPrice+")/100="+minReferencePrice);
			logger.debug("CALC: ("+Math.abs(getStartCash())+"-"+minReferencePrice+")/"+minReferencePrice+"*100="+(Math.abs(getStartCash())-minReferencePrice)/minReferencePrice*100);
			return Math.max(0.0,Math.max(minDiff, maxDiff));
		}
        double referencePrice = getVolume() * (getDealAccruedInterest()+minPrice)*0.01;
        logger.debug("CALC: "+getVolume()+"*("+getDealAccruedInterest()+"+"+minPrice+")/100="+referencePrice);
        logger.debug("CALC: ("+Math.abs(getStartCash())+"-"+referencePrice+")/"+referencePrice+"*100="+(Math.abs(getStartCash())-referencePrice)/referencePrice*100);
        return (Math.abs(getStartCash())-referencePrice)/referencePrice*100;
	}
	
	public boolean isStorno() {
		return isStorno;
	}

	public boolean isStatusDone() {
		return DONE.equalsIgnoreCase(getStatus());
	}

	public boolean isStatusVerified() {
		return VER.equalsIgnoreCase(getStatus());
	}

	public boolean isStatusCanceled() {
		return CANC.equalsIgnoreCase(getStatus());
	}

	public boolean isRepo() {
		return REPO.equalsIgnoreCase(getUnderlyingValGroup());
	}
	public boolean isReverseRepo() {
		return REV.equalsIgnoreCase(getUnderlyingValGroup());
	}
	public boolean isSecurityLending() {
		return SECL.equalsIgnoreCase(getUnderlyingValGroup());
	}
	public boolean isSecurityBorrowing() {
	    return SECB.equalsIgnoreCase(getUnderlyingValGroup());
	}
	public boolean isBuySellBack() {
		return BSBK.equalsIgnoreCase(getUnderlyingValGroup());
	}
	public boolean isSellBuyBack() {
		return SBBK.equalsIgnoreCase(getUnderlyingValGroup());
	}
	public boolean isCollateral() {
		return COLL.equalsIgnoreCase(getUnderlyingValGroup());
	}
	public boolean isNonCollateral() {
		return NOCL.equalsIgnoreCase(getUnderlyingValGroup());
	}

	public boolean isSecurityLendingOrBorrowing() {
		return isSecurityLending() || isSecurityBorrowing();
	}

	public boolean isSmallRate() {
		return getRepoRate() < 0.01;
	}

	public boolean isMccRelevantChange() {
		return isMccRelevantChange;
	}

	public boolean isUnderlyingPassed() {
    	double diff = calculatePriceDifference(getMarketPriceUnderlying(), getMarketPriceUnderlying(), false);
    	String message = getTradeId()+": marketPrice="+getMarketPriceUnderlying();
		Iterator<BloombergRequestImpl> bloombergRequests = getBloombergRequests().iterator();
		while (bloombergRequests.hasNext()) {
			BloombergRequestImpl bloombergRequest = bloombergRequests.next();
			if (bloombergRequest != null) {
			    PriceVo autoCheckPrice = getAutoCheckPrice(bloombergRequest.getLongId());
			    if (autoCheckPrice != null) {
			    	diff = calculatePriceDifference(autoCheckPrice.getMinPrice(), autoCheckPrice.getMaxPrice(), PriceDef.INTERVAL.equals(autoCheckPrice.getPriceType()));
			    	message = getTradeId()+": BLBMarketPrice="+autoCheckPrice.getMinPrice()+"/"+autoCheckPrice.getMaxPrice();
			    }
			}
			if (getBondInstrument() != null && getBondInstrument().getPriceCheckCategory() != null) {
				double threshold = getBondInstrument().getPriceCheckCategory().getPriceTolerancePercent();
				logger.debug(message+" (diff="+diff+", maxTheshold="+threshold+")");
				
				if ( Math.abs(diff) < threshold) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasNoBloombergPrice() {
		Iterator<BloombergRequestImpl> bloombergRequests = getBloombergRequests().iterator();
		while (bloombergRequests.hasNext()) {
			BloombergRequestImpl bloombergRequest = bloombergRequests.next();
			if (bloombergRequest != null) {
			    if ( !MarketDataRequestStateDef.OK_PRICE_UNVALIDATED.equals(bloombergRequest.getRequestState()) && !MarketDataRequestStateDef.OK.equals(bloombergRequest.getRequestState()) ) {
			        return true;
			    }
			} else {
				return true;
			}
		}
		return false;
	}
	
	public boolean isUnderlyingPassed1() {
    	double referenceMarketPrice = getMarketPriceUnderlying();
    	String message = "marketPrice=";
		Iterator<BloombergRequestImpl> bloombergRequests = getBloombergRequests().iterator();
		while (bloombergRequests.hasNext()) {
			BloombergRequestImpl bloombergRequest = bloombergRequests.next();
			if (bloombergRequest != null) {
			    PriceVo autoCheckPrice = getAutoCheckPrice(bloombergRequest.getLongId());
			    if (autoCheckPrice != null) {
			    	referenceMarketPrice = autoCheckPrice.getMidPrice();
			    	message = "BLBMarketPrice=";
			    }
			}
			if (getInstrument() != null && getInstrument().getPriceCheckCategory() != null) {
				double referencePrice = getVolume() * (getDealAccruedInterest()+referenceMarketPrice)*0.01;
				double diff = (getStartPrice()-referencePrice)/referencePrice*100;
				double threshold = getInstrument().getPriceCheckCategory().getPriceTolerancePercent();
				logger.debug(message+referenceMarketPrice+" referencePrice="+referencePrice+" (diff="+diff+", maxTheshold="+threshold+")");
				
				if ( Math.abs(diff) < threshold) {
					return true;
				}
			}
		}
		return false;
	}

    public boolean isAutomatischeWertpapierLeihe() {
        return REPO_MARKET_TYPE_AUTOMATIC_LEIHE.equals(repoMarketType);
    }

    public boolean isBilateraleWertpapierLeihe() {
        return !REPO_MARKET_TYPE_AUTOMATIC_LEIHE.equals(repoMarketType) && isSecurityLendingOrBorrowing();
    }
    
    public boolean isSparkasse() {
        return SPARKASSE.equals(customerType);
    }

    @Override
    public boolean isInternalDeal() {
        return WMBINT.equals(customerType);
    }

	public boolean predecessorInStornoChain() {
		return predecessorInStornoChain;
	}

   public double calcRateReference() {
       if (isSecurityLendingOrBorrowing() ) {
           if (getInstrument() != null && getInstrument().getPriceCheckCategory() != null) {
               // tolerance in percent e.g. 90
               // YieldCurveRate in decimal e.g. 0.01=1PB
               // upperBound is in BP
               double upperBound = getInstrument().getPriceCheckCategory().getPriceTolerancePercent()*getYieldCurveRate();
               double lowerBound = getInstrument().getPriceCheckCategory().getPriceToleranceAbsolute();
               return (upperBound + lowerBound)/2.0;
           }
           logger.warn("Missing price check category for trade "+getTradeId());
           return 0.0;
       }
       return getYieldCurveRate();
   }

   public double calcRateTolerance() {
       if (getInstrument() != null && getInstrument().getPriceCheckCategory() != null) {
           double upperBound = getInstrument().getPriceCheckCategory().getPriceTolerancePercent()*getYieldCurveRate();
           double lowerBound = getInstrument().getPriceCheckCategory().getPriceToleranceAbsolute();
           if (isSecurityLendingOrBorrowing() && upperBound > 0) {
               // tolerance in percent e.g. 90
               // YieldCurveRate in decimal e.g. 0.01=1PB
               // upperBound is in BP
               if (upperBound <= lowerBound) {
                   logger.warn("Rate tolerance set to zero");
                   return 0.0;
               }
               logger.debug("Rate must be between "+lowerBound+" and "+upperBound+" i.e. ref. rate is "+(lowerBound + (upperBound - lowerBound)/2.0));
               return (upperBound - lowerBound)/2.0;
           }
        // result is in BP
           return getInstrument().getPriceCheckCategory().getPriceToleranceAbsolute();
       }
       logger.warn("Missing price check category for trade "+getTradeId());
       return 0.0;
   }

   public double calcRateDifference() {
       if (isSecurityLendingOrBorrowing()) {
           double refRate = calcRateReference();
           return getRepoRate()*100 - refRate;
       }
       return (getYieldCurveRate()-getRepoRate())*100.0;
   }

   public double calcProfitLossDiff() {
//       double openEndDuration = 1d;
//       if (getEndDate() == null || getStartDate() == null) {
//           openEndDuration = getDays();
//       }
       if (isSecurityBorrowing() || isSecurityLending()) {
          if (getRepoRate() != 0.0) {
              double refRate = calcRateReference();
//              return ((0.01*refRate/getRepoRate()*getEndCash())-getEndCash())*openEndDuration;
              return (0.01*refRate/getRepoRate()*getEndCash())-getEndCash();
          }
       } else {
           return getStartCash()*getDays()*(getYieldCurveRate()-getRepoRate())/36000.0;
       }
       return 0.0;
   }
	
	public boolean rateDiffLessBpTolerance() {
	    return calcRateTolerance() > Math.abs(getRateDiff()) ;
	}

	public boolean isBagatelle() {
		return isBagatelle;
	}

	public boolean exceedsMaxPlDiff() {
		return exceedsMaxPlDiff;
	}

	public boolean isPosiDepo() {
		return isPosiDepo;
	}

	@Override
    public boolean isOpenEnd() {
		return "Y".equals(getOpenEndFlag());
	}

    /**
     * @return Returns the exceedsMaxPlDiff.
     */
    public boolean getExceedsMaxPlDiff() {
        return exceedsMaxPlDiff;
    }
    /**
     * @param exceedsMaxPlDiff The exceedsMaxPlDiff to set.
     */
    public void setExceedsMaxPlDiff(boolean exceedsMaxPlDiff) {
        this.exceedsMaxPlDiff = exceedsMaxPlDiff;
    }
    /**
     * @return Returns the predecessorInStornoChain.
     */
    public boolean getPredecessorInStornoChain() {
        return predecessorInStornoChain;
    }
    /**
     * @param predecessorInStornoChain The predecessorInStornoChain to set.
     */
    public void setPredecessorInStornoChain(boolean predecessorInStornoChain) {
        this.predecessorInStornoChain = predecessorInStornoChain;
    }
    /**
     * @param isBagatelle The isBagatelle to set.
     */
    public void setIsBagatelle(boolean isBagatelle) {
        this.isBagatelle = isBagatelle;
    }
    /**
     * @param isMccRelevantChange The isMccRelevantChange to set.
     */
    public void setIsMccRelevantChange(boolean isMccRelevantChange) {
        this.isMccRelevantChange = isMccRelevantChange;
    }
    /**
     * @param isPosiDepo The isPosiDepo to set.
     */
    public void setIsPosiDepo(boolean isPosiDepo) {
        this.isPosiDepo = isPosiDepo;
    }
    /**
     * @param isStorno The isStorno to set.
     */
    public void setIsStorno(boolean isStorno) {
        this.isStorno = isStorno;
    }

	public boolean getIsStorno() {
		return isStorno;
	}
	
	public boolean getIsMccRelevantChange() {
		return isMccRelevantChange;
	}

	public boolean getIsBagatelle() {
		return isBagatelle;
	}

	public boolean getIsPosiDepo() {
		return isPosiDepo;
	}

    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return Returns the version.
     */
    public int getVersion() {
        return version;
    }
    /**
     * @param version The version to set.
     */
    public void setVersion(int version) {
        this.version = version;
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
     * @return Returns the isIntraDayStorno.
     */
    public boolean getIsIntraDayStorno() {
        return isIntraDayStorno;
    }
    public boolean isIntraDayStorno() {
        return isIntraDayStorno;
    }
    /**
     * @param isIntraDayStorno The isIntraDayStorno to set.
     */
    public void setIsIntraDayStorno(boolean isIntraDayStorno) {
        this.isIntraDayStorno = isIntraDayStorno;
    }
    /**
     * @return Returns the isCurrentVersion.
     */
    public boolean isCurrentVersion() {
        return isCurrentVersion;
    }

    public boolean getIsCurrentVersion() {
        return isCurrentVersion;
    }
    /**
     * @param isCurrentVersion The isCurrentVersion to set.
     */
    public void setIsCurrentVersion(boolean isCurrentVersion) {
        this.isCurrentVersion = isCurrentVersion;
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
	
	
	public PriceCheckInstrumentImpl getBondInstrument() {
		return bondInstrument;
	}
	public void setBondInstrument(PriceCheckInstrumentImpl bondInstrument) {
		this.bondInstrument = bondInstrument;
	}
	@Override
    public boolean hasBloombergTradeId() {
		return true;
	}
	@Override
    public String getCBloombergTradeId() {
		return getInstrumentCode();
	}
    
    public boolean isOpenEndTerminated() {
        return isOpenEndTerminated;
    }

    public boolean getIsOpenEndTerminated() {
        return isOpenEndTerminated;
    }

    public void setIsOpenEndTerminated(boolean isOpenEndTerminated) {
        this.isOpenEndTerminated = isOpenEndTerminated;
    }

}

