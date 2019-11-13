package de.westlb.mgb.converter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.ui.util.MmkInstrumentCodeFormat;
import de.westlb.mgb.model.definition.InstrumentNameDef;
import de.westlb.mgb.model.definition.MarketDataRequestStateDef;
import de.westlb.mgb.model.definition.MgbConfigurationDef;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb.mgb.model.impl.AutomaticStateImpl;
import de.westlb.mgb.model.impl.BookImpl;
import de.westlb.mgb.model.impl.DataLoadImpl;
import de.westlb.mgb.model.impl.InstrumentImpl;
import de.westlb.mgb.model.impl.IntervalPriceImpl;
import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.model.impl.LoadSummitForeignExchangeImpl;
import de.westlb.mgb.model.impl.PriceCheckCategoryImpl;
import de.westlb.mgb.model.impl.PriceCheckInstrumentImpl;
import de.westlb.mgb.model.impl.ReutersRequestImpl;
import de.westlb.mgb.model.impl.SourceSystemImpl;
import de.westlb.mgb.model.impl.StatusCalculatorImpl;
import de.westlb.mgb.model.impl.SummitForeignExchangeImpl;
import de.westlb.mgb.model.impl.TradeImpl;
import de.westlb.mgb.model.impl.finder.InstrumentSearchParams;
import de.westlb.mgb.model.impl.finder.MgbFinderImpl;
import de.westlb.mgb.model.impl.finder.PriceCheckCategorySearchParams;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.Transaction;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SummitForeignExchangeConverter extends MgbConverter {

	static Logger logger = Logger.getLogger(SummitForeignExchangeConverter.class);

	private StatusCalculatorImpl statusCalculator;

	private HashMap<String, PriceCheckInstrumentImpl> instrumentMap = null;
	
	private double bagatelleLimit = 0.0d;
	private double turnoverLimit = 0.0d;

	private final String commodityDelimiter = MmkInstrumentCodeFormat.CURRENCY_SEPARATOR;

	public SummitForeignExchangeConverter(SourceSystemImpl sourceSystemImpl) {
		super(sourceSystemImpl);
	}

	@Override
    protected Class<? extends TradeImpl> getTradeClass() {
		return SummitForeignExchangeImpl.class;
	}

	@Override
	protected Class<? extends DataLoadImpl> getLoaderTable() {
		return LoadSummitForeignExchangeImpl.class;
	}

    protected SummitForeignExchangeImpl convert(Session sess, LoadSummitForeignExchangeImpl loadData) throws PersistenceException {
		if (loadData != null) {
		    SummitForeignExchangeImpl fx = (SummitForeignExchangeImpl) sess.create(SummitForeignExchangeImpl.class);
		    fx.setSourceSystem(sourceSystemImpl);
			fx.convert(loadData);
			return fx;
		}
        throw new PersistenceException("SummitForeignExchangeLoadImpl is null.");
	}

    @Override
    protected  void checkCompleteness() throws ConverterException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);

            Query query = sess.createQuery("select distinct load.book from "+ getLoaderTable().getName()+" load where not exists (from "+BookImpl.class.getName()+" book where load.book = book.bookId)");
            logger.debug("HQL: "+query.getQueryString());
            Iterator<String> iter = query.list().iterator();
            Transaction t = sess.beginTransaction();
            while (iter.hasNext()) {
                String bookId = iter.next();
                logger.warn("Found unknown book "+bookId);
                BookImpl book = new BookImpl();
                book.setBookId(bookId);
                book.setDescription("Dummy book created for SummitForeignExchangeImpl");
                /*
                 * Note: This codepath seems to fail sometimes with a
                 * ConstraintViolationException. This could be a race condition,
                 * as on the corresponding day (2013-07-26), two import files as
                 * well as the prs_eaa.dat file contained the same new book and
                 * were imported in one parallel batch. 
                 * -- RS 2013-07-29
                 */
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

    /**
	 * @see de.westlb.mgb.converter.MgbConverter#convert(Session, DataLoadImpl)
	 */
	@Override
    protected TradeImpl convert(Session sess, DataLoadImpl loadData, JobImpl job) throws PersistenceException {
		if (loadData instanceof LoadSummitForeignExchangeImpl) {
			try {
			    SummitForeignExchangeImpl moneyMarket = convert(sess, (LoadSummitForeignExchangeImpl) loadData);
				moneyMarket.setJob(job);

				moneyMarket.setBagatelleLimit(bagatelleLimit);
				moneyMarket.setTurnoverLimit(turnoverLimit);
				double turnover = 0.0d;
				
				if (moneyMarket.getMarketRate() != 0) {
				    turnover = moneyMarket.calculateTurnover(moneyMarket.getMarketRate());

    				ReutersRequestImpl request = (ReutersRequestImpl) sess.create(ReutersRequestImpl.class);
    				request.setRequestState(MarketDataRequestStateDef.OK);
    				request.setRequestDate(moneyMarket.getTradeDate());
    				IntervalPriceImpl price = (IntervalPriceImpl) sess.create(IntervalPriceImpl.class);
                    price.setPriceMin(((LoadSummitForeignExchangeImpl) loadData).getMarketRateSpot());
                    price.setPriceMax(((LoadSummitForeignExchangeImpl) loadData).getMarketRateFwd());
    				price.setPriceDate(moneyMarket.getTradeDate());
    				request.setPriceResult(price);
    				moneyMarket.addRequest(request);
				} else {
                    turnover = moneyMarket.calculateTurnover(moneyMarket.getEodRateFX());				    
				}
                moneyMarket.setIsBagatelle(Math.abs(turnover) > 0 && Math.abs(turnover) < bagatelleLimit);
                moneyMarket.setIsOutOfTurnoverLimit(Math.abs(turnover) > turnoverLimit);

				moneyMarket.setInstrument(getExistingOrNewInstrument(sess, moneyMarket.getSourceSystemInstrument()));

				return moneyMarket;
        	} catch (Exception e) {
        		throw new PersistenceException("Error while converting loader record", e);
        	}
		}
        throw new PersistenceException("Unable to cast to SungardLoadImpl.");
	}

	/**
	 * @see de.westlb.mgb.converter.MgbConverter#preProcess()
	 */
	@Override
    protected void preProcess() throws ConverterException {
		try {
			logger.info("PreProcessing...");
			saveNewBooks();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ConverterException("Error while preProcessing: " + e.getMessage(), e);
		}
		logger.info("PreProcessing finished.");
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
			bagatelleLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.BAGATELLE_LIMIT));
			turnoverLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.TURNOVER_LIMIT));
			
			instrumentMap = new HashMap<String, PriceCheckInstrumentImpl>();
	        InstrumentSearchParams instrumentSearchParam = new InstrumentSearchParams(job.getMandant(), null, true);
	        instrumentSearchParam.setOnlyPriceCheckInstruments(true);
	        Collection<? extends InstrumentImpl> coll = finder.findInstruments(instrumentSearchParam);
	        for (Iterator<? extends InstrumentImpl> iterator = coll.iterator(); iterator.hasNext();) {
                PriceCheckInstrumentImpl instr = (PriceCheckInstrumentImpl) iterator.next();
                instrumentMap.put(instr.getInstrument(), instr);
            }
			
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
			Iterator<SummitForeignExchangeImpl> it = query.iterate();
			while ( it.hasNext()) {
			    SummitForeignExchangeImpl trade = (SummitForeignExchangeImpl) sess.select(SummitForeignExchangeImpl.class, it.next().getId());

				AutomaticStateImpl autoState = statusCalculator.calculateStatus(trade, job.getMandant());
				setCurrentState(sess, trade, autoState, statusCalculator.getLastInternalCalculatorStateComment());

                if ( ++count % BATCH_SIZE == 0 ) {
                    // flush a batch of updates and release memory:
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

	private PriceCheckInstrumentImpl getExistingOrNewInstrument(Session sess, String sourceSystemInstrument) throws PersistenceException {
	    if (instrumentMap.containsKey(sourceSystemInstrument)) {
	        PriceCheckInstrumentImpl p =  instrumentMap.get(sourceSystemInstrument);
	        return (PriceCheckInstrumentImpl)sess.select(PriceCheckInstrumentImpl.class, p.getId());
	    }
        PriceCheckInstrumentImpl newInstr = (PriceCheckInstrumentImpl) sess.create(PriceCheckInstrumentImpl.class);
        newInstr.setInstrument(sourceSystemInstrument);
        newInstr.setInstrumentName(sourceSystemInstrument);
        newInstr.setPriceCheckCategory(findPriceCheckCategory(sess, sourceSystemInstrument));
        instrumentMap.put(sourceSystemInstrument, newInstr);
        return newInstr;
	}


	protected void updateGeneratedCrossCurrency() throws PersistenceException {
		Session sess = null;
		try {
			sess = getSession(job.getMandant(), importUser);
			MgbFinderImpl finder = new MgbFinderImpl(sess);
			PriceCheckCategorySearchParams param = new PriceCheckCategorySearchParams();
			param.setMandant(job.getMandant());
			param.setName("USD" + commodityDelimiter + "%");
			HashMap<String, PriceCheckCategoryImpl> usRates = new HashMap<String, PriceCheckCategoryImpl>();

			Collection<PriceCheckCategoryImpl> col = finder.findPriceCheckCategories(param);
			Iterator<PriceCheckCategoryImpl> it = col.iterator();
			while (it.hasNext()) {
				PriceCheckCategoryImpl priceCheckCategory = it.next();
				if (priceCheckCategory.getName().length() > 6) {
					String key = priceCheckCategory.getName().substring(4, 7);
					usRates.put(key, priceCheckCategory);
				} else {
					throw new PersistenceException("Found a suspicious PriceCheckCategory: " + priceCheckCategory);
				}

			}

			param = new PriceCheckCategorySearchParams();
			param.setMandant(job.getMandant());
			param.setName("%" + commodityDelimiter + "%" + InstrumentNameDef.CROSS_CURRENCY_COMMENT);

			Transaction t = sess.beginTransaction();
			Collection<PriceCheckCategoryImpl> col1 = finder.findPriceCheckCategories(param);
			Iterator<PriceCheckCategoryImpl> iter = col1.iterator();
			int count = 0;
			while (iter.hasNext()) {
			    PriceCheckCategoryImpl priceCheckCategory = (PriceCheckCategoryImpl) sess.select(PriceCheckCategoryImpl.class, iter.next().getId());
				if (priceCheckCategory.getName().length() > 6) {
					String key1 = priceCheckCategory.getName().substring(0, 3);
					String key2 = priceCheckCategory.getName().substring(4, 7);
					PriceCheckCategoryImpl cat1 = usRates.get(key1);
					PriceCheckCategoryImpl cat2 = usRates.get(key2);
					priceCheckCategory.setPriceTolerancePercent(cat1.getPriceTolerancePercent() + cat2.getPriceTolerancePercent());
					priceCheckCategory.setTimeToleranceMinutes(cat1.getTimeToleranceMinutes() + cat2.getTimeToleranceMinutes());
				} else {
					throw new PersistenceException("Found a suspicious PriceCheckCategory: " + priceCheckCategory);
				}
                if ( ++count % BATCH_SIZE == 0 ) {
                    // flush a batch of updates and release memory:
                    sess.flush();
                    sess.clear();
                }
			}
			t.commit();
		} catch (PersistenceException e) {
			logger.error(e.getMessage(), e);
			throw new PersistenceException("Error while updating crosscurrency PriceCheckCategory: " + e.getMessage(), e);
		} finally {
			try {
                if (sess != null) {
                    sess.close();
                }
			} catch (PersistenceException pe) {
			}
		}
	}


	private PriceCheckCategoryImpl findPriceCheckCategory(Session sess, String instrument) throws PersistenceException {
		PriceCheckCategoryImpl priceCheckCategory = null;
		MgbFinderImpl finder = new MgbFinderImpl(sess);
		PriceCheckCategorySearchParams param = new PriceCheckCategorySearchParams();
		param.setMandant(job.getMandant());
		param.setName(instrument);

		Collection<PriceCheckCategoryImpl> col = finder.findPriceCheckCategories(param);
		Iterator<PriceCheckCategoryImpl> it = col.iterator();
		if (it.hasNext()) {
			priceCheckCategory = it.next();
		} else {
			logger.warn("Unknown PriceCheckCategory: " + instrument);
			if (instrument.indexOf(commodityDelimiter) > -1) {
				String curr1 = instrument.substring(0, 3);
				String curr2 = instrument.substring(4, 7);
				if (!"USD".equals(curr1)) {
					PriceCheckCategoryImpl cat1 = findPriceCheckCategory(sess, "USD" + commodityDelimiter + curr1);
					PriceCheckCategoryImpl cat2 = null;
					if ("USD".equals(curr2)) {
						cat2 = new PriceCheckCategoryImpl();
						cat2.setPriceTolerancePercent(0.0d);
						cat2.setTimeToleranceMinutes(0);
					} else {
						cat2 = findPriceCheckCategory(sess, "USD" + commodityDelimiter + curr2);
					}
					if (cat1 != null && cat2 != null) {
						priceCheckCategory = (PriceCheckCategoryImpl) sess.create(PriceCheckCategoryImpl.class);
						priceCheckCategory.setName(curr1 + commodityDelimiter + curr2 + InstrumentNameDef.CROSS_CURRENCY_COMMENT);
						priceCheckCategory.setPriceTolerancePercent(cat1.getPriceTolerancePercent() + cat2.getPriceTolerancePercent());
						priceCheckCategory.setTimeToleranceMinutes(cat1.getTimeToleranceMinutes() + cat2.getTimeToleranceMinutes());
					}
				}
			} else {
//                throw new PersistenceException("Found a suspicious PriceCheckCategory: " + instrument);
                logger.error("Found a suspicious PriceCheckCategory: " + instrument);
			}

		}
		if (it.hasNext()) {
			throw new PersistenceException("More than one PriceCheckCategory found for: " + instrument);
		}
		return priceCheckCategory;
	}

}
