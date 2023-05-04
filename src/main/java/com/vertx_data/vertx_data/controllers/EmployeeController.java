package com.vertx_data.vertx_data.controllers;

import com.vertx_data.vertx_data.handlers.*;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum EmployeeController {

  INSTANCE;

  public static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

  public void router(Router router){
    LOG.info("Route attached : {}", router);
    router.get("/employees").handler(GetEmployeesHandler.INSTANCE);
    router.post("/employee").handler(CreateEmployeesHandler.INSTANCE);
    router.put("/employee").handler(UpdateEmployeeHanlder.INSTANCE);
    router.get("/employee/:id").handler(GetEmployeeHandler.INSTANCE);
    router.put("/soft-delete-employee/:id").handler(SoftDeleteEmployeeHandler.INSTANCE);
    router.delete("/employee/:id").handler(DeleteEmployeeHandler.INSTANCE);
  }
}
