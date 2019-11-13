/*
 * Created on 26.06.2009
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl;

import java.lang.reflect.Field;
import java.util.Calendar;


public class LoadSummitForeignExchangeImpl extends DataLoadImpl {

    private String tradeId;
    private int tradeVersion;
    private String status;
    private String ccyPair;
    private String type;
    private String subType;
    private Calendar nearDate;
    private Calendar farDate;
    private Calendar tradeDate;
    private Calendar tradeEntryDate;
    private Calendar amendDate;
    private double spotRate;
    private double fwdPointsNearLeg;
    private double fwdPointsFarLeg;
    private double marginPoints;
    private double marginRate;
    private double nearAmount;
    private double farAmount;
    private double marketRateSpot;
    private double marketPointsNearLeg;
    private double marketPointsFarLeg;
    private double marketRateFwd;
    private String traderLoginID;
    private String traderName;
    private String counterparty;
    private String book;
    private String comment;
    private double eodRate;
    private Calendar eodRateDate;
    private double eodRateFX;

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

    
    public String getCcyPair() {
        return ccyPair;
    }

    
    public void setCcyPair(String ccyPair) {
        this.ccyPair = ccyPair;
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

    
    public Calendar getNearDate() {
        return nearDate;
    }

    
    public void setNearDate(Calendar nearDate) {
        this.nearDate = nearDate;
    }

    
    public Calendar getFarDate() {
        return farDate;
    }

    
    public void setFarDate(Calendar farDate) {
        this.farDate = farDate;
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

    
    public double getMarginRate() {
        return marginRate;
    }

    
    public void setMarginRate(double marginRate) {
        this.marginRate = marginRate;
    }
    
    
    public double getNearAmount() {
        return nearAmount;
    }

    
    public void setNearAmount(double nearAmount) {
        this.nearAmount = nearAmount;
    }

    
    public double getFarAmount() {
        return farAmount;
    }

    
    public void setFarAmount(double farAmount) {
        this.farAmount = farAmount;
    }

    public double getMarketRateSpot() {
        return marketRateSpot;
    }

    
    public void setMarketRateSpot(double marketRateSpot) {
        this.marketRateSpot = marketRateSpot;
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

    
    public double getMarketRateFwd() {
        return marketRateFwd;
    }

    
    public void setMarketRateFwd(double marketRateFwd) {
        this.marketRateFwd = marketRateFwd;
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

    
    public double getEodRateFX() {
        return eodRateFX;
    }

    
    public void setEodRateFX(double eodRateFX) {
        this.eodRateFX = eodRateFX;
    }

}
