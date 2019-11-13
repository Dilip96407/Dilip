/*
 * Created on Dec 8, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.ui.util.poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author WSY4148
 *
 */
public class PoiReader {
	private static final String ERR_UNEXPECTED_EMPTY_CELL	= "Unexptected empty cell";
	private static final String ERR_UNEXPECTED_TYPE			= "Unexpected type";
	private static final String ERR_STRING_EXPTECTED		= "Type must be String ";
	private static final String ERR_NUMBER_EXPECTED			= "Type must be a number or string convertible to a number";
	private static final String IO_EXCEPTION				= "Error reading file or stream";
	
	private PoiLoader loader;
	private int errorCount = 0;
	private int successCount = 0;
		
	private ArrayList<String> errorList = new ArrayList<String>();
	private Logger logger = Logger.getLogger(PoiReader.class);
	
	public PoiReader(PoiLoader loader) {
		this.loader = loader;	
		init();
	}
	
	private void init() {
		errorCount = 0;
		successCount = 0;
		errorList.clear();
	}

	public void read(String filePath) throws PoiException {
		try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            read(fileInputStream);
			fileInputStream.close();       
		} catch (FileNotFoundException e) {
			throw new PoiException(IO_EXCEPTION, e);		
        } catch (IOException e) {
			throw new PoiException(IO_EXCEPTION, e);		
        }
	}
	
	public void read(InputStream inputStream) throws PoiException {
		init();

		loader.startLoad();
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = workbook.getSheetAt(0);
		
			// Start with line 1, because we expect line 0 to be an header line!
			for (int row=1; row <= sheet.getLastRowNum(); row++) {
				HSSFRow hssfRow = sheet.getRow(row);
				if (hssfRow == null ) {
					continue ;
				}
				
				// Process all columns in row
				Object[] record = new Object[loader.getColumnCount()];
				int tmpErrorCount = errorList.size();
				for (int col=0; col < loader.getColumnCount(); col++) {
					HSSFCell cell = hssfRow.getCell(col);
					// Handle empty cells
					if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
						if (loader.isColumnMandatory(col)) {
							addError(ERR_UNEXPECTED_EMPTY_CELL, cell, row, col);
							record[col] = null;
							continue;
						}
                        record[col] = loader.getColumnDefaultValue(col);
					} else {
						switch (loader.getColumnType(col)) {
							case PoiLoader.COLUMN_TYPE_DATE:
		        				record[col] = readDate(cell, row, col);			
								break;
							case PoiLoader.COLUMN_TYPE_STRING:
								record[col] = readString(cell, row, col);			
								break;
							case PoiLoader.COLUMN_TYPE_DOUBLE:
								record[col] = readDouble(cell, row, col);			
								break;
							case PoiLoader.COLUMN_TYPE_FLOAT:
								record[col] = readFloat(cell, row, col);			
								break;
							case PoiLoader.COLUMN_TYPE_BOOLEAN:
								record[col] = readBoolean(cell, row, col);			
								break;
							default:
								break;
						}
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
				
				error = loader.storeRecord(record);
				if (error != null) {
					errorCount ++;
					addError(error, record, row);
					continue;
				}
				
				successCount ++;
				logger.debug("Row " + row + " loaded successfully");				
			}	
		} catch (IOException e) {
			throw new PoiException(IO_EXCEPTION, e);		
		}
		loader.endLoad();
	}
	
	/**
     * @param cell
     * @param row
     * @param col
     * @return
     */
    private Object readFloat(HSSFCell cell, int row, int col) {
 		Double d = readDouble(cell, row, col);
 		return d == null ? null : new Float(d.floatValue());
    }
    
	/**
	 * @param cell
	 * @param row
	 * @param col
	 * @return
	 */
	private Object readBoolean(HSSFCell cell, int row, int col) {
		Boolean value = null;
		switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue() ? Boolean.TRUE : Boolean.FALSE;
				break;	
			default:
				addError(ERR_UNEXPECTED_TYPE, cell, row, col);	
		}	
		
		return value;
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
	
	private void addError(String message, HSSFCell cell, int row, int col) {
		String msg = buildErrorMessage(message, cell, row, col);
		errorList.add(msg);
		logger.error(msg);
	}
	
	private Double readDouble(HSSFCell cell, int row, int col) {
		Double value = null;
		switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				try {
					String numString =  cell.getRichStringCellValue().getString().trim();
					value = new Double(numString);
				} catch (NumberFormatException e) {
					addError(ERR_NUMBER_EXPECTED, cell, row, col);										
				}
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				value = new Double(cell.getNumericCellValue());
				break;	
			default:
				addError(ERR_UNEXPECTED_TYPE, cell, row, col);	
		}	
		
		return value;
	}	

	
	private Date readDate(HSSFCell cell, int row, int col) {
		Date date;
		switch(cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC:
				date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
				break;
			default:
				addError(ERR_UNEXPECTED_TYPE, cell, row, col);
				date = null;
		}
		return date;
	}
	
	private String readString(HSSFCell cell, int row, int col) {
		if (cell.getCellType() != HSSFCell.CELL_TYPE_STRING) {
			addError(ERR_STRING_EXPTECTED, cell, row, col);
			return null;
		}				
		return cell.getRichStringCellValue().getString();
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
