package de.westlb.mgb.client.ui.tablemodel;

import de.westlb.mgb.client.ui.selection_list.ManualStateCodeList;
import de.westlb.mgb.model.definition.StateCodeDef;
import de.westlb_systems.xaf.swing.SDataModel2;

/**
 * @author WSY4148
 *
 * @deprecated
 */
public class ManualStatesStatisticTableModel extends AbstractTableModel {
	/** Column definitions */
	public static final int STATE					= 0;
	public static final int COUNT					= 1;
	public static final int SEND_RECLAMATION			= 2;

	private ManualStateCodeList stateCodes = new ManualStateCodeList();

	private String[] headerKeys = new String[]{
		"STATE",
		"COUNT",
		"SEND_RECLAMATION",
	};
	
	private String[] propertyNames = new String[] {
		"state",
		"count",
		"reclamationRequired",
	};
	
	
    /**
     * Constructor for AutomaticStateTableModel.
     */
    public ManualStatesStatisticTableModel() {
        super();
        
        setHeaderKeys(headerKeys);
        setPropertyNames(propertyNames);
    }
    
    /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
    	Object value =  super.getValueAt(row, column);
	
		switch(column) {
			case STATE:
				if (StateCodeDef.MAN_REQUIRED_BUT_NOT_HANDLED_YET.equals(value)) {
					value = "No manual state set yet.";
				} else {
					value = stateCodes.get(value);
				}
				break;
		}

        return value;
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



}
