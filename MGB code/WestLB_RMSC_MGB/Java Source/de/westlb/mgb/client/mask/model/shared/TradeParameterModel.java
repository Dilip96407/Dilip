package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.mask.model.mmk.MmkTradePrmParameterPanelModel;
import de.westlb.mgb.client.mask.model.mmk.MmkTradePrmTradePanelModel;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.TradeParameterVo;


/**
 * GUI-Model for CheckStateView
 *
 * @author Manfred Boerner
 */

public class TradeParameterModel extends AbstractModel 
	implements 
			TradePrmParameterPanelModel,
			MmkTradePrmParameterPanelModel,
			MmkTradePrmTradePanelModel
{

	public static final String 
		TPM_INST_NAME = "instrumentName",
		TPM_INST_ISIN = "instrumentIsin",
		TPM_TRADE_TICKER_USED = "tickerUsed"
	;
	
	
	/** Definition der Namen aller Properties im Model */
	private final String[] stdPropertyNames = new String[] {
		// Shared Instrument properties
		TPM_INST_NAME,
		TPM_INST_ISIN,
		
		// Shared Trade related properties
		TPM_TRADE_TICKER_USED,

		// Money market specific trade property
		P_MMK_TRADE_TYPE,
		
		// Check parameter properties
		TPP_CATEGORY,
		TPP_PRICE_TOLERANCE_PERCENT,
		TPP_PRICE_TOLERANCE_ABSOLUTE,
		TPP_TIME_TOLERANCE,
		TPP_PRICE_TOLERANCE_ADDON,
		TPP_TIME_TOLERANCE_ADDON,
		TPP_ADDON_NAME
	};
	
	private final String[] specialPropertyNames = new String[] {
	};
	

	/**
	 * Default Konstruktor erstellt ein leeres Model
	 *
	 */
	public TradeParameterModel() {
		setPropertyNames(stdPropertyNames);
		setPropertyNames(specialPropertyNames);
	}
	
	/**
	 * Konstruktor mit gleichzeitigem Setzen des Business Objects
	 */
	public TradeParameterModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
	/**
	 * Fill the model from the business model.
	 *
	 */
	private void fillModel() {
		TradeParameterVo tradeParameter = null;
		Object bo = getBusinessObject();
		if (bo != null) {
			try {
                tradeParameter = MgbServiceFactory.getService().getTradeParameter(bo);
            } catch (RemoteException e) {
                handleRemoteException(e);
            }
		}
		
		if (tradeParameter == null) {
			return;
		}

		propagateProperties(stdPropertyNames, tradeParameter);
	}


	/**
	 * Setzt das Business Object aus dem das Model seine Daten bezieht
	 *
	 * @param newBusinessObject FastSearchParameter
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		super.setBusinessObject(newBusinessObject);
		fillModel();
	}
}
