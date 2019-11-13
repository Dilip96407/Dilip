package de.westlb.mgb.client.mask.model.bond;

import de.westlb.mgb.client.mask.model.equity.EquityTradeOvTradePanelModel;
import de.westlb.mgb.client.mask.model.shared.TradeOvMarketDataPanelModel;
import de.westlb.mgb.client.mask.model.shared.TradeOverviewModel;

/**
 * @author M. Boerner
 */
public interface BondTradeOvMarketDataPanelModel {
	public boolean isPriceToleranceViolation();

	public static final String 
		P_BND_INSTRUMENT=EquityTradeOvTradePanelModel.P_INSTRUMENT,
		P_BND_CURRENCY=TradeOvMarketDataPanelModel.MD_CURRENCY,
		P_BND_REQUESTSTRING=TradeOvMarketDataPanelModel.MD_REQUESTSTRING,
		P_BND_REQUESTDATE=TradeOvMarketDataPanelModel.MD_REQUESTDATE,
		P_BND_PRICEMIN=TradeOvMarketDataPanelModel.MD_PRICEMIN,
		P_BND_PRICEMAX=TradeOvMarketDataPanelModel.MD_PRICEMAX,
		P_BND_EXCHANGE=TradeOvMarketDataPanelModel.MD_EXCHANGE,
		P_BND_SOURCE=TradeOvMarketDataPanelModel.MD_SOURCE,
		P_BND_TIME=TradeOvMarketDataPanelModel.MD_TIME,
		P_BND_PRICE=TradeOverviewModel.P_PRICE,
		P_BND_PRICE_DIFFERENCE=TradeOverviewModel.P_PRICE_DIFFERENCE,
		P_BND_PRICE_TOLERANCE=TradeOverviewModel.P_PRICE_TOLERANCE,
		P_BND_THEORETIC_PRICE="theoreticPrice",
		P_BND_ADDITIONAL_PRICE="additionalPrice";
}
