<%@page import="de.westlb.mgb.struts_client.*"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<script type="text/javascript">
    /* 
     * Open a new window containing the applet.
     */
	function startApplet(mandantCode) {	
		  window.open("<%=request.getContextPath()%>/startApplet.do?mandant="+mandantCode+"&title=MGB_UAT",'MGB_UAT','fullscreen=0,width=790,height=550,toolbar=0,location=0,directories=0,status=yes,menubar=0,scrollbars=yes,resizable=yes')
	}
</script>

<bean:define id="mandants" name="<%=RequestKeys.MANDANT_LIST%>" scope="request"/>

<div style="text-align:center;font-family:Verdana;font-weight:bold;font-size:x-large">  
MGB V 2.1.2
</div>

<br/>

<p class="title">MGB</p>
<p class="title">Welcome to the MGB Application!</p>

<br/>

<table border="0" cellspacing="0">
    <tr>
        <td>&nbsp;&nbsp;</td>
        <td valign="top">
        <hr/>
        <h2>Startpage</h2>

        <p>Please use one of the following links to to start the controller application for the appropriate client (you can change the client in the application anyway):</p>
        </td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;</td>
        <td valign="top">
        <table border="0" cellspacing="0">
            <logic:iterate id="mandant" name="mandants" type="de.westlb.mgb.client.server.vo.MandantVo">
                <tr>
                <td valign="top" align="left">
                <html:link onclick="<%= &quot;startApplet('&quot;+mandant.getMandantCode()+&quot;')&quot; %>" action="application.do" styleClass='link' title='Start applet'>Start Applet with <bean:write name="mandant" property="mandantName"/></html:link>
                </td>
                </tr>
            </logic:iterate>
        </table>
        </td>
    </tr>
</table>


<br/>

<p class="text">In case of problems contact the production team:</p>
<p class="text">E-Mail: <a class="link" href="mailto:RAIS@1fins.com">Risk Application Infrastructure Support</a>
</p>