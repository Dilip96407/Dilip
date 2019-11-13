package de.westlb.mgb.client.ui.selection_list;

import java.rmi.RemoteException;
import java.util.Dictionary;
import java.util.Enumeration;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.ConditionVo;
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
public class DbConditionDictionaryProvider extends AbstractTableProvider implements DictionaryProvider {

	private static class Item implements SelectionListItem {
		private ConditionVo conditionVo;
		
		Item(ConditionVo conditionVo) {
			this.conditionVo = conditionVo;
		}
				
		@Override
        public String toString() {
			return conditionVo.getConditionName();
		}
		
        /**
         * Returns the priceCheckCategoryVo.
         * @return PriceCheckCategoryVo
         */
        @SuppressWarnings("unused")
        public ConditionVo getPriceCheckCategoryVo() {
            return conditionVo;
        }

	}
	
    public DbConditionDictionaryProvider() {
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
        
        try {
			ConditionVo[] conditions = MgbServiceFactory.getService().findAllConditions();

			for (int i = 0; i < conditions.length; i++) {
				Item item = new Item(conditions[i]); 
	           	dict.put(conditions[i].getId(), item);
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
