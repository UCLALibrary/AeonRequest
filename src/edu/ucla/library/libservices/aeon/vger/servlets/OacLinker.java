package edu.ucla.library.libservices.aeon.vger.servlets;

//import edu.ucla.library.libservices.aeon.vger.db.source.DataSourceFactory;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import javax.sql.DataSource;

//import org.springframework.jdbc.core.JdbcTemplate;

public class OacLinker
  extends HttpServlet
{
  @SuppressWarnings("compatibility:-3030590753088951300")
  private static final long serialVersionUID = -8471856359218498556L;
  /*private static final String COUNT_QUERY =
    "SELECT count(record_id) FROM ucladb.elink_index WHERE link like '%' || ? || '%'";
  private static final String ID_QUERY =
    "SELECT record_id FROM ucladb.elink_index WHERE link like '%' || ? || '%'";*/

  public void init( ServletConfig config )
    throws ServletException
  {
    super.init( config );
  }

  public void doGet( HttpServletRequest request,
                     HttpServletResponse response )
    throws ServletException, IOException
  {
    response.sendRedirect( "https://ucla.libanswers.com/lsc/faq/383029" );
    /*String arkID;

    arkID = null;
    try
    {
      arkID = request.getParameter( "ark" );
    }
    catch ( Exception e )
    {
      e.printStackTrace();
    }
    if ( arkID != null )
    {
      DataSource ds;
      int count;
      ds = DataSourceFactory.createDataSource( this.getServletContext().getInitParameter( "datasource.vger" ) );
      //ds = DataSourceFactory.createVgerSource();
      count = new JdbcTemplate( ds ).queryForInt( COUNT_QUERY, new Object[]
            { arkID } );
      
      if ( count == 0 )
      {
        Exception e;
        e = new Exception("No match for OAC ARK ID");
        request.setAttribute( "Exception", e );
        response.sendRedirect( "errors.jsp" );
        //response.sendError( response.SC_NOT_FOUND,"No match for OAC ARK ID" );
      }
      else if ( count > 1 )
      {
        Exception e;
        e = new Exception("More than one match for OAC ARK ID");
        request.setAttribute( "Exception", e );
        response.sendRedirect( "errors.jsp" );
        //response.sendError( response.SC_NOT_FOUND, "More than one match for OAC ARK ID" );
      }
      else
      {
        int bibID;
        
        bibID = new JdbcTemplate( ds ).queryForInt( ID_QUERY, new Object[]
            { arkID } );
        
        response.sendRedirect( "index.jsp?bibID=" + bibID );
      }
    }
    else
      response.sendError( response.SC_BAD_REQUEST,
                          "Request missing OAC ARK ID" );*/

  }
}
