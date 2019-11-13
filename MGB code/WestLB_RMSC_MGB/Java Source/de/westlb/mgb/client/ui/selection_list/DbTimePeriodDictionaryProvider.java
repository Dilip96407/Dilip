package de.westlb.mgb.client.ui.selection_list;

import java.rmi.RemoteException;
import java.util.Dictionary;
import java.util.Enumeration;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb_systems.xaf.util.table.AbstractTableProvider;
import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * @author wsy4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class DbTimePeriodDictionaryProvider extends AbstractTableProvider implements DictionaryProvider {

	private String mandantCode = "";
		
    public DbTimePeriodDictionaryProvider(String mandantCode) {
        super(null);
        this.mandantCode = mandantCode;
    }

    private static class Item implements SelectionListItem {
        private String code;
        
        Item(String code) {
            this.code = code;
        }
                
        @Override
        public String toString() {
            return code;
        }
        
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
        
        try {
        	String[] timePeriods =  MgbServiceFactory.getService().findAllTimePeriods(mandantCode);
			for (int i = 0; i < timePeriods.length; i++) {
	           	dict.put(timePeriods[i], new Item(timePeriods[i]));
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



