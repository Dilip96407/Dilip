package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import javax.swing.tree.TreePath;

import de.westlb.mgb.client.application.RefreshHelper;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.MailSearchResultEntryVo;
import de.westlb.mgb.client.server.vo.TradeHistoryEntryVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;
import de.westlb.mgb.client.ui.treemodel.MailTreeModel;
import de.westlb.mgb.client.ui.treemodel.MailTreeModel.MailTreeObject;
import de.westlb_systems.xaf.swing.tree.STreeNode;


/**
 * Gui model for the trade history view.
 *
 * @author Manfred Boerner
 */

public class TradeHistoryModel extends AbstractModel implements CheckStateOverviewPanelModel {

	public static final String HISTORY_TABLE_MODEL = "HistoryTableModel";
	public static final String MAIL_TREE_MODEL = "MailTreeModel";
	
	TradeHistoryEntryVo[] histEntries = null;
	
	/** Definition of all properties provided by the model to the view */
	private final String[] propertyNames = new String[] {
		HISTORY_TABLE_MODEL, 
		MAIL_TREE_MODEL,
	};
	
	/**
	 * Default constructor to create an empty model
	 */
	public TradeHistoryModel() {
		setPropertyNames(propertyNames);
	}
	
	/**
	 * Constructor filling the model with data from the business object
	 */
	public TradeHistoryModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
	public Object getAttachmentId(int row) {
		if (histEntries ==  null) {
			return null;
		}
		
		return histEntries[row].getAttachmentId();
	}
	
	public Long getTradeId() {
		return (Long)getBusinessObject();
		
	}
	
	public Long getMailId(TreePath treePath) {
		if (getMailTreeModel() == null) {
			return null;
		}
		STreeNode node = (STreeNode)treePath.getLastPathComponent();
		Long mailId = null;
		if  (node.getUserObject() instanceof MailTreeObject) {
			MailTreeObject mailObject = (MailTreeObject)node.getUserObject();
			mailId = mailObject.getMaildId();
		}
		
		return mailId;
	}
	
	public boolean isControllerMail(TreePath treePath) {
		if (getMailTreeModel() == null) {
			return false;
		}
		STreeNode node = (STreeNode)treePath.getLastPathComponent();
		if  (node.getUserObject() instanceof MailTreeObject) {
			MailTreeObject mailObject = (MailTreeObject)node.getUserObject();
			return mailObject.isControllerMail();
		}	
		
		return false;		
	}
	
	public String getAttachmentName(int row) {
		if (histEntries ==  null) {
			return null;
		}
		
		return histEntries[row].getAttachmentName();

	}
	
	private MailTreeModel getMailTreeModel() {
		return (MailTreeModel)getProperty(MAIL_TREE_MODEL);
	}
	
	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		MailSearchResultEntryVo[] mails = null;
		histEntries = null;
		
		Object bo = getBusinessObject();
		if (bo != null) {
			try {
                histEntries = MgbServiceFactory.getService().getHistory(bo);
        		if (histEntries == null) {
        			return;
        		}
        		TableModel tableModel = TableModelFactory.createTableModel("HistoryTable", histEntries);
        		setProperty(HISTORY_TABLE_MODEL, tableModel);

        		mails = MgbServiceFactory.getService().findMailsByTradeId((Long)bo);
        		MailTreeModel treeModel = new MailTreeModel(mails);
        		setProperty(MAIL_TREE_MODEL, treeModel);
            } catch (RemoteException e) {
            	handleRemoteException(e);
            	return;
            }
            RefreshHelper.registerCache(this, RefreshHelper.TRADE, bo);
		}
		
						
/*		HistoryTableModel tableModel  = new HistoryTableModel();
		tableModel.setData(histEntries);
		tableModel.setResourceBundle(getResourceBundle());
		tableModel.setResourceBase("HST_TBL_H_");		
		setProperty(HISTORY_TABLE_MODEL, tableModel);
*/		
	}


	/**
	 * Sets the business object where the model gets its data from.
	 * 
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		super.setBusinessObject(newBusinessObject);
		fillModel();
	}
}
