package de.westlb.mgb.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import de.westlb.mgb.model.definition.JobStateDef;
import de.westlb.mgb.model.definition.MarketDataRequestStateDef;
import de.westlb.mgb.model.definition.MarketDataRequestTypeDef;
import de.westlb.mgb.model.definition.MgbConfigurationDef;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb.mgb.model.impl.AutomaticStateImpl;
import de.westlb.mgb.model.impl.BloombergRequestImpl;
import de.westlb.mgb.model.impl.BookImpl;
import de.westlb.mgb.model.impl.DataLoadImpl;
import de.westlb.mgb.model.impl.FoRequestImpl;
import de.westlb.mgb.model.impl.HistoricPriceImpl;
import de.westlb.mgb.model.impl.InstrumentImpl;
import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.model.impl.LoadSummitAmendImpl;
import de.westlb.mgb.model.impl.LoadSummitBondImpl;
import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.model.impl.PriceCheckCategoryImpl;
import de.westlb.mgb.model.impl.PriceCheckInstrumentImpl;
import de.westlb.mgb.model.impl.RequestImpl;
import de.westlb.mgb.model.impl.SourceSystemImpl;
import de.westlb.mgb.model.impl.StatusCalculatorImpl;
import de.westlb.mgb.model.impl.SummitBondImpl;
import de.westlb.mgb.model.impl.TradeImpl;
import de.westlb.mgb.model.impl.finder.InstrumentSearchParams;
import de.westlb.mgb.model.impl.finder.MgbFinderImpl;
import de.westlb.mgb.model.impl.finder.SearchParams;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.Transaction;
import de.westlb.mgb.util.SampleCreator;

/**
 * @author D055625
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class SummitBondConverter extends MgbConverter implements MarketDataRequestable {

	static Logger logger = Logger.getLogger(SummitBondConverter.class);

    private StatusCalculatorImpl statusCalculator;

    private double bagatelleLimit = 0.0d;

    private double turnoverLimit = 0.0d;

    private double maxBackToBackSpread = 0.12d;
    
    protected double lateTradeVolumeLimit = 0.0d;

    private String defaultBloombergSources = null;

    private String exoticBloombergSources = null;

    private String smallCustomerBloombergSources = null;

    private HashMap<String, String> currencyCategoryMapping;

    private HashMap<String, String> ratingCategoryMapping;

    private HashMap<String, String> tradeTypeCategoryMapping;

    private ArrayList<String> exoticBooks = new ArrayList<String>();

    private ArrayList<String> nonStruktureToleranceCategory = new ArrayList<String>();

    private ArrayList<String> westlbNonStruktureToleranceCategory = new ArrayList<String>();

    protected ArrayList<String> booksWithBackToBackTrades = new ArrayList<String>();

    public SummitBondConverter(SourceSystemImpl sourceSystemImpl) {
		super(sourceSystemImpl);
	}

    protected SummitBondImpl convert(Session sess, LoadSummitBondImpl loadData) throws PersistenceException {
		SummitBondImpl summitBond = (SummitBondImpl) sess.create(SummitBondImpl.class);
		summitBond.setSourceSystem(sourceSystemImpl);
		summitBond.convert(loadData);
		return summitBond;
	}

	@Override
	protected Class<? extends DataLoadImpl> getLoaderTable() {
		return LoadSummitBondImpl.class;
	}

	@Override
    protected Class<? extends TradeImpl> getTradeClass() {
		return SummitBondImpl.class;
	}
    
	@Override
    public void initRequester(MandantImpl mandant) throws ConverterException {
        Session sess = null;
        try {
            logger.info("initRequester...");
            sess = getSession(mandant, importUser);

            MgbFinderImpl finder = new MgbFinderImpl(sess);
            exoticBloombergSources = finder.findMgbConfigurationValue(mandant, MgbConfigurationDef.BOND_BLOOMBERG_EXOTIC_SOURCES);
            smallCustomerBloombergSources = finder.findMgbConfigurationValue(mandant, MgbConfigurationDef.BOND_BLOOMBERG_SMALL_CUSTOMER_SOURCES);
            exoticBooks = arrayFromMgbConfiguration(finder, MgbConfigurationDef.BOND_EXOTIC_BOOKS);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ConverterException("Error while initRequester: " + e.getMessage(), e);
        } finally {
            try {
                if (sess != null) {
                    sess.close();
                }

            } catch (PersistenceException pe) {
            }
        }
        logger.info("initRequester finished.");
	}

    @Override
    protected  void checkCompleteness() throws ConverterException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);

            Query query = sess.createQuery("select distinct load.book from "+ getLoaderTable().getName()  +" load " +
            		"where not exists (from "+BookImpl.class.getName()+" book where load.book = book.bookId)");
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
        SummitBondImpl summitBond = null;
        if (loadData instanceof LoadSummitBondImpl) {
        	try {
        		summitBond = convert(sess, (LoadSummitBondImpl)loadData);
        		summitBond.setJob(job);
            //	todo implement rating
        		summitBond.setSourceSystemInstrument(buildSourceInstrument(summitBond.getCurrency(), "A", summitBond
                    .getCategory(), summitBond.getStructure(), summitBond.getInstrumentName()));
        		summitBond.setInstrument((PriceCheckInstrumentImpl) getExistingOrNewInstrument(sess, summitBond
                    .getSourceSystemInstrument(), true));
        		if (((LoadSummitBondImpl) loadData).getIsin() != null) {
        			InstrumentImpl instr = getExistingOrNewInstrument(sess, ((LoadSummitBondImpl) loadData).getIsin(), false);
        			summitBond.setBloombergInstrument(instr);
        		}
        		summitBond.setBagatelleLimit(bagatelleLimit);
        		summitBond.setTurnoverLimit(turnoverLimit);
        		double turnover = summitBond.calculateTurnover(summitBond.getMarketPrice());
        		summitBond.checkTurnoverLimits(turnover);
        	} catch (Exception e) {
        		throw new PersistenceException("Error while converting loader record", e);
        	}
        } else {
            throw new PersistenceException("Unable to cast to SummitBondLoadImpl.");
        }
        return summitBond;
    }

    /**
     * @see de.westlb.mgb.converter.MgbConverter#preProcess()
     */
    @Override
    protected void preProcess() throws ConverterException {
        try {
            logger.info("PreProcessing...");
            setCurrentVersion();
            setTradeGroups();
            setMccRelevantChange();
            setIsLateDeal();
            saveNewBooks();
            setBackToBack();
            //			setStatusFlags();
            logger.info("PreProcessing finished.");
        } catch (PersistenceException pe) {
            throw new ConverterException("Error while preprocessing", pe);
        }
    }

    /**
     * @see de.westlb.mgb.converter.MgbConverter#process()
     */
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
            Iterator<SummitBondImpl> it = query.iterate();
            while (it.hasNext()) {
                SummitBondImpl trade = it.next();

                FoRequestImpl request = (FoRequestImpl) sess.create(FoRequestImpl.class);
                request.setRequestState(MarketDataRequestStateDef.OK);
                request.setRequestDate(new GregorianCalendar());
                HistoricPriceImpl price = (HistoricPriceImpl) sess.create(HistoricPriceImpl.class);
                // is this value o.k.?
                price.setPrice(trade.getTheoreticalPrice());
                request.setPriceResult(price);
                trade.addRequest(request);

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
            sess.flush();
            sess.clear();
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

    /**
     * @see de.westlb.mgb.converter.MgbConverter#converterInit()
     */
    @Override
    protected void converterInit() throws ConverterException {
        Session sess = null;
        try {
            logger.info("Initialising converter...");
            sess = getSession(job.getMandant(), importUser);

            MgbFinderImpl finder = new MgbFinderImpl(sess);
            SearchParams param = new SearchParams();
            param.setMandant(job.getMandant());
            currencyCategoryMapping = finder.findCurrencyCategoryMappings(param);
            ratingCategoryMapping = finder.findRatingCategoryMappings(param);
            tradeTypeCategoryMapping = finder.findTradeTypeCategoryMappings(param);

            lateTradeVolumeLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.LATE_TRADE_VOLUME_LIMIT));
            bagatelleLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.BOND_BAGATELLE_LIMIT));
            turnoverLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.BOND_TURNOVER_LIMIT));
            defaultBloombergSources = finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.BOND_BLOOMBERG_DEFAULT_SOURCES);
            exoticBloombergSources = finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.BOND_BLOOMBERG_EXOTIC_SOURCES);
            smallCustomerBloombergSources = finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.BOND_BLOOMBERG_SMALL_CUSTOMER_SOURCES);
            exoticBooks = arrayFromMgbConfiguration(finder, MgbConfigurationDef.BOND_EXOTIC_BOOKS);
            booksWithBackToBackTrades = arrayFromMgbConfiguration(finder, MgbConfigurationDef.BOOKS_WITH_BACK_TO_BACK_TRADES);
            maxBackToBackSpread = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.MAX_BACK_TO_BACK_SPREAD));
            nonStruktureToleranceCategory = arrayFromMgbConfiguration(finder, MgbConfigurationDef.NON_STRUCTURE_TOLERANCE_CATEGORY);
            westlbNonStruktureToleranceCategory = arrayFromMgbConfiguration(finder, MgbConfigurationDef.WESTLB_NON_STRUCTURE_TOLERANCE_CATEGORY);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ConverterException("Error while initialising: " + e.getMessage(), e);
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

    private String buildSourceInstrument(String currency, String rating, String tradeType, String struct, String instrumentName) {
        String delim = "-";

        if (isStruct(struct, instrumentName)) {
        	return struct;
        }
        String currencyCategory = currencyCategoryMapping.get(currency);
        if (currencyCategory == null) {
            currencyCategory = "DEFAULT";
        }

        String ratingCategory = ratingCategoryMapping.get(rating);
        if (ratingCategory == null) {
            ratingCategory = "DEFAULT";
        }

        String tradeTypeCategory = tradeTypeCategoryMapping.get(tradeType);
        if (tradeTypeCategory == null) {
            tradeTypeCategory = "DEFAULT";
        }

        if (ratingCategoryMapping.isEmpty()) {
            return tradeTypeCategory + delim + currencyCategory;
        }
        return tradeTypeCategory + delim + currencyCategory + delim + ratingCategory;
    }

    private boolean isStruct(String struct, String instrumentName) {
    	if (struct != null && struct.length()>0 && !nonStruktureToleranceCategory.contains(struct)) {
			if ( westlbNonStruktureToleranceCategory.contains(struct)
					&& instrumentName != null 
					&& (instrumentName.startsWith("WLB") || instrumentName.startsWith("WESTLB")) ) {
				return false;
			} 
    	}else {
			return false;
		}
		return true;
    }

    private InstrumentImpl getExistingOrNewInstrument(Session sess, String sourceSystemInstrument,
            boolean isPriceCheckInstrument) throws PersistenceException {
        InstrumentSearchParams instrumentSearchParam = new InstrumentSearchParams(job.getMandant(),
                sourceSystemInstrument, true);
        instrumentSearchParam.setOnlyPriceCheckInstruments(isPriceCheckInstrument);
        MgbFinderImpl finder = new MgbFinderImpl(sess);
        Collection<? extends InstrumentImpl> coll = finder.findInstruments(instrumentSearchParam);
        Iterator<? extends InstrumentImpl> i = coll.iterator();
        if (i.hasNext()) {
            InstrumentImpl inst = i.next();
            if (i.hasNext()) {
                logger.error("More than one instrument found for sourceSystemInstrument '" + sourceSystemInstrument
                        + "'");
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
        newInstr.setInstrument(sourceSystemInstrument);
        newInstr.setInstrumentName(sourceSystemInstrument);
        sess.save(newInstr);
        return newInstr;
    }
    
    @Override
    public RequestImpl buildRequest(Session sess, TradeImpl trade, String requestType) throws PersistenceException {
        if (!MarketDataRequestTypeDef.isBloombergRequest(requestType)) {
            // Only Bloomberg requests are supported
            return null;
        }
        if (!(trade instanceof SummitBondImpl)) {
            throw new PersistenceException("Must be instance of SummitBondImpl");
        }
        SummitBondImpl bondTrade = (SummitBondImpl) trade;
        BloombergRequestImpl request = (BloombergRequestImpl) sess.create(BloombergRequestImpl.class);

        request.setRequestType(bondTrade.getCurrentAutoState().getMarketDataRequestType());

        PriceCheckInstrumentImpl inst = bondTrade.getInstrument();
        if (inst != null) {
            PriceCheckCategoryImpl check = inst.getPriceCheckCategory();
            if (check != null) {
                request.setPriceCheckCategory(check);
            }
        }
        InstrumentImpl blbInst = bondTrade.getBloombergInstrument();
        if (blbInst != null) {
        	if (exoticBooks.contains(bondTrade.getBookId())) {
        		request.setRequestSources(exoticBloombergSources);
        	} if (bondTrade.isSmallCustomer()) {
        	    request.setRequestSources(smallCustomerBloombergSources);
        	}else {
        		request.setRequestSources(blbInst.getBloombergRequestSources());
        	}
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

    // to be review, seems not to be relevant for summit
    protected void setStatusFlags1() throws PersistenceException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);
            Transaction t = sess.beginTransaction();

            Query query = sess.createQuery("from "+getTradeClass().getName()+" trade "
                    + "where trade.job = :job and trade.tradeGroupId in ("
                    + "select t1.tradeGroupId from SummitBondImpl t1 " + "where t1.job = :job " + "and t1.version > 1 "
                    + ") order by trade.tradeGroupId, trade.version desc");
            logger.debug("HQL: "+query.getQueryString());
            query.setParameter("job", job);
            String oldGroupId = "";
            String oldStatus = "";
            int count = 0;
            Iterator<SummitBondImpl> it = query.iterate();
            while (it.hasNext()) {
                SummitBondImpl trade = (SummitBondImpl) sess.select(SummitBondImpl.class, it.next().getId());
                if (oldGroupId.equals(trade.getTradeGroupId())) {
                    if ("CANC".equals(oldStatus)) {
                        //TODO
                        trade.setIsIntraDayStorno(true);
                        logger.debug("Setting " + trade.getId() + " to isIntraDayStorno.");
                    } else if ("VER".equals(oldStatus)) {
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

    protected void setCurrentVersion() throws PersistenceException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);
            Transaction t = sess.beginTransaction();

            StringBuffer queryStr = new StringBuffer("select t1 from "+getTradeClass().getName()+" t1 ");
            queryStr.append("where t1.job = :job ");
            queryStr.append("and (t1.version, t1.sourceSystemReutersId) in ");
            queryStr.append("(select max(t2.version), t2.sourceSystemReutersId ");
            queryStr.append("from "+getTradeClass().getName()+" t2 ");
            queryStr.append("where t2.job = :job ");
            queryStr.append("group by t2.sourceSystemReutersId) ");

            Query query = sess.createQuery(queryStr.toString());
            logger.debug("HQL: "+query.getQueryString());
            query.setParameter("job", job);
            int count = 0;
            Iterator<SummitBondImpl> it = query.iterate();
            while (it.hasNext()) {
                SummitBondImpl trade = (SummitBondImpl) sess.select(SummitBondImpl.class, it.next().getId());
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

    /*
     * @see de.westlb.mgb.converter.SummitBondConverter#setCurrentVersion()
     */
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
                        "select t1, t2.tradeId from "+getTradeClass().getName()+" t1, "+getTradeClass().getName()+" t2 ");
                queryStr.append("where t1.sourceSystemReutersId = t2.sourceSystemReutersId ");
                queryStr.append("and t1.job = :job ");
                queryStr.append("and t2.job = :lastJob ");
                queryStr.append("and t1.isCurrentVersion = 'Y' ");
                queryStr.append("and t2.isCurrentVersion = 'Y' ");
//                queryStr.append("and t2.status = 'VER' ");
                queryStr.append("and t1.tradeGroupId is null ");
                query = sess.createQuery(queryStr.toString());
                logger.debug("HQL: "+query.getQueryString());
                query.setParameter("job", job);
                query.setParameter("lastJob", lastJob);
                Iterator<Object[]> it = query.iterate();
                while (it.hasNext()) {
                    Object[] oArray = it.next();
                    SummitBondImpl trade = (SummitBondImpl) sess.select(SummitBondImpl.class, ((SummitBondImpl)oArray[0]).getId());
                    String tradeId = (String) oArray[1];
                    trade.setTradeGroupId(tradeId);
                    boolean isTradeGroup = "VER".equals(trade.getStatus()); 
                    trade.setIsTradeGroup(isTradeGroup);
                    logger.debug("Setting " + trade.getId() + " to isTradeGroup="+isTradeGroup+" with tradeid="+tradeId);
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

    /*
     * @see de.westlb.mgb.converter.SummitBondConverter#setTradeGroups()
     */
    protected void setMccRelevantChange() throws PersistenceException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);
            Transaction t = sess.beginTransaction();

            String[] comparisonFields = { "sourceSystemInstrument", "tradeType", "subType", "category", "currency",
                    "startDay", "expireDay", "tradePrice"};

            StringBuffer queryStr = new StringBuffer("select distinct t1 from "+getTradeClass().getName()+" t1, "+getTradeClass().getName()+" t2 ");
            queryStr.append("where t1.tradeGroupId = t2.tradeId ");
            queryStr.append("and t1.job = :job ");
            queryStr.append("and t2.status = 'VER' ");
            for (int i = 0; i < comparisonFields.length; i++) {
                queryStr.append("and ((t1.").append(comparisonFields[i]).append(" = t2.").append(comparisonFields[i])
                            .append(") or (t1.").append(comparisonFields[i]).append(" is null and t2.").append(comparisonFields[i]).append(" is null)) ");
            }
            queryStr.append("and ((day(t1.tradeDate) = day(t2.tradeDate)) or (t1.tradeDate is null and t2.tradeDate is null)) ");
            Query query = sess.createQuery(queryStr.toString());
            logger.debug("HQL: "+query.getQueryString());
            query.setParameter("job", job);
            int count = 0;
            Iterator<SummitBondImpl> it = query.iterate();
            while (it.hasNext()) {
                SummitBondImpl trade = (SummitBondImpl) sess.select(SummitBondImpl.class, it.next().getId());
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

    /*
     * @see de.westlb.mgb.converter.SummitBondConverter#setTradeGroups()
     */
    protected void setIsLateDeal() throws PersistenceException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);
            Transaction t = sess.beginTransaction();

            StringBuffer queryStr = new StringBuffer("from "+getTradeClass().getName()+" t1 ");
            queryStr.append("where day(t1.systemDate) <> day(t1.tradeDate) ");
            queryStr.append("and t1.job = :job ");
            
            Query query = sess.createQuery(queryStr.toString());
            logger.debug("HQL: "+query.getQueryString());
            query.setParameter("job", job);
            int count = 0;
            Iterator<SummitBondImpl> it = query.iterate();
            while (it.hasNext()) {
                SummitBondImpl trade = (SummitBondImpl) sess.select(SummitBondImpl.class, it.next().getId());
                trade.setIsLateDeal(true);
                logger.debug("Setting " + trade.getId() + " to isLateDeal to true.");
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

    protected void setBackToBack() throws PersistenceException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);

            MgbFinderImpl finder = new MgbFinderImpl(sess);
            double backToBackPercentage = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(),MgbConfigurationDef.SAMPLE_BACK_TO_BACK_PERCENTAGE));

            Transaction t = sess.beginTransaction();

			StringBuffer queryStr = new StringBuffer("select t1 from "+getTradeClass().getName()+" t1 ");
            queryStr.append("where t1.job = :job ");
            queryStr.append("and (t1.bookId, t1.bloombergInstrument) in ");
            queryStr.append("(select t2.bookId, t2.bloombergInstrument ");
            queryStr.append("from "+getTradeClass().getName()+" t2 ");
            queryStr.append("where t2.job = :job ");
            if (booksWithBackToBackTrades.size() > 0) {
            	queryStr.append("and t2.bookId in ('").append(booksWithBackToBackTrades.get(0));
            	for (int i = 1; i < booksWithBackToBackTrades.size(); i++) {
            		queryStr.append("', '").append(booksWithBackToBackTrades.get(i));
            	}
            	queryStr.append("')");
            }
            queryStr.append("group by t2.bookId, t2.bloombergInstrument ");
            queryStr.append("having (max(t2.tradePrice) - min(t2.tradePrice)) <= :maxSpread ");
            queryStr.append("and sum(t2.volume) = 0 ");
            queryStr.append("and count(*) > 1 )");
            queryStr.append("order by t1.bookId, t1.bloombergInstrument ");

            SampleCreator sampleCreator = new SampleCreator();
            String newBackToBackId = "";
            String oldBackToBackId = "";
            boolean check = true;
            Query query = sess.createQuery(queryStr.toString());
            logger.debug("HQL: "+query.getQueryString());
            query.setParameter("job", job);
            query.setParameter("maxSpread", new Double(maxBackToBackSpread));
            int count = 0;
            Iterator<SummitBondImpl> it = query.iterate();
            while (it.hasNext()) {
                SummitBondImpl trade = (SummitBondImpl) sess.select(SummitBondImpl.class, it.next().getId());
                newBackToBackId = trade.getBookId()+trade.getBloombergInstrument().getInstrument();
                if (!newBackToBackId.equals(oldBackToBackId)) {
                	check = sampleCreator.decide(backToBackPercentage);
                }
                trade.setIsBackToBackCheck(check);
                trade.setIsBackToBack(true);
                logger.debug("Setting " + trade.getId() + " to isBackToBack. samplecheck ="+check);
                if ( ++count % BATCH_SIZE == 0 ) {
                    //flush a batch of updates and release memory:
                    sess.flush();
                    sess.clear();
                }
                oldBackToBackId = newBackToBackId; 
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

}