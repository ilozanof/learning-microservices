package com.ilozanof.learning.microservices.bookingService.client;

import com.ilozanof.learning.microservices.bookingService.logic.BookingManager;
import com.ilozanof.learning.microservices.bookingService.model.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("BookingServiceLocal")
public class BookingServiceLocalImpl implements BookingService {

    @Autowired
    BookingManager bookingManager;

    @Override
    public BookingDTO bookFor(String customerId) throws Exception {
        BookingDTO result = bookingManager.book(customerId);;
        return result;
    }
}
