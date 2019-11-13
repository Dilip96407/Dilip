/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.client.ui.treemodel;

import java.util.EventListener;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import de.westlb.mgb.client.server.vo.MailSearchResultEntryVo;
import de.westlb.mgb.client.ui.util.DateFormat;
import de.westlb.mgb.model.definition.MailTypeDef;
import de.westlb_systems.xaf.swing.tree.SDefaultTreeModel;
import de.westlb_systems.xaf.swing.tree.SDefaultTreeNode;
import de.westlb_systems.xaf.swing.tree.STreeModel;
import de.westlb_systems.xaf.ui.misc.IconKatalog;

/**
 * Converts a list of MailVo's to a tree model as it is required for 
 * the GUI.
 *
 * @author M. Boerner 
 */
public class MailTreeModel implements STreeModel {
	private DateFormat dateFormat = new DateFormat(DateFormat.DATE_FORMAT);
	
	public class MailTreeObject {
		private MailSearchResultEntryVo mailSearchResultEntryVo;
		private Icon contollerMailIcon = IconKatalog.getInstance().getIcon("EMAIL_RED");
		private Icon traderMailIcon = IconKatalog.getInstance().getIcon("EMAIL_GREEN");
		
		public MailTreeObject(MailSearchResultEntryVo mailSearchResultEntryVo) {
			this.mailSearchResultEntryVo = mailSearchResultEntryVo;
		}
		
		@Override
        public String toString() {
			StringBuffer stringBuffer = new StringBuffer();
			if (mailSearchResultEntryVo.getCreationDate() != null) {
				stringBuffer.append(dateFormat.format(mailSearchResultEntryVo.getCreationDate()));
			}
			stringBuffer.append(" ");
			stringBuffer.append(mailSearchResultEntryVo.getSenderName());
			stringBuffer.append(":");
			stringBuffer.append(mailSearchResultEntryVo.getSubject());
			return stringBuffer.toString();
		}
		
		public Long getTradeId() {
			return mailSearchResultEntryVo.getTradeId();		
		}
		
		public Long getMaildId() {
			return mailSearchResultEntryVo.getId();		
		}
		
		public boolean isControllerMail() {
			return mailSearchResultEntryVo.getType() != null && mailSearchResultEntryVo.getType().startsWith(MailTypeDef.CONTROLLER_PREFIX);
		}
		
		public boolean isTraderMail() {
			return mailSearchResultEntryVo.getType().startsWith(MailTypeDef.TRADER_PREFIX);
		}
		
		public Icon getMailTypeIcon() {
			if (isControllerMail()) {
				return contollerMailIcon;
			}
            return traderMailIcon;
		}

	}
	
	private SDefaultTreeModel delegate;
	
	public MailTreeModel(MailSearchResultEntryVo[] mailVos) {
		HashMap<Long, SDefaultTreeNode> hashMap = new HashMap<Long, SDefaultTreeNode>();
		SDefaultTreeNode root = new SDefaultTreeNode("Mails");
		
		for (int i = 0; i < mailVos.length; i++) {
			hashMap.put(mailVos[i].getId(), new SDefaultTreeNode(new MailTreeObject(mailVos[i])));
		}
		
		for (int i = 0; i < mailVos.length; i++) {
			SDefaultTreeNode node = hashMap.get(mailVos[i].getId());
			SDefaultTreeNode parent = hashMap.get(mailVos[i].getParentId());
			if (parent == null) {
				 root.add(node);
			} else {
				parent.add(node);
			}
		}
		
		delegate = new SDefaultTreeModel(root);
	}


	/**
	 * @param l
	 */
	@Override
    public void addTreeModelListener(TreeModelListener l) {
		delegate.addTreeModelListener(l);
	}

	/**
	 * @return
	 */
	public boolean asksAllowsChildren() {
		return delegate.asksAllowsChildren();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
    public boolean equals(Object other) {
	    if (this == other)
	        return true;
	    if (other == null)
	        return false;
	    if (other.getClass() != delegate.getClass())
	        return false;
	    if (!(delegate.equals(((MailTreeModel)other).delegate)))
	        return false;
	    return true;
	}

	/**
	 * @param parent
	 * @param index
	 * @return
	 */
	@Override
    public Object getChild(Object parent, int index) {
		return delegate.getChild(parent, index);
	}

	/**
	 * @param parent
	 * @return
	 */
	@Override
    public int getChildCount(Object parent) {
		return delegate.getChildCount(parent);
	}

	/**
	 * @param parent
	 * @param child
	 * @return
	 */
	@Override
    public int getIndexOfChild(Object parent, Object child) {
		return delegate.getIndexOfChild(parent, child);
	}

	/**
	 * @param listenerType
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes"})
    public EventListener[] getListeners(Class listenerType) {
		return delegate.getListeners(listenerType);
	}

	/**
	 * @param aNode
	 * @return
	 */
	public TreeNode[] getPathToRoot(TreeNode aNode) {
		return delegate.getPathToRoot(aNode);
	}

	/**
	 * @return
	 */
	@Override
    public Object getRoot() {
		return delegate.getRoot();
	}

	/**
	 * @return
	 */
	public TreeModelListener[] getTreeModelListeners() {
		return delegate.getTreeModelListeners();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
    public int hashCode() {
		return delegate.hashCode();
	}

	/**
	 * @param newChild
	 * @param parent
	 * @param index
	 */
	public void insertNodeInto(
		MutableTreeNode newChild,
		MutableTreeNode parent,
		int index) {
		delegate.insertNodeInto(newChild, parent, index);
	}

	/**
	 * @param node
	 * @return
	 */
	@Override
    public boolean isLeaf(Object node) {
		return delegate.isLeaf(node);
	}

	/**
	 * @param node
	 */
	public void nodeChanged(TreeNode node) {
		delegate.nodeChanged(node);
	}

	/**
	 * @param node
	 * @param childIndices
	 */
	public void nodesChanged(TreeNode node, int[] childIndices) {
		delegate.nodesChanged(node, childIndices);
	}

	/**
	 * @param node
	 */
	public void nodeStructureChanged(TreeNode node) {
		delegate.nodeStructureChanged(node);
	}

	/**
	 * @param node
	 * @param childIndices
	 */
	public void nodesWereInserted(TreeNode node, int[] childIndices) {
		delegate.nodesWereInserted(node, childIndices);
	}

	/**
	 * @param node
	 * @param childIndices
	 * @param removedChildren
	 */
	public void nodesWereRemoved(
		TreeNode node,
		int[] childIndices,
		Object[] removedChildren) {
		delegate.nodesWereRemoved(node, childIndices, removedChildren);
	}

	/**
	 * 
	 */
	public void reload() {
		delegate.reload();
	}

	/**
	 * @param node
	 */
	public void reload(TreeNode node) {
		delegate.reload(node);
	}

	/**
	 * @param node
	 */
	public void removeNodeFromParent(MutableTreeNode node) {
		delegate.removeNodeFromParent(node);
	}

	/**
	 * @param l
	 */
	@Override
    public void removeTreeModelListener(TreeModelListener l) {
		delegate.removeTreeModelListener(l);
	}

	/**
	 * @param newValue
	 */
	public void setAsksAllowsChildren(boolean newValue) {
		delegate.setAsksAllowsChildren(newValue);
	}

	/**
	 * @param root
	 */
	public void setRoot(TreeNode root) {
		delegate.setRoot(root);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
		return delegate.toString();
	}

	/**
	 * @param path
	 * @param newValue
	 */
	@Override
    public void valueForPathChanged(TreePath path, Object newValue) {
		delegate.valueForPathChanged(path, newValue);
	}

}