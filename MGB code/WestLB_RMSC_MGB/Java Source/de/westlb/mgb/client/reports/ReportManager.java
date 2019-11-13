package de.westlb.mgb.client.reports;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import de.westlb.mgb.client.reports.beans.JRDataSourceFactory;
import de.westlb.mgb.client.server.MgbSessionSingleton;
import de.westlb.mgb.client.server.vo.EmployeeSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.SessionInfoVo;
import de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo;
import de.westlb_systems.xaf.util.PropertyFactory;
import dori.jasper.engine.JRDataSource;
import dori.jasper.engine.JRException;
import dori.jasper.engine.JasperExportManager;
import dori.jasper.engine.JasperFillManager;
import dori.jasper.engine.JasperPrint;
import dori.jasper.engine.JasperReport;
import dori.jasper.engine.util.JRLoader;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ReportManager {	
	public static final int REPORT_TRADER_RECLAMATION		= 1;
	public static final int REPORT_TRADES_RECLAMATION_LATE   = 2;
	public static final int REPORT_TRADES_RECLAMATION_MISC   = 3;
	
	private static final String TEMPLATE_TRADER_RECLAMATION		= "Employee_Reclamation.jasper";
	private static final String TEMPLATE_TRADES_RECLAMATION_LATE	= "Trades_Reclamation_LateEntered.jasper";
	private static final String TEMPLATE_TRADES_RECLAMATION_MISC	= "Trades_Reclamation_MiscReasons.jasper";
	
	private static final String PARAM_FIRSTNAME		= "firstName";
	private static final String PARAM_LASTNAME		= "lastName";
	private static final String PARAM_PHONE			= "phone";
	private static final String PARAM_YESTERDAY		= "yesterday";
	private static final String PARAM_TOMORROW		= "tomorrow";
	private static final String PARAM_FACTORY		= "factory";
	
	private static ReportManager instance;

	private String templateDir = "";
	
	private ReportManager() {
		setTemplateDir(PropertyFactory.getProperty("mgb.report.templateDir"));
	}
	
	private void setTemplateDir(String dirPath) {
		if (dirPath == null) {
			return;
		}
		
		templateDir = StringUtils.replace(dirPath, "<user.home>", System.getProperty("user.home"));
	}
	
		
	public Report createReport(int reportId, Object reportParameter) throws ReportException {
		JasperPrint jasperPrint = null;
		
		// Get standard parameters available in all mgb reports
		Map<String, Object> parameters				= getStdReportParameters();
		JRDataSource jrDataSource	= null;
		String reportName			= null;
		
		switch (reportId) {
        	case REPORT_TRADER_RECLAMATION:
        		if (!(reportParameter instanceof EmployeeSearchResultEntryVo[] ||
        			  reportParameter instanceof Long[])) {
        			throw new IllegalArgumentException("Parameter of Report id <" + reportId + "> must be type  EmployeeSearchResultEntryVo[] or Long[]");
        		}
        		
				// Set report parameters                
	       		reportName = TEMPLATE_TRADER_RECLAMATION;
        		jrDataSource = JRDataSourceFactory.getEmployees((Object[])reportParameter);

				// Set parameter for the reclamations caused by late trades subreport				
				JasperReport subreport;
                try {
                    subreport = (JasperReport) JRLoader.loadObject(templateDir + "/" + TEMPLATE_TRADES_RECLAMATION_LATE);
                } catch (JRException e) {
		        	throw new ReportException(ReportException.REPORT_EXCEPTION, e);                	
                }				
                parameters.put("SubreportLateEntered", subreport);

				// Set parameter for the reclamations caused by misc reasons subreport
                try {
                    subreport = (JasperReport) JRLoader.loadObject(templateDir + "/" + TEMPLATE_TRADES_RECLAMATION_MISC);
                } catch (JRException e) {
		        	throw new ReportException(ReportException.REPORT_EXCEPTION, e);                	
                }								
                
                parameters.put("SubreportMiscReasons", subreport);				
				 
        		break;
        	case REPORT_TRADES_RECLAMATION_LATE:
        		if (!(reportParameter instanceof TradeSearchResultEntryVo[] ||
        			reportParameter instanceof Long[])) {
        			throw new IllegalArgumentException("Parameter of Report id <" + reportId + " must be type  TradeSearchResultEntryVo[]");
        		}
        		reportName = TEMPLATE_TRADES_RECLAMATION_LATE;
        		jrDataSource = JRDataSourceFactory.getTrades((Object[])reportParameter);
        		break;
        	case REPORT_TRADES_RECLAMATION_MISC:
        		if (!(reportParameter instanceof TradeSearchResultEntryVo[] ||
        			reportParameter instanceof Long[])) {
        			throw new IllegalArgumentException("Parameter of Report id <" + reportId + " must be type  TradeSearchResultEntryVo[]");
        		}
        		reportName = TEMPLATE_TRADES_RECLAMATION_MISC;
        		jrDataSource = JRDataSourceFactory.getTrades((Object[])reportParameter);
        		break;
        	default:
       			throw new IllegalArgumentException("Invalid report id <" + reportId);        	
        }

        try {
        	String templatePath = templateDir + "/" + reportName;
        	jasperPrint = JasperFillManager.fillReport(templatePath, parameters, jrDataSource);
        } catch (JRException  jrE) {
        	throw new ReportException(ReportException.REPORT_EXCEPTION, jrE);
        }
        	
    	return new Report(jasperPrint);
	}
	
	public byte[] exportReport(Report report) throws ReportException {
		byte[] export = null;
		
		try {
            export = JasperExportManager.exportReportToPdf(report.getJasperPrint());
        } catch (JRException e) {
        	throw new ReportException(ReportException.REPORT_EXCEPTION, e);
        }	
        
        return export;
	}
		
	public Map<String, Object> getStdReportParameters() throws ReportException {
		GregorianCalendar yesterday = new GregorianCalendar();
		yesterday.add(Calendar.DAY_OF_YEAR, -1);

		GregorianCalendar tomorrow = new GregorianCalendar();
		tomorrow.add(Calendar.DAY_OF_YEAR, 1);

		Map<String, Object> parameters = new HashMap<String, Object>();
        SessionInfoVo sessionInfo;
        try {
            sessionInfo = MgbSessionSingleton.getInstance(false);
        } catch (RemoteException e) {
        	throw new ReportException(ReportException.SERVICE_EXCEPTION, e);
        }
        
		parameters.put(PARAM_LASTNAME,	sessionInfo.getEmployee().getLastName());
		parameters.put(PARAM_FIRSTNAME,	sessionInfo.getEmployee().getFirstName());
		parameters.put(PARAM_PHONE,		sessionInfo.getEmployee().getPhone());
		parameters.put(PARAM_YESTERDAY,	yesterday.getTime());
		parameters.put(PARAM_TOMORROW,	tomorrow.getTime());
		parameters.put(PARAM_FACTORY, new JRDataSourceFactory());
		
		return parameters;
	}
    /**
     * Returns the instance.
     * @return ReportManager
     */
    public static ReportManager getInstance() {
        if (instance == null) {
        	instance = new ReportManager();
        }
        
        return instance;
    }

}
