package edu.ucla.library.libservices.aeon.vger.servlets;

import edu.ucla.library.libservices.aeon.vger.beans.ClarkData;
import edu.ucla.library.libservices.aeon.vger.generators.ClarkDataGenerator;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import java.net.URLEncoder;

public class ClarkLinker
  extends HttpServlet
{
  @SuppressWarnings( "compatibility:-6391279242917955797" )
  private static final long serialVersionUID = -3710699302679718713L;
  private static final String BASE_URL =
    "https://aeon.clarklibrary.ucla.edu/logon?Action=10&Form=20&Value=GenericRequestMonograph";

  private static final String PERMA_LINK = "https://catalog.library.ucla.edu/vwebv/holdingsInfo?bibId=";

  public void init( ServletConfig config )
    throws ServletException
  {
    super.init( config );
  }

  /**Process the HTTP doGet request.
   */
  public void doGet( HttpServletRequest request, HttpServletResponse response )
    throws ServletException, IOException
  {
    ClarkData data;
    ClarkDataGenerator generator;
    StringBuffer redirect;

    generator = new ClarkDataGenerator();
    generator.setBibID( request.getParameter( "bibID" ) );
    generator.setMdhdID( request.getParameter( "mfhdID" ) );
    generator.setDbName( getServletContext().getInitParameter( "datasource.vger" ) );

    data = generator.getData();

    redirect = new StringBuffer( BASE_URL );
    
    redirect.append( "&ReferenceNumber=" ).append( request.getParameter( "bibID" ) );

    if ( data.getItemTitle() != null && data.getItemTitle().length() > 0 )
    {
      redirect.append( "&ItemTitle=" ).append( URLEncoder.encode( data.getItemTitle(), "UTF-8" ) );
    }
    else
    {
      redirect.append( "&ItemTitle=unknown" );
    }
    
    if ( data.getItemAuthor() != null && data.getItemAuthor().length() > 0 )
    {
      redirect.append( "&ItemAuthor=" ).append( URLEncoder.encode( data.getItemAuthor(), "UTF-8" ) );
    }
    
    if ( data.getCallNumber() != null && data.getCallNumber().length() > 0 )
    {
      redirect.append( "&CallNumber=" ).append( URLEncoder.encode( data.getCallNumber(), "UTF-8" ) );
    }
    else
    {
      redirect.append( "&CallNumber=unknown" );
    }
    
    if ( data.getItemPlace() != null && data.getItemPlace().length() > 0 )
    {
      redirect.append( "&ItemPlace=" ).append( URLEncoder.encode( data.getItemPlace(), "UTF-8" ) );
    }
    
    if ( data.getItemPublisher() != null && data.getItemPublisher().length() > 0 )
    {
      redirect.append( "&ItemPublisher=" ).append( URLEncoder.encode( data.getItemPublisher(), "UTF-8" ) );
    }
    
    if ( data.getItemDate() != null && data.getItemDate().length() > 0 )
    {
      redirect.append( "&ItemDate=" ).append( URLEncoder.encode( data.getItemDate(), "UTF-8" ) );
    }
    
    if ( data.getItemEdition() != null && data.getItemEdition().length() > 0 )
    {
      redirect.append( "&ItemEdition=" ).append( URLEncoder.encode( data.getItemEdition(), "UTF-8" ) );
    }
    
    if ( data.getItemVolume() != null && data.getItemVolume().length() > 0 )
    {
      redirect.append( "&ItemVolume=" ).append( URLEncoder.encode( data.getItemVolume(), "UTF-8" ) );
    }
    
    if ( data.getLocation() != null && data.getLocation().length() > 0 )
    {
      redirect.append( "&Location=" ).append( URLEncoder.encode( data.getLocation(), "UTF-8" ) );
    }
    
    redirect.append( "&ItemInfo3=" ).append( URLEncoder.encode( PERMA_LINK.concat( request.getParameter( "bibID" ) ), "UTF-8" ) );
    //ItemInfo3
    //135283
    /*if ( data.getItemInfo1() != null && data.getItemInfo1().length() > 0 )
    {
      redirect.append( "&ItemInfo1=" ).append( URLEncoder.encode( data.getItemInfo1(), "UTF-8" ) );
    }*/
    
    response.sendRedirect(redirect.toString());
  }
}
