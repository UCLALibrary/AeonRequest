package edu.ucla.library.libservices.aeon.vger.utility;

import edu.ucla.library.libservices.hours.beans.DayOfWeek;
import edu.ucla.library.libservices.hours.beans.Week;
import edu.ucla.library.libservices.hours.beans.WeeklyLocation;
import edu.ucla.library.libservices.hours.beans.WeeklyLocationRoot;

import java.util.ArrayList;
import java.util.List;

public class CalendarExtractor
{
  public CalendarExtractor()
  {
    super();
  }
  
  public static List<String> getOpenDates(WeeklyLocationRoot dates)
  {
    List<String> openDates;
    openDates = new ArrayList<String>( 14 );

    for ( WeeklyLocation theLoc : dates.getLocations() )
    {
      for ( Week theWeek : theLoc.getWeeks() )
      {
        if ( getDate( theWeek.getSun() ) != null )
          openDates.add( getDate( theWeek.getSun() ) );
        if ( getDate( theWeek.getMon() ) != null )
          openDates.add( getDate( theWeek.getMon() ) );
        if ( getDate( theWeek.getTues() ) != null )
          openDates.add( getDate( theWeek.getTues() ) );
        if ( getDate( theWeek.getWeds() ) != null )
          openDates.add( getDate( theWeek.getWeds() ) );
        if ( getDate( theWeek.getThurs() ) != null )
          openDates.add( getDate( theWeek.getThurs() ) );
        if ( getDate( theWeek.getFri() ) != null )
          openDates.add( getDate( theWeek.getFri() ) );
        if ( getDate( theWeek.getSat() ) != null )
          openDates.add( getDate( theWeek.getSat() ) );
      }
    }

    return openDates;
  }
  
  private static String getDate( DayOfWeek theDay )
  {
    if ( !theDay.getTimes().getStatus().equalsIgnoreCase( "closed" ) &&
         !theDay.getTimes().getStatus().equalsIgnoreCase( "not-set" ) )
    {
      return theDay.getDate();
    }
    else
      return null;
  }
}
