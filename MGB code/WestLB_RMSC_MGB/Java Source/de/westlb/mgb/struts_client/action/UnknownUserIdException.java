/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */



package de.westlb.mgb.struts_client.action;

/**
 * Exception that is throws if the user calls a struts action with 
 * an unknown user id. That means that the user is not assigned to an employee.
 *
 * @author WSY4148 
 */
public class UnknownUserIdException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 151423905625181794L;

	/**
	 * 
	 */
	public UnknownUserIdException() {
		super();
	}

	/**
	 * @param message
	 */
	public UnknownUserIdException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UnknownUserIdException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public UnknownUserIdException(Throwable cause) {
		super(cause);
	}

}