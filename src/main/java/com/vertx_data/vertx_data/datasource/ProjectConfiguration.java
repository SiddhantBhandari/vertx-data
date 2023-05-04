package com.vertx_data.vertx_data.datasource;

import com.vertx_data.vertx_data.entities.Address;
import com.vertx_data.vertx_data.entities.Customer;
import com.vertx_data.vertx_data.entities.Employees;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;

import java.util.Properties;

public class ProjectConfiguration {

  public static void configureDatabase(){

      DatabaseConfig config = new DatabaseConfig();
      Properties properties = new Properties();
      properties.put("datasource.db.username", "root");
      properties.put("datasource.db.password", "!Bs80121");
      properties.put("datasource.db.databaseUrl", "jdbc:mysql://localhost:3306/db1");
      properties.put("datasource.db.databaseDriver", "com.mysql.cj.jdbc.Driver");

      config.setDefaultServer(true);
      config.setDdlCreateOnly(true);
      config.setDdlGenerate(true);
      config.setDdlRun(true);
      config.loadFromProperties(properties);
      config.addClass(Employees.class);
      config.addClass(Address.class);
      config.addClass(Customer.class);
      DatabaseFactory.create(config);
  }
}
