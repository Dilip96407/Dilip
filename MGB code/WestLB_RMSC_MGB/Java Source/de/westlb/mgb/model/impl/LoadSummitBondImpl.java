package de.westlb.mgb.model.impl;

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
public class LoadSummitBondImpl extends DataLoadImpl {

	private String tradeId;
	private int tradeVersion;
	private String tradeStatus;
	private String instrument;
	private String isin;
	private String currency;
	private double fxEuroRate;
	private String tradeType;
	private String subType;
	private String category;
	private Calendar startDay;
	private Calendar expireDay;
	private String instrumentName;
	private Calendar instrumentStartDay;
	private Calendar instrumentExpireDay;
	private String issuer;
	private String book;
	private double nominal;
	private String counterparty;
	private String alias;
	private String trader;
	private Calendar tradeDate;
	private Calendar tradeTime;
	private Calendar valueDate;
	private String updatedBy;
	private Calendar updateTime;
	private double tradePrice;
	private double marketPrice;
	private double theoreticalPrice;
	private String counterpartyReference;  				    
	private String description;
	private String structure;
	private String company;
	private double discount;
	private String customerType;
	private String legalName;
	private String extNote1;
	private String extNote2;
	private double tradedYield;
	private double theoreticalYield;
    private String marketer;
	
    public String getMarketer() {
        return marketer;
    }
    
    public void setMarketer(String marketer) {
        this.marketer = marketer;
    }
    public double getTheoreticalYield() {
		return theoreticalYield;
	}
	public void setTheoreticalYield(double theoreticalYield) {
		this.theoreticalYield = theoreticalYield;
	}
	public double getTradedYield() {
		return tradedYield;
	}
	public void setTradedYield(double tradedYield) {
		this.tradedYield = tradedYield;
	}
	/**
	 * @return Returns the company.
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company The company to set.
	 */
	public void setCompany(String company) {
		this.company = company;
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
	 * @return Returns the alias.
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * @param alias The alias to set.
	 */
	public void setAlias(String alias) {
		this.alias = alias;
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
	 * @return Returns the category.
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category The category to set.
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return Returns the counterparty.
	 */
	public String getCounterparty() {
		return counterparty;
	}
	/**
	 * @param counterparty The counterparty to set.
	 */
	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}
	/**
	 * @return Returns the currency.
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency The currency to set.
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return Returns the expireDay.
	 */
	public Calendar getExpireDay() {
		return expireDay;
	}
	/**
	 * @param expireDay The expireDay to set.
	 */
	public void setExpireDay(Calendar expireDay) {
		this.expireDay = expireDay;
	}
	/**
	 * @return Returns the fxEuroRate.
	 */
	public double getFxEuroRate() {
		return fxEuroRate;
	}
	/**
	 * @param fxEuroRate The fxEuroRate to set.
	 */
	public void setFxEuroRate(double fxEuroRate) {
		this.fxEuroRate = fxEuroRate;
	}
	/**
	 * @return Returns the instrument.
	 */
	public String getInstrument() {
		return instrument;
	}
	/**
	 * @param instrument The instrument to set.
	 */
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	/**
	 * @return Returns the instrumentExpireDay.
	 */
	public Calendar getInstrumentExpireDay() {
		return instrumentExpireDay;
	}
	/**
	 * @param instrumentExpireDay The instrumentExpireDay to set.
	 */
	public void setInstrumentExpireDay(Calendar instrumentExpireDay) {
		this.instrumentExpireDay = instrumentExpireDay;
	}
	/**
	 * @return Returns the instrumentStartDay.
	 */
	public Calendar getInstrumentStartDay() {
		return instrumentStartDay;
	}
	/**
	 * @param instrumentStartDay The instrumentStartDay to set.
	 */
	public void setInstrumentStartDay(Calendar instrumentStartDay) {
		this.instrumentStartDay = instrumentStartDay;
	}
	/**
	 * @return Returns the isin.
	 */
	public String getIsin() {
		return isin;
	}
	/**
	 * @param isin The isin to set.
	 */
	public void setIsin(String isin) {
		this.isin = isin;
	}
	/**
	 * @return Returns the issuer.
	 */
	public String getIssuer() {
		return issuer;
	}
	/**
	 * @param issuer The issuer to set.
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	/**
	 * @return Returns the nominal.
	 */
	public double getNominal() {
		return nominal;
	}
	/**
	 * @param nominal The nominal to set.
	 */
	public void setNominal(double nominal) {
		this.nominal = nominal;
	}
	/**
	 * @return Returns the startDay.
	 */
	public Calendar getStartDay() {
		return startDay;
	}
	/**
	 * @param startDay The startDay to set.
	 */
	public void setStartDay(Calendar startDay) {
		this.startDay = startDay;
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
	 * @return Returns the tradeStatus.
	 */
	public String getTradeStatus() {
		return tradeStatus;
	}
	/**
	 * @param tradeStatus The tradeStatus to set.
	 */
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	/**
	 * @return Returns the tradeTime.
	 */
	public Calendar getTradeTime() {
		return tradeTime;
	}
	/**
	 * @param tradeTime The tradeTime to set.
	 */
	public void setTradeTime(Calendar tradeTime) {
		this.tradeTime = tradeTime;
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
	/**
	 * @return Returns the updatedBy.
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy The updatedBy to set.
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return Returns the updateTime.
	 */
	public Calendar getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime The updateTime to set.
	 */
	public void setUpdateTime(Calendar updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return Returns the valueDate.
	 */
	public Calendar getValueDate() {
		return valueDate;
	}
	/**
	 * @param valueDate The valueDate to set.
	 */
	public void setValueDate(Calendar valueDate) {
		this.valueDate = valueDate;
	}
	/**
	 * @return Returns the marketPrice.
	 */
	public double getMarketPrice() {
		return marketPrice;
	}
	/**
	 * @param marketPrice The marketPrice to set.
	 */
	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}
	/**
	 * @return Returns the theoreticalPrice.
	 */
	public double getTheoreticalPrice() {
		return theoreticalPrice;
	}
	/**
	 * @param theoreticalPrice The theoreticalPrice to set.
	 */
	public void setTheoreticalPrice(double theoreticalPrice) {
		this.theoreticalPrice = theoreticalPrice;
	}
	/**
	 * @return Returns the tradePrice.
	 */
	public double getTradePrice() {
		return tradePrice;
	}
	/**
	 * @param tradePrice The tradePrice to set.
	 */
	public void setTradePrice(double tradePrice) {
		this.tradePrice = tradePrice;
	}
    /**
     * @return Returns the instrumentName.
     */
    public String getInstrumentName() {
        return instrumentName;
    }
    /**
     * @param instrumentName The instrumentName to set.
     */
    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }
    /**
     * @return Returns the counterpartyReference.
     */
    public String getCounterpartyReference() {
        return counterpartyReference;
    }
    /**
     * @param counterpartyReference The counterpartyReference to set.
     */
    public void setCounterpartyReference(String counterpartyReference) {
        this.counterpartyReference = counterpartyReference;
    }
    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
	/**
	 * @return Returns the structure.
	 */
	public String getStructure() {
		return structure;
	}
	/**
	 * @param structure The structure to set.
	 */
	public void setStructure(String structure) {
		this.structure = structure;
	}
	
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	
	public String getExtNote1() {
		return extNote1;
	}
	public void setExtNote1(String extNote1) {
		this.extNote1 = extNote1;
	}
	public String getExtNote2() {
		return extNote2;
	}
	public void setExtNote2(String extNote2) {
		this.extNote2 = extNote2;
	}

}
