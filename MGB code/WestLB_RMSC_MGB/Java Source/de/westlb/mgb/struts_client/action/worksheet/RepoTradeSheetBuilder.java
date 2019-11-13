package de.westlb.mgb.struts_client.action.worksheet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import de.westlb.mgb.client.server.vo.RepoTradeOverviewVo;
import de.westlb.mgb.client.server.vo.TradeOverviewVo;

public class RepoTradeSheetBuilder extends TradeSheetBuilder {
		
    private static int i = 0; 
	private static final int COL_TRADE = i++;
    private static final int COL_ISIN = i++;
	private static final int COL_INSTRUMENT = i++;
    private static final int COL_CATEGORY = i++;
    private static final int COL_SUB_TYPE = i++;
    private static final int COL_TRADER = i++;
    private static final int COL_TRADE_DATE = i++;
    private static final int COL_AMEND_DATE = i++;
	private static final int COL_CURRENCY = i++;
	private static final int COL_BOOK = i++;
    private static final int COL_VOLUME = i++;
	private static final int COL_COUNTERPARTY = i++;
    private static final int COL_REPO_RATE = i++;
    private static final int COL_MARKET_PRICE = i++;
    private static final int COL_BP_DIFFERENCE = i++;
    private static final int COL_BP_TOLERANCE = i++;
    private static final int COL_PL_EFFECT = i++;
    private static final int COL_START_DATE = i++;
    private static final int COL_END_DATE = i++;
	private static final int COL_AUTO_STATE_CODE = i++;
	private static final int COL_MAN_STATE_CODE = i++;
	private static final int COL_MAN_STATE_COMMENT = i++;
	private static final int COL_RECL_STATE_CODE = i++;

    private static final int NUMBER_OF_MARKET_PRICES = 1;


	public RepoTradeSheetBuilder(Workbook workbook, String name) {
	    super(workbook, name);
	}

    @Override
    protected void createHeaderRow(boolean withMarketPrices) {
//      logger.debug("Create header row " + curRow);
        Row row = sheet.createRow(curRow++);
        createHeaderCell(row, COL_TRADE, "Trade ID");
        createHeaderCell(row, COL_ISIN, "ISIN");
        createHeaderCell(row, COL_INSTRUMENT, "Instrument");
        createHeaderCell(row, COL_CATEGORY, "Category");
        createHeaderCell(row, COL_SUB_TYPE, "SubType");
        createHeaderCell(row, COL_TRADER, "Trader");
        createHeaderCell(row, COL_TRADE_DATE, "Trade time");
        createHeaderCell(row, COL_AMEND_DATE, "Amend date");
        createHeaderCell(row, COL_CURRENCY, "Currency");
        createHeaderCell(row, COL_BOOK, "Portfolio");
        createHeaderCell(row, COL_VOLUME, "Volume");
        createHeaderCell(row, COL_COUNTERPARTY, "Counterparty");
        createHeaderCell(row, COL_REPO_RATE, "Repo rate / Fee / Rate");
        int numberOfMarketPrices = NUMBER_OF_MARKET_PRICES;
        if (withMarketPrices) {
            createHeaderCell(row, COL_MARKET_PRICE, "Repo market price");
            numberOfMarketPrices = 0;
        }
        createHeaderCell(row, COL_BP_DIFFERENCE-numberOfMarketPrices, "BPoint difference");
        createHeaderCell(row, COL_BP_TOLERANCE-numberOfMarketPrices, "BPoint tolerance");
        createHeaderCell(row, COL_PL_EFFECT-numberOfMarketPrices, "PL effect");
        createHeaderCell(row, COL_START_DATE-numberOfMarketPrices, "Start date");
        createHeaderCell(row, COL_END_DATE-numberOfMarketPrices, "End date");
        createHeaderCell(row, COL_AUTO_STATE_CODE-numberOfMarketPrices, "Automatic state");
        createHeaderCell(row, COL_MAN_STATE_CODE-numberOfMarketPrices, "Manual state");
        createHeaderCell(row, COL_MAN_STATE_COMMENT-numberOfMarketPrices, "Manual comment");
        createHeaderCell(row, COL_RECL_STATE_CODE-numberOfMarketPrices, "Reclamation state");
    }
    
	@Override
    protected void createTradeRow(TradeOverviewVo repoTrade, boolean withMarketPrices) {
	    RepoTradeOverviewVo trade = (RepoTradeOverviewVo)repoTrade; 
//		logger.debug("Create trade row " + curRow + " for trade "+ trade.getTradeId());
        Row row = sheet.createRow(curRow++);
     	
        createCell(row, COL_TRADE, trade.getTradeId());
        createCell(row, COL_ISIN, trade.getIsin());
        createCell(row, COL_INSTRUMENT, trade.getInstrument());
        createCell(row, COL_CATEGORY, trade.getUnderlyingValueGroup());
        createCell(row, COL_SUB_TYPE, trade.getUnderlyingType());
        createCell(row, COL_TRADER, trade.getTraderId());
        createCell(row, COL_TRADE_DATE, trade.getTradeDate());
        createCell(row, COL_AMEND_DATE, trade.getAmendDate());
        createCell(row, COL_CURRENCY, trade.getCurrency());
        createCell(row, COL_BOOK, trade.getBookId());
        createCell(row, COL_VOLUME, trade.getVolume());
        createCell(row, COL_COUNTERPARTY, trade.getCounterparty());
        createCell(row, COL_REPO_RATE, trade.getRepoRate());
        int numberOfMarketPrices = NUMBER_OF_MARKET_PRICES;
        if (withMarketPrices) {
            if (trade.getCurrentPrice() != null) {
                createCell(row, COL_MARKET_PRICE, trade.getMarketPrice());
            }
            numberOfMarketPrices = 0;
        }
        createCell(row, COL_BP_DIFFERENCE-numberOfMarketPrices, trade.getBasePointDifference());
        createCell(row, COL_BP_TOLERANCE-numberOfMarketPrices, trade.getBasePointTolerance());
        createCell(row, COL_PL_EFFECT-numberOfMarketPrices, trade.getProfitLossEffect());
        createCell(row, COL_START_DATE-numberOfMarketPrices, trade.getStartDate());
        createCell(row, COL_END_DATE-numberOfMarketPrices, trade.getEndDate());
        createCell(row, COL_AUTO_STATE_CODE-numberOfMarketPrices, getState(trade.getAutomaticStateCode()));
        createCell(row, COL_MAN_STATE_CODE-numberOfMarketPrices, getState(trade.getManualStateCode()));
        createCell(row, COL_MAN_STATE_COMMENT-numberOfMarketPrices, trade.getManualStateComment());
        createCell(row, COL_RECL_STATE_CODE-numberOfMarketPrices, getState(trade.getReclamationStateCode()));
	}

}
