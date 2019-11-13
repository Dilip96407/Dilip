package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.ExchangeMappingVo;
import de.westlb.mgb.client.ui.selection_list.AbstractSelectionList;
import de.westlb.mgb.client.ui.selection_list.SourceSystemList;
import de.westlb_systems.xaf.util.table.TablePool;
import de.westlb_systems.xaf.util.table.swing.TablePoolComboBoxModel;

/**
 * Gui model for the exchange mapping edit dialog
 *
 * @author Manfred Boerner
 */

public class ExchangeMappingEditModel extends AbstractModel {
	public static final String P_ISIN						= "isin";
	public static final String P_SOURCE_SYSTEM				= "sourceSystem";
	public static final String P_SOURCE_SYSTEM_CB_MODEL		= "sourceSystemCbModel";
	public static final String P_ISIN_COUNTRY_PREFIX			= "isinCountryPrefix";
	public static final String P_SOURCE_SYSTEM_EXCHANGE_ID	= "sourceSystemExchangeId";
	public static final String P_CURRENCY					= "currency";
			
	private SourceSystemList sourceSystemList = new SourceSystemList();
	private TablePool.Item  nullItem = sourceSystemList.nullItemWith("    ");
		
	/** Definition of all properties provided by the model to the view */
	private final String[] stdPropertyNames = new String[] {
		P_ISIN, 
		P_ISIN_COUNTRY_PREFIX,
		P_SOURCE_SYSTEM_EXCHANGE_ID,
		P_CURRENCY,
	};
	
	private final String[] spcPropertyNames = new String[] {
		P_SOURCE_SYSTEM,
		P_SOURCE_SYSTEM_CB_MODEL,
	};
	
	private ExchangeMappingVo exchangeMappingVo;
		
	/**
	 * Default constructor to create an empty model
	 */
	public ExchangeMappingEditModel() {
		setPropertyNames(stdPropertyNames);
		setPropertyNames(spcPropertyNames);
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public ExchangeMappingEditModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
		
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		TablePoolComboBoxModel comboBoxModel = new TablePoolComboBoxModel(sourceSystemList, nullItem);
		setProperty(P_SOURCE_SYSTEM_CB_MODEL, comboBoxModel);

		if (exchangeMappingVo != null) {
			propagateProperties(stdPropertyNames, exchangeMappingVo);			
			if (exchangeMappingVo.getSourceSystemCode() == null) {				
				setProperty(P_SOURCE_SYSTEM, nullItem);
			} else {
				setProperty(P_SOURCE_SYSTEM, AbstractSelectionList.getItemFor(sourceSystemList, exchangeMappingVo.getSourceSystemCode()));			
			}
		}
		
		return;
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

        String currency = (String) getProperty(P_CURRENCY);
        String isin = (String) getProperty(P_ISIN);
        String isinCountryPrefix = (String) getProperty(P_ISIN_COUNTRY_PREFIX);
        String sourceSystemCode = (String) AbstractSelectionList.getKeyFrom(getProperty(P_SOURCE_SYSTEM));
        String sourceSystemExchangeId = (String) getProperty(P_SOURCE_SYSTEM_EXCHANGE_ID);

        exchangeMappingVo.setCurrency(currency);
        exchangeMappingVo.setIsin(isin);
        exchangeMappingVo.setIsinCountryPrefix(isinCountryPrefix);
        exchangeMappingVo.setSourceSystemCode(sourceSystemCode);
        exchangeMappingVo.setSourceSystemExchangeId(sourceSystemExchangeId);
        
        try {
            MgbServiceFactory.getService().saveExchangeMapping(exchangeMappingVo);
        } catch (RemoteException e) {
            setErrorDetails(e);
            return false;
        }
        return true;
    }


	@Override
    public void reload() {
		fillModel();
	}
	
	/**
	 * Sets the business object where the model gets its data from.
	 * Expects to get an existing ExchangeMappingVo in Update-Mode or
	 * an ExchangeId (Long) in Insert-Mode.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		// Expects to get an existing ExchangeMappingVo (Update-Modus)
		if (newBusinessObject instanceof ExchangeMappingVo) {
			exchangeMappingVo = (ExchangeMappingVo)newBusinessObject;
		} else if (newBusinessObject instanceof Long) {
			exchangeMappingVo = new ExchangeMappingVo();
			exchangeMappingVo.setExchangeId((Long)newBusinessObject);
		}
			 		
		super.setBusinessObject(newBusinessObject);		
		fillModel();
	}

}
