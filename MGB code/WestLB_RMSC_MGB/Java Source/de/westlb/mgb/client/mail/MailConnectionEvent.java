/*
 * Created on May 18, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.mail;

import java.util.Collection;
import java.util.Map;

/**
 * @author WSY4148
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MailConnectionEvent {
	private String subject;
	private String text;
	private Collection<String> originalRecipients;
	private Collection<String> copyRecipients;
	private Map<String, Object> parameters;
	/**
	 * @return
	 */
	public Collection<String> getCopyRecipients() {
		return copyRecipients;
	}

	/**
	 * @return
	 */
	public Collection<String> getOriginalRecipients() {
		return originalRecipients;
	}

	/**
	 * @return
	 */
	public Object getParameter(String name) {
		return parameters.get(name);
	}

	/**
	 * @return
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param collection
	 */
	public void setCopyRecipients(Collection<String> collection) {
		copyRecipients = collection;
	}

	/**
	 * @param collection
	 */
	public void setOriginalRecipients(Collection<String> collection) {
		originalRecipients = collection;
	}

	/**
	 * @param map
	 */
	void setParameters(Map<String, Object> map) {
		parameters = map;
	}

	/**
	 * @param string
	 */
	public void setSubject(String string) {
		subject = string;
	}

	/**
	 * @param string
	 */
	public void setText(String string) {
		text = string;
	}

}
