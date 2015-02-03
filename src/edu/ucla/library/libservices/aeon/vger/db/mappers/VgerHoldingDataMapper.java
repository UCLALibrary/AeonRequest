package edu.ucla.library.libservices.aeon.vger.db.mappers;

import edu.ucla.library.libservices.aeon.vger.beans.VgerHoldingData;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class VgerHoldingDataMapper
  implements RowMapper
{
  public VgerHoldingDataMapper()
  {
    super();
  }

  public Object mapRow( ResultSet rs, int rowNum )
    throws SQLException
  {
    VgerHoldingData bean;

    bean = new VgerHoldingData();
    bean.setCallNo( rs.getString( "call_no" ) );
    bean.setPickupLocale( rs.getString( "pickupLocale" ) );

    return bean;
  }
}
