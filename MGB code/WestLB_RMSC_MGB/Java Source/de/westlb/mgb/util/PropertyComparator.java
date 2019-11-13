/*
 * Created on 06.06.2003
 */
package de.westlb.mgb.util;

import java.lang.reflect.Method;
import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

/**
 * Compares objects of specified type by specified property.
 * The property may use dotted notation for nested property.
 * 
 * @author beck
 */
@SuppressWarnings("rawtypes")
public class PropertyComparator implements Comparator {
	private static Class[] NO_ARGS = new Class[0];

	private Method[] methods;
	private boolean reverse;

	/**
	 * Compares properties of specified type. The property may
	 * use dotted notation for a nested property.
	 */
	public PropertyComparator(Class type, String property) throws NoSuchMethodException {
		this(type, property, false);
	}

	/**
	 * Compares properties of specified type. The property may
	 * use dotted notation for a nested property.
	 */
	public PropertyComparator(Class type, String property, boolean reverse) throws NoSuchMethodException {
		super();
		
		String[] properties =  StringUtils.split(property, "\\.");
		methods = new Method[properties.length];
		for (int i = 0; i < properties.length; i++) {
			methods[i] = getGetter(type, properties[i]);
			type = methods[i].getReturnType();
		}
		this.reverse = reverse;
	}

	@SuppressWarnings("unchecked")
    private Method getGetter(Class type, String property) throws NoSuchMethodException {
		char first = Character.toUpperCase(property.charAt(0));
		String prefix = type == Boolean.class ? "is" : "get";
		String name = prefix + first + property.substring(1);
		return type.getMethod(name, NO_ARGS);	
	}

	private Comparable get(Object value) throws Exception {
		for (int i = 0; i < methods.length && value != null; i++) {
			value = methods[i].invoke(value);
		}
		return (Comparable)value;
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
    public int compare(Object o1, Object o2) {
		int cmp = compareImpl(o1, o2);
		return reverse ? -cmp : cmp;
	}

	@SuppressWarnings("unchecked")
    private int compareImpl(Object o1, Object o2) {
		Comparable c1 = null, c2 = null;

		try {
			c1 = get(o1);
		} catch (Exception e) {
			return -1;
		}
		if (c1 == null)
			return -1;

		try {
			c2 = get(o2);
		} catch (Exception e) {
			return 1;
		}
		if (c2 == null)
			return 1;

		return c1.compareTo(c2);
	}
}
