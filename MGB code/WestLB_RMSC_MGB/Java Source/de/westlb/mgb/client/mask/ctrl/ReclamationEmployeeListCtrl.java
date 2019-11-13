package de.westlb.mgb.client.mask.ctrl;

import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.model.shared.ReclamationEmployeeListModel;
import de.westlb.mgb.client.mask.view.shared.ReclamationEmployeeListView;
import de.westlb.mgb.client.reports.ReportManager;
import de.westlb_systems.xaf.application.event.ContentRequestEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.ui.ctrl.Ctrl;

/**
 * @author WSY4148
 *
 * Handels user events fired by EmployeeListView
 */
public class ReclamationEmployeeListCtrl extends Ctrl {
     @Override
    public void actionRequested(UserEvent ue) {
     	ReclamationEmployeeListView view = null;
     	ReclamationEmployeeListModel model = null;
     	
     	if (ue.getSource() instanceof ReclamationEmployeeListView) {
     		view = (ReclamationEmployeeListView)ue.getSource();
     		model = view.getReclamationEmployeeListModel();
     	}
     	
     	if (view == null || model == null) {
     		return;
     	}
     	 
     	switch (ue.getAction()) {
     		case ReclamationEmployeeListView.EV_PREVIEW:
				startPreview(view, model);
				break;
			case ReclamationEmployeeListView.EV_REMARK_RECL:
				int msgType = ReclamationEmployeeListView.SHOW_QUESTION;
				String msg = view.getResourceString(view.RESOURCE_BASE + "Q_CONFIRM_REMARK_RECL");
				if (view.showMessageBox(msgType, msg)) {
					remarkReclamation(view, model);
				}
				break;
     	}
     }
     
     public void startPreview(ReclamationEmployeeListView view, ReclamationEmployeeListModel model) {
     	int[] rows = view.getSelectedRows();
     	for (int i = 0; i < rows.length; i++) {
     		Object[] parameters = new Object[2];
     		parameters[0] = Integer.valueOf(ReportManager.REPORT_TRADER_RECLAMATION);
     		parameters[1] = new Long[] { model.getEmployeeId(rows[i]) };
     		fireContentRequestet(new ContentRequestEvent(this, ContentID.MGB_REPORT, parameters));
     	}     	
     }
     
     public void remarkReclamation(ReclamationEmployeeListView view, ReclamationEmployeeListModel model) {
		model.doRemarkReclamation(view.getSelectedRows());
		if (model.getErrorDetails() != null) {
			view.showError(model.getErrorDetails());	
		}
     }
}
