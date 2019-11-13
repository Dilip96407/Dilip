/*
 * Created on Apr 13, 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.client.task;

/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface MgbTaskDef {

	public static final String MANDANT_CODE_LIST = "MANDANT_CODE";
	public static final String CLIENT_NAME = "CLIENT_NAME";
	public static final String TASK_ID_LIST = "TASK_ID";
	public static final String USER_ID = "USER_ID";
	public static final String URL = "URL";
	
	public static final String STATE_PENDING = "PENDING";
	public static final String STATE_RUNNING = "RUNNING";
	public static final String STATE_CANCELLED = "CANCELLED";
	public static final String STATE_STOP = "STOP";
	public static final String STATE_STOPPED = "STOPPED";
	public static final String STATE_STOPPED_OK = "OK";
	public static final String STATE_STOPPED_ERROR = "ERROR";

    public static final String CRON_EVERY_5_MIN = "0 0/5 * * * ?";
    public static final String CRON_EVERY_5_MIN_NOT_WEEKEND = "0 0/5 * ? * MON-FRI";

}
