package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import javax.swing.UIManager;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.StatisticReportParamsVo;
import de.westlb.mgb.client.server.vo.TradeSearchParamsVo;
import de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;
import de.westlb.mgb.model.impl.finder.MgbFinderImpl;


/**
 * Gui model for the trade list view. 
 *
 * @author Manfred Boerner
 */

public class TradeListModel extends AbstractModel {

	public static final int TABLE_FORMAT_LONG		= 1;
	public static final int TABLE_FORMAT_SHORT		= 2;
	
	public static final String TRADE_TABLE_MODEL = "TradeTableModel";
	
	/**
     * A hard-coded threshold that is designed to help avoid Out-Of-Memory
     * situations on the server side (i. e., in {@link MgbFinderImpl}), in which
     * a user-induced trade search query would bring down the whole server,
     * including import threads. (See Mantis #56981.) A value of 40000 has been
     * found to be OK and reasonably comfortable for a 64-bit VM with 4GB heap
     * size. (This is based on a conservative extrapolation of UAT test results:
     * Querying the whole Derivates data stock there, which are 27,000 entries
     * at the time of the test &ndash; 2015-02 &ndash;, resulted in 2.2 GB VM
     * RSS; scaling that linearly, 40,000 should be within the capabilities of a
     * 4GB VM.) Note that this limit must still be set manually by callers in
     * their {@link TradeSearchParamsVo} objects, as {@link #findTrades(Object)}
     * won't automatically apply it. The constant here should provide a sensible
     * default to do so.
     * 
     * <p>
     * Note that typically, (32-bit) clients which are applets run into OOM
     * situations at a far lower number of trades when they actually try to
     * display them in data grids. Hence, this limit should not really impose
     * any other behavior on clients than the one they're used to. It's really
     * designed to make the server side more bullet-proof.
     * </p>
     */
    public static final int MAX_RESULTS = 40000;
    
	/** Definition of all properties provided by the model to the view */
	private final String[] propertyNames = new String[] {
		TRADE_TABLE_MODEL, 
	};
	
	private Object[] searchResult;
	private int tableFormat = TABLE_FORMAT_SHORT;

	/**
	 * Default constructor to create an empty model
	 */
	public TradeListModel() {
		setPropertyNames(propertyNames);
		searchResult = new TradeSearchResultEntryVo[0];
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public TradeListModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
/*	public Object getId(int row) {
		Object id = null;
		if(getProperty(TRADE_TABLE_MODEL) instanceof TradeTableModel) {
			TradeTableModel tableModel = (TradeTableModel)getProperty(TRADE_TABLE_MODEL);
			id = tableModel.getId(row);
		}
		return id;
	}
*/
	public Object getId(int row) {
		Object id = null;
		if(getProperty(TRADE_TABLE_MODEL) instanceof TableModel) {
			TableModel tableModel = (TableModel)getProperty(TRADE_TABLE_MODEL);
			id = tableModel.getUnfomattedKeyValue(row);
		}
		return id;
	}

	public Object getValueAt(int row, int col) {
		Object id = null;
		if(getProperty(TRADE_TABLE_MODEL) instanceof TableModel) {
			TableModel tableModel = (TableModel)getProperty(TRADE_TABLE_MODEL);
			id = tableModel.getValueAt(row, col);
		}
		return id;
	}
	
	public Object[] getIds(int[] rows) {
		Object[] tradeIds = new Long[rows.length];
		for (int i = 0; i < rows.length; i++) {
        	tradeIds[i] = getId(rows[i]);
        }
        return tradeIds;
	}
	
	private Object[] findTrades(Object businessObject) {
		Object[] searchResult = null;
		int maxResults=-1;
		try {
			if (businessObject instanceof TradeSearchParamsVo) {
				TradeSearchParamsVo searchParams = (TradeSearchParamsVo)businessObject;
				maxResults=searchParams.getMaxResults();
				if (getTableFormat() == TABLE_FORMAT_SHORT) {
					searchResult = MgbServiceFactory.getService().findTrades(searchParams);
				} else {
					searchResult = MgbServiceFactory.getService().findTradesOverviewVo(searchParams);
				}
			} else if (businessObject instanceof StatisticReportParamsVo) {
				StatisticReportParamsVo searchParams = (StatisticReportParamsVo)businessObject;
				if (getTableFormat() == TABLE_FORMAT_SHORT) {
					searchResult = MgbServiceFactory.getService().findTradesForStatisticRow(searchParams);
				} else {
					searchResult = MgbServiceFactory.getService().findTradeOverviewVosForStatisticRow(searchParams);
				}
			}		
            if(maxResults>0 && searchResult.length>=maxResults)
            {
                logger.warn("Search result hit maximum number of records: "+
                        maxResults);
                setError(DATABASE_ERROR,"Search result hit maximum number of records ("+
                        maxResults+"), some records may be missing",null);
            }
		} catch (RemoteException e) {
            handleRemoteException(e);
		}
		return searchResult;	
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		tableFormat = getTableFormatFromSession();
		
		searchResult = findTrades(getBusinessObject());
			
		try{
		    String sys = "TradeTable"+UIManager.get("MandantName");
		    String type = "Short";
		    if (getTableFormat() == TABLE_FORMAT_LONG) {
		        type = "Long";
		    }
		    TableModel mod = TableModelFactory.createTableModel(sys+type, searchResult);
		    setProperty(TRADE_TABLE_MODEL, mod);
		} catch (RemoteException e) {
            handleRemoteException(e);
		}
	}


	/**
	 * Sets the business object where the model gets its data from.
	 * 
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		super.setBusinessObject(newBusinessObject);
		fillModel();
	}
    /**
     * Returns the tradeList.
     * @return TradeSearchResultEntryVo[]
     */
    public Object[] getSearchResult() {
        return searchResult;
    }

    /**
     * @return
     */
    public int getTableFormat() {
        return tableFormat;
    }

    public int getTableFormatFromSession() {
		try {
			Object value = MgbServiceFactory.getService().getClientConfig("TRADE_TABLE_FORMAT");
			if (value != null && value instanceof Integer) {
				return ((Integer)value).intValue();
			}
		} catch (RemoteException e) {
            handleRemoteException(e);
		}
		return TABLE_FORMAT_LONG;
    }

    /**
     * @param i
     */
    public void setTableFormat(int newTableFormat) {
        tableFormat = newTableFormat;
		try {
			MgbServiceFactory.getService().setClientConfig("TRADE_TABLE_FORMAT", Integer.valueOf(newTableFormat));
		} catch (RemoteException e) {
            handleRemoteException(e);
		}
        reload();
    }

}
