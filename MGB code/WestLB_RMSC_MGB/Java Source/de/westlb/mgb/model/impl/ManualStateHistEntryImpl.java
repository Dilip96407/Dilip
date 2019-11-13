package de.westlb.mgb.model.impl;
/**
 * 
 * @modelguid {611D309E-3481-4526-A476-5189A3A0B458}
 */
public class ManualStateHistEntryImpl extends TradeHistEntryImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6463825328321902430L;

	private AbstractManualState manualState;
	
	/** @modelguid {A968DBC4-3A03-4D3D-9EB2-7C3659FA3C4C} */
	private ReportImageImpl reportImage;

	/**
	 * @see de.westlb.mgb.model.impl.TradeHistEntryImpl#getTradeState()
	 */
	@Override
    public StateImpl getState() {
		return getManualState();
	}

	/**
	 * @see de.westlb.mgb.model.impl.TradeHistEntryImpl#getStateType()
	 * @modelguid {6C96A706-1FE2-42D1-8B9A-78889EBFA26B}
	 */
	@Override
    public String getStateType() {
		return MANUAL;
	}

	/**
	 * Returns the reportImage.
	 * @return ReportImageImpl
	 * @modelguid {F58E5EC4-A9F1-4489-B7F7-3BED222B562D}
	 */
	public ReportImageImpl getReportImage() {
		return reportImage;
	}

	/**
	 * Sets the reportImage.
	 * @param reportImage The reportImage to set
	 * @modelguid {50CE5473-E92B-4708-9AB2-4DEC375D187D}
	 */
	public void setReportImage(ReportImageImpl reportImage) {
		this.reportImage = reportImage;
	}

	/**
	 * Returns the state.
	 * @return ManualStateImpl
	 */
	public AbstractManualState getManualState() {
		return manualState;
	}

	/**
	 * Sets the state.
	 * @param state The state to set
	 */
	public void setManualState(AbstractManualState state) {
		this.manualState = state;
	}

}

