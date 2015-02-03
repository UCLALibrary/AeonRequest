package edu.ucla.library.libservices.aeon.vger.utility;

import edu.ucla.library.libservices.aeon.vger.db.source.DataSourceFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.GregorianCalendar;
import java.util.Calendar;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class AvailableDates
{
  private static final DateFormat DATE_OUTPUT =
    new SimpleDateFormat( "MM/dd/yyyy" );
  private static final String QUERY =
    "select Library_Web.dbo.is_unit_open(?, ?)"; //date, unit ID

  private Calendar calendar;
  private String[] availables;
  private String dbName;
  private DataSource ds;
  private String unitID;

  public AvailableDates()
  {
    super();
    calendar = new GregorianCalendar();
    availables = new String[ 14 ];
  }

  public String[] getAvailables()
  {
    makeConnection();
    getFirstDate();
    getRemainingDates();
    return availables;
  }

  private void makeConnection()
  {
    ds = DataSourceFactory.createDataSource( getDbName() );
    //ds = DataSourceFactory.createHoursSource();
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  private String getDbName()
  {
    return dbName;
  }

  private void getFirstDate()
  {
    int dayOfWeek;
    dayOfWeek = calendar.get( Calendar.DAY_OF_WEEK );

    switch ( dayOfWeek )
    {
      case 1:
      case 2:
      case 3:
      case 4:
        calendar.add( Calendar.DAY_OF_WEEK, 2 );
        break;
      case 5:
      case 6:
        calendar.add( Calendar.DAY_OF_WEEK, 4 );
        break;
      case 7:
        calendar.add( Calendar.DAY_OF_WEEK, 3 );
        break;
    }
    while ( !isUnitOpen() )
      calendar.add( Calendar.DAY_OF_WEEK, 1 );

    availables[ 0 ] = DATE_OUTPUT.format( calendar.getTime() );
  }

  private void getRemainingDates()
  {
    for ( int i = 1; i < 14; i++ )
    {
      calendar.add( Calendar.DAY_OF_WEEK, 1 );
      while ( !isUnitOpen() )
        calendar.add( Calendar.DAY_OF_WEEK, 1 );
      availables[ i ] = DATE_OUTPUT.format( calendar.getTime() );
    }
  }

  private boolean isUnitOpen()
  {
    return (  new JdbcTemplate( ds ).queryForInt( QUERY, new Object[]
          { calendar.getTime(), getUnitID() } ) != 0 );
  }

  public void setUnitID( String unitID )
  {
    this.unitID = unitID;
  }

  private String getUnitID()
  {
    return unitID;
  }
}
