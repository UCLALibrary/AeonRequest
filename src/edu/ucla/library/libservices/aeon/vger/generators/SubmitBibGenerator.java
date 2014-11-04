package edu.ucla.library.libservices.aeon.vger.generators;

import edu.ucla.library.libservices.aeon.vger.beans.VgerBibData;
import edu.ucla.library.libservices.aeon.vger.db.mappers.VgerBibDataMapper;

import org.springframework.jdbc.core.JdbcTemplate;

public class SubmitBibGenerator
  extends BaseGenerator
{
  private VgerBibData bibData;
  private String[] itemIDs;

  private static final String BIB_QUERY =
    "SELECT bt.title_brief AS title,vger_support.get_aeon_246( bt.bib_id ) " +
    "AS marc246,bt.author,bt.pub_dates_combined,vger_support.get_aeon_506" +
    "( bt.bib_id ) AS marc506,vger_support.get_aeon_524( bt.bib_id ) AS " +
    "marc524,vger_support.get_aeon_590( bt.bib_id ) AS marc590," +
    "vger_support.get_856_url( bt.bib_id ) AS url, bt.pub_place, " +
    "bt.publisher, bt.edition, vger_support.get_aeon_300( bt.bib_id) AS " +
    "physDesc FROM ucladb.bib_text bt WHERE bt.bib_id = ?";

  public SubmitBibGenerator()
  {
    super();
  }

  public VgerBibData getBibData()
  {
    VgerItemDataGenerator itemGen;

    makeConnection();

    bibData = new VgerBibData();
    bibData =
        ( VgerBibData ) new JdbcTemplate( ds ).queryForObject( BIB_QUERY,
                                                               new Object[]
          { getBibID() }, new VgerBibDataMapper() );

    itemGen = new VgerItemDataGenerator();
    itemGen.setDbName( getDbName() );
    itemGen.setBibID( getBibID() );
    itemGen.setItemIDs( getItemIDs() );
    bibData.setSrlfItems( itemGen.getSimpleItems() );

    return bibData;
  }

  public void setItemIDs( String[] itemIDs )
  {
    this.itemIDs = itemIDs;
  }

  private String[] getItemIDs()
  {
    return itemIDs;
  }
}
