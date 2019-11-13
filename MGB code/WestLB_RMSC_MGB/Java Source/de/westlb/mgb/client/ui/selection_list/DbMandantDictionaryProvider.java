package de.westlb.mgb.client.ui.selection_list;

import java.rmi.RemoteException;
import java.util.Dictionary;
import java.util.Enumeration;

import de.westlb.mgb.client.server.MgbSessionSingleton;
import de.westlb.mgb.client.server.vo.MandantVo;
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
public class DbMandantDictionaryProvider extends AbstractTableProvider implements DictionaryProvider {
	
	@SuppressWarnings("unused")
    private String ntId;
	
	private static class Item implements SelectionListItem {
		@SuppressWarnings("unused")
        private String code;
		private String name;
		
		Item(String code, String name) {
			this.code = code;
			this.name = name;
		}
				
		@Override
        public String toString() {
			return name;
		}
		
	}
	
    public DbMandantDictionaryProvider(String ntId) {
        super(null);
        this.ntId = ntId;
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
        	MandantVo[] mandants =  MgbSessionSingleton.getInstance(false).getMandants();
			for (int i = 0; i < mandants.length; i++) {
            	MandantVo mandantVo = mandants[i];
				Item item = new Item(
					mandantVo.getMandantCode(),
					mandantVo.getMandantName()
				); 
	           	dict.put(mandantVo.getMandantCode(), item);
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
