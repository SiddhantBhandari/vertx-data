package com.vertx_data.vertx_data.handlers;


import com.vertx_data.vertx_data.entities.Employees;
import com.vertx_data.vertx_data.response.GetEmployeeResponse;
import com.vertx_data.vertx_data.response.Status;
import io.ebean.DB;
import io.ebean.Database;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

import java.util.Optional;

public enum GetEmployeeHandler implements Handler<RoutingContext> {

  INSTANCE;

  @Override
  public void handle(RoutingContext event) {
    String id = event.request().getParam("id");

    Employees employees = DB.find(Employees.class)
      .where().eq("id", id)
      .eq("isEnabled", true)
      .findOne();

    Optional<Employees> employeesOptional = Optional.ofNullable(employees);
    if(!employeesOptional.isPresent()){
      event.response().setStatusCode(HttpResponseStatus.NOT_FOUND.code())
        .end(new Status(-2, "Employee not found with respect to id: " + id).toJsonObject().toBuffer());
    }
    GetEmployeeResponse getEmployeeResponse = new GetEmployeeResponse();
    event.response().setStatusCode(HttpResponseStatus.OK.code())
      .end(getEmployeeResponse.setEmployee(employeesOptional.get())
        .setStatus(new Status("Employee retrieved successfully with id " + id)).toJsonObject().toBuffer());
  }
}
