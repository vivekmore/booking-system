package com.bravolt.bookingsystem.booking;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class BookingException extends Exception {
    
    public BookingException(String s) {
        super(s);
    }
}
