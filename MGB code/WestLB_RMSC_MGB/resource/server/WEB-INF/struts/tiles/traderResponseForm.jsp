<%@page import="de.westlb.mgb.struts_client.*"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/string-1.1" prefix="s" %>
<%@ taglib uri="http://www.westlb.de/mgb/tags" prefix="mgb"%>

<bean:define id="tradeVo" name="<%=RequestKeys.TRADE_VO%>"
	scope="request" />
<bean:define id="mailVo" name="<%=RequestKeys.MAIL_VO%>" scope="request" />
<bean:define id="childMails" name="<%=RequestKeys.MAIL_CHILD_LIST%>"
	scope="request" />


<table border="1" width="100%">
	<tbody>
		<tr>
			<td class="tablehead" colspan="4"><bean:message
				key="page.traderResponse.label.subtitle.trade" /></td>
		</tr>
		<tr>
			<td class="tablelabel"><bean:message
				key="page.traderResponse.label.tradeId" /></td>
			<td class="tablevalue"><bean:write name="tradeVo" property="tradeId" /></td>
			<td class="tablelabel"><bean:message
				key="page.traderResponse.label.reclamationCode" /></td>
			<td class="tablevalue"><bean:write name="tradeVo"
				property="reclamationStateCode" /></td>
		</tr>
		<tr>
			<td class="tablelabel"><bean:message
				key="page.traderResponse.label.trader" /></td>
			<td class="tablevalue"><bean:write name="tradeVo"
				property="traderName" /></td>
			<td class="tablelabel"><bean:message
				key="page.traderResponse.label.tradeDate" /></td>
			<td class="tablevalue" align="right"><mgb:write name="tradeVo"
				property="tradeDate" formatKey="formats.dateTime" bundle="formats" /></td>
		</tr>
		<tr>
			<td class="tablelabel"><bean:message
				key="page.traderResponse.label.instrument" /></td>
			<td class="tablevalue"><bean:write name="tradeVo"
				property="instrument" /></td>
			<td class="tablelabel"><bean:message
				key="page.traderResponse.label.systemDate" /></td>
			<td class="tablevalue" align="right"><mgb:write name="tradeVo"
				property="systemDate" formatKey="formats.dateTime" bundle="formats" /></td>
		</tr>
		<tr>
			<td class="tablelabel"><bean:message
				key="page.traderResponse.label.volume" /></td>
			<td class="tablevalue" align="right"><bean:write name="tradeVo"
				property="volume" formatKey="formats.currency" bundle="formats" /></td>
			<td class="tableempty"></td>
			<td class="tableempty"></td>
		</tr>
		<tr>
			<td class="tablelabel"><bean:message
				key="page.traderResponse.label.tradePrice" /></td>
			<td class="tablevalue" align="right"><bean:write name="tradeVo"
				property="tradePrice" formatKey="formats.currency" bundle="formats" /></td>
			<td class="tableempty"></td>
			<td class="tableempty"></td>
		</tr>

		<logic:notEmpty name="tradeVo.currentPrice">
			<tr>
				<td class="tablelabel"><bean:message
					key="page.traderResponse.label.marketPrice" /></td>
				<td class="tablevalue" align="right"><bean:write name="tradeVo"
					property="currentPrice.minPrice" formatKey="formats.currency"
					bundle="formats" /></td>
				<td class="tableempty"></td>
				<td class="tableempty"></td>
			</tr>
			<tr>
				<td class="tablelabel"><bean:message
					key="page.traderResponse.label.marketPriceTime" /></td>
				<td class="tablevalue" align="right"><bean:write name="tradeVo"
					property="currentPrice.priceDate" formatKey="formats.currency"
					bundle="formats" /></td>
				<td class="tableempty"></td>
				<td class="tableempty"></td>
			</tr>
		</logic:notEmpty>
	</tbody>
</table>

<br/>

<table border="1" width="100%">
	<tbody>
		<tr>
			<td class="tablehead" colspan="1"><bean:message
				key="page.traderResponse.label.controllerMail.subtitle" /></td>
		</tr>
		<tr>
			<td class="tablevalue" align="left"><bean:write name="mailVo"
				property="subject" /></td>
		</tr>
		<tr>
			<td class="tablevalue" align="left">
			<div id="EmailTextDiv"
				style="display: ''; width: 100%; height: 90px; overflow-x: auto; overflow-y: scroll">
			<s:replace replace="NL" with="<br/>NL" newlineToken="NL">
				<bean:write name="mailVo" property="text" />
			</s:replace></div>
			</td>
		</tr>
	</tbody>
</table>

<br/>

<html:form action="/traderResponseSubmit.do">
	<html:hidden name="<%=RequestKeys.MAIL_VO%>" property="id" />
	<table border="1" width="100%">
		<tbody>
			<tr>
				<td class="tablehead"><bean:message
					key="page.traderResponse.label.subtitle.response" /></td>
			</tr>
			<tr>
				<td class="tablevalue">
				<div id="ResponseTextDiv"
					style="display: ''; width: 100%; height: 50px; overflow-x: auto; overflow-y: scroll">

				<logic:iterate id="childMail" name="childMails">
				Response from <bean:write name="childMail" property="senderName"/>  
				<mgb:write name="childMail" property="creationDate" formatKey="formats.dateTime" bundle="formats" /> :
				<s:replace replace="NL" with="<br/>NL" newlineToken="NL">
					<bean:write name="childMail" property="text" />
				</s:replace>
				<br/>
				</logic:iterate>
				</div>
				</td>
			</tr>
			<tr>
				<td class="tablevalue" align="left">Add comment!</td>
			</tr>
			<tr>
				<td align="center"><html:textarea property="text" rows="4" cols="100"></html:textarea></td>
			</tr>
			<tr>
				<td align="center"><input type="submit" name="Submit"
					value="Submit" /></td>
			</tr>
		</tbody>
	</table>
</html:form>