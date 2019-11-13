package de.westlb.mgb.client.ui.selection_list;

import java.util.Dictionary;

import de.westlb_systems.xaf.util.table.DictionaryProvider;
import de.westlb_systems.xaf.util.table.TableLookup;
import de.westlb_systems.xaf.util.table.TablePool;

/**
 * Super class for all MGB selection lists.
 *
 */
public abstract class AbstractSelectionList extends TableLookup {
	public class NullItem extends TableLookup.Item {
		private Object value;
		public NullItem(Object value) {
			super(null);
			setValue(value);
		}
		public void setValue(Object value) {
			this.value = value;
		}
		@Override
        public Object getValue() {
			return value;
		}
	}
/**
 * AbstractSelectionList constructor comment.
 */
public AbstractSelectionList() {
	super();
}
/**
 * Get an Item form a List
 *
 * return the Item or key if no Item is found
 */
public static Object getItemFor(AbstractSelectionList liste, Object key) {

	Object item = null;

	if(liste != null && key != null) {
		item = liste.itemFor(key);
	}
	
	return item != null ? item : key;
}

/**
 * Overwrites method from superclass, because we are not going
 * to use caching here, because the clearCache-Method of
 * an TableProvider would have no effect.
 */
@Override
@SuppressWarnings("rawtypes")
protected Dictionary getDictionary() {
	if(provider != null) {
		return provider.getDictionary(getClass());
	}
	return null;
}

/**
 * Get the Key from an Item
 *
 * return the key of null if item is not instanceof TablePool.Item
 */
public static Object getKeyFrom(Object item) {

	Object key = null;
	
	if(item instanceof Item) {
		key = ((Item)item).getObject();
	}
	
	return key;
}
/**
 * Get an Item form a List
 *
 * return the Item or key if no Item is found
 */
public static Object getValueFor(AbstractSelectionList liste, Object key) {

	Object value = null;

	if(liste != null && key != null) {
		value = liste.get(key);
	}
	
	return value != null ? value : key;
}
/**
 * Create a new <code>TablePool.Item</code> holding specified key.
 */
@Override
public TablePool.Item itemFor(Object key) {
	Item item = null;
	if (key != null) {
		try {
			item = new Item(key);
		} catch (IllegalArgumentException e) {
			item = null; //super.itemFor(key);
		}
	}
	return item;
}
/**
 * Create a new TablePool.Item with given Value and key null
 */
public NullItem nullItemWith(Object value) {
	return new NullItem(value);
}
/**
 * Set Dictionary provider for specified class
 */
public static void setDictionaryProvider(Class<?> cls, DictionaryProvider provider) {
  setTableProvider(cls, provider);
}
}
