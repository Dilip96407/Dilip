/*
 * Copyright (c) 2004, WestLB
 *
 * All rights reserved
 * This information contained herein may not be used in whole
 * or in part without the expressed written consent of WestLB Systems.
 */


package de.westlb.mgb.client.application;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Locale;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.mask.model.shared.DataSelectionModel;
import de.westlb.mgb.client.mask.view.shared.InputDialog;
import de.westlb.mgb.client.mask.view.shared.PriceFetcher;
import de.westlb.mgb.client.server.Mgb;
import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.MgbSessionSingleton;
import de.westlb.mgb.client.server.vo.DataSelectionVo;
import de.westlb.mgb.client.server.vo.JobVo;
import de.westlb.mgb.client.server.vo.SessionInfoVo;
import de.westlb.mgb.client.server.vo.TradeSearchParamsVo;
import de.westlb.mgb.model.definition.JobStateDef;
import de.westlb.mgb.model.definition.MgbConfigurationDef;
import de.westlb_systems.xaf.application.ActionHandler;
import de.westlb_systems.xaf.application.ActionSet;
import de.westlb_systems.xaf.application.ClipBoard;
import de.westlb_systems.xaf.application.ContentSet;
import de.westlb_systems.xaf.application.Folder;
import de.westlb_systems.xaf.application.HistoryStack;
import de.westlb_systems.xaf.application.MenuBar;
import de.westlb_systems.xaf.application.MultipleLanguage;
import de.westlb_systems.xaf.application.S3Bar;
import de.westlb_systems.xaf.application.StatusBar;
import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.application.ToolBar;
import de.westlb_systems.xaf.application.event.LogMessageEvent;
import de.westlb_systems.xaf.application.event.UserEvent;
import de.westlb_systems.xaf.swing.STable;
import de.westlb_systems.xaf.swing.SUtilities;
import de.westlb_systems.xaf.ui.view.PopUpDialog;
import de.westlb_systems.xaf.util.PropertyFactory;
import de.westlb_systems.xaf.util.SLocale;
import de.westlb_systems.xaf.util.table.AbstractTableProvider;
import de.westlb_systems.xaf.util.table.SetProvider;
import de.westlb_systems.xaf.util.table.TablePool;


/**
 * This class handles all ActionEvents send by common controls like  Menubar and Folderbar
 * (=S3Bar). Furthermore it handles all  ContentRequestEvents send by the current displayed
 * ContentSet.  Events from common controls are handled by the superclass which is part of the
 * WestLB Systems XAF framework. This class handles all events sent by the MGB ContentSets.
 *
 * @author M. Boerner
 * @version 1.0s
 */
public class ApplicationControl extends de.westlb_systems.xaf.application.ApplicationControl {
    private static final int SHOW_QUESTION = LogMessageEvent.QUESTION;
    private static final int SHOW_INFO = LogMessageEvent.INFO;
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * Default Constructor
     */
    public ApplicationControl() {
        this(null);
    }

    /**
     * Creates an ApplicationControl holding a reference to the client (May be  used to prevent
     * Class-Variables to be killed)
     *
     * @param clientObject
     */
    public ApplicationControl(Object clientObject) {
        super(clientObject);
		initJavaHelp();

        //       
        initClientProperties();

        // Set the maximum width of a table column
        STable.setMaxInitColWidth(1000);
        componentBox = createApplicationComponentBox();
        actionHandler = createActionHandler();

        // TODO: changeLanguage(SLocale.getDefaultLocale());
        changeLanguage(Locale.GERMAN);
    }

    /**
     * Changes the current languages. Refreshes selection lists, updates menu and tool bar and
     * force a refresh of the current view.
     *
     * @param newLocale
     */
    @SuppressWarnings("rawtypes")
    private void changeLanguage(Locale newLocale) {
        debug(3, "changeLanguage(" + newLocale + ")");

        if (SLocale.getDefaultLocale().equals(newLocale)) {
            return;
        }

        // The SLocale singleton is used by the XAF framework gui
        // components.  
        SLocale.setDefaultLocale(newLocale);

        // Selection lists might be language dependend.
        Enumeration tableProvider = TablePool.getTableProviders();

        while ((tableProvider != null) && tableProvider.hasMoreElements()) {
            Object provider = tableProvider.nextElement();

            if (provider instanceof SetProvider) {
                ((SetProvider) provider).setLocale(newLocale);
            }
        }

        // Update der ActionSets fuer Menu und ToolBar
        ActionHandler.getInstance().updateLabels();

        // Update DataSelectionView (alias FastSearch)
        if ((getFastSearch() != null) &&
                getFastSearch().getViewComponent() instanceof MultipleLanguage) {
            ((MultipleLanguage) getFastSearch().getViewComponent()).updateLabels();
        }

        // Update labels of all views that a referenced by the history stack
        if (useOldContent && (getHistoryStack() != null)) {
            int count = getHistoryStack().getActionCount();

            for (int a = 0; a < count; a++) {
                Object content = ((ContentSet) getHistoryStack().getContentSetAt(a)).getViewContent();

                if (content instanceof MultipleLanguage) {
                    ((MultipleLanguage) content).updateLabels();
                }
            }
        } else {
            // If history stack is not available update current view only.
            if ((getContentSet() != null) &&
                    getContentSet().getViewComponent() instanceof MultipleLanguage) {
                ((MultipleLanguage) getContentSet().getViewComponent()).updateLabels();
            }
        }

        // Update folderbar. Has to be called after update of history stack.
        if (getFolderBar() instanceof MultipleLanguage) {
            ((MultipleLanguage) getFolderBar()).updateLabels();
        }

        // Update layout and repaint.
        if (getApplicationPanel() != null) {
            getApplicationPanel().invalidate();
            getApplicationPanel().validate();
            getApplicationPanel().repaint();
        }
    }

    /**
     * Do all actions hat are necessary after users changes the client of  multi-client-capability.
     */
    @SuppressWarnings("rawtypes")
    private void handleMandantChange() {
        logger.debug("handleMandantChange");

        // Force a reload of the cached client information.
        try {
            MgbSessionSingleton.getInstance(true);
        } catch (RemoteException rE) {
            rE.printStackTrace();
        }

        // clear selection list cache
        Enumeration tableProvider = TablePool.getTableProviders();

        while ((tableProvider != null) && tableProvider.hasMoreElements()) {
            Object provider = tableProvider.nextElement();

            if (provider instanceof AbstractTableProvider) {
                ((AbstractTableProvider) provider).clearCache();
            }
        }

        // Reset market data proxy directory. The market data proxy directory is defined
        // in the client dependend configuration parameters.
        try {
            Mgb mgb = MgbServiceFactory.getService();
            String proxyDir = mgb.getMgbConfigurationValue(MgbConfigurationDef.MARKET_DATA_PROXY_DIRECTORY);
            mgb.setMarketDataProxyDirectory(proxyDir);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        // Recreate all components of the application panel. This is necessary because
        // masks use client specific classes and properties like colors.
        ((de.westlb.mgb.client.application.AbstractClient) client).recreateComponents();
        start(ContentID.HISTORY_HOME, null);
    }

    /**
     * Optionally do some clean up at destroy time of this object.
     */
    @Override
    protected void finalize() {
        super.finalize();
        debug(3, "finalize()");
    }

    /**
     * Return a string defined in resource bundle for this component. Makes use of the
     * ApplicationResourceHandler singleton which manages the resource strings used by the
     * application package.
     *
     * @param key the key for the desired string
     *
     * @return the string for the given key
     */
    private String getResourceString(String key) {
        return ApplicationResourceHandler.getInstance().getResourceString(key);
    }

    /**
     * This method handles all events send by the common components folder bar, menu bar, status
     * bar and history stack of the application panel. If there is no common implementation in the
     * application control for the action event and the current content set is registered for this
     * event, the action event is passed to the  current content. An action event is registered
     * for the content if the content returnes the action command in it getActions() method.
     *
     * @param e the ActionEvent to handle.
     *
     * @see ActionEvent
     */
    protected void handleActionPerformed_run(ActionEvent e) {
        debug(3, "handleActionPerformed()");

        if ((e == null) || (e.getActionCommand() == null)) {
            return;
        }

        // Handle actions from the folder bar.
        if (handleActionPerformed_S3Bar(e)) {
            return;
        }

        // Handle actions from the edit menu
        if (handleActionPerformed_edit(e)) {
            return;
        }

        // Handle actions from the extra menu
        if (handleActionPerformed_extra(e)) {
            return;
        }

        // Handle actions from the file menu
        if (handleActionPerformed_file(e)) {
            return;
        }

        // Handle actions from the go menu
        if (handleActionPerformed_go(e)) {
            return;
        }

        // Handle actions from the help menu
        if (handleActionPerformed_help(e)) {
            return;
        }

        // Handle actions from the view menu
        if (handleActionPerformed_view(e)) {
            return;
        }

        String command = e.getActionCommand();
        provideActionToContent(command);
    }

    /**
     * Handles all action events send by the s3 folder bar.
     *
     * @param e the ActionEvent to handle.
     *
     * @return boolean false, if the action is not a s3 bar action.
     *
     * @see ActionEvent
     */
    private boolean handleActionPerformed_S3Bar(ActionEvent e) {
        // zurueck falls keine ActionCommand vorhanden
        if ((e == null) || (e.getActionCommand() == null)) {
            return false;
        }

        String command = e.getActionCommand();

        if (ActionCommands.GO_ANALYSE_MANUAL.equals(command)) {
            setContentSet(ContentID.MGB_CHECKSTATE, null, false);
        } else if (ActionCommands.GO_STATISTIC_TRADER_RECLAMATION.equals(command)) {
            setContentSet(ContentID.MGB_EMPLOYEE_RECLAMATION_LIST, null, false);
		} else if (ActionCommands.GO_ANALYSE_TRADE_RECLAMATION.equals(command)) {
			setContentSet(ContentID.MGB_TRADE_RECLAMATION_LIST, null, false);
        } else if (ActionCommands.GO_ANALYSE_JOBS.equals(command)) {
            setContentSet(ContentID.MGB_JOB_LIST, null, false);
        } else if (ActionCommands.GO_ANALYSE_COMBI_AUTOMATIC.equals(command)) {
            if (showMessageBox(SHOW_QUESTION, getResourceString("Q_CONFIRM_AUTO_CHECK"))) {
//                setContentSet(ContentID.MGB_TRADER_INFO_NEW, null, false);
                setContentSet(ContentID.MGB_INSTRUMENT_INFO_NEW, null, false);

                String password = getUserInput();
                if (password != null) {
                	PriceFetcher fetcher = new PriceFetcher(this.getContentSet().getViewComponent());
                	fetcher.fetchEuwaxPrices(password);
                	fetcher.fetchBloombergPrices();
                	// the autocheck is included into the pricefetch thread and runs on the server
                	// fetcher.autoCheck();
                	setContentSet(ContentID.MGB_CHECKSTATE, null, false);
                }
            }
        } else if (ActionCommands.GO_ANALYSE_EUWAX_AUTOMATIC.equals(command)) {
            if (showMessageBox(SHOW_QUESTION, getResourceString("Q_CONFIRM_AUTO_CHECK"))) {
                String password = getUserInput();
                if (password != null) {
                	PriceFetcher fetcher = new PriceFetcher(this.getContentSet().getViewComponent());
                	fetcher.fetchEuwaxPrices(password);
                	setContentSet(ContentID.MGB_CHECKSTATE, null, false);
                }
            }
        } else if (ActionCommands.GO_ANALYSE_BLB_AUTOMATIC.equals(command)) {
            if (showMessageBox(SHOW_QUESTION, getResourceString("Q_CONFIRM_AUTO_CHECK"))) {
//                setContentSet(ContentID.MGB_TRADER_INFO_NEW, null, false);
                setContentSet(ContentID.MGB_INSTRUMENT_INFO_NEW, null, false);

               	PriceFetcher fetcher = new PriceFetcher(this.getContentSet().getViewComponent());
               	fetcher.fetchBloombergPrices();
               	setContentSet(ContentID.MGB_CHECKSTATE, null, false);
            }
        } else if (ActionCommands.GO_ANALYSE_CONSISTANCY.equals(command)) {
                PriceFetcher fetcher = new PriceFetcher(this.getContentSet().getViewComponent());
                fetcher.checkInconsistancy();
                setContentSet(ContentID.MGB_CHECKSTATE, null, false);
        } else if (ActionCommands.GO_ANALYSE_SAMPLE.equals(command)) {
            if (showMessageBox(SHOW_QUESTION, getResourceString("Q_CONFIRM_SAMPLE_CHECK"))) {
        		try {
        			MgbServiceFactory.getService().runSampleChecks();
         		} catch (RemoteException re) {
        		}
            	setContentSet(ContentID.MGB_CHECKSTATE, null, false);
            }
        } else if (ActionCommands.GO_ANALYSE_OPRECL.equals(command)) {
            // Search all trades which have an Reclamation State and
            // the reclamation state is "OPEN"
            //
            TradeSearchParamsVo searchParamsVo = new TradeSearchParamsVo();
            searchParamsVo.setSearchInAllJobs(true);
            searchParamsVo.setJobStatus(new String[] {JobStateDef.JOB_OK_STATUS, JobStateDef.JOB_SPK_CLOSED_STATUS});
            searchParamsVo.setIsReclamationRequiredOpen(Boolean.TRUE);
    		try {
    			// getCurrentDataSelection does not throw an exception. But axis generater needs the signature.
    			DataSelectionVo selection = MgbServiceFactory.getService().getCurrentDataSelection();
    			searchParamsVo.setMandantCode(selection.getMandantCode());
     		} catch (RemoteException re) {
    		}
            setContentSet(ContentID.MGB_TRADE_LIST, searchParamsVo, false);
        } 
        
        else if (ActionCommands.GO_ADMIN_EMPLOYEE.equals(command)) {
            setContentSet(ContentID.MGB_EMPLOYEE_LIST, null, false);
        }

        else if (ActionCommands.GO_ADMIN_STATE_CODES.equals(command)) {
            setContentSet(ContentID.MGB_STATE_CODE_LIST, null, false);
        }
        
        if (ActionCommands.GO_ADMIN_EXCHANGE.equals(command)) {
            setContentSet(ContentID.MGB_EXCHANGE_LIST, null, false);
        }

        if (ActionCommands.GO_ADMIN_ADDON.equals(command)) {
            setContentSet(ContentID.MGB_ADDON_LIST, null, false);
        }

        if (ActionCommands.GO_ADMIN_INSTRUMENT.equals(command)) {
            setContentSet(ContentID.MGB_INSTRUMENT_LIST, null, false);
        }

        if (ActionCommands.GO_STATISTIC_BUSINESS_DAY.equals(command)) {
            setContentSet(ContentID.MGB_STATISTIC_BUSINESS_DAY, null, false);
        }
        
		if (ActionCommands.GO_STATISTIC_BUSINESS_MONTH.equals(command)) {
			setContentSet(ContentID.MGB_STATISTIC_BUSINESS_MONTH, null, false);
		}
		
		if (ActionCommands.GO_STATISTIC_JOB.equals(command)) {
			setContentSet(ContentID.MGB_STATISTIC_JOB, null, false);
		}

		if (ActionCommands.GO_STATISTIC_BUSINESS_UNIT.equals(command)) {
			setContentSet(ContentID.MGB_STATISTIC_BUSINESS_UNIT, null, false);
		}

		if (ActionCommands.GO_STATISTIC_LOCATION.equals(command)) {
			setContentSet(ContentID.MGB_STATISTIC_LOCATION, null, false);
		}
		if (ActionCommands.GO_STATISTIC_LIMIT_UNIT.equals(command)) {
			setContentSet(ContentID.MGB_STATISTIC_LIMIT_UNIT, null, false);
		}
		
		if (ActionCommands.GO_STATISTIC_TRADE_TYPE.equals(command)) {
			setContentSet(ContentID.MGB_STATISTIC_TRADE_TYPE, null, false);
		}

		if (ActionCommands.GO_STATISTIC_TRADE_CATEGORY_BOND.equals(command)) {
			setContentSet(ContentID.MGB_STATISTIC_TRADE_CATEGORY_BOND, null, false);
		}

        if (ActionCommands.GO_STATISTIC_TRADE_CATEGORY_DERIVATIVE.equals(command)) {
            setContentSet(ContentID.MGB_STATISTIC_TRADE_CATEGORY_DERIVATIVE, null, false);
        }

        if (ActionCommands.GO_STATISTIC_TRADE_CATEGORY_REPO.equals(command)) {
			setContentSet(ContentID.MGB_STATISTIC_TRADE_CATEGORY_REPO, null, false);
		}

        if (ActionCommands.GO_STATISTIC_TRADE_CATEGORY_MM.equals(command)) {
            setContentSet(ContentID.MGB_STATISTIC_TRADE_CATEGORY_MM, null, false);
        }

        if (ActionCommands.GO_STATISTIC_TRADE_CATEGORY_EQUITY.equals(command)) {
			setContentSet(ContentID.MGB_STATISTIC_TRADE_CATEGORY_EQUITY, null, false);
		}

		if (ActionCommands.GO_STATISTIC_TRADE_TYPE_EQUITY.equals(command)) {
	        setContentSet(ContentID.MGB_STATISTIC_TRADE_TYPE_EQUITY, null, false);
	    }

        if (ActionCommands.GO_STATISTIC_BOOK.equals(command)) {
            setContentSet(ContentID.MGB_STATISTIC_BOOK, null, false);
        }

        if (ActionCommands.GO_STATISTIC_INSTRUMENT.equals(command)) {
            setContentSet(ContentID.MGB_STATISTIC_INSTRUMENT, null, false);
        }

		if (ActionCommands.GO_STATISTIC_TRADER.equals(command)) {
			setContentSet(ContentID.MGB_STATISTIC_TRADER, null, false);
		}

        if (ActionCommands.GO_ADMIN_PRICE_CHECK_CATEGORIE.equals(command)) {
            setContentSet(ContentID.MGB_PRICE_CHECK_CATEGORY_LIST, null, false);
        }

        if (ActionCommands.GO_ADMIN_MGB_CONFIGURATION.equals(command)) {
            setContentSet(ContentID.MGB_CONFIGURATION_LIST, null, false);
        } else {
            return false;
        }

        return true;
    }

    /**
     * Behandlung der Actions, die ueber das Menu 'File' zu erreichen sind
     *
     * @param e the ActionEvent to handle.
     *
     * @return boolean false, if the action is not a file menu action.
     *
     * @see ActionEvent
     */
    private boolean handleActionPerformed_file(ActionEvent e) {
        // zurueck falls keine ActionCommand vorhanden
        if ((e == null) || (e.getActionCommand() == null)) {
            return false;
        }

        String command = e.getActionCommand();

        if (command.equals(ActionCommands.FILE_EXIT)) {
            fireUserEvent(new UserEvent(this, 0, ActionCommands.FILE_EXIT));
        } else {
            return false;
        }

        return true;
    }

    /**
     * Handles all action events send by the edit menu.
     *
     * @param e the ActionEvent to handle.
     *
     * @return boolean false, if the action is not a edit menu action.
     *
     * @see ActionEvent
     */
    private boolean handleActionPerformed_edit(ActionEvent e) {
        // zurueck falls keine ActionCommand vorhanden
        if ((e == null) || (e.getActionCommand() == null)) {
            return false;
        }

        String command = e.getActionCommand();

        // Historie
        if (command.equals(ActionCommands.EDIT_NEW_ADDON)) {
            setContentSet(ContentID.MGB_ADDON_EDIT, null, false);
        } else if (command.equals(ActionCommands.EDIT_NEW_EMPLOYEE)) {
            setContentSet(ContentID.MGB_EDIT_EMPLOYEE, null, false);
        } else if (command.equals(ActionCommands.EDIT_NEW_EXCHANGE)) {
            setContentSet(ContentID.MGB_EXCHANGE_EDIT, null, false);                   
        } else if(command.equals("EDIT_COPY")) {
			if(!clipBoard.copyText(getApplicationPanel())){
				provideActionToContent(command);
			}
		} else if(command.equals("EDIT_CUT")) {
			if(!clipBoard.cutText(getApplicationPanel())){
				provideActionToContent(command);
			}
		} else if(command.equals("EDIT_PASTE")) {
			if(!clipBoard.pasteText(getApplicationPanel())){
				provideActionToContent(command);
			}
		} else {
            return false;
        }

        return true;
    }

    /**
     * Handles all action events send by the exra menu.
     *
     * @param e the ActionEvent to handle.
     *
     * @return boolean false, if the action is not a extra menu action.
     *
     * @see ActionEvent
     */
    private boolean handleActionPerformed_extra(ActionEvent e) {
        // back if no action command
        if ((e == null) || (e.getActionCommand() == null)) {
            return false;
        }

        String command = e.getActionCommand();

        Boolean forceShow = Boolean.TRUE;

        // Extra
        if (command.equals(ActionCommands.EXTRA_NEW_INSTRUMENTS)) {
            setContentSet(ContentID.MGB_INSTRUMENT_INFO_NEW, forceShow, false);
        } else if (command.equals(ActionCommands.EXTRA_NEW_LOCATIONS)) {
                setContentSet(ContentID.MGB_LOCATION_INFO_NEW, forceShow, false);
        } else if (command.equals(ActionCommands.EXTRA_NEW_TRADERS)) {
            setContentSet(ContentID.MGB_TRADER_INFO_NEW, forceShow, false);
        } else if (command.equals(ActionCommands.EXTRA_SEARCH_TRADES)) {
            TradeSearchParamsVo params = new TradeSearchParamsVo();
    		try {
    			// getCurrentDataSelection does not throw an exception. But axis generater needs the signature.
    			DataSelectionVo selection = MgbServiceFactory.getService().getCurrentDataSelection();
    			params.setMandantCode(selection.getMandantCode());
    			JobVo[] jobs = selection.getSelectedJobs();
    			Long[] jobIds = new Long[jobs.length];
    			for (int i = 0; i < jobIds.length; i++) {
    				jobIds[i] = jobs[i].getJobId(); 
    			}
    			params.setJobIds(jobIds);
    		} catch (RemoteException re) {
    		}
            setContentSet(ContentID.MGB_TRADE_SEARCH_DIALOG, params, false);
        } else if (command.equals(ActionCommands.EXTRA_DUAL_CONTROL_JOBS)) {
            setContentSet(ContentID.MGB_DUAL_CONTROL_INFO, forceShow, false);
        } else if (command.equals(ActionCommands.EXTRA_APP_STATISTIC)) {
            setContentSet(ContentID.MGB_APP_STATISTIC, null, false);
        } else if (command.equals(ActionCommands.EXTRA_ADMIN_LOGIN)) {
            try {
                SessionInfoVo sessionInfo = MgbSessionSingleton.getInstance(false);

                if (!sessionInfo.getEmployee().isAdmin()) {
                    showMessageBox(SHOW_INFO, getResourceString("E_NOT_ADMIN"));
                } else {
                    setContentSet(ContentID.MGB_ADMIN_LOGIN, forceShow, false);
                    sessionInfo = MgbSessionSingleton.getInstance(false);

                    String userName = " " + sessionInfo.getEmployee().getFirstName() + " " +
                        sessionInfo.getEmployee().getLastName() + " ";
                    getStatusBar().setTextAt(StatusBar.USERNAME, userName);
                    handleMandantChange();
                }
            } catch (Exception exc) {
            }
        } else if (command.equals(ActionCommands.EXTRA_REFRESH_SELECTION_LISTS)) {
            setContentSet(ContentID.MGB_REFRESH_SELECTION_LIST, null, false);
        }	else if (command.equals(ActionCommands.EXTRA_DEBUG)) {
			setContentSet(ContentID.MGB_DEBUG, null, false);
		}	 else {
            return false;
        }

        return true;
    }

    /**
     * Handles all action events send by the go menu.
     *
     * @param e the ActionEvent to handle.
     *
     * @return boolean false, if the action is not a go menu action.
     *
     * @see ActionEvent
     */
    private boolean handleActionPerformed_go(ActionEvent e) {
        // zurueck falls keine ActionCommand vorhanden
        if ((e == null) || (e.getActionCommand() == null)) {
            return false;
        }

        String command = e.getActionCommand();

        // Historie
        if (command.equals(ActionCommands.GO_HOME)) {
            setContentSet(ContentID.HISTORY_HOME, null, false);
        } else if (command.equals(ActionCommands.GO_PREV)) {
            setContentSet(ContentID.HISTORY_PREV, null, false);
        } else if (command.equals(ActionCommands.GO_NEXT)) {
            setContentSet(ContentID.HISTORY_NEXT, null, false);
        } else if (command.startsWith(ActionCommands.GO_HIST)) {
            Integer index = new Integer(command.substring(ActionCommands.GO_HIST.length()));
            setContentSet(ContentID.HISTORY_INDEX, index, false);
        } else {
            return false;
        }

        return true;
    }

    /**
     * Handles all action events send by the help menu.
     *
     * @param e the ActionEvent to handle.
     *
     * @return boolean false, if the action is not a help menu action.
     *
     * @see ActionEvent
     */
    private boolean handleActionPerformed_help(ActionEvent e) {
        // zurueck falls keine ActionCommand vorhanden
        if ((e == null) || (e.getActionCommand() == null)) {
            return false;
        }

        String command = e.getActionCommand();

        // Historie
        if (command.equals(ActionCommands.HELP_ABOUT)) {
            setContentSet(ContentID.S3ABOUT, null, false);
        } else
        
        if (command.equals(ActionCommands.HELP_HELP)) {
//        	if (hb != null) {
//        		hb.setDisplayed(true);
//        	}
        } else

        if (command.equals(ActionCommands.HELP_PROBLEM_DB)) {
            setContentSet(ContentID.URLVIEW,
                PropertyFactory.getProperty("mgb.help.problem_db_url"), false);
        } else {
            return false;
        }

        return true;
    }

    /**
     * Behandlung der Actions, die ueber das Menu 'View' zu erreichen sind
     *
     * @param e the ActionEvent to handle.
     *
     * @return boolean false, if the action is not a go menu action.
     *
     * @see ActionEvent
     */
    private boolean handleActionPerformed_view(ActionEvent e) {
        // zurueck falls keine ActionCommand vorhanden
        if ((e == null) || (e.getActionCommand() == null)) {
            return false;
        }

        String command = e.getActionCommand();

        if (command.equals(ActionCommands.VIEW_S3BAR)) {
            setFolderBarVisible(!isFolderBarVisible());
        } else if (command.equals(ActionCommands.VIEW_TOOLBAR)) {
            setToolBarVisible(!isToolBarVisible());
        } else if (command.equals(ActionCommands.VIEW_LARGEICONS)) {
            if (getToolBar() != null) {
                getToolBar().toggleIconSize();

                ActionSet viewLI = actionHandler.getAction(ActionCommands.VIEW_LARGEICONS);

                if (viewLI != null) {
                    viewLI.setSelected(getToolBar().isLargeIcons());
                }

                setToolBarVisible(false);
                setToolBarVisible(true);
            }
        } else if (command.equals(ActionCommands.VIEW_STATUSBAR)) {
            setStatusBarVisible(!isStatusBarVisible());
        } else if (command.equals(ActionCommands.VIEW_REFRESH)) {
            setContentSet(ContentID.VIEW_REFRESH, null, false);
        }
        // keine Uebereinstimmung gefunden
        else {
            return false;
        }

        return true;
    }

    @Override
    protected void handlePropertyChanged(PropertyChangeEvent evt) {
        if (DataSelectionModel.P_MANDANT.equals(evt.getPropertyName())) {
            handleMandantChange();
        }

        super.handlePropertyChanged(evt);
    }

    /**
     * DOCUMENT ME!
     */
    @Override
    public void init() {
        clipBoard = new ClipBoard();
        setHistoryStack(new HistoryStack(ApplicationDefinitions.FOLDERBAR_RESSOURCE));
        initStatusBar();
        initialized = true;
    }

    /**
     * Initialisierung aus den Client-Properties. Die folgenden Properties werden ausgelesen:
     * 
     * <UL>
     * <li>
     * history.addDialogs (Dialoge sollen im Verlauf gespeichert werden, Voreinstellung false)
     * </li>
     * <li>
     * history.useOldContent (Im Verlauf soll der Content gemerkt werden, Voreinstellung true)
     * </li>
     * <li>
     * application.favorites (Favoriten sollen angezeigt werden, Voreinstellung false)
     * </li>
     * </ul>
     */
    private void initClientProperties() {

        sClientService = PropertyFactory.getProperty("toolBar.clientService");
        useOldContent = PropertyFactory.isTrueProperty("history.useOldContent", true);
        dlg2History = PropertyFactory.isTrueProperty("history.addDialogs", false);
        useFavorites = PropertyFactory.isTrueProperty("module.favorites", false);

        PropertyFactory.isTrueProperty("table.doubleColor", false);

        //	boolean tmp    = Client.getUserSettings().getTableDoubleColor().booleanValue();
        //	de.westlb_systems.webvis.ui.swing.table.Table.setInitValUseDoubleColor(tmp) ;
        //	de.westlb_systems.webvis.ui.swing.table.Table.setInitValTableGrid(tmp) ;
        Integer iTmp = Integer.valueOf(3);
        STable.setMaxInitColWidth(iTmp.intValue());

        if (useFavorites) {
            ActionSet action;
            String[] acts = { "FAVORITES", "FAVORITES_NEW", "FAVORITES_EDIT", "FAVORITES_DESKTOP" };

            for (int i = 0; i < acts.length; i++) {
                action = actionHandler.getAction(acts[i]);

                if (action != null) {
                    action.setEnabled(true);
                }
            }
        }

        int timeout = PropertyFactory.getIntProperty("CHANGE_TO_OTHER_TIMEOUT");
        CHANGE_TO_OTHER_TIMEOUT = (timeout > 0) ? timeout : CHANGE_TO_OTHER_TIMEOUT;
    }

    /**
     * DOCUMENT ME!
     *
     * @param contentSet DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    protected boolean preUnloadContent(ContentSet contentSet) {
        return true;
    }

    /**
     * Set the HistoryStack
     *
     * @see #getHistoryStack
     */
    public void setHistoryStack(HistoryStack newStack) {
        if (useFavorites) {
            if (getFolderBar() != null) {
                favorites = new HistoryStack(
                        "de.westlb_systems.webvis.crm.application.resource.FolderBarResource");
                favorites.setActionCommandPrefix("FAVORITE_");
                favorites.setUseNumber(false);

                Folder folder = new Folder(favorites);
                favorites.setFolder(folder);
                getS3FolderBar().addFavorite(folder);
            }
        }

        stack = newStack;

        if (getFolderBar() != null) {
            Folder folder = new Folder(stack);
            stack.setFolder(folder);
            getS3FolderBar().addHistory(folder);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param contentID DOCUMENT ME!
     */
    @Override
    public void start(String contentID) {
        start(contentID, null);
    }
    
	/**
	 * Starts the content assigned to id contentID and starts
	 * a thread that performs some initialization tasks 
	 * in background.
	 *
	 * @param contentID ContentID
	 */
	@Override
    public void start(String contentID, Object parameters) {
		startContent(contentID, parameters);

		ApplicationStartThread startThread = new ApplicationStartThread();
		startThread.addContentRequestListener(listener);
		startThread.start();
	}

    /**
     * Set the DataSelectionView
     *
     * @see #getFastSearch
     */
    public void setFastSearch(ContentSet newFastSearch) {
        if ((fastSearch != null) && (fastSearch.getContentRequestSource() != null)) {
            fastSearch.getContentRequestSource().removeContentRequestListener(listener);
        }

        fastSearch = newFastSearch;

        if ((fastSearch != null) && (fastSearch.getContentRequestSource() != null)) {
            fastSearch.getContentRequestSource().addContentRequestListener(listener);
        }

        if (getApplicationPanel() != null) {
            if (fastSearch != null) {
                getApplicationPanel().setFastSearch(fastSearch.getViewComponent());
            } else {
                getApplicationPanel().setFastSearch(null);
            }
        }
    }

    /**
     * Set the SFolderBar
     *
     * @see #getFolderBar
     */
    @Override
    public void setFolderBar(Component newFolderBar) {
        if (getFolderBar() != null) {
            ((S3Bar) getFolderBar()).removeActionListener(listener);
        }

        folderBar = newFolderBar;

        if (folderBar != null) {
            ((S3Bar) getFolderBar()).addActionListener(listener);
        }

        setFolderBarVisible(folderBar != null);
    }

    /**
     * DOCUMENT ME!
     *
     * @param visible DOCUMENT ME!
     */
    @Override
    public void setFolderBarVisible(boolean visible) {
        folderBarVisible = (getFolderBar() != null) ? visible : false;
        ApplicationDefinitions.view_s3bar.setSelected(folderBarVisible);

        if (getApplicationPanel() != null) {
            getApplicationPanel().setFolderBar(folderBarVisible ? getFolderBar() : null);

            if (getFastSearch() != null) {
                getApplicationPanel().setFastSearch(folderBarVisible
                    ? getFastSearch().getViewComponent() : null);
            }
        }
    }

    /**
     * Setzen der MenuBar
     *
     * @param newMenuBar die neue MenuBar
     *
     * @see #getMenuBar
     */
    @Override
    public void setMenuBar(MenuBar newMenuBar) {
        if (menuBar != null) {
            menuBar.removeActionListener(listener);
        }

        menuBar = newMenuBar;

        if (menuBar != null) {
            menuBar.addActionListener(listener);
        }

        if (getApplicationPanel() != null) {
            getApplicationPanel().setMenuBar(menuBar);
        }
    }

    /**
     * Set the StatusBar
     *
     * @see #getStatusBar
     */
    @Override
    public void setStatusBar(StatusBar newStatusBar) {
        statusBar = newStatusBar;

        if (statusBar != null) {
            statusBar.removeActionListener(listener);
        }

        statusBar = newStatusBar;

        if (statusBar != null) {
            statusBar.addActionListener(listener);
        }

        setStatusBarVisible(statusBar != null); // ? true : getTicker() != null && isStatusBarVisible());
    }

    /**
     * DOCUMENT ME!
     *
     * @param visible DOCUMENT ME!
     */
    @Override
    public void setStatusBarVisible(boolean visible) {
        //	statusBarVisible = getStatusBar() != null || getTicker() != null ? visible : false;
        statusBarVisible = (getStatusBar() != null) ? visible : false;
        ApplicationDefinitions.view_status.setSelected(statusBarVisible);

        if (getApplicationPanel() != null) {
            getApplicationPanel().setStatusBar(statusBarVisible ? getStatusBar() : null);

            //		getApplicationPanel().setTicker(statusBarVisible ? getTicker() : null);
        }
    }

    /**
     * Set the Ticker
     *
     * @see #getTicker
     */
    public void setTicker(Component newTicker) {
        ticker = newTicker;

        //	setStatusBarVisible(ticker != null ? true : getStatusBar() != null && isStatusBarVisible());
        appPanel.setTicker(ticker);
    }

    /**
     * Setzen der ToolBar
     *
     * @param newToolBar die neue ToolBar
     *
     * @see #getToolBar
     */
    @Override
    public void setToolBar(ToolBar newToolBar) {
        if (toolBar != null) {
            toolBar.removeActionListener(listener);
        }

        toolBar = newToolBar;

        if (toolBar != null) {
            toolBar.addActionListener(listener);
        }

        setToolBarVisible(toolBar != null);
    }

    /**
     * DOCUMENT ME!
     *
     * @param visible DOCUMENT ME!
     */
    @Override
    public void setToolBarVisible(boolean visible) {
        toolBarVisible = (getToolBar() != null) ? visible : false;
        ApplicationDefinitions.view_toolbar.setSelected(toolBarVisible);

        if (getApplicationPanel() != null) {
            getApplicationPanel().setToolBar(toolBarVisible ? getToolBar() : null);
        }
    }

    /**
     * Get the DataSelectionView
     *
     * @return the DataSelectionView
     *
     * @see #setFastSearch
     */
    public ContentSet getFastSearch() {
        return fastSearch;
    }

    /**
     * Get the SFolderBar
     *
     * @return the SFolderBar
     *
     * @see #setFolderBar
     */
    @Override
    public Component getFolderBar() {
        return folderBar;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public S3Bar getS3FolderBar() {
        return (S3Bar) getFolderBar();
    }

    /**
     * Set the HistoryStack
     *
     * @return the HistoryStack
     *
     * @see #setHistoryStack
     */
    @Override
    public HistoryStack getHistoryStack() {
        return stack;
    }

    /**
     * Get the MenuBar
     *
     * @return the MenuBar
     *
     * @see #setMenuBar
     */
    @Override
    public MenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * Set the Ticker
     *
     * @return the StatusBar
     *
     * @see #setStatusBar
     */
    @Override
    public StatusBar getStatusBar() {
        return statusBar;
    }

    /**
     * Get the ToolBar
     *
     * @return the ToolBar
     *
     * @see #setToolBar
     */
    @Override
    public ToolBar getToolBar() {
        return toolBar;
    }

    /**
     * Insert the method's description here. <br> Creation date: (24.07.01 15:30:14) <br>
     */
    protected void initStatusBar() {
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean isFolderBarVisible() {
        return folderBarVisible;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean isStatusBarVisible() {
        return statusBarVisible;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean isToolBarVisible() {
        return toolBarVisible;
    }

    /**
     * @see de.westlb_systems.xaf.application.ApplicationControl#handleActionPerformedRun(ActionEvent)
     */
    @Override
    protected void handleActionPerformedRun(ActionEvent e) {
        long start = System.currentTimeMillis();
        logger.debug("START "+e.getActionCommand());
        handleActionPerformed_run(e);
        logger.debug("STOP "+e.getActionCommand()+" ("+(System.currentTimeMillis()-start)+" millis)");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public ActionHandler createActionHandler() {
        ApplicationDefinitions def = new ApplicationDefinitions();
        ActionHandler.getInstance().setDefaultActions(def.getActionSets());

        return ActionHandler.getInstance();
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public de.westlb_systems.xaf.application.ApplicationComponentBox createApplicationComponentBox() {
        return new ApplicationComponentBox();
    }

    /**
     * @see de.westlb_systems.xaf.application.ApplicationControl#connectContent(ContentSet)
     */
    @Override
    public boolean connectContent(ContentSet contentSet) {
        boolean retCode = super.connectContent(contentSet);

        if (contentSet.getViewContent() instanceof MayBeRefreshSupport) {
            ((MayBeRefreshSupport) contentSet.getViewContent()).mayBeRefresh();
        }

        return retCode;
    }

    /**
     * use this methode to show a Message Box
     *
     * @param type DOCUMENT ME!
     * @param message DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    protected boolean showMessageBox(int type, Object message) {
        Component frame = SUtilities.getRootFrame(appPanel);
        boolean oldEnabled = true;
        PopUpDialog popUp = new PopUpDialog(frame);

        try {
            if (frame != null) {
                oldEnabled = frame.isEnabled();
                frame.setEnabled(false);
            }

            String text = (message != null) ? message.toString() : "No Message";
            boolean oldC = Synchronizer.setWaitCursor(false, frame);
            boolean ret = popUp.showMessage(text, type);
            Synchronizer.setWaitCursor(oldC, frame);

            return ret;
        } finally {
            if (frame != null) {
                frame.setEnabled(oldEnabled);
            }
        }
    }

    protected String getUserInput() {
        Component frame = SUtilities.getRootFrame(appPanel);
        boolean oldEnabled = true;
        InputDialog inputDialog = new InputDialog(frame, true);
        try {
        	String userName = MgbServiceFactory.getService().getDomainUser();
        	inputDialog.setInfo("Please enter the password of "+userName+".");
        } catch (Exception e) {
        	logger.error(e);
        }
        try {
            if (frame != null) {
                oldEnabled = frame.isEnabled();
                frame.setEnabled(false);
            }

            boolean oldC = Synchronizer.setWaitCursor(false, frame);
            inputDialog.show();
            String ret = inputDialog.getInputString(); 
            Synchronizer.setWaitCursor(oldC, frame);

            return ret;
        } finally {
            if (frame != null) {
                frame.setEnabled(oldEnabled);
            }
        }
    }

	/**
	 * 
	 */
	private void initJavaHelp() {
//		String hsName = "javahelp/jhelpset.hs";
//		try {
//			ClassLoader cl = ApplicationComponentBox.class.getClassLoader();
//			URL hsURL = HelpSet.findHelpSet(cl, hsName);
//			hs = new HelpSet(null, hsURL);
//			hb = hs.createHelpBroker();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			System.err.println("HelpSet " + hsName + " not found!");
//		}
		        
	}


    /**
     * @see de.westlb_systems.xaf.application.ApplicationControl#debug(int, java.lang.String)
     */
    @Override
    protected void debug(int level, String message) {
        logger.debug(message);
    }
    /**
     * @see de.westlb_systems.xaf.application.ApplicationControl#debug2(int, java.lang.String)
     */
    @Override
    protected void debug2(int level, String message) {
        logger.debug(message);
    }
}