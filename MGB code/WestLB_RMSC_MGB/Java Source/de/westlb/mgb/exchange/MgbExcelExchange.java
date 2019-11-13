package de.westlb.mgb.exchange;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.util.SystemOutLogger;

import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.MgbSoapBindingProxyImpl;
import de.westlb.mgb.client.server.vo.JobSearchParamsVo;
import de.westlb.mgb.client.server.vo.JobVo;
import de.westlb.mgb.client.server.vo.MandantVo;
import de.westlb.mgb.client.server.vo.TradeOverviewVo;
import de.westlb.mgb.client.server.vo.TradeSearchParamsVo;
import de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo;
import de.westlb.mgb.model.definition.JobStateDef;
//import de.westlb.mgb.converter.MgbConverter;
//import de.westlb.mgb.model.impl.SourceSystemImpl;
//import de.westlb.mgb.persistence.PersistenceException;
//import de.westlb.mgb.persistence.Session;
//import de.westlb.mgb.persistence.StoreSingleton;
import de.westlb.mgb.struts_client.action.worksheet.TradeSheetBuilder;
import de.westlb.mgb.struts_client.action.worksheet.TradeSheetBuilderFactory;
import de.westlb.mgb.util.DateTimeUtils;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MgbExcelExchange {
	
	private static Logger logger = Logger.getLogger(MgbExcelExchange.class);
	
	
	
	private static final String SHEETNAME_STATECODES = "ManualStateCode";
	private static final String HEADER_TRADEID = "Trade ID";
	private static final String HEADER_MANUALSTATE = "Manual state";
	private static final String HEADER_MANUALCOMMENT = "Manual comment";
	private static final String HEADER_RECLAMATIONSTATE = "Reclamation state";
	
	//jobs sheet
	private static final String SHEETNAME_JOBS = "Jobs";
	private static final String HEADER_JOBID = "JobID";
	private static final String HEADER_MANDANTCODE = "Mandant Code";
	private static final String HEADER_MANDANTNAME = "Mandant Name";
	private static final String HEADER_IMPORTDATE = "ImportDate";
	private static final String HEADER_COBDATE = "CoB Date";
	private static final String HEADER_NOOPENRECORS = "NumberOfOpenRecords";
	private static final String HEADER_NOTOTALRECORS = "NumberOfTotalRecords";
	private static final String HEADER_JOBSTATUS = "Status";
	private static final String HEADER_ACTION = "Get Notice";
	private static final String GETNOTICEDVALUE = "Informed";
	
	public static final SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	
    private String sUserID;
    private String sMandantCode;
    
    public MgbExcelExchange(String sUserID, String sMandantCode) {
    	this.sUserID=sUserID;
    	this.sMandantCode = sMandantCode;     	
    }
	
	public void extractTrades(boolean manCheckOnly, boolean useCreationDate, int daysToIclude, String outputdir,String outputfilename, String reportLocation, String client) {
		Calendar from = Calendar.getInstance();
		from.add(Calendar.DAY_OF_MONTH, -daysToIclude);
		
		Workbook workBook = null;
		
		try {
			workBook = this.createWorkBookForClient(from, Calendar.getInstance(), manCheckOnly, true, useCreationDate, reportLocation, client);
		} catch (RemoteException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	
		
		try {
			String outfilename= StringUtils.join(new Object[] {outputdir, outputfilename}, File.separator);
			workBook.write(new FileOutputStream(new File(outfilename)));
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	private Workbook createWorkBookForClient(Calendar fromDate, Calendar toDate, boolean manCheckOnly, boolean showMarketData, boolean useCreationDate, String reportLocation, String client) throws RemoteException  {
		long millis = System.currentTimeMillis();
		int tradeCount = 0;
		int colSTATECODE=0;
		HashMap<String,String[]> mandantStateCodeMap;
		mandantStateCodeMap = new HashMap<String,String[]>();
		
        List<JobVo> jobList = new ArrayList<JobVo>();

        Mgb service = MgbSoapBindingProxyImpl.getProxy(true);
		service.login(this.sUserID, this.sMandantCode, "");

		MandantVo[] mandants = service.findAllMandants();
		Workbook workBook = new HSSFWorkbook();
		String[] stateCode;
		for (MandantVo mandant : mandants) {
			if (!client.equals(mandant.getClient())) {
				continue;
			}

            JobSearchParamsVo jobParam = new JobSearchParamsVo();
            jobParam.setMandant(mandant);
            jobParam.setImportDaysFrom(fromDate);
            JobVo[] jobs = service.findAllJobs(jobParam);
            for (JobVo job : jobs)
            {
            	if (!job.getStatus().equals(JobStateDef.JOB_CLOSED_STATUS) && !job.getStatus().startsWith(JobStateDef.JOB_IGNORE_STATUS))
            	{
            		int numOpen = 0;
            		if (job.getNumberOfTotalRecords() != 0) {
                    	TradeSearchParamsVo tradeParam = new TradeSearchParamsVo();
                    	tradeParam.setJobIds(new Long[] {job.getJobId()});
//                    	tradeParam.setIsManualCheckRequired(true);
                    	tradeParam.setIsManualCheckRequiredButNotHandled(true);
                        TradeSearchResultEntryVo[] trades = service.findTrades(tradeParam);
                        numOpen = trades.length;
            		}
                	job.setNumberOfOpenRecords(numOpen);
            		jobList.add(job);
            	}
            }

			TradeSearchParamsVo param = new TradeSearchParamsVo();
			setDateParameters(param,fromDate,toDate,useCreationDate);
			param.setMandantCode(mandant.getMandantCode());
			param.setLocations(reportLocation);
			//param.setIsManualCheckRequired(manCheckOnly);
			if (manCheckOnly==true) {
				param.setIsManualCheckRequiredButNotHandled(manCheckOnly);
			}

			TradeOverviewVo[] trades = service.findTradesOverviewVo(param);
			tradeCount = tradeCount + trades.length;
			String name = getSheetName(mandant.getMandantName(),param.getLocations());
			TradeSheetBuilder b1 = TradeSheetBuilderFactory.getInstance(workBook, name, mandant.getProductClass());
			Object map = service.getStateCodeMap(mandant.getMandantCode(), null);
			if (map != null && map instanceof HashMap) {
				b1.setStateMap((HashMap<String, String>)map);
			}
			b1.process(trades, showMarketData);
						
			//get manual state codes
			Object codelistmap = service.getStateCodeMap(mandant.getMandantCode(), "MANUAL");
			if (codelistmap != null && codelistmap instanceof HashMap) {
				stateCode = (((HashMap<String, String>)codelistmap).keySet()).toArray(new String[(((HashMap<String, String>)codelistmap).keySet()).size()]);
				mandantStateCodeMap.put((getSheetName(mandant.getMandantName(),reportLocation)).replaceAll("\\s", "_"), stateCode);
				
			}
		}
		
		// create jobs sheet begin
		
		HSSFSheet Jobs = (HSSFSheet) workBook.createSheet(SHEETNAME_JOBS);
		
	    HSSFCellStyle style = (HSSFCellStyle) workBook.createCellStyle();
	    style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	    //style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    style.setFillPattern((short) 2);
	    HSSFFont font = (HSSFFont) workBook.createFont();
	    font.setColor(HSSFColor.DARK_BLUE.index);
	    font.setBoldweight((short) 100);
	    style.setFont(font);
		
		int jobcounter = 1;
				 
		TreeMap<String,Integer> jobFieldMapHeader = new TreeMap<String,Integer>();
		jobFieldMapHeader.put(HEADER_JOBID, 0);
		jobFieldMapHeader.put(HEADER_MANDANTCODE, 1);
		jobFieldMapHeader.put(HEADER_MANDANTNAME, 2);
		jobFieldMapHeader.put(HEADER_IMPORTDATE, 3);
		jobFieldMapHeader.put(HEADER_COBDATE, 4);
		jobFieldMapHeader.put(HEADER_NOOPENRECORS, 5);
		jobFieldMapHeader.put(HEADER_NOTOTALRECORS, 6);
		jobFieldMapHeader.put(HEADER_JOBSTATUS, 7);
		jobFieldMapHeader.put(HEADER_ACTION, 8);

		HSSFRow row = Jobs.getRow(jobcounter-1);
		if (row==null) {
			  row = Jobs.createRow(jobcounter-1);
		}
		
		for(String colName : jobFieldMapHeader.keySet()) {
			HSSFCell cell = row.getCell(jobFieldMapHeader.get(colName));
			if (cell==null) {
			  cell = row.createCell(jobFieldMapHeader.get(colName));
			}
			cell.setCellValue(colName);
			cell.setCellStyle(style);
		}
		
		for (JobVo job : jobList) {
			
			TreeMap<Integer,String> jobFieldMap;
			jobFieldMap = new TreeMap<Integer,String>();
			jobFieldMap.put(jobFieldMapHeader.get(HEADER_JOBID),job.getJobId().toString()); // job id
			
			if (job.getMandantCode() != null){
				jobFieldMap.put(jobFieldMapHeader.get(HEADER_MANDANTCODE),job.getMandantCode()); // Mandant Code
			}else {
				jobFieldMap.put(jobFieldMapHeader.get(HEADER_MANDANTCODE),"");
			}
			
			
			if (job.getMandantName() != null){
				jobFieldMap.put(jobFieldMapHeader.get(HEADER_MANDANTNAME),job.getMandantName()); // Mandant Name
			}else {
				jobFieldMap.put(jobFieldMapHeader.get(HEADER_MANDANTNAME),"");
			}
			
			
			if (job.getStopLoadTime() != null){
				jobFieldMap.put(jobFieldMapHeader.get(HEADER_IMPORTDATE),simplDateFormat.format(job.getStopLoadTime().getTime())); //import date
			}else {
				jobFieldMap.put(jobFieldMapHeader.get(HEADER_IMPORTDATE),"");
			}	
				
			if (job.getCob() != null){
				jobFieldMap.put(jobFieldMapHeader.get(HEADER_COBDATE),simplDateFormat.format(job.getCob().getTime())); //last Cob
			}else {
				jobFieldMap.put(jobFieldMapHeader.get(HEADER_COBDATE),"0");
			}
			
			jobFieldMap.put(jobFieldMapHeader.get(HEADER_NOOPENRECORS),String.valueOf(job.getNumberOfOpenRecords())); // open Records
			jobFieldMap.put(jobFieldMapHeader.get(HEADER_NOTOTALRECORS),String.valueOf(job.getNumberOfTotalRecords())); // total Records
			jobFieldMap.put(jobFieldMapHeader.get(HEADER_JOBSTATUS),job.getStatus()); // Status
			jobFieldMap.put(jobFieldMapHeader.get(HEADER_ACTION),""); // get noticed
			
			row = Jobs.getRow(jobcounter);
			if (row==null) {
				  row = Jobs.createRow(jobcounter);
			}
			
			for(Integer clNumber : jobFieldMap.keySet()) {
					HSSFCell cell = row.getCell(clNumber);
					if (cell==null) {
					  cell = row.createCell(clNumber);
					}
					cell.setCellValue(jobFieldMap.get(clNumber));
			}
			jobcounter++;

		}
		
		for(String colName : jobFieldMapHeader.keySet()) {
			Jobs.autoSizeColumn(jobFieldMapHeader.get(colName));
		}
		
		DVConstraint constraintJobs = DVConstraint.createExplicitListConstraint(new String[]{GETNOTICEDVALUE});
		CellRangeAddressList addressListJobs = new CellRangeAddressList(1, Jobs.getLastRowNum(), jobFieldMapHeader.get(HEADER_ACTION), jobFieldMapHeader.get(HEADER_ACTION));
		HSSFDataValidation validationJobs = new HSSFDataValidation(addressListJobs, constraintJobs);
		Jobs.addValidationData(validationJobs);
		
		// jos sheet end
		
		
		HSSFSheet ManualStateCode = (HSSFSheet) workBook.createSheet(SHEETNAME_STATECODES);
		
		MandantVo[] mandants1 = service.findAllMandants();
		int mandantcounter = 0;
		for (MandantVo mandant : mandants1) {
			if (!client.equals(mandant.getClient())) {
					continue;
				}
			mandantcounter++;
			stateCode=mandantStateCodeMap.get((getSheetName(mandant.getMandantName(),reportLocation)).replaceAll("\\s", "_"));
			
			
			for (int i = 0, length= stateCode.length; i < length; i++) {
				   String name = stateCode[i];
				   row = ManualStateCode.getRow(i);
				   if (row==null) {
					   row = ManualStateCode.createRow(i);
				   }
				   HSSFCell cell = row.getCell(mandantcounter);
				   if (cell==null) {
					   cell = row.createCell(mandantcounter);
				   }
				   cell.setCellValue(name);
				}
			
			Name namedCell = workBook.createName();
			namedCell.setNameName((getSheetName(mandant.getMandantName(),reportLocation)).replaceAll("\\s", "_"));
			namedCell.setRefersToFormula(SHEETNAME_STATECODES + "!$" + CellReference.convertNumToColString(mandantcounter)  +"$1:$" +CellReference.convertNumToColString(mandantcounter) + "$" + stateCode.length);
		}
		

		for (int wi=0; wi < workBook.getNumberOfSheets(); wi++) {
			HSSFSheet sheet = (HSSFSheet) workBook.getSheetAt(wi);
			if (sheet.getSheetName().equals(SHEETNAME_STATECODES) || sheet.getSheetName().equals(SHEETNAME_JOBS)) {
				continue;
			}
			HSSFRow headRow = sheet.getRow(sheet.getTopRow());
			for (int col=0; col <= 50; col++) {
				HSSFCell cell = headRow.getCell(col);
				if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					if (cell.getRichStringCellValue().getString().equals(HEADER_MANUALSTATE)) {
						colSTATECODE=cell.getColumnIndex();
						String commentStr;
						if (mandantStateCodeMap.get(sheet.getSheetName().replaceAll("\\s", "_"))==null) {
							commentStr=" ";
						}else {
							commentStr=StringUtils.join(mandantStateCodeMap.get(sheet.getSheetName().replaceAll("\\s", "_")), ',');
						}
						HSSFPatriarch patr = sheet.createDrawingPatriarch();
						HSSFComment comment1 = patr.createComment(new HSSFClientAnchor(100, 100, 100, 100, (short)1, 1, (short) 3, 22));
						comment1.setString(new HSSFRichTextString(commentStr));
						cell.setCellComment(comment1);
						//break;
					} else if (cell.getRichStringCellValue().getString().equals(HEADER_RECLAMATIONSTATE)) {
						headRow.removeCell(cell);


					}
					
				}				
			}

		DVConstraint constraint = DVConstraint.createFormulaListConstraint((sheet.getSheetName()).replaceAll("\\s", "_"));
		CellRangeAddressList addressList = new CellRangeAddressList(1, sheet.getLastRowNum(), colSTATECODE, colSTATECODE);
		HSSFDataValidation validation = new HSSFDataValidation(addressList, constraint);
		
		sheet.addValidationData(validation);

		}

		workBook.setSheetHidden(workBook.getSheetIndex(SHEETNAME_STATECODES), true);
		workBook.setSheetOrder(SHEETNAME_JOBS, 0);
		workBook.setActiveSheet(workBook.getSheetIndex(SHEETNAME_JOBS));
		workBook.getSheet(SHEETNAME_JOBS).isSelected();
		
		logger.info("WorkBook with local report has " + workBook.getNumberOfSheets()+ " sheets with "+tradeCount+" total records produced in "+(System.currentTimeMillis()-millis)+" millis)");
		
		return workBook;
	}

	
    static int maxSheetNameLength=31;
    
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
    
    private static void setDateParameters(TradeSearchParamsVo param,
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

}
