package edu.ucla.library.libservices.aeon.vger.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType( XmlAccessType.FIELD )
public class VgerBibData
{
  @XmlElement( name = "title" )
  private String title;
  @XmlElement( name = "marc246" )
  private String marc246;
  @XmlElement( name = "author" )
  private String author;
  @XmlElement( name = "pubDates" )
  private String pubDates;
  @XmlElement( name = "marc506" )
  private String marc506;
  @XmlElement( name = "marc524" )
  private String marc524;
  @XmlElement( name = "marc590" )
  private String marc590;
  @XmlElement( name = "oacUrl" )
  private String oacUrl;
  @XmlElement( name = "publisher" )
  private String publisher;
  @XmlElement( name = "pubPlace" )
  private String pubPlace;
  @XmlElement( name = "edition" )
  private String edition;
  @XmlElement( name = "physDesc" )
  private String physDesc;
  @XmlElement( name = "yrlHoldings" )
  private List<VgerHoldingData> yrlHoldings;
  @XmlElement( name = "srlfItems" )
  private List<VgerItemData> srlfItems;
  
  public VgerBibData()
  {
    super();
  }

  public void setYrlHoldings( List<VgerHoldingData> holding )
  {
    this.yrlHoldings = holding;
  }

  public List<VgerHoldingData> getYrlHoldings()
  {
    return yrlHoldings;
  }

  public void setTitle( String marc245 )
  {
    this.title = marc245;
  }

  public String getTitle()
  {
    return title;
  }

  public void setMarc246( String marc246 )
  {
    this.marc246 = marc246;
  }

  public String getMarc246()
  {
    return marc246;
  }

  public void setPubDates( String marc260 )
  {
    this.pubDates = marc260;
  }

  public String getPubDates()
  {
    return pubDates;
  }

  public void setMarc506( String marc506 )
  {
    this.marc506 = marc506;
  }

  public String getMarc506()
  {
    return marc506;
  }

  public void setSrlfItems( List<VgerItemData> srlfItems )
  {
    this.srlfItems = srlfItems;
  }

  public List<VgerItemData> getSrlfItems()
  {
    return srlfItems;
  }

  public void setAuthor( String author )
  {
    this.author = author;
  }

  public String getAuthor()
  {
    return author;
  }

  public void setMarc524( String marc524 )
  {
    this.marc524 = marc524;
  }

  public String getMarc524()
  {
    return marc524;
  }

  public void setMarc590( String marc590 )
  {
    this.marc590 = marc590;
  }

  public String getMarc590()
  {
    return marc590;
  }

  public void setOacUrl( String oacUrl )
  {
    this.oacUrl = oacUrl;
  }

  public String getOacUrl()
  {
    return oacUrl;
  }

  public void setPublisher( String publisher )
  {
    this.publisher = publisher;
  }

  public String getPublisher()
  {
    return publisher;
  }

  public void setPubPlace( String pubPlace )
  {
    this.pubPlace = pubPlace;
  }

  public String getPubPlace()
  {
    return pubPlace;
  }

  public void setEdition( String edition )
  {
    this.edition = edition;
  }

  public String getEdition()
  {
    return edition;
  }

  public void setPhysDesc( String physDesc )
  {
    this.physDesc = physDesc;
  }

  public String getPhysDesc()
  {
    return physDesc;
  }
}
