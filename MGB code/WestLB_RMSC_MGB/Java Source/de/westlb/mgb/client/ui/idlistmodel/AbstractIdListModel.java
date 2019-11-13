package de.westlb.mgb.client.ui.idlistmodel;

import java.io.Serializable;

import org.apache.commons.beanutils.PropertyUtils;

import de.westlb_systems.xaf.util.PropertyChangeSupportingObject;

/**
 * @author wsy4148
 *
 * Generic wrapper class to wrap an Array of beans to
 * an KeyListModel.
 * 
 */
public abstract class AbstractIdListModel extends PropertyChangeSupportingObject implements IdListModel {

	protected Serializable[] keys = new Serializable[0];
	private int selectedIndex = -1;
	
	public AbstractIdListModel(Object[] data) {
		setData(data);	
	}	
	
	protected void setData(Object[] data) {
		Object oldValue = keys;
		selectedIndex = -1;
		if (data == null) {
			keys = new Serializable[0];
			return;
		}
		
		keys = new Serializable[data.length];
		for (int i = 0; i < data.length; i++) {
        	try {
        		if (data[i] instanceof Long) {
        			keys[i] = (Serializable)data[i];
        		} else {
	                keys[i] = (Serializable)PropertyUtils.getProperty(data[i], getIdPropertyName());
        		}
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
        
        firePropertyChange("data", oldValue, keys);        
	}
		
    /**
     * @see de.westlb.mgb.client.ui.rsmodel.RecordSetModel#getSelectedIndex()
     */
    @Override
    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * @see de.westlb.mgb.client.ui.rsmodel.RecordSetModel#getSelectedKey()
     */
    @Override
    public Serializable getSelectedId() {
        if (selectedIndex < 0) {
        	return null;
        }
        return keys[selectedIndex];
    }

    /**
     * @see de.westlb.mgb.client.ui.rsmodel.RecordSetModel#moveTo(int)
     */
    @Override
    public boolean moveTo(int i) {
        if (i < keys.length && i >= 0) {
        	int oldValue = selectedIndex;
        	selectedIndex = i;
	        firePropertyChange("data", oldValue, selectedIndex);        
        	return true;
        }
        
        return false;
    }

    /**
     * @see de.westlb.mgb.client.ui.rsmodel.RecordSetModel#size()
     */
    @Override
    public int size() {
        return keys.length;
    }

    /**
     * Returns the keyProperty.
     * @return String
     */
    public abstract String getIdPropertyName();

}
