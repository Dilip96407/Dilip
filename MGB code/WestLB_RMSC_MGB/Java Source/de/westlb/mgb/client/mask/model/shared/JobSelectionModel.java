package de.westlb.mgb.client.mask.model.shared;

import de.westlb.mgb.client.server.vo.JobVo;

/**
 * @author wsy4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface JobSelectionModel {
    public JobVo[] getSelectedJobs();
    public JobVo[] getAvailableJobs();
    public void setSelectedJobs(JobVo[] jobs);
    public boolean saveModel();
}
