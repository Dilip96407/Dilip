package de.westlb.mgb.model.impl.statistic;

import java.util.ArrayList;
import java.util.Iterator;

import de.westlb.mgb.model.impl.statistic.reports.ReportDef;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;


/**
 * <p>
 * A variant of {@link StatisticReportFactoryImpl} that uses a GROUP BY GROUPING
 * SETS-like layout (individual states in extra rows). To achieve that layout,
 * the various grouping expressions are issued as separate HQL statements as I'm
 * not sure about an easy way to use the GROUPING SETS syntax directly. (Note
 * that is <em>is</em> possible to use GROUP BY CUBE in HQL, but that would
 * require sorting out a couple of additional NULL values, so I'm not sure about
 * the benefit).
 * </p>
 * 
 * <p>
 * This report does not support drill-down (in terms of successor reports) at
 * the moment, I think it's not going to be requested and it would be a little
 * harder to implement too as it would require an extended context class, which
 * wouldn't right away maintain compatibility to the regular report view.
 * </p>
 * <p>
 * Drill-down into trades is probably not as much effort, but again, without a
 * user request I see little point in implementing it right now. &ndash; RS
 * 2014-10-08
 * </p>
 * <p>
 * This class makes use of
 * {@link StatisticReportFactoryImpl.TemplateQueryBuilder} to parse query
 * templates.
 * </p>
 */
public class StatisticCubeReportFactoryImpl
{
    /* We'll group on the state's short description, not the code. If two
     * distinct states have the same names, they will be shown as one. That's
     * how the previous SQL report worked, too. */
    /* All of the below statements need to be in the template format supported
     * by StatisticReportFactoryImpl.TemplateQueryBuilder. */
    /**1st part of SQL that groups by state codes, for trades that do not
     * require a manual check */
    private static final String SQL_AUTO_STATES = "select <keyCols,>  "
            + "trade.currentAutoStateHistEntry.automaticState.shortDescription, "
            + "trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired, "
            + "count(*) "
            + "from <entity> trade ";
    /**2nd part of SQL that groups by state codes, for trades that do not
     * require a manual check */
    private static final String SQL2_AUTO_STATES = " and "
            + "trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired=false "
            + "group by <keyCols,>  "
            + "trade.currentAutoStateHistEntry.automaticState.shortDescription, "
            + "trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired";
    
    /**1st part of SQL that groups by state codes, for trades that do
     * require a manual check which has already been done. */
    private static final String SQL_MAN_STATES = "select <keyCols,>  "
            + "trade.currentManualStateHistEntry.manualState.shortDescription, "
            + "trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired, "
            + "count(*) "
            + "from <entity> trade";
    /**2nd part of SQL that groups by state codes, for trades that do
     * require a manual check which has already been done. */
    private static final String SQL2_MAN_STATES = " and "
            + "trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired=true "
            + "group by <keyCols,>  "
            + "trade.currentManualStateHistEntry.manualState.shortDescription, "
            + "trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired";
    
    /**1st part of SQL that groups by state codes, for trades that do
     * require a manual check which has <em>not</em> already been done. */
    private static final String SQL_PENDING_MAN_STATES = "select <keyCols,>  "
            + "trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired, "
            + "count(*) "
            + "from <entity> trade";
    /**2nd part of SQL that groups by state codes, for trades that do
     * require a manual check which has <em>not</em> already been done. */
    private static final String SQL2_PENDING_MAN_STATES = " and "
            + "trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired=true and "
            + "trade.currentManualStateHistEntry is null "
            + "group by <keyCols,>  "
            + "trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired";
    
    /**1st part of SQL that groups by whether a manual check is required or not. */
    private static final String SQL_AUTO_STATES_GROUP_MANUALYN = "select <keyCols,>  "
            + "trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired, "
            + "count(*) "
            + "from <entity> trade";
    /**2nd part of SQL that groups by whether a manual check is required or not. */
    private static final String SQL2_AUTO_STATES_GROUP_MANUALYN = " group by <keyCols,>  "
            + "trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired";
    
    /**1st part of SQL that groups only by key cols, i. e. shows overall totals. */
    private static final String SQL_AUTO_STATES_GROUP_KEYCOLS = "select <keyCols,>  "
            + "count(*) from <entity> trade";
    /**2nd part of SQL that groups only by key cols, i. e. shows overall totals. */
    private static final String SQL2_AUTO_STATES_GROUP_KEYCOLS = " group by <keyCols>";

    private StatisticReportFactoryImpl.TemplateQueryBuilder templateQueryBuilder;

    public StatisticCubeReportFactoryImpl(Session sess) 
    {
        templateQueryBuilder=new StatisticReportFactoryImpl.TemplateQueryBuilder(sess);
    }

    private Query buildStatisticQuery(String template, String hint, StatisticReportParamsImpl params, String[] addClauseTokens, String endClause)
    throws PersistenceException
    {
        return templateQueryBuilder.buildStatisticQuery(template,hint,params,addClauseTokens,endClause);
    }

    private StatisticCubeRowImpl getRow(ReportDef reportDef, Object[] sqlResultRow) {
        ArrayList<Object> key = new ArrayList<Object>();

        for (int i = 0; i < reportDef.getKeyCount(); i++) {
            key.add(sqlResultRow[i]);
        }
        StatisticCubeRowImpl row = new StatisticCubeRowImpl();
        Object[] values = new Object[reportDef.getKeyCount()];
		for (int i = 0; i < reportDef.getKeyCount(); i++) {
			values[i] = sqlResultRow[i];
		}    
		row.setKeyValues(values); 
		
		values = new Object[reportDef.getAddKeyCount()];
		for (int i = 0; i < reportDef.getAddKeyCount(); i++) {
			values[i] = sqlResultRow[i+reportDef.getKeyCount()];
		}    
		row.setAddValues(values); 

        return row;
    }
    
    /**
     * Creates a statistic report. Uses Hibernate aggregation to calculate the total values.
     *
     * @param params Contains the name of the report and specifies the subset of data for the
     *        query.
     *
     * @return Report data.
     *
     * @throws PersistenceException If something unexpected happens while executing  Hibernate
     *         queries.
     * @throws IllegalArgumentException If the mandant property of params is null.
     */
    public StatisticCubeReportImpl createReport(StatisticReportParamsImpl params)
    throws PersistenceException 
    {
        ArrayList<StatisticCubeRowImpl> rows=new ArrayList<StatisticCubeRowImpl>();

        if ((params == null) || (params.getMandant() == null)) {
            throw new IllegalArgumentException("The mandant property has to be set for getReport");
        }        
        
        ReportDef reportDef = params.getReportDef();

        int idxKeyCols = 0;
        int keyColCnt = reportDef.getKeyCount();
        int idxAddCols = idxKeyCols + keyColCnt;
        int addColCnt = reportDef.getAddKeyCount();
        int idxData = idxAddCols + addColCnt;

        /*
         * pt 1 -- group by automatic states, for trades that do not require manual check
         */
        {
            Iterator<Object[]> iterator = buildStatisticQuery(SQL_AUTO_STATES,
                    reportDef.getHint(),params,null,SQL2_AUTO_STATES).list()
                    .iterator();
            
            while (iterator.hasNext()) {
                Object[] sqlResultRow = iterator.next();
                StatisticCubeRowImpl reportRow = getRow(reportDef, sqlResultRow);
                String stateCode = (String) sqlResultRow[idxData];
                Boolean manualCheckRequired = (Boolean)sqlResultRow[idxData + 1];
                long stateCount = ((Long) sqlResultRow[idxData + 2]).longValue();
                reportRow.setNoTradesTotal(stateCount);
                reportRow.setManualCheckRequired(manualCheckRequired);
                reportRow.setState(stateCode);
                rows.add(reportRow);
            }
        }
        /*
         * pt 2 -- group by manual states, for trades that do require manual check
         * which has been completed
         */
        {
            Iterator<Object[]> iterator = buildStatisticQuery(SQL_MAN_STATES,
                    reportDef.getHint(),params,null,SQL2_MAN_STATES).list()
                    .iterator();
            
            while (iterator.hasNext()) {
                Object[] sqlResultRow = iterator.next();
                StatisticCubeRowImpl reportRow = getRow(reportDef, sqlResultRow);
                String stateCode = (String) sqlResultRow[idxData];
                Boolean manualCheckRequired = (Boolean)sqlResultRow[idxData + 1];
                long stateCount = ((Long) sqlResultRow[idxData + 2]).longValue();
                reportRow.setNoTradesTotal(stateCount);
                reportRow.setManualCheckRequired(manualCheckRequired);
                reportRow.setState(stateCode);
                rows.add(reportRow);
            }
        }
        /*
         * pt 3 -- group by manual states, for trades that do require manual check
         * which has not been completed
         */
        {
            Iterator<Object[]> iterator = buildStatisticQuery(SQL_PENDING_MAN_STATES,
                    reportDef.getHint(),params,null,SQL2_PENDING_MAN_STATES).list()
                    .iterator();
            
            while (iterator.hasNext()) {
                Object[] sqlResultRow = iterator.next();
                StatisticCubeRowImpl reportRow = getRow(reportDef, sqlResultRow);
                Boolean manualCheckRequired = (Boolean)sqlResultRow[idxData];
                long stateCount = ((Long) sqlResultRow[idxData + 1]).longValue();
                reportRow.setNoTradesTotal(stateCount);
                reportRow.setManualCheckRequired(manualCheckRequired);
                /* No manual state set yet, set it to empty string */
                reportRow.setState("");
                rows.add(reportRow);
            }
        }
        /*
         * pt 4 -- group by manualCheckRequired
         */
        {
            Iterator<Object[]> iterator = buildStatisticQuery(
                    SQL_AUTO_STATES_GROUP_MANUALYN,reportDef.getHint(),params,
                    null,SQL2_AUTO_STATES_GROUP_MANUALYN).list().iterator();
            
            while (iterator.hasNext()) {
                Object[] sqlResultRow = iterator.next();
                StatisticCubeRowImpl reportRow = getRow(reportDef, sqlResultRow);
                Boolean manualCheckRequired = (Boolean)sqlResultRow[idxData];
                long stateCount = ((Long) sqlResultRow[idxData + 1]).longValue();
                reportRow.setNoTradesTotal(stateCount);
                reportRow.setState(StatisticCubeRowImpl.TOTAL);
                reportRow.setManualCheckRequired(manualCheckRequired);
                rows.add(reportRow);
            }
        }
        /*
         * pt 5 -- group only by keycols (= shows totals as the regular view)
         */
        {
            Iterator<Object[]> iterator = buildStatisticQuery(
                    SQL_AUTO_STATES_GROUP_KEYCOLS,reportDef.getHint(),
                    params,null,SQL2_AUTO_STATES_GROUP_KEYCOLS).list()
                    .iterator();
            
            while (iterator.hasNext()) {
                Object[] sqlResultRow = iterator.next();
                StatisticCubeRowImpl reportRow = getRow(reportDef, sqlResultRow);
                long stateCount = ((Long) sqlResultRow[idxData]).longValue();
                reportRow.setNoTradesTotal(stateCount);
                reportRow.setState(StatisticCubeRowImpl.TOTAL);
                rows.add(reportRow);
            }
        }
        
        /*
         * Hm, I wonder whether we should replicate the default sorting of the
         * original SQL somehow (not really possible through HQL because we
         * split up the statements). But actually I think, MGB's GUI sorting
         * functionality should be good enough. Maybe do it later when there is
         * a user request.
         */

        StatisticCubeReportImpl report = new StatisticCubeReportImpl();
        report.setName(params.getReportName());
        report.setKeyColumnNames(reportDef.getKeyColumnNames());
        report.setAddKeyColumnNames(reportDef.getAddKeyColumnNames());
        report.setSuccessorNames(reportDef.getSuccessorReportNames());
        report.setFromDate(params.getFromDate());
        report.setToDate(params.getToDate());
        report.addAllRows(rows);
        report.setContext(params.getContext());

        return report;
    }

}