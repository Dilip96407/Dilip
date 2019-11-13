/*
 * Created on 02.06.2003
 */
package de.westlb.mgb.model;


/**
 * User interface.
 *
 * @author Oliver Stuhr
 */
public interface User extends Entity {
	
	/**
	 * get name property
	 */
	public String getName();

	/**
	 * set name property
	 */
	public void setName(String value);

	/**
	 * get userId property
	 */
	public String getUserId();

	/**
	 * set userId property
	 */
	public void setUserId(String value);

	/**
	 * get password property
	 */
	public String getPassword();

	/**
	 * set password property
	 */
	public void setPassword(String value);	
}
