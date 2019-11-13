package de.westlb.mgb.client.ui.selection_list;

import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * Type comment.
 *
 * @author Manfred Boerner
 */
public class MailTypeList extends AbstractSelectionList {
    /**
     * Creates a new SourceSystemList
     */
    public MailTypeList() {
        super();
    }
    public static void setTableProvider(DictionaryProvider provider) {
        setTableProvider(MailTypeList.class, provider);
    }
}
