package de.westlb.mgb.client.ui.tablemodel;

import de.westlb.mgb.client.server.vo.StateCodeVo;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb_systems.xaf.swing.SIcon;
import de.westlb_systems.xaf.ui.misc.IconKatalog;

/**
 * @author WSY4148
 *
 * Adapter to convert the employee search result returned
 * by the mgb service to a gui table model.
 * 
 * @deprecated
 */
public class StateCodeTableModel extends AbstractTableModel {
	/** Column definitions */
	public static final int COLUMN_TYPE_AS_ICON					= 0;
	public static final int COLUMN_STATE_CODE					= 1;
	public static final int COLUMN_SHORT_DESCRIPTION				= 2;
	public static final int COLUMN_MANUAL_CHECK_REQUIRED			= 3;
	public static final int COLUMN_RECLAMATION_CHECK_REQUIRED	= 4;
	
	private static SIcon icon_automatic	= IconKatalog.getInstance().getIcon("STATE_AUTOMATIC");
	private static SIcon icon_manual		= IconKatalog.getInstance().getIcon("STATE_MANUAL");
	private static SIcon icon_reclamation	= IconKatalog.getInstance().getIcon("STATE_RECLAMATION");
	
	private String[] headerKeys = new String[]{
		"typeAsIcon",
		"stateCode",		
		"shortDescription",
		"manualCheckRequired",
		"reclamationRequired",
	};

	private String[] propertyNames = new String[] {
		"type",
		"stateCode",
		"shortDescription",
		"manualCheckRequired",
		"reclamationRequired",
	};
	
    /**
     * Constructor for AutomaticStateTableModel.
     */
    public StateCodeTableModel() {
        super();

        setHeaderKeys(headerKeys);
        setPropertyNames(propertyNames);
    }
    
    private StateCodeVo getStateCodeVo(int row) {
    	return ((StateCodeVo)getDataArray()[row]);
    }
    
    /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
    	Object value = null;
		switch (column) {
        	case COLUMN_TYPE_AS_ICON:
        			if (StateCodeTypeDef.AUTO.equals(getStateCodeVo(row).getType())) {
        				value = icon_automatic;
        			} else if (StateCodeTypeDef.MANUAL.equals(getStateCodeVo(row).getType())) {
        				value = icon_manual;
        			} else if (StateCodeTypeDef.SAMPLE.equals(getStateCodeVo(row).getType())) {
        				value = icon_manual;
        			} else {
        				value = icon_reclamation;
        			}
        		break;
        
        	default:
        			value = super.getValueAt(row, column);
        		break;
        }    	
    	return  value;
    }

    /**
     * @see de.westlb_systems.xaf.swing.SDataModel2#getColumnType(int)
     */
    @Override
    public int getColumnType(int column) {
    	int columnType = DEFAULT;
    	
    	switch (column) {
        	case COLUMN_TYPE_AS_ICON:
        			columnType = ICON;
        		break;
        	case COLUMN_MANUAL_CHECK_REQUIRED:
        		columnType = BOOLEAN;
        		break;
        	case COLUMN_RECLAMATION_CHECK_REQUIRED:
        		columnType = BOOLEAN;
        		break;        		
        	default:
        		columnType = super.getColumnType(column);
        }
        return columnType;
    }

    /**
     * @see de.westlb_systems.xaf.swing.SSortableDataModel#compareRows(int, int, int)
     */
    @Override
    public int compareRows(int first, int second, int column) {
    	int value = 0;
    	switch (column) {
        	case COLUMN_TYPE_AS_ICON:
        			String firstType = getStateCodeVo(first).getType();
        			String secondType = getStateCodeVo(second).getType();
        			value = firstType.compareTo(secondType);
        		break;
        
        	default:
        		value = super.compareRows(first, second, column);
        }
        return value;
    }

}
