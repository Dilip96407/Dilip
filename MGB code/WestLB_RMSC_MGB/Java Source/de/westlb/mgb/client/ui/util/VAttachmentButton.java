package de.westlb.mgb.client.ui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.westlb.mgb.client.mask.view.shared.FileHandler;
import de.westlb_systems.xaf.swing.SIcon;
import de.westlb_systems.xaf.ui.components.VLinkButton;
import de.westlb_systems.xaf.ui.misc.IconKatalog;


/**
 * @author: Manfred Boerner
 */
public class VAttachmentButton extends VLinkButton implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4176782945588988296L;
	private SIcon enabledIcon  = null;
	private SIcon disabledIcon = null;
	
	private AttachmentModel attachmentModel = null;

	public VAttachmentButton() {
		this(null);
	}

	public VAttachmentButton(String newText) {
		super(newText);
		init();
	}
	
	/**
	 * Start download of the attached file from the database.
	 */
	@Override
    public void actionPerformed(ActionEvent event) {
		boolean enabled = this.isEnabled();
		
		setDisabledIcon(null);
		this.setEnabled(false);
		
		try{
			if(attachmentModel != null) {
				try {
					FileHandler fileSupport = new FileHandler(null);
					fileSupport.openFile(attachmentModel.getId(), attachmentModel.getFileName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} finally {
			this.setEnabled(enabled);
			setDisabledIcon(disabledIcon);
		}
	}
	
    /**
     * Insert the method's description here.
     */
    private void init() {
        enabledIcon = IconKatalog.getInstance().getIcon("ATTACHMENT");
        disabledIcon = IconKatalog.getInstance().getIcon("empty");
        setDisabledIcon(disabledIcon);
        setEnabled(false);
        addActionListener(this);
    }
    
    /**
     * @see de.westlb_systems.xaf.swing.SComponent#setValue(Object)
     */
    @Override
    public void setValue(Object o) {
    	if (!(o instanceof AttachmentModel) || 
    		( (AttachmentModel)o).getFileName() == null || ((AttachmentModel)o).getId() == null) 	{
    		setEnabled(false);
    		setIcon(disabledIcon);
 	        return;
    	}
    		  	
		this.attachmentModel = (AttachmentModel)o; 
		setEnabled(true);
		setIcon(enabledIcon);
    	super.setValue(enabledIcon);
    }

}
