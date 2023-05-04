package com.vertx_data.vertx_data.response;

import com.vertx_data.vertx_data.entities.Employees;
import io.vertx.core.json.JsonObject;

public class GetEmployeeResponse {

  private Employees employee;

  private Status status;

  public Employees getEmployee() {
    return employee;
  }

  public GetEmployeeResponse setEmployee(Employees employee) {
    this.employee = employee;
    return this;
  }

  public Status getStatus() {
    return status;
  }

  public GetEmployeeResponse setStatus(Status status) {
    this.status = status;
    return this;
  }

  public JsonObject toJsonObject(){
    return JsonObject.mapFrom(this);
  }

}
