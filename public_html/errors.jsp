<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=utf-8" isErrorPage="true"
         import="java.io.CharArrayWriter, java.io.PrintWriter"%>

<html lang="EN">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="shortcut icon" href="./images/favicon.ico" type="image/x-icon" />
    <link rel="icon" href="./images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="bootstrap-4.3.1-dist/css/bootstrap.css" />
    <link rel="stylesheet" href="css/main.css" />
    <title>Aeon UCLA Library - An Error Occurred</title>
  </head>

  <body class="d-flex flex-column">
    <section class="site-navbar">
      <nav class="navbar navbar-light nav-primary">
        <a href="https://speccoll.library.ucla.edu">
          <div class="navbar-brand py-0">
            <img src="images/ucla-library-logo-wht.svg" class="" alt="UCLA Library logo" />
            <span class="site-title">Special Collections Request System</span>
          </div>
        </a>
      </nav>

      <nav class="nav nav-secondary flex-column flex-md-row pl-3 py-2">
        <a class="nav-link" href="http://www.library.ucla.edu/special-collections" target="_blank"
          >Special Collections Home</a
        >
        <a class="nav-link" href="https://catalog.library.ucla.edu/" target="_blank">UCLA Library Catalog</a>
        <a class="nav-link" href="http://www.oac.cdlib.org/" target="_blank">Online Archive of California (OAC)</a>
        <a
          class="nav-link"
          href="https://www.library.ucla.edu/special-collections/frequently-asked-questions-faq"
          target="_blank"
          >FAQs</a
        >
      </nav>
    </section>

    <main class="container flex-grow-1 my-5">
      <section class="pg-welcome shadow-sm rounded p-md-5 p-4">
        <p class="text-danger"><b>We're sorry: An error occured.</b></p>

        <p>Your request for material could not be processed through the automated system.</p>
            
        <p>To request this material, please contact Library Special Collections for assistance:
          <ul>
            <li class="py-2">Paging and Reference Email: <a href="mailto:spec-coll@library.ucla.edu">spec-coll@library.ucla.edu</a></li>
            <li class="py-2">Duplication Services E-mail: <a href="mailto:speccoll-duplication@library.ucla.edu">speccoll-duplication@library.ucla.edu</a></li>
            <li class="py-2">Reference Desk phone (usually 10 a.m. - 5 p.m. PST Monday through Saturday): 310.825.4988</li>
          </ul>
        </p>

        <p>Manual requests can also be made by clicking on the “Request an Item” link under the “New Request” sidebar option of the UCLA Library Special Collections Request System home page.</p>

        <!--  -->
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
      </section>

      <!--  -->
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
    </main>
  
    <footer class="p-4">
      <small class="text-white">Copyright &copy; 2019 UC Regents. All Rights Reserved.</small>
    </footer>

  <script src="js/toggle.js" type="text/javascript" language="JavaScript"></script>
  </body>
</html>
