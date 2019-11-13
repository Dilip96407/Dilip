/*
 * Created on 22.08.2007
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl.finder;

import java.util.Collection;
import java.util.Iterator;

import de.westlb.mgb.model.impl.BloombergCurrencyCodesImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;

public class BloombergCurrencyFinderImpl extends MgbFinderImpl {

	public BloombergCurrencyFinderImpl(Session sess) {
		super(sess);
	}

    public BloombergCurrencyCodesImpl findBloombergCurrencyCode(String isoCurrencyCode)
			throws PersistenceException {
				Query query = sess.createQuery("from "+BloombergCurrencyCodesImpl.class.getName()+" curr where curr.isoCurrencyCode = :isoCurrencyCode");
				query.setParameter("isoCurrencyCode", isoCurrencyCode);
				Collection<BloombergCurrencyCodesImpl> col = query.list();
				if (col.size() > 1) {
					Iterator<BloombergCurrencyCodesImpl> it = col.iterator();
					StringBuffer sb = new StringBuffer("Multiple BloombergCurrencyCodeImpl found for isoCurrency of " + isoCurrencyCode + ".");
					while (it.hasNext()) {
						sb.append(" " + it.next().getBloombergCurrencyCode());
					}
					sb.append(")");
					throw new PersistenceException(sb.toString());
				}
                Iterator<BloombergCurrencyCodesImpl> it = col.iterator();
                if (it.hasNext()) {
                	return it.next();
                }
                throw new PersistenceException("No BloombergCurrencyCode found for the isoCurrency of " + isoCurrencyCode + ".");
			}

	public Collection<BloombergCurrencyCodesImpl> findBloombergCurrencyCodes() throws PersistenceException {
	    Collection<BloombergCurrencyCodesImpl> result = sess.createQuery("from "+BloombergCurrencyCodesImpl.class.getName()+ " order by currencyName").list();
		return result;
	}

	
}
