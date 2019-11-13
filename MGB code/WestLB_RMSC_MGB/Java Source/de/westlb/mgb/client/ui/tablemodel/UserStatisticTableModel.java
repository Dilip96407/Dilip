package de.westlb.mgb.client.ui.tablemodel;

import java.util.Calendar;

import de.westlb.mgb.client.ui.util.DateFormat;



/**
 * @author WSY4148
 *
 * @deprecated
 */
public class UserStatisticTableModel extends AbstractTableModel {

	private DateFormat timeFormat = new DateFormat(DateFormat.TIME_FORMAT); 
	
	/** Column definitions */
	public static final int COLUMN_USER_ID						= 0;
	public static final int COLUMN_USER_NAME					= 1;
	public static final int COLUMN_ACTIVE_SESSION_COUNT			= 2;
	public static final int COLUMN_SESSION_COUNT				= 3;
	public static final int COLUMN_LATEST_SESSION_CREATE_TIME	= 4;
	public static final int COLUMN_LATEST_SESSION_DESTROY_TIME	= 5;
	
	private String[] headerKeys = new String[]{
		"userId",
		"userName",
		"activeSessionCount",
		"sessionCount",		
		"latestSessionCreateTime",
		"latestSessionDestroyTime",
	};

	private String[] propertyNames = new String[] {
		"userId",
		"userName",
		"activeSessionCount",
		"sessionCount",		
		"latestSessionCreateTime",
		"latestSessionDestroyTime",
	};
	
    /**
     * Constructor for AutomaticStateTableModel.
     */
    public UserStatisticTableModel() {
        super();

        setHeaderKeys(headerKeys);
        setPropertyNames(propertyNames);
    }

    /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
		Object value = null;
		switch (getColumnIndexInModel(column)) {
			case COLUMN_LATEST_SESSION_CREATE_TIME:
			case COLUMN_LATEST_SESSION_DESTROY_TIME:
				value =  super.getValueAt(row, column);
				if (value instanceof Calendar) {
					value = timeFormat.format(value);
				}
				break;
			default:
				value = super.getValueAt(row, column);
				break;
		}
		return value;
	 }
}
