package edu.ucla.library.libservices.aeon.vger.db.mappers;

import edu.ucla.library.libservices.aeon.vger.beans.VgerItemData;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class VgerItemDataMapper
  implements RowMapper
{
  public VgerItemDataMapper()
  {
    super();
  }

  public Object mapRow( ResultSet rs, int rowNum )
    throws SQLException
  {
    VgerItemData bean;

    bean = new VgerItemData();
    bean.setBarcode( rs.getString( "barcode" ) );
    bean.setCallNo( rs.getString( "call_no" ) );
    bean.setChron( rs.getString( "chron" ) );
    bean.setCopy( rs.getString( "copy" ) );
    bean.setFreeText( rs.getString( "freetext" ) );
    bean.setItemEnum( rs.getString( "item_enum" ) );
    bean.setItemID( rs.getInt( "item_id" ) );
    bean.setLocation( rs.getString( "location_code" ) );
    bean.setNote( rs.getString( "note" ) );
    bean.setPickupLocale( rs.getString( "pickupLocale" ) );
    bean.setStatus( rs.getString( "status" ) );
    bean.setStatusID( rs.getInt( "status_id" ) );

    return bean;
  }
}
