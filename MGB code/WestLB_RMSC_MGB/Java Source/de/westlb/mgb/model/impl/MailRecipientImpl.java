/*
 * Copyright (c) 2003, WestLB Systems
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.model.impl;

/**
 * Persistent class representing a copy recipient for an email.
 * A MailCopyRecipient is privatly owned by a mail entity
 * (Aggregate association).
 *
 */
public class MailRecipientImpl extends EntityImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8496477093661919654L;
	EmployeeImpl employee;
	MailImpl mail;
	
	/**
	 * @return
	 */
	public EmployeeImpl getEmployee() {
		return employee;
	}

	/**
	 * @param impl
	 */
	public void setEmployee(EmployeeImpl impl) {
		employee = impl;
	}

	/**
	 * @return
	 */
	public MailImpl getMail() {
		return mail;
	}

	/**
	 * @param impl
	 */
	public void setMail(MailImpl impl) {
		mail = impl;
	}
	
	public boolean isCopyRecipient() {
		return false;
	}

}