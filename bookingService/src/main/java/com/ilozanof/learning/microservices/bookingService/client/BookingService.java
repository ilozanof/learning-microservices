package com.ilozanof.learning.microservices.bookingService.client;

import com.ilozanof.learning.microservices.bookingService.model.BookingDTO;

public interface BookingService {
    BookingDTO bookFor(String customerId) throws Exception;
}
