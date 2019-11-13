package de.westlb.mgb.client.mask.view.shared;

import javax.swing.ButtonGroup;

import de.westlb.mgb.client.mask.model.shared.ReclamationLevelPanelModel;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.ui.components.VRadioButton;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;


/**
 * @author M. Boerner
 *
 * View to display the statistic of trade checking.
 */
public class ReclamationLevelPanelView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2260597790068504834L;

	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	private VRadioButton rbLevel1 = new VRadioButton();
	private VRadioButton rbLevel2 = new VRadioButton();
	private VRadioButton rbLevel3 = new VRadioButton();
	private VRadioButton rbLevel4 = new VRadioButton();

	/**
	 * Constructor for CheckStateView.
	 */
	public ReclamationLevelPanelView() {
		try {
			initComponents();
			initLabels();
			initLayout();
			initProperties();
		} catch (Exception ex) {
			Debug.log(Debug.ERROR, this, "Exception in View-Constuctor");
			Debug.log(ex);
			ex.printStackTrace();
		}
	}

	/**
	 * Constructor for CheckStateView.
	 * @param model
	 */
	public ReclamationLevelPanelView(Model model) {
		super(model);
	}

	/**
	 * Felder des Views mit Werten aus dem Model fuellen
	 */
	private void fillView() {
		if (getModel() == null)
			return;
		propertyHandler.syncFields();
	}

	/**
	 * Method initComponents.
	 */
	private void initComponents() {
		setOpaque(false);

		ButtonGroup group = new ButtonGroup();
		group.add(rbLevel1);
		group.add(rbLevel2);
		group.add(rbLevel3);
		group.add(rbLevel4);
        setEnabled(false);
	}

    @Override
    public void setEnabled(boolean enabled) {
        rbLevel1.setEnabled(enabled);		
        rbLevel2.setEnabled(enabled);		
        rbLevel3.setEnabled(enabled);		
        rbLevel4.setEnabled(enabled);		
    }

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		rbLevel1.setText(getResourceString("TRV_L_LEVEL1"));
		rbLevel2.setText(getResourceString("TRV_L_LEVEL2"));
		rbLevel3.setText(getResourceString("TRV_L_LEVEL3"));
		rbLevel4.setText(getResourceString("TRV_L_LEVEL4"));
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayout() {		
		// Reminder level panel
		add(rbLevel1, new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets0000, 0, 0));
		add(rbLevel2, new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets0000, 0, 0));
		add(rbLevel3, new GridBagConstraints2(0, 2, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets0000, 0, 0));
		add(rbLevel4, new GridBagConstraints2(0, 3, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets0000, 0, 0));
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		propertyHandler.add(ReclamationLevelPanelModel.P_REM_LEVEL_1,		rbLevel1);
		propertyHandler.add(ReclamationLevelPanelModel.P_REM_LEVEL_2,		rbLevel2);
		propertyHandler.add(ReclamationLevelPanelModel.P_REM_ESCALATION,	rbLevel3);
		propertyHandler.add(ReclamationLevelPanelModel.P_REM_OUTSIDE_SYS,	rbLevel4);
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof ReclamationLevelPanelModel))
			throw new IllegalArgumentException("Model not instance of ReclamationLevelPanelModel!");
		propertyHandler.setModel(newModel);
		
		super.setModel(newModel);
		fillView();
	}

}
