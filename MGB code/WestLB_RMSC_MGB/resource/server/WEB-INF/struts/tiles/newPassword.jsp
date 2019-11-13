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
<title>Get New MGB Password</title>
</head>

<body style="background-image:url(<%=root%>/images/navigationBackground.gif); background-repeat:repeat-y;">

<h2>Get New MGB Password</h2>
<p>
Please enter your MGB account and press submit to get your personal password.
</p>

<%
if ( session != null && request.getAttribute("login.message") != null) {
	String message = (String) request.getAttribute("login.message");
%>
	<p class="error"><%=message%></p>
<%
}
%>

<form method="post" action="<%=root%>/directLogin">
<table border="1">
	<tbody>
		<tr>
			<td class="tablehead" align="left">MGB account</td>
			<td class="tablevalue" align="left">
				<input type="text" name="newPasswordUserAccount" value=""/>
			</td>
		</tr>
		<tr>
			<td class="tablebutton" colspan="2" align="center">
				<input type="submit" name="Submit" value="Submit"/>
			</td>
		</tr>
	</tbody>
</table>
</form>

<p>
If you have any questions in signing up, please contact <a class="link" href="mailto:jens_richelsen@westlb.de?subject=MGB_Direct_Login">Jens Richelsen</a>.
</p>

</body>
</html>
