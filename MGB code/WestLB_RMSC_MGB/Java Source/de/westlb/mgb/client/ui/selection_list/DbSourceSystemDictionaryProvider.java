package de.westlb.mgb.client.ui.selection_list;

import java.rmi.RemoteException;
import java.util.Dictionary;
import java.util.Enumeration;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.MgbSessionSingleton;
import de.westlb.mgb.client.server.vo.SourceSystemVo;
import de.westlb_systems.xaf.util.table.AbstractTableProvider;
import de.westlb_systems.xaf.util.table.DictionaryProvider;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class DbSourceSystemDictionaryProvider extends AbstractTableProvider implements DictionaryProvider {
	private boolean all = false;
	
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
	
    public DbSourceSystemDictionaryProvider() {
        this(false);
    }

	public DbSourceSystemDictionaryProvider(boolean all) {
		super(null);
		this.all = all;
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
//        return loadDictionary(cls);
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
			SourceSystemVo[] sources;
			if (all) {
	         	sources =  MgbServiceFactory.getService().findAllSourceSystems();
			} else {
				sources =  MgbSessionSingleton.getInstance(false).getSourceSystems();
			}
			for (int i = 0; i < sources.length; i++) {
            	SourceSystemVo sourceSystemVo = sources[i];
				Item item = new Item(
					sourceSystemVo.getSourceSystemCode(),
					sourceSystemVo.getSourceSystemName()
				); 
	           	dict.put(sourceSystemVo.getSourceSystemCode(), item);
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
