package de.westlb.mgb.client.application;

import java.util.Iterator;
import java.util.WeakHashMap;

/**
 * @author M. Boerner (WSY4148)
 *
 * This class support the refresh mechanismen of the mgb tool.
 * Whenever a gui model successfully changed data of the
 * business model, it has to call registerUpdate.
 * 
 * A gui model which is interested to know if a refresh is
 * required might call registerCache when it successfully
 * loaded data from the business model. Later it might
 * call isRefreshRequired to check if it should load
 * the data again.
 * 
 * References to the gui model objects are stored in 
 * a WeakHashMap, so they are garbage collected when there is no
 * other reference to these objects.
 * 
 */
public class RefreshHelper {
	public static final int TRADE = 1;
	public static final int EMPLOYEE = 2;
	
	
	private static WeakHashMap<Object, CacheItem> cacheItems = new WeakHashMap<Object, CacheItem>();
	
	public static boolean isRefreshRequired(Object object) {
		CacheItem item = cacheItems.get(object);
		return item != null && item.refreshRequired;
	}
	
	public static void registerCache(Object object, int type, Object id) {
		cacheItems.put(object, new CacheItem(type, id, false));
	}

    public static void registerUpdate(Object object, int type, Object id) {
        Iterator<CacheItem> it = cacheItems.values().iterator();
        while (it.hasNext()) {
        	CacheItem item = it.next();
        	if (item.type == type && item.id.equals(id)) {
        		item.refreshRequired = true;	
        	}
        }
        registerCache(object, type, id);
    }		
	
	private static class CacheItem {
		int type;
		Object id;
		boolean refreshRequired ;
		
		public CacheItem(int aspect, Object id, boolean refreshRequired) {
			this.type = aspect;
			this.id = id;	
			this.refreshRequired = refreshRequired;
		}
	}

}
