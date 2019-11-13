/**
 * MgbTaskVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class MgbTaskVo  extends de.westlb.mgb.client.server.vo.EmployeeSearchResultEntryVo  implements java.io.Serializable {
    private java.lang.String changedBy;

    private java.util.Calendar changedDate;

    private java.lang.String client;

    private java.lang.String createdBy;

    private java.util.Calendar creationDate;

    private java.lang.String mandantCode;

    private java.lang.String message;

    private java.util.Calendar startTime;

    private java.lang.String state;

    private java.util.Calendar stopTime;

    private java.lang.Long taskId;

    private java.lang.String taskName;

    private java.lang.String threadName;

    private java.lang.String user;

    public MgbTaskVo() {
    }

    public MgbTaskVo(
           boolean controller,
           java.lang.String email,
           java.lang.Long employeeId,
           java.lang.String firstName,
           java.lang.String lastName,
           java.lang.String phone,
           boolean trader,
           java.lang.String report,
           java.lang.String changedBy,
           java.util.Calendar changedDate,
           java.lang.String client,
           java.lang.String createdBy,
           java.util.Calendar creationDate,
           java.lang.String mandantCode,
           java.lang.String message,
           java.util.Calendar startTime,
           java.lang.String state,
           java.util.Calendar stopTime,
           java.lang.Long taskId,
           java.lang.String taskName,
           java.lang.String threadName,
           java.lang.String user) {
        super(
            controller,
            email,
            employeeId,
            firstName,
            lastName,
            phone,
            trader,
            report);
        this.changedBy = changedBy;
        this.changedDate = changedDate;
        this.client = client;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.mandantCode = mandantCode;
        this.message = message;
        this.startTime = startTime;
        this.state = state;
        this.stopTime = stopTime;
        this.taskId = taskId;
        this.taskName = taskName;
        this.threadName = threadName;
        this.user = user;
    }


    /**
     * Gets the changedBy value for this MgbTaskVo.
     * 
     * @return changedBy
     */
    public java.lang.String getChangedBy() {
        return changedBy;
    }


    /**
     * Sets the changedBy value for this MgbTaskVo.
     * 
     * @param changedBy
     */
    public void setChangedBy(java.lang.String changedBy) {
        this.changedBy = changedBy;
    }


    /**
     * Gets the changedDate value for this MgbTaskVo.
     * 
     * @return changedDate
     */
    public java.util.Calendar getChangedDate() {
        return changedDate;
    }


    /**
     * Sets the changedDate value for this MgbTaskVo.
     * 
     * @param changedDate
     */
    public void setChangedDate(java.util.Calendar changedDate) {
        this.changedDate = changedDate;
    }


    /**
     * Gets the client value for this MgbTaskVo.
     * 
     * @return client
     */
    public java.lang.String getClient() {
        return client;
    }


    /**
     * Sets the client value for this MgbTaskVo.
     * 
     * @param client
     */
    public void setClient(java.lang.String client) {
        this.client = client;
    }


    /**
     * Gets the createdBy value for this MgbTaskVo.
     * 
     * @return createdBy
     */
    public java.lang.String getCreatedBy() {
        return createdBy;
    }


    /**
     * Sets the createdBy value for this MgbTaskVo.
     * 
     * @param createdBy
     */
    public void setCreatedBy(java.lang.String createdBy) {
        this.createdBy = createdBy;
    }


    /**
     * Gets the creationDate value for this MgbTaskVo.
     * 
     * @return creationDate
     */
    public java.util.Calendar getCreationDate() {
        return creationDate;
    }


    /**
     * Sets the creationDate value for this MgbTaskVo.
     * 
     * @param creationDate
     */
    public void setCreationDate(java.util.Calendar creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * Gets the mandantCode value for this MgbTaskVo.
     * 
     * @return mandantCode
     */
    public java.lang.String getMandantCode() {
        return mandantCode;
    }


    /**
     * Sets the mandantCode value for this MgbTaskVo.
     * 
     * @param mandantCode
     */
    public void setMandantCode(java.lang.String mandantCode) {
        this.mandantCode = mandantCode;
    }


    /**
     * Gets the message value for this MgbTaskVo.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this MgbTaskVo.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the startTime value for this MgbTaskVo.
     * 
     * @return startTime
     */
    public java.util.Calendar getStartTime() {
        return startTime;
    }


    /**
     * Sets the startTime value for this MgbTaskVo.
     * 
     * @param startTime
     */
    public void setStartTime(java.util.Calendar startTime) {
        this.startTime = startTime;
    }


    /**
     * Gets the state value for this MgbTaskVo.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this MgbTaskVo.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }


    /**
     * Gets the stopTime value for this MgbTaskVo.
     * 
     * @return stopTime
     */
    public java.util.Calendar getStopTime() {
        return stopTime;
    }


    /**
     * Sets the stopTime value for this MgbTaskVo.
     * 
     * @param stopTime
     */
    public void setStopTime(java.util.Calendar stopTime) {
        this.stopTime = stopTime;
    }


    /**
     * Gets the taskId value for this MgbTaskVo.
     * 
     * @return taskId
     */
    public java.lang.Long getTaskId() {
        return taskId;
    }


    /**
     * Sets the taskId value for this MgbTaskVo.
     * 
     * @param taskId
     */
    public void setTaskId(java.lang.Long taskId) {
        this.taskId = taskId;
    }


    /**
     * Gets the taskName value for this MgbTaskVo.
     * 
     * @return taskName
     */
    public java.lang.String getTaskName() {
        return taskName;
    }


    /**
     * Sets the taskName value for this MgbTaskVo.
     * 
     * @param taskName
     */
    public void setTaskName(java.lang.String taskName) {
        this.taskName = taskName;
    }


    /**
     * Gets the threadName value for this MgbTaskVo.
     * 
     * @return threadName
     */
    public java.lang.String getThreadName() {
        return threadName;
    }


    /**
     * Sets the threadName value for this MgbTaskVo.
     * 
     * @param threadName
     */
    public void setThreadName(java.lang.String threadName) {
        this.threadName = threadName;
    }


    /**
     * Gets the user value for this MgbTaskVo.
     * 
     * @return user
     */
    public java.lang.String getUser() {
        return user;
    }


    /**
     * Sets the user value for this MgbTaskVo.
     * 
     * @param user
     */
    public void setUser(java.lang.String user) {
        this.user = user;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MgbTaskVo)) return false;
        MgbTaskVo other = (MgbTaskVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.changedBy==null && other.getChangedBy()==null) || 
             (this.changedBy!=null &&
              this.changedBy.equals(other.getChangedBy()))) &&
            ((this.changedDate==null && other.getChangedDate()==null) || 
             (this.changedDate!=null &&
              this.changedDate.equals(other.getChangedDate()))) &&
            ((this.client==null && other.getClient()==null) || 
             (this.client!=null &&
              this.client.equals(other.getClient()))) &&
            ((this.createdBy==null && other.getCreatedBy()==null) || 
             (this.createdBy!=null &&
              this.createdBy.equals(other.getCreatedBy()))) &&
            ((this.creationDate==null && other.getCreationDate()==null) || 
             (this.creationDate!=null &&
              this.creationDate.equals(other.getCreationDate()))) &&
            ((this.mandantCode==null && other.getMandantCode()==null) || 
             (this.mandantCode!=null &&
              this.mandantCode.equals(other.getMandantCode()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.startTime==null && other.getStartTime()==null) || 
             (this.startTime!=null &&
              this.startTime.equals(other.getStartTime()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            ((this.stopTime==null && other.getStopTime()==null) || 
             (this.stopTime!=null &&
              this.stopTime.equals(other.getStopTime()))) &&
            ((this.taskId==null && other.getTaskId()==null) || 
             (this.taskId!=null &&
              this.taskId.equals(other.getTaskId()))) &&
            ((this.taskName==null && other.getTaskName()==null) || 
             (this.taskName!=null &&
              this.taskName.equals(other.getTaskName()))) &&
            ((this.threadName==null && other.getThreadName()==null) || 
             (this.threadName!=null &&
              this.threadName.equals(other.getThreadName()))) &&
            ((this.user==null && other.getUser()==null) || 
             (this.user!=null &&
              this.user.equals(other.getUser())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getChangedBy() != null) {
            _hashCode += getChangedBy().hashCode();
        }
        if (getChangedDate() != null) {
            _hashCode += getChangedDate().hashCode();
        }
        if (getClient() != null) {
            _hashCode += getClient().hashCode();
        }
        if (getCreatedBy() != null) {
            _hashCode += getCreatedBy().hashCode();
        }
        if (getCreationDate() != null) {
            _hashCode += getCreationDate().hashCode();
        }
        if (getMandantCode() != null) {
            _hashCode += getMandantCode().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getStartTime() != null) {
            _hashCode += getStartTime().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        if (getStopTime() != null) {
            _hashCode += getStopTime().hashCode();
        }
        if (getTaskId() != null) {
            _hashCode += getTaskId().hashCode();
        }
        if (getTaskName() != null) {
            _hashCode += getTaskName().hashCode();
        }
        if (getThreadName() != null) {
            _hashCode += getThreadName().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MgbTaskVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "MgbTaskVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changedBy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "changedBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "changedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("client");
        elemField.setXmlName(new javax.xml.namespace.QName("", "client"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createdBy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "createdBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "creationDate"));
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
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("", "message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "startTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stopTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stopTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taskId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "taskId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taskName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "taskName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("threadName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "threadName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("", "user"));
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
