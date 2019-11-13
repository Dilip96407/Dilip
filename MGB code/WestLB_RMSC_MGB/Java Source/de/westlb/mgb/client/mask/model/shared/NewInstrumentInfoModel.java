package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.InstrumentSearchResultEntryVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;
import de.westlb_systems.xaf.swing.SDataModel;


/**
 * Gui model for the dialog which informs about new instruments.
 *
 * @author Manfred Boerner
 */

public class NewInstrumentInfoModel extends AbstractModel {

	public static final String P_TABLE_MODEL = "TableModel";

	private TableModel instrumentTableModel = null;	
	private InstrumentSearchResultEntryVo[] searchResult;
	
	/** Definition of all properties provided by the model to the view */
	private final String[] propertyNames = new String[] {
		P_TABLE_MODEL, 
	};
	
	/**
	 * Default constructor to create an empty model
	 */
	public NewInstrumentInfoModel() {
		setPropertyNames(propertyNames);
		fillModel();
	}
	
	
	public Long getInstrumentId(int row) {
		if (searchResult == null || row >= searchResult.length) {
				return null;
		}
		
		return searchResult[row].getId();
	}

	/**
	 * Return the table model of the new instruments.
	 */
	public SDataModel getDataModel() {
		// This method is used to check if the mask is to be displayed, so initialize
		// the model if it has not already been done.
		if (instrumentTableModel == null) {
			fillModel();
		}
		return instrumentTableModel;
		
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		searchResult = new InstrumentSearchResultEntryVo[0];
		
        try {
            searchResult = MgbServiceFactory.getService().findNewInstruments();
            if (searchResult.length > 0) {
                   instrumentTableModel = TableModelFactory.createTableModel("InstrumentTable", searchResult);
    		    setProperty(P_TABLE_MODEL, instrumentTableModel);
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
