package com.vertx_data.vertx_data.handlers;

import com.vertx_data.vertx_data.entities.Employees;
import com.vertx_data.vertx_data.response.Status;
import io.ebean.DB;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

import java.util.Optional;

public enum SoftDeleteEmployeeHandler implements Handler<RoutingContext> {

  INSTANCE;

  @Override
  public void handle(RoutingContext event) {
    String id = event.request().getParam("id");
    Employees employee = DB.find(Employees.class)
      .where()
      .eq("id", id)
      .eq("isEnabled", true).findOne();

    Optional<Employees> optional = Optional.ofNullable(employee);
    if(!optional.isPresent()){
      event.response()
        .setStatusCode(HttpResponseStatus.NOT_FOUND.code())
        .end(new Status("Employee not available with id : " + id).toJsonObject().toBuffer());
    }
    Employees employees = optional.get();
    employees.setEnabled(false);
    DB.update(employees);
    event.response()
      .setStatusCode(HttpResponseStatus.OK.code())
      .end(new Status("Employee with id : " + id  + " deleted sucessfully").toJsonObject().toBuffer());

  }
}
