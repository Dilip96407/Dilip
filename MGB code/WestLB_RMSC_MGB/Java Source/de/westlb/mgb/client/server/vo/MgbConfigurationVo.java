/**
 * MgbConfigurationVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class MgbConfigurationVo  implements java.io.Serializable {
    private boolean changePending;

    private java.lang.String changeRequestedByName;

    private boolean createNew;

    private java.lang.String key;

    private java.lang.String mandantCode;

    private java.lang.String value;

    private java.lang.Boolean hidden;

    public MgbConfigurationVo() {
    }

    public MgbConfigurationVo(
           boolean changePending,
           java.lang.String changeRequestedByName,
           boolean createNew,
           java.lang.String key,
           java.lang.String mandantCode,
           java.lang.String value,
           java.lang.Boolean hidden) {
           this.changePending = changePending;
           this.changeRequestedByName = changeRequestedByName;
           this.createNew = createNew;
           this.key = key;
           this.mandantCode = mandantCode;
           this.value = value;
           this.hidden = hidden;
    }


    /**
     * Gets the changePending value for this MgbConfigurationVo.
     * 
     * @return changePending
     */
    public boolean isChangePending() {
        return changePending;
    }


    /**
     * Sets the changePending value for this MgbConfigurationVo.
     * 
     * @param changePending
     */
    public void setChangePending(boolean changePending) {
        this.changePending = changePending;
    }


    /**
     * Gets the changeRequestedByName value for this MgbConfigurationVo.
     * 
     * @return changeRequestedByName
     */
    public java.lang.String getChangeRequestedByName() {
        return changeRequestedByName;
    }


    /**
     * Sets the changeRequestedByName value for this MgbConfigurationVo.
     * 
     * @param changeRequestedByName
     */
    public void setChangeRequestedByName(java.lang.String changeRequestedByName) {
        this.changeRequestedByName = changeRequestedByName;
    }


    /**
     * Gets the createNew value for this MgbConfigurationVo.
     * 
     * @return createNew
     */
    public boolean isCreateNew() {
        return createNew;
    }


    /**
     * Sets the createNew value for this MgbConfigurationVo.
     * 
     * @param createNew
     */
    public void setCreateNew(boolean createNew) {
        this.createNew = createNew;
    }


    /**
     * Gets the key value for this MgbConfigurationVo.
     * 
     * @return key
     */
    public java.lang.String getKey() {
        return key;
    }


    /**
     * Sets the key value for this MgbConfigurationVo.
     * 
     * @param key
     */
    public void setKey(java.lang.String key) {
        this.key = key;
    }


    /**
     * Gets the mandantCode value for this MgbConfigurationVo.
     * 
     * @return mandantCode
     */
    public java.lang.String getMandantCode() {
        return mandantCode;
    }


    /**
     * Sets the mandantCode value for this MgbConfigurationVo.
     * 
     * @param mandantCode
     */
    public void setMandantCode(java.lang.String mandantCode) {
        this.mandantCode = mandantCode;
    }


    /**
     * Gets the value value for this MgbConfigurationVo.
     * 
     * @return value
     */
    public java.lang.String getValue() {
        return value;
    }


    /**
     * Sets the value value for this MgbConfigurationVo.
     * 
     * @param value
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }


    /**
     * Gets the hidden value for this MgbConfigurationVo.
     * 
     * @return hidden
     */
    public java.lang.Boolean getHidden() {
        return hidden;
    }


    /**
     * Sets the hidden value for this MgbConfigurationVo.
     * 
     * @param hidden
     */
    public void setHidden(java.lang.Boolean hidden) {
        this.hidden = hidden;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MgbConfigurationVo)) return false;
        MgbConfigurationVo other = (MgbConfigurationVo) obj;
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
            this.createNew == other.isCreateNew() &&
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            ((this.mandantCode==null && other.getMandantCode()==null) || 
             (this.mandantCode!=null &&
              this.mandantCode.equals(other.getMandantCode()))) &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue()))) &&
            ((this.hidden==null && other.getHidden()==null) || 
             (this.hidden!=null &&
              this.hidden.equals(other.getHidden())));
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
        _hashCode += (isCreateNew() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getMandantCode() != null) {
            _hashCode += getMandantCode().hashCode();
        }
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        if (getHidden() != null) {
            _hashCode += getHidden().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MgbConfigurationVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "MgbConfigurationVo"));
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
        elemField.setFieldName("createNew");
        elemField.setXmlName(new javax.xml.namespace.QName("", "createNew"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("", "key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandantCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mandantCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hidden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hidden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
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
