/**
 * PriceDeviationStatisticVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class PriceDeviationStatisticVo  implements java.io.Serializable {
    private java.lang.String category;

    private java.lang.Double numberOfExceededTrades;

    private java.lang.Double numberOfTrades;

    private java.lang.Double percentageOfExceededTrades;

    private java.lang.String priceCheckCategory;

    private java.util.Calendar priceCheckCategoryChangeDate;

    private java.lang.String priceCheckCategoryChangedBy;

    private java.lang.String summitCategory;

    private java.lang.Double tolerance;

    public PriceDeviationStatisticVo() {
    }

    public PriceDeviationStatisticVo(
           java.lang.String category,
           java.lang.Double numberOfExceededTrades,
           java.lang.Double numberOfTrades,
           java.lang.Double percentageOfExceededTrades,
           java.lang.String priceCheckCategory,
           java.util.Calendar priceCheckCategoryChangeDate,
           java.lang.String priceCheckCategoryChangedBy,
           java.lang.String summitCategory,
           java.lang.Double tolerance) {
           this.category = category;
           this.numberOfExceededTrades = numberOfExceededTrades;
           this.numberOfTrades = numberOfTrades;
           this.percentageOfExceededTrades = percentageOfExceededTrades;
           this.priceCheckCategory = priceCheckCategory;
           this.priceCheckCategoryChangeDate = priceCheckCategoryChangeDate;
           this.priceCheckCategoryChangedBy = priceCheckCategoryChangedBy;
           this.summitCategory = summitCategory;
           this.tolerance = tolerance;
    }


    /**
     * Gets the category value for this PriceDeviationStatisticVo.
     * 
     * @return category
     */
    public java.lang.String getCategory() {
        return category;
    }


    /**
     * Sets the category value for this PriceDeviationStatisticVo.
     * 
     * @param category
     */
    public void setCategory(java.lang.String category) {
        this.category = category;
    }


    /**
     * Gets the numberOfExceededTrades value for this PriceDeviationStatisticVo.
     * 
     * @return numberOfExceededTrades
     */
    public java.lang.Double getNumberOfExceededTrades() {
        return numberOfExceededTrades;
    }


    /**
     * Sets the numberOfExceededTrades value for this PriceDeviationStatisticVo.
     * 
     * @param numberOfExceededTrades
     */
    public void setNumberOfExceededTrades(java.lang.Double numberOfExceededTrades) {
        this.numberOfExceededTrades = numberOfExceededTrades;
    }


    /**
     * Gets the numberOfTrades value for this PriceDeviationStatisticVo.
     * 
     * @return numberOfTrades
     */
    public java.lang.Double getNumberOfTrades() {
        return numberOfTrades;
    }


    /**
     * Sets the numberOfTrades value for this PriceDeviationStatisticVo.
     * 
     * @param numberOfTrades
     */
    public void setNumberOfTrades(java.lang.Double numberOfTrades) {
        this.numberOfTrades = numberOfTrades;
    }


    /**
     * Gets the percentageOfExceededTrades value for this PriceDeviationStatisticVo.
     * 
     * @return percentageOfExceededTrades
     */
    public java.lang.Double getPercentageOfExceededTrades() {
        return percentageOfExceededTrades;
    }


    /**
     * Sets the percentageOfExceededTrades value for this PriceDeviationStatisticVo.
     * 
     * @param percentageOfExceededTrades
     */
    public void setPercentageOfExceededTrades(java.lang.Double percentageOfExceededTrades) {
        this.percentageOfExceededTrades = percentageOfExceededTrades;
    }


    /**
     * Gets the priceCheckCategory value for this PriceDeviationStatisticVo.
     * 
     * @return priceCheckCategory
     */
    public java.lang.String getPriceCheckCategory() {
        return priceCheckCategory;
    }


    /**
     * Sets the priceCheckCategory value for this PriceDeviationStatisticVo.
     * 
     * @param priceCheckCategory
     */
    public void setPriceCheckCategory(java.lang.String priceCheckCategory) {
        this.priceCheckCategory = priceCheckCategory;
    }


    /**
     * Gets the priceCheckCategoryChangeDate value for this PriceDeviationStatisticVo.
     * 
     * @return priceCheckCategoryChangeDate
     */
    public java.util.Calendar getPriceCheckCategoryChangeDate() {
        return priceCheckCategoryChangeDate;
    }


    /**
     * Sets the priceCheckCategoryChangeDate value for this PriceDeviationStatisticVo.
     * 
     * @param priceCheckCategoryChangeDate
     */
    public void setPriceCheckCategoryChangeDate(java.util.Calendar priceCheckCategoryChangeDate) {
        this.priceCheckCategoryChangeDate = priceCheckCategoryChangeDate;
    }


    /**
     * Gets the priceCheckCategoryChangedBy value for this PriceDeviationStatisticVo.
     * 
     * @return priceCheckCategoryChangedBy
     */
    public java.lang.String getPriceCheckCategoryChangedBy() {
        return priceCheckCategoryChangedBy;
    }


    /**
     * Sets the priceCheckCategoryChangedBy value for this PriceDeviationStatisticVo.
     * 
     * @param priceCheckCategoryChangedBy
     */
    public void setPriceCheckCategoryChangedBy(java.lang.String priceCheckCategoryChangedBy) {
        this.priceCheckCategoryChangedBy = priceCheckCategoryChangedBy;
    }


    /**
     * Gets the summitCategory value for this PriceDeviationStatisticVo.
     * 
     * @return summitCategory
     */
    public java.lang.String getSummitCategory() {
        return summitCategory;
    }


    /**
     * Sets the summitCategory value for this PriceDeviationStatisticVo.
     * 
     * @param summitCategory
     */
    public void setSummitCategory(java.lang.String summitCategory) {
        this.summitCategory = summitCategory;
    }


    /**
     * Gets the tolerance value for this PriceDeviationStatisticVo.
     * 
     * @return tolerance
     */
    public java.lang.Double getTolerance() {
        return tolerance;
    }


    /**
     * Sets the tolerance value for this PriceDeviationStatisticVo.
     * 
     * @param tolerance
     */
    public void setTolerance(java.lang.Double tolerance) {
        this.tolerance = tolerance;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PriceDeviationStatisticVo)) return false;
        PriceDeviationStatisticVo other = (PriceDeviationStatisticVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.category==null && other.getCategory()==null) || 
             (this.category!=null &&
              this.category.equals(other.getCategory()))) &&
            ((this.numberOfExceededTrades==null && other.getNumberOfExceededTrades()==null) || 
             (this.numberOfExceededTrades!=null &&
              this.numberOfExceededTrades.equals(other.getNumberOfExceededTrades()))) &&
            ((this.numberOfTrades==null && other.getNumberOfTrades()==null) || 
             (this.numberOfTrades!=null &&
              this.numberOfTrades.equals(other.getNumberOfTrades()))) &&
            ((this.percentageOfExceededTrades==null && other.getPercentageOfExceededTrades()==null) || 
             (this.percentageOfExceededTrades!=null &&
              this.percentageOfExceededTrades.equals(other.getPercentageOfExceededTrades()))) &&
            ((this.priceCheckCategory==null && other.getPriceCheckCategory()==null) || 
             (this.priceCheckCategory!=null &&
              this.priceCheckCategory.equals(other.getPriceCheckCategory()))) &&
            ((this.priceCheckCategoryChangeDate==null && other.getPriceCheckCategoryChangeDate()==null) || 
             (this.priceCheckCategoryChangeDate!=null &&
              this.priceCheckCategoryChangeDate.equals(other.getPriceCheckCategoryChangeDate()))) &&
            ((this.priceCheckCategoryChangedBy==null && other.getPriceCheckCategoryChangedBy()==null) || 
             (this.priceCheckCategoryChangedBy!=null &&
              this.priceCheckCategoryChangedBy.equals(other.getPriceCheckCategoryChangedBy()))) &&
            ((this.summitCategory==null && other.getSummitCategory()==null) || 
             (this.summitCategory!=null &&
              this.summitCategory.equals(other.getSummitCategory()))) &&
            ((this.tolerance==null && other.getTolerance()==null) || 
             (this.tolerance!=null &&
              this.tolerance.equals(other.getTolerance())));
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
        if (getCategory() != null) {
            _hashCode += getCategory().hashCode();
        }
        if (getNumberOfExceededTrades() != null) {
            _hashCode += getNumberOfExceededTrades().hashCode();
        }
        if (getNumberOfTrades() != null) {
            _hashCode += getNumberOfTrades().hashCode();
        }
        if (getPercentageOfExceededTrades() != null) {
            _hashCode += getPercentageOfExceededTrades().hashCode();
        }
        if (getPriceCheckCategory() != null) {
            _hashCode += getPriceCheckCategory().hashCode();
        }
        if (getPriceCheckCategoryChangeDate() != null) {
            _hashCode += getPriceCheckCategoryChangeDate().hashCode();
        }
        if (getPriceCheckCategoryChangedBy() != null) {
            _hashCode += getPriceCheckCategoryChangedBy().hashCode();
        }
        if (getSummitCategory() != null) {
            _hashCode += getSummitCategory().hashCode();
        }
        if (getTolerance() != null) {
            _hashCode += getTolerance().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PriceDeviationStatisticVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "PriceDeviationStatisticVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category");
        elemField.setXmlName(new javax.xml.namespace.QName("", "category"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfExceededTrades");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberOfExceededTrades"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfTrades");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numberOfTrades"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percentageOfExceededTrades");
        elemField.setXmlName(new javax.xml.namespace.QName("", "percentageOfExceededTrades"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceCheckCategory");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceCheckCategory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceCheckCategoryChangeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceCheckCategoryChangeDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceCheckCategoryChangedBy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "priceCheckCategoryChangedBy"));
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
        elemField.setFieldName("tolerance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tolerance"));
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
