<%@page import="de.westlb.mgb.struts_client.*"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<bean:define id="user" name="<%=RequestKeys.EMPLOYEE_VO%>" scope="request"/>
<bean:define id="reports" name="<%=RequestKeys.REPORT_LIST%>" scope="request"/>

<table>
<tr><td>

<p class="title">User Maintenance</p>
<p class="text">Please fill out all required yellow fields, check the appropriate roles and 'Submit' the data.</p>

<html:form action="userDetailSubmit">
<html:hidden name="user" property="employeeId"></html:hidden>
<html:hidden name="user" property="mandantCode"></html:hidden>
<table border="1" width="100%">
	<tbody>
		<tr>
			<td class="tablehead" align="left"><bean:message key="page.userDetail.label.firstName"/><sup style="color:#FF0000">*</sup></td>
			<td class="tablevalue" style="width:50%" align="left">
				<html:text styleClass="formvaluerequired" name="user" property="firstName"></html:text>
			</td>
		</tr>
		<tr>
			<td class="tablehead" align="left"><bean:message key="page.userDetail.label.lastName"/><sup style="color:#FF0000">*</sup></td>
			<td class="tablevalue" align="left">
				<html:text styleClass="formvaluerequired" name="user" property="lastName"></html:text>
			</td>
		</tr>
		<tr>
			<td class="tablehead" align="left"><bean:message key="page.userDetail.label.mandantCode"/></td>
			<td class="tablevalue" align="left">
				<bean:write name="user" property="mandantCode"/>
			</td>
		</tr>
		<tr>
			<td class="tablehead" align="left"><bean:message key="page.userDetail.label.phone"/></td>
			<td class="tablevalue" align="left">
				<html:text styleClass="formvalue" name="user" property="phone"></html:text>
			</td>
		</tr>
		<tr>
			<td class="tablehead" align="left"><bean:message key="page.userDetail.label.email"/><sup style="color:#FF0000">*</sup></td>
			<td class="tablevalue" align="left">
				<html:text styleClass="formvaluerequired" name="user" property="email"></html:text>
			</td>
		</tr>
		<tr>
			<td class="tablehead" align="left"><bean:message key="page.userDetail.label.ntId"/><sup style="color:#FF0000">*</sup></td>
			<td class="tablevalue" align="left">
				<html:text styleClass="formvaluerequired" name="user" property="ntId"></html:text>
			</td>
		</tr>
		<tr>
			<td class="tablehead" align="left"><bean:message key="page.userDetail.label.trader"/></td>
			<td class="tablevalue" align="left">
				<html:checkbox name="user" property="trader"></html:checkbox>
			</td>
		</tr>
		<tr>
			<td class="tablehead" align="left"><bean:message key="page.userDetail.label.controller"/></td>
			<td class="tablevalue" align="left">
				<html:checkbox name="user" property="controller"></html:checkbox>
			</td>
		</tr>
		<tr>
			<td class="tablehead" align="left"><bean:message key="page.userDetail.label.admin"/></td>
			<td class="tablevalue" align="left">
				<html:checkbox name="user" property="admin"></html:checkbox>
			</td>
		</tr>
		<tr>
			<td class="tablehead" align="left"><bean:message key="page.userDetail.label.systemAdmin"/></td>
			<td class="tablevalue" align="left">
				<html:checkbox name="user" property="systemAdmin"></html:checkbox>
			</td>
		</tr>
		<tr>
			<td class="tablehead" align="left"><bean:message key="page.userDetail.label.sparkassenAdmin"/></td>
			<td class="tablevalue" align="left">
				<html:checkbox name="user" property="sparkassenAdmin"></html:checkbox>
			</td>
		</tr>
		<tr>
			<td class="tablehead" align="left"><bean:message key="page.userDetail.label.userMaintainAdmin"/></td>
			<td class="tablevalue" align="left">
				<html:checkbox name="user" property="userMaintainAdmin"></html:checkbox>
			</td>
		</tr>
		<tr>
			<td class="tablehead" align="left"><bean:message key="page.userDetail.label.reporter"/></td>
			<td class="tablevalue" align="left">
				<html:checkbox name="user" property="reporter"></html:checkbox>
			</td>
		</tr>
		<tr>
			<td class="tablehead" align="left"><bean:message key="page.userDetail.label.report"/></td>
			<td class="tablevalue" align="left">
				<html:select styleClass="formvalue" name="user" property="report">
					<html:options name="reports"></html:options>
				</html:select>
			</td>
		</tr>
		<tr>
			<td class="tablehead" align="left"><bean:message key="page.userDetail.label.readOnly"/></td>
			<td class="tablevalue" align="left">
				<html:checkbox name="user" property="readOnly"></html:checkbox>
			</td>
		</tr>

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

</td></tr>
</table>

<p class="text"><sup style="color:#FF0000">*</sup> = required field</p>