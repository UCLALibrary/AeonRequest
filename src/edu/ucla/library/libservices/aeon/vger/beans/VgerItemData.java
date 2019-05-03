package edu.ucla.library.libservices.aeon.vger.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType( XmlAccessType.FIELD )
public class VgerItemData
{
  private int itemID;
  @XmlElement( name = "itemEnum" )
  private String itemEnum;
  @XmlElement( name = "chron" )
  private String chron;
  @XmlElement( name = "copy" )
  private String copy;
  @XmlElement( name = "barcode" )
  private String barcode;
  @XmlElement( name = "statusID" )
  private int statusID;
  @XmlElement( name = "status" )
  private String status;
  @XmlElement( name = "oacDetails" )
  private String oacDetails;
  @XmlElement( name = "pickupLocale" )
  private String pickupLocale;
  @XmlElement( name = "note" )
  private String note;
  @XmlElement( name = "callNo" )
  private String callNo;
  @XmlElement( name = "location" )
  private String location;
  @XmlElement( name = "freeText" )
  private String freeText;

  public VgerItemData()
  {
    super();
  }

  public void setItemEnum( String vgerEnum )
  {
    this.itemEnum = vgerEnum;
  }

  public String getItemEnum()
  {
    return itemEnum; // (itemEnum == null ? "N/A" : itemEnum);
  }

  public void setChron( String chron )
  {
    this.chron = chron;
  }

  public String getChron()
  {
    return chron; // (chron == null ? "N/A" : chron );
  }

  public void setCopy( String copy )
  {
    this.copy = copy;
  }

  public String getCopy()
  {
    return copy;
  }

  public void setBarcode( String barcode )
  {
    this.barcode = barcode;
  }

  public String getBarcode()
  {
    return barcode;
  }

  public void setItemID( int itemID )
  {
    this.itemID = itemID;
  }

  public int getItemID()
  {
    return itemID;
  }

  public void setStatusID( int statusID )
  {
    this.statusID = statusID;
  }

  public int getStatusID()
  {
    return statusID;
  }

  public void setStatus( String status )
  {
    this.status = status;
  }

  public String getStatus()
  {
    return status;
  }

  public void setOacDetails( String oacDetails )
  {
    this.oacDetails = oacDetails;
  }

  public String getOacDetails()
  {
    return oacDetails;
  }

  public void setPickupLocale( String pickupLocale )
  {
    this.pickupLocale = pickupLocale;
  }

  public String getPickupLocale()
  {
    return pickupLocale;
  }

  public void setNote( String note )
  {
    this.note = note;
  }

  public String getNote()
  {
    return note;
  }

  public void setCallNo( String callNo )
  {
    this.callNo = callNo;
  }

  public String getCallNo()
  {
    return callNo;
  }

  public void setLocation( String location )
  {
    this.location = location;
  }

  public String getLocation()
  {
    return location;
  }

  public void setFreeText( String freeText )
  {
    this.freeText = freeText;
  }

  public String getFreeText()
  {
    return freeText;
  }
}
