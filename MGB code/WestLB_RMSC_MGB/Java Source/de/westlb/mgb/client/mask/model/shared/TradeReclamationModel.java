package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.application.RefreshHelper;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.TradeReclamationVo;
import de.westlb.mgb.client.ui.selection_list.ReclamationStateCodeList;


/**
 * GUI-Model for TradeReclamationView
 *
 * @author Manfred Boerner
 */

public class TradeReclamationModel extends AbstractModel implements ReclamationLevelPanelModel {
	
	private ReclamationStateCodeList reclamationStateCodes = new ReclamationStateCodeList();

	public static final String 
		P_CLOSED="closed",
		P_CLOSED_COMMENT="closedComment",
		P_COMMENT="comment",
		P_LEVEL="level",
		P_REASON="reason",
		P_SENDER_NAME="senderName",
		P_SEND_DATE="sendDate"
	;
	
	/** Definition der Namen aller Properties im Model */
	private final String[] stdPropertyNames = new String[] {
		P_CLOSED,
		P_CLOSED_COMMENT,
		P_COMMENT,
		P_LEVEL,
		P_SENDER_NAME,
		P_SEND_DATE,		
	};
	private final String[] specialPropertyNames = new String[] {
		P_REASON,
		ReclamationLevelPanelModel.P_REM_LEVEL_1,
		ReclamationLevelPanelModel.P_REM_LEVEL_2,
		ReclamationLevelPanelModel.P_REM_ESCALATION,
		ReclamationLevelPanelModel.P_REM_OUTSIDE_SYS,
	};
	
	private Long id;

	/**
	 * Default Konstruktor erstellt ein leeres Model
	 *
	 */
	public TradeReclamationModel() {
		setPropertyNames(stdPropertyNames);
		setPropertyNames(specialPropertyNames);
	}
	
	/**
	 * Konstruktor mit gleichzeitigem Setzen des Business Objects
	 */
	public TradeReclamationModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
	/**
	 * Fill the model from the business model.
	 *
	 */
	private void fillModel() {
		TradeReclamationVo data = null;
		if (id != null) {
			try {
                data = MgbServiceFactory.getService().getTradeReclamation(id);
                RefreshHelper.registerCache(this, RefreshHelper.TRADE, id);
            } catch (RemoteException e) {
                handleRemoteException(e);
            }
		}
		
		if (data == null) {
			return;
		}
		propagateProperties(stdPropertyNames, data);	
			
		setProperty(P_REM_LEVEL_1, Boolean.valueOf(data.getLevel() == 1));
		setProperty(P_REM_LEVEL_2, Boolean.valueOf(data.getLevel() == 2));
		setProperty(P_REM_ESCALATION, Boolean.valueOf(data.getLevel() >= 3));
		setProperty(P_REM_OUTSIDE_SYS, Boolean.valueOf(data.getLevel() == -1));
		setProperty(P_REASON, reclamationStateCodes.get(data.getReason()));
	}


	/**
	 * Setzt das Business Object aus dem das Model seine Daten bezieht
	 *
	 * @param newBusinessObject FastSearchParameter
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		if(newBusinessObject instanceof Long){
			id = (Long)newBusinessObject;
		} else {
			id = null;
		}
		super.setBusinessObject(newBusinessObject);
		
		fillModel();
	}
}
