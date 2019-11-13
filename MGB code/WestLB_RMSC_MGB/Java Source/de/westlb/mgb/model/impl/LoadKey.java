package de.westlb.mgb.model.impl;

import java.io.Serializable;

public class LoadKey implements Serializable {

	private Long rowNum;
	private String sourceSystem;

	public LoadKey() {}

	public LoadKey(Long rowId, String srcSystemCode) {
		this.rowNum = rowId;
		this.sourceSystem = srcSystemCode;
	}

	public Long getRowNum() {
		return rowNum;
	}

	public void setRowNum(Long rowId) {
		this.rowNum = rowId;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String srcSystemCode) {
		this.sourceSystem = srcSystemCode;
	}

	@Override
	public boolean equals(Object arg0) {
		if(arg0 == null) return false;
		if(!(arg0 instanceof LoadKey)) return false;
		LoadKey arg1 = (LoadKey) arg0;
		return (this.rowNum.equals(arg1.getRowNum())) &&
				(this.sourceSystem.equals(arg1.getSourceSystem()));
	}

	@Override
	public int hashCode() {
		int hsCode;
		hsCode = rowNum.hashCode();
		hsCode = 19 * hsCode+ sourceSystem.hashCode();
		return hsCode;
	}

}
