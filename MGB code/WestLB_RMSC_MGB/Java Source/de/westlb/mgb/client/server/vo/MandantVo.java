/**
 * MandantVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class MandantVo  implements java.io.Serializable {
    private java.lang.String mandantCode;

    private java.lang.String mandantName;

    private java.lang.String client;

    private java.lang.String productClass;

    private java.lang.Boolean autoCheck;

    private java.lang.Boolean enabled;

    public MandantVo() {
    }

    public MandantVo(
           java.lang.String mandantCode,
           java.lang.String mandantName,
           java.lang.String client,
           java.lang.String productClass,
           java.lang.Boolean autoCheck,
           java.lang.Boolean enabled) {
           this.mandantCode = mandantCode;
           this.mandantName = mandantName;
           this.client = client;
           this.productClass = productClass;
           this.autoCheck = autoCheck;
           this.enabled = enabled;
    }


    /**
     * Gets the mandantCode value for this MandantVo.
     * 
     * @return mandantCode
     */
    public java.lang.String getMandantCode() {
        return mandantCode;
    }


    /**
     * Sets the mandantCode value for this MandantVo.
     * 
     * @param mandantCode
     */
    public void setMandantCode(java.lang.String mandantCode) {
        this.mandantCode = mandantCode;
    }


    /**
     * Gets the mandantName value for this MandantVo.
     * 
     * @return mandantName
     */
    public java.lang.String getMandantName() {
        return mandantName;
    }


    /**
     * Sets the mandantName value for this MandantVo.
     * 
     * @param mandantName
     */
    public void setMandantName(java.lang.String mandantName) {
        this.mandantName = mandantName;
    }


    /**
     * Gets the client value for this MandantVo.
     * 
     * @return client
     */
    public java.lang.String getClient() {
        return client;
    }


    /**
     * Sets the client value for this MandantVo.
     * 
     * @param client
     */
    public void setClient(java.lang.String client) {
        this.client = client;
    }


    /**
     * Gets the productClass value for this MandantVo.
     * 
     * @return productClass
     */
    public java.lang.String getProductClass() {
        return productClass;
    }


    /**
     * Sets the productClass value for this MandantVo.
     * 
     * @param productClass
     */
    public void setProductClass(java.lang.String productClass) {
        this.productClass = productClass;
    }


    /**
     * Gets the autoCheck value for this MandantVo.
     * 
     * @return autoCheck
     */
    public java.lang.Boolean getAutoCheck() {
        return autoCheck;
    }


    /**
     * Sets the autoCheck value for this MandantVo.
     * 
     * @param autoCheck
     */
    public void setAutoCheck(java.lang.Boolean autoCheck) {
        this.autoCheck = autoCheck;
    }


    /**
     * Gets the enabled value for this MandantVo.
     * 
     * @return enabled
     */
    public java.lang.Boolean getEnabled() {
        return enabled;
    }


    /**
     * Sets the enabled value for this MandantVo.
     * 
     * @param enabled
     */
    public void setEnabled(java.lang.Boolean enabled) {
        this.enabled = enabled;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MandantVo)) return false;
        MandantVo other = (MandantVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mandantCode==null && other.getMandantCode()==null) || 
             (this.mandantCode!=null &&
              this.mandantCode.equals(other.getMandantCode()))) &&
            ((this.mandantName==null && other.getMandantName()==null) || 
             (this.mandantName!=null &&
              this.mandantName.equals(other.getMandantName()))) &&
            ((this.client==null && other.getClient()==null) || 
             (this.client!=null &&
              this.client.equals(other.getClient()))) &&
            ((this.productClass==null && other.getProductClass()==null) || 
             (this.productClass!=null &&
              this.productClass.equals(other.getProductClass()))) &&
            ((this.autoCheck==null && other.getAutoCheck()==null) || 
             (this.autoCheck!=null &&
              this.autoCheck.equals(other.getAutoCheck()))) &&
            ((this.enabled==null && other.getEnabled()==null) || 
             (this.enabled!=null &&
              this.enabled.equals(other.getEnabled())));
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
        if (getMandantCode() != null) {
            _hashCode += getMandantCode().hashCode();
        }
        if (getMandantName() != null) {
            _hashCode += getMandantName().hashCode();
        }
        if (getClient() != null) {
            _hashCode += getClient().hashCode();
        }
        if (getProductClass() != null) {
            _hashCode += getProductClass().hashCode();
        }
        if (getAutoCheck() != null) {
            _hashCode += getAutoCheck().hashCode();
        }
        if (getEnabled() != null) {
            _hashCode += getEnabled().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MandantVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "MandantVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandantCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mandantCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandantName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mandantName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("client");
        elemField.setXmlName(new javax.xml.namespace.QName("", "client"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productClass");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productClass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoCheck");
        elemField.setXmlName(new javax.xml.namespace.QName("", "autoCheck"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enabled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enabled"));
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
