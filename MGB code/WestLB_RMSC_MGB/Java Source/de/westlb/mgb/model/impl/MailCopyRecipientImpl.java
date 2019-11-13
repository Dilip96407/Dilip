package de.westlb.mgb.model.impl;


public class MailCopyRecipientImpl extends MailRecipientImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7212779244603176774L;

	@Override
    public boolean isCopyRecipient() {
		return true;
	}
}
