/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.struts_client.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.vo.JobSearchParamsVo;
import de.westlb.mgb.client.server.vo.JobVo;
import de.westlb.mgb.client.server.vo.MandantVo;
import de.westlb.mgb.client.server.vo.SessionInfoVo;
import de.westlb.mgb.client.server.vo.TradeSearchParamsVo;
import de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo;
import de.westlb.mgb.model.definition.JobStateDef;
import de.westlb.mgb.struts_client.RequestKeys;


/**
 * This action provides a list of all MGB jobs. Filters the last 14 days.
 * 
 * @author WSY4148 
 */
public class OpenJobsListAction extends MgbAction {
    /**
         *
         */
    public OpenJobsListAction() {
        super();
    }

    /* (Kein Javadoc)
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response) throws Exception {

        Mgb mgb = getMgbService(request, response, null);
        
        /* Search for all clients the user has access to. */
        SessionInfoVo sessionInfo = mgb.getSessionInfo();
        List<MandantVo> mandants = Arrays.asList(sessionInfo.getMandants());
        List<JobVo> jobList = new ArrayList<JobVo>();
        for(MandantVo mandant : mandants)
        {
           	Calendar cal = Calendar.getInstance();
        	cal.add(Calendar.DAY_OF_WEEK, -14);
            JobSearchParamsVo param = new JobSearchParamsVo();
            param.setMandant(mandant);
            param.setImportDaysFrom(cal);
            JobVo[] jobs = mgb.findAllJobs(param);
            for (JobVo job : jobs)
            {
            	if (!job.getStatus().equals(JobStateDef.JOB_CLOSED_STATUS) && !job.getStatus().startsWith(JobStateDef.JOB_IGNORE_STATUS))
            	{
            		int numOpen = 0;
            		if (job.getNumberOfTotalRecords() != 0) {
                    	TradeSearchParamsVo tradeParam = new TradeSearchParamsVo();
                    	tradeParam.setJobIds(new Long[] {job.getJobId()});
                    	tradeParam.setIsManualCheckRequired(true);
                        TradeSearchResultEntryVo[] trades = mgb.findTrades(tradeParam);
                        numOpen = trades.length;
            		}
                	job.setNumberOfOpenRecords(numOpen);
            		jobList.add(job);
            	}
            }
        
            Collections.sort(jobList, new Comparator<JobVo>() {

				@Override
				public int compare(JobVo o1, JobVo o2) {
					Calendar time1 = o1.getCob();
					Calendar time2 = o2.getCob();
					if (time1 != null && time2 != null)
					{
						return time2.compareTo(time1);
					} 
					else if (time1 == null && time2 != null)
					{
						return 1;
					}
					else if (time1 != null && time2 == null)
					{
						return -1;
					}
					else
					{
						Calendar importTime1 = o1.getStartLoadTime();
						Calendar importTime2 = o2.getStartLoadTime();
						return importTime2.compareTo(importTime1);						
					}
				}
			});
        }
        request.setAttribute(RequestKeys.OPEN_JOBS_LIST, jobList);
        return mapping.findForward("success");
    }
}