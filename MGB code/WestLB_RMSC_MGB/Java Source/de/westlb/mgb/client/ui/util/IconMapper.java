package de.westlb.mgb.client.ui.util;

import de.westlb_systems.xaf.swing.SIcon;
import de.westlb_systems.xaf.ui.misc.IconKatalog;

/**
 * @author wsy4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class IconMapper {
	private static IconMapper instance = new IconMapper();  
	private SIcon errorIcon;

	private IconMapper() {
		super();
		errorIcon = IconKatalog.getInstance().getIcon("error");        
	}
	
    public static IconMapper getInstance() {
    	return instance;
    }
    
    /**
     * Returns an icon for a filename (calculation based on
     * the extension.
     */
    public SIcon getIconForFileName(String fileName) {
    	if (fileName == null) {
    		return null;
    	}

    	SIcon icon = null;    	
    	int iDot = fileName.lastIndexOf(".");
    	
    	if (iDot > 0) {
			String extension = fileName.substring(iDot+1).toUpperCase();
			icon = IconKatalog.getInstance().getIcon("ATTACHMENT_" + extension);
			if (icon == null || icon == errorIcon) {
				icon = 	IconKatalog.getInstance().getIcon("ATTACHMENT_NULL");
			}
			icon.setIconText(extension);
    	} 
    	
     	return icon;
    }

}
