

package de.westlb.mgb.exchange;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class MgbPagExcelReader {
	
	private static final String ERR_UNEXPECTED_EMPTY_CELL	= "Unexptected empty cell";
	private static final String ERR_UNEXPECTED_TYPE			= "Unexpected type";
	private static final String ERR_STRING_EXPTECTED		= "Type must be String ";
	private static final String ERR_NUMBER_EXPECTED			= "Type must be a number or string convertible to a number";
	private static final String IO_EXCEPTION				= "Error reading file or stream";
	
	private static final String HEADER_TRADEID = "Trade ID";
	private static final String HEADER_MANUALSTATE = "Manual state";
	private static final String HEADER_MANUALCOMMENT = "Manual comment";
	private static final String HEADER_RECLAMATIONSTATE = "Reclamation state";
	
	private static final String SHEETNAME_STATECODES = "ManualStateCode";
	
	private static final String SHEETNAME_JOBS = "Jobs";
	private static final String HEADER_JOBID = "JobID";
	private static final String HEADER_MANDANTCODE = "Mandant Code";
	private static final String HEADER_JOBSTATUS = "Status";
	private static final String HEADER_ACTION = "Get Notice";
	

	private int colHEADER_TRADEID;
	private int colHEADER_MANUALSTATE;
	private int colHEADER_MANUALCOMMENT;
	private int colHEADER_RECLAMATIONSTATE;
	
	private int colHEADER_JOBID;
	private int colHEADER_MANDANTCODE;
	private int colHEADER_JOBSTATUS;
	private int colHEADER_ACTION;
	
	private MgbPagCommentLoader loader;
	private int errorCount = 0;
	private int successCount = 0;
		
	private ArrayList<String> errorList = new ArrayList<String>();
	private Logger logger = Logger.getLogger(MgbPagExcelReader.class);
	
	public MgbPagExcelReader(MgbPagCommentLoader loader) {
		this.loader = loader;	
		init();
	}
	
	private void init() {
		errorCount = 0;
		successCount = 0;
		errorList.clear();
	}
	
	public void read(String filePath) throws MgbPagPoiException {
		try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            read(fileInputStream);
			fileInputStream.close();       
		} catch (FileNotFoundException e) {
			throw new MgbPagPoiException(IO_EXCEPTION, e);		
        } catch (IOException e) {
			throw new MgbPagPoiException(IO_EXCEPTION, e);		
        }
	}
	
	
	public void read(InputStream inputStream) throws MgbPagPoiException{
		init();
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			
			
			for (int wi=0; wi < workbook.getNumberOfSheets(); wi++) {
				HSSFSheet sheet = workbook.getSheetAt(wi);
				
				if (SHEETNAME_JOBS.equals(sheet.getSheetName()) || SHEETNAME_STATECODES.equals(sheet.getSheetName())) {
					continue;
				} else {
					readCommentsSheet(sheet);
				}
			}
			
			readJobsSheet(workbook.getSheet(SHEETNAME_JOBS));
			
		} catch (IOException e) {
			throw new MgbPagPoiException(IO_EXCEPTION, e);		
		}
		
		
		
		loader.endLoad();
	}
	
	private void readJobsSheet(HSSFSheet sheet) throws MgbPagPoiException{
		loader.startLoad(sheet.getSheetName());
		
		// get relevant columns
		HSSFRow headRow = sheet.getRow(sheet.getTopRow());
		for (int col=0; col <= loader.getColumnCount(); col++) {
			HSSFCell cell = headRow.getCell(col);
			
			if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				if (cell.getRichStringCellValue().getString().equals(HEADER_JOBID)) {
					colHEADER_JOBID=cell.getColumnIndex();
				} else if (cell.getRichStringCellValue().getString().equals(HEADER_MANDANTCODE)) {
					colHEADER_MANDANTCODE=cell.getColumnIndex();
				} else if (cell.getRichStringCellValue().getString().equals(HEADER_JOBSTATUS)) {
					colHEADER_JOBSTATUS=cell.getColumnIndex();
				} else if (cell.getRichStringCellValue().getString().equals(HEADER_ACTION)) {
					colHEADER_ACTION=cell.getColumnIndex();
				}
				
			}
		}
		
		int arr[]={colHEADER_JOBID,colHEADER_MANDANTCODE,colHEADER_JOBSTATUS,colHEADER_ACTION};
		
		// Start with line 1, because we expect line 0 to be a header line!
		for (int row=1; row <= sheet.getLastRowNum(); row++) {
			HSSFRow hssfRow = sheet.getRow(row);
			if (hssfRow == null ) {
				continue ;
			}
	
			// Process relevant columns in row
			String[] record = new String[arr.length];
			int tmpErrorCount = errorList.size();
			
			for (int i = 0; i < arr.length; i++) {
			    int col = arr[i];
			    HSSFCell cell = hssfRow.getCell(arr[i]);
			
				if (col==colHEADER_ACTION  && cell == null) {
					continue;
				} else {
					record[i] = readString(cell, row, col);			
				}
			}
			
			if (errorList.size() > tmpErrorCount) {
				errorCount ++;
				// The format of the record is wrong. Skip this record
				continue;
			}
			
			String error = loader.validateRecord(record);
			if (error != null) {
				errorCount ++;
				// Validation of the record failed. Skip this record
				addError(error, record, row);
				continue;
			}
			
			//jobid, mandantcode and are mandatory
			if (record[0] !=null && record[1] !=null && record[2] !=null  && record[3] !=null && !record[0].equals("") && !record[1].equals("") && !record[2].equals("") && !record[3].equals("")) {
				error = loader.closeOpenJobs(record);
				if (error != null && !"success".equals(error)) {
					errorCount ++;
					addError(error, record, row);
					continue;
				}
				
				successCount ++;
				logger.debug("JobRow " + row + " loaded successfully");							
				
			}
	
		}
	}
	
	private void readCommentsSheet(HSSFSheet sheet) throws MgbPagPoiException{
		
		loader.startLoad(sheet.getSheetName());
		
		// get relevant columns
		HSSFRow headRow = sheet.getRow(sheet.getTopRow());
		for (int col=0; col <= loader.getColumnCount(); col++) {
			HSSFCell cell = headRow.getCell(col);
			
			if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				if (cell.getRichStringCellValue().getString().equals(HEADER_TRADEID)) {
					colHEADER_TRADEID=cell.getColumnIndex();
				} else if (cell.getRichStringCellValue().getString().equals(HEADER_MANUALSTATE)) {
					colHEADER_MANUALSTATE=cell.getColumnIndex();
				} else if (cell.getRichStringCellValue().getString().equals(HEADER_MANUALCOMMENT)) {
					colHEADER_MANUALCOMMENT=cell.getColumnIndex();
				}
			}
		}
		
		int arr[]={colHEADER_TRADEID,colHEADER_MANUALSTATE,colHEADER_MANUALCOMMENT};
		
		// Start with line 1, because we expect line 0 to be a header line!
		for (int row=1; row <= sheet.getLastRowNum(); row++) {
			HSSFRow hssfRow = sheet.getRow(row);
			if (hssfRow == null ) {
				continue ;
			}
	
			// Process relevant columns in row
			String[] record = new String[arr.length];
			int tmpErrorCount = errorList.size();
			
			for (int i = 0; i < arr.length; i++) {
			    int col = arr[i];
			    HSSFCell cell = hssfRow.getCell(arr[i]);
			
				if (cell == null) {
					if (loader.isColumnMandatory(col)) {
						addError(ERR_UNEXPECTED_EMPTY_CELL, cell, row, col);
						record[i] = null;
						continue;
					}
                    record[i] = loader.getColumnDefaultValue(col);
				} else {
					record[i] = readString(cell, row, col);			
				}
			}
			
			if (errorList.size() > tmpErrorCount) {
				errorCount ++;
				// The format of the record is wrong. Skip this record
				continue;
			}
			
			String error = loader.validateRecord(record);
			if (error != null) {
				errorCount ++;
				// Validation of the record failed. Skip this record
				addError(error, record, row);
				continue;
			}
			
			//comment and manual state code mandatory
			//if (record[2] !=null && record[1] !=null && !record[2].equals("") && !record[1].equals("")) {
			if (record[1] !=null && !record[1].equals("")) {
				error = loader.storeRecord(record);
				if (error != null) {
					errorCount ++;
					addError(error, record, row);
					continue;
				}
				
				successCount ++;
				logger.debug("Row " + row + " loaded successfully");							
				
			}
	
		}
		
	}
	

	

    


    /**
     * @param error
     * @param record
     * @param row
     */
    private void addError(String message, Object[] record, int row) {
		StringBuffer strBuf = new StringBuffer(message);
		strBuf.append(" (row="); 
		strBuf.append(row); 
		strBuf.append(")");
		String msg = strBuf.toString();
		errorList.add(msg);
		logger.error(msg);    
	}

	private void addError(String message, HSSFCell cell, int row, int col) {
		String msg = buildErrorMessage(message, cell, row, col);
		errorList.add(msg);
		logger.error(msg);
	}
	
    private String buildErrorMessage(String message, HSSFCell cell, int rowNum, int colNum) {
		StringBuffer strBuf = new StringBuffer(message);
		strBuf.append(" (col="); 
		strBuf.append(colNum);
		strBuf.append(",row="); 
		strBuf.append(rowNum); 
		strBuf.append(" ");
		if (cell == null) {
			strBuf.append("missing cell");				
		} else {
			switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_BLANK:
					strBuf.append("empty");				
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					strBuf.append("boolean value=: ");
					strBuf.append(cell.getBooleanCellValue());				
					break;
				case HSSFCell.CELL_TYPE_ERROR:
					strBuf.append("error");				
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					strBuf.append("formula");				
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					strBuf.append("numeric value=");				
					strBuf.append(cell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
					strBuf.append("string value=");				
					strBuf.append(cell.getRichStringCellValue().getString());
					break;
				default:
					strBuf.append("unknown type");				
					break;
			}
		}
		return strBuf.toString();
	}
	
	private String readString(HSSFCell cell, int row, int col) {
		if (cell== null) {
			addError(ERR_UNEXPECTED_EMPTY_CELL, cell, row, col);
			return null;
		}else if (cell.getCellType() != HSSFCell.CELL_TYPE_STRING) {
			addError(ERR_STRING_EXPTECTED, cell, row, col);
			return loader.getColumnDefaultValue(col);
		}				
		//return cell.getRichStringCellValue().getString();
		return cell.getStringCellValue();
	}

    /**
     * @return
     */
    public int getErrorCount() {
        return errorCount;
    }
    
    public int getErrorTotalCount() {
    	return errorList.size();
    }

    /**
     * @return
     */
    public int getSuccessCount() {
        return successCount;
    }
    
    public String getResultMessage() {
    	StringBuffer strBuf = new StringBuffer();
		strBuf.append("Import finished. ");   	
    	strBuf.append(getSuccessCount());
    	strBuf.append(" records succeeded. ");
    	strBuf.append("");
    	strBuf.append(getErrorTotalCount());
    	strBuf.append(" errors in ");
		strBuf.append(getErrorCount());
		strBuf.append(" records.");
    	
    	return strBuf.toString();
    }

	public String getErrorDetails() {
		if (errorList.size() < 1) {
			return null;
		}
		
		StringBuffer strBuf = new StringBuffer();
		Iterator<String> iterator = errorList.iterator();
		int i = 1;
		while (iterator.hasNext()) {
			strBuf.append( i++ );
			strBuf.append( ": " );
        	strBuf.append( iterator.next());
        	strBuf.append( "\n");
        }
        
        return strBuf.toString();
	}
	


}

