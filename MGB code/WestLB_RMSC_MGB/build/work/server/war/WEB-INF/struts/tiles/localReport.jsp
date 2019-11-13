<%@page import="de.westlb.mgb.struts_client.form.*"%>
<%@page import="de.westlb.mgb.struts_client.*"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<bean:define id="reports" name="<%=RequestKeys.REPORT_LIST%>" scope="request"/>

<script type="text/javascript">

function hideShowTimeFields()
{
	var rb=document.forms["localReportRequestForm"].elements["useJobCreationDate"];
	var rbVal;
    for(i=0;i<rb.length;i++)
    {
        if(rb[i].checked) rbVal=rb[i].value;
    }
	if(rbVal&&rbVal=='<%=LocalReportRequestForm.USE_JOB_COB_DATE%>')
	{
		document.getElementById("fromTimeField").style.visibility="hidden";
		document.getElementById("toTimeField").style.visibility="hidden";
	}
	else
	{
		document.getElementById("fromTimeField").style.visibility="visible";
		document.getElementById("toTimeField").style.visibility="visible";
	}

}

</script>

<p class="title"><bean:message key="page.localReport.title"/></p>

<p><bean:message key="page.localReport.description"/></p>

<%--
<bean:define toScope="page" id="form" name="localReportRequestForm"
             type="de.westlb.mgb.struts_client.form.LocalReportRequestForm"/>
<bean:message key="page.localReport.sizeWarning" 
              arg0='<%= String.valueOf(form.getMaxResultsPerClient()) %>'/>
</p>
--%>

<html:form action="localReportSubmit.do" method="get">
	<table border="1" width="50%">
		<tbody>
			<tr>
				<td class="tablehead" align="left">
					<bean:message key="page.localReport.selectForm.label.dateFrom"/><sup style="color:#FF0000">*</sup>
				</td>
				<td class="tablevalue" align="left">
					<html:text styleClass="formvaluerequired" style="width:47%" property="fromDate"/>
					<html:text styleClass="formvaluerequired" styleId="fromTimeField" style="width:47%;visibility:hidden;" property="fromTime"/>
				</td>
			</tr>
			<tr>
				<td class="tablehead" align="left">
					<bean:message key="page.localReport.selectForm.label.dateTo"/><sup style="color:#FF0000">*</sup>
				</td>
				<td class="tablevalue" align="left">
					<html:text styleClass="formvaluerequired" style="width:47%" property="toDate"/>
					<html:text styleClass="formvaluerequired" styleId="toTimeField" style="width:47%;visibility:hidden;" property="toTime"/>
				</td>
			</tr>
			<tr>
				<td class="tablehead" align="left">
					<bean:message key="page.localReport.selectForm.label.useJobDateType"/>
				</td>
				<td class="tablevalue" align="left">
					<html:radio property="useJobCreationDate" onclick="hideShowTimeFields()" value="<%=LocalReportRequestForm.USE_JOB_COB_DATE%>" />
					<bean:message key="page.localReport.selectForm.label.useJobCobDate"/>					
					<br/>
					<html:radio property="useJobCreationDate" onclick="hideShowTimeFields()" value="<%=LocalReportRequestForm.USE_JOB_CREATION_DATE%>" />
					<bean:message key="page.localReport.selectForm.label.useJobCreationDate"/>
				</td>
			</tr>
			<tr>
				<td class="tablehead" align="left">
					<bean:message key="page.localReport.selectForm.label.reportType"/>
				</td>
				<td class="tablevalue" align="left">
					<html:radio property="reportType" value="<%=LocalReportRequestForm.REPORT_ALL%>" />
					<bean:message key="page.localReport.selectForm.label.reportType.all"/>
					<br/>
					<html:radio property="reportType" value="<%=LocalReportRequestForm.REPORT_MAN_CHECK%>" />
					<bean:message key="page.localReport.selectForm.label.reportType.manCheck"/>					
				</td>
			</tr>
			<tr>
				<td class="tablehead" align="left">
					<bean:message key="page.localReport.selectForm.label.marketData"/>
				</td>
				<td class="tablevalue" align="left">
					<html:radio property="marketData" value="<%=LocalReportRequestForm.MARKET_DATA%>" />
					<bean:message key="page.localReport.selectForm.label.marketData.show"/>
					<br/>
					<html:radio property="marketData" value="<%=LocalReportRequestForm.NO_MARKET_DATA%>" />
					<bean:message key="page.localReport.selectForm.label.marketData.hide"/>					
				</td>
			</tr>

			<c:if test="${!empty reports}">
				<tr>
					<td class="tablehead" align="left">
						<bean:message key="page.localReport.selectForm.label.reportLocation"/>
					</td>
					<td class="tablevalue" align="left">
						<c:forEach var="report" items="${reports}">
							<html-el:radio property="reportLocation" value="${report}" />
							<c:out value="${report}"/>
							<br/>
						</c:forEach>
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td colspan="2" align="center">
					<html:submit property="Submit">
						<bean:message key="button.submit"/>
					</html:submit>
					<html:cancel>
						<bean:message key="button.cancel"/>
					</html:cancel>
				</td>
			</tr>
		</tbody>
	</table>
</html:form>

<p class="text"><sup style="color:#FF0000">*</sup> = required field</p>
