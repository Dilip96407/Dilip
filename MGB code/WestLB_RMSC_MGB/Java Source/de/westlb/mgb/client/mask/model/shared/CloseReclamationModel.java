package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.application.RefreshHelper;
import de.westlb.mgb.client.server.MgbServiceFactory;

/**
 * gui model for the close reclamation view.
 *
 * @author Manfred Boerner
 */

public class CloseReclamationModel extends AbstractModel implements ReclamationLevelPanelModel
{

	private String RESOURCE_BASE = getResourceBase();
		
	public static final String 
		COMMENT = "comment"
	;
		
	/** Definition der Namen aller Properties im Model */
	private final String[] propertyNames = new String[] {
		COMMENT,
	};
	
	/**
	 * Default Konstruktor erstellt ein leeres Model
	 *
	 */
	public CloseReclamationModel() {
		setPropertyNames(propertyNames);
	}
	
	/**
	 * Fill the model from the bu>siness model.
	 *
	 */
	private void fillModel() {
	}

	/**
	 * Save Model Data
	 */
	@Override
    public boolean saveModel() {		
		if (getBusinessObject() == null || ! (getBusinessObject() instanceof Long)) {
			String msg = getResourceString(RESOURCE_BASE + "E_001");
			setError(APPLICATION_ERROR, RESOURCE_BASE + "E_001", msg);
			logMessage(LOG_ERROR, msg);
			return false;
		}
		
		Long tradeId = (Long)getBusinessObject();
		String comment = (String)getProperty(COMMENT);
	
		try {
      		MgbServiceFactory.getService().closeReclamation(tradeId, comment);
			RefreshHelper.registerUpdate(this, RefreshHelper.TRADE, tradeId);
        } catch (RemoteException e) {
        	handleRemoteException(e);
        	return false;
        }
		return true;
	}

	/**
	 * Sets the business object from which the model gets its data.
	 * It exepts to get an internal trade id.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		super.setBusinessObject(newBusinessObject);
		fillModel();
	}
}
