package de.westlb.mgb.model.impl;
/**
 * 
 * @modelguid {38784A7E-7CA3-4B63-816C-FE2A25D518C3}
 */
public class ReclStateHistEntryImpl extends TradeHistEntryImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -649351091732806424L;

	private ReclamationStateImpl reclamationState;
		
	/** @modelguid {F34C74F7-9D8D-4858-8EFF-895D3F8E045A} */
	private int level;
	
	/** @modelguid {4290BEB8-53D4-48A3-BD5D-00C8047D19DC} */
	private boolean isClosed;

    public static final int CLOSED_COMMENT_MAX_LENGTH = 255;
	/** @modelguid {42F9DFFB-A4DA-47BE-BC65-0054C33A764B} */
	private String closedComment;
	
	/** @modelguid {A35D75A3-D4B8-496D-AD56-F1876CF2C86A} */
	private ReportImageImpl reportImage;

	/**
	 * @see de.westlb.mgb.model.impl.TradeHistEntryImpl#getTradeState()
	 */
	@Override
    public StateImpl getState() {
		return getReclamationState();
	}

	/**
	 * Returns the level.
	 * @return int
	 * @modelguid {D65D1F99-26E3-4C9F-A723-90619F4E9D78}
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Returns the reportImage.
	 * @return ReportImageImpl
	 * @modelguid {A94AB396-25C3-4866-8887-1AAE83F607CE}
	 */
	public ReportImageImpl getReportImage() {
		return reportImage;
	}

	/**
	 * Sets the level.
	 * @param level The level to set
	 * @modelguid {C86BCF6D-7F27-4924-8DD3-8C60294DCD58}
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Sets the reportImage.
	 * @param reportImage The reportImage to set
	 * @modelguid {01FFF459-2D6D-471B-9AE6-ABFAC0802DA6}
	 */
	public void setReportImage(ReportImageImpl reportImage) {
		this.reportImage = reportImage;
	}


	/**
	 * @see de.westlb.mgb.model.impl.TradeHistEntryImpl#getStateType()
	 * @modelguid {E7019AEE-E7A3-4A98-AF0B-5E693300981D}
	 */
	@Override
    public String getStateType() {
		return RECLAMATION;
	}

	/**
	 * Returns the closedComment.
	 * @return String
	 * @modelguid {7C6FE594-A7FE-4957-8A87-7AD19DDC9914}
	 */
	public String getClosedComment() {
		return closedComment;
	}

	/**
	 * Returns the isClosed.
	 * @return boolean
	 * @modelguid {247F5B3E-D7D6-403C-903A-B5BF0A0AD3A5}
	 */
	public boolean isClosed() {
		return isClosed;
	}

	/** @modelguid {7E42169D-012F-45A0-8A84-99B9A569DB97} */
	@SuppressWarnings("unused")
    private boolean getIsClosed() {
		return isClosed;
	}

	/**
	 * Sets the closedComment.
	 * @param closedComment The closedComment to set
	 * @modelguid {BDBAC172-25F0-42BA-9F02-B7DB778911D2}
	 */
	public void setClosedComment(String closedComment) {
		this.closedComment = closedComment;
	}

	/**
	 * Sets the isClosed.
	 * @param isClosed The isClosed to set
	 * @modelguid {FD5192C8-0B49-4CC6-B37F-74006D86A595}
	 */
	public void setIsClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	/**
	 * Returns the state.
	 * @return ReclamationStateImpl
	 */
	public ReclamationStateImpl getReclamationState() {
		return reclamationState;
	}

	/**
	 * Sets the state.
	 * @param state The state to set
	 */
	public void setReclamationState(ReclamationStateImpl state) {
		this.reclamationState = state;
	}

}

