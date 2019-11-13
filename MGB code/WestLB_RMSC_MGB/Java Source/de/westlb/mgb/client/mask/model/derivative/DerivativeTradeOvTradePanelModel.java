package de.westlb.mgb.client.mask.model.derivative;

import de.westlb.mgb.client.mask.model.bond.BondTradeOvTradePanelModel;
import de.westlb.mgb.client.mask.model.equity.EquityTradeOvTradePanelModel;
import de.westlb.mgb.client.mask.model.mmk.MmkTradeOvTradePanelModel;

/**
 * @author WSY4148
 *
 * For attibutes which are directly mapped to the
 * gui, the string constants defined here must match
 * the property name of the value object "TradeOververview".
 */
public interface DerivativeTradeOvTradePanelModel {

	public static final String 
	P_DVD_TRADE_ID=		  	EquityTradeOvTradePanelModel.P_TRADE_ID,

	P_DVD_DEAL_ID=	  		MmkTradeOvTradePanelModel.P_MMK_GROUP_ID,
	P_DVD_STRUCTURE=	  	BondTradeOvTradePanelModel.P_BND_STRUCTURE,
	P_DVD_TRADE_TYPE=	  	MmkTradeOvTradePanelModel.P_MMK_TRADE_TYPE,
	P_DVD_INSTRUMENT_NAME=	EquityTradeOvTradePanelModel.P_INSTRUMENT,
	P_DVD_TRADER_NAME=	  	BondTradeOvTradePanelModel.P_BND_TRADER_NAME,
	P_DVD_TRADER_ID=		EquityTradeOvTradePanelModel.P_TRADER_ID,
	P_DVD_BOOK=			  	EquityTradeOvTradePanelModel.P_BOOK,
	P_DVD_COMPANY=			"company",
	P_DVD_COUNTERPARTY=		EquityTradeOvTradePanelModel.P_COUNTERPARTY,
	P_DVD_INTERNAL=			EquityTradeOvTradePanelModel.P_INTERNAL,
	P_DVD_AMENDED=          "stornoGroup",
	P_DVD_TRADE_TIME=	  	EquityTradeOvTradePanelModel.P_TRADE_TIME,
    P_DVD_SYSTEM_TIME=      EquityTradeOvTradePanelModel.P_SYSTEM_TIME,
    P_DVD_TRADER_LOCATION=  EquityTradeOvTradePanelModel.P_TRADER_LOCATION,
    P_DVD_REPORT_LOCATION=  EquityTradeOvTradePanelModel.P_REPORT_LOCATION,
	P_DVD_DESCRIPTION=	  	BondTradeOvTradePanelModel.P_BND_DESCRIPTION,
	P_DVD_ASSETS=	  		"assets"
	;
}
