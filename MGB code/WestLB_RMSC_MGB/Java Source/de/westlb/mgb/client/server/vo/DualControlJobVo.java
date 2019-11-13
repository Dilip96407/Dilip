/**
 * DualControlJobVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class DualControlJobVo  extends de.westlb.mgb.client.server.vo.DualControlJobSearchResultEntryVo  implements java.io.Serializable {
    private java.lang.String[] names;

    private java.lang.String[] newValues;

    private java.lang.String[] oldValues;

    public DualControlJobVo() {
    }

    public DualControlJobVo(
           java.lang.String entityName,
           java.lang.String entityType,
           java.lang.Long id,
           java.lang.String operation,
           java.lang.String requestedByName,
           java.lang.String requestedByNtId,
           java.util.Calendar requestedDate,
           java.lang.String[] names,
           java.lang.String[] newValues,
           java.lang.String[] oldValues) {
        super(
            entityName,
            entityType,
            id,
            operation,
            requestedByName,
            requestedByNtId,
            requestedDate);
        this.names = names;
        this.newValues = newValues;
        this.oldValues = oldValues;
    }


    /**
     * Gets the names value for this DualControlJobVo.
     * 
     * @return names
     */
    public java.lang.String[] getNames() {
        return names;
    }


    /**
     * Sets the names value for this DualControlJobVo.
     * 
     * @param names
     */
    public void setNames(java.lang.String[] names) {
        this.names = names;
    }


    /**
     * Gets the newValues value for this DualControlJobVo.
     * 
     * @return newValues
     */
    public java.lang.String[] getNewValues() {
        return newValues;
    }


    /**
     * Sets the newValues value for this DualControlJobVo.
     * 
     * @param newValues
     */
    public void setNewValues(java.lang.String[] newValues) {
        this.newValues = newValues;
    }


    /**
     * Gets the oldValues value for this DualControlJobVo.
     * 
     * @return oldValues
     */
    public java.lang.String[] getOldValues() {
        return oldValues;
    }


    /**
     * Sets the oldValues value for this DualControlJobVo.
     * 
     * @param oldValues
     */
    public void setOldValues(java.lang.String[] oldValues) {
        this.oldValues = oldValues;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DualControlJobVo)) return false;
        DualControlJobVo other = (DualControlJobVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.names==null && other.getNames()==null) || 
             (this.names!=null &&
              java.util.Arrays.equals(this.names, other.getNames()))) &&
            ((this.newValues==null && other.getNewValues()==null) || 
             (this.newValues!=null &&
              java.util.Arrays.equals(this.newValues, other.getNewValues()))) &&
            ((this.oldValues==null && other.getOldValues()==null) || 
             (this.oldValues!=null &&
              java.util.Arrays.equals(this.oldValues, other.getOldValues())));
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
        if (getNames() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNames());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNames(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNewValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNewValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNewValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOldValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOldValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOldValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DualControlJobVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "DualControlJobVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("names");
        elemField.setXmlName(new javax.xml.namespace.QName("", "names"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newValues");
        elemField.setXmlName(new javax.xml.namespace.QName("", "newValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oldValues");
        elemField.setXmlName(new javax.xml.namespace.QName("", "oldValues"));
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
