/**
 * StatisticContextVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class StatisticContextVo  implements java.io.Serializable {
    private java.lang.Object[] addKeyValues;

    private java.lang.Object[] keyValues;

    private java.lang.String reportName;

    public StatisticContextVo() {
    }

    public StatisticContextVo(
           java.lang.Object[] addKeyValues,
           java.lang.Object[] keyValues,
           java.lang.String reportName) {
           this.addKeyValues = addKeyValues;
           this.keyValues = keyValues;
           this.reportName = reportName;
    }


    /**
     * Gets the addKeyValues value for this StatisticContextVo.
     * 
     * @return addKeyValues
     */
    public java.lang.Object[] getAddKeyValues() {
        return addKeyValues;
    }


    /**
     * Sets the addKeyValues value for this StatisticContextVo.
     * 
     * @param addKeyValues
     */
    public void setAddKeyValues(java.lang.Object[] addKeyValues) {
        this.addKeyValues = addKeyValues;
    }


    /**
     * Gets the keyValues value for this StatisticContextVo.
     * 
     * @return keyValues
     */
    public java.lang.Object[] getKeyValues() {
        return keyValues;
    }


    /**
     * Sets the keyValues value for this StatisticContextVo.
     * 
     * @param keyValues
     */
    public void setKeyValues(java.lang.Object[] keyValues) {
        this.keyValues = keyValues;
    }


    /**
     * Gets the reportName value for this StatisticContextVo.
     * 
     * @return reportName
     */
    public java.lang.String getReportName() {
        return reportName;
    }


    /**
     * Sets the reportName value for this StatisticContextVo.
     * 
     * @param reportName
     */
    public void setReportName(java.lang.String reportName) {
        this.reportName = reportName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatisticContextVo)) return false;
        StatisticContextVo other = (StatisticContextVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addKeyValues==null && other.getAddKeyValues()==null) || 
             (this.addKeyValues!=null &&
              java.util.Arrays.equals(this.addKeyValues, other.getAddKeyValues()))) &&
            ((this.keyValues==null && other.getKeyValues()==null) || 
             (this.keyValues!=null &&
              java.util.Arrays.equals(this.keyValues, other.getKeyValues()))) &&
            ((this.reportName==null && other.getReportName()==null) || 
             (this.reportName!=null &&
              this.reportName.equals(other.getReportName())));
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
        if (getAddKeyValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAddKeyValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAddKeyValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getKeyValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getKeyValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getKeyValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReportName() != null) {
            _hashCode += getReportName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatisticContextVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StatisticContextVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addKeyValues");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addKeyValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keyValues");
        elemField.setXmlName(new javax.xml.namespace.QName("", "keyValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reportName"));
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
