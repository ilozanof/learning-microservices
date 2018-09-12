package com.ilozanof.learning.microservices.customerService.client;

import com.ilozanof.learning.microservices.customerService.model.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Feign client for thjis sercice, which defines an interface over the endpoints of this Service, as they are
 * defined in the Controller (CustomerServiceController).
 *
 */

// NOTE:
// Make sure you use the right URLs in the methods below. Sometimes, if you copy these urls from
// the Controller, you might forget some URLs defined at a controller level, not at method level

// When using along with Ribbon and Eureka, the "value" attribute must mach the name of the
// Service we are consuming (that name is defined in the "spring.application.name" property in the
// service's application.properties file).


@FeignClient(value = "customerService", fallback = CustomerServiceFallback.class)
public interface CustomerServiceFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/customer/register", produces = "application/json")
    String register(CustomerDTO customer);

    // NOTE:
    // When using @PathVariable in a Feign client, we NEED to provide the NAME of the variable in the
    // annotation, otherwise we'll get an error ("..PathVariable annotation was empty..")

    @RequestMapping(method = RequestMethod.GET, value = "/customer/info/{login}", produces = "application/json")
    @ResponseBody
    CustomerDTO getInfo(@PathVariable("login") String login);
}
