/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.model.impl.statistic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import de.westlb.mgb.client.server.vo.CheckStateVo;
import de.westlb.mgb.client.server.vo.StateStatisticEntryVo;
import de.westlb.mgb.model.definition.JobStateDef;
import de.westlb.mgb.model.definition.StateCodeDef;
import de.westlb.mgb.model.impl.AbstractManualState;
import de.westlb.mgb.model.impl.AutomaticStateImpl;
import de.westlb.mgb.model.impl.ManualStateImpl;
import de.westlb.mgb.model.impl.StateIdImpl;
import de.westlb.mgb.model.impl.TradeImpl;
import de.westlb.mgb.model.impl.finder.AbstractFinder;
import de.westlb.mgb.model.impl.statistic.reports.ReportDef;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;


/**
 * This class creates most MGB statistic reports.
 * 
 * <p>
 * Note: In addition to the aptly-called "Statistics" panels in MGB, this class
 * is also used to create the totals numbers at the top of every job trade list.
 * See {@link #createBasicStatistic(ReportSearchParams)}.
 * </p>
 *
 * @author Manfred Boerner
 */
public class StatisticReportFactoryImpl extends AbstractFinder {
    private static final String SQL_TRADE_COUNT = "select <keyCols,>  count(trade) from <entity> trade";
    private static final String SQL2_TRADE_COUNT = " group by <keyCols>";
	private static final String SQL_AUTO_STATES = "select <keyCols,>  trade.currentAutoStateHistEntry.automaticState.stateId.stateCode, count(trade.currentAutoStateHistEntry.automaticState.stateId.stateCode) from <entity> trade";
	private static final String SQL2_AUTO_STATES = " group by <keyCols,>  trade.currentAutoStateHistEntry.automaticState.stateId.stateCode";
    private static final String SQL_MANUAL_STATE = "select <keyCols,>   trade.currentManualStateHistEntry.manualState.stateId.stateCode, count(trade.currentManualStateHistEntry.manualState.stateId.stateCode) from <entity> trade";
    private static final String SQL2_MANUAL_STATE = " group by <keyCols,>  trade.currentManualStateHistEntry.manualState.stateId.stateCode";
    private static final String SQL_RECL_CLOSED_COUNT = "select <keyCols,> trade.currentReclStateHistEntry.isClosed, count(trade.currentReclStateHistEntry.isClosed) from <entity> trade";
    private static final String SQL2_RECL_CLOSED_COUNT = " group by <keyCols,> trade.currentReclStateHistEntry.isClosed";

	static Logger logger = Logger.getLogger(StatisticReportFactoryImpl.class);

    /**
     * DOCUMENT ME!
     *
     * @param sess
     */
    public StatisticReportFactoryImpl(Session sess) {
        super(sess);
        templateQueryBuilder=new TemplateQueryBuilder(sess);
    }

    private Query buildStatisticQuery(String template, String hint, StatisticReportParamsImpl params) throws PersistenceException {
        return buildStatisticQuery(template, hint, params, null, "");
    }
    private Query buildStatisticQuery(String template, String hint, StatisticReportParamsImpl params, String[] addClauseTokens, String endClause)
    throws PersistenceException
    {
        return templateQueryBuilder.buildStatisticQuery(template,hint,params,addClauseTokens,endClause);
    }
    
    private TemplateQueryBuilder templateQueryBuilder;
    
    /**
     * Because the statistic report query template syntax is now used by
     * {@link StatisticCubeReportFactoryImpl} too, it's moved into a nested
     * class. {@link StatisticReportFactoryImpl} still needs to extend
     * {@link AbstractFinder} though because of some legacy methods still in
     * the class that relay to the latter's methods.
     */
    static class TemplateQueryBuilder extends AbstractFinder
    {
        public TemplateQueryBuilder(Session sess) { super(sess); }
        public Query buildStatisticQuery(String template, String hint, StatisticReportParamsImpl params, String[] addClauseTokens, String endClause)
            throws PersistenceException {
            StringBuffer strBuf = new StringBuffer();
            String[] keyColNames = params.getReportDef().getKeyColumnNames();
    		String[] addKeyColNames = params.getReportDef().getAddKeyColumnNames();
            if (keyColNames != null) {
                for (int i = 0; i < keyColNames.length; i++) {
                    strBuf.append(keyColNames[i]);
                    if (addKeyColNames != null && addKeyColNames.length > 0) {
                        strBuf.append(",");
                    }
                }
            }
    
    		if (addKeyColNames != null) {
    			for (int i = 0; i < addKeyColNames.length; i++) {
    				strBuf.append(addKeyColNames[i]);
    				if (i != (addKeyColNames.length - 1)) {
    					strBuf.append(",");
    				}
    			}
    		}
    		String keyCols = strBuf.toString();
    		String sqlEndClause = StringUtils.replace(endClause, "<keyCols>", keyCols);
    		sqlEndClause = StringUtils.replace(sqlEndClause, "<keyCols,>", keyCols + ",");
    		String sql = StringUtils.replace(template, "<keyCols>", keyCols);
    		sql = StringUtils.replace(sql, "<keyCols,>", keyCols + ",");
            sql = StringUtils.replace(sql, "<entity>", params.calculateEntityName());
            
            ArrayList<String> clauseTokens = new ArrayList<String>();
            HashMap<String, Object> columnValues = new HashMap<String, Object>();
    
            if (params.getFromDate() != null) {
                clauseTokens.add("trade.job.stopBusinessTime >= :fromDate");
                columnValues.put("fromDate", params.getFromDate());
            }
    
            if (params.getToDate() != null) {
                clauseTokens.add("trade.job.stopBusinessTime <= :toDate");
                columnValues.put("toDate", params.getToDate());
            }
            
    		if (params.getMandant() != null) {
    			clauseTokens.add("trade.job.mandant = :mandant");
    			columnValues.put("mandant", params.getMandant());
    		}
    		
    		// Take into account only jobs in status 'CLOSED' and 'OK'
    		if (params.getJobs() == null) {
    			clauseTokens.add("trade.job.status in ('" + JobStateDef.JOB_OK_STATUS + "','"  + JobStateDef.JOB_CLOSED_STATUS + "','"  + JobStateDef.JOB_SPK_CLOSED_STATUS + "')");
    		}
    
            if ((params.getJobs() != null) && (params.getJobs().length > 0)) {
                String s = "trade.job in (";
    
                if (params.getJobs().length > 0) {
                    columnValues.put("job0", params.getJobs()[0]);
                    s = s + " :job0";
                }
    
                for (int i = 1; i < params.getJobs().length; i++) {
                    columnValues.put("job" + i, params.getJobs()[i]);
                    s = s + ", :job" + i;
                }
    
                s = s + ")";
                clauseTokens.add(s);
            }
            
            if (addClauseTokens != null) {
                for (int i = 0; i < addClauseTokens.length; i++) {
                    clauseTokens.add(addClauseTokens[i]);
                }
            }
            
            if (params.getContext() != null) {
            	StatisticContextImpl[] context = params.getContext();
            	for (int i = 0; i < context.length; i++) {
            		for (int j = 0; j < context[i].getReportDef().getKeyColumnNames().length; j++) {
            			String colName = context[i].getReportDef().getKeyColumnNames()[j];
            			Object colValue = context[i].getKeyValues()[j];
            			if (context[i].getReportDef().getHint().length() > 0) {
            			    hint = hint + context[i].getReportDef().getHint();
            			}
            			if (colValue == null) {
    						clauseTokens.add(colName + " is null");
    					} else {
    						String colAlias = colName.replaceAll("\\.","_");
    						clauseTokens.add(colName + " = :" + colAlias);
    						columnValues.put(colAlias, colValue);
    					}
    				}
                }
            }
    
        Query q = buildQuery(sql+hint, columnValues, clauseTokens, sqlEndClause);
            logger.debug(q.getQueryString());
            return q;
        }
    }

    private StatisticRowImpl getRow(HashMap<ArrayList<Object>, StatisticRowImpl> cache, ReportDef reportDef, Object[] sqlResultRow) {
        ArrayList<Object> key = new ArrayList<Object>();

        for (int i = 0; i < reportDef.getKeyCount(); i++) {
            key.add(sqlResultRow[i]);
        }

        StatisticRowImpl row = cache.get(key);

        if (row == null) {
            row = new StatisticRowImpl();
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
			cache.put(key, row);
        }

        return row;
    }
    
    /**
     * Finds the trades contained in a static report row.
     * 
     * @param reportName The name of the report containing the statistic row.
     * @param statisticRow The row for which the trades are to  be returned.
     * @return Collection of TradeImpl
     * 
     * @throws PersistenceException
     */
    public Collection<TradeImpl> getTradesForStatisticRow(StatisticReportParamsImpl params) throws PersistenceException {
		StatisticContextImpl[] ctx = params.getContext();
		if (ctx == null || ctx.length < 1) {
			throw new IllegalArgumentException("Illegal report params context must be > 0");
		}
		params.setReportName(ctx[ctx.length-1].getReportName());

		Query query =  buildStatisticQuery("select trade from <entity> trade", "", params);
	    Collection<TradeImpl> result = query.list();
		return result;
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
    public StatisticReportImpl createReport(StatisticReportParamsImpl params)
        throws PersistenceException {
        HashMap<ArrayList<Object>, StatisticRowImpl> rowCache = new HashMap<ArrayList<Object>, StatisticRowImpl>();

        if ((params == null) || (params.getMandant() == null)) {
            throw new IllegalArgumentException("The mandant property has to be set for getReport");
        }        
        
        ReportDef reportDef = params.getReportDef();

        int idxKeyCols = 0;
        int keyColCnt = reportDef.getKeyCount();
        int idxAddCols = idxKeyCols + keyColCnt;
        int addColCnt = reportDef.getAddKeyCount();
        int idxStatCnt = idxAddCols + addColCnt;

        /**
         * Count automatic states and manual check required.
         */
        {
            Iterator<Object[]> iterator = buildStatisticQuery(SQL_AUTO_STATES, reportDef.getHint(), params, null, SQL2_AUTO_STATES).list().iterator();
            
            while (iterator.hasNext()) {
                Object[] sqlResultRow = iterator.next();
                StatisticRowImpl reportRow = getRow(rowCache, reportDef, sqlResultRow);
                String stateCode = (String) sqlResultRow[idxStatCnt];
                long stateCount = ((Long) sqlResultRow[idxStatCnt + 1]).longValue();
                AutomaticStateImpl state = (AutomaticStateImpl) sess.select(AutomaticStateImpl.class,
                        new StateIdImpl(params.getMandant(), stateCode));

                if (state.isManualCheckRequired()) {
                    reportRow.setNoTradesManualCheckRequired(reportRow.getNoTradesManualCheckRequired() + stateCount);
                } else if (state.getIsMccChecked()){
                	reportRow.setNoTradesOkAfterAutoMccCheck(reportRow.getNoTradesOkAfterAutoMccCheck() + stateCount);
                    StateStatisticEntryImpl stateEntry = new StateStatisticEntryImpl(state, stateCount);
                    reportRow.putAutomaticState(stateEntry);
                } else {
                    reportRow.setNoTradesOkAfterAutoCheck(reportRow.getNoTradesOkAfterAutoCheck() + stateCount);
                    StateStatisticEntryImpl stateEntry = new StateStatisticEntryImpl(state, stateCount);
                    reportRow.putAutomaticState(stateEntry);
                }

            }
        }

        /**
         * Count manual states and reclamation required.
         */
        {
            Iterator<Object[]> iterator = buildStatisticQuery(SQL_MANUAL_STATE, reportDef.getHint(), params, null, SQL2_MANUAL_STATE).list().iterator();

            while (iterator.hasNext()) {
                Object[] sqlResultRow = iterator.next();
                StatisticRowImpl reportRow = getRow(rowCache, reportDef, sqlResultRow);
                String stateCode = (String) sqlResultRow[idxStatCnt];
                long stateCount = ((Long) sqlResultRow[idxStatCnt + 1]).longValue();
                AbstractManualState state = (AbstractManualState) sess.select(AbstractManualState.class,
                        new StateIdImpl(params.getMandant(), stateCode));

                if (state.isReclamationRequired()) {
                    reportRow.setNoTradesReclRequired(reportRow.getNoTradesReclRequired() + stateCount);
                    reportRow.setNoTradesReclOpen(reportRow.getNoTradesReclRequired());
                } else {
	                StateStatisticEntryImpl stateEntry = new StateStatisticEntryImpl(state, stateCount);
                	reportRow.putManualState(stateEntry);
                }
            }
        }

        /**
         * Count trades where manual state required but not handled yet.
         */
        {
            String[] addClauseTokens = new String[] {
                    "trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired = 'Y'",
                    "trade.currentManualStateHistEntry is null",
                };
            Iterator<Object[]> iterator = buildStatisticQuery(SQL_TRADE_COUNT, reportDef.getHint(), params, addClauseTokens, SQL2_TRADE_COUNT).list().iterator();

            while (iterator.hasNext()) {
                Object[] sqlResultRow = iterator.next();
                long stateCount = ((Long) sqlResultRow[idxStatCnt]).longValue();
                StatisticRowImpl reportRow = getRow(rowCache, reportDef, sqlResultRow);
                reportRow.setNoTradesManualStateRequiredButNotHandledYet(stateCount);
                StateStatisticEntryImpl stateEntry = new StateStatisticEntryImpl(new ManualStateImpl(new StateIdImpl(params.getMandant(), StateCodeDef.MAN_REQUIRED_BUT_NOT_HANDLED_YET)), stateCount);
            	reportRow.putManualState(stateEntry);
            }
        }

        /**
         * Count trades that had been entered too late into the frontoffice system
         */
        {
            String[] addClauseTokens = new String[] { "trade.isLateDeal = 'Y'", };
            Iterator<Object[]> iterator = buildStatisticQuery(SQL_TRADE_COUNT, reportDef.getHint(), params, addClauseTokens, SQL2_TRADE_COUNT).list().iterator();

            while (iterator.hasNext()) {
                Object[] sqlResultRow = iterator.next();
                StatisticRowImpl reportRow = getRow(rowCache, reportDef, sqlResultRow);
                long stateCount = ((Long) sqlResultRow[idxStatCnt]).longValue();
                reportRow.setNoTradesLateEntry(stateCount);
            }
        }

        /**
         * Count reclamations waiting and reclamations closed.
         */
        {
            Iterator<Object[]> iterator = buildStatisticQuery(SQL_RECL_CLOSED_COUNT, reportDef.getHint(), params, null, SQL2_RECL_CLOSED_COUNT).list().iterator();

            while (iterator.hasNext()) {
                Object[] sqlResultRow = iterator.next();
                StatisticRowImpl reportRow = getRow(rowCache, reportDef, sqlResultRow);
                Boolean isClosed = (Boolean) sqlResultRow[idxStatCnt];
                long stateCount = ((Long) sqlResultRow[idxStatCnt + 1]).longValue();

                if (Boolean.TRUE.equals(isClosed)) {
                    reportRow.setNoTradesReclClosed(stateCount);
                    reportRow.setNoTradesReclOpen(Math.max(0, reportRow.getNoTradesReclOpen() - stateCount));
                } else if (Boolean.FALSE.equals(isClosed)) {
                    reportRow.setNoTradesReclWaiting(stateCount);
                    reportRow.setNoTradesReclOpen(Math.max(0, reportRow.getNoTradesReclOpen() - stateCount));
                }
            }
        }


        StatisticReportImpl report = new StatisticReportImpl();
        report.setName(params.getReportName());
        report.setKeyColumnNames(reportDef.getKeyColumnNames());
        report.setAddKeyColumnNames(reportDef.getAddKeyColumnNames());
        report.setSuccessorNames(reportDef.getSuccessorReportNames());
        report.setFromDate(params.getFromDate());
        report.setToDate(params.getToDate());
        report.addAllRows(rowCache.values());
        report.setContext(params.getContext());

        return report;
    }

    private Query buildQuery(String sql, ReportSearchParams params, String[] conditions)
    throws PersistenceException {
    return buildQuery(sql, params, conditions, "");
}

    private Query buildQuery(String sql, ReportSearchParams params, String[] conditions, String endClause)
        throws PersistenceException {
        ArrayList<String> clauseTokens = new ArrayList<String>();
        HashMap<String, Object> columnValues = new HashMap<String, Object>();
        boolean resticted = false;

        if (params.getMandant() != null) {
            clauseTokens.add("trade.mandant = :mandant");
            columnValues.put("mandant", params.getMandant());
        }

        if (params.getFromDate() != null) {
            clauseTokens.add("trade.tradeDate >= :fromDate");
            columnValues.put("fromDate", params.getFromDate());
            resticted = true;
        }

        if (params.getToDate() != null) {
            clauseTokens.add("trade.tradeDate <= :toDate");
            columnValues.put("toDate", params.getToDate());
            resticted = true;
        }

        if ((params.getJobs() != null) && (params.getJobs().length > 0)) {
            String s = "trade.job in (";

            if (params.getJobs().length > 0) {
                columnValues.put("job0", params.getJobs()[0]);
                s = s + " :job0";
            }

            for (int i = 1; i < params.getJobs().length; i++) {
                columnValues.put("job" + i, params.getJobs()[i]);
                s = s + ", :job" + i;
            }

            s = s + ")";
            clauseTokens.add(s);
            resticted = true;
        }

        if (!resticted) {
            clauseTokens.add("1 = 2");
            logger.warn("resticting an unrestricted statistic query");
        }

        if (conditions != null) {
            for (int i = 0; i < conditions.length; i++) {
                clauseTokens.add(conditions[i]);
            }
        }
        
        return buildQuery(sql, columnValues, clauseTokens, endClause);
    }

    /**
     * This method exists for historical reason only. As soon as possible
     * the method should be replaced by createReport.
     *
     * @param params ReportSearchParams
     *
     * @return CheckStateVo
     *
     * @throws PersistenceException If something unexpected happens while executing  Hibernate
     *         queries.
     * @throws IllegalArgumentException If the mandant property of params is null.
     * 
     */
    public CheckStateVo createBasicStatistic(ReportSearchParams params)
        throws PersistenceException {
        if ((params == null) || (params.getMandant() == null)) {
            throw new IllegalArgumentException(
                "The mandant property has to be set for findCheckStateVo");
        }

        long noTradesManualCheckRequired = 0;
        long noTradesOkAfterAutoCheck = 0;
        long noTradesReclClosed = 0;
        long noReclRequiredButNotHandled = 0;
        long noTradesReclWaiting = 0;
        long noTradesToCheck = 0;
        long noTradesLateEntry = 0;
        long noAutomaticStateNotSet = 0;
        long noManualRequiredButNotHandled = 0;
        
        ArrayList<StateStatisticEntryVo> automaticStates = new ArrayList<StateStatisticEntryVo>();
        ArrayList<StateStatisticEntryVo> manualStates = new ArrayList<StateStatisticEntryVo>();

        /**
         * Count automatic states and manual check required.
         */
        {
          String table = "select trade.currentAutoStateHistEntry.automaticState.stateId.stateCode, trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired, count(trade.currentAutoStateHistEntry.automaticState.stateId.stateCode) from "+TradeImpl.class.getName()+" trade";
          String endClause = " group by trade.currentAutoStateHistEntry.automaticState.stateId.stateCode,  trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired";
            Query query = buildQuery(table, params, null, endClause);
            Collection<Object[]> result = query.list();
            Iterator<Object[]> iterator = result.iterator();

            while (iterator.hasNext()) {
                Object[] resultEntry = iterator.next();
                String stateCode = (String) resultEntry[0];
                boolean isManualCheckRequired = resultEntry[1] != null && ((Boolean)resultEntry[1]).booleanValue();
                long stateCount = ((Long) resultEntry[2]).longValue();

                if (isManualCheckRequired) {
                    noTradesManualCheckRequired += stateCount;
                } else {
                    noTradesOkAfterAutoCheck += stateCount;
                }

                StateStatisticEntryVo stateStatisticEntry = new StateStatisticEntryVo();
                stateStatisticEntry.setCount(stateCount);
                stateStatisticEntry.setState(stateCode);
                stateStatisticEntry.setManualCheckRequired(isManualCheckRequired);
                automaticStates.add(stateStatisticEntry);
            }
        }

        /**
         * Count manual states and reclamation required.
         */
        {
            String table = "select trade.currentManualStateHistEntry.manualState.stateId.stateCode, trade.currentManualStateHistEntry.manualState.isReclamationRequired, count(trade.currentManualStateHistEntry.manualState.stateId.stateCode) from "+TradeImpl.class.getName()+" trade";
            String endClause = " group by trade.currentManualStateHistEntry.manualState.stateId.stateCode, trade.currentManualStateHistEntry.manualState.isReclamationRequired";
            Query query = buildQuery(table, params, null, endClause);
            Collection<Object[]> result = query.list();
            Iterator<Object[]> iterator = result.iterator();

            while (iterator.hasNext()) {
                Object[] resultEntry = iterator.next();
                String stateCode = (String) resultEntry[0];
                boolean isReclamationRequired = resultEntry[1] != null && ((Boolean)resultEntry[1]).booleanValue();
                long stateCount = ((Long) resultEntry[2]).longValue();
            	StateStatisticEntryVo stateStatisticEntry = new StateStatisticEntryVo();
                stateStatisticEntry.setCount(stateCount);
                stateStatisticEntry.setState(stateCode);
                stateStatisticEntry.setReclamationRequired(isReclamationRequired);
                manualStates.add(stateStatisticEntry);
            }
        }


        /**
         * Count trades where currentAutoState is null
         */
        {
            String table = "select count(trade) from "+TradeImpl.class.getName()+" trade";
            Query query = buildQuery(table, params,
                    new String[] { "trade.currentAutoStateHistEntry is null" });
            noAutomaticStateNotSet = ((Long) (query.list().iterator().next())).longValue();
        }

        noTradesToCheck = noAutomaticStateNotSet + noTradesOkAfterAutoCheck + noTradesManualCheckRequired;

        /**
         * Count trades where manual check is required but not handled.
         */
        {
            String table = "select count(trade) from "+TradeImpl.class.getName()+" trade";
            Query query = buildQuery(table, params, new String[] { 
						"trade.currentManualStateHistEntry is null",
						"trade.currentAutoStateHistEntry.automaticState.isManualCheckRequired = 'Y'"
            });
			noManualRequiredButNotHandled = ((Long) (query.list().iterator().next())).longValue();

            StateStatisticEntryVo stateStatisticEntry = new StateStatisticEntryVo();
            stateStatisticEntry.setCount(noManualRequiredButNotHandled);
            stateStatisticEntry.setState(StateCodeDef.MAN_REQUIRED_BUT_NOT_HANDLED_YET);
            manualStates.add(stateStatisticEntry);
        }
        
        /**
         * Count trades that had been entered too late into the frontoffice system
         */
        {
            String table = "select count(trade) from "+TradeImpl.class.getName()+" trade";
            Query query = buildQuery(table, params,
                    new String[] { "trade.isLateDeal = 'Y'" });

            noTradesLateEntry = ((Long) (query.list().iterator().next())).longValue();
        }

        
        /**
         * Count reclamations waiting and reclamations closed.
         */
        {
            String table = "select trade.currentReclStateHistEntry.isClosed, count(trade) from "+TradeImpl.class.getName()+" trade";
            String endClause = " group by trade.currentReclStateHistEntry.isClosed";
            Query query = buildQuery(table, params, null, endClause);
            Collection<Object[]> result = query.list();
            Iterator<Object[]> iterator = result.iterator();

            while (iterator.hasNext()) {
                Object[] resultEntry = iterator.next();
                Boolean isClosed = (Boolean) resultEntry[0];
                long count = ((Long) resultEntry[1]).longValue();

                if (Boolean.TRUE.equals(isClosed)) {
                    noTradesReclClosed = count;
                } else if (Boolean.FALSE.equals(isClosed)) {
                    noTradesReclWaiting = count;
                }
            }
        }

		/**
		 * Count trades reclamation required but not handled
		 */
        {
            String table = "select count(trade) from "+TradeImpl.class.getName()+" trade";
            Query query = buildQuery(table, params, new String[] { 
					 "trade.currentReclStateHistEntry is null",
					 "trade.currentManualStateHistEntry.manualState.isReclamationRequired = 'Y'"
            });
            noReclRequiredButNotHandled = ((Long) (query.list().iterator().next())).longValue();
        }

        /** Count reclamations open and waiting and calculate reclamations */
        CheckStateVo checkState = new CheckStateVo();
        checkState.setNoTradesManualCheckRequired(noTradesManualCheckRequired);
        checkState.setNoTradesOkAfterAutoCheck(noTradesOkAfterAutoCheck);
        checkState.setNoTradesReclRequiredButNotHandled(noReclRequiredButNotHandled);
        checkState.setNoTradesReclOpen(noTradesReclWaiting);
        checkState.setNoTradesReclClosed(noTradesReclClosed);
        checkState.setNoTradesToCheck(noTradesToCheck);
        checkState.setManualCheckFinished(noManualRequiredButNotHandled <= 0);
        checkState.setReclamationFinished(noReclRequiredButNotHandled <= 0 && noTradesReclWaiting <= 0);
        checkState.setNoTradesLateEntry(noTradesLateEntry);

        checkState.setAutomaticStates(automaticStates.toArray(
                new StateStatisticEntryVo[0]));
        checkState.setManualStates(manualStates.toArray(
                new StateStatisticEntryVo[0]));

        return checkState;
    }
}