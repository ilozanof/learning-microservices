package com.ilozanof.learning.microservices.customerService.client;

import com.ilozanof.learning.microservices.customerService.model.CustomerDTO;
import org.junit.*;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;

/**
 * Testing class for the Feoign CustomerService Client
 *
 * NOTE: In this case, we don´t need to use the "application-test.properties", since we
 * can rely on the regular "application.properties" file, which is automatically loaded buy
 * the SpringBoot App.
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableEurekaClient
@EnableFeignClients
@RunWith(SpringRunner.class)
public class CustomerFeignClientTest {

    // IntelliJ IDEA might show an error here ("Could not autowire")
    @Autowired
    private CustomerServiceFeignClient customerServiceClient;

    private static ExecutorService executor = null;


    @Test
    public void testRegister() throws Exception {
        String response = customerServiceClient.register(CustomerDTO.newRandomCustomer());
        assertTrue(response != null);
    }
}
