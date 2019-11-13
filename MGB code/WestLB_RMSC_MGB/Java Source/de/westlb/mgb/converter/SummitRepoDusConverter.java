package de.westlb.mgb.converter;

import java.util.HashMap;

import org.apache.log4j.Logger;

import de.westlb.mgb.model.definition.MandantDef;
import de.westlb.mgb.model.definition.MgbConfigurationDef;
import de.westlb.mgb.model.impl.DataLoadImpl;
import de.westlb.mgb.model.impl.InstrumentImpl;
import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.model.impl.PriceCheckInstrumentImpl;
import de.westlb.mgb.model.impl.SourceSystemImpl;
import de.westlb.mgb.model.impl.SummitRepoImpl;
import de.westlb.mgb.model.impl.LoadSummitRepoImpl;
import de.westlb.mgb.model.impl.TradeImpl;
import de.westlb.mgb.model.impl.finder.MgbFinderImpl;
import de.westlb.mgb.model.impl.finder.SearchParams;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.StoreSingleton;

/**
 * @author D055625
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class SummitRepoDusConverter extends SummitRepoConverter {

	static Logger logger = Logger.getLogger(SummitRepoDusConverter.class);

    protected HashMap<String, String> currencyCategoryBondMapping;

    protected HashMap<String, String> ratingCategoryBondMapping;

    protected HashMap<String, String> tradeTypeCategoryBondMapping;
    
    protected MandantImpl bondMandant;
    
    protected double automatischeWertpapierLeiheBagatelleLimit = 0d;

    protected double bilateraleWertpapierLeiheBagatelleLimit = 0d;

    public SummitRepoDusConverter(SourceSystemImpl sourceSystemImpl) {
		super(sourceSystemImpl);
	}

    @Override
    protected void converterInit() throws ConverterException {
        Session sess = null;
        try {
            logger.info("Initialising converter...");
            sess = getSession(job.getMandant(), importUser);

            MgbFinderImpl finder = new MgbFinderImpl(sess);
            SearchParams param = new SearchParams();
            param.setMandant(job.getMandant());
            tradeTypeCategoryMapping = finder.findTradeTypeCategoryMappings(param);
    		String bondMandantCode = finder.findMgbConfigurationValue(job.getMandant(), MgbConfigurationDef.REPO_BOND_MANDANT);
    		bondMandant = new MandantImpl(bondMandantCode);

            defaultBloombergSources = finder.findMgbConfigurationValue(job.getMandant(),
                    MgbConfigurationDef.BOND_BLOOMBERG_DEFAULT_SOURCES);
            bagatelleLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(),
                    MgbConfigurationDef.REPO_BAGATELLE_LIMIT));
            secLendingBagatelleLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(),
                    MgbConfigurationDef.SELO_BAGATELLE_LIMIT));
            automatischeWertpapierLeiheBagatelleLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(),
                    MgbConfigurationDef.AWPL_BAGATELLE_LIMIT));
            bilateraleWertpapierLeiheBagatelleLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(),
                    MgbConfigurationDef.BWPL_BAGATELLE_LIMIT));
            turnoverLimit = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(),
                    MgbConfigurationDef.REPO_TURNOVER_LIMIT));
            openEndDuration = Double.parseDouble(finder.findMgbConfigurationValue(job.getMandant(),
                    MgbConfigurationDef.REPO_OPEN_END_DURATION));
            
            SearchParams bondParam = new SearchParams();
            // Requesting with a bond Mandant
            bondParam.setMandant(bondMandant);
            currencyCategoryBondMapping = finder.findCurrencyCategoryMappings(bondParam);
            ratingCategoryBondMapping = finder.findRatingCategoryMappings(bondParam);
            tradeTypeCategoryBondMapping = finder.findTradeTypeCategoryMappings(bondParam);

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
     * @see de.westlb.mgb.converter.MgbConverter#convert(Session, DataLoadImpl)
     */
    @Override
    protected TradeImpl convert(Session sess, DataLoadImpl loadData, JobImpl job) throws PersistenceException {
        if (loadData instanceof LoadSummitRepoImpl) {
        	try {
        	    SummitRepoImpl summitRepo = (SummitRepoImpl) sess.create(SummitRepoImpl.class);
        	    summitRepo.setSourceSystem(sourceSystemImpl);

        	    // preset the open end duration in days
        		summitRepo.setDays(openEndDuration);
        		summitRepo.convert((LoadSummitRepoImpl) loadData);
        		summitRepo.setJob(job);

        		String instr = buildSourceInstrument(summitRepo.getUnderlyingValGroup(), summitRepo.isSparkasse(), summitRepo.isAutomatischeWertpapierLeihe(), summitRepo.isBilateraleWertpapierLeihe());
        		summitRepo.setInstrument((PriceCheckInstrumentImpl) getExistingOrNewInstrument(sess, instr, true, job.getMandant()));
                summitRepo.setRateDiff(summitRepo.calcRateDifference());// depends on the instrument
                summitRepo.setProfitLossDiff(summitRepo.calcProfitLossDiff());// depends on the instrument
        		summitRepo.setProfitLossDiffEuro(summitRepo.getProfitLossDiff()*summitRepo.getForeignExchangeRate());// depends on the instrument

                summitRepo.setExceedsMaxPlDiff(Math.abs(summitRepo.getProfitLossDiffEuro()) > turnoverLimit);
                summitRepo.setIsBagatelle(Math.abs(summitRepo.getProfitLossDiffEuro()) < getBagatelleLimit(summitRepo)
                    && Math.abs(summitRepo.getProfitLossDiffEuro()) > 0);

                // special treatment for the bond underlying
        		if (((LoadSummitRepoImpl) loadData).getIsin() != null) {
        			InstrumentImpl blbInstr = getExistingOrNewInstrument(sess, ((LoadSummitRepoImpl) loadData).getIsin(), false, job.getMandant());
        			summitRepo.setBloombergInstrument(blbInstr);
        		}
        		String bondInstr = buildBondSourceInstrument(summitRepo.getCurrency(), "A", summitRepo.getUnderlyingCategory(), null, summitRepo.getInstrumentCode());
        		summitRepo.setBondInstrument((PriceCheckInstrumentImpl) getExistingOrNewInstrument(sess, bondInstr, true, bondMandant));
                return summitRepo;
        	} catch (Exception e) {
        		throw new PersistenceException("Error while converting loader record", e);
        	}
        }
        throw new PersistenceException("Unable to cast to SummitRepoLoadImpl.");
    }

    @Override
    protected double getBagatelleLimit(SummitRepoImpl repoTrade) {
        if (repoTrade.isSparkasse()) {
            if (repoTrade.isAutomatischeWertpapierLeihe()) {
                return automatischeWertpapierLeiheBagatelleLimit;
            } else if (repoTrade.isBilateraleWertpapierLeihe()) {
                return bilateraleWertpapierLeiheBagatelleLimit;
            }
        } else if (repoTrade.isSecurityLending()) {
            return secLendingBagatelleLimit;
    	} 
    	return bagatelleLimit;
    }
    
    private String buildBondSourceInstrument(String currency, String rating, String tradeType, String struct, String instrumentName) {
        String delim = "-";

        if (SummitRepoDusConverter.isStruct(struct, instrumentName)) {
        	return struct;
        }
        String currencyCategory = currencyCategoryBondMapping.get(currency);
        if (currencyCategory == null) {
            currencyCategory = "DEFAULT";
        }

        String ratingCategory = ratingCategoryBondMapping.get(rating);
        if (ratingCategory == null) {
            ratingCategory = "DEFAULT";
        }

        String tradeTypeCategory = tradeTypeCategoryBondMapping.get(tradeType);
        if (tradeTypeCategory == null) {
            tradeTypeCategory = "DEFAULT";
        }

        if (ratingCategoryBondMapping.isEmpty()) {
            return tradeTypeCategory + delim + currencyCategory;
        }
        return tradeTypeCategory + delim + currencyCategory + delim + ratingCategory;
    }

    private String buildSourceInstrument( String tradeType, boolean isSparkasse, boolean isAutomatischeWertpapierLeihe, boolean isBilateraleWertpapierLeihe) {
        if (isSparkasse) {
            if (isAutomatischeWertpapierLeihe) {
                return "AUTO";
            } else if (isBilateraleWertpapierLeihe) {
                return "BILATERAL";
            }
    	}
    	return tradeType;
    }

    private static boolean isStruct(String struct, String instrumentName) {
    	if (struct != null &&struct.length()>0) {
    		if ( ("CALL".equals(struct) || "CALLSTEP".equals(struct) || "COMLINK".equals(struct) || "CLN".equals(struct) || "EQUITY".equals(struct))
    				&& instrumentName != null 
    				&& (instrumentName.startsWith("WLB") || instrumentName.startsWith("WESTLB")) ) {
    			return false;
    		} 
    	}else {
    		return false;
    	}
    	return true;
    }

}