package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.PriceDeviationStatisticVo;
import de.westlb.mgb.client.server.vo.TradeSearchParamsVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;

/**
 * Gui model for the exchange list view.
 *
 * @author Manfred Boerner
 */

public class PriceDeviationStatisticListModel extends AbstractModel {

	public static final String P_SEARCH_RESULT_TABLE_MODEL	= "searchResultTableModel";


	public static final String P_TITLE = "title";

	private final String[] propertyNames = new String[] { 
			P_SEARCH_RESULT_TABLE_MODEL
 };

	private PriceDeviationStatisticVo[] searchResult = null;

	private TradeSearchParamsVo searchParams = null;
	
	/**
	 * Default constructor to create an empty model
	 */
	public PriceDeviationStatisticListModel() {
		setPropertyNames(propertyNames);
	}

	public PriceDeviationStatisticListModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
		fillModel();
	}
	

	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		try {
            searchResult = MgbServiceFactory.getService().getPriceDeviationStatistic(searchParams);
    		TableModel tableModel = TableModelFactory.createTableModel("PriceDeviationStatisticTable", searchResult);
    		setProperty(P_SEARCH_RESULT_TABLE_MODEL, tableModel);	
        } catch (RemoteException e) {
        	handleRemoteException(e);
        }
	}

	

	/**
	 * Sets the business object where the model gets its data from.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		if (newBusinessObject instanceof TradeSearchParamsVo) {
			searchParams = (TradeSearchParamsVo)newBusinessObject;
		}
		super.setBusinessObject(newBusinessObject);
		fillModel();
	}

	@Override
    public void reload() {
   	 	fillModel();
	}

}
