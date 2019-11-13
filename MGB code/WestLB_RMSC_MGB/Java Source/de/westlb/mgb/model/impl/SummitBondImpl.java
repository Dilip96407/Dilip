package de.westlb.mgb.model.impl;

import java.util.Calendar;
import java.util.Iterator;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.server.vo.PriceVo;
import de.westlb.mgb.model.definition.MarketDataRequestStateDef;

public class SummitBondImpl extends TradeImpl {

    /**
     * 
     */
    private static final long serialVersionUID = -7156962565055701699L;

    static Logger logger = Logger.getLogger(SummitBondImpl.class);

    public static final String VER = "VER";
    public static final String CANC = "CANC";
    public static final String DONE = "DONE";
    
    public static final String BOND = "BOND";
    public static final String LOPT = "LOPT";
    public static final String FUT = "FUT";
    public static final String BONDOP = "BONDOP";

    public static final String WMBINT = "WMBINT";    
    
    public static final String VIVALDIS_TRADE = "VIVALDIS";
    
    private static final int DESCRIPTION_LENTH = 255;

    /* #####################################################################
     * 
     * If a new field is added specify the getter method in the filterclass
     * de.westlb.mgb.util.MgbSummitBondMethodFilter
     * 
     * #####################################################################
     */
    private InstrumentImpl bloombergInstrument;
    private String status;
    private String marketer;
	private double fxEuroRate;
	private String tradeType;
	private String subType;
	private String category;
	private Calendar startDay;
	private Calendar expireDay;
	private String instrumentName;
	private Calendar instrumentStartDay;
	private Calendar instrumentExpireDay;
	private String issuer;
	private String alias;
	private String updatedBy;
	private int version;
	private String counterpartyReference;  				    
	private String description;
	private String structure;
	private String company;
	private String customerType;
	private String legalName;
	private double tradePrice;
	private double marketPrice;
	private double theoreticalPrice;

	private double bagatelleLimit;
	private double turnoverLimit;
	private double bonification;

	private boolean isSmallCustomer;
	private boolean isExchangeTraded;
	private boolean isIntraDayStorno;
	private boolean isStorno;
	private boolean isMccRelevantChange;
	private boolean isBagatelle;
	private boolean isOutOfTurnoverLimit;
	private boolean isCurrentVersion;
	private boolean isBackToBack;
	private boolean isBackToBackCheck;

	protected final static double asPerCent = 0.01d;

    public SummitBondImpl() {
	}

    public SummitBondImpl(SourceSystemImpl sourceSystemImpl) {
		setSourceSystem(sourceSystemImpl);
	}

	public SummitBondImpl(LoadSummitBondImpl summitData, SourceSystemImpl sourceSystemImpl) {
		setSourceSystem(sourceSystemImpl);
		convert(summitData);
	}

	public void convert(LoadSummitBondImpl summitData) {
	    setAmendedDate(summitData.getUpdateTime());
		setAlias(summitData.getAlias());
		setCategory(summitData.getCategory());
		setBonification(summitData.getDiscount());
		setBookId(summitData.getBook());
		setCounterpartyId(summitData.getCounterparty());
		setCounterpartyReference(summitData.getCounterpartyReference());
		setCompany(summitData.getCompany());
		setCurrency(summitData.getCurrency());
		setCustomerType(summitData.getCustomerType());
		StringBuffer descriptionString = new StringBuffer();
		if (summitData.getDescription() != null) {
			descriptionString.append(summitData.getDescription());			
		}
		if (summitData.getExtNote1() != null) {
			if (descriptionString.length() > 0) {
				descriptionString.append("\n");
			}
			descriptionString.append(summitData.getExtNote1());
		}
		if (summitData.getExtNote2() != null) {
			if (descriptionString.length() > 0) {
				descriptionString.append("\n");
			}
			descriptionString.append(summitData.getExtNote2());
		}
		setDescription(descriptionString.toString().substring(0, Math.min(DESCRIPTION_LENTH, descriptionString.toString().length())));
		setExpireDay(summitData.getExpireDay());
		setFxEuroRate(summitData.getFxEuroRate());
		setTradeType(summitData.getTradeType());
		setIssuer(summitData.getIssuer());
		setInstrumentName(summitData.getInstrumentName());
		setInstrumentStartDay(summitData.getInstrumentStartDay());
		setInstrumentExpireDay(summitData.getInstrumentExpireDay());
		setLegalName(summitData.getLegalName());
		setMarketPrice(summitData.getMarketPrice());
		setMarketer(summitData.getMarketer());
		setTheoreticalPrice(summitData.getTheoreticalPrice());
		setTradePrice(summitData.getTradePrice());
		setPrice(summitData.getTradePrice());
		setSettlementDate(summitData.getValueDate());
		setSourceSystemInstrument(summitData.getIsin());
		setStartDay(summitData.getStartDay());
		setSubType(summitData.getSubType());
		if (summitData.getUpdateTime() != null) {
			// changing LON to DUS time
			Calendar updateTime = (Calendar)summitData.getUpdateTime().clone();
			updateTime.add(Calendar.HOUR_OF_DAY,1);
			setSystemDate(updateTime);
		}

		// TODO: Rename the property "SourceSystemBloombergId" into BloombergTradeId
		setSourceSystemBloombergId(summitData.getInstrument());

		if (summitData.getTradeTime() != null) {
			// changing LON to DUS time
			Calendar tradeTime = (Calendar)summitData.getTradeTime().clone();
			tradeTime.add(Calendar.HOUR_OF_DAY,1);
			setTradeDate(tradeTime);
		}
		
		// this is just temporaray and not "the fine english way"
		setSourceSystemReutersId(summitData.getTradeId());
		
		setTradeId(summitData.getTradeId()+"_"+summitData.getTradeVersion());
		if (summitData.getTrader() != null && summitData.getTrader().length() > 0) {
			setTraderId(summitData.getTrader());
			setTraderName(summitData.getTrader());
		} else {
			setTraderId(summitData.getUpdatedBy());
			setTraderName(summitData.getUpdatedBy());
		}
		setStatus(summitData.getTradeStatus());
		setStructure(summitData.getStructure());
		setUpdatedBy(summitData.getUpdatedBy());
		setVolume(summitData.getNominal());
		setVersion(summitData.getTradeVersion());
		
		setIsExchangeTraded(getAlias() != null && getAlias().toUpperCase().startsWith("HD"));
		setIsStorno(false);
		setIsMccRelevantChange(true);
		setIsCurrentVersion(false);
		
//        setIsLateDeal( (getSystemDate().getTimeInMillis() - getTradeDate().getTimeInMillis()) > DateTimeUtils.MILLIS_PER_DAY );
        setIsLateDeal(false);
		
		setIsSmallCustomer( checkSmallCustomer() );
	}

    @Override
    public double getFrontOfficeMarketRate() {
        return getTheoreticalPrice();
    }

    @Override
    public String getTradeCategory() {
        return getCategory();
    }

	@Override
    public void addAutoCheckPrices(PriceVo autoCheckPrice) {
	    super.addAutoCheckPrices(autoCheckPrice);
	    double turnover = calculateTurnover(autoCheckPrice.getMidPrice());
	    logger.debug("Adding price "+autoCheckPrice.getMidPrice());
	    checkTurnoverLimits(turnover);
	}

	public void checkTurnoverLimits(double turnover) {
	    boolean bagatelle = Math.abs(turnover) < getBagatelleLimit();
	    boolean outOfTurnover = Math.abs(turnover) > getTurnoverLimit();
	    logger.debug("Setting turnover="+turnover+", bagatelle="+bagatelle+", outOfTurnover="+outOfTurnover);
	    setIsBagatelle(bagatelle);
	    setIsOutOfTurnoverLimit(outOfTurnover);
	}
		
	@Override
    public double calculateTurnover(double marketPrice) {
		return calculateTurnover(marketPrice, getPrice(), getVolume(), getFxEuroRate());
	}

	public static double calculateTurnover(double marketPrice, double tradePrice, double volume, double fxrate) {
		if (marketPrice == 0d) {
			return 0d;
		}
		if (fxrate == 0d) {
			return (marketPrice - tradePrice) * volume*0.01;
		}
        return (marketPrice - tradePrice) * volume*0.01 /fxrate;
	}

	public boolean isPriceHundred() {
	    return (Math.abs(getPrice() - 100.0d) < 0.00001d);
	}

	public boolean isBuy() {
	    return getVolume() > 0.0;
	}
	
	public boolean isSell() {
	    return !isBuy();
	}

	public boolean isBloombergOutOfTurnoverLimit() {
		Iterator<BloombergRequestImpl> bloombergRequests = getBloombergRequests().iterator();
		while (bloombergRequests.hasNext()) {
			BloombergRequestImpl bloombergRequest = bloombergRequests.next();
			if (bloombergRequest != null) {
			    PriceVo autoCheckPrice = getAutoCheckPrice(bloombergRequest.getLongId());
				if (autoCheckPrice != null) {
					boolean isOutOfTurnoverLimit = Math.abs(calculateTurnover(autoCheckPrice.getMidPrice())) > getTurnoverLimit();
					if (!isOutOfTurnoverLimit) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public boolean isBloombergBagatelle() {
		Iterator<BloombergRequestImpl> bloombergRequests = getBloombergRequests().iterator();
		while (bloombergRequests.hasNext()) {
			BloombergRequestImpl bloombergRequest = bloombergRequests.next();
			if (bloombergRequest != null) {
			    PriceVo autoCheckPrice = getAutoCheckPrice(bloombergRequest.getLongId());
				if (autoCheckPrice != null) {
					boolean isBagatelle = Math.abs(calculateTurnover(autoCheckPrice.getMidPrice())) < getBagatelleLimit();
					if (!isBagatelle) {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}


    @Override
    public boolean isInternalDeal() {
        return WMBINT.equals(customerType);
    }

    private boolean checkSmallCustomer() {
    	if (getAlias() != null && (getAlias().toUpperCase().startsWith("WB") || getAlias().toUpperCase().startsWith("SB") || getAlias().toUpperCase().startsWith("DB"))) {
    		if (getCounterpartyReference() != null && getCounterpartyReference().length() == 8) {
    			try {
    				Integer.parseInt(getCounterpartyReference());
    				return true;
    			} catch (Exception e) {}
    		}
    	}
    	return false;
    }

	/**
	 * @return Returns the isExchangeTraded.
	 */
	public boolean isExchangeTraded() {
		return isExchangeTraded;
	}
	public boolean getIsExchangeTraded() {
		return isExchangeTraded;
	}

	/**
	 * @return Returns the isIntraDayStorno.
	 */
	public boolean isIntraDayStorno() {
		return isIntraDayStorno;
	}
	public boolean getIsIntraDayStorno() {
		return isIntraDayStorno;
	}

	/**
	 * @return Returns the isSmallCustomer.
	 */
	public boolean isSmallCustomer() {
		return isSmallCustomer;
	}
	public boolean getIsSmallCustomer() {
		return isSmallCustomer;
	}

	/**
	 * @return Returns the isStorno.
	 */
	public boolean isStorno() {
		return isStorno;
	}
	public boolean getIsStorno() {
		return isStorno;
	}
			
	public boolean isReutersOk() {
		if (getInstrument() != null && getInstrument().getPriceCheckCategory() != null) {
			double minThreshold = marketPrice * (1 - getInstrument().getPriceCheckCategory().getPriceTolerancePercent() * asPerCent);
			double maxThreshold = marketPrice * (1 + getInstrument().getPriceCheckCategory().getPriceTolerancePercent() * asPerCent);
			logger.debug("tradePrice="+getPrice()+" (minThreshold="+minThreshold+", maxThreshold="+maxThreshold+")");
			if (minThreshold < getPrice() && getPrice() < maxThreshold) {
				return true;
			}
		}
		return false;
	}

	public boolean isBloombergOk() {
		Iterator<BloombergRequestImpl> bloombergRequests = getBloombergRequests().iterator();
		while (bloombergRequests.hasNext()) {
			BloombergRequestImpl bloombergRequest = bloombergRequests.next();
			if (bloombergRequest != null) {
			    PriceVo autoCheckPrice = getAutoCheckPrice(bloombergRequest.getLongId());
			    if (autoCheckPrice != null) {
			        if (getInstrument() != null && getInstrument().getPriceCheckCategory() != null) {
			            double minThreshold = autoCheckPrice.getMinPrice() * (1 - getInstrument().getPriceCheckCategory().getPriceTolerancePercent() * asPerCent);
			            double maxThreshold = autoCheckPrice.getMaxPrice() * (1 + getInstrument().getPriceCheckCategory().getPriceTolerancePercent() * asPerCent);
						logger.debug("tradePrice="+getPrice()+" (minThreshold="+minThreshold+", maxThreshold="+maxThreshold+")");
			            if (minThreshold < getPrice() && getPrice() < maxThreshold) {
			                return true;
			            }
			        }
			        return false;
			    }
			}
		}
		return false;
	}

	public boolean hasNoTheoreticalPrice() {
		return theoreticalPrice == 0d;
	}

	public boolean isTheoreticalOk() {
		if (getInstrument() != null && getInstrument().getPriceCheckCategory() != null) {
			double minThreshold = theoreticalPrice * (1 - getInstrument().getPriceCheckCategory().getPriceTolerancePercent() * asPerCent);
			double maxThreshold = theoreticalPrice * (1 + getInstrument().getPriceCheckCategory().getPriceTolerancePercent() * asPerCent);
			logger.debug("tradePrice="+getPrice()+" (minThreshold="+minThreshold+", maxThreshold="+maxThreshold+")");
			if (minThreshold < getPrice() && getPrice() < maxThreshold) {
				return true;
			}
		}
		return false;
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

	public boolean isBond() {
		return BOND.equalsIgnoreCase(getTradeType());
	}

	public boolean isListedOption() {
		return LOPT.equalsIgnoreCase(getTradeType());
	}

	public boolean isFuture() {
		return FUT.equalsIgnoreCase(getTradeType());
	}

	public boolean isBondOption() {
		return BONDOP.equalsIgnoreCase(getTradeType());
	}

	public boolean isBackToBack() {
		return isBackToBack; 
	}
	/**
	 * @return Returns the alias.
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * @param alias The alias to set.
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * @return Returns the bloombergInstrument.
	 */
	public InstrumentImpl getBloombergInstrument() {
		return bloombergInstrument;
	}
	/**
	 * @param bloombergInstrument The bloombergInstrument to set.
	 */
	public void setBloombergInstrument(InstrumentImpl bloombergInstrument) {
		this.bloombergInstrument = bloombergInstrument;
	}
	/**
	 * @return Returns the category.
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category The category to set.
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return Returns the expireDay.
	 */
	public Calendar getExpireDay() {
		return expireDay;
	}
	/**
	 * @param expireDay The expireDay to set.
	 */
	public void setExpireDay(Calendar expireDay) {
		this.expireDay = expireDay;
	}
	/**
	 * @return Returns the fxEuroRate.
	 */
	public double getFxEuroRate() {
		return fxEuroRate;
	}
	/**
	 * @param fxEuroRate The fxEuroRate to set.
	 */
	public void setFxEuroRate(double fxEuroRate) {
		this.fxEuroRate = fxEuroRate;
	}
	/**
	 * @return Returns the instrumentExpireDay.
	 */
	public Calendar getInstrumentExpireDay() {
		return instrumentExpireDay;
	}
	/**
	 * @param instrumentExpireDay The instrumentExpireDay to set.
	 */
	public void setInstrumentExpireDay(Calendar instrumentExpireDay) {
		this.instrumentExpireDay = instrumentExpireDay;
	}
	/**
	 * @return Returns the instrumentStartDay.
	 */
	public Calendar getInstrumentStartDay() {
		return instrumentStartDay;
	}
	/**
	 * @param instrumentStartDay The instrumentStartDay to set.
	 */
	public void setInstrumentStartDay(Calendar instrumentStartDay) {
		this.instrumentStartDay = instrumentStartDay;
	}
	/**
	 * @return Returns the isBagatelle.
	 */
	public boolean isBagatelle() {
		return isBagatelle;
	}
	public boolean getIsBagatelle() {
		return isBagatelle;
	}
	/**
	 * @param isBagatelle The isBagatelle to set.
	 */
	public void setIsBagatelle(boolean isBagatelle) {
		this.isBagatelle = isBagatelle;
	}
	/**
	 * @return Returns the issuer.
	 */
	public String getIssuer() {
		return issuer;
	}
	/**
	 * @param issuer The issuer to set.
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	/**
	 * @return Returns the marketPrice.
	 */
	public double getMarketPrice() {
		return marketPrice;
	}
	/**
	 * @param marketPrice The marketPrice to set.
	 */
	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}
	/**
	 * @return Returns the startDay.
	 */
	public Calendar getStartDay() {
		return startDay;
	}
	/**
	 * @param startDay The startDay to set.
	 */
	public void setStartDay(Calendar startDay) {
		this.startDay = startDay;
	}
	/**
	 * @return Returns the subType.
	 */
	public String getSubType() {
		return subType;
	}
	/**
	 * @param subType The subType to set.
	 */
	public void setSubType(String subType) {
		this.subType = subType;
	}
	/**
	 * @return Returns the theoreticalPrice.
	 */
	public double getTheoreticalPrice() {
		return theoreticalPrice;
	}
	/**
	 * @param theoreticalPrice The theoreticalPrice to set.
	 */
	public void setTheoreticalPrice(double theoreticalPrice) {
		this.theoreticalPrice = theoreticalPrice;
	}
	/**
	 * @return Returns the tradePrice.
	 */
	public double getTradePrice() {
		return tradePrice;
	}
	/**
	 * @param tradePrice The tradePrice to set.
	 */
	public void setTradePrice(double tradePrice) {
		this.tradePrice = tradePrice;
	}
	/**
	 * @return Returns the tradeStatus.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param tradeStatus The tradeStatus to set.
	 */
	public void setStatus(String tradeStatus) {
		this.status = tradeStatus;
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
	 * @return Returns the updatedBy.
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy The updatedBy to set.
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @param isExchangeTraded The isExchangeTraded to set.
	 */
	public void setIsExchangeTraded(boolean isExchangeTraded) {
		this.isExchangeTraded = isExchangeTraded;
	}
	
	/**
	 * @return Returns the isOutOfTurnoverLimit.
	 */
	public boolean isOutOfTurnoverLimit() {
		return isOutOfTurnoverLimit;
	}
	public boolean getIsOutOfTurnoverLimit() {
		return isOutOfTurnoverLimit;
	}
	/**
	 * @param isOutOfTurnoverLimit The isOutOfTurnoverLimit to set.
	 */
	public void setIsOutOfTurnoverLimit(boolean isOutOfTurnoverLimit) {
		this.isOutOfTurnoverLimit = isOutOfTurnoverLimit;
	}
	/**
	 * @param isIntraDayStorno The isIntraDayStorno to set.
	 */
	public void setIsIntraDayStorno(boolean isIntraDayStorno) {
		this.isIntraDayStorno = isIntraDayStorno;
	}
	/**
	 * @param isSmallCustomer The isSmallCustomer to set.
	 */
	public void setIsSmallCustomer(boolean isSmallCustomer) {
		this.isSmallCustomer = isSmallCustomer;
	}
	/**
	 * @param isStorno The isStorno to set.
	 */
	public void setIsStorno(boolean isStorno) {
		this.isStorno = isStorno;
	}

    /**
     * @return Returns the bagatelleLimit.
     */
    public double getBagatelleLimit() {
        return bagatelleLimit;
    }
    /**
     * @param bagatelleLimit The bagatelleLimit to set.
     */
    public void setBagatelleLimit(double bagatelleLimit) {
        this.bagatelleLimit = bagatelleLimit;
    }
    /**
     * @return Returns the turnoverLimit.
     */
    public double getTurnoverLimit() {
        return turnoverLimit;
    }
    /**
     * @param turnoverLimit The turnoverLimit to set.
     */
    public void setTurnoverLimit(double turnoverLimit) {
        this.turnoverLimit = turnoverLimit;
    }
    /**
     * @return Returns the isMccRelevantStorno.
     */
    public boolean isMccRelevantChange() {
        return isMccRelevantChange;
    }
    public boolean getIsMccRelevantChange() {
        return isMccRelevantChange;
    }
    /**
     * @param isMccRelevantStorno The isMccRelevantStorno to set.
     */
    public void setIsMccRelevantChange(boolean isMccRelevantChange) {
        this.isMccRelevantChange = isMccRelevantChange;
    }
    
    public boolean hasBloombergRequestString() {
        return getBloombergInstrument() != null;
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
     * @return Returns the instrumentName.
     */
    public String getInstrumentName() {
        return instrumentName;
    }
    /**
     * @param instrumentName The instrumentName to set.
     */
    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
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
     * @return Returns the counterpartyReference.
     */
    public String getCounterpartyReference() {
        return counterpartyReference;
    }
    /**
     * @param counterpartyReference The counterpartyReference to set.
     */
    public void setCounterpartyReference(String counterpartyReference) {
        this.counterpartyReference = counterpartyReference;
    }
    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
	/**
	 * @return Returns the structure.
	 */
	public String getStructure() {
		return structure;
	}
	/**
	 * @param structure The structure to set.
	 */
	public void setStructure(String structure) {
		this.structure = structure;
	}

	/**
	 * @return Returns the company.
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company The company to set.
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	
	public void setIsBackToBack(boolean isBackToBack) {
		this.isBackToBack = isBackToBack;
	}
	
	public boolean getIsBackToBack() {
		return isBackToBack;
	}

	public boolean isIPO() {
		return getBookId() != null && getBookId().startsWith("NE");
	}

	public double getBonification() {
		return bonification;
	}
	
	public void setBonification(double bonification) {
		this.bonification = bonification;
	}
	
	/**
	 * Returns true because Summit bond delivers a bloomberg id.
	 */
	@Override
    public boolean hasBloombergTradeId() {
		return true;
	}
	
	@Override
    public boolean hasBonification() {
		return true;
	}
	
	/**
	 * Returns the bloomberg id that has been delivered by the
	 * front office system. The  default implementation in this class returns
	 * null. This method should be overwritten in subclasses
	 * if appropriate.
	 */
	@Override
    public String getCBloombergTradeId() {
		return instrumentName;
	}
	
	@Override
    public double getCBonification() {
		return getBonification();
	}

	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	
	public boolean isBackToBackCheck() {
		return isBackToBackCheck;
	}
	public boolean getIsBackToBackCheck() {
		return isBackToBackCheck;
	}
	public void setIsBackToBackCheck(boolean isBackToBackCheck) {
		this.isBackToBackCheck = isBackToBackCheck;
	}

    
    public String getMarketer() {
        return marketer;
    }

    
    public void setMarketer(String marketer) {
        this.marketer = marketer;
    }	

}

