package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;
import de.westlb_systems.xaf.swing.SDataModel;


/**
 * Gui model for the dialog which informs about new locations.
 *
 * @author Portigon GmbH
 */

public class NewLocationInfoModel extends AbstractModel {

	public static final String P_TABLE_MODEL = "TableModel";

	private TableModel locationTableModel = null;	
	private TradeSearchResultEntryVo[] searchResult;
	
	/** Definition of all properties provided by the model to the view */
	private final String[] propertyNames = new String[] {
		P_TABLE_MODEL, 
	};
	
	/**
	 * Default constructor to create an empty model
	 */
	public NewLocationInfoModel() {
		setPropertyNames(propertyNames);
		fillModel();
	}
	
	
	public Long getTradeId(int row) {
		if (searchResult == null || row >= searchResult.length) {
				return null;
		}
		
		return searchResult[row].getId();
	}

	/**
	 * Return the table model of the new locations.
	 */
	public SDataModel getDataModel() {
		// This method is used to check if the mask is to be displayed, so initialize
		// the model if it has not already been done.
		if (locationTableModel == null) {
			fillModel();
		}
		return locationTableModel;
		
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		searchResult = new TradeSearchResultEntryVo[0];
		
        try {
            searchResult = MgbServiceFactory.getService().findNewLocations();
            if (searchResult.length > 0) {
                   locationTableModel = TableModelFactory.createTableModel("TradeTable", searchResult);
    		    setProperty(P_TABLE_MODEL, locationTableModel);
            }
        } catch (RemoteException e) {
            handleRemoteException(e);
        }						
	}

    @Override
    public void reload() {
		fillModel();
	}

    public int getSize() {
        return searchResult == null ? 0 : searchResult.length; 
    }

}
