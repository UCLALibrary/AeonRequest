package edu.ucla.library.libservices.aeon.vger.db.mappers;

import edu.ucla.library.libservices.aeon.vger.beans.ClarkData;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ClarkDataMapper
  implements RowMapper
{
  public ClarkDataMapper()
  {
    super();
  }

  @Override
  public Object mapRow( ResultSet rs, int rowNum )
    throws SQLException
  {
    // TODO Implement this method
    ClarkData bean;
    
    bean = new ClarkData();
    bean.setCallNumber( rs.getString( "call_no" ) );
    bean.setItemAuthor( rs.getString( "author" ) );
    bean.setItemDate( rs.getString( "dates" ) );
    bean.setItemEdition( rs.getString( "edition" ) );
    bean.setItemInfo1( rs.getString( "copy" ) ); //copy number
    //bean.setItemIssue( rs.getString( "arg0" ) );
    bean.setLocation( rs.getString( "location" ) );
    bean.setItemPlace( rs.getString( "place" ) );
    bean.setItemPublisher( rs.getString( "publisher" ) );
    bean.setItemTitle( rs.getString( "title" ) );
    bean.setItemVolume( rs.getString( "item_enum" ) );
    
    return bean;
  }
}
