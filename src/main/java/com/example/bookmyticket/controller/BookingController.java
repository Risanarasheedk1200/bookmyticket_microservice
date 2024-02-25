package com.example.bookmyticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmyticket.dto.BookingRequest;
import com.example.bookmyticket.models.Booking;
import com.example.bookmyticket.service.BookingServiceImpl;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingService;

    //postman -api -14
    @PostMapping("/create")
    public Booking createBooking(@RequestBody BookingRequest bookingRequest) {
        return bookingService.createBooking(bookingRequest);
    }
    

    //utility apis

    @GetMapping("/{bookingId}")
    public Booking getBookingById(@PathVariable("bookingId") String bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @DeleteMapping("/{bookingId}/cancel")
    public void cancelBooking(@PathVariable("bookingId") String bookingId) {
        bookingService.cancelBooking(bookingId);
    }
}
