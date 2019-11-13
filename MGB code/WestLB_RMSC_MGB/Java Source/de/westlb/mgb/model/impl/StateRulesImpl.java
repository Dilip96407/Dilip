package de.westlb.mgb.model.impl;
/** @modelguid {143650D0-7A56-4992-86D9-ACD0042A900B} */
public class StateRulesImpl extends EntityImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7321873922635268964L;

	/** @modelguid {6CF626E6-9A2B-4CF2-BDEB-A680D391F8EE} */
	private AutomaticStateImpl finalState;

	/** @modelguid {7A066C53-BA90-44CB-AAE9-22E43258E028} */
	private String conditionName;
	
	/** @modelguid {46E4033A-4F14-4D4C-84EB-31624DE426E9} */
	private SourceSystemImpl sourceSystem;
	
	/** @modelguid {B83CCAAC-3852-445B-A81F-5F7B61CB2193} */
	private String comment;
	
	/** @modelguid {CE48A696-21ED-4876-8F86-88E4DB780F4C} */
	private int priority;

	/** @modelguid {AF14A7FE-1DB3-4564-9012-DEF33ADC9BA0} */
	private int stage;

	/** @modelguid {C16C2300-DC0A-4122-8993-60981110D3A0} */
	@Override
    public Long getLongId() {
		return super.getLongId();
	}

	/**
	 * Returns the comment.
	 * @return String
	 * @modelguid {138775BC-295A-4B80-A310-42395EE6B09F}
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Returns the condition.
	 * @return String
	 * @modelguid {6D3B0545-EE86-48F0-9F58-B012785E6EF0}
	 */
	public String getConditionName() {
		return conditionName;
	}

	/**
	 * Returns the finalState.
	 * @return StateImpl
	 * @modelguid {73193B1B-9EB9-4C9F-84A8-3A92BCFBB638}
	 */
	public AutomaticStateImpl getFinalState() {
		return finalState;
	}

	/**
	 * Returns the priority.
	 * @return int
	 * @modelguid {96A279E5-197D-437C-8757-E34C4A8BAFCC}
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Returns the sourceSystem.
	 * @return SourceSystemImpl
	 * @modelguid {90FA7D55-9D25-404A-8C1E-7546D5B9ADD9}
	 */
	public SourceSystemImpl getSourceSystem() {
		return sourceSystem;
	}

	/**
	 * Sets the comment.
	 * @param comment The comment to set
	 * @modelguid {2C0AFEC7-1335-400C-BED7-4C436CC6247C}
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Sets the condition.
	 * @param condition The condition to set
	 * @modelguid {52080487-D2F2-4452-97BC-7ACC4D487BF4}
	 */
	public void setConditionName(String condition) {
		this.conditionName = condition;
	}

	/**
	 * Sets the finalState.
	 * @param finalState The finalState to set
	 * @modelguid {FED665AC-00FC-4884-89DF-AABBDD891F3C}
	 */
	public void setFinalState(AutomaticStateImpl finalState) {
		this.finalState = finalState;
	}

	/**
	 * Sets the priority.
	 * @param priority The priority to set
	 * @modelguid {FA70B3AA-504A-4313-A54F-99103788B6FE}
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Sets the sourceSystem.
	 * @param sourceSystem The sourceSystem to set
	 * @modelguid {1E73158E-17AA-4315-8463-518008AF8BAA}
	 */
	public void setSourceSystem(SourceSystemImpl sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	/**
	 * Returns the stage.
	 * @return int
	 * @modelguid {D5F23090-9E4C-4199-9CB7-4F912E5FF0A1}
	 */
	public int getStage() {
		return stage;
	}

	/**
	 * Sets the stage.
	 * @param stage The stage to set
	 * @modelguid {5B8DA74A-D4C8-4721-B006-36DFC2657320}
	 */
	public void setStage(int stage) {
		this.stage = stage;
	}

	/** @modelguid {7BA023F1-21A0-4B59-BA07-56B8BCCF60C3} */
	@Override
    public String toString() {
		return finalState.getStateCode() + " = " + conditionName;
	}
	
	/**
	 * @see java.lang.Comparable#compareTo(Object)
	 * @modelguid {67F1BDF6-9552-4FFF-A26F-DA7DA6AEF452}
	 */
	@Override
	public int compareTo(EntityImpl o) {
		int result = getSourceSystem().getCode().compareTo( ((StateRulesImpl)o).getSourceSystem().getCode() );
		if ( result != 0) {
			result = getStage() -  ((StateRulesImpl)o).getStage() ;
			if ( result != 0 ) {
				result = getPriority() - ((StateRulesImpl)o).getPriority();
			}
		}
		return result;
	}

}

