package de.westlb.mgb.model.impl;

import java.util.Calendar;

import de.westlb.mgb.model.definition.StateCodeTypeDef;


/** @modelguid {B4F3C379-16FD-49F8-93C4-2CE3E052E910} */
public abstract class TradeHistEntryImpl extends EntityImpl implements StateCodeTypeDef {

	/**
     * 
     */
    private static final long serialVersionUID = 1284698431663570149L;

    /** @modelguid {D1DE0B31-2582-4408-9100-F02F2717EDB2} */
	private Calendar stateTime;

	/** @modelguid {369B23B4-155C-4371-B5FE-0BADA58FBBA8} */
	private TradeImpl trade;

	public static final int COMMENT_MAX_LENGTH = 1000;
	private String comment;

	private EmployeeImpl createdByEmployee;

	/** @modelguid {CFA1827D-2CC3-48F3-A781-E8652EF7F0AC} */
	@Override
    public Long getLongId() {
		return super.getLongId();
	}

	public abstract StateImpl getState();
	
	/**
	 * Returns the stateTime.
	 * @return Calendar
	 * @modelguid {E2794415-98E3-4D5D-8DD4-329A33D9FCA9}
	 */
	public Calendar getStateTime() {
		return stateTime;
	}


	/**
	 * Returns the trade.
	 * @return TradeImpl
	 * @modelguid {71CCB1DB-4396-49D9-A145-E6E86EFA34F6}
	 */
	public TradeImpl getTrade() {
		return trade;
	}

	/**
	 * Sets the stateTime.
	 * @param stateTime The stateTime to set
	 * @modelguid {B6E08A11-6B26-4637-8BBB-7FE76794F94B}
	 */
	public void setStateTime(Calendar stateTime) {
		this.stateTime = stateTime;
	}

	/**
	 * Sets the trade.
	 * @param trade The trade to set
	 * @modelguid {4C8EF136-CDCD-47D4-AC4D-AF1A9412F9EC}
	 */
	public void setTrade(TradeImpl trade) {
		this.trade = trade;
	}

	/**
	 * Returns the stateType.
	 * @return String
	 * @modelguid {7C021865-E914-4CCB-B7C7-89A7AFD617CD}
	 */
	public abstract String getStateType();

	/**
	 * Returns the comment.
	 * @return String
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment.
	 * @param comment The comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

    /**
     * @return
     */
    public EmployeeImpl getCreatedByEmployee() {
        return createdByEmployee;
    }
    
    /**
     * Returns the full name of the employee who
     * create this state.
     */
    public String getCreatorsFullname() {
    	EmployeeImpl employee = getCreatedByEmployee();
    	return employee == null ? "" : employee.getFullName();
    }

    /**
     * @param impl
     */
    public void setCreatedByEmployee(EmployeeImpl impl) {
        createdByEmployee = impl;
    }

}
