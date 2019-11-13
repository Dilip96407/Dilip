package de.westlb.mgb.model.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Calendar;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class LoadSummitUnknownImpl extends DataLoadImpl {

	private String tradeId;
	private int tradeVersion;
	private String tradeType;
	private String subType;
	private String book;
	private String trader;
	private Calendar tradeDate;
	
	@Override
    public Serializable getId() {
		return tradeId;
	}

	@Override
    public void setNullId() {
	}

	@Override
    public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer s = new StringBuffer(this.getClass().getName() + ": ");
		for (int i = 0; i < fields.length; i++) {
			try {
				s.append("[" + fields[i].getName() + ": " + fields[i].get(this) + "] ");
			} catch (IllegalAccessException e) {
			}
		}
		return s.toString();
	}

	
	/**
	 * @return Returns the book.
	 */
	public String getBook() {
		return book;
	}
	/**
	 * @param book The book to set.
	 */
	public void setBook(String book) {
		this.book = book;
	}
	/**
	 * @return Returns the subType.
	 */
	public String getSubType() {
		return subType;
	}
	/**
	 * @param subType The subType to set.
	 */
	public void setSubType(String subType) {
		this.subType = subType;
	}
	/**
	 * @return Returns the tradeDate.
	 */
	public Calendar getTradeDate() {
		return tradeDate;
	}
	/**
	 * @param tradeDate The tradeDate to set.
	 */
	public void setTradeDate(Calendar tradeDate) {
		this.tradeDate = tradeDate;
	}
	/**
	 * @return Returns the tradeId.
	 */
	public String getTradeId() {
		return tradeId;
	}
	/**
	 * @param tradeId The tradeId to set.
	 */
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	/**
	 * @return Returns the trader.
	 */
	public String getTrader() {
		return trader;
	}
	/**
	 * @param trader The trader to set.
	 */
	public void setTrader(String trader) {
		this.trader = trader;
	}
	/**
	 * @return Returns the tradeType.
	 */
	public String getTradeType() {
		return tradeType;
	}
	/**
	 * @param tradeType The tradeType to set.
	 */
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	/**
	 * @return Returns the tradeVersion.
	 */
	public int getTradeVersion() {
		return tradeVersion;
	}
	/**
	 * @param tradeVersion The tradeVersion to set.
	 */
	public void setTradeVersion(int tradeVersion) {
		this.tradeVersion = tradeVersion;
	}
}
