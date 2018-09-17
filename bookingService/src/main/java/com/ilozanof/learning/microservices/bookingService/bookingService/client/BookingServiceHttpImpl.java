package com.ilozanof.learning.microservices.bookingService.bookingService.client;

import com.ilozanof.learning.microservices.bookingService.bookingService.model.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class BookingServiceHttpImpl implements BookingService {

    @Autowired
    BookingServiceFeignClient bookingFeignclient;

    @Override
    public BookingDTO bookFor(String customerId) throws Exception {
        BookingDTO result = bookingFeignclient.bookFor(customerId);
        return result;
    }
}
