package de.westlb.mgb.client.ui.selection_list;

import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ConditionList extends AbstractSelectionList {

	/**
	 * Constructor for ConditionList.
	 */
	public ConditionList() {
		super();
	}

    /**
     * Method setTableProvider.
     * @param provider
     */
    public static void setTableProvider(DictionaryProvider provider) {
        setTableProvider(ConditionList.class, provider);
    }

}
