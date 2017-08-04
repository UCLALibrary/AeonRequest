package edu.ucla.library.libservices.aeon.vger.utility;

import edu.ucla.library.libservices.aeon.vger.clients.CalendarClient;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.util.List;

public class AvailableDates
{
  private static final DateTimeFormatter DATE_OUTPUT = DateTimeFormatter.ofPattern( "MM/dd/yyyy" );
  private static final DateTimeFormatter DATE_CONVERT = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
  private static final String BASE_URL = "https://webservices.library.ucla.edu/calendar/hours/weekly/";
  private static final int TWEO_MONTHS = 8;

  private LocalDate firstDate;
  private CalendarClient client;
  private List<String> openDates;
  private String[] availables;
  private String unitID;

  public AvailableDates()
  {
    super();
    firstDate = LocalDate.now();
    availables = new String[ 14 ];
  }

  public String[] getAvailables()
  {
    findFirstDate();
    findOpenDates();
    findAvailableDates();
    return availables;
  }

  private void findFirstDate()
  {
    switch ( firstDate.getDayOfWeek().getValue() )
    {
      case 7:
      case 1:
      case 2:
      case 3:
        firstDate = firstDate.plusDays( 2 );
        break;
      case 4:
      case 5:
        firstDate = firstDate.plusDays( 4 );
        break;
      case 6:
        firstDate = firstDate.plusDays( 3 );
        break;
    }
  }

  private void findOpenDates()
  {
    String unitURL;

    client = new CalendarClient();
    unitURL =
      BASE_URL.concat( String.valueOf( getUnitID() ) ).concat( "/weeks/" ).concat( String.valueOf( TWEO_MONTHS ) );

    client.setRequetURL( unitURL );
    openDates = CalendarExtractor.getOpenDates( client.getDates() );
  }

  private void findAvailableDates()
  {
    int addedIndex;
    int index;

    addedIndex = 0;

    for ( index = 0; index < openDates.size() && addedIndex < 14; index++ )
    {
      if ( !LocalDate.parse( openDates.get( index ), DATE_CONVERT ).isBefore( firstDate ) )
      {
        availables[ addedIndex ] = DATE_OUTPUT.format( LocalDate.parse( openDates.get( index ), DATE_CONVERT ) );
        addedIndex++;
      }
    }
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
/*
 * determine possible first day
 * retrieve 2 months of calendar
 * extract all open days from 2 months list
 * loop through & save open dates later than possible first day
 * populate output array
 */

//import edu.ucla.library.libservices.aeon.vger.db.source.DataSourceFactory;
//import javax.sql.DataSource;

//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.JdbcTemplate;
  //private String dbName;
  //private DataSource ds;
  //makeConnection();
  /*private void makeConnection()
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
  }*/

  //while ( !isUnitOpen() )
    //calendar.add( Calendar.DAY_OF_WEEK, 1 );

  //availables[ 0 ] = DATE_OUTPUT.format( calendar.getTime() );
  /*for ( int i = 1; i < 14; i++ )
  {
    calendar.add( Calendar.DAY_OF_WEEK, 1 );
    while ( !isUnitOpen() )
      calendar.add( Calendar.DAY_OF_WEEK, 1 );
    availables[ i ] = DATE_OUTPUT.format( calendar.getTime() );
  }*/
  /*private boolean isUnitOpen()
  {
    return (  new JdbcTemplate( ds ).queryForInt( QUERY, new Object[]
          { calendar.getTime(), getUnitID() } ) != 0 );
  }*/

  //private static final String QUERY = "select Library_Web.dbo.is_unit_open(?, ?)"; //date, unit ID
