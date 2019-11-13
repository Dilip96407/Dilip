/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */

package de.westlb.mgb.model.impl.statistic;

import de.westlb.mgb.model.impl.AutomaticStateImpl;
import de.westlb.mgb.model.impl.ManualStateImpl;
import de.westlb.mgb.model.impl.StateImpl;

/**
 * 
 */
public class StateStatisticEntryImpl {
	private StateImpl state;
	private long count;
	
	public StateStatisticEntryImpl(StateImpl state, long count) {
		this.state = state;
		this.count = count;
	}
	
	public boolean isManualCheckRequired() {
		boolean retCode = false;
		if (state instanceof AutomaticStateImpl) {
			retCode = ((AutomaticStateImpl)state).isManualCheckRequired();
		} 
		
		return retCode;
	}
	
	public boolean isReclamationRequired() {
		boolean retCode = false;
		if (state instanceof ManualStateImpl) {
			retCode = ((ManualStateImpl)state).isReclamationRequired();
		} 
		
		return retCode;
	}
	
    /**
     * @return
     */
    public long getCount() {
        return count;
    }

    /**
     * @return
     */
    public StateImpl getState() {
        return state;
    }

    /**
     * @param i
     */
    public void setCount(long i) {
        count = i;
    }

    /**
     * @param impl
     */
    public void setState(StateImpl impl) {
        state = impl;
    }

}