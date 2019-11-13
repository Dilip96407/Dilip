/**
 * StatisticRowVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class StatisticRowVo  implements java.io.Serializable {
    private java.lang.Object[] addKeyValues;

    private de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] automaticMccCheckStates;

    private de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] automaticStates;

    private java.lang.Object[] keyValues;

    private boolean manualCheckFinished;

    private de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] manualStates;

    private long noTradesAutoMccChecked;

    private long noTradesAutomaticStateNotSet;

    private long noTradesLateEntry;

    private long noTradesManualCheckRequired;

    private long noTradesManualStateRequiredButNotHandledYet;

    private long noTradesManualStateSet;

    private long noTradesOkAfterAutoCheck;

    private long noTradesReclClosed;

    private long noTradesReclOpen;

    private long noTradesReclRequired;

    private long noTradesReclWaiting;

    private long noTradesTotal;

    private de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] reclamationStates;

    public StatisticRowVo() {
    }

    public StatisticRowVo(
           java.lang.Object[] addKeyValues,
           de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] automaticMccCheckStates,
           de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] automaticStates,
           java.lang.Object[] keyValues,
           boolean manualCheckFinished,
           de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] manualStates,
           long noTradesAutoMccChecked,
           long noTradesAutomaticStateNotSet,
           long noTradesLateEntry,
           long noTradesManualCheckRequired,
           long noTradesManualStateRequiredButNotHandledYet,
           long noTradesManualStateSet,
           long noTradesOkAfterAutoCheck,
           long noTradesReclClosed,
           long noTradesReclOpen,
           long noTradesReclRequired,
           long noTradesReclWaiting,
           long noTradesTotal,
           de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] reclamationStates) {
           this.addKeyValues = addKeyValues;
           this.automaticMccCheckStates = automaticMccCheckStates;
           this.automaticStates = automaticStates;
           this.keyValues = keyValues;
           this.manualCheckFinished = manualCheckFinished;
           this.manualStates = manualStates;
           this.noTradesAutoMccChecked = noTradesAutoMccChecked;
           this.noTradesAutomaticStateNotSet = noTradesAutomaticStateNotSet;
           this.noTradesLateEntry = noTradesLateEntry;
           this.noTradesManualCheckRequired = noTradesManualCheckRequired;
           this.noTradesManualStateRequiredButNotHandledYet = noTradesManualStateRequiredButNotHandledYet;
           this.noTradesManualStateSet = noTradesManualStateSet;
           this.noTradesOkAfterAutoCheck = noTradesOkAfterAutoCheck;
           this.noTradesReclClosed = noTradesReclClosed;
           this.noTradesReclOpen = noTradesReclOpen;
           this.noTradesReclRequired = noTradesReclRequired;
           this.noTradesReclWaiting = noTradesReclWaiting;
           this.noTradesTotal = noTradesTotal;
           this.reclamationStates = reclamationStates;
    }


    /**
     * Gets the addKeyValues value for this StatisticRowVo.
     * 
     * @return addKeyValues
     */
    public java.lang.Object[] getAddKeyValues() {
        return addKeyValues;
    }


    /**
     * Sets the addKeyValues value for this StatisticRowVo.
     * 
     * @param addKeyValues
     */
    public void setAddKeyValues(java.lang.Object[] addKeyValues) {
        this.addKeyValues = addKeyValues;
    }


    /**
     * Gets the automaticMccCheckStates value for this StatisticRowVo.
     * 
     * @return automaticMccCheckStates
     */
    public de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] getAutomaticMccCheckStates() {
        return automaticMccCheckStates;
    }


    /**
     * Sets the automaticMccCheckStates value for this StatisticRowVo.
     * 
     * @param automaticMccCheckStates
     */
    public void setAutomaticMccCheckStates(de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] automaticMccCheckStates) {
        this.automaticMccCheckStates = automaticMccCheckStates;
    }


    /**
     * Gets the automaticStates value for this StatisticRowVo.
     * 
     * @return automaticStates
     */
    public de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] getAutomaticStates() {
        return automaticStates;
    }


    /**
     * Sets the automaticStates value for this StatisticRowVo.
     * 
     * @param automaticStates
     */
    public void setAutomaticStates(de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] automaticStates) {
        this.automaticStates = automaticStates;
    }


    /**
     * Gets the keyValues value for this StatisticRowVo.
     * 
     * @return keyValues
     */
    public java.lang.Object[] getKeyValues() {
        return keyValues;
    }


    /**
     * Sets the keyValues value for this StatisticRowVo.
     * 
     * @param keyValues
     */
    public void setKeyValues(java.lang.Object[] keyValues) {
        this.keyValues = keyValues;
    }


    /**
     * Gets the manualCheckFinished value for this StatisticRowVo.
     * 
     * @return manualCheckFinished
     */
    public boolean isManualCheckFinished() {
        return manualCheckFinished;
    }


    /**
     * Sets the manualCheckFinished value for this StatisticRowVo.
     * 
     * @param manualCheckFinished
     */
    public void setManualCheckFinished(boolean manualCheckFinished) {
        this.manualCheckFinished = manualCheckFinished;
    }


    /**
     * Gets the manualStates value for this StatisticRowVo.
     * 
     * @return manualStates
     */
    public de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] getManualStates() {
        return manualStates;
    }


    /**
     * Sets the manualStates value for this StatisticRowVo.
     * 
     * @param manualStates
     */
    public void setManualStates(de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] manualStates) {
        this.manualStates = manualStates;
    }


    /**
     * Gets the noTradesAutoMccChecked value for this StatisticRowVo.
     * 
     * @return noTradesAutoMccChecked
     */
    public long getNoTradesAutoMccChecked() {
        return noTradesAutoMccChecked;
    }


    /**
     * Sets the noTradesAutoMccChecked value for this StatisticRowVo.
     * 
     * @param noTradesAutoMccChecked
     */
    public void setNoTradesAutoMccChecked(long noTradesAutoMccChecked) {
        this.noTradesAutoMccChecked = noTradesAutoMccChecked;
    }


    /**
     * Gets the noTradesAutomaticStateNotSet value for this StatisticRowVo.
     * 
     * @return noTradesAutomaticStateNotSet
     */
    public long getNoTradesAutomaticStateNotSet() {
        return noTradesAutomaticStateNotSet;
    }


    /**
     * Sets the noTradesAutomaticStateNotSet value for this StatisticRowVo.
     * 
     * @param noTradesAutomaticStateNotSet
     */
    public void setNoTradesAutomaticStateNotSet(long noTradesAutomaticStateNotSet) {
        this.noTradesAutomaticStateNotSet = noTradesAutomaticStateNotSet;
    }


    /**
     * Gets the noTradesLateEntry value for this StatisticRowVo.
     * 
     * @return noTradesLateEntry
     */
    public long getNoTradesLateEntry() {
        return noTradesLateEntry;
    }


    /**
     * Sets the noTradesLateEntry value for this StatisticRowVo.
     * 
     * @param noTradesLateEntry
     */
    public void setNoTradesLateEntry(long noTradesLateEntry) {
        this.noTradesLateEntry = noTradesLateEntry;
    }


    /**
     * Gets the noTradesManualCheckRequired value for this StatisticRowVo.
     * 
     * @return noTradesManualCheckRequired
     */
    public long getNoTradesManualCheckRequired() {
        return noTradesManualCheckRequired;
    }


    /**
     * Sets the noTradesManualCheckRequired value for this StatisticRowVo.
     * 
     * @param noTradesManualCheckRequired
     */
    public void setNoTradesManualCheckRequired(long noTradesManualCheckRequired) {
        this.noTradesManualCheckRequired = noTradesManualCheckRequired;
    }


    /**
     * Gets the noTradesManualStateRequiredButNotHandledYet value for this StatisticRowVo.
     * 
     * @return noTradesManualStateRequiredButNotHandledYet
     */
    public long getNoTradesManualStateRequiredButNotHandledYet() {
        return noTradesManualStateRequiredButNotHandledYet;
    }


    /**
     * Sets the noTradesManualStateRequiredButNotHandledYet value for this StatisticRowVo.
     * 
     * @param noTradesManualStateRequiredButNotHandledYet
     */
    public void setNoTradesManualStateRequiredButNotHandledYet(long noTradesManualStateRequiredButNotHandledYet) {
        this.noTradesManualStateRequiredButNotHandledYet = noTradesManualStateRequiredButNotHandledYet;
    }


    /**
     * Gets the noTradesManualStateSet value for this StatisticRowVo.
     * 
     * @return noTradesManualStateSet
     */
    public long getNoTradesManualStateSet() {
        return noTradesManualStateSet;
    }


    /**
     * Sets the noTradesManualStateSet value for this StatisticRowVo.
     * 
     * @param noTradesManualStateSet
     */
    public void setNoTradesManualStateSet(long noTradesManualStateSet) {
        this.noTradesManualStateSet = noTradesManualStateSet;
    }


    /**
     * Gets the noTradesOkAfterAutoCheck value for this StatisticRowVo.
     * 
     * @return noTradesOkAfterAutoCheck
     */
    public long getNoTradesOkAfterAutoCheck() {
        return noTradesOkAfterAutoCheck;
    }


    /**
     * Sets the noTradesOkAfterAutoCheck value for this StatisticRowVo.
     * 
     * @param noTradesOkAfterAutoCheck
     */
    public void setNoTradesOkAfterAutoCheck(long noTradesOkAfterAutoCheck) {
        this.noTradesOkAfterAutoCheck = noTradesOkAfterAutoCheck;
    }


    /**
     * Gets the noTradesReclClosed value for this StatisticRowVo.
     * 
     * @return noTradesReclClosed
     */
    public long getNoTradesReclClosed() {
        return noTradesReclClosed;
    }


    /**
     * Sets the noTradesReclClosed value for this StatisticRowVo.
     * 
     * @param noTradesReclClosed
     */
    public void setNoTradesReclClosed(long noTradesReclClosed) {
        this.noTradesReclClosed = noTradesReclClosed;
    }


    /**
     * Gets the noTradesReclOpen value for this StatisticRowVo.
     * 
     * @return noTradesReclOpen
     */
    public long getNoTradesReclOpen() {
        return noTradesReclOpen;
    }


    /**
     * Sets the noTradesReclOpen value for this StatisticRowVo.
     * 
     * @param noTradesReclOpen
     */
    public void setNoTradesReclOpen(long noTradesReclOpen) {
        this.noTradesReclOpen = noTradesReclOpen;
    }


    /**
     * Gets the noTradesReclRequired value for this StatisticRowVo.
     * 
     * @return noTradesReclRequired
     */
    public long getNoTradesReclRequired() {
        return noTradesReclRequired;
    }


    /**
     * Sets the noTradesReclRequired value for this StatisticRowVo.
     * 
     * @param noTradesReclRequired
     */
    public void setNoTradesReclRequired(long noTradesReclRequired) {
        this.noTradesReclRequired = noTradesReclRequired;
    }


    /**
     * Gets the noTradesReclWaiting value for this StatisticRowVo.
     * 
     * @return noTradesReclWaiting
     */
    public long getNoTradesReclWaiting() {
        return noTradesReclWaiting;
    }


    /**
     * Sets the noTradesReclWaiting value for this StatisticRowVo.
     * 
     * @param noTradesReclWaiting
     */
    public void setNoTradesReclWaiting(long noTradesReclWaiting) {
        this.noTradesReclWaiting = noTradesReclWaiting;
    }


    /**
     * Gets the noTradesTotal value for this StatisticRowVo.
     * 
     * @return noTradesTotal
     */
    public long getNoTradesTotal() {
        return noTradesTotal;
    }


    /**
     * Sets the noTradesTotal value for this StatisticRowVo.
     * 
     * @param noTradesTotal
     */
    public void setNoTradesTotal(long noTradesTotal) {
        this.noTradesTotal = noTradesTotal;
    }


    /**
     * Gets the reclamationStates value for this StatisticRowVo.
     * 
     * @return reclamationStates
     */
    public de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] getReclamationStates() {
        return reclamationStates;
    }


    /**
     * Sets the reclamationStates value for this StatisticRowVo.
     * 
     * @param reclamationStates
     */
    public void setReclamationStates(de.westlb.mgb.client.server.vo.StateStatisticEntryVo[] reclamationStates) {
        this.reclamationStates = reclamationStates;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatisticRowVo)) return false;
        StatisticRowVo other = (StatisticRowVo) obj;
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
            ((this.automaticMccCheckStates==null && other.getAutomaticMccCheckStates()==null) || 
             (this.automaticMccCheckStates!=null &&
              java.util.Arrays.equals(this.automaticMccCheckStates, other.getAutomaticMccCheckStates()))) &&
            ((this.automaticStates==null && other.getAutomaticStates()==null) || 
             (this.automaticStates!=null &&
              java.util.Arrays.equals(this.automaticStates, other.getAutomaticStates()))) &&
            ((this.keyValues==null && other.getKeyValues()==null) || 
             (this.keyValues!=null &&
              java.util.Arrays.equals(this.keyValues, other.getKeyValues()))) &&
            this.manualCheckFinished == other.isManualCheckFinished() &&
            ((this.manualStates==null && other.getManualStates()==null) || 
             (this.manualStates!=null &&
              java.util.Arrays.equals(this.manualStates, other.getManualStates()))) &&
            this.noTradesAutoMccChecked == other.getNoTradesAutoMccChecked() &&
            this.noTradesAutomaticStateNotSet == other.getNoTradesAutomaticStateNotSet() &&
            this.noTradesLateEntry == other.getNoTradesLateEntry() &&
            this.noTradesManualCheckRequired == other.getNoTradesManualCheckRequired() &&
            this.noTradesManualStateRequiredButNotHandledYet == other.getNoTradesManualStateRequiredButNotHandledYet() &&
            this.noTradesManualStateSet == other.getNoTradesManualStateSet() &&
            this.noTradesOkAfterAutoCheck == other.getNoTradesOkAfterAutoCheck() &&
            this.noTradesReclClosed == other.getNoTradesReclClosed() &&
            this.noTradesReclOpen == other.getNoTradesReclOpen() &&
            this.noTradesReclRequired == other.getNoTradesReclRequired() &&
            this.noTradesReclWaiting == other.getNoTradesReclWaiting() &&
            this.noTradesTotal == other.getNoTradesTotal() &&
            ((this.reclamationStates==null && other.getReclamationStates()==null) || 
             (this.reclamationStates!=null &&
              java.util.Arrays.equals(this.reclamationStates, other.getReclamationStates())));
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
        _hashCode += new Long(getNoTradesAutoMccChecked()).hashCode();
        _hashCode += new Long(getNoTradesAutomaticStateNotSet()).hashCode();
        _hashCode += new Long(getNoTradesLateEntry()).hashCode();
        _hashCode += new Long(getNoTradesManualCheckRequired()).hashCode();
        _hashCode += new Long(getNoTradesManualStateRequiredButNotHandledYet()).hashCode();
        _hashCode += new Long(getNoTradesManualStateSet()).hashCode();
        _hashCode += new Long(getNoTradesOkAfterAutoCheck()).hashCode();
        _hashCode += new Long(getNoTradesReclClosed()).hashCode();
        _hashCode += new Long(getNoTradesReclOpen()).hashCode();
        _hashCode += new Long(getNoTradesReclRequired()).hashCode();
        _hashCode += new Long(getNoTradesReclWaiting()).hashCode();
        _hashCode += new Long(getNoTradesTotal()).hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatisticRowVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StatisticRowVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addKeyValues");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addKeyValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("keyValues");
        elemField.setXmlName(new javax.xml.namespace.QName("", "keyValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
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
        elemField.setFieldName("noTradesAutoMccChecked");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noTradesAutoMccChecked"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noTradesAutomaticStateNotSet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noTradesAutomaticStateNotSet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
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
        elemField.setFieldName("noTradesManualStateRequiredButNotHandledYet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noTradesManualStateRequiredButNotHandledYet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noTradesManualStateSet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noTradesManualStateSet"));
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
        elemField.setFieldName("noTradesReclRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noTradesReclRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noTradesReclWaiting");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noTradesReclWaiting"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noTradesTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "noTradesTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reclamationStates");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reclamationStates"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "StateStatisticEntryVo"));
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
