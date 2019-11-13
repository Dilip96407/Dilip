package de.westlb.mgb.client.ui.tablemodel;


/**
 * @author D055625
 *
 * @deprecated
 */
public class MgbConfigurationTableModel extends AbstractTableModel {
	/** Column definitions */
	public static final int COLUMN_KEY								= 0;
	public static final int COLUMN_VALUE							= 1;
	public static final int COLUMN_CHANGE_PENDING					= 2;
	public static final int COLUMN_CHANGE_REQUESTED_BY_NAME			= 3;
	
	private String[] headerKeys = new String[]{
		"key",
		"value",		
		"changePending",
		"changeRequestedByName",
	};

	private String[] propertyNames = new String[] {
		"key",
		"value",		
		"changePending",
		"changeRequestedByName",
	};
	
    /**
     * @see de.westlb_systems.xaf.swing.SDataModel2#getColumnType(int)
     */
    @Override
    public int getColumnType(int column) {
		int value;
    	switch (getColumnIndexInModel(column)) {
         	case COLUMN_CHANGE_PENDING:
        		value = BOOLEAN;
        		break;
        	default:
		        value = super.getColumnType(column);	
        		break;
        }
        return value;
    } 	
    /**
     * Constructor for AutomaticStateTableModel.
     */
    public MgbConfigurationTableModel() {
        super();

        setHeaderKeys(headerKeys);
        setPropertyNames(propertyNames);
    }

}
