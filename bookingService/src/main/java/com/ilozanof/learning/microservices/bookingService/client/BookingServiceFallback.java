package com.ilozanof.learning.microservices.bookingService.client;

import com.ilozanof.learning.microservices.bookingService.model.BookingDTO;
import com.ilozanof.learning.microservices.customerService.model.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class BookingServiceFallback implements BookingServiceFeignClient {
    @Override
    public BookingDTO bookFor(String customerId) throws Exception {
        BookingDTO result = new BookingDTO("Failing!", new CustomerDTO("Failing", "Failing", 0));
        return result;
    }
}
