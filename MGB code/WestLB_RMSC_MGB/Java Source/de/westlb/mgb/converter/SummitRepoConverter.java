package de.westlb.mgb.converter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import de.westlb.mgb.model.definition.JobStateDef;
import de.westlb.mgb.model.definition.MarketDataRequestTypeDef;
import de.westlb.mgb.model.definition.MgbConfigurationDef;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb.mgb.model.impl.AutomaticStateImpl;
import de.westlb.mgb.model.impl.BloombergRequestImpl;
import de.westlb.mgb.model.impl.BookImpl;
import de.westlb.mgb.model.impl.DataLoadImpl;
import de.westlb.mgb.model.impl.InstrumentImpl;
import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.model.impl.LoadSummitRepoImpl;
import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.model.impl.PriceCheckCategoryImpl;
import de.westlb.mgb.model.impl.PriceCheckInstrumentImpl;
import de.westlb.mgb.model.impl.RequestImpl;
import de.westlb.mgb.model.impl.SourceSystemImpl;
import de.westlb.mgb.model.impl.StatusCalculatorImpl;
import de.westlb.mgb.model.impl.SummitRepoImpl;
import de.westlb.mgb.model.impl.TradeImpl;
import de.westlb.mgb.model.impl.finder.InstrumentSearchParams;
import de.westlb.mgb.model.impl.finder.MgbFinderImpl;
import de.westlb.mgb.model.impl.finder.SearchParams;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.Transaction;

/**
 * @author D055625
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class SummitRepoConverter extends MgbConverter implements MarketDataRequestable {

	static Logger logger = Logger.getLogger(SummitRepoConverter.class);

    protected StatusCalculatorImpl statusCalculator;

    protected String defaultBloombergSources = null;

    protected double bagatelleLimit = 0.0d;

    protected double turnoverLimit = 0.0d;

    protected double secLendingBagatelleLimit = 0d;

    protected double openEndDuration = 45.0d;

    protected HashMap<String, String> tradeTypeCategoryMapping;
    
    public SummitRepoConverter(SourceSystemImpl sourceSystemImpl) {
		super(sourceSystemImpl);
	}

    @Override
    public void initRequester(MandantImpl mandant) throws ConverterException {
    }

	@Override
    protected Class<? extends TradeImpl> getTradeClass() {
		return SummitRepoImpl.class;
	}

	@Override
	protected Class<? extends DataLoadImpl> getLoaderTable() {
		return LoadSummitRepoImpl.class;
	}

    @Override
    protected  void checkCompleteness() throws ConverterException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);
            Query query = sess.createQuery("select distinct load.book from "+ getLoaderTable().getName()+" load where not exists (from "+BookImpl.class.getName()+" book where load.book = book.bookId)");
            logger.debug("HQL: "+query.getQueryString());
            Iterator<String> iter = query.list().iterator();
            StringBuffer unknownBooks = new StringBuffer(); 
            while (iter.hasNext()) {
                unknownBooks.append(iter.next()).append(" ");
            }
            if (unknownBooks.length() > 0) {
            	throw new ConverterException("Books are unknown in the mgb-book table. Please check PARIS feed. The books: "+unknownBooks.toString());
            }
        } catch (PersistenceException e) {
            throw new ConverterException("Error while checking completeness of the loader table", e);
        } finally {
            try {
                if (sess != null) {
                    sess.close();
                }

            } catch (PersistenceException pe) {
            }
        }
    }

	/**
     * @see de.westlb.mgb.converter.MgbConverter#convert(Session,
     *      DataLoadImpl)
     */
    @Override
    protected TradeImpl convert(Session sess, DataLoadImpl loadData, JobImpl job) throws PersistenceException {
        if (loadData instanceof LoadSummitRepoImpl) {
            try {
                SummitRepoImpl summitRepo = (SummitRepoImpl) sess.create(getTradeClass());
                summitRepo.setSourceSystem(sourceSystemImpl);
                summitRepo.setDays(openEndDuration);
                summitRepo.convert((LoadSummitRepoImpl) loadData);
                summitRepo.setJob(job);
                String sourceInstrument = buildSourceInstrument(sess, summitRepo.getUnderlyingValGroup(), summitRepo.getTradeCategory());
                summitRepo.setInstrument((PriceCheckInstrumentImpl) getExistingOrNewInstrument(sess, sourceInstrument, true, job.getMandant()));
                summitRepo.setRateDiff(summitRepo.calcRateDifference());// depends on the instrument
                summitRepo.setProfitLossDiff(summitRepo.calcProfitLossDiff());// depends on the instrument
                summitRepo.setProfitLossDiffEuro(summitRepo.getProfitLossDiff()*summitRepo.getForeignExchangeRate());// depends on the instrument

                summitRepo.setExceedsMaxPlDiff(Math.abs(summitRepo.getProfitLossDiffEuro()) > turnoverLimit);
                summitRepo.setIsBagatelle(Math.abs(summitRepo.getProfitLossDiffEuro()) < getBagatelleLimit(summitRepo)
                    && Math.abs(summitRepo.getProfitLossDiffEuro()) > 0);
                return summitRepo;
            } catch (Exception e) {
                throw new PersistenceException("Error while converting loader record", e);
            }
        }
        throw new PersistenceException("Unable to cast to SummitRepoLoadImpl.");
    }

    protected String buildSourceInstrument(Session sess, String tradeType, String tradeCategory)
            throws PersistenceException {
        return tradeType;
    }
    
    protected InstrumentImpl getExistingOrNewInstrument(Session sess, String sourceSystemInstrument,
            boolean isPriceCheckInstrument, MandantImpl mandant) throws PersistenceException {
        InstrumentSearchParams instrumentSearchParam = new InstrumentSearchParams(mandant,
                sourceSystemInstrument, true);
        instrumentSearchParam.setOnlyPriceCheckInstruments(isPriceCheckInstrument);
        MgbFinderImpl finder = new MgbFinderImpl(sess);
        Collection<? extends InstrumentImpl> coll = finder.findInstruments(instrumentSearchParam);
        Iterator<? extends InstrumentImpl> i = coll.iterator();
        if (i.hasNext()) {
            InstrumentImpl inst = i.next();
            if (i.hasNext()) {
                logger.error("More than one instrument found for sourceSystemInstrument '" + sourceSystemInstrument + "'");
            }
            return inst;
        }
        InstrumentImpl newInstr = null;
        if (isPriceCheckInstrument) {
            newInstr = new PriceCheckInstrumentImpl();
        } else {
            newInstr = new InstrumentImpl();
            newInstr.setBloombergRequestSources(defaultBloombergSources);
        }
        newInstr.setEnabled(true);
        newInstr.setMandant(mandant);
        newInstr.setInstrument(sourceSystemInstrument);
        newInstr.setInstrumentName(sourceSystemInstrument);
        sess.save(newInstr);
        return newInstr;
    }


    @Override
    protected void process() throws ConverterException {
        Session sess = null;
        try {
            logger.info("MainProcessing...");
            sess = getSession(job.getMandant(), importUser);

            statusCalculator = new StatusCalculatorImpl();
            statusCalculator.loadConfiguration(sess, job.getSourceSystem(), StateCodeTypeDef.FIRST_STAGE);

            Query query = sess.createQuery("from "+getTradeClass().getName()+" trade where trade.job = :job");
            logger.debug("HQL: "+query.getQueryString());
            query.setParameter("job", job);
            Transaction t = sess.beginTransaction();
            int count = 0;
            Iterator<SummitRepoImpl> it = query.iterate();
            while (it.hasNext()) {
                SummitRepoImpl trade = (SummitRepoImpl) sess.select(SummitRepoImpl.class, it.next().getId());

                AutomaticStateImpl autoState = statusCalculator.calculateStatus(trade, job.getMandant());
                setCurrentState(sess, trade, autoState, statusCalculator.getLastInternalCalculatorStateComment());
                if (autoState != null) {
                    try {
                        trade.addRequest(buildRequest(sess, trade, autoState.getMarketDataRequestType()));
                    } catch (PersistenceException pe) {
                        logger.error("Error while building Bloomberg request.", pe);
                    }
                }
                if ( ++count % BATCH_SIZE == 0 ) {
                    //flush a batch of updates and release memory:
                    sess.flush();
                    sess.clear();
                }
            }
            t.commit();
                        
        } catch (PersistenceException e) {
            logger.error(e.getMessage(), e);
            throw new ConverterException("Error while mainProcessing: " + e.getMessage(), e);
        } finally {
            try {
                if (sess != null) {
                    sess.close();
                }
            } catch (PersistenceException pe) {
            }
        }
        logger.info("MainProcessing finished.");
    }

    @Override
    protected void preProcess() throws ConverterException {
        try {
            logger.info("PreProcessing...");
            setCurrentVersion();
            setTradeGroups();
            setMccRelevantChange();
            setOpenEndTerminated();
			saveNewBooks();
            //			setStatusFlags();
            logger.info("PreProcessing finished.");
        } catch (PersistenceException pe) {
            throw new ConverterException("Error while preprocessing", pe);
        }
    }

    protected void setStatusFlags() throws PersistenceException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);
            Transaction t = sess.beginTransaction();

            Query query = sess.createQuery("from "+getTradeClass().getName()+"  trade "
                    + "where trade.job = :job and trade.sourceSystemReutersId in ("
                    + "select t1.sourceSystemReutersId from "+getTradeClass().getName()+"  t1 " + "where t1.job = :job " + "and t1.version > 1 "
                    + ") order by trade.sourceSystemReutersId, trade.version desc");
            logger.debug("HQL: "+query.getQueryString());
            query.setParameter("job", job);
            String oldGroupId = "";
            String oldStatus = "";
            int count = 0;
            Iterator<SummitRepoImpl> it = query.iterate();
            while (it.hasNext()) {
                SummitRepoImpl trade = (SummitRepoImpl) sess.select(SummitRepoImpl.class, it.next().getId());
                if (oldGroupId.equals(trade.getTradeGroupId())) {
                    if (SummitRepoImpl.CANC.equals(oldStatus)) {
                        trade.setPredecessorInStornoChain(true);
                        logger.debug("Setting " + trade.getId() + " to isPredecessorInStornoChain.");
                    } else if (SummitRepoImpl.VER.equals(oldStatus)) {
                        trade.setIsIntraDayStorno(true);
                        logger.debug("Setting " + trade.getId() + " to isIntraDayStorno.");
                    }
                } else {
                    oldStatus = trade.getStatus();
                }
                oldGroupId = trade.getTradeGroupId();
                if ( ++count % BATCH_SIZE == 0 ) {
                    //flush a batch of updates and release memory:
                    sess.flush();
                    sess.clear();
                }
            }
            t.commit();

        } catch (PersistenceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            try {
                if (sess != null) {
                    sess.close();
                }
            } catch (PersistenceException pe) {
            }
        }
    }

    protected void setMccRelevantChange() throws PersistenceException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);
            Transaction t = sess.beginTransaction();

            String[] comparisonFields = { 
            		"bondAccruedInterest",
					"currency",
					"endCash",
					"endDate",
					"fundingSpread",
					"instrument",
					"npv",
					"openEndFlag",
					"repoRate",
					"startCash",
					"startDate",
					"startPrice",
					"underlyingValGroup",
					"volume"
            		};

            StringBuffer queryStr = new StringBuffer("select distinct t1 from "+getTradeClass().getName()+"  t1, "+getTradeClass().getName()+"  t2 ");
            queryStr.append("where t1.tradeGroupId = t2.tradeId ");
            queryStr.append("and t1.job = :job ");
            for (int i = 0; i < comparisonFields.length; i++) {
                queryStr.append("and ((t1.").append(comparisonFields[i]).append(" = t2.").append(comparisonFields[i])
                            .append(") or (t1.").append(comparisonFields[i]).append(" is null and t2.").append(comparisonFields[i]).append(" is null)) ");
            }
            Query query = sess.createQuery(queryStr.toString());
            logger.debug("HQL: "+query.getQueryString());
            query.setParameter("job", job);
            int count = 0;
            Iterator<SummitRepoImpl> it = query.iterate();
            while (it.hasNext()) {
                SummitRepoImpl trade = (SummitRepoImpl) sess.select(SummitRepoImpl.class, it.next().getId());
                trade.setIsMccRelevantChange(false);
                logger.debug("Setting " + trade.getId() + " to isMccRelevantChange to false.");
                if ( ++count % BATCH_SIZE == 0 ) {
                    //flush a batch of updates and release memory:
                    sess.flush();
                    sess.clear();
                }
           }
           t.commit();
        } catch (PersistenceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            try {
                if (sess != null) {
                    sess.close();
                }
            } catch (PersistenceException pe) {
            }
        }
    }

    protected void setOpenEndTerminated() throws PersistenceException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);
            Transaction t = sess.beginTransaction();

            StringBuffer queryStr = new StringBuffer("select distinct t1 from "+getTradeClass().getName()+"  t1, "+getTradeClass().getName()+"  t2 ");
            queryStr.append("where t1.tradeGroupId = t2.tradeId ");
            queryStr.append("and t1.job = :job ");
            queryStr.append("and t1.openEndFlag = 'N' ");
            queryStr.append("and t2.openEndFlag = 'Y' ");

            Query query = sess.createQuery(queryStr.toString());
            logger.debug("HQL: "+query.getQueryString());
            query.setParameter("job", job);
            int count = 0;
            Iterator<SummitRepoImpl> it = query.iterate();
            while (it.hasNext()) {
                SummitRepoImpl trade = (SummitRepoImpl) sess.select(SummitRepoImpl.class, it.next().getId());
                trade.setIsOpenEndTerminated(true);
                logger.debug("Setting " + trade.getId() + " to isOpenEndTerminated to true.");
                if ( ++count % BATCH_SIZE == 0 ) {
                    //flush a batch of updates and release memory:
                    sess.flush();
                    sess.clear();
                }
           }
           t.commit();
        } catch (PersistenceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            try {
                if (sess != null) {
                    sess.close();
                }
            } catch (PersistenceException pe) {
            }
        }
    }

    protected void setCurrentVersion() throws PersistenceException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);
            Transaction t = sess.beginTransaction();

            StringBuffer queryStr = new StringBuffer("select t1 from "+getTradeClass().getName()+"  t1 ");
            queryStr.append("where t1.job = :job ");
            queryStr.append("and (t1.version, t1.sourceSystemReutersId) in ");
            queryStr.append("(select max(t2.version), t2.sourceSystemReutersId ");
            queryStr.append("from "+getTradeClass().getName()+"  t2 ");
            queryStr.append("where t2.job = :job ");
            queryStr.append("group by t2.sourceSystemReutersId) ");

            Query query = sess.createQuery(queryStr.toString());
            logger.debug("HQL: "+query.getQueryString());
            query.setParameter("job", job);
            int count = 0;
            Iterator<SummitRepoImpl> it = query.iterate();
            while (it.hasNext()) {
                SummitRepoImpl trade = (SummitRepoImpl) sess.select(SummitRepoImpl.class, it.next().getId());
                trade.setIsCurrentVersion(true);
                logger.debug("Setting " + trade.getId() + " to isCurrentVersion.");
                if ( ++count % BATCH_SIZE == 0 ) {
                    //flush a batch of updates and release memory:
                    sess.flush();
                    sess.clear();
                }
            }
            t.commit();

        } catch (PersistenceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            try {
                if (sess != null) {
                    sess.close();
                }
            } catch (PersistenceException pe) {
            }
        }
    }

    @Override
    protected void setTradeGroups() throws PersistenceException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);
            Transaction t = null;
            Query query = sess
                    .createQuery("select job from "+JobImpl.class.getName()+" job where job.sourceSystem = :sourceSystem and job.status in ('"+JobStateDef.JOB_OK_STATUS+"', '"+JobStateDef.JOB_CLOSED_STATUS+"', '"+JobStateDef.JOB_SPK_CLOSED_STATUS+"') and job <> :job and job.archived = 'N' order by job desc");
            logger.debug("HQL: "+query.getQueryString());
            query.setParameter("job", job);
            query.setParameter("sourceSystem", job.getSourceSystem());
            JobImpl lastJob = null;
            Iterator<JobImpl> iter = query.list().iterator();
            if (!iter.hasNext()) {
                logger.debug("No previous job found");
            }
            int count = 0;
            while (iter.hasNext()) {
                t = sess.beginTransaction();
                lastJob = iter.next();
                StringBuffer queryStr = new StringBuffer(
                        "select t1, t2.tradeId from "+getTradeClass().getName()+"  t1, "+getTradeClass().getName()+"  t2 ");
                queryStr.append("where t1.sourceSystemReutersId = t2.sourceSystemReutersId ");
                queryStr.append("and t1.job = :job ");
                queryStr.append("and t2.job = :lastJob ");
                queryStr.append("and t1.isCurrentVersion = 'Y' ");
                queryStr.append("and t2.isCurrentVersion = 'Y' ");
                queryStr.append("and t2.status = '"+SummitRepoImpl.VER+"' ");
                queryStr.append("and t1.tradeGroupId is null ");
                query = sess.createQuery(queryStr.toString());
                query.setParameter("job", job);
                query.setParameter("lastJob", lastJob);
                Iterator<Object[]> it = query.iterate();
                while (it.hasNext()) {
                    Object[] oArray = it.next();
                    TradeImpl trade = (TradeImpl) sess.select(TradeImpl.class, ((TradeImpl)oArray[0]).getId());
                    String tradeId = (String) oArray[1];
                    trade.setTradeGroupId(tradeId);
                    trade.setIsTradeGroup(true);
                    logger.debug("Setting " + trade.getId() + " to isTradeGroup.");
                    if ( ++count % BATCH_SIZE == 0 ) {
                        //flush a batch of updates and release memory:
                        sess.flush();
                        sess.clear();
                    }
                }
                t.commit();
            }
        } catch (PersistenceException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            try {
                if (sess != null) {
                    sess.close();
                }
            } catch (PersistenceException he) {
            }
        }
    }

    @Override
    public RequestImpl buildRequest(Session sess, TradeImpl trade, String requestType) throws PersistenceException {
        if (!MarketDataRequestTypeDef.isBloombergRequest(requestType)) {
            // Only Bloomberg requests are supported
            return null;
        }
        if (!(trade instanceof SummitRepoImpl)) {
            throw new PersistenceException("Must be instance of SummitRepoImpl");
        }
        SummitRepoImpl repoTrade = (SummitRepoImpl) trade;

        BloombergRequestImpl request = (BloombergRequestImpl) sess.create(BloombergRequestImpl.class);

        request.setRequestType(repoTrade.getCurrentAutoState().getMarketDataRequestType());

        PriceCheckInstrumentImpl inst = repoTrade.getBondInstrument();
        if (inst != null) {
            PriceCheckCategoryImpl check = inst.getPriceCheckCategory();
            if (check != null) {
                request.setPriceCheckCategory(check);
            }
        }
        InstrumentImpl blbInst = repoTrade.getBloombergInstrument();
        if (blbInst != null) {
            request.setRequestSources(blbInst.getBloombergRequestSources());
            String instId = blbInst.getInstrument();
            if (instId != null && instId.length() > 9) {
                request.setRequestString("/isin/"+blbInst.getInstrument() + " Corp");
            } else if (instId != null && instId.length() > 6) {
                request.setRequestString("/cusip/"+blbInst.getInstrument() + " Corp");
            } else {
                request.setRequestString("/wpk/"+blbInst.getInstrument() + " Corp");
            }
        }
        return request;
    }


    @Override
    protected void converterInit() throws ConverterException {
        Session sess = null;
        try {
            logger.info("Initialising converter...");
            sess = getSession(job.getMandant(), importUser);

            MgbFinderImpl finder = new MgbFinderImpl(sess);
            SearchParams param = new SearchParams(job.getMandant());
            tradeTypeCategoryMapping = finder.findTradeTypeCategoryMappings(param);

            bagatelleLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(),
                    MgbConfigurationDef.REPO_BAGATELLE_LIMIT));
            secLendingBagatelleLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(),
                    MgbConfigurationDef.SELO_BAGATELLE_LIMIT));
            turnoverLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(),
                    MgbConfigurationDef.REPO_TURNOVER_LIMIT));
            openEndDuration = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(),
                    MgbConfigurationDef.REPO_OPEN_END_DURATION));
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ConverterException("Error while preProcessing: " + e.getMessage(), e);
        } finally {
            try {
                if (sess != null) {
                    sess.close();
                }
            } catch (PersistenceException pe) {
            }
        }
        logger.info("Initialising converter finished.");
    }

    protected double getBagatelleLimit(SummitRepoImpl repoTrade) {
        if (repoTrade.isSecurityLending()) {
            return secLendingBagatelleLimit;
        } 
        return bagatelleLimit;
    }


}