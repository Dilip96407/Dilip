package de.westlb.mgb.struts_client.action.worksheet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import de.westlb.mgb.client.server.vo.TradeOverviewVo;

public class DerivativeTradeSheetBuilder extends TradeSheetBuilder {

	private static int i = 0;	
	private static final int COL_TRADE = i++;
	private static final int COL_DEAL = i++;
	private static final int COL_INSTRUMENT = i++;
	private static final int COL_STRUCTURE = i++;
	private static final int COL_TYPE = i++;
	private static final int COL_BOOK = i++;
	private static final int COL_TRADER_LOCATION = i++;
	private static final int COL_VOLUME = i++;
    private static final int COL_TRADE_PRICE = i++;
    private static final int COL_CURRENCY = i++;
	private static final int COL_TURNOVER = i++;
    private static final int COL_TRADER = i++;
    private static final int COL_COUNTERPARTY = i++;
	private static final int COL_TRADE_DATE = i++;
	private static final int COL_SYSTEM_DATE = i++;
    private static final int COL_PRICE_DIFFERENCE = i++;
	private static final int COL_PRICE_TOLERANCE = i++;
	private static final int COL_NPV_CHANGE = i++;
	private static final int COL_AUTO_STATE_CODE = i++;
	private static final int COL_MAN_STATE_CODE = i++;
	private static final int COL_MAN_STATE_COMMENT = i++;
	private static final int COL_RECL_STATE_CODE = i++;


	public DerivativeTradeSheetBuilder(Workbook workbook, String name) {
	    super(workbook, name);
	}

    @Override
    protected void createHeaderRow(boolean withMarketPrices) {
//      logger.debug("Create header row " + curRow);
        Row row = sheet.createRow(curRow++);
        createHeaderCell(row, COL_TRADE, "Trade ID");
        createHeaderCell(row, COL_DEAL, "Deal");
        createHeaderCell(row, COL_INSTRUMENT, "Instrument");
        createHeaderCell(row, COL_STRUCTURE, "Structure");
        createHeaderCell(row, COL_TYPE, "Type");
        createHeaderCell(row, COL_BOOK, "Book");
        createHeaderCell(row, COL_TRADER_LOCATION, "Location");
        createHeaderCell(row, COL_VOLUME, "Volume");
        createHeaderCell(row, COL_TRADE_PRICE, "Price");
        createHeaderCell(row, COL_CURRENCY, "Currency");
        createHeaderCell(row, COL_TURNOVER, "Turnover");
        createHeaderCell(row, COL_TRADER, "Trader");
        createHeaderCell(row, COL_COUNTERPARTY, "Counterparty");
        createHeaderCell(row, COL_TRADE_DATE, "Trade time");
        createHeaderCell(row, COL_SYSTEM_DATE, "Amend time");
        createHeaderCell(row, COL_PRICE_DIFFERENCE, "Price difference");
        createHeaderCell(row, COL_PRICE_TOLERANCE, "Price tolerance");
        createHeaderCell(row, COL_NPV_CHANGE, "NPV Change");
        createHeaderCell(row, COL_AUTO_STATE_CODE, "Automatic state");
        createHeaderCell(row, COL_MAN_STATE_CODE, "Manual state");
        createHeaderCell(row, COL_MAN_STATE_COMMENT, "Manual comment");
        createHeaderCell(row, COL_RECL_STATE_CODE, "Reclamation state");
    }
    
	@Override
    protected void createTradeRow(TradeOverviewVo trade, boolean withMarketPrices) {
//		logger.debug("Create trade row " + curRow + " for trade "+ trade.getTradeId());
        Row row = sheet.createRow(curRow++);
     	
        createCell(row, COL_TRADE, trade.getTradeId());
        createCell(row, COL_DEAL, trade.getStornoGroupId());
        createCell(row, COL_INSTRUMENT, trade.getInstrument());
        createCell(row, COL_STRUCTURE, trade.getStructure());
        createCell(row, COL_TYPE, trade.getTradeType());
        createCell(row, COL_BOOK, trade.getBookId());
        createCell(row, COL_TRADER_LOCATION, trade.getTraderLocation());
        createCell(row, COL_VOLUME, trade.getVolume());
        createCell(row, COL_TRADE_PRICE, trade.getTradePrice());
        createCell(row, COL_CURRENCY, trade.getCurrency());
        createCell(row, COL_TURNOVER, trade.getTurnover());
        createCell(row, COL_TRADER, trade.getTraderId());
        createCell(row, COL_COUNTERPARTY, trade.getCounterparty());
        createCell(row, COL_TRADE_DATE, trade.getTradeDate());
        createCell(row, COL_SYSTEM_DATE, trade.getSystemDate());
        createCell(row, COL_PRICE_DIFFERENCE, trade.getCurrentPriceDifference());
        createCell(row, COL_PRICE_TOLERANCE, trade.getPriceTolerance());
        createCell(row, COL_NPV_CHANGE, trade.getAmendmentNpvChange());
        createCell(row, COL_AUTO_STATE_CODE, getState(trade.getAutomaticStateCode()));
        createCell(row, COL_MAN_STATE_CODE, getState(trade.getManualStateCode()));
        createCell(row, COL_MAN_STATE_COMMENT, trade.getManualStateComment());
        createCell(row, COL_RECL_STATE_CODE, getState(trade.getReclamationStateCode()));
	}

}