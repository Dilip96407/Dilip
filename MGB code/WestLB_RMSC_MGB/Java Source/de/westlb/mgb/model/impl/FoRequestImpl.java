package de.westlb.mgb.model.impl;
/**
 * 
 * @modelguid {CECCFE5F-C1F8-4E65-90B9-788B30BC0CAA}
 */
public class FoRequestImpl extends RequestImpl {
    private static final long serialVersionUID = -5060005325492416473L;

    @Override
    public String getMarketDataSource() {
            return FO;
    };
}

