/**
 * StatisticReportParamsVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class StatisticReportParamsVo  implements java.io.Serializable {
    private de.westlb.mgb.client.server.vo.StatisticContextVo[] context;

    private java.util.Calendar fromDate;

    private java.lang.String predecessorReportName;

    private java.lang.String reportName;

    private java.util.Calendar toDate;

    public StatisticReportParamsVo() {
    }

    public StatisticReportParamsVo(
           de.westlb.mgb.client.server.vo.StatisticContextVo[] context,
           java.util.Calendar fromDate,
           java.lang.String predecessorReportName,
           java.lang.String reportName,
           java.util.Calendar toDate) {
           this.context = context;
           this.fromDate = fromDate;
           this.predecessorReportName = predecessorReportName;
           this.reportName = reportName;
           this.toDate = toDate;
    }


    /**
     * Gets the context value for this StatisticReportParamsVo.
     * 
     * @return context
     */
    public de.westlb.mgb.client.server.vo.StatisticContextVo[] getContext() {
        return context;
    }


    /**
     * Sets the context value for this StatisticReportParamsVo.
     * 
     * @param context
     */
    public void setContext(de.westlb.mgb.client.server.vo.StatisticContextVo[] context) {
        this.context = context;
    }


    /**
     * Gets the fromDate value for this StatisticReportParamsVo.
     * 
     * @return fromDate
     */
    public java.util.Calendar getFromDate() {
        return fromDate;
    }


    /**
     * Sets the fromDate value for this StatisticReportParamsVo.
     * 
     * @param fromDate
     */
    public void setFromDate(java.util.Calendar fromDate) {
        this.fromDate = fromDate;
    }


    /**
     * Gets the predecessorReportName value for this StatisticReportParamsVo.
     * 
     * @return predecessorReportName
     */
    public java.lang.String getPredecessorReportName() {
        return predecessorReportName;
    }


    /**
     * Sets the predecessorReportName value for this StatisticReportParamsVo.
     * 
     * @param predecessorReportName
     */
    public void setPredecessorReportName(java.lang.String predecessorReportName) {
        this.predecessorReportName = predecessorReportName;
    }


    /**
     * Gets the reportName value for this StatisticReportParamsVo.
     * 
     * @return reportName
     */
    public java.lang.String getReportName() {
        return reportName;
    }


    /**
     * Sets the reportName value for this StatisticReportParamsVo.
     * 
     * @param reportName
     */
    public void setReportName(java.lang.String reportName) {
        this.reportName = reportName;
    }


    /**
     * Gets the toDate value for this StatisticReportParamsVo.
     * 
     * @return toDate
     */
    public java.util.Calendar getToDate() {
        return toDate;
    }


    /**
     * Sets the toDate value for this StatisticReportParamsVo.
     * 
     * @param toDate
     */
    public void setToDate(java.util.Calendar toDate) {
        this.toDate = toDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatisticReportParamsVo)) return false;
        StatisticReportParamsVo other = (StatisticReportParamsVo) obj;
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
            ((this.fromDate==null && other.getFromDate()==null) || 
             (this.fromDate!=null &&
              this.fromDate.equals(other.getFromDate()))) &&
            ((this.predecessorReportName==null && other.getPredecessorReportName()==null) || 
             (this.predecessorReportName!=null &&
              this.predecessorReportName.equals(other.getPredecessorReportName()))) &&
            ((this.reportName==null && other.getReportName()==null) || 
             (this.reportName!=null &&
              this.reportName.equals(other.getReportName()))) &&
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
        if (getFromDate() != null) {
            _hashCode += getFromDate().hashCode();
        }
        if (getPredecessorReportName() != null) {
            _hashCode += getPredecessorReportName().hashCode();
        }
        if (getReportName() != null) {
            _hashCode += getReportName().hashCode();
        }
        if (getToDate() != null) {
            _hashCode += getToDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatisticReportParamsVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StatisticReportParamsVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("context");
        elemField.setXmlName(new javax.xml.namespace.QName("", "context"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StatisticContextVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fromDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("predecessorReportName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "predecessorReportName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reportName"));
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
