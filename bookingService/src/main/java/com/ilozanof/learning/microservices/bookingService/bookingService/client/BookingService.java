package com.ilozanof.learning.microservices.bookingService.bookingService.client;

import com.ilozanof.learning.microservices.bookingService.bookingService.model.BookingDTO;

public interface BookingService {
    BookingDTO bookFor(String customerId) throws Exception;
}
