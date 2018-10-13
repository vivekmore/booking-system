package com.bravolt.bookingsystem;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bravolt.bookingsystem.booking.BookingService;
import com.bravolt.bookingsystem.booking.Room;

@SpringBootApplication
public class BookingSystemApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(BookingSystemApplication.class, args);
    }
    
    @Bean
    CommandLineRunner dataLoader(BookingService bookingService) {
        return args -> bookingService.addRooms(initialRooms());
    }
    
    @Bean
    Collection<Room> initialRooms() {
        Collection<Room> rooms = new ArrayList<>();
        rooms.add(buildRoom("101", 1, 1));
        rooms.add(buildRoom("102", 1, 1));
        rooms.add(buildRoom("103", 1, 2));
        rooms.add(buildRoom("104", 1, 3));
        rooms.add(buildRoom("201", 2, 1));
        rooms.add(buildRoom("201", 2, 1));
        rooms.add(buildRoom("201", 2, 2));
        rooms.add(buildRoom("201", 2, 3));
        return rooms;
    }
    
    private Room buildRoom(String roomNumber, int level, int beds) {
        Room room = new Room();
        room.setNumber(roomNumber);
        room.setLevel(level);
        room.setBeds(beds);
        return room;
    }
}
