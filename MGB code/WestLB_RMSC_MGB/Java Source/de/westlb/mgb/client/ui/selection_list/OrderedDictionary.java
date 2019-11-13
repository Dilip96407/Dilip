package de.westlb.mgb.client.ui.selection_list;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * Dictionary, which answers enumerations in order: 
 * a < b if a has been put into dictionary earlier than b
 */
class OrderedDictionary extends 
Dictionary<Object, Object> {

	private Vector<Object> keys = new Vector<Object>();
	private HashMap<Object, Object> hash = new HashMap<Object, Object>();

	/**
	 * OrderedHash constructor comment.
	 */
	public OrderedDictionary() {
		super();
	}
	@Override
    public synchronized Enumeration<Object> elements() {
		Object[] a = new Object[size()];
		for (int i = 0; i < keys.size(); i++)
			a[i] = get(keys.elementAt(i));
		return new ArrayEnumeration(a);
	}
	@Override
    public Object get(Object key) {
		return hash.get(key);
	}
	@Override
    public boolean isEmpty() {
		return hash.isEmpty();
	}
	@Override
    public synchronized Enumeration<Object> keys() {
		Object[] a = new Object[size()];
		keys.copyInto(a);
		return new ArrayEnumeration(a);
	}
	@Override
    public synchronized Object put(Object key, Object value) {
		if (!hash.containsKey(key))
			keys.addElement(key);
		return hash.put(key, value);
	}
	@Override
    public synchronized Object remove(Object key) {
		if (hash.containsKey(key))
			keys.removeElement(key);
		return hash.remove(key);
	}
	@Override
    public int size() {
		return hash.size();
	}
}

/**
 * Array enumeration.
 *
 */
class ArrayEnumeration implements Enumeration<Object>, Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = 182631761245770992L;
    private int next = 0;
	private Object[] array;
	public ArrayEnumeration(Object[] a) {
		array = a;
	}
	@Override
    public boolean hasMoreElements() {
		return next < array.length;
	}
	@Override
    public Object nextElement() {
		if (!hasMoreElements())
			throw new NoSuchElementException("ArrayEnumeration>>nextElement() no such...");
		return array[next++];
	}
}
