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
import de.westlb.mgb.model.impl.LoadParisBookImpl;
import de.westlb.mgb.model.impl.SourceSystemImpl;
import de.westlb.mgb.model.impl.TradeImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Session;


/**
 * Converts book records loaded from the PARIS system. 
 * 
 * @author M. Boerner 
 */
public class ParisConverter extends MgbConverter {
	
	static Logger logger = Logger.getLogger(ParisConverter.class);

	public ParisConverter(SourceSystemImpl sourceSystemImpl) {
		super(sourceSystemImpl);
	}

	/**
     * @see de.westlb.mgb.converter.MgbConverter#getLoaderTableSelectQuery()
     */
    @Override
    protected String getLoaderTableSelectQuery() {
        return "from "+LoadParisBookImpl.class.getName();
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
        if (loadData instanceof LoadParisBookImpl) {
        	try {
        		LoadParisBookImpl parisData = (LoadParisBookImpl) loadData;
        		String bookId = parisData.getBookId();
        		BookImpl book = null;
        		try {
        			book = (BookImpl)sess.select(BookImpl.class, bookId);
        		} catch (PersistenceException pE) {
        			if (pE.getKey() != PersistenceException.SELECT_OBJ) {
        				//throw pE;
        			    logger.error("No such object", pE);
        			}
        		}
			
        		if (book == null) {
				// Create new
        			book = new BookImpl();
        			book.setBookId(bookId);
        			book.convert((LoadParisBookImpl) loadData);
        			sess.save(book);
        		} else {
        			book.convert((LoadParisBookImpl) loadData);
        			sess.save(book);
        		}
                return null;
        	} catch (Exception e) {
        		throw new PersistenceException("Error while converting loader record", e);
        	}
        }
        throw new PersistenceException("Unable to cast to ParisBookLoadImpl.");
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

	@Override
	protected Class<? extends DataLoadImpl> getLoaderTable() {
		return LoadParisBookImpl.class;
	}

}