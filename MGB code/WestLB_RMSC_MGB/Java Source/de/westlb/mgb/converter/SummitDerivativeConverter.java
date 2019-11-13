/*
 * Created on 22.06.2007
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.converter;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import de.westlb.mgb.model.definition.JobStateDef;
import de.westlb.mgb.model.definition.MgbConfigurationDef;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb.mgb.model.impl.AssetImpl;
import de.westlb.mgb.model.impl.AutomaticStateImpl;
import de.westlb.mgb.model.impl.BookImpl;
import de.westlb.mgb.model.impl.DataLoadImpl;
import de.westlb.mgb.model.impl.InstrumentImpl;
import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.model.impl.PriceCheckInstrumentImpl;
import de.westlb.mgb.model.impl.SourceSystemImpl;
import de.westlb.mgb.model.impl.StatusCalculatorImpl;
import de.westlb.mgb.model.impl.SummitAmendImpl;
import de.westlb.mgb.model.impl.SummitBondImpl;
import de.westlb.mgb.model.impl.SummitDerivativeImpl;
import de.westlb.mgb.model.impl.LoadSummitDerivativeImpl;
import de.westlb.mgb.model.impl.TradeImpl;
import de.westlb.mgb.model.impl.finder.InstrumentSearchParams;
import de.westlb.mgb.model.impl.finder.MgbFinderImpl;
import de.westlb.mgb.model.impl.finder.SearchParams;
import de.westlb.mgb.model.impl.finder.TradeSearchParams;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.Transaction;

/**
 * @author D055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SummitDerivativeConverter extends MgbConverter {

	static Logger logger = Logger.getLogger(SummitDerivativeConverter.class);

    private StatusCalculatorImpl statusCalculator;

    private double bondoptBagatelleLimit = 0.0d;
    private double irgBagatelleLimit = 0.0d;
    private double swapBagatelleLimit = 0.0d;
    private double fraBagatelleLimit = 0.0d;
    private double swaptionBagatelleLimit = 0.0d;
    private double fxoptBagatelleLimit = 0.0d;
    private double exoticBagatelleLimit = 0.0d;
    private double turnoverLimit = 0.0d;

	public SummitDerivativeConverter(SourceSystemImpl sourceSystemImpl) {
		super(sourceSystemImpl);
	}

	@Override
    protected Class<? extends TradeImpl> getTradeClass() {
		return SummitDerivativeImpl.class;
	}

	@Override
	protected Class<? extends DataLoadImpl> getLoaderTable() {
		return LoadSummitDerivativeImpl.class;
	}

    protected SummitDerivativeImpl convert(Session sess, LoadSummitDerivativeImpl loadData) throws PersistenceException {
		SummitDerivativeImpl summitDerivative = (SummitDerivativeImpl) sess.create(SummitDerivativeImpl.class);
		summitDerivative.setSourceSystem(sourceSystemImpl);
		summitDerivative.convert(loadData);
		return summitDerivative;
	}
 
    @Override
    protected  void checkCompleteness() throws ConverterException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);

            Query query = sess.createQuery("select distinct load.book from "+ getLoaderTable().getName() +" load where not exists (from "+BookImpl.class.getName()+" book where load.book = book.bookId)");
            logger.debug("HQL: "+query.getQueryString());
            Iterator<String> iter = query.list().iterator();
            Transaction t = sess.beginTransaction();
            while (iter.hasNext()) {
                String bookId = iter.next();
                logger.warn("Found unknown book "+bookId);
                BookImpl book = new BookImpl();
                book.setBookId(bookId);
                book.setDescription("Dummy book created for SummitDerivativeImpl");
                sess.save(book);
            }
            t.commit();
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



	/* 
	 * @see de.westlb.mgb.converter.AbstractMgbConverter#convert(de.westlb.mgb.persistence.Session, de.westlb.mgb.model.impl.DataLoadImpl, de.westlb.mgb.model.impl.JobImpl)
	 */
	@Override
    protected TradeImpl convert(Session sess, DataLoadImpl loadData, JobImpl job) throws PersistenceException {
		if (loadData instanceof LoadSummitDerivativeImpl) {
			try {
				SummitDerivativeImpl derivativeTrade = null;
				MgbFinderImpl finder = new MgbFinderImpl(sess);
				TradeSearchParams params = new TradeSearchParams(job.getMandant(), new JobImpl[]{job}, null, null);
				params.setTradeIds(new String[]{((LoadSummitDerivativeImpl)loadData).getVersionedTradeId()});
				Iterator<TradeImpl> iter = finder.findTrades(params).iterator();
				if (iter.hasNext()) {
					derivativeTrade = (SummitDerivativeImpl)iter.next();
					if (iter.hasNext()) {
						throw new PersistenceException("Found doublicate of SummitDerivativeImpl "+((LoadSummitDerivativeImpl)loadData).getTradeId()+" in job "+job.getId());
					}
				} else {
					derivativeTrade = convert(sess,(LoadSummitDerivativeImpl)loadData);
				}
				AssetImpl asset = (AssetImpl)sess.create(AssetImpl.class);
				asset.convert(derivativeTrade, (LoadSummitDerivativeImpl)loadData);
				derivativeTrade.addAsset(asset);
				derivativeTrade.setJob(job);
				return derivativeTrade;
        	} catch (Exception e) {
        		throw new PersistenceException("Error while converting loader record", e);
        	}				
		}
        throw new PersistenceException("Unable to cast to SummitDerivativeLoadImpl.");
	}

	/* (non-Javadoc)
	 * @see de.westlb.mgb.converter.AbstractMgbConverter#converterInit()
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
            bondoptBagatelleLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.BONDOPT_BAGATELLE_LIMIT));
            irgBagatelleLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.IRG_BAGATELLE_LIMIT));
            swapBagatelleLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.SWAP_BAGATELLE_LIMIT));
            fraBagatelleLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.FRA_BAGATELLE_LIMIT));
            swaptionBagatelleLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.SWAPTION_BAGATELLE_LIMIT));
            fxoptBagatelleLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.FXOPT_BAGATELLE_LIMIT));
            exoticBagatelleLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.EXOTIC_BAGATELLE_LIMIT));
            turnoverLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.DERIVATIVE_TURNOVER_LIMIT));
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

	/*
	 * @see de.westlb.mgb.converter.AbstractMgbConverter#preProcess()
	 */
	@Override
    protected void preProcess() throws ConverterException {
		logger.info("PreProcessing...");
		try {
//          the initial value for mccRelevantChange must be true            

		 // neue art amendments zu erkennen
		    setTradeGroups(false);
		    linkTradesToAmendments();
		    setTradeAmendGroups();
		    setMccRelevantAmendChange();
//          the initial value for mccRelevantChange must be true            
		    
			saveNewTrader();
			saveNewBooks();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ConverterException("Error while preProcessing: " + e.getMessage(), e);
		}
		logger.info("PreProcessing finished.");
	}


    /* (non-Javadoc)
	 * @see de.westlb.mgb.converter.AbstractMgbConverter#process()
	 */
	@Override
    protected void process() throws ConverterException {
	       Session sess = null;
	       String instrumentDelimiter = "_";
	       String instrumentStandard = instrumentDelimiter+"STD";
	       String instrumentNonStandard = instrumentDelimiter+"NON";
	        try {
	            logger.info("MainProcessing...");
	            sess = getSession(job.getMandant(), importUser);

	            statusCalculator = new StatusCalculatorImpl();
	            statusCalculator.loadConfiguration(sess, job.getSourceSystem(), StateCodeTypeDef.FIRST_STAGE);

	            Transaction t = sess.beginTransaction();
	            Query query = sess.createQuery("from "+getTradeClass().getName()+" trade where trade.job = :job");
	            logger.debug("HQL: "+query.getQueryString());
	            query.setParameter("job", job);
	            int count = 0;
	            Iterator<SummitDerivativeImpl> it = query.iterate();
	            while (it.hasNext()) {
	            	SummitDerivativeImpl trade = (SummitDerivativeImpl) sess.select(SummitDerivativeImpl.class, it.next().getId());

	            	if (trade.isIrg()) { // IRG
	            		trade.setTurnover(trade.getPrice() + trade.getPremium());
	            		if (trade.isStandard()) {
		            		trade.setDeviation(trade.getTurnover()/nonZero(trade.getVega()));
	            			trade.setSourceSystemInstrument(trade.getTradeType()+instrumentDelimiter+trade.getTradeSubType()+instrumentDelimiter+trade.getCurrency()+instrumentStandard);
	            		} else {
		            		trade.setDeviation(trade.getTurnover()/nonZero(trade.getVolume()));
	            			trade.setSourceSystemInstrument(trade.getTradeType()+instrumentDelimiter+trade.getTradeSubType()+instrumentDelimiter+trade.getCurrency()+instrumentNonStandard);
	            		}
	            		trade.setIsBagatelle(Math.abs(trade.getTurnover()) < irgBagatelleLimit);
	            	} else if (trade.isSwap()) { // SWAP
	            		trade.setTurnover(trade.getPrice());
	            		if (trade.isStandard()) {
		            		trade.setDeviation(trade.getTurnover()/nonZero(trade.getDelta()));
	            			trade.setSourceSystemInstrument(trade.getTradeType()+instrumentDelimiter+trade.getTradeSubType()+instrumentDelimiter+trade.getCurrency()+instrumentStandard);
	            		} else {
		            		trade.setDeviation(trade.getTurnover()/nonZero(trade.getVolume()));
	            			trade.setSourceSystemInstrument(trade.getTradeType()+instrumentDelimiter+trade.getTradeSubType()+instrumentDelimiter+trade.getCurrency()+instrumentNonStandard);
	            		}
	            		trade.setIsBagatelle(Math.abs(trade.getTurnover()) < swapBagatelleLimit);
	            	} else if (trade.isFra()) { //FRA
	            		trade.setTurnover(trade.getPrice());
	            		trade.setDeviation(trade.getTurnover()/nonZero(trade.getDelta()));
            			trade.setSourceSystemInstrument(trade.getTradeType()+instrumentDelimiter+trade.getCurrency()+instrumentStandard);
	            		trade.setIsBagatelle(Math.abs(trade.getTurnover()) < fraBagatelleLimit);
	            	} else if (trade.isSwoption()) { // SWAPTION
	            		trade.setTurnover(trade.getPrice() + trade.getPremium());
	            		if (trade.isStandard()) {
		            		trade.setDeviation(trade.getTurnover()/nonZero(trade.getVega()));
	            			trade.setSourceSystemInstrument(trade.getTradeType()+instrumentDelimiter+trade.getTradeSubType()+instrumentDelimiter+trade.getCurrency()+instrumentStandard);
	            		} else {
		            		trade.setDeviation(trade.getTurnover()/nonZero(trade.getVolume()));
	            			trade.setSourceSystemInstrument(trade.getTradeType()+instrumentDelimiter+trade.getTradeSubType()+instrumentDelimiter+trade.getCurrency()+instrumentNonStandard);
	            		}
	            		trade.setIsBagatelle(Math.abs(trade.getTurnover()) < swaptionBagatelleLimit);
	            	} else if (trade.isFxoption()) { // FXOPT
	            		trade.setTurnover(trade.getPrice() + trade.getPremium());
	            		if (trade.isStandard()) {
		            		trade.setDeviation(trade.getTurnover()/nonZero(trade.getVega()));
	            			trade.setSourceSystemInstrument(trade.getTradeType()+instrumentDelimiter+trade.getSingleAsset().getPayCurrency()+instrumentDelimiter+trade.getSingleAsset().getReceiveCurrency()+instrumentDelimiter+trade.getSingleAsset().getStyle()+instrumentStandard);
	            		} else {
		            		trade.setDeviation(trade.getTurnover()/nonZero(trade.getVolume()));
	            			trade.setSourceSystemInstrument(trade.getTradeType()+instrumentDelimiter+trade.getSingleAsset().getPayCurrency()+instrumentDelimiter+trade.getSingleAsset().getReceiveCurrency()+instrumentDelimiter+trade.getSingleAsset().getStyle()+instrumentNonStandard);
	            		}
	            		trade.setIsBagatelle(Math.abs(trade.getTurnover()) < fxoptBagatelleLimit);
	            	} else if (trade.isExotic()) { // EXOTIC
	            		trade.setTurnover(trade.getPrice() + trade.getPremium());
	            		if (trade.isStandard()) {
		            		trade.setDeviation(trade.getTurnover()/nonZero(trade.getVega()+trade.getDelta()));
	            			trade.setSourceSystemInstrument(trade.getTradeType()+instrumentDelimiter+trade.getCurrency()+instrumentStandard);
	            		} else {
		            		trade.setDeviation(trade.getTurnover()/nonZero(trade.getDelta()));
	            			trade.setSourceSystemInstrument(trade.getTradeType()+instrumentDelimiter+trade.getCurrency()+instrumentNonStandard);
	            		}
	            		trade.setIsBagatelle(Math.abs(trade.getTurnover()) < exoticBagatelleLimit);
                    } else if (trade.isBondoption()) { // BONDOP
                        trade.setTurnover(trade.getPrice() + trade.getPremium());
                        if (trade.isStandard()) {
                            trade.setDeviation(trade.getTurnover()/nonZero(trade.getVega()));
                            trade.setSourceSystemInstrument(trade.getTradeType()+instrumentDelimiter+trade.getCurrency()+instrumentDelimiter+trade.getSingleAsset().getStyle()+instrumentStandard);
                        } else {
                            trade.setDeviation(trade.getTurnover()/nonZero(trade.getVolume()));
                            trade.setSourceSystemInstrument(trade.getTradeType()+instrumentDelimiter+trade.getCurrency()+instrumentDelimiter+trade.getSingleAsset().getStyle()+instrumentNonStandard);
                        }
                        trade.setIsBagatelle(Math.abs(trade.getTurnover()) < bondoptBagatelleLimit);
	            	} else {
	            		logger.warn("Unknown trade type "+trade.getTradeType());
	            	}

	            	if (trade.isNpvAmended()) {
	            	    // the npv as base for the calculation above, can not be used for amended traded
	            	    // instead the deviation is set to zero
	            	    trade.setDeviation(0);
	            	    trade.setTurnover(0);
	            	    trade.setIsBagatelle(false);
	            	}
	            	trade.setIsHighTurnover(Math.abs(trade.getTurnover()) > turnoverLimit);
	            	
	           		trade.setInstrument((PriceCheckInstrumentImpl) getExistingOrNewInstrument(sess, trade.getSourceSystemInstrument(), true));

	                AutomaticStateImpl autoState = statusCalculator.calculateStatus(trade, job.getMandant());
	                setCurrentState(sess, trade, autoState, statusCalculator.getLastInternalCalculatorStateComment());

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

	private double nonZero(double value) {
		if (value == 0.0) {
			logger.warn("taking default value in devision by zero");
			return 0.001;
		}
        return value;
	}
    private InstrumentImpl getExistingOrNewInstrument(Session sess, String sourceSystemInstrument,
            boolean isPriceCheckInstrument) throws PersistenceException {
        InstrumentSearchParams instrumentSearchParam = new InstrumentSearchParams(job.getMandant(), sourceSystemInstrument, true);
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
        InstrumentImpl newInstr = new PriceCheckInstrumentImpl();
        newInstr.setEnabled(true);
        newInstr.setInstrument(sourceSystemInstrument);
        newInstr.setInstrumentName(sourceSystemInstrument);
        sess.save(newInstr);
        return newInstr;
    }

    protected void setTradeGroups(boolean setIsTradeGroupFlag) throws PersistenceException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);
            Transaction t = null;
            Query query = sess
                    .createQuery("select distinct job from "+JobImpl.class.getName()+" job,"+
                            SummitDerivativeImpl.class.getName()+" d "+
                            " where d in elements(job.trades)"+
                            " and job.status in ('"+JobStateDef.JOB_OK_STATUS+"', '"+JobStateDef.JOB_CLOSED_STATUS+"', '"+JobStateDef.JOB_SPK_CLOSED_STATUS+"') and job <> :job and job.archived = 'N' order by job desc");
            logger.debug("HQL: "+query.getQueryString());
            query.setParameter("job", job);
            JobImpl lastJob = null;
            List<JobImpl> jobList = query.list();
            logger.debug(jobList.size()+" previous job(s) found");
            Iterator<JobImpl> iter = jobList.iterator();
            if (!iter.hasNext()) {
                logger.debug("No previous job found");
            }
            int count = 0;
            while (iter.hasNext()) {
                t = sess.beginTransaction();
                lastJob = iter.next();
                StringBuffer queryStr = new StringBuffer(
                        "select t1, t2.tradeId from "+getTradeClass().getName()+" t1, "+SummitDerivativeImpl.class.getName()+" t2 ");
                queryStr.append("where t1.sourceSystemReutersId = t2.sourceSystemReutersId ");
                queryStr.append("and t1.tradeId != t2.tradeId ");
                queryStr.append("and t1.job = :job ");
                queryStr.append("and t2.job = :lastJob ");
                queryStr.append("and t2.tradeStatus = 'VER' ");
                queryStr.append("and t1.tradeGroupId is null ");
                query = sess.createQuery(queryStr.toString());
                logger.debug("HQL: "+query.getQueryString());
                query.setParameter("job", job);
                query.setParameter("lastJob", lastJob);
                Iterator<Object[]> it = query.iterate();
                while (it.hasNext()) {
                    Object[] oArray = it.next();
                    TradeImpl trade = (TradeImpl) sess.select(TradeImpl.class, ((TradeImpl)oArray[0]).getId());
                    String tradeId = (String) oArray[1];
                    trade.setTradeGroupId(tradeId);
                    trade.setIsTradeGroup(setIsTradeGroupFlag);
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

    protected void setMccRelevantAmendChange() throws ConverterException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);
            Transaction t = sess.beginTransaction();
            SummitAmendComparatorImpl amendComparator = new SummitAmendComparatorImpl(sess, job.getMandant()); 
            Query query =
                sess.createQuery(
                    "select newTrade, amend from "+getTradeClass().getName()+" newTrade, "+SummitAmendImpl.class.getName()+" amend, "+SummitDerivativeImpl.class.getName()+" oldTrade "+
                    "where newTrade.job = :job " +
                    "and newTrade.tradeId = amend.tradeIdNew " +
                    "and oldTrade.tradeId = amend.tradeIdOld " +
                    "order by newTrade.tradeId, amend.tradeVersionNew"
                     );
            logger.debug("HQL: "+query.getQueryString());
            query.setParameter("job", job);
            String lastTrade = "";
            int count=0;
            Iterator<Object[]> it = query.iterate();
            while (it.hasNext()) {
                Object[] o = it.next();
                SummitDerivativeImpl trade = (SummitDerivativeImpl) sess.select(SummitDerivativeImpl.class,((SummitDerivativeImpl)o[0]).getId());
                String currentTrade = trade.getTradeId();
                SummitAmendImpl amend = (SummitAmendImpl) sess.select(SummitAmendImpl.class,((SummitAmendImpl)o[1]).getId());                
                // save the relevance if a trade is checked the first time or
                // if it is checked again and is set to false
                if (!lastTrade.equals(currentTrade)
                        || (lastTrade.equals(currentTrade) && !trade.getIsMccRelevantChange())) {
                    boolean hasRelevantAmendments = amendComparator.hasRelevantAmendments(amend); 
                    trade.setIsMccRelevantChange(hasRelevantAmendments);
                    logger.debug("Setting " + trade.getId() + " to isTradeGroup(amended) to "+hasRelevantAmendments);
                    count++;
                }
                if (SummitAmendImpl.FIELD_NPV.equals(amend.getFieldModified()) && amend.getFieldValueNew() != null) {
                    try {
                        trade.setAmendmentNpv(Double.parseDouble(amend.getFieldValueNew()));
                        trade.setAmendmentNpvChange(trade.getAmendmentNpvChange() + Double.parseDouble(amend.getFieldValueChange()));
                        logger.debug("Setting amendment npv for " + trade.getId());
                    } catch (NumberFormatException nfe) {
                        logger.error(nfe.getMessage(),nfe);
                    }
                }
                if ( count % BATCH_SIZE == 0 ) {
                    //flush a batch of updates and release memory:
                    sess.flush();
                    sess.clear();
                }
                lastTrade = currentTrade;
            }
            t.commit();

        } catch (PersistenceException e) {
            logger.error(e.getMessage(), e);
            throw new ConverterException(e);
        } finally {
            try {
                if (sess != null) {
                    sess.close();
                }
            } catch (PersistenceException he) {
            }
        }
    }

    protected void linkTradesToAmendments() throws ConverterException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);
            Transaction t = sess.beginTransaction();
            Query query =
                sess.createQuery(
                    "select newTrade.tradeId, oldTrade.tradeId, amend from "+getTradeClass().getName()+" newTrade, "+SummitAmendImpl.class.getName()+" amend, "+SummitDerivativeImpl.class.getName()+" oldTrade "+
                    "where newTrade.job = :job " +
                    "and newTrade.tradeGroupId = oldTrade.tradeId " +
                    "and newTrade.sourceSystemReutersId = amend.tradeId " +
                    "and newTrade.version >= amend.tradeVersionNew " +
                    "and oldTrade.version <= amend.tradeVersionOld " +
                    "order by newTrade.tradeId"
                     );
            logger.debug("HQL: "+query.getQueryString());
            query.setParameter("job", job);
            int count=0;
            Iterator<Object[]> it = query.iterate();
            while (it.hasNext()) {
                Object[] o = it.next();
                String tradeIdNew = (String)o[0];
                String tradeIdOld = (String)o[1];
                SummitAmendImpl amend = (SummitAmendImpl) sess.select(SummitAmendImpl.class,((SummitAmendImpl)o[2]).getId());                

                amend.setTradeIdNew(tradeIdNew);
                amend.setTradeIdOld(tradeIdOld);
                logger.debug("updated amendmend "+amend.getId()+" with trade "+tradeIdNew+" and "+tradeIdOld);
                sess.save(amend);
                count++;
                if ( count % BATCH_SIZE == 0 ) {
                    //flush a batch of updates and release memory:
                    sess.flush();
                    sess.clear();
                }
            }
            t.commit();

            // trades with no old version
            t = sess.beginTransaction();
            query =
                sess.createQuery(
                    "select newTrade.tradeId, amend from "+getTradeClass().getName()+" newTrade, "+SummitAmendImpl.class.getName()+" amend "+
                    "where newTrade.job = :job " +
                    "and newTrade.tradeGroupId is null " +
                    "and newTrade.sourceSystemReutersId = amend.tradeId " +
                    "and newTrade.version >= amend.tradeVersionNew " +
                    "order by newTrade.tradeId"
                     );
            logger.debug("HQL: "+query.getQueryString());
            query.setParameter("job", job);
            count=0;
            Iterator<Object[]> it2 = query.iterate();
            while (it2.hasNext()) {
                Object[] o = it2.next();
                String tradeIdNew = (String)o[0];
                SummitAmendImpl amend = (SummitAmendImpl) sess.select(SummitAmendImpl.class,((SummitAmendImpl)o[1]).getId());                

                amend.setTradeIdNew(tradeIdNew);
                logger.debug("updated amendmend "+amend.getId()+" with trade "+tradeIdNew);
                sess.save(amend);
                count++;
                if ( count % BATCH_SIZE == 0 ) {
                    //flush a batch of updates and release memory:
                    sess.flush();
                    sess.clear();
                }
            }
            t.commit();
        } catch (PersistenceException e) {
            logger.error(e.getMessage(), e);
            throw new ConverterException(e);
        } finally {
            try {
                if (sess != null) {
                    sess.close();
                }
            } catch (PersistenceException he) {
            }
        }
    }

}
