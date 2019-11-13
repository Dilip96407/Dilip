/*
 * Copyright (c) 2002-2003 by OpenSymphony
 * All rights reserved.
 */
package com.opensymphony.oscache.plugins.diskpersistence;

import com.opensymphony.oscache.base.Config;
import com.opensymphony.oscache.base.persistence.CachePersistenceException;
import com.opensymphony.oscache.base.persistence.PersistenceListener;
import com.opensymphony.oscache.web.ServletCacheAdministrator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

import java.util.Set;

import javax.servlet.jsp.PageContext;

/**
 * Persist the cache data to disk.
 *
 * The code in this class is totally not thread safe it is the resonsibility
 * of the cache using this persistence listener to handle the concurrency.
 *
 * @version        $Revision: 1.1 $
 * @author <a href="mailto:fbeauregard@pyxis-tech.com">Francois Beauregard</a>
 * @author <a href="mailto:abergevin@pyxis-tech.com">Alain Bergevin</a>
 * @author <a href="&#109;a&#105;&#108;&#116;&#111;:chris&#64;swebtec.&#99;&#111;&#109;">Chris Miller</a>
 */
public final class DiskPersistenceListener implements PersistenceListener {
    protected final static String CACHE_PATH_KEY = "cache.path";

    /**
     * File extension for disk cache file
     */
    private final static String CACHE_EXTENSION = "cache";

    /**
     * The directory that cache groups are stored under
     */
    private final static String GROUP_DIRECTORY = "__groups__";

    /**
     * Sub path name for application cache
     */
    private final static String APPLICATION_CACHE_SUBPATH = "application";

    /**
     * Sub path name for session cache
     */
    private final static String SESSION_CACHE_SUBPATH = "session";

    /**
     * Property to get the temporary working directory of the servlet container.
     */
    private static final String CONTEXT_TMPDIR = "javax.servlet.context.tempdir";
    private static transient final Log log = LogFactory.getLog(DiskPersistenceListener.class);

    /**
     * Base path where the disk cache reside.
     */
    private File cachePath = null;
    private File contextTmpDir;

    /**
     * Root path for disk cache
     */
    private String root = null;

    /**
       *        Get the physical cache path on disk.
       *
       *        @return        A file representing the physical cache location.
       */
    public File getCachePath() {
        return cachePath;
    }

    /**
     * Verify if a group exists in the cache
     *
     * @param group The group name to check
     * @return True if it exists
     * @throws CachePersistenceException
     */
    public boolean isGroupStored(String group) throws CachePersistenceException {
        try {
            File file = getCacheGroupFile(group);

            return file.exists();
        } catch (Exception e) {
            throw new CachePersistenceException("Unable verify group '" + group + "' exists in the cache: " + e);
        }
    }

    /**
     * Verify if an object is currently stored in the cache
     *
     * @param key The object key
     * @return True if it exists
     * @throws CachePersistenceException
     */
    public boolean isStored(String key) throws CachePersistenceException {
        try {
            File file = getCacheFile(key);

            return file.exists();
        } catch (Exception e) {
            throw new CachePersistenceException("Unable verify id '" + key + "' is stored in the cache: " + e);
        }
    }

    /**
     * Clears the whole cache directory, starting from the root
     *
     * @throws CachePersistenceException
     */
    public void clear() throws CachePersistenceException {
        clear(root);
    }

    /**
     * Initialises this <tt>DiskPersistenceListener</tt> using the supplied
     * configuration.
     *
     * @param config The OSCache configuration
     */
    public PersistenceListener configure(Config config) {
        String sessionId = null;
        int scope = 0;
        initFileCaching(config.getProperty(CACHE_PATH_KEY));

        if (config.getProperty(ServletCacheAdministrator.HASH_KEY_SESSION_ID) != null) {
            sessionId = config.getProperty(ServletCacheAdministrator.HASH_KEY_SESSION_ID);
        }

        if (config.getProperty(ServletCacheAdministrator.HASH_KEY_SCOPE) != null) {
            scope = Integer.parseInt(config.getProperty(ServletCacheAdministrator.HASH_KEY_SCOPE));
        }

        StringBuffer root = new StringBuffer(getCachePath().getPath());
        root.append("/");
        root.append(getPathPart(scope));

        if ((sessionId != null) && (sessionId.length() > 0)) {
            root.append("/");
            root.append(sessionId);
        }

        this.root = root.toString();
        this.contextTmpDir = (File) config.get(ServletCacheAdministrator.HASH_KEY_CONTEXT_TMPDIR);

        return this;
    }

    /**
     * Delete a single cache entry.
     *
     * @param key The object key to delete
     * @throws CachePersistenceException
     */
    public void remove(String key) throws CachePersistenceException {
        File file = getCacheFile(key);
        remove(file);
    }

    /**
     * Deletes an entire group from the cache.
     *
     * @param groupName The name of the group to delete
     * @throws CachePersistenceException
     */
    public void removeGroup(String groupName) throws CachePersistenceException {
        File file = getCacheGroupFile(groupName);
        remove(file);
    }

    /**
     * Retrieve an object from the disk
     *
     * @param key The object key
     * @return The retrieved object
     * @throws CachePersistenceException
     */
    public Object retrieve(String key) throws CachePersistenceException {
        return retrieve(getCacheFile(key));
    }

    /**
     * Retrieves a group from the cache, or <code>null</code> if the group
     * file could not be found.
     *
     * @param groupName The name of the group to retrieve.
     * @return A <code>Set</code> containing keys of all of the cache
     * entries that belong to this group.
     * @throws CachePersistenceException
     */
    public Set retrieveGroup(String groupName) throws CachePersistenceException {
        File groupFile = getCacheGroupFile(groupName);

        try {
            return (Set) retrieve(groupFile);
        } catch (ClassCastException e) {
            throw new CachePersistenceException("Group file " + groupFile + " was not persisted as a Set: " + e);
        }
    }

    /**
     * Stores an object in cache
     *
     * @param key The object's key
     * @param obj The object to store
     * @throws CachePersistenceException
     */
    public void store(String key, Object obj) throws CachePersistenceException {
        File file = getCacheFile(key);
        store(file, obj);
    }

    /**
     * Stores a group in the persistent cache. This will overwrite any existing
     * group with the same name
     */
    public void storeGroup(String groupName, Set group) throws CachePersistenceException {
        File groupFile = getCacheGroupFile(groupName);
        store(groupFile, group);
    }

    /**
     * Allows to translate to the temp dir of the servlet container if cachePathStr
     * is javax.servlet.context.tempdir.
     *
     * @param cachePathStr  Cache path read from the properties file.
     * @return Adjusted cache path
     */
    protected String adjustFileCachePath(String cachePathStr) {
        if (cachePathStr.compareToIgnoreCase(CONTEXT_TMPDIR) == 0) {
            cachePathStr = contextTmpDir.getAbsolutePath();
        }

        return cachePathStr;
    }

    /**
     *        Set caching to file on or off.
     *  If the <code>cache.path</code> property exists, we assume file caching is turned on.
     *        By the same token, to turn off file caching just remove this property.
     */
    protected void initFileCaching(String cachePathStr) {
        if (cachePathStr != null) {
            cachePath = new File(cachePathStr);

            try {
                if (!cachePath.exists()) {
                    if (log.isInfoEnabled()) {
                        log.info("cache.path '" + cachePathStr + "' does not exist, creating");
                    }

                    cachePath.mkdirs();
                }

                if (!cachePath.isDirectory()) {
                    log.error("cache.path '" + cachePathStr + "' is not a directory");
                    cachePath = null;
                } else if (!cachePath.canWrite()) {
                    log.error("cache.path '" + cachePathStr + "' is not a writable location");
                    cachePath = null;
                }
            } catch (Exception e) {
                log.error("cache.path '" + cachePathStr + "' could not be used", e);
                cachePath = null;
            }
        } else {
            // Use default value
        }
    }

    protected void remove(File file) throws CachePersistenceException {
        try {
            // Loop until we are able to delete (No current read).
            // The cache must ensure that there are never two concurrent threads
            // doing write (store and delete) operations on the same item.
            // Delete only should be enough but file.exists prevents infinite loop
            while (!file.delete() && file.exists()) {
                ;
            }
        } catch (Exception e) {
            throw new CachePersistenceException("Unable to remove '" + file + "' from the cache: " + e);
        }
    }

    /**
     * Stores an object using the supplied file object
     *
     * @param file The file to use for storing the object
     * @param obj the object to store
     * @throws CachePersistenceException
     */
    protected void store(File file, Object obj) throws CachePersistenceException {
        // check if the directory structure required exists and create it if it doesn't
        File filepath = new File(file.getParent());

        try {
            if (!filepath.exists()) {
                filepath.mkdirs();
            }
        } catch (Exception e) {
            throw new CachePersistenceException("Unable to create the directory " + filepath);
        }

        // Loop until we are able to delete (No current read).
        // The cache must ensure that there are never two concurrent threads
        // doing write (store and delete) operations on the same item.
        // Delete only should be enough but file.exists prevents infinite loop
        while (file.exists() && !file.delete()) {
            ;
        }

        // Write the object to disk
        FileOutputStream fout = null;
        ObjectOutputStream oout = null;

        try {
            fout = new FileOutputStream(file);
            oout = new ObjectOutputStream(fout);
            oout.writeObject(obj);
            oout.flush();
        } catch (Exception e) {
            throw new CachePersistenceException("Unable to write '" + file + "' in the cache. Exception: " + e.getClass().getName() + ", Message: " + e.getMessage());
        } finally {
            try {
                fout.close();
            } catch (Exception e) {
            }

            try {
                oout.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * Build fully qualified cache file name specifying a cache entry key.
     *
     * @param key   Cache Entry Key.
     * @return File reference.
     */
    private File getCacheFile(String key) {
        if ((key == null) || (key.length() == 0)) {
            throw new IllegalArgumentException("Invalid key '" + key + "' specified to getCacheFile.");
        }

        char[] chars = key.toCharArray();
        char[] fileChars = new char[chars.length];

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            switch (c) {
                case '.':
                case '/':
                case '\\':
                case ' ':
                case ':':
                case ';':
                case '"':
                case '\'':
                    fileChars[i] = '_';
                    break;
                default:
                    fileChars[i] = c;
            }
        }

        File file = new File(root, new String(fileChars) + "." + CACHE_EXTENSION);

        return file;
    }

    /**
     * Builds a fully qualified file name that specifies a cache group entry.
     *
     * @param group The name of the group
     * @return A File reference
     */
    private File getCacheGroupFile(String group) {
        int AVERAGE_PATH_LENGTH = 30;

        if ((group == null) || (group.length() == 0)) {
            throw new IllegalArgumentException("Invalid group '" + group + "' specified to getCacheGroupFile.");
        }

        StringBuffer path = new StringBuffer(AVERAGE_PATH_LENGTH);

        // Build a fully qualified file name for this group
        path.append(GROUP_DIRECTORY).append('/');
        path.append(group).append('.').append(CACHE_EXTENSION);

        return new File(root, path.toString());
    }

    /**
     * This allows to persist different scopes in different path in the case of
     * file caching.
     *
     * @param scope   Cache scope.
     * @return The scope subpath
     */
    private String getPathPart(int scope) {
        if (scope == PageContext.SESSION_SCOPE) {
            return SESSION_CACHE_SUBPATH;
        } else {
            return APPLICATION_CACHE_SUBPATH;
        }
    }

    /**
     * Clears a whole directory, starting from the specified
     * directory
     *
     * @param baseDirName The root directory to delete
     * @throws CachePersistenceException
     */
    private void clear(String baseDirName) throws CachePersistenceException {
        File baseDir = new File(baseDirName);
        File[] fileList = baseDir.listFiles();

        try {
            if (fileList != null) {
                // Loop through all the files and directory to delete them
                for (int count = 0; count < fileList.length; count++) {
                    if (fileList[count].isFile()) {
                        fileList[count].delete();
                    } else {
                        // Make a recursive call to delete the directory
                        clear(fileList[count].toString());
                        fileList[count].delete();
                    }
                }
            }

            // Delete the root directory
            baseDir.delete();
        } catch (Exception e) {
            throw new CachePersistenceException("Unable to clear the cache directory");
        }
    }

    /**
     * Retrives a serialized object from the supplied file, or returns
     * <code>null</code> if the file does not exist.
     *
     * @param file The file to deserialize
     * @return The deserialized object
     * @throws CachePersistenceException
     */
    private Object retrieve(File file) throws CachePersistenceException {
        Object readContent = null;
        boolean fileExist;

        try {
            fileExist = file.exists();
        } catch (Exception e) {
            throw new CachePersistenceException("Unable to verify if " + file + " exists: " + e);
        }

        // Read the file if it exists
        if (fileExist) {
            BufferedInputStream in = null;
            ObjectInputStream oin = null;

            try {
                in = new BufferedInputStream(new FileInputStream(file));
                oin = new ObjectInputStream(in);
                readContent = oin.readObject();
            } catch (Exception e) {
                // We expect this exception to occur.
                // This is when the item will be invalidated (written or deleted)
                // during read.
                // The cache has the logic to retry reading.
                throw new CachePersistenceException("Unable to read '" + file.getAbsolutePath() + "' from the cache: " + e);
            } finally {
                try {
                    oin.close();
                } catch (Exception ex) {
                }

                try {
                    in.close();
                } catch (Exception ex) {
                }
            }
        }

        return readContent;
    }
}