package de.westlb.mgb.exchange;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.MgbSoapBindingProxyImpl;
import de.westlb.mgb.client.server.vo.TradeHistoryEntryVo;
import de.westlb.mgb.client.server.vo.TradeOverviewVo;
import de.westlb.mgb.client.server.vo.TradeSearchParamsVo;
import de.westlb.mgb.model.definition.JobStateDef;
import de.westlb.mgb.model.definition.StateCodeTypeDef;


public class MgbPagCommentLoader {

    private static final int COL_TRADEID                  = 0;
    private static final int COL_INSTRUMENT                  = 1;
    private static final int COL_MANUAL_COMMENT              = 20;
    
    private static final String GETNOTICEDVALUE = "Informed";
    
    
	private ArrayList<String> errorList = new ArrayList<String>();
	private Logger logger = Logger.getLogger(MgbPagExcelReader.class);
	
    private Mgb mgb = null;

    private String sUserID;
    private String client;
    private String reportLocation;
    
    public MgbPagCommentLoader(String sUserID, String reportLocation, String client) {
    	this.sUserID=sUserID;
    	this.reportLocation=reportLocation;
    	this.client=client;
    }
    
    public void startLoad(String sheetname) {
    	String mandantLogin = ""; 

		if (sheetname.equalsIgnoreCase("Bond PAG_PORTIGON")) {
			mandantLogin = "BNG";
		} else if (sheetname.equalsIgnoreCase("Derivative PAG_PORTIGON")) {
			mandantLogin = "DVG";
		} else if (sheetname.equalsIgnoreCase("MoneyMarket PAG_PORTIGON")) {
			mandantLogin = "MMG";
		} else if (sheetname.equalsIgnoreCase("Repo PAG_PORTIGON")) {
			mandantLogin = "REG";
		} else {
			mandantLogin = "BNG";
		}
		
		changelogin(this.sUserID, mandantLogin,"");

    }
    
    private void changelogin(String userIs, String mandantcode, String usercomment) {
    	//mgb = MgbServiceFactory.getService();
		mgb = MgbSoapBindingProxyImpl.getProxy(true);
	
		try {
			mgb.login(userIs, mandantcode, usercomment);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
    }
    
    public int getColumnCount() {
        return 50;
    }
    
    public boolean isColumnMandatory(int col) {
    	return false;
        //return col != COL_TRADEID && col != COL_MANUAL_COMMENT;
    }
    
    public String getColumnDefaultValue(int col) {
        return "";
    }
    
    
   
    public String validateRecord(String[] record) {
        String errorMsg = null;
       
        return errorMsg;
    }
    
    private String tradesStillUnhandled(Long[] jobId, String mandantCode) {
    	
		TradeSearchParamsVo param = new TradeSearchParamsVo();
		param.setJobIds(jobId);
		param.setMandantCode(mandantCode);
		param.setIsManualCheckRequiredButNotHandled(true);
		TradeOverviewVo[] trades = null;
		try {
			trades = mgb.findTradesOverviewVo(param);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (trades.length>0) {
			return trades.length + " trades need action";
		}else {
			return "OK";
		}
    	
    	
    }
    
    public String closeOpenJobs(String[] record) {
    	
    	for (int i=0; i < record.length; i++) {
        	logger.info("Start processing record from joblist : " + record[i]);
        	//System.out.println(record[i]);
        }
    	
    	String sJobId = record[0];
        String sMandant = record[1];
        String sStatus = record[2];
        String action = record[3];
    	
        logger.info("trying to close job "+sJobId+" for mandant "+sMandant);
    	
        if (sJobId != null && sJobId.length() > 0) {
            try {
                Long jobId = new Long(sJobId);
                Long [] jobIdlist = {jobId};
                if (sMandant == null) {
                    return "no Mandant suplied";                	
                }

                changelogin(sUserID,sMandant,"");

                if (GETNOTICEDVALUE.equals(action)) {
                	if ("OK".equals(sStatus)) {
                		String unhandledTrades = tradesStillUnhandled(jobIdlist, sMandant);
                		if (unhandledTrades.equals("OK")) {
                			logger.info("closing jobid: " + jobId);
                			mgb.setJobStatus(jobId, JobStateDef.JOB_CLOSED_STATUS); 
                		}else {
                			logger.info(unhandledTrades + " in jobid: " + sJobId);
                		}
                	}else {
                		mgb.setJobStatus(jobId, JobStateDef.JOB_IGNORE_STATUS);
                	}
                }
                
                return "success";
            } catch (NumberFormatException nfe){
                nfe.printStackTrace();
                return "failure";
            } catch (RemoteException e) {
            	
				e.printStackTrace();
				return "failure";
			}
        } else {
            return "failure";
        }
    }
    
	public String storeRecord(String[] record) {
		
	
		int tradeCount = 0;
		
        for (int i=0; i < record.length; i++) {
        	logger.info("Start processing parameter : " + record[i]);
        	//System.out.println(record[i]);
        }
        
		String[] tradeIds = new String[] {record[0]};
		//String[] tradeIds = new String[] {"0001343867_2","4820491D_12","4642088NY_840","4817445D_6"};
		
		String comment = record[2];
		String stateCode = record[1];

		// find trades for many tradeid's
		TradeSearchParamsVo param = new TradeSearchParamsVo();
		param.setTradeIds(tradeIds);

		TradeOverviewVo[] tradeOverview = null;
		try {
			tradeOverview = mgb.findTradesOverviewVo(param);
		} catch (RemoteException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		tradeCount = tradeCount + tradeOverview.length;
		
        for (int i=0; i < tradeOverview.length; i++) {

        	try {
        		TradeHistoryEntryVo tov_comment = null;
    			
        		tov_comment = mgb.getTradeState(tradeOverview[i].getId());
    			
    			if (tov_comment==null){
    			
	    	        Long[] internalTradeIds = {tradeOverview[i].getId()};
	    	    	
	    			TradeHistoryEntryVo tradeHistEntry = new TradeHistoryEntryVo();
	    			tradeHistEntry.setStateCode(stateCode);
	    			tradeHistEntry.setComment(comment);
	    			tradeHistEntry.setType(StateCodeTypeDef.MANUAL);
	    			//tradeHistEntry.setDate(date);
	    			tradeHistEntry.setCreatedByName(this.reportLocation);
	    			
	    	    	/*
	    	    	tradeOverview.setManualCheckRequired(manualCheckRequired);
	    	    	tradeOverview.setManualStateByEmployeeId(manualStateByEmployeeId);
	    	    	tradeOverview.setManualStateByName(manualStateByName);
	    	    	tradeOverview.setManualStateCode(manualStateCode);
	    	    	tradeOverview.setManualStateComment(manualStateComment);
	    	    	tradeOverview.setReclamationStateComment(reclamationStateComment);
	    	    	*/
	    			
	    			
	    			if (tradeOverview[i].getReportLocation().equalsIgnoreCase(this.reportLocation)){
	 
		    			try {
		    				mgb.saveTradeState(internalTradeIds, tradeHistEntry, null);
		    				logger.info("saved trade state " + tradeOverview[i].getId());
		    			} catch (RemoteException e) {
		    				logger.error(e.getMessage());
		    				e.printStackTrace();
		    			}
	    			
	    			}
	    			else {
	    				// TODO VS throw exception here
	    				//System.err.println("not saved non-PORTIGON TRADE " + tradeOverview[i].getTradeId());
	    				logger.error("not allowed non-" + this.reportLocation + " TRADE " + tradeOverview[i].getTradeId());
	    			}
    			
    			}else {
    				// TODO VS throw exception here
    				//System.err.println("manual comment already exist for TRADE " + tradeOverview[i].getTradeId());
    				logger.error("manual comment already exist for TRADE: " + tradeOverview[i].getTradeId());
    			}

    		} catch (RemoteException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			//continue;
    		}        	
        }

        
        return null;
    }
    
    public void endLoad() {
        // TODO Auto-generated method stub

    }
    
}
