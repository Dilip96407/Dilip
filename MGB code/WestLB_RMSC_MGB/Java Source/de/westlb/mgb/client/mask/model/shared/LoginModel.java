package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;

/**
 * Gui model for the employee edit dialog
 *
 * @author Manfred Boerner
 */

public class LoginModel extends AbstractModel {
	public static final String P_USER_ID		= "userId";
	public static final String P_PASSWORD	= "password";

		
	/** Definition of all properties provided by the model to the view */
	private final String[] propertyNames = new String[] {
		P_USER_ID, 
		P_PASSWORD,
	};
	
	/**
	 * Default constructor to create an empty model
	 */
	public LoginModel() {
		setPropertyNames(propertyNames);
	}
	
	/**
	 * Save Model Data
	 */
	@Override
    public boolean saveModel() {
	
	    String userId = (String) getProperty(P_USER_ID);
	    String password = (String) getProperty(P_PASSWORD);
	
	    try {
	        boolean success = MgbServiceFactory.getService().adminLogin(userId, password);
	        if (!success) {
	        	setError(APPLICATION_ERROR);
	        	setErrorMessage("E_001");
	        }
	    } catch (RemoteException e) {
	        setErrorDetails(e);
	        return false;
	    }
	    return true;
	}

}
