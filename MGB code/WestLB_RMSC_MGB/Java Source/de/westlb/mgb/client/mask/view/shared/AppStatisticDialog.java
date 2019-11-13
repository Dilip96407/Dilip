package de.westlb.mgb.client.mask.view.shared;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.AppStatisticModel;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.STable;
import de.westlb_systems.xaf.swing.STableEditorEvent;
import de.westlb_systems.xaf.swing.STableEditorListener;
import de.westlb_systems.xaf.swing.STableSelectionEvent;
import de.westlb_systems.xaf.swing.STableSelectionListener;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.ui.view.TitlePanel;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author WSY4148
 *
 * The view to show statistic of the application.
 */
public class AppStatisticDialog extends AbstractView {
	/**
     * 
     */
    private static final long serialVersionUID = 4250939693727281732L;

    private String RESOURCE_BASE = getResourceBase();

	/** gui components */
	private TitlePanel pTitle 			= new TitlePanel();
	private STable tResult				= new STable();
	private Dimension minSize = new Dimension(460, 190);	
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	/** Listener fuer alle Events */
	private Listener listener = new Listener();
	
	/**
	 * Listener fuer alle Events
	 */
	private static class Listener implements STableSelectionListener, STableEditorListener, ActionListener {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
        }
        /**
         * @see de.westlb_systems.xaf.swing.STableSelectionListener#selectionChanged(STableSelectionEvent)
         */
        @Override
        public void selectionChanged(STableSelectionEvent e) {
        }

        /**
         * @see de.westlb_systems.xaf.swing.STableEditorListener#invokeEditor(STableEditorEvent)
         */
        @Override
        public void invokeEditor(STableEditorEvent e) {
		}

	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public AppStatisticDialog() {
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
	public AppStatisticDialog(Model model) {
		this();
		setModel(model);
	}
	

    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getAppStatisticModel() == null) {
            return;
        }
        propertyHandler.syncFields();
    }
    
    /**
	 * Return casted model
	 * 
	 */
	public AppStatisticModel getAppStatisticModel() {
		return getModel() instanceof AppStatisticModel ? (AppStatisticModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(AppStatisticModel.P_USER_STATISTIC_TABLE_MODEL,	tResult);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	this.setTitle			(getResourceString(RESOURCE_BASE + "T_001"));
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;
		setMinimumSize(minSize);
				
		// Layout total view
		l = 0; c = 0; 
	 	this.add(pTitle,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));	 	
	 	this.add(tResult,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 1.0, CENTER, BOTH, insets8888, 0, 0));	 	
    }	

    /**
     * Method initComponents.
     */
    private void initComponents() {
		pTitle.setHeaderTitleColor(UIManager.getColor("StateCodeList.foreground"));
		pTitle.setColorProgress(
			UIManager.getColor("StateCodeList.titleBackground1"), 
			UIManager.getColor("StateCodeList.titleBackground2"));
	
//		tResult.setPopupProvider(
//			STable.ADD_PROVIDER |
//			STable.OPEN_PROVIDER|
//			STable.EDIT_PROVIDER|
//			STable.REMOVE_PROVIDER
//		
//		);
		tResult.addTableEditorListener(listener);
		tResult.addSTableSelectionListener(listener);
		tResult.setGridEnabled(true);

    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof AppStatisticModel)) {
	        throw new IllegalArgumentException("Model not instance of AppStatisticModel");
	    }
	    propertyHandler.setModel(newModel);
	    super.setModel(newModel);
	    fillView();
	}

     
     /**
      * Update the current content
      */
    @Override
    public void refresh() {
        fillView();
    }	
	/**
	 * Titel setzen
	 *
	 */
	@Override
    public void setTitle(String title) {
		pTitle.setHeaderTitel(title);
		super.setTitle(title);
	}

	
	/**
	 * Updates all language dependend labels
	 */
	@Override
    public void updateLabels() {
		initLabels();
	}
}
