/**
 * JobVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class JobVo  implements java.io.Serializable {
    private boolean archived;

    private java.lang.Long jobId;

    private int numberOfConvertErrors;

    private int numberOfLoadErrors;

    private int numberOfTotalRecords;

    private int numberOfOpenRecords;

    private java.lang.String sourceSystemCode;

    private java.util.Calendar startBusinessTime;

    private java.util.Calendar startConvertTime;

    private java.util.Calendar startLoadTime;

    private java.lang.String status;

    private java.util.Calendar stopBusinessTime;

    private java.util.Calendar stopConvertTime;

    private java.util.Calendar stopLoadTime;

    private java.util.Calendar systemTime;

    private java.lang.String mandantCode;

    private java.lang.String mandantName;

    private java.lang.String sourceSystemName;

    private java.util.Calendar cob;

    public JobVo() {
    }

    public JobVo(
           boolean archived,
           java.lang.Long jobId,
           int numberOfConvertErrors,
           int numberOfLoadErrors,
           int numberOfTotalRecords,
           int numberOfOpenRecords,
           java.lang.String sourceSystemCode,
           java.util.Calendar startBusinessTime,
           java.util.Calendar startConvertTime,
           java.util.Calendar startLoadTime,
           java.lang.String status,
           java.util.Calendar stopBusinessTime,
           java.util.Calendar stopConvertTime,
           java.util.Calendar stopLoadTime,
           java.util.Calendar systemTime,
           java.lang.String mandantCode,
           java.lang.String mandantName,
           java.lang.String sourceSystemName,
           java.util.Calendar cob) {
           this.archived = archived;
           this.jobId = jobId;
           this.numberOfConvertErrors = numberOfConvertErrors;
           this.numberOfLoadErrors = numberOfLoadErrors;
           this.numberOfTotalRecords = numberOfTotalRecords;
           this.numberOfOpenRecords = numberOfOpenRecords;
           this.sourceSystemCode = sourceSystemCode;
           this.startBusinessTime = startBusinessTime;
           this.startConvertTime = startConvertTime;
           this.startLoadTime = startLoadTime;
           this.status = status;
           this.stopBusinessTime = stopBusinessTime;
           this.stopConvertTime = stopConvertTime;
           this.stopLoadTime = stopLoadTime;
           this.systemTime = systemTime;
           this.mandantCode = mandantCode;
           this.mandantName = mandantName;
           this.sourceSystemName = sourceSystemName;
           this.cob = cob;
    }


    /**
     * Gets the archived value for this JobVo.
     * 
     * @return archived
     */
    public boolean isArchived() {
        return archived;
    }


    /**
     * Sets the archived value for this JobVo.
     * 
     * @param archived
     */
    public void setArchived(boolean archived) {
        this.archived = archived;
    }


    /**
     * Gets the jobId value for this JobVo.
     * 
     * @return jobId
     */
    public java.lang.Long getJobId() {
        return jobId;
    }


    /**
     * Sets the jobId value for this JobVo.
     * 
     * @param jobId
     */
    public void setJobId(java.lang.Long jobId) {
        this.jobId = jobId;
    }


    /**
     * Gets the numberOfConvertErrors value for this JobVo.
     * 
     * @return numberOfConvertErrors
     */
    public int getNumberOfConvertErrors() {
        return numberOfConvertErrors;
    }


    /**
     * Sets the numberOfConvertErrors value for this JobVo.
     * 
     * @param numberOfConvertErrors
     */
    public void setNumberOfConvertErrors(int numberOfConvertErrors) {
        this.numberOfConvertErrors = numberOfConvertErrors;
    }


    /**
     * Gets the numberOfLoadErrors value for this JobVo.
     * 
     * @return numberOfLoadErrors
     */
    public int getNumberOfLoadErrors() {
        return numberOfLoadErrors;
    }


    /**
     * Sets the numberOfLoadErrors value for this JobVo.
     * 
     * @param numberOfLoadErrors
     */
    public void setNumberOfLoadErrors(int numberOfLoadErrors) {
        this.numberOfLoadErrors = numberOfLoadErrors;
    }


    /**
     * Gets the numberOfTotalRecords value for this JobVo.
     * 
     * @return numberOfTotalRecords
     */
    public int getNumberOfTotalRecords() {
        return numberOfTotalRecords;
    }


    /**
     * Sets the numberOfTotalRecords value for this JobVo.
     * 
     * @param numberOfTotalRecords
     */
    public void setNumberOfTotalRecords(int numberOfTotalRecords) {
        this.numberOfTotalRecords = numberOfTotalRecords;
    }


    /**
     * Gets the numberOfOpenRecords value for this JobVo.
     * 
     * @return numberOfOpenRecords
     */
    public int getNumberOfOpenRecords() {
        return numberOfOpenRecords;
    }


    /**
     * Sets the numberOfOpenRecords value for this JobVo.
     * 
     * @param numberOfOpenRecords
     */
    public void setNumberOfOpenRecords(int numberOfOpenRecords) {
        this.numberOfOpenRecords = numberOfOpenRecords;
    }


    /**
     * Gets the sourceSystemCode value for this JobVo.
     * 
     * @return sourceSystemCode
     */
    public java.lang.String getSourceSystemCode() {
        return sourceSystemCode;
    }


    /**
     * Sets the sourceSystemCode value for this JobVo.
     * 
     * @param sourceSystemCode
     */
    public void setSourceSystemCode(java.lang.String sourceSystemCode) {
        this.sourceSystemCode = sourceSystemCode;
    }


    /**
     * Gets the startBusinessTime value for this JobVo.
     * 
     * @return startBusinessTime
     */
    public java.util.Calendar getStartBusinessTime() {
        return startBusinessTime;
    }


    /**
     * Sets the startBusinessTime value for this JobVo.
     * 
     * @param startBusinessTime
     */
    public void setStartBusinessTime(java.util.Calendar startBusinessTime) {
        this.startBusinessTime = startBusinessTime;
    }


    /**
     * Gets the startConvertTime value for this JobVo.
     * 
     * @return startConvertTime
     */
    public java.util.Calendar getStartConvertTime() {
        return startConvertTime;
    }


    /**
     * Sets the startConvertTime value for this JobVo.
     * 
     * @param startConvertTime
     */
    public void setStartConvertTime(java.util.Calendar startConvertTime) {
        this.startConvertTime = startConvertTime;
    }


    /**
     * Gets the startLoadTime value for this JobVo.
     * 
     * @return startLoadTime
     */
    public java.util.Calendar getStartLoadTime() {
        return startLoadTime;
    }


    /**
     * Sets the startLoadTime value for this JobVo.
     * 
     * @param startLoadTime
     */
    public void setStartLoadTime(java.util.Calendar startLoadTime) {
        this.startLoadTime = startLoadTime;
    }


    /**
     * Gets the status value for this JobVo.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this JobVo.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the stopBusinessTime value for this JobVo.
     * 
     * @return stopBusinessTime
     */
    public java.util.Calendar getStopBusinessTime() {
        return stopBusinessTime;
    }


    /**
     * Sets the stopBusinessTime value for this JobVo.
     * 
     * @param stopBusinessTime
     */
    public void setStopBusinessTime(java.util.Calendar stopBusinessTime) {
        this.stopBusinessTime = stopBusinessTime;
    }


    /**
     * Gets the stopConvertTime value for this JobVo.
     * 
     * @return stopConvertTime
     */
    public java.util.Calendar getStopConvertTime() {
        return stopConvertTime;
    }


    /**
     * Sets the stopConvertTime value for this JobVo.
     * 
     * @param stopConvertTime
     */
    public void setStopConvertTime(java.util.Calendar stopConvertTime) {
        this.stopConvertTime = stopConvertTime;
    }


    /**
     * Gets the stopLoadTime value for this JobVo.
     * 
     * @return stopLoadTime
     */
    public java.util.Calendar getStopLoadTime() {
        return stopLoadTime;
    }


    /**
     * Sets the stopLoadTime value for this JobVo.
     * 
     * @param stopLoadTime
     */
    public void setStopLoadTime(java.util.Calendar stopLoadTime) {
        this.stopLoadTime = stopLoadTime;
    }


    /**
     * Gets the systemTime value for this JobVo.
     * 
     * @return systemTime
     */
    public java.util.Calendar getSystemTime() {
        return systemTime;
    }


    /**
     * Sets the systemTime value for this JobVo.
     * 
     * @param systemTime
     */
    public void setSystemTime(java.util.Calendar systemTime) {
        this.systemTime = systemTime;
    }


    /**
     * Gets the mandantCode value for this JobVo.
     * 
     * @return mandantCode
     */
    public java.lang.String getMandantCode() {
        return mandantCode;
    }


    /**
     * Sets the mandantCode value for this JobVo.
     * 
     * @param mandantCode
     */
    public void setMandantCode(java.lang.String mandantCode) {
        this.mandantCode = mandantCode;
    }


    /**
     * Gets the mandantName value for this JobVo.
     * 
     * @return mandantName
     */
    public java.lang.String getMandantName() {
        return mandantName;
    }


    /**
     * Sets the mandantName value for this JobVo.
     * 
     * @param mandantName
     */
    public void setMandantName(java.lang.String mandantName) {
        this.mandantName = mandantName;
    }


    /**
     * Gets the sourceSystemName value for this JobVo.
     * 
     * @return sourceSystemName
     */
    public java.lang.String getSourceSystemName() {
        return sourceSystemName;
    }


    /**
     * Sets the sourceSystemName value for this JobVo.
     * 
     * @param sourceSystemName
     */
    public void setSourceSystemName(java.lang.String sourceSystemName) {
        this.sourceSystemName = sourceSystemName;
    }


    /**
     * Gets the cob value for this JobVo.
     * 
     * @return cob
     */
    public java.util.Calendar getCob() {
        return cob;
    }


    /**
     * Sets the cob value for this JobVo.
     * 
     * @param cob
     */
    public void setCob(java.util.Calendar cob) {
        this.cob = cob;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof JobVo)) return false;
        JobVo other = (JobVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.archived == other.isArchived() &&
            ((this.jobId==null && other.getJobId()==null) || 
             (this.jobId!=null &&
              this.jobId.equals(other.getJobId()))) &&
            this.numberOfConvertErrors == other.getNumberOfConvertErrors() &&
            this.numberOfLoadErrors == other.getNumberOfLoadErrors() &&
            this.numberOfTotalRecords == other.getNumberOfTotalRecords() &&
            this.numberOfOpenRecords == other.getNumberOfOpenRecords() &&
            ((this.sourceSystemCode==null && other.getSourceSystemCode()==null) || 
             (this.sourceSystemCode!=null &&
              this.sourceSystemCode.equals(other.getSourceSystemCode()))) &&
            ((this.startBusinessTime==null && other.getStartBusinessTime()==null) || 
             (this.startBusinessTime!=null &&
              this.startBusinessTime.equals(other.getStartBusinessTime()))) &&
            ((this.startConvertTime==null && other.getStartConvertTime()==null) || 
             (this.startConvertTime!=null &&
              this.startConvertTime.equals(other.getStartConvertTime()))) &&
            ((this.startLoadTime==null && other.getStartLoadTime()==null) || 
             (this.startLoadTime!=null &&
              this.startLoadTime.equals(other.getStartLoadTime()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.stopBusinessTime==null && other.getStopBusinessTime()==null) || 
             (this.stopBusinessTime!=null &&
              this.stopBusinessTime.equals(other.getStopBusinessTime()))) &&
            ((this.stopConvertTime==null && other.getStopConvertTime()==null) || 
             (this.stopConvertTime!=null &&
              this.stopConvertTime.equals(other.getStopConvertTime()))) &&
            ((this.stopLoadTime==null && other.getStopLoadTime()==null) || 
             (this.stopLoadTime!=null &&
              this.stopLoadTime.equals(other.getStopLoadTime()))) &&
            ((this.systemTime==null && other.getSystemTime()==null) || 
             (this.systemTime!=null &&
              this.systemTime.equals(other.getSystemTime()))) &&
            ((this.mandantCode==null && other.getMandantCode()==null) || 
             (this.mandantCode!=null &&
              this.mandantCode.equals(other.getMandantCode()))) &&
            ((this.mandantName==null && other.getMandantName()==null) || 
             (this.mandantName!=null &&
              this.mandantName.equals(other.getMandantName()))) &&
            ((this.sourceSystemName==null && other.getSourceSystemName()==null) || 
             (this.sourceSystemName!=null &&
              this.sourceSystemName.equals(other.getSourceSystemName()))) &&
            ((this.cob==null && other.getCob()==null) || 
             (this.cob!=null &&
              this.cob.equals(other.getCob())));
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
        _hashCode += (isArchived() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getJobId() != null) {
            _hashCode += getJobId().hashCode();
        }
        _hashCode += getNumberOfConvertErrors();
        _hashCode += getNumberOfLoadErrors();
        _hashCode += getNumberOfTotalRecords();
        _hashCode += getNumberOfOpenRecords();
        if (getSourceSystemCode() != null) {
            _hashCode += getSourceSystemCode().hashCode();
        }
        if (getStartBusinessTime() != null) {
            _hashCode += getStartBusinessTime().hashCode();
        }
        if (getStartConvertTime() != null) {
            _hashCode += getStartConvertTime().hashCode();
        }
        if (getStartLoadTime() != null) {
            _hashCode += getStartLoadTime().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getStopBusinessTime() != null) {
            _hashCode += getStopBusinessTime().hashCode();
        }
        if (getStopConvertTime() != null) {
            _hashCode += getStopConvertTime().hashCode();
        }
        if (getStopLoadTime() != null) {
            _hashCode += getStopLoadTime().hashCode();
        }
        if (getSystemTime() != null) {
            _hashCode += getSystemTime().hashCode();
        }
        if (getMandantCode() != null) {
            _hashCode += getMandantCode().hashCode();
        }
        if (getMandantName() != null) {
            _hashCode += getMandantName().hashCode();
        }
        if (getSourceSystemName() != null) {
            _hashCode += getSourceSystemName().hashCode();
        }
        if (getCob() != null) {
            _hashCode += getCob().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(JobVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "JobVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archived");
        elemField.setXmlName(new javax.xml.namespace.QName("", "archived"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "jobId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfConvertErrors");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberOfConvertErrors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfLoadErrors");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberOfLoadErrors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfTotalRecords");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberOfTotalRecords"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfOpenRecords");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberOfOpenRecords"));
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
        elemField.setFieldName("startBusinessTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startBusinessTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startConvertTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startConvertTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startLoadTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startLoadTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stopBusinessTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stopBusinessTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stopConvertTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stopConvertTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stopLoadTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stopLoadTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("systemTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "systemTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandantCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mandantCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandantName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mandantName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceSystemName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceSystemName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cob");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cob"));
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
