<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<%
     String redirectURL = request.getContextPath() + "/welcome.do";
     response.sendRedirect(redirectURL);
%>

