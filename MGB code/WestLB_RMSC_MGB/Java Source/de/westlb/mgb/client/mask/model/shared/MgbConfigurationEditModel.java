package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.MgbConfigurationVo;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MgbConfigurationEditModel extends AbstractModel {
	public static final String P_KEY							= "key";
	public static final String P_VALUE							= "value";

	private String RESOURCE_BASE = getResourceBase();
	
	private MgbConfigurationVo mgbConfigurationVo = null;
	
	/** Definition of all properties provided by the model to the view */
	private final String[] stdPropertyNames = new String[] {
		P_KEY, 
		P_VALUE,
	};
	
	/**
	 * Default constructor to create an empty model
	 */
	public MgbConfigurationEditModel() {
		setPropertyNames(stdPropertyNames);
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public MgbConfigurationEditModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		if (getMgbConfiguration() == null) {
			return;
		}

		mgbConfigurationVo = getMgbConfiguration();
        
        propagateProperties(stdPropertyNames, mgbConfigurationVo);				
		return;
	}


	/**
	 * Returns the casted the business model.
	 */
	public MgbConfigurationVo getMgbConfiguration() {
		Object businessObject = getBusinessObject();
		if (!(businessObject instanceof MgbConfigurationVo)) {
			return null;
		}
		
		return (MgbConfigurationVo)businessObject;
	}

	/**
	 * Returns true if the model has an empty primary key.
	 */
	public boolean createNew() {
		if (getMgbConfiguration() != null) {
			return getMgbConfiguration().getKey() == null || getMgbConfiguration().getKey().length() == 0;
		}
        return true;
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

	        // Check mandatory fields
	        if (getProperty(P_KEY) == null || 
	        	getProperty(P_VALUE) == null)
	        {
	            setError(PROPERTY_ERROR);
	            logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_MANDATORY"));
	            return false;
	        }

	        String key = (String)getProperty(P_KEY);
	        String value = (String)getProperty(P_VALUE);
	
			if (mgbConfigurationVo == null ) {
				mgbConfigurationVo = new MgbConfigurationVo();
				mgbConfigurationVo.setCreateNew(true);
			}
			
			mgbConfigurationVo.setKey(key);
			mgbConfigurationVo.setValue(value);
					
			Mgb mgb = MgbServiceFactory.getService();
            mgb.saveMgbConfiguration(mgbConfigurationVo);
        } catch (RemoteException e) {
            handleRemoteException(e);
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
