package com.example.bookmyticket.models;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersData user;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "movie_show_id")
    private MovieShow movieShow;

    public boolean isCancelled() {
        return cancelled;
    }
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
    @Column(nullable = false)
    private boolean cancelled;

    
    public MovieShow getMovieShow() {
        return movieShow;
    }
    public void setMovieShow(MovieShow movieShow) {
        this.movieShow = movieShow;
    }
    public List<Seat> getSeats() {
        return seats;
    }
    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "booking_seat",
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private List<Seat> seats;
    
    private BigDecimal totalPrice;
    private LocalDateTime bookingTime;
    public UsersData getUser() {
        return user;
    }
    public void setUser(UsersData user) {
        this.user = user;
    }
    
  
}
