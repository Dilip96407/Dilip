<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>
<style type="text/css">
<!--
body {background-color:#FFFFCC;font-family:Verdana,sans-serif;font-size:11pt;margin-left:0px; margin-right:0px; margin-top:0px; margin-bottom:0px}
td {font-family:Verdana,sans-serif;font-size:11pt;padding-left:3px;padding-right:3px;}
h1 { color:red}

h1 {font-size:36pt;
	text-align:center;
     color:#FF0000;
     font-style:italic;
     border-bottom:solid thin black; }

h2 {font-size:12pt;
	text-align:left;
     color:#FF0000;
     font-style:plain;
     border-bottom:solid thin black; }
*.hinterlegt {background-color:#FFD0A0;}
-->
</style>

<head>
        <% String windowTitle = (String) request.getParameter("title"); %>
        <title><%= windowTitle %></title>
</head>
<body>

<script type="text/javascript" language="JavaScript"><!--
document.writeln('<object ');
if (navigator.userAgent.indexOf("Trident/4.0") != -1) {
  document.writeln('    classid = "clsid:CAFEEFAC-0016-0000-0025-ABCDEFFEDCBA"');
} else {
  document.writeln('    classid = "clsid:8AD9C840-044E-11D1-B3E9-00805F499D93"');
}
document.writeln('    codebase = "http://java.sun.com/update/1.6.0/jinstall-6-windows-i586.cab#Version=1,6,0,25"');
document.writeln('    width = "100%" height = "100%" >');
document.writeln('    <param name = "progressbar" value="true"/>');
document.writeln('    <param name = "progresscolor" value="orange"/>');
document.writeln('    <param name = "boxmessage" value="LOADING MGB 2.1.2.1917">');
document.writeln('    <param name = "code" value = "de.westlb.mgb.client.applet.Applet.class" />');
document.writeln('    <param name = "codebase" value="./client"/>');
document.writeln('    <param name = "cache_archive" value = "mgb-client.jar,');
document.writeln('                                           mgb-properties.jar,');
document.writeln('                                           activation.jar,');
document.writeln('                                           axis-1.4.jar,');
document.writeln('                                           blpapi-3.6.1-0.jar,');
document.writeln('                                           commons-beanutils-1.6.1.jar,');
document.writeln('                                           commons-codec-1.3.jar,');
document.writeln('                                           commons-collections-3.1.jar,');
document.writeln('                                           commons-digester-1.8.jar,');
document.writeln('                                           commons-discovery-0.2.jar,');
document.writeln('                                           commons-lang3-3.0.1.jar,');
document.writeln('                                           commons-logging-1.0.3.jar,');
document.writeln('                                           dom4j-1.6.1.jar,');
document.writeln('                                           itext-0.96.jar,');
document.writeln('                                           jacob-1.14.3.jar,');
document.writeln('                                           jasperreports-0.5.0.jar,');
document.writeln('                                           jaws.jar,');
document.writeln('                                           jaxrpc.jar,');
document.writeln('                                           jhall.jar,');
document.writeln('                                           log4j-1.2.17.jar,');
document.writeln('                                           mailapi.jar,');
document.writeln('                                           poi-3.7-20101029.jar,');
document.writeln('                                           saaj-1.2.jar,');
document.writeln('                                           smtp.jar,');
document.writeln('                                           wsdl4j-1.5.1.jar,');
document.writeln('                                           xaf_ctrl.jar,');
document.writeln('                                           xaf_swing.jar"/>');
document.writeln('    <param name = "cache_version" value = "2.1.2.1917,');
document.writeln('                                           2.1.2.1917,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.3.0,');
document.writeln('	                                         1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           1.0.2.0,');
document.writeln('                                           2.1.2.1917,');
document.writeln('                                           2.1.2.1917"/>');
document.writeln('    <param name = "type" value = "application/x-java-applet;version=1.6"/>');
document.writeln('    <param name = "scriptable" value = "false"/>');
document.writeln('    <param name = "servername" value = "../services/Mgb"/>');
document.writeln('    <param name = "userid" value = "<%= session.getAttribute("userId") %>"/>');
document.writeln('    <param name = "key" value = "<%= session.getAttribute("key") %>"/>');
document.writeln('    <param name = "cookie" value = "',document.cookie,'"/>');
//--></script>
<% 	if (session.getAttribute("startContentId") != null) {
		out.println("<param name = \"startContentId\" value = \"" + session.getAttribute("startContentId") + "\"/>");
		out.println("<param name = \"startContentParameter\" value = \"" + session.getAttribute("startContentParameter") + "\"/>");
	}
	if (session.getAttribute("mandant") != null) {
		out.println("<param name = \"mandant\" value = \"" + session.getAttribute("mandant") + "\"/>");
	}
%>
<script type="text/javascript" language="JavaScript"><!--
document.writeln('    <comment>');
document.writeln('	  		<embed  type = "application/x-java-applet;version=1.6" ');
document.writeln('	  				java_arguments = "-Xmx256m"');
document.writeln('	  				width = "100%" height = "100%"');
document.writeln('	  	            code = "de.westlb.mgb.client.applet.Applet.class"');
document.writeln('                  archive =               "mgb-client.jar,');
document.writeln('	  	                                     mgb-properties.jar,');
document.writeln('	  	                                     activation.jar,');
document.writeln('                                           axis-1.4.jar,');
document.writeln('                                           blpapi-3.6.1-0.jar,');
document.writeln('                                           commons-beanutils-1.6.1.jar,');
document.writeln('                                           commons-codec-1.3.jar,');
document.writeln('                                           commons-collections-3.1.jar,');
document.writeln('                                           commons-digester-1.8.jar,');
document.writeln('                                           commons-discovery-0.2.jar,');
document.writeln('                                           commons-lang3-3.0.1.jar,');
document.writeln('                                           commons-logging-1.0.3.jar,');
document.writeln('                                           dom4j-1.6.1.jar,');
document.writeln('                                           itext-0.96.jar,');
document.writeln('                                           jacob-1.14.3.jar,');
document.writeln('                                           jasperreports-0.5.0.jar,');
document.writeln('                                           jaws.jar,');
document.writeln('                                           jaxrpc.jar,');
document.writeln('                                           jhall.jar,');
document.writeln('                                           log4j-1.2.17.jar,');
document.writeln('                                           mailapi.jar,');
document.writeln('                                           poi-3.7-20101029.jar,');
document.writeln('                                           saaj-1.2.jar,');
document.writeln('                                           smtp.jar,');
document.writeln('                                           wsdl4j-1.5.1.jar,');
document.writeln('                                           xaf_ctrl.jar,');
document.writeln('                                           xaf_swing.jar"');
document.writeln('		            ServerName = "../services/Mgb" ');
document.writeln('			    	scriptable = false');
document.writeln('			    	progressbar = true');
document.writeln('			    	progresscolor = orange');
document.writeln('			    	boxmessage = "LOADING MGB 2.1.2.1917"');
document.writeln('                  mandant = "<%= session.getAttribute("mandant") %>"');
document.writeln('                  userid = "<%= session.getAttribute("userId") %>"');
document.writeln('                  key = "<%= session.getAttribute("key") %>"');
document.writeln('			    	codebase ="./client"');
document.writeln('			    	pluginspage = "http://www.java.com/de/download/">');
document.writeln(' 					<noembed>');
document.writeln(' 		            </noembed>');
document.writeln(' 			</embed>');
document.writeln('    </comment>');
document.writeln('			<h1>Java Plugin</h1>');
document.writeln('			<table border="0" align="center" width="95%"  cellspacing="10px">');
document.writeln('				<tr>');
document.writeln('					<td class="hinterlegt" width="50%" >');
document.writeln('						<h2>Fehlendes Java-Plugin</h2>');
document.writeln('						Es tut uns leid, das Java 2 Plugin ist nicht installiert.');
document.writeln('						Um das Java 2 Plugin zu bekommen, wenden Sie sich bitte, je nach Standort, an folgende Ansprechpartner:');
document.writeln('					</td>');
document.writeln('					<td class="hinterlegt" width="50%" valign="top">');
document.writeln('						<h2>Missing Java-Plugin</h2>');
document.writeln('						Sorry, the Java 2 Plugin ist not installed.');
document.writeln('						To get the Java 2 Plugin, please call the following contacts depending on your location.');
document.writeln('					</td>');
document.writeln('				</tr>');
document.writeln('				<tr>');
document.writeln('					<td align="center" colspan="2">');
document.writeln('						<table class="hinterlegt" border="1" width="80%">');
document.writeln('						 <tr>');
document.writeln('							<th align="center">Region</th>');
document.writeln('							<th align="center">Contact</th>');
document.writeln('							<th align="center">Phone Number</th>');
document.writeln('						 </tr>');
document.writeln('						 <tr>');
document.writeln('							<td align="center">GSA/Trading</td>');
document.writeln('							<td align="center">Customer Service</td>');
document.writeln('							<td align="center">+49 211 826 4444</td>');
document.writeln('						 </tr>');
document.writeln('						 <tr>');
document.writeln('							<td align="center">EMEA</td>');
document.writeln('							<td align="center">Help Desk</td>');
document.writeln('							<td align="center">+44 20 7020 6363</td>');
document.writeln('						 </tr>');
document.writeln('						 <tr>');
document.writeln('							<td align="center">NASA</td>');
document.writeln('							<td align="center">Help Desk</td>');
document.writeln('							<td align="center">+1 212 852 6222</td>');
document.writeln('						 </tr>');
document.writeln('							<td align="center">APAC</td>');
document.writeln('							<td align="center">Help Desk</td>');
document.writeln('							<td align="center">+ 81 3 5510 3557</td>');
document.writeln('						</table>');
document.writeln('					</td>');
document.writeln('				</tr>');
document.writeln('			</table>');
document.writeln('��� </noembed></embed>');
document.writeln('</object>');
//--></script>
</body>
</html>