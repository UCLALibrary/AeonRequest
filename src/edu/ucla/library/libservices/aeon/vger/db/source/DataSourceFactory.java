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
    
    ds  = new DriverManagerDataSource();
    ds.setDriverClassName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
    ds.setUrl( "sql_server_url" );
    ds.setUsername( "sql_server_user" );
    ds.setPassword( "sql_server__pwd" );
    
    return ds;
  }

  public static DriverManagerDataSource createVgerSource()
  {
    DriverManagerDataSource ds;

    ds = new DriverManagerDataSource();
    ds.setDriverClassName( "oracle.jdbc.OracleDriver" );
    ds.setUrl( "oracle_url" );
    ds.setUsername( "oracle_user" );
    ds.setPassword( "oracle_pwd" );

    return ds;
  }
}
