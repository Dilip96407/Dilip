<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<%@ page language="java" %>

<%@ page import="de.westlb.mgb.converter.*"%>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.westlb.de/mgb/tags" prefix="mgb"%>

<%String root = ((javax.servlet.http.HttpServletRequest) pageContext.getRequest()).getContextPath();%>

<bean:define id="importStatusArray" name="<%=MgbDataHandlerServlet.IMPORT_THREAD_STATUS_ARRAY%>" scope="request"/>

<html:xhtml/>
<html:html xhtml="true">
<head>
	<link rel="stylesheet" href="<%=root%>/css/standard.css" type="text/css" />
	<title>MGB - Import Status</title>
</head>

<body>

<p class="title">MGB - Import Status</p>

<table border="1" width="100%">
	<tr>
		<td class="tablehead" align="left">system</td>
		<td class="tablehead" align="left">status</td>
		<td class="tablehead" align="left">startTime</td>
		<td class="tablehead" align="left">lastImportTime</td>
	</tr>
	<c:forEach var="importStatus" items="${importStatusArray}">
		<tr>
			<td class="tablevalue" align="left">${importStatus.sourceSystemName}</td>	
			<td class="tablevalue" align="left">${importStatus.status}</td>	
			<td class="tablevalue" align="left">${importStatus.startTime.time}</td>
			<td class="tablevalue" align="left">${importStatus.lastImportTime.time}&nbsp;</td>
		</tr>
	</c:forEach>
</table>

<c:url var="myUrl" value="/CheckImport">
</c:url>
<p>
Status der Importprozesse <a class="link" href="<c:out value='${myUrl}'/>">AKTUALISIEREN</a>.
</p>

<c:url var="myUrl" value="/CheckImport">
	<c:param name="action" value="stop"/>
</c:url>
<p>
Alle Importprozesse <a class="link" href="<c:out value='${myUrl}'/>">STOPPEN</a>.
</p>

<c:url var="myUrl" value="/CheckImport">
	<c:param name="action" value="restart"/>
</c:url>
<p>
Alle Importprozesse <a class="link" href="<c:out value='${myUrl}'/>">RESTARTEN</a>.
</p>

</body>
</html:html>
