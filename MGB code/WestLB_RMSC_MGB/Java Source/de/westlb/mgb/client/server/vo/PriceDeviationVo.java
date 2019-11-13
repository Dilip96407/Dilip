/**
 * PriceDeviationVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class PriceDeviationVo  implements java.io.Serializable {
    private java.lang.Double actualDiff;

    private java.lang.Double bloombergDiff;

    private java.lang.Double bloombergMaxPrice;

    private java.lang.Double bloombergMinPrice;

    private java.lang.String bookId;

    private java.lang.String category;

    private java.lang.String instrument;

    private java.lang.Boolean isNotExceeded;

    private java.lang.String priceCheckCategory;

    private java.util.Calendar requestDate;

    private java.lang.Long requestId;

    private java.lang.String requestString;

    private java.lang.String srcTradeId;

    private java.lang.String summitCategory;

    private java.lang.Double theorericalDiff;

    private java.lang.Double theorericalPrice;

    private java.lang.Double tolerance;

    private java.lang.Long tradeId;

    private java.lang.Double tradePrice;

    public PriceDeviationVo() {
    }

    public PriceDeviationVo(
           java.lang.Double actualDiff,
           java.lang.Double bloombergDiff,
           java.lang.Double bloombergMaxPrice,
           java.lang.Double bloombergMinPrice,
           java.lang.String bookId,
           java.lang.String category,
           java.lang.String instrument,
           java.lang.Boolean isNotExceeded,
           java.lang.String priceCheckCategory,
           java.util.Calendar requestDate,
           java.lang.Long requestId,
           java.lang.String requestString,
           java.lang.String srcTradeId,
           java.lang.String summitCategory,
           java.lang.Double theorericalDiff,
           java.lang.Double theorericalPrice,
           java.lang.Double tolerance,
           java.lang.Long tradeId,
           java.lang.Double tradePrice) {
           this.actualDiff = actualDiff;
           this.bloombergDiff = bloombergDiff;
           this.bloombergMaxPrice = bloombergMaxPrice;
           this.bloombergMinPrice = bloombergMinPrice;
           this.bookId = bookId;
           this.category = category;
           this.instrument = instrument;
           this.isNotExceeded = isNotExceeded;
           this.priceCheckCategory = priceCheckCategory;
           this.requestDate = requestDate;
           this.requestId = requestId;
           this.requestString = requestString;
           this.srcTradeId = srcTradeId;
           this.summitCategory = summitCategory;
           this.theorericalDiff = theorericalDiff;
           this.theorericalPrice = theorericalPrice;
           this.tolerance = tolerance;
           this.tradeId = tradeId;
           this.tradePrice = tradePrice;
    }


    /**
     * Gets the actualDiff value for this PriceDeviationVo.
     * 
     * @return actualDiff
     */
    public java.lang.Double getActualDiff() {
        return actualDiff;
    }


    /**
     * Sets the actualDiff value for this PriceDeviationVo.
     * 
     * @param actualDiff
     */
    public void setActualDiff(java.lang.Double actualDiff) {
        this.actualDiff = actualDiff;
    }


    /**
     * Gets the bloombergDiff value for this PriceDeviationVo.
     * 
     * @return bloombergDiff
     */
    public java.lang.Double getBloombergDiff() {
        return bloombergDiff;
    }


    /**
     * Sets the bloombergDiff value for this PriceDeviationVo.
     * 
     * @param bloombergDiff
     */
    public void setBloombergDiff(java.lang.Double bloombergDiff) {
        this.bloombergDiff = bloombergDiff;
    }


    /**
     * Gets the bloombergMaxPrice value for this PriceDeviationVo.
     * 
     * @return bloombergMaxPrice
     */
    public java.lang.Double getBloombergMaxPrice() {
        return bloombergMaxPrice;
    }


    /**
     * Sets the bloombergMaxPrice value for this PriceDeviationVo.
     * 
     * @param bloombergMaxPrice
     */
    public void setBloombergMaxPrice(java.lang.Double bloombergMaxPrice) {
        this.bloombergMaxPrice = bloombergMaxPrice;
    }


    /**
     * Gets the bloombergMinPrice value for this PriceDeviationVo.
     * 
     * @return bloombergMinPrice
     */
    public java.lang.Double getBloombergMinPrice() {
        return bloombergMinPrice;
    }


    /**
     * Sets the bloombergMinPrice value for this PriceDeviationVo.
     * 
     * @param bloombergMinPrice
     */
    public void setBloombergMinPrice(java.lang.Double bloombergMinPrice) {
        this.bloombergMinPrice = bloombergMinPrice;
    }


    /**
     * Gets the bookId value for this PriceDeviationVo.
     * 
     * @return bookId
     */
    public java.lang.String getBookId() {
        return bookId;
    }


    /**
     * Sets the bookId value for this PriceDeviationVo.
     * 
     * @param bookId
     */
    public void setBookId(java.lang.String bookId) {
        this.bookId = bookId;
    }


    /**
     * Gets the category value for this PriceDeviationVo.
     * 
     * @return category
     */
    public java.lang.String getCategory() {
        return category;
    }


    /**
     * Sets the category value for this PriceDeviationVo.
     * 
     * @param category
     */
    public void setCategory(java.lang.String category) {
        this.category = category;
    }


    /**
     * Gets the instrument value for this PriceDeviationVo.
     * 
     * @return instrument
     */
    public java.lang.String getInstrument() {
        return instrument;
    }


    /**
     * Sets the instrument value for this PriceDeviationVo.
     * 
     * @param instrument
     */
    public void setInstrument(java.lang.String instrument) {
        this.instrument = instrument;
    }


    /**
     * Gets the isNotExceeded value for this PriceDeviationVo.
     * 
     * @return isNotExceeded
     */
    public java.lang.Boolean getIsNotExceeded() {
        return isNotExceeded;
    }


    /**
     * Sets the isNotExceeded value for this PriceDeviationVo.
     * 
     * @param isNotExceeded
     */
    public void setIsNotExceeded(java.lang.Boolean isNotExceeded) {
        this.isNotExceeded = isNotExceeded;
    }


    /**
     * Gets the priceCheckCategory value for this PriceDeviationVo.
     * 
     * @return priceCheckCategory
     */
    public java.lang.String getPriceCheckCategory() {
        return priceCheckCategory;
    }


    /**
     * Sets the priceCheckCategory value for this PriceDeviationVo.
     * 
     * @param priceCheckCategory
     */
    public void setPriceCheckCategory(java.lang.String priceCheckCategory) {
        this.priceCheckCategory = priceCheckCategory;
    }


    /**
     * Gets the requestDate value for this PriceDeviationVo.
     * 
     * @return requestDate
     */
    public java.util.Calendar getRequestDate() {
        return requestDate;
    }


    /**
     * Sets the requestDate value for this PriceDeviationVo.
     * 
     * @param requestDate
     */
    public void setRequestDate(java.util.Calendar requestDate) {
        this.requestDate = requestDate;
    }


    /**
     * Gets the requestId value for this PriceDeviationVo.
     * 
     * @return requestId
     */
    public java.lang.Long getRequestId() {
        return requestId;
    }


    /**
     * Sets the requestId value for this PriceDeviationVo.
     * 
     * @param requestId
     */
    public void setRequestId(java.lang.Long requestId) {
        this.requestId = requestId;
    }


    /**
     * Gets the requestString value for this PriceDeviationVo.
     * 
     * @return requestString
     */
    public java.lang.String getRequestString() {
        return requestString;
    }


    /**
     * Sets the requestString value for this PriceDeviationVo.
     * 
     * @param requestString
     */
    public void setRequestString(java.lang.String requestString) {
        this.requestString = requestString;
    }


    /**
     * Gets the srcTradeId value for this PriceDeviationVo.
     * 
     * @return srcTradeId
     */
    public java.lang.String getSrcTradeId() {
        return srcTradeId;
    }


    /**
     * Sets the srcTradeId value for this PriceDeviationVo.
     * 
     * @param srcTradeId
     */
    public void setSrcTradeId(java.lang.String srcTradeId) {
        this.srcTradeId = srcTradeId;
    }


    /**
     * Gets the summitCategory value for this PriceDeviationVo.
     * 
     * @return summitCategory
     */
    public java.lang.String getSummitCategory() {
        return summitCategory;
    }


    /**
     * Sets the summitCategory value for this PriceDeviationVo.
     * 
     * @param summitCategory
     */
    public void setSummitCategory(java.lang.String summitCategory) {
        this.summitCategory = summitCategory;
    }


    /**
     * Gets the theorericalDiff value for this PriceDeviationVo.
     * 
     * @return theorericalDiff
     */
    public java.lang.Double getTheorericalDiff() {
        return theorericalDiff;
    }


    /**
     * Sets the theorericalDiff value for this PriceDeviationVo.
     * 
     * @param theorericalDiff
     */
    public void setTheorericalDiff(java.lang.Double theorericalDiff) {
        this.theorericalDiff = theorericalDiff;
    }


    /**
     * Gets the theorericalPrice value for this PriceDeviationVo.
     * 
     * @return theorericalPrice
     */
    public java.lang.Double getTheorericalPrice() {
        return theorericalPrice;
    }


    /**
     * Sets the theorericalPrice value for this PriceDeviationVo.
     * 
     * @param theorericalPrice
     */
    public void setTheorericalPrice(java.lang.Double theorericalPrice) {
        this.theorericalPrice = theorericalPrice;
    }


    /**
     * Gets the tolerance value for this PriceDeviationVo.
     * 
     * @return tolerance
     */
    public java.lang.Double getTolerance() {
        return tolerance;
    }


    /**
     * Sets the tolerance value for this PriceDeviationVo.
     * 
     * @param tolerance
     */
    public void setTolerance(java.lang.Double tolerance) {
        this.tolerance = tolerance;
    }


    /**
     * Gets the tradeId value for this PriceDeviationVo.
     * 
     * @return tradeId
     */
    public java.lang.Long getTradeId() {
        return tradeId;
    }


    /**
     * Sets the tradeId value for this PriceDeviationVo.
     * 
     * @param tradeId
     */
    public void setTradeId(java.lang.Long tradeId) {
        this.tradeId = tradeId;
    }


    /**
     * Gets the tradePrice value for this PriceDeviationVo.
     * 
     * @return tradePrice
     */
    public java.lang.Double getTradePrice() {
        return tradePrice;
    }


    /**
     * Sets the tradePrice value for this PriceDeviationVo.
     * 
     * @param tradePrice
     */
    public void setTradePrice(java.lang.Double tradePrice) {
        this.tradePrice = tradePrice;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PriceDeviationVo)) return false;
        PriceDeviationVo other = (PriceDeviationVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.actualDiff==null && other.getActualDiff()==null) || 
             (this.actualDiff!=null &&
              this.actualDiff.equals(other.getActualDiff()))) &&
            ((this.bloombergDiff==null && other.getBloombergDiff()==null) || 
             (this.bloombergDiff!=null &&
              this.bloombergDiff.equals(other.getBloombergDiff()))) &&
            ((this.bloombergMaxPrice==null && other.getBloombergMaxPrice()==null) || 
             (this.bloombergMaxPrice!=null &&
              this.bloombergMaxPrice.equals(other.getBloombergMaxPrice()))) &&
            ((this.bloombergMinPrice==null && other.getBloombergMinPrice()==null) || 
             (this.bloombergMinPrice!=null &&
              this.bloombergMinPrice.equals(other.getBloombergMinPrice()))) &&
            ((this.bookId==null && other.getBookId()==null) || 
             (this.bookId!=null &&
              this.bookId.equals(other.getBookId()))) &&
            ((this.category==null && other.getCategory()==null) || 
             (this.category!=null &&
              this.category.equals(other.getCategory()))) &&
            ((this.instrument==null && other.getInstrument()==null) || 
             (this.instrument!=null &&
              this.instrument.equals(other.getInstrument()))) &&
            ((this.isNotExceeded==null && other.getIsNotExceeded()==null) || 
             (this.isNotExceeded!=null &&
              this.isNotExceeded.equals(other.getIsNotExceeded()))) &&
            ((this.priceCheckCategory==null && other.getPriceCheckCategory()==null) || 
             (this.priceCheckCategory!=null &&
              this.priceCheckCategory.equals(other.getPriceCheckCategory()))) &&
            ((this.requestDate==null && other.getRequestDate()==null) || 
             (this.requestDate!=null &&
              this.requestDate.equals(other.getRequestDate()))) &&
            ((this.requestId==null && other.getRequestId()==null) || 
             (this.requestId!=null &&
              this.requestId.equals(other.getRequestId()))) &&
            ((this.requestString==null && other.getRequestString()==null) || 
             (this.requestString!=null &&
              this.requestString.equals(other.getRequestString()))) &&
            ((this.srcTradeId==null && other.getSrcTradeId()==null) || 
             (this.srcTradeId!=null &&
              this.srcTradeId.equals(other.getSrcTradeId()))) &&
            ((this.summitCategory==null && other.getSummitCategory()==null) || 
             (this.summitCategory!=null &&
              this.summitCategory.equals(other.getSummitCategory()))) &&
            ((this.theorericalDiff==null && other.getTheorericalDiff()==null) || 
             (this.theorericalDiff!=null &&
              this.theorericalDiff.equals(other.getTheorericalDiff()))) &&
            ((this.theorericalPrice==null && other.getTheorericalPrice()==null) || 
             (this.theorericalPrice!=null &&
              this.theorericalPrice.equals(other.getTheorericalPrice()))) &&
            ((this.tolerance==null && other.getTolerance()==null) || 
             (this.tolerance!=null &&
              this.tolerance.equals(other.getTolerance()))) &&
            ((this.tradeId==null && other.getTradeId()==null) || 
             (this.tradeId!=null &&
              this.tradeId.equals(other.getTradeId()))) &&
            ((this.tradePrice==null && other.getTradePrice()==null) || 
             (this.tradePrice!=null &&
              this.tradePrice.equals(other.getTradePrice())));
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
        if (getActualDiff() != null) {
            _hashCode += getActualDiff().hashCode();
        }
        if (getBloombergDiff() != null) {
            _hashCode += getBloombergDiff().hashCode();
        }
        if (getBloombergMaxPrice() != null) {
            _hashCode += getBloombergMaxPrice().hashCode();
        }
        if (getBloombergMinPrice() != null) {
            _hashCode += getBloombergMinPrice().hashCode();
        }
        if (getBookId() != null) {
            _hashCode += getBookId().hashCode();
        }
        if (getCategory() != null) {
            _hashCode += getCategory().hashCode();
        }
        if (getInstrument() != null) {
            _hashCode += getInstrument().hashCode();
        }
        if (getIsNotExceeded() != null) {
            _hashCode += getIsNotExceeded().hashCode();
        }
        if (getPriceCheckCategory() != null) {
            _hashCode += getPriceCheckCategory().hashCode();
        }
        if (getRequestDate() != null) {
            _hashCode += getRequestDate().hashCode();
        }
        if (getRequestId() != null) {
            _hashCode += getRequestId().hashCode();
        }
        if (getRequestString() != null) {
            _hashCode += getRequestString().hashCode();
        }
        if (getSrcTradeId() != null) {
            _hashCode += getSrcTradeId().hashCode();
        }
        if (getSummitCategory() != null) {
            _hashCode += getSummitCategory().hashCode();
        }
        if (getTheorericalDiff() != null) {
            _hashCode += getTheorericalDiff().hashCode();
        }
        if (getTheorericalPrice() != null) {
            _hashCode += getTheorericalPrice().hashCode();
        }
        if (getTolerance() != null) {
            _hashCode += getTolerance().hashCode();
        }
        if (getTradeId() != null) {
            _hashCode += getTradeId().hashCode();
        }
        if (getTradePrice() != null) {
            _hashCode += getTradePrice().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PriceDeviationVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "PriceDeviationVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualDiff");
        elemField.setXmlName(new javax.xml.namespace.QName("", "actualDiff"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bloombergDiff");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bloombergDiff"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bloombergMaxPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bloombergMaxPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bloombergMinPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bloombergMinPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bookId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category");
        elemField.setXmlName(new javax.xml.namespace.QName("", "category"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instrument");
        elemField.setXmlName(new javax.xml.namespace.QName("", "instrument"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isNotExceeded");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isNotExceeded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceCheckCategory");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceCheckCategory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestString");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestString"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("srcTradeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "srcTradeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("summitCategory");
        elemField.setXmlName(new javax.xml.namespace.QName("", "summitCategory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("theorericalDiff");
        elemField.setXmlName(new javax.xml.namespace.QName("", "theorericalDiff"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("theorericalPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "theorericalPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tolerance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tolerance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradePrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradePrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
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
