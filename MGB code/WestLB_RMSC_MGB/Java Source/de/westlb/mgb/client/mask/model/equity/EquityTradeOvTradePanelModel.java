package de.westlb.mgb.client.mask.model.equity;

/**
 * @author WSY4148
 *
 * For attibutes which are directly mapped to the
 * gui, the string constants defined here must match
 * the property name of the value object "TradeOververview".
 */
public interface EquityTradeOvTradePanelModel {
	
	public boolean isPriceToleranceViolation();
	public boolean isLateEntry();
	
	public static final String 
		P_TRADE_ID="tradeId",
		P_INSTRUMENT="instrument",
		P_PRICE="tradePrice",
		P_VOLUME="volume",
		P_CURRENCY="currency",
		P_EXCHANGE="exchange",
		P_NET="net",
		P_INTERNAL="internal",
		P_AUTOMATIC="automatic",
		P_ISIN="isin",
		P_BUY="buy",
		P_BOOK="bookId",
		P_SETTLEMENT_TIME="settlementDate",
		P_TRADE_TIME="tradeDate",
		P_SYSTEM_TIME="systemDate",
        P_TRADER_ID="traderId",
        P_TRADER_NAME="traderName",
		P_STORNO="storno",
		P_MISCH_PRICE="mischPrice",
		P_PRICE_TOLERANCE="priceTolerance",
		P_PRICE_DIFFERENCE="currentPriceDifference",
		P_COUNTERPARTY="counterparty",
		P_LATE_ENTRY="lateEntry",
		P_PRICE_TOLERANCE_VIOLATION="priceToleranceViolation",
		P_FREE_TEXT="memo",
	    P_TURNOVER="turnover",
        P_GROUP_ID="stornoGroupId",
		P_SPARKASSE="sparkasse",
	    P_CONTRACT_SIZE="volume2",
        P_QUOTE_TYPE="subType",
        P_FX_RATE="fxRate",
        P_AMEND_DATE="amendDate",
        P_REPORT_LOCATION="reportLocation",
        P_TRADER_LOCATION="traderLocation";
}
