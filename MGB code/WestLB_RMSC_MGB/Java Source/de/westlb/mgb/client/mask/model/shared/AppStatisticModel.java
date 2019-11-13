package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.UserStatisticVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;


/**
 * Gui model for the addon list view.
 *
 * @author Manfred Boerner
 */

public class AppStatisticModel extends AbstractModel {

	public static final String P_USER_STATISTIC_TABLE_MODEL	= "userStatisticTableModel";

	public static final String P_TITLE = "title";
	
	private final String[] propertyNames = new String[] {
		P_USER_STATISTIC_TABLE_MODEL,
	};
	
	private UserStatisticVo[] userStatistic = null;

	/**
	 * Default constructor to create an empty model
	 */
	public AppStatisticModel() {
		setPropertyNames(propertyNames);
		fillModel();
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		userStatistic = null;
		try {
            userStatistic = MgbServiceFactory.getService().getUserStatistic();
    		TableModel tableModel = TableModelFactory.createTableModel("UserStatisticTable", userStatistic);
    		setProperty(P_USER_STATISTIC_TABLE_MODEL, tableModel);	
        } catch (RemoteException e) {
        	handleRemoteException(e);
        	return;
        }
        
        						
/*		UserStatisticTableModel tableModel = new UserStatisticTableModel();
		tableModel.setData(userStatistic);		
		tableModel.setResourceBundle(getResourceBundle());
		tableModel.setResourceBase(RESOURCE_BASE + "TBL_H_");		
		setProperty(P_USER_STATISTIC_TABLE_MODEL, tableModel);	
		return;
*/	}

	
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
