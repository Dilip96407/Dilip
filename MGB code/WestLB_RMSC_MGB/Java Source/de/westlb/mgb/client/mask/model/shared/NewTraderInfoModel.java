package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.TraderSearchResultEntryVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;
import de.westlb_systems.xaf.swing.SDataModel;


/**
 * Gui model for the trade list view. 
 *
 * @author Manfred Boerner
 */

public class NewTraderInfoModel extends AbstractModel {

	public static final String P_TRADER_TABLE_MODEL = "TraderTableModel";

	private TableModel traderTableModel = null;	
	private TraderSearchResultEntryVo[] searchResult;
	
	/** Definition of all properties provided by the model to the view */
	private final String[] propertyNames = new String[] {
		P_TRADER_TABLE_MODEL, 
	};
	
	/**
	 * Default constructor to create an empty model
	 */
	public NewTraderInfoModel() {
		setPropertyNames(propertyNames);
		fillModel();
	}
	
	/**
	 * Return the table model of the new traders.
	 */
	public SDataModel getDataModel() {
		if (traderTableModel == null) {
			fillModel();
		}
		return traderTableModel;
		
	}
	
	public Object getTraderSearchResultEntry(int row) {
		if (searchResult == null || row >= searchResult.length) {
				return null;
		}
		
		return searchResult[row];
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		searchResult = new TraderSearchResultEntryVo[0];
		
        try {
            searchResult = MgbServiceFactory.getService().findNewTrader();
            if (searchResult.length > 0) {
                traderTableModel = TableModelFactory.createTableModel("TraderTable", searchResult);
                setProperty(P_TRADER_TABLE_MODEL, traderTableModel);
            }
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
						
/*		traderTableModel = new TraderTableModel();
		traderTableModel.setData(searchResult);		
		traderTableModel.setResourceBundle(getResourceBundle());
		traderTableModel.setResourceBase(RESOURCE_BASE + "TBL_H_");		
		
		setProperty(P_TRADER_TABLE_MODEL, traderTableModel);	
*/	}
	
    @Override
    public void reload() {
		fillModel();
	}


    /**
     * @see de.westlb_systems.xaf.ui.model.Model#setBusinessObject(Object)
     */
    @Override
    public void setBusinessObject(Object businessObject) {
        super.setBusinessObject(businessObject);
        fillModel();
    }

    public int getSize() {
        return searchResult == null ? 0 : searchResult.length; 
    }
}
