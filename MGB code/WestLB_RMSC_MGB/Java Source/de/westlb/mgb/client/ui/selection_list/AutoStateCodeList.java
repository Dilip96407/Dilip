package de.westlb.mgb.client.ui.selection_list;

import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * Selection list for state codes used by automatic checking.
 *
 * @author Manfred Boerner
 */
public class AutoStateCodeList extends AbstractSelectionList {
    /**
     * Creates a new SourceSystemList
     */
    public AutoStateCodeList() {
        super();
    }
    
    public static void setTableProvider(DictionaryProvider provider) {
        setTableProvider(AutoStateCodeList.class, provider);
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
