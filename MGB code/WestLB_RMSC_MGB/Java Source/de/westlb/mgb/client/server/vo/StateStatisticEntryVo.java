/**
 * StateStatisticEntryVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class StateStatisticEntryVo  implements java.io.Serializable {
    private long count;

    private boolean manualCheckRequired;

    private boolean reclamationRequired;

    private java.lang.String state;

    public StateStatisticEntryVo() {
    }

    public StateStatisticEntryVo(
           long count,
           boolean manualCheckRequired,
           boolean reclamationRequired,
           java.lang.String state) {
           this.count = count;
           this.manualCheckRequired = manualCheckRequired;
           this.reclamationRequired = reclamationRequired;
           this.state = state;
    }


    /**
     * Gets the count value for this StateStatisticEntryVo.
     * 
     * @return count
     */
    public long getCount() {
        return count;
    }


    /**
     * Sets the count value for this StateStatisticEntryVo.
     * 
     * @param count
     */
    public void setCount(long count) {
        this.count = count;
    }


    /**
     * Gets the manualCheckRequired value for this StateStatisticEntryVo.
     * 
     * @return manualCheckRequired
     */
    public boolean isManualCheckRequired() {
        return manualCheckRequired;
    }


    /**
     * Sets the manualCheckRequired value for this StateStatisticEntryVo.
     * 
     * @param manualCheckRequired
     */
    public void setManualCheckRequired(boolean manualCheckRequired) {
        this.manualCheckRequired = manualCheckRequired;
    }


    /**
     * Gets the reclamationRequired value for this StateStatisticEntryVo.
     * 
     * @return reclamationRequired
     */
    public boolean isReclamationRequired() {
        return reclamationRequired;
    }


    /**
     * Sets the reclamationRequired value for this StateStatisticEntryVo.
     * 
     * @param reclamationRequired
     */
    public void setReclamationRequired(boolean reclamationRequired) {
        this.reclamationRequired = reclamationRequired;
    }


    /**
     * Gets the state value for this StateStatisticEntryVo.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this StateStatisticEntryVo.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StateStatisticEntryVo)) return false;
        StateStatisticEntryVo other = (StateStatisticEntryVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.count == other.getCount() &&
            this.manualCheckRequired == other.isManualCheckRequired() &&
            this.reclamationRequired == other.isReclamationRequired() &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState())));
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
        _hashCode += new Long(getCount()).hashCode();
        _hashCode += (isManualCheckRequired() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isReclamationRequired() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StateStatisticEntryVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StateStatisticEntryVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("count");
        elemField.setXmlName(new javax.xml.namespace.QName("", "count"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualCheckRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualCheckRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("", "state"));
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
