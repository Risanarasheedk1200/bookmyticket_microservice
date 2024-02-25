package com.example.bookmyticket.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookmyticket.dto.BookingRequest;
import com.example.bookmyticket.models.Booking;
import com.example.bookmyticket.models.MovieShow;
import com.example.bookmyticket.models.Seat;
import com.example.bookmyticket.models.UsersData;
import com.example.bookmyticket.repository.BookingRepository;
import com.example.bookmyticket.repository.MovieShowRepository;
import com.example.bookmyticket.repository.SeatRepository;
import com.example.bookmyticket.repository.UserRepository;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieShowRepository movieShowRepository;

    @Autowired
    private SeatRepository seatRepository;
    
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Booking createBooking(BookingRequest bookingRequest) {
     
        Booking booking = new Booking();
        
        UsersData usersData = userRepository.findById(bookingRequest.getUserId())
        .orElseThrow(() -> new RuntimeException("UsersData not found"));

        booking.setUser(usersData);
        
        MovieShow movieShow= movieShowRepository.findById(bookingRequest.getShowId())
        .orElseThrow(()-> new RuntimeException("Show not found"));
         
        booking.setMovieShow(movieShow);
        
        
        List<Seat> seats = new ArrayList<>();
        for (String seatId : bookingRequest.getSeatIds()) {
            Seat seat = seatRepository.findById(seatId).orElseThrow(()-> new RuntimeException("Seat not found"));
            
        if (seat.getBooked()) {
            throw new RuntimeException("Seat " + seatId + " is already booked.");
        }
      
            seats.add(seat);
            seat.setBooked(true);
            seatRepository.save(seat);
        }
        booking.setSeats(seats);
        
        booking.setBookingTime(LocalDateTime.now()); 
        
        BigDecimal totalPrice = calculateTotalPrice(bookingRequest.getSeatIds()); 
        booking.setTotalPrice(totalPrice);
        
        return bookingRepository.save(booking);
    }
    
    
    private BigDecimal calculateTotalPrice(List<String> seatIds) {
        // Implement logic 
        return BigDecimal.ZERO; 
    }


    @Override
    public Booking getBookingById(String bookingId) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        return bookingOptional.orElse(null); 
    }

    @Override
    public void cancelBooking(String bookingId) {
        //not done completely, need to mark the seats as available also
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            
            booking.setCancelled(true);
            
            bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Booking not found");
        }
    }
}