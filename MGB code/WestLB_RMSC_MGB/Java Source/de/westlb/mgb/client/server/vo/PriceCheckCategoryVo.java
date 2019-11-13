/**
 * PriceCheckCategoryVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class PriceCheckCategoryVo  implements java.io.Serializable {
    private boolean changePending;

    private java.lang.String changeRequestedByName;

    private java.lang.Long id;

    private java.lang.String manualSampleCode;

    private java.lang.Integer manualSamplePercentage;

    private java.lang.String name;

    private double priceToleranceAbsolute;

    private double priceTolerancePercent;

    private int timeToleranceMinutes;

    public PriceCheckCategoryVo() {
    }

    public PriceCheckCategoryVo(
           boolean changePending,
           java.lang.String changeRequestedByName,
           java.lang.Long id,
           java.lang.String manualSampleCode,
           java.lang.Integer manualSamplePercentage,
           java.lang.String name,
           double priceToleranceAbsolute,
           double priceTolerancePercent,
           int timeToleranceMinutes) {
           this.changePending = changePending;
           this.changeRequestedByName = changeRequestedByName;
           this.id = id;
           this.manualSampleCode = manualSampleCode;
           this.manualSamplePercentage = manualSamplePercentage;
           this.name = name;
           this.priceToleranceAbsolute = priceToleranceAbsolute;
           this.priceTolerancePercent = priceTolerancePercent;
           this.timeToleranceMinutes = timeToleranceMinutes;
    }


    /**
     * Gets the changePending value for this PriceCheckCategoryVo.
     * 
     * @return changePending
     */
    public boolean isChangePending() {
        return changePending;
    }


    /**
     * Sets the changePending value for this PriceCheckCategoryVo.
     * 
     * @param changePending
     */
    public void setChangePending(boolean changePending) {
        this.changePending = changePending;
    }


    /**
     * Gets the changeRequestedByName value for this PriceCheckCategoryVo.
     * 
     * @return changeRequestedByName
     */
    public java.lang.String getChangeRequestedByName() {
        return changeRequestedByName;
    }


    /**
     * Sets the changeRequestedByName value for this PriceCheckCategoryVo.
     * 
     * @param changeRequestedByName
     */
    public void setChangeRequestedByName(java.lang.String changeRequestedByName) {
        this.changeRequestedByName = changeRequestedByName;
    }


    /**
     * Gets the id value for this PriceCheckCategoryVo.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this PriceCheckCategoryVo.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the manualSampleCode value for this PriceCheckCategoryVo.
     * 
     * @return manualSampleCode
     */
    public java.lang.String getManualSampleCode() {
        return manualSampleCode;
    }


    /**
     * Sets the manualSampleCode value for this PriceCheckCategoryVo.
     * 
     * @param manualSampleCode
     */
    public void setManualSampleCode(java.lang.String manualSampleCode) {
        this.manualSampleCode = manualSampleCode;
    }


    /**
     * Gets the manualSamplePercentage value for this PriceCheckCategoryVo.
     * 
     * @return manualSamplePercentage
     */
    public java.lang.Integer getManualSamplePercentage() {
        return manualSamplePercentage;
    }


    /**
     * Sets the manualSamplePercentage value for this PriceCheckCategoryVo.
     * 
     * @param manualSamplePercentage
     */
    public void setManualSamplePercentage(java.lang.Integer manualSamplePercentage) {
        this.manualSamplePercentage = manualSamplePercentage;
    }


    /**
     * Gets the name value for this PriceCheckCategoryVo.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this PriceCheckCategoryVo.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the priceToleranceAbsolute value for this PriceCheckCategoryVo.
     * 
     * @return priceToleranceAbsolute
     */
    public double getPriceToleranceAbsolute() {
        return priceToleranceAbsolute;
    }


    /**
     * Sets the priceToleranceAbsolute value for this PriceCheckCategoryVo.
     * 
     * @param priceToleranceAbsolute
     */
    public void setPriceToleranceAbsolute(double priceToleranceAbsolute) {
        this.priceToleranceAbsolute = priceToleranceAbsolute;
    }


    /**
     * Gets the priceTolerancePercent value for this PriceCheckCategoryVo.
     * 
     * @return priceTolerancePercent
     */
    public double getPriceTolerancePercent() {
        return priceTolerancePercent;
    }


    /**
     * Sets the priceTolerancePercent value for this PriceCheckCategoryVo.
     * 
     * @param priceTolerancePercent
     */
    public void setPriceTolerancePercent(double priceTolerancePercent) {
        this.priceTolerancePercent = priceTolerancePercent;
    }


    /**
     * Gets the timeToleranceMinutes value for this PriceCheckCategoryVo.
     * 
     * @return timeToleranceMinutes
     */
    public int getTimeToleranceMinutes() {
        return timeToleranceMinutes;
    }


    /**
     * Sets the timeToleranceMinutes value for this PriceCheckCategoryVo.
     * 
     * @param timeToleranceMinutes
     */
    public void setTimeToleranceMinutes(int timeToleranceMinutes) {
        this.timeToleranceMinutes = timeToleranceMinutes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PriceCheckCategoryVo)) return false;
        PriceCheckCategoryVo other = (PriceCheckCategoryVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.changePending == other.isChangePending() &&
            ((this.changeRequestedByName==null && other.getChangeRequestedByName()==null) || 
             (this.changeRequestedByName!=null &&
              this.changeRequestedByName.equals(other.getChangeRequestedByName()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.manualSampleCode==null && other.getManualSampleCode()==null) || 
             (this.manualSampleCode!=null &&
              this.manualSampleCode.equals(other.getManualSampleCode()))) &&
            ((this.manualSamplePercentage==null && other.getManualSamplePercentage()==null) || 
             (this.manualSamplePercentage!=null &&
              this.manualSamplePercentage.equals(other.getManualSamplePercentage()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            this.priceToleranceAbsolute == other.getPriceToleranceAbsolute() &&
            this.priceTolerancePercent == other.getPriceTolerancePercent() &&
            this.timeToleranceMinutes == other.getTimeToleranceMinutes();
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
        _hashCode += (isChangePending() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getChangeRequestedByName() != null) {
            _hashCode += getChangeRequestedByName().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getManualSampleCode() != null) {
            _hashCode += getManualSampleCode().hashCode();
        }
        if (getManualSamplePercentage() != null) {
            _hashCode += getManualSamplePercentage().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        _hashCode += new Double(getPriceToleranceAbsolute()).hashCode();
        _hashCode += new Double(getPriceTolerancePercent()).hashCode();
        _hashCode += getTimeToleranceMinutes();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PriceCheckCategoryVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "PriceCheckCategoryVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changePending");
        elemField.setXmlName(new javax.xml.namespace.QName("", "changePending"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changeRequestedByName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "changeRequestedByName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualSampleCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualSampleCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualSamplePercentage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualSamplePercentage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceToleranceAbsolute");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceToleranceAbsolute"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceTolerancePercent");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceTolerancePercent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeToleranceMinutes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timeToleranceMinutes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
