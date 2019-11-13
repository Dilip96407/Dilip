package de.westlb.mgb.client.mask.view.shared;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.TradeOvResultPanelModel;
import de.westlb.mgb.client.ui.util.VAttachmentButton;
import de.westlb.mgb.client.ui.util.VCompleteButton;
import de.westlb.mgb.client.ui.util.VEMailButton;
import de.westlb.mgb.client.ui.util.VEditButton;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.STextArea;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author M. Boerner
 *
 * View to display the statistic of trade checking.
 */
public class TradeOvResultPanelView extends AbstractView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8762026522333204939L;

	private String RESOURCE_BASE = getResourceBase();
	
	public static final int EV_NEW_MANUAL_STATE = 1;
	public static final int EV_NEW_RECLAMATION_STATE = 2;
	public static final int EV_SEND_RECL_MAIL = 3;
	public static final int EV_CLOSE_RECL = 4;

	/**
	 * Constant definitions for the layout style of the panel.
	 * LAYOUT_VERTICAL: Optimize for a panel with small width value
	 * LAYOUT_HORIZONTAL: Optimize of a panel with small height value
	 */
	public static final int LAYOUT_VERTICAL = 1;
	public static final int LAYOUT_HORIZONTAL = 2;
	
	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	private static final Dimension commentSize = new Dimension(200,40);
	private int layoutStyle;

	/** Listener for all events */
	private Listener listener = new Listener();

	/** Universal Listener fuer alle Events die im View auftreten */
	private class Listener implements PropertyChangeListener, ActionListener {
        /**
         * @see java.beans.PropertyChangeListener#propertyChange(PropertyChangeEvent)
         */
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
	 		if (TradeOvResultPanelModel.RES_MANUAL_STATE_ATTACHMENT_MODEL == evt.getPropertyName()) {
				syncAttachments();
			} else if (TradeOvResultPanelModel.RES_RECLAMATION_IS_CLOSED == evt.getPropertyName()) {
				reInitLayout();					
			}
        }
        /* (Kein Javadoc)
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
        	Object tradeId = getModel() == null ? null : getModel().getBusinessObject();
           if (e.getSource() == bEditManualState) {
           		fireUserEvent(EV_NEW_MANUAL_STATE, tradeId);
           } else if (e.getSource() == bEditReclState) {
				fireUserEvent(EV_NEW_RECLAMATION_STATE, tradeId);
		   } else if (e.getSource() == bSendReclMail) {
				fireUserEvent(EV_SEND_RECL_MAIL, tradeId);
		   } else if (e.getSource() == bCloseRecl) {
				fireUserEvent(EV_CLOSE_RECL, tradeId);
		   }

        }

	}

	// UI - Components
	private SLabel lAutomaticState = new SLabel();
	private SLabel lManualState = new SLabel();
	private SLabel lReclamationState = new SLabel();
	private SLabel lReclamationLevel = new SLabel();
	private SLabel lManualStateComment = new SLabel();
	private SLabel lManualStateByName = new SLabel();
	private SLabel lReclClosedComment = new SLabel();
	
	private STextField tfAutomaticState = new STextField();
	private STextArea taManualStateComment = new STextArea();
	private STextArea taReclClosedComment = new STextArea();
	
	private STextField tfReclamationLevel = new STextField(DEF_COL_02);
	private STextField tfManualState		= new STextField(DEF_COL_08);
	private STextField tfReclamationState	= new STextField(DEF_COL_08);
	private STextField tfManualStateByName	= new STextField(DEF_COL_08);
	
	private VAttachmentButton bManualStateAttachment = new VAttachmentButton();	
	private VAttachmentButton bOpenReclAttachment = new VAttachmentButton();	
	private VEditButton bEditManualState = new VEditButton();
	private VEMailButton bSendReclMail = new VEMailButton();
	private VEditButton bEditReclState = new VEditButton();
	private VCompleteButton bCloseRecl = new VCompleteButton();
	
	/**
	 * Constructor for CheckStateView.
	 */
	public TradeOvResultPanelView() {
		this(LAYOUT_HORIZONTAL);
	}
	
	public TradeOvResultPanelView(int layoutStyle) {
		try {
			this.layoutStyle = layoutStyle;
			initComponents();
			initLabels();
			initLayout(layoutStyle);
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
	public TradeOvResultPanelView(Model model) {
		super(model);
	}

	/**
	 * Felder des Views mit Werten aus dem Model fuellen
	 */
	private void fillView() {
		if (getModel() == null)
			return;
		reInitLayout();
		propertyHandler.syncFields();
		syncAttachments();
	}
	
	/**
	 * Method initComponents.
	 */
	private void initComponents() {
		this.setBackground(UIManager.getColor("DetailsPanel.background"));
		this.setOpaque(false);
		bEditManualState.addActionListener(listener);
		bSendReclMail.addActionListener(listener);
		bEditReclState.addActionListener(listener);
		bCloseRecl.addActionListener(listener);
		// Background color initialized explicit, because the component is
		// not in the panel when the background for all components is initialized
		// in parent view. 
		bCloseRecl.setBackground(UIManager.getColor("DetailsPanel.background"));
	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		lAutomaticState.setText		(getResourceString(RESOURCE_BASE + "L_AUTO_STATE"));
		lManualState.setText		(getResourceString(RESOURCE_BASE + "L_MANUAL_STATE"));
		lManualStateComment.setText	(getResourceString(RESOURCE_BASE + "L_MANUAL_STATE_COMMENT"));
		lManualStateByName.setText	(getResourceString(RESOURCE_BASE + "L_MANUAL_STATE_BY_NAME"));
		
		lReclamationState.setText	(getResourceString(RESOURCE_BASE + "L_RECLAMATION_STATE"));
		lReclamationLevel.setText	(getResourceString(RESOURCE_BASE + "L_RECLAMATION_LEVEL"));
		lReclClosedComment.setText	(getResourceString(RESOURCE_BASE + "L_RECLAMATION_CLOSED_COMMENT"));
		
	}
	
	private void reInitLayout() {
		this.removeAll();
		initLayout(layoutStyle);
	}
	
	private void initLayout(int layoutStyle) {
		
		switch (layoutStyle) {
        	case LAYOUT_HORIZONTAL:
        		initLayoutHorizontalStyle();
        		break;
			case LAYOUT_VERTICAL:
				initLayoutVerticalStyle();
				break;
        	default:
        		break;
        }
	}

	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayoutHorizontalStyle() {
		TradeOvResultPanelModel model = getTradeOvResultPanelModel();
				
		int c;
		taManualStateComment.setMinimumSize(commentSize);
		taManualStateComment.setPreferredSize(commentSize);
		taReclClosedComment.setMinimumSize(commentSize);
		taReclClosedComment.setPreferredSize(commentSize);
		tfReclamationLevel.setMinimumSize(new Dimension(20,20));

		// Line 0
		add(lAutomaticState, 			new GridBagConstraints2(0, 0, 1, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));	
		add(lManualState, 				new GridBagConstraints2(1, 0, 3, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lReclamationState, 			new GridBagConstraints2(4, 0, 2, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		add(lReclamationLevel, 			new GridBagConstraints2(6, 0, 3, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));

		// Line 1
		c = 0;
		add(tfAutomaticState,			new GridBagConstraints2(c++, 1, 1, 1, 1.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
		add(tfManualState, 				new GridBagConstraints2(c++, 1, 1, 1, 1.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
		add(bEditManualState, 			new GridBagConstraints2(c++, 1, 1, 1, 0.0, 0.0, WEST, NONE, insets4000, 0, 0));
		add(bManualStateAttachment, 	new GridBagConstraints2(c++, 1, 1, 1, 0.0, 0.0, WEST, NONE, insets4000, 0, 0));
		add(tfReclamationState,			new GridBagConstraints2(c++, 1, 1, 1, 1.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
		add(bEditReclState,				new GridBagConstraints2(c++, 1, 1, 1, 0.0, 0.0, WEST, NONE, insets4000, 0, 0));
		add(tfReclamationLevel,			new GridBagConstraints2(c++, 1, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4000, 0, 0));
		add(bSendReclMail,				new GridBagConstraints2(c++, 1, 1, 1, 0.0, 0.0, WEST, NONE, insets4000, 0, 0));
		if (model != null && !model.isReclamationClosed()) {
			add(bCloseRecl,					new GridBagConstraints2(c++, 1, 1, 1, 0.0, 0.0, WEST, NONE, insets4000, 0, 0));
		}
		add(bOpenReclAttachment,		new GridBagConstraints2(c++, 1, 1, 1, 0.0, 0.0, WEST, NONE, insets4008, 0, 0));
		
		int nCols = c;

		// Line 2
		add(lManualStateByName,			new GridBagConstraints2(0, 2, 1, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
		if (model != null && model.isReclamationClosed()) {
			// Over three cols: tfManualState, bEditManualState, bManualStateAttachment
			add(lManualStateComment,		new GridBagConstraints2(1, 2, 3, 1, 1.0, 0.0, WEST, HORIZ, insets8800, 0, 0));
			add(lReclClosedComment, 		new GridBagConstraints2(4, 2, nCols-3, 1, 1.0, 0.0, WEST, HORIZ, insets8808, 0, 0));
		} else {
			add(lManualStateComment,		new GridBagConstraints2(1, 2, nCols, 1, 1.0, 0.0, WEST, HORIZ, insets8808, 0, 0));
		}

		// Line 3
		add(tfManualStateByName,		new GridBagConstraints2(0, 3, 1, 1, 1.0, 0.0, WEST, HORIZ, insets4880, 0, 0));
		if (model != null && model.isReclamationClosed()) {
			add(taManualStateComment,		new GridBagConstraints2(1, 3, 3, 2, 1.0, 1.0, WEST, BOTH, insets4880, 0, 0));
			add(taReclClosedComment, 		new GridBagConstraints2(4, 3, nCols-3, 2, 1.0, 1.0, WEST, BOTH, insets4888, 0, 0));
		} else {
			add(taManualStateComment,		new GridBagConstraints2(1, 3, nCols, 2, 1.0, 1.0, WEST, BOTH, insets4888, 0, 0));
		}
	
	}
	
	/**
	 * Method initLayout.
	 * 
	 */
	private void initLayoutVerticalStyle() {		
		TradeOvResultPanelModel model = getTradeOvResultPanelModel();
		taManualStateComment.setMinimumSize(commentSize);
		taManualStateComment.setPreferredSize(commentSize);
		taReclClosedComment.setMinimumSize(commentSize);
		taReclClosedComment.setPreferredSize(commentSize);
		tfReclamationLevel.setMinimumSize(new Dimension(20,20));

		int r = 0;
		add(lAutomaticState, 			new GridBagConstraints2(0, r, 6, 1, 0.0, 0.0, WEST, HORIZ, insets8800, 0, 0));		
		r++;
		
		add(tfAutomaticState,			new GridBagConstraints2(0, r, 6, 1, 1.0, 0.0, WEST, HORIZ, insets4808, 0, 0));
		r++;
		
		add(lManualState, 				new GridBagConstraints2(0, r, 6, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
		r++;
		
		add(tfManualState, 				new GridBagConstraints2(0, r, 4, 1, 1.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
		add(bEditManualState, 			new GridBagConstraints2(4, r, 1, 1, 0.0, 0.0, WEST, NONE, insets4000, 0, 0));
		add(bManualStateAttachment, 	new GridBagConstraints2(5, r, 1, 1, 0.0, 0.0, WEST, NONE, insets4000, 0, 0));
		r++;
		
		add(lManualStateByName,			new GridBagConstraints2(0, r, 6, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
		r++;
		
		add(tfManualStateByName, 		new GridBagConstraints2(0, r, 6, 1, 1.0, 0.0, WEST, HORIZ, insets4808, 0, 0));
		r++;
		
		add(lManualStateComment,		new GridBagConstraints2(0, r, 6, 1, 1.0, 0.0, WEST, HORIZ, insets4808, 0, 0));
		r++;
		
		add(taManualStateComment,		new GridBagConstraints2(0, r, 6, 2, 1.0, 1.0, WEST, BOTH, insets4808, 0, 0));
		r +=2;

		// Line 9		
		add(lReclamationState, 			new GridBagConstraints2(0, r, 2, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
		add(lReclamationLevel, 			new GridBagConstraints2(2, r, 3, 1, 0.0, 0.0, WEST, HORIZ, insets4800, 0, 0));
		r++;

		// Line 10
		add(tfReclamationState,			new GridBagConstraints2(0, r, 1, 1, 1.0, 0.0, WEST, HORIZ, insets4880, 0, 0));
		add(bEditReclState,				new GridBagConstraints2(1, r, 1, 1, 0.0, 0.0, WEST, NONE, insets4080, 0, 0));
		add(tfReclamationLevel,			new GridBagConstraints2(2, r, 1, 1, 0.0, 0.0, WEST, HORIZ, insets4080, 0, 0));
		add(bSendReclMail,				new GridBagConstraints2(3, r, 1, 1, 0.0, 0.0, WEST, NONE, insets4080, 0, 0));
		if (model != null && !model.isReclamationClosed()) {
			add(bCloseRecl,				new GridBagConstraints2(4, r, 1, 1, 0.0, 0.0, WEST, NONE, insets4080, 0, 0));
			add(bOpenReclAttachment,	new GridBagConstraints2(5, r, 1, 1, 0.0, 0.0, WEST, NONE, insets4080, 0, 0));
		} else {
			add(bOpenReclAttachment,	new GridBagConstraints2(4, r, 1, 1, 0.0, 0.0, WEST, NONE, insets4080, 0, 0));
		}
		r++;
		
		
		if (model != null && model.isReclamationClosed()) {
			add(lReclClosedComment, 	new GridBagConstraints2(0, r, 6, 1, 1.0, 0.0, WEST, HORIZ, insets4880, 0, 0));
			r++;

			add(taReclClosedComment, 	new GridBagConstraints2(0, r, 6, 1, 1.0, 0.0, WEST, HORIZ, insets4888, 0, 0));
			r++;
		}

	}

	private void syncAttachments() {
		bManualStateAttachment.setValue(((TradeOvResultPanelModel)getModel()).getManualStateAttachmentModel());		
		bOpenReclAttachment.setValue(((TradeOvResultPanelModel)getModel()).getReclamationStateAttachmentModel());		
	}
	
	
	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
		propertyHandler.add(TradeOvResultPanelModel.RES_AUTO_STATE, 						tfAutomaticState, ro);		
		propertyHandler.add(TradeOvResultPanelModel.RES_MANUAL_STATE,						tfManualState, ro);
		propertyHandler.add(TradeOvResultPanelModel.RES_MANUAL_STATE_COMMENT, 				taManualStateComment, ro);
		propertyHandler.add(TradeOvResultPanelModel.RES_MANUAL_STATE_BY_NAME, 				tfManualStateByName, ro);
		propertyHandler.add(TradeOvResultPanelModel.RES_MANUAL_STATE_ATTACHMENT_MODEL, 		bManualStateAttachment, ro);
		propertyHandler.add(TradeOvResultPanelModel.RES_RECLAMATION_STATE, 					tfReclamationState, ro);
		propertyHandler.add(TradeOvResultPanelModel.RES_RECLAMATION_STATE_ATTACHMENT_MODEL, bOpenReclAttachment, ro);
		propertyHandler.add(TradeOvResultPanelModel.RES_RECLAMATION_LEVEL, 					tfReclamationLevel, ro);
		propertyHandler.add(TradeOvResultPanelModel.RES_RECLAMATION_CLOSED_COMMENT,			taReclClosedComment, ro);
	}
	
	/**
	 * return the casted model.
	 */
	private TradeOvResultPanelModel getTradeOvResultPanelModel() {
		if (getModel() instanceof TradeOvResultPanelModel) {
			return (TradeOvResultPanelModel)getModel();
		}
		
		return null;
	}
	
		
	/**
	 * Setzen des ViewModels
	 */
    @Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof TradeOvResultPanelModel))
			throw new IllegalArgumentException("Model not instance of TradeOvResultPanelModel!");

		if (getModel() != null) {
			getModel().removePropertyChangeListener(listener);
		}
		propertyHandler.setModel(newModel);
		if (getModel() != null) {
		    getModel().addPropertyChangeListener(listener);	
		}
		
		super.setModel(newModel);
		fillView();
	}

}
