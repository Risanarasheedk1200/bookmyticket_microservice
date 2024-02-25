package com.example.bookmyticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmyticket.models.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    
}