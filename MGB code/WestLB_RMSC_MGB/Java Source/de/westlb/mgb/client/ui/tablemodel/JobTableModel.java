package de.westlb.mgb.client.ui.tablemodel;

import java.util.Calendar;

import de.westlb.mgb.client.server.vo.JobVo;
import de.westlb.mgb.client.ui.selection_list.SourceSystemList;
import de.westlb.mgb.client.ui.util.ClientUtils;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb_systems.xaf.swing.SDataModel2;

/**
 * @author WSY4148
 *
 * Adapter to convert the employee search result returned
 * by the mgb service to a gui table model.
 * 
 * @deprecated
 */
public class JobTableModel extends AbstractTableModel {
	/** Column definitions */
	public static final int COLUMN_ID					= 0;
	public static final int COLUMN_SYSTEM_TIME			= 1;
	public static final int COLUMN_COB                  = 2;
	public static final int COLUMN_START_BUSINESS_TIME	= 3;
	public static final int COLUMN_STOP_BUSINESS_TIME	= 4;
	public static final int COLUMN_STATUS				= 5;
	public static final int COLUMN_SOURCE_SYSTEM		= 6;
	public static final int COLUMN_START_LOAD_TIME		= 7;
	public static final int COLUMN_STOP_LOAD_TIME		= 8;
	public static final int COLUMN_START_CONVERT_TIME	= 9;
	public static final int COLUMN_STOP_CONVERT_TIME	= 10;
	public static final int COLUMN_NUMBER_OF_TOTAL_RECORDS	= 11;
	public static final int COLUMN_NUMBER_OF_LOAD_ERRORS	= 12;
	public static final int COLUMN_NUMBER_OF_CONVERT_ERRORS	= 13;
		
	private static DateFormat timeFormat = new DateFormat(DateFormat.TIME_FORMAT_LONG);
	private static DateFormat dateFormat = new DateFormat(DateFormat.DATE_FORMAT);
	private static SourceSystemList sourceSystemList = new SourceSystemList();
	
	private String[] headerKeys = new String[]{
		"jobId",
		"systemTime",		
		"cob",
		"startBusinessTime",
		"stopBusinessTime",
		"status",
		"sourceSystemCode",
		"startLoadTime",
		"stopLoadTime",
		"startConvertTime",
		"stopConvertTime",
		"numberOfTotalRecords",
		"numberOfLoadErrors",
		"numberOfConvertErrors",
	};

	private String[] propertyNames = new String[] {
		"jobId",
		"systemTime",		
		"cob",
		"startBusinessTime",
		"stopBusinessTime",
		"status",	
		"sourceSystemCode",
		"startLoadTime",
		"stopLoadTime",
		"startConvertTime",
		"stopConvertTime",
		"numberOfTotalRecords",
		"numberOfLoadErrors",
		"numberOfConvertErrors",
	};
	
    /**
     * Constructor for AutomaticStateTableModel.
     */
    public JobTableModel() {
        super();

        setHeaderKeys(headerKeys);
        setPropertyNames(propertyNames);
    }
    
        /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
    	Object value = 	super.getValueAt(row, column);
    	
		switch (getColumnIndexInModel(column)) {
			case COLUMN_SOURCE_SYSTEM:
				value = sourceSystemList.get(value);
				break;
			case COLUMN_COB:
				if (value instanceof Calendar) {
				value = dateFormat.format(value);
				}
			break;
			case COLUMN_START_BUSINESS_TIME:
			case COLUMN_STOP_BUSINESS_TIME:
			case COLUMN_SYSTEM_TIME:
			case COLUMN_START_CONVERT_TIME:
			case COLUMN_STOP_CONVERT_TIME:
			case COLUMN_START_LOAD_TIME:
			case COLUMN_STOP_LOAD_TIME:
					if (value instanceof Calendar) {
					value = timeFormat.format(value);
				}
				break;
		}

        return value;
    }
    
    public String getNameForJob(int row) {
		return ClientUtils.getNameForJob(getJobVo(row));  	
    }
    
    public JobVo getJobVo(int row) {
		return (JobVo)(getDataArray()[row]);
    }
    
    /**
	 * @see de.westlb_systems.xaf.swing.SDataModel2#getColumnType(int)
	 */
	@Override
    public int getColumnType(int column) {
		int columnType;
    	
		switch (getColumnIndexInModel(column)) {
			case COLUMN_ID:
			case COLUMN_NUMBER_OF_TOTAL_RECORDS:
			case COLUMN_NUMBER_OF_LOAD_ERRORS:
			case COLUMN_NUMBER_OF_CONVERT_ERRORS:
				// Right aligment
				columnType = SDataModel2.NUMBER;
				break;
			default:
				columnType = super.getColumnType(column);
		}
		return columnType;
	}
}
