package de.westlb.mgb.client.ui.tablemodel;

import de.westlb.mgb.client.ui.selection_list.PriceCheckCategoryList;

/**
 * @author WSY4148
 *
 * @deprecated
 */
public class InstrumentTableModel extends AbstractTableModel {
	/** Column definitions */
	public static final int COLUMN_ISIN							= 0;
	public static final int COLUMN_INSTRUMENT_NAME				= 1;
	public static final int COLUMN_PRICE_CHECK_CATEGORY_NAME	= 2;
	public static final int COLUMN_CHANGE_PENDING				= 3;
	public static final int COLUMN_CHANGE_REQUESTED_BY			= 4;

	private PriceCheckCategoryList priceCheckCategoryList = new PriceCheckCategoryList();
	
	private String[] headerKeys = new String[]{
		"ISIN",
		"INSTRUMENT_NAME",
		"PRICE_CHECK_CATEGORY_NAME",
		"CHANGE_PENDING",		
		"CHANGE_REQUESTED_BY",
	};

	private String[] propertyNames = new String[] {
		"isin",
		"instrumentName",
		"priceCheckCategoryId",
		"changePending",
		"changeRequestedByName",
	};
	
    /**
     * Constructor for AutomaticStateTableModel.
     */
    public InstrumentTableModel() {
        super();

        setHeaderKeys(headerKeys);
        setPropertyNames(propertyNames);
    }

    /**
     * @see de.westlb_systems.xaf.swing.SDataModel2#getColumnType(int)
     */
    @Override
    public int getColumnType(int column) {
		switch(column) {
			case COLUMN_CHANGE_PENDING:
					return BOOLEAN;
			default:
					return DEFAULT;
		}
    }
        
    /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
    	Object value;

		switch(getColumnIndexInModel(column)) {
			case COLUMN_PRICE_CHECK_CATEGORY_NAME:
					value =  super.getValueAt(row, column);
					value = priceCheckCategoryList.get(value);
					break;
			default:
					value =  super.getValueAt(row, column);
					break;
		}

        return value;
    }
}
