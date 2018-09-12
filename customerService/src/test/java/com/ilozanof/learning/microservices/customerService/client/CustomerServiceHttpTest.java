package com.ilozanof.learning.microservices.customerService.client;

import com.ilozanof.learning.microservices.customerService.model.CustomerDTO;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableEurekaClient
@EnableFeignClients
@RunWith(SpringRunner.class)
public class CustomerServiceHttpTest {
    private Logger logger = LoggerFactory.getLogger(CustomerServiceHttpTest.class);

    @Autowired
    private CustomerService customerService;

    @Test
    public void testGetInfo() throws Exception {

        // We make multiple calls to the service, to check if the Circuit Braker
        // works fine...
        int NUM_CALLS = 10;
            int numFails = 0;
        for (int i = 0; i < NUM_CALLS; i++) {
            CustomerDTO customer = customerService.getInfo("Ivan");
            assertTrue(customer != null);
            if (customer.getLogin().contains("Failed")) numFails++;
        }
        logger.info("testGetInfo finished.");
    }

    @Test
    public void testRegister() throws Exception {

        String login = customerService.register(CustomerDTO.newRandomCustomer());
        assertTrue(login != null && !login.contains("Failed"));
    }
}
