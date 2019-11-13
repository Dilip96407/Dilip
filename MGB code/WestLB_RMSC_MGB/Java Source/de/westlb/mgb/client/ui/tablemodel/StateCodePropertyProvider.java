/*
 * Created on Aug 31, 2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.client.ui.tablemodel;

import de.westlb.mgb.client.server.vo.StateCodeVo;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb_systems.xaf.swing.SIcon;
import de.westlb_systems.xaf.ui.misc.IconKatalog;

/**
 * @author d055625
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class StateCodePropertyProvider implements AbstractPropertyProvider {

	private static SIcon icon_automatic	= IconKatalog.getInstance().getIcon("STATE_AUTOMATIC");
	private static SIcon icon_manual		= IconKatalog.getInstance().getIcon("STATE_MANUAL");
	private static SIcon icon_sample		= IconKatalog.getInstance().getIcon("STATE_SAMPLE");
	private static SIcon icon_reclamation	= IconKatalog.getInstance().getIcon("STATE_RECLAMATION");

    static {
        icon_automatic.setIconText("Automatic");
        icon_manual.setIconText("Manual");
        icon_sample.setIconText("Sample");
        icon_reclamation.setIconText("Reclamation");
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see de.westlb.mgb.client.ui.tablemodel.AbstractPropertyProvider#getValueAt(int,
     *      int, java.lang.Object)
     */
    @Override
    public Object getValueAt(int row, int col, Object businessObject) {
        if (businessObject instanceof StateCodeVo) {
			if (StateCodeTypeDef.AUTO.equals(((StateCodeVo) businessObject).getType())) {
			    return icon_automatic;
			} else if (StateCodeTypeDef.MANUAL.equals(((StateCodeVo) businessObject).getType())) {
			    return icon_manual;
			} else if (StateCodeTypeDef.SAMPLE.equals(((StateCodeVo) businessObject).getType())) {
			    return icon_sample;
			} else {
			    return icon_reclamation;
			}
        }
        return null;
    }
}