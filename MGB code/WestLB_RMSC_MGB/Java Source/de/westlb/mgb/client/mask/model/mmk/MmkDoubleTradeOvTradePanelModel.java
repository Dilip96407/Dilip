package de.westlb.mgb.client.mask.model.mmk;

import de.westlb.mgb.client.mask.model.equity.EquityTradeOvTradePanelModel;

/**
 * @author WSY4148
 *
 * For attibutes which are directly mapped to the
 * gui, the string constants defined here must match
 * the property name of the value object "TradeOververview".
 */
public interface MmkDoubleTradeOvTradePanelModel {
	public boolean isPriceToleranceViolation();
	public boolean isLateEntry();
	
	public static final String 
		P_MMK2_TRADE_ID=EquityTradeOvTradePanelModel.P_TRADE_ID,
		P_MMK2_INSTRUMENT=EquityTradeOvTradePanelModel.P_INSTRUMENT,
		P_MMK2_PRICE=EquityTradeOvTradePanelModel.P_PRICE,
		P_MMK2_TRADE_TYPE=MmkTradeOvTradePanelModel.P_MMK_TRADE_TYPE,
		P_MMK2_TURNOVER=MmkTradeOvTradePanelModel.P_MMK_TURNOVER,
        P_MMK2_NEAR_DATE=EquityTradeOvTradePanelModel.P_SETTLEMENT_TIME,
        P_MMK2_NEAR_AMOUNT=EquityTradeOvTradePanelModel.P_VOLUME,
        P_MMK2_FWD_POINTS_NEAR_LEG="fwdPointsNearLeg",
        P_MMK2_MARKET_POINTS_NEAR_LEG="marketPointsNearLeg",
        P_MMK2_NEAR_TURNOVER="nearTurnover",
        P_MMK2_FAR_DATE=MmkTradeOvTradePanelModel.P_MMK_MATURITY_DATE,
        P_MMK2_FAR_AMOUNT=EquityTradeOvTradePanelModel.P_VOLUME+"2",
        P_MMK2_FWD_POINTS_FAR_LEG="fwdPointsFarLeg",
        P_MMK2_MARKET_POINTS_FAR_LEG="marketPointsFarLeg",
        P_MMK2_FAR_TURNOVER="farTurnover",
		P_MMK2_COUNTERPARTY=EquityTradeOvTradePanelModel.P_COUNTERPARTY,
		P_MMK2_TRADE_TIME=EquityTradeOvTradePanelModel.P_TRADE_TIME,
		P_MMK2_SYSTEM_TIME=EquityTradeOvTradePanelModel.P_SYSTEM_TIME,
		P_MMK2_TRADER_NAME=MmkTradeOvTradePanelModel.P_MMK_TRADER_NAME,
		P_MMK2_PRICE_TOLERANCE=EquityTradeOvTradePanelModel.P_PRICE_TOLERANCE,
		P_MMK2_PRICE_DIFFERENCE=EquityTradeOvTradePanelModel.P_PRICE_DIFFERENCE,
		P_MMK2_BOOK_ID=EquityTradeOvTradePanelModel.P_BOOK,
		P_MMK2_RECORD_TYPE=MmkTradeOvTradePanelModel.P_MMK_RECORD_TYPE,
		P_MMK2_MEMO=MmkTradeOvTradePanelModel.P_MMK_MEMO,
		P_MMK2_MARGIN=MmkTradeOvTradePanelModel.P_MMK_MARGIN,
        P_MMK2_FX_RATE=MmkTradeOvTradePanelModel.P_MMK_FX_RATE,
        P_MMK2_AMEND_DATE=MmkTradeOvTradePanelModel.P_MMK_AMEND_DATE
		;
}
