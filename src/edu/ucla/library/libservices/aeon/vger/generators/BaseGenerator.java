package edu.ucla.library.libservices.aeon.vger.generators;

import edu.ucla.library.libservices.aeon.vger.db.source.DataSourceFactory;

import javax.sql.DataSource;

public abstract class BaseGenerator
{
  protected DataSource ds;
  protected String dbName;
  protected String bibID;

  public BaseGenerator()
  {
    super();
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  public String getDbName()
  {
    return dbName;
  }

  public void setBibID( String bibID )
  {
    this.bibID = bibID;
  }

  public String getBibID()
  {
    return bibID;
  }

  protected void makeConnection()
  {
    ds = DataSourceFactory.createDataSource( getDbName() );
    //ds = DataSourceFactory.createVgerSource();
  }
}
