package com.vertx_data.vertx_data.handlers;


import com.vertx_data.vertx_data.entities.Employees;
import com.vertx_data.vertx_data.response.Status;
import io.ebean.DB;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

import java.util.Optional;

public enum DeleteEmployeeHandler implements Handler<RoutingContext> {

  INSTANCE;

  @Override
  public void handle(RoutingContext event) {
    String id = event.request().getParam("id");
    Employees employee = DB.find(Employees.class)
      .where()
      .eq("id", id)
      .eq("isEnabled", true)
      .findOne();

    Optional<Employees> employeesOptional = Optional.ofNullable(employee);
    if(!employeesOptional.isPresent()){
      event.response()
        .setStatusCode(HttpResponseStatus.OK.code())
        .end(new Status(-3, "Employee not found with id : " + id).toJsonObject().toBuffer());
      return;
    }
    DB.delete(employeesOptional.get());
    event.response().setStatusCode(HttpResponseStatus.OK.code())
      .end(new Status("Employee deleted successfully with id : " + id).toJsonObject().toBuffer());
  }
}
