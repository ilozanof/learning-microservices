package com.ilozanof.learning.microservices.customerService.controller;

import com.ilozanof.learning.microservices.customerService.model.CustomerDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Test class for the CustomerService Controller
 *
 * The "@SpringBootTest" takes care of running the main SpringBoot application first, which in turn starts up
 * the Web Server and runs the controllers. So we can assume that in our tests, the application will be already
 * running. We also configure the Server to run in a random port, since we don't really care, and we inject the
 * port number by using the @LocalServerport annotation.
 *
 * TODO: Check what happens if we run 2 servers in the same machine, (will the @LocalServerPort still work fine?).
 */

// NOTE: The URL of the Services are declared in the 'application-test.properties' file, which is loaded by
// the "@TestPropertySource" annotation.

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class CustomerControllerTest {

    Logger logger = LoggerFactory.getLogger(CustomerControllerTest.class);

    @LocalServerPort
    int serverPort;

    @Value("${customerService.getInfo.url}")
    String getInfoUrl;

    @Value("${customerService.register.url}")
    String registerUrl;

    private String registerUser(CustomerDTO customer) throws Exception {
        String result = null;
        String url = "http://localhost:" + serverPort + registerUrl;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, customer, String.class);
        result = response.getBody();
        return result;
    }

    @Test
    public void testRegister() throws Exception {
        String response = registerUser(CustomerDTO.newRandomCustomer());
        assertTrue(response != null && response.startsWith("login"));
    }

    @Test
    public void testGetInfo() throws Exception {

        // First, we register a User...
        String registerResponse = registerUser(CustomerDTO.newRandomCustomer());

        // Now we retrieve its information...
        String url = "http://localhost:" + serverPort + getInfoUrl + registerResponse;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomerDTO> response = restTemplate.getForEntity(url, CustomerDTO.class);
        assertTrue(response != null && response.getBody() != null);
    }
}
