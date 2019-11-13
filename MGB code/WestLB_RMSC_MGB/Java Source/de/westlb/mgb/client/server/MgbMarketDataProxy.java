package de.westlb.mgb.client.server;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.RemoteException;

import de.westlb.mgb.client.priceprovider.FilePriceProvider;
import de.westlb.mgb.client.server.vo.AutoCheckVo;
import de.westlb.mgb.client.server.vo.PriceVo;
import de.westlb.mgb.client.server.vo.RequestVo;
import de.westlb.mgb.client.server.vo.SourceSystemVo;
import de.westlb.mgb.client.server.vo.TradeOverviewVo;
import de.westlb.mgb.model.definition.MandantDef;
import de.westlb.mgb.model.definition.MarketDataSourceDef;
import de.westlb.mgb.model.definition.PriceDef;
import de.westlb.mgb.model.impl.SummitBondImpl;
import de.westlb.mgb.util.PriceUtils;

/**
 * @author WSY4148
 *
 * This class adds the desired behavior of storing bloomberg market data
 * on the local client to the MGB Service.
 * 
 */
public class MgbMarketDataProxy implements InvocationHandler {

	private final Mgb delegate;

	private static FilePriceProvider priceProvider = null;

	private MgbMarketDataProxy(Mgb mgbService) {
		this.delegate = mgbService;
	}

	/**
	 * @see java.lang.reflect.InvocationHandler#invoke(Object, Method, Object[])
	 */
	@Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			Object object = null;
			if (method.getName().equals("getPrice") && args.length == 1 && args[0] instanceof RequestVo) {
				object = priceProvider.getPrice((RequestVo) args[0]);
			} else if (method.getName().equals("savePrice") && args.length == 2 && args[0] instanceof RequestVo && args[1] instanceof PriceVo) {
				priceProvider.savePrice((RequestVo) args[0], (PriceVo) args[1]);
			} else if (method.getName().equals("savePrices") && args.length == 2 && args[0] instanceof RequestVo[] && args[1] instanceof PriceVo[]) {
				priceProvider.savePrices((RequestVo[]) args[0], (PriceVo[]) args[1]);
			} else if (method.getName().equals("setMarketDataProxyDirectory") && args.length == 1 && args[0] instanceof String) {
				setMarketDataProxyDirectory((String) args[0]);
			} else {
				object = method.invoke(delegate, args);
				if (object instanceof TradeOverviewVo) {
					object = handleTradeOverviewVo((TradeOverviewVo) object);
				}
				if (object instanceof TradeOverviewVo[]) {
					object = handleTradeOverviewVoArray((TradeOverviewVo[]) object);
				}
				if (object instanceof AutoCheckVo[]) {
					object = handleAutoCheckVo((AutoCheckVo[]) object);
				}
			}
			return object;
		} catch (final InvocationTargetException ite) {
			ite.getTargetException().printStackTrace();
			throw ite.getTargetException();
		}
	}

	public Object handleTradeOverviewVoArray(TradeOverviewVo[] overview) throws RemoteException {
	    for (int i = 0; i < overview.length; i++) {
	        overview[i] = (TradeOverviewVo)handleTradeOverviewVo(overview[i]);
        }
	    return overview;
	}
	
	public Object handleTradeOverviewVo(TradeOverviewVo overview) throws RemoteException {
		boolean isChangedBloombergPrice = false;
		PriceVo[] prices = overview.getPrices();
		Double[] priceDifferences = overview.getPriceDifferences();
		for (int i = 0; i < overview.getRequests().length; i++) {
			RequestVo req = overview.getRequests()[i];
			if (MarketDataSourceDef.BLOOMBERG.equals(req.getPriceSource())) {
				// Get the data from local db
				try {
					prices[i] = priceProvider.getPrice(req);
					if (prices[i] != null) {
						priceDifferences[i] = new Double(PriceUtils.calculatePriceDifference(overview.getTradePrice(), prices[i].getMinPrice(), prices[i].getMaxPrice(), PriceDef.INTERVAL.equals(prices[i].getPriceType())));
						isChangedBloombergPrice = true;
					}
				} catch (Exception e) {
					prices[i] = null;
					e.printStackTrace();
				}
			}
		}
		if (isChangedBloombergPrice) {
			overview.setPrices(prices);
			overview.setPriceDifferences(priceDifferences);
			if (prices != null && prices.length > 0) {
				overview.setCurrentPrice(prices[prices.length - 1]);
				overview.setCurrentPriceDifference(priceDifferences[priceDifferences.length - 1].doubleValue());
				double price = PriceUtils.calculateMidPrice(overview.getCurrentPrice().getMinPrice(), overview.getCurrentPrice().getMaxPrice());
				overview.setPriceTolerance(price * overview.getParameter().getPriceTolerancePercent() / 100.0);
				String product = null;
				SourceSystemVo[] sourceSystems = delegate.findAllSourceSystems();
				for (SourceSystemVo sourceSystem : sourceSystems)
				{
					if (sourceSystem.getSourceSystemCode().equals(overview.getSourceSystemCode()))
					{
						product = sourceSystem.getProductClass();
					}
				}
				if (MandantDef.PRODUCT_BOND.equals(product)) {
				    overview.setTurnover(SummitBondImpl.calculateTurnover(price, overview.getTradePrice(), overview.getVolume(), overview.getFxRate()));
				}
			}
		}
		return overview;
	}

	public Object handleAutoCheckVo(AutoCheckVo[] autoChecks) throws RemoteException {
		for (int j = 0; j < autoChecks.length; j++) {
			PriceVo[] prices = autoChecks[j].getPrices();
			for (int i = 0; i < autoChecks[j].getRequests().length; i++) {
				RequestVo req = autoChecks[j].getRequests()[i];
				if (MarketDataSourceDef.BLOOMBERG.equals(req.getPriceSource())) {
					// Get the data from local db
					prices[i] = priceProvider.getPrice(req);
				}
			}
			autoChecks[j].setPrices(prices);
		}
		return autoChecks;
	}

	public static void setMarketDataProxyDirectory(String marketDataProxyDirectory) throws RemoteException {
	    if (marketDataProxyDirectory == null || marketDataProxyDirectory.length() == 0) {
	        throw new RemoteException("The MARKET_DATA_PROXY_DIRECTORY needs to be read from the database and must be non null");
	    }
		priceProvider = new FilePriceProvider(marketDataProxyDirectory);
	}

	public static Mgb getProxy(Mgb mgbService) {
		InvocationHandler handler = new MgbMarketDataProxy(mgbService);
		Class<?> interfaces[] = new Class[] { Mgb.class };
		ClassLoader classloader = handler.getClass().getClassLoader();
		Object proxy = Proxy.newProxyInstance(classloader, interfaces, handler);

		priceProvider = new FilePriceProvider("");
		return (Mgb) proxy;
	}

}
