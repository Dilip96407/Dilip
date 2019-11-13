/**
 * SessionInfoVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class SessionInfoVo  implements java.io.Serializable {
    private de.westlb.mgb.client.server.vo.EmployeeVo employee;

    private de.westlb.mgb.client.server.vo.MandantVo[] mandants;

    private de.westlb.mgb.client.server.vo.SourceSystemVo[] sourceSystems;

    public SessionInfoVo() {
    }

    public SessionInfoVo(
           de.westlb.mgb.client.server.vo.EmployeeVo employee,
           de.westlb.mgb.client.server.vo.MandantVo[] mandants,
           de.westlb.mgb.client.server.vo.SourceSystemVo[] sourceSystems) {
           this.employee = employee;
           this.mandants = mandants;
           this.sourceSystems = sourceSystems;
    }


    /**
     * Gets the employee value for this SessionInfoVo.
     * 
     * @return employee
     */
    public de.westlb.mgb.client.server.vo.EmployeeVo getEmployee() {
        return employee;
    }


    /**
     * Sets the employee value for this SessionInfoVo.
     * 
     * @param employee
     */
    public void setEmployee(de.westlb.mgb.client.server.vo.EmployeeVo employee) {
        this.employee = employee;
    }


    /**
     * Gets the mandants value for this SessionInfoVo.
     * 
     * @return mandants
     */
    public de.westlb.mgb.client.server.vo.MandantVo[] getMandants() {
        return mandants;
    }


    /**
     * Sets the mandants value for this SessionInfoVo.
     * 
     * @param mandants
     */
    public void setMandants(de.westlb.mgb.client.server.vo.MandantVo[] mandants) {
        this.mandants = mandants;
    }


    /**
     * Gets the sourceSystems value for this SessionInfoVo.
     * 
     * @return sourceSystems
     */
    public de.westlb.mgb.client.server.vo.SourceSystemVo[] getSourceSystems() {
        return sourceSystems;
    }


    /**
     * Sets the sourceSystems value for this SessionInfoVo.
     * 
     * @param sourceSystems
     */
    public void setSourceSystems(de.westlb.mgb.client.server.vo.SourceSystemVo[] sourceSystems) {
        this.sourceSystems = sourceSystems;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SessionInfoVo)) return false;
        SessionInfoVo other = (SessionInfoVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.employee==null && other.getEmployee()==null) || 
             (this.employee!=null &&
              this.employee.equals(other.getEmployee()))) &&
            ((this.mandants==null && other.getMandants()==null) || 
             (this.mandants!=null &&
              java.util.Arrays.equals(this.mandants, other.getMandants()))) &&
            ((this.sourceSystems==null && other.getSourceSystems()==null) || 
             (this.sourceSystems!=null &&
              java.util.Arrays.equals(this.sourceSystems, other.getSourceSystems())));
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
        if (getEmployee() != null) {
            _hashCode += getEmployee().hashCode();
        }
        if (getMandants() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMandants());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMandants(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSourceSystems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSourceSystems());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSourceSystems(), i);
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
        new org.apache.axis.description.TypeDesc(SessionInfoVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "SessionInfoVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("employee");
        elemField.setXmlName(new javax.xml.namespace.QName("", "employee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "EmployeeVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandants");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mandants"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "MandantVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceSystems");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sourceSystems"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "SourceSystemVo"));
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
