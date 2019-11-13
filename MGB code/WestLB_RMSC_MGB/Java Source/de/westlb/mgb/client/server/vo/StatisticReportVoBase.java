/**
 * StatisticReportVoBase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public abstract class StatisticReportVoBase  implements java.io.Serializable {
    private de.westlb.mgb.client.server.vo.StatisticContextVo[] context;

    private java.lang.String[] addKeyColumnNames;

    private java.util.Calendar fromDate;

    private java.lang.String[] keyColumnNames;

    private java.lang.String name;

    private java.lang.String[] successorNames;

    private java.util.Calendar toDate;

    public StatisticReportVoBase() {
    }

    public StatisticReportVoBase(
           de.westlb.mgb.client.server.vo.StatisticContextVo[] context,
           java.lang.String[] addKeyColumnNames,
           java.util.Calendar fromDate,
           java.lang.String[] keyColumnNames,
           java.lang.String name,
           java.lang.String[] successorNames,
           java.util.Calendar toDate) {
           this.context = context;
           this.addKeyColumnNames = addKeyColumnNames;
           this.fromDate = fromDate;
           this.keyColumnNames = keyColumnNames;
           this.name = name;
           this.successorNames = successorNames;
           this.toDate = toDate;
    }


    /**
     * Gets the context value for this StatisticReportVoBase.
     * 
     * @return context
     */
    public de.westlb.mgb.client.server.vo.StatisticContextVo[] getContext() {
        return context;
    }


    /**
     * Sets the context value for this StatisticReportVoBase.
     * 
     * @param context
     */
    public void setContext(de.westlb.mgb.client.server.vo.StatisticContextVo[] context) {
        this.context = context;
    }


    /**
     * Gets the addKeyColumnNames value for this StatisticReportVoBase.
     * 
     * @return addKeyColumnNames
     */
    public java.lang.String[] getAddKeyColumnNames() {
        return addKeyColumnNames;
    }


    /**
     * Sets the addKeyColumnNames value for this StatisticReportVoBase.
     * 
     * @param addKeyColumnNames
     */
    public void setAddKeyColumnNames(java.lang.String[] addKeyColumnNames) {
        this.addKeyColumnNames = addKeyColumnNames;
    }


    /**
     * Gets the fromDate value for this StatisticReportVoBase.
     * 
     * @return fromDate
     */
    public java.util.Calendar getFromDate() {
        return fromDate;
    }


    /**
     * Sets the fromDate value for this StatisticReportVoBase.
     * 
     * @param fromDate
     */
    public void setFromDate(java.util.Calendar fromDate) {
        this.fromDate = fromDate;
    }


    /**
     * Gets the keyColumnNames value for this StatisticReportVoBase.
     * 
     * @return keyColumnNames
     */
    public java.lang.String[] getKeyColumnNames() {
        return keyColumnNames;
    }


    /**
     * Sets the keyColumnNames value for this StatisticReportVoBase.
     * 
     * @param keyColumnNames
     */
    public void setKeyColumnNames(java.lang.String[] keyColumnNames) {
        this.keyColumnNames = keyColumnNames;
    }


    /**
     * Gets the name value for this StatisticReportVoBase.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this StatisticReportVoBase.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the successorNames value for this StatisticReportVoBase.
     * 
     * @return successorNames
     */
    public java.lang.String[] getSuccessorNames() {
        return successorNames;
    }


    /**
     * Sets the successorNames value for this StatisticReportVoBase.
     * 
     * @param successorNames
     */
    public void setSuccessorNames(java.lang.String[] successorNames) {
        this.successorNames = successorNames;
    }


    /**
     * Gets the toDate value for this StatisticReportVoBase.
     * 
     * @return toDate
     */
    public java.util.Calendar getToDate() {
        return toDate;
    }


    /**
     * Sets the toDate value for this StatisticReportVoBase.
     * 
     * @param toDate
     */
    public void setToDate(java.util.Calendar toDate) {
        this.toDate = toDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatisticReportVoBase)) return false;
        StatisticReportVoBase other = (StatisticReportVoBase) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.context==null && other.getContext()==null) || 
             (this.context!=null &&
              java.util.Arrays.equals(this.context, other.getContext()))) &&
            ((this.addKeyColumnNames==null && other.getAddKeyColumnNames()==null) || 
             (this.addKeyColumnNames!=null &&
              java.util.Arrays.equals(this.addKeyColumnNames, other.getAddKeyColumnNames()))) &&
            ((this.fromDate==null && other.getFromDate()==null) || 
             (this.fromDate!=null &&
              this.fromDate.equals(other.getFromDate()))) &&
            ((this.keyColumnNames==null && other.getKeyColumnNames()==null) || 
             (this.keyColumnNames!=null &&
              java.util.Arrays.equals(this.keyColumnNames, other.getKeyColumnNames()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.successorNames==null && other.getSuccessorNames()==null) || 
             (this.successorNames!=null &&
              java.util.Arrays.equals(this.successorNames, other.getSuccessorNames()))) &&
            ((this.toDate==null && other.getToDate()==null) || 
             (this.toDate!=null &&
              this.toDate.equals(other.getToDate())));
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
        if (getContext() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContext());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContext(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAddKeyColumnNames() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAddKeyColumnNames());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAddKeyColumnNames(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFromDate() != null) {
            _hashCode += getFromDate().hashCode();
        }
        if (getKeyColumnNames() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getKeyColumnNames());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getKeyColumnNames(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getSuccessorNames() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSuccessorNames());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSuccessorNames(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getToDate() != null) {
            _hashCode += getToDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatisticReportVoBase.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StatisticReportVoBase"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("context");
        elemField.setXmlName(new javax.xml.namespace.QName("", "context"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StatisticContextVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addKeyColumnNames");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addKeyColumnNames"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fromDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keyColumnNames");
        elemField.setXmlName(new javax.xml.namespace.QName("", "keyColumnNames"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("successorNames");
        elemField.setXmlName(new javax.xml.namespace.QName("", "successorNames"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("toDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "toDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
