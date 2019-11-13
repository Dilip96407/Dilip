package de.westlb.mgb.client.mask.view.shared;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.UIManager;

import de.westlb.mgb.client.application.ApplicationComponentBox;
import de.westlb.mgb.client.application.ContentID;
import de.westlb.mgb.client.mask.model.shared.DataSelectionModel;
import de.westlb_systems.xaf.application.ContentSet;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SBorder;
import de.westlb_systems.xaf.swing.SBorderFactory;
import de.westlb_systems.xaf.swing.SIcon;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.ui.components.VComboBox;
import de.westlb_systems.xaf.ui.components.VLinkButton;
import de.westlb_systems.xaf.ui.misc.IconKatalog;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.DialogViewer;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;

/**
 * Panel for data selection
 *
 * @author: Manfred Boerner
 */
/* 
 * Creation date: (9/14/00 9:46:03 AM)
 * @owner: Herbert Benkhoff
 */
public class DataSelectionView extends AbstractView {
	/**
     * 
     */
    private static final long serialVersionUID = -9028448094149468782L;

    private String RESOURCE_BASE = getResourceBase();

	public static final int DO_SELECTION = 1;

	// Layout Komponenten
	private Object[] defModel = new String[] { " " };
	private SIcon iconEdit = IconKatalog.getInstance().getIcon("EDIT");

	private SLabel lMandant = new SLabel();
	private VComboBox coMandant = new VComboBox(defModel);
	private SLabel lJobs = new SLabel();
	private STextField tfJobs = new STextField();
	private SBorder border = SBorderFactory.createEmptyBorder(insets0000);

	private SPanel pNorth = new SPanel();	
	
	private VLinkButton bJobs = new VLinkButton();

	private PropertyHandler propertyHandler = new PropertyHandler();

	private Listener listener = new Listener();

	private class Listener implements PropertyChangeListener, ActionListener {
		public Listener() {
		};
		@Override
        public void actionPerformed(ActionEvent ev) {
			if (ev.getSource() == coMandant) {
				changeMandant();
			} else if (ev.getSource() == bJobs) {
				editJobs();	
			}
		}
        /**
         * @see java.beans.PropertyChangeListener#propertyChange(PropertyChangeEvent)
         */
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
        	if (DataSelectionModel.P_SELECTED_JOBS_TEXT.equals(evt.getPropertyName())) {
        		propertyHandler.syncFields();
        	}
        }

	}
	/**
	 * Default Konstruktor
	 */
	public DataSelectionView() {
		try {
			initComponents();
			initLayout();
			initLabels();
		} catch (Exception ex) {
			Debug.log(Debug.ERROR, this, "Exception in View-Constuctor");
			Debug.log(ex);
		}
	}
	
	/**
	 * Felder des Views mit Werten aus dem Model fuellen
	 */
	private void fillView() {
		if (getModel() == null) {
			return;
		}
		propertyHandler.syncFields();
	}

	private DataSelectionModel getDataSelectionModel() {
		return getModel() instanceof DataSelectionModel ? (DataSelectionModel)getModel() : null;
	}
	
	public void setTextColor(Color c) {
		lMandant.setForeground(c);
		lJobs.setForeground(c);
	}
	
	public void setBackgroundColor(Color c) {
		super.setBackground(c);
		pNorth.setBackground(c);	
	}
	
	/**
	 * Initialisierung
	 *
	 */
	private void initComponents() {
		tfJobs.setEnabled(false);
		bJobs.setEnabled(true);
		bJobs.setValue(iconEdit);

		bJobs.addActionListener(listener);

	 	// Initialisierung der Properties
		propertyHandler.add(DataSelectionModel.P_MANDANT_CO_MODEL, coMandant);
		propertyHandler.add(DataSelectionModel.P_MANDANT, coMandant);
		propertyHandler.add(DataSelectionModel.P_SELECTED_JOBS_TEXT, tfJobs);
	}

	/**
	 * Setzen aller Label Texte
	 *
	 */
	private void initLabels() {
		// lTitle.setText(getResourceString("L_TITLE"));
		lMandant.setText(getResourceString(RESOURCE_BASE + "L_MANDANT"));
		coMandant.setToolTipText(getResourceString(RESOURCE_BASE + "L_MANDANT_TIP"));
		lJobs.setText(getResourceString(RESOURCE_BASE + "L_JOBS"));
		tfJobs.setToolTipText(getResourceString(RESOURCE_BASE + "L_JOBS_TIP"));
	}

	/**
	 * Erstellen des Layouts
	 *
	 * @exception Exception allgemeiner Fehler
	 */
	private void initLayout() throws Exception {
		this.setBorder(border);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentX(LEFT_ALIGNMENT);
		
		// Line 0
		pNorth.add(lMandant,		new GridBagConstraints2(0, 0, 1, 1, 0.0, 0.0, WEST, NONE, insets4808, 0, 0));
		pNorth.add(coMandant,		new GridBagConstraints2(1, 0, 2, 1, 1.0, 0.0, WEST, HORIZ, insets4808, 0, 0));
		
		// Line 1
		pNorth.add(lJobs,			new GridBagConstraints2(0, 1, 1, 1, 0.0, 0.0, WEST, NONE, insets4808, 0, 0));
		pNorth.add(tfJobs,			new GridBagConstraints2(1, 1, 1, 1, 1.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
		pNorth.add(bJobs,			new GridBagConstraints2(2, 1, 1, 1, 0.0, 0.0, WEST, NONE, insets4408, 0, 0));
		
		this.add(pNorth);
		this.add(Box.createRigidArea(new Dimension(0, 4)));
		
		setBackgroundForAllPanels(UIManager.getColor("DetailsPanel.background"));
	}
	
	/**
	 * Setzen des Default Focus
	 *
	 * @return true
	 */
//	public boolean requestDefaultFocus() {
//		cbSourceSystem.requestFocus();
//		return true;
//	}

	/**
	 * Setzen des View-Models
	 */
	@Override
    public void setModel(Model newModel) {
		if (getModel() != null) {
			getModel().removePropertyChangeListener(listener);
		}
		
		propertyHandler.setModel(newModel);
		super.setModel(newModel);

		coMandant.removeActionListener(listener);
		fillView();
		coMandant.addActionListener(listener);

		if (newModel != null) {
			newModel.addPropertyChangeListener(listener);
		}
	}

	/**
	 * Start data selection
	 */
	private boolean changeMandant() {
		DataSelectionModel model = getDataSelectionModel();
		Object oldValue = model.getProperty(DataSelectionModel.P_MANDANT);
		if (selectData()) {
			// Fire property change event to enable application control
			// to clear caches and reset gui controls.
			Object newValue = model.getProperty(DataSelectionModel.P_MANDANT);
			firePropertyChange(DataSelectionModel.P_MANDANT, oldValue, newValue);	
			return true;		
		}

		return false;
	}
	
	private void editJobs() {
		JobSelectionDialog jobSelectionDialog = new JobSelectionDialog(getDataSelectionModel());
		jobSelectionDialog.setPreferredSize(new Dimension(500,300));
		Model model = getModel();
		if (model == null) {
			return;
		}
		model.reload();
		model.removePropertyChangeListener(listener);
		showDialog(ContentID.MGB_JOBS_SELECTION_DIALOG, getModel(), this);
		model.addPropertyChangeListener(listener);
	}
	
	/**
	 * Display a dialog
	 * 
	 * @param contentID int
	 * @param parameter java.lang.Object
	 * @param view Owner Componente
	 */
	private boolean showDialog(String contentID, Object parameter, Component view) {
		
		ApplicationComponentBox box = new ApplicationComponentBox();
	
		// Dialog starten
		if (contentID != null) {
			ContentSet set = box.createContentSet(contentID, parameter, view);
			if (set != null && set.getViewDialog() instanceof DialogViewer) {
				return ((DialogViewer) set.getViewDialog()).showDialog();
			}
		}
		return false;
	}
	
	

	/**
	 * Schnellsuche starten
	 *
	 */
	private boolean selectData() {

		// Sicher gehen dass Model aktuell ist
		propertyHandler.syncModel();

		// Model speichern und Event feuern
		if (getModel() != null) {
			Model model = getModel();
			if (model.saveModel()) {
				fireUserEvent(new UserEvent(this, DO_SELECTION, model.getBusinessObject()));
				return true;
			}
            handleModelError();
		}

		return false;
	}
	/**
	 * Update aller Label Texte
	 *
	 */
	@Override
    public void updateLabels() {
		initLabels();
	}
}
