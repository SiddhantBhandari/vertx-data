package com.vertx_data.vertx_data.entities;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer extends BaseModel{

  private String name;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "address")
  private Address address;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Customer(String name, Address address) {
    this.name = name;
    this.address = address;
  }
}
