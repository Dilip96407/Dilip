package de.westlb.mgb.client.mask.model.repo;

/**
 * @author WSY4148
 *
 * Model interface for the CheckStateOverview-Panel
 */
public interface RepoTradeOvMarketDataPanelModel {
	public static final String 
		MD_REPO_REQUESTSTRING="mdRequestString",
		MD_REPO_REQUESTDATE="mdRequestDate",
		MD_REPO_PRICEMIN="mdPriceMin",
		MD_REPO_PRICEMAX="mdPriceMax",
		MD_REPO_EXCHANGE="mdExchange",
		MD_REPO_CURRENCY="mdCurrency",
		MD_REPO_SOURCE="mdSource",
		MD_REPO_TIME="mdTime",
		MD_REPO_REFERENCE_PRICE="marketPriceUnderlying",
		MD_REPO_UNDERLYING_DIFFERENCE="currentPriceDifference",
		MD_REPO_UNDERLYING_TOLERANCE="priceTolerance"		
		;
}
