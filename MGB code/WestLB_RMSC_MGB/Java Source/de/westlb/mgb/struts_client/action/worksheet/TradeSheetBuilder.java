package de.westlb.mgb.struts_client.action.worksheet;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import de.westlb.mgb.client.server.vo.TradeOverviewVo;

public abstract class TradeSheetBuilder extends SheetBuilder {
	private static Logger logger = Logger.getLogger(TradeSheetBuilder.class);
    protected int curRow = 0;
		

	private HashMap<String, String> stateMap = new HashMap<String, String>(); 
	
	protected Sheet sheet;

	public TradeSheetBuilder(Workbook workbook, String name) {
	    this.init(workbook, name);
	}

    protected abstract void createHeaderRow(boolean withMarketPrices);

    protected abstract void createTradeRow(TradeOverviewVo trade, boolean withMarketPrices);

	protected void init(Workbook workbook, String name) {
		super.init(workbook);
		curRow = 0;
        logger.debug("Creating sheet "+name);
		sheet = createSheet(workbook, name);
	}

	public void process(TradeOverviewVo[] trades, boolean withMarketPrices) {
	    logger.debug("Start processing " + trades.length + " trades");
	    createHeaderRow(withMarketPrices);
	    for (int i = 0; i < trades.length; i++) {
	        createTradeRow(trades[i], withMarketPrices);
        }
	    logger.debug("Stop processing " + trades.length + " trades");
	}

    
    protected String getState(Object key) {
        if (key != null) {
            if (stateMap != null && stateMap.containsKey(key)) {
                return stateMap.get(key);
            }
            return key.toString();
        } 
        return "";
    }

    
    public void setStateMap(HashMap<String, String> stateMap) {
        this.stateMap = stateMap;
    }
}
