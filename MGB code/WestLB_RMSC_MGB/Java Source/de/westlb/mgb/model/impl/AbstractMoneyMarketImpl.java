/*
 * Created on 26.06.2009
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl;

import org.apache.log4j.Logger;


public abstract class AbstractMoneyMarketImpl extends TradeImpl {

    static Logger logger = Logger.getLogger(AbstractMoneyMarketImpl.class);

    private static final long serialVersionUID = -3892053326674972526L;

    public abstract double getReutersMarketRate();

    public abstract double getEodMarketRate();

    @Override
    public double getFrontOfficeMarketRate() {
        if (getReutersMarketRate() == 0.0) {
            return getEodMarketRate();
        }
        return getReutersMarketRate();
    }

    public boolean hasNoReutersPrice() {
        return getReutersMarketRate() == 0.0d;
    }

    public boolean hasNoEodPrice() {
        return getEodMarketRate() == 0.0d;
    }

    public boolean hasZeroPrice() {
        return getPrice() == 0.0d;
    }

    public boolean isOutOfReutersPrice() {
        return isOutOfPrice(getReutersMarketRate(),getPrice());
    }
    
    public boolean isOutOfEodPrice() {
        return isOutOfPrice(getEodMarketRate(),getPrice());
    }

    protected boolean isOutOfPrice(double price,double tradePrice) {
        if (getInstrument() != null && getInstrument().getPriceCheckCategory() != null) {
            double minTheshold;
            double maxTheshold;
            if (getInstrument().getPriceCheckCategory().getPriceToleranceAbsolute() != 0) {
                minTheshold = price - getInstrument().getPriceCheckCategory().getPriceToleranceAbsolute();
                maxTheshold = price + getInstrument().getPriceCheckCategory().getPriceToleranceAbsolute();
                logger.debug("price: " + getPrice() + "; minTheshold: " + minTheshold + "; maxTheshold: " + maxTheshold + " (absolute)");          
            } else {
                minTheshold = price * (1 - getInstrument().getPriceCheckCategory().getPriceTolerancePercent() * AS_PER_CENT);
                maxTheshold = price * (1 + getInstrument().getPriceCheckCategory().getPriceTolerancePercent() * AS_PER_CENT);
                logger.debug("price: " + getPrice() + "; minTheshold: " + minTheshold + "; maxTheshold: " + maxTheshold+ " (relative)");
            }
            return minTheshold > tradePrice || tradePrice > maxTheshold;

        }
        logger.warn("using default result");
        return true;
    }

}
