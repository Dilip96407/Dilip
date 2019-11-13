package de.westlb.mgb.model.impl.statistic;

import de.westlb.mgb.model.impl.AutomaticStateImpl;

/**
 */
public class StatisticCubeRowImpl
{
    private Object[] keyValues = new Object[0];

    private Object[] addValues = new Object[0];
    
    public static String TOTAL="TOTAL";
    
    /**
     * The automatic or manual state code of a report row. This can have valid
     * empty values, when a manual state is required but has not been set yet.
     * (For now, an empty string is used in this case, as it's safer to display
     * in a sortable table than <code>null</code>) The report generator uses the
     * special value {@link #TOTAL} to indicate totals in this column.
     */
    private String state=null;
    
    /**
     * Whether the automatic state of a trade has the
     * {@link AutomaticStateImpl#isManualCheckRequired()} flag set. Since an
     * automatic state is always present, no valid NULL values can occur in the
     * database. <code>null</code> in this field is used to indicate totals. For
     * display in a text-based table column,
     * {@link #getManualCheckRequiredStr()} is can be used which converts
     * <code>null</code> values to something more descriptive.
     */
    private Boolean manualCheckRequired=null;
    
    private long noTradesTotal=0;

    /**
     * @return
     */
    public Object[] getKeyValues()
    {
        return keyValues;
    }

    /**
     * @param objects
     */
    public void setKeyValues(Object[] objects)
    {
        keyValues = objects;
    }

    /**
     * @return
     */
    public Object[] getAddValues()
    {
        return addValues;
    }

    /**@see #manualCheckRequired */
    public Boolean getManualCheckRequired()
    {
        return manualCheckRequired;
    }
    
    public String getManualCheckRequiredStr()
    {
        return manualCheckRequired==null?TOTAL:
               manualCheckRequired.booleanValue()?"Y":"N";
    }

    /**@see #manualCheckRequired */
    public void setManualCheckRequired(Boolean manualCheckRequired)
    {
        this.manualCheckRequired = manualCheckRequired;
    }

    /**
     * @param objects
     */
    public void setAddValues(Object[] objects)
    {
        addValues = objects;
    }
    
    /**@see #state */
    public String getState()
    {
        return state;
    }

    /**@see #state */
    public void setState(String state)
    {
        this.state = state;
    }

    public long getNoTradesTotal()
    {
        return noTradesTotal;
    }

    public void setNoTradesTotal(long rowsTotal)
    {
        this.noTradesTotal = rowsTotal;
    }

}
