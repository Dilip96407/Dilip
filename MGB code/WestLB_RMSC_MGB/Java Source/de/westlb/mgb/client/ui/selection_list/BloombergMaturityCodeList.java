package de.westlb.mgb.client.ui.selection_list;

import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * Selection list of bloomberg maturity codes.
 *
 * @author Manfred Boerner
 */
public class BloombergMaturityCodeList extends AbstractSelectionList {
    /**
     * Creates a new Bloomberg maturity code list.
     */
    public BloombergMaturityCodeList() {
        super();
    }
    
    public static void setTableProvider(DictionaryProvider provider) {
        setTableProvider(BloombergMaturityCodeList.class, provider);
    }
}
