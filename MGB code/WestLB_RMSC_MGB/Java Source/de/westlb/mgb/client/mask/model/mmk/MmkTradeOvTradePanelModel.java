package de.westlb.mgb.client.mask.model.mmk;

import de.westlb.mgb.client.mask.model.equity.EquityTradeOvTradePanelModel;

/**
 * @author WSY4148
 *
 * For attibutes which are directly mapped to the
 * gui, the string constants defined here must match
 * the property name of the value object "TradeOververview".
 */
public interface MmkTradeOvTradePanelModel {
	public boolean isPriceToleranceViolation();
	public boolean isLateEntry();
	
	public static final String 
		P_MMK_TRADE_ID=EquityTradeOvTradePanelModel.P_TRADE_ID,
		P_MMK_INSTRUMENT=EquityTradeOvTradePanelModel.P_INSTRUMENT,
		P_MMK_PRICE=EquityTradeOvTradePanelModel.P_PRICE,
		P_MMK_VOLUME=EquityTradeOvTradePanelModel.P_VOLUME,
		P_MMK_TRADE_TYPE="tradeType",
		P_MMK_TURNOVER="turnover",
		P_MMK_COUNTERPARTY=EquityTradeOvTradePanelModel.P_COUNTERPARTY,
		P_MMK_TRADE_TIME=EquityTradeOvTradePanelModel.P_TRADE_TIME,
		P_MMK_SYSTEM_TIME=EquityTradeOvTradePanelModel.P_SYSTEM_TIME,
		P_MMK_TRADER_NAME=EquityTradeOvTradePanelModel.P_TRADER_NAME,
		P_MMK_PRICE_TOLERANCE=EquityTradeOvTradePanelModel.P_PRICE_TOLERANCE,
		P_MMK_PRICE_DIFFERENCE=EquityTradeOvTradePanelModel.P_PRICE_DIFFERENCE,
		P_MMK_GROUP_ID="stornoGroupId",
		P_MMK_BOOK_ID=EquityTradeOvTradePanelModel.P_BOOK,
		P_MMK_RECORD_TYPE="recordType",
		P_MMK_MATURITY_DATE="maturityDate",
		P_MMK_MEMO="memo",
		P_MMK_MARGIN="margin",
		P_MMK_FX_RATE=EquityTradeOvTradePanelModel.P_FX_RATE,
		P_MMK_AMEND_DATE=EquityTradeOvTradePanelModel.P_AMEND_DATE,
		P_MMK_TRADER_LOCATION=  EquityTradeOvTradePanelModel.P_TRADER_LOCATION,
		P_MMK_REPORT_LOCATION=  EquityTradeOvTradePanelModel.P_REPORT_LOCATION;
}
