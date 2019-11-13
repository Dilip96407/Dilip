package de.westlb.mgb.client.mask.view.shared;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.TradeReclamationModel;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.client.ui.util.VDateField;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SButton;
import de.westlb_systems.xaf.swing.SCheckBox;
import de.westlb_systems.xaf.swing.SGridLayout;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.swing.STextArea;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.ui.view.TablePanel;
import de.westlb_systems.xaf.util.Debug;


/**
 * @author M. Boerner
 *
 * View to display the reclamation information of a trade.
 * 
 */
public class TradeReclamationView extends AbstractView {
	// MenuItems
//	private ActionSet
//		newResultAction = new ActionSet("EDIT_NEW_RESULT", true);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1716898235173578730L;
	/* Constant definitions in UserEvents */
	public static final int NEW_RECLAMATION		= 1;
	public static final int CLOSE_RECLAMATION	= 2;
	
	/** Listener for all events */
	private Listener listener = new Listener();
	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	/** Universal Listener fuer alle Events die im View auftreten */
	private class Listener implements ActionListener {
		public Listener() {
		}

		@Override
        public void actionPerformed(ActionEvent ae) {
			Object source = ae.getSource();
			if (source == bNewReclamation) {
				newReclamation();	
			} else if (source == bCloseReclamation) {
				closeReclamation();	
			}
		}
	}

   	private int GAP = 8;
	private Insets insets1 = new Insets(4, 8, 0, 8);
	private Insets insets2 = new Insets(4, 8, 8, 8);

	// UI - Components
	private TablePanel tpReclamation = new TablePanel();
	private SPanel pReclamation = new SPanel();
	private SPanel pButtons = new SPanel();
	
	private ReclamationLevelPanelView pReclamationLevel = new ReclamationLevelPanelView();
		
	private SLabel lReason = new SLabel();
	private SLabel lSenderName = new SLabel();
	private SLabel lSendDate = new SLabel();
	private SLabel lLevel = new SLabel();
	private SLabel lComment = new SLabel();
	private SLabel lClosed = new SLabel();
	private SLabel lClosedComment = new SLabel();

	private SCheckBox cbClosed			= new SCheckBox();
	
	private STextField tfResaon			= new STextField(DEF_COL_12);	
	private STextField tfSenderName		= new STextField(DEF_COL_10);
	private STextField tfSendDate		= new VDateField(DEF_COL_10, DateFormat.TIME_FORMAT_LONG);	
	
	private STextArea taComment = new STextArea();
	private STextArea taClosedComment = new STextArea();	
	
 	private SButton bNewReclamation = new SButton();
 	private SButton bCloseReclamation = new SButton();

	/**
	 * Constructor for TradeReclamationView.
	 */
	public TradeReclamationView() {
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
	 * Constructor for TradeReclamationView.
	 * @param model
	 */
	public TradeReclamationView(Model model) {
		super(model);
	}

    /**
     * Method closeReclamation.
     */
    private void closeReclamation() {
		fireUserEvent(new UserEvent(this, CLOSE_RECLAMATION, getModel().getBusinessObject()));
    }


    /**
     * Method newReclamation.
     */
    private void newReclamation() {
		fireUserEvent(new UserEvent(this, NEW_RECLAMATION, getModel().getBusinessObject()));
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
		tpReclamation.setTable(pReclamation);
		
		pButtons.setOpaque(false);

		pReclamationLevel.setEnabled(false);
				
		bNewReclamation.addActionListener(listener);
		bCloseReclamation.addActionListener(listener);
		
//		setActions(new ActionSet[]{ newResultAction });
	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		tpReclamation.setText	(getResourceString("L_HEADER_RECLAMATION"));
		lClosed.setText			(getResourceString("L_CLOSED"));
		lClosedComment.setText	(getResourceString("L_CLOSED_COMMENT"));
		lComment.setText		(getResourceString("L_COMMENT"));
		lLevel.setText			(getResourceString("L_LEVEL"));
		lReason.setText			(getResourceString("L_REASON"));
		lSendDate.setText		(getResourceString("L_SEND_DATE"));
		lSenderName.setText		(getResourceString("L_SENDER_NAME"));
		
		bCloseReclamation.setText(getResourceString("B_CLOSE_RECLAMATION"));
		bNewReclamation.setText(getResourceString("B_NEW_RECLAMATION"));

		taComment.setValue("Default Content");		
	}

	/**
	 * Method initLayout.
	 */
	private void initLayout() {
		// Button panel
		SGridLayout gridLayout = new SGridLayout();
		gridLayout.setHorizontalSplitPosition(0);
		gridLayout.setHgap(GAP);
		
		pButtons.setLayout(gridLayout);
		pButtons.add(bNewReclamation);
		pButtons.add(bCloseReclamation);
		
		// Reclamation line 1
		pReclamation.add(lReason, 			new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets1, 0, 0));
		pReclamation.add(lSenderName,		new GridBagConstraints2(1, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets1, 0, 0));
		pReclamation.add(lSendDate, 		new GridBagConstraints2(2, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets1, 0, 0));
		pReclamation.add(lLevel, 			new GridBagConstraints2(3, 0, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets1, 0, 0));

		// Reclamation line 2
		pReclamation.add(tfResaon, 			new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets2, 0, 0));
		pReclamation.add(tfSenderName,		new GridBagConstraints2(1, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets2, 0, 0));		
		pReclamation.add(tfSendDate, 		new GridBagConstraints2(2, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets2, 0, 0));
		pReclamation.add(pReclamationLevel,	new GridBagConstraints2(3, 1, 1, 3, 1.0, 0.0, NWEST, HORIZ, insets2, 0, 0));

		// Reclamation line 3
		pReclamation.add(lComment, 			new GridBagConstraints2(0, 2, 3, 1, 0.0, 0.0, NWEST, HORIZ, insets1, 0, 0));
		// Reclamation line 4
		pReclamation.add(taComment, 		new GridBagConstraints2(0, 3, 3, 1, 0.0, 1.0, NWEST, BOTH, insets2, 0, 0));
		// Reclamation line 5
		pReclamation.add(lClosedComment, 	new GridBagConstraints2(0, 4, 3, 1, 0.0, 0.0, NWEST, HORIZ, insets1, 0, 0));
		pReclamation.add(lClosed, 			new GridBagConstraints2(3, 4, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets1, 0, 0));
		// Reclamation line 6
		pReclamation.add(taClosedComment,	new GridBagConstraints2(0, 5, 3, 1, 0.0, 1.0, NWEST, BOTH, insets2, 0, 0));
		pReclamation.add(cbClosed, 			new GridBagConstraints2(3, 5, 1, 1, 0.0, 0.0, NWEST, HORIZ, insets1, 0, 0));

		// Buttons
		add(tpReclamation, 	new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, NWEST, HORIZ, insets1, 0, 0));
		add(pButtons,		new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets2, 0, 0));

		setReadOnly();
		setBackgroundForAllPanels(UIManager.getColor("DetailsPanel.background"));
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;

		propertyHandler.add(TradeReclamationModel.P_CLOSED,			cbClosed, ro);
		propertyHandler.add(TradeReclamationModel.P_CLOSED_COMMENT,	taClosedComment ,ro);		
		propertyHandler.add(TradeReclamationModel.P_COMMENT,		taComment, ro);
		propertyHandler.add(TradeReclamationModel.P_REASON,			tfResaon, ro);
		propertyHandler.add(TradeReclamationModel.P_SEND_DATE, 		tfSendDate, ro);
		propertyHandler.add(TradeReclamationModel.P_SENDER_NAME,	tfSenderName, ro);
	}
	
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof TradeReclamationModel))
			throw new IllegalArgumentException("Model not instance of TradeReclamationModel!");
		propertyHandler.setModel(newModel);
		
		pReclamationLevel.setModel(newModel);
		super.setModel(newModel);
		
		fillView();
	}

}
