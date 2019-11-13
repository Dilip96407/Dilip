package de.westlb.mgb.model.impl.finder;

import de.westlb.mgb.model.impl.MandantImpl;

/**
 * 
 * @modelguid {A855CC2B-944B-4D56-BE7B-1F6F3B252510}
 */
public class StateSearchParams extends SearchParams {

	private String marketDataSource;

	/**
	 * Constructor for EmployeeSearchParams.
	 */
	public StateSearchParams() {

	}

	/**
	 * Constructor for EmployeeSearchParams.
	 * @param mandant
	 */
	public StateSearchParams(
		MandantImpl mandant,
		String marketDataSource) {
		super(mandant);
		this.marketDataSource = marketDataSource;
	}


	public String getMarketDataSource() {
		return marketDataSource;
	}
	public void setMarketDataSource(String marketDataSource) {
		this.marketDataSource = marketDataSource;
	}
}
