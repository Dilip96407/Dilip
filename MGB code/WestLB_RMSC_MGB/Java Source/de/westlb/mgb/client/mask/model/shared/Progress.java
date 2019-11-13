package de.westlb.mgb.client.mask.model.shared;

/**
 * @author wsy4148
 *
 * Interface to implement by a prograss bar control
 */

public interface Progress {
	public boolean isCanceled();
	public void setProgress(long current, long maximum);
}
