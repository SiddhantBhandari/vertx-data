package com.vertx_data.vertx_data.entities;

import io.ebean.annotation.DbEnumValue;

import java.util.HashMap;
import java.util.Map;

public enum Gender {

  MALE("Male"), FEMALE("Female"), OTHER("Other");

  private static final Map<String, Gender> lookup = new HashMap<>();

  static {
    for(Gender gender : Gender.values()){
      lookup.put(gender.getValue(), gender);
    }
  }
  String dbValue;


  Gender(String dbValue) {
    this.dbValue = dbValue;
  }

  @DbEnumValue
  public String getValue() {
    return dbValue;
  }

  public static Gender getGender(String gender){
    return lookup.get(gender);
  }
}
