package com.ilozanof.learning.microservices.customerService.client;

import com.ilozanof.learning.microservices.customerService.model.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


/**
 * Full Service implementation of the Service interface. It uses internally a Feign Client, which in turns
 * implements cient-load balancing, service discovery and circuit braker capabilities.
 *
 * This is the implementation by default.
 *
 * This is the implementation meant to be used by all the clients of this service, unless the clients is in the
 * same Domains as this sercice and want to use the "local" implementation (CustomerServiceLocalImpl).
 */

@Service
@Primary
public class CustomerServiceHttpImpl implements CustomerService {

    // Feign client for the Customer Service...
    @Autowired
    CustomerServiceFeignClient feignClient;

    @Override
    public String register(CustomerDTO customer) throws Exception {
        String result = feignClient.register(customer);
        return result;
    }

    @Override
    public CustomerDTO getInfo(String login) throws Exception {
        CustomerDTO result = feignClient.getInfo(login);
        return result;
    }
}
