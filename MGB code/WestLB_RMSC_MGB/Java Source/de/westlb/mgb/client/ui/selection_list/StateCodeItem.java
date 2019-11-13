package de.westlb.mgb.client.ui.selection_list;


import de.westlb.mgb.client.server.vo.StateCodeVo;


/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class StateCodeItem implements SelectionListItem {
	private StateCodeVo stateCodeVo;
	
	StateCodeItem(StateCodeVo stateCodeVo) {
		this.stateCodeVo = stateCodeVo;
	}
	
	public StateCodeVo getStateCodeVo() {
		return stateCodeVo;
	}
	
	@Override
    public String toString() {
		return stateCodeVo.getShortDescription();
	}
}