package de.westlb.mgb.client.server;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.RemoteException;

import de.westlb.mgb.client.priceprovider.FilePriceProvider;
import de.westlb.mgb.client.server.vo.PriceVo;
import de.westlb.mgb.client.server.vo.RequestVo;

/**
 * @author WSY4148
 *
 * This class adds the desired behavior of storing bloomberg market data
 * on the local client to the MGB Service.
 * 
 */
public class MgbMarketDataYSwitchProxy implements InvocationHandler, Serializable {

    private static final long serialVersionUID = 5213356806435044219L;

    private final Mgb delegate;

	private static FilePriceProvider priceProvider = null;

	private MgbMarketDataYSwitchProxy(Mgb mgbService) {
		this.delegate = mgbService;
	}

	/**
	 * @see java.lang.reflect.InvocationHandler#invoke(Object, Method, Object[])
	 */
	@Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			Object object = null;
			if (method.getName().equals("setMarketDataProxyDirectory") && args.length == 1 && args[0] instanceof String) {
				setMarketDataProxyDirectory((String) args[0]);
			} else {
				if (priceProvider.isAvailable()) {
					if (method.getName().equals("savePrice") && args.length == 2 && args[0] instanceof RequestVo && args[1] instanceof PriceVo) {
						priceProvider.savePrice((RequestVo) args[0], (PriceVo) args[1]);
					} else if (method.getName().equals("savePrices") && args.length == 2 && args[0] instanceof RequestVo[] && args[1] instanceof PriceVo[]) {
						priceProvider.savePrices((RequestVo[]) args[0], (PriceVo[]) args[1]);
					}
				}
				object = method.invoke(delegate, args);
			}
			return object;
		} catch (final InvocationTargetException ite) {
			ite.getTargetException().printStackTrace();
			throw ite.getTargetException();
		}
	}


	public static void setMarketDataProxyDirectory(String marketDataProxyDirectory) throws RemoteException {
        if (marketDataProxyDirectory == null || marketDataProxyDirectory.length() == 0) {
            throw new RemoteException("The MARKET_DATA_PROXY_DIRECTORY needs to be read from the database and must be non null");
        }
		priceProvider = new FilePriceProvider(marketDataProxyDirectory);
	}

	public static Mgb getProxy(Mgb mgbService) {
		InvocationHandler handler = new MgbMarketDataYSwitchProxy(mgbService);
		Class<?> interfaces[] = new Class[] { Mgb.class };
		ClassLoader classloader = handler.getClass().getClassLoader();
		Object proxy = Proxy.newProxyInstance(classloader, interfaces, handler);

		priceProvider = new FilePriceProvider("");
		return (Mgb) proxy;
	}

}
