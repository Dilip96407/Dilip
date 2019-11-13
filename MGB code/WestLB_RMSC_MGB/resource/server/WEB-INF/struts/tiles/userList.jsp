<%@page import="de.westlb.mgb.struts_client.*"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<% String root=((javax.servlet.http.HttpServletRequest)pageContext.getRequest()).getContextPath(); %>

<table>
<tr><td>

<bean:define id="users" name="<%=RequestKeys.EMPLOYEE_LIST%>" scope="request"/>
<bean:define id="mandant" name="<%=RequestKeys.MANDANT_CODE%>" scope="request"/>
<p class="title">User Maintenance</p>
<p class="text">The list contains all user of the selected client <bean:write name="mandant"/>.
<br/>
Either <a class="link" href='<%=root%>/userDetail.do?mandant=<bean:write name="mandant"/>'>add a new user</a>
or click on the <img src="<%=root%>/images/Edit16.gif" alt="edit"/> icon to edit the user data as necessary.
</p>
<table border="1" width="100%">
	<tbody>
		<tr>
			<td class="tablehead" align="left">&nbsp;</td>
			<td class="tablehead" align="left"><bean:message key="page.userList.label.firstName"/></td>
			<td class="tablehead" align="left"><bean:message key="page.userList.label.lastName"/></td>
			<td class="tablehead" align="left"><bean:message key="page.userList.label.mandantCode"/></td>
			<td class="tablehead" align="left"><bean:message key="page.userList.label.phone"/></td>
			<td class="tablehead" align="left"><bean:message key="page.userList.label.email"/></td>
			<td class="tablehead" align="left"><bean:message key="page.userList.label.ntId"/></td>
			<td class="tablehead" align="left"><bean:message key="page.userList.label.trader"/></td>
			<td class="tablehead" align="left"><bean:message key="page.userList.label.controller"/></td>
			<td class="tablehead" align="left"><bean:message key="page.userList.label.admin"/></td>
			<td class="tablehead" align="left"><bean:message key="page.userList.label.systemAdmin"/></td>
			<td class="tablehead" align="left"><bean:message key="page.userList.label.sparkassenAdmin"/></td>
			<td class="tablehead" align="left"><bean:message key="page.userList.label.userMaintainAdmin"/></td>
			<td class="tablehead" align="left"><bean:message key="page.userList.label.reporter"/></td>
			<td class="tablehead" align="left"><bean:message key="page.userList.label.readOnly"/></td>
			<td class="tablehead" align="left"><bean:message key="page.userList.label.report"/></td>
		</tr>
		<logic:iterate id="user" name="users">
			<tr>
				<td class="tablevalue" align="left">
					<a href='<%=root%>/userDetail.do?mandant=<bean:write name="mandant"/>&amp;userId=<bean:write name="user" property="employeeId"/>'> 
						<img src="<%=root%>/images/Edit16.gif" alt="edit"/>
					</a>
				</td>				
				<td class="tablevalue" align="left">
					<bean:write name="user" property="firstName"/>
				</td>
				<td class="tablevalue" align="left">
					<bean:write name="user" property="lastName"/>
				</td>
				<td class="tablevalue" align="left">
					<bean:write name="user" property="mandantCode"/>
				</td>
				<td class="tablevalue" align="left">
					<logic:notEmpty name="user" property="phone">
						<bean:write name="user" property="phone"/>
					</logic:notEmpty>
					<logic:empty name="user" property="phone">
						&nbsp;
					</logic:empty>
				</td>
				<td class="tablevalue" align="left">
					<bean:write name="user" property="email"/>
				</td>
				<td class="tablevalue" align="left">
					<bean:write name="user" property="ntId"/>
				</td>
				<td class="tablevalue" align="center">
					<logic:equal name="user" property="trader" value="true">
						x
					</logic:equal>	
					<logic:notEqual name="user" property="trader" value="true">
						&nbsp;
					</logic:notEqual>	
				</td>
				<td class="tablevalue" align="center">
					<logic:equal name="user" property="controller" value="true">
						x
					</logic:equal>	
					<logic:notEqual name="user" property="controller" value="true">
						&nbsp;
					</logic:notEqual>	
				</td>
				<td class="tablevalue" align="center">
					<logic:equal name="user" property="admin" value="true">
						x
					</logic:equal>	
					<logic:notEqual name="user" property="admin" value="true">
						&nbsp;
					</logic:notEqual>	
				</td>
				<td class="tablevalue" align="center">
					<logic:equal name="user" property="systemAdmin" value="true">
						x
					</logic:equal>	
					<logic:notEqual name="user" property="systemAdmin" value="true">
						&nbsp;
					</logic:notEqual>	
				</td>
				<td class="tablevalue" align="center">
					<logic:equal name="user" property="sparkassenAdmin" value="true">
						x
					</logic:equal>	
					<logic:notEqual name="user" property="sparkassenAdmin" value="true">
						&nbsp;
					</logic:notEqual>	
				</td>
				<td class="tablevalue" align="center">
					<logic:equal name="user" property="userMaintainAdmin" value="true">
						x
					</logic:equal>	
					<logic:notEqual name="user" property="userMaintainAdmin" value="true">
						&nbsp;
					</logic:notEqual>	
				</td>
				<td class="tablevalue" align="center">
					<logic:equal name="user" property="reporter" value="true">
						x
					</logic:equal>	
					<logic:notEqual name="user" property="reporter" value="true">
						&nbsp;
					</logic:notEqual>	
				</td>
				<td class="tablevalue" align="center">
					<logic:equal name="user" property="readOnly" value="true">
						x
					</logic:equal>	
					<logic:notEqual name="user" property="readOnly" value="true">
						&nbsp;
					</logic:notEqual>	
				</td>
				<td class="tablevalue" align="left">
					<logic:notEmpty name="user" property="report">
						<bean:write name="user" property="report"/>
					</logic:notEmpty>
					<logic:empty name="user" property="report">
						&nbsp;
					</logic:empty>
				</td>
			</tr>
		</logic:iterate>
	</tbody>
</table>

</td></tr>
</table>
