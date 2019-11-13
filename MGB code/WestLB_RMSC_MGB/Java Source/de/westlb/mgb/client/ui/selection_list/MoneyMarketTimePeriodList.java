package de.westlb.mgb.client.ui.selection_list;

import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * Type comment.
 *
 * @author Manfred Boerner
 */
public class MoneyMarketTimePeriodList extends AbstractSelectionList {
    /**
     * Creates a new SourceSystemList
     */
    public MoneyMarketTimePeriodList() {
        super();
    }
    public static void setTableProvider(DictionaryProvider provider) {
        setTableProvider(MoneyMarketTimePeriodList.class, provider);
    }
}
