package de.westlb.mgb.client.reports.beans;

import java.rmi.RemoteException;
import java.util.Collection;

import de.westlb.mgb.client.reports.ReportException;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.EmployeeVo;
import de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.TraderVo;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ReportEmployeeBean  {
	private EmployeeVo employeeVo = null;
	// Contains a collection of JRTradeBeans
	private Collection<ReportTradeBean> lateTrades = null;
	private  Collection<ReportTradeBean> reclamationTrades = null;
	
	public ReportEmployeeBean(EmployeeVo employeeVo) {
		this.employeeVo = employeeVo;
		
		// Initialize trade collections, because of Java SecurityException when remote server is
		// accessed during report creation.  
		try {
            lateTrades = getLateTrades();
			reclamationTrades = getReclamationTrades();
        } catch (ReportException e) {
            e.printStackTrace();
        }
	}
	
	public Collection<ReportTradeBean> getLateTrades() throws ReportException {
		if (lateTrades == null) {
			try {
				TradeSearchResultEntryVo[] searchResult = MgbServiceFactory.getService().findTradesLateReclReqByEmployee(getEmployeeId());
				lateTrades = ReportBeanFactory.getTradeBeans(searchResult);
			} catch (ReportException repE) {
				throw repE;
			} catch (RemoteException remE) {
				throw new ReportException("Can not get late trades for employee " + getEmployeeId() + " from MGB Service", remE); 				 
			}
		}
		
		return lateTrades;
	}

	public Collection<ReportTradeBean> getReclamationTrades() throws ReportException{
		if (reclamationTrades == null) {
			try {
				TradeSearchResultEntryVo[] searchResult = MgbServiceFactory.getService().findTradesMiscReclReqByEmployee(getEmployeeId());
				reclamationTrades = ReportBeanFactory.getTradeBeans(searchResult);
			} catch (ReportException repE) {
				throw repE;
			} catch (RemoteException remE) {
				throw new ReportException("Can not get reclamation recquired trades for employee " + getEmployeeId() + " from MGB Service", remE); 				 
			}
		}
		
		return reclamationTrades;
	}
	
	public String getEmail() {
        return employeeVo.getEmail();
    }


    public Long getEmployeeId() {
        return employeeVo.getEmployeeId();
    }


    public String getFirstName() {
        return employeeVo.getFirstName();
    }


    public String getLastName() {
        return employeeVo.getLastName();
    }
	
	public String getFullName() {
		return getLastName() + ", " + getFirstName();
	}

    public String getMandantCode() {
        return employeeVo.getMandantCode();
    }


    public String getNtId() {
        return employeeVo.getNtId();
    }


    public String getPhone() {
        return employeeVo.getPhone();
    }


    public TraderVo[] getTraderIds() {
        return employeeVo.getTraderIds();
    }


	
	
}
