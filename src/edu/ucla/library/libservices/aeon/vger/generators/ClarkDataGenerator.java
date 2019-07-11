package edu.ucla.library.libservices.aeon.vger.generators;

import edu.ucla.library.libservices.aeon.vger.beans.ClarkData;

import edu.ucla.library.libservices.aeon.vger.db.mappers.ClarkDataMapper;

import org.springframework.jdbc.core.JdbcTemplate;

public class ClarkDataGenerator
  extends BaseGenerator
{
  private static final String QUERY =
    "SELECT * FROM (SELECT vger_support.unifix(bt.title_brief) AS title, vger_support.unifix(bt.author) AS author," 
    + " bt.pub_dates_combined AS dates, vger_support.unifix(bt.pub_place) AS place, vger_support.unifix(bt.publisher)" 
    + " AS publisher, bt.edition, mm.display_call_no AS call_no, mi.item_enum, i.copy_number AS copy, l.location_code AS location " 
    + "FROM ucladb.bib_text bt INNER JOIN ucladb.bib_mfhd bm ON bt.bib_id = bm.bib_id INNER JOIN ucladb.mfhd_master" 
    + " mm ON bm.mfhd_id = mm.mfhd_id INNER JOIN ucladb.location l ON mm.location_id = l.location_id LEFT OUTER JOIN" 
    + " ucladb.mfhd_item mi ON mm.mfhd_id = mi.mfhd_id LEFT OUTER JOIN ucladb.item i ON mi.item_id = i.item_id WHERE" 
    + " bt.bib_id = ? AND bm.mfhd_id = ? AND l.location_display_name LIKE '%Clark%' ORDER BY call_no, copy NULLS FIRST)" 
    + " WHERE ROWNUM = 1";
  
  private ClarkData data;
  private String mdhdID;

  public ClarkDataGenerator()
  {
    super();
  }

  public void setMdhdID( String mdhdID )
  {
    this.mdhdID = mdhdID;
  }

  public String getMdhdID()
  {
    return mdhdID;
  }

  public ClarkData getData()
  {
    makeConnection();
    data =
      ( ClarkData ) new JdbcTemplate( ds ).queryForObject( QUERY, new Object[] { getBibID(), getMdhdID() }, new ClarkDataMapper() );
    return data;
  }
}
