package de.westlb.mgb.client.ui.selection_list;

import java.rmi.RemoteException;
import java.util.Dictionary;
import java.util.Enumeration;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.BloombergMaturityCodeVo;
import de.westlb_systems.xaf.util.table.AbstractTableProvider;
import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * @author wsy4148
 *
 * Provides a dicitonary of maturity codes from the database.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class DbBloombergMaturityCodeDictionaryProvider extends AbstractTableProvider implements DictionaryProvider {
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
	
    public DbBloombergMaturityCodeDictionaryProvider() {
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
        	BloombergMaturityCodeVo[] maturityCodes =  MgbServiceFactory.getService().findAllBloombergMaturityCodes();
			for (int i = 0; i < maturityCodes.length; i++) {
				BloombergMaturityCodeVo maturityCode = maturityCodes[i];
				Item item = new Item(
					maturityCode.getMaturityName()
				); 
	           	dict.put(maturityCode.getMaturityCode(), item);
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
