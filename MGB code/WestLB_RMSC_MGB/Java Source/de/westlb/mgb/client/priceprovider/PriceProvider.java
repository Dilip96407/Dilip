package de.westlb.mgb.client.priceprovider;

import java.rmi.RemoteException;

import de.westlb.mgb.client.server.vo.PriceVo;
import de.westlb.mgb.client.server.vo.RequestVo;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface PriceProvider {

	public PriceVo getPrice(RequestVo request) throws RemoteException;

	public void savePrice(RequestVo request, PriceVo price) throws RemoteException;

	public void savePrices(RequestVo[] requests, PriceVo[] prices) throws RemoteException;
	
	public boolean isAvailable();

}
