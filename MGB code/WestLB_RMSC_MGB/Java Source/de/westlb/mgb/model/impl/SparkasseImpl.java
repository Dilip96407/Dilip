/*
 * Created on Nov 4, 2005
 *
 */
package de.westlb.mgb.model.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wsy4148
 *
 * Persistent entity holding the information about a Sparkasse. 
 */
public class SparkasseImpl extends EntityImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3553334509797318341L;
	private String name;
	private String clientNr;
	private String longName;
	private boolean active;
	private Set<CounterpartyImpl> counterparties = new HashSet<CounterpartyImpl>();

	/**
	 * @return Returns the enabled.
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * @param enabled The enabled to set.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	/**
	 * @return Returns the longName.
	 */
	public String getLongName() {
		return longName;
	}
	/**
	 * @param longName The longName to set.
	 */
	public void setLongName(String longName) {
		this.longName = longName;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the counterpartys.
	 */
	private Set<CounterpartyImpl> getCounterparties() {
		return counterparties;
	}
	/**
	 * @param counterpartys The counterpartys to set.
	 */
	@SuppressWarnings("unused")
    private void setCounterparties(Set<CounterpartyImpl> counterparties) {
		this.counterparties = counterparties;
	}
	
	public void addCounterparty(CounterpartyImpl counterparty) {
		counterparties.add(counterparty);
	}
	
	public void resetCounterparties(Collection<CounterpartyImpl> newCol) {
		// Add all new entries
		counterparties.addAll(newCol);
		// Retain only the entries from the new collection
		counterparties.retainAll(newCol);
	}
	
	public void removeCounterparty(CounterpartyImpl counterparty) {
		//System.out.println("size=" + counterparties.size());
		counterparties.remove(counterparty);
		//System.out.println("size=" + counterparties.size());
	}
	
	public Iterator<CounterpartyImpl> getCounterpartiesIterator() {
		return getCounterparties().iterator();
	}
	
	public int getCounterpartiesSize() {
		return getCounterparties().size();
	}
	
	/**
	 * @return Returns the clientNr.
	 */
	public String getClientNr() {
		return clientNr;
	}
	/**
	 * @param clientNr The clientNr to set.
	 */
	public void setClientNr(String clientNr) {
		this.clientNr = clientNr;
	}

}

