/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */

package de.westlb.mgb.struts_client.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import de.westlb.mgb.struts_client.MessageKeys;

/**
 * Form bean for the Local Check.
 */
public class LocalCheckCommentForm extends ActionForm {

    public LocalCheckCommentForm() {
        super();
        init();
    }

    private static final long serialVersionUID = 1L;

    private Long id;

    private String comment;

    private String mandantCode;

    private void init() {
        id=null;
        comment=null;
        mandantCode=null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.
     * ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void reset(ActionMapping arg0, HttpServletRequest request) {
        init();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.apache.struts.action.ActionForm#validate(org.apache.struts.action
     * .ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        checkDateField(id, errors, "id");
        checkDateField(mandantCode, errors, "mandantCode");
        return errors;
    }

    private void checkDateField(String fieldValue, ActionErrors errors, String fieldName) {
        if (StringUtils.isEmpty(fieldValue)) {
             errors.add("error", new ActionMessage(MessageKeys.ERROR_FIELD_REQUIRED, fieldName));
        }
    }

    private void checkDateField(Long fieldValue, ActionErrors errors, String fieldName) {
        if (fieldValue.longValue() <= 0) {
             errors.add("error", new ActionMessage(MessageKeys.ERROR_FIELD_REQUIRED, fieldName));
        }
    }
    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getComment() {
        return comment;
    }

    
    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setMandantCode(String mandantCode) {
        this.mandantCode = mandantCode;
    }

    public String getMandantCode() {
        return mandantCode;
    }

}