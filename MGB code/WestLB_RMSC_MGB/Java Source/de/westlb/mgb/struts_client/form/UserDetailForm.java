/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */

package de.westlb.mgb.struts_client.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import de.westlb.mgb.struts_client.MessageKeys;

/**
 * Form bean for the User detail edit page.
 */
public class UserDetailForm extends ActionForm {

	private static final long serialVersionUID = -8984772293934786658L;

    public static final String NULL_STRING = "-----";

    public static final String REPORT_ADMIN_STRING = "ADMIN";

    private boolean admin;
    private boolean controller;
    private String email;
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String mandantCode;
    private String ntId;
    private String phone;
    private String report;
    private boolean systemAdmin;
    private boolean trader;
    private boolean userMaintainAdmin;
    private boolean reporter;
    private boolean readOnly;
	
    /* (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void reset(ActionMapping arg0, HttpServletRequest request) {
        this.admin = false;
        this.controller = false;
        this.systemAdmin = false;
        this.trader = false;
        this.userMaintainAdmin = false;
        this.reporter = false;
        this.readOnly = false;
        this.report = NULL_STRING;
    }

    /* (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		checkEmptyFields(email, errors, "email");
		checkEmptyFields(firstName, errors, "First Name");
		checkEmptyFields(lastName, errors, "Last Name");
		checkEmptyFields(mandantCode, errors, "Mandant");
		checkEmptyFields(ntId, errors, "NT-ID");
		return errors;				
    }
    
    private void checkEmptyFields(String fieldValue, ActionErrors errs, String fieldName) {
        if (fieldValue == null || fieldValue.trim().length() == 0) {
            errs.add("error", new ActionMessage(MessageKeys.ERROR_FIELD_REQUIRED, fieldName));
        }
    }

    /**
     * @return Returns the admin.
     */
    public boolean isAdmin() {
        return admin;
    }
    /**
     * @param admin The admin to set.
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    /**
     * @return Returns the controller.
     */
    public boolean isController() {
        return controller;
    }
    /**
     * @param controller The controller to set.
     */
    public void setController(boolean controller) {
        this.controller = controller;
    }
    /**
     * @return Returns the email.
     */
    public java.lang.String getEmail() {
        return email;
    }
    /**
     * @param email The email to set.
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }
    /**
     * @return Returns the employeeId.
     */
    public java.lang.Long getEmployeeId() {
        return employeeId;
    }
    /**
     * @param employeeId The employeeId to set.
     */
    public void setEmployeeId(java.lang.Long employeeId) {
        this.employeeId = employeeId;
    }
    /**
     * @return Returns the firstName.
     */
    public java.lang.String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName The firstName to set.
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }
    /**
     * @return Returns the lastName.
     */
    public java.lang.String getLastName() {
        return lastName;
    }
    /**
     * @param lastName The lastName to set.
     */
    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }
    /**
     * @return Returns the mandantCode.
     */
    public java.lang.String getMandantCode() {
        return mandantCode;
    }
    /**
     * @param mandantCode The mandantCode to set.
     */
    public void setMandantCode(java.lang.String mandantCode) {
        this.mandantCode = mandantCode;
    }
    /**
     * @return Returns the ntId.
     */
    public java.lang.String getNtId() {
        return ntId;
    }
    /**
     * @param ntId The ntId to set.
     */
    public void setNtId(java.lang.String ntId) {
        this.ntId = ntId;
    }
    /**
     * @return Returns the phone.
     */
    public java.lang.String getPhone() {
        return phone;
    }
    /**
     * @param phone The phone to set.
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }
    /**
     * @return Returns the systemAdmin.
     */
    public boolean isSystemAdmin() {
        return systemAdmin;
    }
    /**
     * @param systemAdmin The systemAdmin to set.
     */
    public void setSystemAdmin(boolean systemAdmin) {
        this.systemAdmin = systemAdmin;
    }
    /**
     * @return Returns the trader.
     */
    public boolean isTrader() {
        return trader;
    }
    /**
     * @param trader The trader to set.
     */
    public void setTrader(boolean trader) {
        this.trader = trader;
    }
    /**
     * @return Returns the userMaintainAdmin.
     */
    public boolean isUserMaintainAdmin() {
        return userMaintainAdmin;
    }
    /**
     * @param userMaintainAdmin The userMaintainAdmin to set.
     */
    public void setUserMaintainAdmin(boolean userMaintainAdmin) {
        this.userMaintainAdmin = userMaintainAdmin;
    }

    
    public String getReport() {
        return report;
    }

    
    public void setReport(String report) {
        this.report = report;
    }

    
    public boolean isReporter() {
        return reporter;
    }

    
    public void setReporter(boolean reporter) {
        this.reporter = reporter;
    }

    
    public boolean isReadOnly() {
        return readOnly;
    }

    
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
    
}