package de.westlb.mgb.client.ui.selection_list;

import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * Type comment.
 *
 * @author Manfred Boerner
 */
public class AllSourceSystemList extends AbstractSelectionList {
    /**
     * Creates a new SourceSystemList
     */
    public AllSourceSystemList() {
        super();
    }
    public static void setTableProvider(DictionaryProvider provider) {
        setTableProvider(AllSourceSystemList.class, provider);
    }
}
