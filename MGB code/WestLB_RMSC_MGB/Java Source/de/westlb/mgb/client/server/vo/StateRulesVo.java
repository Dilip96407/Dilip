/**
 * StateRulesVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class StateRulesVo  implements java.io.Serializable {
    private java.lang.String comment;

    private java.lang.String conditionEvaluator;

    private java.lang.String conditionName;

    private int priority;

    private java.lang.String sourceSystemCode;

    private de.westlb.mgb.client.server.vo.StateCodeVo state;

    public StateRulesVo() {
    }

    public StateRulesVo(
           java.lang.String comment,
           java.lang.String conditionEvaluator,
           java.lang.String conditionName,
           int priority,
           java.lang.String sourceSystemCode,
           de.westlb.mgb.client.server.vo.StateCodeVo state) {
           this.comment = comment;
           this.conditionEvaluator = conditionEvaluator;
           this.conditionName = conditionName;
           this.priority = priority;
           this.sourceSystemCode = sourceSystemCode;
           this.state = state;
    }


    /**
     * Gets the comment value for this StateRulesVo.
     * 
     * @return comment
     */
    public java.lang.String getComment() {
        return comment;
    }


    /**
     * Sets the comment value for this StateRulesVo.
     * 
     * @param comment
     */
    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }


    /**
     * Gets the conditionEvaluator value for this StateRulesVo.
     * 
     * @return conditionEvaluator
     */
    public java.lang.String getConditionEvaluator() {
        return conditionEvaluator;
    }


    /**
     * Sets the conditionEvaluator value for this StateRulesVo.
     * 
     * @param conditionEvaluator
     */
    public void setConditionEvaluator(java.lang.String conditionEvaluator) {
        this.conditionEvaluator = conditionEvaluator;
    }


    /**
     * Gets the conditionName value for this StateRulesVo.
     * 
     * @return conditionName
     */
    public java.lang.String getConditionName() {
        return conditionName;
    }


    /**
     * Sets the conditionName value for this StateRulesVo.
     * 
     * @param conditionName
     */
    public void setConditionName(java.lang.String conditionName) {
        this.conditionName = conditionName;
    }


    /**
     * Gets the priority value for this StateRulesVo.
     * 
     * @return priority
     */
    public int getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this StateRulesVo.
     * 
     * @param priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }


    /**
     * Gets the sourceSystemCode value for this StateRulesVo.
     * 
     * @return sourceSystemCode
     */
    public java.lang.String getSourceSystemCode() {
        return sourceSystemCode;
    }


    /**
     * Sets the sourceSystemCode value for this StateRulesVo.
     * 
     * @param sourceSystemCode
     */
    public void setSourceSystemCode(java.lang.String sourceSystemCode) {
        this.sourceSystemCode = sourceSystemCode;
    }


    /**
     * Gets the state value for this StateRulesVo.
     * 
     * @return state
     */
    public de.westlb.mgb.client.server.vo.StateCodeVo getState() {
        return state;
    }


    /**
     * Sets the state value for this StateRulesVo.
     * 
     * @param state
     */
    public void setState(de.westlb.mgb.client.server.vo.StateCodeVo state) {
        this.state = state;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StateRulesVo)) return false;
        StateRulesVo other = (StateRulesVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.comment==null && other.getComment()==null) || 
             (this.comment!=null &&
              this.comment.equals(other.getComment()))) &&
            ((this.conditionEvaluator==null && other.getConditionEvaluator()==null) || 
             (this.conditionEvaluator!=null &&
              this.conditionEvaluator.equals(other.getConditionEvaluator()))) &&
            ((this.conditionName==null && other.getConditionName()==null) || 
             (this.conditionName!=null &&
              this.conditionName.equals(other.getConditionName()))) &&
            this.priority == other.getPriority() &&
            ((this.sourceSystemCode==null && other.getSourceSystemCode()==null) || 
             (this.sourceSystemCode!=null &&
              this.sourceSystemCode.equals(other.getSourceSystemCode()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState())));
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
        if (getComment() != null) {
            _hashCode += getComment().hashCode();
        }
        if (getConditionEvaluator() != null) {
            _hashCode += getConditionEvaluator().hashCode();
        }
        if (getConditionName() != null) {
            _hashCode += getConditionName().hashCode();
        }
        _hashCode += getPriority();
        if (getSourceSystemCode() != null) {
            _hashCode += getSourceSystemCode().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StateRulesVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StateRulesVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("priority");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceSystemCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceSystemCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StateCodeVo"));
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
