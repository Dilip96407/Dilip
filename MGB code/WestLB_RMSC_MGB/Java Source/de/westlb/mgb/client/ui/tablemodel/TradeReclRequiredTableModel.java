package de.westlb.mgb.client.ui.tablemodel;


import de.westlb.mgb.client.server.vo.TradeReclRequiredVo;
import de.westlb.mgb.client.ui.selection_list.ManualStateCodeList;
import de.westlb_systems.xaf.swing.SDataModel2;

/**
 * @author WSY4148
 *
 * @deprecated
 */
public class TradeReclRequiredTableModel extends AbstractTableModel {
	/** Column definitions */
	public static final int TRADE_ID					= 0;
	public static final int EMPLOYEE_NAME				= 1;
	public static final int EMAIL						= 2;
	public static final int PHONE						= 3;
	public static final int MANUAL_STATE				= 4;	
	public static final int IS_LATE_ENTRY				= 5;	
	public static final int SOURCE_SYSTEM_INSTRUMENT	= 6;
	public static final int SYSTEM_TIME					= 7;
	public static final int SETTLEMENT_TIME				= 8;
	public static final int TRADE_TIME					= 9;
	public static final int PRICE						= 10;
	public static final int CURRENCY					= 11;
	public static final int VOLUME						= 12;
	public static final int TRADE_TABLE_LAST_COLUMN		= VOLUME;

	protected ManualStateCodeList manualStateCodeList = new ManualStateCodeList();
	
	private String[] headerKeys = new String[]{
		"TRADE_ID",
		"EMPLOYEE_NAME",
		"EMAIL",
		"PHONE",
		"MANUAL_STATE",
		"IS_LATE_ENTRY",
		"SOURCE_SYSTEM_INSTRUMENT",
		"SYSTEM_TIME",
		"SETTLEMENT_TIME",
		"TRADE_TIME",
		"PRICE",
		"CURRENCY",		
		"VOLUME",	
	};

	private String[] propertyNames = new String[] {
		"tradeId",
		"employeeName",
		"email",
		"phone",
		"manualStateCode",
		"lateEntry",
		"sourceSystemInstrument",
		"systemDate",
		"settlementDate",
		"tradeDate",
		"price",
		"currency",
		"volume",
	};
	
    /**
     * Constructor for AutomaticStateTableModel.
     */
    public TradeReclRequiredTableModel() {
        super();
        setHeaderKeys(headerKeys);
        setPropertyNames(propertyNames);
    }
    
    public Long getId(int row) {
 		Object rowObj = getRowObject(row);
 		Long id = null;
 		
 		if (rowObj != null) {
 			if (rowObj instanceof TradeReclRequiredVo) {
 				id = ((TradeReclRequiredVo)rowObj).getId();
 			} 
 		}
 		
    	return id;
    }

	/**
	 * @see de.westlb_systems.xaf.swing.SDataModel2#getColumnType(int)
	 */
	@Override
    public int getColumnType(int column) {
		int columnType;
    	
		switch (getColumnIndexInModel(column)) {
			case IS_LATE_ENTRY:
				columnType = SDataModel2.BOOLEAN;
				break;
			case PRICE:
			case VOLUME:
				// Right aligment
				columnType = SDataModel2.NUMBER;
				break;
			default:
				columnType = super.getColumnType(column);
		}
		return columnType;
	}
	
	/**
	 * @see de.westlb_systems.xaf.swing.SDataModel#getValueAt(int, int)
	 */
	@Override
    public Object getValueAt(int row, int column) {
		Object value = null;
    	
		switch(getColumnIndexInModel(column)) {
			case MANUAL_STATE:
				value =  super.getValueAt(row, column);
				value = value == null ? "" : manualStateCodeList.get(value);
				break;
			default:
			value = super.getValueAt(row, column);
		}

		return value;
	}
}
