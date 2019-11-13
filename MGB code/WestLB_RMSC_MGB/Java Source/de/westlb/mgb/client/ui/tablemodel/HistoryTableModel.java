package de.westlb.mgb.client.ui.tablemodel;

import java.util.Calendar;

import de.westlb.mgb.client.server.vo.TradeHistoryEntryVo;
import de.westlb.mgb.client.ui.selection_list.AutoStateCodeList;
import de.westlb.mgb.client.ui.selection_list.ManualStateCodeList;
import de.westlb.mgb.client.ui.selection_list.ReclamationStateCodeList;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.client.ui.util.IconMapper;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb_systems.xaf.swing.SIcon;

/**
 * @author WSY4148
 *
 * @deprecated
 */
public class HistoryTableModel extends AbstractTableModel {
	private static final int MAX_COMMENT_LENGTH = 255;
	/** Column definitions */
	public static final int TYPE					= 0;
	public static final int DESCRIPTION				= 1;
	public static final int DATE					= 2;
	public static final int CREATED_BY_NAME			= 3;
	public static final int ATTACHMENT_TYP			= 4;
	public static final int COMMENT					= 5;

	private DateFormat timeFormat;
	private ManualStateCodeList manualStateCodes = new ManualStateCodeList();
	private AutoStateCodeList autoStateCodes = new AutoStateCodeList();
	private ReclamationStateCodeList reclamationStateCodes = new ReclamationStateCodeList();
	
	private String[] headerKeys = new String[]{
		"TYPE",
		"DESCRIPTION",
		"DATE",
		"CREATED_BY_NAME",
		"ATTACHMENT_TYPE",		
		"COMMENT",
	};

	private String[] propertyNames = new String[] {
		"type",
		"stateCode",
		"date",
		"createdByName",
		"attachmentName",
		"comment",
	};
	
    /**
     * Constructor for AutomaticStateTableModel.
     */
    public HistoryTableModel() {
        super();

		timeFormat = new DateFormat(DateFormat.TIME_FORMAT_LONG);        
        setHeaderKeys(headerKeys);
        setPropertyNames(propertyNames);
    }
    
    /**
     * @see de.westlb_systems.xaf.swing.SDataModel#getValueAt(int, int)
     */
    @Override
    public Object getValueAt(int row, int column) {
    	Object value =  super.getValueAt(row, column);
    	TradeHistoryEntryVo entry = (TradeHistoryEntryVo)(getDataArray()[row]);
    	
		switch(column) {
			case DATE:
				if (value instanceof Calendar) {
					value = timeFormat.format(value);
				}
				break;
			case DESCRIPTION:
				if (StateCodeTypeDef.AUTO.equals(entry.getType())) {
					value = autoStateCodes.get(value);
				} else if (StateCodeTypeDef.MANUAL.equals(entry.getType())) {
					value = manualStateCodes.get(value);
				} else if (StateCodeTypeDef.SAMPLE.equals(entry.getType())) {
					value = manualStateCodes.get(value);
				} else if (StateCodeTypeDef.RECLAMATION.equals(entry.getType())) {
					value = reclamationStateCodes.get(value);
				} 
				break;
			case ATTACHMENT_TYP:
				value = getAttachmentIcon(row);
				break;
			case COMMENT:
				String comment = (String)value;
				if (comment != null && comment.length() > MAX_COMMENT_LENGTH) {
					value = comment.substring(0, MAX_COMMENT_LENGTH - 3) + "...";
				} 
				break;
		}

        return value;
    }
    
    private SIcon getAttachmentIcon(int row) {
    	String fileName = (String) getUnformattedValueAt(row, ATTACHMENT_TYP);
    	return IconMapper.getInstance().getIconForFileName(fileName);
    }

}
