/**
 * RequestVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class RequestVo  implements java.io.Serializable {
    private java.lang.String isoCurrency;

    private java.lang.Long jobId;

    private java.util.Calendar priceDate;

    private java.lang.String priceSource;

    private java.util.Calendar requestDate;

    private java.lang.String requestField;

    private java.lang.Long requestId;

    private java.lang.String[] requestSources;

    private java.lang.String requestState;

    private java.lang.String requestString;

    private java.lang.String requestType;

    private double tradePrice;

    public RequestVo() {
    }

    public RequestVo(
           java.lang.String isoCurrency,
           java.lang.Long jobId,
           java.util.Calendar priceDate,
           java.lang.String priceSource,
           java.util.Calendar requestDate,
           java.lang.String requestField,
           java.lang.Long requestId,
           java.lang.String[] requestSources,
           java.lang.String requestState,
           java.lang.String requestString,
           java.lang.String requestType,
           double tradePrice) {
           this.isoCurrency = isoCurrency;
           this.jobId = jobId;
           this.priceDate = priceDate;
           this.priceSource = priceSource;
           this.requestDate = requestDate;
           this.requestField = requestField;
           this.requestId = requestId;
           this.requestSources = requestSources;
           this.requestState = requestState;
           this.requestString = requestString;
           this.requestType = requestType;
           this.tradePrice = tradePrice;
    }


    /**
     * Gets the isoCurrency value for this RequestVo.
     * 
     * @return isoCurrency
     */
    public java.lang.String getIsoCurrency() {
        return isoCurrency;
    }


    /**
     * Sets the isoCurrency value for this RequestVo.
     * 
     * @param isoCurrency
     */
    public void setIsoCurrency(java.lang.String isoCurrency) {
        this.isoCurrency = isoCurrency;
    }


    /**
     * Gets the jobId value for this RequestVo.
     * 
     * @return jobId
     */
    public java.lang.Long getJobId() {
        return jobId;
    }


    /**
     * Sets the jobId value for this RequestVo.
     * 
     * @param jobId
     */
    public void setJobId(java.lang.Long jobId) {
        this.jobId = jobId;
    }


    /**
     * Gets the priceDate value for this RequestVo.
     * 
     * @return priceDate
     */
    public java.util.Calendar getPriceDate() {
        return priceDate;
    }


    /**
     * Sets the priceDate value for this RequestVo.
     * 
     * @param priceDate
     */
    public void setPriceDate(java.util.Calendar priceDate) {
        this.priceDate = priceDate;
    }


    /**
     * Gets the priceSource value for this RequestVo.
     * 
     * @return priceSource
     */
    public java.lang.String getPriceSource() {
        return priceSource;
    }


    /**
     * Sets the priceSource value for this RequestVo.
     * 
     * @param priceSource
     */
    public void setPriceSource(java.lang.String priceSource) {
        this.priceSource = priceSource;
    }


    /**
     * Gets the requestDate value for this RequestVo.
     * 
     * @return requestDate
     */
    public java.util.Calendar getRequestDate() {
        return requestDate;
    }


    /**
     * Sets the requestDate value for this RequestVo.
     * 
     * @param requestDate
     */
    public void setRequestDate(java.util.Calendar requestDate) {
        this.requestDate = requestDate;
    }


    /**
     * Gets the requestField value for this RequestVo.
     * 
     * @return requestField
     */
    public java.lang.String getRequestField() {
        return requestField;
    }


    /**
     * Sets the requestField value for this RequestVo.
     * 
     * @param requestField
     */
    public void setRequestField(java.lang.String requestField) {
        this.requestField = requestField;
    }


    /**
     * Gets the requestId value for this RequestVo.
     * 
     * @return requestId
     */
    public java.lang.Long getRequestId() {
        return requestId;
    }


    /**
     * Sets the requestId value for this RequestVo.
     * 
     * @param requestId
     */
    public void setRequestId(java.lang.Long requestId) {
        this.requestId = requestId;
    }


    /**
     * Gets the requestSources value for this RequestVo.
     * 
     * @return requestSources
     */
    public java.lang.String[] getRequestSources() {
        return requestSources;
    }


    /**
     * Sets the requestSources value for this RequestVo.
     * 
     * @param requestSources
     */
    public void setRequestSources(java.lang.String[] requestSources) {
        this.requestSources = requestSources;
    }


    /**
     * Gets the requestState value for this RequestVo.
     * 
     * @return requestState
     */
    public java.lang.String getRequestState() {
        return requestState;
    }


    /**
     * Sets the requestState value for this RequestVo.
     * 
     * @param requestState
     */
    public void setRequestState(java.lang.String requestState) {
        this.requestState = requestState;
    }


    /**
     * Gets the requestString value for this RequestVo.
     * 
     * @return requestString
     */
    public java.lang.String getRequestString() {
        return requestString;
    }


    /**
     * Sets the requestString value for this RequestVo.
     * 
     * @param requestString
     */
    public void setRequestString(java.lang.String requestString) {
        this.requestString = requestString;
    }


    /**
     * Gets the requestType value for this RequestVo.
     * 
     * @return requestType
     */
    public java.lang.String getRequestType() {
        return requestType;
    }


    /**
     * Sets the requestType value for this RequestVo.
     * 
     * @param requestType
     */
    public void setRequestType(java.lang.String requestType) {
        this.requestType = requestType;
    }


    /**
     * Gets the tradePrice value for this RequestVo.
     * 
     * @return tradePrice
     */
    public double getTradePrice() {
        return tradePrice;
    }


    /**
     * Sets the tradePrice value for this RequestVo.
     * 
     * @param tradePrice
     */
    public void setTradePrice(double tradePrice) {
        this.tradePrice = tradePrice;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RequestVo)) return false;
        RequestVo other = (RequestVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.isoCurrency==null && other.getIsoCurrency()==null) || 
             (this.isoCurrency!=null &&
              this.isoCurrency.equals(other.getIsoCurrency()))) &&
            ((this.jobId==null && other.getJobId()==null) || 
             (this.jobId!=null &&
              this.jobId.equals(other.getJobId()))) &&
            ((this.priceDate==null && other.getPriceDate()==null) || 
             (this.priceDate!=null &&
              this.priceDate.equals(other.getPriceDate()))) &&
            ((this.priceSource==null && other.getPriceSource()==null) || 
             (this.priceSource!=null &&
              this.priceSource.equals(other.getPriceSource()))) &&
            ((this.requestDate==null && other.getRequestDate()==null) || 
             (this.requestDate!=null &&
              this.requestDate.equals(other.getRequestDate()))) &&
            ((this.requestField==null && other.getRequestField()==null) || 
             (this.requestField!=null &&
              this.requestField.equals(other.getRequestField()))) &&
            ((this.requestId==null && other.getRequestId()==null) || 
             (this.requestId!=null &&
              this.requestId.equals(other.getRequestId()))) &&
            ((this.requestSources==null && other.getRequestSources()==null) || 
             (this.requestSources!=null &&
              java.util.Arrays.equals(this.requestSources, other.getRequestSources()))) &&
            ((this.requestState==null && other.getRequestState()==null) || 
             (this.requestState!=null &&
              this.requestState.equals(other.getRequestState()))) &&
            ((this.requestString==null && other.getRequestString()==null) || 
             (this.requestString!=null &&
              this.requestString.equals(other.getRequestString()))) &&
            ((this.requestType==null && other.getRequestType()==null) || 
             (this.requestType!=null &&
              this.requestType.equals(other.getRequestType()))) &&
            this.tradePrice == other.getTradePrice();
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
        if (getIsoCurrency() != null) {
            _hashCode += getIsoCurrency().hashCode();
        }
        if (getJobId() != null) {
            _hashCode += getJobId().hashCode();
        }
        if (getPriceDate() != null) {
            _hashCode += getPriceDate().hashCode();
        }
        if (getPriceSource() != null) {
            _hashCode += getPriceSource().hashCode();
        }
        if (getRequestDate() != null) {
            _hashCode += getRequestDate().hashCode();
        }
        if (getRequestField() != null) {
            _hashCode += getRequestField().hashCode();
        }
        if (getRequestId() != null) {
            _hashCode += getRequestId().hashCode();
        }
        if (getRequestSources() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRequestSources());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRequestSources(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRequestState() != null) {
            _hashCode += getRequestState().hashCode();
        }
        if (getRequestString() != null) {
            _hashCode += getRequestString().hashCode();
        }
        if (getRequestType() != null) {
            _hashCode += getRequestType().hashCode();
        }
        _hashCode += new Double(getTradePrice()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RequestVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "RequestVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isoCurrency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isoCurrency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "jobId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceSource");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceSource"));
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
        elemField.setFieldName("requestField");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestField"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestSources");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestSources"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestState");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestState"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestString");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestString"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradePrice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tradePrice"));
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
