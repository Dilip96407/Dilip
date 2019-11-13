/*
 * Created on Dec 20, 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.bloomberg.model;

import org.apache.log4j.Logger;

import de.westlb.mgb.model.definition.MarketDataSourceDef;

/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class PricingRequesterFactory {

	private static Logger logger = Logger.getLogger(PricingRequesterFactory.class);

	private static EuwaxWebRequester euwaxWebRequester = null; 

	private static DummyRequester dummyRequester = null; 

	private static BlpApiBloombergRequester bloombergRequester = null; 


	
	public synchronized static PricingRequester getRequester(String marketDataSource) throws Exception {
		if (isDummyRequester()) {
			logger.warn("### You are generating a dummy price. ###");
			return dummyRequester;
		}
		if (MarketDataSourceDef.BLOOMBERG.equals(marketDataSource)) {
			if (bloombergRequester == null) {
				logger.debug("Loading BloombergRequester");
				bloombergRequester = new BlpApiBloombergRequester();
				bloombergRequester.connect();
			}
			return bloombergRequester;
		} else if (MarketDataSourceDef.EUWAX.equals(marketDataSource)) {
			if (euwaxWebRequester == null) {
				euwaxWebRequester = new EuwaxWebRequester();
//				euwaxWebRequester.setWlbPassword(Base64.encodeBase64("XXXX".getBytes()));
//				euwaxWebRequester.init();
			}
			return euwaxWebRequester;
		} else if (MarketDataSourceDef.REUTERS.equals(marketDataSource)) {
			if (euwaxWebRequester == null) {
				euwaxWebRequester = new EuwaxWebRequester(); 
			}
			return euwaxWebRequester;
		} else {
			throw new IllegalArgumentException("The market data source must be ["+MarketDataSourceDef.BLOOMBERG+", "+MarketDataSourceDef.EUWAX+", "+MarketDataSourceDef.REUTERS+"]");
		}
	}

	
	public static void destroy() {
	    synchronized (PricingRequesterFactory.class) {
	        if (bloombergRequester != null) {
	            bloombergRequester.disconnect();
	            bloombergRequester = null;
	        }
	    }
	}

	public static void setDummyRequester(double deciamlPercent, int minutes) {
        synchronized (PricingRequesterFactory.class) {
            if (dummyRequester == null) {
                logger.warn("##################################################");
                logger.warn("### WARNING                                    ###");
                logger.warn("### You are running the dummy price requester. ###");
                logger.warn("### WARNING                                    ###");
                logger.warn("##################################################");
                dummyRequester = new DummyRequester(deciamlPercent, minutes);
            }
		}
	}

	public static boolean isDummyRequester() {
		return dummyRequester != null;
	}
}
