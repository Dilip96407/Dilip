/**
 * SourceSystemVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class SourceSystemVo  implements java.io.Serializable {
    private java.lang.String sourceSystemCode;

    private java.lang.String sourceSystemName;

    private java.lang.String client;

    private java.lang.String mandantCode;

    private java.lang.String productClass;

    public SourceSystemVo() {
    }

    public SourceSystemVo(
           java.lang.String sourceSystemCode,
           java.lang.String sourceSystemName,
           java.lang.String client,
           java.lang.String mandantCode,
           java.lang.String productClass) {
           this.sourceSystemCode = sourceSystemCode;
           this.sourceSystemName = sourceSystemName;
           this.client = client;
           this.mandantCode = mandantCode;
           this.productClass = productClass;
    }


    /**
     * Gets the sourceSystemCode value for this SourceSystemVo.
     * 
     * @return sourceSystemCode
     */
    public java.lang.String getSourceSystemCode() {
        return sourceSystemCode;
    }


    /**
     * Sets the sourceSystemCode value for this SourceSystemVo.
     * 
     * @param sourceSystemCode
     */
    public void setSourceSystemCode(java.lang.String sourceSystemCode) {
        this.sourceSystemCode = sourceSystemCode;
    }


    /**
     * Gets the sourceSystemName value for this SourceSystemVo.
     * 
     * @return sourceSystemName
     */
    public java.lang.String getSourceSystemName() {
        return sourceSystemName;
    }


    /**
     * Sets the sourceSystemName value for this SourceSystemVo.
     * 
     * @param sourceSystemName
     */
    public void setSourceSystemName(java.lang.String sourceSystemName) {
        this.sourceSystemName = sourceSystemName;
    }


    /**
     * Gets the client value for this SourceSystemVo.
     * 
     * @return client
     */
    public java.lang.String getClient() {
        return client;
    }


    /**
     * Sets the client value for this SourceSystemVo.
     * 
     * @param client
     */
    public void setClient(java.lang.String client) {
        this.client = client;
    }


    /**
     * Gets the mandantCode value for this SourceSystemVo.
     * 
     * @return mandantCode
     */
    public java.lang.String getMandantCode() {
        return mandantCode;
    }


    /**
     * Sets the mandantCode value for this SourceSystemVo.
     * 
     * @param mandantCode
     */
    public void setMandantCode(java.lang.String mandantCode) {
        this.mandantCode = mandantCode;
    }


    /**
     * Gets the productClass value for this SourceSystemVo.
     * 
     * @return productClass
     */
    public java.lang.String getProductClass() {
        return productClass;
    }


    /**
     * Sets the productClass value for this SourceSystemVo.
     * 
     * @param productClass
     */
    public void setProductClass(java.lang.String productClass) {
        this.productClass = productClass;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SourceSystemVo)) return false;
        SourceSystemVo other = (SourceSystemVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sourceSystemCode==null && other.getSourceSystemCode()==null) || 
             (this.sourceSystemCode!=null &&
              this.sourceSystemCode.equals(other.getSourceSystemCode()))) &&
            ((this.sourceSystemName==null && other.getSourceSystemName()==null) || 
             (this.sourceSystemName!=null &&
              this.sourceSystemName.equals(other.getSourceSystemName()))) &&
            ((this.client==null && other.getClient()==null) || 
             (this.client!=null &&
              this.client.equals(other.getClient()))) &&
            ((this.mandantCode==null && other.getMandantCode()==null) || 
             (this.mandantCode!=null &&
              this.mandantCode.equals(other.getMandantCode()))) &&
            ((this.productClass==null && other.getProductClass()==null) || 
             (this.productClass!=null &&
              this.productClass.equals(other.getProductClass())));
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
        if (getSourceSystemCode() != null) {
            _hashCode += getSourceSystemCode().hashCode();
        }
        if (getSourceSystemName() != null) {
            _hashCode += getSourceSystemName().hashCode();
        }
        if (getClient() != null) {
            _hashCode += getClient().hashCode();
        }
        if (getMandantCode() != null) {
            _hashCode += getMandantCode().hashCode();
        }
        if (getProductClass() != null) {
            _hashCode += getProductClass().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SourceSystemVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "SourceSystemVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceSystemCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceSystemCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceSystemName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceSystemName"));
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
        elemField.setFieldName("mandantCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mandantCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productClass");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productClass"));
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
