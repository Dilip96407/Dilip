/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */

package de.westlb.mgb.client.ui.tablemodel;

import java.util.Calendar;
import java.util.Enumeration;

import de.westlb.mgb.client.mask.model.shared.StatisticListModel;
import de.westlb.mgb.client.server.vo.StatisticReportVo;
import de.westlb.mgb.client.server.vo.StatisticRowVo;
import de.westlb.mgb.client.ui.selection_list.AutoStateCodeList;
import de.westlb.mgb.client.ui.selection_list.ManualSampleStateCodeList;
import de.westlb.mgb.client.ui.selection_list.ManualStateCodeList;
import de.westlb.mgb.client.ui.selection_list.ReclamationStateCodeList;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.model.definition.StateCodeDef;
import de.westlb_systems.xaf.swing.SDataModel2;
import de.westlb_systems.xaf.swing.SDataModelListener;
import de.westlb_systems.xaf.util.SResourceBundle;


/**
 * DOCUMENT ME!
 *
 * @author WSY4148 Folgendes auswählen, um die Schablone für den erstellten Typenkommentar zu
 *         ändern: Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und Kommentare
 *         
 */
public class StatisticReportTableModel implements SDataModel2 {
	private static final int SUM_COLUMN						= 1;
	
	private SResourceBundle resourceBundle = null;
	private String resourceBase = null;
	private DateFormat timeFormat; 
	
	//public static final int COLUMN_
	private StatisticReportVo statisticReportVo;
	private int idxKeyCols;
	private int cntKeyCols;
	private int idxAddKeyCols;
	private int cntAddKeyCols;
	private int idxTotal;
	private int idxAutoMccCheckStateSum;
	private int idxAutoMccCheckStateCols;
	private int cntAutoMccCheckStateCols;
	private int idxAutoStateSum;
	private int idxAutoStateCols;
	private int cntAutoStateCols;
	private int idxManualStateSum;
	private int idxManualStateCols;
	private int cntManualStateCols;
	private int idxReclStateSum;
	private int idxReclStateOpen;
	private int idxReclStateClosed;
	private int idxReclStateCols;
	private int cntReclStateCols;

	private ManualStateCodeList manualStateCodes = new ManualStateCodeList();
	private ManualSampleStateCodeList manualSampleStateCodes = new ManualSampleStateCodeList();
	private AutoStateCodeList autoStateCodes = new AutoStateCodeList();
	private ReclamationStateCodeList reclamationStateCodes = new ReclamationStateCodeList();
		 
	public StatisticReportTableModel(StatisticReportVo statisticReportVo) {
		timeFormat = new DateFormat(DateFormat.TIME_FORMAT); 
		this.statisticReportVo = statisticReportVo;
		idxKeyCols = 0;
		cntKeyCols = statisticReportVo.getKeyColumnNames()== null ? 0 : statisticReportVo.getKeyColumnNames().length;
		
		idxAddKeyCols = idxKeyCols + cntKeyCols;
		cntAddKeyCols = statisticReportVo.getAddKeyColumnNames() == null ? 0 : statisticReportVo.getAddKeyColumnNames().length;
		
		idxTotal = idxAddKeyCols +  cntAddKeyCols;

		idxAutoStateSum = idxTotal +  SUM_COLUMN;
		idxAutoStateCols = idxAutoStateSum + SUM_COLUMN;
		cntAutoStateCols = statisticReportVo.getAutomaticStates() == null ? 0 : statisticReportVo.getAutomaticStates().length;

		idxAutoMccCheckStateSum = idxAutoStateCols +  cntAutoStateCols;
		idxAutoMccCheckStateCols = idxAutoMccCheckStateSum + SUM_COLUMN;
		cntAutoMccCheckStateCols = statisticReportVo.getAutomaticMccCheckStates() == null ? 0 : statisticReportVo.getAutomaticMccCheckStates().length;;

		idxManualStateSum = idxAutoMccCheckStateCols + cntAutoMccCheckStateCols;
		idxManualStateCols = idxManualStateSum + SUM_COLUMN;
		cntManualStateCols = statisticReportVo.getManualStates() == null ? 0 : statisticReportVo.getManualStates().length;

		idxReclStateSum = idxManualStateCols + cntManualStateCols;
		idxReclStateCols = idxReclStateSum + SUM_COLUMN;
		cntReclStateCols = statisticReportVo.getReclamationStates() == null ? 0 : statisticReportVo.getReclamationStates().length;

		idxReclStateOpen = idxReclStateCols + cntReclStateCols;
		idxReclStateClosed = idxReclStateOpen + SUM_COLUMN;

	}
	
    /* (Kein Javadoc)
     * @see de.westlb_systems.xaf.swing.SDataModel2#addSDataModelListener(de.westlb_systems.xaf.swing.SDataModelListener)
     */
    @Override
    public void addSDataModelListener(SDataModelListener listener) {
    }

    /* (Kein Javadoc)
     * @see de.westlb_systems.xaf.swing.SDataModel2#getColumnType(int)
     */
    @Override
    public int getColumnType(int column) {
    	int colType;
		if (idxAutoMccCheckStateCols <= column && column < idxAutoMccCheckStateCols + cntAutoMccCheckStateCols) {
       		colType = SDataModel2.NUMBER;
		} else if (idxAutoStateCols <= column && column < idxAutoStateCols + cntAutoStateCols) {
			colType = SDataModel2.NUMBER;
		} else if (idxManualStateCols <= column && column < idxManualStateCols + cntManualStateCols) {
			colType = SDataModel2.NUMBER;
		} else if (idxReclStateCols <= column && column < idxReclStateCols + cntReclStateCols) {
			colType = SDataModel2.NUMBER;
        } else {
			colType = 0;
        }
        return colType;
    }

    /* (Kein Javadoc)
     * @see de.westlb_systems.xaf.swing.SDataModel2#removeSDataModelListener(de.westlb_systems.xaf.swing.SDataModelListener)
     */
    @Override
    public void removeSDataModelListener(SDataModelListener listener) {
    }

    /* (Kein Javadoc)
     * @see de.westlb_systems.xaf.swing.SDataModel#getColumnCount()
     */
    @Override
    public int getColumnCount() {
		return cntKeyCols + cntAddKeyCols + SUM_COLUMN + SUM_COLUMN + cntAutoMccCheckStateCols + SUM_COLUMN + cntAutoStateCols + SUM_COLUMN + cntManualStateCols + SUM_COLUMN + cntReclStateCols + SUM_COLUMN + SUM_COLUMN;
    }

    /* (Kein Javadoc)
     * @see de.westlb_systems.xaf.swing.SDataModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int column) {
		String colName = "";
		if(idxKeyCols <= column && column < idxKeyCols + cntKeyCols)
		{
		    String key=StatisticListModel.REPORT_PREFIX + statisticReportVo
		            .getName()+"_COLUMN_"+statisticReportVo.getKeyColumnNames()[column-idxKeyCols];
		    colName = getResourceOrEmptyString(key);
        }
		else if(idxAddKeyCols <= column && column < idxAddKeyCols + cntAddKeyCols)
		{
            String key=StatisticListModel.REPORT_PREFIX + statisticReportVo
                    .getName()+"_COLUMN_"+statisticReportVo.getAddKeyColumnNames()[column-idxAddKeyCols];
            colName = getResourceOrEmptyString(key);
		}
		else if (idxAutoMccCheckStateCols <= column && column < idxAutoMccCheckStateCols + cntAutoMccCheckStateCols) {
			colName = getResourceString("COLUMN_MCC_CHECKED_PREFIX") + autoStateCodes.get(statisticReportVo.getAutomaticMccCheckStates()[column - idxAutoMccCheckStateCols].getState()).toString();
		} else if (idxAutoStateCols <= column && column < idxAutoStateCols + cntAutoStateCols) {
			colName = getResourceString("COLUMN_FILTERED_PREFIX") + autoStateCodes.get(statisticReportVo.getAutomaticStates()[column - idxAutoStateCols].getState()).toString();
		} else if (idxManualStateCols <= column && column < idxManualStateCols + cntManualStateCols) {
			String manualState = statisticReportVo.getManualStates()[column - idxManualStateCols].getState();
			if (manualStateCodes.get(manualState) != null) {
				colName = getResourceString("COLUMN_MANUAL_PREFIX") + manualStateCodes.get(manualState).toString();
			} else if (manualSampleStateCodes.get(manualState) != null){
				colName = getResourceString("COLUMN_MANUAL_PREFIX") + manualSampleStateCodes.get(manualState).toString();				
			} else if (StateCodeDef.MAN_REQUIRED_BUT_NOT_HANDLED_YET.equals(manualState)) {
				colName = getResourceString("COLUMN_MANUAL_PREFIX") + "No manual state set yet";
			}
		} else if (idxReclStateCols <= column && column < idxReclStateCols + cntReclStateCols) {
			colName = getResourceString("COLUMN_RECLAMATION_PREFIX") + reclamationStateCodes.get(statisticReportVo.getReclamationStates()[column - idxReclStateCols].getState()).toString();
    	} else if (idxTotal == column) {
    		colName = getResourceString("COLUMN_NO_TRADES_TOTAL");
    	} else if (idxAutoMccCheckStateSum == column) {
    		colName = getResourceString("COLUMN_NO_TRADES_AUTO_MCC_CHECKED");
    	} else if (idxAutoStateSum == column) {
    		colName = getResourceString("COLUMN_NO_TRADES_FILTERED_AUTOMATICALLY");
    	} else if (idxManualStateSum == column) {
    		colName = getResourceString("COLUMN_NO_TRADES_MANUAL_CHECK_REQUIRED");
    	} else if (idxReclStateSum == column) {
    		colName = getResourceString("COLUMN_NO_TRADES_RECL_REQUIRED");
    	} else if (idxReclStateOpen == column) {
    		colName = getResourceString("COLUMN_NO_TRADES_RECL_OPEN");
    	} else if (idxReclStateClosed == column) {
    		colName = getResourceString("COLUMN_NO_TRADES_RECL_CLOSED");
		}
        return colName;
    }

    /* (Kein Javadoc)
     * @see de.westlb_systems.xaf.swing.SDataModel#getRowCount()
     */
    @Override
    public int getRowCount() {
        return statisticReportVo.getRows().length;
    }

    /* (Kein Javadoc)
     * @see de.westlb_systems.xaf.swing.SDataModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
		Object value = null;
    	StatisticRowVo rowVo = statisticReportVo.getRows()[row];
		// Key columns
    	if (idxKeyCols <= column && column < idxKeyCols + cntKeyCols) {
    		value = rowVo.getKeyValues()[column - idxKeyCols];
    	} else if (idxAddKeyCols <= column && column < idxAddKeyCols + cntAddKeyCols) {
			value = rowVo.getAddKeyValues()[column - idxAddKeyCols];
		} else if (idxAutoStateCols <= column && column < idxAutoStateCols + cntAutoStateCols) {
			value = Long.valueOf(rowVo.getAutomaticStates()[column - idxAutoStateCols].getCount());
		} else if (idxManualStateCols <= column && column < idxManualStateCols + cntManualStateCols) {
			value = Long.valueOf(rowVo.getManualStates()[column - idxManualStateCols].getCount());
		} else if (idxReclStateCols <= column && column < idxReclStateCols + cntReclStateCols) {
			value = Long.valueOf(rowVo.getReclamationStates()[column - idxReclStateCols].getCount());
    	} else if (idxAutoMccCheckStateCols <= column && column < idxAutoMccCheckStateCols + cntAutoMccCheckStateCols) {
			value = Long.valueOf(rowVo.getAutomaticMccCheckStates()[column - idxAutoMccCheckStateCols].getCount());
    	} else if (idxTotal == column) {
			value = Long.valueOf(rowVo.getNoTradesTotal());
    	} else if (idxAutoMccCheckStateSum == column) {
            value = Long.valueOf(rowVo.getNoTradesAutoMccChecked());
    	} else if (idxAutoStateSum == column) {
            value = Long.valueOf(rowVo.getNoTradesOkAfterAutoCheck());
    	} else if (idxManualStateSum == column) {
			value = Long.valueOf(rowVo.getNoTradesManualCheckRequired()-(rowVo.getNoTradesReclClosed()+rowVo.getNoTradesReclOpen()+rowVo.getNoTradesReclWaiting()));
    	} else if (idxReclStateSum == column) {
			value = Long.valueOf(rowVo.getNoTradesReclClosed()+rowVo.getNoTradesReclOpen()+rowVo.getNoTradesReclWaiting());
    	} else if (idxReclStateOpen == column) {
			value = Long.valueOf(rowVo.getNoTradesReclOpen()+rowVo.getNoTradesReclWaiting());
    	} else if (idxReclStateClosed == column) {
			value = Long.valueOf(rowVo.getNoTradesReclClosed());
        }

		if (value instanceof Calendar) {
			value = timeFormat.format(value);
		}
    	
        return value;
    }

    /* (Kein Javadoc)
     * @see de.westlb_systems.xaf.swing.SSortableDataModel#compareRows(int, int, int)
     */
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes"})
    public int compareRows(int first, int second, int column) {
		Object value1 = getValueAt(first, column);
		Object value2 = getValueAt(second, column);
		return value1 instanceof Comparable ? ((Comparable)value1).compareTo(value2) : 0;
    }
    
	/**
	 * Returns the resource string for an key
	 *
	 * The key is calculated by resourceBase+Key
	 * 
	 */
	public String getResourceString(String key) {
		if(getResourceBundle() != null) {
			if(getResourceBase() != null) {
				key = getResourceBase() + key;
			}
			return getResourceBundle().getResourceString(key);
		}
		return key;
	}
	
    /**
     * A variant of {@link #getResourceString(String)} that does not use
     * <code>key</code> as default text which the XAF method normally does (at
     * least I don't see an immediate way around it). This method returns an
     * empty string if no suitable resource key was found.
     */
	public String getResourceOrEmptyString(String key)
	{
        String bkey=key;
        if(getResourceBase() != null) 
        {
            bkey = getResourceBase() + key;
        }
        for(Enumeration keys = getResourceBundle().getKeys();keys.hasMoreElements();)
        {
            if(keys.nextElement().equals(bkey)) return getResourceString(key);
        }
        return "";
	}
	
    /**
     * @return
     */
    public String getResourceBase() {
        return resourceBase;
    }

    /**
     * @return
     */
    public SResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    /**
     * @param string
     */
    public void setResourceBase(String string) {
        resourceBase = string;
    }

    /**
     * @param bundle
     */
    public void setResourceBundle(SResourceBundle bundle) {
        resourceBundle = bundle;
    }
}