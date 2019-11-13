package de.westlb.mgb.client.mask.model.mmk;

import de.westlb.mgb.client.mask.model.shared.TradeOvMarketDataPanelModel;

/**
 * @author M. Boerner
 */
public interface MmkTradeOvMarketDataPanelModel {
	public boolean isPriceToleranceViolation();

	public static final String 
		P_MMK_REQUESTSTRING=TradeOvMarketDataPanelModel.MD_REQUESTSTRING,
		P_MMK_REQUESTDATE=TradeOvMarketDataPanelModel.MD_REQUESTDATE,
        P_MMK_PRICE1=TradeOvMarketDataPanelModel.MD_PRICEMIN,
        P_MMK_PRICE2=TradeOvMarketDataPanelModel.MD_PRICEMAX,
		P_MMK_EXCHANGE=TradeOvMarketDataPanelModel.MD_EXCHANGE,
		P_MMK_SOURCE=TradeOvMarketDataPanelModel.MD_SOURCE,
        P_MMK_TIME=TradeOvMarketDataPanelModel.MD_TIME,
        P_MMK_ADDITIONAL_PRICE="additionalPrice";
}
