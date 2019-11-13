<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<%@ page language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%String root = ((javax.servlet.http.HttpServletRequest) pageContext.getRequest()).getContextPath();%>

<html:xhtml/>
<html:html xhtml="true">
<head>
<link rel="stylesheet" href="<%=root%>/css/standard.css" type="text/css" />
<tiles:useAttribute name="title" />
<title><bean:message name="title" bundle="labels"/></title>
</head>

<body>
<script type="text/javascript">
function submitForm(action)
{
	document.forms[0].action=document.forms[0].action+ "?action=" + action + "";
	document.forms[0].submit();
}

function out(action)
{
	self.location.href = "<%=root%>" + action;
}

function cancelForm()
{
	document.forms[0].action=document.forms[0].action+ '?org.apache.struts.taglib.html.CANCEL=0';
	document.forms[0].submit();
}
</script>


<table border="0" width="100%" cellspacing="0">
	<tr>
		<td colspan="2">
			<tiles:insert attribute="header" />
		</td>
	</tr>
	<tr>
		<td valign="top" style="width:15%;height:800px;background-image:url(<%=root%>/images/navigationBackground.gif);background-repeat:repeat-y">
			<tiles:insert attribute="menu" />
		</td>
		<td valign="top" align="left">
		<table border="0" width="100%" cellspacing="0">
			<tr>
				<td>
				<logic:messagesPresent>
					<ul>
						<html:messages id="error" bundle="messages" header="errors.header" footer="errors.footer">
							<li><bean:write name="error" filter="false" ignore="true"/></li>
						</html:messages>
					</ul>
				</logic:messagesPresent> 
				<logic:messagesPresent message="true">
					<ul>
						<html:messages id="message" bundle="messages" message="true">
							<li><bean:write name="message" filter="false" ignore="true"/></li>
						</html:messages>
					</ul>
				</logic:messagesPresent>
				</td>
			</tr>
			<tr>
				<td valign="top" align="left">
					<tiles:insert attribute="content" />
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<hr/>
			<tiles:insert attribute="footer" />
		</td>
	</tr>
</table>
</body>
</html:html>

