package de.westlb.mgb.client.ui.tablemodel;

import java.lang.reflect.InvocationTargetException;
import java.text.Format;

import javax.swing.UIManager;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import de.westlb.mgb.client.ui.util.CurrencyFormat;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb_systems.xaf.swing.SDataModel2;
import de.westlb_systems.xaf.swing.SDataModelListener;
import de.westlb_systems.xaf.util.SResourceBundle;

/**
 * @author WSY4148
 *
 * Abstract superclass for all classes which provide
 * a gui table model for a collection of data value objects.
 * The subclasses have to provide an aray of propertyNames.
 */
public abstract class AbstractTableModel  implements SDataModel2 {
	Logger logger = Logger.getLogger(this.getClass());
	private SResourceBundle resourceBundle = null;
	private String resourceBase = null;
	
	// The keys of the column header
	private String headerKeys[] = null;
	
	// The underlying object collection as array
	private Object dataArray[] = null;
	private String propertyNames[] = null;
	
	// THe formatted values returned by getValue(..)
    private Object[][] values = null;
    
    /** 
     * A mapping of the columns in the view to the columns in
     * the model. If null the table contains all columns in default order.
	 */
    private int[] columnMap = null;
    
    /**
     * A definition of formats for the columns
     */
    private Format[] columnFormats = null;

	protected CurrencyFormat format3GMin4Max10;
	protected CurrencyFormat format3GMin0Max0;
	protected DateFormat timeFormat; 

    /**
     * Constructor for AbstractTableModel.
     */
    public AbstractTableModel() {
        super();
        columnMap = getColumnMapFromLookAndFeel();
        columnFormats = getColumnFormatsFromLookAndFeel();
        
		timeFormat = new DateFormat(DateFormat.TIME_FORMAT_LONG); 
		
		format3GMin4Max10 = new CurrencyFormat();
		format3GMin4Max10.setMinimumFractionDigits(4);
		format3GMin4Max10.setMaximumFractionDigits(10);
		format3GMin4Max10.setGroupingUsed(true);
		format3GMin4Max10.setGroupingSize(3);
		
		format3GMin0Max0 = new CurrencyFormat();
		format3GMin0Max0.setMinimumFractionDigits(0);
		format3GMin0Max0.setMaximumFractionDigits(0);
		format3GMin0Max0.setGroupingUsed(true);
		format3GMin0Max0.setGroupingSize(3);    
	}    

    /**
     */
    public AbstractTableModel(Object[] newData) {
        this();
		setData(newData);

    }
    
    public void setData(Object[] newData) {
        if (newData == null) {
        	return;
        }
        
        dataArray = newData;
        values = new Object[newData.length][propertyNames.length];
		for (int col=0; col < propertyNames.length; col++) {
			boolean isColumnMapped = isColumnMapped(col);
			for (int row = 0; row < dataArray.length; row++) {
				if (!isColumnMapped) {
					values[row][col] = null;
					continue;

				}
				Object object = newData[row];
				try {
					values[row][col] = calculateTableValue(object, col);
				} catch (Exception e) {
					values[row][col] = null;
					e.printStackTrace();
				}
			}
		}
		
		
    }

    /**
     * @see de.westlb_systems.xaf.swing.SDataModel2#addSDataModelListener(SDataModelListener)
     */
    @Override
    public void addSDataModelListener(SDataModelListener listener) {
    }

    /**
     * @see de.westlb_systems.xaf.swing.SDataModel2#getColumnType(int)
     */
    @Override
    public int getColumnType(int column) {
        return 0;
    }

    /**
     * @see de.westlb_systems.xaf.swing.SDataModel2#removeSDataModelListener(SDataModelListener)
     */
    @Override
    public void removeSDataModelListener(SDataModelListener listener) {
    }

    /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getColumnCount()
     */
    @Override
    public int getColumnCount() {
    	if (columnMap != null) {
    		return columnMap.length;
    	}
		if (headerKeys != null) {
			return headerKeys.length;
		}  
		
		return 0;  
	}
	
	/**
     * @see de.westlb_systems.xaf.swing.SDataModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int column) {
		if( headerKeys != null && column >= 0 && column < getColumnCount()) {
			return getResourceString(headerKeys[getColumnIndexInModel(column)]);
		}  
	
		return "COL" + column;  
	}
	
	/**
	 * Return the column index in the view by a column index in the model or
	 * -1 if the column does not exist in the view.
	 */
	public int getColumnIndexInView(int column) {
		if (columnMap == null) {
			return -1;
		}
		for (int i = 0; i < columnMap.length; i++) {
			if (columnMap[i] == column) {
				return i;
			}
        }
        return -1;
	}
	
	/**
	 * Return the column index in the model by the column index in the
	 * view.
	 * 
	 * @param column
	 * @return
	 */
	protected int getColumnIndexInModel(int column) {
		if (columnMap == null) {
			return column;
		}
		
		return columnMap[column];
	}
	
	/**
	 * Returns the resource string for an key
	 *
	 * The key is calculated by resourceBase+Key
	 * 
	 */
	public String getResourceString(String key) {
		if(getResourceBundle() != null) {
			if(getResourceBase() != null) {
				key = getResourceBase() + key;
			}
			return getResourceBundle().getResourceString(key);
		}
		return key;
	}

    /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getRowCount()
     */
    @Override
    public int getRowCount() {
        return (values == null) ? 0 : values.length;
    }
    
    public Object getRowObject(int row) {
    	Object rowObj = null;
    	
    	if (dataArray != null && row < dataArray.length) {
    		rowObj = dataArray[row];
    	}
    	
    	return rowObj;
    }

    /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int col) {
    	Object value = null;
    	
    	int mappedCol = getColumnIndexInModel(col);
		if (row < getRowCount() && col < getColumnCount()) {
			value = values[row][mappedCol];
		}
		
		if (columnFormats != null) {
			Format format = columnFormats[col];
			if (format != null && value != null) {
				value = format.format(value);
			}
		}
		
        return value;
    }
    
    /**
     * Returnes true, if the column is contained in the column
     * map. That means that the column is visible.
     */
    private boolean isColumnMapped(int column) {
    	if (columnMap == null) {
    		return true;
    	}
    	
    	for (int i = 0; i < columnMap.length; i++) {
        	if (columnMap[i] == column) {
        		return true;
        	}
        }
        
        return false;
    }
    
	public Object getUnformattedValueAt(int row, int col) {
		if (row < getRowCount() && col < getColumnCount()) {
			return values[row][getColumnIndexInModel(col)];
		}
        return null;	
	}
	
	protected Object calculateTableValue(Object businessModelObj, int col) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException 
	{
		return PropertyUtils.getProperty(businessModelObj, propertyNames[col]);
	}
	
    /**
     * @see de.westlb_systems.xaf.swing.SSortableDataModel#compareRows(int, int, int)
     */
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes"})
    public int compareRows(int r1, int r2, int col) {
    	int columnType = getColumnType(col);
    	
        Object val1 = columnType == NUMBER ? getUnformattedValueAt(r1, col) : getValueAt(r1, col);
        Object val2 = columnType == NUMBER ? getUnformattedValueAt(r2, col) : getValueAt(r2, col);

        if (val1 == null) {
            return (val2 == null)        ? 0 : -1;
        } else if (val2 == null) {
            return 1;
        } else {
        	if (columnType == NUMBER) {
        		return ((Comparable)val1).compareTo(val2);
        	}
            return val1.toString().compareTo(val2.toString());
        }
    }
    
    /**
     * Returns the headerKeys.
     * @return String[]
     */
    public String[] getHeaderKeys() {
        return headerKeys;
    }

    /**
     * Returns the resourceBase.
     * @return String
     */
    public String getResourceBase() {
        return resourceBase;
    }

    /**
     * Returns the resourceBundle.
     * @return SResourceBundle
     */
    public SResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    /**
     * Sets the headerKeys.
     * @param headerKeys The headerKeys to set
     */
    public void setHeaderKeys(String[] headerKeys) {
        this.headerKeys = headerKeys;
    }
    
    /**
     * Sets the resourceBase.
     * @param resourceBase The resourceBase to set
     */
    public void setResourceBase(String resourceBase) {
        this.resourceBase = resourceBase;
    }

    /**
     * Sets the resourceBundle.
     * @param resourceBundle The resourceBundle to set
     */
    public void setResourceBundle(SResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    /**
     * Returns the propertyNames.
     * @return String[]
     */
    public String[] getPropertyNames() {
        return propertyNames;
    }
    
    protected Format[] getColumnFormatsFromLookAndFeel() {
		Object columnFormatsObj =  getValueFromLookAndFeel("formats");
		if (columnFormatsObj == null) {
			return null;
		}
		
		if (!(columnFormatsObj instanceof Format[])) {
			logger.error("Wrong type for column format definition in LookAndFeel. Expected type is Format[]. Obj found = " + columnFormatsObj);
			return null;
		}
		return (Format[]) columnFormatsObj;    	
    }

    private Format getStandardFormatFromLookAndFeel(String formatKey) {
		Object formatsObj = getValueFromLookAndFeel(formatKey);
		if (formatsObj == null) {
			return null;
		}
		
		if (!(formatsObj instanceof Format)) {
			logger.error("Wrong type standard format definition in LookAndFeel. Expected type is Format. Obj found = " + formatsObj);
			return null;
		}
		return (Format) formatsObj;    	
    }

    protected Format getStandardTimeFormatFromLookAndFeel() {
    	return getStandardFormatFromLookAndFeel("stdTimeFormat");
    }

    protected Format getStandardDateFormatFromLookAndFeel() {
    	return getStandardFormatFromLookAndFeel("stdDateFormat");
    }

    protected Format getStandardIntegerFormatFromLookAndFeel() {
    	return getStandardFormatFromLookAndFeel("stdIntegerFormat");
    }

    protected Format getStandardDecimalFormatFromLookAndFeel() {
    	return getStandardFormatFromLookAndFeel("stdDecimalFormat");
    }
    
    private Object getValueFromLookAndFeel(String property) {
		String classFullName = getClass().getName();
		String className = classFullName.substring(classFullName.lastIndexOf(".") + 1);
		return UIManager.get(className + "." + property);
    }
    
    /**
     * Returns the columnMap configured in the UIManager.
     */
    protected int[] getColumnMapFromLookAndFeel() {
		Object columnMapObj =  getValueFromLookAndFeel("columns");
		if (columnMapObj == null) {
			return null;
		}
		
		if (!(columnMapObj instanceof int[])) {
			logger.error("Wrong type for column map in LookAndFeel. Expected type is int[]. Obj found = " + columnMapObj);
			return null;
		}
		return (int[]) columnMapObj;    	
    }

    /**
     * Sets the propertyNames.
     * @param propertyNames The propertyNames to set
     */
    public void setPropertyNames(String[] propertyNames) {
        this.propertyNames = propertyNames;
    }
    
    public void useResourceBundleFromLookAndFeel() {
		String classFullName = getClass().getName();
		String className = classFullName.substring(classFullName.lastIndexOf(".") + 1);
    	String resourceName = (String)UIManager.get(className + ".resourceName");
    	if (resourceName == null) {
    		logger.error("Can not get object <"+ className + ".resource> from LookAndFeel");
    		return;
    	}
		resourceBundle = new SResourceBundle(resourceName);
		resourceBase = className + "_H_";
    }

    /**
     * Returns the data.
     * @return Collection
     */
    protected Object[] getDataArray() {
        return dataArray;
    }

}
