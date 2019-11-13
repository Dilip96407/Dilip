package de.westlb.mgb.client.mask.model.repo;

import de.westlb.mgb.client.mask.model.equity.EquityTradeOvTradePanelModel;

/**
 * @author wsy4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface RepoTradeOvTradePanelModel extends EquityTradeOvTradePanelModel {

	public boolean isSparkasse();

	public static final String
		P_REPO_START_CASH="startCash",
		P_REPO_END_CASH="endCash",
    	P_REPO_INSTRUMENT_TYPE="instrumentType",
		P_REPO_UNDERLYING_TYPE="underlyingType",
		P_REPO_UNDERLYING_VALUE_GROUP="underlyingValueGroup",
		P_REPO_START_DATE="startDate",
		P_REPO_END_DATE="endDate",
		P_REPO_OPEN_END="openEnd",
		P_REPO_MATURITY_DAYS="maturityDays",
		P_REPO_AMEND_DATE="amendDate",
		P_REPO_VERIFY_DATE="verifyDate",
		P_REPO_MARKET_PRICE_UNDERLYING="marketPriceUnderlying",
		P_REPO_DEAL_ACCRUED_INTEREST="dealAccruedInterest",
		P_REPO_START_PRICE="startPrice",
		P_REPO_BOND_ACCRUED_INTEREST="bondAccruedInterest",
		P_REPO_MARKET_TYPE="repoMarketType",
		P_REPO_CUSTOMER_TYPE="counterpartyReference",
		P_REPO_TRADER_LOCATION=EquityTradeOvTradePanelModel.P_TRADER_LOCATION,
		P_REPO_REPORT_LOCATION=EquityTradeOvTradePanelModel.P_REPORT_LOCATION;
	;
}
