package de.westlb.mgb.client.ui.selection_list;

import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * Type comment.
 *
 * @author Manfred Boerner
 */
public class DualControlJobOperationList extends AbstractSelectionList {
    /**
     * Creates a new SourceSystemList
     */
    public DualControlJobOperationList() {
        super();
    }
    public static void setTableProvider(DictionaryProvider provider) {
        setTableProvider(DualControlJobOperationList.class, provider);
    }
}
