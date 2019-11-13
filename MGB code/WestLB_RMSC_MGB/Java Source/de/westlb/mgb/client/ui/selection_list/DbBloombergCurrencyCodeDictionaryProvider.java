package de.westlb.mgb.client.ui.selection_list;

import java.rmi.RemoteException;
import java.util.Dictionary;
import java.util.Enumeration;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.BloombergCurrencyCodeVo;
import de.westlb_systems.xaf.util.table.AbstractTableProvider;
import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * @author M. Boerner
 *
 * Provides a dicitonary of currency codes from the database.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class DbBloombergCurrencyCodeDictionaryProvider extends AbstractTableProvider implements DictionaryProvider {
	private static class Item implements SelectionListItem {
		private String name;
		
		Item(String name) {
			this.name = name;
		}
				
		@Override
        public String toString() {
			return name;
		}
	}
	
    public DbBloombergCurrencyCodeDictionaryProvider() {
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
    * Loads the dictionary.
    *
    * @param cls Selection list class. 
    * @return das Dictionary
    */
    protected Dictionary loadDictionary(Class cls) {
        Dictionary dict = new OrderedDictionary();
        
        try {
        	BloombergCurrencyCodeVo[] currencyCodes =  MgbServiceFactory.getService().findAllBloombergCurrencyCodes();
			for (int i = 0; i < currencyCodes.length; i++) {
				BloombergCurrencyCodeVo currencyCode = currencyCodes[i];
	           	dict.put(currencyCode.getIsoCurrencyCode(), new Item(currencyCode.getCurrencyName()));
            }
        } catch (RemoteException e) {
        	e.printStackTrace();
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
