/*
 * Created on Jun 16, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.model.impl.finder;

/**
 * @author WSY4148
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RequestSearchParams extends TradeSearchParams {

    private String state = null;
	
    private boolean stateNotOkOnly = false;
	/**
	 * @return
	 */
	public boolean isStateNotOkOnly() {
		return stateNotOkOnly;
	}

	/**
	 * @param b
	 */
	public void setStateNotOkOnly(boolean b) {
		stateNotOkOnly = b;
	}

    /**
     * @return Returns the state.
     */
    public String getState() {
        return state;
    }
    /**
     * @param state The state to set.
     */
    public void setState(String state) {
        this.state = state;
    }
}
