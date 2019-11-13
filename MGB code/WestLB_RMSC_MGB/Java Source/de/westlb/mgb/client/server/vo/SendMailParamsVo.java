/**
 * SendMailParamsVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class SendMailParamsVo  implements java.io.Serializable {
    private java.lang.String[] additionalParamNames;

    private java.lang.String[] additionalParamValues;

    private java.lang.Long parentMailId;

    private java.lang.Long recipientEmployeeId;

    private java.lang.Long senderEmployeeId;

    private java.lang.Long tradeId;

    private java.lang.String type;

    public SendMailParamsVo() {
    }

    public SendMailParamsVo(
           java.lang.String[] additionalParamNames,
           java.lang.String[] additionalParamValues,
           java.lang.Long parentMailId,
           java.lang.Long recipientEmployeeId,
           java.lang.Long senderEmployeeId,
           java.lang.Long tradeId,
           java.lang.String type) {
           this.additionalParamNames = additionalParamNames;
           this.additionalParamValues = additionalParamValues;
           this.parentMailId = parentMailId;
           this.recipientEmployeeId = recipientEmployeeId;
           this.senderEmployeeId = senderEmployeeId;
           this.tradeId = tradeId;
           this.type = type;
    }


    /**
     * Gets the additionalParamNames value for this SendMailParamsVo.
     * 
     * @return additionalParamNames
     */
    public java.lang.String[] getAdditionalParamNames() {
        return additionalParamNames;
    }


    /**
     * Sets the additionalParamNames value for this SendMailParamsVo.
     * 
     * @param additionalParamNames
     */
    public void setAdditionalParamNames(java.lang.String[] additionalParamNames) {
        this.additionalParamNames = additionalParamNames;
    }


    /**
     * Gets the additionalParamValues value for this SendMailParamsVo.
     * 
     * @return additionalParamValues
     */
    public java.lang.String[] getAdditionalParamValues() {
        return additionalParamValues;
    }


    /**
     * Sets the additionalParamValues value for this SendMailParamsVo.
     * 
     * @param additionalParamValues
     */
    public void setAdditionalParamValues(java.lang.String[] additionalParamValues) {
        this.additionalParamValues = additionalParamValues;
    }


    /**
     * Gets the parentMailId value for this SendMailParamsVo.
     * 
     * @return parentMailId
     */
    public java.lang.Long getParentMailId() {
        return parentMailId;
    }


    /**
     * Sets the parentMailId value for this SendMailParamsVo.
     * 
     * @param parentMailId
     */
    public void setParentMailId(java.lang.Long parentMailId) {
        this.parentMailId = parentMailId;
    }


    /**
     * Gets the recipientEmployeeId value for this SendMailParamsVo.
     * 
     * @return recipientEmployeeId
     */
    public java.lang.Long getRecipientEmployeeId() {
        return recipientEmployeeId;
    }


    /**
     * Sets the recipientEmployeeId value for this SendMailParamsVo.
     * 
     * @param recipientEmployeeId
     */
    public void setRecipientEmployeeId(java.lang.Long recipientEmployeeId) {
        this.recipientEmployeeId = recipientEmployeeId;
    }


    /**
     * Gets the senderEmployeeId value for this SendMailParamsVo.
     * 
     * @return senderEmployeeId
     */
    public java.lang.Long getSenderEmployeeId() {
        return senderEmployeeId;
    }


    /**
     * Sets the senderEmployeeId value for this SendMailParamsVo.
     * 
     * @param senderEmployeeId
     */
    public void setSenderEmployeeId(java.lang.Long senderEmployeeId) {
        this.senderEmployeeId = senderEmployeeId;
    }


    /**
     * Gets the tradeId value for this SendMailParamsVo.
     * 
     * @return tradeId
     */
    public java.lang.Long getTradeId() {
        return tradeId;
    }


    /**
     * Sets the tradeId value for this SendMailParamsVo.
     * 
     * @param tradeId
     */
    public void setTradeId(java.lang.Long tradeId) {
        this.tradeId = tradeId;
    }


    /**
     * Gets the type value for this SendMailParamsVo.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this SendMailParamsVo.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SendMailParamsVo)) return false;
        SendMailParamsVo other = (SendMailParamsVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.additionalParamNames==null && other.getAdditionalParamNames()==null) || 
             (this.additionalParamNames!=null &&
              java.util.Arrays.equals(this.additionalParamNames, other.getAdditionalParamNames()))) &&
            ((this.additionalParamValues==null && other.getAdditionalParamValues()==null) || 
             (this.additionalParamValues!=null &&
              java.util.Arrays.equals(this.additionalParamValues, other.getAdditionalParamValues()))) &&
            ((this.parentMailId==null && other.getParentMailId()==null) || 
             (this.parentMailId!=null &&
              this.parentMailId.equals(other.getParentMailId()))) &&
            ((this.recipientEmployeeId==null && other.getRecipientEmployeeId()==null) || 
             (this.recipientEmployeeId!=null &&
              this.recipientEmployeeId.equals(other.getRecipientEmployeeId()))) &&
            ((this.senderEmployeeId==null && other.getSenderEmployeeId()==null) || 
             (this.senderEmployeeId!=null &&
              this.senderEmployeeId.equals(other.getSenderEmployeeId()))) &&
            ((this.tradeId==null && other.getTradeId()==null) || 
             (this.tradeId!=null &&
              this.tradeId.equals(other.getTradeId()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType())));
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
        if (getAdditionalParamNames() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdditionalParamNames());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdditionalParamNames(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAdditionalParamValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdditionalParamValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdditionalParamValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getParentMailId() != null) {
            _hashCode += getParentMailId().hashCode();
        }
        if (getRecipientEmployeeId() != null) {
            _hashCode += getRecipientEmployeeId().hashCode();
        }
        if (getSenderEmployeeId() != null) {
            _hashCode += getSenderEmployeeId().hashCode();
        }
        if (getTradeId() != null) {
            _hashCode += getTradeId().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendMailParamsVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "SendMailParamsVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalParamNames");
        elemField.setXmlName(new javax.xml.namespace.QName("", "additionalParamNames"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalParamValues");
        elemField.setXmlName(new javax.xml.namespace.QName("", "additionalParamValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentMailId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parentMailId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recipientEmployeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recipientEmployeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderEmployeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senderEmployeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
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
