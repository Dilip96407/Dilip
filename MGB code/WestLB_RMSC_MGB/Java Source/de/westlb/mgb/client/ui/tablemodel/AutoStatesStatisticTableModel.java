package de.westlb.mgb.client.ui.tablemodel;

import de.westlb.mgb.client.ui.selection_list.AutoStateCodeList;
import de.westlb_systems.xaf.swing.SDataModel2;

/**
 * @author WSY4148
 *
 * @deprecated
 */
public class AutoStatesStatisticTableModel extends AbstractTableModel {
	/** Column definitions */
	public static final int STATE					= 0;
	public static final int COUNT					= 1;
	public static final int MANUAL_CHECK_REQUIRED	= 2;
	public static final int SEND_RECLAMATION			= 3;
	
	private AutoStateCodeList stateCodes = new AutoStateCodeList();

	private String[] headerKeys = new String[]{
		"STATE",
		"COUNT",
		"MANUAL_CHECK_REQUIRED",
		"SEND_RECLAMATION",
	};
	
	private String[] propertyNames = new String[] {
		"state",
		"count",
		"manualCheckRequired",
		"reclamationRequired",
	};
	
	
    /**
     * Constructor for AutomaticStateTableModel.
     */
    public AutoStatesStatisticTableModel() {
        super();
        
        setHeaderKeys(headerKeys);
        setPropertyNames(propertyNames);
    }
    
	/**
	 * @see de.westlb_systems.xaf.swing.SDataModel2#getColumnType(int)
	 */
	@Override
    public int getColumnType(int column) {
		int columnType;
    	
		switch (getColumnIndexInModel(column)) {
			case COUNT:
				// Right aligment
				columnType = SDataModel2.NUMBER;
				break;
			default:
				columnType = super.getColumnType(column);
		}
		return columnType;
	}
    
    /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
    	Object value =  super.getValueAt(row, column);
	
		switch(column) {
			case STATE:
				value = stateCodes.get(value);
				break;
		}

        return value;
    }


}
