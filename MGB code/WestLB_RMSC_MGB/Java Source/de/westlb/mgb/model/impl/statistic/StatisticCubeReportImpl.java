package de.westlb.mgb.model.impl.statistic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

/**
 */
public class StatisticCubeReportImpl
{
    private Collection<StatisticCubeRowImpl> rows = new ArrayList<StatisticCubeRowImpl>();

    private String name;

    private String[] successorNames;

    private StatisticContextImpl[] context;

    private String[] keyColumnNames;

    private String[] addKeyColumnNames;

    private Calendar fromDate;

    private Calendar toDate;

    /**
     */
    public Collection<StatisticCubeRowImpl> getRows()
    {
        return rows;
    }

    /**
     */
    public void addAllRows(Collection<StatisticCubeRowImpl> rows)
    {
        Iterator<StatisticCubeRowImpl> iterator = rows.iterator();
        while(iterator.hasNext())
        {
            addRow(iterator.next());
        }
    }

    private void addRow(StatisticCubeRowImpl row)
    {
        rows.add(row);
    }

    /**
     */
    public String getName()
    {
        return name;
    }

    /**
     */
    public void setName(String string)
    {
        name = string;
    }

    /**
     */
    public String[] getAddKeyColumnNames()
    {
        return addKeyColumnNames;
    }

    /**
     */
    public String[] getKeyColumnNames()
    {
        return keyColumnNames;
    }

    /**
     */
    public void setAddKeyColumnNames(String[] strings)
    {
        addKeyColumnNames = strings;
    }

    /**
     */
    public void setKeyColumnNames(String[] strings)
    {
        keyColumnNames = strings;
    }

    /**
     */
    public StatisticContextImpl[] getContext()
    {
        return context;
    }

    /**
     */
    public void setContext(StatisticContextImpl[] impls)
    {
        context = impls;
    }

    /**
     */
    public Calendar getFromDate()
    {
        return fromDate;
    }

    /**
     */
    public Calendar getToDate()
    {
        return toDate;
    }

    /**
     */
    public void setFromDate(Calendar calendar)
    {
        fromDate = calendar;
    }

    /**
     */
    public void setToDate(Calendar calendar)
    {
        toDate = calendar;
    }

    /**
	 */
    public String[] getSuccessorNames()
    {
        return successorNames;
    }

    /**
	 */
    public void setSuccessorNames(String[] strings)
    {
        successorNames = strings;
    }

}