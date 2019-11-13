<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<% String root=((javax.servlet.http.HttpServletRequest)pageContext.getRequest()).getContextPath(); %>

<script type="text/javascript">
	/* 
	 * HOME -> zur MGB Startseite
	 */
	function home() {	
	    self.location.href = '<%=root%>/welcome.do';
	}
	
	function reportProblem() {	
	    self.location.href = 'http://crms.static.westlb.net:83/mantis';
	}
	
</script>


<table width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td class="topmenu" style="width:400px">&nbsp;<span class="topmenu">|</span>&nbsp;
			<a class="topmenu" onclick="home()"	title="Zur Startseite des MGB-Tools" style="cursor:hand">Home</a>		&nbsp;<span class="topmenu">|</span>&nbsp; 
			<a class="topmenu"	onclick="reportProblem()" title="Report Problem" style="cursor:hand">Report Problem</a> 
			&nbsp;
			<span class="topmenu">|</span>
			&nbsp;
		</td>
		<td class="topmenu" style="width:*" align="center">&nbsp;</td>
		<td class="topmenu" style="width:300px" align="right">
			<a class='topmenu' title='User id'>
				<bean:write name="LoginContext" property="ntUserId" scope="session" ignore="true"/>
			</a>
			&nbsp;&nbsp;
		</td>
	</tr>

	<tr>
		<td style="background-image:url(<%=root%>/images/Header_mgb.jpg);height:40px"></td>
		<td style="background-image:url(<%=root%>/images/background.gif);height:40px"></td>
		<td style="background-image:url(<%=root%>/images/background.gif);height:40px"></td>
	</tr>
</table>