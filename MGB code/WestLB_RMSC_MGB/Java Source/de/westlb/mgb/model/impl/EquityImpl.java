package de.westlb.mgb.model.impl;

import java.util.Iterator;

import de.westlb.mgb.client.server.vo.PriceVo;
import de.westlb.mgb.model.definition.MarketDataRequestTypeDef;

/**
 * 
 * @modelguid {12186F82-4144-437F-8BBE-83C41528764F}
 */
public abstract class EquityImpl extends TradeImpl {

    private static final long serialVersionUID = 6210056891873272558L;

    private String sourceSystemExchange;

	private double bagatelleLimit;
	private double turnoverLimit;

	private ExchangeMappingEntryPrioImpl exchangeMappingPriority;
	private boolean isBuy;
	private boolean isInternalDeal;
	private boolean isNet;
	private boolean isOtc;
	private boolean isStorno;
	private boolean isWarrant;
	private boolean isForeignExchange;
	private boolean isBuySellIdentical;
    private double fxRate;
    private double volumeMultiplicator;

	public abstract boolean isAutomatic();
	public abstract boolean isNonExchangeTraded();
	public abstract boolean isInternalInstrument();
	public abstract boolean hasNoIsin();

    @Override
    public double getFrontOfficeMarketRate() {
        return 0.0d;
    }

	@Override
    public double calculateTurnover(double marketPrice) {
		return calculateTurnover(marketPrice, getPrice(), getVolume()*getVolumeMultiplicator(), getFxRate());
	}

	public static double calculateTurnover(double marketPrice, double tradePrice, double volume, double fxRate) {
		return (tradePrice - marketPrice) * volume * fxRate;
	}

	public boolean isOutOfTurnoverLimit() {
		Iterator<RequestImpl> requests = getExternalRequests().iterator();
		while (requests.hasNext()) {
			RequestImpl request = requests.next();
			if (request != null) {
			    PriceVo autoCheckPrice = getAutoCheckPrice(request.getLongId());
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

	public boolean isBagatelle() {
		Iterator<RequestImpl> requests = getExternalRequests().iterator();
		while (requests.hasNext()) {
			RequestImpl request = requests.next();
			if (request != null) {
			    PriceVo autoCheckPrice = getAutoCheckPrice(request.getLongId());
				if (autoCheckPrice != null) {
					boolean isBagatelle = Math.abs(calculateTurnover(autoCheckPrice.getMidPrice())) < getBagatelleLimit();
					if (isBagatelle) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isEuwaxTimeOut() {
	    return isRequestTimeOut(MarketDataRequestTypeDef.HIGH_LOW_WEB_REQUEST) || isRequestTimeOut(MarketDataRequestTypeDef.HISTORIC_WEB_REQUEST);
	}

	/**
	 * @return Returns the isBuy.
	 */
	public boolean isBuy() {
		return isBuy;
	}

	public boolean getIsBuy() {
		return isBuy;
	}
/**
	 * @param isBuy The isBuy to set.
	 */
	public void setIsBuy(boolean isBuy) {
		this.isBuy = isBuy;
	}
	/**
	 * @return Returns the isBuySellIdentical.
	 */
	public boolean isBuySellIdentical() {
		return isBuySellIdentical;
	}
	public boolean getIsBuySellIdentical() {
		return isBuySellIdentical;
	}
	/**
	 * @param isBuySellIdentical The isBuySellIdentical to set.
	 */
	public void setIsBuySellIdentical(boolean isBuySellIdentical) {
		this.isBuySellIdentical = isBuySellIdentical;
	}
	/**
	 * @return Returns the isForeignExchange.
	 */
	public boolean isForeignExchange() {
		return isForeignExchange;
	}
	public boolean getIsForeignExchange() {
		return isForeignExchange;
	}
	/**
	 * @param isForeignExchange The isForeignExchange to set.
	 */
	public void setIsForeignExchange(boolean isForeignExchange) {
		this.isForeignExchange = isForeignExchange;
	}
	/**
	 * @return Returns the isInternalDeal.
	 */
	@Override
    public boolean isInternalDeal() {
		return isInternalDeal;
	}
	public boolean getIsInternalDeal() {
		return isInternalDeal;
	}
	/**
	 * @param isInternalDeal The isInternalDeal to set.
	 */
	public void setIsInternalDeal(boolean isInternalDeal) {
		this.isInternalDeal = isInternalDeal;
	}
	/**
	 * @return Returns the isNet.
	 */
	public boolean isNet() {
		return isNet;
	}
	public boolean getIsNet() {
		return isNet;
	}
	/**
	 * @param isNet The isNet to set.
	 */
	public void setIsNet(boolean isNet) {
		this.isNet = isNet;
	}
	/**
	 * @return Returns the isOtc.
	 */
	public boolean isOtc() {
		return isOtc;
	}
	public boolean getIsOtc() {
		return isOtc;
	}
	/**
	 * @param isOtc The isOtc to set.
	 */
	public void setIsOtc(boolean isOtc) {
		this.isOtc = isOtc;
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
	/**
	 * @param isStorno The isStorno to set.
	 */
	public void setIsStorno(boolean isStorno) {
		this.isStorno = isStorno;
	}
	/**
	 * @return Returns the isWarrant.
	 */
	public boolean isWarrant() {
		return isWarrant;
	}
	public boolean getIsWarrant() {
		return isWarrant;
	}
	/**
	 * @param isWarrant The isWarrant to set.
	 */
	public void setIsWarrant(boolean isWarrant) {
		this.isWarrant = isWarrant;
	}

	/**
	 * @return Returns the sourceSystemExchange.
	 */
	public String getSourceSystemExchange() {
		return sourceSystemExchange;
	}
	/**
	 * @param sourceSystemExchange The sourceSystemExchange to set.
	 */
	public void setSourceSystemExchange(String sourceSystemExchange) {
		this.sourceSystemExchange = sourceSystemExchange;
	}
	/**
	 * @return Returns the exchangeMappingPriority.
	 */
	public ExchangeMappingEntryPrioImpl getExchangeMappingPriority() {
		return exchangeMappingPriority;
	}
	/**
	 * @param exchangeMappingPriority The exchangeMappingPriority to set.
	 */
	public void setExchangeMappingPriority(
			ExchangeMappingEntryPrioImpl exchangeMappingPriority) {
		this.exchangeMappingPriority = exchangeMappingPriority;
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
    public void setFxRate(double fxRate) {
        this.fxRate = fxRate;
    }
    public double getFxRate() {
        return fxRate;
    }
    public void setVolumeMultiplicator(double volumeMultiplicator) {
        this.volumeMultiplicator = volumeMultiplicator;
    }
    public double getVolumeMultiplicator() {
        return volumeMultiplicator;
    }
}

