package com.example.bookmyticket.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "movie_show_id")
    private MovieShow movieShow;
    
    private Integer row;
    private Integer number;
    private Boolean booked;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public MovieShow getMovieShow() {
        return movieShow;
    }
    public void setMovieShow(MovieShow movieShow) {
        this.movieShow = movieShow;
    }
    public Integer getRow() {
        return row;
    }
    public void setRow(Integer row) {
        this.row = row;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public Boolean getBooked() {
        return booked;
    }
    public void setBooked(Boolean booked) {
        this.booked = booked;
    }
    

}
