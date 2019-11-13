package de.westlb.mgb.client.ui.selection_list;

import java.io.File;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import de.westlb_systems.xaf.util.table.AbstractTableProvider;
import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * @author WSY4148
 *
 * Provides the functionality to get a dictionary for a class from a 
 * property file.
 * 
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class PropertyFileDictionaryProvider extends AbstractTableProvider implements DictionaryProvider {

    static Logger logger = Logger.getLogger(PropertyFileDictionaryProvider.class);

	/**
	 * Constructor for PropertyFileDictionaryProvider.
	 */
	public PropertyFileDictionaryProvider() {
		super(null);
	}

	/**
	 * @see de.westlb_systems.xaf.util.table.DictionaryProvider#getDictionary(Class)
	 */
	@Override
    public Dictionary getDictionary(Class cls) {
		Dictionary dict = (Dictionary) getCache().get(cls);
		if (dict == null) {
			dict = loadDictionary(cls);
			if (dict != null) {
				getCache().put(cls, dict);
			}
		}
		return dict;
	}

	/**
	* Laden eines Dictionaries
	*
	* @param cls Auswahlliste 
	* @return das Dictionary
	*/
	protected Dictionary loadDictionary(Class cls) {
		Dictionary dict = new OrderedDictionary();
		String className = cls.getName();
		String bundleFile = "list" + File.separatorChar + className.substring(className.lastIndexOf(".") + 1);

		try {
			ResourceBundle bundle = ResourceBundle.getBundle(bundleFile);
            Enumeration enumration = bundle.getKeys();
		    while (enumration.hasMoreElements()) {
		        String key = (String) enumration.nextElement();
		        dict.put(key.toUpperCase(), bundle.getString(key));
		    }
        } catch (MissingResourceException e) {
            logger.warn("Can not load bundle file '" + bundleFile + "': "+ e.getMessage());
            String propFile = "/list/" + className.substring(className.lastIndexOf(".") + 1) + ".properties";
            try {
                Properties prop = new Properties();
//                InputStream in = getClass().getResourceAsStream(propFile);
                InputStream in = PropertyFileDictionaryProvider.class.getResourceAsStream(propFile);
                if (in != null) {
                    prop.load(in);
                    Enumeration propEnum = prop.keys();
                    while (propEnum.hasMoreElements()) {
                        String key = (String) propEnum.nextElement();
                        dict.put(key.toUpperCase(), prop.getProperty(key));
                    }
                }
            } catch (Exception ex) {
                logger.warn("Loading properties file '" + propFile + "' failed!", ex);
            }                   
        }

		return dict;
	}

	/**
	 * @see de.westlb_systems.xaf.util.table.SetProvider#getElements(Class)
	 */
	@Override
    public Enumeration getElements(Class cls) {
		return null;
	}
}

