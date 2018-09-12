package com.ilozanof.learning.microservices.customerService.client;

import com.ilozanof.learning.microservices.customerService.model.CustomerDTO;
import org.springframework.stereotype.Component;

/**
 * This class implementes the Service interface, but only works as a Fallback, that is it's only invokes when a problem
 * is detected when invoking the methods of the Feignclient (CustomerServiceFiegnClient),
 */

@Component
public class CustomerServiceFallback implements CustomerServiceFeignClient {

    @Override
    public String register(CustomerDTO customer) {
        return "Failed!";
    }

    @Override
    public CustomerDTO getInfo(String login) {
        return new CustomerDTO("Failed", "failed", 0);
    }
}
