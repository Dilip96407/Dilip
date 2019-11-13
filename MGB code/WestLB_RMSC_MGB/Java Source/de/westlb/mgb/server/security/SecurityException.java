/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.server.security;

/**
 * Any exception that occurs inside the security module.
 *
 * @author WSY4148
 */
public class SecurityException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7674213129314362060L;

	/**
	 * 
	 */
	public SecurityException() {
		super();
	}

	/**
	 * @param message
	 */
	public SecurityException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SecurityException(String message, Throwable cause) {
		super(message, cause);
	}
}