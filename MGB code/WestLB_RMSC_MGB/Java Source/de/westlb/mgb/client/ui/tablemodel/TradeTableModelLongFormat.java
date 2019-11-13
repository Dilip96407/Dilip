/*
 * Created on Jan 12, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.ui.tablemodel;

import de.westlb.mgb.client.ui.selection_list.AllStateCodeList;
import de.westlb_systems.xaf.swing.SDataModel2;

/**
 * @author WSY4148
 *
 * @deprecated
 */
public class TradeTableModelLongFormat extends TradeTableModel {

	public static final int COLUMN_COUNTERPARTY			= TRADE_TABLE_LAST_COLUMN + 1 ;
	public static final int COLUMN_PRICE_DIFFERENCE		= TRADE_TABLE_LAST_COLUMN + 2 ;
	public static final int COLUMN_PRICE_TOLERANCE		= TRADE_TABLE_LAST_COLUMN + 3 ;
	public static final int COLUMN_TURNOVER				= TRADE_TABLE_LAST_COLUMN + 4 ;
	public static final int COLUMN_AUTOMATIC_STATE		= TRADE_TABLE_LAST_COLUMN + 5 ;
	public static final int COLUMN_MANUAL_STATE			= TRADE_TABLE_LAST_COLUMN + 6 ;
	public static final int COLUMN_RECLAMATION_STATE	= TRADE_TABLE_LAST_COLUMN + 7 ;
	public static final int COLUMN_REPO_RATE			= TRADE_TABLE_LAST_COLUMN + 8 ;
	public static final int COLUMN_REPO_PROFIT_LOSS_EFFECT	= TRADE_TABLE_LAST_COLUMN + 9 ;
	public static final int COLUMN_REPO_MARKET_PRICE		= TRADE_TABLE_LAST_COLUMN + 10 ;
	public static final int COLUMN_REPO_START_TIME			= TRADE_TABLE_LAST_COLUMN + 11;
	public static final int COLUMN_REPO_END_TIME			= TRADE_TABLE_LAST_COLUMN + 12;	
	public static final int COLUMN_REPO_BASE_POINT_TOLERANCE = TRADE_TABLE_LAST_COLUMN + 13;
	public static final int COLUMN_REPO_BASE_POINT_DIFFERENCE = TRADE_TABLE_LAST_COLUMN + 14;

	protected AllStateCodeList stateCodeList = new AllStateCodeList();
		
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
		"counterparty",
		"currentPriceDifference",	
		"priceTolerance",	
		"turnover",
		"automaticState",
		"manualState",
		"reclamationState",
		"repoRate",
		"repoProfitLossEffect",
		"repoMarketPrice",
		"repoStartDate",
		"repoEndDate",
		"repoBasePointTolerance",
		"repoBasePointDifference",
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
		"tradePrice",
		"currency",
		"volume",
		"tradeType",
		"bookId",
		"traderName",
		"counterparty",
		"currentPriceDifference",	
		"priceTolerance",	
		"turnover",
		"automaticStateCode",
		"manualStateCode",
		"reclamationStateCode",		
		"repoRate",
		"profitLossEffect",
		"marketPrice",
		"startDate",
		"endDate",
		"basePointTolerance",
		"basePointDifference",
	};
	
	
    /**
     * 
     */
    public TradeTableModelLongFormat() {
        super();
		setHeaderKeys(headerKeys);
		setPropertyNames(propertyNames);
	}
	
	/**
	 * @see de.westlb_systems.xaf.swing.SDataModel2#getColumnType(int)
	 */
	@Override
    public int getColumnType(int column) {
		int columnType;
    	
		switch (getColumnIndexInModel(column)) {
			case COLUMN_PRICE_DIFFERENCE:
			case COLUMN_PRICE_TOLERANCE:
			case COLUMN_TURNOVER:
			case COLUMN_REPO_RATE:
			case COLUMN_REPO_PROFIT_LOSS_EFFECT:
			case COLUMN_REPO_MARKET_PRICE:
			case COLUMN_REPO_BASE_POINT_TOLERANCE:
			case COLUMN_REPO_BASE_POINT_DIFFERENCE:
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
			case COLUMN_AUTOMATIC_STATE:
			case COLUMN_MANUAL_STATE:
			case COLUMN_RECLAMATION_STATE:
				value =  super.getValueAt(row, column);
				value = value == null ? "" : stateCodeList.get(value);
				break;
			default:
			value = super.getValueAt(row, column);
		}

		return value;
	}
}
