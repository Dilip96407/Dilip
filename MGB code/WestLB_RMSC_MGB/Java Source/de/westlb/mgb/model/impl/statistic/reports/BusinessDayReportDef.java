/*
 * Created on Apr 5, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.model.impl.statistic.reports;

import de.westlb.mgb.model.definition.StatisticReportNameDef;
import de.westlb.mgb.model.impl.TradeImpl;

/**
 * @author WSY4148
 *
 */
public class BusinessDayReportDef extends AbstractReportDef implements ReportDef {
	private final static String[] KEY_COLUMN_NAMES = new String[] {
			"trade.job.cobDayString",
	};
	
	private final static String[] ADD_KEY_COLUMN_NAMES = new String[] {
	};
	
	private final static String[] SUCCESSOR_REPORT_NAMES = new String[] {
		StatisticReportNameDef.JOB,
		StatisticReportNameDef.TRADE_TYPE,		
		StatisticReportNameDef.TRADE_CATEGORY_REPO		
	};

	
    /* (Kein Javadoc)
     * @see de.westlb.mgb.model.impl.statistic.reports.ReportDef#getKeyColumnNames()
     */
    @Override
    public String[] getKeyColumnNames() {
        return KEY_COLUMN_NAMES;
    }
    
    @Override
    public String[] getAddKeyColumnNames() {
    	return ADD_KEY_COLUMN_NAMES;
    }
    
    @Override
    public String getEntityName() {
        return TradeImpl.class.getName();
    }

    /* (Kein Javadoc)
     * @see de.westlb.mgb.model.impl.statistic.reports.ReportDef#getDrillDownReport()
     */
    @Override
    public String[] getSuccessorReportNames() {
        return SUCCESSOR_REPORT_NAMES;
    }

    @Override
    public String getHint() {
        return "";
    }
}
