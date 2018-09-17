package com.ilozanof.learning.microservices.bookingService.bookingService.logic;

import com.ilozanof.learning.microservices.bookingService.bookingService.model.BookingDTO;
import com.ilozanof.learning.microservices.customerService.model.CustomerDTO;

public interface BookingManager {
    BookingDTO book(String customerId) throws Exception;
}
