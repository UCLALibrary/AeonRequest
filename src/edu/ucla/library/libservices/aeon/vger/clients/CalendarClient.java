package edu.ucla.library.libservices.aeon.vger.clients;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.ucla.library.libservices.hours.beans.WeeklyLocationRoot;

import org.apache.log4j.Logger;

public class CalendarClient
{
  private static final Logger logger =
    Logger.getLogger( CalendarClient.class );
  private Client client;
  private String requetURL;
  private Invocation.Builder invocationBuilder;
  private Response response;
  private WebTarget webTarget;

  public CalendarClient()
  {
    super();
  }

  public void setRequetURL( String requetURL )
  {
    this.requetURL = requetURL;
  }

  private String getRequetURL()
  {
    return requetURL;
  }
  
  public WeeklyLocationRoot getDates()
  {
    WeeklyLocationRoot dates;
    
    dates = null;
    
    client = ClientBuilder.newClient();
    webTarget = client.target( getRequetURL() );
    invocationBuilder = webTarget.request( MediaType.APPLICATION_JSON );
    response = invocationBuilder.get();

    try
    {
      dates = response.readEntity( WeeklyLocationRoot.class );
    }
    catch (Exception e)
    {
      e.printStackTrace();
      logger.info( webTarget.getUri() );
      logger.error( e.getMessage() );
    }
    return dates;
  }
}
