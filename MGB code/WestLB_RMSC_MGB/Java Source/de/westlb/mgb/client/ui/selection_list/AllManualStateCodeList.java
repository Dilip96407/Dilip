package de.westlb.mgb.client.ui.selection_list;

import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * Selection list for state codes used by automatic checking.
 *
 * @author Manfred Boerner
 */
public class AllManualStateCodeList extends StateCodeList {
    /**
     * Creates a new SourceSystemList
     */
    public AllManualStateCodeList() {
        super();
    }
    
    public static void setTableProvider(DictionaryProvider provider) {
        setTableProvider(AllManualStateCodeList.class, provider);
    }
}