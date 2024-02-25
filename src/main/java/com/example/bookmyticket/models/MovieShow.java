package com.example.bookmyticket.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class MovieShow {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;
    
    private LocalDateTime showTime;
    public String getId() {
        return id;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "movieShow")
    private List<Seat> seats;

    public void setId(String id) {
        this.id = id;
    }
    public Movie getMovie() {
        return movie;
    }
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    public Theater getTheater() {
        return theater;
    }
    public void setTheater(Theater theater) {
        this.theater = theater;
    }
    public LocalDateTime getShowTime() {
        return showTime;
    }
    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }
    public Integer getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }
    private Integer availableSeats;
    

}
