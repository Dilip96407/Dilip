package de.westlb.mgb.client.mask.model.shared;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.server.MgbSessionSingleton;
import de.westlb.mgb.client.server.vo.CheckStateVo;
import de.westlb.mgb.client.server.vo.JobSearchParamsVo;
import de.westlb.mgb.client.server.vo.JobVo;
import de.westlb.mgb.client.ui.tablemodel.TableModel;
import de.westlb.mgb.client.ui.tablemodel.TableModelFactory;
import de.westlb.mgb.model.definition.JobStateDef;

/**
 * Gui model for the exchange list view.
 *
 * @author Manfred Boerner
 */

public class JobListModel extends AbstractModel {

    static Logger logger = Logger.getLogger(JobListModel.class.getName());

	public static final String P_SEARCH_RESULT_TABLE_MODEL = "searchResultTableModel";
	public static final String P_NUMBER_OF_JOBS	= "numberOfJobs";
	public static final String ERROR_MSG_MANUAL_CHECK_NOT_FINISHED = "E_003";
	public static final String ERROR_MSG_OPEN_RECLAMATIONS = "E_004";
	public static final String ERROR_MSG_NOT_ADMIN = "E_005";

	public static final String P_TITLE = "title";

	private String RESOURCE_BASE = getResourceBase();

	private final String[] propertyNames = new String[] { P_SEARCH_RESULT_TABLE_MODEL, P_NUMBER_OF_JOBS };

	private JobVo[] searchResult = null;

	private JobSearchParamsVo searchParams = null;
	
	/**
	 * Default constructor to create an empty model
	 */
	public JobListModel() {
		setPropertyNames(propertyNames);
		fillModel();
	}

	public JobListModel(Object businessObject) {
		this();
		setBusinessObject(businessObject);
	}
	
	public void clearSearchResult() {
		searchResult = null;
		setProperty(P_SEARCH_RESULT_TABLE_MODEL, null);	
	}	

	/**
	 * Fills the model from the business model.
	 */
	private void fillModel() {
		if (searchParams != null) {
			setProperty(P_NUMBER_OF_JOBS, searchParams.getNumberOfJobs());
		}
		
		return;
	}

	public boolean doSearch() {
		if (searchParams == null) {
			searchParams = new JobSearchParamsVo();
		}
		Object numberOfJobs = getProperty(P_NUMBER_OF_JOBS);
		
		if (numberOfJobs != null) {
		    if (numberOfJobs instanceof String) {
		        try {
		            searchParams.setNumberOfJobs(Integer.valueOf(Integer.parseInt((String)numberOfJobs)));
		        } catch (NumberFormatException nfe) {
		        }
		    } else {
                searchParams.setNumberOfJobs((Integer)numberOfJobs);
		    }
		}
	
		searchResult = null;
		try {
			searchResult = MgbServiceFactory.getService().findAllJobs(searchParams);
			TableModel tableModel = TableModelFactory.createTableModel("JobTable", searchResult);
			setProperty(P_SEARCH_RESULT_TABLE_MODEL, tableModel);
		} catch (RemoteException e) {
			handleRemoteException(e);
		}
        
		// Ok to start the search.
		return true;
	}

	
	public Long getId(int row) {
		if (searchResult == null || searchResult.length < row) {
			return null;
		}

		return searchResult[row].getJobId();
	}
	
	/**
	 * Returns true, if the currenly logged has admin role.
	 */
	public boolean isAdmin() {
		boolean isAdmin = false;
		try {
			isAdmin = MgbSessionSingleton.getInstance(false).getEmployee().isAdmin();
			if (!isAdmin) {
				setError(APPLICATION_ERROR);
				setErrorMessage(ERROR_MSG_NOT_ADMIN);
				return false;
			}		
		} catch (RemoteException rE) {
		}
		
		return isAdmin;
	}

	/**
	 * Executes assignment traderid to selected employee
	 */
	public boolean doDelete(int[] rows) {
		try {
			for (int i = 0; i < rows.length; i++) {
				if (getId(rows[i]) == null) {
					logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_002"));
					return false;
				}
            }

			for (int i = 0; i < rows.length; i++) {
				MgbServiceFactory.getService().deleteJob(getId(rows[i]));
			}
		} catch (RemoteException e) {
			handleRemoteException(e);
			return false;
		}

		return true;
	}

	public CheckStateVo getCheckStateVo(int row) throws RemoteException {
		return MgbServiceFactory.getService().getCheckState(new Long[] {getId(row)});
	}
	
    public CheckStateVo getCheckStateVo(int[] rows) throws RemoteException {
        Long[] jobIds = new Long[rows.length];
        for (int i = 0; i < rows.length; i++) {
            jobIds[i] = getId(rows[i]);
        }
        return MgbServiceFactory.getService().getCheckState(jobIds);
    }

    public Long[] getJobIds(int[] rows) {
		Long jobIds[] = new Long[rows == null ? 0 : rows.length];
		for (int i = 0; i < jobIds.length; i++) {
			jobIds[i] = getId(rows[i]);
		}
		return jobIds;
	}

	public boolean doClose1(int[] rows) {
		try {
			CheckStateVo checkStateVo = getCheckStateVo(rows);
			if (!checkStateVo.isManualCheckFinished()) {
				logger.debug("Unhandled manual checks");
				setError(APPLICATION_ERROR);
				setErrorMessage(ERROR_MSG_MANUAL_CHECK_NOT_FINISHED);
				return false;
			}
			if (!checkStateVo.isReclamationFinished()) {
				logger.debug("Unhandled reclamations");
				setErrorMessage(ERROR_MSG_OPEN_RECLAMATIONS);
				return false;
				
			}
		} catch (RemoteException rE) {
			handleRemoteException(rE);
		}
		for (int i = 0; i < rows.length; i++) {
			Long id = null;
			if (getId(rows[i]) != null) {
				id = getId(rows[i]);
			}

			// Check minumum input
			if (id == null) {
				logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_002"));
				return false;
			}

			try {
				MgbServiceFactory.getService().setJobStatus(id, JobStateDef.JOB_CLOSED_STATUS);
			} catch (RemoteException e) {
				handleRemoteException(e);
				return false;
			}
		}
		return true;
	}

	public boolean doClose(int[] rows) {
	    boolean result = true;
        for (int i = 0; i < rows.length; i++) {
            Long id = null;
            if (getId(rows[i]) != null) {
                id = getId(rows[i]);
            }
            // Check minumum input
 
            try {
                if (id == null) {
                    logger.debug("Invalid job id in row "+rows[i]);
                    logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_002"));
                    result = false;
                } else {
                    CheckStateVo checkStateVo = MgbServiceFactory.getService().getCheckState(new Long[] {id});
                    if (!checkStateVo.isManualCheckFinished()) {
                        logger.debug("Unhandled manual checks in job "+id);
                        setError(APPLICATION_ERROR);
                        setErrorMessage(ERROR_MSG_MANUAL_CHECK_NOT_FINISHED);
                        result = false;
                    } else if (!checkStateVo.isReclamationFinished()) {
                        logger.debug("Unhandled reclamations in job "+id);
                        setError(APPLICATION_ERROR);
                        setErrorMessage(ERROR_MSG_OPEN_RECLAMATIONS);
                        result = false;
                    } else {
                        MgbServiceFactory.getService().setJobStatus(id, JobStateDef.JOB_CLOSED_STATUS);
                    }
                }
            } catch (RemoteException e) {
                handleRemoteException(e);
                result = false;
            }
        }
        return result;
    }

	/**
	 * Sets the business object where the model gets its data from.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		if (newBusinessObject instanceof JobSearchParamsVo) {
			searchParams = (JobSearchParamsVo)newBusinessObject;
		}
		super.setBusinessObject(newBusinessObject);
		fillModel();
	}

	@Override
    public void reload() {
   		doSearch();
   	 	fillModel();
	}

    public boolean doSaveAs(int row, File file) {
        boolean result = true;
        FileOutputStream fileOutputStream = null;
        try {
            Long jobId = getId(row);
            byte[] buffer = MgbServiceFactory.getService().downloadJobFile(jobId, "dat");
            
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(buffer);
        } catch (Exception e) {
            handleRemoteException(e);
            result = false;
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                }
            }
        }
        return result;
    }

}
