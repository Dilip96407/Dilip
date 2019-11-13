package de.westlb.mgb.model.impl;

import java.io.Serializable;
import java.util.Calendar;

import de.westlb.mgb.model.Entity;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public abstract class DataLoadImpl implements Entity {
	
	private LoadKey loadKey;

	private Long rowNum;
  	private String sourceSystem;
    
	private String loadStatus;

	private Calendar loadTime;

	public static final int LOAD_MESSAGE_MAX_LENGTH = 255;

	private String loadMessage;

    /**
	 * @return the loadKey
	 */
	public LoadKey getLoadKey() {
		return loadKey;
	}

	/**
	 * @param loadKey the loadKey to set
	 */
	public void setLoadKey(LoadKey loadKey) {
		this.loadKey = loadKey;
	}

	@Override
    public Serializable getId() {
        return loadKey;
    }

    @Override
    public void setNullId() {
        loadKey = null;
    }

    /**
	 * @return the rownum
	 */
	public Long getRowNum() {
		return rowNum;
	}

	/**
	 * @param rownum the rownum to set
	 */
	public void setRowNum(Long rownum) {
		this.rowNum = rownum;
	}

	/**
	 * @return the sourceSystem
	 */
	public String getSourceSystem() {
		return sourceSystem;
	}

	/**
	 * @param sourceSystem the sourceSystem to set
	 */
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	/**
	 * Returns the loadStatus.
	 * @return String
	 */
	public String getLoadStatus() {
		return loadStatus;
	}

	/**
	 * Returns the loadTime.
	 * @return Date
	 */
	public Calendar getLoadTime() {
		return loadTime;
	}

	/**
	 * Sets the loadStatus.
	 * @param loadStatus The loadStatus to set
	 */
	public void setLoadStatus(String loadStatus) {
		this.loadStatus = loadStatus;
	}

	/**
	 * Sets the loadTime.
	 * @param loadTime The loadTime to set
	 */
	public void setLoadTime(Calendar loadTime) {
		this.loadTime = loadTime;
	}

	/**
	 * Returns the loadMessage.
	 * @return String
	 */
	public String getLoadMessage() {
		return loadMessage;
	}

	/**
	 * Sets the loadMessage.
	 * @param loadMessage The loadMessage to set
	 */
	public void setLoadMessage(String loadMessage) {
		this.loadMessage = loadMessage;
	}

}
