package com.vertx_data.vertx_data.request;

import com.google.gson.annotations.Expose;

public class UpdateEmployee {

  @Expose
  private String id;

  @Expose
  private String contact;
  @Expose
  private Long dateOfBirth;
  @Expose
  private Long dateOfJoining;
  @Expose
  private String gender;
  @Expose
  private String name;

  public String getContact() {
    return contact;
  }

  public Long getDateOfBirth() {
    return dateOfBirth;
  }

  public Long getDateOfJoining() {
    return dateOfJoining;
  }

  public String getGender() {
    return gender;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

}
