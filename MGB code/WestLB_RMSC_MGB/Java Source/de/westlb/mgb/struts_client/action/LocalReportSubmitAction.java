/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */

package de.westlb.mgb.struts_client.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.vo.EmployeeSearchParamsVo;
import de.westlb.mgb.client.server.vo.EmployeeVo;
import de.westlb.mgb.client.server.vo.MandantVo;
import de.westlb.mgb.client.server.vo.TradeOverviewVo;
import de.westlb.mgb.client.server.vo.TradeSearchParamsVo;
import de.westlb.mgb.server.security.SecurityUtils;
import de.westlb.mgb.struts_client.action.worksheet.TradeSheetBuilder;
import de.westlb.mgb.struts_client.action.worksheet.TradeSheetBuilderFactory;
import de.westlb.mgb.struts_client.form.LocalReportRequestForm;
import de.westlb.mgb.struts_client.form.converter.CalendarConverter;
import de.westlb.mgb.struts_client.form.converter.TimeConverter;
import de.westlb.mgb.util.DateTimeUtils;

/**
 * 
 * This action selects the date for the report overview display. 
 * 
 * @author WSY4148 
 */
public class LocalReportSubmitAction extends MgbDownloadAction {
	protected static final Logger logger = Logger.getLogger(LocalReportSubmitAction.class);

	/* This was an idea to be able to limit the maximum number of result records
	 * per client (i. e. per query). However, local reports are not as likely
	 * to run into OOM conditions as trade searches from the applet. I've
	 * successfully retrieved several 10,000 entries as local report in the UAT.
	 * So at the moment there's no pressing need to introduce this kind of check
	 * in this place. */
	
	private Workbook workBook;
			
	public LocalReportSubmitAction() {
        super();
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // Check if cancel button has been pressed!
        if (mapping.findForward("cancel") != null && isCancelled(request)) {
            form.reset(mapping, request);
            return mapping.findForward("cancel");
        }
        return super.execute(mapping, form, request, response);
    }


    @Override
    protected StreamInfo getStreamInfo(ActionMapping mapping,ActionForm form,
            HttpServletRequest request,HttpServletResponse response) 
    throws Exception {
//        String userId = assertNtlmAuthentication(request, response);

        LocalReportRequestForm requestForm = (LocalReportRequestForm)form;

        boolean useCreationDate = LocalReportRequestForm.USE_JOB_CREATION_DATE.equals(requestForm.getUseJobCreationDate());
        Mgb service = getMgbService(request, response, null);
        Calendar fromDate,toDate;
        String dateType = "";
        if(useCreationDate)
        {
        	dateType = "CREATE_";
            fromDate = stringToCalendar(requestForm.getFromDate(),requestForm.getFromTime());
            toDate = stringToCalendar(requestForm.getToDate(),requestForm.getToTime());
        }
        else
        {
        	dateType = "COB_";
            fromDate = stringToCalender(requestForm.getFromDate());
            toDate = stringToCalender(requestForm.getToDate());
        }
        logger.debug("Local report from "+requestForm.getFromDate()+
                (fromDate!=null?" ("+fromDate.getTime()+")":"")+" to "+
                requestForm.getToDate()+
                (toDate!=null?" ("+toDate.getTime()+")":""));
        String reportType = "";
        if (LocalReportRequestForm.REPORT_MAN_CHECK.equals(requestForm.getReportType())) {
            reportType = "MAN-CHECK_";
        }
        boolean showMarketData = LocalReportRequestForm.MARKET_DATA.equals(requestForm.getMarketData());
        String marketData = "";
        if (showMarketData) {
            marketData = "MD_";
        }
        String reportLocation = requestForm.getReportLocation();
        workBook = createWorkBook(fromDate, toDate, service, requestForm.getReportType(), showMarketData, useCreationDate, reportLocation);
        if (workBook.getNumberOfSheets() == 0) {
            workBook.createSheet("No data found");
        }
        final StreamInfo streamInfo=new StreamInfo() {

            @Override
            public String getContentType() {
                return "application/vnd.ms-excel";
            }

            @Override
            public InputStream getInputStream() throws IOException {
                ByteArrayOutputStream buffer=new ByteArrayOutputStream();
                workBook.write(buffer);
                return new ByteArrayInputStream(buffer.toByteArray());
            }
            
        };

        String filename = "MCC_"+reportLocation+"_"+reportType+marketData+dateType+getDateIdentifier(fromDate, toDate)+".xls";
        response.setHeader("Content-disposition","attachment; filename=\"" + filename + "\"");

        return streamInfo;
    }

    static int maxSheetNameLength=31; /* for POI .xls output */
    
    /**
     * Creates a name suitable for POI XLS sheets which is a concatenation of
     * <code>mandantName</code> and <code>location</code>, separated by
     * underscore. Both components of the name are truncated if they exceed a
     * certain limit.
     */
    static String getSheetName(String mandantName,String location)
    {
        StringBuilder b = new StringBuilder(mandantName);
        String shortLoc = location.substring(0,Math.min(location.length(),
                maxSheetNameLength/2));  /* up to maxlen/2 chars for location */
        if(b.length()+1+shortLoc.length()>maxSheetNameLength)
        {
            b.replace(maxSheetNameLength-shortLoc.length(),
                    maxSheetNameLength,shortLoc);
            b.setCharAt(maxSheetNameLength-1-shortLoc.length(),'_');
            if(b.length()>maxSheetNameLength) b.delete(maxSheetNameLength,b.length());
        }
        else
        {
            b.append('_').append(shortLoc);
        }
        return WorkbookUtil.createSafeSheetName(b.toString());
    }
    
    private void setDateParameters(TradeSearchParamsVo param,
            Calendar fromDate,Calendar toDate,boolean useCreationDate)
    {
        if(useCreationDate)
        {
            param.setFromJobCreationDate(fromDate);
            param.setToJobCreationDate(toDate);
        }
        else
        {
            param.setFromJobCobDate(fromDate);
            param.setToJobCobDate(DateTimeUtils.endOfDayCal(toDate));
        }
    }
    
    /**
     * @param fromDate
     * @param toDate
     * @param service
     * @param reportType
     * @param overwriteReportLocation overwrites the location, if the user has a configured location
     * @return
     * @throws RemoteException
     */
    @SuppressWarnings("unchecked")
    public Workbook createWorkBook(Calendar fromDate, Calendar toDate, Mgb service, 
            String reportType, boolean showMarketData, boolean useCreationDate,
            String overwriteReportLocation) throws RemoteException {
		long millis = System.currentTimeMillis();
		int tradeCount = 0;
		
		String userId = SecurityUtils.extractUserFromDomainUser(service.getDomainUser());

		EmployeeSearchParamsVo searchParam = new EmployeeSearchParamsVo();
		searchParam.setNtId(userId);
		
		EmployeeVo[] employees = service.findAllEmployees(searchParam);
		Workbook workBook = new HSSFWorkbook();
		
		Map<String,MandantVo> productMap = new HashMap<String,MandantVo>();
		MandantVo[] mandants  = service.findAllMandants();
        for (MandantVo mandant : mandants) {
        	productMap.put(mandant.getMandantCode(), mandant);
        }

        String lastMandantCode = null;
		for (int emplyoeeCounter = 0; emplyoeeCounter < employees.length; emplyoeeCounter++) {
			EmployeeVo employee = employees[emplyoeeCounter]; 
			String mandantCode = employee.getMandantCode();
	        logger.debug("Processing mandant "+ mandantCode );
	        logger.debug("last mandant "+ lastMandantCode );
			MandantVo mandant = productMap.get(mandantCode);
		    if (mandantCode != null && !mandantCode.equals(lastMandantCode) && employee.getReport().equals(mandant.getClient())) {
		        TradeSearchParamsVo param = new TradeSearchParamsVo();
		        setDateParameters(param,fromDate,toDate,useCreationDate);
                //param.setMaxResults(MAX_RESULTS);
		        param.setMandantCode(mandantCode);
                param.setLocations(employee.getReport());
		        if (overwriteReportLocation != null && param.getLocations() != null) {
		            param.setLocations(overwriteReportLocation);
		        }
		        if (LocalReportRequestForm.REPORT_MAN_CHECK.equals(reportType)) {
		            param.setIsManualCheckRequired(Boolean.TRUE);
		        }
		        
		        if (param.getLocations() == null) {
		            logger.warn("Ignoring the trades for "+mandantCode+" since no locations are defined");
		        } else {
		            TradeOverviewVo[] trades = service.findTradesOverviewVo(param);
		            //if(trades.length>=MAX_RESULTS) this.maxResultsReturned = true;
		            tradeCount = tradeCount + trades.length;
		            if (trades.length > 0) {
		                String name = getSheetName(employee.getMandantName(),param.getLocations());
		                TradeSheetBuilder b1 = TradeSheetBuilderFactory.getInstance(workBook, name, mandant.getProductClass());
		                Object map = service.getStateCodeMap(mandantCode, null);
		                if (map != null && map instanceof HashMap) {
		                    b1.setStateMap((HashMap<String, String>)map);
		                }
		                b1.process(trades, showMarketData);
		            } else {
		                logger.debug("No trades for "+mandantCode+" "+param.getLocations());
		            }
		        } 
		    } else {
		    	if (!employee.getReport().equals(mandant.getClient())) {
			        logger.debug("Employee "+employee.getNtId() + " not allowed to get Report for " + mandantCode );
		    	} else {
			        logger.debug("Trades for "+mandantCode+" already selected");		    		
		    	}
		    }
		    lastMandantCode = mandantCode;
		}
		logger.debug("WorkBook with local report has " + workBook.getNumberOfSheets()+ " sheets with "+tradeCount+" total records produced in "+(System.currentTimeMillis()-millis)+" millis)");
		return workBook;
	}
    
    @SuppressWarnings("unchecked")
    public Workbook createWorkBookForAllMandants(Calendar fromDate, Calendar toDate, 
            Mgb service, String reportType, boolean showMarketData, boolean useCreationDate,
            String reportLocation) throws RemoteException {
        long millis = System.currentTimeMillis();
        int tradeCount = 0;
        
        MandantVo[] mandants = service.findAllMandants();
        Workbook workBook = new HSSFWorkbook();
        for (int mandantCounter = 0; mandantCounter < mandants.length; mandantCounter++) {
                TradeSearchParamsVo param = new TradeSearchParamsVo();
                setDateParameters(param,fromDate,toDate,useCreationDate);
                //param.setMaxResults(MAX_RESULTS);
                param.setMandantCode(mandants[mandantCounter].getMandantCode());
                param.setLocations(reportLocation);
                if (LocalReportRequestForm.REPORT_MAN_CHECK.equals(reportType)) {
                    param.setIsManualCheckRequired(Boolean.TRUE);
                }
                
                TradeOverviewVo[] trades = service.findTradesOverviewVo(param);
                //if(trades.length>=MAX_RESULTS) this.maxResultsReturned = true;
                tradeCount = tradeCount + trades.length;
                if (trades.length > 0) {
                    String name = getSheetName(mandants[mandantCounter].getMandantName(),param.getLocations());
                    String mandant = param.getMandantCode();
                    TradeSheetBuilder b1 = TradeSheetBuilderFactory.getInstance(workBook, name, mandants[mandantCounter].getProductClass());
                    Object map = service.getStateCodeMap(mandant, null);
                    if (map != null && map instanceof HashMap) {
                        b1.setStateMap((HashMap<String, String>)map);
                    }
                    b1.process(trades, showMarketData);
                } else {
                    logger.debug("No trades for "+param.getMandantCode()+" "+param.getLocations());
                }
        }
        logger.debug("WorkBook with local report has " + workBook.getNumberOfSheets()+ " sheets with "+tradeCount+" total records produced in "+(System.currentTimeMillis()-millis)+" millis)");
        return workBook;
    }

    private String getDateIdentifier(Calendar fromDate, Calendar toDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); 
        return format.format(fromDate.getTime())+"_"+format.format(toDate.getTime());
    }

    private Calendar stringToCalender(String calenderString) {
        if (!StringUtils.isEmpty(calenderString)) {
            return (Calendar)new CalendarConverter()
                .stringToObject(calenderString
            );
        }
        return null;
    }

    private Calendar stringToCalendar(String calenderString,String timeStr) {
        Calendar c=null;
        Calendar cTime=null;
        if (!StringUtils.isEmpty(calenderString)) {
            c = (Calendar)new CalendarConverter()
                .stringToObject(calenderString
            );
        }
        if(!StringUtils.isEmpty(timeStr))
        {
            cTime = (Calendar)new TimeConverter().stringToObject(timeStr);
            if(cTime!=null)
            {
                c.set(Calendar.HOUR_OF_DAY,cTime.get(Calendar.HOUR_OF_DAY));
                c.set(Calendar.MINUTE,cTime.get(Calendar.MINUTE));
                c.set(Calendar.SECOND,cTime.get(Calendar.SECOND));
            }
        }
        return c;
    }

}