package de.westlb.mgb.client.ui.tablemodel;

import de.westlb_systems.xaf.swing.SDataModel2;
import de.westlb_systems.xaf.swing.SDataModelListener;
import de.westlb_systems.xaf.util.SResourceBundle;

/**
 * @author wsy4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class BeanDifferenceTableModel implements SDataModel2 {
	/** Column definitions */
	public static final int PROPERTY_NAME			= 0;
	public static final int OLD_VALUE				= 1;
	public static final int NEW_VALUE				= 2;

	private static final String[] headerKeys = new String[]{
		"PROPERTY_NAME",
		"OLD_VALUE",
		"NEW_VALUE",
	};

	private SResourceBundle resourceBundle = null;
	private String resourceBase = null;
	
	private String names[];
	private String oldValues[];
	private String newValues[];
	
	public BeanDifferenceTableModel(String[] names, String[] oldValues, String[] newValues) {
		this.names = names;
		this.oldValues = oldValues;
		this.newValues = newValues;
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
     * Constructor for BeanDifferenceTableModel.
     */
    public BeanDifferenceTableModel() {
        super();
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
        return SDataModel2.DEFAULT;
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
        return 3;
    }

    /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int column) {
		if(headerKeys != null && column >=0 && column < headerKeys.length) {
			return getResourceString(headerKeys[column]);
		}  
	
		return "COL" + column;      
	}

    /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getRowCount()
     */
    @Override
    public int getRowCount() {
        return names.length;
    }

    /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
    	Object value = null;
        switch (column) {
        	case 0:
        		value = names[row];
        		break;
        	case 1:
        		value = oldValues[row];
        		break;
        	case 2:
        		value = newValues[row];
        		break;
        	default:
        		break;
        };
        
        return value;
    }

    /**
     * @see de.westlb_systems.xaf.swing.SSortableDataModel#compareRows(int, int, int)
     */
    @Override
    public int compareRows(int r1, int r2, int col) {
        Object val1 = getValueAt(r1, col);
        val1 = (val1 != null)        ? val1.toString() : null;

        Object val2 = getValueAt(r2, col);
        val2 = (val2 != null)        ? val2.toString() : null;

        if (val1 == null) {
            return (val2 == null)        ? 0 : -1;
        } else if (val2 == null) {
            return 1;
        } else {
            return val1.toString().compareTo(val2.toString());
        }
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

}
