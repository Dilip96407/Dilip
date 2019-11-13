package de.westlb.mgb.model.impl.finder;

import de.westlb.mgb.model.impl.MandantImpl;

/**
 * 
 * @modelguid {E52499AE-57E5-4FF0-9E65-82990D0AFE1B}
 */
public class InstrumentSearchParams extends SearchParams {
	
	/** @modelguid {13E226E0-D2F2-4441-AF29-5BCD24FDAFD2} */
	private String instrument;

	private boolean isExactMatch = false;

	private boolean onlyPriceCheckInstruments = false;

	/**
	 * Constructor for InstrumentSearchParams.
	 * @modelguid {C2828C87-CBDC-4966-8924-1FFB1887309E}
	 */
	public InstrumentSearchParams() {
	}

	/**
	 * Constructor for InstrumentSearchParams.
	 * @param mandant
	 * @modelguid {8DD38511-6608-4B46-9A65-3E1E222E3CEC}
	 */
	public InstrumentSearchParams(MandantImpl mandant) {
		super(mandant);
	}

	/**
	 * Constructor for InstrumentSearchParams.
	 * @param mandant
	 * @modelguid {8DD38511-6608-4B46-9A65-3E1E222E3CEC}
	 */
	public InstrumentSearchParams(MandantImpl mandant, String name) {
		super(mandant);
		this.instrument = name;
		this.isExactMatch = false;
	}

	public InstrumentSearchParams(MandantImpl mandant, String name, boolean isExactMatch) {
		super(mandant);
		this.instrument = name;
		this.isExactMatch = isExactMatch;
	}

	/**
	 * Returns the name.
	 * @return String
	 * @modelguid {10103A2E-D86C-46C8-9B31-E40EE9FB3247}
	 */
	public String getInstrument() {
		return instrument;
	}

	/**
	 * Sets the name.
	 * @param name The name to set
	 * @modelguid {E7925A9C-2B31-43D0-B7CD-A8CFFF220B06}
	 */
	public void setInstrument(String name) {
		this.instrument = name;
	}

	/**
	 * Returns the isExactMatch.
	 * @return boolean
	 */
	public boolean isExactMatch() {
		return isExactMatch;
	}

	/**
	 * Sets the isExactMatch.
	 * @param isExactMatch The isExactMatch to set
	 */
	public void setIsExactMatch(boolean isExactMatch) {
		this.isExactMatch = isExactMatch;
	}

	/**
	 * @return Returns the onlyPriceCheckInstruments.
	 */
	public boolean isOnlyPriceCheckInstruments() {
		return onlyPriceCheckInstruments;
	}
	/**
	 * @param onlyPriceCheckInstruments The onlyPriceCheckInstruments to set.
	 */
	public void setOnlyPriceCheckInstruments(boolean onlyPriceCheckInstruments) {
		this.onlyPriceCheckInstruments = onlyPriceCheckInstruments;
	}
}

