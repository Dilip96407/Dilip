package de.westlb.mgb.model.impl;

import java.util.Calendar;


public class AssetImpl extends EntityImpl {

	/**
     * 
     */
    private static final long serialVersionUID = -3865720402803942570L;
    private String assetId;
	private String tradeSubType;
	private String callPut;
	private String payCurrency;
	private double payNotional;
	private String receiveCurrency;
	private double receiveNotional;
	private Calendar startDate;
	private Calendar maturityDate;
	private String payIndexBasis;
	private double payRateSpread;
	private String payDiscountCurve;
	private String receiveIndexBasis;
	private double receiveRateSpread;
	private String receiveDiscountCurve;
	private String capFloor;
	private String buySell;
	private double quantity;
	private String underlying;
	private double strike;
	private String style;
	private double premiumAmount;
	private String premiumCurrency;
	private Calendar expiryDate;
	private Calendar fixingDate;
	private Calendar paymentDate;
	private Calendar firstCouponDate;
	private String model;
	private String instrumentStyle;
	private String formula;
	private String productName;
	private String prodData;	
	private double npv;
	private double marketRateIR;
	private double marketRateVola;
	private double fxRate;
	private double delta;
	private String deltaCurrency;
	private double vega;
	private String vegaCurrency;

	private TradeImpl trade;

	public AssetImpl() {
	}

	public AssetImpl(TradeImpl trade, LoadSummitDerivativeImpl loaderObject) {
		convert(trade, loaderObject);
	}
	
	public void convert(TradeImpl trade, LoadSummitDerivativeImpl loaderObject) {
		setTrade(trade);
		if (loaderObject.getAssetId() == null || loaderObject.getAssetId().length() == 0) {
            setAssetId(loaderObject.getTradeId());		    
		} else {
		    setAssetId(loaderObject.getAssetId());
		}
		setTradeSubType(loaderObject.getTradeSubType());
		if ("Call".equalsIgnoreCase(loaderObject.getCallPut())) {
			setCallPut("C");
		} else if ("Put".equalsIgnoreCase(loaderObject.getCallPut())) {
			setCallPut("P");
		} else if (loaderObject.getCallPut() != null && loaderObject.getCallPut().length() == 1) {
		    setCallPut(loaderObject.getCallPut());
		}
		setPayCurrency(loaderObject.getPayCurrency());
		setPayNotional(loaderObject.getPayNotional());
		setReceiveCurrency(loaderObject.getReceiveCurrency());
		setReceiveNotional(loaderObject.getReceiveNotional());
		setStartDate(lonToDusCalendar(loaderObject.getStartDate()));
		setMaturityDate(lonToDusCalendar(loaderObject.getMaturityDate()));
		setPayIndexBasis(loaderObject.getPayIndexBasis());
		setPayRateSpread(loaderObject.getPayRateSpread());
		setPayDiscountCurve(loaderObject.getPayDiscountCurve());
		setReceiveIndexBasis(loaderObject.getReceiveIndexBasis());
		setReceiveRateSpread(loaderObject.getReceiveRateSpread());
		setReceiveDiscountCurve(loaderObject.getReceiveDiscountCurve());
		if ("CAP".equalsIgnoreCase(loaderObject.getCapFloor())) {
			setCapFloor("C");
		} else if ("FLOOR".equalsIgnoreCase(loaderObject.getCapFloor())) {
			setCapFloor("F");
        } else if (loaderObject.getCapFloor() != null && loaderObject.getCapFloor().length() == 1) {
            setCapFloor(loaderObject.getCapFloor());
        }
		if ("Buy".equalsIgnoreCase(loaderObject.getBuySell())) {
			setBuySell("B");
		} else {
			setBuySell("S");
		}
		setQuantity(loaderObject.getQuantity());
		setUnderlying(loaderObject.getUnderlying());
		setStrike(loaderObject.getStrike());
		setStyle(loaderObject.getStyle());
		setPremiumAmount(loaderObject.getPremiumAmount());
		setPremiumCurrency(loaderObject.getPremiumCurrency());
		setExpiryDate(lonToDusCalendar(loaderObject.getExpiryDate()));
		setFixingDate(lonToDusCalendar(loaderObject.getFixingDate()));
		setPaymentDate(lonToDusCalendar(loaderObject.getPaymentDate()));
		setFirstCouponDate(loaderObject.getFirstCouponDate());
		setModel(loaderObject.getModel());
		setInstrumentStyle(loaderObject.getInstrumentStyle());
		setFormula(loaderObject.getFormula());
		setProductName(loaderObject.getProductName());
		setProdData(loaderObject.getProdData());
		setNpv(loaderObject.getNpv());
		setMarketRateIR(loaderObject.getMarketRateIR());
		setMarketRateVola(loaderObject.getMarketRateVola());
		setFxRate(loaderObject.getFxRate());
		setDelta(loaderObject.getDelta());
		setDeltaCurrency(loaderObject.getDeltaCurrency());
		setVega(loaderObject.getVega());
		setVegaCurrency(loaderObject.getVegaCurrency());
	}

	private Calendar lonToDusCalendar(Calendar lonCal) {
		if (lonCal != null) {
		// 	changing LON to DUS time
			Calendar dusCal = (Calendar)lonCal.clone();
			dusCal.add(Calendar.HOUR_OF_DAY,1);
			return dusCal;
		}
        return lonCal;
	}

	@Override
    public Long getLongId() {
		return super.getLongId();
	}

	public TradeImpl getTrade() {
		return trade;
	}
	public void setTrade(TradeImpl trade) {
		this.trade = trade;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getBuySell() {
		return buySell;
	}
	public void setBuySell(String buySell) {
		this.buySell = buySell;
	}
	public String getCallPut() {
		return callPut;
	}
	public void setCallPut(String callPut) {
		this.callPut = callPut;
	}
	public String getCapFloor() {
		return capFloor;
	}
	public void setCapFloor(String capFloor) {
		this.capFloor = capFloor;
	}
	public double getDelta() {
		return delta;
	}
	public void setDelta(double delta) {
		this.delta = delta;
	}
	public String getDeltaCurrency() {
		return deltaCurrency;
	}
	public void setDeltaCurrency(String deltaCurrency) {
		this.deltaCurrency = deltaCurrency;
	}
	public Calendar getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Calendar expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Calendar getFirstCouponDate() {
		return firstCouponDate;
	}
	public void setFirstCouponDate(Calendar firstCouponDate) {
		this.firstCouponDate = firstCouponDate;
	}
	public Calendar getFixingDate() {
		return fixingDate;
	}
	public void setFixingDate(Calendar fixingDate) {
		this.fixingDate = fixingDate;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public double getFxRate() {
		return fxRate;
	}
	public void setFxRate(double fxRate) {
		this.fxRate = fxRate;
	}
	public String getInstrumentStyle() {
		return instrumentStyle;
	}
	public void setInstrumentStyle(String instrumentStyle) {
		this.instrumentStyle = instrumentStyle;
	}
	public double getMarketRateIR() {
		return marketRateIR;
	}
	public void setMarketRateIR(double marketRateIR) {
		this.marketRateIR = marketRateIR;
	}
	public double getMarketRateVola() {
		return marketRateVola;
	}
	public void setMarketRateVola(double marketRateVola) {
		this.marketRateVola = marketRateVola;
	}
	public Calendar getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(Calendar maturityDate) {
		this.maturityDate = maturityDate;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getPayNotional() {
		return payNotional;
	}
	public void setPayNotional(double notionalPay) {
		this.payNotional = notionalPay;
	}
	public double getReceiveNotional() {
		return receiveNotional;
	}
	public void setReceiveNotional(double notionalReceive) {
		this.receiveNotional = notionalReceive;
	}
	public double getNpv() {
		return npv;
	}
	public void setNpv(double npv) {
		this.npv = npv;
	}
	public String getPayCurrency() {
		return payCurrency;
	}
	public void setPayCurrency(String payCurrency) {
		this.payCurrency = payCurrency;
	}
	public String getPayDiscountCurve() {
		return payDiscountCurve;
	}
	public void setPayDiscountCurve(String payDiscountCurve) {
		this.payDiscountCurve = payDiscountCurve;
	}
	public String getPayIndexBasis() {
		return payIndexBasis;
	}
	public void setPayIndexBasis(String payIndexBasis) {
		this.payIndexBasis = payIndexBasis;
	}
	public Calendar getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Calendar paymentDate) {
		this.paymentDate = paymentDate;
	}
	public double getPayRateSpread() {
		return payRateSpread;
	}
	public void setPayRateSpread(double payRateSpread) {
		this.payRateSpread = payRateSpread;
	}
	public double getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	public String getPremiumCurrency() {
		return premiumCurrency;
	}
	public void setPremiumCurrency(String premiumCurrency) {
		this.premiumCurrency = premiumCurrency;
	}
	public String getProdData() {
		return prodData;
	}
	public void setProdData(String prodData) {
		this.prodData = prodData;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getReceiveCurrency() {
		return receiveCurrency;
	}
	public void setReceiveCurrency(String receiveCurrency) {
		this.receiveCurrency = receiveCurrency;
	}
	public String getReceiveDiscountCurve() {
		return receiveDiscountCurve;
	}
	public void setReceiveDiscountCurve(String receiveDiscountCurve) {
		this.receiveDiscountCurve = receiveDiscountCurve;
	}
	public String getReceiveIndexBasis() {
		return receiveIndexBasis;
	}
	public void setReceiveIndexBasis(String receiveIndexBasis) {
		this.receiveIndexBasis = receiveIndexBasis;
	}
	public double getReceiveRateSpread() {
		return receiveRateSpread;
	}
	public void setReceiveRateSpread(double receiveRateSpread) {
		this.receiveRateSpread = receiveRateSpread;
	}
	public Calendar getStartDate() {
		return startDate;
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	public double getStrike() {
		return strike;
	}
	public void setStrike(double strike) {
		this.strike = strike;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getTradeSubType() {
		return tradeSubType;
	}
	public void setTradeSubType(String tradeSubType) {
		this.tradeSubType = tradeSubType;
	}
	public String getUnderlying() {
		return underlying;
	}
	public void setUnderlying(String underlying) {
		this.underlying = underlying;
	}
	public double getVega() {
		return vega;
	}
	public void setVega(double vega) {
		this.vega = vega;
	}
	public String getVegaCurrency() {
		return vegaCurrency;
	}
	public void setVegaCurrency(String vegaCurrency) {
		this.vegaCurrency = vegaCurrency;
	}
}

