/*
 * Created on Jun 15, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.model.impl;

import java.io.Serializable;

/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class TimePeriodCategoryImpl extends EntityImpl {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9075564461626836199L;
	public static final String NEGATIV_PERIOD = "NEGATIV_PERIOD";
	private String code;
	private String name;
	private double minPeriodDays;
	private double maxPeriodDays;
	
	@SuppressWarnings("unused")
    private TimePeriodCategoryImpl() {
	}

	public TimePeriodCategoryImpl(String code) {
        this.code = code;
	}
	
	@Override
    public Serializable getId() {
		return code;
	}
		
    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }
    /**
     * @param code The code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * @return Returns the maxPeriodDays.
     */
    public double getMaxPeriodDays() {
        return maxPeriodDays;
    }
    /**
     * @param maxPeriodDays The maxPeriodDays to set.
     */
    public void setMaxPeriodDays(double maxPeriodDays) {
        this.maxPeriodDays = maxPeriodDays;
    }
    /**
     * @return Returns the minPeriodDays.
     */
    public double getMinPeriodDays() {
        return minPeriodDays;
    }
    /**
     * @param minPeriodDays The minPeriodDays to set.
     */
    public void setMinPeriodDays(double minPeriodDays) {
        this.minPeriodDays = minPeriodDays;
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
}
