/*
 * Created on 22.08.2007
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl.finder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import de.westlb.mgb.model.impl.MailImpl;
import de.westlb.mgb.model.impl.MailRecipientImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;

public class MailFinderImpl extends MgbFinderImpl {

	public MailFinderImpl(Session sess) {
		super(sess);
	}

	public Collection<MailImpl> findMailsByTradeId(Long tradeId) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		if (tradeId != null) {
			clauseTokens.add("mail.trade.id = :tradeId");
			columnValues.put("tradeId", tradeId);
		}
//        Query query = buildQuery("from "+MailImpl.class.getName()+" mail join fetch mail.trade join fetch mail.sender join fetch mail.receiver ", columnValues, clauseTokens);
        Query query = buildQuery("from "+MailImpl.class.getName()+" mail join fetch mail.sender join fetch mail.receiver ", columnValues, clauseTokens);
        Collection<MailImpl> result = query.list();
        return result;
	}

	public Collection<MailImpl> findMails(MailSearchParams params) throws PersistenceException {
		HashMap<String, Object> columnValues = new HashMap<String, Object>();
		ArrayList<String> clauseTokens = new ArrayList<String>();
		String selectFrom = "select mail from "+MailImpl.class.getName()+" mail";
		if (params != null) {
			if (isNotNullOrZero(params.getRecipientNtId())) {
				selectFrom = "select distinct mail from "+MailImpl.class.getName()+" mail, "+MailRecipientImpl.class.getName()+" recipient"; 
				clauseTokens.add("mail = recipient.mail");
				clauseTokens.add("recipient.employee.ntId = :recipientNtId");
				columnValues.put("recipientNtId", params.getRecipientNtId());
			}
				
			if (isNotNullOrZero(params.getTradeId())) {
				clauseTokens.add("mail.trade.id = :tradeId");
				columnValues.put("tradeId", params.getTradeId());
			}
	
			if (params.isReclamationOpenOnly()) {
				clauseTokens.add("(mail.trade.currentReclStateHistEntry.isClosed <> 'Y' OR mail.trade.currentReclStateHistEntry.isClosed is null)");
			}
		}
	
		Query query = buildQuery(selectFrom, columnValues, clauseTokens);
        Collection<MailImpl> result = query.list();
        return result;
	}

}
