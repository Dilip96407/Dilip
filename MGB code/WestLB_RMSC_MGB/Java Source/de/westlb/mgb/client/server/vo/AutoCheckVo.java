/**
 * AutoCheckVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class AutoCheckVo  implements java.io.Serializable {
    private double bloombergPriceToleranceDeviationFactor;

    private java.lang.Double[] priceToleranceAbsolute;

    private java.lang.Double[] priceTolerancePercent;

    private de.westlb.mgb.client.server.vo.PriceVo[] prices;

    private de.westlb.mgb.client.server.vo.RequestVo[] requests;

    private java.lang.String sourceSystemCode;

    private java.lang.Integer[] timeToleranceMinutes;

    private java.lang.Long tradeId;

    public AutoCheckVo() {
    }

    public AutoCheckVo(
           double bloombergPriceToleranceDeviationFactor,
           java.lang.Double[] priceToleranceAbsolute,
           java.lang.Double[] priceTolerancePercent,
           de.westlb.mgb.client.server.vo.PriceVo[] prices,
           de.westlb.mgb.client.server.vo.RequestVo[] requests,
           java.lang.String sourceSystemCode,
           java.lang.Integer[] timeToleranceMinutes,
           java.lang.Long tradeId) {
           this.bloombergPriceToleranceDeviationFactor = bloombergPriceToleranceDeviationFactor;
           this.priceToleranceAbsolute = priceToleranceAbsolute;
           this.priceTolerancePercent = priceTolerancePercent;
           this.prices = prices;
           this.requests = requests;
           this.sourceSystemCode = sourceSystemCode;
           this.timeToleranceMinutes = timeToleranceMinutes;
           this.tradeId = tradeId;
    }


    /**
     * Gets the bloombergPriceToleranceDeviationFactor value for this AutoCheckVo.
     * 
     * @return bloombergPriceToleranceDeviationFactor
     */
    public double getBloombergPriceToleranceDeviationFactor() {
        return bloombergPriceToleranceDeviationFactor;
    }


    /**
     * Sets the bloombergPriceToleranceDeviationFactor value for this AutoCheckVo.
     * 
     * @param bloombergPriceToleranceDeviationFactor
     */
    public void setBloombergPriceToleranceDeviationFactor(double bloombergPriceToleranceDeviationFactor) {
        this.bloombergPriceToleranceDeviationFactor = bloombergPriceToleranceDeviationFactor;
    }


    /**
     * Gets the priceToleranceAbsolute value for this AutoCheckVo.
     * 
     * @return priceToleranceAbsolute
     */
    public java.lang.Double[] getPriceToleranceAbsolute() {
        return priceToleranceAbsolute;
    }


    /**
     * Sets the priceToleranceAbsolute value for this AutoCheckVo.
     * 
     * @param priceToleranceAbsolute
     */
    public void setPriceToleranceAbsolute(java.lang.Double[] priceToleranceAbsolute) {
        this.priceToleranceAbsolute = priceToleranceAbsolute;
    }


    /**
     * Gets the priceTolerancePercent value for this AutoCheckVo.
     * 
     * @return priceTolerancePercent
     */
    public java.lang.Double[] getPriceTolerancePercent() {
        return priceTolerancePercent;
    }


    /**
     * Sets the priceTolerancePercent value for this AutoCheckVo.
     * 
     * @param priceTolerancePercent
     */
    public void setPriceTolerancePercent(java.lang.Double[] priceTolerancePercent) {
        this.priceTolerancePercent = priceTolerancePercent;
    }


    /**
     * Gets the prices value for this AutoCheckVo.
     * 
     * @return prices
     */
    public de.westlb.mgb.client.server.vo.PriceVo[] getPrices() {
        return prices;
    }


    /**
     * Sets the prices value for this AutoCheckVo.
     * 
     * @param prices
     */
    public void setPrices(de.westlb.mgb.client.server.vo.PriceVo[] prices) {
        this.prices = prices;
    }


    /**
     * Gets the requests value for this AutoCheckVo.
     * 
     * @return requests
     */
    public de.westlb.mgb.client.server.vo.RequestVo[] getRequests() {
        return requests;
    }


    /**
     * Sets the requests value for this AutoCheckVo.
     * 
     * @param requests
     */
    public void setRequests(de.westlb.mgb.client.server.vo.RequestVo[] requests) {
        this.requests = requests;
    }


    /**
     * Gets the sourceSystemCode value for this AutoCheckVo.
     * 
     * @return sourceSystemCode
     */
    public java.lang.String getSourceSystemCode() {
        return sourceSystemCode;
    }


    /**
     * Sets the sourceSystemCode value for this AutoCheckVo.
     * 
     * @param sourceSystemCode
     */
    public void setSourceSystemCode(java.lang.String sourceSystemCode) {
        this.sourceSystemCode = sourceSystemCode;
    }


    /**
     * Gets the timeToleranceMinutes value for this AutoCheckVo.
     * 
     * @return timeToleranceMinutes
     */
    public java.lang.Integer[] getTimeToleranceMinutes() {
        return timeToleranceMinutes;
    }


    /**
     * Sets the timeToleranceMinutes value for this AutoCheckVo.
     * 
     * @param timeToleranceMinutes
     */
    public void setTimeToleranceMinutes(java.lang.Integer[] timeToleranceMinutes) {
        this.timeToleranceMinutes = timeToleranceMinutes;
    }


    /**
     * Gets the tradeId value for this AutoCheckVo.
     * 
     * @return tradeId
     */
    public java.lang.Long getTradeId() {
        return tradeId;
    }


    /**
     * Sets the tradeId value for this AutoCheckVo.
     * 
     * @param tradeId
     */
    public void setTradeId(java.lang.Long tradeId) {
        this.tradeId = tradeId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutoCheckVo)) return false;
        AutoCheckVo other = (AutoCheckVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.bloombergPriceToleranceDeviationFactor == other.getBloombergPriceToleranceDeviationFactor() &&
            ((this.priceToleranceAbsolute==null && other.getPriceToleranceAbsolute()==null) || 
             (this.priceToleranceAbsolute!=null &&
              java.util.Arrays.equals(this.priceToleranceAbsolute, other.getPriceToleranceAbsolute()))) &&
            ((this.priceTolerancePercent==null && other.getPriceTolerancePercent()==null) || 
             (this.priceTolerancePercent!=null &&
              java.util.Arrays.equals(this.priceTolerancePercent, other.getPriceTolerancePercent()))) &&
            ((this.prices==null && other.getPrices()==null) || 
             (this.prices!=null &&
              java.util.Arrays.equals(this.prices, other.getPrices()))) &&
            ((this.requests==null && other.getRequests()==null) || 
             (this.requests!=null &&
              java.util.Arrays.equals(this.requests, other.getRequests()))) &&
            ((this.sourceSystemCode==null && other.getSourceSystemCode()==null) || 
             (this.sourceSystemCode!=null &&
              this.sourceSystemCode.equals(other.getSourceSystemCode()))) &&
            ((this.timeToleranceMinutes==null && other.getTimeToleranceMinutes()==null) || 
             (this.timeToleranceMinutes!=null &&
              java.util.Arrays.equals(this.timeToleranceMinutes, other.getTimeToleranceMinutes()))) &&
            ((this.tradeId==null && other.getTradeId()==null) || 
             (this.tradeId!=null &&
              this.tradeId.equals(other.getTradeId())));
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
        _hashCode += new Double(getBloombergPriceToleranceDeviationFactor()).hashCode();
        if (getPriceToleranceAbsolute() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPriceToleranceAbsolute());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPriceToleranceAbsolute(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPriceTolerancePercent() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPriceTolerancePercent());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPriceTolerancePercent(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        if (getSourceSystemCode() != null) {
            _hashCode += getSourceSystemCode().hashCode();
        }
        if (getTimeToleranceMinutes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTimeToleranceMinutes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTimeToleranceMinutes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTradeId() != null) {
            _hashCode += getTradeId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AutoCheckVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "AutoCheckVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bloombergPriceToleranceDeviationFactor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bloombergPriceToleranceDeviationFactor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceToleranceAbsolute");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceToleranceAbsolute"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceTolerancePercent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceTolerancePercent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prices");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prices"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "PriceVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requests");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requests"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "RequestVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceSystemCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceSystemCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeToleranceMinutes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timeToleranceMinutes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
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
