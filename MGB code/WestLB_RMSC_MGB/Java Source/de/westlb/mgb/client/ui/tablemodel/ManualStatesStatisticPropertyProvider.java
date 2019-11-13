/*
 * Created on Aug 31, 2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.client.ui.tablemodel;

import de.westlb.mgb.client.server.vo.StateStatisticEntryVo;
import de.westlb.mgb.client.ui.selection_list.AllManualStateCodeList;
import de.westlb.mgb.model.definition.StateCodeDef;

/**
 * @author d055625
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class ManualStatesStatisticPropertyProvider implements AbstractPropertyProvider {

	private AllManualStateCodeList stateCodes = new AllManualStateCodeList();

    /*
     * (non-Javadoc)
     * 
     * @see de.westlb.mgb.client.ui.tablemodel.AbstractPropertyProvider#getValueAt(int,
     *      int, java.lang.Object)
     */
    @Override
    public Object getValueAt(int row, int col, Object businessObject) {
        if (businessObject instanceof StateStatisticEntryVo) {
            String stateCode = ((StateStatisticEntryVo)businessObject).getState();
			if (StateCodeDef.MAN_REQUIRED_BUT_NOT_HANDLED_YET.equals(stateCode)) {
				return "No manual state set yet.";
			}
            return stateCodes.get(stateCode);
        }
        return null;
    }
}