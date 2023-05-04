package com.vertx_data.vertx_data;

import com.vertx_data.vertx_data.config.BrokerConfig;
import com.vertx_data.vertx_data.config.ConfigLoader;
import com.vertx_data.vertx_data.controllers.EmployeeController;
import com.vertx_data.vertx_data.datasource.ProjectConfiguration;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRouter extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(HttpRouter.class);

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    Router router = Router.router(vertx);
    ConfigLoader.load(vertx)
      .onFailure(startPromise::fail)
      .onSuccess(
        configuration ->{
          LOG.info("Retrieved configuration : {} " , configuration);
          handleRoutes(startPromise,configuration, router);
        });
    router.route().handler(BodyHandler.create())
      .failureHandler(error -> {
        if(error.response().ended()){
          return;
        }
        LOG.error("Error: {}", error.failure());
        error.response()
          .setStatusCode(500)
          .end(new JsonObject().put("message", "Something went Wrong").toBuffer());
      });

  }

  public void handleRoutes(Promise<Void> startPromise, BrokerConfig config, Router router){
    EmployeeController.INSTANCE.router(router);

    vertx.createHttpServer().requestHandler(router)
      .exceptionHandler(error -> {
        LOG.error("HTTP server error: ", error.getCause());
      })
      .listen(config.getServerPort(), http -> {
        if(http.succeeded()){
          startPromise.complete();
          ProjectConfiguration.configureDatabase();
          LOG.info("HTTP server started on port {} ", config.getServerPort());
        }else startPromise.fail(http.cause());
      });
  }
}
