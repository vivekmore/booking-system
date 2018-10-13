package com.bravolt.bookingsystem.booking;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;
    
    @GetMapping("rooms")
    public ResponseEntity<Collection<Room>> getRooms() {
        return new ResponseEntity<>(bookingService.getAllRooms(), HttpStatus.OK);
    }
    
    @PostMapping("bookRoom")
    public ResponseEntity<Boolean> bookRoom(@RequestBody Booking booking) throws BookingException {
        return new ResponseEntity<>(bookingService.bookRoom(booking), HttpStatus.OK);
    }
    
}
