/**
 * TradeReclRequiredVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class TradeReclRequiredVo  implements java.io.Serializable {
    private java.lang.String currency;

    private java.lang.String email;

    private java.lang.Long employeeId;

    private java.lang.String employeeName;

    private java.lang.Long id;

    private java.lang.Boolean lateEntry;

    private java.lang.String manualStateCode;

    private java.lang.String phone;

    private double price;

    private java.util.Calendar settlementDate;

    private java.lang.String sourceSystemInstrument;

    private java.util.Calendar systemDate;

    private java.util.Calendar tradeDate;

    private java.lang.String tradeId;

    private java.lang.String traderId;

    private double volume;

    public TradeReclRequiredVo() {
    }

    public TradeReclRequiredVo(
           java.lang.String currency,
           java.lang.String email,
           java.lang.Long employeeId,
           java.lang.String employeeName,
           java.lang.Long id,
           java.lang.Boolean lateEntry,
           java.lang.String manualStateCode,
           java.lang.String phone,
           double price,
           java.util.Calendar settlementDate,
           java.lang.String sourceSystemInstrument,
           java.util.Calendar systemDate,
           java.util.Calendar tradeDate,
           java.lang.String tradeId,
           java.lang.String traderId,
           double volume) {
           this.currency = currency;
           this.email = email;
           this.employeeId = employeeId;
           this.employeeName = employeeName;
           this.id = id;
           this.lateEntry = lateEntry;
           this.manualStateCode = manualStateCode;
           this.phone = phone;
           this.price = price;
           this.settlementDate = settlementDate;
           this.sourceSystemInstrument = sourceSystemInstrument;
           this.systemDate = systemDate;
           this.tradeDate = tradeDate;
           this.tradeId = tradeId;
           this.traderId = traderId;
           this.volume = volume;
    }


    /**
     * Gets the currency value for this TradeReclRequiredVo.
     * 
     * @return currency
     */
    public java.lang.String getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this TradeReclRequiredVo.
     * 
     * @param currency
     */
    public void setCurrency(java.lang.String currency) {
        this.currency = currency;
    }


    /**
     * Gets the email value for this TradeReclRequiredVo.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this TradeReclRequiredVo.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the employeeId value for this TradeReclRequiredVo.
     * 
     * @return employeeId
     */
    public java.lang.Long getEmployeeId() {
        return employeeId;
    }


    /**
     * Sets the employeeId value for this TradeReclRequiredVo.
     * 
     * @param employeeId
     */
    public void setEmployeeId(java.lang.Long employeeId) {
        this.employeeId = employeeId;
    }


    /**
     * Gets the employeeName value for this TradeReclRequiredVo.
     * 
     * @return employeeName
     */
    public java.lang.String getEmployeeName() {
        return employeeName;
    }


    /**
     * Sets the employeeName value for this TradeReclRequiredVo.
     * 
     * @param employeeName
     */
    public void setEmployeeName(java.lang.String employeeName) {
        this.employeeName = employeeName;
    }


    /**
     * Gets the id value for this TradeReclRequiredVo.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this TradeReclRequiredVo.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the lateEntry value for this TradeReclRequiredVo.
     * 
     * @return lateEntry
     */
    public java.lang.Boolean getLateEntry() {
        return lateEntry;
    }


    /**
     * Sets the lateEntry value for this TradeReclRequiredVo.
     * 
     * @param lateEntry
     */
    public void setLateEntry(java.lang.Boolean lateEntry) {
        this.lateEntry = lateEntry;
    }


    /**
     * Gets the manualStateCode value for this TradeReclRequiredVo.
     * 
     * @return manualStateCode
     */
    public java.lang.String getManualStateCode() {
        return manualStateCode;
    }


    /**
     * Sets the manualStateCode value for this TradeReclRequiredVo.
     * 
     * @param manualStateCode
     */
    public void setManualStateCode(java.lang.String manualStateCode) {
        this.manualStateCode = manualStateCode;
    }


    /**
     * Gets the phone value for this TradeReclRequiredVo.
     * 
     * @return phone
     */
    public java.lang.String getPhone() {
        return phone;
    }


    /**
     * Sets the phone value for this TradeReclRequiredVo.
     * 
     * @param phone
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }


    /**
     * Gets the price value for this TradeReclRequiredVo.
     * 
     * @return price
     */
    public double getPrice() {
        return price;
    }


    /**
     * Sets the price value for this TradeReclRequiredVo.
     * 
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }


    /**
     * Gets the settlementDate value for this TradeReclRequiredVo.
     * 
     * @return settlementDate
     */
    public java.util.Calendar getSettlementDate() {
        return settlementDate;
    }


    /**
     * Sets the settlementDate value for this TradeReclRequiredVo.
     * 
     * @param settlementDate
     */
    public void setSettlementDate(java.util.Calendar settlementDate) {
        this.settlementDate = settlementDate;
    }


    /**
     * Gets the sourceSystemInstrument value for this TradeReclRequiredVo.
     * 
     * @return sourceSystemInstrument
     */
    public java.lang.String getSourceSystemInstrument() {
        return sourceSystemInstrument;
    }


    /**
     * Sets the sourceSystemInstrument value for this TradeReclRequiredVo.
     * 
     * @param sourceSystemInstrument
     */
    public void setSourceSystemInstrument(java.lang.String sourceSystemInstrument) {
        this.sourceSystemInstrument = sourceSystemInstrument;
    }


    /**
     * Gets the systemDate value for this TradeReclRequiredVo.
     * 
     * @return systemDate
     */
    public java.util.Calendar getSystemDate() {
        return systemDate;
    }


    /**
     * Sets the systemDate value for this TradeReclRequiredVo.
     * 
     * @param systemDate
     */
    public void setSystemDate(java.util.Calendar systemDate) {
        this.systemDate = systemDate;
    }


    /**
     * Gets the tradeDate value for this TradeReclRequiredVo.
     * 
     * @return tradeDate
     */
    public java.util.Calendar getTradeDate() {
        return tradeDate;
    }


    /**
     * Sets the tradeDate value for this TradeReclRequiredVo.
     * 
     * @param tradeDate
     */
    public void setTradeDate(java.util.Calendar tradeDate) {
        this.tradeDate = tradeDate;
    }


    /**
     * Gets the tradeId value for this TradeReclRequiredVo.
     * 
     * @return tradeId
     */
    public java.lang.String getTradeId() {
        return tradeId;
    }


    /**
     * Sets the tradeId value for this TradeReclRequiredVo.
     * 
     * @param tradeId
     */
    public void setTradeId(java.lang.String tradeId) {
        this.tradeId = tradeId;
    }


    /**
     * Gets the traderId value for this TradeReclRequiredVo.
     * 
     * @return traderId
     */
    public java.lang.String getTraderId() {
        return traderId;
    }


    /**
     * Sets the traderId value for this TradeReclRequiredVo.
     * 
     * @param traderId
     */
    public void setTraderId(java.lang.String traderId) {
        this.traderId = traderId;
    }


    /**
     * Gets the volume value for this TradeReclRequiredVo.
     * 
     * @return volume
     */
    public double getVolume() {
        return volume;
    }


    /**
     * Sets the volume value for this TradeReclRequiredVo.
     * 
     * @param volume
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TradeReclRequiredVo)) return false;
        TradeReclRequiredVo other = (TradeReclRequiredVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.employeeId==null && other.getEmployeeId()==null) || 
             (this.employeeId!=null &&
              this.employeeId.equals(other.getEmployeeId()))) &&
            ((this.employeeName==null && other.getEmployeeName()==null) || 
             (this.employeeName!=null &&
              this.employeeName.equals(other.getEmployeeName()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.lateEntry==null && other.getLateEntry()==null) || 
             (this.lateEntry!=null &&
              this.lateEntry.equals(other.getLateEntry()))) &&
            ((this.manualStateCode==null && other.getManualStateCode()==null) || 
             (this.manualStateCode!=null &&
              this.manualStateCode.equals(other.getManualStateCode()))) &&
            ((this.phone==null && other.getPhone()==null) || 
             (this.phone!=null &&
              this.phone.equals(other.getPhone()))) &&
            this.price == other.getPrice() &&
            ((this.settlementDate==null && other.getSettlementDate()==null) || 
             (this.settlementDate!=null &&
              this.settlementDate.equals(other.getSettlementDate()))) &&
            ((this.sourceSystemInstrument==null && other.getSourceSystemInstrument()==null) || 
             (this.sourceSystemInstrument!=null &&
              this.sourceSystemInstrument.equals(other.getSourceSystemInstrument()))) &&
            ((this.systemDate==null && other.getSystemDate()==null) || 
             (this.systemDate!=null &&
              this.systemDate.equals(other.getSystemDate()))) &&
            ((this.tradeDate==null && other.getTradeDate()==null) || 
             (this.tradeDate!=null &&
              this.tradeDate.equals(other.getTradeDate()))) &&
            ((this.tradeId==null && other.getTradeId()==null) || 
             (this.tradeId!=null &&
              this.tradeId.equals(other.getTradeId()))) &&
            ((this.traderId==null && other.getTraderId()==null) || 
             (this.traderId!=null &&
              this.traderId.equals(other.getTraderId()))) &&
            this.volume == other.getVolume();
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
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getEmployeeId() != null) {
            _hashCode += getEmployeeId().hashCode();
        }
        if (getEmployeeName() != null) {
            _hashCode += getEmployeeName().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getLateEntry() != null) {
            _hashCode += getLateEntry().hashCode();
        }
        if (getManualStateCode() != null) {
            _hashCode += getManualStateCode().hashCode();
        }
        if (getPhone() != null) {
            _hashCode += getPhone().hashCode();
        }
        _hashCode += new Double(getPrice()).hashCode();
        if (getSettlementDate() != null) {
            _hashCode += getSettlementDate().hashCode();
        }
        if (getSourceSystemInstrument() != null) {
            _hashCode += getSourceSystemInstrument().hashCode();
        }
        if (getSystemDate() != null) {
            _hashCode += getSystemDate().hashCode();
        }
        if (getTradeDate() != null) {
            _hashCode += getTradeDate().hashCode();
        }
        if (getTradeId() != null) {
            _hashCode += getTradeId().hashCode();
        }
        if (getTraderId() != null) {
            _hashCode += getTraderId().hashCode();
        }
        _hashCode += new Double(getVolume()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TradeReclRequiredVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "TradeReclRequiredVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("employeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "employeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("employeeName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "employeeName"));
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
        elemField.setFieldName("lateEntry");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lateEntry"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("manualStateCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "manualStateCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "phone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("settlementDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "settlementDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceSystemInstrument");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceSystemInstrument"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("systemDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "systemDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("traderId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "traderId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("volume");
        elemField.setXmlName(new javax.xml.namespace.QName("", "volume"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
