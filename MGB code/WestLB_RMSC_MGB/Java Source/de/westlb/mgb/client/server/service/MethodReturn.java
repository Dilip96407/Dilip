/*
 * Created on Nov 3, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package de.westlb.mgb.client.server.service;

import java.io.Serializable;

/**
 * @author boernema
 *
 * Represents the return of a remote method call.
 */
public class MethodReturn implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -512571847425258136L;
	private Object object;
	private Exception exception;
	
	/**
	 * @return Returns the object.
	 */
	public Object getObject() {
		return object;
	}
	/**
	 * @param object The object to set.
	 */
	public void setObject(Object object) {
		this.object = object;
	}
	
	/**
	 * @return Returns the exception.
	 */
	public Exception getException() {
		return exception;
	}
	/**
	 * @param exception The exception to set.
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}
}
