package com.ilozanof.learning.microservices.bookingService.client;

import com.ilozanof.learning.microservices.bookingService.model.BookingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("bookingService")
public interface BookingServiceFeignClient {
    @RequestMapping(method = RequestMethod.GET, value="/bookFor/{customerLogin}", produces = "application/json")
    @ResponseBody
    BookingDTO bookFor(String customerId) throws Exception;
}
