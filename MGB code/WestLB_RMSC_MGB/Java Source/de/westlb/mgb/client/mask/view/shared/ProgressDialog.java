/*
 * Created on Apr 5, 2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package de.westlb.mgb.client.mask.view.shared;

import de.westlb.mgb.client.mask.model.shared.Progress;

/**
 * @author d055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface ProgressDialog extends Progress {

	public void close();

	@Override
    public boolean isCanceled();

	public void setCancelable(boolean value);

	public void setDetails(String text);

	public void setDetails(String text1, String text2);

	public void setInfo(String text);

	public void setMyTitle(String text);

    public void show();

}