/**
 * EmployeeVo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server.vo;

public class EmployeeVo  implements java.io.Serializable {
    private boolean admin;

    private boolean controller;

    private java.lang.String email;

    private java.lang.Long employeeId;

    private java.lang.String firstName;

    private java.lang.String lastName;

    private java.lang.String mandantCode;

    private java.lang.String ntId;

    private java.lang.String phone;

    private boolean readOnly;

    private boolean reporter;

    private boolean sparkassenAdmin;

    private boolean systemAdmin;

    private boolean trader;

    private de.westlb.mgb.client.server.vo.TraderVo[] traderIds;

    private boolean userMaintainAdmin;

    private java.lang.String report;

    private java.lang.String mandantName;

    public EmployeeVo() {
    }

    public EmployeeVo(
           boolean admin,
           boolean controller,
           java.lang.String email,
           java.lang.Long employeeId,
           java.lang.String firstName,
           java.lang.String lastName,
           java.lang.String mandantCode,
           java.lang.String ntId,
           java.lang.String phone,
           boolean readOnly,
           boolean reporter,
           boolean sparkassenAdmin,
           boolean systemAdmin,
           boolean trader,
           de.westlb.mgb.client.server.vo.TraderVo[] traderIds,
           boolean userMaintainAdmin,
           java.lang.String report,
           java.lang.String mandantName) {
           this.admin = admin;
           this.controller = controller;
           this.email = email;
           this.employeeId = employeeId;
           this.firstName = firstName;
           this.lastName = lastName;
           this.mandantCode = mandantCode;
           this.ntId = ntId;
           this.phone = phone;
           this.readOnly = readOnly;
           this.reporter = reporter;
           this.sparkassenAdmin = sparkassenAdmin;
           this.systemAdmin = systemAdmin;
           this.trader = trader;
           this.traderIds = traderIds;
           this.userMaintainAdmin = userMaintainAdmin;
           this.report = report;
           this.mandantName = mandantName;
    }


    /**
     * Gets the admin value for this EmployeeVo.
     * 
     * @return admin
     */
    public boolean isAdmin() {
        return admin;
    }


    /**
     * Sets the admin value for this EmployeeVo.
     * 
     * @param admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }


    /**
     * Gets the controller value for this EmployeeVo.
     * 
     * @return controller
     */
    public boolean isController() {
        return controller;
    }


    /**
     * Sets the controller value for this EmployeeVo.
     * 
     * @param controller
     */
    public void setController(boolean controller) {
        this.controller = controller;
    }


    /**
     * Gets the email value for this EmployeeVo.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this EmployeeVo.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the employeeId value for this EmployeeVo.
     * 
     * @return employeeId
     */
    public java.lang.Long getEmployeeId() {
        return employeeId;
    }


    /**
     * Sets the employeeId value for this EmployeeVo.
     * 
     * @param employeeId
     */
    public void setEmployeeId(java.lang.Long employeeId) {
        this.employeeId = employeeId;
    }


    /**
     * Gets the firstName value for this EmployeeVo.
     * 
     * @return firstName
     */
    public java.lang.String getFirstName() {
        return firstName;
    }


    /**
     * Sets the firstName value for this EmployeeVo.
     * 
     * @param firstName
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }


    /**
     * Gets the lastName value for this EmployeeVo.
     * 
     * @return lastName
     */
    public java.lang.String getLastName() {
        return lastName;
    }


    /**
     * Sets the lastName value for this EmployeeVo.
     * 
     * @param lastName
     */
    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }


    /**
     * Gets the mandantCode value for this EmployeeVo.
     * 
     * @return mandantCode
     */
    public java.lang.String getMandantCode() {
        return mandantCode;
    }


    /**
     * Sets the mandantCode value for this EmployeeVo.
     * 
     * @param mandantCode
     */
    public void setMandantCode(java.lang.String mandantCode) {
        this.mandantCode = mandantCode;
    }


    /**
     * Gets the ntId value for this EmployeeVo.
     * 
     * @return ntId
     */
    public java.lang.String getNtId() {
        return ntId;
    }


    /**
     * Sets the ntId value for this EmployeeVo.
     * 
     * @param ntId
     */
    public void setNtId(java.lang.String ntId) {
        this.ntId = ntId;
    }


    /**
     * Gets the phone value for this EmployeeVo.
     * 
     * @return phone
     */
    public java.lang.String getPhone() {
        return phone;
    }


    /**
     * Sets the phone value for this EmployeeVo.
     * 
     * @param phone
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }


    /**
     * Gets the readOnly value for this EmployeeVo.
     * 
     * @return readOnly
     */
    public boolean isReadOnly() {
        return readOnly;
    }


    /**
     * Sets the readOnly value for this EmployeeVo.
     * 
     * @param readOnly
     */
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }


    /**
     * Gets the reporter value for this EmployeeVo.
     * 
     * @return reporter
     */
    public boolean isReporter() {
        return reporter;
    }


    /**
     * Sets the reporter value for this EmployeeVo.
     * 
     * @param reporter
     */
    public void setReporter(boolean reporter) {
        this.reporter = reporter;
    }


    /**
     * Gets the sparkassenAdmin value for this EmployeeVo.
     * 
     * @return sparkassenAdmin
     */
    public boolean isSparkassenAdmin() {
        return sparkassenAdmin;
    }


    /**
     * Sets the sparkassenAdmin value for this EmployeeVo.
     * 
     * @param sparkassenAdmin
     */
    public void setSparkassenAdmin(boolean sparkassenAdmin) {
        this.sparkassenAdmin = sparkassenAdmin;
    }


    /**
     * Gets the systemAdmin value for this EmployeeVo.
     * 
     * @return systemAdmin
     */
    public boolean isSystemAdmin() {
        return systemAdmin;
    }


    /**
     * Sets the systemAdmin value for this EmployeeVo.
     * 
     * @param systemAdmin
     */
    public void setSystemAdmin(boolean systemAdmin) {
        this.systemAdmin = systemAdmin;
    }


    /**
     * Gets the trader value for this EmployeeVo.
     * 
     * @return trader
     */
    public boolean isTrader() {
        return trader;
    }


    /**
     * Sets the trader value for this EmployeeVo.
     * 
     * @param trader
     */
    public void setTrader(boolean trader) {
        this.trader = trader;
    }


    /**
     * Gets the traderIds value for this EmployeeVo.
     * 
     * @return traderIds
     */
    public de.westlb.mgb.client.server.vo.TraderVo[] getTraderIds() {
        return traderIds;
    }


    /**
     * Sets the traderIds value for this EmployeeVo.
     * 
     * @param traderIds
     */
    public void setTraderIds(de.westlb.mgb.client.server.vo.TraderVo[] traderIds) {
        this.traderIds = traderIds;
    }


    /**
     * Gets the userMaintainAdmin value for this EmployeeVo.
     * 
     * @return userMaintainAdmin
     */
    public boolean isUserMaintainAdmin() {
        return userMaintainAdmin;
    }


    /**
     * Sets the userMaintainAdmin value for this EmployeeVo.
     * 
     * @param userMaintainAdmin
     */
    public void setUserMaintainAdmin(boolean userMaintainAdmin) {
        this.userMaintainAdmin = userMaintainAdmin;
    }


    /**
     * Gets the report value for this EmployeeVo.
     * 
     * @return report
     */
    public java.lang.String getReport() {
        return report;
    }


    /**
     * Sets the report value for this EmployeeVo.
     * 
     * @param report
     */
    public void setReport(java.lang.String report) {
        this.report = report;
    }


    /**
     * Gets the mandantName value for this EmployeeVo.
     * 
     * @return mandantName
     */
    public java.lang.String getMandantName() {
        return mandantName;
    }


    /**
     * Sets the mandantName value for this EmployeeVo.
     * 
     * @param mandantName
     */
    public void setMandantName(java.lang.String mandantName) {
        this.mandantName = mandantName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EmployeeVo)) return false;
        EmployeeVo other = (EmployeeVo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.admin == other.isAdmin() &&
            this.controller == other.isController() &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.employeeId==null && other.getEmployeeId()==null) || 
             (this.employeeId!=null &&
              this.employeeId.equals(other.getEmployeeId()))) &&
            ((this.firstName==null && other.getFirstName()==null) || 
             (this.firstName!=null &&
              this.firstName.equals(other.getFirstName()))) &&
            ((this.lastName==null && other.getLastName()==null) || 
             (this.lastName!=null &&
              this.lastName.equals(other.getLastName()))) &&
            ((this.mandantCode==null && other.getMandantCode()==null) || 
             (this.mandantCode!=null &&
              this.mandantCode.equals(other.getMandantCode()))) &&
            ((this.ntId==null && other.getNtId()==null) || 
             (this.ntId!=null &&
              this.ntId.equals(other.getNtId()))) &&
            ((this.phone==null && other.getPhone()==null) || 
             (this.phone!=null &&
              this.phone.equals(other.getPhone()))) &&
            this.readOnly == other.isReadOnly() &&
            this.reporter == other.isReporter() &&
            this.sparkassenAdmin == other.isSparkassenAdmin() &&
            this.systemAdmin == other.isSystemAdmin() &&
            this.trader == other.isTrader() &&
            ((this.traderIds==null && other.getTraderIds()==null) || 
             (this.traderIds!=null &&
              java.util.Arrays.equals(this.traderIds, other.getTraderIds()))) &&
            this.userMaintainAdmin == other.isUserMaintainAdmin() &&
            ((this.report==null && other.getReport()==null) || 
             (this.report!=null &&
              this.report.equals(other.getReport()))) &&
            ((this.mandantName==null && other.getMandantName()==null) || 
             (this.mandantName!=null &&
              this.mandantName.equals(other.getMandantName())));
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
        _hashCode += (isAdmin() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isController() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getEmployeeId() != null) {
            _hashCode += getEmployeeId().hashCode();
        }
        if (getFirstName() != null) {
            _hashCode += getFirstName().hashCode();
        }
        if (getLastName() != null) {
            _hashCode += getLastName().hashCode();
        }
        if (getMandantCode() != null) {
            _hashCode += getMandantCode().hashCode();
        }
        if (getNtId() != null) {
            _hashCode += getNtId().hashCode();
        }
        if (getPhone() != null) {
            _hashCode += getPhone().hashCode();
        }
        _hashCode += (isReadOnly() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isReporter() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isSparkassenAdmin() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isSystemAdmin() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isTrader() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTraderIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTraderIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTraderIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += (isUserMaintainAdmin() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getReport() != null) {
            _hashCode += getReport().hashCode();
        }
        if (getMandantName() != null) {
            _hashCode += getMandantName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EmployeeVo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "EmployeeVo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("admin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "admin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("controller");
        elemField.setXmlName(new javax.xml.namespace.QName("", "controller"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
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
        elemField.setFieldName("firstName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "firstName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandantCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mandantCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ntId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ntId"));
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
        elemField.setFieldName("readOnly");
        elemField.setXmlName(new javax.xml.namespace.QName("", "readOnly"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reporter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reporter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sparkassenAdmin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sparkassenAdmin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("systemAdmin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "systemAdmin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trader");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("traderIds");
        elemField.setXmlName(new javax.xml.namespace.QName("", "traderIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://vo.server.client.mgb.westlb.de", "TraderVo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userMaintainAdmin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userMaintainAdmin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("report");
        elemField.setXmlName(new javax.xml.namespace.QName("", "report"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandantName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mandantName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
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
