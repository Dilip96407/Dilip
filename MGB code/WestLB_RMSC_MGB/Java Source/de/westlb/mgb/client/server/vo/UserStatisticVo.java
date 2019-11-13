/**
 * UserStatisticVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class UserStatisticVo  implements java.io.Serializable {
    private int activeSessionCount;

    private java.util.Calendar latestSessionCreateTime;

    private java.util.Calendar latestSessionDestroyTime;

    private int sessionCount;

    private java.lang.String userId;

    private java.lang.String userName;

    public UserStatisticVo() {
    }

    public UserStatisticVo(
           int activeSessionCount,
           java.util.Calendar latestSessionCreateTime,
           java.util.Calendar latestSessionDestroyTime,
           int sessionCount,
           java.lang.String userId,
           java.lang.String userName) {
           this.activeSessionCount = activeSessionCount;
           this.latestSessionCreateTime = latestSessionCreateTime;
           this.latestSessionDestroyTime = latestSessionDestroyTime;
           this.sessionCount = sessionCount;
           this.userId = userId;
           this.userName = userName;
    }


    /**
     * Gets the activeSessionCount value for this UserStatisticVo.
     * 
     * @return activeSessionCount
     */
    public int getActiveSessionCount() {
        return activeSessionCount;
    }


    /**
     * Sets the activeSessionCount value for this UserStatisticVo.
     * 
     * @param activeSessionCount
     */
    public void setActiveSessionCount(int activeSessionCount) {
        this.activeSessionCount = activeSessionCount;
    }


    /**
     * Gets the latestSessionCreateTime value for this UserStatisticVo.
     * 
     * @return latestSessionCreateTime
     */
    public java.util.Calendar getLatestSessionCreateTime() {
        return latestSessionCreateTime;
    }


    /**
     * Sets the latestSessionCreateTime value for this UserStatisticVo.
     * 
     * @param latestSessionCreateTime
     */
    public void setLatestSessionCreateTime(java.util.Calendar latestSessionCreateTime) {
        this.latestSessionCreateTime = latestSessionCreateTime;
    }


    /**
     * Gets the latestSessionDestroyTime value for this UserStatisticVo.
     * 
     * @return latestSessionDestroyTime
     */
    public java.util.Calendar getLatestSessionDestroyTime() {
        return latestSessionDestroyTime;
    }


    /**
     * Sets the latestSessionDestroyTime value for this UserStatisticVo.
     * 
     * @param latestSessionDestroyTime
     */
    public void setLatestSessionDestroyTime(java.util.Calendar latestSessionDestroyTime) {
        this.latestSessionDestroyTime = latestSessionDestroyTime;
    }


    /**
     * Gets the sessionCount value for this UserStatisticVo.
     * 
     * @return sessionCount
     */
    public int getSessionCount() {
        return sessionCount;
    }


    /**
     * Sets the sessionCount value for this UserStatisticVo.
     * 
     * @param sessionCount
     */
    public void setSessionCount(int sessionCount) {
        this.sessionCount = sessionCount;
    }


    /**
     * Gets the userId value for this UserStatisticVo.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this UserStatisticVo.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }


    /**
     * Gets the userName value for this UserStatisticVo.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this UserStatisticVo.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UserStatisticVo)) return false;
        UserStatisticVo other = (UserStatisticVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.activeSessionCount == other.getActiveSessionCount() &&
            ((this.latestSessionCreateTime==null && other.getLatestSessionCreateTime()==null) || 
             (this.latestSessionCreateTime!=null &&
              this.latestSessionCreateTime.equals(other.getLatestSessionCreateTime()))) &&
            ((this.latestSessionDestroyTime==null && other.getLatestSessionDestroyTime()==null) || 
             (this.latestSessionDestroyTime!=null &&
              this.latestSessionDestroyTime.equals(other.getLatestSessionDestroyTime()))) &&
            this.sessionCount == other.getSessionCount() &&
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId()))) &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName())));
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
        _hashCode += getActiveSessionCount();
        if (getLatestSessionCreateTime() != null) {
            _hashCode += getLatestSessionCreateTime().hashCode();
        }
        if (getLatestSessionDestroyTime() != null) {
            _hashCode += getLatestSessionDestroyTime().hashCode();
        }
        _hashCode += getSessionCount();
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UserStatisticVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "UserStatisticVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activeSessionCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "activeSessionCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("latestSessionCreateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "latestSessionCreateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("latestSessionDestroyTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "latestSessionDestroyTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sessionCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userName"));
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
