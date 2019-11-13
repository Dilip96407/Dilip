package de.westlb.mgb.client.ui.idlistmodel;

import java.io.Serializable;

/**
 * @author WSY4148
 *
 * Model for an edit list which holds a list of primary key and 
 * keeps track of the current position.
 */
public interface IdListModel {

	public Serializable getSelectedId();
	
	public int getSelectedIndex();
	
	public boolean moveTo(int i);
	
	public int size();	
}
