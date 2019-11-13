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
 * Represents a MGB proprietary remote method call.
 */
public class MethodCall implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8013158504535805410L;
	// The name of the method. Must exist in the MGB interface.
	private String name;
	// The arguments for the method call.
	private Object[] args;

	/**
	 * @return Returns the args.
	 */
	public Object[] getArgs() {
		return args;
	}
	/**
	 * @param args The args to set.
	 */
	public void setArgs(Object[] args) {
		this.args = args;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
}