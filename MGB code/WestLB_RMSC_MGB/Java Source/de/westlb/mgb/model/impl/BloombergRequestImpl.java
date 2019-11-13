package de.westlb.mgb.model.impl;
/**
 * 
 * @modelguid {67C7142C-E668-4A77-85E2-D81C0637DECF}
 */
public class BloombergRequestImpl extends RequestImpl {

    private static final long serialVersionUID = -2137358869344860669L;

    private String requestField;
	private String requestSources;

    @Override
    public String getMarketDataSource() {
        return BLOOMBERG;
};

	/**
	 * Returns the requestField.
	 * @return String
	 */
	public String getRequestField() {
		return requestField;
	}

	/**
	 * Sets the requestField.
	 * @param requestField The requestField to set
	 */
	public void setRequestField(String requestField) {
		this.requestField = requestField;
	}

	/**
	 * @return Returns the requestSources.
	 */
	public String getRequestSources() {
		return requestSources;
	}
	/**
	 * @param requestSources The requestSources to set.
	 */
	public void setRequestSources(String requestSources) {
		this.requestSources = requestSources;
	}
	
	public String[] getRequestSourceArray() {
		return requestSources == null ? null : requestSources.split(",");
	}

	public void setRequestSourceArray(String[] requestSourceArray) {
		if ( requestSourceArray.length > 0) {
			this.requestSources = requestSourceArray[0]; 
		}
		for (int i = 1; i < requestSourceArray.length; i++) {
			this.requestSources = this.requestSources + "," + requestSourceArray[i]; 
		}
	}

}

