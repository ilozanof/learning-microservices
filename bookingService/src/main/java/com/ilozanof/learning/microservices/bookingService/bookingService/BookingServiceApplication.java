package com.ilozanof.learning.microservices.bookingService.bookingService;

import com.ilozanof.learning.microservices.customerService.client.CustomerService;
import com.ilozanof.learning.microservices.customerService.client.CustomerServiceHttpImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


// NOTE: Since in this applicaiton we are using Beans from another JAR projecgt (the customerService), we
// need to specify the packages used for scanning, in the @ComnponentScan Annotation. If we don't do that, the
// beans defined in the "customerService" project will NOT be injected properlyl, since they asre in a different
// package structure. Below you can see the difference:
//
// The package hierarchy for this project:              com.ilozanof.learning.microservices.bookingService
// The package hierarchy for "customerService" project: com.ilozanof.learning.microservices.customerService

// So, in the @ComponentScan property, we use the "parent" package of bothm so beans from both projects are
// injected properly.

@SpringBootApplication
@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@ComponentScan("com.ilozanof.learning.microservices")
public class BookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingServiceApplication.class, args);
	}


}
