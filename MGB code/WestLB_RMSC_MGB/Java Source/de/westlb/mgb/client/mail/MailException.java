/*
 * Created on May 18, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.mail;

/**
 * @author WSY4148
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5284144598040180695L;

	/**
	 * 
	 */
	public MailException() {
		super();
	}

	/**
	 * @param message
	 */
	public MailException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MailException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public MailException(Throwable cause) {
		super(cause);
	}

}
