<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<% String root=((javax.servlet.http.HttpServletRequest)pageContext.getRequest()).getContextPath(); %>

<!-- Trade-Control -->
<table class="menutable" cellspacing="0">
	<tr>
		<td align='left'>
		<table class="menutable" style="background-image:url(<%=root%>/images/BlueBar.gif);background-repeat:no-repeat;height:20px" width="150">
			<tr>
				<td class="menu" >
					<h5>&nbsp;&nbsp;&nbsp;Trade-Control</h5>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<table>
	<tr>
		<td>&nbsp;</td>
		<td class="menu"><img src="<%=root%>/images/bulletBlue.gif" alt="*"/></td>
		<td class="menu" colspan="2">
			<html:link	action="/application.do" styleClass="nav2" title="Application">Application</html:link>
		</td>
	</tr>
</table>
<br/>
<!-- End Trade-Control -->

<!-- Trader -->
<table class="menutable" cellspacing="0">
	<tr>
		<td align='left'>
		<table class="menutable" style="background-image:url(<%=root%>/images/BlueBar.gif);background-repeat:no-repeat;height:20px" width="150">
			<tr>
				<td class="menu" >
					<h5>&nbsp;&nbsp;&nbsp;Trader</h5>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<table>
	<tr>
		<td>&nbsp;</td>
		<td class="menu"><img src="<%=root%>/images/bulletBlue.gif" alt="*"/></td>
		<td class="menu" colspan="2">
			<html:link action="/reclamationMailList.do" styleClass='nav2' title='List of reclamation mails'>Inbox</html:link>
		</td>
	</tr>
</table>
<br/>
<!-- End Trader -->



<!-- User Maintenance -->
<table class="menutable" cellspacing="0">
	<tr>
		<td align='left'>
		<table class="menutable" style="background-image:url(<%=root%>/images/BlueBar.gif);background-repeat:no-repeat;height:20px" width="150">
			<tr>
				<td class="menu">
					<h5>&nbsp;&nbsp;&nbsp;User Maintenance</h5>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<table>
    <tr>
        <td>&nbsp;</td>
        <td class="menu"><img src="<%=root%>/images/bulletBlue.gif" alt="*"/></td>
        <td class="menu" colspan="2">
            <html:link action="/userMaintenance.do" styleClass='nav2' title='UserMaintenance'>User Maintenance</html:link>
        </td>
    </tr>
	<tr>
		<td>&nbsp;&nbsp;</td>
		<td class="menu"><img src="<%=root%>/images/bulletBlue.gif" alt="*"/></td>
		<td class="menu" colspan="2">
			<html:link href="https://westgate.westlb.net/" styleClass='nav2' title='WestGate User Management E-Forms'>Apply via E-Forms</html:link>
		</td>
	</tr>
</table>
<br/>
<!-- End User management -->

<!-- Local Report -->
<table class="menutable" cellspacing="0">
	<tr>
		<td align='left'>
		<table class="menutable" style="background-image:url(<%=root%>/images/BlueBar.gif);background-repeat:no-repeat;height:20px" width="150">
			<tr>
				<td class="menu">
					<h5>&nbsp;&nbsp;&nbsp;Local Report</h5>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<table>
    <tr>
        <td>&nbsp;&nbsp;</td>
        <td class="menu"><img src="<%=root%>/images/bulletBlue.gif" alt="*"/></td>
        <td class="menu" colspan="2">
            <a href="<%=root%>/openJobsList.do" class='nav2' title='Open Jobs'>Open Jobs</a>
        </td>
    </tr>
    <tr>
        <td>&nbsp;&nbsp;</td>
        <td class="menu"><img src="<%=root%>/images/bulletBlue.gif" alt="*"/></td>
        <td class="menu" colspan="2">
            <a href="<%=root%>/localReport.do" class='nav2' title='Local Report'>Local Report</a>
        </td>
    </tr>
</table>
<br/>
<!-- End Local Report -->

<!-- Marketdata batch -->
<table class="menutable" cellspacing="0">
	<tr>
		<td align='left'>
		<table class="menutable" style="background-image:url(<%=root%>/images/BlueBar.gif);background-repeat:no-repeat;height:20px" width="150">
			<tr>
				<td class="menu">
					<h5>&nbsp;&nbsp;&nbsp;Marketdata batch</h5>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<table>
	<tr>
		<td>&nbsp;&nbsp;</td>
		<td class="menu"><img src="<%=root%>/images/bulletBlue.gif" alt="*"/></td>
		<td class="menu" colspan="2">
			<a href="<%=root%>/autoCheck.jnlp" class='nav2' title='Start Marketdata Batch'>Start Marketdata Batch</a>
		</td>
	</tr>
</table>
<br/>
<!-- End Marketdata batch -->

<!-- Documentation -->
<table class="menutable" cellspacing="0">
	<tr>
		<td align='left'>
		<table class="menutable" style="background-image:url(<%=root%>/images/BlueBar.gif);background-repeat:no-repeat;height:20px" width="150">
			<tr>
				<td class="menu">
					<h5>&nbsp;&nbsp;&nbsp;Documentation</h5>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<table>
	<tr>
		<td>&nbsp;&nbsp;</td>
		<td class="menu"><img src="<%=root%>/images/bulletBlue.gif" alt="*"/></td>
		<td class="menu" colspan="2">
			<a href="<%=root%>/docu.do" class='nav2' title='Documentation'>Documentation</a>
		</td>
	</tr>
</table>
<br/>
<!-- End Documentation -->


<table>
	<tr>
		<td valign='middle' colspan="4">
			<br/>
			<p class="text">Production Team:<br/>
            E-Mail: <a class="link" href="mailto:RAIS@1fins.com">Risk Application Infrastructure Support</a>
			</p>
		</td>
	</tr>	
</table>