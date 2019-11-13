package de.westlb.mgb.client.ui.selection_list;

import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * Selection list for state types (reclamation, manual, ...)
 *
 * @author Manfred Boerner
 */
public class StateTypeList extends AbstractSelectionList {
    /**
     * Creates a new StateTypelist
     */
    public StateTypeList() {
        super();
    }
    
    /**
     * Method setTableProvider.
     * @param provider
     */
    public static void setTableProvider(DictionaryProvider provider) {
        setTableProvider(StateTypeList.class, provider);
    }
}
