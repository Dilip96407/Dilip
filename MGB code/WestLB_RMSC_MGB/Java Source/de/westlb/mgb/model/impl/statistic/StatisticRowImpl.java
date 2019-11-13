/*
 * Created on Apr 5, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.model.impl.statistic;

import java.util.HashMap;
import java.util.Map;

import de.westlb.mgb.model.impl.AutomaticStateImpl;

/**
 * @author WSY4148
 *
 */
public class StatisticRowImpl {
	private HashMap<String, StateStatisticEntryImpl> automaticStates = new HashMap<String, StateStatisticEntryImpl>();
	private HashMap<String, StateStatisticEntryImpl> automaticMccCheckStates = new HashMap<String, StateStatisticEntryImpl>();
	private HashMap<String, StateStatisticEntryImpl> manualStates = new HashMap<String, StateStatisticEntryImpl>();
	private HashMap<String, StateStatisticEntryImpl> reclamationStates = new HashMap<String, StateStatisticEntryImpl>();
	private Object[] keyValues = new Object[0];
	private Object[] addValues = new Object[0];
	private long noTradesLateEntry;
	private long noTradesManualCheckRequired;
	private long noTradesManualStateSet;
	private long noTradesManualStateRequiredButNotHandledYet;
	private long noTradesOkAfterAutoCheck;
	private long noTradesOkAfterAutoMccCheck;
	private long noTradesReclRequired;
	private long noTradesReclClosed;
	private long noTradesReclOpen;
	private long noTradesReclWaiting;
	private long noTradesAutomaticStateNotSet;
	
    /**
     * @return
     */
    public boolean calcManualCheckFinished() {
        return getNoTradesManualStateRequiredButNotHandledYet() <= 0;
    }

     /**
     * @return
     */
    public long getNoTradesLateEntry() {
        return noTradesLateEntry;
    }

    /**
     * @return
     */
    public long getNoTradesManualCheckRequired() {
        return noTradesManualCheckRequired;
    }

    /**
     * @return
     */
    public long getNoTradesOkAfterAutoCheck() {
        return noTradesOkAfterAutoCheck;
    }

    /**
     * @return
     */
    public long getNoTradesOkAfterAutoMccCheck() {
        return noTradesOkAfterAutoMccCheck;
    }

    /**
     * @return
     */
    public long getNoTradesReclClosed() {
        return noTradesReclClosed;
    }

    /**
     * @return
     */
    public long getNoTradesReclOpen() {
        return noTradesReclOpen;
    }

    /**
     * @return
     */
    public long getNoTradesReclWaiting() {
        return noTradesReclWaiting;
    }

    /**
     * @return
     */
    public long calcNoTradesTotal() {
        return noTradesAutomaticStateNotSet + noTradesOkAfterAutoCheck + noTradesOkAfterAutoMccCheck + noTradesManualCheckRequired;
    }
        
    /**
     * @param objects
     */
    public void putAutomaticState(StateStatisticEntryImpl entry) {
    	if (entry.getState() instanceof AutomaticStateImpl && ((AutomaticStateImpl)entry.getState()).getIsMccChecked() ) {
            automaticMccCheckStates.put(entry.getState().getStateCode(), entry);    		
    	} else {
    		automaticStates.put(entry.getState().getStateCode(), entry);
    	}
    }

    /**
     * @param objects
     */
    public void putManualState(StateStatisticEntryImpl entry) {
		manualStates.put(entry.getState().getStateCode(), entry);
    }

    /**
     * @param l
     */
    public void setNoTradesLateEntry(long l) {
        noTradesLateEntry = l;
    }

    /**
     * @param l
     */
    public void setNoTradesManualCheckRequired(long l) {
        noTradesManualCheckRequired = l;
    }

    /**
     * @param l
     */
    public void setNoTradesOkAfterAutoCheck(long l) {
        noTradesOkAfterAutoCheck = l;
    }

    public void setNoTradesOkAfterAutoMccCheck(long l) {
        noTradesOkAfterAutoMccCheck = l;
    }

    /**
     * @param l
     */
    public void setNoTradesReclClosed(long l) {
        noTradesReclClosed = l;
    }

    /**
     * @param l
     */
    public void setNoTradesReclOpen(long l) {
        noTradesReclOpen = l;
    }

    /**
     * @param l
     */
    public void setNoTradesReclWaiting(long l) {
        noTradesReclWaiting = l;
    }


    /**
     * @param list
     */
    public void putReclamationState(StateStatisticEntryImpl entry) {
		reclamationStates.put(entry.getState().getStateCode(), entry);
    }

    /**
     * @return
     */
    public long getNoTradesReclRequired() {
        return noTradesReclRequired;
    }

    /**
     * @param l
     */
    public void setNoTradesReclRequired(long l) {
        noTradesReclRequired = l;
    }

    /**
     * @return
     */
    public long getNoTradesAutomaticStateNotSet() {
        return noTradesAutomaticStateNotSet;
    }

    /**
     * @param l
     */
    public void setNoTradesAutomaticStateNotSet(long l) {
        noTradesAutomaticStateNotSet = l;
    }
    
    /**
     * @return
     */
    public long getNoTradesManualStateRequiredButNotHandledYet() {
        return noTradesManualStateRequiredButNotHandledYet;
    }

    /**
     * @param l
     */
    public void setNoTradesManualStateRequiredButNotHandledYet(long l) {
        noTradesManualStateRequiredButNotHandledYet = l;
    }

    /**
     * @return
     */
    public long getNoTradesManualStateSet() {
        return noTradesManualStateSet;
    }

    /**
     * @param l
     */
    public void setNoTradesManualStateSet(long l) {
        noTradesManualStateSet = l;
    }

    /**
     * @return
     */
    public Map<String, StateStatisticEntryImpl> getAutomaticStates() {
        return automaticStates;
    }

    /**
     * @return
     */
    public Map<String, StateStatisticEntryImpl> getAutomaticMccCheckStates() {
        return automaticMccCheckStates;
    }

    /**
     * @return
     */
    public Map<String, StateStatisticEntryImpl> getManualStates() {
        return manualStates;
    }

    /**
     * @return
     */
    public Map<String, StateStatisticEntryImpl> getReclamationStates() {
        return reclamationStates;
    }

    /**
     * @return
     */
    public Object[] getKeyValues() {
        return keyValues;
    }

    /**
     * @param objects
     */
    public void setKeyValues(Object[] objects) {
        keyValues = objects;
    }

    /**
     * @return
     */
    public Object[] getAddValues() {
        return addValues;
    }

    /**
     * @param objects
     */
    public void setAddValues(Object[] objects) {
        addValues = objects;
    }

}
