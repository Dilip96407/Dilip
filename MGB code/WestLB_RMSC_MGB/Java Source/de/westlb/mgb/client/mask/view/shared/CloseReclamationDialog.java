package de.westlb.mgb.client.mask.view.shared;

import java.awt.Dimension;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.CloseReclamationModel;
import de.westlb.mgb.client.mask.model.shared.NewTradeReclamationModel;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.STextArea;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.EditDialog;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;


/**
 * @author WSY4148
 *
 * Dialog to enter a new manual state of a trade.
 */
public class CloseReclamationDialog extends AbstractView implements EditDialog {
	/**
     * 
     */
    private static final long serialVersionUID = 2074685073794218360L;
    /**
	 * PropertyHandler for the model view synchronization.
	 */
	private static final Dimension commentSize = new Dimension(250,100);
	private String RESOURCE_BASE = getResourceBase();
	
	private PropertyHandler propertyHandler = new PropertyHandler();
	
	private SLabel lComment				= new SLabel();	
	private STextArea taComment		= new STextArea();

	
	/**
	 * Constructor for CheckStateView.
	 */
	public CloseReclamationDialog() {
		try {
			initComponents();
			initLayout();
			initLabels();
			initProperties();
		} catch (Exception ex) {
			Debug.log(Debug.ERROR, this, "Exception in View-Constuctor");
			Debug.log(ex);
			ex.printStackTrace();
		}
	}
	
	/**
	 * Constructor which sets the model
	 */
	public CloseReclamationDialog(Model model) {
		this();
		setModel(model);
	}
	
    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (!(getModel() instanceof NewTradeReclamationModel)) {
            return;
        }
        propertyHandler.syncFields();
    }
	
    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(NewTradeReclamationModel.COMMENT, 			taComment);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
		lComment.setText		(getResourceString(RESOURCE_BASE + "L_COMMENT"));
    }


    /**
     * Method initLayout.
     */
    private void initLayout() {
    	add(lComment, 			new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8800, 0, 0));
    	add(taComment,	 		new GridBagConstraints2(0, 1, 1, 1, 1.0, 1.0, NWEST, BOTH, insets8800, 0, 0));

		taComment.setMinimumSize(commentSize);
		taComment.setPreferredSize(commentSize);
		
		setBackgroundForAllPanels(UIManager.getColor("controlBackground"));
    }


    /**
     * Method initComponents.
     */
    private void initComponents() {
    }


	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof CloseReclamationModel)) {
	        throw new IllegalArgumentException("Model not instance of CloseReclamationModel");
	    }
	    propertyHandler.setModel(newModel);
	    super.setModel(newModel);
	    fillView();
	}
}
