/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.model.impl.statistic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * DOCUMENT ME!
 *
 * @author WSY4148 
 */
public class StatisticReportImpl {
    private Collection<StatisticRowImpl> rows = new ArrayList<StatisticRowImpl>();
    private String name;
    private String[] successorNames;
    private StatisticContextImpl[] context;
    private String[] keyColumnNames;
    private String[] addKeyColumnNames;
	private Calendar fromDate;
	private	Calendar toDate;
	    
	private HashMap<String, StateStatisticEntryImpl> automaticStates = new HashMap<String, StateStatisticEntryImpl>();
	private HashMap<String, StateStatisticEntryImpl> automaticMccCheckStates = new HashMap<String, StateStatisticEntryImpl>();
	private HashMap<String, StateStatisticEntryImpl> manualStates = new HashMap<String, StateStatisticEntryImpl>();
	private HashMap<String, StateStatisticEntryImpl> reclamationStates = new HashMap<String, StateStatisticEntryImpl>();

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public Collection<StatisticRowImpl> getRows() {
        return rows;
    }


    /**
     * DOCUMENT ME!
    
     *
     * @param rows DOCUMENT ME!
     */
    public void addAllRows(Collection<StatisticRowImpl> rows) {
        Iterator<StatisticRowImpl> iterator = rows.iterator();
        while (iterator.hasNext()) {
	        addRow(iterator.next());
        }
    }
    
    private void addRow(StatisticRowImpl row) {
    	Iterator<StateStatisticEntryImpl> iterator;
    	
    	iterator = row.getAutomaticStates().values().iterator();
    	while (iterator.hasNext()) {
    		StateStatisticEntryImpl stateEntry = iterator.next();
    		String stateCode = stateEntry.getState().getStateCode();
    		StateStatisticEntryImpl totalStateEntry = automaticStates.get(stateCode);
    		if (totalStateEntry == null) {
    			totalStateEntry = new StateStatisticEntryImpl(stateEntry.getState(), 0);
				automaticStates.put(stateCode, totalStateEntry); 
    		}
			totalStateEntry.setCount(totalStateEntry.getCount()+stateEntry.getCount());
    	}
    	
    	iterator = row.getAutomaticMccCheckStates().values().iterator();
    	while (iterator.hasNext()) {
    		StateStatisticEntryImpl stateEntry = iterator.next();
    		String stateCode = stateEntry.getState().getStateCode();
    		StateStatisticEntryImpl totalStateEntry = automaticMccCheckStates.get(stateCode);
    		if (totalStateEntry == null) {
    			totalStateEntry = new StateStatisticEntryImpl(stateEntry.getState(), 0);
    			automaticMccCheckStates.put(stateCode, totalStateEntry); 
    		}
			totalStateEntry.setCount(totalStateEntry.getCount()+stateEntry.getCount());
    	}

    	iterator = row.getManualStates().values().iterator();
		while (iterator.hasNext()) {
			StateStatisticEntryImpl stateEntry = iterator.next();
			String stateCode = stateEntry.getState().getStateCode();
			StateStatisticEntryImpl totalStateEntry = manualStates.get(stateCode);
			if (totalStateEntry == null) {
				totalStateEntry = new StateStatisticEntryImpl(stateEntry.getState(), 0);
				manualStates.put(stateCode, totalStateEntry); 
			}
			totalStateEntry.setCount(totalStateEntry.getCount()+stateEntry.getCount());
		}

		iterator = row.getReclamationStates().values().iterator();
		while (iterator.hasNext()) {
			StateStatisticEntryImpl stateEntry = iterator.next();
			String stateCode = stateEntry.getState().getStateCode();
			StateStatisticEntryImpl totalStateEntry = reclamationStates.get(stateCode);
			if (totalStateEntry == null) {
				totalStateEntry = new StateStatisticEntryImpl(stateEntry.getState(), 0);
				reclamationStates.put(stateCode, totalStateEntry); 
			}
			totalStateEntry.setCount(totalStateEntry.getCount()+stateEntry.getCount());
		}
    	
    	
    	rows.add(row);
    }


    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * DOCUMENT ME!
     *
     * @param string
     */
    public void setName(String string) {
        name = string;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public String[] getAddKeyColumnNames() {
        return addKeyColumnNames;
    }

    /**
     * DOCUMENT ME!
     *
     * @return
     */
    public String[] getKeyColumnNames() {
        return keyColumnNames;
    }

    /**
     * DOCUMENT ME!
     *
     * @param strings
     */
    public void setAddKeyColumnNames(String[] strings) {
        addKeyColumnNames = strings;
    }

    /**
     * DOCUMENT ME!
     *
     * @param strings
     */
    public void setKeyColumnNames(String[] strings) {
        keyColumnNames = strings;
    }
    /**
     * @return
     */
    public StatisticContextImpl[] getContext() {
        return context;
    }

    /**
     * @param impls
     */
    public void setContext(StatisticContextImpl[] impls) {
        context = impls;
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
    public Calendar getFromDate() {
        return fromDate;
    }

    /**
     * @return
     */
    public Calendar getToDate() {
        return toDate;
    }

    /**
     * @param calendar
     */
    public void setFromDate(Calendar calendar) {
        fromDate = calendar;
    }

    /**
     * @param calendar
     */
    public void setToDate(Calendar calendar) {
        toDate = calendar;
    }

	/**
	 * @return
	 */
	public String[] getSuccessorNames() {
		return successorNames;
	}

	/**
	 * @param strings
	 */
	public void setSuccessorNames(String[] strings) {
		successorNames = strings;
	}

}