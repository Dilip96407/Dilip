<%@page import="de.westlb.mgb.struts_client.*"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<% String root=((javax.servlet.http.HttpServletRequest)pageContext.getRequest()).getContextPath(); %>

<table>
<tr><td>

<bean:define id="jobs" name="<%=RequestKeys.OPEN_JOBS_LIST%>" scope="request"/>
<p class="title">Open Jobs Maintenance</p>
<p class="text">The list shows the status of all jobs that have been imported within the past 14 days.
Click on a COB to get a report for that date.</p>
<table border="1" width="100%">
	<tbody>
		<tr>
			<td class="tablehead" align="left"><bean:message key="page.openJobsList.label.businessDay"/></td>
			<td class="tablehead" align="left"><bean:message key="page.openJobsList.label.jobId"/></td>
			<td class="tablehead" align="left"><bean:message key="page.openJobsList.label.mandant"/></td>
			<td class="tablehead" align="left"><bean:message key="page.openJobsList.label.sourceSystem"/></td>
			<td class="tablehead" align="left"><bean:message key="page.openJobsList.label.stopConvertTime"/></td>
            <td class="tablehead" align="left"><bean:message key="page.openJobsList.label.status"/></td>
            <td class="tablehead" align="left"><bean:message key="page.openJobsList.label.numOpenRecords"/></td>
			<td class="tablehead" align="left"><bean:message key="page.openJobsList.label.action"/></td>
		</tr>
		<logic:iterate id="job" name="jobs">
			<tr>
				<td class="tablevalue" align="left">
				    <logic:present name="job" property="cob">
                        <a style="text-decoration:underline;"
                           href='<%=root%>/localReportSubmit.do?fromDate=<bean:write 
                               format="dd.MM.yyyy" name="job" property="cob.time"
                           />&toDate=<bean:write 
                               format="dd.MM.yyyy" name="job" property="cob.time"
                           />&reportType=REPORT_ALL&marketData=MARKET_DATA'>
                          <bean:write format="yyyy-MM-dd" name="job" property="cob.time"/>
                        </a>
				    </logic:present>
				</td>
				<td class="tablevalue" align="left">
					<bean:write name="job" property="jobId"/>
				</td>
				<td class="tablevalue" align="left">
                        <a style="text-decoration:underline;"
                           href='<%=root%>/startApplet.do?mandant=<bean:write name="job" property="mandantCode"/>&title=MGB_UAT' target="MGB_UAT">
                           <bean:write name="job" property="mandantName"/>
                        </a>
				</td>
				<td class="tablevalue" align="left">
					<bean:write name="job" property="sourceSystemName"/>
				</td>
                <td class="tablevalue" align="left">
                    <bean:write name="job" property="stopLoadTime.time"/>
                </td>
                <td class="tablevalue" align="left">
                    <bean:write name="job" property="status"/>
                </td>
                <td class="tablevalue" align="left">
                    <bean:write name="job" property="numberOfOpenRecords"/>
                </td>
                <td class="tablevalue" align="left">
                    <logic:equal name="job" property="numberOfOpenRecords" value="0">
                        <logic:match name="job" property="status" value="OK">
                             <a style="text-decoration:underline;"
                               href='<%=root%>/openJobClose.do?action=close&jobId=<bean:write name="job" property="jobId"/>&mandant=<bean:write name="job" property="mandantCode"/>'>
                              close
                            </a>
                        </logic:match>
                        <logic:match name="job" property="status" value="ERROR">
                             <a style="text-decoration:underline;"
                               href='<%=root%>/openJobClose.do?action=ignore&jobId=<bean:write name="job" property="jobId"/>&mandant=<bean:write name="job" property="mandantCode"/>'>
                              ignore
                            </a>
                        </logic:match>
                    </logic:equal>
                    <logic:notEqual name="job" property="numberOfOpenRecords" value="0">
                        <a style="text-decoration:underline;"
                           href='<%=root%>/startApplet.do?mandant=<bean:write name="job" property="mandantCode"/>&title=MGB_UAT' target="MGB_UAT">
                           edit
                        </a>
                    </logic:notEqual>
                </td>
			</tr>
		</logic:iterate>
	</tbody>
</table>

</td></tr>
</table>
