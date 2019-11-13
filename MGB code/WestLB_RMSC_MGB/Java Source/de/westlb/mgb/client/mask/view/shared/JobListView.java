package de.westlb.mgb.client.mask.view.shared;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.UIManager;

import de.westlb.mgb.client.application.LocalUserSettings;
import de.westlb.mgb.client.mask.model.shared.JobListModel;
import de.westlb_systems.xaf.application.ActionSet;
import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.application.event.LogMessageEvent;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SFileDialog;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SMenuItem;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.swing.SPopupMenu;
import de.westlb_systems.xaf.swing.SPopupProvider;
import de.westlb_systems.xaf.swing.STable;
import de.westlb_systems.xaf.swing.STableEditorEvent;
import de.westlb_systems.xaf.swing.STableEditorListener;
import de.westlb_systems.xaf.swing.STableSelectionEvent;
import de.westlb_systems.xaf.swing.STableSelectionListener;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.ui.view.TablePanel;
import de.westlb_systems.xaf.ui.view.TitlePanel;
import de.westlb_systems.xaf.util.Debug;

/**
 * @author WSY4148
 *
 * Dialog to enter a new manual state of a trade.
 */
public class JobListView extends AbstractView {
	/**
     * 
     */
    private static final long serialVersionUID = 963988448952215824L;
    private String RESOURCE_BASE = getResourceBase();

	public final static int EV_NEW					= 1;
	public final static int EV_CLOSE				= 2;
	public final static int EV_SHOW_TRADES			= 3;
	public final static int EV_SHOW_PRICE_DEVIATION			= 4;
	public final static int EV_SHOW_PRICE_DEVIATION_STAT	= 5;
	public final static int EV_SELECT_JOBS			= 6;
	public final static int EV_LOG_VIEW = 7;
	
	/** gui components */
	private TitlePanel pTitle = new TitlePanel();
	private TablePanel tpResultPanel	= new TablePanel();
	private TablePanel tpFilterPanel	= new TablePanel();
	private STable tResult				= new STable();
	private SPanel pFilterPanel 		= new SPanel();

	private SLabel lFilter			= new SLabel();	
	
	private STextField tfFilter		= new STextField();

	private SMenuItem pItemClose		= new SMenuItem();
	private SMenuItem pItemSelectJobs	= new SMenuItem();
	private SMenuItem pItemShowTrades	= new SMenuItem();	
	private SMenuItem pItemShowPriceDeviation			= new SMenuItem();
	private SMenuItem pItemShowPriceDeviationStatistic	= new SMenuItem();	
	
	private SFileDialog fileDialog = null;
	
	/** Property handler to synchronize model and view */
	private PropertyHandler propertyHandler = new PropertyHandler();	

	/** Listener fuer alle Events */
	private Listener listener = new Listener();
	
	/**
	 * Listener fuer alle Events
	 */
	private class Listener implements STableSelectionListener, STableEditorListener, ActionListener, KeyListener {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(ActionEvent)
         */
		@Override
        public void actionPerformed(ActionEvent ae) {
			Object source = ae.getSource();
			if (source == pItemClose) {
				doClose(tResult.getSelectedRows());
			} else if (source == pItemShowTrades) {
				doShowTrades(tResult.getSelectedRows());
			} else if (source == pItemShowPriceDeviation) {
				doShowPriceDeviations(tResult.getSelectedRows());
			} else if (source == pItemShowPriceDeviationStatistic) {
				doShowPriceDeviationStatistic(tResult.getSelectedRows());
			} else if (source == pItemSelectJobs) {
				doSelectJobs(tResult.getSelectedRows());
			}
		}


        /**
         * @see de.westlb_systems.xaf.swing.STableSelectionListener#selectionChanged(STableSelectionEvent)
         */
        @Override
        public void selectionChanged(STableSelectionEvent e) {
			if (e.isDoubleClicked()) {
			    doSelectJobs(new int[] {e.getRow()});
			}
        }

        /**
         * @see de.westlb_systems.xaf.swing.STableEditorListener#invokeEditor(STableEditorEvent)
         */
        @Override
        public void invokeEditor(STableEditorEvent e) {
			JobListModel model = getJobListModel();
			if (model == null) {
				return;
			}
			
        	if (STableEditorEvent.DELETE.equals(e.getActionCommand())) {
       			doDelete(tResult.getSelectedRows());
        	} else if (STableEditorEvent.INSERT.equals(e.getActionCommand())) {
        		doImport();
            } else if (STableEditorEvent.SAVEAS.equals(e.getActionCommand())) {
//                doSaveAs(tResult.getSelectedRow());
                doLogView(tResult.getSelectedRow());
        	} 
		}

        /**
         * @see java.awt.event.KeyListener#keyPressed(KeyEvent)
         */
        @Override
        public void keyPressed(KeyEvent e) {
        	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        		doSearch();
        	}
        }

        /**
         * @see java.awt.event.KeyListener#keyReleased(KeyEvent)
         */
        @Override
        public void keyReleased(KeyEvent e) {
        }

        /**
         * @see java.awt.event.KeyListener#keyTyped(KeyEvent)
         */
        @Override
        public void keyTyped(KeyEvent e) {
        }


	}
	
	/**
	 * Constructor for CheckStateView.
	 */
	public JobListView() {
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
	public JobListView(Model model) {
		this();
		setModel(model);
	}

	/**
	 * Starts the search
	 */
	public boolean doSearch() {
		boolean success = false;
		// Ensure model to be uptodate
		propertyHandler.syncModel();

        boolean oldC = Synchronizer.setWaitCursor(true, this);
        if (getJobListModel() != null) {
        	success = getJobListModel().doSearch();
        }        
        Synchronizer.setWaitCursor(oldC, this);

        if (!success && getModel() != null) {
            int error = getModel().getError();
            if ((error == Model.APPLICATION_ERROR) || (error == Model.DATABASE_ERROR)) {
                Object message = getModel().getErrorMessage();
                Object details = getModel().getErrorDetails();
                showMessageBox(SHOW_ERROR, message, details);
            }
        } 

		fillView();
		tResult.setSelectedRow(0);
		return success;
	}

	public boolean doImport() {
		fireUserEvent(EV_NEW, null);	
		return true;
	}

    public boolean doLogView(int row) {
        JobListModel model = getJobListModel();
        if (model == null) { 
            return false;
        }

        fireUserEvent(EV_LOG_VIEW, model.getJobIds(new int[] { row})[0]);
        return true;
    }

    public boolean doSaveAs(int row) {
        boolean success = false;
        if (fileDialog == null) {
            fileDialog = new SFileDialog(this);
        }

        fileDialog.setDirectory(LocalUserSettings.getInstance().getString("jobDir"));
        fileDialog.setMode(SFileDialog.SAVE);
        fileDialog.setTitle(getResourceString(RESOURCE_BASE + "T_FILE"));

        fileDialog.show();
        if ( fileDialog.getDirectory() != null) {
            LocalUserSettings.getInstance().setString("jobDir", fileDialog.getDirectory());
        }

        if (fileDialog.getFile() != null) {
            File file = new File(fileDialog.getDirectory(), fileDialog.getFile());
            // Synchronize model, because propertyHandler updates the model
            // only on focusLost-Events
            propertyHandler.syncModel();
                
            boolean oldC = Synchronizer.setWaitCursor(true, this);
            success = getJobListModel().doSaveAs(row, file);
            Synchronizer.setWaitCursor(oldC, this);

            if (!success) {
                handleModelError();
            } 

            getModel().reload();
            fillView();
            return success;
        }

        for (java.awt.Container parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof java.awt.Window) {
                ((java.awt.Window) parent).toFront();
                break;
            }
        }
        return success;
    }

    public boolean doSelectJobs(int[] rows) {
		if (rows == null || rows.length == 0)  {
			logMessage(new LogMessageEvent(this, LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_NOTHING_SELECTED")));
			return false;
		}			
		JobListModel model = getJobListModel();
		if (model == null) {
			return false;
			
		}
		fireUserEvent(EV_SELECT_JOBS, model.getJobIds(rows));	
		return true;
	}

	private boolean doShowTrades(int[] rows) {
		if (rows == null || rows.length == 0)  {
			logMessage(new LogMessageEvent(this, LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_NOTHING_SELECTED")));
			return false;
		}			
		JobListModel model = getJobListModel();
		if (model == null) {
			return false;
			
		}
		
		fireUserEvent(EV_SHOW_TRADES, model.getJobIds(rows));	
        return true;
	}

	private boolean doShowPriceDeviations(int[] rows) {
		if (rows == null || rows.length == 0)  {
			logMessage(new LogMessageEvent(this, LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_NOTHING_SELECTED")));
			return false;
		}			
		JobListModel model = getJobListModel();
		if (model == null) {
			return false;
			
		}
		fireUserEvent(EV_SHOW_PRICE_DEVIATION, model.getJobIds(rows));	
        return true;
	}

	private boolean doShowPriceDeviationStatistic(int[] rows) {
		if (rows == null || rows.length == 0)  {
			logMessage(new LogMessageEvent(this, LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_NOTHING_SELECTED")));
			return false;
		}			
		JobListModel model = getJobListModel();
		if (model == null) {
			return false;
			
		}
		
		fireUserEvent(EV_SHOW_PRICE_DEVIATION_STAT, model.getJobIds(rows));	
        return true;
	}

	public boolean doDelete(int[] rows) {
		if (rows == null || rows.length == 0)  {
			logMessage(new LogMessageEvent(this, LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_NOTHING_SELECTED")));
			return false;
		}			
		JobListModel model = getJobListModel();
		if (model == null) {
			return false;
			
		}
	
		if (!showMessageBox(SHOW_QUESTION, getResourceString(RESOURCE_BASE + "Q_DELETE"))) {
			return false;
		};

		boolean success = false;
		// Ensure model to be uptodate
		propertyHandler.syncModel();

        boolean oldC = Synchronizer.setWaitCursor(true, this);
       	success = getJobListModel().doDelete(rows);
        Synchronizer.setWaitCursor(oldC, this);

        if (!success) {
			handleModelError();
        } 

		getModel().reload();
		fillView();
		tResult.setSelectedRow(0);
		return success;				
	}

	public boolean doClose(int[] rows) {
		if (rows == null || rows.length == 0)  {
			logMessage(new LogMessageEvent(this, LogMessageEvent.ERROR, getResourceString(RESOURCE_BASE + "E_NOTHING_SELECTED")));
			return false;
		}
					
		boolean success = false;
		// Ensure model to be uptodate
		propertyHandler.syncModel();

		JobListModel model = getJobListModel();
		if (model == null) {
			return false;
		}

        boolean oldC = Synchronizer.setWaitCursor(true, this);
		success = model.doClose(rows);
		if (!success) {
			String msg = model.getErrorMessage();
			if (msg == JobListModel.ERROR_MSG_MANUAL_CHECK_NOT_FINISHED ||
			  	msg == JobListModel.ERROR_MSG_OPEN_RECLAMATIONS) {
				showMessageBox(SHOW_INFO, getResourceString(RESOURCE_BASE + msg));
			} else {
				handleModelError();
			}
		}
		Synchronizer.setWaitCursor(oldC, this);

		getModel().reload();
		fillView();
		tResult.setSelectedRow(0);
		return success;				
	}

    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        if (getJobListModel() == null) {
            return;
        }
        propertyHandler.syncFields();
    }
    
    /**
	 * Return casted model
	 * 
	 */
	public JobListModel getJobListModel() {
		return getModel() instanceof JobListModel ? (JobListModel)getModel() : null;
	}


    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(JobListModel.P_NUMBER_OF_JOBS,			tfFilter);	
		propertyHandler.add(JobListModel.P_SEARCH_RESULT_TABLE_MODEL, 	tResult);
    }


    /**
     * Method initLabels.
     */
    private void initLabels() {
    	this.setTitle					(getResourceString(RESOURCE_BASE + "T_001"));
		tpFilterPanel.setText			(getResourceString(RESOURCE_BASE + "T_FILTER"));
		lFilter.setText					(getResourceString(RESOURCE_BASE + "L_FILTER"));
		tpResultPanel.setText			(getResourceString(RESOURCE_BASE + "T_RESULT"));
		pItemClose.setText				(getResourceString(RESOURCE_BASE + "M_CLOSE"));
		pItemSelectJobs.setText			(getResourceString(RESOURCE_BASE + "M_SELECT_JOBS"));
		pItemShowTrades.setText			(getResourceString(RESOURCE_BASE + "M_SHOW_TRADES"));
		pItemShowPriceDeviation.setText				(getResourceString(RESOURCE_BASE + "M_PRICE_DEVIATION"));
		pItemShowPriceDeviationStatistic.setText	(getResourceString(RESOURCE_BASE + "M_PRICE_DEVIATION_STATISTIC"));
    }
    
    /**
     * Method initLayout.
     */
    private void initLayout() {
		int l, c;
		
		// Layout filterPanel
		l = 0; c = 0; // Row 0
	 	pFilterPanel.add(lFilter,	new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets8888, 0, 0));	 	

		l++; c = 0; 
	 	pFilterPanel.add(tfFilter,	new GridBagConstraints2(c++, l, 1, 1, 1.0, 0.0, NWEST, HORIZ, insets4888, 0, 0));	 	
				
		// Layout total view
		l = 0; c = 0; 
	 	this.add(pTitle,			new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets0000, 0, 0));	 	
	 	this.add(tpFilterPanel,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 0.0, CENTER, HORIZ, insets8888, 0, 0));	 	
	 	this.add(tpResultPanel,		new GridBagConstraints2(c, l++, 1, 1, 1.0, 1.0, CENTER, BOTH, insets8888, 0, 0));	 	

		setBackgroundForAllPanels(UIManager.getColor("DetailsPanel.background"));

    }	

    /**
     * Method initComponents.
     */
    private void initComponents() {
		pTitle.setHeaderTitleColor(UIManager.getColor("StateCodeList.foreground"));
		pTitle.setColorProgress(
			UIManager.getColor("StateCodeList.titleBackground1"), 
			UIManager.getColor("StateCodeList.titleBackground2"));
		
		tResult.setSelectionMode(STable.MULTIPLE_SELECTION);
		
		tpFilterPanel.setTable(pFilterPanel);
		tpResultPanel.setTable(tResult);
		tfFilter.addKeyListener(listener);
	
		tResult.setPopupProvider(STable.ADD_PROVIDER |STable.REMOVE_PROVIDER| STable.SAVEAS_PROVIDER);
		tResult.addTableEditorListener(listener);
		tResult.addSTableSelectionListener(listener);
		tResult.setGridEnabled(true);

		// Initialize table popup menu
		pItemClose.addActionListener(listener);
		pItemSelectJobs.addActionListener(listener);
		pItemShowTrades.addActionListener(listener);
		pItemShowPriceDeviation.addActionListener(listener);
		pItemShowPriceDeviationStatistic.addActionListener(listener);
		SPopupProvider provider = tResult.getPopupProvider();
		SPopupMenu menu = provider.getPopupMenu();
		Component[] items = menu.getComponents();	
		menu.removeAll();	
		if (items!=null) {
			for (int i=0;i<items.length;i++) {
				menu.add(items[i]);
			}
		}
		menu.addSeparator();
		menu.add(pItemClose);
		menu.add(pItemSelectJobs);
		menu.add(pItemShowTrades);
		menu.addSeparator();
		menu.add(pItemShowPriceDeviation);
		menu.add(pItemShowPriceDeviationStatistic);
		
		
		// Add the functions to the menu
		setActions (
			new ActionSet[] {
				  new ActionSet("EDIT_EDIT", true)
			}
		);

    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof JobListModel)) {
	        throw new IllegalArgumentException("Model not instance of JobListModel");
	    }
	    propertyHandler.setModel(newModel);
	    super.setModel(newModel);
	    doSearch();
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
