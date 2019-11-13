package de.westlb.mgb.client.server;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import de.westlb.mgb.model.impl.JobImpl;
import de.westlb.mgb.model.impl.MailImpl;
import de.westlb.mgb.model.impl.MailRecipientImpl;
import de.westlb.mgb.model.impl.RequestImpl;
import de.westlb.mgb.model.impl.TradeImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;

/**
 * Contains reusable service functions for trades.
 *
 */
public class TradeService {

    static Logger logger = Logger.getLogger(TradeService.class);

    /**
	 * Deletes one trade. Assumes that the transactions exists.
	 * 
	 * @param session
	 * @param trade
	 */
	public void deleteTrade(Session session, TradeImpl trade) throws PersistenceException{
//        MailFinderImpl finder = new MailFinderImpl(session);
//        Collection col = finder.findMailsByTradeId(trade.getLongId());
//        session.deleteAll(col);

//?        session.deleteAll(trade.getHistoryEntries());
        Collection<RequestImpl> reqCol = trade.getRequests();
//        Iterator iter = reqCol.iterator();
//        while (iter.hasNext()) {
//            RequestImpl request = (RequestImpl) iter.next();
//            if (request.getPriceResult() != null) {
//                session.delete(PriceImpl.class, request.getPriceResult().getId());
//            }
//        }
        session.deleteAll(reqCol);
//        session.deleteAll(trade.getReutersRequests());
//        session.deleteAll(trade.getEuwaxRequests());
//        session.deleteAll(trade.getExternalRequests());
//        session.deleteAll(trade.getBloombergRequests());
        
//        if (trade instanceof SummitDerivativeImpl) {
//        	session.deleteAll(((SummitDerivativeImpl)trade).getAssets());
//        }
        
        session.delete(TradeImpl.class, trade.getId());
}
	
	/**
	 * Deletes all trades of a job. Assumes that the transactions exists.
	 * 
	 * @param session
	 * @param job
	 */
	public void deleteAllTradesOfJob(Session session, JobImpl job) throws PersistenceException {
	    deleteAllMailsOfJob(session, job);
		Iterator<TradeImpl> it = job.getTrades().iterator();
		while (it.hasNext()) {
            TradeImpl trade = it.next();
            deleteTrade(session, trade);
            it.remove();
		}
	}
	
	private int deleteAllMailsOfJob(Session session, JobImpl job) throws PersistenceException {
        Query query1 = session.createQuery("delete "+MailRecipientImpl.class.getName()+" where mail in ( from "+MailImpl.class.getName()+" m where m.trade.job = :job)");
        query1.setParameter("job", job);
        int deletedEntities1 = query1.executeUpdate();
        logger.debug("Deleted "+deletedEntities1+" MailRecipientImpl records of job "+job.getLongId());

        Query query2 = session.createQuery("delete "+MailImpl.class.getName()+" where trade in ( from "+TradeImpl.class.getName()+" t where t.job = :job)");
        query2.setParameter("job", job);
        int deletedEntities2 = query2.executeUpdate();
        logger.debug("Deleted "+deletedEntities2+" MailImpl records of job "+job.getLongId());
        return deletedEntities1+deletedEntities2;
	}
}
