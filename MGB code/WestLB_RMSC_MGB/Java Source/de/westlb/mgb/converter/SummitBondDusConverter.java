/*
 * Created on Sep 14, 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.converter;

import java.util.Collection;
import java.util.Iterator;

import de.westlb.mgb.model.impl.SourceSystemImpl;
import de.westlb.mgb.model.impl.SummitBondImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.Transaction;

/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SummitBondDusConverter extends SummitBondConverter {

    public SummitBondDusConverter(SourceSystemImpl sourceSystemImpl) {
		super(sourceSystemImpl);
	}

	/*
     * @see de.westlb.mgb.converter.SummitBondConverter#setTradeGroups()
     */
    @Override
    protected void setIsLateDeal() throws PersistenceException {
        Session sess = null;
        try {
            sess = getSession(job.getMandant(), importUser);
            Transaction t = sess.beginTransaction();

            StringBuffer queryStr1 = new StringBuffer("select t1 from "+getTradeClass().getName()+" t1, "+getTradeClass().getName()+" t2 ");
            queryStr1.append("where t1.tradeGroupId = t2.tradeId ");
            queryStr1.append("and t1.job = :job ");
            queryStr1.append("and (day(t1.systemDate) <> day(t1.tradeDate) or t1.systemTimeString > '2000') ");
            queryStr1.append("and abs(t1.volume) > :volume ");
//            queryStr1.append("and t2.status = 'DONE' ");
            if (booksWithBackToBackTrades.size() > 0) {
                queryStr1.append("and t1.bookId not in ('").append(booksWithBackToBackTrades.get(0));
                for (int i = 1; i < booksWithBackToBackTrades.size(); i++) {
                    queryStr1.append("', '").append(booksWithBackToBackTrades.get(i));
                }
                queryStr1.append("')");
            }

            
            Query query1 = sess.createQuery(queryStr1.toString());
            logger.debug("HQL: "+query1.getQueryString());
            query1.setParameter("job", job);
            query1.setParameter("volume", new Double(lateTradeVolumeLimit));
            
            Collection<SummitBondImpl> coll = query1.list();

            StringBuffer queryStr2 = new StringBuffer("select t1 from "+getTradeClass().getName()+" t1 ");
            queryStr2.append("where t1.tradeGroupId is null ");
            queryStr2.append("and t1.job = :job ");
            queryStr2.append("and (day(t1.systemDate) <> day(t1.tradeDate) or t1.systemTimeString > '2000') ");
            queryStr2.append("and abs(t1.volume) > :volume ");
            if (booksWithBackToBackTrades.size() > 0) {
                queryStr2.append("and t1.bookId not in ('").append(booksWithBackToBackTrades.get(0));
                for (int i = 1; i < booksWithBackToBackTrades.size(); i++) {
                    queryStr2.append("', '").append(booksWithBackToBackTrades.get(i));
                }
                queryStr2.append("')");
            }

            Query query2 = sess.createQuery(queryStr2.toString());
            logger.debug("HQL: "+query2.getQueryString());
            query2.setParameter("job", job);
            query2.setParameter("volume", new Double(lateTradeVolumeLimit));

            Collection<SummitBondImpl> coll2 = query2.list();
            coll.addAll(coll2);
            int count = 0;
            Iterator<SummitBondImpl> it = coll.iterator();
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

}
