package de.westlb.mgb.client.ui.tablemodel;

import de.westlb.mgb.client.ui.selection_list.SourceSystemList;

/**
 * @author WSY4148
 *
 * Wraps an array of TraderCodeVo's into a gui table model.
 * 
 * @deprecated
 */
public class TraderTableModel extends AbstractTableModel {
	/** Column definitions */
	public static final int TRADER_CODE				= 0;
	public static final int TRADER_NAME				= 1;
	public static final int SOURCE_SYSTEM			= 2;
	
	public SourceSystemList sourceSystemList = new SourceSystemList();

	private String[] headerKeys = new String[]{
		"TRADER_CODE",
		"TRADER_NAME",
		"SOURCE_SYSTEM",
	};

	private String[] propertyNames = new String[] {
		"traderCode",
		"traderName",
		"sourceSystemCode",
	};
	
    /**
     * Constructor for AutomaticStateTableModel.
     */
    public TraderTableModel() {
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
			case SOURCE_SYSTEM:
					value = sourceSystemList.get(value);
					break;
			default:
					break;
		}

        return value;
    }

}
