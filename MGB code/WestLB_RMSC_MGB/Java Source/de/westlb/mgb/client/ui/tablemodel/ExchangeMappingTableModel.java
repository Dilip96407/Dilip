package de.westlb.mgb.client.ui.tablemodel;

/**
 * @author WSY4148
 *
 * Adapter to convert the employee search result returned
 * by the mgb service to a gui table model.
 * 
 * @deprecated
 */
public class ExchangeMappingTableModel extends AbstractTableModel {
	/** Column definitions */
	public static final int COLUMNG_ISIN						= 0;
	public static final int COLUMN_SOURCE_SYSTEM				= 1;
	public static final int COLUMN_ISIN_COUNTRY_PREFIX			= 2;
	public static final int COLUMN_SOURCE_SYSTEM_EXCHANGE_ID	= 3;
	public static final int COLUMN_CURRENCY						= 4;
	public static final int COLUMN_CHANGE_PENDING				= 5;
	public static final int COLUMN_CHANGE_REQUESTED_BY_NAME		= 6;


	private String[] headerKeys = new String[]{
		"isin",
		"sourceSystemCode",		
		"isinCountryPrefix",		
		"sourceSystemExchangeId",		
		"currency",	
		"changePending",	
		"changeRequestedByName",
	};

	private String[] propertyNames = new String[] {
		"isin",
		"sourceSystemCode",		
		"isinCountryPrefix",		
		"sourceSystemExchangeId",		
		"currency",	
		"changePending",		
		"changeRequestedByName",
	};
	
    /**
     * Constructor for AutomaticStateTableModel.
     */
    public ExchangeMappingTableModel() {
        super();

        setHeaderKeys(headerKeys);
        setPropertyNames(propertyNames);
    }
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

}
