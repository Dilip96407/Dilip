/*
 * Created on Apr 2, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.model.impl.finder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import de.westlb.mgb.model.Entity;
import de.westlb.mgb.model.definition.MandantDef;
import de.westlb.mgb.model.impl.AbstractMoneyMarketImpl;
import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.model.impl.PrimeEquityImpl;
import de.westlb.mgb.model.impl.SummitBondImpl;
import de.westlb.mgb.model.impl.SummitDerivativeImpl;
import de.westlb.mgb.model.impl.SummitRepoImpl;
import de.westlb.mgb.model.impl.TradeImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;

/**
 * @author WSY4148
 *
 */
public class AbstractFinder {

	private static Logger logger = Logger.getLogger(AbstractFinder.class);

	protected Session sess;

	protected AbstractFinder(Session sess) {
		this.sess = sess;
	}

	protected Query buildQuery(String table, HashMap<String, Object> columnValues, ArrayList<String> clauseTokens) throws PersistenceException {
		return buildQuery(table, columnValues, clauseTokens, "");
	}

	protected Query buildQuery(String table, HashMap<String, Object> columnValues, ArrayList<String> clauseTokens, String endClause) throws PersistenceException {
		Query query = sess.createQuery(table + wereClauseFromTokenList(clauseTokens) + " " + endClause);
		Iterator<Entry<String, Object>> i = columnValues.entrySet().iterator();
		String queryString = query.getQueryString();
		while (i.hasNext()) {
			Entry<String, Object> entry = i.next();
			query.setParameter(entry.getKey(), entry.getValue());

			if (logger.isDebugEnabled()) {
				String value = entry.getValue().toString();
				if (entry.getValue() instanceof Entity) {
					value = ((Entity)entry.getValue()).getId().toString();
				} else if (entry.getValue() instanceof Calendar) {
					value = ((Calendar)entry.getValue()).getTime().toString();
				}

				queryString = queryString.replaceAll(":"+entry.getKey(), "'"+value+"'");
			}
		}
		logger.debug("HQL: " + queryString);
		return query;
	}

	protected String wereClauseFromTokenList(List<String> clauseTokens) {
		StringBuffer whereClause = new StringBuffer();
		for (int i = 0; i < clauseTokens.size(); i++) {
			if (i == 0) {
				whereClause.append(" where ");
			} else {
				whereClause.append(" and ");
			}
			whereClause.append(clauseTokens.get(i));
		}
		return whereClause.toString();
	}

	public static Class<? extends TradeImpl> getTradeClassForMandantCode(MandantImpl mandant) {
			if (MandantDef.PRODUCT_BOND.equals(mandant.getProductClass())) {
	            return SummitBondImpl.class;
	        } else if (MandantDef.PRODUCT_DERIVATIVE.equals(mandant.getProductClass())) {
	            return SummitDerivativeImpl.class;
	        } else if (MandantDef.PRODUCT_EQUITY.equals(mandant.getProductClass())) {
	            return PrimeEquityImpl.class;
	        } else if (MandantDef.PRODUCT_MONEYMARKET.equals(mandant.getProductClass())) {
	            return AbstractMoneyMarketImpl.class;
	        } else if (MandantDef.PRODUCT_REPO.equals(mandant.getProductClass())) {
	            return SummitRepoImpl.class;
	        }
	        logger.warn("mapping mandant code "+mandant.getCode()+" to default TradeImpl.class");
	        return TradeImpl.class;
	    }

}
