/**
 * DataSelectionVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class DataSelectionVo  extends de.westlb.mgb.client.server.vo.CheckStateVo  implements java.io.Serializable {
    private de.westlb.mgb.client.server.vo.JobVo[] availableJobs;

    private java.lang.String mandantCode;

    private de.westlb.mgb.client.server.vo.JobVo[] selectedJobs;

    public DataSelectionVo() {
    }

    public DataSelectionVo(
           de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] automaticStates,
           boolean manualCheckFinished,
           de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] manualStates,
           long noTradesLateEntry,
           long noTradesManualCheckRequired,
           long noTradesOkAfterAutoCheck,
           long noTradesReclClosed,
           long noTradesReclOpen,
           long noTradesReclRequiredButNotHandled,
           long noTradesToCheck,
           boolean reclamationFinished,
           boolean sparkassenCheckFinished,
           de.westlb.mgb.client.server.vo.JobVo[] availableJobs,
           java.lang.String mandantCode,
           de.westlb.mgb.client.server.vo.JobVo[] selectedJobs) {
        super(
            automaticStates,
            manualCheckFinished,
            manualStates,
            noTradesLateEntry,
            noTradesManualCheckRequired,
            noTradesOkAfterAutoCheck,
            noTradesReclClosed,
            noTradesReclOpen,
            noTradesReclRequiredButNotHandled,
            noTradesToCheck,
            reclamationFinished,
            sparkassenCheckFinished);
        this.availableJobs = availableJobs;
        this.mandantCode = mandantCode;
        this.selectedJobs = selectedJobs;
    }


    /**
     * Gets the availableJobs value for this DataSelectionVo.
     * 
     * @return availableJobs
     */
    public de.westlb.mgb.client.server.vo.JobVo[] getAvailableJobs() {
        return availableJobs;
    }


    /**
     * Sets the availableJobs value for this DataSelectionVo.
     * 
     * @param availableJobs
     */
    public void setAvailableJobs(de.westlb.mgb.client.server.vo.JobVo[] availableJobs) {
        this.availableJobs = availableJobs;
    }


    /**
     * Gets the mandantCode value for this DataSelectionVo.
     * 
     * @return mandantCode
     */
    public java.lang.String getMandantCode() {
        return mandantCode;
    }


    /**
     * Sets the mandantCode value for this DataSelectionVo.
     * 
     * @param mandantCode
     */
    public void setMandantCode(java.lang.String mandantCode) {
        this.mandantCode = mandantCode;
    }


    /**
     * Gets the selectedJobs value for this DataSelectionVo.
     * 
     * @return selectedJobs
     */
    public de.westlb.mgb.client.server.vo.JobVo[] getSelectedJobs() {
        return selectedJobs;
    }


    /**
     * Sets the selectedJobs value for this DataSelectionVo.
     * 
     * @param selectedJobs
     */
    public void setSelectedJobs(de.westlb.mgb.client.server.vo.JobVo[] selectedJobs) {
        this.selectedJobs = selectedJobs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DataSelectionVo)) return false;
        DataSelectionVo other = (DataSelectionVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.availableJobs==null && other.getAvailableJobs()==null) || 
             (this.availableJobs!=null &&
              java.util.Arrays.equals(this.availableJobs, other.getAvailableJobs()))) &&
            ((this.mandantCode==null && other.getMandantCode()==null) || 
             (this.mandantCode!=null &&
              this.mandantCode.equals(other.getMandantCode()))) &&
            ((this.selectedJobs==null && other.getSelectedJobs()==null) || 
             (this.selectedJobs!=null &&
              java.util.Arrays.equals(this.selectedJobs, other.getSelectedJobs())));
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
        if (getAvailableJobs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAvailableJobs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAvailableJobs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMandantCode() != null) {
            _hashCode += getMandantCode().hashCode();
        }
        if (getSelectedJobs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSelectedJobs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSelectedJobs(), i);
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
        new org.apache.axis.description.TypeDesc(DataSelectionVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "DataSelectionVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("availableJobs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "availableJobs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "JobVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandantCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mandantCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selectedJobs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "selectedJobs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "JobVo"));
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
