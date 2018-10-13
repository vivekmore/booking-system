package com.bravolt.bookingsystem.booking;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class BookingServiceTest {
    
    private BookingService sut;
    
    @Before
    public void setUp() {
        sut = new BookingService();
    }
    
    @Test
    public void shouldAddRoom() {
        assertTrue(sut.addRooms(Collections.singleton(new Room())));
    }
    
    @Test
    public void shouldReturnNoRoomsInitially() {
        
        Set<Room> allRooms = sut.getAllRooms();
        
        assertThat(allRooms, is(empty()));
    }
    
    @Test
    public void shouldReturnRoom() {
        
        Room room = new Room();
        sut.addRooms(Collections.singleton(room));
        
        Set<Room> allRooms = sut.getAllRooms();
        
        assertThat(allRooms, contains(room));
    }
    
    @Test
    public void shouldBookRoom() throws Exception {
        
        Booking booking = new Booking();
        
        sut.bookRoom(booking);
        
        assertThat(sut.getBookings(), contains(booking));
    }
    
    @Test(expected = BookingException.class)
    public void shouldThrowExceptionForBookingWithMoreThan2Pets() throws Exception {
        
        Booking booking = new Booking();
        booking.setPets(3);
        
        sut.bookRoom(booking);
    }
    
    @Test(expected = BookingException.class)
    public void shouldNotAllowPetsOutsideLevel1() throws Exception {
        
        Room room = new Room();
        room.setLevel(2);
        
        Booking booking = new Booking();
        booking.setPets(2);
        booking.setRoom(room);
        
        sut.bookRoom(booking);
    }
    
}
