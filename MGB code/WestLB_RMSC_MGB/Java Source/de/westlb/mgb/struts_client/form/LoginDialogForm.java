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
 * Form bean for the Sparkasse detail edit page.
 */
public class LoginDialogForm extends ActionForm {
    /**
     * 
     */
    private static final long serialVersionUID = -2347872805118770547L;
    private String osUser;
    private String user;
    private String password;
	
    /* (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void reset(ActionMapping arg0, HttpServletRequest request) {
        this.osUser = null;
        this.user = null;
        this.password = null;
    }

    /* (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		checkEmptyFields(user, errors, "User");
		checkEmptyFields(password, errors, "Password");
		return errors;
    }
    
    private void checkEmptyFields(String fieldValue, ActionErrors errs, String fieldName) {
        if (fieldValue == null || fieldValue.trim().length() == 0) {
            errs.add("error", new ActionMessage(MessageKeys.ERROR_FIELD_REQUIRED, fieldName));
        }
    }

    
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

    
    public String getOsUser() {
        return osUser;
    }

    
    public void setOsUser(String osUser) {
        this.osUser = osUser;
    }
}