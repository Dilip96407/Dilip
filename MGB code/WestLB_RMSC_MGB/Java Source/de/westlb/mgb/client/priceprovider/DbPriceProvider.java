package de.westlb.mgb.client.priceprovider;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.TimeZone;

import de.westlb.mgb.client.server.VoFactory;
import de.westlb.mgb.client.server.vo.PriceVo;
import de.westlb.mgb.client.server.vo.RequestVo;
import de.westlb.mgb.model.definition.PriceDef;
import de.westlb.mgb.model.impl.HistoricPriceImpl;
import de.westlb.mgb.model.impl.IntervalPriceImpl;
import de.westlb.mgb.model.impl.PriceImpl;
import de.westlb.mgb.model.impl.RequestImpl;
import de.westlb.mgb.persistence.PersistenceException;
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
public class DbPriceProvider implements PriceProvider {

	private Session session;

	@SuppressWarnings("unused")
    private DbPriceProvider() {
	}

	public DbPriceProvider(Session session) {
		this.session = session;
	}

	/**
	 * @see de.westlb.mgb.client.priceprovider.PriceProvider#getPrice(RequestVo)
	 */
	@Override
    public PriceVo getPrice(RequestVo request) throws RemoteException {
		PriceVo price = null;
		try {
			RequestImpl dbRequest = (RequestImpl) session.select(RequestImpl.class, request.getRequestId());
			if (dbRequest != null && dbRequest.getPriceResult() != null) {
				PriceImpl dbprice = dbRequest.getPriceResult();
				price = VoFactory.createPriceVo(dbprice, request.getRequestId());
			}
		} catch (PersistenceException pe) {
			throw new RemoteException(pe.getMessage(), pe);
		}
		return price;
	}

	/**
	 * @see de.westlb.mgb.client.priceprovider.PriceProvider#savePrice(RequestVo, PriceVo)
	 */
	@Override
    public void savePrice(RequestVo request, PriceVo price) throws RemoteException {
		Transaction t = null;
		try {
			t = session.beginTransaction();
			RequestImpl dbRequest = (RequestImpl) session.select(RequestImpl.class, request.getRequestId());
			Calendar cal = null;
			if (price.getPriceDate() != null) {
				//ensure that the calendar is set to the local timezone, to save a consistant date in the DB
				cal = (Calendar)price.getPriceDate().clone();
				cal.setTimeZone(TimeZone.getDefault());
			}
            
			if (PriceDef.SINGLE.equals(price.getPriceType())) {
				HistoricPriceImpl dbprice = new HistoricPriceImpl();
				dbprice.setPriceDate(cal);
				dbprice.setSource(request.getRequestType());
				dbprice.setPrice(price.getMinPrice());
				session.save(dbprice);
				dbRequest.setPriceResult(dbprice);
			} else {
				IntervalPriceImpl dbprice = new IntervalPriceImpl();
				dbprice.setSource(request.getRequestType());
				dbprice.setPriceDate(cal);
				dbprice.setPriceMin(price.getMinPrice());
				dbprice.setPriceMax(price.getMaxPrice());
				session.save(dbprice);
				dbRequest.setPriceResult(dbprice);
			}
			session.save(dbRequest);
			t.commit();
		} catch (Exception e) {
			try {
				if (t != null) {
					t.rollback();
				}
			} catch (PersistenceException pe) {
			}
			throw new RemoteException(e.getMessage(), e);
		}
	}

	@Override
    public void savePrices(RequestVo[] requests, PriceVo[] prices) throws RemoteException {
		if (requests != null && prices != null) {
			if (requests.length == prices.length) {
				for (int i = 0; i < requests.length; i++) {
					savePrice(requests[i], prices[i]);
				}
			} else {
				throw new RemoteException(
					"The request array and the price array must have the same length (" + requests.length + " != " + prices.length + ").");
			}
		}
	}

	@Override
    public boolean isAvailable() {
		return true;
	}
	
}
