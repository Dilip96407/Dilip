package de.westlb.mgb.client.ui.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.LibraryLoader;
import com.jacob.com.Variant;

import de.westlb.mgb.client.ui.selection_list.SelectionListItem;
import de.westlb.mgb.client.ui.tablemodel.AbstractTableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb_systems.xaf.swing.SDataModel;
import de.westlb_systems.xaf.swing.SIcon;
import de.westlb_systems.xaf.swing.STable;

/**
 * Since we don't have a 64-bit version of bridge2java.dll, this class aims to
 * provide a replacement with the help of the JACOB implementation (<a
 * href="http://sourceforge.net/projects/jacob-project/"
 * >http://sourceforge.net/projects/jacob-project/</a>).
 * 
 * <p>
 * There is little documentation available about JACOB. A pointer to start with
 * is <a href=
 * "http://www.javaquery.com/2013/12/getting-started-with-jacob-example-with.html"
 * >http://www.javaquery.com/2013/12/getting-started-with-jacob-example-with.
 * html</a>. We follow the approach outlined in that article to deploy the JACOB
 * DLL from the resource folder into a temporary location, this should be fast
 * enough and also relieve us from the duty to check for upgrades on the file.
 * </p>
 * 
 * <p>For the exporter logic, I modeled it pretty much after the approach
 * in the bridge2java based exporter (getCells(), setItem() and all that).
 * I handle <code>NaN</code> and <code>Inf</code> specially because they
 * were exported as 65535 otherwise. I am not sure how the old exporter handled
 * that, or if that even can occur in MGB's tables.
 * &ndash; RSt 2015-01-19<p>
 */
/* If this is successful, maybe we can dump all this generated crap
 * from the MGB source that is currently required for the bridge2java
 * implementation. RSt 2015-01-19 */
public class JacobExcelExporter implements STable.ExportProvider {
	private static JacobExcelExporter instance = null;

	private JacobExcelExporter()
	{
        String libFile = System.getProperty("os.arch").equals("amd64") ? 
                "jacob-1.14.3-x64.dll" : "jacob-1.14.3-x86.dll";
        File temporaryDll=null;
        try {
            /* Read DLL file */
            InputStream inputStream = getClass().getResourceAsStream(libFile);
            temporaryDll = File.createTempFile("jacob",".dll");
            System.out.println("Deploying temporary local DLL "+temporaryDll.getAbsolutePath());
            FileOutputStream outputStream = new FileOutputStream(temporaryDll);
            byte[] array = new byte[8192];
            for (int i = inputStream.read(array);i != -1;i = inputStream.read(array)) 
            {
                outputStream.write(array, 0, i);
            }
            outputStream.close();
            System.setProperty(LibraryLoader.JACOB_DLL_PATH,temporaryDll.getAbsolutePath());
            LibraryLoader.loadJacobLibrary();
        }
        catch(Exception e0)
        {
            /* Unbelievably, ExcelExporter uses System.err.println. Well I suppose
             * for the Java client console output it's really sufficient, we
             * don't have any real log files there anyway. */
            System.err.println("Error loading JACOB DLL file:");
            e0.printStackTrace();
        }
        finally
        {
            temporaryDll.deleteOnExit();
        }
	}
	
    /**
     */    
    @Override
    public void exportTable(SDataModel dataModel) 
    {
        ActiveXComponent comp=null;
        try
        {
            comp = new ActiveXComponent("clsid:{00024500-0000-0000-C000-000000000046}");        
            Dispatch.put(comp,"Visible",new Variant(true));
            Variant workbooksVariant = comp.getProperty("Workbooks");
            Dispatch workbooks = workbooksVariant.toDispatch();
            workbooksVariant.safeRelease();
            Variant workbookVariant = Dispatch.get(workbooks,"Add");
            Dispatch workbook = workbookVariant.toDispatch();
            workbookVariant.safeRelease();
            Variant sheetVariant = Dispatch.get(workbook,"ActiveSheet");
            Dispatch sheet = sheetVariant.toDispatch();
            sheetVariant.safeRelease();
            Variant cellsVariant = Dispatch.get(sheet,"Cells");
            Dispatch cells = cellsVariant.toDispatch();
            cellsVariant.safeRelease();
            
            for (int col = 0; col < dataModel.getColumnCount(); col++) {
                Object colName = dataModel.getColumnName(col);
                Dispatch.invoke(cells,"Item",Dispatch.Put,new Object[]{
                        Integer.valueOf(1),Integer.valueOf(col+1),colName},new int[1]);
            }
            
            for (int row = 0; row < dataModel.getRowCount(); row++) {
                for (int col = 0; col < dataModel.getColumnCount(); col++) {
                    Object unformattedValue = null;
                    if (dataModel instanceof AbstractTableModel) {
                        unformattedValue = ((AbstractTableModel)dataModel).getUnformattedValueAt(row, col);
                    } else if (dataModel instanceof TableModel) {
                        unformattedValue = ((TableModel)dataModel).getUnformattedValueAt(row, col);
                        Object formattedValue = ((TableModel)dataModel).getValueAt(row, col);
                        if (formattedValue instanceof SIcon || formattedValue instanceof SelectionListItem) {
                            // the String respresentation of the object is better than the unformatted one 
                            unformattedValue = formattedValue.toString();
                        } else if (formattedValue instanceof String) {
                            // take reformatted Strings
                            unformattedValue = formattedValue;
                        }
                    } else {
                        unformattedValue = dataModel.getValueAt(row, col);
                    }
                    
                    if (unformattedValue != null) {
                        Object returnValue = null;
                        if (unformattedValue instanceof Calendar) {
                            returnValue = ((Calendar)unformattedValue).getTime();
                        } else  if (unformattedValue instanceof SIcon || unformattedValue instanceof SelectionListItem) {
                            returnValue = unformattedValue.toString();
                        }
                        else if(unformattedValue instanceof Double &&
                                Double.isInfinite(((Double)unformattedValue).doubleValue()))
                        {
                            returnValue = 
                                    ((Double)unformattedValue).doubleValue() < 0?
                                            "-Inf":"Inf";
                        }
                        else if(unformattedValue instanceof Double &&
                                Double.isNaN(((Double)unformattedValue).doubleValue()))
                        {
                            returnValue = "NaN";
                        }
                        else if(unformattedValue instanceof Float &&
                                Float.isInfinite(((Float)unformattedValue).floatValue()))
                        {
                            returnValue = 
                                    ((Float)unformattedValue).floatValue() < 0?
                                            "-Inf":"Inf";
                        }
                        else if(unformattedValue instanceof Float &&
                                Float.isNaN(((Float)unformattedValue).floatValue()))
                        {
                            returnValue = "NaN";

                        } else {
                            returnValue = unformattedValue;
                        }

                        if (returnValue != null) {
                            // fill the cell
                            Dispatch.invoke(cells,"Item",Dispatch.Put,new Object[]{
                                    Integer.valueOf(row+2),Integer.valueOf(col+1),returnValue},new int[1]);
                        }
                    }
                }
            }
            
            Variant columnsVariant = Dispatch.get(sheet,"Columns");
            Dispatch columns = columnsVariant.toDispatch();
            columnsVariant.safeRelease();
            
            Dispatch.call(columns,"AutoFit");
            
            columns.safeRelease();
            cells.safeRelease();
            sheet.safeRelease();
            workbook.safeRelease();
            workbooks.safeRelease();
        }
        catch(Exception e0)
        {
            System.err.println("Error exporting XLS data:");
            e0.printStackTrace();
        }
        finally
        {
            if(comp!=null)
            {
                comp.safeRelease();
            }
        }
    }

    /**
     * @see de.westlb_systems.xaf.swing.STable.ExportProvider#exportTable(STable)
     */
    @Override
    public void exportTable(STable table) {
        SDataModel model = table.getDataModel();
        exportTable(model);
    }
    
    public static JacobExcelExporter getInstance() {
    	if (instance == null) {
    		instance = new JacobExcelExporter();
    	}
    	
    	return instance;
    }
    
    public static void main(String args[]) {
    	SDataModel sDataModel = new SDataModel() {
    	    
    	    /**Some test values. Try to concentrate on cases that might
    	     * fail */
    	    private Object[][] values={
    	            {
    	                Long.valueOf(1),Long.valueOf(Long.MAX_VALUE),
    	                Double.valueOf(0.12345),Double.valueOf(Double.NaN),
    	                Double.valueOf(Double.POSITIVE_INFINITY),
    	            },
                    {
                        "Test","\u00e4",
                        Double.valueOf(Double.NEGATIVE_INFINITY),
                        Integer.valueOf(1),Integer.valueOf(Integer.MAX_VALUE),
                    },
                    {
                        new Date(),Float.valueOf(Float.POSITIVE_INFINITY),
                        Float.valueOf(Float.NEGATIVE_INFINITY),
                        Float.valueOf(Float.NaN),Boolean.TRUE,
                    }
    	    };
    		@Override
            public String getColumnName(int col) {
    			return "col" + col;
    		}
    		
    		@Override
            public int getColumnCount() {
    			return 5;
    		}
    		
    		@Override
            public int getRowCount() {
    			return 3;
    		}
    		
    		@Override
            public Object getValueAt(int row, int col) {
    			return values[row][col];
    		}
    	};
		JacobExcelExporter.getInstance().exportTable(sDataModel);    	
    }

}
