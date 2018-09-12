package com.ilozanof.learning.microservices.customerService.controller;

import com.ilozanof.learning.microservices.customerService.client.CustomerService;
import com.ilozanof.learning.microservices.customerService.model.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * Controller that defines the REST endpoints for this Service.
 */

// NOTE:
// The endpoints defined here (their URLs) must match the ones defined in the FeignClient (CustomerServiceFeignclient)

@RestController
@RequestMapping("/customer")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    @Qualifier("CustomerServiceLocal")
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.POST, value = "/register", produces = "application/json")
    public String register(CustomerDTO customer) throws Exception {
        logger.debug("register endpoint Starting...");
        String result = customerService.register(customer);
        logger.debug("register endpoint end.");
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/info/{login}", produces = "application/json")
    @ResponseBody
    public CustomerDTO getInfo(@PathVariable String login) throws Exception{
        logger.debug("getInfo endpoint Starting...");
        CustomerDTO result = customerService.getInfo(login);
        logger.debug("getInfo endpoint end.");
        return result;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) throws Exception {
        logger.error("ERROR in CustomerService Controller: " + e.getMessage());
        throw e;
    }

}
