package com.vertx_data.vertx_data.response;

import com.vertx_data.vertx_data.entities.Employees;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class GetEmployeesResponse {

  private List<Employees> employeesList = new ArrayList<>();

  private Status status;

  public GetEmployeesResponse setEmployeesList(List<Employees> employeesList) {
    this.employeesList = employeesList;
    return this;
  }

  public GetEmployeesResponse setStatus(Status status) {
    this.status = status;
    return this;
  }

  public JsonObject toJsonObject(){
    return JsonObject.mapFrom(this);
  }

  public List<Employees> getEmployeesList() {
    return employeesList;
  }

  public Status getStatus() {
    return status;
  }
}
