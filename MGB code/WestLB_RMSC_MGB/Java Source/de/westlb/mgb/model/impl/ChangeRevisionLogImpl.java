package de.westlb.mgb.model.impl;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ChangeRevisionLogImpl extends EntityImpl {

	/**
     * 
     */
    private static final long serialVersionUID = -1035884153024122526L;
    private String fieldName;
    
    public static final int FIELD_VALUE_MAX_LENGTH = 255;

	private String fieldValueOld;
	private String fieldValueNew;
	private DualControlJobImpl dualControlJob;

	/**
	 * Returns the dualControlJob.
	 * @return DualControlJobImpl
	 */
	public DualControlJobImpl getDualControlJob() {
		return dualControlJob;
	}

	/**
	 * Returns the fieldName.
	 * @return String
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * Returns the fieldValueNew.
	 * @return String
	 */
	public String getFieldValueNew() {
		return fieldValueNew;
	}

	/**
	 * Returns the fieldValueOld.
	 * @return String
	 */
	public String getFieldValueOld() {
		return fieldValueOld;
	}

	/**
	 * Sets the dualControlJob.
	 * @param dualControlJob The dualControlJob to set
	 */
	public void setDualControlJob(DualControlJobImpl dualControlJob) {
		this.dualControlJob = dualControlJob;
	}

	/**
	 * Sets the fieldName.
	 * @param fieldName The fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * Sets the fieldValueNew.
	 * @param fieldValueNew The fieldValueNew to set
	 */
	public void setFieldValueNew(String fieldValueNew) {
		this.fieldValueNew = fieldValueNew;
	}

	/**
	 * Sets the fieldValueOld.
	 * @param fieldValueOld The fieldValueOld to set
	 */
	public void setFieldValueOld(String fieldValueOld) {
		this.fieldValueOld = fieldValueOld;
	}

}
