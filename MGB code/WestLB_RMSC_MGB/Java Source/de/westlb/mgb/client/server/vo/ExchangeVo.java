/**
 * ExchangeVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class ExchangeVo  extends de.westlb.mgb.client.server.vo.ExchangeSearchResultEntryVo  implements java.io.Serializable {
    private de.westlb.mgb.client.server.vo.ExchangeMappingVo[] mappings;

    public ExchangeVo() {
    }

    public ExchangeVo(
           java.lang.String bloombergId,
           boolean changePending,
           java.lang.String changeRequestedByName,
           java.lang.Long id,
           java.lang.String reutersId,
           de.westlb.mgb.client.server.vo.ExchangeMappingVo[] mappings) {
        super(
            bloombergId,
            changePending,
            changeRequestedByName,
            id,
            reutersId);
        this.mappings = mappings;
    }


    /**
     * Gets the mappings value for this ExchangeVo.
     * 
     * @return mappings
     */
    public de.westlb.mgb.client.server.vo.ExchangeMappingVo[] getMappings() {
        return mappings;
    }


    /**
     * Sets the mappings value for this ExchangeVo.
     * 
     * @param mappings
     */
    public void setMappings(de.westlb.mgb.client.server.vo.ExchangeMappingVo[] mappings) {
        this.mappings = mappings;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExchangeVo)) return false;
        ExchangeVo other = (ExchangeVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.mappings==null && other.getMappings()==null) || 
             (this.mappings!=null &&
              java.util.Arrays.equals(this.mappings, other.getMappings())));
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
        if (getMappings() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMappings());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMappings(), i);
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
        new org.apache.axis.description.TypeDesc(ExchangeVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "ExchangeVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mappings");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mappings"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "ExchangeMappingVo"));
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
