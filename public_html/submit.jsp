<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" errorPage="errors.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="bibSource"
             class="edu.ucla.library.libservices.aeon.vger.generators.SubmitBibGenerator">
  <jsp:setProperty name="bibSource" property="bibID" param="bibID"/>
  <jsp:setProperty name="bibSource" property="itemIDs" value="${paramValues.itemID}"/>
  <jsp:setProperty name="bibSource" property="dbName" value='<%= application.getInitParameter("datasource.vger") %>'/>
</jsp:useBean>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>submit</title>
    <script type="text/javascript" language="JavaScript">
      /*function dologout()
      {
        document.AeonRequest.submit();
      }*/
      <!--
          function toggle_visibility(id, doSwitch) {
             var e = document.getElementById(id);
             var form = document.getElementById('AeonRequest');
             if(doSwitch == 2)
             {
                e.style.display = 'block';
                form.Format.disabled = false;
                form.ForPublication.disabled = false;
                form.ItemInfo3.disabled = false;
                form.RequestType.disabled = false;
                form.SkipOrderEstimate.disabled = false;
             }
             else
             {
                e.style.display = 'none';
                form.Format.disabled = true;
                form.ForPublication.disabled = true;
                form.ItemInfo3.disabled = true;
                form.RequestType.disabled = true;
                form.SkipOrderEstimate.disabled = true;
             }
          }
      //-->
    </script>
    <link href="http://www.library.ucla.edu/css/wht.css" rel="stylesheet" type="text/css">
  </head>
  <body bgcolor="#FFFFFF" topmargin="0" marginheight="0" marginwidth="0" leftmargin="0" width="960">
  <!--body onLoad="javascript:dologout()"-->
    <table width="960" cellpadding="0" cellspacing="0" align="center">
      <tr>
        <td width="165" bgcolor="#3283BE" align="center">
          <img src="http://www.library.ucla.edu/sites/all/themes/uclalib_omega/logo.png">
        </td>
        <td bgcolor="#3283BE" align="center">
          <font color="#666" class="body"><b>Library Special Collections</b></font><br/>
        </td>
        <td  width="155" bgcolor="#3283BE">
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center">Items Selected for Processing</td>
      </tr>
    </table>
    <form id="AeonRequest" name="AeonRequest" target="_self" method="post" action="https://speccoll.library.ucla.edu/logon"><!--https://speccoll.library.ucla.edu/aeon/aeon.dll"-->
    <!--form method="post" action="finalSubmit.jsp"-->
      <input name="AeonForm" value="EADRequest" type="hidden">
      <c:set var="bibRecord" value="${bibSource.bibData}"/>
      <table align="center" width="960" border="0" cellpadding="3">
        <tr>
          <td>Author</td>
          <td>${bibRecord.author}</td>
        </tr>
        <tr>
          <td>Title</td>
          <td>${bibRecord.title}</td>
        </tr>
        <tr>
          <td>Call Number(s)</td>
          <td>
            <ul>
              <c:forEach var="yrl" items="${bibRecord.yrlHoldings}">
                <li>${yrl.callNo}</li>
              </c:forEach>
            </ul>
          </td>
        </tr>
        <tr>
          <td>Is this request for:</td>
          <td>
            <input type="radio" name="reqType" value="1" checked="checked" onclick="toggle_visibility('dupe',1);">On-site review<br/>
            <input type="radio" name="reqType" value="2" onclick="toggle_visibility('dupe',2);">Duplication
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <div id="dupe" style="display: none;">
              <table>
                <tr>
                  <td>
                    Format:&nbsp;
                    <select name="Format" id="Format" disabled="true">
                      <option value="AV">Audio/video</option>
                      <option value="PDF">PDF</option>
                      <option value="TIFF">TIFF</option>
                    </select>
                  </td>
                </tr>
                <tr>
                  <td>
                    <input type="checkbox" name="ForPublication" id="ForPublication" value="Yes" disabled="true">For Publication?
                  </td>
                </tr>
                <tr>
                  <td>
                    Project Discription<br/>
                    <textarea cols="50" rows="5" id="ItemInfo3" name="ItemInfo3" disabled="true"></textarea>
                  </td>
                </tr>
              </table>
              <input value="Copy" name="RequestType" id="RequestType" type="hidden" disabled="true"/>
              <input type="hidden" name="SkipOrderEstimate" id="SkipOrderEstimate" value="Yes" disabled="true"/>
            </div>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <input type="submit" value="Submit Request"/>
          </td>
        </tr>
      </table>
      <input value="${param.specReq}" name="SpecialRequest" type="hidden"/>
      <input value="${param.notes}" name="notes" type="hidden"/>
      <c:choose>
        <c:when test="${not empty param.theDate}">
          <input name="ScheduledDate" type="hidden" value="${param.theDate}"/>
        </c:when>
        <c:otherwise>
          <input name="ScheduledDate" type="hidden" value="${param.textDate}"/>
        </c:otherwise>
      </c:choose>
      <c:choose>
        <c:when test="${bibSource.isBio}">
          <input name="Site" type="hidden" value="BIOMED"/>
        </c:when>
        <c:otherwise>
          <input name="Site" type="hidden" value="YRL"/>
        </c:otherwise>
      </c:choose>
      <input type="hidden" name="ItemAuthor" value="${bibRecord.author}"/>
      <input type="hidden" name="ItemDate" value="${bibRecord.pubDates}"/>
      <input type="hidden" name="SubmitButton" value="Submit Request"/>
      <input type="hidden" name="WebRequestForm" value="DefaultRequest"/>
      <c:choose>
        <c:when test="${not empty bibRecord.srlfItems}">
          <c:set var="index" value="-1"/>
          
          <c:forEach var="srlf" items="${bibRecord.srlfItems}" begin="0" end="0">
            <input value="${srlf.location}" name="Location" type="hidden"/>
          </c:forEach>
          <table align="center" width="960" border="1" class="footer">
            <tr>
              <th width="25%">Item Description</th>
              <th width="75%">Item Comment</th>
            </tr>
            <c:forEach var="srlf" items="${bibRecord.srlfItems}">
              <tr>
                  <td width="75%">
                    <c:if test="${not empty srlf.itemEnum}">
                      ${srlf.itemEnum}&nbsp;|&nbsp;
                    </c:if>
                    <c:if test="${not empty srlf.chron}">
                      ${srlf.chron}&nbsp;|&nbsp;
                    </c:if>
                    <c:if test="${(not empty srlf.copy) and (srlf.copy ne 0)}">
                      Copy&nbsp;${srlf.copy}&nbsp;|&nbsp;
                    </c:if>
                    <c:if test="${not empty srlf.note}">
                      ${srlf.note}&nbsp;|&nbsp;
                    </c:if>
                    <c:if test="${not empty srlf.oacDetails}">
                      <br/>
                      ${srlf.oacDetails}
                    </c:if>
                    <c:if test="${not empty srlf.pickupLocale}">
                      <br/>
                      ${srlf.pickupLocale}
                    </c:if>
                    <c:set var="index" value="${index + 1}"/>
                    <input type="hidden" name="Request" value="${index}"/>
                    <input value="${bibRecord.title}" name="ItemTitle_${index}" type="hidden"/>
                    <input value="${srlf.itemEnum}" name="ItemVolume_${index}" type="hidden"/>
                    <input value="${param.bibID}" name="ReferenceNumber_${index}" type="hidden"/>
                    <input value="${srlf.barcode}" name="ItemNumber_${index}" type="hidden"/>
                    <input value="${srlf.copy}" name="ItemIssue_${index}" type="hidden"/>
                    <input value="${srlf.status}" name="ItemCitation_${index}" type="hidden"/>
                    <input type="hidden" name="CallNumber_${index}" value="${srlf.callNo}"/>
                  </td>
                  <td width="25%">
                    <textarea cols="50" rows="10" name="Notes">${srlf.itemEnum}:</textarea>
                  </td>
                </tr>
            </c:forEach>
            <tr>
              <td colspan="2">
                <input type="submit" value="Submit Request"/>
              </td>
            </tr>
          </table>
        </c:when>
        <c:otherwise>
          <input type="hidden" name="Request" value="0"/>
          <input value="${bibRecord.title}" name="ItemTitle_0" type="hidden"/>
          <input value="" name="ItemVolume_0" type="hidden"/>
          <input value="${param.bibID}" name="ReferenceNumber_0" type="hidden"/>
          <input value="" name="ItemNumber_0" type="hidden"/>
          <input value="" name="ItemIssue_0" type="hidden"/>
          <c:forEach var="yrl" items="${bibRecord.yrlHoldings}">
            <c:if test="${not empty yrl.callNo}">
                <input type="hidden" name="CallNumber_0" value="${yrl.callNo}"/>
            </c:if>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </form>
  </body>
</html>
