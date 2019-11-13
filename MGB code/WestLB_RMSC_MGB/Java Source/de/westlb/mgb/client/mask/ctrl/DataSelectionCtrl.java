package de.westlb.mgb.client.mask.ctrl;

import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;

public class DataSelectionCtrl extends Ctrl {

    public DataSelectionCtrl() {
    }
    
    /**
     * Handels user events fired by DataSelectionView
     */
    @Override
    public void actionRequested(UserEvent ue) {
    	// to be varified
//        if (ue.getAction() == DataSelectionView.DO_SELECTION) {
//            fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_CHECKSTATE, null));
//        }
    }
}
