/*
 * Created on Apr 2, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.model.impl.statistic;

import java.util.Calendar;

import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.model.impl.MandantImpl;

/**
 * @author WSY4148
 *
 */
public interface ReportSearchParams {
	/**
	 * Returns the mandant.
	 * @return MandantImpl
	 */
	public abstract MandantImpl getMandant();
	
    /**
     * Returns the fromDate.
     * @return Calendar
     */
    public abstract Calendar getFromDate();

    /**
     * Returns the toDate.
     * @return Calendar
     */
    public abstract Calendar getToDate();

    /**
     * Returns the job.
     * @return JobImpl
     */
    public abstract JobImpl[] getJobs();
}