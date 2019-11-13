/*
 * Created on 22.06.2007
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.ui.util.DateFormat;

/**
 * @author D055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SummitDerivativeImpl extends TradeImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1221570045934370893L;
    protected DateFormat format = new DateFormat(DateFormat.DATE_FORMAT);
    static Logger logger = Logger.getLogger(SummitDerivativeImpl.class);

    public static final String WMBINT = "WMBINT";

	private Calendar asOfDate;
	private String tradeType;
	private String tradeStatus;
	private int version;
	private String structureId;
	private Calendar doneDate;
	private Calendar verifyDate;
	private Calendar cancelDate;
	private String comment;
	private String internalExternal;
	private String desk;
	private String company;
	private String auditCurrent;

	private String exercised;
	private String amendUser;
	private String auditUser;
	private String termAssignStatus;
	private Calendar termTradeDate;
	private Calendar termInputDate;
	private Calendar termEffDate;
    private String resetRemark;
    private String customerType;
	
	private double vega;
	private double delta;
	private double premium;
	
	private Set<AssetImpl> assets;

	private double deviation;
	private double turnover;

    private double amendmentNpv;
    private double amendmentNpvChange;

	private boolean isBagatelle = false;
	private boolean isHighTurnover = false;
	private boolean isStandard = true;
	private boolean isMccRelevantChange = true;
		

    public SummitDerivativeImpl() {
	}

    public SummitDerivativeImpl(SourceSystemImpl sourceSystemImpl) {
		setSourceSystem(sourceSystemImpl);
	}

	public SummitDerivativeImpl(LoadSummitDerivativeImpl summitData, SourceSystemImpl sourceSystemImpl) {
		setSourceSystem(sourceSystemImpl);
		convert(summitData);
	}

	public void convert(LoadSummitDerivativeImpl loaderObject) {
		setAsOfDate(lonToDusCalendar(loaderObject.getAsOfDate()));
		setTradeType(loaderObject.getTradeType());
		setTradeStatus(loaderObject.getTradeStatus());
		setCurrency(loaderObject.getTradeCurrency());
		setTradeId(loaderObject.getVersionedTradeId());
		setSourceSystemReutersId(loaderObject.getTradeId());
        setTradeGroupId(null);
        setIsTradeGroup(false);
		setVersion(loaderObject.getVersion());
		setStructureId(loaderObject.getStructureId());
		setDoneDate(lonToDusCalendar(loaderObject.getDoneDate()));
		setVerifyDate(lonToDusCalendar(loaderObject.getVerifyDate()));
		setAmendedDate(lonToDusCalendar(loaderObject.getAmendDate()));
		setCancelDate(lonToDusCalendar(loaderObject.getCancelDate()));
		setComment(loaderObject.getComment());
		if (loaderObject.getComment2() != null) {
			setComment(getComment()+loaderObject.getComment2());
		}
		setCounterpartyId(loaderObject.getCounterparty());
		if ("Internal".equalsIgnoreCase(loaderObject.getInternalExternal())) {
			setInternalExternal("I");
		} else {
			setInternalExternal("E");
		}
		setBookId(loaderObject.getBook());
		setDesk(loaderObject.getDesk());
		setCompany(loaderObject.getCompany());
		setTraderId(loaderObject.getTrader());
		setTraderName(loaderObject.getTrader());
		setAuditCurrent(loaderObject.getAuditCurrent());
		setTradeDate(lonToDusCalendar(loaderObject.getTradeDate()));
		setSystemDate(lonToDusCalendar(loaderObject.getAuditDate()));
		setExercised(loaderObject.getExercised());
		setAmendUser(loaderObject.getAmendUser());
		setAuditUser(loaderObject.getAuditUser());
		setTermAssignStatus(loaderObject.getTermAssignStatus());
		setTermEffDate(lonToDusCalendar(loaderObject.getTermEffDate()));
		setTermTradeDate(lonToDusCalendar(loaderObject.getTermTradeDate()));
		setTermInputDate(lonToDusCalendar(loaderObject.getTermInputDate()));
		setResetRemark(loaderObject.getResetRemark());
        setIsMccRelevantChange(true);
        
		setSettlementDate(lonToDusCalendar(loaderObject.getStartDate()));
	}

    @Override
    public double getFrontOfficeMarketRate() {
        return 0.0;
    }

    @Override
    public String getTradeCategory() {
        return getTradeType();
    }

	public Collection<AssetImpl> getAssets() {
		return assets;
	}
	public void setAssets(Set<AssetImpl> assets) {
// Assets are not set as a set. They are set by the addAsset method.
//		addAssetData(null);
//		if (assets != null) {
//			for (Iterator iter = assets.iterator(); iter.hasNext();) {
//				AssetImpl asset = (AssetImpl) iter.next();
//				addAssetData(asset);
//			}
//		}
		this.assets = assets;
	}
	
	public boolean addAsset(AssetImpl asset) {
		asset.setTrade(this);
		if (assets != null && assets.size() > 0) {
			addAssetData(asset);
			return assets.add(asset);
		}
        assets = new TreeSet<AssetImpl>();
        addAssetData(asset);
        return assets.add(asset);
	}

	private void addAssetData(AssetImpl asset) {
		if (asset == null) {
			setDelta(0d);
			setVega(0d);
			setPremium(0d);
			setPrice(0d);
			setVolume(0d);
			setIsStandard(true);
		} else {
			setDelta(getDelta()+(asset.getDelta()*asset.getFxRate()));
			setVega(getVega()+(asset.getVega()*asset.getFxRate()));
			setPremium(getPremium()+(asset.getPremiumAmount()*asset.getFxRate()));
			setPrice(getPrice()+(asset.getNpv()*asset.getFxRate()));
			if (Math.abs(asset.getQuantity()*asset.getFxRate()) > Math.abs(getVolume())) {
				setVolume(asset.getQuantity()*asset.getFxRate());
			}
			if (asset.getProductName() != null && asset.getProductName().length() > 0) {
				setIsStandard(false);
			}
		}
	}

	@SuppressWarnings("unused")
    private boolean removeAsset(AssetImpl asset) {
		if (assets != null && !assets.isEmpty()) {
			return assets.remove(asset);
		}
        return false;
	}

	public AssetImpl getSingleAsset() {
		if (getAssets() != null && !getAssets().isEmpty()) {
			return getAssets().iterator().next();
		}
		return null;
	}
	
	public String getTradeSubType() {
		if (getSingleAsset() != null) {
			String subType = getSingleAsset().getTradeSubType();
			if (subType != null) {
				return subType;
			}
		}
		return "NONE";
	}

	public String getAssetPayCurrencys() {
	    StringBuffer sb = new StringBuffer(); 
	    for (Iterator<AssetImpl> iterator = getAssets().iterator(); iterator.hasNext();) {
	        sb.append(iterator.next().getPayCurrency()).append(" ");
	    }
	    return sb.toString();
	}
	
    public String getAssetReceiveCurrencys() {
        StringBuffer sb = new StringBuffer(); 
        for (Iterator<AssetImpl> iterator = getAssets().iterator(); iterator.hasNext();) {
            sb.append(iterator.next().getReceiveCurrency()).append(" ");
        }
        return sb.toString();
    }

    public String getAssetStartDates() {
        StringBuffer sb = new StringBuffer(); 
        for (Iterator<AssetImpl> iterator = getAssets().iterator(); iterator.hasNext();) {
            sb.append(format.format(iterator.next().getStartDate())).append(" ");
        }
        return sb.toString();
    }

    public String getAssetMaturityDates() {
        StringBuffer sb = new StringBuffer(); 
        for (Iterator<AssetImpl> iterator = getAssets().iterator(); iterator.hasNext();) {
            sb.append(format.format(iterator.next().getMaturityDate())).append(" ");
        }
        return sb.toString();
    }

    public String getAssetPayIndexBasis() {
        StringBuffer sb = new StringBuffer(); 
        for (Iterator<AssetImpl> iterator = getAssets().iterator(); iterator.hasNext();) {
            sb.append(iterator.next().getPayIndexBasis()).append(" ");
        }
        return sb.toString();
    }

    public String getAssetReceiveIndexBasis() {
        StringBuffer sb = new StringBuffer(); 
        for (Iterator<AssetImpl> iterator = getAssets().iterator(); iterator.hasNext();) {
            sb.append(iterator.next().getReceiveIndexBasis()).append(" ");
        }
        return sb.toString();
    }

    public String getAssetProductNames() {
        StringBuffer sb = new StringBuffer(); 
        for (Iterator<AssetImpl> iterator = getAssets().iterator(); iterator.hasNext();) {
            sb.append(iterator.next().getProductName()).append(" ");
        }
        return sb.toString();
    }

    @Override
    public boolean isInternalDeal() {
        return WMBINT.equals(customerType);
    }
    
	public boolean isIrg() {
		return "IRG".equalsIgnoreCase(getTradeType());
	}

	public boolean isSwap() {
		return "SWAP".equalsIgnoreCase(getTradeType());
	}

	public boolean isFra() {
		return "FRA".equalsIgnoreCase(getTradeType());
	}

	public boolean isSwoption() {
		return "SWOPT".equalsIgnoreCase(getTradeType());
	}

	public boolean isFxoption() {
		return "FXOPT".equalsIgnoreCase(getTradeType());
	}

    public boolean isBondoption() {
        return "BONDOP".equalsIgnoreCase(getTradeType());
    }

    public boolean isExotic() {
		return "EXOTIC".equalsIgnoreCase(getTradeType());
	}

	public boolean isDone() {
		return "DONE".equalsIgnoreCase(getTradeStatus());
	}

	public boolean isCancelled() {
		return "CANC".equalsIgnoreCase(getTradeStatus());
	}

	public boolean isVerified() {
		return "VER".equalsIgnoreCase(getTradeStatus());
	}

	public boolean isMatured() {
		return "MAT".equalsIgnoreCase(getTradeStatus());
	}

	public boolean isEarlyTermination() {
		return "TERM".equalsIgnoreCase(getTermAssignStatus());
	}

	public boolean isOptionExpired() {
		return "EXP".equalsIgnoreCase(getExercised());
	}

	public boolean isOptionExercised() {
		return "EXER".equalsIgnoreCase(getExercised());
	}

    public boolean isStandard() {
		return isStandard;
	}

	public boolean isBagatelle() {
		return isBagatelle;
	}

	public boolean isHighTurnover() {
		return isHighTurnover;
	}

    public boolean isRateReset() {
        return "RateReset".equalsIgnoreCase(getResetRemark());
    }

    public boolean isOutOfPrice() {
		if (getInstrument() != null && getInstrument().getPriceCheckCategory() != null) {
			if (getInstrument().getPriceCheckCategory().getPriceToleranceAbsolute() != 0) {
				logger.debug("deviation in bp: " + getDeviation() + "; absolute tolerance: " + getInstrument().getPriceCheckCategory().getPriceToleranceAbsolute());
				return Math.abs(getDeviation()) > getInstrument().getPriceCheckCategory().getPriceToleranceAbsolute();
			}
            logger.debug("deviation in npv ratio: " + getDeviation() + "; relative tolerance: " + getInstrument().getPriceCheckCategory().getPriceTolerancePercent());
            return Math.abs(getDeviation()) > getInstrument().getPriceCheckCategory().getPriceTolerancePercent();
		}
        logger.warn("No price check category set for trade "+ getTradeId());
        return true;
	}

	public boolean isMccRelevantChange() {
		return isMccRelevantChange;
	}
	
	public boolean getIsStandard() {
		return isStandard;
	}
	public void setIsStandard(boolean isStandard) {
		this.isStandard = isStandard;
	}
	public double getDelta() {
		return delta;
	}
	public void setDelta(double delta) {
		this.delta = delta;
	}
	public double getPremium() {
		return premium;
	}
	public void setPremium(double premium) {
		this.premium = premium;
	}
	public double getVega() {
		return vega;
	}
	public void setVega(double vega) {
		this.vega = vega;
	}
	public Calendar getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Calendar asOfDate) {
		this.asOfDate = asOfDate;
	}
	public String getAuditCurrent() {
		return auditCurrent;
	}
	public void setAuditCurrent(String auditCurrent) {
		this.auditCurrent = auditCurrent;
	}
	public Calendar getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Calendar cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDesk() {
		return desk;
	}
	public void setDesk(String desk) {
		this.desk = desk;
	}
	public Calendar getDoneDate() {
		return doneDate;
	}
	public void setDoneDate(Calendar doneDate) {
		this.doneDate = doneDate;
	}
	public String getInternalExternal() {
		return internalExternal;
	}
	public void setInternalExternal(String internalExternal) {
		this.internalExternal = internalExternal;
	}
	public String getStructureId() {
		return structureId;
	}
	public void setStructureId(String structureId) {
		this.structureId = structureId;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public Calendar getVerifyDate() {
		return verifyDate;
	}
	public void setVerifyDate(Calendar verifyDate) {
		this.verifyDate = verifyDate;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public double getDeviation() {
		return deviation;
	}
	public void setDeviation(double deviation) {
		this.deviation = deviation;
	}
	public boolean getIsBagatelle() {
		return isBagatelle;
	}
	public void setIsBagatelle(boolean isBagatelle) {
		this.isBagatelle = isBagatelle;
	}
	public boolean getIsHighTurnover() {
		return isHighTurnover;
	}
	public void setIsHighTurnover(boolean isHighTurnover) {
		this.isHighTurnover = isHighTurnover;
	}
	public double getTurnover() {
		return turnover;
	}
	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}
	public String getAmendUser() {
		return amendUser;
	}
	public void setAmendUser(String amendUser) {
		this.amendUser = amendUser;
	}
	public String getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	public String getExercised() {
		return exercised;
	}
	public void setExercised(String exercised) {
		this.exercised = exercised;
	}
	public String getTermAssignStatus() {
		return termAssignStatus;
	}
	public void setTermAssignStatus(String termAssignStatus) {
		this.termAssignStatus = termAssignStatus;
	}
	public Calendar getTermEffDate() {
		return termEffDate;
	}
	public void setTermEffDate(Calendar termEffDate) {
		this.termEffDate = termEffDate;
	}
	public Calendar getTermInputDate() {
		return termInputDate;
	}
	public void setTermInputDate(Calendar termInputDate) {
		this.termInputDate = termInputDate;
	}
	public Calendar getTermTradeDate() {
		return termTradeDate;
	}
	public void setTermTradeDate(Calendar termTradeDate) {
		this.termTradeDate = termTradeDate;
	}
	public boolean getIsMccRelevantChange() {
		return isMccRelevantChange;
	}
	public void setIsMccRelevantChange(boolean isMccRelevantChange) {
		this.isMccRelevantChange = isMccRelevantChange;
	}
    
    public String getResetRemark() {
        return resetRemark;
    }
    
    public void setResetRemark(String resetRemark) {
        this.resetRemark = resetRemark;
    }

    public double getAmendmentNpv() {
        return amendmentNpv;
    }
    
    public void setAmendmentNpv(double amendmentNpv) {
        this.amendmentNpv = amendmentNpv;
    }
    
    public double getAmendmentNpvChange() {
        return amendmentNpvChange;
    }
    
    public void setAmendmentNpvChange(double amendmentNpvChange) {
        this.amendmentNpvChange = amendmentNpvChange;
    }
    
    public boolean isNpvAmended() {
        return getAmendmentNpv() != 0d;
    }
    
    public boolean isNewTrade()
    {
        return getTradeGroupId() == null;
    }
    
    /* Eh -- isn't this a Hibernate-operated getter? So after the first
     * write-back we'll have amendment values in the DB, not the original ones!
     * That was not what was intended (as JR told me now), however
     * it shouldn't matter much. For now we don't need to retain the original
     * price in amended trades. -- RS 2014-10-20 */
    @Override
    public double getPrice() {
        if (isNpvAmended()) {
            return getAmendmentNpv();
        }
        return super.getPrice();
    }
    
	public boolean isCdsTrade() {
		return "CDS".equals(getTradeSubType());
	}

}