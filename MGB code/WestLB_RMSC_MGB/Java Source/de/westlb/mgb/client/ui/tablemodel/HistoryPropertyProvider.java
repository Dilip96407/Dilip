/*
 * Created on Aug 31, 2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.client.ui.tablemodel;

import de.westlb.mgb.client.server.vo.TradeHistoryEntryVo;
import de.westlb.mgb.client.ui.util.IconMapper;

/**
 * @author d055625
 * 
 * @deprecated
 */
public class HistoryPropertyProvider implements AbstractPropertyProvider {
    
    /*
     * (non-Javadoc)
     * 
     * @see de.westlb.mgb.client.ui.tablemodel.AbstractPropertyProvider#getValueAt(int,
     *      int, java.lang.Object)
     */
    @Override
    public Object getValueAt(int row, int col, Object businessObject) {
        if (businessObject instanceof TradeHistoryEntryVo) {
            String fileName = ((TradeHistoryEntryVo) businessObject).getAttachmentName();
           	return IconMapper.getInstance().getIconForFileName(fileName);
        }
        return null;
    }
    
}