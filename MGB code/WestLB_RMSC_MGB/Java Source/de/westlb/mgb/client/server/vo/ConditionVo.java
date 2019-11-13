/**
 * ConditionVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class ConditionVo  implements java.io.Serializable {
    private java.lang.String conditionEvaluator;

    private java.lang.String conditionName;

    private java.lang.String conditionType;

    private java.lang.Long id;

    private java.lang.String sourceSystemCode;

    public ConditionVo() {
    }

    public ConditionVo(
           java.lang.String conditionEvaluator,
           java.lang.String conditionName,
           java.lang.String conditionType,
           java.lang.Long id,
           java.lang.String sourceSystemCode) {
           this.conditionEvaluator = conditionEvaluator;
           this.conditionName = conditionName;
           this.conditionType = conditionType;
           this.id = id;
           this.sourceSystemCode = sourceSystemCode;
    }


    /**
     * Gets the conditionEvaluator value for this ConditionVo.
     * 
     * @return conditionEvaluator
     */
    public java.lang.String getConditionEvaluator() {
        return conditionEvaluator;
    }


    /**
     * Sets the conditionEvaluator value for this ConditionVo.
     * 
     * @param conditionEvaluator
     */
    public void setConditionEvaluator(java.lang.String conditionEvaluator) {
        this.conditionEvaluator = conditionEvaluator;
    }


    /**
     * Gets the conditionName value for this ConditionVo.
     * 
     * @return conditionName
     */
    public java.lang.String getConditionName() {
        return conditionName;
    }


    /**
     * Sets the conditionName value for this ConditionVo.
     * 
     * @param conditionName
     */
    public void setConditionName(java.lang.String conditionName) {
        this.conditionName = conditionName;
    }


    /**
     * Gets the conditionType value for this ConditionVo.
     * 
     * @return conditionType
     */
    public java.lang.String getConditionType() {
        return conditionType;
    }


    /**
     * Sets the conditionType value for this ConditionVo.
     * 
     * @param conditionType
     */
    public void setConditionType(java.lang.String conditionType) {
        this.conditionType = conditionType;
    }


    /**
     * Gets the id value for this ConditionVo.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this ConditionVo.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the sourceSystemCode value for this ConditionVo.
     * 
     * @return sourceSystemCode
     */
    public java.lang.String getSourceSystemCode() {
        return sourceSystemCode;
    }


    /**
     * Sets the sourceSystemCode value for this ConditionVo.
     * 
     * @param sourceSystemCode
     */
    public void setSourceSystemCode(java.lang.String sourceSystemCode) {
        this.sourceSystemCode = sourceSystemCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConditionVo)) return false;
        ConditionVo other = (ConditionVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.conditionEvaluator==null && other.getConditionEvaluator()==null) || 
             (this.conditionEvaluator!=null &&
              this.conditionEvaluator.equals(other.getConditionEvaluator()))) &&
            ((this.conditionName==null && other.getConditionName()==null) || 
             (this.conditionName!=null &&
              this.conditionName.equals(other.getConditionName()))) &&
            ((this.conditionType==null && other.getConditionType()==null) || 
             (this.conditionType!=null &&
              this.conditionType.equals(other.getConditionType()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.sourceSystemCode==null && other.getSourceSystemCode()==null) || 
             (this.sourceSystemCode!=null &&
              this.sourceSystemCode.equals(other.getSourceSystemCode())));
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
        if (getConditionEvaluator() != null) {
            _hashCode += getConditionEvaluator().hashCode();
        }
        if (getConditionName() != null) {
            _hashCode += getConditionName().hashCode();
        }
        if (getConditionType() != null) {
            _hashCode += getConditionType().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getSourceSystemCode() != null) {
            _hashCode += getSourceSystemCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConditionVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "ConditionVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conditionEvaluator");
        elemField.setXmlName(new javax.xml.namespace.QName("", "conditionEvaluator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conditionName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "conditionName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conditionType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "conditionType"));
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
        elemField.setFieldName("sourceSystemCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceSystemCode"));
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
