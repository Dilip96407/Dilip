package de.westlb.mgb.client.mask.view.shared;

import de.westlb.mgb.client.application.ApplicationDefinitions;
import de.westlb.mgb.client.application.MayBeRefreshSupport;
import de.westlb_systems.xaf.application.ContentSet;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.MultiView;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class AbstractMultiView extends MultiView implements MayBeRefreshSupport {
	
    /**
     * 
     */
    private static final long serialVersionUID = 7419645576879374838L;

    /**
     * Constructor for AbstractMultiView.
     */
    public AbstractMultiView() {
        super();
    }

    /**
     * Constructor for AbstractMultiView.
     * @param model
     */
    public AbstractMultiView(Model model) {
        this();
        setModel(model);
    }

	/*
	 * Delegates the mayBeRefresh request to the current
	 * visible ViewContent of the MultiView.
	 */    
    @Override
    public void mayBeRefresh() {
		ContentSet contentSet = getContentSetAt(getSelectedIndex());
		if (contentSet.getViewContent() instanceof MayBeRefreshSupport) {
			((MayBeRefreshSupport)contentSet.getViewContent()).mayBeRefresh();
		}
    }
    
    /**
	 * Returnes the name of the class without package prefix as
	 * name for the resource file.
	 */
	@Override
    public String getResourceName() {
		String className = getClass().getName();
		String name = className.substring(className.lastIndexOf(".") + 1);
		if (name.endsWith("View")) {
			name = name.substring(0, name.length()-4);
		} else if (name.endsWith("Dialog")) {
			name = name.substring(0, name.length()-6);
		}
		
		name = ApplicationDefinitions.LABEL_PATH + name;
		
		return name;			
	}

}
