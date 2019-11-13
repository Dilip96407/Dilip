package de.westlb.mgb.model.impl;

import java.io.Serializable;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class BloombergCurrencyCodesImpl extends EntityImpl {


    private static final long serialVersionUID = 8148092767383159767L;

    @SuppressWarnings("unused")
    private BloombergCurrencyCodesImpl() {
	}

	public BloombergCurrencyCodesImpl(String isoCurrencyCode) {
		this.isoCurrencyCode = isoCurrencyCode;
	}

	private String currencyName;
	private String bloombergCurrencyCode;
	private String isoCurrencyCode;

	@Override
    public Serializable getId() {
		return isoCurrencyCode;
	}

	/**
	 * Returns the bloombergCurrencyCode.
	 * @return String
	 */
	public String getBloombergCurrencyCode() {
		return bloombergCurrencyCode;
	}

	/**
	 * Returns the currencyName.
	 * @return String
	 */
	public String getCurrencyName() {
		return currencyName;
	}

	/**
	 * Returns the isoCurrencyCode.
	 * @return String
	 */
	public String getIsoCurrencyCode() {
		return isoCurrencyCode;
	}

	/**
	 * Sets the bloombergCurrencyCode.
	 * @param bloombergCurrencyCode The bloombergCurrencyCode to set
	 */
	public void setBloombergCurrencyCode(String bloombergCurrencyCode) {
		this.bloombergCurrencyCode = bloombergCurrencyCode;
	}

	/**
	 * Sets the currencyName.
	 * @param currencyName The currencyName to set
	 */
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	/**
	 * Sets the isoCurrencyCode.
	 * @param isoCurrencyCode The isoCurrencyCode to set
	 */
	public void setIsoCurrencyCode(String isoCurrencyCode) {
		this.isoCurrencyCode = isoCurrencyCode;
	}

}
