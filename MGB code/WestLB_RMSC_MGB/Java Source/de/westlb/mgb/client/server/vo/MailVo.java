/**
 * MailVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class MailVo  extends de.westlb.mgb.client.server.vo.MailSearchResultEntryVo  implements java.io.Serializable {
    private java.lang.String acknowledgment;

    private de.westlb.mgb.client.server.vo.EmployeeVo[] copyRecipients;

    private de.westlb.mgb.client.server.vo.TradeHistoryEntryVo reclamationState;

    private boolean supressCopyToSender;

    private java.lang.String text;

    public MailVo() {
    }

    public MailVo(
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
           java.lang.String type,
           java.lang.String acknowledgment,
           de.westlb.mgb.client.server.vo.EmployeeVo[] copyRecipients,
           de.westlb.mgb.client.server.vo.TradeHistoryEntryVo reclamationState,
           boolean supressCopyToSender,
           java.lang.String text) {
        super(
            creationDate,
            id,
            mandantCode,
            parentId,
            recipientAdress,
            recipientEmployeeId,
            recipientName,
            senderAdress,
            senderEmployeeId,
            senderName,
            subject,
            tradeId,
            type);
        this.acknowledgment = acknowledgment;
        this.copyRecipients = copyRecipients;
        this.reclamationState = reclamationState;
        this.supressCopyToSender = supressCopyToSender;
        this.text = text;
    }


    /**
     * Gets the acknowledgment value for this MailVo.
     * 
     * @return acknowledgment
     */
    public java.lang.String getAcknowledgment() {
        return acknowledgment;
    }


    /**
     * Sets the acknowledgment value for this MailVo.
     * 
     * @param acknowledgment
     */
    public void setAcknowledgment(java.lang.String acknowledgment) {
        this.acknowledgment = acknowledgment;
    }


    /**
     * Gets the copyRecipients value for this MailVo.
     * 
     * @return copyRecipients
     */
    public de.westlb.mgb.client.server.vo.EmployeeVo[] getCopyRecipients() {
        return copyRecipients;
    }


    /**
     * Sets the copyRecipients value for this MailVo.
     * 
     * @param copyRecipients
     */
    public void setCopyRecipients(de.westlb.mgb.client.server.vo.EmployeeVo[] copyRecipients) {
        this.copyRecipients = copyRecipients;
    }


    /**
     * Gets the reclamationState value for this MailVo.
     * 
     * @return reclamationState
     */
    public de.westlb.mgb.client.server.vo.TradeHistoryEntryVo getReclamationState() {
        return reclamationState;
    }


    /**
     * Sets the reclamationState value for this MailVo.
     * 
     * @param reclamationState
     */
    public void setReclamationState(de.westlb.mgb.client.server.vo.TradeHistoryEntryVo reclamationState) {
        this.reclamationState = reclamationState;
    }


    /**
     * Gets the supressCopyToSender value for this MailVo.
     * 
     * @return supressCopyToSender
     */
    public boolean isSupressCopyToSender() {
        return supressCopyToSender;
    }


    /**
     * Sets the supressCopyToSender value for this MailVo.
     * 
     * @param supressCopyToSender
     */
    public void setSupressCopyToSender(boolean supressCopyToSender) {
        this.supressCopyToSender = supressCopyToSender;
    }


    /**
     * Gets the text value for this MailVo.
     * 
     * @return text
     */
    public java.lang.String getText() {
        return text;
    }


    /**
     * Sets the text value for this MailVo.
     * 
     * @param text
     */
    public void setText(java.lang.String text) {
        this.text = text;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MailVo)) return false;
        MailVo other = (MailVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.acknowledgment==null && other.getAcknowledgment()==null) || 
             (this.acknowledgment!=null &&
              this.acknowledgment.equals(other.getAcknowledgment()))) &&
            ((this.copyRecipients==null && other.getCopyRecipients()==null) || 
             (this.copyRecipients!=null &&
              java.util.Arrays.equals(this.copyRecipients, other.getCopyRecipients()))) &&
            ((this.reclamationState==null && other.getReclamationState()==null) || 
             (this.reclamationState!=null &&
              this.reclamationState.equals(other.getReclamationState()))) &&
            this.supressCopyToSender == other.isSupressCopyToSender() &&
            ((this.text==null && other.getText()==null) || 
             (this.text!=null &&
              this.text.equals(other.getText())));
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
        if (getAcknowledgment() != null) {
            _hashCode += getAcknowledgment().hashCode();
        }
        if (getCopyRecipients() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCopyRecipients());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCopyRecipients(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReclamationState() != null) {
            _hashCode += getReclamationState().hashCode();
        }
        _hashCode += (isSupressCopyToSender() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getText() != null) {
            _hashCode += getText().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MailVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "MailVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("acknowledgment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "acknowledgment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("copyRecipients");
        elemField.setXmlName(new javax.xml.namespace.QName("", "copyRecipients"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "EmployeeVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationState");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationState"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "TradeHistoryEntryVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("supressCopyToSender");
        elemField.setXmlName(new javax.xml.namespace.QName("", "supressCopyToSender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("text");
        elemField.setXmlName(new javax.xml.namespace.QName("", "text"));
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
