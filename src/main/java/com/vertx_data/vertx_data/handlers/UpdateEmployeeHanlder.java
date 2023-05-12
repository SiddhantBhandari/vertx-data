package com.vertx_data.vertx_data.handlers;

import com.vertx_data.vertx_data.entities.Employees;
import com.vertx_data.vertx_data.entities.Gender;
import com.vertx_data.vertx_data.request.UpdateEmployee;
import com.vertx_data.vertx_data.response.Status;
import io.ebean.DB;
import io.ebean.Database;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;

public enum UpdateEmployeeHanlder implements Handler<RoutingContext> {

  INSTANCE;

  @Override
  public void handle(RoutingContext event) {
    Database database = DB.getDefault();
    UpdateEmployee updateEmployee = event.body().asPojo(UpdateEmployee.class);
    Employees employees = database.find(Employees.class, updateEmployee.getId());
    Optional<Employees> optional = Optional.ofNullable(employees);
    if(!optional.isPresent()){
      event.response()
        .setStatusCode(HttpResponseStatus.NOT_FOUND.code())
        .end(new Status(-1, "Employee not available with " + updateEmployee.getId() + " id").toJsonObject().toBuffer());
    }
    assert employees != null;
    employees.setName(updateEmployee.getName());
    employees.setContact(updateEmployee.getContact());
    employees.setGender(Gender.getGender(updateEmployee.getGender()));
    employees.setDateOfBirth(Instant.ofEpochMilli(updateEmployee.getDateOfBirth()).atZone(ZoneId.systemDefault()).toLocalDate());
    employees.setDateOfJoining(Instant.ofEpochMilli(updateEmployee.getDateOfJoining()).atZone(ZoneId.systemDefault()).toLocalDate());
    database.update(employees);
    event.response().setStatusCode(HttpResponseStatus.OK.code())
      .end(new Status("Employee updated successfully").toJsonObject().toBuffer());
  }
}
