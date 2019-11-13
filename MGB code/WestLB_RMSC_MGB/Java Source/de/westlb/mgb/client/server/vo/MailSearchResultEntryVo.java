/**
 * MailSearchResultEntryVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class MailSearchResultEntryVo  implements java.io.Serializable {
    private java.util.Calendar creationDate;

    private java.lang.Long id;

    private java.lang.String mandantCode;

    private java.lang.Long parentId;

    private java.lang.String recipientAdress;

    private java.lang.Long recipientEmployeeId;

    private java.lang.String recipientName;

    private java.lang.String senderAdress;

    private java.lang.Long senderEmployeeId;

    private java.lang.String senderName;

    private java.lang.String subject;

    private java.lang.Long tradeId;

    private java.lang.String type;

    public MailSearchResultEntryVo() {
    }

    public MailSearchResultEntryVo(
           java.util.Calendar creationDate,
           java.lang.Long id,
           java.lang.String mandantCode,
           java.lang.Long parentId,
           java.lang.String recipientAdress,
           java.lang.Long recipientEmployeeId,
           java.lang.String recipientName,
           java.lang.String senderAdress,
           java.lang.Long senderEmployeeId,
           java.lang.String senderName,
           java.lang.String subject,
           java.lang.Long tradeId,
           java.lang.String type) {
           this.creationDate = creationDate;
           this.id = id;
           this.mandantCode = mandantCode;
           this.parentId = parentId;
           this.recipientAdress = recipientAdress;
           this.recipientEmployeeId = recipientEmployeeId;
           this.recipientName = recipientName;
           this.senderAdress = senderAdress;
           this.senderEmployeeId = senderEmployeeId;
           this.senderName = senderName;
           this.subject = subject;
           this.tradeId = tradeId;
           this.type = type;
    }


    /**
     * Gets the creationDate value for this MailSearchResultEntryVo.
     * 
     * @return creationDate
     */
    public java.util.Calendar getCreationDate() {
        return creationDate;
    }


    /**
     * Sets the creationDate value for this MailSearchResultEntryVo.
     * 
     * @param creationDate
     */
    public void setCreationDate(java.util.Calendar creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * Gets the id value for this MailSearchResultEntryVo.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this MailSearchResultEntryVo.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the mandantCode value for this MailSearchResultEntryVo.
     * 
     * @return mandantCode
     */
    public java.lang.String getMandantCode() {
        return mandantCode;
    }


    /**
     * Sets the mandantCode value for this MailSearchResultEntryVo.
     * 
     * @param mandantCode
     */
    public void setMandantCode(java.lang.String mandantCode) {
        this.mandantCode = mandantCode;
    }


    /**
     * Gets the parentId value for this MailSearchResultEntryVo.
     * 
     * @return parentId
     */
    public java.lang.Long getParentId() {
        return parentId;
    }


    /**
     * Sets the parentId value for this MailSearchResultEntryVo.
     * 
     * @param parentId
     */
    public void setParentId(java.lang.Long parentId) {
        this.parentId = parentId;
    }


    /**
     * Gets the recipientAdress value for this MailSearchResultEntryVo.
     * 
     * @return recipientAdress
     */
    public java.lang.String getRecipientAdress() {
        return recipientAdress;
    }


    /**
     * Sets the recipientAdress value for this MailSearchResultEntryVo.
     * 
     * @param recipientAdress
     */
    public void setRecipientAdress(java.lang.String recipientAdress) {
        this.recipientAdress = recipientAdress;
    }


    /**
     * Gets the recipientEmployeeId value for this MailSearchResultEntryVo.
     * 
     * @return recipientEmployeeId
     */
    public java.lang.Long getRecipientEmployeeId() {
        return recipientEmployeeId;
    }


    /**
     * Sets the recipientEmployeeId value for this MailSearchResultEntryVo.
     * 
     * @param recipientEmployeeId
     */
    public void setRecipientEmployeeId(java.lang.Long recipientEmployeeId) {
        this.recipientEmployeeId = recipientEmployeeId;
    }


    /**
     * Gets the recipientName value for this MailSearchResultEntryVo.
     * 
     * @return recipientName
     */
    public java.lang.String getRecipientName() {
        return recipientName;
    }


    /**
     * Sets the recipientName value for this MailSearchResultEntryVo.
     * 
     * @param recipientName
     */
    public void setRecipientName(java.lang.String recipientName) {
        this.recipientName = recipientName;
    }


    /**
     * Gets the senderAdress value for this MailSearchResultEntryVo.
     * 
     * @return senderAdress
     */
    public java.lang.String getSenderAdress() {
        return senderAdress;
    }


    /**
     * Sets the senderAdress value for this MailSearchResultEntryVo.
     * 
     * @param senderAdress
     */
    public void setSenderAdress(java.lang.String senderAdress) {
        this.senderAdress = senderAdress;
    }


    /**
     * Gets the senderEmployeeId value for this MailSearchResultEntryVo.
     * 
     * @return senderEmployeeId
     */
    public java.lang.Long getSenderEmployeeId() {
        return senderEmployeeId;
    }


    /**
     * Sets the senderEmployeeId value for this MailSearchResultEntryVo.
     * 
     * @param senderEmployeeId
     */
    public void setSenderEmployeeId(java.lang.Long senderEmployeeId) {
        this.senderEmployeeId = senderEmployeeId;
    }


    /**
     * Gets the senderName value for this MailSearchResultEntryVo.
     * 
     * @return senderName
     */
    public java.lang.String getSenderName() {
        return senderName;
    }


    /**
     * Sets the senderName value for this MailSearchResultEntryVo.
     * 
     * @param senderName
     */
    public void setSenderName(java.lang.String senderName) {
        this.senderName = senderName;
    }


    /**
     * Gets the subject value for this MailSearchResultEntryVo.
     * 
     * @return subject
     */
    public java.lang.String getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this MailSearchResultEntryVo.
     * 
     * @param subject
     */
    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }


    /**
     * Gets the tradeId value for this MailSearchResultEntryVo.
     * 
     * @return tradeId
     */
    public java.lang.Long getTradeId() {
        return tradeId;
    }


    /**
     * Sets the tradeId value for this MailSearchResultEntryVo.
     * 
     * @param tradeId
     */
    public void setTradeId(java.lang.Long tradeId) {
        this.tradeId = tradeId;
    }


    /**
     * Gets the type value for this MailSearchResultEntryVo.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this MailSearchResultEntryVo.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MailSearchResultEntryVo)) return false;
        MailSearchResultEntryVo other = (MailSearchResultEntryVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.creationDate==null && other.getCreationDate()==null) || 
             (this.creationDate!=null &&
              this.creationDate.equals(other.getCreationDate()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.mandantCode==null && other.getMandantCode()==null) || 
             (this.mandantCode!=null &&
              this.mandantCode.equals(other.getMandantCode()))) &&
            ((this.parentId==null && other.getParentId()==null) || 
             (this.parentId!=null &&
              this.parentId.equals(other.getParentId()))) &&
            ((this.recipientAdress==null && other.getRecipientAdress()==null) || 
             (this.recipientAdress!=null &&
              this.recipientAdress.equals(other.getRecipientAdress()))) &&
            ((this.recipientEmployeeId==null && other.getRecipientEmployeeId()==null) || 
             (this.recipientEmployeeId!=null &&
              this.recipientEmployeeId.equals(other.getRecipientEmployeeId()))) &&
            ((this.recipientName==null && other.getRecipientName()==null) || 
             (this.recipientName!=null &&
              this.recipientName.equals(other.getRecipientName()))) &&
            ((this.senderAdress==null && other.getSenderAdress()==null) || 
             (this.senderAdress!=null &&
              this.senderAdress.equals(other.getSenderAdress()))) &&
            ((this.senderEmployeeId==null && other.getSenderEmployeeId()==null) || 
             (this.senderEmployeeId!=null &&
              this.senderEmployeeId.equals(other.getSenderEmployeeId()))) &&
            ((this.senderName==null && other.getSenderName()==null) || 
             (this.senderName!=null &&
              this.senderName.equals(other.getSenderName()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject()))) &&
            ((this.tradeId==null && other.getTradeId()==null) || 
             (this.tradeId!=null &&
              this.tradeId.equals(other.getTradeId()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType())));
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
        if (getCreationDate() != null) {
            _hashCode += getCreationDate().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getMandantCode() != null) {
            _hashCode += getMandantCode().hashCode();
        }
        if (getParentId() != null) {
            _hashCode += getParentId().hashCode();
        }
        if (getRecipientAdress() != null) {
            _hashCode += getRecipientAdress().hashCode();
        }
        if (getRecipientEmployeeId() != null) {
            _hashCode += getRecipientEmployeeId().hashCode();
        }
        if (getRecipientName() != null) {
            _hashCode += getRecipientName().hashCode();
        }
        if (getSenderAdress() != null) {
            _hashCode += getSenderAdress().hashCode();
        }
        if (getSenderEmployeeId() != null) {
            _hashCode += getSenderEmployeeId().hashCode();
        }
        if (getSenderName() != null) {
            _hashCode += getSenderName().hashCode();
        }
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        if (getTradeId() != null) {
            _hashCode += getTradeId().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MailSearchResultEntryVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "MailSearchResultEntryVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "creationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandantCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mandantCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recipientAdress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recipientAdress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recipientEmployeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recipientEmployeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recipientName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recipientName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderAdress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senderAdress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderEmployeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senderEmployeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "senderName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
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
