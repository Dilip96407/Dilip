package de.westlb.mgb.client.mask.model.shared;

import java.util.Date;

/**
 * @author wsy4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DataSelectionParameter {

	private String mandantKey;
	private String sourceSystemKey;
	private Date fromDate;
	private Date toDate;
	
	/**
	 * Constructor for DataSelectionParameter.
	 */
	public DataSelectionParameter() {
		super();
	}

	/**
	 * Returns the fromDate.
	 * @return Date
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * Returns the mandantKey.
	 * @return String
	 */
	public String getMandantKey() {
		return mandantKey;
	}

	/**
	 * Returns the sourceSystemKey.
	 * @return String
	 */
	public String getSourceSystemKey() {
		return sourceSystemKey;
	}

	/**
	 * Returns the toDate.
	 * @return Date
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * Sets the fromDate.
	 * @param fromDate The fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * Sets the mandantKey.
	 * @param mandantKey The mandantKey to set
	 */
	public void setMandantKey(String mandantKey) {
		this.mandantKey = mandantKey;
	}

	/**
	 * Sets the sourceSystemKey.
	 * @param sourceSystemKey The sourceSystemKey to set
	 */
	public void setSourceSystemKey(String sourceSystemKey) {
		this.sourceSystemKey = sourceSystemKey;
	}

	/**
	 * Sets the toDate.
	 * @param toDate The toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}
