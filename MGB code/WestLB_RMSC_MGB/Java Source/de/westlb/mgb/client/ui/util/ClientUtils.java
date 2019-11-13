package de.westlb.mgb.client.ui.util;

import de.westlb.mgb.client.server.vo.JobVo;
import de.westlb.mgb.client.ui.selection_list.SourceSystemList;


public class ClientUtils {

	/**
	 * Constructs a display name from the attributes of the JobVo-object
	 */
	public static String getNameForJob(JobVo jobVo) {
	    DateFormat timeFormat = new DateFormat(DateFormat.TIME_FORMAT);
	    DateFormat dateFormat = new DateFormat(DateFormat.DATE_FORMAT);
	    SourceSystemList sourceSystemList = new SourceSystemList();
	
	    StringBuffer strBuf = new StringBuffer();
	    strBuf.append(jobVo.getJobId());
	    strBuf.append(" ");
	    strBuf.append(sourceSystemList.get(jobVo.getSourceSystemCode()));
	    strBuf.append(" ");
	    if (jobVo.getStopBusinessTime() != null) {
	        strBuf.append(dateFormat.format(jobVo.getStopBusinessTime()));
	    } else if (jobVo.getSystemTime() != null) {
	        strBuf.append(timeFormat.format(jobVo.getSystemTime()) + " (ExtractTime)");
	    } else if (jobVo.getStartLoadTime() != null) {
	        strBuf.append(timeFormat.format(jobVo.getStartLoadTime()) + " (LoadTime)");
	    } else if (jobVo.getStartConvertTime() != null) {
	        strBuf.append(timeFormat.format(jobVo.getStartConvertTime()) + " (ConvertTime)");
	    }
	    return strBuf.toString();
	}
}
