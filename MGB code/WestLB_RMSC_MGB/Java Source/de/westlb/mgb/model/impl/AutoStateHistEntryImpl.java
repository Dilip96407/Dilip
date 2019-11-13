package de.westlb.mgb.model.impl;
/**
 * 
 * @modelguid {4CDEF141-70C7-489B-A200-EE7F07B52512}
 */
public class AutoStateHistEntryImpl extends TradeHistEntryImpl {

	/**
     * 
     */
    private static final long serialVersionUID = 253364820107184207L;
    private AutomaticStateImpl automaticState;
	
	/**
	 * @see de.westlb.mgb.model.impl.TradeHistEntryImpl#getStateType()
	 * @modelguid {552D8FA3-ECDE-4FF3-BF11-062752A78625}
	 */
	@Override
    public String getStateType() {
		return AUTO;
	}

	/**
	 * @see de.westlb.mgb.model.impl.TradeHistEntryImpl#getTradeState()
	 */
	@Override
    public StateImpl getState() {
		return getAutomaticState();
	}

	/**
	 * Returns the state.
	 * @return AutomaticStateImpl
	 */
	public AutomaticStateImpl getAutomaticState() {
		return automaticState;
	}

	/**
	 * Sets the state.
	 * @param state The state to set
	 */
	public void setAutomaticState(AutomaticStateImpl state) {
		this.automaticState = state;
	}

}

