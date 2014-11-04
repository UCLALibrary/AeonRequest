package edu.ucla.library.libservices.aeon.vger.db.mappers;

import edu.ucla.library.libservices.aeon.vger.beans.VgerBibData;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class VgerBibDataMapper
  implements RowMapper
{
  public VgerBibDataMapper()
  {
    super();
  }

  public Object mapRow( ResultSet rs, int rowNum )
    throws SQLException
  {
    VgerBibData bean;
    
    bean = new VgerBibData();
    bean.setAuthor( rs.getString( "author" ) );
    bean.setEdition( rs.getString( "edition" ) );
    bean.setMarc246( rs.getString( "marc246" ) );
    bean.setMarc506( rs.getString( "marc506" ) );
    bean.setMarc524( rs.getString( "marc524" ) );
    bean.setMarc590( rs.getString( "marc590" ) );
    bean.setOacUrl( rs.getString( "url" ) );
    bean.setPhysDesc( rs.getString( "physDesc" ) );
    bean.setPubDates( rs.getString( "pub_dates_combined" ) );
    bean.setPubPlace( rs.getString( "pub_place" ) );
    bean.setPublisher( rs.getString( "publisher" ) );
    bean.setTitle( rs.getString( "title" ) );
    
    return bean;
  }
}
