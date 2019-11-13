/*
 * Created on 26.06.2009
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl;

import java.lang.reflect.Field;
import java.util.Calendar;


public class LoadSummitMoneyMarketImpl extends DataLoadImpl {

    private String tradeId;
    private int tradeVersion;
    private String status;
    private String ccy;
    private String type;
    private String subType;
    private String index;
    private Calendar startDate;
    private Calendar maturityDate;
    private Calendar tradeDate;
    private Calendar tradeEntryDate;
    private Calendar amendDate;
    private double internalRate;
    private double spread;
    private double marginPoints;
    private double dealtRate;
    private double marketRate1;
    private double marketRate2;
    private double notional;
    private String traderLoginID;
    private String traderName;
    private String counterparty;
    private String book;
    private String comment;
    private String structureID;
    private double eodRate;
    private Calendar eodRateDate;
    private String autoRateReset;
    private double eodRateMM;
    
    @Override
    public String toString() {
        Field[] fields = this.getClass().getDeclaredFields();
        StringBuffer s = new StringBuffer(this.getClass().getName() + ": ");
        for (int i = 0; i < fields.length; i++) {
            try {
                s.append("[" + fields[i].getName() + ": " + fields[i].get(this) + "] ");
            } catch (IllegalAccessException e) {
            }
        }
        return s.toString();
    }

    
    public String getTradeId() {
        return tradeId;
    }

    
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
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

    
    public String getCcy() {
        return ccy;
    }

    
    public void setCcy(String ccy) {
        this.ccy = ccy;
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

    
    public Calendar getStartDate() {
        return startDate;
    }

    
    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    
    public Calendar getMaturityDate() {
        return maturityDate;
    }

    
    public void setMaturityDate(Calendar maturityDate) {
        this.maturityDate = maturityDate;
    }

    
    public Calendar getTradeDate() {
        return tradeDate;
    }

    
    public void setTradeDate(Calendar tradeDate) {
        this.tradeDate = tradeDate;
    }

    
    public Calendar getTradeEntryDate() {
        return tradeEntryDate;
    }

    
    public void setTradeEntryDate(Calendar tradeEntryDate) {
        this.tradeEntryDate = tradeEntryDate;
    }

    
    public Calendar getAmendDate() {
        return amendDate;
    }

    
    public void setAmendDate(Calendar amendDate) {
        this.amendDate = amendDate;
    }

    
    public double getInternalRate() {
        return internalRate;
    }

    
    public void setInternalRate(double internalRate) {
        this.internalRate = internalRate;
    }

    
    public double getSpread() {
        return spread;
    }

    
    public void setSpread(double spread) {
        this.spread = spread;
    }
  
    
    public double getMarginPoints() {
        return marginPoints;
    }

    
    public void setMarginPoints(double marginPoints) {
        this.marginPoints = marginPoints;
    }

    public double getDealtRate() {
        return dealtRate;
    }

    
    public void setDealtRate(double dealtRate) {
        this.dealtRate = dealtRate;
    }

    
    public double getMarketRate1() {
        return marketRate1;
    }

    
    public void setMarketRate1(double marketRate1) {
        this.marketRate1 = marketRate1;
    }

    
    public double getMarketRate2() {
        return marketRate2;
    }

    
    public void setMarketRate2(double marketRate2) {
        this.marketRate2 = marketRate2;
    }

    
    public double getNotional() {
        return notional;
    }

    
    public void setNotional(double notional) {
        this.notional = notional;
    }

    
    public String getTraderLoginID() {
        return traderLoginID;
    }

    
    public void setTraderLoginID(String traderLoginID) {
        this.traderLoginID = traderLoginID;
    }

    
    public String getTraderName() {
        return traderName;
    }

    
    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    
    public String getCounterparty() {
        return counterparty;
    }

    
    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }

    
    public String getBook() {
        return book;
    }

    
    public void setBook(String book) {
        this.book = book;
    }

    
    public String getComment() {
        return comment;
    }

    
    public void setComment(String comment) {
        this.comment = comment;
    }

    
    public String getStructureID() {
        return structureID;
    }

    
    public void setStructureID(String structureID) {
        this.structureID = structureID;
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

    
    public String getAutoRateReset() {
        return autoRateReset;
    }

    
    public void setAutoRateReset(String autoRateReset) {
        this.autoRateReset = autoRateReset;
    }

    
    public double getEodRateMM() {
        return eodRateMM;
    }

    
    public void setEodRateMM(double eodRateMM) {
        this.eodRateMM = eodRateMM;
    }

}
