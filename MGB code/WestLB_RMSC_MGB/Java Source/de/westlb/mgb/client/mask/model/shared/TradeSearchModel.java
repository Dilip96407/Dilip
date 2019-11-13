package de.westlb.mgb.client.mask.model.shared;

import java.util.Calendar;

import de.westlb.mgb.client.mask.view.shared.TradeSearchDialog;
import de.westlb.mgb.client.server.vo.TradeSearchParamsVo;
import de.westlb.mgb.client.ui.selection_list.AbstractSelectionList;
import de.westlb.mgb.client.ui.selection_list.AllStateCodeList;
import de.westlb.mgb.util.DateTimeUtils;
import de.westlb_systems.xaf.util.table.TablePool;
import de.westlb_systems.xaf.util.table.swing.TablePoolComboBoxModel;

/**
 * Gui model for the employee edit dialog
 *
 * @author Manfred Boerner
 */

public class TradeSearchModel extends AbstractModel {
	public static final String P_TRADE_IDS							= "tradeIds";						
    public static final String P_JOB_IDS                            = "jobIds";                         
    public static final String P_INSTRUMENTS                        = "instruments";                         
	public static final String P_STATE_CODE_CB_MODEL1				= "stateCodeComboBoxModel1";
	public static final String P_STATE_CODE_CB_MODEL2				= "stateCodeComboBoxModel2";
	public static final String P_STATE_CODE							= "stateCode";
	public static final String P_FROM_DATE							= "fromDate";						
	public static final String P_TO_DATE		   					= "toDate";
	public static final String P_IS_MANUALCHECK_REQUIRED 			= "isManualCheckRequired";
	public static final String P_IS_MANUALCHECK_REQUIRED_NOTHANDLED	= "isManualCheckRequiredButNotHandled";
	public static final String P_IS_MARKETDATA_REQUEST_REQUIRED		= "isMarketDataRequestRequired";
	public static final String P_IS_SMALL_CUSTOMER	 				= "isSmallCustomer";
	public static final String P_IS_STORNO							= "isStorno";
	public static final String P_IS_LATE_ENTRY		 				= "isLateEntry";
	public static final String P_HAS_PRE_DECESSOR		 			= "hasPreDecessor";
	public static final String P_HAS_LOW_TURNOVER		 			= "hasLowTurnover";
		
	private TablePool.Item nullItem;
		
	private int searchMode = -1;
	
	/** Definition of all properties provided by the model to the view */
	private final String[] stdPropertyNames = new String[] {
			P_IS_MANUALCHECK_REQUIRED, 			
			P_IS_MANUALCHECK_REQUIRED_NOTHANDLED,
			P_IS_MARKETDATA_REQUEST_REQUIRED,	
			P_IS_SMALL_CUSTOMER,	 			
			P_IS_STORNO,						
			P_IS_LATE_ENTRY,		 			
			P_HAS_PRE_DECESSOR,
			P_HAS_LOW_TURNOVER,
	};
	
	private final String[] spcPropertyNames = new String[] {
			P_FROM_DATE,						
			P_TO_DATE,		   				
			P_TRADE_IDS,						
			P_JOB_IDS,
			P_INSTRUMENTS,
			P_STATE_CODE_CB_MODEL1,
			P_STATE_CODE_CB_MODEL2,
			P_STATE_CODE,
	};
	

	private TradeSearchParamsVo tradeSearchParamsVo;

	private AllStateCodeList stateCodeList = new AllStateCodeList();

	/**
	 * Default constructor to create an empty model
	 */
	public TradeSearchModel() {
		setPropertyNames(stdPropertyNames);
		setPropertyNames(spcPropertyNames);
		nullItem = stateCodeList.nullItemWith("  ");
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public TradeSearchModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}

	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
				
        setProperty("nullItem", nullItem);
        TablePoolComboBoxModel comboBoxModel = new TablePoolComboBoxModel(stateCodeList);
        comboBoxModel.setNullItem(nullItem);
        setProperty(P_STATE_CODE_CB_MODEL1, comboBoxModel);
        setProperty(P_STATE_CODE_CB_MODEL2, comboBoxModel);

        if (tradeSearchParamsVo != null) {
			propagateProperties(stdPropertyNames, tradeSearchParamsVo);			

    		Long[] jobIds = tradeSearchParamsVo.getJobIds();
    		setProperty(P_JOB_IDS, jobIds);
    
    		String[] instruments = tradeSearchParamsVo.getInstruments();
            setProperty(P_INSTRUMENTS, instruments);

            String[] tradeIds = tradeSearchParamsVo.getTradeIds();
    		setProperty(P_TRADE_IDS, tradeIds);
    		
    		Calendar from = tradeSearchParamsVo.getFromDate();
    		setProperty(P_TO_DATE, from);
    		
    		Calendar to = tradeSearchParamsVo.getToDate();
    		setProperty(P_FROM_DATE, to);
    		
    		setProperty(P_STATE_CODE, AllStateCodeList.getItemFor(stateCodeList, tradeSearchParamsVo.getStateCode()));
        }
		return;
	}
	
    
    /**
     * Save Model Data
     */
    @Override
    public boolean saveModel() {
        boolean enableUnRestrictedSearch = false;
        if (!isModelChanged()) {
            logMessage(LOG_INFO, null);
            return true;
        }
        
        String[] tradeIds                       = getTradeIds();
        String[] instruments                    = getInstruments();
        Long[] jobIds 							= (Long[]) getProperty(P_JOB_IDS);
       	Calendar fromDate						= (Calendar) getProperty(P_FROM_DATE);
        Calendar toDate							= (Calendar) getProperty(P_TO_DATE);
        String stateCode						= getStateCode();
        Boolean isManualCheckRequired			= (Boolean) getProperty(P_IS_MANUALCHECK_REQUIRED);
        Boolean isManualCheckRequiredNotHandled = (Boolean) getProperty(P_IS_MANUALCHECK_REQUIRED_NOTHANDLED);
		Boolean isMarketDataRequestRequired		= (Boolean) getProperty(P_IS_MARKETDATA_REQUEST_REQUIRED);
        Boolean isStorno						= (Boolean) getProperty(P_IS_SMALL_CUSTOMER);
        Boolean isSmallCustomer					= (Boolean) getProperty(P_IS_STORNO);
        Boolean isLateEntry					 	= (Boolean) getProperty(P_IS_LATE_ENTRY);
        Boolean hasPreDecessor					= (Boolean) getProperty(P_HAS_PRE_DECESSOR);
        Boolean hasLowTurnover					= (Boolean) getProperty(P_HAS_LOW_TURNOVER);

        if (searchMode == TradeSearchDialog.DATE_INTERVAL_SEARCH_MODE || searchMode == TradeSearchDialog.JOB_SEARCH_MODE ) {
        	if (searchMode == TradeSearchDialog.DATE_INTERVAL_SEARCH_MODE ) {
        	    if (fromDate == null && toDate == null && !enableUnRestrictedSearch) {
        	        return false;
        	    }
        		tradeSearchParamsVo.setJobIds(null);
        		tradeSearchParamsVo.setFromDate(fromDate);
        		tradeSearchParamsVo.setToDate(DateTimeUtils.endOfDayCal(toDate));
        		tradeSearchParamsVo.setTradeIds(null);        	
        	} else {
        	    if ((jobIds == null || jobIds.length == 0 ) && !enableUnRestrictedSearch) {
        	        return false;
        	    }
           		tradeSearchParamsVo.setJobIds(jobIds);
           		tradeSearchParamsVo.setFromDate(null);
           		tradeSearchParamsVo.setToDate(null);
           		tradeSearchParamsVo.setTradeIds(null);        	
        	}
        	tradeSearchParamsVo.setInstruments(instruments);
        	tradeSearchParamsVo.setStateCode(stateCode);
        	tradeSearchParamsVo.setIsManualCheckRequired(isManualCheckRequired);
        	tradeSearchParamsVo.setIsManualCheckRequiredButNotHandled(isManualCheckRequiredNotHandled);
        	tradeSearchParamsVo.setIsMarketDataRequestRequired(isMarketDataRequestRequired);
        	tradeSearchParamsVo.setIsStorno(isStorno);
        	tradeSearchParamsVo.setIsSmallCustomer(isSmallCustomer);
        	tradeSearchParamsVo.setIsLateEntry(isLateEntry);
        	tradeSearchParamsVo.setHasPreDecessor(hasPreDecessor);
        	tradeSearchParamsVo.setHasLowTurnover(hasLowTurnover);
        }
        if (searchMode == TradeSearchDialog.TRADE_ID_SEARCH_MODE ) {
     	    if ((tradeIds == null || tradeIds.length == 0 ) && !enableUnRestrictedSearch) {
    	        return false;
    	    }
   	    	String mandant = tradeSearchParamsVo.getMandantCode();
   	    	tradeSearchParamsVo = new TradeSearchParamsVo();
   	    	tradeSearchParamsVo.setMandantCode(mandant);
   	    	tradeSearchParamsVo.setTradeIds(tradeIds);
        }
        logger.debug("Setting max results to :"+TradeListModel.MAX_RESULTS);
        tradeSearchParamsVo.setMaxResults(TradeListModel.MAX_RESULTS);
        return true;
    }

	private String[] getTradeIds() {
	    Object tradeIdsProperty = getProperty(P_TRADE_IDS); 
		if (tradeIdsProperty instanceof String[]){
        	 return (String[]) tradeIdsProperty;
        }
		return null;
	}

	private String[] getInstruments() {
        Object instrumentsProperty = getProperty(P_INSTRUMENTS);
        if (instrumentsProperty instanceof String[]) { return (String[]) instrumentsProperty; }
        return null;
    }

    private String getStateCode() {
    	Object stateCodeProperty = getProperty(P_STATE_CODE);

    	return (stateCodeProperty == null) ? null : (String) AbstractSelectionList.getKeyFrom(stateCodeProperty);
    }

	@Override
    public void reload() {
		fillModel();
	}
	
	/**
	 * Sets the business object where the model gets its data from.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		if (!(newBusinessObject instanceof TradeSearchParamsVo)) {
			throw new IllegalArgumentException("Businessobject type must be " + TradeSearchParamsVo.class.getName());
		}
		tradeSearchParamsVo = (TradeSearchParamsVo)newBusinessObject;
		super.setBusinessObject(newBusinessObject);		
		fillModel();
	}

    /**
     */
    public TradeSearchParamsVo getTradeSearchParamsVo() {
        return (tradeSearchParamsVo == null) ? null : tradeSearchParamsVo;
    }

	/**
	 * @param searchMode The searchMode to set.
	 */
	public void setSearchMode(int searchMode) {
		this.searchMode = searchMode;
	}
}
