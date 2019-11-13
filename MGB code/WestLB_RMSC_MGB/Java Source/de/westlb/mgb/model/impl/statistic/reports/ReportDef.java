/*
 * Created on Apr 2, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.model.impl.statistic.reports;

/**
 * @author WSY4148
 *
 */
public interface ReportDef {
	public String getEntityName();
	public String[] getAddKeyColumnNames();
	public String[] getKeyColumnNames();
	public String[] getSuccessorReportNames();
	public int getKeyCount();
	public int getAddKeyCount();
    public String getHint();

}
