package de.westlb.mgb.client.mask.model.shared;

/**
 * @author WSY4148
 *
 * Model interface for the CheckStateOverview-Panel
 */
public interface CheckStateOverviewPanelModel {
	public static final String 
		NO_TRADES_TOTAL 				= "noTradesTotal",
		NO_TRADES_MANUAL_CHECK_REQUIRED	= "noTradesManualCheckRequired",
		NO_TRADES_OK_AFTER_AUTOCHECK	= "noTradesOkAfterAutoCheck",
		NO_TRADES_RECLAMATION_REQUIRED	= "noTradesReclRequired",
		NO_TRADES_RECLAMATION_OPEN		= "noTradesReclOpen",
		NO_TRADES_RECLAMATION_CLOSED	= "noTradesReclClosed",
		NO_TRADES_LATE_ENTRY			= "noTradesLateEntry";
}
