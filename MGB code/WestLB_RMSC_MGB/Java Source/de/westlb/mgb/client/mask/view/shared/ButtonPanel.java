package de.westlb.mgb.client.mask.view.shared;

import de.westlb_systems.xaf.swing.SButton;
import de.westlb_systems.xaf.swing.SGridLayout;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author wsy4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ButtonPanel extends SPanel {
   	/**
     * 
     */
    private static final long serialVersionUID = 6118307950563683150L;
    private int GAP = 8;

	/**
	 * Constructor for ButtonPanel.
	 */
	public ButtonPanel() {
		try {
			initLayout();
		} catch (Exception ex) {
			Debug.log(Debug.ERROR, this, "Exception in View-Constuctor");
			Debug.log(ex);
			ex.printStackTrace();
		}
	}

	/**
	 * Method initLayout.
	 */
	private void initLayout() {	
		SGridLayout gridLayout = new SGridLayout();
		gridLayout.setHorizontalSplitPosition(0);
		gridLayout.setHgap(GAP);
		this.setLayout(gridLayout);
	}
	
	public void addButton(SButton button) {
		this.add(button);
	}
}
