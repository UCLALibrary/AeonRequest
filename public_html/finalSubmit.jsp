<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252" errorPage="errors.jsp"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ page import="java.util.*" %>

<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="shortcut icon" href="./images/favicon.ico" type="image/x-icon" />
    <link rel="icon" href="./images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="bootstrap-4.3.1-dist/css/bootstrap.css" />
    <link rel="stylesheet" href="css/main.css" />
    <title>Aeon UCLA Library - Logout</title>
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

    <main class="container my-5 flex-grow-1">
      <section class="pg-welcome shadow-sm rounded p-md-5 p-4">
        <p>You have successfully logged out of the Aeon system.</p>

        <p>
          However, you may have active sessions at other sites you've visited while signed in. If you are using a public
          or shared workstation, <b>shut down your browsers to make sure you have signed out of all applications</b>
        </p>
      </section>
    </main>

    <footer class="p-4">
      <small class="text-white">Copyright &copy; 2019 UC Regents. All Rights Reserved.</small>
    </footer>

    <script src="js/toggle.js" type="text/javascript" language="JavaScript"></script>
  </body>
</html>
