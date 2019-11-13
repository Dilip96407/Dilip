/**
 * InstrumentVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class InstrumentVo  implements java.io.Serializable {
    private java.lang.Long id;

    private java.lang.String instrumentName;

    private java.lang.String isin;

    private java.lang.Long priceCheckCategoryId;

    public InstrumentVo() {
    }

    public InstrumentVo(
           java.lang.Long id,
           java.lang.String instrumentName,
           java.lang.String isin,
           java.lang.Long priceCheckCategoryId) {
           this.id = id;
           this.instrumentName = instrumentName;
           this.isin = isin;
           this.priceCheckCategoryId = priceCheckCategoryId;
    }


    /**
     * Gets the id value for this InstrumentVo.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this InstrumentVo.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the instrumentName value for this InstrumentVo.
     * 
     * @return instrumentName
     */
    public java.lang.String getInstrumentName() {
        return instrumentName;
    }


    /**
     * Sets the instrumentName value for this InstrumentVo.
     * 
     * @param instrumentName
     */
    public void setInstrumentName(java.lang.String instrumentName) {
        this.instrumentName = instrumentName;
    }


    /**
     * Gets the isin value for this InstrumentVo.
     * 
     * @return isin
     */
    public java.lang.String getIsin() {
        return isin;
    }


    /**
     * Sets the isin value for this InstrumentVo.
     * 
     * @param isin
     */
    public void setIsin(java.lang.String isin) {
        this.isin = isin;
    }


    /**
     * Gets the priceCheckCategoryId value for this InstrumentVo.
     * 
     * @return priceCheckCategoryId
     */
    public java.lang.Long getPriceCheckCategoryId() {
        return priceCheckCategoryId;
    }


    /**
     * Sets the priceCheckCategoryId value for this InstrumentVo.
     * 
     * @param priceCheckCategoryId
     */
    public void setPriceCheckCategoryId(java.lang.Long priceCheckCategoryId) {
        this.priceCheckCategoryId = priceCheckCategoryId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InstrumentVo)) return false;
        InstrumentVo other = (InstrumentVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.instrumentName==null && other.getInstrumentName()==null) || 
             (this.instrumentName!=null &&
              this.instrumentName.equals(other.getInstrumentName()))) &&
            ((this.isin==null && other.getIsin()==null) || 
             (this.isin!=null &&
              this.isin.equals(other.getIsin()))) &&
            ((this.priceCheckCategoryId==null && other.getPriceCheckCategoryId()==null) || 
             (this.priceCheckCategoryId!=null &&
              this.priceCheckCategoryId.equals(other.getPriceCheckCategoryId())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getInstrumentName() != null) {
            _hashCode += getInstrumentName().hashCode();
        }
        if (getIsin() != null) {
            _hashCode += getIsin().hashCode();
        }
        if (getPriceCheckCategoryId() != null) {
            _hashCode += getPriceCheckCategoryId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InstrumentVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "InstrumentVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instrumentName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "instrumentName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceCheckCategoryId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceCheckCategoryId"));
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
