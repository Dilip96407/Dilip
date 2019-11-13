/**
 * DualControlJobSearchParamsVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class DualControlJobSearchParamsVo  implements java.io.Serializable {
    private java.lang.String excludedRequesterNtId;

    private boolean showCommitedJobs;

    public DualControlJobSearchParamsVo() {
    }

    public DualControlJobSearchParamsVo(
           java.lang.String excludedRequesterNtId,
           boolean showCommitedJobs) {
           this.excludedRequesterNtId = excludedRequesterNtId;
           this.showCommitedJobs = showCommitedJobs;
    }


    /**
     * Gets the excludedRequesterNtId value for this DualControlJobSearchParamsVo.
     * 
     * @return excludedRequesterNtId
     */
    public java.lang.String getExcludedRequesterNtId() {
        return excludedRequesterNtId;
    }


    /**
     * Sets the excludedRequesterNtId value for this DualControlJobSearchParamsVo.
     * 
     * @param excludedRequesterNtId
     */
    public void setExcludedRequesterNtId(java.lang.String excludedRequesterNtId) {
        this.excludedRequesterNtId = excludedRequesterNtId;
    }


    /**
     * Gets the showCommitedJobs value for this DualControlJobSearchParamsVo.
     * 
     * @return showCommitedJobs
     */
    public boolean isShowCommitedJobs() {
        return showCommitedJobs;
    }


    /**
     * Sets the showCommitedJobs value for this DualControlJobSearchParamsVo.
     * 
     * @param showCommitedJobs
     */
    public void setShowCommitedJobs(boolean showCommitedJobs) {
        this.showCommitedJobs = showCommitedJobs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DualControlJobSearchParamsVo)) return false;
        DualControlJobSearchParamsVo other = (DualControlJobSearchParamsVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.excludedRequesterNtId==null && other.getExcludedRequesterNtId()==null) || 
             (this.excludedRequesterNtId!=null &&
              this.excludedRequesterNtId.equals(other.getExcludedRequesterNtId()))) &&
            this.showCommitedJobs == other.isShowCommitedJobs();
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
        if (getExcludedRequesterNtId() != null) {
            _hashCode += getExcludedRequesterNtId().hashCode();
        }
        _hashCode += (isShowCommitedJobs() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DualControlJobSearchParamsVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "DualControlJobSearchParamsVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excludedRequesterNtId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "excludedRequesterNtId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("showCommitedJobs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "showCommitedJobs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
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
