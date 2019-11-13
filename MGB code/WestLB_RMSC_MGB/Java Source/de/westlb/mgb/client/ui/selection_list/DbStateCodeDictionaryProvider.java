package de.westlb.mgb.client.ui.selection_list;

import java.rmi.RemoteException;
import java.util.Dictionary;
import java.util.Enumeration;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.StateCodeVo;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
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
public class DbStateCodeDictionaryProvider extends AbstractTableProvider implements DictionaryProvider {

	private String stateCodeType = StateCodeTypeDef.MANUAL;
		
    public DbStateCodeDictionaryProvider(String stateCodeType) {
        super(null);
        this.stateCodeType = stateCodeType;
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

//        if (stateCodeType == null) {
//            throw new IllegalArgumentException("DbStateCodeDictionaryProvider can't load dictionary for " + cls.getName());
//        }
        
        try {
        	StateCodeVo[] stateCodes =  MgbServiceFactory.getService().getStateCodes(stateCodeType);
			for (int i = 0; i < stateCodes.length; i++) {
            	StateCodeVo stateCodeVo = stateCodes[i];
				StateCodeItem item = new StateCodeItem(stateCodeVo); 
	           	dict.put(stateCodeVo.getStateCode(), item);
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



