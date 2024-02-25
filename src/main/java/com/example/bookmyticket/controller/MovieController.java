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

import com.example.bookmyticket.models.Movie;
import com.example.bookmyticket.models.MovieShow;
import com.example.bookmyticket.service.MovieService;
import com.example.bookmyticket.service.TheaterService;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private TheaterService theaterService;
    
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
    @PostMapping("/")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        try {
            Movie savedMovie = movieService.addMovie(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/{movieId}")
    public Movie getMovieById(@PathVariable("movieId") String movieId) {
        return movieService.getMovieById(movieId);
    }

    @GetMapping("/{movieId}/shows")
    public List<MovieShow> getShowsForMovie(@PathVariable("movieId") String movieId) {
        return movieService.getShowsForMovie(movieId);
    }

    
}
