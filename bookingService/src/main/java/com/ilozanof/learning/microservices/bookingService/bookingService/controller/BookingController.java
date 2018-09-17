package com.ilozanof.learning.microservices.bookingService.bookingService.controller;

import com.ilozanof.learning.microservices.bookingService.bookingService.client.BookingService;
import com.ilozanof.learning.microservices.bookingService.bookingService.model.BookingDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private Logger logger = LoggerFactory.getLogger(BookingController.class);

    // business logic...
    @Autowired
    BookingService bookingService;

    @RequestMapping(method = RequestMethod.GET, value="/bookFor/{customerLogin}", produces = "application/json")
    @ResponseBody
    public BookingDTO book(@PathVariable String customerLogin) throws Exception {
        logger.debug("book endpoint started...");
        BookingDTO result = bookingService.bookFor(customerLogin);
        logger.debug("book endpoint end.");
        return result;
    }
}
