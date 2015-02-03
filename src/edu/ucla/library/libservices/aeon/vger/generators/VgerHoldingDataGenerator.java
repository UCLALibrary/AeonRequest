package edu.ucla.library.libservices.aeon.vger.generators;

import edu.ucla.library.libservices.aeon.vger.beans.VgerHoldingData;

import edu.ucla.library.libservices.aeon.vger.db.mappers.VgerHoldingDataMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class VgerHoldingDataGenerator
  extends BaseGenerator
{
  private List<VgerHoldingData> items;

  private static final String ITEMS_QUERY =
    "SELECT DISTINCT mm.display_call_no AS call_no, vger_support.bio_or_yrl" + 
    "(mm.location_id) AS pickupLocale  FROM ucladb.bib_mfhd bm INNER JOIN " + 
    "ucladb.mfhd_master mm ON bm.mfhd_id = mm.mfhd_id INNER JOIN " +
    "vger_support.aeon_locations al ON mm.location_id = al.location_id " +
    "WHERE bm.bib_id = ? ORDER BY call_no";

  public VgerHoldingDataGenerator()
  {
    super();
  }

  public List<VgerHoldingData> getItems()
  {
    makeConnection();

    try
    {
      items = new JdbcTemplate( ds ).query( ITEMS_QUERY, new Object[]
            { getBibID() }, new VgerHoldingDataMapper() );
    }
    catch ( Exception e )
    {
      e.printStackTrace();
      items = new ArrayList<VgerHoldingData>();
    }
    return items;
  }
}
