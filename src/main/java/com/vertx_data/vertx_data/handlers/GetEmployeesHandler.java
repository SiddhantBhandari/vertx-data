package com.vertx_data.vertx_data.handlers;


import com.google.gson.Gson;
import com.vertx_data.vertx_data.controllers.EmployeeController;
import com.vertx_data.vertx_data.entities.Employees;
import com.vertx_data.vertx_data.response.GetEmployeesResponse;
import com.vertx_data.vertx_data.response.Status;
import io.ebean.DB;
import io.ebean.Database;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public enum GetEmployeesHandler implements Handler<RoutingContext> {

  INSTANCE;

  public static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

  @Override
  public void handle(RoutingContext event) {
    LOG.info("Handling get employees request");

    List<Employees> list = DB.find(Employees.class)
      .where()
      .eq("isEnabled", true)
      .findList();
    LOG.info("List of employees : {}", list);

    if (list.isEmpty()) {
      event.response()
        .setStatusCode(HttpResponseStatus.NOT_FOUND.code())
        .end(new JsonObject().put("message", "Employees not found").toBuffer());
    }

    GetEmployeesResponse getEmployeesResponse = new GetEmployeesResponse();
    event.response()
      .setStatusCode(HttpResponseStatus.OK.code())
      .end(getEmployeesResponse.setEmployeesList(list)
        .setStatus(new Status("Employees retrieved successfully")).toJsonObject().toBuffer());
  }

}
