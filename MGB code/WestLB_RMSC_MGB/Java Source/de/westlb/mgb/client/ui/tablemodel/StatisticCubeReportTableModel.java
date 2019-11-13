package de.westlb.mgb.client.ui.tablemodel;

import java.util.Calendar;
import java.util.Enumeration;

import de.westlb.mgb.client.mask.model.shared.StatisticListModel;
import de.westlb.mgb.client.server.vo.StatisticCubeReportVo;
import de.westlb.mgb.client.server.vo.StatisticCubeRowVo;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.model.impl.statistic.StatisticCubeReportFactoryImpl;
import de.westlb_systems.xaf.swing.SDataModel2;
import de.westlb_systems.xaf.swing.SDataModelListener;
import de.westlb_systems.xaf.util.SResourceBundle;

/**
 * The table model used by the "CUBE" type statistics report.
 * 
 * @see StatisticCubeReportFactoryImpl
 */
public class StatisticCubeReportTableModel implements SDataModel2
{
    private SResourceBundle resourceBundle = null;

    private String resourceBase = null;

    private DateFormat timeFormat;

    private StatisticCubeReportVo statisticCubeReportVo;

    private int idxKeyCols;

    private int cntKeyCols;

    private int idxAddKeyCols;

    private int cntAddKeyCols;

    private int idxStateCode;
    
    private int idxTotal;
    
    private int idxManualCheckRequired;

    public StatisticCubeReportTableModel(StatisticCubeReportVo statisticCubeReportVo)
    {
        timeFormat = new DateFormat(DateFormat.TIME_FORMAT);
        this.statisticCubeReportVo = statisticCubeReportVo;
        idxKeyCols = 0;
        cntKeyCols = statisticCubeReportVo.getKeyColumnNames() == null ? 0
                : statisticCubeReportVo.getKeyColumnNames().length;

        idxAddKeyCols = idxKeyCols + cntKeyCols;
        cntAddKeyCols = statisticCubeReportVo.getAddKeyColumnNames() == null ? 0
                : statisticCubeReportVo.getAddKeyColumnNames().length;

        idxManualCheckRequired = idxAddKeyCols + cntAddKeyCols;
        idxStateCode = idxManualCheckRequired + 1;
        idxTotal = idxStateCode + 1;
    }

    @Override
    public void addSDataModelListener(SDataModelListener listener)
    {
    }

    @Override
    public int getColumnType(int column)
    {
        int colType;
        if(idxTotal == column)
        {
            colType = SDataModel2.NUMBER;
        }
        /* we're currently using the String representation of the
         * manualCheckRequired flag (more consistent with the state column and
         * the old SQL report) */
//        else if(idxManualCheckRequired == column)
//        {
//            colType = SDataModel2.BOOLEAN;
//        }
        else
        {
            colType = 0;
        }
        return colType;
    }

    @Override
    public void removeSDataModelListener(SDataModelListener listener)
    {
    }

    @Override
    public int getColumnCount()
    {
        return idxTotal+1;
    }

    @Override
    public String getColumnName(int column)
    {
        String colName = "";
        if(idxKeyCols <= column && column < idxKeyCols + cntKeyCols)
        {
            String key = StatisticListModel.REPORT_PREFIX
                    + statisticCubeReportVo.getName()
                    + "_COLUMN_"
                    + statisticCubeReportVo.getKeyColumnNames()[column - idxKeyCols];
            colName = getResourceOrEmptyString(key);
        }
        else if(idxAddKeyCols <= column
                && column < idxAddKeyCols + cntAddKeyCols)
        {
            String key = StatisticListModel.REPORT_PREFIX
                    + statisticCubeReportVo.getName()
                    + "_COLUMN_"
                    + statisticCubeReportVo.getAddKeyColumnNames()[column
                            - idxAddKeyCols];
            colName = getResourceOrEmptyString(key);
        }
        else if(idxTotal == column)
        {
            colName = getResourceString("COLUMN_NO_TRADES_TOTAL");
        }
        else if(idxStateCode == column)
        {
            colName = getResourceString("COLUMN_STATE_CODE");
        }
        else if(idxManualCheckRequired == column)
        {
            colName = getResourceString("COLUMN_MANUAL_CHECK_REQUIRED");
        }
        return colName;
    }

    @Override
    public int getRowCount()
    {
        return statisticCubeReportVo.getRows().length;
    }

    @Override
    public Object getValueAt(int row,int column)
    {
        Object value = null;
        StatisticCubeRowVo rowVo = statisticCubeReportVo.getRows()[row];
        if(idxKeyCols <= column && column < idxKeyCols + cntKeyCols)
        {
            value = rowVo.getKeyValues()[column - idxKeyCols];
        }
        else if(idxAddKeyCols <= column
                && column < idxAddKeyCols + cntAddKeyCols)
        {
            value = rowVo.getAddKeyValues()[column - idxAddKeyCols];
        }
        else if(idxTotal == column)
        {
            value = Long.valueOf(rowVo.getNoTradesTotal());
        }
        else if(idxStateCode == column)
        {
            value = rowVo.getState();
        }
        else if(idxManualCheckRequired==column)
        {
//            value=rowVo.getManualCheckRequired();
            value=rowVo.getManualCheckRequiredStr();
        }

        if(value instanceof Calendar)
        {
            value = timeFormat.format(value);
        }
        return value;
    }

    @Override
    @SuppressWarnings({ "unchecked","rawtypes" })
    public int compareRows(int first,int second,int column)
    {
        Object value1 = getValueAt(first,column);
        Object value2 = getValueAt(second,column);
        return value1 instanceof Comparable ? ((Comparable)value1)
                .compareTo(value2) : 0;
    }

    /**
     * Returns the resource string for a key
     *
     * <p>
     * The key is calculated as <code>resourceBase+Key</code>
     * </p>
     * 
     */
    public String getResourceString(String key)
    {
        if(getResourceBundle() != null)
        {
            if(getResourceBase() != null)
            {
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
        String bkey = key;
        if(getResourceBase() != null)
        {
            bkey = getResourceBase() + key;
        }
        for(Enumeration keys = getResourceBundle().getKeys();keys
                .hasMoreElements();)
        {
            if(keys.nextElement().equals(bkey)) return getResourceString(key);
        }
        return "";
    }

    /**
     */
    public String getResourceBase()
    {
        return resourceBase;
    }

    /**
     */
    public SResourceBundle getResourceBundle()
    {
        return resourceBundle;
    }

    /**
     */
    public void setResourceBase(String string)
    {
        resourceBase = string;
    }

    /**
     */
    public void setResourceBundle(SResourceBundle bundle)
    {
        resourceBundle = bundle;
    }
}