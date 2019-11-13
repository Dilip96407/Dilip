/**
 * ExchangeMappingVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class ExchangeMappingVo  implements java.io.Serializable {
    private boolean changePending;

    private java.lang.String changeRequestedByName;

    private java.lang.String currency;

    private java.lang.Long exchangeId;

    private java.lang.Long id;

    private java.lang.String isin;

    private java.lang.String isinCountryPrefix;

    private java.lang.String sourceSystemCode;

    private java.lang.String sourceSystemExchangeId;

    public ExchangeMappingVo() {
    }

    public ExchangeMappingVo(
           boolean changePending,
           java.lang.String changeRequestedByName,
           java.lang.String currency,
           java.lang.Long exchangeId,
           java.lang.Long id,
           java.lang.String isin,
           java.lang.String isinCountryPrefix,
           java.lang.String sourceSystemCode,
           java.lang.String sourceSystemExchangeId) {
           this.changePending = changePending;
           this.changeRequestedByName = changeRequestedByName;
           this.currency = currency;
           this.exchangeId = exchangeId;
           this.id = id;
           this.isin = isin;
           this.isinCountryPrefix = isinCountryPrefix;
           this.sourceSystemCode = sourceSystemCode;
           this.sourceSystemExchangeId = sourceSystemExchangeId;
    }


    /**
     * Gets the changePending value for this ExchangeMappingVo.
     * 
     * @return changePending
     */
    public boolean isChangePending() {
        return changePending;
    }


    /**
     * Sets the changePending value for this ExchangeMappingVo.
     * 
     * @param changePending
     */
    public void setChangePending(boolean changePending) {
        this.changePending = changePending;
    }


    /**
     * Gets the changeRequestedByName value for this ExchangeMappingVo.
     * 
     * @return changeRequestedByName
     */
    public java.lang.String getChangeRequestedByName() {
        return changeRequestedByName;
    }


    /**
     * Sets the changeRequestedByName value for this ExchangeMappingVo.
     * 
     * @param changeRequestedByName
     */
    public void setChangeRequestedByName(java.lang.String changeRequestedByName) {
        this.changeRequestedByName = changeRequestedByName;
    }


    /**
     * Gets the currency value for this ExchangeMappingVo.
     * 
     * @return currency
     */
    public java.lang.String getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this ExchangeMappingVo.
     * 
     * @param currency
     */
    public void setCurrency(java.lang.String currency) {
        this.currency = currency;
    }


    /**
     * Gets the exchangeId value for this ExchangeMappingVo.
     * 
     * @return exchangeId
     */
    public java.lang.Long getExchangeId() {
        return exchangeId;
    }


    /**
     * Sets the exchangeId value for this ExchangeMappingVo.
     * 
     * @param exchangeId
     */
    public void setExchangeId(java.lang.Long exchangeId) {
        this.exchangeId = exchangeId;
    }


    /**
     * Gets the id value for this ExchangeMappingVo.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this ExchangeMappingVo.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the isin value for this ExchangeMappingVo.
     * 
     * @return isin
     */
    public java.lang.String getIsin() {
        return isin;
    }


    /**
     * Sets the isin value for this ExchangeMappingVo.
     * 
     * @param isin
     */
    public void setIsin(java.lang.String isin) {
        this.isin = isin;
    }


    /**
     * Gets the isinCountryPrefix value for this ExchangeMappingVo.
     * 
     * @return isinCountryPrefix
     */
    public java.lang.String getIsinCountryPrefix() {
        return isinCountryPrefix;
    }


    /**
     * Sets the isinCountryPrefix value for this ExchangeMappingVo.
     * 
     * @param isinCountryPrefix
     */
    public void setIsinCountryPrefix(java.lang.String isinCountryPrefix) {
        this.isinCountryPrefix = isinCountryPrefix;
    }


    /**
     * Gets the sourceSystemCode value for this ExchangeMappingVo.
     * 
     * @return sourceSystemCode
     */
    public java.lang.String getSourceSystemCode() {
        return sourceSystemCode;
    }


    /**
     * Sets the sourceSystemCode value for this ExchangeMappingVo.
     * 
     * @param sourceSystemCode
     */
    public void setSourceSystemCode(java.lang.String sourceSystemCode) {
        this.sourceSystemCode = sourceSystemCode;
    }


    /**
     * Gets the sourceSystemExchangeId value for this ExchangeMappingVo.
     * 
     * @return sourceSystemExchangeId
     */
    public java.lang.String getSourceSystemExchangeId() {
        return sourceSystemExchangeId;
    }


    /**
     * Sets the sourceSystemExchangeId value for this ExchangeMappingVo.
     * 
     * @param sourceSystemExchangeId
     */
    public void setSourceSystemExchangeId(java.lang.String sourceSystemExchangeId) {
        this.sourceSystemExchangeId = sourceSystemExchangeId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExchangeMappingVo)) return false;
        ExchangeMappingVo other = (ExchangeMappingVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.changePending == other.isChangePending() &&
            ((this.changeRequestedByName==null && other.getChangeRequestedByName()==null) || 
             (this.changeRequestedByName!=null &&
              this.changeRequestedByName.equals(other.getChangeRequestedByName()))) &&
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
            ((this.exchangeId==null && other.getExchangeId()==null) || 
             (this.exchangeId!=null &&
              this.exchangeId.equals(other.getExchangeId()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.isin==null && other.getIsin()==null) || 
             (this.isin!=null &&
              this.isin.equals(other.getIsin()))) &&
            ((this.isinCountryPrefix==null && other.getIsinCountryPrefix()==null) || 
             (this.isinCountryPrefix!=null &&
              this.isinCountryPrefix.equals(other.getIsinCountryPrefix()))) &&
            ((this.sourceSystemCode==null && other.getSourceSystemCode()==null) || 
             (this.sourceSystemCode!=null &&
              this.sourceSystemCode.equals(other.getSourceSystemCode()))) &&
            ((this.sourceSystemExchangeId==null && other.getSourceSystemExchangeId()==null) || 
             (this.sourceSystemExchangeId!=null &&
              this.sourceSystemExchangeId.equals(other.getSourceSystemExchangeId())));
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
        _hashCode += (isChangePending() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getChangeRequestedByName() != null) {
            _hashCode += getChangeRequestedByName().hashCode();
        }
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
        }
        if (getExchangeId() != null) {
            _hashCode += getExchangeId().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIsin() != null) {
            _hashCode += getIsin().hashCode();
        }
        if (getIsinCountryPrefix() != null) {
            _hashCode += getIsinCountryPrefix().hashCode();
        }
        if (getSourceSystemCode() != null) {
            _hashCode += getSourceSystemCode().hashCode();
        }
        if (getSourceSystemExchangeId() != null) {
            _hashCode += getSourceSystemExchangeId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExchangeMappingVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "ExchangeMappingVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changePending");
        elemField.setXmlName(new javax.xml.namespace.QName("", "changePending"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changeRequestedByName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "changeRequestedByName"));
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
        elemField.setFieldName("exchangeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "exchangeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isinCountryPrefix");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isinCountryPrefix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceSystemCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceSystemCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceSystemExchangeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceSystemExchangeId"));
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
