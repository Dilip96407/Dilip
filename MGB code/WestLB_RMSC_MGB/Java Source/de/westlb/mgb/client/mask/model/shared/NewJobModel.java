package de.westlb.mgb.client.mask.model.shared;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import de.westlb.mgb.client.server.MgbServiceFactory;
import de.westlb.mgb.client.ui.selection_list.AbstractSelectionList;
import de.westlb.mgb.client.ui.selection_list.AllSourceSystemList;
import de.westlb.mgb.client.ui.selection_list.SourceSystemList;
import de.westlb_systems.xaf.util.table.swing.TablePoolComboBoxModel;

/**
 * GUI-Model for NewManualStateView
 *
 * @author Manfred Boerner
 */

public class NewJobModel extends AbstractModel 
{
	private SourceSystemList sourceSystemList = new SourceSystemList();
	private AllSourceSystemList allSourceSystemList = new AllSourceSystemList();
	
	private String RESOURCE_BASE = getResourceBase();
	
	private String fixedSourceSystem;
	
	public static final String 
		P_SOURCE_SYSTEM_CB_MODEL	= "sourceSystemComboBoxModel",
		P_FILE_PATH					= "filePath"
	;
	
	
	private final String[] propertyNames = new String[] {
		P_SOURCE_SYSTEM_CB_MODEL,
		P_FILE_PATH,
	};
	

	/**
	 * Default Konstruktor erstellt ein leeres Model
	 *
	 */
	public NewJobModel() {
		setPropertyNames(propertyNames);
	}
	
	public boolean isSourceSystemFixed() {
		return fixedSourceSystem != null;
	}

	/**
	 * Fill the model from the business model.
	 *
	 */
	private void fillModel() {
		// do special conversions
		TablePoolComboBoxModel comboBoxModel = null;
		if (fixedSourceSystem != null) {
			comboBoxModel = new TablePoolComboBoxModel(allSourceSystemList);
			comboBoxModel.setSelectedItem(AllSourceSystemList.getItemFor(allSourceSystemList, fixedSourceSystem));
		} else {
			// do special conversions
			comboBoxModel = new TablePoolComboBoxModel(sourceSystemList);
			if (comboBoxModel.getSize() > 0) {
				comboBoxModel.setSelectedItem(comboBoxModel.getElementAt(0));
			}
		}
		
		setProperty(P_SOURCE_SYSTEM_CB_MODEL, comboBoxModel);
			
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

		// Properies auslesen
		String sourceSystemCode = (String)AbstractSelectionList.getKeyFrom(getProperty(P_SOURCE_SYSTEM_CB_MODEL));
		String filePath = (String)getProperty(P_FILE_PATH);
		
		// Check input
		if (filePath == null || sourceSystemCode == null) {
			setError(PROPERTY_ERROR);
			logMessage(LOG_ERROR, getResourceString(RESOURCE_BASE + "E_002"));
			return false;
		}
		
        FileInputStream inputStream = null;
		try {
			byte[] fileData = null;
			File file = new File(filePath);
			inputStream = new FileInputStream(file);
			fileData = new byte[(int) file.length()];
			inputStream.read(fileData);
			logMessage(LOG_INFO,"Will create new job for source system "+sourceSystemCode);
       		MgbServiceFactory.getService().createNewJob(sourceSystemCode, fileData);
        } catch (Exception e) {
        	setError(APPLICATION_ERROR);
        	setErrorDetails(e);
        	return false;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException io){
                }
            }
} 
		return true;
	}

	/**
	 * Sets the business object from which the model gets its data.
	 * It exepts to get an internal trade id.
	 */
	@Override
    public void setBusinessObject(Object newBusinessObject) {
		super.setBusinessObject(newBusinessObject);
		if (newBusinessObject instanceof String) {
			fixedSourceSystem = (String)newBusinessObject;
		}
		fillModel();
	}
}
