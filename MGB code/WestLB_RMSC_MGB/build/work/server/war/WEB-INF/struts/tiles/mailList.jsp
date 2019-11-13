<%@page import="de.westlb.mgb.struts_client.*"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://www.westlb.de/mgb/tags" prefix="mgb"%>

<% String root = ((javax.servlet.http.HttpServletRequest)pageContext.getRequest()).getContextPath(); %>

<table>
<tr><td>

<bean:define id="mails" name="<%=RequestKeys.MAIL_LIST%>" scope="request"/>
<p class="title">Trader reclamation  inbox</p>
<p class="text">The list contains all controller mails for open reclamations. Please edit the mails.</p>
<table border="1" width="100%">
	<tbody>
		<tr>
			<td class="tablehead" align="left">&nbsp;</td>
			<td class="tablehead" align="left"><bean:message key="page.reclamationMailList.label.sendDate"/></td>
			<td class="tablehead" align="left"><bean:message key="page.reclamationMailList.label.senderName"/></td>
			<td class="tablehead" align="left"><bean:message key="page.reclamationMailList.label.tradeId"/></td>
			<td class="tablehead" align="left"><bean:message key="page.reclamationMailList.label.subject"/></td>
			<td class="tablehead" align="left"><bean:message key="page.reclamationMailList.label.responseExists"/></td>
		</tr>
		<logic:iterate id="mail" name="mails">
			<tr>
				<td class="tablevalue" align="left">
					<a href='<%=root%>/traderResponse.do?mailId=<bean:write name="mail" property="id"/>&amp;mandant=<bean:write name="mail" property="mandantCode"/>'> 
						<img src="<%=root%>/images/Edit16.gif" alt="edit"/>
					</a>
				</td>
				<td class="tablevalue" align="left">
					<mgb:write name="mail" property="creationDate" formatKey="formats.dateTime" bundle="formats"/>
				</td>
				<td class="tablevalue" align="left">
					<bean:write name="mail" property="senderName"/>
				</td>
				<td class="tablevalue" align="left">
					<bean:write name="mail" property="tradeId"/>
				</td>
				<td class="tablevalue" align="left">
					<a href='<%=root%>/traderResponse.do?mailId=<bean:write name="mail" property="id"/>&amp;mandant=<bean:write name="mail" property="mandantCode"/>'> <bean:write name="mail" property="subject"/></a>
				</td>
				<td class="tablevalue" align="center">
					<logic:equal name="mail" property="traderResponseExisting" value="true">
						x
					</logic:equal>	
					<logic:notEqual name="mail" property="traderResponseExisting" value="true">
						&nbsp;
					</logic:notEqual>	
				</td>
			</tr>
		</logic:iterate>
	</tbody>
</table>

</td></tr>
</table>