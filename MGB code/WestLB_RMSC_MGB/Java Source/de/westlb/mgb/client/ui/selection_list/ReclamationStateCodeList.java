package de.westlb.mgb.client.ui.selection_list;

import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * Selection list for state codes used for reclamations.
 *
 * @author Manfred Boerner
 */
public class ReclamationStateCodeList extends AbstractSelectionList {
    /**
     * Creates a new ReclamationStateCodeList
     */
    public ReclamationStateCodeList() {
        super();
    }
    
    /**
     * Method setTableProvider.
     * @param provider
     */
    public static void setTableProvider(DictionaryProvider provider) {
        setTableProvider(ReclamationStateCodeList.class, provider);
    }

    /**
	 * @see de.westlb_systems.xaf.util.table.TableLookup#get(java.lang.Object)
	 */
	@Override
    public Object get(Object key) {
		Object o = super.get(key);
		if (o != null) {
			return o;
		}
        return key;
	}
    
}
