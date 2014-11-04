package edu.ucla.library.libservices.aeon.submission.web;

import edu.ucla.library.libservices.aeon.submission.builder.FileBuilder;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class FileSubmitServlet
  extends HttpServlet
{
  private static final String CONTENT_TYPE = "text/xml; charset=UTF-8";
  //private static final String DOC_TYPE = null;

  public void init( ServletConfig config )
    throws ServletException
  {
    super.init(config);
  }

  /**Process the HTTP doGet request.
   */
  public void doGet( HttpServletRequest request,
                     HttpServletResponse response )
    throws ServletException, IOException
  {
    doPost( request, response );
  }

  /**Process the HTTP doPost request.
   */
  public void doPost( HttpServletRequest request,
                      HttpServletResponse response )
    throws ServletException, IOException
  {
    FileBuilder builder;
    PrintWriter out;

    response.setContentType(CONTENT_TYPE);
    out = response.getWriter();
    builder = new FileBuilder();
    builder.setBibID( request.getParameter( "bibID" ) );
    builder.setDbName( this.getServletContext().getInitParameter( "datasource.vger" ) );
    builder.setItemIDs( request.getParameterValues( "itemID" ) );
    
    builder.makeFile(out);
    out.close();
  }
}
