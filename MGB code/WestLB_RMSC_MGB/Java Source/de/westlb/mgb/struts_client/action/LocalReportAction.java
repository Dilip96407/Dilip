/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */

package de.westlb.mgb.struts_client.action;



import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import de.westlb.mgb.struts_client.form.LocalReportRequestForm;


/**
 * 
 * This action selects the date for the report overview display.
 * 
 * @author WSY4148
 */
public class LocalReportAction extends LocalAbstractAction {

    protected static final Logger logger = Logger.getLogger(LocalReportAction.class);

    public LocalReportAction() {
        super();
    }
    
    @Override
    protected void setInitialReport(ActionForm form, String[] reports) {
        if (reports != null && reports.length > 0) {
            ((LocalReportRequestForm)form).setReportLocation(reports[0]);
        }
    }

}