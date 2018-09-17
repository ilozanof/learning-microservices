package com.ilozanof.learning.microservices.bookingService.bookingService.client;

import com.ilozanof.learning.microservices.bookingService.bookingService.logic.BookingManager;
import com.ilozanof.learning.microservices.bookingService.bookingService.model.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingServiceLocalImpl implements BookingService {

    @Autowired
    BookingManager bookingManager;

    @Override
    public BookingDTO bookFor(String customerId) throws Exception {
        BookingDTO result = bookingManager.book(customerId);;
        return result;
    }
}
