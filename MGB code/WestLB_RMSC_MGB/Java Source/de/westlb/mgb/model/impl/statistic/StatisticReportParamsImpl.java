/*
 * Created on Apr 5, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.model.impl.statistic;

import java.util.Calendar;

import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.model.impl.statistic.reports.ReportDef;

/**
 * @author WSY4148
 *
 */
public class StatisticReportParamsImpl {
	private String reportName;
	private MandantImpl mandant;
	private StatisticContextImpl[] context;
	private Calendar fromDate;
	private Calendar toDate;	
	private JobImpl[] jobs;
//	private ReportDef cachedReportDef;
//	private String cachedEntityName;


    /**
     * @return
     */
    public Calendar getFromDate() {
        return fromDate;
    }

    /**
     * @return
     */
    public JobImpl[] getJobs() {
        return jobs;
    }

    /**
     * @return
     */
    public MandantImpl getMandant() {
        return mandant;
    }

    /**
     * @return
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * @return
     */
    public Calendar getToDate() {
        return toDate;
    }

    /**
     * @param calendar
     */
    public void setFromDate(Calendar calendar) {
        fromDate = calendar;
    }

    /**
     * @param impls
     */
    public void setJobs(JobImpl[] impls) {
        jobs = impls;
    }

    /**
     * @param impl
     */
    public void setMandant(MandantImpl impl) {
        mandant = impl;
    }

    /**
     * @param string
     */
    public void setReportName(String string) {
        reportName = string;
    }

    /**
     * @param calendar
     */
    public void setToDate(Calendar calendar) {
        toDate = calendar;
    }

    /**
     * @return
     */
    public StatisticContextImpl[] getContext() {
        return context;
    }

    /**
     * @param impls
     */
    public void setContext(StatisticContextImpl[] context) {
        this.context = context;
    }
    
    public String calculateEntityName() {
//    	if (cachedEntityName != null) {
//    		return cachedEntityName;
//    	}
    	
    	String entityName = getReportDef().getEntityName();
    	try {
	    	if (context != null && context.length > 0) {
	    		Class<?> c = Class.forName( getReportDef().getEntityName());
	    	 	for (int i=0; i < context.length; i++) {
	    	 		Class<?> c1 = Class.forName(context[i].getReportDef().getEntityName());
	    	 		if (c.isAssignableFrom(c1)) {
	    	 			c = c1;
	    	 			entityName = context[i].getReportDef().getEntityName();
	    	 		}
	    	 	}
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entityName;
    }
    
    public ReportDef getReportDef() {
//    	if (cachedReportDef != null) {
//    		return cachedReportDef;
//    	}
    	return getReportDef(getReportName());
    }
    
    public static ReportDef getReportDef(String reportName) {
		ReportDef reportDef  = null;
		try {
			reportDef = (ReportDef) Class.forName(reportName).newInstance();
		} catch (Exception e1) {
			throw new IllegalArgumentException("Illegal Report Name " + reportName);
		}
		
		return reportDef;
    }
     
}
