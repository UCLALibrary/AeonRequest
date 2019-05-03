package edu.ucla.library.libservices.aeon.vger.generators;

import edu.ucla.library.libservices.aeon.vger.beans.VgerItemData;
import edu.ucla.library.libservices.aeon.vger.db.mappers.VgerItemDataMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.jdbc.core.JdbcTemplate;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

import org.xml.sax.SAXException;

import org.apache.log4j.Logger;

public class VgerItemDataGenerator
  extends BaseGenerator
{
  private List<VgerItemData> items;
  private Map<String, String> details;
  private String url;
  private String[] itemIDs;
  private static final Logger logger =
    Logger.getLogger( VgerItemDataGenerator.class );

  private static final String BOX_PATTERN = "\\Qbox.\\E\\d+";
  private static final String SERIES_PATTERN = "\\Qser.\\E[a-zA-Z]+\\s";
  private static final String ITEMS_QUERY =
    "SELECT i.item_id,mm.display_call_no AS call_no,al.location_code," 
    + "mi.item_enum,mi.chron,i.copy_number AS copy,ib.item_barcode AS barcode," 
    + "ucladb.getLatestItemStatus(i.item_id) AS status_id," 
    + "vger_support.get_status_desc(ucladb.getLatestItemStatus(i.item_id)) AS " 
    + "status,vger_support.bio_or_yrl(mm.location_id) AS pickupLocale," 
    + "vger_support.get_aeon_852_note(mm.mfhd_id) AS note, mi.freetext FROM ucladb.bib_mfhd " 
    + "bm INNER JOIN ucladb.mfhd_master mm ON bm.mfhd_id = mm.mfhd_id INNER " 
    + "JOIN vger_support.aeon_locations al ON mm.location_id = al.location_id "  
    + "INNER JOIN ucladb.mfhd_item mi ON mm.mfhd_id = mi.mfhd_id LEFT OUTER " 
    + "JOIN ucladb.item i ON mi.item_id = i.item_id LEFT OUTER JOIN " 
    + "ucladb.item_barcode ib ON i.item_id = ib.item_id AND ib.barcode_status " 
    + "= 1 WHERE bm.bib_id = ? ORDER BY pickupLocale, item_enum, note NULLS FIRST, copy";
  private static final String ITEM_QUERY = 
    "SELECT mi.item_id,mm.display_call_no AS call_no,al.location_code," 
    + "mi.item_enum,mi.chron,i.copy_number AS copy,ib.item_barcode AS barcode," 
    + "ucladb.getLatestItemStatus(i.item_id) AS status_id," 
    + "vger_support.get_status_desc(ucladb.getLatestItemStatus(i.item_id)) AS " 
    + "status,vger_support.bio_or_yrl(mm.location_id) AS pickupLocale," 
    + "vger_support.get_aeon_852_note(mm.mfhd_id) AS note, mi.freetext FROM " 
    + "ucladb.bib_mfhd bm INNER JOIN ucladb.mfhd_master mm ON bm.mfhd_id =" 
    + " mm.mfhd_id INNER JOIN vger_support.aeon_locations al ON mm.location_id" 
    + " = al.location_id INNER JOIN ucladb.mfhd_item mi ON mm.mfhd_id = " 
    + "mi.mfhd_id LEFT OUTER JOIN ucladb.item i ON mi.item_id = i.item_id " 
    + "LEFT OUTER JOIN ucladb.item_barcode ib ON i.item_id = ib.item_id AND " 
    + "ib.barcode_status = 1 WHERE mi.item_id = ? AND bm.bib_id = ?";

  public VgerItemDataGenerator()
  {
    super();
  }
  
  public List<VgerItemData> getSimpleItems()
  {
    makeConnection();
    items = new ArrayList<VgerItemData>();
    
    if ( getItemIDs() != null && getItemIDs().length != 0 )
    {
      for ( String id : getItemIDs() )
      {
        try
        {
          VgerItemData theItem;
          
          theItem =
              ( VgerItemData ) new JdbcTemplate( ds ).queryForObject( ITEM_QUERY, new Object[]{id,getBibID()}, new VgerItemDataMapper() );
          items.add( theItem );
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }

    return items;
  }
  
  public List<VgerItemData> getItems()
  {
    makeConnection();

    try
    {
      //System.out.println( "executing items query @ " + new Date() );
      items = new JdbcTemplate( ds ).query( ITEMS_QUERY, new Object[]
            { getBibID() }, new VgerItemDataMapper() );
      //System.out.println( "number of items = " + items.size() );
      //System.out.println( "done executing holdings query @ " + new Date() );

      //System.out.println( "getting ead data @ " + new Date() );
      populateMap();
      //System.out.println( "done getting ead data @ " + new Date() );
      /*for ( String theKey : details.keySet() )
        System.out.println( theKey );*/
      for ( VgerItemData theItem: items )
        setDetails( theItem );
    }
    catch ( Exception e )
    {
      e.printStackTrace();
      items = new ArrayList<VgerItemData>();
    }
    return items;
  }

  private void populateMap()
  {
    String ead;

    details = new TreeMap<String, String>();
    ead = null;

    ead = getEadURL();

    if ( ead != null && ead.length() != 0 && !ead.equals( "" ) )
    //if ( getBibID().equals( "6911986" ) )
    {
      Document document;
      DOMImplementation domimpl;
      DocumentTraversal traversal;
      TreeWalker walker;
      Node thisNode;
      String key;

      try
      {
        //System.out.println( "in populateMap, opening EAD URL" );
        logger.info( "in populateMap, opening EAD URL" );
        document =
            DocumentBuilderFactory.newInstance().newDocumentBuilder().parse( new URL( ead ).openStream() );
        logger.info( "in populateMap, after opening EAD URL" );
        domimpl = document.getImplementation();

        if ( domimpl.hasFeature( "Traversal", "2.0" ) )
        {
          //System.out.println( "doing EAD traversal" );
          traversal = ( DocumentTraversal ) document;
          walker =
              traversal.createTreeWalker( document.getDocumentElement(),
                                          NodeFilter.SHOW_ALL, null,
                                          false );
          thisNode = walker.nextNode();
          while ( thisNode != null )
          {
            if ( thisNode.getNodeName().trim().equalsIgnoreCase( "container" ) &&
                 thisNode.getAttributes().getNamedItem( "type" ).getNodeValue().equalsIgnoreCase( "box" ) )
            {
              key = thisNode.getTextContent().trim();

              if ( key.contains( "-" ) )
              {
                String[] range;
                int index;

                range = key.split( "-" );

                if ( range.length == 2 )
                {
                  try
                  {
                    for ( index = Integer.parseInt( range[ 0 ].trim() );
                          index <= Integer.parseInt( range[ 1 ].trim() );
                          index++ )
                    {
                      addDetails( thisNode, String.valueOf( index ) );
                    }
                  }
                  catch ( NumberFormatException nfe )
                  {
                    nfe.printStackTrace();
                  }
                }
              }
              else
              {
                addDetails( thisNode, key );
              }
            }
            thisNode = walker.nextNode();
          }
        }
      }
      catch ( ParserConfigurationException pce )
      {
        pce.printStackTrace();
        logger.error( "Parse error in getEadURL, " + pce.getMessage() );
      }
      catch ( SAXException saxe )
      {
        saxe.printStackTrace();
        logger.error( "SAX error in getEadURL, " + saxe.getMessage() );
      }
      catch ( IOException ioe )
      {
        ioe.printStackTrace();
        logger.error( "IO error in getEadURL, " + ioe.getMessage() );
      }
    }
  }

  private void addDetails( Node thisNode, String key )
  {
    StringBuffer buffer;
    buffer = new StringBuffer();
    NodeList detailNodes = thisNode.getParentNode().getChildNodes();
    for ( int i = 0; i < detailNodes.getLength(); i++ )
    {
      if ( ( detailNodes.item( i ).getNodeName().equalsIgnoreCase( "unittitle" ) ||
             detailNodes.item( i ).getNodeName().equalsIgnoreCase( "unitdate" ) ) &&
           detailNodes.item( i ).getTextContent().trim() != "" )
        buffer.append( detailNodes.item( i ).getTextContent().trim() ).append( "|" );
    }
    if ( details.containsKey( key ) )
    {
      details.put( key, "" );
    }
    else
    {
      details.put( key, buffer.toString() );
    }
  }

  private void setDetails( VgerItemData theItem )
  {
    //System.out.println( "working with item " + theItem.getBarcode() );
    if ( theItem.getItemEnum() != null &&
         theItem.getItemEnum().length() != 0 &&
         !theItem.getItemEnum().equals( "" ) )
    {
      String boxNumber;
      String seriesID;

      Matcher boxMatcher;
      Matcher seriesMatcher;
      Pattern boxPattern;
      Pattern seriesPattern;

      seriesID = "";
      boxNumber = "";

      boxPattern = Pattern.compile( BOX_PATTERN );
      seriesPattern = Pattern.compile( SERIES_PATTERN );

      boxMatcher = boxPattern.matcher( theItem.getItemEnum() );
      seriesMatcher = seriesPattern.matcher( theItem.getItemEnum() );

      if ( boxMatcher.find() )
      {
        boxNumber =
            String.valueOf( Integer.parseInt( boxMatcher.group().substring( boxMatcher.group().indexOf( "." ) +
                                                                            1 ) ) );
      }
      if ( seriesMatcher.find() )
      {
        seriesID =
            seriesMatcher.group().substring( seriesMatcher.group().indexOf( "." ) +
                                             1 ).trim();
      }

      boxNumber = boxNumber.concat( seriesID ).trim();
      if ( details.containsKey( boxNumber ) )
        theItem.setOacDetails( details.get( boxNumber ) );
    }
  }

  public void setUrl( String url )
  {
    this.url = url;
  }

  private String getUrl()
  {
    return url;
  }

  private String getEadURL()
  {
    if ( getUrl() != null && getUrl().length() != 0 &&
         !getUrl().equals( "" ) )
    {
      BufferedReader in;
      String inputLine;
      String result;

      result = null;

      try
      {
        logger.info( "in getEadURL, opening OAC URL" );
        in =
            new BufferedReader( new InputStreamReader( new URL( getUrl() ).openStream() ) );
        logger.info( "in getEadURL, after opening OAC URL" );
        while ( ( inputLine = in.readLine() ) != null )
        {
          if ( inputLine.contains( "EAD: " ) )
          {
            result = inputLine.substring( inputLine.indexOf( "http" ) );
            //System.out.println( "getting EAD URL " + result );
          }
        }
        in.close();
      }
      catch ( MalformedURLException me )
      {
        me.printStackTrace();
        logger.error( "malformed URL in getEadURL, " + me.getMessage() );
      }
      catch ( IOException ioe )
      {
        ioe.printStackTrace();
        logger.error( "IO error in getEadURL, " + ioe.getMessage() );
      }

      return result;
    }
    else
      return null;
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

/*
 * pass bid ID and URL to item generator;
 * if URL is not empty, the use URL object & BufferedReader to read content & retrieve EAD URL
 * use EAD URL to retrieve boxes and populate item details
 */
        /*if ( theItem.getItemEnum().contains( "box" ) )
        {
          if ( theItem.getItemEnum().startsWith( "box" ) )
          {
            if ( theItem.getItemEnum().contains( " " ) )
            {
              boxNumber =
                  String.valueOf( Integer.parseInt( theItem.getItemEnum().substring( 
                    theItem.getItemEnum().indexOf( "." ) + 1,
                    theItem.getItemEnum().indexOf( " " ) ) ) );
            }
            else
            {
              boxNumber =
                  String.valueOf( Integer.parseInt( theItem.getItemEnum().substring( theItem.getItemEnum().lastIndexOf( "." ) +
                                                                                     1 ) ) );
            }
          }
          else
          {
            boxNumber =
                String.valueOf( Integer.parseInt( theItem.getItemEnum().substring( theItem.getItemEnum().lastIndexOf( "." ) +
                                                                                   1 ) ) );
          }
          if ( details.containsKey( boxNumber ) )
            theItem.setOacDetails( details.get( boxNumber ) );
        }*/
