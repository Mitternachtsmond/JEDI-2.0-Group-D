package com.dropwizard;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipkart.restcontroller.UserRestAPI;
import com.flipkart.restcontroller.*;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


//import com.dropwizard.rest.EmployeeRESTController;
//import com.dropwizard.rest.HelloRestController;

/**
 * Hello world!
 *
 */
public class App extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
 
    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }
    
    /*
     * run will exe dropwizard container and register all web services here.
     * */
 
    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");
      //  e.jersey().register(new EmployeeRESTController(e.getValidator()));
        e.jersey().register(new StudentRestAPI());
        e.jersey().register(new UserRestAPI());
    }
 
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}