/**
 * BloombergCurrencyCodeVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class BloombergCurrencyCodeVo  implements java.io.Serializable {
    private java.lang.String bloombergCurrencyCode;

    private java.lang.String currencyName;

    private java.lang.String isoCurrencyCode;

    public BloombergCurrencyCodeVo() {
    }

    public BloombergCurrencyCodeVo(
           java.lang.String bloombergCurrencyCode,
           java.lang.String currencyName,
           java.lang.String isoCurrencyCode) {
           this.bloombergCurrencyCode = bloombergCurrencyCode;
           this.currencyName = currencyName;
           this.isoCurrencyCode = isoCurrencyCode;
    }


    /**
     * Gets the bloombergCurrencyCode value for this BloombergCurrencyCodeVo.
     * 
     * @return bloombergCurrencyCode
     */
    public java.lang.String getBloombergCurrencyCode() {
        return bloombergCurrencyCode;
    }


    /**
     * Sets the bloombergCurrencyCode value for this BloombergCurrencyCodeVo.
     * 
     * @param bloombergCurrencyCode
     */
    public void setBloombergCurrencyCode(java.lang.String bloombergCurrencyCode) {
        this.bloombergCurrencyCode = bloombergCurrencyCode;
    }


    /**
     * Gets the currencyName value for this BloombergCurrencyCodeVo.
     * 
     * @return currencyName
     */
    public java.lang.String getCurrencyName() {
        return currencyName;
    }


    /**
     * Sets the currencyName value for this BloombergCurrencyCodeVo.
     * 
     * @param currencyName
     */
    public void setCurrencyName(java.lang.String currencyName) {
        this.currencyName = currencyName;
    }


    /**
     * Gets the isoCurrencyCode value for this BloombergCurrencyCodeVo.
     * 
     * @return isoCurrencyCode
     */
    public java.lang.String getIsoCurrencyCode() {
        return isoCurrencyCode;
    }


    /**
     * Sets the isoCurrencyCode value for this BloombergCurrencyCodeVo.
     * 
     * @param isoCurrencyCode
     */
    public void setIsoCurrencyCode(java.lang.String isoCurrencyCode) {
        this.isoCurrencyCode = isoCurrencyCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BloombergCurrencyCodeVo)) return false;
        BloombergCurrencyCodeVo other = (BloombergCurrencyCodeVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bloombergCurrencyCode==null && other.getBloombergCurrencyCode()==null) || 
             (this.bloombergCurrencyCode!=null &&
              this.bloombergCurrencyCode.equals(other.getBloombergCurrencyCode()))) &&
            ((this.currencyName==null && other.getCurrencyName()==null) || 
             (this.currencyName!=null &&
              this.currencyName.equals(other.getCurrencyName()))) &&
            ((this.isoCurrencyCode==null && other.getIsoCurrencyCode()==null) || 
             (this.isoCurrencyCode!=null &&
              this.isoCurrencyCode.equals(other.getIsoCurrencyCode())));
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
        if (getBloombergCurrencyCode() != null) {
            _hashCode += getBloombergCurrencyCode().hashCode();
        }
        if (getCurrencyName() != null) {
            _hashCode += getCurrencyName().hashCode();
        }
        if (getIsoCurrencyCode() != null) {
            _hashCode += getIsoCurrencyCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BloombergCurrencyCodeVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "BloombergCurrencyCodeVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bloombergCurrencyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bloombergCurrencyCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currencyName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currencyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isoCurrencyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isoCurrencyCode"));
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
