<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=utf-8" isErrorPage="true"
         import="java.io.CharArrayWriter, java.io.PrintWriter"%>
<html lang="EN">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="shortcut icon" href="./images/favicon.ico" type="image/x-icon">
    <link rel="icon" href="./images/favicon.ico" type="image/x-icon">
    <link href="http://www.library.ucla.edu/css/wht.css" rel="stylesheet" type="text/css">
    <title>Aeon UCLA Library - An Error Occurred</title>
  </head>
  <body bgcolor="#FFFFFF" topmargin="0" marginheight="0" marginwidth="0" leftmargin="0" width="960">
    <table width="960" cellpadding="0" cellspacing="0" align="center">
      <tr>
        <td width="165" bgcolor="#3283BE" align="center">
          <img alt="UCLA Library logo" src="http://www.library.ucla.edu/sites/all/themes/uclalib_omega/logo.png">
        </td>
        <td bgcolor="#3283BE" align="center">
          <h1><font color="#ffffff" class="body"><b>Library Special Collections</b></font></h1><br/>
        </td>
        <td  width="155" bgcolor="#3283BE">
        </td>
      </tr>
      </tr>
    </table>
    <table align="center" width="960" border="0" class="footer">
      <tr>
        <td>We're sorry: An error occured.</td>
      </tr>
      <tr>
        <td colspan="2">
          Your request for material from the UCLA Library Special Collections 
          (LSC) could not be processed through the automated system. To request 
          this material, please send a message or call LSC for assistance:
        </td>
      </tr>
      <tr>
        <td>Paging and Reference Email: </td>
        <td>
          <a href="mailto:spec-coll@library.ucla.edu">spec-coll@library.ucla.edu</a>
        </td>
      </tr>
      <tr>
        <td>Duplication Services E-mail:</td>
        <td>
          <a href="mailto:speccoll-duplication@library.ucla.edu">speccoll-duplication@library.ucla.edu</a>
        </td>
      </tr>
      <tr>
        <td>Reference Desk phone (usually 10-5 Monday through Saturday):</td>
        <td>310.825.4988</td>
      </tr>
      <tr>
        <td colspan="2">
          Manual requests can also be made by clicking on the “Request an Item” 
          link under the “New Request” sidebar option of the UCLA Library Special 
          Collections Request System home page.
        </td>
      </tr>
      <tr>
        <td colspan="2">
          Click <a href="http://www.oac.cdlib.org/">here</a> to search the 
          Online Archive of California.
        </td>
      </tr>
      <tr>
        <td colspan="2">
          Click <a href="http://catalog.library.ucla.edu/">here</a> to search 
          the UCLA Library Catalog.
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <%
            if (exception != null) 
            { 
              out.println(exception.getMessage());
              //application.log( exception.getMessage(), exception );
              CharArrayWriter charArrayWriter = new CharArrayWriter(); 
              PrintWriter printWriter = new PrintWriter(charArrayWriter, true); 
              exception.printStackTrace(printWriter); 
              out.println(charArrayWriter.toString()); 
            }
            else
            {
              out.println( "Sorry, no error message available" );
            }
          %>
        </td>
      </tr>
    </table>
    <%--pre>
    <
      if (exception != null) 
      { 
        out.println(exception.getMessage());
        CharArrayWriter charArrayWriter = new CharArrayWriter(); 
        PrintWriter printWriter = new PrintWriter(charArrayWriter, true); 
        exception.printStackTrace(printWriter); 
        out.println(charArrayWriter.toString()); 
      } 
    >
    </pre--%>
    </body>
</html>
