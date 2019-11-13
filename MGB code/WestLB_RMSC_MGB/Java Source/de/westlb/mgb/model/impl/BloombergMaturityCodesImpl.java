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
public class BloombergMaturityCodesImpl extends EntityImpl {
	/**
     * 
     */
    private static final long serialVersionUID = -7112155105326432337L;
    private String maturityCode;
	private String maturityName;
	private String bloombergMaturityCode;
	private String bloombergMaturityField;
	private double minMaturityDays;
	private double maxMaturityDays;

	@SuppressWarnings("unused")
    private BloombergMaturityCodesImpl() {
	}

	public BloombergMaturityCodesImpl(String maturityCode) {
		this.maturityCode = maturityCode;
	}

	@Override
    public Serializable getId() {
		return maturityCode;
	}

	/**
	 * Returns the bloombergMaturityCode.
	 * @return String
	 */
	public String getBloombergMaturityCode() {
		return bloombergMaturityCode;
	}

	/**
	 * Returns the maturityName.
	 * @return String
	 */
	public String getMaturityName() {
		return maturityName;
	}

	/**
	 * Returns the maxMaturityDays.
	 * @return double
	 */
	public double getMaxMaturityDays() {
		return maxMaturityDays;
	}

	/**
	 * Returns the minMaturityDays.
	 * @return double
	 */
	public double getMinMaturityDays() {
		return minMaturityDays;
	}

	/**
	 * Sets the bloombergMaturityCode.
	 * @param bloombergMaturityCode The bloombergMaturityCode to set
	 */
	public void setBloombergMaturityCode(String bloombergMaturityCode) {
		this.bloombergMaturityCode = bloombergMaturityCode;
	}

	/**
	 * Sets the maturityName.
	 * @param maturityName The maturityName to set
	 */
	public void setMaturityName(String maturityName) {
		this.maturityName = maturityName;
	}

	/**
	 * Sets the maxMaturityDays.
	 * @param maxMaturityDays The maxMaturityDays to set
	 */
	public void setMaxMaturityDays(double maxMaturityDays) {
		this.maxMaturityDays = maxMaturityDays;
	}

	/**
	 * Sets the minMaturityDays.
	 * @param minMaturityDays The minMaturityDays to set
	 */
	public void setMinMaturityDays(double minMaturityDays) {
		this.minMaturityDays = minMaturityDays;
	}

	/**
	 * Returns the maturityCode.
	 * @return String
	 */
	public String getMaturityCode() {
		return maturityCode;
	}

	/**
	 * Sets the maturityCode.
	 * @param maturityCode The maturityCode to set
	 */
	public void setMaturityCode(String maturityCode) {
		this.maturityCode = maturityCode;
	}
	/**
	 * Returns the bloombergMaturityField.
	 * @return String
	 */
	public String getBloombergMaturityField() {
		return bloombergMaturityField;
	}

	/**
	 * Sets the bloombergMaturityField.
	 * @param bloombergMaturityField The bloombergMaturityField to set
	 */
	public void setBloombergMaturityField(String bloombergMaturityField) {
		this.bloombergMaturityField = bloombergMaturityField;
	}

}
