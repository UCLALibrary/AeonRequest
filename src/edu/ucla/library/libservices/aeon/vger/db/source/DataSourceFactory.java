package edu.ucla.library.libservices.aeon.vger.db.source;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceFactory
{
  public DataSourceFactory()
  {
    super();
  }

  public static DataSource createDataSource( String name )
  {
    InitialContext context;
    DataSource connection;

    try
    {
      context = new InitialContext();
      connection = ( DataSource ) context.lookup( name );
    }
    catch ( NamingException e )
    {
      e.printStackTrace();
      connection = null;
    }

    return connection;
  }

  public static DriverManagerDataSource createHoursSource()
  {
    DriverManagerDataSource ds;

    //code here for setting connection; hidden in repo version

    return ds;
  }

  public static DriverManagerDataSource createVgerSource()
  {
    DriverManagerDataSource ds;

    //code here for setting connection; hidden in repo version

    return ds;
  }
}
