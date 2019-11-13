package de.westlb.mgb.model.impl;

import java.util.Calendar;

/**
 * 
 * @modelguid {F91A1C26-C18A-4AF7-8B02-7DD1AD4A6B30}
 */
public class MgbTaskImpl extends EntityImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4402594301011344620L;

	private String taskName;

	private String threadName;

	private String user;

	private Calendar startTime;

	private Calendar stopTime;

    public static final int MESSAGE_MAX_LENGTH = 255;
	private String message;

	private String state;

	private String client;

	@Override
    public Long getLongId() {
		return super.getLongId();
	}

	/**
	 * @return Returns the client.
	 */
	public String getClient() {
		return client;
	}

	/**
	 * @param client
	 *            The client to set.
	 */
	public void setClient(String client) {
		this.client = client;
	}

	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return Returns the startTime.
	 */
	public Calendar getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            The startTime to set.
	 */
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return Returns the stopTime.
	 */
	public Calendar getStopTime() {
		return stopTime;
	}

	/**
	 * @param stopTime
	 *            The stopTime to set.
	 */
	public void setStopTime(Calendar stopTime) {
		this.stopTime = stopTime;
	}

	/**
	 * @return Returns the taskName.
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName
	 *            The taskName to set.
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return Returns the user.
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            The user to set.
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return Returns the threadName.
	 */
	public String getThreadName() {
		return threadName;
	}

	/**
	 * @param threadName
	 *            The threadName to set.
	 */
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
}

