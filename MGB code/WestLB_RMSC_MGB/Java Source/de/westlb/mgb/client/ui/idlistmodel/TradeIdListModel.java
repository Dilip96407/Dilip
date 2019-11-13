package de.westlb.mgb.client.ui.idlistmodel;

/**
 * @author wsy4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class TradeIdListModel extends AbstractIdListModel {

	public TradeIdListModel(Object[] data) {
		super(data);
	}
	
    /**
     * @see de.westlb.mgb.client.ui.keylistmodel.AbstractKeyListModel#getKeyProperty()
     */
    @Override
    public String getIdPropertyName() {
        return "id";
    }

}
