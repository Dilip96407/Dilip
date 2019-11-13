/**
 * AssetVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class AssetVo  implements java.io.Serializable {
    private java.lang.String assetId;

    private java.lang.String buySell;

    private java.lang.String callPut;

    private java.lang.String capFloor;

    private double delta;

    private java.lang.String deltaCurrency;

    private java.util.Calendar expiryDate;

    private java.util.Calendar firstCouponDate;

    private java.util.Calendar fixingDate;

    private java.lang.String formula;

    private double fxRate;

    private java.lang.String instrumentStyle;

    private double marketRateIR;

    private double marketRateVola;

    private java.util.Calendar maturityDate;

    private java.lang.String model;

    private double npv;

    private java.lang.String payCurrency;

    private java.lang.String payDiscountCurve;

    private java.lang.String payIndexBasis;

    private double payNotional;

    private double payRateSpread;

    private java.util.Calendar paymentDate;

    private double premiumAmount;

    private java.lang.String premiumCurrency;

    private java.lang.String prodData;

    private java.lang.String productName;

    private double quantity;

    private java.lang.String receiveCurrency;

    private java.lang.String receiveDiscountCurve;

    private java.lang.String receiveIndexBasis;

    private double receiveNotional;

    private double receiveRateSpread;

    private java.util.Calendar startDate;

    private double strike;

    private java.lang.String style;

    private java.lang.String tradeSubType;

    private java.lang.String underlying;

    private double vega;

    private java.lang.String vegaCurrency;

    public AssetVo() {
    }

    public AssetVo(
           java.lang.String assetId,
           java.lang.String buySell,
           java.lang.String callPut,
           java.lang.String capFloor,
           double delta,
           java.lang.String deltaCurrency,
           java.util.Calendar expiryDate,
           java.util.Calendar firstCouponDate,
           java.util.Calendar fixingDate,
           java.lang.String formula,
           double fxRate,
           java.lang.String instrumentStyle,
           double marketRateIR,
           double marketRateVola,
           java.util.Calendar maturityDate,
           java.lang.String model,
           double npv,
           java.lang.String payCurrency,
           java.lang.String payDiscountCurve,
           java.lang.String payIndexBasis,
           double payNotional,
           double payRateSpread,
           java.util.Calendar paymentDate,
           double premiumAmount,
           java.lang.String premiumCurrency,
           java.lang.String prodData,
           java.lang.String productName,
           double quantity,
           java.lang.String receiveCurrency,
           java.lang.String receiveDiscountCurve,
           java.lang.String receiveIndexBasis,
           double receiveNotional,
           double receiveRateSpread,
           java.util.Calendar startDate,
           double strike,
           java.lang.String style,
           java.lang.String tradeSubType,
           java.lang.String underlying,
           double vega,
           java.lang.String vegaCurrency) {
           this.assetId = assetId;
           this.buySell = buySell;
           this.callPut = callPut;
           this.capFloor = capFloor;
           this.delta = delta;
           this.deltaCurrency = deltaCurrency;
           this.expiryDate = expiryDate;
           this.firstCouponDate = firstCouponDate;
           this.fixingDate = fixingDate;
           this.formula = formula;
           this.fxRate = fxRate;
           this.instrumentStyle = instrumentStyle;
           this.marketRateIR = marketRateIR;
           this.marketRateVola = marketRateVola;
           this.maturityDate = maturityDate;
           this.model = model;
           this.npv = npv;
           this.payCurrency = payCurrency;
           this.payDiscountCurve = payDiscountCurve;
           this.payIndexBasis = payIndexBasis;
           this.payNotional = payNotional;
           this.payRateSpread = payRateSpread;
           this.paymentDate = paymentDate;
           this.premiumAmount = premiumAmount;
           this.premiumCurrency = premiumCurrency;
           this.prodData = prodData;
           this.productName = productName;
           this.quantity = quantity;
           this.receiveCurrency = receiveCurrency;
           this.receiveDiscountCurve = receiveDiscountCurve;
           this.receiveIndexBasis = receiveIndexBasis;
           this.receiveNotional = receiveNotional;
           this.receiveRateSpread = receiveRateSpread;
           this.startDate = startDate;
           this.strike = strike;
           this.style = style;
           this.tradeSubType = tradeSubType;
           this.underlying = underlying;
           this.vega = vega;
           this.vegaCurrency = vegaCurrency;
    }


    /**
     * Gets the assetId value for this AssetVo.
     * 
     * @return assetId
     */
    public java.lang.String getAssetId() {
        return assetId;
    }


    /**
     * Sets the assetId value for this AssetVo.
     * 
     * @param assetId
     */
    public void setAssetId(java.lang.String assetId) {
        this.assetId = assetId;
    }


    /**
     * Gets the buySell value for this AssetVo.
     * 
     * @return buySell
     */
    public java.lang.String getBuySell() {
        return buySell;
    }


    /**
     * Sets the buySell value for this AssetVo.
     * 
     * @param buySell
     */
    public void setBuySell(java.lang.String buySell) {
        this.buySell = buySell;
    }


    /**
     * Gets the callPut value for this AssetVo.
     * 
     * @return callPut
     */
    public java.lang.String getCallPut() {
        return callPut;
    }


    /**
     * Sets the callPut value for this AssetVo.
     * 
     * @param callPut
     */
    public void setCallPut(java.lang.String callPut) {
        this.callPut = callPut;
    }


    /**
     * Gets the capFloor value for this AssetVo.
     * 
     * @return capFloor
     */
    public java.lang.String getCapFloor() {
        return capFloor;
    }


    /**
     * Sets the capFloor value for this AssetVo.
     * 
     * @param capFloor
     */
    public void setCapFloor(java.lang.String capFloor) {
        this.capFloor = capFloor;
    }


    /**
     * Gets the delta value for this AssetVo.
     * 
     * @return delta
     */
    public double getDelta() {
        return delta;
    }


    /**
     * Sets the delta value for this AssetVo.
     * 
     * @param delta
     */
    public void setDelta(double delta) {
        this.delta = delta;
    }


    /**
     * Gets the deltaCurrency value for this AssetVo.
     * 
     * @return deltaCurrency
     */
    public java.lang.String getDeltaCurrency() {
        return deltaCurrency;
    }


    /**
     * Sets the deltaCurrency value for this AssetVo.
     * 
     * @param deltaCurrency
     */
    public void setDeltaCurrency(java.lang.String deltaCurrency) {
        this.deltaCurrency = deltaCurrency;
    }


    /**
     * Gets the expiryDate value for this AssetVo.
     * 
     * @return expiryDate
     */
    public java.util.Calendar getExpiryDate() {
        return expiryDate;
    }


    /**
     * Sets the expiryDate value for this AssetVo.
     * 
     * @param expiryDate
     */
    public void setExpiryDate(java.util.Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }


    /**
     * Gets the firstCouponDate value for this AssetVo.
     * 
     * @return firstCouponDate
     */
    public java.util.Calendar getFirstCouponDate() {
        return firstCouponDate;
    }


    /**
     * Sets the firstCouponDate value for this AssetVo.
     * 
     * @param firstCouponDate
     */
    public void setFirstCouponDate(java.util.Calendar firstCouponDate) {
        this.firstCouponDate = firstCouponDate;
    }


    /**
     * Gets the fixingDate value for this AssetVo.
     * 
     * @return fixingDate
     */
    public java.util.Calendar getFixingDate() {
        return fixingDate;
    }


    /**
     * Sets the fixingDate value for this AssetVo.
     * 
     * @param fixingDate
     */
    public void setFixingDate(java.util.Calendar fixingDate) {
        this.fixingDate = fixingDate;
    }


    /**
     * Gets the formula value for this AssetVo.
     * 
     * @return formula
     */
    public java.lang.String getFormula() {
        return formula;
    }


    /**
     * Sets the formula value for this AssetVo.
     * 
     * @param formula
     */
    public void setFormula(java.lang.String formula) {
        this.formula = formula;
    }


    /**
     * Gets the fxRate value for this AssetVo.
     * 
     * @return fxRate
     */
    public double getFxRate() {
        return fxRate;
    }


    /**
     * Sets the fxRate value for this AssetVo.
     * 
     * @param fxRate
     */
    public void setFxRate(double fxRate) {
        this.fxRate = fxRate;
    }


    /**
     * Gets the instrumentStyle value for this AssetVo.
     * 
     * @return instrumentStyle
     */
    public java.lang.String getInstrumentStyle() {
        return instrumentStyle;
    }


    /**
     * Sets the instrumentStyle value for this AssetVo.
     * 
     * @param instrumentStyle
     */
    public void setInstrumentStyle(java.lang.String instrumentStyle) {
        this.instrumentStyle = instrumentStyle;
    }


    /**
     * Gets the marketRateIR value for this AssetVo.
     * 
     * @return marketRateIR
     */
    public double getMarketRateIR() {
        return marketRateIR;
    }


    /**
     * Sets the marketRateIR value for this AssetVo.
     * 
     * @param marketRateIR
     */
    public void setMarketRateIR(double marketRateIR) {
        this.marketRateIR = marketRateIR;
    }


    /**
     * Gets the marketRateVola value for this AssetVo.
     * 
     * @return marketRateVola
     */
    public double getMarketRateVola() {
        return marketRateVola;
    }


    /**
     * Sets the marketRateVola value for this AssetVo.
     * 
     * @param marketRateVola
     */
    public void setMarketRateVola(double marketRateVola) {
        this.marketRateVola = marketRateVola;
    }


    /**
     * Gets the maturityDate value for this AssetVo.
     * 
     * @return maturityDate
     */
    public java.util.Calendar getMaturityDate() {
        return maturityDate;
    }


    /**
     * Sets the maturityDate value for this AssetVo.
     * 
     * @param maturityDate
     */
    public void setMaturityDate(java.util.Calendar maturityDate) {
        this.maturityDate = maturityDate;
    }


    /**
     * Gets the model value for this AssetVo.
     * 
     * @return model
     */
    public java.lang.String getModel() {
        return model;
    }


    /**
     * Sets the model value for this AssetVo.
     * 
     * @param model
     */
    public void setModel(java.lang.String model) {
        this.model = model;
    }


    /**
     * Gets the npv value for this AssetVo.
     * 
     * @return npv
     */
    public double getNpv() {
        return npv;
    }


    /**
     * Sets the npv value for this AssetVo.
     * 
     * @param npv
     */
    public void setNpv(double npv) {
        this.npv = npv;
    }


    /**
     * Gets the payCurrency value for this AssetVo.
     * 
     * @return payCurrency
     */
    public java.lang.String getPayCurrency() {
        return payCurrency;
    }


    /**
     * Sets the payCurrency value for this AssetVo.
     * 
     * @param payCurrency
     */
    public void setPayCurrency(java.lang.String payCurrency) {
        this.payCurrency = payCurrency;
    }


    /**
     * Gets the payDiscountCurve value for this AssetVo.
     * 
     * @return payDiscountCurve
     */
    public java.lang.String getPayDiscountCurve() {
        return payDiscountCurve;
    }


    /**
     * Sets the payDiscountCurve value for this AssetVo.
     * 
     * @param payDiscountCurve
     */
    public void setPayDiscountCurve(java.lang.String payDiscountCurve) {
        this.payDiscountCurve = payDiscountCurve;
    }


    /**
     * Gets the payIndexBasis value for this AssetVo.
     * 
     * @return payIndexBasis
     */
    public java.lang.String getPayIndexBasis() {
        return payIndexBasis;
    }


    /**
     * Sets the payIndexBasis value for this AssetVo.
     * 
     * @param payIndexBasis
     */
    public void setPayIndexBasis(java.lang.String payIndexBasis) {
        this.payIndexBasis = payIndexBasis;
    }


    /**
     * Gets the payNotional value for this AssetVo.
     * 
     * @return payNotional
     */
    public double getPayNotional() {
        return payNotional;
    }


    /**
     * Sets the payNotional value for this AssetVo.
     * 
     * @param payNotional
     */
    public void setPayNotional(double payNotional) {
        this.payNotional = payNotional;
    }


    /**
     * Gets the payRateSpread value for this AssetVo.
     * 
     * @return payRateSpread
     */
    public double getPayRateSpread() {
        return payRateSpread;
    }


    /**
     * Sets the payRateSpread value for this AssetVo.
     * 
     * @param payRateSpread
     */
    public void setPayRateSpread(double payRateSpread) {
        this.payRateSpread = payRateSpread;
    }


    /**
     * Gets the paymentDate value for this AssetVo.
     * 
     * @return paymentDate
     */
    public java.util.Calendar getPaymentDate() {
        return paymentDate;
    }


    /**
     * Sets the paymentDate value for this AssetVo.
     * 
     * @param paymentDate
     */
    public void setPaymentDate(java.util.Calendar paymentDate) {
        this.paymentDate = paymentDate;
    }


    /**
     * Gets the premiumAmount value for this AssetVo.
     * 
     * @return premiumAmount
     */
    public double getPremiumAmount() {
        return premiumAmount;
    }


    /**
     * Sets the premiumAmount value for this AssetVo.
     * 
     * @param premiumAmount
     */
    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }


    /**
     * Gets the premiumCurrency value for this AssetVo.
     * 
     * @return premiumCurrency
     */
    public java.lang.String getPremiumCurrency() {
        return premiumCurrency;
    }


    /**
     * Sets the premiumCurrency value for this AssetVo.
     * 
     * @param premiumCurrency
     */
    public void setPremiumCurrency(java.lang.String premiumCurrency) {
        this.premiumCurrency = premiumCurrency;
    }


    /**
     * Gets the prodData value for this AssetVo.
     * 
     * @return prodData
     */
    public java.lang.String getProdData() {
        return prodData;
    }


    /**
     * Sets the prodData value for this AssetVo.
     * 
     * @param prodData
     */
    public void setProdData(java.lang.String prodData) {
        this.prodData = prodData;
    }


    /**
     * Gets the productName value for this AssetVo.
     * 
     * @return productName
     */
    public java.lang.String getProductName() {
        return productName;
    }


    /**
     * Sets the productName value for this AssetVo.
     * 
     * @param productName
     */
    public void setProductName(java.lang.String productName) {
        this.productName = productName;
    }


    /**
     * Gets the quantity value for this AssetVo.
     * 
     * @return quantity
     */
    public double getQuantity() {
        return quantity;
    }


    /**
     * Sets the quantity value for this AssetVo.
     * 
     * @param quantity
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


    /**
     * Gets the receiveCurrency value for this AssetVo.
     * 
     * @return receiveCurrency
     */
    public java.lang.String getReceiveCurrency() {
        return receiveCurrency;
    }


    /**
     * Sets the receiveCurrency value for this AssetVo.
     * 
     * @param receiveCurrency
     */
    public void setReceiveCurrency(java.lang.String receiveCurrency) {
        this.receiveCurrency = receiveCurrency;
    }


    /**
     * Gets the receiveDiscountCurve value for this AssetVo.
     * 
     * @return receiveDiscountCurve
     */
    public java.lang.String getReceiveDiscountCurve() {
        return receiveDiscountCurve;
    }


    /**
     * Sets the receiveDiscountCurve value for this AssetVo.
     * 
     * @param receiveDiscountCurve
     */
    public void setReceiveDiscountCurve(java.lang.String receiveDiscountCurve) {
        this.receiveDiscountCurve = receiveDiscountCurve;
    }


    /**
     * Gets the receiveIndexBasis value for this AssetVo.
     * 
     * @return receiveIndexBasis
     */
    public java.lang.String getReceiveIndexBasis() {
        return receiveIndexBasis;
    }


    /**
     * Sets the receiveIndexBasis value for this AssetVo.
     * 
     * @param receiveIndexBasis
     */
    public void setReceiveIndexBasis(java.lang.String receiveIndexBasis) {
        this.receiveIndexBasis = receiveIndexBasis;
    }


    /**
     * Gets the receiveNotional value for this AssetVo.
     * 
     * @return receiveNotional
     */
    public double getReceiveNotional() {
        return receiveNotional;
    }


    /**
     * Sets the receiveNotional value for this AssetVo.
     * 
     * @param receiveNotional
     */
    public void setReceiveNotional(double receiveNotional) {
        this.receiveNotional = receiveNotional;
    }


    /**
     * Gets the receiveRateSpread value for this AssetVo.
     * 
     * @return receiveRateSpread
     */
    public double getReceiveRateSpread() {
        return receiveRateSpread;
    }


    /**
     * Sets the receiveRateSpread value for this AssetVo.
     * 
     * @param receiveRateSpread
     */
    public void setReceiveRateSpread(double receiveRateSpread) {
        this.receiveRateSpread = receiveRateSpread;
    }


    /**
     * Gets the startDate value for this AssetVo.
     * 
     * @return startDate
     */
    public java.util.Calendar getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this AssetVo.
     * 
     * @param startDate
     */
    public void setStartDate(java.util.Calendar startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the strike value for this AssetVo.
     * 
     * @return strike
     */
    public double getStrike() {
        return strike;
    }


    /**
     * Sets the strike value for this AssetVo.
     * 
     * @param strike
     */
    public void setStrike(double strike) {
        this.strike = strike;
    }


    /**
     * Gets the style value for this AssetVo.
     * 
     * @return style
     */
    public java.lang.String getStyle() {
        return style;
    }


    /**
     * Sets the style value for this AssetVo.
     * 
     * @param style
     */
    public void setStyle(java.lang.String style) {
        this.style = style;
    }


    /**
     * Gets the tradeSubType value for this AssetVo.
     * 
     * @return tradeSubType
     */
    public java.lang.String getTradeSubType() {
        return tradeSubType;
    }


    /**
     * Sets the tradeSubType value for this AssetVo.
     * 
     * @param tradeSubType
     */
    public void setTradeSubType(java.lang.String tradeSubType) {
        this.tradeSubType = tradeSubType;
    }


    /**
     * Gets the underlying value for this AssetVo.
     * 
     * @return underlying
     */
    public java.lang.String getUnderlying() {
        return underlying;
    }


    /**
     * Sets the underlying value for this AssetVo.
     * 
     * @param underlying
     */
    public void setUnderlying(java.lang.String underlying) {
        this.underlying = underlying;
    }


    /**
     * Gets the vega value for this AssetVo.
     * 
     * @return vega
     */
    public double getVega() {
        return vega;
    }


    /**
     * Sets the vega value for this AssetVo.
     * 
     * @param vega
     */
    public void setVega(double vega) {
        this.vega = vega;
    }


    /**
     * Gets the vegaCurrency value for this AssetVo.
     * 
     * @return vegaCurrency
     */
    public java.lang.String getVegaCurrency() {
        return vegaCurrency;
    }


    /**
     * Sets the vegaCurrency value for this AssetVo.
     * 
     * @param vegaCurrency
     */
    public void setVegaCurrency(java.lang.String vegaCurrency) {
        this.vegaCurrency = vegaCurrency;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssetVo)) return false;
        AssetVo other = (AssetVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.assetId==null && other.getAssetId()==null) || 
             (this.assetId!=null &&
              this.assetId.equals(other.getAssetId()))) &&
            ((this.buySell==null && other.getBuySell()==null) || 
             (this.buySell!=null &&
              this.buySell.equals(other.getBuySell()))) &&
            ((this.callPut==null && other.getCallPut()==null) || 
             (this.callPut!=null &&
              this.callPut.equals(other.getCallPut()))) &&
            ((this.capFloor==null && other.getCapFloor()==null) || 
             (this.capFloor!=null &&
              this.capFloor.equals(other.getCapFloor()))) &&
            this.delta == other.getDelta() &&
            ((this.deltaCurrency==null && other.getDeltaCurrency()==null) || 
             (this.deltaCurrency!=null &&
              this.deltaCurrency.equals(other.getDeltaCurrency()))) &&
            ((this.expiryDate==null && other.getExpiryDate()==null) || 
             (this.expiryDate!=null &&
              this.expiryDate.equals(other.getExpiryDate()))) &&
            ((this.firstCouponDate==null && other.getFirstCouponDate()==null) || 
             (this.firstCouponDate!=null &&
              this.firstCouponDate.equals(other.getFirstCouponDate()))) &&
            ((this.fixingDate==null && other.getFixingDate()==null) || 
             (this.fixingDate!=null &&
              this.fixingDate.equals(other.getFixingDate()))) &&
            ((this.formula==null && other.getFormula()==null) || 
             (this.formula!=null &&
              this.formula.equals(other.getFormula()))) &&
            this.fxRate == other.getFxRate() &&
            ((this.instrumentStyle==null && other.getInstrumentStyle()==null) || 
             (this.instrumentStyle!=null &&
              this.instrumentStyle.equals(other.getInstrumentStyle()))) &&
            this.marketRateIR == other.getMarketRateIR() &&
            this.marketRateVola == other.getMarketRateVola() &&
            ((this.maturityDate==null && other.getMaturityDate()==null) || 
             (this.maturityDate!=null &&
              this.maturityDate.equals(other.getMaturityDate()))) &&
            ((this.model==null && other.getModel()==null) || 
             (this.model!=null &&
              this.model.equals(other.getModel()))) &&
            this.npv == other.getNpv() &&
            ((this.payCurrency==null && other.getPayCurrency()==null) || 
             (this.payCurrency!=null &&
              this.payCurrency.equals(other.getPayCurrency()))) &&
            ((this.payDiscountCurve==null && other.getPayDiscountCurve()==null) || 
             (this.payDiscountCurve!=null &&
              this.payDiscountCurve.equals(other.getPayDiscountCurve()))) &&
            ((this.payIndexBasis==null && other.getPayIndexBasis()==null) || 
             (this.payIndexBasis!=null &&
              this.payIndexBasis.equals(other.getPayIndexBasis()))) &&
            this.payNotional == other.getPayNotional() &&
            this.payRateSpread == other.getPayRateSpread() &&
            ((this.paymentDate==null && other.getPaymentDate()==null) || 
             (this.paymentDate!=null &&
              this.paymentDate.equals(other.getPaymentDate()))) &&
            this.premiumAmount == other.getPremiumAmount() &&
            ((this.premiumCurrency==null && other.getPremiumCurrency()==null) || 
             (this.premiumCurrency!=null &&
              this.premiumCurrency.equals(other.getPremiumCurrency()))) &&
            ((this.prodData==null && other.getProdData()==null) || 
             (this.prodData!=null &&
              this.prodData.equals(other.getProdData()))) &&
            ((this.productName==null && other.getProductName()==null) || 
             (this.productName!=null &&
              this.productName.equals(other.getProductName()))) &&
            this.quantity == other.getQuantity() &&
            ((this.receiveCurrency==null && other.getReceiveCurrency()==null) || 
             (this.receiveCurrency!=null &&
              this.receiveCurrency.equals(other.getReceiveCurrency()))) &&
            ((this.receiveDiscountCurve==null && other.getReceiveDiscountCurve()==null) || 
             (this.receiveDiscountCurve!=null &&
              this.receiveDiscountCurve.equals(other.getReceiveDiscountCurve()))) &&
            ((this.receiveIndexBasis==null && other.getReceiveIndexBasis()==null) || 
             (this.receiveIndexBasis!=null &&
              this.receiveIndexBasis.equals(other.getReceiveIndexBasis()))) &&
            this.receiveNotional == other.getReceiveNotional() &&
            this.receiveRateSpread == other.getReceiveRateSpread() &&
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            this.strike == other.getStrike() &&
            ((this.style==null && other.getStyle()==null) || 
             (this.style!=null &&
              this.style.equals(other.getStyle()))) &&
            ((this.tradeSubType==null && other.getTradeSubType()==null) || 
             (this.tradeSubType!=null &&
              this.tradeSubType.equals(other.getTradeSubType()))) &&
            ((this.underlying==null && other.getUnderlying()==null) || 
             (this.underlying!=null &&
              this.underlying.equals(other.getUnderlying()))) &&
            this.vega == other.getVega() &&
            ((this.vegaCurrency==null && other.getVegaCurrency()==null) || 
             (this.vegaCurrency!=null &&
              this.vegaCurrency.equals(other.getVegaCurrency())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAssetId() != null) {
            _hashCode += getAssetId().hashCode();
        }
        if (getBuySell() != null) {
            _hashCode += getBuySell().hashCode();
        }
        if (getCallPut() != null) {
            _hashCode += getCallPut().hashCode();
        }
        if (getCapFloor() != null) {
            _hashCode += getCapFloor().hashCode();
        }
        _hashCode += new Double(getDelta()).hashCode();
        if (getDeltaCurrency() != null) {
            _hashCode += getDeltaCurrency().hashCode();
        }
        if (getExpiryDate() != null) {
            _hashCode += getExpiryDate().hashCode();
        }
        if (getFirstCouponDate() != null) {
            _hashCode += getFirstCouponDate().hashCode();
        }
        if (getFixingDate() != null) {
            _hashCode += getFixingDate().hashCode();
        }
        if (getFormula() != null) {
            _hashCode += getFormula().hashCode();
        }
        _hashCode += new Double(getFxRate()).hashCode();
        if (getInstrumentStyle() != null) {
            _hashCode += getInstrumentStyle().hashCode();
        }
        _hashCode += new Double(getMarketRateIR()).hashCode();
        _hashCode += new Double(getMarketRateVola()).hashCode();
        if (getMaturityDate() != null) {
            _hashCode += getMaturityDate().hashCode();
        }
        if (getModel() != null) {
            _hashCode += getModel().hashCode();
        }
        _hashCode += new Double(getNpv()).hashCode();
        if (getPayCurrency() != null) {
            _hashCode += getPayCurrency().hashCode();
        }
        if (getPayDiscountCurve() != null) {
            _hashCode += getPayDiscountCurve().hashCode();
        }
        if (getPayIndexBasis() != null) {
            _hashCode += getPayIndexBasis().hashCode();
        }
        _hashCode += new Double(getPayNotional()).hashCode();
        _hashCode += new Double(getPayRateSpread()).hashCode();
        if (getPaymentDate() != null) {
            _hashCode += getPaymentDate().hashCode();
        }
        _hashCode += new Double(getPremiumAmount()).hashCode();
        if (getPremiumCurrency() != null) {
            _hashCode += getPremiumCurrency().hashCode();
        }
        if (getProdData() != null) {
            _hashCode += getProdData().hashCode();
        }
        if (getProductName() != null) {
            _hashCode += getProductName().hashCode();
        }
        _hashCode += new Double(getQuantity()).hashCode();
        if (getReceiveCurrency() != null) {
            _hashCode += getReceiveCurrency().hashCode();
        }
        if (getReceiveDiscountCurve() != null) {
            _hashCode += getReceiveDiscountCurve().hashCode();
        }
        if (getReceiveIndexBasis() != null) {
            _hashCode += getReceiveIndexBasis().hashCode();
        }
        _hashCode += new Double(getReceiveNotional()).hashCode();
        _hashCode += new Double(getReceiveRateSpread()).hashCode();
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        _hashCode += new Double(getStrike()).hashCode();
        if (getStyle() != null) {
            _hashCode += getStyle().hashCode();
        }
        if (getTradeSubType() != null) {
            _hashCode += getTradeSubType().hashCode();
        }
        if (getUnderlying() != null) {
            _hashCode += getUnderlying().hashCode();
        }
        _hashCode += new Double(getVega()).hashCode();
        if (getVegaCurrency() != null) {
            _hashCode += getVegaCurrency().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssetVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "AssetVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "assetId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buySell");
        elemField.setXmlName(new javax.xml.namespace.QName("", "buySell"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callPut");
        elemField.setXmlName(new javax.xml.namespace.QName("", "callPut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("capFloor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "capFloor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "delta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deltaCurrency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deltaCurrency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expiryDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "expiryDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstCouponDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "firstCouponDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fixingDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fixingDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formula");
        elemField.setXmlName(new javax.xml.namespace.QName("", "formula"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fxRate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fxRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instrumentStyle");
        elemField.setXmlName(new javax.xml.namespace.QName("", "instrumentStyle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("marketRateIR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "marketRateIR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("marketRateVola");
        elemField.setXmlName(new javax.xml.namespace.QName("", "marketRateVola"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maturityDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maturityDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("model");
        elemField.setXmlName(new javax.xml.namespace.QName("", "model"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("npv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "npv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payCurrency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payCurrency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payDiscountCurve");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payDiscountCurve"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payIndexBasis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payIndexBasis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payNotional");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payNotional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payRateSpread");
        elemField.setXmlName(new javax.xml.namespace.QName("", "payRateSpread"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paymentDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("premiumAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "premiumAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("premiumCurrency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "premiumCurrency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prodData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prodData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "quantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiveCurrency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "receiveCurrency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiveDiscountCurve");
        elemField.setXmlName(new javax.xml.namespace.QName("", "receiveDiscountCurve"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiveIndexBasis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "receiveIndexBasis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiveNotional");
        elemField.setXmlName(new javax.xml.namespace.QName("", "receiveNotional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiveRateSpread");
        elemField.setXmlName(new javax.xml.namespace.QName("", "receiveRateSpread"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strike");
        elemField.setXmlName(new javax.xml.namespace.QName("", "strike"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("style");
        elemField.setXmlName(new javax.xml.namespace.QName("", "style"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeSubType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeSubType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("underlying");
        elemField.setXmlName(new javax.xml.namespace.QName("", "underlying"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vega");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vega"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vegaCurrency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vegaCurrency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
