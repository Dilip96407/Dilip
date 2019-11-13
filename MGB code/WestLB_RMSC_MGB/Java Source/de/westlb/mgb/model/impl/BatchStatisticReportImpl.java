/*
 * Copyright (c) 2016, Portigon Financial Services GmbH
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of Portigon Financial Services GmbH.
 */


package de.westlb.mgb.model.impl;



/**
 * Report configuration for automated E-Mail reports.
 */
public class BatchStatisticReportImpl extends EntityImpl {

    private static final long serialVersionUID = -6443366592441222416L;
    private String name;
    private String mailList;
    private String cron;
    private String sqlFileName;
    private String sqlText;
    private String sqlStartDate;
    private String sqlStopDate;
    private String sqlParam;
    private String outputFile;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailList() {
		return mailList;
	}
	public void setMailList(String mailList) {
		this.mailList = mailList;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	public String getSqlFileName() {
		return sqlFileName;
	}
	public void setSqlFileName(String sqlFileName) {
		this.sqlFileName = sqlFileName;
	}
	public String getSqlText() {
		return sqlText;
	}
	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}
	public String getSqlStartDate() {
		return sqlStartDate;
	}
	public void setSqlStartDate(String sqlStartDate) {
		this.sqlStartDate = sqlStartDate;
	}
	public String getSqlStopDate() {
		return sqlStopDate;
	}
	public void setSqlStopDate(String sqlStopDate) {
		this.sqlStopDate = sqlStopDate;
	}
	public String getSqlParam() {
		return sqlParam;
	}
	public void setSqlParam(String sqlParam) {
		this.sqlParam = sqlParam;
	}
	public String getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

    

}