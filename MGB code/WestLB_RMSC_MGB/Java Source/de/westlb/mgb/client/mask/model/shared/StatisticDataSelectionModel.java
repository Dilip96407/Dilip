package de.westlb.mgb.client.mask.model.shared;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import de.westlb.mgb.client.server.vo.StatisticReportParamsVo;

/**
 * Gui model for the employee edit dialog
 *
 * @author Manfred Boerner
 */

public class StatisticDataSelectionModel extends AbstractModel {
	
	public static final String P_FROM_DATE = "fromDate";
	public static final String P_TO_DATE = "toDate";
		
	/** Definition of all properties provided by the model to the view */
	private final String[] stdPropertyNames = new String[] {
		P_FROM_DATE, 
		P_TO_DATE,
	};
	
			
	/**
	 * Default constructor to create an empty model
	 */
	public StatisticDataSelectionModel() {
		setBusinessObject(new StatisticReportParamsVo());
		setPropertyNames(stdPropertyNames);
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public StatisticDataSelectionModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		setProperty(P_FROM_DATE, getStatisticReportParamsVo().getFromDate());
		setProperty(P_TO_DATE, getStatisticReportParamsVo().getToDate());
	}
	

	@Override
    public void reload() {
		fillModel();
	}
	
	/**
	 * Sets the business object where the model gets its data from.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		// Expects to get a StatisticReportParamsVo
		super.setBusinessObject(newBusinessObject);		
		fillModel();
	}

    public StatisticReportParamsVo getStatisticReportParamsVo() {
        return (getBusinessObject() instanceof StatisticReportParamsVo) ? (StatisticReportParamsVo)getBusinessObject() : null;
    }

    /* (Kein Javadoc)
     * @see de.westlb_systems.xaf.ui.model.Model#saveModel()
     */
    @Override
    public boolean saveModel() {
    	StatisticReportParamsVo params = getStatisticReportParamsVo();
    	if (params == null) {
    		return false;
    	}
		GregorianCalendar fromDate = null;
    	GregorianCalendar toDate = null;
    	try {
            fromDate = getDateProperty(P_FROM_DATE);
            if (fromDate != null) {
	            fromDate.set(Calendar.HOUR_OF_DAY, 0);
	            fromDate.set(Calendar.MINUTE, 0);
	            fromDate.set(Calendar.SECOND, 0);
	            fromDate.set(Calendar.MILLISECOND, 0);
			}
			toDate = getDateProperty(P_TO_DATE);
			if (toDate != null) {
				toDate.set(Calendar.HOUR_OF_DAY, 23);
				toDate.set(Calendar.MINUTE, 59);
				toDate.set(Calendar.SECOND, 59);
				toDate.set(Calendar.MILLISECOND, 999);
			}
        } catch (ParseException e) {
			setError(APPLICATION_ERROR);
			setErrorDetails(e);
			return false;
        }
        params.setFromDate(fromDate);
        params.setToDate(toDate);
    	
    	return true;
    }

}
