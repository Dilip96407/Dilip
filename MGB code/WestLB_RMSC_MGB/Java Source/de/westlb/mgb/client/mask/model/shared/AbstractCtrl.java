/*
 * Created on Jan 30, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.mask.model.shared;

import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;
import de.westlb_systems.xaf.ui.view.View;

/**
 * @author WSY4148
 *
 * Folgendes auswählen, um die Schablone für den erstellten Typenkommentar zu ändern:
 * Fenster&gt;Benutzervorgaben&gt;Java&gt;Codegenerierung&gt;Code und Kommentare
 */
public abstract class AbstractCtrl extends Ctrl {
	
    public void refresh(Object source) {
        if (source instanceof View) {
            View view = (View) source;
            if (view.getModel() != null) {
				boolean oldC = Synchronizer.setWaitCursor(true, view);
                view.getModel().reload();
                view.refresh();
				Synchronizer.setWaitCursor(oldC, view);
            }
        }
    }
}
