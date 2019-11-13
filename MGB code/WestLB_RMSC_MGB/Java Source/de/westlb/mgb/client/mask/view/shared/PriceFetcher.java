package de.westlb.mgb.client.mask.view.shared;

import java.awt.Component;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.mask.model.shared.PriceFetcherModel;
import de.westlb.mgb.model.impl.BloombergRequestImpl;
import de.westlb.mgb.model.impl.EuwaxRequestImpl;
import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.application.event.LogMessageEvent;
import de.westlb_systems.xaf.ui.view.ErrorBox;
import de.westlb_systems.xaf.ui.view.PopUpDialog;

/**
 * Insert the type's description here.
 * @author: Jens Richelsen
 */
public class PriceFetcher extends AbstractView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9220453798398071895L;

	private static Logger logger = Logger.getLogger(PriceFetcher.class);

	private static final String RESOURCE_BASE = PriceFetcherModel.RESOURCE_BASE;


	private Component dialogOwner = null;
	private boolean success = true;

	private Exception exception = null;

	private PriceFetcherModel model = null;
	private ProgressDialog progressDialog = null;

	private static final int BLOMBERG_PRICE_FETCH_THREAD = 1;
	private static final int REUTERS_PRICE_FETCH_THREAD = 2;
	private static final int EUWAX_PRICE_FETCH_THREAD = 3;
	private static final int AUTO_CHECK_THREAD = 4;
	private static final int CHECK_INCONSISTANCY_THREAD = 5;

	private static final String PARAM_PASSWORD = "PARAM_PASSWORD";


	private class HandlerThread extends Thread {

		private int threadType = 0;
        private Map<String, String> parameterMap = null;

		public HandlerThread(int type, String name, Map<String, String> parameterMap) {
			this(type, name);
			this.parameterMap = parameterMap;
		}

		public HandlerThread(int type, String name) {
			super(name);
			threadType = type;
			setPriority(Thread.MIN_PRIORITY);
			setDaemon(true);
		}

		@Override
        public void run() {
			logger.debug("Thread <" + threadType + "> started");
			switch (threadType) {
				case BLOMBERG_PRICE_FETCH_THREAD :
					setSuccess(localFetchBloombergPrices());
					progressDialog.close();
					break;
				case REUTERS_PRICE_FETCH_THREAD :
					setSuccess(false);
					progressDialog.close();
					break;
				case EUWAX_PRICE_FETCH_THREAD :
					if (parameterMap != null && parameterMap.get(PARAM_PASSWORD)!= null)
					setSuccess(localFetchEuwaxPrices(parameterMap.get(PARAM_PASSWORD)));
					progressDialog.close();
					break;
				case AUTO_CHECK_THREAD :
					setSuccess(localAutoCheck());
					progressDialog.close();
					break;
				case CHECK_INCONSISTANCY_THREAD :
					setSuccess(localCheckInconsistancy());
					progressDialog.close();
					break;
				default :
					break;
			}
		}

	}

	private static class ThreadInvoker implements Runnable {
		private Thread invokerThread;
		public ThreadInvoker(Thread thread) {
			invokerThread = thread;
		}
		@Override
        public void run() {
			invokerThread.start();
		}
	}

	/**
	 * DateiHandler
	 *
	 * @param owner Componente auf die der Dialog angezeigt werden soll
	 */
	public PriceFetcher(Component owner) {
		super();
		dialogOwner = owner;
	}

	public boolean fetchBloombergPrices() {
		getProgressDialog().setInfo(getResourceString(RESOURCE_BASE + "HEADER_01"));
		getProgressDialog().setCancelable(true);
		HandlerThread thread = new HandlerThread(BLOMBERG_PRICE_FETCH_THREAD, "BLOMBERG_PRICE_FETCH_THREAD");
		SwingUtilities.invokeLater(new ThreadInvoker(thread));
		getProgressDialog().show();
		if (!success) {
			showError(getResourceString(RESOURCE_BASE + "E_003"));
			checkInconsistancy();
//		} else {
// no infowindow, not to interrupt the thread. can be changed to an automatic closing window,
// or to show the window in a thread.
//			showInfo(getResourceString(RESOURCE_BASE + "I_005"), null);
		}
		return success;
	}

	public boolean fetchEuwaxPrices(String password) {
		getProgressDialog().setInfo(getResourceString(RESOURCE_BASE + "HEADER_01"));
		getProgressDialog().setCancelable(true);
		HashMap<String, String> param = new HashMap<String, String>();
		param.put(PARAM_PASSWORD, password);
		HandlerThread thread = new HandlerThread(EUWAX_PRICE_FETCH_THREAD, "EUWAX_PRICE_FETCH_THREAD", param);
		SwingUtilities.invokeLater(new ThreadInvoker(thread));
		getProgressDialog().show();
		if (!success) {
			showError(getResourceString(RESOURCE_BASE + "E_003"));
			checkInconsistancy();
		}
		return success;
	}

	public boolean autoCheck() {
		getProgressDialog().setInfo(getResourceString(RESOURCE_BASE + "HEADER_02"));
		getProgressDialog().setCancelable(true);
		HandlerThread thread = new HandlerThread(AUTO_CHECK_THREAD, "AUTO_CHECK_THREAD");
		SwingUtilities.invokeLater(new ThreadInvoker(thread));
		getProgressDialog().show();
		if (!success) {
			showError(getResourceString(RESOURCE_BASE + "E_005"));
		} else {
			showInfo(getResourceString(RESOURCE_BASE + "I_014"), null);
		}
		return success;
	}

	public boolean checkInconsistancy() {
		getProgressDialog().setInfo(getResourceString(RESOURCE_BASE + "HEADER_03"));
		getProgressDialog().setCancelable(true);
		HandlerThread thread = new HandlerThread(CHECK_INCONSISTANCY_THREAD, "CHECK_INCONSISTANCY_THREAD");
		SwingUtilities.invokeLater(new ThreadInvoker(thread));
		getProgressDialog().show();
		if (!success) {
			showError(getResourceString(RESOURCE_BASE + "E_006"));
		} else {
			showInfo(getResourceString(RESOURCE_BASE + "I_022"), null);
		}
		return success;
	}


	private boolean localFetchBloombergPrices() {
		boolean oldCursor = Synchronizer.setWaitCursor(true, dialogOwner);
		boolean success = false;
		try {
			getPriceFetchModel().setProgress(getProgressDialog());
			getProgressDialog().setDetails(getResourceString(RESOURCE_BASE + "I_001"));
			success = getPriceFetchModel().fetchBloombergPrices();
		} catch (RemoteException re) {
			exception = re;
		} finally {
			Synchronizer.setWaitCursor(oldCursor, dialogOwner);
		}
		return success;
	}

	private boolean localFetchEuwaxPrices(String password) {
		boolean oldCursor = Synchronizer.setWaitCursor(true, dialogOwner);
		boolean success = false;
		try {
			getPriceFetchModel().setProgress(getProgressDialog());
			getProgressDialog().setDetails(getResourceString(RESOURCE_BASE + "I_031"));
			success = getPriceFetchModel().fetchEuwaxPrices(password);
		} catch (RemoteException re) {
			exception = re;
		} finally {
			Synchronizer.setWaitCursor(oldCursor, dialogOwner);
		}
		return success;
	}

	private boolean localCheckInconsistancy() {
		boolean oldCursor = Synchronizer.setWaitCursor(true, dialogOwner);
		boolean success = false;
		try {
			getPriceFetchModel().setProgress(getProgressDialog());
			getProgressDialog().setDetails(getResourceString(RESOURCE_BASE + "I_021"));
			success = getPriceFetchModel().checkInconsistantRequests(BloombergRequestImpl.class.getName());
			success = getPriceFetchModel().checkInconsistantRequests(EuwaxRequestImpl.class.getName());
		} catch (RemoteException re) {
			exception = re;
		} finally {
			Synchronizer.setWaitCursor(oldCursor, dialogOwner);
		}
		return success;
	}

	@SuppressWarnings("deprecation")
    private boolean localAutoCheck() {
		boolean oldCursor = Synchronizer.setWaitCursor(true, dialogOwner);
		boolean success = false;
		try {
			getPriceFetchModel().setProgress(getProgressDialog());
			getProgressDialog().setDetails(getResourceString(RESOURCE_BASE + "I_011"));
			success = getPriceFetchModel().autoCheck();
		} catch (RemoteException re) {
			exception = re;
		} finally {
			Synchronizer.setWaitCursor(oldCursor, dialogOwner);
		}
		return success;
	}

	/**
	 * Returns the PriceFetcherModel.
	 * Creates a new instance if it does not exist.
	 *
	 * @return PriceFetcherModel
	 */
	public PriceFetcherModel getPriceFetchModel() {
		if (model == null) {
			model = new PriceFetcherModel();
		}
		return model;
	}

	/**
	* Liefert den ProgressDialog.
	* <p>
	* Falls noch nicht vorhanden, wird eine neues Instanz des ProgressDialog erzeugt.
	*
	* @return der ProgressDialog
	*/
	private ProgressDialog getProgressDialog() {
		if (progressDialog == null) {
			progressDialog = new ProgressDialogImpl(dialogOwner);
		}
		return progressDialog;
	}

	/**
	 * Setzen der Instanzvariable 'success'.
	 *
	 * Success wird von den verschiedenen Handler Threads gesetzt
	 * 
	 * @param value true zeigt an dass eine parallele Aktion erfolgreich war.
	 */
	private synchronized void setSuccess(boolean value) {
		success = value;
	}
	
	/**
	 * Anzeigen der internen Fehlermeldung.
	 * Nach der Anzeige wird der Fehler gelöscht.
	 * 
	 * @param message Fehlermeldung
	 * @see de.westlb_systems.webvis.ui.view.ErrorBox
	 */
	private void showError(String message) {
		if (exception != null) {
			new ErrorBox(dialogOwner, message, exception).show();
			exception = null;
		}
	}

	/**
	 * Anzeigen einer Fehlermeldung.
	 * 
	 * @param message Fehlermeldung
	 * @param details Object fuer den Detail-Bereich der ErrorBox
	 */
	private void showInfo(String message, Object details) {

		PopUpDialog dialog = new PopUpDialog(dialogOwner);

		dialog.showMessage(message, LogMessageEvent.INFO);

	}

}
