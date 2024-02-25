package com.example.bookmyticket.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookmyticket.dto.BookingRequest;
import com.example.bookmyticket.models.Booking;

public interface BookingService {

    Booking createBooking(BookingRequest bookingRequest);

    Booking getBookingById(String bookingId);

    void cancelBooking(String bookingId);

}