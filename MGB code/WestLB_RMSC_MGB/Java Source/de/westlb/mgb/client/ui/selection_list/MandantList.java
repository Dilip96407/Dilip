package de.westlb.mgb.client.ui.selection_list;

import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * Type comment.
 *
 * @author Manfred Boerner
 */
public class MandantList extends AbstractSelectionList {
/**
 * Creates a new Mandant list.
 */
public MandantList() {
	super();
}
public static void setTableProvider(DictionaryProvider provider) {
	setTableProvider(MandantList.class, provider);
}
}
