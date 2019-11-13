/**
 * StatisticCubeReportVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class StatisticCubeReportVo  extends de.westlb.mgb.client.server.vo.StatisticReportVoBase  implements java.io.Serializable {
    private de.westlb.mgb.client.server.vo.StatisticCubeRowVo[] rows;

    public StatisticCubeReportVo() {
    }

    public StatisticCubeReportVo(
           de.westlb.mgb.client.server.vo.StatisticContextVo[] context,
           java.lang.String[] addKeyColumnNames,
           java.util.Calendar fromDate,
           java.lang.String[] keyColumnNames,
           java.lang.String name,
           java.lang.String[] successorNames,
           java.util.Calendar toDate,
           de.westlb.mgb.client.server.vo.StatisticCubeRowVo[] rows) {
        super(
            context,
            addKeyColumnNames,
            fromDate,
            keyColumnNames,
            name,
            successorNames,
            toDate);
        this.rows = rows;
    }


    /**
     * Gets the rows value for this StatisticCubeReportVo.
     * 
     * @return rows
     */
    public de.westlb.mgb.client.server.vo.StatisticCubeRowVo[] getRows() {
        return rows;
    }


    /**
     * Sets the rows value for this StatisticCubeReportVo.
     * 
     * @param rows
     */
    public void setRows(de.westlb.mgb.client.server.vo.StatisticCubeRowVo[] rows) {
        this.rows = rows;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatisticCubeReportVo)) return false;
        StatisticCubeReportVo other = (StatisticCubeReportVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.rows==null && other.getRows()==null) || 
             (this.rows!=null &&
              java.util.Arrays.equals(this.rows, other.getRows())));
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
        if (getRows() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRows());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRows(), i);
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
        new org.apache.axis.description.TypeDesc(StatisticCubeReportVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StatisticCubeReportVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rows");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rows"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StatisticCubeRowVo"));
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
