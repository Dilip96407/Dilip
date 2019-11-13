
package de.westlb.mgb.client.mask.model.shared;

import java.rmi.RemoteException;

import org.apache.commons.lang3.StringUtils;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.vo.DataSelectionVo;
import de.westlb.mgb.client.server.vo.JobVo;
import de.westlb.mgb.client.ui.selection_list.AbstractSelectionList;
import de.westlb.mgb.client.ui.selection_list.MandantList;
import de.westlb.mgb.client.ui.util.ClientUtils;
import de.westlb_systems.xaf.util.table.swing.TablePoolComboBoxModel;

/**
 * GUI-Model for DataSelection
 *
 * @author Manfred Boerner
 */

public class DataSelectionModel extends AbstractModel implements JobSelectionModel {
	private String RESOURCE_BASE = getResourceBase();
	
    public static final String P_MANDANT_CO_MODEL 			= "mandantComboxModel";
    public static final String P_MANDANT 					= "mandant";
    public static final String P_SELECTED_JOBS				= "selectedJobs";
	public static final String P_AVAILABLE_JOBS				= "availableJobs";
    public static final String P_SELECTED_JOBS_TEXT 		= "selectedJobsText";

    /** Definition der Namen aller Properties im Model */
    private final String[] propertyNames =
        new String[] { 
        	P_MANDANT, 
        	P_MANDANT_CO_MODEL, 
        	P_SELECTED_JOBS_TEXT, 
        	P_AVAILABLE_JOBS,
        	P_SELECTED_JOBS, 
     	};

    private MandantList mandantList = null;
    private DataSelectionVo dataSelectionVo = null;

    /**
     * Default Konstruktor erstellt ein leeres Model
     *
     */
    public DataSelectionModel() {
        setPropertyNames(propertyNames);

        try {
            mandantList = new MandantList();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
		fillModel();
    }

    /**
     * Befuellen des Models aus dem Business Object
     *
     */
    private void fillModel() {
        try {
        	dataSelectionVo  = MgbServiceFactory.getService().getDataSelection();
		} catch (RemoteException e) {
			handleRemoteException(e);
			return;
		}

        Object item = mandantList.itemFor(dataSelectionVo.getMandantCode());
        if (getProperty(P_MANDANT_CO_MODEL) == null) {
	        setProperty(P_MANDANT_CO_MODEL, new TablePoolComboBoxModel(mandantList));
        }

		JobVo[] selectedJobs = dataSelectionVo.getSelectedJobs();
		JobVo[] availableJobs = dataSelectionVo.getAvailableJobs();
		
        setProperty(P_MANDANT, item);
        setProperty(P_SELECTED_JOBS, selectedJobs);
        setProperty(P_AVAILABLE_JOBS, availableJobs);
        updateSelectedJobsText();
    }
    
    private void updateSelectedJobsText() {
        JobVo[] jobs = getSelectedJobs();
        
        String selectedJobs;
        String jobName = null;
        if (jobs == null) {
            selectedJobs = getResourceString(RESOURCE_BASE + "V_JOBS_NULL_SELECTED");
        } else if (jobs.length == 0) {
            selectedJobs = getResourceString(RESOURCE_BASE + "V_JOBS_0_SELECTED");            
        } else if (jobs.length == 1) { 
            selectedJobs = getResourceString(RESOURCE_BASE + "V_JOBS_1_SELECTED");            
            jobName = ClientUtils.getNameForJob(jobs[0]);        
        } else {
        	selectedJobs = getResourceString(RESOURCE_BASE + "V_JOBS_N_SELECTED");
        }
        if (jobs != null) {
            selectedJobs = StringUtils.replace(selectedJobs, "<n>", "" + jobs.length);
        }
       	selectedJobs = StringUtils.replace(selectedJobs, "<jobName>", jobName);
        setProperty(P_SELECTED_JOBS_TEXT, selectedJobs);
    }


    /**
     * Setzen des BusinessObjects
     *
     * @param newBusinessObject eine DataMessage mit Kontakt-Daten
     */
    @Override
    public void setBusinessObject(Object newBusinessObject) {

    }

    @Override
    public JobVo[] getSelectedJobs() {
    	return (JobVo[])getProperty(P_SELECTED_JOBS);
    }

    @Override
    public JobVo[] getAvailableJobs() {
    	return dataSelectionVo != null ? dataSelectionVo.getAvailableJobs() : null;
    }
    
    /**
     * Overwrite method from model, because this model is edited by different views and i don't want
     * the properties to be cleared.
     */
    @Override
    public void dispose() {
    }

    /**
     * Prüfung der in der View eingegebenen Werte. Übernahme der Werte
     * falls die Eingaben ok sind.
     * 
     * @return true wenn OK, false sonst
     */
    @Override
    public boolean saveModel() {

        try {
            String mandant = (String) AbstractSelectionList.getKeyFrom(getProperty(P_MANDANT));
			
            // pruefe Mindestbefuellung
            if (mandant == null) {
                logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_002"));
                return false;
            }
                        
            DataSelectionVo dataSelection = new DataSelectionVo();
            dataSelection.setMandantCode(mandant);
            dataSelection.setSelectedJobs(getSelectedJobs());
            MgbServiceFactory.getService().setDataSelection(dataSelection);
            this.reload();
        } catch (RemoteException e) {
        	handleRemoteException(e);
            logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_EXECUTE"));
            return false;
        }

        setError(NO_ERROR);
        logMessage(LOG_INFO, " ");
        return true;
    }
    
    @Override
    public void reload() {
		fillModel();
    }

    /**
     * @see de.westlb.mgb.client.mask.model.JobSelectionModel#setSelectedJobs(JobVo[])
     */
    @Override
    public void setSelectedJobs(JobVo[] jobs) {
		setProperty(P_SELECTED_JOBS, jobs);
		updateSelectedJobsText();
    }

}
