/*
 * Created on 22.06.2007
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl;

import java.lang.reflect.Field;
import java.util.Calendar;

/**
 * @author D055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class LoadSummitDerivativeImpl extends DataLoadImpl {

	private Calendar asOfDate;
	private String assetId;
	private String tradeType;
	private String tradeSubType;
	private String tradeId;
	private String dealId;
	private int version;
	private String auditCurrent;
	private String structureId;
	private String tradeStatus;
	private String tradeCurrency;
	private String callPut;
	private String payCurrency;
	private double payNotional;
	private String receiveCurrency;
	private double receiveNotional;
	private Calendar startDate;
	private Calendar maturityDate;
	private String payIndexBasis;
	private double payRateSpread;
	private String payDiscountCurve;
	private String receiveIndexBasis;
	private double receiveRateSpread;
	private String receiveDiscountCurve;
	private String capFloor;
	private String buySell;
	private double quantity;
	private String underlying;
	private double strike;
	private String style;
	private double premiumAmount;
	private String premiumCurrency;
	private Calendar expiryDate;
	private Calendar fixingDate;
	private Calendar paymentDate;
	private Calendar firstCouponDate;
	private String model;
	private String instrumentStyle;
	private String formula;
	private String productName;
	private String prodData;
	private double npv;
	private double marketRateIR;
	private double marketRateVola;
	private double fxRate;
	private double delta;
	private String deltaCurrency;
	private double vega;
	private String vegaCurrency;
	private String exercised;
	private Calendar doneDate;
	private Calendar verifyDate;
	private Calendar amendDate;
	private Calendar cancelDate;
	private String comment;
	private String comment2;
	private String counterparty;
	private String internalExternal;
	private String book;
	private String desk;
	private String company;
	private String trader;
	private Calendar tradeDate;	
	private String amendUser;
	private Calendar auditDate;
	private String auditUser;
	private String termAssignStatus;
	private Calendar termTradeDate;
	private Calendar termInputDate;
	private Calendar termEffDate;
    private String resetRemark;


	@Override
    public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer s = new StringBuffer(this.getClass().getName() + ": ");
		for (int i = 0; i < fields.length; i++) {
			try {
				s.append(
					"["
						+ fields[i].getName()
						+ ": "
						+ fields[i].get(this)
						+ "] ");
			} catch (IllegalAccessException e) {
			}
		}
		return s.toString();
	}

	public String getVersionedTradeId() {
		return getTradeId()+"_"+getVersion();
	}

	public Calendar getAmendDate() {
		return amendDate;
	}
	public void setAmendDate(Calendar amendDate) {
		this.amendDate = amendDate;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getBuySell() {
		return buySell;
	}
	public void setBuySell(String buySell) {
		this.buySell = buySell;
	}
	public String getCallPut() {
		return callPut;
	}
	public void setCallPut(String callPut) {
		this.callPut = callPut;
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
	public String getComment2() {
		return comment2;
	}
	public void setComment2(String comment2) {
		this.comment2 = comment2;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCounterparty() {
		return counterparty;
	}
	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}
	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealID) {
		this.dealId = dealID;
	}
	public double getDelta() {
		return delta;
	}
	public void setDelta(double delta) {
		this.delta = delta;
	}
	public String getDeltaCurrency() {
		return deltaCurrency;
	}
	public void setDeltaCurrency(String deltaCurrency) {
		this.deltaCurrency = deltaCurrency;
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
	public Calendar getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Calendar expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public double getFxRate() {
		return fxRate;
	}
	public void setFxRate(double fxRate) {
		this.fxRate = fxRate;
	}
	public String getInternalExternal() {
		return internalExternal;
	}
	public void setInternalExternal(String internalExternal) {
		this.internalExternal = internalExternal;
	}
	public double getMarketRateIR() {
		return marketRateIR;
	}
	public void setMarketRateIR(double marketRateIR) {
		this.marketRateIR = marketRateIR;
	}
	public double getMarketRateVola() {
		return marketRateVola;
	}
	public void setMarketRateVola(double marketRateVola) {
		this.marketRateVola = marketRateVola;
	}
	public Calendar getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(Calendar maturityDate) {
		this.maturityDate = maturityDate;
	}
	public double getPayNotional() {
		return payNotional;
	}
	public void setPayNotional(double notionalPay) {
		this.payNotional = notionalPay;
	}
	public double getReceiveNotional() {
		return receiveNotional;
	}
	public void setReceiveNotional(double notionalReceive) {
		this.receiveNotional = notionalReceive;
	}
	public double getNpv() {
		return npv;
	}
	public void setNpv(double npv) {
		this.npv = npv;
	}
	public String getPayCurrency() {
		return payCurrency;
	}
	public void setPayCurrency(String payCurrency) {
		this.payCurrency = payCurrency;
	}
	public String getPayIndexBasis() {
		return payIndexBasis;
	}
	public void setPayIndexBasis(String payIndexBasis) {
		this.payIndexBasis = payIndexBasis;
	}
	public double getPayRateSpread() {
		return payRateSpread;
	}
	public void setPayRateSpread(double payRateSpread) {
		this.payRateSpread = payRateSpread;
	}
	public double getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	public String getPremiumCurrency() {
		return premiumCurrency;
	}
	public void setPremiumCurrency(String premiumCurency) {
		this.premiumCurrency = premiumCurency;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getReceiveCurrency() {
		return receiveCurrency;
	}
	public void setReceiveCurrency(String receiveCurrency) {
		this.receiveCurrency = receiveCurrency;
	}
	public String getReceiveIndexBasis() {
		return receiveIndexBasis;
	}
	public void setReceiveIndexBasis(String receiveIndexBasis) {
		this.receiveIndexBasis = receiveIndexBasis;
	}
	public double getReceiveRateSpread() {
		return receiveRateSpread;
	}
	public void setReceiveRateSpread(double receiveRateSpread) {
		this.receiveRateSpread = receiveRateSpread;
	}
	public Calendar getStartDate() {
		return startDate;
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	public double getStrike() {
		return strike;
	}
	public void setStrike(double strike) {
		this.strike = strike;
	}
	public String getStructureId() {
		return structureId;
	}
	public void setStructureId(String structureID) {
		this.structureId = structureID;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeID) {
		this.tradeId = tradeID;
	}
	public String getTrader() {
		return trader;
	}
	public void setTrader(String trader) {
		this.trader = trader;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public String getTradeSubType() {
		return tradeSubType;
	}
	public void setTradeSubType(String tradeSubType) {
		this.tradeSubType = tradeSubType;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public double getVega() {
		return vega;
	}
	public void setVega(double vega) {
		this.vega = vega;
	}
	public String getVegaCurrency() {
		return vegaCurrency;
	}
	public void setVegaCurrency(String vegaCurrency) {
		this.vegaCurrency = vegaCurrency;
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
	public Calendar getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Calendar asOfDate) {
		this.asOfDate = asOfDate;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getAuditCurrent() {
		return auditCurrent;
	}
	public void setAuditCurrent(String auditCurrent) {
		this.auditCurrent = auditCurrent;
	}
	public String getCapFloor() {
		return capFloor;
	}
	public void setCapFloor(String capFloor) {
		this.capFloor = capFloor;
	}
	public Calendar getFirstCouponDate() {
		return firstCouponDate;
	}
	public void setFirstCouponDate(Calendar firstCouponDate) {
		this.firstCouponDate = firstCouponDate;
	}
	public Calendar getFixingDate() {
		return fixingDate;
	}
	public void setFixingDate(Calendar fixingDate) {
		this.fixingDate = fixingDate;
	}
	public String getInstrumentStyle() {
		return instrumentStyle;
	}
	public void setInstrumentStyle(String instrumentStyle) {
		this.instrumentStyle = instrumentStyle;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPayDiscountCurve() {
		return payDiscountCurve;
	}
	public void setPayDiscountCurve(String payDiscountCurve) {
		this.payDiscountCurve = payDiscountCurve;
	}
	public Calendar getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Calendar paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getProdData() {
		return prodData;
	}
	public void setProdData(String prodData) {
		this.prodData = prodData;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getReceiveDiscountCurve() {
		return receiveDiscountCurve;
	}
	public void setReceiveDiscountCurve(String receiveDiscountCurve) {
		this.receiveDiscountCurve = receiveDiscountCurve;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getUnderlying() {
		return underlying;
	}
	public void setUnderlying(String underlying) {
		this.underlying = underlying;
	}
	public String getAmendUser() {
		return amendUser;
	}
	public void setAmendUser(String amendUser) {
		this.amendUser = amendUser;
	}
	public Calendar getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Calendar auditDate) {
		this.auditDate = auditDate;
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
	public String getTradeCurrency() {
		return tradeCurrency;
	}
	public void setTradeCurrency(String tradeCurrency) {
		this.tradeCurrency = tradeCurrency;
	}
	public Calendar getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Calendar tradeDate) {
		this.tradeDate = tradeDate;
	}
    
    public String getResetRemark() {
        return resetRemark;
    }

    public void setResetRemark(String resetRemark) {
        this.resetRemark = resetRemark;
    }

}
