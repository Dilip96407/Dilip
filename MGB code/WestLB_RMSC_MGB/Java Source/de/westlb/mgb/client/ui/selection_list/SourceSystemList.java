package de.westlb.mgb.client.ui.selection_list;

import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * Type comment.
 *
 * @author Manfred Boerner
 */
public class SourceSystemList extends AbstractSelectionList {
    /**
     * Creates a new SourceSystemList
     */
    public SourceSystemList() {
        super();
    }
    public static void setTableProvider(DictionaryProvider provider) {
        setTableProvider(SourceSystemList.class, provider);
    }
}
