package de.westlb.mgb.converter;

import java.util.Iterator;

import de.westlb.mgb.model.impl.SourceSystemImpl;
import de.westlb.mgb.model.impl.SummitMoneyMarketImpl;
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
public class SummitMoneyMarketDusConverter extends SummitMoneyMarketConverter {

	public SummitMoneyMarketDusConverter(SourceSystemImpl sourceSystemImpl) {
		super(sourceSystemImpl);
	}

	/**
	 * @see de.westlb.mgb.converter.MgbConverter#preProcess()
	 */
	@Override
    protected void preProcess() throws ConverterException {
		super.preProcess();;
		try {
            setMccRelevantChange();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ConverterException("Error while preProcessing: " + e.getMessage(), e);
		}
		logger.info("PreProcessing of DUS client finished.");
	}

    protected void setMccRelevantChange() throws PersistenceException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);
            Transaction t = sess.beginTransaction();

            String[] comparisonFields = { 
					"instrument",
					"price",
					"settlementDate",
					"maturityDate",
					"volume"
            		};

            StringBuffer queryStr = new StringBuffer("select distinct t1 from "+getTradeClass().getName()+"  t1, "+getTradeClass().getName()+"  t2 ");
            queryStr.append("where t1.tradeGroupId = t2.tradeGroupId ");
            queryStr.append("and t1.job = :job ");
            queryStr.append("and t1.subType = 'CLOAN' ");
            for (int i = 0; i < comparisonFields.length; i++) {
                queryStr.append("and ((t1.").append(comparisonFields[i]).append(" = t2.").append(comparisonFields[i])
                            .append(") or (t1.").append(comparisonFields[i]).append(" is null and t2.").append(comparisonFields[i]).append(" is null)) ");
            }
            Query query = sess.createQuery(queryStr.toString());
            logger.debug("HQL: "+query.getQueryString());
            query.setParameter("job", job);
            int count = 0;
            Iterator<SummitMoneyMarketImpl> it = query.iterate();
            while (it.hasNext()) {
            	SummitMoneyMarketImpl trade = (SummitMoneyMarketImpl) sess.select(SummitMoneyMarketImpl.class, it.next().getId());
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

}
