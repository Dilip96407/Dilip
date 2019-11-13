/*
 * Copyright (c) 2004, WestLB Systems
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.client.mail;

import java.util.EventListener;

/**
  * Implementations of this listener interface should be attached to a
  * {@link de.westlb.mgb.client.mail.MailConnection MailConnection} to monitor 
  * the sending of EMails.
  */
public interface MailConnectionListener extends EventListener {
	/**
	 * Called to notify the client that a mail has been successfully
	 * sent.
	 */
	public void sendMailCompleted(MailConnectionEvent mce) throws MailException ;
}