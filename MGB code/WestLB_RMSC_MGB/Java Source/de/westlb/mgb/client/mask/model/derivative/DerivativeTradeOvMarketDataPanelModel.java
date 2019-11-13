package de.westlb.mgb.client.mask.model.derivative;

import de.westlb.mgb.client.mask.model.bond.BondTradeOvTradePanelModel;
import de.westlb.mgb.client.mask.model.equity.EquityTradeOvTradePanelModel;

/**
 * @author WSY4148
 *
 * For attibutes which are directly mapped to the
 * gui, the string constants defined here must match
 * the property name of the value object "TradeOververview".
 */
public interface DerivativeTradeOvMarketDataPanelModel {

	public boolean isPriceToleranceViolation();

	public static final String 
	P_DVD_NPV=	  			EquityTradeOvTradePanelModel.P_PRICE,
	P_DVD_VEGA=	  			"vega",
	P_DVD_DELTA=	  		"delta",
	P_DVD_PREMIUM=			"premium",
	P_DVD_AMENDMENT_NPV_CHANGE= "amendmentNpvChange",
	P_DVD_VOLUME=			EquityTradeOvTradePanelModel.P_VOLUME,
	P_DVD_TURNOVER=	  		BondTradeOvTradePanelModel.P_BND_TURNOVER,
	P_DVD_DEVIATION=		EquityTradeOvTradePanelModel.P_PRICE_DIFFERENCE,
	P_DVD_TOLERANCE=		EquityTradeOvTradePanelModel.P_PRICE_TOLERANCE
	;
}
