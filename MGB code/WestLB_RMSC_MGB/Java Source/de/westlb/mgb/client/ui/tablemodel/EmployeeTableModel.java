package de.westlb.mgb.client.ui.tablemodel;

import de.westlb.mgb.client.server.vo.EmployeeSearchResultEntryVo;
import de.westlb_systems.xaf.swing.SIcon;
import de.westlb_systems.xaf.ui.misc.IconKatalog;

/**
 * @author WSY4148
 *
 * Adapter to convert the employee search result returned
 * by the mgb service to a gui table model.
 * 
 * @deprecated
 */
public class EmployeeTableModel extends AbstractTableModel {
	/** Column definitions */
	public static final int COLUMN_EMPLOYEE_TYPE		= 0;
	public static final int COLUMN_LAST_NAME			= 1;
	public static final int COLUMN_FIRST_NAME		= 2;
	public static final int COLUMN_PHONE				= 3;
	public static final int COLUMN_EMAIIL			= 4;
	
	private static SIcon icon_controller	= IconKatalog.getInstance().getIcon("EMPLOYEE_CONTROLLER");
	private static SIcon icon_trader		= IconKatalog.getInstance().getIcon("EMPLOYEE_TRADER");
	
	private String[] headerKeys = new String[]{
		"EMPLOYEE_TYPE",
		"LAST_NAME",
		"FIRST_NAME",
		"PHONE",
		"EMAIL",
	};

	private String[] propertyNames = new String[] {
		"controller",
		"lastName",
		"firstName",
		"phone",
		"email",
	};
	
    /**
     * Constructor for AutomaticStateTableModel.
     */
    public EmployeeTableModel() {
        super();

        setHeaderKeys(headerKeys);
        setPropertyNames(propertyNames);
    }
    
    private EmployeeSearchResultEntryVo getEmployeeSearchResultEntryVo(int row) {
    	return ((EmployeeSearchResultEntryVo)getDataArray()[row]);
    }
    
    /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
    	Object value = null;
		switch (column) {
        	case COLUMN_EMPLOYEE_TYPE:
        			if (getEmployeeSearchResultEntryVo(row).isController()) {
        				value = icon_controller;
        			} else {
        				value = icon_trader;
        			}
        		break;
        
        	default:
        			value = super.getValueAt(row, column);
        		break;
        }    	
    	return  value;
    }

    /**
     * @see de.westlb_systems.xaf.swing.SDataModel2#getColumnType(int)
     */
    @Override
    public int getColumnType(int column) {
    	int columnType = DEFAULT;
    	
    	switch (column) {
        	case COLUMN_EMPLOYEE_TYPE:
        			columnType = ICON;
        		break;
        	default:
        		columnType = super.getColumnType(column);
        }
        return columnType;
    }

    /**
     * @see de.westlb_systems.xaf.swing.SSortableDataModel#compareRows(int, int, int)
     */
    @Override
    public int compareRows(int first, int second, int column) {
    	int value = 0;
    	switch (column) {
        	case COLUMN_EMPLOYEE_TYPE:
        		boolean firstIsCtrl = getEmployeeSearchResultEntryVo(first).isController();
        		boolean secondIsCtrl = getEmployeeSearchResultEntryVo(second).isController();
        		if (firstIsCtrl && !secondIsCtrl) {
        				value = 1;
        		} else if (secondIsCtrl && !firstIsCtrl) {
        				value = -1 ;
        		} else {
        				value = 0;
        		}
        		break;
        
        	default:
        		value = super.compareRows(first, second, column);
        }
        return value;
    }

}
