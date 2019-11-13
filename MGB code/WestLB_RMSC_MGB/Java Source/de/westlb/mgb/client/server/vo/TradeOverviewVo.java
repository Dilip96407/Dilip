/**
 * TradeOverviewVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class TradeOverviewVo  implements java.io.Serializable {
    private double additionalPrice;

    private double amendmentNpvChange;

    private de.westlb.mgb.client.server.vo.AssetVo[] assets;

    private java.lang.Boolean automatic;

    private java.lang.Long automaticStateByEmployeeId;

    private java.lang.String automaticStateByName;

    private java.lang.String automaticStateCode;

    private java.lang.String bloombergTradeId;

    private java.lang.String bookId;

    private java.lang.Boolean buy;

    private java.lang.Boolean cancelation;

    private java.lang.String company;

    private java.lang.String counterparty;

    private java.lang.String counterpartyReference;

    private java.lang.String currency;

    private de.westlb.mgb.client.server.vo.PriceVo currentPrice;

    private double currentPriceDifference;

    private de.westlb.mgb.client.server.vo.RequestVo currentRequest;

    private double delta;

    private java.lang.String description;

    private java.lang.String exchange;

    private double farTurnover;

    private double fwdPointsFarLeg;

    private double fwdPointsNearLeg;

    private double fxRate;

    private java.lang.Boolean highTurnOver;

    private java.lang.Long id;

    private java.lang.String instrument;

    private java.lang.String instrumentName;

    private java.lang.Boolean internal;

    private java.lang.String isin;

    private java.lang.Boolean lateEntry;

    private java.lang.Boolean lowTurnOver;

    private java.lang.Boolean manualCheckRequired;

    private java.lang.Long manualStateAttachmentId;

    private java.lang.String manualStateAttachmentName;

    private java.lang.Long manualStateByEmployeeId;

    private java.lang.String manualStateByName;

    private java.lang.String manualStateCode;

    private java.lang.String manualStateComment;

    private double margin;

    private double marketPointsFarLeg;

    private double marketPointsNearLeg;

    private java.lang.String marketer;

    private java.util.Calendar maturityDate;

    private java.lang.String mdCurrency;

    private java.lang.String memo;

    private java.lang.Boolean mischPrice;

    private double nearTurnover;

    private java.lang.Boolean net;

    private de.westlb.mgb.client.server.vo.TradeParameterVo parameter;

    private double premium;

    private java.lang.Double[] priceDifferences;

    private double priceTolerance;

    private java.lang.Boolean priceToleranceViolation;

    private de.westlb.mgb.client.server.vo.PriceVo[] prices;

    private java.lang.Boolean reclamationCheckRequired;

    private java.lang.String reclamationClosedComment;

    private java.lang.Boolean reclamationIsClosed;

    private java.lang.Long reclamationStateAttachmentId;

    private java.lang.String reclamationStateAttachmentName;

    private java.lang.Long reclamationStateByEmployeeId;

    private java.lang.String reclamationStateByName;

    private java.lang.String reclamationStateCode;

    private java.lang.String reclamationStateComment;

    private java.lang.String recordType;

    private int reminderLevel;

    private java.lang.String reportLocation;

    private de.westlb.mgb.client.server.vo.RequestVo[] requests;

    private java.util.Calendar settlementDate;

    private java.lang.Boolean smallCustomer;

    private java.lang.String sourceSystemCode;

    private java.lang.Boolean sparkasse;

    private java.lang.Boolean storno;

    private java.lang.Boolean stornoGroup;

    private java.lang.String stornoGroupId;

    private java.lang.String structure;

    private java.lang.String subType;

    private java.util.Calendar systemDate;

    private double theoreticPrice;

    private java.lang.String tradeCategory;

    private java.util.Calendar tradeDate;

    private java.lang.String tradeId;

    private double tradePrice;

    private java.lang.String tradeState;

    private java.lang.String tradeType;

    private java.lang.String traderId;

    private java.lang.String traderLocation;

    private java.lang.String traderName;

    private double turnover;

    private java.lang.String updatedBy;

    private double vega;

    private java.lang.Boolean vivaldisTrade;

    private double volume;

    private double volume2;

    private java.util.Calendar amendDate;

    public TradeOverviewVo() {
    }

    public TradeOverviewVo(
           double additionalPrice,
           double amendmentNpvChange,
           de.westlb.mgb.client.server.vo.AssetVo[] assets,
           java.lang.Boolean automatic,
           java.lang.Long automaticStateByEmployeeId,
           java.lang.String automaticStateByName,
           java.lang.String automaticStateCode,
           java.lang.String bloombergTradeId,
           java.lang.String bookId,
           java.lang.Boolean buy,
           java.lang.Boolean cancelation,
           java.lang.String company,
           java.lang.String counterparty,
           java.lang.String counterpartyReference,
           java.lang.String currency,
           de.westlb.mgb.client.server.vo.PriceVo currentPrice,
           double currentPriceDifference,
           de.westlb.mgb.client.server.vo.RequestVo currentRequest,
           double delta,
           java.lang.String description,
           java.lang.String exchange,
           double farTurnover,
           double fwdPointsFarLeg,
           double fwdPointsNearLeg,
           double fxRate,
           java.lang.Boolean highTurnOver,
           java.lang.Long id,
           java.lang.String instrument,
           java.lang.String instrumentName,
           java.lang.Boolean internal,
           java.lang.String isin,
           java.lang.Boolean lateEntry,
           java.lang.Boolean lowTurnOver,
           java.lang.Boolean manualCheckRequired,
           java.lang.Long manualStateAttachmentId,
           java.lang.String manualStateAttachmentName,
           java.lang.Long manualStateByEmployeeId,
           java.lang.String manualStateByName,
           java.lang.String manualStateCode,
           java.lang.String manualStateComment,
           double margin,
           double marketPointsFarLeg,
           double marketPointsNearLeg,
           java.lang.String marketer,
           java.util.Calendar maturityDate,
           java.lang.String mdCurrency,
           java.lang.String memo,
           java.lang.Boolean mischPrice,
           double nearTurnover,
           java.lang.Boolean net,
           de.westlb.mgb.client.server.vo.TradeParameterVo parameter,
           double premium,
           java.lang.Double[] priceDifferences,
           double priceTolerance,
           java.lang.Boolean priceToleranceViolation,
           de.westlb.mgb.client.server.vo.PriceVo[] prices,
           java.lang.Boolean reclamationCheckRequired,
           java.lang.String reclamationClosedComment,
           java.lang.Boolean reclamationIsClosed,
           java.lang.Long reclamationStateAttachmentId,
           java.lang.String reclamationStateAttachmentName,
           java.lang.Long reclamationStateByEmployeeId,
           java.lang.String reclamationStateByName,
           java.lang.String reclamationStateCode,
           java.lang.String reclamationStateComment,
           java.lang.String recordType,
           int reminderLevel,
           java.lang.String reportLocation,
           de.westlb.mgb.client.server.vo.RequestVo[] requests,
           java.util.Calendar settlementDate,
           java.lang.Boolean smallCustomer,
           java.lang.String sourceSystemCode,
           java.lang.Boolean sparkasse,
           java.lang.Boolean storno,
           java.lang.Boolean stornoGroup,
           java.lang.String stornoGroupId,
           java.lang.String structure,
           java.lang.String subType,
           java.util.Calendar systemDate,
           double theoreticPrice,
           java.lang.String tradeCategory,
           java.util.Calendar tradeDate,
           java.lang.String tradeId,
           double tradePrice,
           java.lang.String tradeState,
           java.lang.String tradeType,
           java.lang.String traderId,
           java.lang.String traderLocation,
           java.lang.String traderName,
           double turnover,
           java.lang.String updatedBy,
           double vega,
           java.lang.Boolean vivaldisTrade,
           double volume,
           double volume2,
           java.util.Calendar amendDate) {
           this.additionalPrice = additionalPrice;
           this.amendmentNpvChange = amendmentNpvChange;
           this.assets = assets;
           this.automatic = automatic;
           this.automaticStateByEmployeeId = automaticStateByEmployeeId;
           this.automaticStateByName = automaticStateByName;
           this.automaticStateCode = automaticStateCode;
           this.bloombergTradeId = bloombergTradeId;
           this.bookId = bookId;
           this.buy = buy;
           this.cancelation = cancelation;
           this.company = company;
           this.counterparty = counterparty;
           this.counterpartyReference = counterpartyReference;
           this.currency = currency;
           this.currentPrice = currentPrice;
           this.currentPriceDifference = currentPriceDifference;
           this.currentRequest = currentRequest;
           this.delta = delta;
           this.description = description;
           this.exchange = exchange;
           this.farTurnover = farTurnover;
           this.fwdPointsFarLeg = fwdPointsFarLeg;
           this.fwdPointsNearLeg = fwdPointsNearLeg;
           this.fxRate = fxRate;
           this.highTurnOver = highTurnOver;
           this.id = id;
           this.instrument = instrument;
           this.instrumentName = instrumentName;
           this.internal = internal;
           this.isin = isin;
           this.lateEntry = lateEntry;
           this.lowTurnOver = lowTurnOver;
           this.manualCheckRequired = manualCheckRequired;
           this.manualStateAttachmentId = manualStateAttachmentId;
           this.manualStateAttachmentName = manualStateAttachmentName;
           this.manualStateByEmployeeId = manualStateByEmployeeId;
           this.manualStateByName = manualStateByName;
           this.manualStateCode = manualStateCode;
           this.manualStateComment = manualStateComment;
           this.margin = margin;
           this.marketPointsFarLeg = marketPointsFarLeg;
           this.marketPointsNearLeg = marketPointsNearLeg;
           this.marketer = marketer;
           this.maturityDate = maturityDate;
           this.mdCurrency = mdCurrency;
           this.memo = memo;
           this.mischPrice = mischPrice;
           this.nearTurnover = nearTurnover;
           this.net = net;
           this.parameter = parameter;
           this.premium = premium;
           this.priceDifferences = priceDifferences;
           this.priceTolerance = priceTolerance;
           this.priceToleranceViolation = priceToleranceViolation;
           this.prices = prices;
           this.reclamationCheckRequired = reclamationCheckRequired;
           this.reclamationClosedComment = reclamationClosedComment;
           this.reclamationIsClosed = reclamationIsClosed;
           this.reclamationStateAttachmentId = reclamationStateAttachmentId;
           this.reclamationStateAttachmentName = reclamationStateAttachmentName;
           this.reclamationStateByEmployeeId = reclamationStateByEmployeeId;
           this.reclamationStateByName = reclamationStateByName;
           this.reclamationStateCode = reclamationStateCode;
           this.reclamationStateComment = reclamationStateComment;
           this.recordType = recordType;
           this.reminderLevel = reminderLevel;
           this.reportLocation = reportLocation;
           this.requests = requests;
           this.settlementDate = settlementDate;
           this.smallCustomer = smallCustomer;
           this.sourceSystemCode = sourceSystemCode;
           this.sparkasse = sparkasse;
           this.storno = storno;
           this.stornoGroup = stornoGroup;
           this.stornoGroupId = stornoGroupId;
           this.structure = structure;
           this.subType = subType;
           this.systemDate = systemDate;
           this.theoreticPrice = theoreticPrice;
           this.tradeCategory = tradeCategory;
           this.tradeDate = tradeDate;
           this.tradeId = tradeId;
           this.tradePrice = tradePrice;
           this.tradeState = tradeState;
           this.tradeType = tradeType;
           this.traderId = traderId;
           this.traderLocation = traderLocation;
           this.traderName = traderName;
           this.turnover = turnover;
           this.updatedBy = updatedBy;
           this.vega = vega;
           this.vivaldisTrade = vivaldisTrade;
           this.volume = volume;
           this.volume2 = volume2;
           this.amendDate = amendDate;
    }


    /**
     * Gets the additionalPrice value for this TradeOverviewVo.
     * 
     * @return additionalPrice
     */
    public double getAdditionalPrice() {
        return additionalPrice;
    }


    /**
     * Sets the additionalPrice value for this TradeOverviewVo.
     * 
     * @param additionalPrice
     */
    public void setAdditionalPrice(double additionalPrice) {
        this.additionalPrice = additionalPrice;
    }


    /**
     * Gets the amendmentNpvChange value for this TradeOverviewVo.
     * 
     * @return amendmentNpvChange
     */
    public double getAmendmentNpvChange() {
        return amendmentNpvChange;
    }


    /**
     * Sets the amendmentNpvChange value for this TradeOverviewVo.
     * 
     * @param amendmentNpvChange
     */
    public void setAmendmentNpvChange(double amendmentNpvChange) {
        this.amendmentNpvChange = amendmentNpvChange;
    }


    /**
     * Gets the assets value for this TradeOverviewVo.
     * 
     * @return assets
     */
    public de.westlb.mgb.client.server.vo.AssetVo[] getAssets() {
        return assets;
    }


    /**
     * Sets the assets value for this TradeOverviewVo.
     * 
     * @param assets
     */
    public void setAssets(de.westlb.mgb.client.server.vo.AssetVo[] assets) {
        this.assets = assets;
    }


    /**
     * Gets the automatic value for this TradeOverviewVo.
     * 
     * @return automatic
     */
    public java.lang.Boolean getAutomatic() {
        return automatic;
    }


    /**
     * Sets the automatic value for this TradeOverviewVo.
     * 
     * @param automatic
     */
    public void setAutomatic(java.lang.Boolean automatic) {
        this.automatic = automatic;
    }


    /**
     * Gets the automaticStateByEmployeeId value for this TradeOverviewVo.
     * 
     * @return automaticStateByEmployeeId
     */
    public java.lang.Long getAutomaticStateByEmployeeId() {
        return automaticStateByEmployeeId;
    }


    /**
     * Sets the automaticStateByEmployeeId value for this TradeOverviewVo.
     * 
     * @param automaticStateByEmployeeId
     */
    public void setAutomaticStateByEmployeeId(java.lang.Long automaticStateByEmployeeId) {
        this.automaticStateByEmployeeId = automaticStateByEmployeeId;
    }


    /**
     * Gets the automaticStateByName value for this TradeOverviewVo.
     * 
     * @return automaticStateByName
     */
    public java.lang.String getAutomaticStateByName() {
        return automaticStateByName;
    }


    /**
     * Sets the automaticStateByName value for this TradeOverviewVo.
     * 
     * @param automaticStateByName
     */
    public void setAutomaticStateByName(java.lang.String automaticStateByName) {
        this.automaticStateByName = automaticStateByName;
    }


    /**
     * Gets the automaticStateCode value for this TradeOverviewVo.
     * 
     * @return automaticStateCode
     */
    public java.lang.String getAutomaticStateCode() {
        return automaticStateCode;
    }


    /**
     * Sets the automaticStateCode value for this TradeOverviewVo.
     * 
     * @param automaticStateCode
     */
    public void setAutomaticStateCode(java.lang.String automaticStateCode) {
        this.automaticStateCode = automaticStateCode;
    }


    /**
     * Gets the bloombergTradeId value for this TradeOverviewVo.
     * 
     * @return bloombergTradeId
     */
    public java.lang.String getBloombergTradeId() {
        return bloombergTradeId;
    }


    /**
     * Sets the bloombergTradeId value for this TradeOverviewVo.
     * 
     * @param bloombergTradeId
     */
    public void setBloombergTradeId(java.lang.String bloombergTradeId) {
        this.bloombergTradeId = bloombergTradeId;
    }


    /**
     * Gets the bookId value for this TradeOverviewVo.
     * 
     * @return bookId
     */
    public java.lang.String getBookId() {
        return bookId;
    }


    /**
     * Sets the bookId value for this TradeOverviewVo.
     * 
     * @param bookId
     */
    public void setBookId(java.lang.String bookId) {
        this.bookId = bookId;
    }


    /**
     * Gets the buy value for this TradeOverviewVo.
     * 
     * @return buy
     */
    public java.lang.Boolean getBuy() {
        return buy;
    }


    /**
     * Sets the buy value for this TradeOverviewVo.
     * 
     * @param buy
     */
    public void setBuy(java.lang.Boolean buy) {
        this.buy = buy;
    }


    /**
     * Gets the cancelation value for this TradeOverviewVo.
     * 
     * @return cancelation
     */
    public java.lang.Boolean getCancelation() {
        return cancelation;
    }


    /**
     * Sets the cancelation value for this TradeOverviewVo.
     * 
     * @param cancelation
     */
    public void setCancelation(java.lang.Boolean cancelation) {
        this.cancelation = cancelation;
    }


    /**
     * Gets the company value for this TradeOverviewVo.
     * 
     * @return company
     */
    public java.lang.String getCompany() {
        return company;
    }


    /**
     * Sets the company value for this TradeOverviewVo.
     * 
     * @param company
     */
    public void setCompany(java.lang.String company) {
        this.company = company;
    }


    /**
     * Gets the counterparty value for this TradeOverviewVo.
     * 
     * @return counterparty
     */
    public java.lang.String getCounterparty() {
        return counterparty;
    }


    /**
     * Sets the counterparty value for this TradeOverviewVo.
     * 
     * @param counterparty
     */
    public void setCounterparty(java.lang.String counterparty) {
        this.counterparty = counterparty;
    }


    /**
     * Gets the counterpartyReference value for this TradeOverviewVo.
     * 
     * @return counterpartyReference
     */
    public java.lang.String getCounterpartyReference() {
        return counterpartyReference;
    }


    /**
     * Sets the counterpartyReference value for this TradeOverviewVo.
     * 
     * @param counterpartyReference
     */
    public void setCounterpartyReference(java.lang.String counterpartyReference) {
        this.counterpartyReference = counterpartyReference;
    }


    /**
     * Gets the currency value for this TradeOverviewVo.
     * 
     * @return currency
     */
    public java.lang.String getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this TradeOverviewVo.
     * 
     * @param currency
     */
    public void setCurrency(java.lang.String currency) {
        this.currency = currency;
    }


    /**
     * Gets the currentPrice value for this TradeOverviewVo.
     * 
     * @return currentPrice
     */
    public de.westlb.mgb.client.server.vo.PriceVo getCurrentPrice() {
        return currentPrice;
    }


    /**
     * Sets the currentPrice value for this TradeOverviewVo.
     * 
     * @param currentPrice
     */
    public void setCurrentPrice(de.westlb.mgb.client.server.vo.PriceVo currentPrice) {
        this.currentPrice = currentPrice;
    }


    /**
     * Gets the currentPriceDifference value for this TradeOverviewVo.
     * 
     * @return currentPriceDifference
     */
    public double getCurrentPriceDifference() {
        return currentPriceDifference;
    }


    /**
     * Sets the currentPriceDifference value for this TradeOverviewVo.
     * 
     * @param currentPriceDifference
     */
    public void setCurrentPriceDifference(double currentPriceDifference) {
        this.currentPriceDifference = currentPriceDifference;
    }


    /**
     * Gets the currentRequest value for this TradeOverviewVo.
     * 
     * @return currentRequest
     */
    public de.westlb.mgb.client.server.vo.RequestVo getCurrentRequest() {
        return currentRequest;
    }


    /**
     * Sets the currentRequest value for this TradeOverviewVo.
     * 
     * @param currentRequest
     */
    public void setCurrentRequest(de.westlb.mgb.client.server.vo.RequestVo currentRequest) {
        this.currentRequest = currentRequest;
    }


    /**
     * Gets the delta value for this TradeOverviewVo.
     * 
     * @return delta
     */
    public double getDelta() {
        return delta;
    }


    /**
     * Sets the delta value for this TradeOverviewVo.
     * 
     * @param delta
     */
    public void setDelta(double delta) {
        this.delta = delta;
    }


    /**
     * Gets the description value for this TradeOverviewVo.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this TradeOverviewVo.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the exchange value for this TradeOverviewVo.
     * 
     * @return exchange
     */
    public java.lang.String getExchange() {
        return exchange;
    }


    /**
     * Sets the exchange value for this TradeOverviewVo.
     * 
     * @param exchange
     */
    public void setExchange(java.lang.String exchange) {
        this.exchange = exchange;
    }


    /**
     * Gets the farTurnover value for this TradeOverviewVo.
     * 
     * @return farTurnover
     */
    public double getFarTurnover() {
        return farTurnover;
    }


    /**
     * Sets the farTurnover value for this TradeOverviewVo.
     * 
     * @param farTurnover
     */
    public void setFarTurnover(double farTurnover) {
        this.farTurnover = farTurnover;
    }


    /**
     * Gets the fwdPointsFarLeg value for this TradeOverviewVo.
     * 
     * @return fwdPointsFarLeg
     */
    public double getFwdPointsFarLeg() {
        return fwdPointsFarLeg;
    }


    /**
     * Sets the fwdPointsFarLeg value for this TradeOverviewVo.
     * 
     * @param fwdPointsFarLeg
     */
    public void setFwdPointsFarLeg(double fwdPointsFarLeg) {
        this.fwdPointsFarLeg = fwdPointsFarLeg;
    }


    /**
     * Gets the fwdPointsNearLeg value for this TradeOverviewVo.
     * 
     * @return fwdPointsNearLeg
     */
    public double getFwdPointsNearLeg() {
        return fwdPointsNearLeg;
    }


    /**
     * Sets the fwdPointsNearLeg value for this TradeOverviewVo.
     * 
     * @param fwdPointsNearLeg
     */
    public void setFwdPointsNearLeg(double fwdPointsNearLeg) {
        this.fwdPointsNearLeg = fwdPointsNearLeg;
    }


    /**
     * Gets the fxRate value for this TradeOverviewVo.
     * 
     * @return fxRate
     */
    public double getFxRate() {
        return fxRate;
    }


    /**
     * Sets the fxRate value for this TradeOverviewVo.
     * 
     * @param fxRate
     */
    public void setFxRate(double fxRate) {
        this.fxRate = fxRate;
    }


    /**
     * Gets the highTurnOver value for this TradeOverviewVo.
     * 
     * @return highTurnOver
     */
    public java.lang.Boolean getHighTurnOver() {
        return highTurnOver;
    }


    /**
     * Sets the highTurnOver value for this TradeOverviewVo.
     * 
     * @param highTurnOver
     */
    public void setHighTurnOver(java.lang.Boolean highTurnOver) {
        this.highTurnOver = highTurnOver;
    }


    /**
     * Gets the id value for this TradeOverviewVo.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this TradeOverviewVo.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the instrument value for this TradeOverviewVo.
     * 
     * @return instrument
     */
    public java.lang.String getInstrument() {
        return instrument;
    }


    /**
     * Sets the instrument value for this TradeOverviewVo.
     * 
     * @param instrument
     */
    public void setInstrument(java.lang.String instrument) {
        this.instrument = instrument;
    }


    /**
     * Gets the instrumentName value for this TradeOverviewVo.
     * 
     * @return instrumentName
     */
    public java.lang.String getInstrumentName() {
        return instrumentName;
    }


    /**
     * Sets the instrumentName value for this TradeOverviewVo.
     * 
     * @param instrumentName
     */
    public void setInstrumentName(java.lang.String instrumentName) {
        this.instrumentName = instrumentName;
    }


    /**
     * Gets the internal value for this TradeOverviewVo.
     * 
     * @return internal
     */
    public java.lang.Boolean getInternal() {
        return internal;
    }


    /**
     * Sets the internal value for this TradeOverviewVo.
     * 
     * @param internal
     */
    public void setInternal(java.lang.Boolean internal) {
        this.internal = internal;
    }


    /**
     * Gets the isin value for this TradeOverviewVo.
     * 
     * @return isin
     */
    public java.lang.String getIsin() {
        return isin;
    }


    /**
     * Sets the isin value for this TradeOverviewVo.
     * 
     * @param isin
     */
    public void setIsin(java.lang.String isin) {
        this.isin = isin;
    }


    /**
     * Gets the lateEntry value for this TradeOverviewVo.
     * 
     * @return lateEntry
     */
    public java.lang.Boolean getLateEntry() {
        return lateEntry;
    }


    /**
     * Sets the lateEntry value for this TradeOverviewVo.
     * 
     * @param lateEntry
     */
    public void setLateEntry(java.lang.Boolean lateEntry) {
        this.lateEntry = lateEntry;
    }


    /**
     * Gets the lowTurnOver value for this TradeOverviewVo.
     * 
     * @return lowTurnOver
     */
    public java.lang.Boolean getLowTurnOver() {
        return lowTurnOver;
    }


    /**
     * Sets the lowTurnOver value for this TradeOverviewVo.
     * 
     * @param lowTurnOver
     */
    public void setLowTurnOver(java.lang.Boolean lowTurnOver) {
        this.lowTurnOver = lowTurnOver;
    }


    /**
     * Gets the manualCheckRequired value for this TradeOverviewVo.
     * 
     * @return manualCheckRequired
     */
    public java.lang.Boolean getManualCheckRequired() {
        return manualCheckRequired;
    }


    /**
     * Sets the manualCheckRequired value for this TradeOverviewVo.
     * 
     * @param manualCheckRequired
     */
    public void setManualCheckRequired(java.lang.Boolean manualCheckRequired) {
        this.manualCheckRequired = manualCheckRequired;
    }


    /**
     * Gets the manualStateAttachmentId value for this TradeOverviewVo.
     * 
     * @return manualStateAttachmentId
     */
    public java.lang.Long getManualStateAttachmentId() {
        return manualStateAttachmentId;
    }


    /**
     * Sets the manualStateAttachmentId value for this TradeOverviewVo.
     * 
     * @param manualStateAttachmentId
     */
    public void setManualStateAttachmentId(java.lang.Long manualStateAttachmentId) {
        this.manualStateAttachmentId = manualStateAttachmentId;
    }


    /**
     * Gets the manualStateAttachmentName value for this TradeOverviewVo.
     * 
     * @return manualStateAttachmentName
     */
    public java.lang.String getManualStateAttachmentName() {
        return manualStateAttachmentName;
    }


    /**
     * Sets the manualStateAttachmentName value for this TradeOverviewVo.
     * 
     * @param manualStateAttachmentName
     */
    public void setManualStateAttachmentName(java.lang.String manualStateAttachmentName) {
        this.manualStateAttachmentName = manualStateAttachmentName;
    }


    /**
     * Gets the manualStateByEmployeeId value for this TradeOverviewVo.
     * 
     * @return manualStateByEmployeeId
     */
    public java.lang.Long getManualStateByEmployeeId() {
        return manualStateByEmployeeId;
    }


    /**
     * Sets the manualStateByEmployeeId value for this TradeOverviewVo.
     * 
     * @param manualStateByEmployeeId
     */
    public void setManualStateByEmployeeId(java.lang.Long manualStateByEmployeeId) {
        this.manualStateByEmployeeId = manualStateByEmployeeId;
    }


    /**
     * Gets the manualStateByName value for this TradeOverviewVo.
     * 
     * @return manualStateByName
     */
    public java.lang.String getManualStateByName() {
        return manualStateByName;
    }


    /**
     * Sets the manualStateByName value for this TradeOverviewVo.
     * 
     * @param manualStateByName
     */
    public void setManualStateByName(java.lang.String manualStateByName) {
        this.manualStateByName = manualStateByName;
    }


    /**
     * Gets the manualStateCode value for this TradeOverviewVo.
     * 
     * @return manualStateCode
     */
    public java.lang.String getManualStateCode() {
        return manualStateCode;
    }


    /**
     * Sets the manualStateCode value for this TradeOverviewVo.
     * 
     * @param manualStateCode
     */
    public void setManualStateCode(java.lang.String manualStateCode) {
        this.manualStateCode = manualStateCode;
    }


    /**
     * Gets the manualStateComment value for this TradeOverviewVo.
     * 
     * @return manualStateComment
     */
    public java.lang.String getManualStateComment() {
        return manualStateComment;
    }


    /**
     * Sets the manualStateComment value for this TradeOverviewVo.
     * 
     * @param manualStateComment
     */
    public void setManualStateComment(java.lang.String manualStateComment) {
        this.manualStateComment = manualStateComment;
    }


    /**
     * Gets the margin value for this TradeOverviewVo.
     * 
     * @return margin
     */
    public double getMargin() {
        return margin;
    }


    /**
     * Sets the margin value for this TradeOverviewVo.
     * 
     * @param margin
     */
    public void setMargin(double margin) {
        this.margin = margin;
    }


    /**
     * Gets the marketPointsFarLeg value for this TradeOverviewVo.
     * 
     * @return marketPointsFarLeg
     */
    public double getMarketPointsFarLeg() {
        return marketPointsFarLeg;
    }


    /**
     * Sets the marketPointsFarLeg value for this TradeOverviewVo.
     * 
     * @param marketPointsFarLeg
     */
    public void setMarketPointsFarLeg(double marketPointsFarLeg) {
        this.marketPointsFarLeg = marketPointsFarLeg;
    }


    /**
     * Gets the marketPointsNearLeg value for this TradeOverviewVo.
     * 
     * @return marketPointsNearLeg
     */
    public double getMarketPointsNearLeg() {
        return marketPointsNearLeg;
    }


    /**
     * Sets the marketPointsNearLeg value for this TradeOverviewVo.
     * 
     * @param marketPointsNearLeg
     */
    public void setMarketPointsNearLeg(double marketPointsNearLeg) {
        this.marketPointsNearLeg = marketPointsNearLeg;
    }


    /**
     * Gets the marketer value for this TradeOverviewVo.
     * 
     * @return marketer
     */
    public java.lang.String getMarketer() {
        return marketer;
    }


    /**
     * Sets the marketer value for this TradeOverviewVo.
     * 
     * @param marketer
     */
    public void setMarketer(java.lang.String marketer) {
        this.marketer = marketer;
    }


    /**
     * Gets the maturityDate value for this TradeOverviewVo.
     * 
     * @return maturityDate
     */
    public java.util.Calendar getMaturityDate() {
        return maturityDate;
    }


    /**
     * Sets the maturityDate value for this TradeOverviewVo.
     * 
     * @param maturityDate
     */
    public void setMaturityDate(java.util.Calendar maturityDate) {
        this.maturityDate = maturityDate;
    }


    /**
     * Gets the mdCurrency value for this TradeOverviewVo.
     * 
     * @return mdCurrency
     */
    public java.lang.String getMdCurrency() {
        return mdCurrency;
    }


    /**
     * Sets the mdCurrency value for this TradeOverviewVo.
     * 
     * @param mdCurrency
     */
    public void setMdCurrency(java.lang.String mdCurrency) {
        this.mdCurrency = mdCurrency;
    }


    /**
     * Gets the memo value for this TradeOverviewVo.
     * 
     * @return memo
     */
    public java.lang.String getMemo() {
        return memo;
    }


    /**
     * Sets the memo value for this TradeOverviewVo.
     * 
     * @param memo
     */
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
    }


    /**
     * Gets the mischPrice value for this TradeOverviewVo.
     * 
     * @return mischPrice
     */
    public java.lang.Boolean getMischPrice() {
        return mischPrice;
    }


    /**
     * Sets the mischPrice value for this TradeOverviewVo.
     * 
     * @param mischPrice
     */
    public void setMischPrice(java.lang.Boolean mischPrice) {
        this.mischPrice = mischPrice;
    }


    /**
     * Gets the nearTurnover value for this TradeOverviewVo.
     * 
     * @return nearTurnover
     */
    public double getNearTurnover() {
        return nearTurnover;
    }


    /**
     * Sets the nearTurnover value for this TradeOverviewVo.
     * 
     * @param nearTurnover
     */
    public void setNearTurnover(double nearTurnover) {
        this.nearTurnover = nearTurnover;
    }


    /**
     * Gets the net value for this TradeOverviewVo.
     * 
     * @return net
     */
    public java.lang.Boolean getNet() {
        return net;
    }


    /**
     * Sets the net value for this TradeOverviewVo.
     * 
     * @param net
     */
    public void setNet(java.lang.Boolean net) {
        this.net = net;
    }


    /**
     * Gets the parameter value for this TradeOverviewVo.
     * 
     * @return parameter
     */
    public de.westlb.mgb.client.server.vo.TradeParameterVo getParameter() {
        return parameter;
    }


    /**
     * Sets the parameter value for this TradeOverviewVo.
     * 
     * @param parameter
     */
    public void setParameter(de.westlb.mgb.client.server.vo.TradeParameterVo parameter) {
        this.parameter = parameter;
    }


    /**
     * Gets the premium value for this TradeOverviewVo.
     * 
     * @return premium
     */
    public double getPremium() {
        return premium;
    }


    /**
     * Sets the premium value for this TradeOverviewVo.
     * 
     * @param premium
     */
    public void setPremium(double premium) {
        this.premium = premium;
    }


    /**
     * Gets the priceDifferences value for this TradeOverviewVo.
     * 
     * @return priceDifferences
     */
    public java.lang.Double[] getPriceDifferences() {
        return priceDifferences;
    }


    /**
     * Sets the priceDifferences value for this TradeOverviewVo.
     * 
     * @param priceDifferences
     */
    public void setPriceDifferences(java.lang.Double[] priceDifferences) {
        this.priceDifferences = priceDifferences;
    }


    /**
     * Gets the priceTolerance value for this TradeOverviewVo.
     * 
     * @return priceTolerance
     */
    public double getPriceTolerance() {
        return priceTolerance;
    }


    /**
     * Sets the priceTolerance value for this TradeOverviewVo.
     * 
     * @param priceTolerance
     */
    public void setPriceTolerance(double priceTolerance) {
        this.priceTolerance = priceTolerance;
    }


    /**
     * Gets the priceToleranceViolation value for this TradeOverviewVo.
     * 
     * @return priceToleranceViolation
     */
    public java.lang.Boolean getPriceToleranceViolation() {
        return priceToleranceViolation;
    }


    /**
     * Sets the priceToleranceViolation value for this TradeOverviewVo.
     * 
     * @param priceToleranceViolation
     */
    public void setPriceToleranceViolation(java.lang.Boolean priceToleranceViolation) {
        this.priceToleranceViolation = priceToleranceViolation;
    }


    /**
     * Gets the prices value for this TradeOverviewVo.
     * 
     * @return prices
     */
    public de.westlb.mgb.client.server.vo.PriceVo[] getPrices() {
        return prices;
    }


    /**
     * Sets the prices value for this TradeOverviewVo.
     * 
     * @param prices
     */
    public void setPrices(de.westlb.mgb.client.server.vo.PriceVo[] prices) {
        this.prices = prices;
    }


    /**
     * Gets the reclamationCheckRequired value for this TradeOverviewVo.
     * 
     * @return reclamationCheckRequired
     */
    public java.lang.Boolean getReclamationCheckRequired() {
        return reclamationCheckRequired;
    }


    /**
     * Sets the reclamationCheckRequired value for this TradeOverviewVo.
     * 
     * @param reclamationCheckRequired
     */
    public void setReclamationCheckRequired(java.lang.Boolean reclamationCheckRequired) {
        this.reclamationCheckRequired = reclamationCheckRequired;
    }


    /**
     * Gets the reclamationClosedComment value for this TradeOverviewVo.
     * 
     * @return reclamationClosedComment
     */
    public java.lang.String getReclamationClosedComment() {
        return reclamationClosedComment;
    }


    /**
     * Sets the reclamationClosedComment value for this TradeOverviewVo.
     * 
     * @param reclamationClosedComment
     */
    public void setReclamationClosedComment(java.lang.String reclamationClosedComment) {
        this.reclamationClosedComment = reclamationClosedComment;
    }


    /**
     * Gets the reclamationIsClosed value for this TradeOverviewVo.
     * 
     * @return reclamationIsClosed
     */
    public java.lang.Boolean getReclamationIsClosed() {
        return reclamationIsClosed;
    }


    /**
     * Sets the reclamationIsClosed value for this TradeOverviewVo.
     * 
     * @param reclamationIsClosed
     */
    public void setReclamationIsClosed(java.lang.Boolean reclamationIsClosed) {
        this.reclamationIsClosed = reclamationIsClosed;
    }


    /**
     * Gets the reclamationStateAttachmentId value for this TradeOverviewVo.
     * 
     * @return reclamationStateAttachmentId
     */
    public java.lang.Long getReclamationStateAttachmentId() {
        return reclamationStateAttachmentId;
    }


    /**
     * Sets the reclamationStateAttachmentId value for this TradeOverviewVo.
     * 
     * @param reclamationStateAttachmentId
     */
    public void setReclamationStateAttachmentId(java.lang.Long reclamationStateAttachmentId) {
        this.reclamationStateAttachmentId = reclamationStateAttachmentId;
    }


    /**
     * Gets the reclamationStateAttachmentName value for this TradeOverviewVo.
     * 
     * @return reclamationStateAttachmentName
     */
    public java.lang.String getReclamationStateAttachmentName() {
        return reclamationStateAttachmentName;
    }


    /**
     * Sets the reclamationStateAttachmentName value for this TradeOverviewVo.
     * 
     * @param reclamationStateAttachmentName
     */
    public void setReclamationStateAttachmentName(java.lang.String reclamationStateAttachmentName) {
        this.reclamationStateAttachmentName = reclamationStateAttachmentName;
    }


    /**
     * Gets the reclamationStateByEmployeeId value for this TradeOverviewVo.
     * 
     * @return reclamationStateByEmployeeId
     */
    public java.lang.Long getReclamationStateByEmployeeId() {
        return reclamationStateByEmployeeId;
    }


    /**
     * Sets the reclamationStateByEmployeeId value for this TradeOverviewVo.
     * 
     * @param reclamationStateByEmployeeId
     */
    public void setReclamationStateByEmployeeId(java.lang.Long reclamationStateByEmployeeId) {
        this.reclamationStateByEmployeeId = reclamationStateByEmployeeId;
    }


    /**
     * Gets the reclamationStateByName value for this TradeOverviewVo.
     * 
     * @return reclamationStateByName
     */
    public java.lang.String getReclamationStateByName() {
        return reclamationStateByName;
    }


    /**
     * Sets the reclamationStateByName value for this TradeOverviewVo.
     * 
     * @param reclamationStateByName
     */
    public void setReclamationStateByName(java.lang.String reclamationStateByName) {
        this.reclamationStateByName = reclamationStateByName;
    }


    /**
     * Gets the reclamationStateCode value for this TradeOverviewVo.
     * 
     * @return reclamationStateCode
     */
    public java.lang.String getReclamationStateCode() {
        return reclamationStateCode;
    }


    /**
     * Sets the reclamationStateCode value for this TradeOverviewVo.
     * 
     * @param reclamationStateCode
     */
    public void setReclamationStateCode(java.lang.String reclamationStateCode) {
        this.reclamationStateCode = reclamationStateCode;
    }


    /**
     * Gets the reclamationStateComment value for this TradeOverviewVo.
     * 
     * @return reclamationStateComment
     */
    public java.lang.String getReclamationStateComment() {
        return reclamationStateComment;
    }


    /**
     * Sets the reclamationStateComment value for this TradeOverviewVo.
     * 
     * @param reclamationStateComment
     */
    public void setReclamationStateComment(java.lang.String reclamationStateComment) {
        this.reclamationStateComment = reclamationStateComment;
    }


    /**
     * Gets the recordType value for this TradeOverviewVo.
     * 
     * @return recordType
     */
    public java.lang.String getRecordType() {
        return recordType;
    }


    /**
     * Sets the recordType value for this TradeOverviewVo.
     * 
     * @param recordType
     */
    public void setRecordType(java.lang.String recordType) {
        this.recordType = recordType;
    }


    /**
     * Gets the reminderLevel value for this TradeOverviewVo.
     * 
     * @return reminderLevel
     */
    public int getReminderLevel() {
        return reminderLevel;
    }


    /**
     * Sets the reminderLevel value for this TradeOverviewVo.
     * 
     * @param reminderLevel
     */
    public void setReminderLevel(int reminderLevel) {
        this.reminderLevel = reminderLevel;
    }


    /**
     * Gets the reportLocation value for this TradeOverviewVo.
     * 
     * @return reportLocation
     */
    public java.lang.String getReportLocation() {
        return reportLocation;
    }


    /**
     * Sets the reportLocation value for this TradeOverviewVo.
     * 
     * @param reportLocation
     */
    public void setReportLocation(java.lang.String reportLocation) {
        this.reportLocation = reportLocation;
    }


    /**
     * Gets the requests value for this TradeOverviewVo.
     * 
     * @return requests
     */
    public de.westlb.mgb.client.server.vo.RequestVo[] getRequests() {
        return requests;
    }


    /**
     * Sets the requests value for this TradeOverviewVo.
     * 
     * @param requests
     */
    public void setRequests(de.westlb.mgb.client.server.vo.RequestVo[] requests) {
        this.requests = requests;
    }


    /**
     * Gets the settlementDate value for this TradeOverviewVo.
     * 
     * @return settlementDate
     */
    public java.util.Calendar getSettlementDate() {
        return settlementDate;
    }


    /**
     * Sets the settlementDate value for this TradeOverviewVo.
     * 
     * @param settlementDate
     */
    public void setSettlementDate(java.util.Calendar settlementDate) {
        this.settlementDate = settlementDate;
    }


    /**
     * Gets the smallCustomer value for this TradeOverviewVo.
     * 
     * @return smallCustomer
     */
    public java.lang.Boolean getSmallCustomer() {
        return smallCustomer;
    }


    /**
     * Sets the smallCustomer value for this TradeOverviewVo.
     * 
     * @param smallCustomer
     */
    public void setSmallCustomer(java.lang.Boolean smallCustomer) {
        this.smallCustomer = smallCustomer;
    }


    /**
     * Gets the sourceSystemCode value for this TradeOverviewVo.
     * 
     * @return sourceSystemCode
     */
    public java.lang.String getSourceSystemCode() {
        return sourceSystemCode;
    }


    /**
     * Sets the sourceSystemCode value for this TradeOverviewVo.
     * 
     * @param sourceSystemCode
     */
    public void setSourceSystemCode(java.lang.String sourceSystemCode) {
        this.sourceSystemCode = sourceSystemCode;
    }


    /**
     * Gets the sparkasse value for this TradeOverviewVo.
     * 
     * @return sparkasse
     */
    public java.lang.Boolean getSparkasse() {
        return sparkasse;
    }


    /**
     * Sets the sparkasse value for this TradeOverviewVo.
     * 
     * @param sparkasse
     */
    public void setSparkasse(java.lang.Boolean sparkasse) {
        this.sparkasse = sparkasse;
    }


    /**
     * Gets the storno value for this TradeOverviewVo.
     * 
     * @return storno
     */
    public java.lang.Boolean getStorno() {
        return storno;
    }


    /**
     * Sets the storno value for this TradeOverviewVo.
     * 
     * @param storno
     */
    public void setStorno(java.lang.Boolean storno) {
        this.storno = storno;
    }


    /**
     * Gets the stornoGroup value for this TradeOverviewVo.
     * 
     * @return stornoGroup
     */
    public java.lang.Boolean getStornoGroup() {
        return stornoGroup;
    }


    /**
     * Sets the stornoGroup value for this TradeOverviewVo.
     * 
     * @param stornoGroup
     */
    public void setStornoGroup(java.lang.Boolean stornoGroup) {
        this.stornoGroup = stornoGroup;
    }


    /**
     * Gets the stornoGroupId value for this TradeOverviewVo.
     * 
     * @return stornoGroupId
     */
    public java.lang.String getStornoGroupId() {
        return stornoGroupId;
    }


    /**
     * Sets the stornoGroupId value for this TradeOverviewVo.
     * 
     * @param stornoGroupId
     */
    public void setStornoGroupId(java.lang.String stornoGroupId) {
        this.stornoGroupId = stornoGroupId;
    }


    /**
     * Gets the structure value for this TradeOverviewVo.
     * 
     * @return structure
     */
    public java.lang.String getStructure() {
        return structure;
    }


    /**
     * Sets the structure value for this TradeOverviewVo.
     * 
     * @param structure
     */
    public void setStructure(java.lang.String structure) {
        this.structure = structure;
    }


    /**
     * Gets the subType value for this TradeOverviewVo.
     * 
     * @return subType
     */
    public java.lang.String getSubType() {
        return subType;
    }


    /**
     * Sets the subType value for this TradeOverviewVo.
     * 
     * @param subType
     */
    public void setSubType(java.lang.String subType) {
        this.subType = subType;
    }


    /**
     * Gets the systemDate value for this TradeOverviewVo.
     * 
     * @return systemDate
     */
    public java.util.Calendar getSystemDate() {
        return systemDate;
    }


    /**
     * Sets the systemDate value for this TradeOverviewVo.
     * 
     * @param systemDate
     */
    public void setSystemDate(java.util.Calendar systemDate) {
        this.systemDate = systemDate;
    }


    /**
     * Gets the theoreticPrice value for this TradeOverviewVo.
     * 
     * @return theoreticPrice
     */
    public double getTheoreticPrice() {
        return theoreticPrice;
    }


    /**
     * Sets the theoreticPrice value for this TradeOverviewVo.
     * 
     * @param theoreticPrice
     */
    public void setTheoreticPrice(double theoreticPrice) {
        this.theoreticPrice = theoreticPrice;
    }


    /**
     * Gets the tradeCategory value for this TradeOverviewVo.
     * 
     * @return tradeCategory
     */
    public java.lang.String getTradeCategory() {
        return tradeCategory;
    }


    /**
     * Sets the tradeCategory value for this TradeOverviewVo.
     * 
     * @param tradeCategory
     */
    public void setTradeCategory(java.lang.String tradeCategory) {
        this.tradeCategory = tradeCategory;
    }


    /**
     * Gets the tradeDate value for this TradeOverviewVo.
     * 
     * @return tradeDate
     */
    public java.util.Calendar getTradeDate() {
        return tradeDate;
    }


    /**
     * Sets the tradeDate value for this TradeOverviewVo.
     * 
     * @param tradeDate
     */
    public void setTradeDate(java.util.Calendar tradeDate) {
        this.tradeDate = tradeDate;
    }


    /**
     * Gets the tradeId value for this TradeOverviewVo.
     * 
     * @return tradeId
     */
    public java.lang.String getTradeId() {
        return tradeId;
    }


    /**
     * Sets the tradeId value for this TradeOverviewVo.
     * 
     * @param tradeId
     */
    public void setTradeId(java.lang.String tradeId) {
        this.tradeId = tradeId;
    }


    /**
     * Gets the tradePrice value for this TradeOverviewVo.
     * 
     * @return tradePrice
     */
    public double getTradePrice() {
        return tradePrice;
    }


    /**
     * Sets the tradePrice value for this TradeOverviewVo.
     * 
     * @param tradePrice
     */
    public void setTradePrice(double tradePrice) {
        this.tradePrice = tradePrice;
    }


    /**
     * Gets the tradeState value for this TradeOverviewVo.
     * 
     * @return tradeState
     */
    public java.lang.String getTradeState() {
        return tradeState;
    }


    /**
     * Sets the tradeState value for this TradeOverviewVo.
     * 
     * @param tradeState
     */
    public void setTradeState(java.lang.String tradeState) {
        this.tradeState = tradeState;
    }


    /**
     * Gets the tradeType value for this TradeOverviewVo.
     * 
     * @return tradeType
     */
    public java.lang.String getTradeType() {
        return tradeType;
    }


    /**
     * Sets the tradeType value for this TradeOverviewVo.
     * 
     * @param tradeType
     */
    public void setTradeType(java.lang.String tradeType) {
        this.tradeType = tradeType;
    }


    /**
     * Gets the traderId value for this TradeOverviewVo.
     * 
     * @return traderId
     */
    public java.lang.String getTraderId() {
        return traderId;
    }


    /**
     * Sets the traderId value for this TradeOverviewVo.
     * 
     * @param traderId
     */
    public void setTraderId(java.lang.String traderId) {
        this.traderId = traderId;
    }


    /**
     * Gets the traderLocation value for this TradeOverviewVo.
     * 
     * @return traderLocation
     */
    public java.lang.String getTraderLocation() {
        return traderLocation;
    }


    /**
     * Sets the traderLocation value for this TradeOverviewVo.
     * 
     * @param traderLocation
     */
    public void setTraderLocation(java.lang.String traderLocation) {
        this.traderLocation = traderLocation;
    }


    /**
     * Gets the traderName value for this TradeOverviewVo.
     * 
     * @return traderName
     */
    public java.lang.String getTraderName() {
        return traderName;
    }


    /**
     * Sets the traderName value for this TradeOverviewVo.
     * 
     * @param traderName
     */
    public void setTraderName(java.lang.String traderName) {
        this.traderName = traderName;
    }


    /**
     * Gets the turnover value for this TradeOverviewVo.
     * 
     * @return turnover
     */
    public double getTurnover() {
        return turnover;
    }


    /**
     * Sets the turnover value for this TradeOverviewVo.
     * 
     * @param turnover
     */
    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }


    /**
     * Gets the updatedBy value for this TradeOverviewVo.
     * 
     * @return updatedBy
     */
    public java.lang.String getUpdatedBy() {
        return updatedBy;
    }


    /**
     * Sets the updatedBy value for this TradeOverviewVo.
     * 
     * @param updatedBy
     */
    public void setUpdatedBy(java.lang.String updatedBy) {
        this.updatedBy = updatedBy;
    }


    /**
     * Gets the vega value for this TradeOverviewVo.
     * 
     * @return vega
     */
    public double getVega() {
        return vega;
    }


    /**
     * Sets the vega value for this TradeOverviewVo.
     * 
     * @param vega
     */
    public void setVega(double vega) {
        this.vega = vega;
    }


    /**
     * Gets the vivaldisTrade value for this TradeOverviewVo.
     * 
     * @return vivaldisTrade
     */
    public java.lang.Boolean getVivaldisTrade() {
        return vivaldisTrade;
    }


    /**
     * Sets the vivaldisTrade value for this TradeOverviewVo.
     * 
     * @param vivaldisTrade
     */
    public void setVivaldisTrade(java.lang.Boolean vivaldisTrade) {
        this.vivaldisTrade = vivaldisTrade;
    }


    /**
     * Gets the volume value for this TradeOverviewVo.
     * 
     * @return volume
     */
    public double getVolume() {
        return volume;
    }


    /**
     * Sets the volume value for this TradeOverviewVo.
     * 
     * @param volume
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }


    /**
     * Gets the volume2 value for this TradeOverviewVo.
     * 
     * @return volume2
     */
    public double getVolume2() {
        return volume2;
    }


    /**
     * Sets the volume2 value for this TradeOverviewVo.
     * 
     * @param volume2
     */
    public void setVolume2(double volume2) {
        this.volume2 = volume2;
    }


    /**
     * Gets the amendDate value for this TradeOverviewVo.
     * 
     * @return amendDate
     */
    public java.util.Calendar getAmendDate() {
        return amendDate;
    }


    /**
     * Sets the amendDate value for this TradeOverviewVo.
     * 
     * @param amendDate
     */
    public void setAmendDate(java.util.Calendar amendDate) {
        this.amendDate = amendDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TradeOverviewVo)) return false;
        TradeOverviewVo other = (TradeOverviewVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.additionalPrice == other.getAdditionalPrice() &&
            this.amendmentNpvChange == other.getAmendmentNpvChange() &&
            ((this.assets==null && other.getAssets()==null) || 
             (this.assets!=null &&
              java.util.Arrays.equals(this.assets, other.getAssets()))) &&
            ((this.automatic==null && other.getAutomatic()==null) || 
             (this.automatic!=null &&
              this.automatic.equals(other.getAutomatic()))) &&
            ((this.automaticStateByEmployeeId==null && other.getAutomaticStateByEmployeeId()==null) || 
             (this.automaticStateByEmployeeId!=null &&
              this.automaticStateByEmployeeId.equals(other.getAutomaticStateByEmployeeId()))) &&
            ((this.automaticStateByName==null && other.getAutomaticStateByName()==null) || 
             (this.automaticStateByName!=null &&
              this.automaticStateByName.equals(other.getAutomaticStateByName()))) &&
            ((this.automaticStateCode==null && other.getAutomaticStateCode()==null) || 
             (this.automaticStateCode!=null &&
              this.automaticStateCode.equals(other.getAutomaticStateCode()))) &&
            ((this.bloombergTradeId==null && other.getBloombergTradeId()==null) || 
             (this.bloombergTradeId!=null &&
              this.bloombergTradeId.equals(other.getBloombergTradeId()))) &&
            ((this.bookId==null && other.getBookId()==null) || 
             (this.bookId!=null &&
              this.bookId.equals(other.getBookId()))) &&
            ((this.buy==null && other.getBuy()==null) || 
             (this.buy!=null &&
              this.buy.equals(other.getBuy()))) &&
            ((this.cancelation==null && other.getCancelation()==null) || 
             (this.cancelation!=null &&
              this.cancelation.equals(other.getCancelation()))) &&
            ((this.company==null && other.getCompany()==null) || 
             (this.company!=null &&
              this.company.equals(other.getCompany()))) &&
            ((this.counterparty==null && other.getCounterparty()==null) || 
             (this.counterparty!=null &&
              this.counterparty.equals(other.getCounterparty()))) &&
            ((this.counterpartyReference==null && other.getCounterpartyReference()==null) || 
             (this.counterpartyReference!=null &&
              this.counterpartyReference.equals(other.getCounterpartyReference()))) &&
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
            ((this.currentPrice==null && other.getCurrentPrice()==null) || 
             (this.currentPrice!=null &&
              this.currentPrice.equals(other.getCurrentPrice()))) &&
            this.currentPriceDifference == other.getCurrentPriceDifference() &&
            ((this.currentRequest==null && other.getCurrentRequest()==null) || 
             (this.currentRequest!=null &&
              this.currentRequest.equals(other.getCurrentRequest()))) &&
            this.delta == other.getDelta() &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.exchange==null && other.getExchange()==null) || 
             (this.exchange!=null &&
              this.exchange.equals(other.getExchange()))) &&
            this.farTurnover == other.getFarTurnover() &&
            this.fwdPointsFarLeg == other.getFwdPointsFarLeg() &&
            this.fwdPointsNearLeg == other.getFwdPointsNearLeg() &&
            this.fxRate == other.getFxRate() &&
            ((this.highTurnOver==null && other.getHighTurnOver()==null) || 
             (this.highTurnOver!=null &&
              this.highTurnOver.equals(other.getHighTurnOver()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.instrument==null && other.getInstrument()==null) || 
             (this.instrument!=null &&
              this.instrument.equals(other.getInstrument()))) &&
            ((this.instrumentName==null && other.getInstrumentName()==null) || 
             (this.instrumentName!=null &&
              this.instrumentName.equals(other.getInstrumentName()))) &&
            ((this.internal==null && other.getInternal()==null) || 
             (this.internal!=null &&
              this.internal.equals(other.getInternal()))) &&
            ((this.isin==null && other.getIsin()==null) || 
             (this.isin!=null &&
              this.isin.equals(other.getIsin()))) &&
            ((this.lateEntry==null && other.getLateEntry()==null) || 
             (this.lateEntry!=null &&
              this.lateEntry.equals(other.getLateEntry()))) &&
            ((this.lowTurnOver==null && other.getLowTurnOver()==null) || 
             (this.lowTurnOver!=null &&
              this.lowTurnOver.equals(other.getLowTurnOver()))) &&
            ((this.manualCheckRequired==null && other.getManualCheckRequired()==null) || 
             (this.manualCheckRequired!=null &&
              this.manualCheckRequired.equals(other.getManualCheckRequired()))) &&
            ((this.manualStateAttachmentId==null && other.getManualStateAttachmentId()==null) || 
             (this.manualStateAttachmentId!=null &&
              this.manualStateAttachmentId.equals(other.getManualStateAttachmentId()))) &&
            ((this.manualStateAttachmentName==null && other.getManualStateAttachmentName()==null) || 
             (this.manualStateAttachmentName!=null &&
              this.manualStateAttachmentName.equals(other.getManualStateAttachmentName()))) &&
            ((this.manualStateByEmployeeId==null && other.getManualStateByEmployeeId()==null) || 
             (this.manualStateByEmployeeId!=null &&
              this.manualStateByEmployeeId.equals(other.getManualStateByEmployeeId()))) &&
            ((this.manualStateByName==null && other.getManualStateByName()==null) || 
             (this.manualStateByName!=null &&
              this.manualStateByName.equals(other.getManualStateByName()))) &&
            ((this.manualStateCode==null && other.getManualStateCode()==null) || 
             (this.manualStateCode!=null &&
              this.manualStateCode.equals(other.getManualStateCode()))) &&
            ((this.manualStateComment==null && other.getManualStateComment()==null) || 
             (this.manualStateComment!=null &&
              this.manualStateComment.equals(other.getManualStateComment()))) &&
            this.margin == other.getMargin() &&
            this.marketPointsFarLeg == other.getMarketPointsFarLeg() &&
            this.marketPointsNearLeg == other.getMarketPointsNearLeg() &&
            ((this.marketer==null && other.getMarketer()==null) || 
             (this.marketer!=null &&
              this.marketer.equals(other.getMarketer()))) &&
            ((this.maturityDate==null && other.getMaturityDate()==null) || 
             (this.maturityDate!=null &&
              this.maturityDate.equals(other.getMaturityDate()))) &&
            ((this.mdCurrency==null && other.getMdCurrency()==null) || 
             (this.mdCurrency!=null &&
              this.mdCurrency.equals(other.getMdCurrency()))) &&
            ((this.memo==null && other.getMemo()==null) || 
             (this.memo!=null &&
              this.memo.equals(other.getMemo()))) &&
            ((this.mischPrice==null && other.getMischPrice()==null) || 
             (this.mischPrice!=null &&
              this.mischPrice.equals(other.getMischPrice()))) &&
            this.nearTurnover == other.getNearTurnover() &&
            ((this.net==null && other.getNet()==null) || 
             (this.net!=null &&
              this.net.equals(other.getNet()))) &&
            ((this.parameter==null && other.getParameter()==null) || 
             (this.parameter!=null &&
              this.parameter.equals(other.getParameter()))) &&
            this.premium == other.getPremium() &&
            ((this.priceDifferences==null && other.getPriceDifferences()==null) || 
             (this.priceDifferences!=null &&
              java.util.Arrays.equals(this.priceDifferences, other.getPriceDifferences()))) &&
            this.priceTolerance == other.getPriceTolerance() &&
            ((this.priceToleranceViolation==null && other.getPriceToleranceViolation()==null) || 
             (this.priceToleranceViolation!=null &&
              this.priceToleranceViolation.equals(other.getPriceToleranceViolation()))) &&
            ((this.prices==null && other.getPrices()==null) || 
             (this.prices!=null &&
              java.util.Arrays.equals(this.prices, other.getPrices()))) &&
            ((this.reclamationCheckRequired==null && other.getReclamationCheckRequired()==null) || 
             (this.reclamationCheckRequired!=null &&
              this.reclamationCheckRequired.equals(other.getReclamationCheckRequired()))) &&
            ((this.reclamationClosedComment==null && other.getReclamationClosedComment()==null) || 
             (this.reclamationClosedComment!=null &&
              this.reclamationClosedComment.equals(other.getReclamationClosedComment()))) &&
            ((this.reclamationIsClosed==null && other.getReclamationIsClosed()==null) || 
             (this.reclamationIsClosed!=null &&
              this.reclamationIsClosed.equals(other.getReclamationIsClosed()))) &&
            ((this.reclamationStateAttachmentId==null && other.getReclamationStateAttachmentId()==null) || 
             (this.reclamationStateAttachmentId!=null &&
              this.reclamationStateAttachmentId.equals(other.getReclamationStateAttachmentId()))) &&
            ((this.reclamationStateAttachmentName==null && other.getReclamationStateAttachmentName()==null) || 
             (this.reclamationStateAttachmentName!=null &&
              this.reclamationStateAttachmentName.equals(other.getReclamationStateAttachmentName()))) &&
            ((this.reclamationStateByEmployeeId==null && other.getReclamationStateByEmployeeId()==null) || 
             (this.reclamationStateByEmployeeId!=null &&
              this.reclamationStateByEmployeeId.equals(other.getReclamationStateByEmployeeId()))) &&
            ((this.reclamationStateByName==null && other.getReclamationStateByName()==null) || 
             (this.reclamationStateByName!=null &&
              this.reclamationStateByName.equals(other.getReclamationStateByName()))) &&
            ((this.reclamationStateCode==null && other.getReclamationStateCode()==null) || 
             (this.reclamationStateCode!=null &&
              this.reclamationStateCode.equals(other.getReclamationStateCode()))) &&
            ((this.reclamationStateComment==null && other.getReclamationStateComment()==null) || 
             (this.reclamationStateComment!=null &&
              this.reclamationStateComment.equals(other.getReclamationStateComment()))) &&
            ((this.recordType==null && other.getRecordType()==null) || 
             (this.recordType!=null &&
              this.recordType.equals(other.getRecordType()))) &&
            this.reminderLevel == other.getReminderLevel() &&
            ((this.reportLocation==null && other.getReportLocation()==null) || 
             (this.reportLocation!=null &&
              this.reportLocation.equals(other.getReportLocation()))) &&
            ((this.requests==null && other.getRequests()==null) || 
             (this.requests!=null &&
              java.util.Arrays.equals(this.requests, other.getRequests()))) &&
            ((this.settlementDate==null && other.getSettlementDate()==null) || 
             (this.settlementDate!=null &&
              this.settlementDate.equals(other.getSettlementDate()))) &&
            ((this.smallCustomer==null && other.getSmallCustomer()==null) || 
             (this.smallCustomer!=null &&
              this.smallCustomer.equals(other.getSmallCustomer()))) &&
            ((this.sourceSystemCode==null && other.getSourceSystemCode()==null) || 
             (this.sourceSystemCode!=null &&
              this.sourceSystemCode.equals(other.getSourceSystemCode()))) &&
            ((this.sparkasse==null && other.getSparkasse()==null) || 
             (this.sparkasse!=null &&
              this.sparkasse.equals(other.getSparkasse()))) &&
            ((this.storno==null && other.getStorno()==null) || 
             (this.storno!=null &&
              this.storno.equals(other.getStorno()))) &&
            ((this.stornoGroup==null && other.getStornoGroup()==null) || 
             (this.stornoGroup!=null &&
              this.stornoGroup.equals(other.getStornoGroup()))) &&
            ((this.stornoGroupId==null && other.getStornoGroupId()==null) || 
             (this.stornoGroupId!=null &&
              this.stornoGroupId.equals(other.getStornoGroupId()))) &&
            ((this.structure==null && other.getStructure()==null) || 
             (this.structure!=null &&
              this.structure.equals(other.getStructure()))) &&
            ((this.subType==null && other.getSubType()==null) || 
             (this.subType!=null &&
              this.subType.equals(other.getSubType()))) &&
            ((this.systemDate==null && other.getSystemDate()==null) || 
             (this.systemDate!=null &&
              this.systemDate.equals(other.getSystemDate()))) &&
            this.theoreticPrice == other.getTheoreticPrice() &&
            ((this.tradeCategory==null && other.getTradeCategory()==null) || 
             (this.tradeCategory!=null &&
              this.tradeCategory.equals(other.getTradeCategory()))) &&
            ((this.tradeDate==null && other.getTradeDate()==null) || 
             (this.tradeDate!=null &&
              this.tradeDate.equals(other.getTradeDate()))) &&
            ((this.tradeId==null && other.getTradeId()==null) || 
             (this.tradeId!=null &&
              this.tradeId.equals(other.getTradeId()))) &&
            this.tradePrice == other.getTradePrice() &&
            ((this.tradeState==null && other.getTradeState()==null) || 
             (this.tradeState!=null &&
              this.tradeState.equals(other.getTradeState()))) &&
            ((this.tradeType==null && other.getTradeType()==null) || 
             (this.tradeType!=null &&
              this.tradeType.equals(other.getTradeType()))) &&
            ((this.traderId==null && other.getTraderId()==null) || 
             (this.traderId!=null &&
              this.traderId.equals(other.getTraderId()))) &&
            ((this.traderLocation==null && other.getTraderLocation()==null) || 
             (this.traderLocation!=null &&
              this.traderLocation.equals(other.getTraderLocation()))) &&
            ((this.traderName==null && other.getTraderName()==null) || 
             (this.traderName!=null &&
              this.traderName.equals(other.getTraderName()))) &&
            this.turnover == other.getTurnover() &&
            ((this.updatedBy==null && other.getUpdatedBy()==null) || 
             (this.updatedBy!=null &&
              this.updatedBy.equals(other.getUpdatedBy()))) &&
            this.vega == other.getVega() &&
            ((this.vivaldisTrade==null && other.getVivaldisTrade()==null) || 
             (this.vivaldisTrade!=null &&
              this.vivaldisTrade.equals(other.getVivaldisTrade()))) &&
            this.volume == other.getVolume() &&
            this.volume2 == other.getVolume2() &&
            ((this.amendDate==null && other.getAmendDate()==null) || 
             (this.amendDate!=null &&
              this.amendDate.equals(other.getAmendDate())));
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
        _hashCode += new Double(getAdditionalPrice()).hashCode();
        _hashCode += new Double(getAmendmentNpvChange()).hashCode();
        if (getAssets() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAssets());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAssets(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAutomatic() != null) {
            _hashCode += getAutomatic().hashCode();
        }
        if (getAutomaticStateByEmployeeId() != null) {
            _hashCode += getAutomaticStateByEmployeeId().hashCode();
        }
        if (getAutomaticStateByName() != null) {
            _hashCode += getAutomaticStateByName().hashCode();
        }
        if (getAutomaticStateCode() != null) {
            _hashCode += getAutomaticStateCode().hashCode();
        }
        if (getBloombergTradeId() != null) {
            _hashCode += getBloombergTradeId().hashCode();
        }
        if (getBookId() != null) {
            _hashCode += getBookId().hashCode();
        }
        if (getBuy() != null) {
            _hashCode += getBuy().hashCode();
        }
        if (getCancelation() != null) {
            _hashCode += getCancelation().hashCode();
        }
        if (getCompany() != null) {
            _hashCode += getCompany().hashCode();
        }
        if (getCounterparty() != null) {
            _hashCode += getCounterparty().hashCode();
        }
        if (getCounterpartyReference() != null) {
            _hashCode += getCounterpartyReference().hashCode();
        }
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
        }
        if (getCurrentPrice() != null) {
            _hashCode += getCurrentPrice().hashCode();
        }
        _hashCode += new Double(getCurrentPriceDifference()).hashCode();
        if (getCurrentRequest() != null) {
            _hashCode += getCurrentRequest().hashCode();
        }
        _hashCode += new Double(getDelta()).hashCode();
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getExchange() != null) {
            _hashCode += getExchange().hashCode();
        }
        _hashCode += new Double(getFarTurnover()).hashCode();
        _hashCode += new Double(getFwdPointsFarLeg()).hashCode();
        _hashCode += new Double(getFwdPointsNearLeg()).hashCode();
        _hashCode += new Double(getFxRate()).hashCode();
        if (getHighTurnOver() != null) {
            _hashCode += getHighTurnOver().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getInstrument() != null) {
            _hashCode += getInstrument().hashCode();
        }
        if (getInstrumentName() != null) {
            _hashCode += getInstrumentName().hashCode();
        }
        if (getInternal() != null) {
            _hashCode += getInternal().hashCode();
        }
        if (getIsin() != null) {
            _hashCode += getIsin().hashCode();
        }
        if (getLateEntry() != null) {
            _hashCode += getLateEntry().hashCode();
        }
        if (getLowTurnOver() != null) {
            _hashCode += getLowTurnOver().hashCode();
        }
        if (getManualCheckRequired() != null) {
            _hashCode += getManualCheckRequired().hashCode();
        }
        if (getManualStateAttachmentId() != null) {
            _hashCode += getManualStateAttachmentId().hashCode();
        }
        if (getManualStateAttachmentName() != null) {
            _hashCode += getManualStateAttachmentName().hashCode();
        }
        if (getManualStateByEmployeeId() != null) {
            _hashCode += getManualStateByEmployeeId().hashCode();
        }
        if (getManualStateByName() != null) {
            _hashCode += getManualStateByName().hashCode();
        }
        if (getManualStateCode() != null) {
            _hashCode += getManualStateCode().hashCode();
        }
        if (getManualStateComment() != null) {
            _hashCode += getManualStateComment().hashCode();
        }
        _hashCode += new Double(getMargin()).hashCode();
        _hashCode += new Double(getMarketPointsFarLeg()).hashCode();
        _hashCode += new Double(getMarketPointsNearLeg()).hashCode();
        if (getMarketer() != null) {
            _hashCode += getMarketer().hashCode();
        }
        if (getMaturityDate() != null) {
            _hashCode += getMaturityDate().hashCode();
        }
        if (getMdCurrency() != null) {
            _hashCode += getMdCurrency().hashCode();
        }
        if (getMemo() != null) {
            _hashCode += getMemo().hashCode();
        }
        if (getMischPrice() != null) {
            _hashCode += getMischPrice().hashCode();
        }
        _hashCode += new Double(getNearTurnover()).hashCode();
        if (getNet() != null) {
            _hashCode += getNet().hashCode();
        }
        if (getParameter() != null) {
            _hashCode += getParameter().hashCode();
        }
        _hashCode += new Double(getPremium()).hashCode();
        if (getPriceDifferences() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPriceDifferences());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPriceDifferences(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Double(getPriceTolerance()).hashCode();
        if (getPriceToleranceViolation() != null) {
            _hashCode += getPriceToleranceViolation().hashCode();
        }
        if (getPrices() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPrices());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPrices(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReclamationCheckRequired() != null) {
            _hashCode += getReclamationCheckRequired().hashCode();
        }
        if (getReclamationClosedComment() != null) {
            _hashCode += getReclamationClosedComment().hashCode();
        }
        if (getReclamationIsClosed() != null) {
            _hashCode += getReclamationIsClosed().hashCode();
        }
        if (getReclamationStateAttachmentId() != null) {
            _hashCode += getReclamationStateAttachmentId().hashCode();
        }
        if (getReclamationStateAttachmentName() != null) {
            _hashCode += getReclamationStateAttachmentName().hashCode();
        }
        if (getReclamationStateByEmployeeId() != null) {
            _hashCode += getReclamationStateByEmployeeId().hashCode();
        }
        if (getReclamationStateByName() != null) {
            _hashCode += getReclamationStateByName().hashCode();
        }
        if (getReclamationStateCode() != null) {
            _hashCode += getReclamationStateCode().hashCode();
        }
        if (getReclamationStateComment() != null) {
            _hashCode += getReclamationStateComment().hashCode();
        }
        if (getRecordType() != null) {
            _hashCode += getRecordType().hashCode();
        }
        _hashCode += getReminderLevel();
        if (getReportLocation() != null) {
            _hashCode += getReportLocation().hashCode();
        }
        if (getRequests() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRequests());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRequests(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSettlementDate() != null) {
            _hashCode += getSettlementDate().hashCode();
        }
        if (getSmallCustomer() != null) {
            _hashCode += getSmallCustomer().hashCode();
        }
        if (getSourceSystemCode() != null) {
            _hashCode += getSourceSystemCode().hashCode();
        }
        if (getSparkasse() != null) {
            _hashCode += getSparkasse().hashCode();
        }
        if (getStorno() != null) {
            _hashCode += getStorno().hashCode();
        }
        if (getStornoGroup() != null) {
            _hashCode += getStornoGroup().hashCode();
        }
        if (getStornoGroupId() != null) {
            _hashCode += getStornoGroupId().hashCode();
        }
        if (getStructure() != null) {
            _hashCode += getStructure().hashCode();
        }
        if (getSubType() != null) {
            _hashCode += getSubType().hashCode();
        }
        if (getSystemDate() != null) {
            _hashCode += getSystemDate().hashCode();
        }
        _hashCode += new Double(getTheoreticPrice()).hashCode();
        if (getTradeCategory() != null) {
            _hashCode += getTradeCategory().hashCode();
        }
        if (getTradeDate() != null) {
            _hashCode += getTradeDate().hashCode();
        }
        if (getTradeId() != null) {
            _hashCode += getTradeId().hashCode();
        }
        _hashCode += new Double(getTradePrice()).hashCode();
        if (getTradeState() != null) {
            _hashCode += getTradeState().hashCode();
        }
        if (getTradeType() != null) {
            _hashCode += getTradeType().hashCode();
        }
        if (getTraderId() != null) {
            _hashCode += getTraderId().hashCode();
        }
        if (getTraderLocation() != null) {
            _hashCode += getTraderLocation().hashCode();
        }
        if (getTraderName() != null) {
            _hashCode += getTraderName().hashCode();
        }
        _hashCode += new Double(getTurnover()).hashCode();
        if (getUpdatedBy() != null) {
            _hashCode += getUpdatedBy().hashCode();
        }
        _hashCode += new Double(getVega()).hashCode();
        if (getVivaldisTrade() != null) {
            _hashCode += getVivaldisTrade().hashCode();
        }
        _hashCode += new Double(getVolume()).hashCode();
        _hashCode += new Double(getVolume2()).hashCode();
        if (getAmendDate() != null) {
            _hashCode += getAmendDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TradeOverviewVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "TradeOverviewVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "additionalPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amendmentNpvChange");
        elemField.setXmlName(new javax.xml.namespace.QName("", "amendmentNpvChange"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assets");
        elemField.setXmlName(new javax.xml.namespace.QName("", "assets"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "AssetVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automatic");
        elemField.setXmlName(new javax.xml.namespace.QName("", "automatic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automaticStateByEmployeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "automaticStateByEmployeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automaticStateByName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "automaticStateByName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automaticStateCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "automaticStateCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bloombergTradeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bloombergTradeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bookId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "buy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cancelation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("company");
        elemField.setXmlName(new javax.xml.namespace.QName("", "company"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("counterparty");
        elemField.setXmlName(new javax.xml.namespace.QName("", "counterparty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("counterpartyReference");
        elemField.setXmlName(new javax.xml.namespace.QName("", "counterpartyReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currentPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "PriceVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentPriceDifference");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currentPriceDifference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currentRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "RequestVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "delta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exchange");
        elemField.setXmlName(new javax.xml.namespace.QName("", "exchange"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("farTurnover");
        elemField.setXmlName(new javax.xml.namespace.QName("", "farTurnover"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fwdPointsFarLeg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fwdPointsFarLeg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fwdPointsNearLeg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fwdPointsNearLeg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fxRate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fxRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("highTurnOver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "highTurnOver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instrument");
        elemField.setXmlName(new javax.xml.namespace.QName("", "instrument"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instrumentName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "instrumentName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("internal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "internal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lateEntry");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lateEntry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lowTurnOver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lowTurnOver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualCheckRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualCheckRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualStateAttachmentId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualStateAttachmentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualStateAttachmentName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualStateAttachmentName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualStateByEmployeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualStateByEmployeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualStateByName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualStateByName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualStateCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualStateCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualStateComment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualStateComment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("margin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "margin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("marketPointsFarLeg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "marketPointsFarLeg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("marketPointsNearLeg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "marketPointsNearLeg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("marketer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "marketer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maturityDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maturityDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mdCurrency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mdCurrency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "memo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mischPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mischPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nearTurnover");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nearTurnover"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("net");
        elemField.setXmlName(new javax.xml.namespace.QName("", "net"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parameter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "TradeParameterVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("premium");
        elemField.setXmlName(new javax.xml.namespace.QName("", "premium"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceDifferences");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceDifferences"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceTolerance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceTolerance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceToleranceViolation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceToleranceViolation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prices");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prices"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "PriceVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationCheckRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationCheckRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationClosedComment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationClosedComment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationIsClosed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationIsClosed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationStateAttachmentId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationStateAttachmentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationStateAttachmentName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationStateAttachmentName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationStateByEmployeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationStateByEmployeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationStateByName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationStateByName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationStateCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationStateCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationStateComment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationStateComment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recordType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reminderLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reminderLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reportLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requests");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requests"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "RequestVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("settlementDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "settlementDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("smallCustomer");
        elemField.setXmlName(new javax.xml.namespace.QName("", "smallCustomer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceSystemCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceSystemCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sparkasse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sparkasse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("storno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "storno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stornoGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stornoGroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stornoGroupId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stornoGroupId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("structure");
        elemField.setXmlName(new javax.xml.namespace.QName("", "structure"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("systemDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "systemDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("theoreticPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "theoreticPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeCategory");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeCategory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradePrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradePrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeState");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeState"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("traderId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "traderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("traderLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "traderLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("traderName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "traderName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("turnover");
        elemField.setXmlName(new javax.xml.namespace.QName("", "turnover"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updatedBy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "updatedBy"));
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
        elemField.setFieldName("vivaldisTrade");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vivaldisTrade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("volume");
        elemField.setXmlName(new javax.xml.namespace.QName("", "volume"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("volume2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "volume2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amendDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "amendDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
