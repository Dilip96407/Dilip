package de.westlb.mgb.client.ui.tablemodel;


import de.westlb.mgb.client.server.vo.TradeOverviewVo;
import de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo;
import de.westlb_systems.xaf.swing.SDataModel2;

/**
 * @author WSY4148
 *
 * @deprecated
 */
public class TradeTableModel extends AbstractTableModel {
	/** Column definitions */
	public static final int TRADE_ID				= 0;
	public static final int ISIN					= 1;
	public static final int INSTRUMENT				= 2;
	public static final int TRADER					= 3;
	public static final int SYSTEM_TIME				= 4;
	public static final int SETTLEMENT_TIME			= 5;
	public static final int TRADE_TIME				= 6;
	public static final int MATURITY_TIME			= 7;
	public static final int PRICE					= 8;
	public static final int CURRENCY				= 9;
	public static final int VOLUME					= 10;
	public static final int TRADE_TYPE				= 11;
	public static final int BOOK_ID					= 12;
	public static final int TRADER_NAME				= 13;
	public static final int TRADE_TABLE_LAST_COLUMN = TRADER_NAME;

	
	private String[] headerKeys = new String[]{
		"TRADE_ID",
		"ISIN",
		"INSTRUMENT",
		"TRADER",
		"SYSTEM_TIME",
		"SETTLEMENT_TIME",
		"TRADE_TIME",
		"MATURITY_TIME",
		"PRICE",
		"CURRENCY",		
		"VOLUME",	
		"TRADE_TYPE",
		"BOOK_ID",
		"TRADER",
	};

	private String[] propertyNames = new String[] {
		"tradeId",
		"isin",
		"instrument",
		"traderId",
		"systemDate",
		"settlementDate",
		"tradeDate",
		"maturityDate",
		"price",
		"currency",
		"volume",
		"tradeType",
		"bookId",
		"traderName",
	};
	
    /**
     * Constructor for AutomaticStateTableModel.
     */
    public TradeTableModel() {
        super();
        setHeaderKeys(headerKeys);
        setPropertyNames(propertyNames);
    }
    
    public Long getId(int row) {
 		Object rowObj = getRowObject(row);
 		Long id = null;
 		
 		if (rowObj != null) {
 			if (rowObj instanceof TradeSearchResultEntryVo) {
 				id = ((TradeSearchResultEntryVo)rowObj).getId();
 			} else if (rowObj instanceof TradeOverviewVo) {
				id = ((TradeOverviewVo)rowObj).getId();
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
}
