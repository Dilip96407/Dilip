package de.westlb.mgb.client.ui.tablemodel;

import java.util.Calendar;

import de.westlb.mgb.client.ui.selection_list.DualControlJobEntityTypeList;
import de.westlb.mgb.client.ui.selection_list.DualControlJobOperationList;
import de.westlb.mgb.client.ui.util.DateFormat;

/**
 * @author WSY4148
 *
 * Adapter to convert the dual control job search result
 * to a gui table model.
 * 
 * @deprecated
 */
public class DualControlJobTableModel extends AbstractTableModel {
	/** Column definitions */
	public static final int COLUMN_ENTITY_TYPE		= 0;
	public static final int COLUMN_ENTITY_NAME		= 1;
	public static final int COLUMN_OPERATION		= 2;
	public static final int COLUMN_REQUESTED_BY		= 3;
	public static final int COLUMN_REQUESTED_DATE	= 4;

	private DateFormat timeFormat;
	private DualControlJobOperationList dualControlJobOperationList = new DualControlJobOperationList();
	private DualControlJobEntityTypeList dualControlJobEntityTypeList = new DualControlJobEntityTypeList();
	
	private String[] headerKeys = new String[]{
		"ENTITY_TYPE",
		"ENTITY_NAME",
		"OPERATION",
		"REQUESTED_BY",
		"REQUESTED_DATE",
	};

	private String[] propertyNames = new String[] {
		"entityType",
		"entityName",
		"operation",
		"requestedByName",
		"requestedDate",
	};
	
    /**
     * Constructor for AutomaticStateTableModel.
     */
    public DualControlJobTableModel() {
        super();

		timeFormat = new DateFormat(DateFormat.TIME_FORMAT);        
        setHeaderKeys(headerKeys);
        setPropertyNames(propertyNames);
    }
    
    /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
    	Object value =  super.getValueAt(row, column);
		switch(column) {
			case COLUMN_REQUESTED_DATE:
				if (value instanceof Calendar) {
					value = timeFormat.format(value);
				}
				break;
			case COLUMN_OPERATION:
				value = dualControlJobOperationList.get(value);
				break;
			case COLUMN_ENTITY_TYPE:
				if ( value != null ) {
					value = dualControlJobEntityTypeList.get(((String)value).toUpperCase());
				}
				break;				
		}

        return value;
    }

}
