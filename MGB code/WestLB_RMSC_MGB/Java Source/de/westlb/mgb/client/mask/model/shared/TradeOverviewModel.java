package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import de.westlb.mgb.client.application.RefreshHelper;
import de.westlb.mgb.client.mask.model.bond.BondTradeOvMarketDataPanelModel;
import de.westlb.mgb.client.mask.model.bond.BondTradeOvTradePanelModel;
import de.westlb.mgb.client.mask.model.derivative.DerivativeTradeOvMarketDataPanelModel;
import de.westlb.mgb.client.mask.model.derivative.DerivativeTradeOvTradePanelModel;
import de.westlb.mgb.client.mask.model.equity.EquityTradeOvParameterPanelModel;
import de.westlb.mgb.client.mask.model.equity.EquityTradeOvTradePanelModel;
import de.westlb.mgb.client.mask.model.mmk.MmkDoubleTradeOvTradePanelModel;
import de.westlb.mgb.client.mask.model.mmk.MmkTradeOvMarketDataPanelModel;
import de.westlb.mgb.client.mask.model.mmk.MmkTradeOvTradePanelModel;
import de.westlb.mgb.client.mask.model.repo.RepoTradeOvCheckPanelModel;
import de.westlb.mgb.client.mask.model.repo.RepoTradeOvMarketDataPanelModel;
import de.westlb.mgb.client.mask.model.repo.RepoTradeOvTradePanelModel;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.PriceVo;
import de.westlb.mgb.client.server.vo.RepoTradeOverviewVo;
import de.westlb.mgb.client.server.vo.RequestVo;
import de.westlb.mgb.client.server.vo.TradeOverviewVo;
import de.westlb.mgb.client.ui.selection_list.AllManualStateCodeList;
import de.westlb.mgb.client.ui.selection_list.AutoStateCodeList;
import de.westlb.mgb.client.ui.selection_list.ReclamationStateCodeList;
import de.westlb.mgb.client.ui.util.AttachmentModel;
import de.westlb.mgb.model.impl.TradeImpl;

/**
 * @author Manfred Boerner
 */

public class TradeOverviewModel
	extends AbstractModel
	implements
		TradeOvResultPanelModel,
		TradeOvMarketDataPanelModel,
		EquityTradeOvTradePanelModel,
		EquityTradeOvParameterPanelModel,
		BondTradeOvTradePanelModel,
		BondTradeOvMarketDataPanelModel,
		RepoTradeOvTradePanelModel,
		RepoTradeOvCheckPanelModel,
		RepoTradeOvMarketDataPanelModel,
		DerivativeTradeOvTradePanelModel,
		DerivativeTradeOvMarketDataPanelModel,
		MmkTradeOvTradePanelModel,
		MmkTradeOvMarketDataPanelModel,
		MmkDoubleTradeOvTradePanelModel {
	private String RESOURCE_BASE = getResourceBase();

	public static final String P_BUSINESS_OBJECT = "businessObject";

	// The value object of the current trade
	private TradeOverviewVo tradeOverviewVo = null;
	private AutoStateCodeList autoStateCodeList = null;
	private AllManualStateCodeList manualStateCodeList = null;
	private ReclamationStateCodeList reclamationStateCodeList = null;

	private final String[] stdPropertyNames = new String[] {
		// Trade properties
			P_INSTRUMENT,
			P_PRICE,
			P_VOLUME,
			P_CURRENCY,
			P_EXCHANGE,
			P_NET,
			P_INTERNAL,
			P_AUTOMATIC,
			P_ISIN,
			P_BUY,
			P_BOOK,
			P_SETTLEMENT_TIME,
			P_SYSTEM_TIME,
			P_AMEND_DATE,
			P_TRADE_ID,
			P_TRADER_ID,
			P_TRADER_NAME,
			P_STORNO,
			P_MISCH_PRICE,
			P_PRICE_TOLERANCE,
			P_PRICE_DIFFERENCE,
			P_COUNTERPARTY,
			P_TRADE_TIME,
			P_LATE_ENTRY,
			P_PRICE_TOLERANCE_VIOLATION,
			P_SPARKASSE,
			P_FX_RATE,
			P_REPORT_LOCATION,
			P_TRADER_LOCATION,

		// Money market specific trade properties
		P_MMK_TRADE_TYPE, P_MMK_TRADER_NAME, P_MMK_GROUP_ID, P_MMK_MATURITY_DATE, P_MMK_MEMO, P_MMK_MARGIN,

        // Money market specific trade properties
		P_MMK2_FWD_POINTS_NEAR_LEG,	P_MMK2_MARKET_POINTS_NEAR_LEG, P_MMK2_NEAR_TURNOVER,
		P_MMK2_FAR_AMOUNT, P_MMK2_FWD_POINTS_FAR_LEG, P_MMK2_MARKET_POINTS_FAR_LEG,
		P_MMK2_FAR_TURNOVER,
		
		// Bond specific trade properties
		P_BND_INSTRUMENT_NAME, P_BND_STRUCTURE,
		P_BND_SMALL_CUSTOMER, P_BND_TURNOVER, P_BND_CANCELATION, P_BND_LOW_TURNOVER, P_BND_TRADE_TYPE,
		P_BND_TRADER_NAME,	P_BND_HIGH_TURNOVER, P_BND_TRADE_CATEGORY,
		P_BND_TRADE_STATE, P_BND_SUB_TYPE, P_BND_FREE_TEXT, P_BND_THEORETIC_PRICE, P_BND_COUNTERPARTY_REF,
		P_BND_DESCRIPTION, P_BND_UPDATED_BY, P_BND_VIVALDIS_TRADE, P_BND_ADDITIONAL_PRICE, P_BND_MARKETER, 

		// derivative specific trade properties
		P_DVD_ASSETS, P_DVD_COMPANY, P_DVD_DELTA, P_DVD_VEGA, P_DVD_PREMIUM, P_DVD_AMENDED, P_DVD_AMENDMENT_NPV_CHANGE,

		// Market data properties
		MD_CURRENCY,

		// State properties
		RES_MANUAL_STATE_COMMENT, RES_MANUAL_STATE_BY_NAME, };

	/** Definition der Namen aller Properties im Model */
	private final String[] specialPropertyNames = new String[] { P_PRICE_TOLERANCE_VIOLATION,

		// Market data properties
		MD_REQUESTSTRING, MD_REQUESTDATE, MD_PRICEMIN, MD_PRICEMAX, MD_SOURCE, MD_TIME,

		// State properties
		RES_AUTO_STATE,
			RES_MANUAL_STATE,
			RES_MANUAL_STATE_ATTACHMENT_MODEL,
			RES_RECLAMATION_STATE,
			RES_RECLAMATION_IS_CLOSED,
			RES_RECLAMATION_CLOSED_COMMENT,
			RES_RECLAMATION_STATE_ATTACHMENT_MODEL,
			RES_RECLAMATION_LEVEL,
			P_MMK_TURNOVER,
			P_MMK_RECORD_TYPE,
			P_BND_MATURITY_TIME, 
			};

	private final String[] stdRepoPropertyNames = new String[] {
		// Repo Trade Properties
		P_REPO_START_CASH,
			P_REPO_END_CASH,
			P_REPO_INSTRUMENT_TYPE,
			P_REPO_UNDERLYING_TYPE,
			P_REPO_UNDERLYING_VALUE_GROUP,
			P_REPO_START_DATE,
			P_REPO_END_DATE,
			P_REPO_OPEN_END,
			P_REPO_MATURITY_DAYS,
			P_REPO_VERIFY_DATE,			   
			P_REPO_MARKET_PRICE_UNDERLYING,
			P_REPO_DEAL_ACCRUED_INTEREST,
			P_REPO_START_PRICE,			   
			P_REPO_BOND_ACCRUED_INTEREST,  
			P_REPO_MARKET_TYPE,					   

		//Repo Check data properties
			P_REPO_NPV,					   
			P_REPO_RATE,
			P_REPO_MARKET_PRICE,
			P_REPO_BASE_POINT_DIFFERENCE,
			P_REPO_BASE_POINT_TOLERANCE,
			P_REPO_PROFIT_LOSS_EFFECT,
			P_REPO_FOREIGN_EXCHANGE_RATE,
			P_REPO_PORTFOLIO
			};

	// Std trade parameter properties
	private final String[] stdParameterPropertyNames =
		new String[] { CATEGORY_NAME, PRICE_TOLERANCE_PERCENT, PRICE_TOLERANCE_ABSOLUTE, TIME_TOLERANCE, ADDON_NAME, PRICE_TOLERANCE_ADDON, TIME_TOLERANCE_ADDON, };

	/**
	 * Default Konstruktor erstellt ein leeres Model
	 *
	 */
	public TradeOverviewModel() {
		autoStateCodeList = new AutoStateCodeList();
		manualStateCodeList = new AllManualStateCodeList();
		reclamationStateCodeList = new ReclamationStateCodeList();
		setPropertyNames(stdPropertyNames);
		setPropertyNames(specialPropertyNames);
		setPropertyNames(stdRepoPropertyNames);
		setPropertyNames(stdParameterPropertyNames);
	}

	/**
	 * Konstruktor mit gleichzeitigem Setzen des Business Objects
	 */
	public TradeOverviewModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}

	/**
	 * Fill the model from the business model.
	 *
	 */
	private void fillModel() {
		tradeOverviewVo = null;
		if (getBusinessObject() != null) {
			try {
				tradeOverviewVo = MgbServiceFactory.getService().getTradeOverview(getBusinessObject());
			} catch (RemoteException e) {
				handleRemoteException(e);
				return;
			}
		}
		RefreshHelper.registerCache(this, RefreshHelper.TRADE, getBusinessObject());

		if (tradeOverviewVo == null) {
			return;
		}

		propagateProperties(stdPropertyNames, tradeOverviewVo);

		if (tradeOverviewVo.getMaturityDate() != null) {
			setProperty(P_BND_MATURITY_TIME, tradeOverviewVo.getMaturityDate());
		}

		PriceVo currentPriceVo = tradeOverviewVo.getCurrentPrice();
		if (currentPriceVo != null) {
			setDoubleProperty(MD_PRICEMIN, currentPriceVo.getMinPrice(), format3GMin0Max10);
			setDoubleProperty(MD_PRICEMAX, currentPriceVo.getMaxPrice(), format3GMin0Max10);
			if (currentPriceVo.getPriceDate() != null) {
				setProperty(MD_TIME, currentPriceVo.getPriceDate());
			}
		}

		RequestVo requestVo = tradeOverviewVo.getCurrentRequest();
		if (requestVo != null) {
			String requestString = "";
			if (requestVo.getRequestString() != null) {
			    requestString = requestVo.getRequestString();
			}
		    requestString = requestString + " (ID="+requestVo.getRequestId()+ ")";
			String requestSource = requestVo.getPriceSource();
			setProperty(MD_REQUESTSTRING, requestString);
			setProperty(MD_SOURCE, requestSource);
			if (requestVo.getRequestDate() != null) {
				setProperty(MD_REQUESTDATE, requestVo.getRequestDate());
			}
		}

		Object automState = autoStateCodeList.get(tradeOverviewVo.getAutomaticStateCode());
		Object manualState = manualStateCodeList.get(tradeOverviewVo.getManualStateCode());
		Object reclamationState = reclamationStateCodeList.get(tradeOverviewVo.getReclamationStateCode());

		setProperty(RES_AUTO_STATE, automState);
		setProperty(RES_MANUAL_STATE, manualState);
		setProperty(RES_RECLAMATION_STATE, reclamationState);
		setProperty(RES_RECLAMATION_LEVEL, Integer.valueOf(tradeOverviewVo.getReminderLevel()));
		setProperty(RES_RECLAMATION_IS_CLOSED, tradeOverviewVo.getReclamationIsClosed());
		setProperty(RES_RECLAMATION_CLOSED_COMMENT, tradeOverviewVo.getReclamationClosedComment());
		
		setDoubleProperty(P_MMK_TURNOVER, tradeOverviewVo.getTurnover(), format3G0D);

		setProperty(P_MMK_RECORD_TYPE, getResourceString(RESOURCE_BASE + "L_RECORD_TYPE_" + tradeOverviewVo.getRecordType()));

		AttachmentModel attachmentModel;
		attachmentModel = new AttachmentModel() {
			@Override
            public Long getId() {
				return tradeOverviewVo.getManualStateAttachmentId();
			}

			@Override
            public String getFileName() {
				return tradeOverviewVo.getManualStateAttachmentName();
			}

		};
		setProperty(RES_MANUAL_STATE_ATTACHMENT_MODEL, attachmentModel);

		attachmentModel = new AttachmentModel() {
			@Override
            public Long getId() {
				return tradeOverviewVo.getReclamationStateAttachmentId();
			}

			@Override
            public String getFileName() {
				return tradeOverviewVo.getReclamationStateAttachmentName();
			}

		};
		setProperty(RES_RECLAMATION_STATE_ATTACHMENT_MODEL, attachmentModel);

		if (tradeOverviewVo instanceof RepoTradeOverviewVo) {
			propagateProperties(stdRepoPropertyNames, tradeOverviewVo);
			boolean priceTolViolation = Math.abs(((RepoTradeOverviewVo)tradeOverviewVo).getBasePointDifference()) > ((RepoTradeOverviewVo)tradeOverviewVo).getBasePointTolerance();
		    setProperty(P_PRICE_TOLERANCE_VIOLATION, Boolean.valueOf(priceTolViolation));
		} else {
			boolean priceTolViolation = isPriceToleranceViolation();
			if (currentPriceVo != null && tradeOverviewVo.getParameter() != null) {
				priceTolViolation =
					!TradeImpl.isTradePriceInRange(
						tradeOverviewVo.getTradePrice(),
						currentPriceVo.getMinPrice(),
						currentPriceVo.getMaxPrice(),
						tradeOverviewVo.getParameter().getPriceTolerancePercent() + tradeOverviewVo.getParameter().getPriceToleranceAddon(),
						tradeOverviewVo.getParameter().getPriceToleranceAbsolute());
			}
		    setProperty(P_PRICE_TOLERANCE_VIOLATION, Boolean.valueOf(priceTolViolation));
		}

		if (tradeOverviewVo.getParameter() != null) {
			propagateProperties(stdParameterPropertyNames, tradeOverviewVo.getParameter());
		}
	}

	@Override
    public AttachmentModel getManualStateAttachmentModel() {
		return (AttachmentModel) getProperty(RES_MANUAL_STATE_ATTACHMENT_MODEL);
	}

	@Override
    public AttachmentModel getReclamationStateAttachmentModel() {
		return (AttachmentModel) getProperty(RES_RECLAMATION_STATE_ATTACHMENT_MODEL);
	}

	@Override
    public boolean isPriceToleranceViolation() {
		return Boolean.TRUE.equals(getProperty(P_PRICE_TOLERANCE_VIOLATION));
	}
	
	@Override
    public boolean isReclamationClosed() {
		return Boolean.TRUE.equals(getProperty(RES_RECLAMATION_IS_CLOSED));
	}	

	@Override
    public boolean isLateEntry() {
		return Boolean.TRUE.equals(getProperty(P_LATE_ENTRY));
	}

	@Override
    public boolean isSparkasse() {
		return Boolean.TRUE.equals(getProperty(P_SPARKASSE));
	}

	public boolean isRepoTrade() {
		return tradeOverviewVo != null && tradeOverviewVo instanceof RepoTradeOverviewVo;
	}

    public boolean isDoubleTrade() {
        return tradeOverviewVo != null && tradeOverviewVo.getSourceSystemCode() != null && tradeOverviewVo.getSourceSystemCode().startsWith("TF");
    }

	/**
	 * Setzt das Business Object aus dem das Model seine Daten bezieht
	 *
	 * @param newBusinessObject FastSearchParameter
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		Object oldBusinessObject = getBusinessObject();
		super.setBusinessObject(newBusinessObject);
		fillModel();
		firePropertyChange(P_BUSINESS_OBJECT, oldBusinessObject, newBusinessObject);
	}
}
