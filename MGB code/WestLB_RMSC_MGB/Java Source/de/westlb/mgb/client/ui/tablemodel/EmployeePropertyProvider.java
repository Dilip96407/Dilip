/*
 * Created on Aug 31, 2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.client.ui.tablemodel;

import de.westlb.mgb.client.server.vo.EmployeeSearchResultEntryVo;
import de.westlb_systems.xaf.swing.SIcon;
import de.westlb_systems.xaf.ui.misc.IconKatalog;

/**
 * @author d055625
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class EmployeePropertyProvider implements AbstractPropertyProvider {

    private static SIcon icon_controller = IconKatalog.getInstance().getIcon("EMPLOYEE_CONTROLLER");
    private static SIcon icon_trader = IconKatalog.getInstance().getIcon("EMPLOYEE_TRADER");

    static {
        icon_controller.setIconText("Controller");
        icon_trader.setIconText("Trader");
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see de.westlb.mgb.client.ui.tablemodel.AbstractPropertyProvider#getValueAt(int,
     *      int, java.lang.Object)
     */
    @Override
    public Object getValueAt(int row, int col, Object businessObject) {
        if (businessObject instanceof EmployeeSearchResultEntryVo) {
            if (((EmployeeSearchResultEntryVo) businessObject).isController()) {
                return icon_controller;
            } else if (((EmployeeSearchResultEntryVo) businessObject).isTrader()) {
                return icon_trader;
            }
        }
        return null;
    }
}