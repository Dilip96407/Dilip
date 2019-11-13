package de.westlb.mgb.model.impl.finder;

/**
 * 
 * @modelguid {E75F7355-ABEC-41F3-B8FE-A18F6F5C43E5}
 */
public class ExchangeMappingSearchParams extends SearchParams {

	/** @modelguid {6627ABB3-3FE5-4F5B-BC61-0DA9A39EB8A3} */
	private String sourceSystem;

	/** @modelguid {A12C2026-BFC8-490C-BFD9-0A97D5347553} */
	private String isin;

	private String sourceSystemExchangeId;

	private String currency;

	private String isinCountryPrefix;

	public ExchangeMappingSearchParams() {
	}

	public ExchangeMappingSearchParams(
		String sourceSystem,
		String sourceSystemExchangeId,
		String isin,
		String currency) {
		this.sourceSystem = sourceSystem;
		this.sourceSystemExchangeId = sourceSystemExchangeId;
		this.isin = isin;
		if (isin != null && isin.length() > 2) {
			this.isinCountryPrefix = isin.substring(0, 2);
		} else {
			this.isinCountryPrefix = null;
		}
		this.currency = currency;
	}

	/**
	 * Returns the currency.
	 * @return String
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Returns the isin.
	 * @return String
	 */
	public String getIsin() {
		return isin;
	}

	/**
	 * Returns the isinCountryPrefix.
	 * @return String
	 */
	public String getIsinCountryPrefix() {
		return isinCountryPrefix;
	}

	/**
	 * Returns the sourceSystem.
	 * @return String
	 */
	public String getSourceSystem() {
		return sourceSystem;
	}

	/**
	 * Returns the sourceSystemExchangeId.
	 * @return String
	 */
	public String getSourceSystemExchangeId() {
		return sourceSystemExchangeId;
	}

	/**
	 * Sets the currency.
	 * @param currency The currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * Sets the isin.
	 * @param isin The isin to set
	 */
	public void setIsin(String isin) {
		if (isin != null && isin.length() > 2) {
			this.isinCountryPrefix = isin.substring(0, 2);
		} else {
			this.isinCountryPrefix = null;
		}
	}

	/**
	 * Sets the sourceSystem.
	 * @param sourceSystem The sourceSystem to set
	 */
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	/**
	 * Sets the sourceSystemExchangeId.
	 * @param sourceSystemExchangeId The sourceSystemExchangeId to set
	 */
	public void setSourceSystemExchangeId(String sourceSystemExchangeId) {
		this.sourceSystemExchangeId = sourceSystemExchangeId;
	}

}
