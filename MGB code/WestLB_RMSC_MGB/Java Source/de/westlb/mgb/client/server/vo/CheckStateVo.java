/**
 * CheckStateVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class CheckStateVo  implements java.io.Serializable {
    private de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] automaticStates;

    private boolean manualCheckFinished;

    private de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] manualStates;

    private long noTradesLateEntry;

    private long noTradesManualCheckRequired;

    private long noTradesOkAfterAutoCheck;

    private long noTradesReclClosed;

    private long noTradesReclOpen;

    private long noTradesReclRequiredButNotHandled;

    private long noTradesToCheck;

    private boolean reclamationFinished;

    private boolean sparkassenCheckFinished;

    public CheckStateVo() {
    }

    public CheckStateVo(
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
           boolean sparkassenCheckFinished) {
           this.automaticStates = automaticStates;
           this.manualCheckFinished = manualCheckFinished;
           this.manualStates = manualStates;
           this.noTradesLateEntry = noTradesLateEntry;
           this.noTradesManualCheckRequired = noTradesManualCheckRequired;
           this.noTradesOkAfterAutoCheck = noTradesOkAfterAutoCheck;
           this.noTradesReclClosed = noTradesReclClosed;
           this.noTradesReclOpen = noTradesReclOpen;
           this.noTradesReclRequiredButNotHandled = noTradesReclRequiredButNotHandled;
           this.noTradesToCheck = noTradesToCheck;
           this.reclamationFinished = reclamationFinished;
           this.sparkassenCheckFinished = sparkassenCheckFinished;
    }


    /**
     * Gets the automaticStates value for this CheckStateVo.
     * 
     * @return automaticStates
     */
    public de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] getAutomaticStates() {
        return automaticStates;
    }


    /**
     * Sets the automaticStates value for this CheckStateVo.
     * 
     * @param automaticStates
     */
    public void setAutomaticStates(de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] automaticStates) {
        this.automaticStates = automaticStates;
    }


    /**
     * Gets the manualCheckFinished value for this CheckStateVo.
     * 
     * @return manualCheckFinished
     */
    public boolean isManualCheckFinished() {
        return manualCheckFinished;
    }


    /**
     * Sets the manualCheckFinished value for this CheckStateVo.
     * 
     * @param manualCheckFinished
     */
    public void setManualCheckFinished(boolean manualCheckFinished) {
        this.manualCheckFinished = manualCheckFinished;
    }


    /**
     * Gets the manualStates value for this CheckStateVo.
     * 
     * @return manualStates
     */
    public de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] getManualStates() {
        return manualStates;
    }


    /**
     * Sets the manualStates value for this CheckStateVo.
     * 
     * @param manualStates
     */
    public void setManualStates(de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] manualStates) {
        this.manualStates = manualStates;
    }


    /**
     * Gets the noTradesLateEntry value for this CheckStateVo.
     * 
     * @return noTradesLateEntry
     */
    public long getNoTradesLateEntry() {
        return noTradesLateEntry;
    }


    /**
     * Sets the noTradesLateEntry value for this CheckStateVo.
     * 
     * @param noTradesLateEntry
     */
    public void setNoTradesLateEntry(long noTradesLateEntry) {
        this.noTradesLateEntry = noTradesLateEntry;
    }


    /**
     * Gets the noTradesManualCheckRequired value for this CheckStateVo.
     * 
     * @return noTradesManualCheckRequired
     */
    public long getNoTradesManualCheckRequired() {
        return noTradesManualCheckRequired;
    }


    /**
     * Sets the noTradesManualCheckRequired value for this CheckStateVo.
     * 
     * @param noTradesManualCheckRequired
     */
    public void setNoTradesManualCheckRequired(long noTradesManualCheckRequired) {
        this.noTradesManualCheckRequired = noTradesManualCheckRequired;
    }


    /**
     * Gets the noTradesOkAfterAutoCheck value for this CheckStateVo.
     * 
     * @return noTradesOkAfterAutoCheck
     */
    public long getNoTradesOkAfterAutoCheck() {
        return noTradesOkAfterAutoCheck;
    }


    /**
     * Sets the noTradesOkAfterAutoCheck value for this CheckStateVo.
     * 
     * @param noTradesOkAfterAutoCheck
     */
    public void setNoTradesOkAfterAutoCheck(long noTradesOkAfterAutoCheck) {
        this.noTradesOkAfterAutoCheck = noTradesOkAfterAutoCheck;
    }


    /**
     * Gets the noTradesReclClosed value for this CheckStateVo.
     * 
     * @return noTradesReclClosed
     */
    public long getNoTradesReclClosed() {
        return noTradesReclClosed;
    }


    /**
     * Sets the noTradesReclClosed value for this CheckStateVo.
     * 
     * @param noTradesReclClosed
     */
    public void setNoTradesReclClosed(long noTradesReclClosed) {
        this.noTradesReclClosed = noTradesReclClosed;
    }


    /**
     * Gets the noTradesReclOpen value for this CheckStateVo.
     * 
     * @return noTradesReclOpen
     */
    public long getNoTradesReclOpen() {
        return noTradesReclOpen;
    }


    /**
     * Sets the noTradesReclOpen value for this CheckStateVo.
     * 
     * @param noTradesReclOpen
     */
    public void setNoTradesReclOpen(long noTradesReclOpen) {
        this.noTradesReclOpen = noTradesReclOpen;
    }


    /**
     * Gets the noTradesReclRequiredButNotHandled value for this CheckStateVo.
     * 
     * @return noTradesReclRequiredButNotHandled
     */
    public long getNoTradesReclRequiredButNotHandled() {
        return noTradesReclRequiredButNotHandled;
    }


    /**
     * Sets the noTradesReclRequiredButNotHandled value for this CheckStateVo.
     * 
     * @param noTradesReclRequiredButNotHandled
     */
    public void setNoTradesReclRequiredButNotHandled(long noTradesReclRequiredButNotHandled) {
        this.noTradesReclRequiredButNotHandled = noTradesReclRequiredButNotHandled;
    }


    /**
     * Gets the noTradesToCheck value for this CheckStateVo.
     * 
     * @return noTradesToCheck
     */
    public long getNoTradesToCheck() {
        return noTradesToCheck;
    }


    /**
     * Sets the noTradesToCheck value for this CheckStateVo.
     * 
     * @param noTradesToCheck
     */
    public void setNoTradesToCheck(long noTradesToCheck) {
        this.noTradesToCheck = noTradesToCheck;
    }


    /**
     * Gets the reclamationFinished value for this CheckStateVo.
     * 
     * @return reclamationFinished
     */
    public boolean isReclamationFinished() {
        return reclamationFinished;
    }


    /**
     * Sets the reclamationFinished value for this CheckStateVo.
     * 
     * @param reclamationFinished
     */
    public void setReclamationFinished(boolean reclamationFinished) {
        this.reclamationFinished = reclamationFinished;
    }


    /**
     * Gets the sparkassenCheckFinished value for this CheckStateVo.
     * 
     * @return sparkassenCheckFinished
     */
    public boolean isSparkassenCheckFinished() {
        return sparkassenCheckFinished;
    }


    /**
     * Sets the sparkassenCheckFinished value for this CheckStateVo.
     * 
     * @param sparkassenCheckFinished
     */
    public void setSparkassenCheckFinished(boolean sparkassenCheckFinished) {
        this.sparkassenCheckFinished = sparkassenCheckFinished;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckStateVo)) return false;
        CheckStateVo other = (CheckStateVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.automaticStates==null && other.getAutomaticStates()==null) || 
             (this.automaticStates!=null &&
              java.util.Arrays.equals(this.automaticStates, other.getAutomaticStates()))) &&
            this.manualCheckFinished == other.isManualCheckFinished() &&
            ((this.manualStates==null && other.getManualStates()==null) || 
             (this.manualStates!=null &&
              java.util.Arrays.equals(this.manualStates, other.getManualStates()))) &&
            this.noTradesLateEntry == other.getNoTradesLateEntry() &&
            this.noTradesManualCheckRequired == other.getNoTradesManualCheckRequired() &&
            this.noTradesOkAfterAutoCheck == other.getNoTradesOkAfterAutoCheck() &&
            this.noTradesReclClosed == other.getNoTradesReclClosed() &&
            this.noTradesReclOpen == other.getNoTradesReclOpen() &&
            this.noTradesReclRequiredButNotHandled == other.getNoTradesReclRequiredButNotHandled() &&
            this.noTradesToCheck == other.getNoTradesToCheck() &&
            this.reclamationFinished == other.isReclamationFinished() &&
            this.sparkassenCheckFinished == other.isSparkassenCheckFinished();
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
        _hashCode += (isManualCheckFinished() ? Boolean.TRUE : Boolean.FALSE).hashCode();
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
        _hashCode += new Long(getNoTradesLateEntry()).hashCode();
        _hashCode += new Long(getNoTradesManualCheckRequired()).hashCode();
        _hashCode += new Long(getNoTradesOkAfterAutoCheck()).hashCode();
        _hashCode += new Long(getNoTradesReclClosed()).hashCode();
        _hashCode += new Long(getNoTradesReclOpen()).hashCode();
        _hashCode += new Long(getNoTradesReclRequiredButNotHandled()).hashCode();
        _hashCode += new Long(getNoTradesToCheck()).hashCode();
        _hashCode += (isReclamationFinished() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isSparkassenCheckFinished() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckStateVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "CheckStateVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automaticStates");
        elemField.setXmlName(new javax.xml.namespace.QName("", "automaticStates"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StateStatisticEntryVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualCheckFinished");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualCheckFinished"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualStates");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualStates"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StateStatisticEntryVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noTradesLateEntry");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noTradesLateEntry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noTradesManualCheckRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noTradesManualCheckRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noTradesOkAfterAutoCheck");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noTradesOkAfterAutoCheck"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noTradesReclClosed");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noTradesReclClosed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noTradesReclOpen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noTradesReclOpen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noTradesReclRequiredButNotHandled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noTradesReclRequiredButNotHandled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noTradesToCheck");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noTradesToCheck"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationFinished");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationFinished"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sparkassenCheckFinished");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sparkassenCheckFinished"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
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
