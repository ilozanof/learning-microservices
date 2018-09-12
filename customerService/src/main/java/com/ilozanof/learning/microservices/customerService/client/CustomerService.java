package com.ilozanof.learning.microservices.customerService.client;

import com.ilozanof.learning.microservices.customerService.model.CustomerDTO;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Customer Service Interface.
 *
 * This is the Interface that will be used by anyone how wants to make use of this Service. It will typically be
 * injected using the @Autowired annotation.
 *
 * We have 2 options to inject this interface:
 * - @Autowired
 *   By default, the CustomerServiceHttpImpl implementation will be injected, which:
 *       - uses the Eureka Server for Service.Discovery
 *       - uses a FeignClient and Ribbon for Service invocation and client-load balancing
 *       - uses Hystrix as a Fallback and Circuit Braker implementation
 *
 * - @Autowired
 * - @Qualifier("CustomerServiceLocal")
 *   The CustomerServiceLocalImpl implementation is injected, which calls directly the business logic. It does not
 *   provide any load balancing, fault tolerance or Service Discovery capabilities.
 */

public interface CustomerService {

    // Register a Customer and returns its login
    String register(CustomerDTO customer) throws Exception;

    // Return information about the user reguested.
    CustomerDTO getInfo(@PathVariable("login") String login) throws Exception;
}
