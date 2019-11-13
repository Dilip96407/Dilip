/**
 * InboxMailVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class InboxMailVo  extends de.westlb.mgb.client.server.vo.MailVo  implements java.io.Serializable {
    private java.lang.Boolean traderResponseExisting;

    public InboxMailVo() {
    }

    public InboxMailVo(
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
           java.lang.String text,
           java.lang.Boolean traderResponseExisting) {
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
            type,
            acknowledgment,
            copyRecipients,
            reclamationState,
            supressCopyToSender,
            text);
        this.traderResponseExisting = traderResponseExisting;
    }


    /**
     * Gets the traderResponseExisting value for this InboxMailVo.
     * 
     * @return traderResponseExisting
     */
    public java.lang.Boolean getTraderResponseExisting() {
        return traderResponseExisting;
    }


    /**
     * Sets the traderResponseExisting value for this InboxMailVo.
     * 
     * @param traderResponseExisting
     */
    public void setTraderResponseExisting(java.lang.Boolean traderResponseExisting) {
        this.traderResponseExisting = traderResponseExisting;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InboxMailVo)) return false;
        InboxMailVo other = (InboxMailVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.traderResponseExisting==null && other.getTraderResponseExisting()==null) || 
             (this.traderResponseExisting!=null &&
              this.traderResponseExisting.equals(other.getTraderResponseExisting())));
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
        if (getTraderResponseExisting() != null) {
            _hashCode += getTraderResponseExisting().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InboxMailVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "InboxMailVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("traderResponseExisting");
        elemField.setXmlName(new javax.xml.namespace.QName("", "traderResponseExisting"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
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
