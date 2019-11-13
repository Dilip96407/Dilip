package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;
import java.text.ParseException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.AddonVo;

/**
 * GUI model for the addon edit dialog.
 *
 * @author Manfred Boerner
 */

public class AddonEditModel extends AbstractModel {
	public static final String P_TIME_TOLERANCE_IN_MINUTES	= "timeToleranceMinutes";
	public static final String P_PRICE_TOLERANCE_IN_PERCENT	= "priceTolerancePercent";
	public static final String P_CONDITION					= "condition";
	public static final String P_COMMENT						= "comment";

	private String RESOURCE_BASE = getResourceBase();
	
	private AddonVo addonVo = null;
	
	/** Definition of all properties provided by the model to the view */
	private final String[] stdPropertyNames = new String[] {
		P_TIME_TOLERANCE_IN_MINUTES, 
		P_PRICE_TOLERANCE_IN_PERCENT,
		P_CONDITION,
		P_COMMENT,		
	};
	
	/**
	 * Default constructor to create an empty model
	 */
	public AddonEditModel() {
		setPropertyNames(stdPropertyNames);
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public AddonEditModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		if (getAddonId() == null) {
			return;
		}

		addonVo = null;
		try {
            addonVo = MgbServiceFactory.getService().getAddon(getAddonId());
        } catch (RemoteException e) {
        	handleRemoteException(e);
        	return;
        }
        
        propagateProperties(stdPropertyNames, addonVo);				
		return;
	}

	/**
	 * Returns the casted business model
	 */
	public Long getAddonId() {
		Object businessObject = getBusinessObject();
		if (!(businessObject instanceof Long)) {
			return null;
		}
		
		return (Long)businessObject;
	}
	
    /**
    * Save Model Data
    */
    @Override
    public boolean saveModel() {
        if (!isModelChanged()) {
            logMessage(LOG_INFO, null);
            return true;
        }

        try {

			double priceToleranceInPercent = getDoubleProperty(P_PRICE_TOLERANCE_IN_PERCENT, 0f);
	        int timeToleranceInMinutes = getIntProperty(P_TIME_TOLERANCE_IN_MINUTES, 0);
	        
	        String comment = (String)getProperty(P_COMMENT);
	        String condition = (String)getProperty(P_CONDITION);
	
	        // Check mandatory fields
	        if (condition == null) {
	            setError(PROPERTY_ERROR);
	            logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_MANDATORY"));
	            return false;
	        }
	
			if (addonVo == null ) {
				addonVo = new AddonVo();
			}
	        addonVo.setPriceTolerancePercent(priceToleranceInPercent);
	        addonVo.setTimeToleranceMinutes(timeToleranceInMinutes);
	        addonVo.setComment(comment);
	        addonVo.setCondition(condition);
		
            MgbServiceFactory.getService().saveAddon(addonVo);
        } catch (RemoteException e) {
            handleRemoteException(e);
            return false;
        } catch (ParseException pE) {
        	setError(APPLICATION_ERROR);
            setErrorDetails(pE);
            return false;
        }
        
        return true;
    }

		
	/**
	 * Sets the business object where the model gets its data from.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		super.setBusinessObject(newBusinessObject);		
		fillModel();
	}

    @Override
    public void reload() {
    	fillModel();
    }

}
