/*
 * Created on 22.08.2007
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl.finder;

import java.util.Collection;
import java.util.Iterator;

import de.westlb.mgb.model.impl.BloombergMaturityCodesImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;

public class BloombergMaturityFinderImpl extends MgbFinderImpl {

	public BloombergMaturityFinderImpl(Session sess) {
		super(sess);
	}


	public BloombergMaturityCodesImpl findBloombergMaturityCode(double maturity)
			throws PersistenceException {
				Query query = sess.createQuery("from "+BloombergMaturityCodesImpl.class.getName()+" mat where mat.minMaturityDays <= :maturity and  mat.maxMaturityDays > :maturity");
				query.setParameter("maturity", new Double(maturity));
				Collection<BloombergMaturityCodesImpl> col = query.list();
				if (col.size() > 1) {
					Iterator<BloombergMaturityCodesImpl> it = col.iterator();
					StringBuffer sb = new StringBuffer("Multiple BloombergMaturityCode found for the maturity of " + maturity + " days. (");
					while (it.hasNext()) {
						sb.append(" " + it.next().getMaturityName());
					}
					sb.append(")");
					throw new PersistenceException(sb.toString());
				}
                Iterator<BloombergMaturityCodesImpl> it = col.iterator();
                if (it.hasNext()) {
                	return it.next();
                }
                throw new PersistenceException("No BloombergMaturityCode found for the maturity of " + maturity + " days.");
			}

    public Collection<BloombergMaturityCodesImpl> findBloombergMaturityCodes() throws PersistenceException {
	    Collection<BloombergMaturityCodesImpl> result = sess.createQuery("from "+BloombergMaturityCodesImpl.class.getName()).list();
		return result;
	}

	
}
