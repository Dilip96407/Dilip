/*
 * Created on Nov 4, 2005
 *
 */
package de.westlb.mgb.model.impl;

/**
 * @author wsy4148
 *
 * Persistent entity holding the information about a counterparty id.
 * A counterparty id is used to sign the customer of a trade in 
 * the frontoffice system.
 */
public class CounterpartyImpl extends EntityImpl {
	/**
     * 
     */
    private static final long serialVersionUID = -7834824110366205513L;
    private String counterpartyId;
	private SourceSystemImpl sourceSystem;
	private SparkasseImpl sparkasse;
	private String depotType;
	private boolean deactivated = false;
	
	public CounterpartyImpl() {
	}
	
	public CounterpartyImpl(String counterpartyId, SourceSystemImpl sourceSystem) {
		this.counterpartyId = counterpartyId;
		this.sourceSystem = sourceSystem;
	}
	
	/**
	 * @return Returns the counterpartyId.
	 */
	public String getCounterpartyId() {
		return counterpartyId;
	}
	/**
	 * @param counterpartyId The counterpartyId to set.
	 */
	public void setCounterpartyId(String counterpartyId) {
		this.counterpartyId = counterpartyId;
	}
	/**
	 * @return Returns the depotType.
	 */
	public String getDepotType() {
		return depotType;
	}
	/**
	 * @param depotType The depotType to set.
	 */
	public void setDepotType(String depotType) {
		this.depotType = depotType;
	}
	/**
	 * @return Returns the sourceSystem.
	 */
	public SourceSystemImpl getSourceSystem() {
		return sourceSystem;
	}
	/**
	 * @param sourceSystem The sourceSystem to set.
	 */
	public void setSourceSystem(SourceSystemImpl sourceSystem) {
		this.sourceSystem = sourceSystem;
	}
	/**
	 * @return Returns the sparkasse.
	 */
	public SparkasseImpl getSparkasse() {
		return sparkasse;
	}
	/**
	 * @param sparkasse The sparkasse to set.
	 */
	public void setSparkasse(SparkasseImpl sparkasse) {
		this.sparkasse = sparkasse;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
//	public boolean equals(Object o) {
//		if (!(o instanceof CounterpartyImpl)) {
//			return false;
//		}
//		
//		CounterpartyImpl c = (CounterpartyImpl)o;
//		return sourceSystem.equals(c.getSourceSystem()) &&
//			counterpartyId.equals(c.getCounterpartyId());
//		
//	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
//	public int hashCode() {
//		return sourceSystem.hashCode() + counterpartyId.hashCode();
//	}
		
	/**
	 * @return Returns the deactivated.
	 */
	public boolean isDeactivated() {
		return deactivated;
	}
	/**
	 * @param deactivated The deactivated to set.
	 */
	public void setDeactivated(boolean deactivated) {
		this.deactivated = deactivated;
	}
	
}
