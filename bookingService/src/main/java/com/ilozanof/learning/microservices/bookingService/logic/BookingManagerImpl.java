package com.ilozanof.learning.microservices.bookingService.logic;

import com.ilozanof.learning.microservices.bookingService.model.BookingDTO;
import com.ilozanof.learning.microservices.customerService.client.CustomerService;
import com.ilozanof.learning.microservices.customerService.model.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingManagerImpl implements BookingManager {

    // Dependency on the CustomerService...
    @Autowired
    CustomerService customerService;

    @Override
    public BookingDTO book(String customerId) throws Exception {
        BookingDTO result = null;

        CustomerDTO customerDTO = customerService.getInfo(customerId);
        if (customerDTO != null) {
            result = new BookingDTO("dummyBookingNumber", customerDTO);
        }

        return result;
    }
}
