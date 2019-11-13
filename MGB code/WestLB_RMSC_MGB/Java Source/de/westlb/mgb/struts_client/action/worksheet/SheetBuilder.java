package de.westlb.mgb.struts_client.action.worksheet;

import java.util.Calendar;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.CreationHelper;

public abstract class SheetBuilder {
    private CellStyle headerStyle;
    private CellStyle headerTextAreaStyle;
    private CellStyle wrapStyle;
    private CellStyle dateStyle;
    private CreationHelper createHelper;
    protected Workbook workbook;
    
    protected void init(Workbook workbook) {
    	this.workbook = workbook;
    	createHelper = workbook.getCreationHelper();

    	
        // HeaderStyle 
        headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headerStyle.setWrapText(true);
        
        // HeaderTextAreaStyle        
        headerTextAreaStyle = workbook.createCellStyle();
        headerTextAreaStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        headerTextAreaStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerTextAreaStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headerTextAreaStyle.setWrapText(true);
        Font font = workbook.createFont();
        headerTextAreaStyle.setFont(font);

        // Text style
        wrapStyle = workbook.createCellStyle();
        wrapStyle.setWrapText(true);
        wrapStyle.setAlignment(CellStyle.ALIGN_LEFT);
        
        // Date style
        dateStyle = workbook.createCellStyle();
        dateStyle.setAlignment(CellStyle.ALIGN_LEFT);
        dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));        

    }
    
    protected Sheet createSheet(Workbook workbook, String sheetName) {
    	Sheet sheet = workbook.createSheet(sheetName);
        sheet.setDefaultColumnWidth(20);
        return sheet;
    }
    
 	
    protected Cell createHeaderCell(Row row, int col, String text) {
        Cell cell = row.createCell(col);
        cell.setCellStyle(headerStyle);
        cell.setCellValue(createHelper.createRichTextString(text));
        return cell;
    }
    
    /**
     * @param sheet
     * @param row
     * @param col   
     * @param isWrapText
     */
    protected Cell createTextCell(Row row, int col, boolean isWrapText, String text) {
        Cell cell = row.createCell(col);
        CellStyle cellStyle = cell.getCellStyle();
        cellStyle.setWrapText(isWrapText);        
        cell.setCellStyle(cellStyle);
        cell.setCellValue(createHelper.createRichTextString(text));
        return cell;
    }
    
    protected Cell createCell(Row row, int col, String text) {
    	return createTextCell(row, col, false, text);
    }

    protected Cell createCell(Row row, int col, boolean value) {
        Cell cell = row.createCell(col);
        cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
        cell.setCellValue(value);
        return cell;
    }

    protected Cell createCell(Row row, int col, double value) {
        Cell cell = row.createCell(col);
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellValue(value);
        return cell;
    }

    protected Cell createCell(Row row, int col, Calendar value) {
        Cell cell = row.createCell(col);
        if (value != null) {
            cell.setCellStyle(dateStyle);
            cell.setCellValue(value);
        }
        return cell;
    }

}
