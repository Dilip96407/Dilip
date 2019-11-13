package de.westlb.mgb.client.mask.view.shared;

import org.apache.log4j.Logger;

public class ProgressDialogDummyImpl implements ProgressDialog {

    private static Logger logger = Logger.getLogger(ProgressDialogDummyImpl.class);

	boolean isCanceled = false;
	
	@Override
    public void close() {
	}

	@Override
    public boolean isCanceled() {
		return isCanceled;
	}

	@Override
    public void setCancelable(boolean value) {
		isCanceled = value;
	}

	@Override
    public void setDetails(String text1, String text2) {
		logger.info(text1 + " " + text2);
	}

	@Override
    public void setDetails(String text) {
		logger.debug(text);
	}

	@Override
    public void setInfo(String text) {
		logger.info(text);
	}

	@Override
    public void setMyTitle(String text) {
		logger.info(text);
	}

	@Override
    public void show() {
	}

	@Override
    public void setProgress(long current, long maximum) {
		if (maximum != 0) {
			logger.debug(current/maximum*100 + "% done");
		}
	}
}
