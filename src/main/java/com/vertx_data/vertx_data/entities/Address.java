package com.vertx_data.vertx_data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address extends BaseModel{

  @Column(name = "address_line")
  private String addressLine;

  @Column(name = "city")
  private String city;

  @Column(name = "pin_code")
  private String pinCode;

  /**
   *
   * @return addressLine
   */
  public String getAddressLine() {
    return addressLine;
  }


  public void setAddressLine(String addressLine) {
    this.addressLine = addressLine;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPinCode() {
    return pinCode;
  }

  public void setPinCode(String pinCode) {
    this.pinCode = pinCode;
  }

  public Address(String addressLine, String city, String pinCode) {
    this.addressLine = addressLine;
    this.city = city;
    this.pinCode = pinCode;
  }
}
