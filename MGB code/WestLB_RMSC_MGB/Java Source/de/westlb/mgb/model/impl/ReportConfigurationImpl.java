/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.model.impl;



/**
 * @author Persistent class representing a Mapping of instrument to a bloomberg request ticker.
 */
public class ReportConfigurationImpl extends EntityImpl {

    private static final long serialVersionUID = -6443366592441222416L;
    private String reportId;
    private String clientId;
    private String configuration;

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportId() {
        return reportId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}