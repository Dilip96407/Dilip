/**
 * StornoGroupVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class StornoGroupVo  implements java.io.Serializable {
    private java.lang.String fieldName;

    private java.lang.Object fieldValue;

    private java.lang.String fieldValueChange;

    private java.lang.Long id;

    private java.lang.Long referenceId;

    private java.lang.String referenceTradeId;

    private java.lang.Object referenceValue;

    private java.lang.String tradeId;

    public StornoGroupVo() {
    }

    public StornoGroupVo(
           java.lang.String fieldName,
           java.lang.Object fieldValue,
           java.lang.String fieldValueChange,
           java.lang.Long id,
           java.lang.Long referenceId,
           java.lang.String referenceTradeId,
           java.lang.Object referenceValue,
           java.lang.String tradeId) {
           this.fieldName = fieldName;
           this.fieldValue = fieldValue;
           this.fieldValueChange = fieldValueChange;
           this.id = id;
           this.referenceId = referenceId;
           this.referenceTradeId = referenceTradeId;
           this.referenceValue = referenceValue;
           this.tradeId = tradeId;
    }


    /**
     * Gets the fieldName value for this StornoGroupVo.
     * 
     * @return fieldName
     */
    public java.lang.String getFieldName() {
        return fieldName;
    }


    /**
     * Sets the fieldName value for this StornoGroupVo.
     * 
     * @param fieldName
     */
    public void setFieldName(java.lang.String fieldName) {
        this.fieldName = fieldName;
    }


    /**
     * Gets the fieldValue value for this StornoGroupVo.
     * 
     * @return fieldValue
     */
    public java.lang.Object getFieldValue() {
        return fieldValue;
    }


    /**
     * Sets the fieldValue value for this StornoGroupVo.
     * 
     * @param fieldValue
     */
    public void setFieldValue(java.lang.Object fieldValue) {
        this.fieldValue = fieldValue;
    }


    /**
     * Gets the fieldValueChange value for this StornoGroupVo.
     * 
     * @return fieldValueChange
     */
    public java.lang.String getFieldValueChange() {
        return fieldValueChange;
    }


    /**
     * Sets the fieldValueChange value for this StornoGroupVo.
     * 
     * @param fieldValueChange
     */
    public void setFieldValueChange(java.lang.String fieldValueChange) {
        this.fieldValueChange = fieldValueChange;
    }


    /**
     * Gets the id value for this StornoGroupVo.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this StornoGroupVo.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the referenceId value for this StornoGroupVo.
     * 
     * @return referenceId
     */
    public java.lang.Long getReferenceId() {
        return referenceId;
    }


    /**
     * Sets the referenceId value for this StornoGroupVo.
     * 
     * @param referenceId
     */
    public void setReferenceId(java.lang.Long referenceId) {
        this.referenceId = referenceId;
    }


    /**
     * Gets the referenceTradeId value for this StornoGroupVo.
     * 
     * @return referenceTradeId
     */
    public java.lang.String getReferenceTradeId() {
        return referenceTradeId;
    }


    /**
     * Sets the referenceTradeId value for this StornoGroupVo.
     * 
     * @param referenceTradeId
     */
    public void setReferenceTradeId(java.lang.String referenceTradeId) {
        this.referenceTradeId = referenceTradeId;
    }


    /**
     * Gets the referenceValue value for this StornoGroupVo.
     * 
     * @return referenceValue
     */
    public java.lang.Object getReferenceValue() {
        return referenceValue;
    }


    /**
     * Sets the referenceValue value for this StornoGroupVo.
     * 
     * @param referenceValue
     */
    public void setReferenceValue(java.lang.Object referenceValue) {
        this.referenceValue = referenceValue;
    }


    /**
     * Gets the tradeId value for this StornoGroupVo.
     * 
     * @return tradeId
     */
    public java.lang.String getTradeId() {
        return tradeId;
    }


    /**
     * Sets the tradeId value for this StornoGroupVo.
     * 
     * @param tradeId
     */
    public void setTradeId(java.lang.String tradeId) {
        this.tradeId = tradeId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StornoGroupVo)) return false;
        StornoGroupVo other = (StornoGroupVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fieldName==null && other.getFieldName()==null) || 
             (this.fieldName!=null &&
              this.fieldName.equals(other.getFieldName()))) &&
            ((this.fieldValue==null && other.getFieldValue()==null) || 
             (this.fieldValue!=null &&
              this.fieldValue.equals(other.getFieldValue()))) &&
            ((this.fieldValueChange==null && other.getFieldValueChange()==null) || 
             (this.fieldValueChange!=null &&
              this.fieldValueChange.equals(other.getFieldValueChange()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.referenceId==null && other.getReferenceId()==null) || 
             (this.referenceId!=null &&
              this.referenceId.equals(other.getReferenceId()))) &&
            ((this.referenceTradeId==null && other.getReferenceTradeId()==null) || 
             (this.referenceTradeId!=null &&
              this.referenceTradeId.equals(other.getReferenceTradeId()))) &&
            ((this.referenceValue==null && other.getReferenceValue()==null) || 
             (this.referenceValue!=null &&
              this.referenceValue.equals(other.getReferenceValue()))) &&
            ((this.tradeId==null && other.getTradeId()==null) || 
             (this.tradeId!=null &&
              this.tradeId.equals(other.getTradeId())));
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
        if (getFieldName() != null) {
            _hashCode += getFieldName().hashCode();
        }
        if (getFieldValue() != null) {
            _hashCode += getFieldValue().hashCode();
        }
        if (getFieldValueChange() != null) {
            _hashCode += getFieldValueChange().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getReferenceId() != null) {
            _hashCode += getReferenceId().hashCode();
        }
        if (getReferenceTradeId() != null) {
            _hashCode += getReferenceTradeId().hashCode();
        }
        if (getReferenceValue() != null) {
            _hashCode += getReferenceValue().hashCode();
        }
        if (getTradeId() != null) {
            _hashCode += getTradeId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StornoGroupVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StornoGroupVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fieldName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldValue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fieldValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldValueChange");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fieldValueChange"));
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
        elemField.setFieldName("referenceId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "referenceId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenceTradeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "referenceTradeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenceValue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "referenceValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeId"));
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
