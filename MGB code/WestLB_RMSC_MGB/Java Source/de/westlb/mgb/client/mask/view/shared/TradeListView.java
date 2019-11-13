package de.westlb.mgb.client.mask.view.shared;

import java.awt.Component;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

import de.westlb.mgb.client.mask.model.shared.TradeListModel;
import de.westlb_systems.xaf.application.ActionSet;
import de.westlb_systems.xaf.application.MultipleLanguage;
import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.application.event.LogMessageEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SMenuItem;
import de.westlb_systems.xaf.swing.SPopupMenu;
import de.westlb_systems.xaf.swing.SPopupProvider;
import de.westlb_systems.xaf.swing.STable;
import de.westlb_systems.xaf.swing.STableEditorEvent;
import de.westlb_systems.xaf.swing.STableEditorListener;
import de.westlb_systems.xaf.swing.STableSelectionEvent;
import de.westlb_systems.xaf.swing.STableSelectionListener;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.ui.view.TablePanel;
import de.westlb_systems.xaf.ui.view.TitlePanel;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author M. Boerner
 *
 * View to display the statistic of trade checking.
 */
public class TradeListView extends AbstractView implements MultipleLanguage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8924020221253830770L;
	/* Constant definitions in UserEvents */
	public static final int EV_EDIT_TRADE    		= 1;
	public static final int EV_NEW_MANUAL_STATE		= 2;
	
	
	/** Listener for all events */
	private Listener listener = new Listener();
	/**
	 * The PropertyHandler for the model view synchronization.
	 */
	private PropertyHandler propertyHandler = new PropertyHandler();

	/** Universal listener handling for all view internal events */
	private class Listener implements STableSelectionListener, STableEditorListener, ActionListener {
		public Listener() {
		}
		@Override
        public void selectionChanged(STableSelectionEvent e) {
			if (e.isDoubleClicked()) {
				rowSelected((Component)e.getSource(), e.getRow());
			}
		}
		@Override
        public void invokeEditor(STableEditorEvent e) {
			if (STableEditorEvent.EDIT == e.getActionCommand()) {
				rowSelected((Component)e.getSource(), e.getRow());
			} 
		}

		@Override
        public void actionPerformed(ActionEvent ae) {
			Object source = ae.getSource();
			if (source == pItemFormat) {
				doSwitchTableFormat();
			} else if (source == pItemNewManualState) {
				doNewManualState();
			} else if (source == pItemCopyCell) {
				doCopyCell(tTradelist.getPopupProvider().getLastPopupLocation());
			}
		}
 
	}

	// UI - Components
	private TitlePanel pTitle = new TitlePanel();
	private TablePanel tpTradeList = new TablePanel();
	private STable tTradelist = new STable();	
	private SMenuItem pItemFormat			= new SMenuItem();	
	private SMenuItem pItemNewManualState	= new SMenuItem();	
	private SMenuItem pItemCopyCell			= new SMenuItem();	

	/**
	 * Constructor for CheckStateView.
	 */
	public TradeListView() {
		try {
			initComponents();
			initLayout();
			initProperties();
			initLabels();
		} catch (Exception ex) {
			Debug.log(Debug.ERROR, this, "Exception in View-Constuctor");
			Debug.log(ex);
			ex.printStackTrace();
		}
	}

	public TradeListView(Model model) {
		super(model);
	}
	
	 private boolean doCopyCell(Point point) {
	 	if (point != null) {
	 		int row = tTradelist.getRowForPos(point.y);
	 		int col = tTradelist.getColumnForPos(point.x);
	 		if (row > -1 && col > -1) {
	 			TradeListModel model = getTradeListModel();
	 			Object o = model.getValueAt(row, col);
	 			if (o != null) {
	 				Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	 				cb.setContents(new StringSelection(o.toString()), null);
	 				return true;
	 			}
	 		}
	 	}
	 	return false;
	}

	 private boolean doNewManualState() {
		TradeListModel model = getTradeListModel();
		if (model == null) {
			return false;
			
		}
		int[] rows = tTradelist.getSelectedRows();
		
		fireUserEvent(EV_NEW_MANUAL_STATE, model.getIds(rows));	
		return true;		    
	}
	
	
	public void doSwitchTableFormat() {
		TradeListModel model = getTradeListModel();
		if (model == null) {
			return;
		}
		
		int newFormat;
		if (model.getTableFormat() == TradeListModel.TABLE_FORMAT_LONG) {
			newFormat = TradeListModel.TABLE_FORMAT_SHORT;
			pItemFormat.setText(getResourceString("B_LONG_FORMAT"));
		} else {
			newFormat = TradeListModel.TABLE_FORMAT_LONG; 
			pItemFormat.setText(getResourceString("B_SHORT_FORMAT"));
		}
		boolean oldC = Synchronizer.setWaitCursor(true, this);
		model.setTableFormat(newFormat);
		Synchronizer.setWaitCursor(oldC, this);
	}

	/**
	 * Felder des Views mit Werten aus dem Model fuellen
	 */
	private void fillView() {
		if (getModel() == null)
			return;
		propertyHandler.syncFields();
		handleModelError();
	}
	
	private TradeListModel getTradeListModel() {
		return getModel() instanceof TradeListModel ? (TradeListModel)getModel() : null;
	}

	/**
	 * Method initComponents.
	 */
	private void initComponents() {
		this.setBackground(UIManager.getColor("DetailsPanel.background"));
		pTitle.setHeaderTitleColor(UIManager.getColor("TradeListView.foreground"));

		pTitle.setColorProgress(
			UIManager.getColor("TradeListView.titleBackground1"), 
			UIManager.getColor("TradeListView.titleBackground2"));

		tpTradeList.setTable(tTradelist);
		tTradelist.setGridEnabled(true);
		tTradelist.setRowCounterVisible(true);
		tTradelist.addSTableSelectionListener(listener);
		tTradelist.addTableEditorListener(listener);
		tTradelist.setSelectionMode(STable.MULTIPLE_SELECTION);
		
		// Initialize table popup menu
		pItemFormat.addActionListener(listener);
		pItemNewManualState.addActionListener(listener);
		pItemCopyCell.addActionListener(listener);
		SPopupProvider provider = tTradelist.getPopupProvider();
		SPopupMenu menu = provider.getPopupMenu();
		Component[] items = menu.getComponents();	
		menu.removeAll();	
		menu.add(pItemFormat);
		menu.addSeparator();
		menu.add(pItemNewManualState);
		menu.addSeparator();
		menu.add(pItemCopyCell);
		menu.addSeparator();
		if (items!=null) {
			for (int i=0;i<items.length;i++) {
				menu.add(items[i]);
			}
		}
		
		// Add the functions to the menu
		setActions (
			new ActionSet[] {
				  new ActionSet("EDIT_EDIT", true)
			}
		);

	}

	/**
	 * Method initLabels.
	 */
	protected void initLabels() {
		setTitle					(getResourceString("L_TITLE"));
		tpTradeList.setText			(getResourceString("L_HEADER_TRADE_LIST"));
		pItemFormat.setText			(getResourceString("B_LONG_FORMAT"));
		pItemNewManualState.setText	(getResourceString("B_NEW_MANUAL_STATE"));
		pItemCopyCell.setText		(getResourceString("B_COPY_CELL"));
	}

	/**
	 * Method initLayout.
	 */
	private void initLayout() {
		add(pTitle, 		new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));
		add(tpTradeList,	new GridBagConstraints2(0, 1, 1, 1, 1.0, 1.0, CENTER, BOTH, insets8888, 0, 0));
	}

	/**
	 * Initialisierung des PropertyHandlers
	 *
	 */
	private void initProperties() {
		int ro = PropertyHandler.READ_ONLY;
		propertyHandler.add(TradeListModel.TRADE_TABLE_MODEL,  tTradelist, ro);		
	}
	
	private void rowSelected(Component source, int row) {
		if (!(getModel() instanceof TradeListModel)) {
			return;
		}
	
		if(row >= 0) {
			fireUserEvent(new UserEvent(this, EV_EDIT_TRADE, Integer.valueOf(row)));
		} else {
			logMessage(new LogMessageEvent(this, LogMessageEvent.ERROR, getResourceString("TRADELIST_E_001")));
		}	
	}
	
	/**
	 * Setzen des ViewModels
	 */
	@Override
    public void setModel(Model newModel) {
		if (!(newModel instanceof TradeListModel))
			throw new IllegalArgumentException("Model not instance of TradeListModel!");
		propertyHandler.setModel(newModel);
		
		super.setModel(newModel);
		fillView();
		if (getTradeListModel() != null && getTradeListModel().getTableFormat() == TradeListModel.TABLE_FORMAT_LONG) {
			pItemFormat.setText(getResourceString("B_SHORT_FORMAT"));
		} else {
			pItemFormat.setText(getResourceString("B_LONG_FORMAT"));
		}

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
     * Konverierung von Zeilenposition im View zur Zeile im Model
     */
     public int convertRowIndexToModel(int row) {
        return tTradelist.convertRowIndexToModel(row);
    }

    /**
     * Konverierung von Zeile im Model zur Zeilenposition der Ansicht
     */
    public int convertRowIndexToView(int row) {
        return tTradelist.convertRowIndexToView(row);
    }

}
