/*
 * Created on Aug 26, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.client.ui.tablemodel;

import java.lang.reflect.InvocationTargetException;
import java.text.Format;
import java.util.Calendar;

import org.apache.commons.beanutils.PropertyUtils;

import de.westlb.mgb.client.ui.selection_list.AbstractSelectionList;
import de.westlb_systems.xaf.swing.SDataModel2;
import de.westlb_systems.xaf.swing.SDataModelListener;


/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class TableModel implements SDataModel2 {

   
	// The keys of the column header
	private String headerLabels[] = null;
	
	// The underlying object collection as array
	private Object dataArray[] = null;

	private String propertyNames[] = null;
	
	private String keyPropertyName = null;

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

    /**
     * A definition of types for the columns
     */
    private int[] columnTypes = null;

    /**
     * A definition of types for the columns
     */
    private AbstractSelectionList[] selectionLists = null;

    /**
     * A list that shows which column values need to be calculated by
     * the PropertiyProviderClass
     */
    private boolean[] usePropertiyProviderClass = null;

    private AbstractPropertyProvider propertiyProviderClass = null;

    /**
     * Constructor for TableModel.
     */
    public TableModel() {
        super();
	}    

    /**
     */
    public TableModel(Object[] newData) {
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
        return columnTypes[column];
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
		if (headerLabels != null) {
			return headerLabels.length;
		}  
		
		return 0;  
	}
	
	/**
     * @see de.westlb_systems.xaf.swing.SDataModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int column) {
		if( headerLabels != null && column >= 0 && column < getColumnCount()) {
			return headerLabels[column];
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

		if (propertiyProviderClass != null && usePropertiyProviderClass != null && usePropertiyProviderClass[col]) {
		    value = propertiyProviderClass.getValueAt(row, col, getRowObject(row));
		}
		
		if (selectionLists != null && selectionLists[col] != null) {
			if (value != null) {
				Object o = selectionLists[col].get(value);
                if (o != null) {
                    value = o.toString();
                }
			}
		}

		if (columnFormats != null && columnFormats[col] != null) {
			if (value != null) {
				value = columnFormats[col].format(value);
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

    public Object getUnformattedValueAt(int row, String propertyName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        int col = getIndexForProperty(propertyName);
        if ( col > -1) {
            return getUnformattedValueAt(row, col);
        }
        Object rowObject = getRowObject(row);
        return PropertyUtils.getProperty(rowObject, propertyName);
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
    @SuppressWarnings({ "rawtypes", "unchecked"})
    public int compareRows(int r1, int r2, int col) {
		Object val1 = getUnformattedValueAt(r1, col);
		Object val2 = getUnformattedValueAt(r2, col);
		Object val = val1 != null ? val1: val2;

		if (val instanceof Number || val instanceof Comparable ) {
			// Use unformatted values, So do nothing
		} else 	if (val instanceof Calendar) {
			// Compare millis
			if (val1 != null) {
				val1 = Long.valueOf(((Calendar)val1).getTimeInMillis());	
			}
			if (val2 != null) {
				val2 = Long.valueOf(((Calendar)val2).getTimeInMillis());	
			}
		} else {
			// Compare the formatted values
			val1 = getValueAt(r1, col);
			val2 = getValueAt(r2, col);
		}
  
        if (val1 == null) {
            return (val2 == null) ? 0 : -1;
        } else if (val2 == null) {
            return 1;
        } else {
        	if (val1 instanceof Comparable) {
				return ((Comparable)val1).compareTo(val2);
        	}
            return val1.toString().compareTo(val2.toString());
        }
    }
    
    /**
     * Returns the headerKeys.
     * @return String[]
     */
    public String[] getHeaderLabels() {
        return headerLabels;
    }


    /**
     * Sets the headerKeys.
     * @param headerKeys The headerKeys to set
     */
    public void setHeaderLabels(String[] headerLabels) {
        this.headerLabels = headerLabels;
    }
    

    /**
     * Returns the propertyNames.
     * @return String[]
     */
    public String[] getPropertyNames() {
        return propertyNames;
    }
    

    /**
     * Sets the propertyNames.
     * @param propertyNames The propertyNames to set
     */
    public void setPropertyNames(String[] propertyNames) {
        this.propertyNames = propertyNames;
    }
    

    /**
     * Returns the data.
     * @return Collection
     */
    protected Object[] getDataArray() {
        return dataArray;
    }

    /**
     * @param columnFormats The columnFormats to set.
     */
    public void setColumnFormats(Format[] columnFormats) {
        this.columnFormats = columnFormats;
    }

    /**
     * @param columnTypes The columnTypes to set.
     */
    public void setColumnTypes(int[] columnTypes) {
        this.columnTypes = columnTypes;
    }
    /**
     * @param selectionLists The selectionLists to set.
     */
    public void setSelectionLists(AbstractSelectionList[] selectionLists) {
        this.selectionLists = selectionLists;
    }
    
    public void setPropertyProvider(AbstractPropertyProvider propertiyProviderClass, boolean[] usePropertiyProviderClass) {
        this.usePropertiyProviderClass = usePropertiyProviderClass;
        this.propertiyProviderClass = propertiyProviderClass;
    }
    
    protected int getIndexForProperty(String propertyName) {
        if (propertyName != null) {
            for (int i = 0; i < propertyNames.length; i++) {
                if (propertyName.equals(propertyNames[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    
    public Object getUnfomattedKeyValue(int row) {
        if (keyPropertyName != null) {
            try {
                return getUnformattedValueAt(row, keyPropertyName);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return getUnformattedValueAt(row, 0);
    }
    
    /**
     * @param keyPropertyName The keyPropertyName to set.
     */
    public void setKeyPropertyName(String keyPropertyName) {
        this.keyPropertyName = keyPropertyName;
    }
    
    public void setColumnMap(int[] columnMap) {
        this.columnMap = columnMap;
    }

}
