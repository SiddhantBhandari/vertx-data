package com.vertx_data.vertx_data.handlers;

import com.vertx_data.vertx_data.controllers.EmployeeController;
import com.vertx_data.vertx_data.entities.Employees;
import com.vertx_data.vertx_data.entities.Gender;
import com.vertx_data.vertx_data.request.CreateEmployeeRequest;
import com.vertx_data.vertx_data.response.Status;
import io.ebean.DB;
import io.ebean.Database;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.ext.web.RequestBody;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

public enum CreateEmployeesHandler implements Handler<RoutingContext> {

  INSTANCE;
  public static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

  @Override
  public void handle(RoutingContext event) {
    LOG.info("Handling create employee request");
    Database database = DB.getDefault();
    RequestBody body = event.body();
    CreateEmployeeRequest createEmployeeRequest = body.asPojo(CreateEmployeeRequest.class);
    Employees employees = new Employees();
    employees.setId(UUID.randomUUID().toString());
    employees.setName(createEmployeeRequest.getName());
    employees.setContact(createEmployeeRequest.getContact());
    employees.setGender(Gender.getGender(createEmployeeRequest.getGender()));
    employees.setDateOfBirth(Instant.ofEpochMilli(createEmployeeRequest.getDateOfBirth()).atZone(ZoneId.systemDefault()).toLocalDate());
    employees.setDateOfJoining(Instant.ofEpochMilli(createEmployeeRequest.getDateOfJoining()).atZone(ZoneId.systemDefault()).toLocalDate());
    employees.setEnabled(true);
    database.save(employees);
    event.response().setStatusCode(HttpResponseStatus.OK.code())
      .end(new Status("Employee created successfully").toJsonObject().toBuffer());
//    body.asPojo()
  }
}
