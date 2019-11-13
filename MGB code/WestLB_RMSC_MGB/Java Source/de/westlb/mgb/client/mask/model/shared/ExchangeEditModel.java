package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.ExchangeMappingVo;
import de.westlb.mgb.client.server.vo.ExchangeVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;


/**
 * Gui model for the trade list view. 
 *
 * @author Manfred Boerner
 */

public class ExchangeEditModel extends AbstractModel implements ExchangeModel {

	public static final String P_REUTERS_ID				= "reutersId";
	public static final String P_BLOOMBERG_ID			= "bloombergId";
	public static final String P_MAPPING_TABLE_MODEL		= "searchResultTableModel";

	private String RESOURCE_BASE = getResourceBase();
	
	private ExchangeVo exchangeVo = null;
	
	/** Definition of all properties provided by the model to the view */
	private final String[] stdPropertyNames = new String[] {
		P_REUTERS_ID, 
		P_BLOOMBERG_ID,
	};
	
	private final String[] spcPropertyNames = new String[] {
		P_MAPPING_TABLE_MODEL,
	};
	
	/**
	 * Default constructor to create an empty model
	 */
	public ExchangeEditModel() {
		setPropertyNames(stdPropertyNames);
		setPropertyNames(spcPropertyNames);
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public ExchangeEditModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		if (getExchangeId() == null) {
			return;
		}

		exchangeVo = null;
		try {
            exchangeVo = MgbServiceFactory.getService().getExchange(getExchangeId());
    		TableModel tableModel = TableModelFactory.createTableModel("ExchangeMappingTable", exchangeVo.getMappings());
    		setProperty(P_MAPPING_TABLE_MODEL, tableModel);	
        } catch (RemoteException e) {
        	handleRemoteException(e);
        	return;
        }
        
        propagateProperties(stdPropertyNames, exchangeVo);
        						
/*		ExchangeMappingTableModel tableModel = new ExchangeMappingTableModel();
		tableModel.setData(exchangeVo.getMappings());		
		tableModel.setResourceBundle(getResourceBundle());
		tableModel.setResourceBase(RESOURCE_BASE + "TBL_H_");		
		setProperty(P_MAPPING_TABLE_MODEL, tableModel);	
*/				
		return;
	}

	/**
	 * Returns the casted business model
	 */
	@Override
    public Long getExchangeId() {
		Object businessObject = getBusinessObject();
		if (!(businessObject instanceof Long)) {
			return null;
		}
		
		return (Long)businessObject;
	}
	
	@Override
    public ExchangeMappingVo getExchangeMappingVo(int row) {
		return exchangeVo.getMappings()[row];
	}
	
	/**
	 * Executes assignment traderid to selected employee
	 */
	@Override
    public boolean doDeleteMapping(int row) {
		// Check minumum input
		if(getMappingId(row) == null){
			logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE +  "E_002"));
			return false;
		}
		
		try {
            MgbServiceFactory.getService().deleteExchangeMapping(getMappingId(row));
        } catch (RemoteException e) {
        	handleRemoteException(e);
        	return false;
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

            String bloombergId = (String) getProperty(P_BLOOMBERG_ID);
            String reutersId = (String) getProperty(P_REUTERS_ID);

            // Check mandatory fields
            if (bloombergId == null) {
                setError(PROPERTY_ERROR);
                logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_MANDATORY"));
                return false;
            }

			if (exchangeVo == null ) {
				exchangeVo = new ExchangeVo();
			}
            exchangeVo.setBloombergId(bloombergId);
			exchangeVo.setReutersId(reutersId);
			
            try {
                MgbServiceFactory.getService().saveExchange(exchangeVo);
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

    /**
     * @see de.westlb.mgb.client.mask.model.EmployeeListModel#getEmployeeId(int)
     */
    public Long getMappingId(int row) {
    	if (exchangeVo == null || row < 0 || row >= exchangeVo.getMappings().length) {
    		return null;
    	}
    	  
        return exchangeVo.getMappings()[row].getId();
    }
    
    @Override
    public void reload() {
    	fillModel();
    }

}
