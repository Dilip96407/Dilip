package de.westlb.mgb.client.server;

import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.transport.http.HTTPConstants;
import org.apache.log4j.Logger;

import de.westlb.mgb.client.application.MgbProperty;
import de.westlb.mgb.client.server.service.MgbClientService;
import de.westlb_systems.xaf.util.PropertyFactory;

/**
 *
 * Class that provides functionality for the instantiation of the
 * MGB-Service. It makes use of the property mgb.svc_provider.
 * This property has to be set using @see PropertyFactory.
 * 		
 */
public class MgbServiceFactory implements MgbProperty {

    private static Logger logger = Logger.getLogger(MgbServiceFactory.class);

	static String headerCookie = null;
	static URL urlEndPoint = null;
	static Mgb service = null;
	
	public static Mgb createService() {
		Mgb service = null;
		// Determine the service provider to be used
		String provider = PropertyFactory.getProperty(MGB_SVC_PROVIDER);
    	
		if (V_MGB_SVC_PROVIDER_SERVLET_CLIENT.equals(provider)) {
			if (urlEndPoint == null) {
				throw new RuntimeException("A URL must be specified via MgbServiceFactory.setEndPoint(URL).");
			}
			System.setProperty(MgbClientService.SERVICE_URL, urlEndPoint.toString());
			service = MgbClientService.getInstance();			
		} else 

		if (V_MGB_SVC_PROVIDER_SOAP_CLIENT.equals(provider)) {
			if (urlEndPoint == null) {
				throw new RuntimeException("A URL must be specified via MgbServiceFactory.setEndPoint(URL).");
			}
			try {
				MgbSoapBindingStub mgbSoapStub = (MgbSoapBindingStub)new MgbServiceLocator().getMgb(urlEndPoint);
				mgbSoapStub.setMaintainSession(true);
				int timeout = PropertyFactory.getIntProperty(MgbProperty.MGB_SOAP_TIMEOUT);
				if (timeout > 0) {
					mgbSoapStub.setTimeout(timeout);
				}
				if (headerCookie != null) {
					mgbSoapStub._setProperty(HTTPConstants.HEADER_COOKIE, headerCookie);
				}
				service = mgbSoapStub;
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		} else

		if (V_MGB_SVC_PROVIDER_SERVER_IMPL.equals(provider)) {
			boolean checkAccessControl = true;
			if ("no".equals(PropertyFactory.getProperty("mgb.check_authorization"))) {
				checkAccessControl = false;
			}
			service = MgbSoapBindingProxyImpl.getProxy(checkAccessControl);
		}    	    	
		else {
			// Invalid or null provider specified
				String msg = "MGB service provider in properties file not specified or invalid.\n";
				msg += "Property: " + MgbProperty.MGB_SVC_PROVIDER + " = <" + provider + ">";
				throw new RuntimeException(msg);
		}
    	
		// Determin if market data proxy should be used
		boolean useMarketDataServiceProxy = "true".equals(PropertyFactory.getProperty(MARKET_DATA_USE_PROXY));
		if (useMarketDataServiceProxy) {
			boolean useDatabaseMarketDataServiceProxy = "true".equals(PropertyFactory.getProperty(MARKET_DATA_USE_DATABASE_DATA_VIA_PROXY));
			if (useDatabaseMarketDataServiceProxy) {
				service = MgbMarketDataYSwitchProxy.getProxy(service);
			} else {
				service = MgbMarketDataProxy.getProxy(service);
			}
		}
    	logger.info("initialized MGB service as "+service.getClass());
		return service;		
	}
    
    public static Mgb getService() {
    	if (service == null) {
    		return service = createService();
    	}
    	
    	return service;
    }

    public static void closeService() throws RemoteException {
    	if (service != null) {
    		service.close();
    	}
    }


    /**
     * Returns the urlEndPoint.
     * @return URL
     */
    public static URL getUrlEndPoint() {
        return urlEndPoint;
    }

    /**
     * Sets the urlEndPoint.
     * @param urlEndPoint The urlEndPoint to set
     */
    public static void setUrlEndPoint(URL urlEndPoint) {
        MgbServiceFactory.urlEndPoint = urlEndPoint;
    }
	public static String getHeaderCookie() {
		return headerCookie;
	}
	public static void setHeaderCookie(String headerInfo) {
		MgbServiceFactory.headerCookie = headerInfo;
	}
}
