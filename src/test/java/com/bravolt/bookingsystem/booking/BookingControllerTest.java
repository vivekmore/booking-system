package com.bravolt.bookingsystem.booking;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTest {
    
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper jacksonObjectMapper;
    
    @MockBean
    private BookingService bookingService;
    
    @Test
    public void shouldGetAllRooms() throws Exception {
        
        Room room = new Room();
        room.setNumber("1");
        room.setLevel(2);
        room.setBeds(3);
        
        given(bookingService.getAllRooms()).willReturn(Collections.singleton(room));
        
        mvc.perform(get("/api/rooms").contentType(APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", hasSize(1)))
           .andExpect(jsonPath("$[0].number", is("1")))
           .andExpect(jsonPath("$[0].level", is(2)))
           .andExpect(jsonPath("$[0].beds", is(3)));
    }
    
    @Test
    public void shouldBook() throws Exception {
        
        Booking booking = booking();
        given(bookingService.bookRoom(booking)).willReturn(true);
        
        String content = jacksonObjectMapper.writeValueAsString(booking);
        
        // @formatter:off
        mvc.perform(post("/api/bookRoom").contentType(APPLICATION_JSON_UTF8)
                                         .content(content))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", is(true)));
        // @formatter:on
    }
    
    @Test
    public void shouldForbidInvalidRequests() throws Exception {
        
        Booking booking = booking();
        given(bookingService.bookRoom(booking)).willThrow(new BookingException(null));
        
        String content = jacksonObjectMapper.writeValueAsString(booking);
        
        // @formatter:off
        mvc.perform(post("/api/bookRoom").contentType(APPLICATION_JSON_UTF8)
                                         .content(content))
           .andExpect(status().isForbidden());
        // @formatter:on
    }
    
    private Booking booking() {
        Booking booking = new Booking();
        booking.setPets(2);
        booking.setTo(LocalDate.of(2009, 9, 9));
        booking.setFrom(LocalDate.of(2011, 11, 11));
        return booking;
    }
}
