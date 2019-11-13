/**
 * ExchangeSearchResultEntryVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class ExchangeSearchResultEntryVo  implements java.io.Serializable {
    private java.lang.String bloombergId;

    private boolean changePending;

    private java.lang.String changeRequestedByName;

    private java.lang.Long id;

    private java.lang.String reutersId;

    public ExchangeSearchResultEntryVo() {
    }

    public ExchangeSearchResultEntryVo(
           java.lang.String bloombergId,
           boolean changePending,
           java.lang.String changeRequestedByName,
           java.lang.Long id,
           java.lang.String reutersId) {
           this.bloombergId = bloombergId;
           this.changePending = changePending;
           this.changeRequestedByName = changeRequestedByName;
           this.id = id;
           this.reutersId = reutersId;
    }


    /**
     * Gets the bloombergId value for this ExchangeSearchResultEntryVo.
     * 
     * @return bloombergId
     */
    public java.lang.String getBloombergId() {
        return bloombergId;
    }


    /**
     * Sets the bloombergId value for this ExchangeSearchResultEntryVo.
     * 
     * @param bloombergId
     */
    public void setBloombergId(java.lang.String bloombergId) {
        this.bloombergId = bloombergId;
    }


    /**
     * Gets the changePending value for this ExchangeSearchResultEntryVo.
     * 
     * @return changePending
     */
    public boolean isChangePending() {
        return changePending;
    }


    /**
     * Sets the changePending value for this ExchangeSearchResultEntryVo.
     * 
     * @param changePending
     */
    public void setChangePending(boolean changePending) {
        this.changePending = changePending;
    }


    /**
     * Gets the changeRequestedByName value for this ExchangeSearchResultEntryVo.
     * 
     * @return changeRequestedByName
     */
    public java.lang.String getChangeRequestedByName() {
        return changeRequestedByName;
    }


    /**
     * Sets the changeRequestedByName value for this ExchangeSearchResultEntryVo.
     * 
     * @param changeRequestedByName
     */
    public void setChangeRequestedByName(java.lang.String changeRequestedByName) {
        this.changeRequestedByName = changeRequestedByName;
    }


    /**
     * Gets the id value for this ExchangeSearchResultEntryVo.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this ExchangeSearchResultEntryVo.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the reutersId value for this ExchangeSearchResultEntryVo.
     * 
     * @return reutersId
     */
    public java.lang.String getReutersId() {
        return reutersId;
    }


    /**
     * Sets the reutersId value for this ExchangeSearchResultEntryVo.
     * 
     * @param reutersId
     */
    public void setReutersId(java.lang.String reutersId) {
        this.reutersId = reutersId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExchangeSearchResultEntryVo)) return false;
        ExchangeSearchResultEntryVo other = (ExchangeSearchResultEntryVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bloombergId==null && other.getBloombergId()==null) || 
             (this.bloombergId!=null &&
              this.bloombergId.equals(other.getBloombergId()))) &&
            this.changePending == other.isChangePending() &&
            ((this.changeRequestedByName==null && other.getChangeRequestedByName()==null) || 
             (this.changeRequestedByName!=null &&
              this.changeRequestedByName.equals(other.getChangeRequestedByName()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.reutersId==null && other.getReutersId()==null) || 
             (this.reutersId!=null &&
              this.reutersId.equals(other.getReutersId())));
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
        if (getBloombergId() != null) {
            _hashCode += getBloombergId().hashCode();
        }
        _hashCode += (isChangePending() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getChangeRequestedByName() != null) {
            _hashCode += getChangeRequestedByName().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getReutersId() != null) {
            _hashCode += getReutersId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExchangeSearchResultEntryVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "ExchangeSearchResultEntryVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bloombergId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bloombergId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reutersId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reutersId"));
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
