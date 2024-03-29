/*
 * Copyright (c) 2002-2003 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.oscache.base.algorithm;

import java.util.*;

/**
 * FIFO (First In First Out) based queue algorithm for the cache.
 *
 * No synchronization is required in this class since the
 * <code>AbstractConcurrentReadCache</code> already takes care of any
 * synchronization requirements.
 *
 * @version        $Revision: 1.1 $
 * @author        <a href="mailto:mike@atlassian.com">Mike Cannon-Brookes</a>
 * @author        <a href="mailto:abergevin@pyxis-tech.com">Alain Bergevin</a>
 * @author        <a href="&#109;a&#105;&#108;&#116;&#111;:chris&#64;swebtec.&#99;&#111;&#109;">Chris Miller</a>
 */
public class FIFOCache extends AbstractConcurrentReadCache {
    /**
     * A queue containing all cache keys
     */
    private Collection list;

    /**
     * A flag indicating whether we are using a List or a Set for the key collection
     */
    private boolean isSet = false;

    /**
     * Constructs a FIFO Cache.
     */
    public FIFOCache() {
        super();

        // Check if we're running under JRE 1.4+. If so we can use a LinkedHashSet
        // instead of a LinkedList for a big performance boost when removing elements.
        try {
            Class.forName("java.util.LinkedHashSet");
            list = new LinkedHashSet();
            isSet = true;
        } catch (ClassNotFoundException e) {
            list = new LinkedList();
        }
    }

    /**
     * Constructs a FIFO Cache of the specified capacity.
     *
     * @param capacity The maximum cache capacity.
     */
    public FIFOCache(int capacity) {
        this();
        maxEntries = capacity;
    }

    /**
     * An object was retrieved from the cache. This implementation
     * does noting since this event has no impact on the FIFO algorithm.
     *
     * @param key The cache key of the item that was retrieved.
     */
    protected void itemRetrieved(Object key) {
    }

    /**
     * An object was put in the cache. This implementation just adds
     * the key to the end of the list if it doesn't exist in the list
     * already.
     *
     * @param key The cache key of the item that was put.
     */
    protected void itemPut(Object key) {
        if (!list.contains(key)) {
            list.add(key);
        }
    }

    /**
     * An item needs to be removed from the cache. The FIFO implementation
     * removes the first element in the list (ie, the item that has been in
     * the cache for the longest time).
     *
     * @return The key of whichever item was removed.
     */
    protected Object removeItem() {
        Object toRemove;

        if (isSet) {
            Iterator it = list.iterator();
            toRemove = it.next();
            it.remove();
        } else {
            toRemove = ((List) list).remove(0);
        }

        return toRemove;
    }

    /**
     * Remove specified key since that object has been removed from the cache.
     *
     * @param key The cache key of the item that was removed.
     */
    protected void itemRemoved(Object key) {
        list.remove(key);
    }
}
