package com.example.bookmyticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmyticket.dto.MovieShowRequest;
import com.example.bookmyticket.models.Booking;
import com.example.bookmyticket.models.City;
import com.example.bookmyticket.models.MovieShow;
import com.example.bookmyticket.models.Seat;
import com.example.bookmyticket.service.MovieShowService;

@RestController
@RequestMapping("/shows")
public class MovieShowController {

    @Autowired
    private MovieShowService movieShowService;

    @GetMapping("/")
    public List<MovieShow> getAllMovieShows() {
        return movieShowService.getAllMovieShows();
    }

    //postman-api-13
    @GetMapping("/{showId}/seats")
    public List<Seat> getAvailableSeats(@PathVariable("showId") String showId) {
        return movieShowService.getAvailableSeats(showId);
    }

    //postman-api-10
    @PostMapping("/movieshows")
    public ResponseEntity<MovieShow> createMovieShow(@RequestBody MovieShowRequest movieShowRequest) {
        MovieShow movieShow = movieShowService.createMovieShow(movieShowRequest);
        return new ResponseEntity<>(movieShow, HttpStatus.CREATED);
    }

    //postman-api-12
    @GetMapping("/movies/{movieId}/theaters/{theaterId}/movieshows")
public ResponseEntity<List<MovieShow>> getMovieShowsForMovieAndTheater(
        @PathVariable("movieId") String movieId,
        @PathVariable("theaterId") String theaterId) {
    List<MovieShow> movieShows = movieShowService.getMovieShowsForMovieAndTheater(movieId, theaterId);
    return ResponseEntity.ok(movieShows);
}

    
}
