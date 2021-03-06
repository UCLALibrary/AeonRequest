package edu.ucla.library.libservices.aeon.vger.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType( XmlAccessType.FIELD )
public class VgerHoldingData
{
  @XmlElement( name = "callNo" )
  private String callNo;
  @XmlElement( name = "pickupLocale" )
  private String pickupLocale;
  
  public VgerHoldingData()
  {
    super();
  }

  public void setCallNo( String callNo )
  {
    this.callNo = callNo;
  }

  public String getCallNo()
  {
    return callNo;
  }

  public void setPickupLocale( String pickupLocale )
  {
    this.pickupLocale = pickupLocale;
  }

  public String getPickupLocale()
  {
    return pickupLocale;
  }
}
