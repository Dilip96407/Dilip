package de.westlb.mgb.client.mask.model.shared;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.application.RefreshHelper;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.EmployeeVo;
import de.westlb.mgb.client.server.vo.MailVo;
import de.westlb.mgb.client.server.vo.SendMailParamsVo;
import de.westlb.mgb.client.server.vo.TradeHistoryEntryVo;
import de.westlb.mgb.client.ui.selection_list.AbstractSelectionList;
import de.westlb.mgb.client.ui.selection_list.MailTypeList;
import de.westlb.mgb.client.ui.selection_list.ReclamationStateCodeList;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;
import de.westlb.mgb.model.definition.MailTypeDef;
import de.westlb.mgb.model.definition.StateCodeTypeDef;
import de.westlb_systems.xaf.swing.SDataModel2;
import de.westlb_systems.xaf.util.table.swing.TablePoolComboBoxModel;

/**
 * GUI model for the sending a new mail.
 *
 * @author Manfred Boerner
 */

public class NewMailModel extends AbstractModel {
	public Logger logger = Logger.getLogger(NewMailModel.class);
	
	public static final String P_MAIL_TYPE					= "mailType";
	public static final String P_MAIL_TYPE_CB_MODEL			= "mailTypeComboBoxModel";
	public static final String P_RECIPIENT_EMPLOYEE_NAME	= "recipientEmployeeName";
	public static final String P_RECIPIENT_EMAIL			= "recipientEMail";
	public static final String P_SUBJECT					= "subject";
	public static final String P_TEXT						= "text";
	public static final String P_CREATION_DATE				= "creationDate";
	
	public static final String P_RECL_CODE			 		= "reclamationCode";
	public static final String P_RECL_CODE_CB_MODEL 		= "reclamationCodeComboBoxModel";
	public static final String P_RECL_COMMENT 				= "reclComment";
	public static final String P_RECL_ATTACHMENT_PATH 		= "reclAttachmentPath";
	public static final String P_RECL_ATTACHMENT_ID 		= "reclAttachmentId";
	
	public static final String P_COPY_RECIPIENTS_TABLE_MODEL = "copyRecipients";

	// Should the sending of the email remarked as a reclamation by setting reclamation state 
	// of the trade
	public static final String P_RECL_REMARK				= "reclRemark";
	
	
	private String RESOURCE_BASE = getResourceBase();

	private ReclamationStateCodeList reclamationStateCodeList = new ReclamationStateCodeList();
	private MailTypeList mailTypeList = new MailTypeList();
	
	private SendMailParamsVo sendMailParamsVo = null;
	private Long mailId;
	private MailVo mailVo = null;
	
	// The actual list of the employees that should receive a copy of the 
	// mail.
	private List<EmployeeVo> copyRecpients;
	
	// Mode used for sendig a new mail
	private static final int MODE_SEND_MAIL = 1;
	// Mode used for display a mail that has been send.
	private static final int MODE_DISPLAY_MAIL = 2;
	// Current mode
	private int mode =  MODE_SEND_MAIL;
	
	/** Definition of all properties provided by the model to the view */
	private final String[] propertyNames = new String[] {
		P_RECIPIENT_EMPLOYEE_NAME, 
		P_RECIPIENT_EMAIL,
		P_SUBJECT,
		P_TEXT,
		P_RECL_COMMENT,
		P_RECL_CODE_CB_MODEL,
		P_RECL_CODE,
		P_RECL_ATTACHMENT_PATH,
		P_RECL_ATTACHMENT_ID,
		P_RECL_REMARK,
		P_MAIL_TYPE_CB_MODEL,
		P_MAIL_TYPE,
		P_CREATION_DATE,
		P_COPY_RECIPIENTS_TABLE_MODEL
	};

	/**
	 * Default constructor to create an empty model
	 */
	public NewMailModel() {
		setPropertyNames(propertyNames);
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public NewMailModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		try {
			if (mode == MODE_SEND_MAIL) {
				mailVo = MgbServiceFactory.getService().prepareMail(sendMailParamsVo);
			} else if (isModeDisplayMail()) {
				mailVo = MgbServiceFactory.getService().getMail(mailId.longValue());
			}
			
			setProperty(P_RECIPIENT_EMPLOYEE_NAME,	mailVo.getRecipientName());
			setProperty(P_RECIPIENT_EMAIL,			mailVo.getRecipientAdress());
			setProperty(P_SUBJECT,					mailVo.getSubject());
			setProperty(P_TEXT,						mailVo.getText());
			setProperty(P_CREATION_DATE,			mailVo.getCreationDate());
			
			copyRecpients = new ArrayList<EmployeeVo>(Arrays.asList(mailVo.getCopyRecipients()));
			updateCopyRecipientsTableModel();
			
			setReclStateProperties(mailVo);

			TablePoolComboBoxModel reclComboBoxModel = new TablePoolComboBoxModel(reclamationStateCodeList);
			setProperty(P_RECL_CODE_CB_MODEL, reclComboBoxModel);

			TablePoolComboBoxModel mailTypeComboBoxModel = new TablePoolComboBoxModel(mailTypeList);
			setProperty(P_MAIL_TYPE_CB_MODEL, mailTypeComboBoxModel);
			Object object = AbstractSelectionList.getItemFor(mailTypeList, mailVo.getType());
			setProperty(P_MAIL_TYPE, object);
		} catch (RemoteException e) {
			handleRemoteException(e);
		}
        				
		return;
	}
	
	/**
	 * Return the title dependend on the mail type
	 */
	@Override
    public String getTitle() {
		String title;
		
		if (isModeDisplayMail()) {
			title = getResourceString(RESOURCE_BASE + "T_MAIL_DETAILS");
		} else {
			title = getResourceString(RESOURCE_BASE + "T_001");
		}
		
		return title;
	}
	
	/**
	 * Returns the value of the property 'remark reclamation'
	 * 
	 * @return true, if the property remark reclamation has been set.
	 */
	public Boolean getRemarkReclamation() {
		return (Boolean)getProperty(P_RECL_REMARK);
	}

	/**
	 * Returns the casted business model
	 */
	public SendMailParamsVo getSendMailParamsVo() {
		Object businessObject = getBusinessObject();
		if (!(businessObject instanceof SendMailParamsVo)) {
			return null;
		}
		
		return (SendMailParamsVo)businessObject;
	}
	
	public boolean isModeDisplayMail() {
		return mode == MODE_DISPLAY_MAIL;
	}
	
	public boolean isReclamationMail() {
		if (mailVo == null) {
			return false;
		}
		
		return MailTypeDef.CONTROLLER_RECLAMATION.equals(mailVo.getType());
	}
	
    /**
    * Save Model Data
    */
    @Override
    public boolean saveModel() {
        if (!isModelChanged()) {
            logMessage(LOG_INFO, null);
            return true;
        }

        try {
        	if (mode == MODE_SEND_MAIL) {
        		boolean remarkReclamation = getRemarkReclamation().booleanValue();
        		
		        String text = (String)getProperty(P_TEXT);
		        String subject = (String)getProperty(P_SUBJECT);
		        String reclStateCode = 	(String)ReclamationStateCodeList.getKeyFrom(getProperty(P_RECL_CODE));      

		        // Check mandatory fields
		        if (subject == null || (remarkReclamation && reclStateCode == null)) { 
		            setError(PROPERTY_ERROR);
		            logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_MANDATORY"));
		            return false;
		        }
		        
		        TradeHistoryEntryVo reclStateVo = null;
				byte[] attachmentContent = null;
				if (Boolean.TRUE.equals(getRemarkReclamation())) {
					reclStateVo = getReclStateFromProperties();
					attachmentContent = getAttachmentDataFromProperties();
				}

		        mailVo.setText(text);
		        mailVo.setSubject(subject);
		        mailVo.setReclamationState(reclStateVo);
		        
		        mailVo.setCopyRecipients(copyRecpients.toArray(new EmployeeVo[copyRecpients.size()]));

	            MgbServiceFactory.getService().sendMail(mailVo, attachmentContent);
	            RefreshHelper.registerUpdate(this, RefreshHelper.TRADE, mailVo.getTradeId());
			} else {
				// Do Nothing.
			}
        } catch (RemoteException e) {
            handleRemoteException(e);
            return false;
		} catch (Exception e) {
			setError(APPLICATION_ERROR);
			setErrorDetails(e);
			return false;
        } 
        
        return true;
    }
    
    /**
     * Sets the properties related to the reclamation state.
     * @param mailVo contains the information from the business logic.
     */
    private void setReclStateProperties(MailVo mailVo) {
    	TradeHistoryEntryVo stateVo = mailVo.getReclamationState();
    	if (stateVo != null) {
			setProperty(P_RECL_CODE,			ReclamationStateCodeList.getItemFor(reclamationStateCodeList, stateVo.getStateCode()));
			setProperty(P_RECL_COMMENT,			stateVo.getComment());
			setProperty(P_RECL_ATTACHMENT_ID,	stateVo.getAttachmentId());
    	} 
    	
		setProperty(P_RECL_REMARK,	Boolean.valueOf(isReclamationMail()));
    }
    
    /**
     * Creates a reclamation state vo from the current properties.
     * 
     */
    private TradeHistoryEntryVo getReclStateFromProperties() {		
		TradeHistoryEntryVo reclState = new TradeHistoryEntryVo();
		
		// Properies auslesen
		String stateCode			= (String)ReclamationStateCodeList.getKeyFrom(getProperty(P_RECL_CODE));
		String comment				= (String)getProperty(P_RECL_COMMENT);
		String attachmentPath		= (String)getProperty(P_RECL_ATTACHMENT_PATH);
		
		String attachmentName = null;
		if (attachmentPath != null) {
			attachmentName = new File(attachmentPath).getName();
		}
		
		reclState.setStateCode(stateCode);
		reclState.setComment(comment);
		reclState.setInternalTradeId(mailVo.getTradeId());		
		reclState.setAttachmentName(attachmentPath);
		reclState.setType(StateCodeTypeDef.RECLAMATION);
		reclState.setReclamationLevel(Integer.valueOf(0));
		reclState.setAttachmentName(attachmentName);		
		
    	return reclState;
    } 
    
    private byte[] getAttachmentDataFromProperties() throws FileNotFoundException, IOException {
		String attachmentPath		= (String)getProperty(P_RECL_ATTACHMENT_PATH);
		if (attachmentPath == null) {
			return null;
		}
		byte[] attachmentContent = null;
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(attachmentPath);
			attachmentContent = new byte[(int) new File(attachmentPath).length()];
			inputStream.read(attachmentContent);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException io){
                }
            }
        }
		
		return attachmentContent;
    }
    
    /**
     * Updates the table model with data from instance variable
     * copyRecipients that holds the current selected data.
     * 
     * @throws RemoteException
     */
    public void updateCopyRecipientsTableModel() throws RemoteException {
		SDataModel2 tableModel = TableModelFactory.createTableModel("EmployeeTable", copyRecpients.toArray());
		setProperty(P_COPY_RECIPIENTS_TABLE_MODEL, tableModel);
    }

	/**
	 * Adds an employee to the list of copy recpients.
	 * 
	 * @param employeeId Id of the employee to add.
	 */    
    public void addCopyRecipient(Long employeeId) {
    	try {
    		EmployeeVo employeeVo = MgbServiceFactory.getService().getEmployee(employeeId);
			copyRecpients.add(employeeVo);
			updateCopyRecipientsTableModel();
		} catch (RemoteException e) {
			handleRemoteException(e);
		}
    }

	/**
	 * Removes an employee from the list of copy recipients.
	 * 
	 * @param employeeId Id of the employee to add.
	 */    
	public void removeCopyRecipient(int rowId) {
		if (rowId < 0) {
			return;
		}
		try {
			copyRecpients.remove(rowId);
			updateCopyRecipientsTableModel();
		} catch (RemoteException e) {
			handleRemoteException(e);
		}
	}
	
	/**
	 * Sets the business object where the model gets its data from.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		if (!(newBusinessObject instanceof SendMailParamsVo || newBusinessObject instanceof Long)) {
			throw new IllegalArgumentException("Businessobject type must be " + SendMailParamsVo.class.getName()  + " OR " + Long.class.getName());
		}
		super.setBusinessObject(newBusinessObject);
		if (newBusinessObject instanceof SendMailParamsVo) {
			mode = MODE_SEND_MAIL;
			sendMailParamsVo = (SendMailParamsVo)newBusinessObject;
			mailId = null;
			mailVo = null;
		} else {
			mode = MODE_DISPLAY_MAIL;
			sendMailParamsVo = null;
			mailVo = null;
			mailId = (Long)newBusinessObject;
		}
		fillModel();
	}

    @Override
    public void reload() {
    	fillModel();
    }
}
