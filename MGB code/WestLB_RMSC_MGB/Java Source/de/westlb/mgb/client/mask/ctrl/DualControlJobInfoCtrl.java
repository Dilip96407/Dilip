package de.westlb.mgb.client.mask.ctrl;

import java.awt.Component;

import de.westlb.mgb.client.application.ApplicationComponentBox;
import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.view.shared.DualControlJobInfoDialog;
import de.westlb_systems.xaf.application.ContentSet;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;
import de.westlb_systems.xaf.ui.view.DialogViewer;
import de.westlb_systems.xaf.ui.view.View;


/**
 * @author WSY4148
 *
 * Handels user events fired by NewTraderInfoView
 */
public class DualControlJobInfoCtrl extends Ctrl {
	private ApplicationComponentBox componentBox = new ApplicationComponentBox();

		
    @Override
    public void actionRequested(UserEvent ue) {
     	View view  = null; 
  		if (ue.getSource() instanceof View) {
			view = (View)ue.getSource();
  		} else {
  			return;
  		}

     	if (ue.getAction() == DualControlJobInfoDialog.EV_OPEN) {
	        boolean success = showDialog(ContentID.MGB_DUAL_CONTROL_JOB_EDIT, ue.getParameters(),view);
	        if (success) {
	        	view.getModel().reload();
	        }
     	}      	
    }
    
	/**
	 * Display a dialog
	 * 
	 * @param contentID int
	 * @param parameter java.lang.Object
	 * @param view Owner Componente
	 */
	private boolean showDialog(String contentID, Object parameter, Component view) {
		
		ApplicationComponentBox box = componentBox;
	
		// Dialog starten
		if (contentID != null) {
			ContentSet set = box.createContentSet(contentID, parameter, view);
			if (set != null && set.getViewDialog() instanceof DialogViewer) {
				return ((DialogViewer) set.getViewDialog()).showDialog();
			}
		}
		return false;
	}
}
