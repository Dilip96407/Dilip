package de.westlb.mgb.client.ui.tablemodel;

import de.westlb.mgb.client.ui.util.CurrencyFormat;



/**
 * @author WSY4148
 *
 * @deprecated
 */
public class AddonTableModel extends AbstractTableModel {

	private CurrencyFormat currencyFormat = new CurrencyFormat();
	
	/** Column definitions */
	public static final int COLUMN_CONDITION				= 0;
	public static final int COLUMN_PRICE_TOLERANCE_PERCENT	= 1;
	public static final int COLUMN_TIME_TOLERANCE_MINUTES	= 2;
	public static final int COLUMN_COMMENT					= 3;
	public static final int COLUMN_CHANGE_PENDING			= 4;
	public static final int COLUMN_CHANGE_REQUESTED_BY_NAME	= 5;

	private String[] headerKeys = new String[]{
		"condition",
		"priceTolerancePercent",
		"timeToleranceMinutes",		
		"comment",
		"changePending",
		"changeRequestedByName",
	};

	private String[] propertyNames = new String[] {
		"condition",
		"priceTolerancePercent",
		"timeToleranceMinutes",		
		"comment",
		"changePending",
		"changeRequestedByName",
	};
	
    /**
     * Constructor for AutomaticStateTableModel.
     */
    public AddonTableModel() {
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
		switch (getColumnIndexInModel(column)) {
        	case COLUMN_CHANGE_PENDING:
        		value = BOOLEAN;
        		break;
        	case COLUMN_PRICE_TOLERANCE_PERCENT:
			case COLUMN_TIME_TOLERANCE_MINUTES:
        		value = NUMBER;
        		break;
        	default:
		        value = super.getColumnType(column);	
        		break;
        }
        return value;
    }
        
    /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
    	Object value;
		switch (getColumnIndexInModel(column)) {
			case COLUMN_PRICE_TOLERANCE_PERCENT:
				value = currencyFormat.format(super.getValueAt(row, column));
				break;
			default:
				value = super.getValueAt(row, column);	
				break;
		}
		return value;   
	 }
}
