package com.ilozanof.learning.microservices.bookingService.model;

import com.ilozanof.learning.microservices.customerService.model.CustomerDTO;

public class BookingDTO {
    private String bookingId;
    private CustomerDTO customer;


    public BookingDTO(String _bookingId, CustomerDTO _customer) {
        this.bookingId = _bookingId;
        this.customer = _customer;
    }

    public String getBookingId() {
        return bookingId;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }
}
