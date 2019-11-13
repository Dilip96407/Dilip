/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.converter;

import org.apache.log4j.Logger;

import de.westlb.mgb.model.impl.BookImpl;
import de.westlb.mgb.model.impl.DataLoadImpl;
import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.model.impl.SourceSystemImpl;
import de.westlb.mgb.model.impl.SummitAmendImpl;
import de.westlb.mgb.model.impl.LoadSummitAmendImpl;
import de.westlb.mgb.model.impl.TradeImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Session;


/**
 * Converts data of amended trades from summit. 
 * 
 * @author d055625 
 */
public class SummitAmendConverter extends MgbConverter {
	
	static Logger logger = Logger.getLogger(SummitAmendConverter.class);

    public SummitAmendConverter(SourceSystemImpl sourceSystemImpl) {
		super(sourceSystemImpl);
	}

	@Override
	protected Class<? extends DataLoadImpl> getLoaderTable() {
		return LoadSummitAmendImpl.class;
	}

	/**
     * @see de.westlb.mgb.converter.MgbConverter#getLoaderTableSelectQuery()
     */
    @Override
    protected String getLoaderTableSelectQuery() {
        return "from "+getLoaderTable().getName()+"  where sourceSystem = '" + sourceSystemImpl.getCode() + "'";
    }


    @Override
    protected  void checkCompleteness() throws ConverterException {
        // no check necessary
    }

    /**
     * @see de.westlb.mgb.converter.MgbConverter#convert(Session, DataLoadImpl)
     */
    @Override
    protected TradeImpl convert(Session sess, DataLoadImpl loadData, JobImpl job)
        throws PersistenceException {
        if (loadData instanceof LoadSummitAmendImpl) {
        	try {
        	    LoadSummitAmendImpl amendLoadData = (LoadSummitAmendImpl) loadData;
        	    SummitAmendImpl amendData = (SummitAmendImpl)sess.create(SummitAmendImpl.class);
        	    amendData.convert(amendLoadData);
        	    return null;
        	} catch (Exception e) {
        		throw new PersistenceException("Error while converting loader record", e);
        	}
        }
        throw new PersistenceException("Unable to cast to SummitAmendLoadImpl.");
    }

    /**
     * @see de.westlb.mgb.converter.MgbConverter#preProcess()
     */
    @Override
    protected void preProcess() throws ConverterException {
        logger.info("PreProcessing...");
        logger.info("PreProcessing finished.");
    }

    /**
     * @see de.westlb.mgb.converter.MgbConverter#process()
     */
    @Override
    public void process() {
    }

    /**
     * @see de.westlb.mgb.converter.MgbConverter#postProcess()
     */
    protected void postProcess() {
    }

    /**
     * @see de.westlb.mgb.converter.MgbConverter#converterInit()
     */
    @Override
    protected void converterInit() {
    }

}