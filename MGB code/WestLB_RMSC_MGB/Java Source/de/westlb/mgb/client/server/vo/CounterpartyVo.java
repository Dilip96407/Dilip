/**
 * CounterpartyVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class CounterpartyVo  implements java.io.Serializable {
    private java.lang.String counterpartyId;

    private boolean deactivated;

    private java.lang.Long id;

    private java.lang.String sourceSystemCode;

    private java.lang.Long sparkassenId;

    private java.lang.String sparkassenLongName;

    private java.lang.String sparkassenName;

    public CounterpartyVo() {
    }

    public CounterpartyVo(
           java.lang.String counterpartyId,
           boolean deactivated,
           java.lang.Long id,
           java.lang.String sourceSystemCode,
           java.lang.Long sparkassenId,
           java.lang.String sparkassenLongName,
           java.lang.String sparkassenName) {
           this.counterpartyId = counterpartyId;
           this.deactivated = deactivated;
           this.id = id;
           this.sourceSystemCode = sourceSystemCode;
           this.sparkassenId = sparkassenId;
           this.sparkassenLongName = sparkassenLongName;
           this.sparkassenName = sparkassenName;
    }


    /**
     * Gets the counterpartyId value for this CounterpartyVo.
     * 
     * @return counterpartyId
     */
    public java.lang.String getCounterpartyId() {
        return counterpartyId;
    }


    /**
     * Sets the counterpartyId value for this CounterpartyVo.
     * 
     * @param counterpartyId
     */
    public void setCounterpartyId(java.lang.String counterpartyId) {
        this.counterpartyId = counterpartyId;
    }


    /**
     * Gets the deactivated value for this CounterpartyVo.
     * 
     * @return deactivated
     */
    public boolean isDeactivated() {
        return deactivated;
    }


    /**
     * Sets the deactivated value for this CounterpartyVo.
     * 
     * @param deactivated
     */
    public void setDeactivated(boolean deactivated) {
        this.deactivated = deactivated;
    }


    /**
     * Gets the id value for this CounterpartyVo.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this CounterpartyVo.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the sourceSystemCode value for this CounterpartyVo.
     * 
     * @return sourceSystemCode
     */
    public java.lang.String getSourceSystemCode() {
        return sourceSystemCode;
    }


    /**
     * Sets the sourceSystemCode value for this CounterpartyVo.
     * 
     * @param sourceSystemCode
     */
    public void setSourceSystemCode(java.lang.String sourceSystemCode) {
        this.sourceSystemCode = sourceSystemCode;
    }


    /**
     * Gets the sparkassenId value for this CounterpartyVo.
     * 
     * @return sparkassenId
     */
    public java.lang.Long getSparkassenId() {
        return sparkassenId;
    }


    /**
     * Sets the sparkassenId value for this CounterpartyVo.
     * 
     * @param sparkassenId
     */
    public void setSparkassenId(java.lang.Long sparkassenId) {
        this.sparkassenId = sparkassenId;
    }


    /**
     * Gets the sparkassenLongName value for this CounterpartyVo.
     * 
     * @return sparkassenLongName
     */
    public java.lang.String getSparkassenLongName() {
        return sparkassenLongName;
    }


    /**
     * Sets the sparkassenLongName value for this CounterpartyVo.
     * 
     * @param sparkassenLongName
     */
    public void setSparkassenLongName(java.lang.String sparkassenLongName) {
        this.sparkassenLongName = sparkassenLongName;
    }


    /**
     * Gets the sparkassenName value for this CounterpartyVo.
     * 
     * @return sparkassenName
     */
    public java.lang.String getSparkassenName() {
        return sparkassenName;
    }


    /**
     * Sets the sparkassenName value for this CounterpartyVo.
     * 
     * @param sparkassenName
     */
    public void setSparkassenName(java.lang.String sparkassenName) {
        this.sparkassenName = sparkassenName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CounterpartyVo)) return false;
        CounterpartyVo other = (CounterpartyVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.counterpartyId==null && other.getCounterpartyId()==null) || 
             (this.counterpartyId!=null &&
              this.counterpartyId.equals(other.getCounterpartyId()))) &&
            this.deactivated == other.isDeactivated() &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.sourceSystemCode==null && other.getSourceSystemCode()==null) || 
             (this.sourceSystemCode!=null &&
              this.sourceSystemCode.equals(other.getSourceSystemCode()))) &&
            ((this.sparkassenId==null && other.getSparkassenId()==null) || 
             (this.sparkassenId!=null &&
              this.sparkassenId.equals(other.getSparkassenId()))) &&
            ((this.sparkassenLongName==null && other.getSparkassenLongName()==null) || 
             (this.sparkassenLongName!=null &&
              this.sparkassenLongName.equals(other.getSparkassenLongName()))) &&
            ((this.sparkassenName==null && other.getSparkassenName()==null) || 
             (this.sparkassenName!=null &&
              this.sparkassenName.equals(other.getSparkassenName())));
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
        if (getCounterpartyId() != null) {
            _hashCode += getCounterpartyId().hashCode();
        }
        _hashCode += (isDeactivated() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getSourceSystemCode() != null) {
            _hashCode += getSourceSystemCode().hashCode();
        }
        if (getSparkassenId() != null) {
            _hashCode += getSparkassenId().hashCode();
        }
        if (getSparkassenLongName() != null) {
            _hashCode += getSparkassenLongName().hashCode();
        }
        if (getSparkassenName() != null) {
            _hashCode += getSparkassenName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CounterpartyVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "CounterpartyVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("counterpartyId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "counterpartyId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deactivated");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deactivated"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceSystemCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceSystemCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sparkassenId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sparkassenId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sparkassenLongName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sparkassenLongName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sparkassenName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sparkassenName"));
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
