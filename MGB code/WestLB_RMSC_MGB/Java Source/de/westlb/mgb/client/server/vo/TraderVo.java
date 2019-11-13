/**
 * TraderVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class TraderVo  implements java.io.Serializable {
    private java.lang.Long id;

    private java.lang.String sourceSystemCode;

    private java.lang.String traderCode;

    private java.lang.String traderName;

    public TraderVo() {
    }

    public TraderVo(
           java.lang.Long id,
           java.lang.String sourceSystemCode,
           java.lang.String traderCode,
           java.lang.String traderName) {
           this.id = id;
           this.sourceSystemCode = sourceSystemCode;
           this.traderCode = traderCode;
           this.traderName = traderName;
    }


    /**
     * Gets the id value for this TraderVo.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this TraderVo.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the sourceSystemCode value for this TraderVo.
     * 
     * @return sourceSystemCode
     */
    public java.lang.String getSourceSystemCode() {
        return sourceSystemCode;
    }


    /**
     * Sets the sourceSystemCode value for this TraderVo.
     * 
     * @param sourceSystemCode
     */
    public void setSourceSystemCode(java.lang.String sourceSystemCode) {
        this.sourceSystemCode = sourceSystemCode;
    }


    /**
     * Gets the traderCode value for this TraderVo.
     * 
     * @return traderCode
     */
    public java.lang.String getTraderCode() {
        return traderCode;
    }


    /**
     * Sets the traderCode value for this TraderVo.
     * 
     * @param traderCode
     */
    public void setTraderCode(java.lang.String traderCode) {
        this.traderCode = traderCode;
    }


    /**
     * Gets the traderName value for this TraderVo.
     * 
     * @return traderName
     */
    public java.lang.String getTraderName() {
        return traderName;
    }


    /**
     * Sets the traderName value for this TraderVo.
     * 
     * @param traderName
     */
    public void setTraderName(java.lang.String traderName) {
        this.traderName = traderName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TraderVo)) return false;
        TraderVo other = (TraderVo) obj;
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
            ((this.sourceSystemCode==null && other.getSourceSystemCode()==null) || 
             (this.sourceSystemCode!=null &&
              this.sourceSystemCode.equals(other.getSourceSystemCode()))) &&
            ((this.traderCode==null && other.getTraderCode()==null) || 
             (this.traderCode!=null &&
              this.traderCode.equals(other.getTraderCode()))) &&
            ((this.traderName==null && other.getTraderName()==null) || 
             (this.traderName!=null &&
              this.traderName.equals(other.getTraderName())));
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
        if (getSourceSystemCode() != null) {
            _hashCode += getSourceSystemCode().hashCode();
        }
        if (getTraderCode() != null) {
            _hashCode += getTraderCode().hashCode();
        }
        if (getTraderName() != null) {
            _hashCode += getTraderName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TraderVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "TraderVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceSystemCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceSystemCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("traderCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "traderCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("traderName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "traderName"));
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
