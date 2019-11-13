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
import de.westlb.mgb.struts_client.form.converter.CalendarConverter;
import de.westlb.mgb.util.DateTimeUtils;

/**
 * Form bean for the Local Check.
 */
public class LocalCheckRequestForm extends ActionForm {

    public LocalCheckRequestForm() {
        super();
        init();
    }

    private static final long serialVersionUID = 1L;

    private String fromDate;

    private String toDate;

    private String reportLocation;

    private void init() {
        // initialize with last business day, but not today
        String initDate = new CalendarConverter().objectToString(DateTimeUtils.lastBusinessCal(DateTimeUtils.yesterdayCal()));
        this.fromDate = initDate;
        this.toDate = initDate;
        this.reportLocation = null;
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
        checkDateField(fromDate, errors, "FromDate");
        checkDateField(toDate, errors, "ToDate");
        return errors;
    }

    private void checkDateField(String dateValue, ActionErrors errors, String fieldName) {
        if (StringUtils.isNotEmpty(dateValue)) {
            if (!new CalendarConverter().isValid(dateValue)) {
                errors.add("error", new ActionMessage(MessageKeys.ERROR_INVALID_DATE_FIELD, fieldName));
            }
        } else {
            errors.add("error", new ActionMessage(MessageKeys.ERROR_FIELD_REQUIRED, fieldName));
        }
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }

    public String getReportLocation() {
        return reportLocation;
    }

}