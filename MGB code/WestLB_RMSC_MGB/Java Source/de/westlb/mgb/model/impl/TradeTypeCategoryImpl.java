/*
 * Created on Jun 15, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl;

import java.io.Serializable;

/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class TradeTypeCategoryImpl extends EntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7673536721527060852L;

	@SuppressWarnings("unused")
    private TradeTypeCategoryImpl() {
	}

	public TradeTypeCategoryImpl(String code) {
		this.code = code;
	}
	
	private String code;
	private String name;

	@Override
    public Serializable getId() {
		return code;
	}
	
	
	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code The code to set.
	 */
	public void setCode(String code) {
		this.code = code;
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
