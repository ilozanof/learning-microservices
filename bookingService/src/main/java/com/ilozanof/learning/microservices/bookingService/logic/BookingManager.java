package com.ilozanof.learning.microservices.bookingService.logic;

import com.ilozanof.learning.microservices.bookingService.model.BookingDTO;

public interface BookingManager {
    BookingDTO book(String customerId) throws Exception;
}
