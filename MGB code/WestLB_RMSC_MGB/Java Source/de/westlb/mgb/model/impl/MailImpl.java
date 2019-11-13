package de.westlb.mgb.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MailImpl extends EntityImpl {

/**
	 * 
	 */
	private static final long serialVersionUID = 6037931638816559256L;
private String text;
private String type;
private EmployeeImpl sender;
private EmployeeImpl receiver;
private TradeImpl trade;
private MailImpl parent;
private Set<MailImpl> childs;
private String subject;
private Set<MailRecipientImpl> recipients;

public void addRecipient(MailRecipientImpl recipient) {
    if (recipient != null) {
        recipient.setMail(this);
    }
    if (recipients == null) {
        recipients = new HashSet<MailRecipientImpl>();
    }
    recipients.add(recipient);
}

@Deprecated
public void addCopyRecipient(EmployeeImpl employee) {
	addRecipient(employee, MailCopyRecipientImpl.class);
}

@Deprecated
public void addRecipient(EmployeeImpl employee) {
	addRecipient(employee, MailRecipientImpl.class);
}

@Deprecated
private void addRecipient(EmployeeImpl employee, Class<? extends MailRecipientImpl> recipientTypeClass) {
	MailRecipientImpl recipient = null;
	try {
		recipient = recipientTypeClass.newInstance();
	} catch (Exception e) {
		e.printStackTrace();
		return;
	}
	recipient.setMail(this);
	recipient.setEmployee(employee);
	if (recipients == null) {
		recipients = new HashSet<MailRecipientImpl>();
	}
	
	recipients.add(recipient);
	
}

/**
 * Returns the mailType.
 * @return String
 */
public String getType() {
	return type;
}

/**
 * Returns the receiver.
 * @return EmployeeImpl
 */
public EmployeeImpl getReceiver() {
	return receiver;
}

/**
 * Returns the sender.
 * @return EmployeeImpl
 */
public EmployeeImpl getSender() {
	return sender;
}

/**
 * Returns the text.
 * @return String
 */
public String getText() {
	return text;
}

/**
 * Returns the trade.
 * @return TradeImpl
 */
public TradeImpl getTrade() {
	return trade;
}

/**
 * Sets the mailType.
 * @param mailType The mailType to set
 */
public void setType(String mailType) {
	this.type = mailType;
}

/**
 * Sets the receiver.
 * @param receiver The receiver to set
 */
public void setReceiver(EmployeeImpl receiver) {
	this.receiver = receiver;
}

/**
 * Sets the sender.
 * @param sender The sender to set
 */
public void setSender(EmployeeImpl sender) {
	this.sender = sender;
}

/**
 * Sets the text.
 * @param text The text to set
 */
public void setText(String text) {
	this.text = text;
}

/**
 * Sets the trade.
 * @param trade The trade to set
 */
public void setTrade(TradeImpl trade) {
	this.trade = trade;
}

/**
 * @return
 */
public MailImpl getParent() {
	return parent;
}

/**
 * @param impl
 */
public void setParent(MailImpl impl) {
	parent = impl;
}

/**
 * @return
 */
public Collection<MailImpl> getChilds() {
	return childs;
}

/**
 * @param set
 */
public void setChilds(Set<MailImpl> set) {
	childs = set;
}

@Override
public Long getLongId() {
	return super.getLongId();
}

/**
 * @return
 */
public String getSubject() {
	return subject;
}

/**
 * @param string
 */
public void setSubject(String string) {
	subject = string;
}

/**
 * @return
 */
public Set<MailRecipientImpl> getRecipients() {
	return recipients;
}

/**
 * @param set
 */
public void setRecipients(Set<MailRecipientImpl> set) {
	recipients = set;
}

/**
 * Returns all childs for a mail.
 * 
 * @param mailId The id of the mail the successors should be returned. 
 * @param recursiv If false, only the direct response mails are returned.
 * @param mailTypes Which mail types should be returned.
 * 
 * @return An array of the requested successor mails.
 */
public Collection<MailImpl> getChilds(boolean recurse, String[] mailTypes) {
	ArrayList<MailImpl> result = new ArrayList<MailImpl>();
	getChilds(recurse, mailTypes, result);
	return result;
}

/**
 * Returns all childs for a mail. The result is added to the
 * list result.
 * 
 */
private void getChilds(boolean recurse, String[] mailTypes, Collection<MailImpl> result) {
	Iterator<MailImpl> it = getChilds().iterator();
	while (it.hasNext()) {
		MailImpl mail = it.next();
		if (mailTypes != null) {
			boolean found = false;
			for (int i = 0; i < mailTypes.length; i++) {
				if (mailTypes[i].equals(mail.getType())) {
					found = true;
					break;
				}
			}
			if (found) {
				result.add(mail);
			}
			if (recurse) {
				mail.getChilds(recurse, mailTypes, result);
			}
		}
	}
}

}
