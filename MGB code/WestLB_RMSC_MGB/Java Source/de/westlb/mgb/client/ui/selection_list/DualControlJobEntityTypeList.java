package de.westlb.mgb.client.ui.selection_list;

import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * Type comment.
 *
 * @author Manfred Boerner
 */
public class DualControlJobEntityTypeList extends AbstractSelectionList {
    /**
     * Creates a new SourceSystemList
     */
    public DualControlJobEntityTypeList() {
        super();
    }
    public static void setTableProvider(DictionaryProvider provider) {
        setTableProvider(DualControlJobEntityTypeList.class, provider);
    }
    
    /* (non-Javadoc)
     * @see de.westlb_systems.xaf.util.table.TableLookup#get(java.lang.Object)
     */
    @Override
    public Object get(Object key) {
        if (key != null) {
            return super.get(key.toString().toUpperCase());
        }
        return null;
    }
}
