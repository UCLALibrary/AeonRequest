package edu.ucla.library.libservices.aeon.submission.builder;

import edu.ucla.library.libservices.aeon.vger.beans.VgerBibData;
import edu.ucla.library.libservices.aeon.vger.beans.VgerItemData;
import edu.ucla.library.libservices.aeon.vger.generators.VgerBibDataGenerator;

import java.io.File;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class FileBuilder
{
  private String dbName;
  private String bibID;
  private String[] itemIDs;

  public FileBuilder()
  {
    super();
  }

  public void makeFile(PrintWriter out)
  {
    VgerBibDataGenerator generator;
    VgerBibData bibData;
    Document output;
    Element root;
    Element element;
    Transformer identity;
    Result result;

    generator = new VgerBibDataGenerator();
    generator.setDbName( getDbName() );
    generator.setBibID( getBibID() );
    generator.setItemIDs( getItemIDs() );

    bibData = generator.getXmlBibData();

    try
    {
      output =
          DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

      root = output.createElement( "request" );
      output.appendChild( root );

      element = buildBibData( bibData, output );
      root.appendChild( element );

      element = output.createElement( "RequestedItems" );
      for ( VgerItemData theItem: bibData.getSrlfItems() )
        buildItemData( theItem, output, element );
      root.appendChild( element );

      identity = TransformerFactory.newInstance().newTransformer();
      result =
          new StreamResult( out ); // new File( "C:\\Temp\\aeon\\" + getBibID() + ".xml" ) );
      identity.transform( new DOMSource( output ), result );
    }
    catch ( ParserConfigurationException pce )
    {
      pce.printStackTrace();
    }
    catch ( TransformerConfigurationException tce )
    {
      tce.printStackTrace();
    }
    catch ( TransformerException te )
    {
      te.printStackTrace();
    }
  }

  private void buildItemData( VgerItemData theItem, Document output,
                              Element element )
  {
    Element itemNode;

    itemNode = output.createElement( "Item" );
    if ( theItem.getBarcode() != null )
      itemNode.appendChild( makeTextNode( "ItemNumber",
                                          theItem.getBarcode(), output ) );
    if ( theItem.getLocation() != null )
      itemNode.appendChild( makeTextNode( "Location",
                                          theItem.getLocation(),
                                          output ) );
    if ( theItem.getCallNo() != null )
      itemNode.appendChild( makeTextNode( "CallNumber",
                                          theItem.getCallNo(), output ) );
    if ( theItem.getNote() != null )
      itemNode.appendChild( makeTextNode( "ItemInfo2", theItem.getNote(),
                                          output ) );
    if ( theItem.getItemEnum() != null )
      itemNode.appendChild( makeTextNode( "ItemVolume",
                                          theItem.getItemEnum(),
                                          output ) );
    if ( theItem.getCopy() != null )
      itemNode.appendChild( makeTextNode( "ItemIssue", theItem.getCopy(),
                                          output ) );
    element.appendChild( itemNode );
  }

  private Element buildBibData( VgerBibData bibData, Document output )
  {
    Element bibNode;
    bibNode = output.createElement( "BibData" );
    bibNode.appendChild( makeTextNode( "ReferenceNumber", getBibID(),
                                       output ) );
    if ( bibData.getTitle() != null )
      bibNode.appendChild( makeTextNode( "ItemTitle", bibData.getTitle(),
                                         output ) );
    if ( bibData.getAuthor() != null )
      bibNode.appendChild( makeTextNode( "ItemAuthor", bibData.getAuthor(),
                                         output ) );
    if ( bibData.getPubPlace() != null )
      bibNode.appendChild( makeTextNode( "ItemPlace",
                                         bibData.getPubPlace(), output ) );
    if ( bibData.getPublisher() != null )
      bibNode.appendChild( makeTextNode( "ItemPublisher",
                                         bibData.getPublisher(),
                                         output ) );
    if ( bibData.getPubDates() != null )
      bibNode.appendChild( makeTextNode( "ItemDate", bibData.getPubDates(),
                                         output ) );
    if ( bibData.getEdition() != null )
      bibNode.appendChild( makeTextNode( "ItemEdition",
                                         bibData.getEdition(), output ) );
    if ( bibData.getOacUrl() != null )
      bibNode.appendChild( makeTextNode( "EADNumber", bibData.getOacUrl(),
                                         output ) );
    if ( bibData.getPhysDesc() != null )
      bibNode.appendChild( makeTextNode( "ItemInfo1",
                                         bibData.getPhysDesc(), output ) );
    if ( bibData.getMarc590() != null )
      bibNode.appendChild( makeTextNode( "Notes", bibData.getMarc590(),
                                         output ) );
    return bibNode;
  }

  public void setDbName( String dbName )
  {
    this.dbName = dbName;
  }

  private String getDbName()
  {
    return dbName;
  }

  public void setBibID( String bibID )
  {
    this.bibID = bibID;
  }

  private String getBibID()
  {
    return bibID;
  }

  public void setItemIDs( String[] itemIDs )
  {
    this.itemIDs = itemIDs;
  }

  private String[] getItemIDs()
  {
    return itemIDs;
  }

  private Element makeTextNode( String key, String value, Document output )
  {
    Element element;
    Text textNode;

    textNode = output.createTextNode( value );
    element = output.createElement( key );
    element.appendChild( textNode );
    return element;
  }
}
