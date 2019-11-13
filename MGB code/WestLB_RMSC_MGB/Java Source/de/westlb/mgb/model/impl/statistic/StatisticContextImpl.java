/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.model.impl.statistic;

import de.westlb.mgb.model.impl.statistic.reports.ReportDef;

/**
 * This class contains information about one element in the drill down chain. (Report name and
 * selected line)
 */
public class StatisticContextImpl {
    private String reportName;
    private Object[] keyValues;
    private Object[] addKeyValues;

    /**
     * Gets the values of the key columns of the selected line.
     */
    public Object[] getKeyValues() {
        return keyValues;
    }

    /**
     * Sets the values of the key columns of the selected line.
     * 
     * @param Values of the Key columns.
     */
    public void setKeyValues(Object[] objects) {
        keyValues = objects;
    }
    /**
     * Gets the report name.
     * @return
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * Sets the report name.
     * @param report name.
     */
    public void setReportName(String string) {
        reportName = string;
    }
	
	public ReportDef getReportDef() {
		return StatisticReportParamsImpl.getReportDef(getReportName());
	}
    /**
     * @return
     */
    public Object[] getAddKeyValues() {
        return addKeyValues;
    }

    /**
     * @param objects
     */
    public void setAddKeyValues(Object[] objects) {
        addKeyValues = objects;
    }

}