/**
 * PriceVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class PriceVo  implements java.io.Serializable {
    private double maxPrice;

    private double midPrice;

    private double minPrice;

    private java.util.Calendar priceDate;

    private java.lang.String priceType;

    private java.lang.Long requestId;

    public PriceVo() {
    }

    public PriceVo(
           double maxPrice,
           double midPrice,
           double minPrice,
           java.util.Calendar priceDate,
           java.lang.String priceType,
           java.lang.Long requestId) {
           this.maxPrice = maxPrice;
           this.midPrice = midPrice;
           this.minPrice = minPrice;
           this.priceDate = priceDate;
           this.priceType = priceType;
           this.requestId = requestId;
    }


    /**
     * Gets the maxPrice value for this PriceVo.
     * 
     * @return maxPrice
     */
    public double getMaxPrice() {
        return maxPrice;
    }


    /**
     * Sets the maxPrice value for this PriceVo.
     * 
     * @param maxPrice
     */
    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }


    /**
     * Gets the midPrice value for this PriceVo.
     * 
     * @return midPrice
     */
    public double getMidPrice() {
        return midPrice;
    }


    /**
     * Sets the midPrice value for this PriceVo.
     * 
     * @param midPrice
     */
    public void setMidPrice(double midPrice) {
        this.midPrice = midPrice;
    }


    /**
     * Gets the minPrice value for this PriceVo.
     * 
     * @return minPrice
     */
    public double getMinPrice() {
        return minPrice;
    }


    /**
     * Sets the minPrice value for this PriceVo.
     * 
     * @param minPrice
     */
    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }


    /**
     * Gets the priceDate value for this PriceVo.
     * 
     * @return priceDate
     */
    public java.util.Calendar getPriceDate() {
        return priceDate;
    }


    /**
     * Sets the priceDate value for this PriceVo.
     * 
     * @param priceDate
     */
    public void setPriceDate(java.util.Calendar priceDate) {
        this.priceDate = priceDate;
    }


    /**
     * Gets the priceType value for this PriceVo.
     * 
     * @return priceType
     */
    public java.lang.String getPriceType() {
        return priceType;
    }


    /**
     * Sets the priceType value for this PriceVo.
     * 
     * @param priceType
     */
    public void setPriceType(java.lang.String priceType) {
        this.priceType = priceType;
    }


    /**
     * Gets the requestId value for this PriceVo.
     * 
     * @return requestId
     */
    public java.lang.Long getRequestId() {
        return requestId;
    }


    /**
     * Sets the requestId value for this PriceVo.
     * 
     * @param requestId
     */
    public void setRequestId(java.lang.Long requestId) {
        this.requestId = requestId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PriceVo)) return false;
        PriceVo other = (PriceVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.maxPrice == other.getMaxPrice() &&
            this.midPrice == other.getMidPrice() &&
            this.minPrice == other.getMinPrice() &&
            ((this.priceDate==null && other.getPriceDate()==null) || 
             (this.priceDate!=null &&
              this.priceDate.equals(other.getPriceDate()))) &&
            ((this.priceType==null && other.getPriceType()==null) || 
             (this.priceType!=null &&
              this.priceType.equals(other.getPriceType()))) &&
            ((this.requestId==null && other.getRequestId()==null) || 
             (this.requestId!=null &&
              this.requestId.equals(other.getRequestId())));
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
        _hashCode += new Double(getMaxPrice()).hashCode();
        _hashCode += new Double(getMidPrice()).hashCode();
        _hashCode += new Double(getMinPrice()).hashCode();
        if (getPriceDate() != null) {
            _hashCode += getPriceDate().hashCode();
        }
        if (getPriceType() != null) {
            _hashCode += getPriceType().hashCode();
        }
        if (getRequestId() != null) {
            _hashCode += getRequestId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PriceVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "PriceVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maxPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("midPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "midPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "minPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestId"));
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
