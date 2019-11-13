<%@page import="de.westlb.mgb.struts_client.*"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<% String root=((javax.servlet.http.HttpServletRequest)pageContext.getRequest()).getContextPath(); %>

<table>
<tr><td>

<bean:define id="mandants" name="<%=RequestKeys.MANDANT_LIST%>" scope="request"/>

<br/>

<table border="0" cellspacing="0">
	<tr>
		<td>&nbsp;&nbsp;</td>
		<td valign="top">
		<hr/>
		<h2>User Maintenance</h2>

		<p>Please use one of the following links to configure user rights for the appropriate client:</p>
		</td>
	</tr>
	<tr>
		<td>&nbsp;&nbsp;</td>
		<td valign="top">
		<table border="0" cellspacing="0">
			<logic:iterate id="mandant" name="mandants">
				<tr>
				<td valign="top" align="left">
                        <a style="text-decoration:underline;"
                           href='<%=root%>/userList.do?mandantCode=<bean:write name="mandant" property="mandantCode"/>'>
                           <bean:write name="mandant" property="mandantName"/>
                        </a>
				</td>
				</tr>
			</logic:iterate>

		</table>
		</td>
	</tr>
</table>

</td></tr>
</table>
