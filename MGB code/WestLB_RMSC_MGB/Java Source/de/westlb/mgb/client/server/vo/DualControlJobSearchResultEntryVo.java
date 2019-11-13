/**
 * DualControlJobSearchResultEntryVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class DualControlJobSearchResultEntryVo  implements java.io.Serializable {
    private java.lang.String entityName;

    private java.lang.String entityType;

    private java.lang.Long id;

    private java.lang.String operation;

    private java.lang.String requestedByName;

    private java.lang.String requestedByNtId;

    private java.util.Calendar requestedDate;

    public DualControlJobSearchResultEntryVo() {
    }

    public DualControlJobSearchResultEntryVo(
           java.lang.String entityName,
           java.lang.String entityType,
           java.lang.Long id,
           java.lang.String operation,
           java.lang.String requestedByName,
           java.lang.String requestedByNtId,
           java.util.Calendar requestedDate) {
           this.entityName = entityName;
           this.entityType = entityType;
           this.id = id;
           this.operation = operation;
           this.requestedByName = requestedByName;
           this.requestedByNtId = requestedByNtId;
           this.requestedDate = requestedDate;
    }


    /**
     * Gets the entityName value for this DualControlJobSearchResultEntryVo.
     * 
     * @return entityName
     */
    public java.lang.String getEntityName() {
        return entityName;
    }


    /**
     * Sets the entityName value for this DualControlJobSearchResultEntryVo.
     * 
     * @param entityName
     */
    public void setEntityName(java.lang.String entityName) {
        this.entityName = entityName;
    }


    /**
     * Gets the entityType value for this DualControlJobSearchResultEntryVo.
     * 
     * @return entityType
     */
    public java.lang.String getEntityType() {
        return entityType;
    }


    /**
     * Sets the entityType value for this DualControlJobSearchResultEntryVo.
     * 
     * @param entityType
     */
    public void setEntityType(java.lang.String entityType) {
        this.entityType = entityType;
    }


    /**
     * Gets the id value for this DualControlJobSearchResultEntryVo.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this DualControlJobSearchResultEntryVo.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the operation value for this DualControlJobSearchResultEntryVo.
     * 
     * @return operation
     */
    public java.lang.String getOperation() {
        return operation;
    }


    /**
     * Sets the operation value for this DualControlJobSearchResultEntryVo.
     * 
     * @param operation
     */
    public void setOperation(java.lang.String operation) {
        this.operation = operation;
    }


    /**
     * Gets the requestedByName value for this DualControlJobSearchResultEntryVo.
     * 
     * @return requestedByName
     */
    public java.lang.String getRequestedByName() {
        return requestedByName;
    }


    /**
     * Sets the requestedByName value for this DualControlJobSearchResultEntryVo.
     * 
     * @param requestedByName
     */
    public void setRequestedByName(java.lang.String requestedByName) {
        this.requestedByName = requestedByName;
    }


    /**
     * Gets the requestedByNtId value for this DualControlJobSearchResultEntryVo.
     * 
     * @return requestedByNtId
     */
    public java.lang.String getRequestedByNtId() {
        return requestedByNtId;
    }


    /**
     * Sets the requestedByNtId value for this DualControlJobSearchResultEntryVo.
     * 
     * @param requestedByNtId
     */
    public void setRequestedByNtId(java.lang.String requestedByNtId) {
        this.requestedByNtId = requestedByNtId;
    }


    /**
     * Gets the requestedDate value for this DualControlJobSearchResultEntryVo.
     * 
     * @return requestedDate
     */
    public java.util.Calendar getRequestedDate() {
        return requestedDate;
    }


    /**
     * Sets the requestedDate value for this DualControlJobSearchResultEntryVo.
     * 
     * @param requestedDate
     */
    public void setRequestedDate(java.util.Calendar requestedDate) {
        this.requestedDate = requestedDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DualControlJobSearchResultEntryVo)) return false;
        DualControlJobSearchResultEntryVo other = (DualControlJobSearchResultEntryVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.entityName==null && other.getEntityName()==null) || 
             (this.entityName!=null &&
              this.entityName.equals(other.getEntityName()))) &&
            ((this.entityType==null && other.getEntityType()==null) || 
             (this.entityType!=null &&
              this.entityType.equals(other.getEntityType()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.operation==null && other.getOperation()==null) || 
             (this.operation!=null &&
              this.operation.equals(other.getOperation()))) &&
            ((this.requestedByName==null && other.getRequestedByName()==null) || 
             (this.requestedByName!=null &&
              this.requestedByName.equals(other.getRequestedByName()))) &&
            ((this.requestedByNtId==null && other.getRequestedByNtId()==null) || 
             (this.requestedByNtId!=null &&
              this.requestedByNtId.equals(other.getRequestedByNtId()))) &&
            ((this.requestedDate==null && other.getRequestedDate()==null) || 
             (this.requestedDate!=null &&
              this.requestedDate.equals(other.getRequestedDate())));
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
        if (getEntityName() != null) {
            _hashCode += getEntityName().hashCode();
        }
        if (getEntityType() != null) {
            _hashCode += getEntityType().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getOperation() != null) {
            _hashCode += getOperation().hashCode();
        }
        if (getRequestedByName() != null) {
            _hashCode += getRequestedByName().hashCode();
        }
        if (getRequestedByNtId() != null) {
            _hashCode += getRequestedByNtId().hashCode();
        }
        if (getRequestedDate() != null) {
            _hashCode += getRequestedDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DualControlJobSearchResultEntryVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "DualControlJobSearchResultEntryVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "entityName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "entityType"));
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
        elemField.setFieldName("operation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "operation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestedByName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestedByName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestedByNtId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestedByNtId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestedDate"));
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
