package de.westlb.mgb.model.impl;
/**
 * 
 * @modelguid {CECCFE5F-C1F8-4E65-90B9-788B30BC0CAA}
 */
public class EuwaxRequestImpl extends RequestImpl {

    private static final long serialVersionUID = 4904571869821787939L;

    @Override
    public String getMarketDataSource() {
        return EUWAX;
    };
}

