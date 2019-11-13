/**
 * TradeSearchResultEntryVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class TradeSearchResultEntryVo  implements java.io.Serializable {
    private java.lang.String bookId;

    private java.lang.String comment;

    private java.lang.String currency;

    private java.lang.Long id;

    private java.lang.String instrument;

    private java.lang.String isin;

    private java.util.Calendar maturityDate;

    private double price;

    private java.util.Calendar settlementDate;

    private java.util.Calendar systemDate;

    private java.util.Calendar tradeDate;

    private java.lang.String tradeId;

    private java.lang.String tradeType;

    private java.lang.String traderId;

    private java.lang.String traderLocation;

    private java.lang.String traderName;

    private double volume;

    public TradeSearchResultEntryVo() {
    }

    public TradeSearchResultEntryVo(
           java.lang.String bookId,
           java.lang.String comment,
           java.lang.String currency,
           java.lang.Long id,
           java.lang.String instrument,
           java.lang.String isin,
           java.util.Calendar maturityDate,
           double price,
           java.util.Calendar settlementDate,
           java.util.Calendar systemDate,
           java.util.Calendar tradeDate,
           java.lang.String tradeId,
           java.lang.String tradeType,
           java.lang.String traderId,
           java.lang.String traderLocation,
           java.lang.String traderName,
           double volume) {
           this.bookId = bookId;
           this.comment = comment;
           this.currency = currency;
           this.id = id;
           this.instrument = instrument;
           this.isin = isin;
           this.maturityDate = maturityDate;
           this.price = price;
           this.settlementDate = settlementDate;
           this.systemDate = systemDate;
           this.tradeDate = tradeDate;
           this.tradeId = tradeId;
           this.tradeType = tradeType;
           this.traderId = traderId;
           this.traderLocation = traderLocation;
           this.traderName = traderName;
           this.volume = volume;
    }


    /**
     * Gets the bookId value for this TradeSearchResultEntryVo.
     * 
     * @return bookId
     */
    public java.lang.String getBookId() {
        return bookId;
    }


    /**
     * Sets the bookId value for this TradeSearchResultEntryVo.
     * 
     * @param bookId
     */
    public void setBookId(java.lang.String bookId) {
        this.bookId = bookId;
    }


    /**
     * Gets the comment value for this TradeSearchResultEntryVo.
     * 
     * @return comment
     */
    public java.lang.String getComment() {
        return comment;
    }


    /**
     * Sets the comment value for this TradeSearchResultEntryVo.
     * 
     * @param comment
     */
    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }


    /**
     * Gets the currency value for this TradeSearchResultEntryVo.
     * 
     * @return currency
     */
    public java.lang.String getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this TradeSearchResultEntryVo.
     * 
     * @param currency
     */
    public void setCurrency(java.lang.String currency) {
        this.currency = currency;
    }


    /**
     * Gets the id value for this TradeSearchResultEntryVo.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this TradeSearchResultEntryVo.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the instrument value for this TradeSearchResultEntryVo.
     * 
     * @return instrument
     */
    public java.lang.String getInstrument() {
        return instrument;
    }


    /**
     * Sets the instrument value for this TradeSearchResultEntryVo.
     * 
     * @param instrument
     */
    public void setInstrument(java.lang.String instrument) {
        this.instrument = instrument;
    }


    /**
     * Gets the isin value for this TradeSearchResultEntryVo.
     * 
     * @return isin
     */
    public java.lang.String getIsin() {
        return isin;
    }


    /**
     * Sets the isin value for this TradeSearchResultEntryVo.
     * 
     * @param isin
     */
    public void setIsin(java.lang.String isin) {
        this.isin = isin;
    }


    /**
     * Gets the maturityDate value for this TradeSearchResultEntryVo.
     * 
     * @return maturityDate
     */
    public java.util.Calendar getMaturityDate() {
        return maturityDate;
    }


    /**
     * Sets the maturityDate value for this TradeSearchResultEntryVo.
     * 
     * @param maturityDate
     */
    public void setMaturityDate(java.util.Calendar maturityDate) {
        this.maturityDate = maturityDate;
    }


    /**
     * Gets the price value for this TradeSearchResultEntryVo.
     * 
     * @return price
     */
    public double getPrice() {
        return price;
    }


    /**
     * Sets the price value for this TradeSearchResultEntryVo.
     * 
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }


    /**
     * Gets the settlementDate value for this TradeSearchResultEntryVo.
     * 
     * @return settlementDate
     */
    public java.util.Calendar getSettlementDate() {
        return settlementDate;
    }


    /**
     * Sets the settlementDate value for this TradeSearchResultEntryVo.
     * 
     * @param settlementDate
     */
    public void setSettlementDate(java.util.Calendar settlementDate) {
        this.settlementDate = settlementDate;
    }


    /**
     * Gets the systemDate value for this TradeSearchResultEntryVo.
     * 
     * @return systemDate
     */
    public java.util.Calendar getSystemDate() {
        return systemDate;
    }


    /**
     * Sets the systemDate value for this TradeSearchResultEntryVo.
     * 
     * @param systemDate
     */
    public void setSystemDate(java.util.Calendar systemDate) {
        this.systemDate = systemDate;
    }


    /**
     * Gets the tradeDate value for this TradeSearchResultEntryVo.
     * 
     * @return tradeDate
     */
    public java.util.Calendar getTradeDate() {
        return tradeDate;
    }


    /**
     * Sets the tradeDate value for this TradeSearchResultEntryVo.
     * 
     * @param tradeDate
     */
    public void setTradeDate(java.util.Calendar tradeDate) {
        this.tradeDate = tradeDate;
    }


    /**
     * Gets the tradeId value for this TradeSearchResultEntryVo.
     * 
     * @return tradeId
     */
    public java.lang.String getTradeId() {
        return tradeId;
    }


    /**
     * Sets the tradeId value for this TradeSearchResultEntryVo.
     * 
     * @param tradeId
     */
    public void setTradeId(java.lang.String tradeId) {
        this.tradeId = tradeId;
    }


    /**
     * Gets the tradeType value for this TradeSearchResultEntryVo.
     * 
     * @return tradeType
     */
    public java.lang.String getTradeType() {
        return tradeType;
    }


    /**
     * Sets the tradeType value for this TradeSearchResultEntryVo.
     * 
     * @param tradeType
     */
    public void setTradeType(java.lang.String tradeType) {
        this.tradeType = tradeType;
    }


    /**
     * Gets the traderId value for this TradeSearchResultEntryVo.
     * 
     * @return traderId
     */
    public java.lang.String getTraderId() {
        return traderId;
    }


    /**
     * Sets the traderId value for this TradeSearchResultEntryVo.
     * 
     * @param traderId
     */
    public void setTraderId(java.lang.String traderId) {
        this.traderId = traderId;
    }


    /**
     * Gets the traderLocation value for this TradeSearchResultEntryVo.
     * 
     * @return traderLocation
     */
    public java.lang.String getTraderLocation() {
        return traderLocation;
    }


    /**
     * Sets the traderLocation value for this TradeSearchResultEntryVo.
     * 
     * @param traderLocation
     */
    public void setTraderLocation(java.lang.String traderLocation) {
        this.traderLocation = traderLocation;
    }


    /**
     * Gets the traderName value for this TradeSearchResultEntryVo.
     * 
     * @return traderName
     */
    public java.lang.String getTraderName() {
        return traderName;
    }


    /**
     * Sets the traderName value for this TradeSearchResultEntryVo.
     * 
     * @param traderName
     */
    public void setTraderName(java.lang.String traderName) {
        this.traderName = traderName;
    }


    /**
     * Gets the volume value for this TradeSearchResultEntryVo.
     * 
     * @return volume
     */
    public double getVolume() {
        return volume;
    }


    /**
     * Sets the volume value for this TradeSearchResultEntryVo.
     * 
     * @param volume
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TradeSearchResultEntryVo)) return false;
        TradeSearchResultEntryVo other = (TradeSearchResultEntryVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bookId==null && other.getBookId()==null) || 
             (this.bookId!=null &&
              this.bookId.equals(other.getBookId()))) &&
            ((this.comment==null && other.getComment()==null) || 
             (this.comment!=null &&
              this.comment.equals(other.getComment()))) &&
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.instrument==null && other.getInstrument()==null) || 
             (this.instrument!=null &&
              this.instrument.equals(other.getInstrument()))) &&
            ((this.isin==null && other.getIsin()==null) || 
             (this.isin!=null &&
              this.isin.equals(other.getIsin()))) &&
            ((this.maturityDate==null && other.getMaturityDate()==null) || 
             (this.maturityDate!=null &&
              this.maturityDate.equals(other.getMaturityDate()))) &&
            this.price == other.getPrice() &&
            ((this.settlementDate==null && other.getSettlementDate()==null) || 
             (this.settlementDate!=null &&
              this.settlementDate.equals(other.getSettlementDate()))) &&
            ((this.systemDate==null && other.getSystemDate()==null) || 
             (this.systemDate!=null &&
              this.systemDate.equals(other.getSystemDate()))) &&
            ((this.tradeDate==null && other.getTradeDate()==null) || 
             (this.tradeDate!=null &&
              this.tradeDate.equals(other.getTradeDate()))) &&
            ((this.tradeId==null && other.getTradeId()==null) || 
             (this.tradeId!=null &&
              this.tradeId.equals(other.getTradeId()))) &&
            ((this.tradeType==null && other.getTradeType()==null) || 
             (this.tradeType!=null &&
              this.tradeType.equals(other.getTradeType()))) &&
            ((this.traderId==null && other.getTraderId()==null) || 
             (this.traderId!=null &&
              this.traderId.equals(other.getTraderId()))) &&
            ((this.traderLocation==null && other.getTraderLocation()==null) || 
             (this.traderLocation!=null &&
              this.traderLocation.equals(other.getTraderLocation()))) &&
            ((this.traderName==null && other.getTraderName()==null) || 
             (this.traderName!=null &&
              this.traderName.equals(other.getTraderName()))) &&
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
        if (getBookId() != null) {
            _hashCode += getBookId().hashCode();
        }
        if (getComment() != null) {
            _hashCode += getComment().hashCode();
        }
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getInstrument() != null) {
            _hashCode += getInstrument().hashCode();
        }
        if (getIsin() != null) {
            _hashCode += getIsin().hashCode();
        }
        if (getMaturityDate() != null) {
            _hashCode += getMaturityDate().hashCode();
        }
        _hashCode += new Double(getPrice()).hashCode();
        if (getSettlementDate() != null) {
            _hashCode += getSettlementDate().hashCode();
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
        if (getTradeType() != null) {
            _hashCode += getTradeType().hashCode();
        }
        if (getTraderId() != null) {
            _hashCode += getTraderId().hashCode();
        }
        if (getTraderLocation() != null) {
            _hashCode += getTraderLocation().hashCode();
        }
        if (getTraderName() != null) {
            _hashCode += getTraderName().hashCode();
        }
        _hashCode += new Double(getVolume()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TradeSearchResultEntryVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "TradeSearchResultEntryVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bookId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currency"));
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
        elemField.setFieldName("instrument");
        elemField.setXmlName(new javax.xml.namespace.QName("", "instrument"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maturityDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maturityDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("tradeType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeType"));
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
        elemField.setFieldName("traderLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "traderLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("traderName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "traderName"));
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
