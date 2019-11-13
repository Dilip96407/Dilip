/**
 * JobSearchParamsVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class JobSearchParamsVo  implements java.io.Serializable {
    private java.lang.Long[] jobIds;

    private java.lang.Integer numberOfJobs;

    private java.util.Calendar importDaysFrom;

    private de.westlb.mgb.client.server.vo.MandantVo mandant;

    public JobSearchParamsVo() {
    }

    public JobSearchParamsVo(
           java.lang.Long[] jobIds,
           java.lang.Integer numberOfJobs,
           java.util.Calendar importDaysFrom,
           de.westlb.mgb.client.server.vo.MandantVo mandant) {
           this.jobIds = jobIds;
           this.numberOfJobs = numberOfJobs;
           this.importDaysFrom = importDaysFrom;
           this.mandant = mandant;
    }


    /**
     * Gets the jobIds value for this JobSearchParamsVo.
     * 
     * @return jobIds
     */
    public java.lang.Long[] getJobIds() {
        return jobIds;
    }


    /**
     * Sets the jobIds value for this JobSearchParamsVo.
     * 
     * @param jobIds
     */
    public void setJobIds(java.lang.Long[] jobIds) {
        this.jobIds = jobIds;
    }


    /**
     * Gets the numberOfJobs value for this JobSearchParamsVo.
     * 
     * @return numberOfJobs
     */
    public java.lang.Integer getNumberOfJobs() {
        return numberOfJobs;
    }


    /**
     * Sets the numberOfJobs value for this JobSearchParamsVo.
     * 
     * @param numberOfJobs
     */
    public void setNumberOfJobs(java.lang.Integer numberOfJobs) {
        this.numberOfJobs = numberOfJobs;
    }


    /**
     * Gets the importDaysFrom value for this JobSearchParamsVo.
     * 
     * @return importDaysFrom
     */
    public java.util.Calendar getImportDaysFrom() {
        return importDaysFrom;
    }


    /**
     * Sets the importDaysFrom value for this JobSearchParamsVo.
     * 
     * @param importDaysFrom
     */
    public void setImportDaysFrom(java.util.Calendar importDaysFrom) {
        this.importDaysFrom = importDaysFrom;
    }


    /**
     * Gets the mandant value for this JobSearchParamsVo.
     * 
     * @return mandant
     */
    public de.westlb.mgb.client.server.vo.MandantVo getMandant() {
        return mandant;
    }


    /**
     * Sets the mandant value for this JobSearchParamsVo.
     * 
     * @param mandant
     */
    public void setMandant(de.westlb.mgb.client.server.vo.MandantVo mandant) {
        this.mandant = mandant;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof JobSearchParamsVo)) return false;
        JobSearchParamsVo other = (JobSearchParamsVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.jobIds==null && other.getJobIds()==null) || 
             (this.jobIds!=null &&
              java.util.Arrays.equals(this.jobIds, other.getJobIds()))) &&
            ((this.numberOfJobs==null && other.getNumberOfJobs()==null) || 
             (this.numberOfJobs!=null &&
              this.numberOfJobs.equals(other.getNumberOfJobs()))) &&
            ((this.importDaysFrom==null && other.getImportDaysFrom()==null) || 
             (this.importDaysFrom!=null &&
              this.importDaysFrom.equals(other.getImportDaysFrom()))) &&
            ((this.mandant==null && other.getMandant()==null) || 
             (this.mandant!=null &&
              this.mandant.equals(other.getMandant())));
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
        if (getJobIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getJobIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getJobIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumberOfJobs() != null) {
            _hashCode += getNumberOfJobs().hashCode();
        }
        if (getImportDaysFrom() != null) {
            _hashCode += getImportDaysFrom().hashCode();
        }
        if (getMandant() != null) {
            _hashCode += getMandant().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(JobSearchParamsVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "JobSearchParamsVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobIds");
        elemField.setXmlName(new javax.xml.namespace.QName("", "jobIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfJobs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberOfJobs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importDaysFrom");
        elemField.setXmlName(new javax.xml.namespace.QName("", "importDaysFrom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandant");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mandant"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "MandantVo"));
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
