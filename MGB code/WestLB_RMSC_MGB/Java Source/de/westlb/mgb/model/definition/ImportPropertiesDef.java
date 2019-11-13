/*
 * Created on Dec 16, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.model.definition;

/**
 * @author WSY4148
 *
 */
public interface ImportPropertiesDef {
	public static final String PARAM_IMPORT_PROPERTIES = "importProperties";
	public static final String HIBERNATE_PROPERTIES = "mgb.hibernate.properties";
	
	// the following the strings need an appropriate prefix of a source system
	public static final String IMPORT_MAX_CONVERT_ERRORS = ".import.maxconverterrors";
	public static final String IMPORT_MAX_CHECK_DIFF = ".import.maxcheckdiff";
	public static final String IMPORT_MAX_LOAD_ERRORS = ".import.maxloaderrors";
	
	public static final String IMPORT_SYSTEMS = "mgb.import.systems";
	public static final String IMPORT_SQL_LOADER_PATH = "sqlldr.pathname";
	public static final String IMPORT_WORK_DIR = "dir.work";
	public static final String IMPORT_IMPORT_DIR = "dir.import";
	public static final String IMPORT_ARCHIVE_DIR = "dir.archive";
	public static final String IMPORT_CTL_DIR = "dir.ctl";
    public static final String IMPORT_FILE_NAMES = "filename";
    public static final String IMPORT_LAST_LINE_DATE_FORMAT = "lastlinedateformat";
    public static final String IMPORT_SOURCE_SYSTEM_COB_DEPENDS = "cobdepends";
    public static final String IMPORT_FILE_NAME_EXT = "mgb.import.filename.ext";
    public static final String IMPORT_FILE_NAME_MAXNUMBER = "filename.maxfiles";


	public static final String HIBERNATE_USER = "hibernate.connection.username";
	public static final String HIBERNATE_PASSWORD = "hibernate.connection.password";
	public static final String HIBERNATE_URL = "hibernate.connection.url";
}
