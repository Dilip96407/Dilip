package de.westlb.mgb.client.ui.selection_list;

import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * Selection list for state codes used for reclamations.
 *
 * @author Manfred Boerner
 */
public class PriceCheckCategoryList extends AbstractSelectionList {
    /**
     * Creates a new ReclamationStateCodeList
     */
    public PriceCheckCategoryList() {
        super();
    }
    
    /**
     * Method setTableProvider.
     * @param provider
     */
    public static void setTableProvider(DictionaryProvider provider) {
        setTableProvider(PriceCheckCategoryList.class, provider);
    }
}
