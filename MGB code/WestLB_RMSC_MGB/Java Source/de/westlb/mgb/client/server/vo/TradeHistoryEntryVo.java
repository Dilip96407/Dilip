/**
 * TradeHistoryEntryVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class TradeHistoryEntryVo  implements java.io.Serializable {
    private java.lang.Long attachmentId;

    private java.lang.String attachmentName;

    private java.lang.String comment;

    private java.lang.String createdByName;

    private java.util.Calendar date;

    private java.lang.Long internalTradeId;

    private java.lang.String reclamationCloseComment;

    private java.lang.Boolean reclamationClosed;

    private java.lang.Integer reclamationLevel;

    private java.lang.String stateCode;

    private java.lang.String type;

    public TradeHistoryEntryVo() {
    }

    public TradeHistoryEntryVo(
           java.lang.Long attachmentId,
           java.lang.String attachmentName,
           java.lang.String comment,
           java.lang.String createdByName,
           java.util.Calendar date,
           java.lang.Long internalTradeId,
           java.lang.String reclamationCloseComment,
           java.lang.Boolean reclamationClosed,
           java.lang.Integer reclamationLevel,
           java.lang.String stateCode,
           java.lang.String type) {
           this.attachmentId = attachmentId;
           this.attachmentName = attachmentName;
           this.comment = comment;
           this.createdByName = createdByName;
           this.date = date;
           this.internalTradeId = internalTradeId;
           this.reclamationCloseComment = reclamationCloseComment;
           this.reclamationClosed = reclamationClosed;
           this.reclamationLevel = reclamationLevel;
           this.stateCode = stateCode;
           this.type = type;
    }


    /**
     * Gets the attachmentId value for this TradeHistoryEntryVo.
     * 
     * @return attachmentId
     */
    public java.lang.Long getAttachmentId() {
        return attachmentId;
    }


    /**
     * Sets the attachmentId value for this TradeHistoryEntryVo.
     * 
     * @param attachmentId
     */
    public void setAttachmentId(java.lang.Long attachmentId) {
        this.attachmentId = attachmentId;
    }


    /**
     * Gets the attachmentName value for this TradeHistoryEntryVo.
     * 
     * @return attachmentName
     */
    public java.lang.String getAttachmentName() {
        return attachmentName;
    }


    /**
     * Sets the attachmentName value for this TradeHistoryEntryVo.
     * 
     * @param attachmentName
     */
    public void setAttachmentName(java.lang.String attachmentName) {
        this.attachmentName = attachmentName;
    }


    /**
     * Gets the comment value for this TradeHistoryEntryVo.
     * 
     * @return comment
     */
    public java.lang.String getComment() {
        return comment;
    }


    /**
     * Sets the comment value for this TradeHistoryEntryVo.
     * 
     * @param comment
     */
    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }


    /**
     * Gets the createdByName value for this TradeHistoryEntryVo.
     * 
     * @return createdByName
     */
    public java.lang.String getCreatedByName() {
        return createdByName;
    }


    /**
     * Sets the createdByName value for this TradeHistoryEntryVo.
     * 
     * @param createdByName
     */
    public void setCreatedByName(java.lang.String createdByName) {
        this.createdByName = createdByName;
    }


    /**
     * Gets the date value for this TradeHistoryEntryVo.
     * 
     * @return date
     */
    public java.util.Calendar getDate() {
        return date;
    }


    /**
     * Sets the date value for this TradeHistoryEntryVo.
     * 
     * @param date
     */
    public void setDate(java.util.Calendar date) {
        this.date = date;
    }


    /**
     * Gets the internalTradeId value for this TradeHistoryEntryVo.
     * 
     * @return internalTradeId
     */
    public java.lang.Long getInternalTradeId() {
        return internalTradeId;
    }


    /**
     * Sets the internalTradeId value for this TradeHistoryEntryVo.
     * 
     * @param internalTradeId
     */
    public void setInternalTradeId(java.lang.Long internalTradeId) {
        this.internalTradeId = internalTradeId;
    }


    /**
     * Gets the reclamationCloseComment value for this TradeHistoryEntryVo.
     * 
     * @return reclamationCloseComment
     */
    public java.lang.String getReclamationCloseComment() {
        return reclamationCloseComment;
    }


    /**
     * Sets the reclamationCloseComment value for this TradeHistoryEntryVo.
     * 
     * @param reclamationCloseComment
     */
    public void setReclamationCloseComment(java.lang.String reclamationCloseComment) {
        this.reclamationCloseComment = reclamationCloseComment;
    }


    /**
     * Gets the reclamationClosed value for this TradeHistoryEntryVo.
     * 
     * @return reclamationClosed
     */
    public java.lang.Boolean getReclamationClosed() {
        return reclamationClosed;
    }


    /**
     * Sets the reclamationClosed value for this TradeHistoryEntryVo.
     * 
     * @param reclamationClosed
     */
    public void setReclamationClosed(java.lang.Boolean reclamationClosed) {
        this.reclamationClosed = reclamationClosed;
    }


    /**
     * Gets the reclamationLevel value for this TradeHistoryEntryVo.
     * 
     * @return reclamationLevel
     */
    public java.lang.Integer getReclamationLevel() {
        return reclamationLevel;
    }


    /**
     * Sets the reclamationLevel value for this TradeHistoryEntryVo.
     * 
     * @param reclamationLevel
     */
    public void setReclamationLevel(java.lang.Integer reclamationLevel) {
        this.reclamationLevel = reclamationLevel;
    }


    /**
     * Gets the stateCode value for this TradeHistoryEntryVo.
     * 
     * @return stateCode
     */
    public java.lang.String getStateCode() {
        return stateCode;
    }


    /**
     * Sets the stateCode value for this TradeHistoryEntryVo.
     * 
     * @param stateCode
     */
    public void setStateCode(java.lang.String stateCode) {
        this.stateCode = stateCode;
    }


    /**
     * Gets the type value for this TradeHistoryEntryVo.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this TradeHistoryEntryVo.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TradeHistoryEntryVo)) return false;
        TradeHistoryEntryVo other = (TradeHistoryEntryVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.attachmentId==null && other.getAttachmentId()==null) || 
             (this.attachmentId!=null &&
              this.attachmentId.equals(other.getAttachmentId()))) &&
            ((this.attachmentName==null && other.getAttachmentName()==null) || 
             (this.attachmentName!=null &&
              this.attachmentName.equals(other.getAttachmentName()))) &&
            ((this.comment==null && other.getComment()==null) || 
             (this.comment!=null &&
              this.comment.equals(other.getComment()))) &&
            ((this.createdByName==null && other.getCreatedByName()==null) || 
             (this.createdByName!=null &&
              this.createdByName.equals(other.getCreatedByName()))) &&
            ((this.date==null && other.getDate()==null) || 
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.internalTradeId==null && other.getInternalTradeId()==null) || 
             (this.internalTradeId!=null &&
              this.internalTradeId.equals(other.getInternalTradeId()))) &&
            ((this.reclamationCloseComment==null && other.getReclamationCloseComment()==null) || 
             (this.reclamationCloseComment!=null &&
              this.reclamationCloseComment.equals(other.getReclamationCloseComment()))) &&
            ((this.reclamationClosed==null && other.getReclamationClosed()==null) || 
             (this.reclamationClosed!=null &&
              this.reclamationClosed.equals(other.getReclamationClosed()))) &&
            ((this.reclamationLevel==null && other.getReclamationLevel()==null) || 
             (this.reclamationLevel!=null &&
              this.reclamationLevel.equals(other.getReclamationLevel()))) &&
            ((this.stateCode==null && other.getStateCode()==null) || 
             (this.stateCode!=null &&
              this.stateCode.equals(other.getStateCode()))) &&
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
        if (getAttachmentId() != null) {
            _hashCode += getAttachmentId().hashCode();
        }
        if (getAttachmentName() != null) {
            _hashCode += getAttachmentName().hashCode();
        }
        if (getComment() != null) {
            _hashCode += getComment().hashCode();
        }
        if (getCreatedByName() != null) {
            _hashCode += getCreatedByName().hashCode();
        }
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getInternalTradeId() != null) {
            _hashCode += getInternalTradeId().hashCode();
        }
        if (getReclamationCloseComment() != null) {
            _hashCode += getReclamationCloseComment().hashCode();
        }
        if (getReclamationClosed() != null) {
            _hashCode += getReclamationClosed().hashCode();
        }
        if (getReclamationLevel() != null) {
            _hashCode += getReclamationLevel().hashCode();
        }
        if (getStateCode() != null) {
            _hashCode += getStateCode().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TradeHistoryEntryVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "TradeHistoryEntryVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "attachmentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attachmentName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "attachmentName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createdByName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "createdByName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("internalTradeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "internalTradeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationCloseComment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationCloseComment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationClosed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationClosed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stateCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "stateCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
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
