/*
 */
package de.westlb.mgb.model.impl.statistic.reports;

import de.westlb.mgb.model.definition.StatisticReportNameDef;
import de.westlb.mgb.model.impl.TradeImpl;

/**
 */
public class InstrumentReportDef extends AbstractReportDef implements ReportDef
{
    private final static String[] KEY_COLUMN_NAMES = new String[]{
        "trade.instrument.instrumentName", 
    };

    private final static String[] ADD_KEY_COLUMN_NAMES = new String[]{
        "trade.instrument.priceCheckCategory.priceTolerancePercent",
        "trade.instrument.priceCheckCategory.priceToleranceAbsolute",
    };

    private final static String[] SUCCESSOR_REPORT_NAMES = new String[]{
        StatisticReportNameDef.BUSINESS_DAY // hm...
    };

    @Override
    public String[] getKeyColumnNames()
    {
        return KEY_COLUMN_NAMES;
    }

    @Override
    public String[] getAddKeyColumnNames()
    {
        return ADD_KEY_COLUMN_NAMES;
    }

    @Override
    public String getEntityName()
    {
        return TradeImpl.class.getName();
    }

    @Override
    public String[] getSuccessorReportNames()
    {
        return SUCCESSOR_REPORT_NAMES;
    }

    @Override
    public String getHint()
    {
        return " left outer join trade.instrument.priceCheckCategory ";
    }

}
