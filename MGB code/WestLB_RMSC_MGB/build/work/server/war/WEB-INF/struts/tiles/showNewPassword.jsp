<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<%@page import="de.westlb.mgb.struts_client.*"%>

<% 
String root=((javax.servlet.http.HttpServletRequest)pageContext.getRequest()).getContextPath(); 
String queryString="";
if (request.getQueryString() != null) {
	queryString="?"+request.getQueryString();
}
%>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<link rel="stylesheet" href="<%=root%>/css/standard.css" type="text/css" />
<title>New MGB Password</title>
</head>

<body style="background-image:url(<%=root%>/images/navigationBackground.gif); background-repeat:repeat-y;">

<h2>New MGB Password</h2>
<p>
Your new password is
</p>

<%
if ( session != null && request.getAttribute("login.newPassword") != null) {
	String newPassword = (String) request.getAttribute("login.newPassword");
%>
	<table>
		<tr><td class="tablelabel">
		<%=newPassword%>
		</td></tr>
	</table>
<%
}
%>

<p>
Note that this password is only a local MGB-password. It can not be changed.
</p>

<p>
Back to the <a class="link" href="<%=root%>/directLogin">login page</a>.
</p>

</body>
</html>
