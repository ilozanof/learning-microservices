package com.ilozanof.learning.microservices.eurekaServer.eurekaServer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This Controller is NOT needed at all in the eureka Server. It's only here for
 * testing if the use of the "swagger" library for documentation works properly. We
 * define a REST endpoint and we commented it using the Swagger annotations.
 */
@RestController
@RequestMapping("/testing-swagger")
public class EurekaController {

    Logger logger = LoggerFactory.getLogger(EurekaController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/ping2", produces = "text/plain")
    @ResponseBody
    public String ping() throws Exception {
        return "ping from Eureka Server";
    }

}
