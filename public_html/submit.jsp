<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" errorPage="errors.jsp"%> 
<%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="bibSource" class="edu.ucla.library.libservices.aeon.vger.generators.SubmitBibGenerator">
  <jsp:setProperty name="bibSource" property="bibID" param="bibID" />
  <jsp:setProperty name="bibSource" property="itemIDs" value="${paramValues.itemID}" />
  <jsp:setProperty name="bibSource" property="dbName" value='<%= application.getInitParameter("datasource.vger") %>' />
</jsp:useBean>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="shortcut icon" href="./images/favicon.ico" type="image/x-icon" />
    <link rel="icon" href="./images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="bootstrap-4.3.1-dist/css/bootstrap.css" />
    <link rel="stylesheet" href="css/main.css" />
    <title>Aeon UCLA Library - Selected Items</title>
    <script language="javascript" type="text/javascript">
      function submitForm()
      {
        document.forms["AeonRequest"].submit();
      }
    </script>
  </head>
  <body class="d-flex flex-column" onload="javascript:submitForm();">

    <main class="container my-5 flex-grow-1">
      <form
        id="AeonRequest"
        name="AeonRequest"
        target="_self"
        method="post"
        action="https://speccoll.library.ucla.edu/logon"
        class="mt-5"
      >
	   <c:set var="bibRecord" value="${bibSource.bibData}" />
	   <input name="AeonForm" value="EADRequest" type="hidden" />
	   <input value="${param.specReq}" name="SpecialRequest" type="hidden" />
	   <input value="${param.notes}" name="notes" type="hidden" />
	   <c:choose>
		<c:when test="${not empty param.theDate}">
			<input name="ScheduledDate" type="hidden" value="${param.theDate}" />
		</c:when>
		<c:otherwise>
			<input name="ScheduledDate" type="hidden" value="${param.textDate}" />
		</c:otherwise>
	   </c:choose>
	   <c:choose>
		<c:when test="${bibSource.isBio}">
			<input name="Site" type="hidden" value="BIOMED" />
		</c:when>
		<c:otherwise>
			<input name="Site" type="hidden" value="YRL" />
		</c:otherwise>
	   </c:choose>
	   <input type="hidden" name="ItemAuthor" value="${bibRecord.author}" />
	   <input type="hidden" name="ItemDate" value="${bibRecord.pubDates}" />
	   <input type="hidden" name="SubmitButton" value="Submit Request" />
	   <input type="hidden" name="WebRequestForm" value="DefaultRequest" />
	   <input type="hidden" name="reqType" value="${param.reqType}" />
	   <input type="hidden" name="Format" value="${param.Format}" />
	   <input type="hidden" name="ForPublication" value="${param.ForPublication}" />
	   <input type="hidden" name="ItemInfo3" value="${param.ItemInfo3}" />
           <c:choose>
             <c:when test="${param.reqType eq 2}">
               <input value="Copy" name="RequestType" id="RequestType" type="hidden" />
               <input type="hidden" name="SkipOrderEstimate" id="SkipOrderEstimate" value="Yes" />
             </c:when>
             <c:otherwise>
               <input value="Copy" name="RequestType" id="RequestType" type="hidden" disabled="true" />
               <input type="hidden" name="SkipOrderEstimate" id="SkipOrderEstimate" value="Yes" disabled="true" />
             </c:otherwise>
           </c:choose>
	   <c:choose>
			<c:when test="${not empty bibRecord.srlfItems}">
				<c:set var="index" value="-1" />
				<c:forEach var="srlf" items="${bibRecord.srlfItems}" begin="0" end="0">
					<input value="${srlf.location}" name="Location" type="hidden" />
				</c:forEach>
				<c:forEach var="srlf" items="${bibRecord.srlfItems}">
					<c:set var="index" value="${index + 1}" />
					<input type="hidden" name="Request" value="${index}" />
					<input value="${bibRecord.title}" name="ItemTitle_${index}" type="hidden" />
					<input value="${srlf.itemEnum}" name="ItemVolume_${index}" type="hidden" />
					<input value="${param.bibID}" name="ReferenceNumber_${index}" type="hidden" />
					<input value="${srlf.barcode}" name="ItemNumber_${index}" type="hidden" />
					<input value="${srlf.copy}" name="ItemIssue_${index}" type="hidden" />
					<input value="${srlf.status}" name="ItemCitation_${index}" type="hidden" />
					<input type="hidden" name="CallNumber_${index}" value="${srlf.callNo}" />
				</c:forEach>
			</c:when>
			<c:otherwise>
				<input type="hidden" name="Request" value="0" />
				<input value="${bibRecord.title}" name="ItemTitle_0" type="hidden" />
				<input value="" name="ItemVolume_0" type="hidden" />
				<input value="${param.bibID}" name="ReferenceNumber_0" type="hidden" />
				<input value="" name="ItemNumber_0" type="hidden" />
				<input value="" name="ItemIssue_0" type="hidden" />
				<c:forEach var="yrl" items="${bibRecord.yrlHoldings}">
					<c:if test="${not empty yrl.callNo}">
						<input type="hidden" name="CallNumber_0" value="${yrl.callNo}" />
					</c:if>
				</c:forEach>
			</c:otherwise>
	   </c:choose>

      </form>
    </main>

    <script src="js/toggle.js" type="text/javascript" language="JavaScript"></script>
    <script src="js/validateForm.js" type="text/javascript" language="JavaScript"></script>
  </body>
</html>
    <!--section class="site-navbar">
      <nav class="navbar navbar-light nav-primary">
        <a href="#">
          <div class="navbar-brand py-0">
            <img src="images/ucla-library-logo-wht.svg" class="" alt="UCLA Library logo" />

            <span class="site-title">Special Collections Request System</span>
          </div></a
        >
      </nav>
      <nav class="nav nav-secondary flex-column flex-md-row pl-3 py-2">
        <a class="nav-link" href="http://www.library.ucla.edu/special-collections" target="_blank" rel="noopener"
          >Special Collections Home</a
        >
        <a class="nav-link" href="https://catalog.library.ucla.edu/" target="_blank" rel="noopener"
          >UCLA Library Catalog</a
        >
        <a class="nav-link" href="http://www.oac.cdlib.org/" target="_blank" rel="noopener"
          >Online Archive of California (OAC)</a
        >
        <a
          class="nav-link"
          href="https://www.library.ucla.edu/special-collections/frequently-asked-questions-faq"
          target="_blank"
          rel="noopener"
          >FAQs</a
        >
      </nav>
    </section>
         <section class="req-info">
          <div class="table table-50-50">
            <div class="t-title text-center text-white py-1">Request Information</div>
            <div class="t-row">
              <div class="t-cell py-1 px-3 column-1">Author</div>
              <div class="t-cell py-1 px-3 column-2">
                ${bibRecord.author}
              </div>
            </div>
            <div class="t-row">
              <div class="t-cell py-1 px-3 column-1">Title</div>
              <div class="t-cell py-1 px-3 column-2">
                ${bibRecord.title}
              </div>
            </div>
            <div class="t-row">
              <div class="t-cell py-1 px-3 column-1">Call Number(s)</div>
              <div class="t-cell py-1 px-3 column-2">
                <ul>
                  <c:forEach var="yrl" items="${bibRecord.yrlHoldings}">
                    <li>
                      ${yrl.callNo}
                    </li>
                  </c:forEach>
                </ul>
              </div>
            </div>
            <div class="t-row">
              <div class="t-cell py-1 px-3 column-1">Request Type</div>
              <div class="t-cell py-1 px-3 column-2">
                <c:choose>
                  <c:when test="${param.reqType eq 1}">
                    On-Site Review
                  </c:when>
                  <c:otherwise>
                    Duplication: ${param.Format}
                  </c:otherwise>
                </c:choose>
              </div>
            </div>
            <div class="t-row">
              <div class="t-cell py-1 px-3 column-1">Requested Date</div>
              <div class="t-cell py-1 px-3 column-2">
                <c:choose>
                  <c:when test="${not empty param.theDate}">
                    ${param.theDate}
                  </c:when>
                  <c:when test="${not empty param.textDate}">
                    ${param.textDate}
                  </c:when>
                  <c:otherwise>
                    Date not required
                  </c:otherwise>
                </c:choose>
              </div>
            </div>
          </div>
          <hr />
        </section>
             <section class="req-materials">
              <div class="table table-100">
                <div class="t-heading text-center">
                  <div class="t-cell py-2">Item Description</div>
                </div>
                  <div class="t-row">
                    <div class="t-cell py-1 px-3 column-2">
                      <label for="${srlf.itemEnum}">
                        <c:if test="${not empty srlf.itemEnum}">
                          ${srlf.itemEnum} &nbsp;|&nbsp;
                        </c:if>
                        <c:if test="${not empty srlf.chron}">
                          ${srlf.chron} &nbsp;|&nbsp;
                        </c:if>
                        <c:if test="${(not empty srlf.copy) and (srlf.copy ne 0)}">
                          Copy&nbsp; ${srlf.copy} &nbsp;|&nbsp;
                        </c:if>
                        <c:if test="${not empty srlf.note}">
                          ${srlf.note} &nbsp;|&nbsp;
                        </c:if>
                        <c:if test="${not empty srlf.oacDetails}">
                          ${srlf.oacDetails}
                        </c:if>
                        <c:if test="${not empty srlf.pickupLocale}">
                          &nbsp;|&nbsp; ${srlf.pickupLocale}
                        </c:if>
                      </label>

                    </div>
                  </div>
              </div>
              <hr />
            </section>
            <input type="submit" value="Submit Request" />
    <h4 class="h4-blue mb-4">Items Selected for Processing</h4>
            <br />
            <input type="submit" value="Submit Request" />

    <footer class="p-4">
      <small class="text-white">Copyright &copy; 2019 UC Regents. All Rights Reserved.</small>
    </footer-->

		<!--https://speccoll.library.ucla.edu/aeon/aeon.dll"-->
        <!--form method="post" action="finalSubmit.jsp"-->
        <!-- Request Information -->
