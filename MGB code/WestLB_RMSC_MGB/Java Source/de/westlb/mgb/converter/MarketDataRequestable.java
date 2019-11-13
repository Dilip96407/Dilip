/*
 * Created on 15.05.2009
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.converter;

import de.westlb.mgb.model.impl.MandantImpl;
import de.westlb.mgb.model.impl.RequestImpl;
import de.westlb.mgb.model.impl.TradeImpl;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Session;

public interface MarketDataRequestable {

    /**
     * Implements all necessary steps to initialize the requester
     * to create a Bloomberg or a Euwax request.
     * 
     * @param mandant
     * @throws ConverterException
     */
    public void initRequester(MandantImpl mandant) throws ConverterException;

    public RequestImpl buildRequest(Session sess, TradeImpl trade, String requestType) throws PersistenceException;

}