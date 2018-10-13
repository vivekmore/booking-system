package com.bravolt.bookingsystem.booking;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    
    // db
    private final Set<Room> rooms = new HashSet<>();
    private final Set<Booking> bookings = new HashSet<>();
    
    public boolean addRooms(Collection<Room> rooms) {
        return this.rooms.addAll(CollectionUtils.emptyIfNull(rooms));
    }
    
    public Set<Room> getAllRooms() {
        return rooms;
    }
    
    public boolean bookRoom(Booking booking) throws BookingException {
        
        if (booking.getPets() > 2) {
            throw new BookingException("max 2 pets allowed");
        }
        
        if (booking.getPets() > 0 && booking.getRoom().getLevel() != 1) {
            throw new BookingException("pets not allowed on level " + booking.getRoom().getLevel());
        }
        
        return bookings.add(booking);
    }
    
    public Set<Booking> getBookings() {
        return bookings;
    }
    
}
