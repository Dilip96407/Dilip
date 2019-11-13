package de.westlb.mgb.model.impl;
/**
 * 
 * @modelguid {CECCFE5F-C1F8-4E65-90B9-788B30BC0CAA}
 */
public class ReutersRequestImpl extends RequestImpl {

	private static final long serialVersionUID = -2648093930087045706L;
	
    @Override
    public String getMarketDataSource() {
        return REUTERS;
    };

}

