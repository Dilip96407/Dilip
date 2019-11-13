/**
 * StatisticCubeRowVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class StatisticCubeRowVo  implements java.io.Serializable {
    private java.lang.Object[] addKeyValues;

    private java.lang.Object[] keyValues;

    private java.lang.Boolean manualCheckRequired;

    private long noTradesTotal;

    private java.lang.String state;

    private java.lang.String manualCheckRequiredStr;

    public StatisticCubeRowVo() {
    }

    public StatisticCubeRowVo(
           java.lang.Object[] addKeyValues,
           java.lang.Object[] keyValues,
           java.lang.Boolean manualCheckRequired,
           long noTradesTotal,
           java.lang.String state,
           java.lang.String manualCheckRequiredStr) {
           this.addKeyValues = addKeyValues;
           this.keyValues = keyValues;
           this.manualCheckRequired = manualCheckRequired;
           this.noTradesTotal = noTradesTotal;
           this.state = state;
           this.manualCheckRequiredStr = manualCheckRequiredStr;
    }


    /**
     * Gets the addKeyValues value for this StatisticCubeRowVo.
     * 
     * @return addKeyValues
     */
    public java.lang.Object[] getAddKeyValues() {
        return addKeyValues;
    }


    /**
     * Sets the addKeyValues value for this StatisticCubeRowVo.
     * 
     * @param addKeyValues
     */
    public void setAddKeyValues(java.lang.Object[] addKeyValues) {
        this.addKeyValues = addKeyValues;
    }


    /**
     * Gets the keyValues value for this StatisticCubeRowVo.
     * 
     * @return keyValues
     */
    public java.lang.Object[] getKeyValues() {
        return keyValues;
    }


    /**
     * Sets the keyValues value for this StatisticCubeRowVo.
     * 
     * @param keyValues
     */
    public void setKeyValues(java.lang.Object[] keyValues) {
        this.keyValues = keyValues;
    }


    /**
     * Gets the manualCheckRequired value for this StatisticCubeRowVo.
     * 
     * @return manualCheckRequired
     */
    public java.lang.Boolean getManualCheckRequired() {
        return manualCheckRequired;
    }


    /**
     * Sets the manualCheckRequired value for this StatisticCubeRowVo.
     * 
     * @param manualCheckRequired
     */
    public void setManualCheckRequired(java.lang.Boolean manualCheckRequired) {
        this.manualCheckRequired = manualCheckRequired;
    }


    /**
     * Gets the noTradesTotal value for this StatisticCubeRowVo.
     * 
     * @return noTradesTotal
     */
    public long getNoTradesTotal() {
        return noTradesTotal;
    }


    /**
     * Sets the noTradesTotal value for this StatisticCubeRowVo.
     * 
     * @param noTradesTotal
     */
    public void setNoTradesTotal(long noTradesTotal) {
        this.noTradesTotal = noTradesTotal;
    }


    /**
     * Gets the state value for this StatisticCubeRowVo.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this StatisticCubeRowVo.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }


    /**
     * Gets the manualCheckRequiredStr value for this StatisticCubeRowVo.
     * 
     * @return manualCheckRequiredStr
     */
    public java.lang.String getManualCheckRequiredStr() {
        return manualCheckRequiredStr;
    }


    /**
     * Sets the manualCheckRequiredStr value for this StatisticCubeRowVo.
     * 
     * @param manualCheckRequiredStr
     */
    public void setManualCheckRequiredStr(java.lang.String manualCheckRequiredStr) {
        this.manualCheckRequiredStr = manualCheckRequiredStr;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatisticCubeRowVo)) return false;
        StatisticCubeRowVo other = (StatisticCubeRowVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addKeyValues==null && other.getAddKeyValues()==null) || 
             (this.addKeyValues!=null &&
              java.util.Arrays.equals(this.addKeyValues, other.getAddKeyValues()))) &&
            ((this.keyValues==null && other.getKeyValues()==null) || 
             (this.keyValues!=null &&
              java.util.Arrays.equals(this.keyValues, other.getKeyValues()))) &&
            ((this.manualCheckRequired==null && other.getManualCheckRequired()==null) || 
             (this.manualCheckRequired!=null &&
              this.manualCheckRequired.equals(other.getManualCheckRequired()))) &&
            this.noTradesTotal == other.getNoTradesTotal() &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            ((this.manualCheckRequiredStr==null && other.getManualCheckRequiredStr()==null) || 
             (this.manualCheckRequiredStr!=null &&
              this.manualCheckRequiredStr.equals(other.getManualCheckRequiredStr())));
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
        if (getAddKeyValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAddKeyValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAddKeyValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getKeyValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getKeyValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getKeyValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getManualCheckRequired() != null) {
            _hashCode += getManualCheckRequired().hashCode();
        }
        _hashCode += new Long(getNoTradesTotal()).hashCode();
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        if (getManualCheckRequiredStr() != null) {
            _hashCode += getManualCheckRequiredStr().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatisticCubeRowVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StatisticCubeRowVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addKeyValues");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addKeyValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keyValues");
        elemField.setXmlName(new javax.xml.namespace.QName("", "keyValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualCheckRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualCheckRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noTradesTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noTradesTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualCheckRequiredStr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualCheckRequiredStr"));
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
