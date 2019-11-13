/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */

package de.westlb.mgb.struts_client.form;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import de.westlb.mgb.struts_client.MessageKeys;
import de.westlb.mgb.struts_client.form.converter.CalendarConverter;
import de.westlb.mgb.struts_client.form.converter.TimeConverter;
import de.westlb.mgb.util.DateTimeUtils;

/**
 * Form bean for the Local Report.
 */
public class LocalReportRequestForm extends ActionForm {

    public final static String REPORT_ALL = "REPORT_ALL";
    public final static String REPORT_MAN_CHECK = "REPORT_MAN_CHECK";

    public final static String SELECT_ALL_CLIENTS = "SELECT_ALL_CLIENTS";
    public final static String SELECT_CONFIGURED_CLIENTS = "SELECT_CONFIGURED_CLIENTS";

    public final static String MARKET_DATA = "MARKET_DATA";
    public final static String NO_MARKET_DATA = "NO_MARKET_DATA";
    
    public final static String USE_JOB_CREATION_DATE = "USE_JOB_CREATION_DATE";
    public final static String USE_JOB_COB_DATE = "USE_JOB_COB_DATE";

    public LocalReportRequestForm() {
        super();
        init();
    }

    private static final long serialVersionUID = 2L;

    private String fromDate;
    
    private String fromTime;

    private String toDate;
    
    private String toTime;
    
    private String reportType;

    private String marketData;

    private String reportLocation;
    
    private String useJobCreationDate;

    private void init() {
        // initialize with last business day, but not today
        String initDate = new CalendarConverter().objectToString(DateTimeUtils.lastBusinessCal(DateTimeUtils.yesterdayCal()));
        
        this.setFromDate(initDate);
        this.setToDate(initDate);

        Calendar initCal=Calendar.getInstance();
        initCal.set(Calendar.HOUR_OF_DAY,20);
        initCal.set(Calendar.MINUTE,0);
        initCal.set(Calendar.SECOND,0);
        String initFromTime=new TimeConverter().objectToString(initCal);
        this.setFromTime(initFromTime);

        Calendar initToCal=Calendar.getInstance();
        initToCal.set(Calendar.HOUR_OF_DAY,19);
        initToCal.set(Calendar.MINUTE,59);
        initToCal.set(Calendar.SECOND,59);
        String initToTime=new TimeConverter().objectToString(initToCal);
        this.setToTime(initToTime);

        this.setReportType(REPORT_ALL);
        this.setUseJobCreationDate(USE_JOB_COB_DATE);
        this.setMarketData(MARKET_DATA);
        this.setReportLocation(null);
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
        if(LocalReportRequestForm.USE_JOB_CREATION_DATE.equals(getUseJobCreationDate()))
        {
            checkTimeField(fromTime,errors,"FromTime");
            checkTimeField(toTime,errors,"ToTime");
        }
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

    private void checkTimeField(String timeValue, ActionErrors errors, String fieldName) {
        if (StringUtils.isNotEmpty(timeValue)) {
            if (!new TimeConverter().isValid(timeValue)) {
                errors.add("error", new ActionMessage(MessageKeys.ERROR_INVALID_TIME_FIELD, fieldName));
            }
        } else {
            errors.add("error", new ActionMessage(MessageKeys.ERROR_FIELD_REQUIRED, fieldName));
        }
    }

    public String getFromTime()
    {
        return fromTime;
    }

    public void setFromTime(String fromTime)
    {
        this.fromTime = fromTime;
    }

    public String getToTime()
    {
        return toTime;
    }

    public void setToTime(String toTime)
    {
        this.toTime = toTime;
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

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }

    public String getReportLocation() {
        return reportLocation;
    }

    public void setMarketData(String marketData) {
        this.marketData = marketData;
    }

    public String getMarketData() {
        return marketData;
    }
    public String getUseJobCreationDate()
    {
        return useJobCreationDate;
    }

    public void setUseJobCreationDate(String useJobCreationDate)
    {
        this.useJobCreationDate = useJobCreationDate;
    }

}