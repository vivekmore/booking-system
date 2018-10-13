package com.bravolt.bookingsystem.booking;

import lombok.Data;

@Data
public class Room {
    
    private String number;
    private int level = 0;
    private int beds = 1;
    
}
