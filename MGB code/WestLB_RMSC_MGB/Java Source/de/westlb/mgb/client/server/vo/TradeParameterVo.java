/**
 * TradeParameterVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class TradeParameterVo  implements java.io.Serializable {
    private java.lang.String addonName;

    private java.lang.String checkCategoryName;

    private java.lang.String instrumentIsin;

    private java.lang.String instrumentName;

    private double priceToleranceAbsolute;

    private double priceToleranceAddon;

    private double priceTolerancePercent;

    private java.lang.String tickerUsed;

    private int timeTolerance;

    private int timeToleranceAddon;

    private java.lang.String tradeType;

    public TradeParameterVo() {
    }

    public TradeParameterVo(
           java.lang.String addonName,
           java.lang.String checkCategoryName,
           java.lang.String instrumentIsin,
           java.lang.String instrumentName,
           double priceToleranceAbsolute,
           double priceToleranceAddon,
           double priceTolerancePercent,
           java.lang.String tickerUsed,
           int timeTolerance,
           int timeToleranceAddon,
           java.lang.String tradeType) {
           this.addonName = addonName;
           this.checkCategoryName = checkCategoryName;
           this.instrumentIsin = instrumentIsin;
           this.instrumentName = instrumentName;
           this.priceToleranceAbsolute = priceToleranceAbsolute;
           this.priceToleranceAddon = priceToleranceAddon;
           this.priceTolerancePercent = priceTolerancePercent;
           this.tickerUsed = tickerUsed;
           this.timeTolerance = timeTolerance;
           this.timeToleranceAddon = timeToleranceAddon;
           this.tradeType = tradeType;
    }


    /**
     * Gets the addonName value for this TradeParameterVo.
     * 
     * @return addonName
     */
    public java.lang.String getAddonName() {
        return addonName;
    }


    /**
     * Sets the addonName value for this TradeParameterVo.
     * 
     * @param addonName
     */
    public void setAddonName(java.lang.String addonName) {
        this.addonName = addonName;
    }


    /**
     * Gets the checkCategoryName value for this TradeParameterVo.
     * 
     * @return checkCategoryName
     */
    public java.lang.String getCheckCategoryName() {
        return checkCategoryName;
    }


    /**
     * Sets the checkCategoryName value for this TradeParameterVo.
     * 
     * @param checkCategoryName
     */
    public void setCheckCategoryName(java.lang.String checkCategoryName) {
        this.checkCategoryName = checkCategoryName;
    }


    /**
     * Gets the instrumentIsin value for this TradeParameterVo.
     * 
     * @return instrumentIsin
     */
    public java.lang.String getInstrumentIsin() {
        return instrumentIsin;
    }


    /**
     * Sets the instrumentIsin value for this TradeParameterVo.
     * 
     * @param instrumentIsin
     */
    public void setInstrumentIsin(java.lang.String instrumentIsin) {
        this.instrumentIsin = instrumentIsin;
    }


    /**
     * Gets the instrumentName value for this TradeParameterVo.
     * 
     * @return instrumentName
     */
    public java.lang.String getInstrumentName() {
        return instrumentName;
    }


    /**
     * Sets the instrumentName value for this TradeParameterVo.
     * 
     * @param instrumentName
     */
    public void setInstrumentName(java.lang.String instrumentName) {
        this.instrumentName = instrumentName;
    }


    /**
     * Gets the priceToleranceAbsolute value for this TradeParameterVo.
     * 
     * @return priceToleranceAbsolute
     */
    public double getPriceToleranceAbsolute() {
        return priceToleranceAbsolute;
    }


    /**
     * Sets the priceToleranceAbsolute value for this TradeParameterVo.
     * 
     * @param priceToleranceAbsolute
     */
    public void setPriceToleranceAbsolute(double priceToleranceAbsolute) {
        this.priceToleranceAbsolute = priceToleranceAbsolute;
    }


    /**
     * Gets the priceToleranceAddon value for this TradeParameterVo.
     * 
     * @return priceToleranceAddon
     */
    public double getPriceToleranceAddon() {
        return priceToleranceAddon;
    }


    /**
     * Sets the priceToleranceAddon value for this TradeParameterVo.
     * 
     * @param priceToleranceAddon
     */
    public void setPriceToleranceAddon(double priceToleranceAddon) {
        this.priceToleranceAddon = priceToleranceAddon;
    }


    /**
     * Gets the priceTolerancePercent value for this TradeParameterVo.
     * 
     * @return priceTolerancePercent
     */
    public double getPriceTolerancePercent() {
        return priceTolerancePercent;
    }


    /**
     * Sets the priceTolerancePercent value for this TradeParameterVo.
     * 
     * @param priceTolerancePercent
     */
    public void setPriceTolerancePercent(double priceTolerancePercent) {
        this.priceTolerancePercent = priceTolerancePercent;
    }


    /**
     * Gets the tickerUsed value for this TradeParameterVo.
     * 
     * @return tickerUsed
     */
    public java.lang.String getTickerUsed() {
        return tickerUsed;
    }


    /**
     * Sets the tickerUsed value for this TradeParameterVo.
     * 
     * @param tickerUsed
     */
    public void setTickerUsed(java.lang.String tickerUsed) {
        this.tickerUsed = tickerUsed;
    }


    /**
     * Gets the timeTolerance value for this TradeParameterVo.
     * 
     * @return timeTolerance
     */
    public int getTimeTolerance() {
        return timeTolerance;
    }


    /**
     * Sets the timeTolerance value for this TradeParameterVo.
     * 
     * @param timeTolerance
     */
    public void setTimeTolerance(int timeTolerance) {
        this.timeTolerance = timeTolerance;
    }


    /**
     * Gets the timeToleranceAddon value for this TradeParameterVo.
     * 
     * @return timeToleranceAddon
     */
    public int getTimeToleranceAddon() {
        return timeToleranceAddon;
    }


    /**
     * Sets the timeToleranceAddon value for this TradeParameterVo.
     * 
     * @param timeToleranceAddon
     */
    public void setTimeToleranceAddon(int timeToleranceAddon) {
        this.timeToleranceAddon = timeToleranceAddon;
    }


    /**
     * Gets the tradeType value for this TradeParameterVo.
     * 
     * @return tradeType
     */
    public java.lang.String getTradeType() {
        return tradeType;
    }


    /**
     * Sets the tradeType value for this TradeParameterVo.
     * 
     * @param tradeType
     */
    public void setTradeType(java.lang.String tradeType) {
        this.tradeType = tradeType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TradeParameterVo)) return false;
        TradeParameterVo other = (TradeParameterVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addonName==null && other.getAddonName()==null) || 
             (this.addonName!=null &&
              this.addonName.equals(other.getAddonName()))) &&
            ((this.checkCategoryName==null && other.getCheckCategoryName()==null) || 
             (this.checkCategoryName!=null &&
              this.checkCategoryName.equals(other.getCheckCategoryName()))) &&
            ((this.instrumentIsin==null && other.getInstrumentIsin()==null) || 
             (this.instrumentIsin!=null &&
              this.instrumentIsin.equals(other.getInstrumentIsin()))) &&
            ((this.instrumentName==null && other.getInstrumentName()==null) || 
             (this.instrumentName!=null &&
              this.instrumentName.equals(other.getInstrumentName()))) &&
            this.priceToleranceAbsolute == other.getPriceToleranceAbsolute() &&
            this.priceToleranceAddon == other.getPriceToleranceAddon() &&
            this.priceTolerancePercent == other.getPriceTolerancePercent() &&
            ((this.tickerUsed==null && other.getTickerUsed()==null) || 
             (this.tickerUsed!=null &&
              this.tickerUsed.equals(other.getTickerUsed()))) &&
            this.timeTolerance == other.getTimeTolerance() &&
            this.timeToleranceAddon == other.getTimeToleranceAddon() &&
            ((this.tradeType==null && other.getTradeType()==null) || 
             (this.tradeType!=null &&
              this.tradeType.equals(other.getTradeType())));
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
        if (getAddonName() != null) {
            _hashCode += getAddonName().hashCode();
        }
        if (getCheckCategoryName() != null) {
            _hashCode += getCheckCategoryName().hashCode();
        }
        if (getInstrumentIsin() != null) {
            _hashCode += getInstrumentIsin().hashCode();
        }
        if (getInstrumentName() != null) {
            _hashCode += getInstrumentName().hashCode();
        }
        _hashCode += new Double(getPriceToleranceAbsolute()).hashCode();
        _hashCode += new Double(getPriceToleranceAddon()).hashCode();
        _hashCode += new Double(getPriceTolerancePercent()).hashCode();
        if (getTickerUsed() != null) {
            _hashCode += getTickerUsed().hashCode();
        }
        _hashCode += getTimeTolerance();
        _hashCode += getTimeToleranceAddon();
        if (getTradeType() != null) {
            _hashCode += getTradeType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TradeParameterVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "TradeParameterVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addonName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addonName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkCategoryName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "checkCategoryName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instrumentIsin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "instrumentIsin"));
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
        elemField.setFieldName("priceToleranceAbsolute");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceToleranceAbsolute"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceToleranceAddon");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceToleranceAddon"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceTolerancePercent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceTolerancePercent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tickerUsed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tickerUsed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeTolerance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timeTolerance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeToleranceAddon");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timeToleranceAddon"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeType"));
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
