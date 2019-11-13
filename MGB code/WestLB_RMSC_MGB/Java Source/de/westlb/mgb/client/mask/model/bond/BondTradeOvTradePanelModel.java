package de.westlb.mgb.client.mask.model.bond;

import de.westlb.mgb.client.mask.model.equity.EquityTradeOvTradePanelModel;

/**
 * @author WSY4148
 *
 * For attibutes which are directly mapped to the
 * gui, the string constants defined here must match
 * the property name of the value object "TradeOververview".
 */
public interface BondTradeOvTradePanelModel {

	public boolean isSparkasse();

	public static final String 
	P_BND_INSTRUMENT_NAME=	"instrumentName",
	P_BND_VOLUME=		  	EquityTradeOvTradePanelModel.P_VOLUME,
	P_BND_PRICE=		  	EquityTradeOvTradePanelModel.P_PRICE,	
	P_BND_TRADE_TIME=	  	EquityTradeOvTradePanelModel.P_TRADE_TIME,
	P_BND_SMALL_CUSTOMER= 	"smallCustomer",
	P_BND_LATE_DEAL=	  	EquityTradeOvTradePanelModel.P_LATE_ENTRY,	
	P_BND_ISIN=			  	EquityTradeOvTradePanelModel.P_ISIN,
	P_BND_CURRENCY=		  	EquityTradeOvTradePanelModel.P_CURRENCY,
	P_BND_TURNOVER=		  	EquityTradeOvTradePanelModel.P_TURNOVER,
	P_BND_SYSTEM_TIME=	  	EquityTradeOvTradePanelModel.P_SYSTEM_TIME,
	P_BND_CANCELATION=	  	"cancelation",
	P_BND_LOW_TURNOVER=	  	"lowTurnOver",
	P_BND_TRADE_TYPE=	  	"tradeType",
	P_BND_MATURITY_TIME=  	"maturityDate",	
	P_BND_TRADE_ID=		  	EquityTradeOvTradePanelModel.P_TRADE_ID,
	P_BND_TRADER_NAME=	  	EquityTradeOvTradePanelModel.P_TRADER_NAME,
	P_BND_HIGH_TURNOVER=  	"highTurnOver",
	P_BND_TRADE_CATEGORY=	"tradeCategory",
	P_BND_COUNTERPARTY=		EquityTradeOvTradePanelModel.P_COUNTERPARTY,
	P_BND_COUNTERPARTY_REF=	"counterpartyReference",
	P_BND_TRADE_STATE=		"tradeState",
	P_BND_TRADER_ID=		EquityTradeOvTradePanelModel.P_TRADER_ID,
	P_BND_SUB_TYPE=		  	"subType",
	P_BND_BOOK=			  	EquityTradeOvTradePanelModel.P_BOOK,
	P_BND_FREE_TEXT=	  	EquityTradeOvTradePanelModel.P_FREE_TEXT,
	P_BND_DESCRIPTION=	  	"description",
	P_BND_UPDATED_BY=	  	"updatedBy",
	P_BND_STRUCTURE=		"structure",
    P_BND_VIVALDIS_TRADE=   "vivaldisTrade",
    P_BND_MARKETER=         "marketer",
    P_BND_REPORT_LOCATION=  EquityTradeOvTradePanelModel.P_REPORT_LOCATION,
    P_BND_TRADER_LOCATION=  EquityTradeOvTradePanelModel.P_TRADER_LOCATION
			;
}
