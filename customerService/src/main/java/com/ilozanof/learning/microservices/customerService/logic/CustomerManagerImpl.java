package com.ilozanof.learning.microservices.customerService.logic;

import com.ilozanof.learning.microservices.customerService.model.CustomerDTO;
import com.ilozanof.learning.microservices.customerService.model.CustomerDef;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Implementatiopn of the Business Logic Interface.
 *
 * For business purposes, this class simulates some fail-scenarios, depending on the values stored in some
 * variables in the configuration file. See details in the class definition
 */
@Component
public class CustomerManagerImpl implements CustomerManager {

    // Specify how probably (0-100) this class will throw an Exception when ay method is invoked.
    @Value("${customerService.fail.probability:0}")
    private Integer failProbability;

    // Specify how probably (0-100) this class will wait for some time when ay method is invoked. the specific
    // time wo wait is specified in the "sleepMilisec" variable
    @Value("${customerService.sleep.probability:0}")
    private Integer sleepProbability;

    // Specify how much time (in milecs), the methods will wait until returning the response.
    @Value("${customerService.sleep.milisec:0}")
    private Integer sleepMilisec;

    // Simulaltes a fail (exception thrown) or a delay...

    private void simulateFailOrSleep() throws Exception {
        if ((failProbability != null) && (sleepProbability != null) && (sleepMilisec != null)) {
            int number = new Random().nextInt(100);
            if (number < failProbability) throw new Exception("Simulated Exception in Customer Service");
            else {
                number = new Random().nextInt(100);
                if (number < sleepProbability) Thread.sleep(sleepMilisec);
            }
        }
    }

    @Override
    public String register(CustomerDTO customer) throws Exception {
        simulateFailOrSleep();
        int customerNumber = new Random().nextInt(CustomerDef.MAX_CUSTOMERS);
        String result = "customer_" + customerNumber;
        return result;
    }

    @Override
    public CustomerDTO getInfo(String login) throws Exception {
        simulateFailOrSleep();
        int customerNumber = new Random().nextInt(CustomerDef.MAX_CUSTOMERS);
        CustomerDTO result = new CustomerDTO("customer_" + customerNumber,
                "John_" + customerNumber, customerNumber);
        return result;
    }
}
