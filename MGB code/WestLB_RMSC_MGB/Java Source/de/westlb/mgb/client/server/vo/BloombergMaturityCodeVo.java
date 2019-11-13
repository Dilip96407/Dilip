/**
 * BloombergMaturityCodeVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class BloombergMaturityCodeVo  implements java.io.Serializable {
    private java.lang.String bloombergMaturityCode;

    private java.lang.String bloombergMaturityField;

    private java.lang.String maturityCode;

    private java.lang.String maturityName;

    private double maxMaturityDays;

    private double minMaturityDays;

    public BloombergMaturityCodeVo() {
    }

    public BloombergMaturityCodeVo(
           java.lang.String bloombergMaturityCode,
           java.lang.String bloombergMaturityField,
           java.lang.String maturityCode,
           java.lang.String maturityName,
           double maxMaturityDays,
           double minMaturityDays) {
           this.bloombergMaturityCode = bloombergMaturityCode;
           this.bloombergMaturityField = bloombergMaturityField;
           this.maturityCode = maturityCode;
           this.maturityName = maturityName;
           this.maxMaturityDays = maxMaturityDays;
           this.minMaturityDays = minMaturityDays;
    }


    /**
     * Gets the bloombergMaturityCode value for this BloombergMaturityCodeVo.
     * 
     * @return bloombergMaturityCode
     */
    public java.lang.String getBloombergMaturityCode() {
        return bloombergMaturityCode;
    }


    /**
     * Sets the bloombergMaturityCode value for this BloombergMaturityCodeVo.
     * 
     * @param bloombergMaturityCode
     */
    public void setBloombergMaturityCode(java.lang.String bloombergMaturityCode) {
        this.bloombergMaturityCode = bloombergMaturityCode;
    }


    /**
     * Gets the bloombergMaturityField value for this BloombergMaturityCodeVo.
     * 
     * @return bloombergMaturityField
     */
    public java.lang.String getBloombergMaturityField() {
        return bloombergMaturityField;
    }


    /**
     * Sets the bloombergMaturityField value for this BloombergMaturityCodeVo.
     * 
     * @param bloombergMaturityField
     */
    public void setBloombergMaturityField(java.lang.String bloombergMaturityField) {
        this.bloombergMaturityField = bloombergMaturityField;
    }


    /**
     * Gets the maturityCode value for this BloombergMaturityCodeVo.
     * 
     * @return maturityCode
     */
    public java.lang.String getMaturityCode() {
        return maturityCode;
    }


    /**
     * Sets the maturityCode value for this BloombergMaturityCodeVo.
     * 
     * @param maturityCode
     */
    public void setMaturityCode(java.lang.String maturityCode) {
        this.maturityCode = maturityCode;
    }


    /**
     * Gets the maturityName value for this BloombergMaturityCodeVo.
     * 
     * @return maturityName
     */
    public java.lang.String getMaturityName() {
        return maturityName;
    }


    /**
     * Sets the maturityName value for this BloombergMaturityCodeVo.
     * 
     * @param maturityName
     */
    public void setMaturityName(java.lang.String maturityName) {
        this.maturityName = maturityName;
    }


    /**
     * Gets the maxMaturityDays value for this BloombergMaturityCodeVo.
     * 
     * @return maxMaturityDays
     */
    public double getMaxMaturityDays() {
        return maxMaturityDays;
    }


    /**
     * Sets the maxMaturityDays value for this BloombergMaturityCodeVo.
     * 
     * @param maxMaturityDays
     */
    public void setMaxMaturityDays(double maxMaturityDays) {
        this.maxMaturityDays = maxMaturityDays;
    }


    /**
     * Gets the minMaturityDays value for this BloombergMaturityCodeVo.
     * 
     * @return minMaturityDays
     */
    public double getMinMaturityDays() {
        return minMaturityDays;
    }


    /**
     * Sets the minMaturityDays value for this BloombergMaturityCodeVo.
     * 
     * @param minMaturityDays
     */
    public void setMinMaturityDays(double minMaturityDays) {
        this.minMaturityDays = minMaturityDays;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BloombergMaturityCodeVo)) return false;
        BloombergMaturityCodeVo other = (BloombergMaturityCodeVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bloombergMaturityCode==null && other.getBloombergMaturityCode()==null) || 
             (this.bloombergMaturityCode!=null &&
              this.bloombergMaturityCode.equals(other.getBloombergMaturityCode()))) &&
            ((this.bloombergMaturityField==null && other.getBloombergMaturityField()==null) || 
             (this.bloombergMaturityField!=null &&
              this.bloombergMaturityField.equals(other.getBloombergMaturityField()))) &&
            ((this.maturityCode==null && other.getMaturityCode()==null) || 
             (this.maturityCode!=null &&
              this.maturityCode.equals(other.getMaturityCode()))) &&
            ((this.maturityName==null && other.getMaturityName()==null) || 
             (this.maturityName!=null &&
              this.maturityName.equals(other.getMaturityName()))) &&
            this.maxMaturityDays == other.getMaxMaturityDays() &&
            this.minMaturityDays == other.getMinMaturityDays();
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
        if (getBloombergMaturityCode() != null) {
            _hashCode += getBloombergMaturityCode().hashCode();
        }
        if (getBloombergMaturityField() != null) {
            _hashCode += getBloombergMaturityField().hashCode();
        }
        if (getMaturityCode() != null) {
            _hashCode += getMaturityCode().hashCode();
        }
        if (getMaturityName() != null) {
            _hashCode += getMaturityName().hashCode();
        }
        _hashCode += new Double(getMaxMaturityDays()).hashCode();
        _hashCode += new Double(getMinMaturityDays()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BloombergMaturityCodeVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "BloombergMaturityCodeVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bloombergMaturityCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bloombergMaturityCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bloombergMaturityField");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bloombergMaturityField"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maturityCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maturityCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maturityName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maturityName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxMaturityDays");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maxMaturityDays"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minMaturityDays");
        elemField.setXmlName(new javax.xml.namespace.QName("", "minMaturityDays"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
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
