package de.westlb.mgb.client.mask.view.shared;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import de.westlb.mgb.client.mask.model.shared.TradeHistoryModel;
import de.westlb.mgb.client.server.vo.SendMailParamsVo;
import de.westlb.mgb.client.ui.treemodel.MailTreeModel.MailTreeObject;
import de.westlb.mgb.model.definition.MailTypeDef;
import de.westlb_systems.xaf.application.ActionSet;
import de.westlb_systems.xaf.application.MultipleLanguage;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SScrollPane;
import de.westlb_systems.xaf.swing.SSplitPane;
import de.westlb_systems.xaf.swing.STable;
import de.westlb_systems.xaf.swing.STableEditorEvent;
import de.westlb_systems.xaf.swing.STableEditorListener;
import de.westlb_systems.xaf.swing.STableSelectionEvent;
import de.westlb_systems.xaf.swing.STableSelectionListener;
import de.westlb_systems.xaf.swing.STree;
import de.westlb_systems.xaf.swing.tree.SDefaultTreeSelectionModel;
import de.westlb_systems.xaf.swing.tree.STreeModel;
import de.westlb_systems.xaf.ui.components.ContextMenu;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.ui.view.TablePanel;
import de.westlb_systems.xaf.util.Debug;


/**
 * @author M. Boerner
 *
 * View to display the statistic of trade checking.
 */
public class TradeHistoryView extends AbstractView implements MultipleLanguage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5808603305237110669L;

	public static final int EV_NEW_MAIL = 1;
	
	private static final String TREE_MENU_ITEM_0 = "item 0";
	private static final String TREE_MENU_ITEM_1 = "item 1";
	private static final String TREE_MENU_ITEM_2 = "item 2";
	
	
	private String RESOURCE_BASE = getResourceBase();
	
	/** Listener for all events */
	private Listener listener = new Listener();
	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();
	
	/** Universal listener handling for all view internal events */
	private class Listener implements TreeSelectionListener, STableSelectionListener, STableEditorListener, ActionListener, MouseListener {
		public Listener() {
		}

		@Override
        public void mouseClicked(MouseEvent e) {
			// If user double clicked a tree node, show mail details 
			if (e.getSource() == tree && e.getClickCount() == 2) {
				doShowMail(tree.getClosestPathForLocation(e.getX(), e.getY()));
			}
		}

		@Override
        public void mousePressed(MouseEvent e) {
		}

		@Override
        public void mouseReleased(MouseEvent e) {
		}

		@Override
        public void mouseEntered(MouseEvent e) {
		}

		@Override
        public void mouseExited(MouseEvent e) {
		}


		@Override
        public void selectionChanged(STableSelectionEvent e) {
			if (e.isDoubleClicked()) {
				doOpenAttachment(e.getRow());
			}
		}
		@Override
        public void invokeEditor(STableEditorEvent e) {
			if (STableEditorEvent.EDIT == e.getActionCommand()) {
				doOpenAttachment(e.getRow());
			} 
		}

		@Override
        public void actionPerformed(ActionEvent ae) {
			if (TREE_MENU_ITEM_0.equals(ae.getActionCommand())) {
				doSendNewReclamation(tree.getSelectionPath());
			} 
			
			else if (TREE_MENU_ITEM_1.equals(ae.getActionCommand())) {
				doSendReply(tree.getSelectionPath());
			}

			else if (TREE_MENU_ITEM_2.equals(ae.getActionCommand())) {
				doShowMail(tree.getSelectionPath());
			}
			
		}
		/* (non-Javadoc)
		 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
		 */
		@Override
        public void valueChanged(TreeSelectionEvent e) {
		}
	}
	
	private static class MailTreeCellRenderer extends JLabel implements TreeCellRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6055406070564589827L;
		protected Color m_textSelectionColor;
		protected Color m_textNonSelectionColor;
		protected Color m_bkSelectionColor;
		protected Color m_bkNonSelectionColor;
		protected Color m_borderSelectionColor;
		protected boolean m_selected;
		
		public MailTreeCellRenderer() {
			m_textNonSelectionColor = UIManager.getColor("Tree.textForeground");
			m_bkNonSelectionColor = UIManager.getColor("Tree.textBackground");
			m_borderSelectionColor = UIManager.getColor("Tree.selectionBorderColor");

			m_textSelectionColor = UIManager.getColor("Tree.selectionForeground");
			m_bkSelectionColor = UIManager.getColor("Tree.selectionBackground");

			setOpaque(false);
		}
			
			/* (non-Javadoc)
		 * @see javax.swing.tree.TreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int, boolean)
		 */
		@Override
        public Component getTreeCellRendererComponent(
			JTree tree,
			Object value,
			boolean selected,
			boolean expanded,
			boolean leaf,
			int row,
			boolean hasFocus) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
			Object obj = node.getUserObject();
			setText(obj.toString());
			if (obj instanceof MailTreeObject) {
				MailTreeObject mailTreeObject = (MailTreeObject)obj;
				setIcon(mailTreeObject.getMailTypeIcon());
			}
			
			setFont(tree.getFont());
			
			setForeground(selected ? m_textSelectionColor : m_textNonSelectionColor);
			setBackground(selected ? m_bkSelectionColor : m_bkNonSelectionColor);
			m_selected = selected;
			
			return this;
		}
		
		@Override
        public void paint(Graphics g) {
			Icon icon = getIcon();
			int offset = 0;
			if (icon != null && getText() != null) {
				offset = (icon.getIconWidth() + getIconTextGap());				
			}
			
			if (m_selected) {
				g.setColor(m_bkSelectionColor);
				g.fillRect(offset, 1, getWidth() -1 - offset, getHeight() -2 );
				g.setColor(m_borderSelectionColor);
				g.drawRect(offset, 1, getWidth() -1 - offset, getHeight() -2 );
			}
			super.paintComponent(g);
		}

	} /* class Listener */

	// UI - Components
	private TablePanel tpHistory = new TablePanel();
	private STable tHistory = new STable();	
	
	private TablePanel tpMails = new TablePanel();
	private SScrollPane pTreeScrollPane = new SScrollPane();
	private STree tree = new STree();
	private SSplitPane pSplitPane = new SSplitPane();
	
	@SuppressWarnings("unused")
    private TreePath currentTreePath = null;
	private ContextMenu cm = null;


	/**
	 * Constructor for CheckStateView.
	 */
	public TradeHistoryView() {
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
	 * Constructor for CheckStateView with 
	 */
	public TradeHistoryView(Model model) {
		super(model);
	}

	/**
	 * Felder des Views mit Werten aus dem Model fuellen
	 */
	private void fillView() {
		if (getModel() != null) {
			handleModelError();
		}
		
		if (getModel() == null || !(getModel() instanceof TradeHistoryModel)) {
			return;
		}
		
		propertyHandler.syncFields();
		
		tree.setModel((STreeModel) getModel().getProperty(TradeHistoryModel.MAIL_TREE_MODEL));
		tree.expandRow(0);
	}

	/**
	 * Method initComponents.
	 */
	private void initComponents() {
		this.setBackground(UIManager.getColor("DetailsPanel.background"));
		
		MailTreeCellRenderer renderer = new MailTreeCellRenderer();
		tree.setCellRenderer(renderer);
		tree.setRootVisible(false);
		tree.setShowsRootHandles(true);
		tree.setSelectionModel(new SDefaultTreeSelectionModel(SDefaultTreeSelectionModel.SINGLE_TREE_SELECTION));
		tree.addTreeSelectionListener(listener);
		tree.addMouseListener(listener);
		
		// table
		tpHistory.setTable(tHistory);
		tHistory.setGridEnabled(true);
		tHistory.setRowCounterVisible(false);
		tHistory.setPopupProvider(STable.EDIT_PROVIDER);
		tHistory.addTableEditorListener(listener);
		tHistory.addSTableSelectionListener(listener);
		
		pTreeScrollPane.setViewportView(tree);
		tpMails.setTable(pTreeScrollPane);
		
		pSplitPane.setOrientation(SSplitPane.VERTICAL_SPLIT);
		pSplitPane.setLeftComponent(tpHistory);
		pSplitPane.setRightComponent(tpMails);
		pSplitPane.setResizeWeight(1);

		// Context-Menu. Liefert Actions "item #" mit Nummer des Item (0-basierend)
		String items[] = { 
			getResourceString(RESOURCE_BASE + "B_SEND_RECLAMATION"), 
			getResourceString(RESOURCE_BASE + "B_SEND_REPLY"), 
			getResourceString(RESOURCE_BASE + "B_SHOW_MAIL_DETAILS")
		};
		
		if (cm != null) {
			cm.dispose(tree, listener);
		}
		
		cm = new ContextMenu(tree, items, listener);

		// Add the functions to the menu
		setActions (new ActionSet[] {
			new ActionSet("EDIT_EDIT", true)
		});
		

	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		tpHistory.setText(getResourceString("HST_L_HEADER_HIST_TABLE"));
		tpMails.setText(getResourceString("HST_L_HEADER_MAILS"));
	}

	/**
	 * Method initLayout.
	 */
	private void initLayout() {
		add(pSplitPane,		new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, insets4808, 0, 0));
//		add(tpHistory,		new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.6, CENTER, BOTH, insets4808, 0, 0));
//		add(tpMails, 		new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.4, CENTER, BOTH, insets4888, 0, 0));

		setReadOnly();
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
		propertyHandler.add(TradeHistoryModel.HISTORY_TABLE_MODEL,  tHistory, ro);	
		propertyHandler.add(TradeHistoryModel.MAIL_TREE_MODEL, tree);	
	}
	
	private void doOpenAttachment(int row) {
		TradeHistoryModel model = getTradeHistoryModel();
		if (model == null) {
			return;
		}
		
		FileHandler fileSupport = new FileHandler(this);
		Object id = model.getAttachmentId(row) ;
		if (id != null) {
			String name = model.getAttachmentName(row) ;	
			fileSupport.openFile(id, name);
		}
	}
	
	private void doSendReply(TreePath path) {
		SendMailParamsVo params = new SendMailParamsVo();
		params.setType(MailTypeDef.CONTROLLER_REPLY);
		params.setTradeId(getTradeHistoryModel().getTradeId());
		if (path == null) {
			return;
		}
		if (getTradeHistoryModel().isControllerMail(path)) {
			showMessageBox(SHOW_INFO, getResourceString(RESOURCE_BASE + "E_CAN_NOT_REPLY_CONTROLLER_MAIL"));
			return;
		}
		params.setParentMailId(getTradeHistoryModel().getMailId(path));
		fireUserEvent(EV_NEW_MAIL, params);			
	}

	private void doSendNewReclamation(TreePath path) {
		SendMailParamsVo params = new SendMailParamsVo();
		params.setType(MailTypeDef.CONTROLLER_RECLAMATION);
		params.setTradeId(getTradeHistoryModel().getTradeId());
		fireUserEvent(EV_NEW_MAIL, params);			
	}

	private void doShowMail(TreePath path) {
		if (path == null) {
			return;
		}
		
		Long params = getTradeHistoryModel().getMailId(path);
		fireUserEvent(EV_NEW_MAIL, params);			
	}
	
	public TradeHistoryModel getTradeHistoryModel() {
		Model  model = getModel();
		if (!(model instanceof TradeHistoryModel)) {
			return null;
		}
		
		return (TradeHistoryModel)model;	
	}
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		propertyHandler.setModel(newModel);
		
		super.setModel(newModel);
		fillView();
	}
	
	/**
		Wird aufgerufen, wenn ein Ast im Baum geöffnet oder geschlossen wird.
	 */
	@SuppressWarnings("unused")
    private void changeTreeExpansion(TreePath treePath, boolean opened) {
		tree.setSelectionPath(treePath);
	}
	/**
		Wird aufgerufen, wenn die Auswahl im Baum sich ändert.
	 */
	@SuppressWarnings("unused")
    private void changeTreeSelection(TreePath treePath) {
		currentTreePath = treePath;
	}

}
