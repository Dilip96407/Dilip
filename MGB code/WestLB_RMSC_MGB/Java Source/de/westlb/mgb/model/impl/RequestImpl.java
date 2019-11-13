package de.westlb.mgb.model.impl;

import java.util.Calendar;

import de.westlb.mgb.model.definition.MarketDataSourceDef;


/**
 * 
 * @modelguid {3AD03832-1A3A-415A-B14C-C0AB005B1732}
 */
public abstract class RequestImpl extends EntityImpl implements MarketDataSourceDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5314560052476331445L;

	public abstract String getMarketDataSource();
	
	/** @modelguid {B2A9F1C9-2169-4D97-A2E4-116999FF39E2} */
	private PriceImpl priceResult;
	
	/** @modelguid {1CC4695B-C2DF-40A1-A776-D006CBD42F32} */
	private String requestString;

	/** @modelguid {7FC96B18-E374-4A7E-9C9F-ADB619347B64} */
	private Calendar requestDate;

	/** @modelguid {691A5613-6D26-4E8A-90AF-43E3F1150869} */
    public static final int REQUEST_STATE_MAX_LENGTH = 255;
	private String requestState;

	/** @modelguid {DDF9601A-CA24-4DBB-BFD0-A2FC899340E9} */
	private String requestType;
	
	/** @modelguid {8166763E-F09A-4788-AE02-1EAF4093D844} */
	private PriceCheckCategoryImpl priceCheckCategory;
	
	/** @modelguid {C8FDDE83-F779-4417-9173-D76E730E6280} */
	private AddonImpl addon;

	/** @modelguid {7A48F0B3-33E0-4042-BBC4-CB684C91B7FB} */
	private TradeImpl trade;

	/** @modelguid {1573B7F7-A2C7-424F-B6EF-44735D5B14ED} */
	@Override
    public Long getLongId() {
		return super.getLongId();
	}

	/**
	 * Returns the requestDate.
	 * @return Date
	 * @modelguid {87B64D84-A781-46D1-B222-00D40F0EA63A}
	 */
	public Calendar getRequestDate() {
		return requestDate;
	}

	/**
	 * Sets the requestDate.
	 * @param requestDate The requestDate to set
	 * @modelguid {645B9F54-7ED5-48A4-897D-21A21CB04D3F}
	 */
	public void setRequestDate(Calendar requestDate) {
		this.requestDate = requestDate;
	}
	/**
	 * Returns the priceResult.
	 * @return PriceImpl
	 * @modelguid {CA295FDC-6B22-42EF-99C2-3053634B758F}
	 */
	public PriceImpl getPriceResult() {
		return priceResult;
	}

	/**
	 * Sets the priceResult.
	 * @param priceResult The priceResult to set
	 * @modelguid {38CCA931-142A-48EF-B3EF-B3EB58AF257B}
	 */
	public void setPriceResult(PriceImpl priceResult) {
		this.priceResult = priceResult;
	}

	/**
	 * Returns the trade.
	 * @return TradeImpl
	 * @modelguid {FC40AD5C-EA1B-4E77-B02E-786CF894C131}
	 */
	public TradeImpl getTrade() {
		return trade;
	}

	/**
	 * Sets the trade.
	 * @param trade The trade to set
	 * @modelguid {A87EA102-0A61-4083-AD8F-013269AECAEE}
	 */
	public void setTrade(TradeImpl trade) {
		this.trade = trade;
	}

	/**
	 * Returns the requestString.
	 * @return String
	 * @modelguid {58217D2C-0F30-4DD2-A59E-1E515E9458DF}
	 */
	public String getRequestString() {
		return requestString;
	}

	/**
	 * Sets the requestString.
	 * @param requestString The requestString to set
	 * @modelguid {CFDB3F2D-0F27-41EA-8D3F-7B755FBFAE6A}
	 */
	public void setRequestString(String requestString) {
		this.requestString = requestString;
	}

	/**
	 * Returns the addon.
	 * @return AddonImpl
	 * @modelguid {F660ABA8-8537-473C-B931-403768D5013E}
	 */
	public AddonImpl getAddon() {
		return addon;
	}

	/**
	 * Returns the priceCheckCategory.
	 * @return PriceCheckCategoryImpl
	 * @modelguid {2811C482-D19D-448E-9B4D-2EF7C71BFC4A}
	 */
	public PriceCheckCategoryImpl getPriceCheckCategory() {
		return priceCheckCategory;
	}

	/**
	 * Returns the requestStatus.
	 * @return Date
	 * @modelguid {24724336-91C9-4B71-9226-D5D758628FFA}
	 */
	public String getRequestState() {
		return requestState;
	}

	/**
	 * Sets the addon.
	 * @param addon The addon to set
	 * @modelguid {2A6990AD-8F5C-40F7-8617-A57238B44D81}
	 */
	public void setAddon(AddonImpl addon) {
		this.addon = addon;
	}

	/**
	 * Sets the priceCheckCategory.
	 * @param priceCheckCategory The priceCheckCategory to set
	 * @modelguid {065F89D8-18A0-4CB7-BB82-726BE30E4227}
	 */
	public void setPriceCheckCategory(PriceCheckCategoryImpl priceCheckCategory) {
		this.priceCheckCategory = priceCheckCategory;
	}

	/**
	 * Sets the requestStatus.
	 * @param requestStatus The requestStatus to set
	 * @modelguid {AF14CC59-2D3C-4C22-8D50-C81406B78E5B}
	 */
	public void setRequestState(String requestStatus) {
		this.requestState = requestStatus;
	}

	/**
	 * Returns the requestType.
	 * @return String
	 * @modelguid {B61C441E-7EBA-4564-A19C-F38C478D14D7}
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * Sets the requestType.
	 * @param requestType The requestType to set
	 * @modelguid {2A62758C-CFBD-41C5-9DE3-029C715F1713}
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

}

