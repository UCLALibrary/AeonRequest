<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" errorPage="errors.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="bibSource"
             class="edu.ucla.library.libservices.aeon.vger.generators.VgerBibDataGenerator">
  <jsp:setProperty name="bibSource" property="bibID" param="bibID"/>
  <jsp:setProperty name="bibSource" property="dbName" value='<%= application.getInitParameter("datasource.vger") %>'/>
</jsp:useBean>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="http://www.library.ucla.edu/css/wht.css" rel="stylesheet" type="text/css">
    <title>Library Special Collections</title>
    <script language="javascript" type="text/javascript">
      <!--
        function validateForm(formObj)
        {
          var customDate = 0;
          var selectedDate = 0;
          var pattern = /\d{2}\/\d{2}\/\d{4}/;
          
          for ( var i = 0; i < formObj.theDate.length; i++ )
          {
            if ( formObj.theDate[i].checked )
              selectedDate += 1;
          }
          
          if ( formObj.textDate.value.replace(/^(\s+)/,"").replace(/(\s+)$/,"") != "" )
          {
            if ( !pattern.test( formObj.textDate.value ) )
            {
              alert( "The date value must be in MM/DD/YYYY format" );
              formObj.textDate.select();
              formObj.textDate.focus();
              return false;
            }
            else
              customDate += 1;
          }
          
          if ( ( customDate > 0 ) || ( selectedDate > 0 ) )
            return true;
          else
          {
            alert( "You must either select a listed date or enter a desired date for your visit" );
            return false;
          }
        }

        function clearRadioDates()
        {
          var formObj = document.getElementById("bibForm");
          var textDate = document.getElementById("textDate").value;
          
          if ( textDate != "" )
          {
            for ( i = 0; i < formObj.theDate.length; i++ )
            {
              formObj.theDate[ i ].checked = false;
            }
          }
        }
      -->
    </script>
  </head>
  <body bgcolor="#FFFFFF" topmargin="0" marginheight="0" marginwidth="0" leftmargin="0" width="960">
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
      </tr>
    </table>
    <table align="center" width="960" border="1" class="footer">
      <tr>
        <td>
          Welcome to UCLA Library Special Collections.  Thank you for your 
          interest in our holdings.  Below are the details of the material you 
          are considering including which location you will go to view the 
          material.  There are two possible locations: Louise M. Darling 
          Biomedical Library and Charles E. Young Research Library. All of our 
          material is non-circulating and cannot be transferred between the 
          Reading Rooms.<br/><br/>
          You may select as many items as you like, but be aware there is a 
          daily limit of 5 items per researcher for material retrieved from 
          off-site storage.<br/><br/>
          <!--b>If this is your <em>first</em> time using this system:<br/> 
          Before you proceed with your request, please 
          follow this link to create a user account with Library Special 
          Collections: <a href="https://speccoll.library.ucla.edu/logon" target="_blank">Aeon registration</a></b> When 
          you click on this link, a registration page will open up in a new tab, 
          please follow instructions on that page. You may then proceed below 
          with paging material from Library Special Collections.<br/-->
          If you would like to see the material in a particular order, please 
          indicate so in the “Notes” area.<br/><br/>
          Please review your selections before clicking the “Submit Request” 
          button and note the location listed.
        </td>
      </tr>
    </table>
    <br/>
    <c:set var="bibRecord" value="${bibSource.bibData}"/>
    <form action="submit.jsp" id="bibForm" method="POST" onsubmit="JavaScript:return validateForm(this);">
      <input type="hidden" name="bibID" value="${param.bibID}"/>
      <table align="center" width="960" border="0" cellpadding="3">
        <tr>
          <td>Record ID</td>
          <td>${param.bibID}</td>
        </tr>
        <tr>
          <td>Author</td>
          <td>${bibRecord.author}</td>
        </tr>
        <tr>
          <td>Title</td>
          <td>${bibRecord.title}</td>
        </tr>
        <tr>
          <td>Dates</td>
          <td>${bibRecord.pubDates}</td>
        </tr>
        <c:if test="${not empty bibRecord.marc246}">
          <tr>
            <td>Collection Title</td>
            <td>${bibRecord.marc246}</td>
          </tr>
        </c:if>
        <c:if test="${not empty bibRecord.marc506}">
          <tr>
            <td>Restrictions on Access</td>
            <td>${bibRecord.marc506}</td>
          </tr>
        </c:if>
        <c:if test="${not empty bibRecord.marc524}">
          <tr>
            <td>Preferred Citation</td>
            <td>${bibRecord.marc524}</td>
          </tr>
        </c:if>
        <c:if test="${not empty bibRecord.marc590}">
          <tr>
            <td>Local Note</td>
            <td>${bibRecord.marc590}</td>
          </tr>
        </c:if>
        <tr>
          <td>Call Number(s)</td>
          <td>
            <ul>
              <c:forEach var="yrl" items="${bibRecord.yrlHoldings}">
                <li style="list-style-type: none;">
                  <c:if test="${not empty yrl.callNo}">
                    ${yrl.callNo}
                  </c:if>
                </li>
              </c:forEach>
            </ul>
          </td>
        </tr>
        <tr>
          <td>
            Special Requests/Questions?<br/>
            Please enter any special requests or questions you have for library staff.
          </td>
          <td>
            <textarea cols="50" rows="5" name="specReq" placeholder="maximum 255 characters"></textarea>
          </td>
        </tr>
        <tr>
          <td>
            My Notes:<br/>
            enter any notes about this request for your personal reference.
          </td>
          <td>
            <textarea cols="50" rows="5" name="Notes"></textarea>
          </td>
        </tr>
        <jsp:useBean id="dateGetter"
                     class="edu.ucla.library.libservices.aeon.vger.utility.AvailableDates">
          <c:choose>
            <c:when test="${bibSource.isBio}">
              <jsp:setProperty name="dateGetter" property="unitID" value='<%= application.getInitParameter("unitid.biohis") %>'/>
            </c:when>
            <c:otherwise>
              <jsp:setProperty name="dateGetter" property="unitID" value='<%= application.getInitParameter("unitid.yrlspc") %>'/>
            </c:otherwise>
          </c:choose>
          <%--jsp:setProperty name="dateGetter" property="dbName" value='<%= application.getInitParameter("datasource.hours") %>'/--%>
        </jsp:useBean>
        
        <c:set var="openDates" value="${dateGetter.availables}"/>
        <tr>
          <td colspan="2">
              <table>
                <tr>
                  <th colspan="3">
                    Select date of visit.<br/>
                    If material is stored off-site, please allow at least 3 days for retrieval.
                  </th>
                </tr>
                <tr>
                  <td>Select a listed date</td>
                  <td>OR</td>
                  <td>Enter a preferred date</td>
                </tr>
                <tr>
                  <td>
                    <c:forEach var="theDate" items="${openDates}" begin="0" end="6">
                      <input type="radio" name="theDate" value="${theDate}"/>&nbsp;${theDate}<br/>
                    </c:forEach>
                  </td>
                  <td>
                    <c:forEach var="theDate" items="${openDates}" begin="7" end="13">
                      <input type="radio" name="theDate" value="${theDate}"/>&nbsp;${theDate}<br/>
                    </c:forEach>
                  </td>
                  <td>
                    <input name="textDate" id="textDate" value="" onchange="clearRadioDates();" placeholder="MM/DD/YYYY"/>
                  </td>
                </tr>
              </table>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <!--input type="submit" value="Confirm Request"/-->
            &nbsp;
          </td>
        </tr>
      </table>
      <br/>
      <c:choose>
        <c:when test="${not empty bibRecord.srlfItems}">
          <table align="center" width="960" border="1" class="footer">
            <tr>
              <th width="25%">Request</th>
              <th width="75%">Item Description</th>
            </tr>
            <c:forEach var="srlf" items="${bibRecord.srlfItems}">
              <tr>
                <td width="25%" align="center">
                  <input type="checkbox" name="itemID" value="${srlf.itemID}"/>
                  <%--c:if test="${(not empty srlf.statusID) and (srlf.statusID ne 1)}">
                    <br/>
                    (${srlf.status}; There may be a delay in retrieval)
                  </c:if--%>
                </td>
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
          <table align="center" width="960" border="0" class="footer">
            <tr>
              <td>
                <input type="submit" value="Confirm Request"/>
              </td>
            </tr>
          </table>
        </c:otherwise>
      </c:choose>
      <br/>
    </form>
  </body>
</html>
    <!--h3>Viewing bib and holdings for ${param.bibID}</h3-->
    <!--ul>
      <li>title:&nbsp;${bibRecord.title}</li>
      <li>marc246 label:&nbsp;${bibRecord.marc246}</li>
      <li>pub dates:&nbsp;${bibRecord.pubDates}</li>
      <li>marc506 label:&nbsp;${bibRecord.marc506}</li>
      <li>author:&nbsp;${bibRecord.author}</li>
      <li>marc524 label:&nbsp;${bibRecord.marc524}</li>
      <li>marc590 label:&nbsp;${bibRecord.marc590}</li>
    </ul>
    <h3>YRL Holdings</h3>
    <ul>
      <c:forEach var="yrl" items="${bibRecord.yrlHoldings}">
        <li>${yrl.callNo}</li>
      </c:forEach>
    </ul>
    <h3>SRLF Holdings</h3>
    <ul>
      <c:forEach var="srlf" items="${bibRecord.srlfItems}">
        <li>
          <ul>
            <li>Item enumeration:&nbsp;${srlf.itemEnum}</li>
            <li>Chron:&nbsp;${srlf.chron}</li>
            <li>Copy:&nbsp;${srlf.copy}</li>
            <li>Barcode:&nbsp;${srlf.barcode}</li>
          </ul>
        </li>
      </c:forEach>
    </ul-->
