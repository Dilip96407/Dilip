package de.westlb.mgb.client.mask.model.shared;

import java.beans.PropertyChangeEvent;
import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import de.westlb.mgb.client.application.TabDefs;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.ui.idlistmodel.IdListModel;
import de.westlb.mgb.client.ui.idlistmodel.TradeIdListModel;

/**
 * Gui model for the trade list view. 
 *
 * @author Manfred Boerner
 */

public class TradeMultiModel extends AbstractModel implements IdListModel {

	// public static final String INSTRUMENT = "TradeTableModel";
	public static final String TITLE = "Title";
	public static final String TAB_DEF = "TAB_DEF";
	
	/** Definition of all properties provided by the model to the view */
	private final String[] propertyNames = new String[] {
		TITLE,
		TAB_DEF,
	};
	
	/**
	 * Default constructor to create an empty model
	 */
	public TradeMultiModel() {
		setPropertyNames(propertyNames);
	}
	
	/**
	 * Constructor filling the model with data from the business object.
	 * The businessObject is expected to be one of the following:
	 * 
	 * TradeId (Long)
	 * TradeId (String)
	 * TradeIdListModel (TradeIdListModel)
	 * 
	 */
	public TradeMultiModel(Object businessObject) {
		this();
		setBusinessObject(convertBo(businessObject));
	}
	
	/**
	 * Performs a type conversion of the business object
	 * 
	 */
	private Object convertBo(Object object) {
		Object bo = object;
		if (bo instanceof TradeIdListModel) {
			return bo;
		} 
 
		if (bo instanceof String) {
			bo = new Long((String)object);
		} 	
			
		if (bo instanceof Long) {
			bo = new TradeIdListModel(new Object[] { bo } );
			((TradeIdListModel)bo).moveTo(0);
		};
		
		return bo;
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		updateTitle();
       	updateTabDef();	
	}
	
	
	public String[][] updateTabDef() {
		if (getTradeIdListModel() == null) {
			return TabDefs.TRADE;
		} 

		String[][] tabDef = null;		
		try {
			tabDef = MgbServiceFactory.getService().getStornoGroupFromTrade(getTradeIdListModel().getSelectedId()).booleanValue() ? TabDefs.STORNO_TRADE : TabDefs.TRADE;
		} catch (Exception e) {
			tabDef = TabDefs.TRADE;
		}

		setProperty(TAB_DEF, tabDef);		
		return tabDef;
	}
	
	public TradeIdListModel getTradeIdListModel() {
		if (!(getBusinessObject() instanceof TradeIdListModel)) {
			return null;
		}
		
		return (TradeIdListModel) getBusinessObject();
	}


	/**
	 * Sets the business object where the model gets its data from.
	 * 
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		if (getTradeIdListModel() != null) {
			getTradeIdListModel().removePropertyChangeListener(this);
		}
		
		super.setBusinessObject(newBusinessObject);
		
		if (getTradeIdListModel() != null) {
			getTradeIdListModel().addPropertyChangeListener(this);
		}
		
		fillModel();
	}
	
	public void updateTitle() {
		TradeIdListModel tradeIdListModel = getTradeIdListModel();
		if (tradeIdListModel == null) {
			return;
		}		

		String title = getResourceString("TMV_L_TITLE");
		title = StringUtils.replace(title, "<id>", "" + tradeIdListModel.getSelectedId());
		title = StringUtils.replace(title, "<n>", "" + (tradeIdListModel.getSelectedIndex() + 1));
		title = StringUtils.replace(title, "<m>", "" + tradeIdListModel.size());
		
		setProperty(TITLE, title);
		
	}
	
	public String[][] getTabDef() {
		return (String[][])getProperty(TAB_DEF);	
	}
	
	
    /**
     * @see de.westlb.mgb.client.ui.keylistmodel.IdListModel#getSelectedId()
     */
    @Override
    public Serializable getSelectedId() {
    	return getTradeIdListModel() == null ? null : getTradeIdListModel().getSelectedId();
    }

    /**
     * @see de.westlb.mgb.client.ui.keylistmodel.IdListModel#getSelectedIndex()
     */
    @Override
    public int getSelectedIndex() {
    	return getTradeIdListModel() == null ? -1 : getTradeIdListModel().getSelectedIndex();
    }

    /**
     * @see de.westlb.mgb.client.ui.keylistmodel.IdListModel#moveTo(int)
     */
    @Override
    public boolean moveTo(int i) {
    	return getTradeIdListModel() == null ? false : getTradeIdListModel().moveTo(i);
    }

    /**
     * @see de.westlb.mgb.client.ui.keylistmodel.IdListModel#size()
     */
    @Override
    public int size() {
     	return getTradeIdListModel() == null ? 0 : getTradeIdListModel().size();
   }

    /**
     * @see java.beans.PropertyChangeListener#propertyChange(PropertyChangeEvent)
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
        if (evt.getSource() == getTradeIdListModel()) {
        	updateTitle();
        	updateTabDef();	
        }
    }

}
