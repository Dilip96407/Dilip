/*
 * Created on Apr 8, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.model.impl.statistic.reports;

/**
 * @author WSY4148
 *
 */
public abstract class AbstractReportDef implements ReportDef {

    /* (Kein Javadoc)
     * @see de.westlb.mgb.model.impl.statistic.reports.ReportDef#getAddKeyColumnNames()
     */
    @Override
    public abstract String[] getAddKeyColumnNames();
    
    /* (Kein Javadoc)
     * @see de.westlb.mgb.model.impl.statistic.reports.ReportDef#getAddKeyCount()
     */
    @Override
    public int getAddKeyCount() {
    	return getAddKeyColumnNames() == null ? 0 : getAddKeyColumnNames().length;
    }

    /* (Kein Javadoc)
     * @see de.westlb.mgb.model.impl.statistic.reports.ReportDef#getEntityName()
     */
    @Override
    public abstract String getEntityName();

    /* (Kein Javadoc)
     * @see de.westlb.mgb.model.impl.statistic.reports.ReportDef#getKeyColumnNames()
     */
    @Override
    public abstract String[] getKeyColumnNames();

    /* (Kein Javadoc)
     * @see de.westlb.mgb.model.impl.statistic.reports.ReportDef#getKeyCount()
     */
    @Override
    public int getKeyCount() {
		return getKeyColumnNames() == null ? 0 : getKeyColumnNames().length;
    }

    /* (Kein Javadoc)
     * @see de.westlb.mgb.model.impl.statistic.reports.ReportDef#getSuccessorReportName()
     */
    @Override
    public abstract String[] getSuccessorReportNames();

    @Override
    public abstract String getHint();

}
