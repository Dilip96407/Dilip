package de.westlb.mgb.client.ui.selection_list;

import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * Selection list of bloomberg currency codes.
 *
 * @author Manfred Boerner
 */
public class BloombergCurrencyCodeList extends AbstractSelectionList {

    /**
     * Creates a new Bloomberg currency code list.
     */
    public BloombergCurrencyCodeList() {
        super();
    }
    
    public static void setTableProvider(DictionaryProvider provider) {
        setTableProvider(BloombergCurrencyCodeList.class, provider);
    }
}
