package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;
import java.util.ArrayList;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.MgbConfigurationVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MgbConfigurationListModel extends AbstractModel {

	public static final String P_SEARCH_RESULT_TABLE_MODEL	= "searchResultTableModel";

	public static final String P_TITLE = "title";
	
	private final String[] propertyNames = new String[] {
		P_SEARCH_RESULT_TABLE_MODEL,
	};
	
	private MgbConfigurationVo[] searchResult = null;

	/**
	 * Default constructor to create an empty model
	 */
	public MgbConfigurationListModel() {
		setPropertyNames(propertyNames);
		fillModel();
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		try {
			MgbConfigurationVo[] allResults = MgbServiceFactory.getService().findAllMgbConfigurations();
	        ArrayList<MgbConfigurationVo> configArray = new ArrayList<MgbConfigurationVo>();
            for (MgbConfigurationVo config : allResults)
            {
            	if (!config.getHidden())
            	{
            		configArray.add(config);
            	}
            }
            searchResult = configArray.toArray(new MgbConfigurationVo[0]);
    		TableModel tableModel = TableModelFactory.createTableModel("MgbConfigurationTable", searchResult);
    		setProperty(P_SEARCH_RESULT_TABLE_MODEL, tableModel);	
        } catch (RemoteException e) {
        	handleRemoteException(e);
        	return;
        }
    }

	public String getId(int row) {
		if (searchResult == null || searchResult.length < row) {
			return null;
		}
		
		return searchResult[row].getKey();
	}
			
	public Object getObject(int row) {
		if (searchResult == null || searchResult.length < row) {
			return null;
		}
		
		return searchResult[row];
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
