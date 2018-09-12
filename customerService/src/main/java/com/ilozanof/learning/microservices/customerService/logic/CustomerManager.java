package com.ilozanof.learning.microservices.customerService.logic;

import com.ilozanof.learning.microservices.customerService.model.CustomerDTO;

/**
 * Interface for the business logic implementation.
 *
 * There is no real need to use interface in the Business Logic as we are doing here, nevertheless it's a good
 * practice to do so. The only real interface that we DO really need is the Service Interface, defined in
 * "CustomerService". We can define more interfaces in the business logic or not, depending on our needs, in any
 * cases the classes here won't be directly exposed to clients.
 */

public interface CustomerManager {
    String register(CustomerDTO customer) throws Exception;
    CustomerDTO getInfo(String login) throws Exception;
}
