package de.westlb.mgb.client.reports.beans;

import java.util.Date;

import de.westlb.mgb.client.server.vo.PriceVo;
import de.westlb.mgb.client.server.vo.RequestVo;
import de.westlb.mgb.client.server.vo.TradeOverviewVo;
import de.westlb.mgb.client.ui.selection_list.AllStateCodeList;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ReportTradeBean {

	private TradeOverviewVo tradeOverviewVo = null;
	private AllStateCodeList allStateCodeList = null;
	private String reclamationReason = null;
	
    /**
     * Constructor for ReportTradeBean.
     */
    public ReportTradeBean(TradeOverviewVo tradeOverviewVo) {
        super();
        this.tradeOverviewVo = tradeOverviewVo;
        this.allStateCodeList = new AllStateCodeList();
        try {
	        reclamationReason = allStateCodeList.calculateInitialReclamationReason(tradeOverviewVo);
        } catch (Exception e) {
        	e.printStackTrace();
        	reclamationReason = "";
        }
        
    }
      
   public Boolean getAutomatic() {
        return tradeOverviewVo.getAutomatic();
    }


    public String getAutomaticStateCode() {
        return tradeOverviewVo.getAutomaticStateCode();
    }

    public String getBuySellFlag() {
        return Boolean.TRUE.equals(tradeOverviewVo.getBuy()) ? "B" : "S";
    }

    public String getCurrency() {
        return tradeOverviewVo.getCurrency();
    }


    public java.lang.String getExchange() {
        return tradeOverviewVo.getExchange();
    }


    public Long getId() {
        return tradeOverviewVo.getId();
    }


    public String getInstrument() {
        return tradeOverviewVo.getInstrument();
    }
    
    public String getBookId() {
    	return tradeOverviewVo.getBookId();
    }


    public Boolean getInternal() {
        return tradeOverviewVo.getInternal();
    }


    public String getIsin() {
        return tradeOverviewVo.getIsin();
    }


    public String getManualStateCode() {
        return tradeOverviewVo.getManualStateCode();
    }
    
    public String getManualStateComment() {
    	return tradeOverviewVo.getManualStateComment();
    }

    public String getMdCurrency() {
        return tradeOverviewVo.getMdCurrency();
    }


    public Boolean getMischPrice() {
        return tradeOverviewVo.getMischPrice();
    }


    public Boolean getNet() {
        return tradeOverviewVo.getNet();
    }


    public PriceVo[] getPrices() {
        return tradeOverviewVo.getPrices();
    }


    public int getReminderLevel() {
        return tradeOverviewVo.getReminderLevel();
    }


    public RequestVo[] getRequests() {
        return tradeOverviewVo.getRequests();
    }


    public Date getSettlementDate() {
    	return tradeOverviewVo.getSettlementDate() == null ? null :  tradeOverviewVo.getSettlementDate().getTime();
    }


    public Boolean getStorno() {
        return tradeOverviewVo.getStorno();
    }


    public Date getSystemDate() {
    	return tradeOverviewVo.getSystemDate() == null ? null :  tradeOverviewVo.getSystemDate().getTime();
    }

	public Date getTradeDate() {
		return tradeOverviewVo.getTradeDate() == null ? null :  tradeOverviewVo.getTradeDate().getTime();
	}


    public String getTradeId() {
        return tradeOverviewVo.getTradeId();
    }


    public double getTradePrice() {
        return tradeOverviewVo.getTradePrice();
    }


    public String getTraderId() {
        return tradeOverviewVo.getTraderId();
    }


    public double getVolume() {
        return tradeOverviewVo.getVolume();
    }
    /**
     * Returns the reclamationReason.
     * @return String
     */
    public String getReclamationReason() {
    	if (reclamationReason == null) {
    		return "";
    	}
    	
        return reclamationReason;
    }

    /**
     * Sets the reclamationReason.
     * @param reclamationReason The reclamationReason to set
     */
    public void setReclamationReason(String reclamationReason) {
        this.reclamationReason = reclamationReason;
    }

}
