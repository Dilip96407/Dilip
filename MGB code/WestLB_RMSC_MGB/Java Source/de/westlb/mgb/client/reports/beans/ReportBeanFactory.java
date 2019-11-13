package de.westlb.mgb.client.reports.beans;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import de.westlb.mgb.client.reports.ReportException;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.EmployeeSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.EmployeeVo;
import de.westlb.mgb.client.server.vo.TradeOverviewVo;
import de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ReportBeanFactory {
	
	public ReportBeanFactory() {
	}

	/**
	 * Return a Collection of beans which may be used in all MGB trade reports. Input is
	 * an array of employee ids or an array of employee search result entries.
	 */
    public static Collection<ReportTradeBean> getTradeBeans(Object[] trades) throws ReportException {
    	ArrayList<ReportTradeBean> result = new ArrayList<ReportTradeBean>();
    	if (trades == null) {
    		return result;
    	}

	    try {
			for (int i=0; i < trades.length; i++) {
				Long id = null;
				if (trades[i] instanceof TradeSearchResultEntryVo) {
					id = ((TradeSearchResultEntryVo)trades[i]).getId();
				} else if (trades[i] instanceof Long) {
					id = (Long)trades[i];
				} 
					
				TradeOverviewVo tradeOverviewVo = MgbServiceFactory.getService().getTradeOverview(id);
				ReportTradeBean reportTradeBean = new ReportTradeBean(tradeOverviewVo);
				result.add(reportTradeBean);
			}
        } catch (RemoteException e) {
        	throw new ReportException(ReportException.SERVICE_EXCEPTION, e);
        }
    	
    	return result;
    }
    
 	/**
	 * Return a Collection of beans which may be used in all MGB trade reports.
	 */
	public static Collection<ReportEmployeeBean> getEmployeeBeans(Object[] empoyees) throws ReportException {
    	ArrayList<ReportEmployeeBean> result = new ArrayList<ReportEmployeeBean>();
    	if (empoyees == null) {
    		return result;
    	}

	    try {
			for (int i=0; i < empoyees.length; i++) {
				Long id = null;
				if (empoyees[i] instanceof EmployeeSearchResultEntryVo) {
					id = ((EmployeeSearchResultEntryVo)empoyees[i]).getEmployeeId();
				} else if (empoyees[i] instanceof Long) {
					id = (Long)empoyees[i];
				} 
				
				EmployeeVo employeeVo = MgbServiceFactory.getService().getEmployee(id);
				ReportEmployeeBean reportEmployeeBean = new ReportEmployeeBean(employeeVo);
				result.add(reportEmployeeBean);
			}
        } catch (RemoteException e) {
        	throw new ReportException(ReportException.SERVICE_EXCEPTION, e);
        }
    	
    	return result;
    }    

     
        
}
