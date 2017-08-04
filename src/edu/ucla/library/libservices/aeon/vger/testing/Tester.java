package edu.ucla.library.libservices.aeon.vger.testing;

import edu.ucla.library.libservices.aeon.submission.builder.FileBuilder;
import edu.ucla.library.libservices.aeon.vger.beans.VgerBibData;
import edu.ucla.library.libservices.aeon.vger.beans.VgerHoldingData;
import edu.ucla.library.libservices.aeon.vger.beans.VgerItemData;
import edu.ucla.library.libservices.aeon.vger.clients.CalendarClient;
import edu.ucla.library.libservices.aeon.vger.generators.SubmitBibGenerator;
import edu.ucla.library.libservices.aeon.vger.generators.VgerBibDataGenerator;

import edu.ucla.library.libservices.aeon.vger.utility.AvailableDates;

import edu.ucla.library.libservices.aeon.vger.utility.CalendarExtractor;
import edu.ucla.library.libservices.hours.beans.DayOfWeek;
import edu.ucla.library.libservices.hours.beans.WeeklyLocationRoot;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Tester
{
  //private static final DateFormat DATE_OUTPUT = new SimpleDateFormat( "MM/dd/yyyy" );
  private static final int TWEO_MONTHS = 8;
  private static final int YRL = 6135;
  private static final String BASE_URL = "https://webservices.library.ucla.edu/calendar/hours/weekly/";
  private static final DateTimeFormatter DATE_OUTPUT = DateTimeFormatter.ofPattern( "MM/dd/yyyy" );
  
  public Tester()
  {
    super();
  }
  
  private static String getDate( DayOfWeek theDay )
  {
    return null;
  }
  
  public static void main( String[] args )
  {
    LocalDate firstDate;
    int dayOfWeek;

    firstDate = LocalDate.now();
    dayOfWeek = firstDate.getDayOfWeek().getValue();

    switch ( dayOfWeek )
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
    System.out.println( "first date = " + firstDate );
    
    CalendarClient client;
    List<String> openDates;
    String[] availables;
    
    String unitURL;

    client = new CalendarClient();
    unitURL =
      BASE_URL.concat( String.valueOf( YRL ) ).concat( "/weeks/" ).concat( String.valueOf( TWEO_MONTHS ) );

    client.setRequetURL( unitURL );
    openDates = CalendarExtractor.getOpenDates( client.getDates() );

    int addedIndex;
    int index;
    DateTimeFormatter justDate;

    addedIndex = 0;
    justDate = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
    availables = new String[ 14 ];
    
    for ( index = 0; index < openDates.size() && addedIndex < 14; index++ )
    {
     if ( !LocalDate.parse( openDates.get( index ), justDate ).isBefore( firstDate ) )
     {
       availables[ addedIndex ] = DATE_OUTPUT.format( LocalDate.parse( openDates.get( index ), justDate ) );
       addedIndex++;
     }
    }
    
    for ( String anAvailable : availables )
    {
      System.out.println( anAvailable );
    }
    
/*
 * mon = 1
 * tue = 2
 * wed = 3
 * thu = 4
 * fri = 5
 * sat = 6
 * sun = 7
 */
    /*
    CalendarClient client;
    String yrlURL;
    WeeklyLocationRoot dates;

    client = new CalendarClient();
    yrlURL = BASE_URL.concat( String.valueOf( YRL ) ).concat( "/weeks/" ).concat( String.valueOf( TWEO_MONTHS ) );
    client.setRequetURL( yrlURL );
    System.out.println( yrlURL );
    dates = client.getDates();
    System.out.println( "dates contains " + dates.getLocations().size() + " locations; first one named " +
                        dates.getLocations().get( 0 ).getName() );

    AvailableDates dateGetter;

    dateGetter = new AvailableDates();
    //dateGetter.setDbName( "" );
    dateGetter.setUnitID( String.valueOf( YRL ) );

    availables = dateGetter.getAvailables();
    for ( String theDate : availables )
      System.out.println( "old system: " + theDate );

    SubmitBibGenerator generator;
    VgerBibData bibRecord;

    generator = new SubmitBibGenerator();
    generator.setBibID( "5006581" );
    generator.setDbName( "" );
    generator.setItemIDs( new String[]{ "8118105","8118106","8118108" } );
    bibRecord = generator.getBibData();
    System.out.println( "isBio = " + generator.isIsBio() );
    AvailableDates dateGetter;
    String[] availables;

    dateGetter = new AvailableDates();
    dateGetter.setDbName( "" );
    dateGetter.setUnitID( "23" );

    availables = dateGetter.getAvailables();

    for ( int j = 0; j < 14; j++ )
      System.out.println( availables[j] );

    int dayOfWeek;
    Calendar calendar;
    String[] availables;

    calendar = new GregorianCalendar();
    dayOfWeek = calendar.get( Calendar.DAY_OF_WEEK );
    availables = new String[14];

    System.out.println( "Original DAY_OF_WEEK: " + DATE_OUTPUT.format( calendar.getTime() ) );
                        //calendar.getDisplayName( Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale( "en", "US" ) ) );
    switch ( dayOfWeek )
    {
      case 1 :
      case 2 :
      case 3 :
      case 4 :
        calendar.add( Calendar.DAY_OF_WEEK, 2 );
        break;
      case 5 :
      case 6 :
        calendar.add( Calendar.DAY_OF_WEEK, 4 );
        break;
      case 7 :
        calendar.add( Calendar.DAY_OF_WEEK, 3 );
        break;
    }
    System.out.println( "Modified DAY_OF_WEEK: " + DATE_OUTPUT.format( calendar.getTime() ) );
                        //calendar.getDisplayName( Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale( "en", "US" ) ) );
    availables[0] = DATE_OUTPUT.format( calendar.getTime() );
    for ( int i = 1; i < 14; i++ )
    {
      calendar.add( Calendar.DAY_OF_WEEK, 1 );
      availables[i] = DATE_OUTPUT.format( calendar.getTime() );
    }
    for ( int j = 0; j < 14; j++ )
      System.out.println( availables[j] );

 * determine first day by switch rule above
 * determine if unit is open that day
 * if so, loop through to find next 13 days open
 * else fine next open day, then loop through to find next 13 open days
 */
    
    /*FileBuilder builder;
    builder = new FileBuilder();
    builder.setBibID( "925435" );
    builder.setDbName( "" );
    builder.setItemIDs( new String[]{"9757095","9593274"} );

    builder.makeFile(null);
    VgerBibDataGenerator generator;
    VgerBibData bibData;

    generator = new VgerBibDataGenerator();
    generator.setDbName( "" );
    generator.setBibID( "925435" );
    generator.setItemIDs( new String[]{"9757095","9593274"} );

    long start = System.currentTimeMillis();
    bibData = generator.getXmlBibData();
    long end = System.currentTimeMillis();
    System.out.println( "run time = " +
                        ( ( ( end - start ) / 1000 ) ) / 60 );

    System.out.println( "title = " + bibData.getTitle() );
    System.out.println( "marc246 = " + bibData.getMarc246() );
    System.out.println( "pub date = " + bibData.getPubDates() );
    System.out.println( "marc506 = " + bibData.getMarc506() );
    System.out.println( "author = " + bibData.getAuthor() );
    System.out.println( "marc524 = " + bibData.getMarc524() );
    System.out.println( "marc590 = " + bibData.getMarc590() );
    System.out.println( "edition = " + bibData.getEdition() );
    System.out.println( "publisher = " + bibData.getPublisher() );
    System.out.println( "pub place = " + bibData.getPubPlace() );
    System.out.println( "physical description = " +
                        bibData.getPhysDesc() );

    for ( VgerHoldingData holding: bibData.getYrlHoldings() )
    {
      System.out.println( holding.getCallNo() );
    }

    for ( VgerItemData item: bibData.getSrlfItems() )
    {
      System.out.println( item.getItemID() );
      System.out.println( item.getBarcode() );
      System.out.println( item.getChron() );
      System.out.println( item.getCopy() );
      System.out.println( item.getItemEnum() );
      System.out.println( item.getOacDetails() );
      System.out.println( item.getCallNo() );
      System.out.println( item.getLocation() );
      System.out.println( item.getNote() );
      System.out.println( "" );
    }*/
  }
}
