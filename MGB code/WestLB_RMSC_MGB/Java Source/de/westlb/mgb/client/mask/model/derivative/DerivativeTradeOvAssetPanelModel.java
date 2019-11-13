package de.westlb.mgb.client.mask.model.derivative;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.mask.model.shared.AbstractModel;
import de.westlb.mgb.client.mask.model.shared.TradeOverviewModel;
import de.westlb.mgb.client.server.vo.AssetVo;
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
public class DerivativeTradeOvAssetPanelModel extends AbstractModel {

    static Logger logger = Logger.getLogger(DerivativeTradeOvAssetPanelModel.class);

	public static final String P_ASSET_TABLE_MODEL	= "assetTableModel";

	public static final String P_TITLE = "title";
	
	private final String[] propertyNames = new String[] {
		P_ASSET_TABLE_MODEL,
	};
	
	private AssetVo[] assets = null;
	private String tableType = "";

	/**
	 * Default constructor to create an empty model
	 */
	public DerivativeTradeOvAssetPanelModel(Object newBusinessObject) {
		setPropertyNames(propertyNames);
		setBusinessObject(newBusinessObject);
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		try {
    		TableModel tableModel = TableModelFactory.createTableModel("AssetTable"+tableType, assets);
    		setProperty(P_ASSET_TABLE_MODEL, tableModel);	
        } catch (RemoteException e) {
        	try {
        		logger.warn("Taking default table layout. "+e.getMessage());
        		TableModel tableModel = TableModelFactory.createTableModel("AssetTable", assets);
        		setProperty(P_ASSET_TABLE_MODEL, tableModel);	
            } catch (RemoteException re) {
            	handleRemoteException(re);
            }
        }
	}

	
	/**
	 * Sets the business object where the model gets its data from.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		super.setBusinessObject(newBusinessObject);
		if (newBusinessObject instanceof TradeOverviewModel) {
	        assets = (AssetVo[])((TradeOverviewModel)newBusinessObject).getProperty(TradeOverviewModel.P_DVD_ASSETS);
	        tableType = (String)((TradeOverviewModel)newBusinessObject).getProperty(TradeOverviewModel.P_DVD_TRADE_TYPE);
		}
		fillModel();
	}


    
    @Override
    public void reload() {
//    	fillModel();
    }

}
