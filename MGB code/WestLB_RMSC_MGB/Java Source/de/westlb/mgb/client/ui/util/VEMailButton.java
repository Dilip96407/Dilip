package de.westlb.mgb.client.ui.util;

import de.westlb_systems.xaf.swing.SIcon;
import de.westlb_systems.xaf.ui.components.VLinkButton;
import de.westlb_systems.xaf.ui.misc.IconKatalog;


/**
 * @author: Manfred Boerner
 */
public class VEMailButton extends VLinkButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3479967776056133919L;
	private SIcon enabledIcon  = null;
	private SIcon disabledIcon = null;
	
	public VEMailButton() {
		this(null);
	}

	public VEMailButton(String newText) {
		super(newText);
		init();
	}
	
    /**
     * Insert the method's description here.
     */
    private void init() {
        enabledIcon = IconKatalog.getInstance().getIcon("EMAIL");
        disabledIcon = IconKatalog.getInstance().getIcon("empty");
        setDisabledIcon(disabledIcon);
        setEnabled(true);
    }
    
	/**
	 * Insert the method's description here.
	 * @param enabled boolean
	 */
	@Override
    public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		setIcon(enabled ? enabledIcon : disabledIcon);
	}
 
}
