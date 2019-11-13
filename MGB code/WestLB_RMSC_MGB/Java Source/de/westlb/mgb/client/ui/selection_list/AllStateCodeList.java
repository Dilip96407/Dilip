package de.westlb.mgb.client.ui.selection_list;

import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * Selection list for state codes used by automatic checking.
 *
 * @author Manfred Boerner
 */
public class AllStateCodeList extends StateCodeList {
    /**
     * Creates a new SourceSystemList
     */
    public AllStateCodeList() {
        super();
    }
    
    public static void setTableProvider(DictionaryProvider provider) {
        setTableProvider(AllStateCodeList.class, provider);
    }
    
}