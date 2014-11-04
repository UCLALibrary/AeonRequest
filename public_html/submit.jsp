<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
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
      function dologout()
      {
        document.AeonRequest.submit();
      }
    </script>
  </head>
  <body onLoad="javascript:dologout()">
    <p>Seeing what comes out here...</p>
    <form id="AeonRequest" name="AeonRequest" target="_self" method="post" action="https://speccoll.library.ucla.edu/aeon/aeon.dll">
      <input name="AeonForm" value="EADRequest" type="hidden">
      <c:set var="bibRecord" value="${bibSource.bibData}"/>
      <c:if test="${not empty bibRecord.srlfItems}">
        <c:set var="index" value="-1"/>
        <c:forEach var="srlf" items="${bibRecord.srlfItems}">
          <c:set var="index" value="${index + 1}"/>
          <input value="${bibRecord.title}" name="ItemTitle_${index}" type="hidden">
          <input value="${srlf.itemEnum}" name="SubLocation_${index}" type="hidden">
          <input value="${param.bibID}" name="ReferenceNumber_${index}" type="hidden">
          <input value="${srlf.itemID}" name="ItemNumber_${index}" type="hidden">
        </c:forEach>
      </c:if>
    </form>
  </body>
</html>
