package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.StatisticContextVo;
import de.westlb.mgb.client.server.vo.StatisticCubeReportVo;
import de.westlb.mgb.client.server.vo.StatisticReportParamsVo;
import de.westlb.mgb.client.server.vo.StatisticReportVo;
import de.westlb.mgb.client.server.vo.StatisticReportVoBase;
import de.westlb.mgb.client.ui.tablemodel.StatisticCubeReportTableModel;
import de.westlb.mgb.client.ui.tablemodel.StatisticReportTableModel;

/**
 * Gui model for the visualization of a mgb statistic report.
 *
 * @author Manfred Boerner
 */

public class StatisticListModel extends AbstractModel {

    public static final int TABLE_FORMAT_REGULAR       = 1;
    public static final int TABLE_FORMAT_CUBE          = 2;
    
	public static final String P_STATISTIC_TABLE_MODEL = "statisticTableModel";
	public static final String REPORT_PREFIX = "REPORT_";
	private String RESOURCE_BASE = getResourceBase();
	private final String[] propertyNames = new String[] { P_STATISTIC_TABLE_MODEL, };
	
	private StatisticReportVoBase statisticReportVo = null;
	private StatisticReportParamsVo statisticReportParamsVo = null;

    private int tableFormat = TABLE_FORMAT_REGULAR;

    /**
	 * Default constructor to create an empty model
	 */
	public StatisticListModel() {
		setPropertyNames(propertyNames);
	}

	public Object[] getKeyValues(int row) {
		if (statisticReportVo == null) return null;
		if(tableFormat == TABLE_FORMAT_REGULAR)
		{
	        if (((StatisticReportVo)statisticReportVo).getRows().length <= row) return null;
	        return ((StatisticReportVo)statisticReportVo).getRows()[row].getKeyValues();
		}
		else
		{
	        if (((StatisticCubeReportVo)statisticReportVo).getRows().length <= row) return null;
	        return ((StatisticCubeReportVo)statisticReportVo).getRows()[row].getKeyValues();
		}
	}
	
	public Object[] getAddKeyValues(int row) {
        if (statisticReportVo == null) return null;
        if(tableFormat == TABLE_FORMAT_REGULAR)
        {
            if (((StatisticReportVo)statisticReportVo).getRows().length <= row) return null;
            return ((StatisticReportVo)statisticReportVo).getRows()[row].getAddKeyValues();
        }
        else
        {
            if (((StatisticCubeReportVo)statisticReportVo).getRows().length <= row) return null;
            return ((StatisticCubeReportVo)statisticReportVo).getRows()[row].getAddKeyValues();
        }
	}
	
	public String getFirstSuccessorReportName() {
		if (statisticReportVo == null || statisticReportVo.getSuccessorNames() == null || 
			statisticReportVo.getSuccessorNames().length < 1) {
			return null;
		}
		
	
		return statisticReportVo.getSuccessorNames()[0];
	}
	
	public StatisticReportParamsVo getSuccessorParams(int row, String reportName) {
		if (statisticReportVo == null) {
			return null;
		}
		StatisticReportParamsVo params = new StatisticReportParamsVo();
		params.setReportName(reportName);
		params.setFromDate(statisticReportVo.getFromDate());
		params.setToDate(statisticReportVo.getToDate());
		
		params.setContext(buildSuccessorContext(row));
		
		return params;		
	}
	
	/**
	 * Gets a list of all successor reports names.
	 * 
	 * @return list of successor report names.
	 */
	public String[] getSuccessorReportNames() {
		// Return all successor defined for this statistic report, but avoid
		// looping. This may happen because the successor is defined by a link
		// to an other report def.
		ArrayList<String> result = new ArrayList<String>();
		String[] successorNames = statisticReportVo.getSuccessorNames();
		for (int i = 0; i < successorNames.length; i++) {
			if (!isPredecessorReport(successorNames[i])) {
				result.add(successorNames[i]);
			}
		}
		return result.toArray(new String[result.size()]);
	}
	
	public boolean isPredecessorReport(String reportName) {
		StatisticContextVo[] contextArray = statisticReportVo.getContext();
		if (contextArray != null) {
			for (int i=0; i < contextArray.length; i++) {
				if (reportName.equals(contextArray[i].getReportName())) {
					return true;			
				}
			}
		}
		
		return false;
	}
	
	private StatisticContextVo[] buildSuccessorContext(int row) {
		StatisticContextVo[] currentContext = statisticReportVo.getContext();
		if (currentContext == null) {
			currentContext = new StatisticContextVo[0];
		}
		
		StatisticContextVo[] context = new StatisticContextVo[currentContext.length+1];
		for (int i=0; i < currentContext.length; i++) {
			context[i] = currentContext[i];
		}
		StatisticContextVo contextEntry = new StatisticContextVo();
		contextEntry.setReportName(statisticReportVo.getName());
		contextEntry.setKeyValues(getKeyValues(row));
		contextEntry.setAddKeyValues(getAddKeyValues(row));
		context[context.length-1] = contextEntry;
		
		return context;
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
        tableFormat = getTableFormatFromSession();
        statisticReportVo = null;
        
        if (getTableFormat() == TABLE_FORMAT_REGULAR) {
            try {
                statisticReportVo = MgbServiceFactory.getService().getStatisticReport(statisticReportParamsVo);
            } catch (RemoteException e) {
                handleRemoteException(e);
                return;
            }

            StatisticReportTableModel tableModel = new StatisticReportTableModel((StatisticReportVo)statisticReportVo);
            tableModel.setResourceBundle(getResourceBundle());
            tableModel.setResourceBase(RESOURCE_BASE);

            setProperty(P_STATISTIC_TABLE_MODEL, tableModel);
        } else {
            try {
                statisticReportVo = MgbServiceFactory.getService().getStatisticCubeReport(statisticReportParamsVo);
            } catch (RemoteException e) {
                handleRemoteException(e);
                return;
            }

            StatisticCubeReportTableModel tableModel = new StatisticCubeReportTableModel((StatisticCubeReportVo)statisticReportVo);
            tableModel.setResourceBundle(getResourceBundle());
            tableModel.setResourceBase(RESOURCE_BASE);

            setProperty(P_STATISTIC_TABLE_MODEL, tableModel);
        }
        
		return;
	}
	
	
	/**
	 * Sets the business object where the model gets its data from.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		if (!(newBusinessObject instanceof StatisticReportParamsVo)) {
			throw new IllegalArgumentException("Businessobject of StatisticListModel must be type StatisticReportParamsVo");
		}
		statisticReportParamsVo = (StatisticReportParamsVo)newBusinessObject;
		super.setBusinessObject(newBusinessObject);
		fillModel();
	}

	@Override
    public void reload() {
		fillModel();
	}

//    /**
//     * @return
//     */
//    public StatisticReportVo getStatisticReportVo() {
//        return statisticReportVo.reportVo;
//    }
    

    /* (Kein Javadoc)
     * @see de.westlb_systems.xaf.ui.model.Model#getTitle()
     */
    @Override
    public String getTitle() {
    	if (statisticReportVo == null) {
    		return "Statistic";
    	}
		String reportName = getResourceString(RESOURCE_BASE + REPORT_PREFIX + statisticReportVo.getName()); 
		String period = statisticReportVo.getFromDate() == null ? "*" : dateFormat.format(statisticReportVo.getFromDate());
		period = period + " - ";
		period = period + (statisticReportVo.getToDate() == null ? "*" : dateFormat.format(statisticReportVo.getToDate()));    	
		String context = "";
		if (statisticReportVo.getContext() != null && statisticReportVo.getContext().length > 0) {
			for (int i=0; i < statisticReportVo.getContext().length; i++) {
				context += ", ";
				Object[] addKeyValues = statisticReportVo.getContext()[i].getAddKeyValues();
				Object[] keyValues = statisticReportVo.getContext()[i].getKeyValues();
				Object[] values;
				if ( addKeyValues != null &&  addKeyValues.length > 0) {
					values = addKeyValues;
				} else {
					values = keyValues;
				}
				String value = "";
				for (int j=0; j < values.length; j++) {
					if (values[j] instanceof Calendar) {
						dateFormat.format(values[j]);
					} else if (values[j] != null){
					    value += values[j];
					}
					if (j != values.length -1 && value.length() > 0 ) {
					    value += " ";
					}
				}
				if (value.length() == 0) {
				    value = "NO VALUE"; 
				}
				context += value;
			}
		}
  	
     	String template = getResourceString(RESOURCE_BASE + "TITLE");
    	String title = StringUtils.replace(template, "<ReportName>", reportName);
		title = StringUtils.replace(title, "<Period>", period);		
		title = StringUtils.replace(title, "<Context>", context);		
    	
    	return title;
    }

    public int getTableFormat() {
        return tableFormat;
    }

    public int getTableFormatFromSession() {
        try {
            Object value = MgbServiceFactory.getService().getClientConfig("STATISTIC_TABLE_FORMAT");
            if (value != null && value instanceof Integer) {
                return ((Integer)value).intValue();
            }
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
        return TABLE_FORMAT_REGULAR;
    }

    /**
     * @param i
     */
    public void setTableFormat(int newTableFormat) {
        tableFormat = newTableFormat;
        try {
            MgbServiceFactory.getService().setClientConfig("STATISTIC_TABLE_FORMAT", Integer.valueOf(newTableFormat));
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
        reload();
    }
    
}
