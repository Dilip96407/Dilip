package de.westlb.mgb.client.ui.tablemodel;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author WSY4148
 *
 * Adapter to convert the employee search result returned
 * by the mgb service to a gui table model.
 * 
 * @deprecated
 */
public class StornoGroupTableModel extends AbstractTableModel {
	/** Column definitions */
	public static final int COLUMNG_TRADE_ID = 0;
	public static final int COLUMN_REF_TRADE_ID = 1;
	public static final int COLUMN_FIELD_NAME = 2;
	public static final int COLUMN_FIELD_VALUE = 3;
	public static final int COLUMN_REF_VALUE = 4;

	private String[] headerKeys = new String[] { "tradeId", "referenceTradeId", "fieldName", "fieldValue", "referenceValue", };

	private String[] propertyNames = new String[] { "tradeId", "referenceTradeId", "fieldName", "fieldValue", "referenceValue", };

	/**
	 * Constructor for AutomaticStateTableModel.
	 */
	public StornoGroupTableModel() {
		super();

		setHeaderKeys(headerKeys);
		setPropertyNames(propertyNames);
	}
	/**
	 * @see de.westlb_systems.xaf.swing.SDataModel2#getColumnType(int)
	 */
	@Override
    public int getColumnType(int column) {
		return super.getColumnType(column);
	}

	@Override
    public Object getValueAt(int row, int column) {
		Object value = null;

		switch (getColumnIndexInModel(column)) {
			case COLUMN_FIELD_VALUE :
			case COLUMN_REF_VALUE :
				value = super.getValueAt(row, column);
				if (value == null) {
					value = "";
				} else if (value instanceof Calendar || value instanceof GregorianCalendar) {
					value = timeFormat.formatObject(value);
				} else if (value instanceof Float || value instanceof Double) {
					value = format3GMin4Max10.format(value);
				} else if (value instanceof Integer) {
					value = format3GMin0Max0.format(value);
				} else {
					value = value.toString();
				}
				break;
			default :
				value = super.getValueAt(row, column);
		}

		return value;
	}

}
