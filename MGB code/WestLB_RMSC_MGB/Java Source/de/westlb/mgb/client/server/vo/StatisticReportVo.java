/**
 * StatisticReportVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class StatisticReportVo  extends de.westlb.mgb.client.server.vo.StatisticReportVoBase  implements java.io.Serializable {
    private de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] automaticMccCheckStates;

    private de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] automaticStates;

    private de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] manualStates;

    private de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] reclamationStates;

    private de.westlb.mgb.client.server.vo.StatisticRowVo[] rows;

    public StatisticReportVo() {
    }

    public StatisticReportVo(
           de.westlb.mgb.client.server.vo.StatisticContextVo[] context,
           java.lang.String[] addKeyColumnNames,
           java.util.Calendar fromDate,
           java.lang.String[] keyColumnNames,
           java.lang.String name,
           java.lang.String[] successorNames,
           java.util.Calendar toDate,
           de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] automaticMccCheckStates,
           de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] automaticStates,
           de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] manualStates,
           de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] reclamationStates,
           de.westlb.mgb.client.server.vo.StatisticRowVo[] rows) {
        super(
            context,
            addKeyColumnNames,
            fromDate,
            keyColumnNames,
            name,
            successorNames,
            toDate);
        this.automaticMccCheckStates = automaticMccCheckStates;
        this.automaticStates = automaticStates;
        this.manualStates = manualStates;
        this.reclamationStates = reclamationStates;
        this.rows = rows;
    }


    /**
     * Gets the automaticMccCheckStates value for this StatisticReportVo.
     * 
     * @return automaticMccCheckStates
     */
    public de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] getAutomaticMccCheckStates() {
        return automaticMccCheckStates;
    }


    /**
     * Sets the automaticMccCheckStates value for this StatisticReportVo.
     * 
     * @param automaticMccCheckStates
     */
    public void setAutomaticMccCheckStates(de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] automaticMccCheckStates) {
        this.automaticMccCheckStates = automaticMccCheckStates;
    }


    /**
     * Gets the automaticStates value for this StatisticReportVo.
     * 
     * @return automaticStates
     */
    public de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] getAutomaticStates() {
        return automaticStates;
    }


    /**
     * Sets the automaticStates value for this StatisticReportVo.
     * 
     * @param automaticStates
     */
    public void setAutomaticStates(de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] automaticStates) {
        this.automaticStates = automaticStates;
    }


    /**
     * Gets the manualStates value for this StatisticReportVo.
     * 
     * @return manualStates
     */
    public de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] getManualStates() {
        return manualStates;
    }


    /**
     * Sets the manualStates value for this StatisticReportVo.
     * 
     * @param manualStates
     */
    public void setManualStates(de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] manualStates) {
        this.manualStates = manualStates;
    }


    /**
     * Gets the reclamationStates value for this StatisticReportVo.
     * 
     * @return reclamationStates
     */
    public de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] getReclamationStates() {
        return reclamationStates;
    }


    /**
     * Sets the reclamationStates value for this StatisticReportVo.
     * 
     * @param reclamationStates
     */
    public void setReclamationStates(de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] reclamationStates) {
        this.reclamationStates = reclamationStates;
    }


    /**
     * Gets the rows value for this StatisticReportVo.
     * 
     * @return rows
     */
    public de.westlb.mgb.client.server.vo.StatisticRowVo[] getRows() {
        return rows;
    }


    /**
     * Sets the rows value for this StatisticReportVo.
     * 
     * @param rows
     */
    public void setRows(de.westlb.mgb.client.server.vo.StatisticRowVo[] rows) {
        this.rows = rows;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatisticReportVo)) return false;
        StatisticReportVo other = (StatisticReportVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.automaticMccCheckStates==null && other.getAutomaticMccCheckStates()==null) || 
             (this.automaticMccCheckStates!=null &&
              java.util.Arrays.equals(this.automaticMccCheckStates, other.getAutomaticMccCheckStates()))) &&
            ((this.automaticStates==null && other.getAutomaticStates()==null) || 
             (this.automaticStates!=null &&
              java.util.Arrays.equals(this.automaticStates, other.getAutomaticStates()))) &&
            ((this.manualStates==null && other.getManualStates()==null) || 
             (this.manualStates!=null &&
              java.util.Arrays.equals(this.manualStates, other.getManualStates()))) &&
            ((this.reclamationStates==null && other.getReclamationStates()==null) || 
             (this.reclamationStates!=null &&
              java.util.Arrays.equals(this.reclamationStates, other.getReclamationStates()))) &&
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
        if (getAutomaticMccCheckStates() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAutomaticMccCheckStates());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAutomaticMccCheckStates(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAutomaticStates() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAutomaticStates());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAutomaticStates(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getManualStates() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getManualStates());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getManualStates(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReclamationStates() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReclamationStates());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReclamationStates(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
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
        new org.apache.axis.description.TypeDesc(StatisticReportVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StatisticReportVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automaticMccCheckStates");
        elemField.setXmlName(new javax.xml.namespace.QName("", "automaticMccCheckStates"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StateStatisticEntryVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automaticStates");
        elemField.setXmlName(new javax.xml.namespace.QName("", "automaticStates"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StateStatisticEntryVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualStates");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualStates"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StateStatisticEntryVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationStates");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationStates"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StateStatisticEntryVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rows");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rows"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StatisticRowVo"));
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
