package de.westlb.mgb.client.mask.model.repo;


/**
 * @author wsy4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface RepoTradeOvCheckPanelModel {
	
	public boolean isPriceToleranceViolation();
	
	public static final String 
		P_REPO_RATE="repoRate",
		P_REPO_MARKET_PRICE="marketPrice",
    	P_REPO_BASE_POINT_DIFFERENCE="basePointDifference",
    	P_REPO_BASE_POINT_TOLERANCE="basePointTolerance",
		P_REPO_PROFIT_LOSS_EFFECT="profitLossEffect",
		P_REPO_FOREIGN_EXCHANGE_RATE="foreignExchangeRate",
		P_REPO_PORTFOLIO="portfolio",
		P_REPO_NPV="npv"
	;
}
