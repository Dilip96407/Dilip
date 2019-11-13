/**
 * RepoTradeOverviewVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class RepoTradeOverviewVo  extends de.westlb.mgb.client.server.vo.TradeOverviewVo  implements java.io.Serializable {
    private double basePointDifference;

    private double basePointTolerance;

    private double bondAccruedInterest;

    private java.lang.String customerType;

    private double dealAccruedInterest;

    private java.util.Calendar doneDate;

    private double endCash;

    private java.util.Calendar endDate;

    private double foreignExchangeRate;

    private java.lang.String instrumentType;

    private double marketPrice;

    private double marketPriceUnderlying;

    private double maturityDays;

    private double npv;

    private boolean openEnd;

    private java.lang.String portfolio;

    private double profitLossEffect;

    private java.lang.String repoMarketType;

    private double repoRate;

    private double startCash;

    private java.util.Calendar startDate;

    private double startPrice;

    private java.lang.String underlyingType;

    private java.lang.String underlyingValueGroup;

    private java.util.Calendar verifyDate;

    public RepoTradeOverviewVo() {
    }

    public RepoTradeOverviewVo(
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
           java.util.Calendar amendDate,
           double basePointDifference,
           double basePointTolerance,
           double bondAccruedInterest,
           java.lang.String customerType,
           double dealAccruedInterest,
           java.util.Calendar doneDate,
           double endCash,
           java.util.Calendar endDate,
           double foreignExchangeRate,
           java.lang.String instrumentType,
           double marketPrice,
           double marketPriceUnderlying,
           double maturityDays,
           double npv,
           boolean openEnd,
           java.lang.String portfolio,
           double profitLossEffect,
           java.lang.String repoMarketType,
           double repoRate,
           double startCash,
           java.util.Calendar startDate,
           double startPrice,
           java.lang.String underlyingType,
           java.lang.String underlyingValueGroup,
           java.util.Calendar verifyDate) {
        super(
            additionalPrice,
            amendmentNpvChange,
            assets,
            automatic,
            automaticStateByEmployeeId,
            automaticStateByName,
            automaticStateCode,
            bloombergTradeId,
            bookId,
            buy,
            cancelation,
            company,
            counterparty,
            counterpartyReference,
            currency,
            currentPrice,
            currentPriceDifference,
            currentRequest,
            delta,
            description,
            exchange,
            farTurnover,
            fwdPointsFarLeg,
            fwdPointsNearLeg,
            fxRate,
            highTurnOver,
            id,
            instrument,
            instrumentName,
            internal,
            isin,
            lateEntry,
            lowTurnOver,
            manualCheckRequired,
            manualStateAttachmentId,
            manualStateAttachmentName,
            manualStateByEmployeeId,
            manualStateByName,
            manualStateCode,
            manualStateComment,
            margin,
            marketPointsFarLeg,
            marketPointsNearLeg,
            marketer,
            maturityDate,
            mdCurrency,
            memo,
            mischPrice,
            nearTurnover,
            net,
            parameter,
            premium,
            priceDifferences,
            priceTolerance,
            priceToleranceViolation,
            prices,
            reclamationCheckRequired,
            reclamationClosedComment,
            reclamationIsClosed,
            reclamationStateAttachmentId,
            reclamationStateAttachmentName,
            reclamationStateByEmployeeId,
            reclamationStateByName,
            reclamationStateCode,
            reclamationStateComment,
            recordType,
            reminderLevel,
            reportLocation,
            requests,
            settlementDate,
            smallCustomer,
            sourceSystemCode,
            sparkasse,
            storno,
            stornoGroup,
            stornoGroupId,
            structure,
            subType,
            systemDate,
            theoreticPrice,
            tradeCategory,
            tradeDate,
            tradeId,
            tradePrice,
            tradeState,
            tradeType,
            traderId,
            traderLocation,
            traderName,
            turnover,
            updatedBy,
            vega,
            vivaldisTrade,
            volume,
            volume2,
            amendDate);
        this.basePointDifference = basePointDifference;
        this.basePointTolerance = basePointTolerance;
        this.bondAccruedInterest = bondAccruedInterest;
        this.customerType = customerType;
        this.dealAccruedInterest = dealAccruedInterest;
        this.doneDate = doneDate;
        this.endCash = endCash;
        this.endDate = endDate;
        this.foreignExchangeRate = foreignExchangeRate;
        this.instrumentType = instrumentType;
        this.marketPrice = marketPrice;
        this.marketPriceUnderlying = marketPriceUnderlying;
        this.maturityDays = maturityDays;
        this.npv = npv;
        this.openEnd = openEnd;
        this.portfolio = portfolio;
        this.profitLossEffect = profitLossEffect;
        this.repoMarketType = repoMarketType;
        this.repoRate = repoRate;
        this.startCash = startCash;
        this.startDate = startDate;
        this.startPrice = startPrice;
        this.underlyingType = underlyingType;
        this.underlyingValueGroup = underlyingValueGroup;
        this.verifyDate = verifyDate;
    }


    /**
     * Gets the basePointDifference value for this RepoTradeOverviewVo.
     * 
     * @return basePointDifference
     */
    public double getBasePointDifference() {
        return basePointDifference;
    }


    /**
     * Sets the basePointDifference value for this RepoTradeOverviewVo.
     * 
     * @param basePointDifference
     */
    public void setBasePointDifference(double basePointDifference) {
        this.basePointDifference = basePointDifference;
    }


    /**
     * Gets the basePointTolerance value for this RepoTradeOverviewVo.
     * 
     * @return basePointTolerance
     */
    public double getBasePointTolerance() {
        return basePointTolerance;
    }


    /**
     * Sets the basePointTolerance value for this RepoTradeOverviewVo.
     * 
     * @param basePointTolerance
     */
    public void setBasePointTolerance(double basePointTolerance) {
        this.basePointTolerance = basePointTolerance;
    }


    /**
     * Gets the bondAccruedInterest value for this RepoTradeOverviewVo.
     * 
     * @return bondAccruedInterest
     */
    public double getBondAccruedInterest() {
        return bondAccruedInterest;
    }


    /**
     * Sets the bondAccruedInterest value for this RepoTradeOverviewVo.
     * 
     * @param bondAccruedInterest
     */
    public void setBondAccruedInterest(double bondAccruedInterest) {
        this.bondAccruedInterest = bondAccruedInterest;
    }


    /**
     * Gets the customerType value for this RepoTradeOverviewVo.
     * 
     * @return customerType
     */
    public java.lang.String getCustomerType() {
        return customerType;
    }


    /**
     * Sets the customerType value for this RepoTradeOverviewVo.
     * 
     * @param customerType
     */
    public void setCustomerType(java.lang.String customerType) {
        this.customerType = customerType;
    }


    /**
     * Gets the dealAccruedInterest value for this RepoTradeOverviewVo.
     * 
     * @return dealAccruedInterest
     */
    public double getDealAccruedInterest() {
        return dealAccruedInterest;
    }


    /**
     * Sets the dealAccruedInterest value for this RepoTradeOverviewVo.
     * 
     * @param dealAccruedInterest
     */
    public void setDealAccruedInterest(double dealAccruedInterest) {
        this.dealAccruedInterest = dealAccruedInterest;
    }


    /**
     * Gets the doneDate value for this RepoTradeOverviewVo.
     * 
     * @return doneDate
     */
    public java.util.Calendar getDoneDate() {
        return doneDate;
    }


    /**
     * Sets the doneDate value for this RepoTradeOverviewVo.
     * 
     * @param doneDate
     */
    public void setDoneDate(java.util.Calendar doneDate) {
        this.doneDate = doneDate;
    }


    /**
     * Gets the endCash value for this RepoTradeOverviewVo.
     * 
     * @return endCash
     */
    public double getEndCash() {
        return endCash;
    }


    /**
     * Sets the endCash value for this RepoTradeOverviewVo.
     * 
     * @param endCash
     */
    public void setEndCash(double endCash) {
        this.endCash = endCash;
    }


    /**
     * Gets the endDate value for this RepoTradeOverviewVo.
     * 
     * @return endDate
     */
    public java.util.Calendar getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this RepoTradeOverviewVo.
     * 
     * @param endDate
     */
    public void setEndDate(java.util.Calendar endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the foreignExchangeRate value for this RepoTradeOverviewVo.
     * 
     * @return foreignExchangeRate
     */
    public double getForeignExchangeRate() {
        return foreignExchangeRate;
    }


    /**
     * Sets the foreignExchangeRate value for this RepoTradeOverviewVo.
     * 
     * @param foreignExchangeRate
     */
    public void setForeignExchangeRate(double foreignExchangeRate) {
        this.foreignExchangeRate = foreignExchangeRate;
    }


    /**
     * Gets the instrumentType value for this RepoTradeOverviewVo.
     * 
     * @return instrumentType
     */
    public java.lang.String getInstrumentType() {
        return instrumentType;
    }


    /**
     * Sets the instrumentType value for this RepoTradeOverviewVo.
     * 
     * @param instrumentType
     */
    public void setInstrumentType(java.lang.String instrumentType) {
        this.instrumentType = instrumentType;
    }


    /**
     * Gets the marketPrice value for this RepoTradeOverviewVo.
     * 
     * @return marketPrice
     */
    public double getMarketPrice() {
        return marketPrice;
    }


    /**
     * Sets the marketPrice value for this RepoTradeOverviewVo.
     * 
     * @param marketPrice
     */
    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }


    /**
     * Gets the marketPriceUnderlying value for this RepoTradeOverviewVo.
     * 
     * @return marketPriceUnderlying
     */
    public double getMarketPriceUnderlying() {
        return marketPriceUnderlying;
    }


    /**
     * Sets the marketPriceUnderlying value for this RepoTradeOverviewVo.
     * 
     * @param marketPriceUnderlying
     */
    public void setMarketPriceUnderlying(double marketPriceUnderlying) {
        this.marketPriceUnderlying = marketPriceUnderlying;
    }


    /**
     * Gets the maturityDays value for this RepoTradeOverviewVo.
     * 
     * @return maturityDays
     */
    public double getMaturityDays() {
        return maturityDays;
    }


    /**
     * Sets the maturityDays value for this RepoTradeOverviewVo.
     * 
     * @param maturityDays
     */
    public void setMaturityDays(double maturityDays) {
        this.maturityDays = maturityDays;
    }


    /**
     * Gets the npv value for this RepoTradeOverviewVo.
     * 
     * @return npv
     */
    public double getNpv() {
        return npv;
    }


    /**
     * Sets the npv value for this RepoTradeOverviewVo.
     * 
     * @param npv
     */
    public void setNpv(double npv) {
        this.npv = npv;
    }


    /**
     * Gets the openEnd value for this RepoTradeOverviewVo.
     * 
     * @return openEnd
     */
    public boolean isOpenEnd() {
        return openEnd;
    }


    /**
     * Sets the openEnd value for this RepoTradeOverviewVo.
     * 
     * @param openEnd
     */
    public void setOpenEnd(boolean openEnd) {
        this.openEnd = openEnd;
    }


    /**
     * Gets the portfolio value for this RepoTradeOverviewVo.
     * 
     * @return portfolio
     */
    public java.lang.String getPortfolio() {
        return portfolio;
    }


    /**
     * Sets the portfolio value for this RepoTradeOverviewVo.
     * 
     * @param portfolio
     */
    public void setPortfolio(java.lang.String portfolio) {
        this.portfolio = portfolio;
    }


    /**
     * Gets the profitLossEffect value for this RepoTradeOverviewVo.
     * 
     * @return profitLossEffect
     */
    public double getProfitLossEffect() {
        return profitLossEffect;
    }


    /**
     * Sets the profitLossEffect value for this RepoTradeOverviewVo.
     * 
     * @param profitLossEffect
     */
    public void setProfitLossEffect(double profitLossEffect) {
        this.profitLossEffect = profitLossEffect;
    }


    /**
     * Gets the repoMarketType value for this RepoTradeOverviewVo.
     * 
     * @return repoMarketType
     */
    public java.lang.String getRepoMarketType() {
        return repoMarketType;
    }


    /**
     * Sets the repoMarketType value for this RepoTradeOverviewVo.
     * 
     * @param repoMarketType
     */
    public void setRepoMarketType(java.lang.String repoMarketType) {
        this.repoMarketType = repoMarketType;
    }


    /**
     * Gets the repoRate value for this RepoTradeOverviewVo.
     * 
     * @return repoRate
     */
    public double getRepoRate() {
        return repoRate;
    }


    /**
     * Sets the repoRate value for this RepoTradeOverviewVo.
     * 
     * @param repoRate
     */
    public void setRepoRate(double repoRate) {
        this.repoRate = repoRate;
    }


    /**
     * Gets the startCash value for this RepoTradeOverviewVo.
     * 
     * @return startCash
     */
    public double getStartCash() {
        return startCash;
    }


    /**
     * Sets the startCash value for this RepoTradeOverviewVo.
     * 
     * @param startCash
     */
    public void setStartCash(double startCash) {
        this.startCash = startCash;
    }


    /**
     * Gets the startDate value for this RepoTradeOverviewVo.
     * 
     * @return startDate
     */
    public java.util.Calendar getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this RepoTradeOverviewVo.
     * 
     * @param startDate
     */
    public void setStartDate(java.util.Calendar startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the startPrice value for this RepoTradeOverviewVo.
     * 
     * @return startPrice
     */
    public double getStartPrice() {
        return startPrice;
    }


    /**
     * Sets the startPrice value for this RepoTradeOverviewVo.
     * 
     * @param startPrice
     */
    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }


    /**
     * Gets the underlyingType value for this RepoTradeOverviewVo.
     * 
     * @return underlyingType
     */
    public java.lang.String getUnderlyingType() {
        return underlyingType;
    }


    /**
     * Sets the underlyingType value for this RepoTradeOverviewVo.
     * 
     * @param underlyingType
     */
    public void setUnderlyingType(java.lang.String underlyingType) {
        this.underlyingType = underlyingType;
    }


    /**
     * Gets the underlyingValueGroup value for this RepoTradeOverviewVo.
     * 
     * @return underlyingValueGroup
     */
    public java.lang.String getUnderlyingValueGroup() {
        return underlyingValueGroup;
    }


    /**
     * Sets the underlyingValueGroup value for this RepoTradeOverviewVo.
     * 
     * @param underlyingValueGroup
     */
    public void setUnderlyingValueGroup(java.lang.String underlyingValueGroup) {
        this.underlyingValueGroup = underlyingValueGroup;
    }


    /**
     * Gets the verifyDate value for this RepoTradeOverviewVo.
     * 
     * @return verifyDate
     */
    public java.util.Calendar getVerifyDate() {
        return verifyDate;
    }


    /**
     * Sets the verifyDate value for this RepoTradeOverviewVo.
     * 
     * @param verifyDate
     */
    public void setVerifyDate(java.util.Calendar verifyDate) {
        this.verifyDate = verifyDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RepoTradeOverviewVo)) return false;
        RepoTradeOverviewVo other = (RepoTradeOverviewVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.basePointDifference == other.getBasePointDifference() &&
            this.basePointTolerance == other.getBasePointTolerance() &&
            this.bondAccruedInterest == other.getBondAccruedInterest() &&
            ((this.customerType==null && other.getCustomerType()==null) || 
             (this.customerType!=null &&
              this.customerType.equals(other.getCustomerType()))) &&
            this.dealAccruedInterest == other.getDealAccruedInterest() &&
            ((this.doneDate==null && other.getDoneDate()==null) || 
             (this.doneDate!=null &&
              this.doneDate.equals(other.getDoneDate()))) &&
            this.endCash == other.getEndCash() &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            this.foreignExchangeRate == other.getForeignExchangeRate() &&
            ((this.instrumentType==null && other.getInstrumentType()==null) || 
             (this.instrumentType!=null &&
              this.instrumentType.equals(other.getInstrumentType()))) &&
            this.marketPrice == other.getMarketPrice() &&
            this.marketPriceUnderlying == other.getMarketPriceUnderlying() &&
            this.maturityDays == other.getMaturityDays() &&
            this.npv == other.getNpv() &&
            this.openEnd == other.isOpenEnd() &&
            ((this.portfolio==null && other.getPortfolio()==null) || 
             (this.portfolio!=null &&
              this.portfolio.equals(other.getPortfolio()))) &&
            this.profitLossEffect == other.getProfitLossEffect() &&
            ((this.repoMarketType==null && other.getRepoMarketType()==null) || 
             (this.repoMarketType!=null &&
              this.repoMarketType.equals(other.getRepoMarketType()))) &&
            this.repoRate == other.getRepoRate() &&
            this.startCash == other.getStartCash() &&
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            this.startPrice == other.getStartPrice() &&
            ((this.underlyingType==null && other.getUnderlyingType()==null) || 
             (this.underlyingType!=null &&
              this.underlyingType.equals(other.getUnderlyingType()))) &&
            ((this.underlyingValueGroup==null && other.getUnderlyingValueGroup()==null) || 
             (this.underlyingValueGroup!=null &&
              this.underlyingValueGroup.equals(other.getUnderlyingValueGroup()))) &&
            ((this.verifyDate==null && other.getVerifyDate()==null) || 
             (this.verifyDate!=null &&
              this.verifyDate.equals(other.getVerifyDate())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        _hashCode += new Double(getBasePointDifference()).hashCode();
        _hashCode += new Double(getBasePointTolerance()).hashCode();
        _hashCode += new Double(getBondAccruedInterest()).hashCode();
        if (getCustomerType() != null) {
            _hashCode += getCustomerType().hashCode();
        }
        _hashCode += new Double(getDealAccruedInterest()).hashCode();
        if (getDoneDate() != null) {
            _hashCode += getDoneDate().hashCode();
        }
        _hashCode += new Double(getEndCash()).hashCode();
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        _hashCode += new Double(getForeignExchangeRate()).hashCode();
        if (getInstrumentType() != null) {
            _hashCode += getInstrumentType().hashCode();
        }
        _hashCode += new Double(getMarketPrice()).hashCode();
        _hashCode += new Double(getMarketPriceUnderlying()).hashCode();
        _hashCode += new Double(getMaturityDays()).hashCode();
        _hashCode += new Double(getNpv()).hashCode();
        _hashCode += (isOpenEnd() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getPortfolio() != null) {
            _hashCode += getPortfolio().hashCode();
        }
        _hashCode += new Double(getProfitLossEffect()).hashCode();
        if (getRepoMarketType() != null) {
            _hashCode += getRepoMarketType().hashCode();
        }
        _hashCode += new Double(getRepoRate()).hashCode();
        _hashCode += new Double(getStartCash()).hashCode();
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        _hashCode += new Double(getStartPrice()).hashCode();
        if (getUnderlyingType() != null) {
            _hashCode += getUnderlyingType().hashCode();
        }
        if (getUnderlyingValueGroup() != null) {
            _hashCode += getUnderlyingValueGroup().hashCode();
        }
        if (getVerifyDate() != null) {
            _hashCode += getVerifyDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RepoTradeOverviewVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "RepoTradeOverviewVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("basePointDifference");
        elemField.setXmlName(new javax.xml.namespace.QName("", "basePointDifference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("basePointTolerance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "basePointTolerance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bondAccruedInterest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bondAccruedInterest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "customerType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dealAccruedInterest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dealAccruedInterest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("doneDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "doneDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endCash");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endCash"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foreignExchangeRate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "foreignExchangeRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instrumentType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "instrumentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("marketPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "marketPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("marketPriceUnderlying");
        elemField.setXmlName(new javax.xml.namespace.QName("", "marketPriceUnderlying"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maturityDays");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maturityDays"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("npv");
        elemField.setXmlName(new javax.xml.namespace.QName("", "npv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openEnd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "openEnd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("portfolio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "portfolio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profitLossEffect");
        elemField.setXmlName(new javax.xml.namespace.QName("", "profitLossEffect"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repoMarketType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "repoMarketType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repoRate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "repoRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startCash");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startCash"));
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
        elemField.setFieldName("startPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("underlyingType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "underlyingType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("underlyingValueGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("", "underlyingValueGroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("verifyDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "verifyDate"));
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
