package de.westlb.mgb.client.mask.model.shared;

import de.westlb.mgb.client.ui.util.AttachmentModel;

/**
 * @author WSY4148
 *
 * Model interface for the CheckStateOverview-Panel
 */
public interface TradeOvResultPanelModel {
	public static final String 
		RES_AUTO_STATE="AutomaticState",
		RES_AUTO_STATE_BY_NAME="automaticStateByName",
		
		RES_MANUAL_STATE="ManualState",
		RES_MANUAL_STATE_COMMENT="manualStateComment",
		RES_MANUAL_STATE_BY_NAME="manualStateByName",
		RES_MANUAL_STATE_ATTACHMENT_MODEL="ManualStateAttachmentModel",
		
		RES_RECLAMATION_STATE="ReclamationState",
		RES_RECLAMATION_STATE_BY_NAME="reclamationStateByName",
		
		RES_RECLAMATION_STATE_ATTACHMENT_MODEL="ReclamationStateAttachmentModel",
		RES_RECLAMATION_LEVEL="reminderLevel",
		RES_RECLAMATION_IS_CLOSED = "reclamationIsClosed",
		RES_RECLAMATION_CLOSED_COMMENT = "reclamationClosedComment";
		
	public AttachmentModel getManualStateAttachmentModel();
	public AttachmentModel getReclamationStateAttachmentModel();
	public boolean isReclamationClosed();
}