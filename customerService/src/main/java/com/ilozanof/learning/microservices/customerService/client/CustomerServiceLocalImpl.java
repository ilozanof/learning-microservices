package com.ilozanof.learning.microservices.customerService.client;

import com.ilozanof.learning.microservices.customerService.logic.CustomerManager;
import com.ilozanof.learning.microservices.customerService.model.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Local Implementation of the Service Interface.
 *
 * This implementation is only intended to be used by a Client that is deployed in the same Domain/Environment, and
 * it might improve the performance due to the fact that there are not NET communications (not HTTP request).
 *
 * This implementatiopn used directly the Bussiness Logic of this service to do the job.
 *
 * If you want to use this implementation, you need to inject the interface this way:
 *
 *  @Autowired
 *  @Qualifier("CustomerServiceLocal")
 *  CustomerService customerService;
 */

@Component
@Qualifier("CustomerServiceLocal")
public class CustomerServiceLocalImpl implements CustomerService {

    @Autowired
    CustomerManager customerManager;

    @Override
    public String register(CustomerDTO customer) throws Exception {
        String result = customerManager.register(customer);
        return result;
    }

    @Override
    public CustomerDTO getInfo(@PathVariable("login") String login) throws Exception {
        CustomerDTO result = customerManager.getInfo(login);
        return result;
    }
}
