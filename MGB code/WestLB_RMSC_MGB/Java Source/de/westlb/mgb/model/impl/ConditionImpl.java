package de.westlb.mgb.model.impl;
public class ConditionImpl extends EntityImpl {

	/**
     * 
     */
    private static final long serialVersionUID = -1877378383065408184L;
	public final static String JAVA_SERVER = "SERVER(TradeImpl)";
	public final static String JAVA_CLIENT = "CLIENT(PriceFetcherModel)";
	
	private String conditionName;

	private String conditionEvaluator;

	private String conditionType;

	private SourceSystemImpl sourceSystem;

	
	@Override
    public Long getLongId() {
		return super.getLongId();
	}

	/**
	 * Returns the conditionName.
	 * @return String
	 */
	public String getConditionName() {
		return conditionName;
	}

	/**
	 * Returns the conditionQuery.
	 * @return String
	 */
	public String getConditionEvaluator() {
		return conditionEvaluator;
	}

	/**
	 * Returns the conditionType.
	 * @return String
	 */
	public String getConditionType() {
		return conditionType;
	}

	/**
	 * Returns the sourceSystem.
	 * @return SourceSystemImpl
	 */
	public SourceSystemImpl getSourceSystem() {
		return sourceSystem;
	}

	/**
	 * Sets the conditionName.
	 * @param conditionName The conditionName to set
	 */
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	/**
	 * Sets the conditionQuery.
	 * @param conditionQuery The conditionQuery to set
	 */
	public void setConditionEvaluator(String conditionQuery) {
		this.conditionEvaluator = conditionQuery;
	}

	/**
	 * Sets the conditionType.
	 * @param conditionType The conditionType to set
	 */
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	/**
	 * Sets the sourceSystem.
	 * @param sourceSystem The sourceSystem to set
	 */
	public void setSourceSystem(SourceSystemImpl sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

}

