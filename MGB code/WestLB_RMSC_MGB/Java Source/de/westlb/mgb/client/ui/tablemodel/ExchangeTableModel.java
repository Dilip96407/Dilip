package de.westlb.mgb.client.ui.tablemodel;

/**
 * @author WSY4148
 *
 * Adapter to convert the employee search result returned
 * by the mgb service to a gui table model.
 * 
 * @deprecated
 */
public class ExchangeTableModel extends AbstractTableModel {
	/** Column definitions */
	public static final int COLUMN_BLOOMBERG_ID					= 0;
	public static final int COLUMN_REUTERS_ID					= 1;
	public static final int COLUMN_CHANGE_PENDING				= 2;
	public static final int COLUMN_CHANGE_REQUESTED_BY_NAME		= 3;
	
	private String[] headerKeys = new String[]{
		"bloombergId",
		"reutersId",		
		"changePending",
		"changeRequestedByName",
	};

	private String[] propertyNames = new String[] {
		"bloombergId",
		"reutersId",
		"changePending",
		"changeRequestedByName",
	};
	
	/**
	 * @see de.westlb_systems.xaf.swing.SDataModel2#getColumnType(int)
	 */
	@Override
    public int getColumnType(int column) {
		int value;
		switch (column) {
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
    public ExchangeTableModel() {
        super();

        setHeaderKeys(headerKeys);
        setPropertyNames(propertyNames);
    }
}
