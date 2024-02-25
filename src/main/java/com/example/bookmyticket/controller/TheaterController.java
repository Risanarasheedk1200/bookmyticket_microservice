package com.example.bookmyticket.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmyticket.models.City;
import com.example.bookmyticket.models.Movie;
import com.example.bookmyticket.models.MovieShow;
import com.example.bookmyticket.models.Theater;
import com.example.bookmyticket.service.CityService;
import com.example.bookmyticket.service.MovieService;
import com.example.bookmyticket.service.TheaterService;

@RestController
@RequestMapping("/theaters")
public class TheaterController {
private static final Logger logger = LoggerFactory.getLogger(TheaterController.class);

    @Autowired
    private TheaterService theaterService;

    @Autowired
    private CityService cityService;

    @Autowired
    private MovieService movieService;

    //postman-api-6
    @GetMapping("/")
    public List<Theater> getAllTheaters() {
        return theaterService.getAllTheaters();
    }

    //postman-api-7
    @PostMapping("/")
    public ResponseEntity<String> addTheater(@RequestBody Theater theater) {
        try {
            Theater savedTheater = theaterService.addTheater(theater);
            return ResponseEntity.ok("Theater added successfully. Theater ID: " + savedTheater.getId() +"city id"+ savedTheater.getCity().getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add theater: " + e.getMessage());
        }
    }

    //postman-api-9
    @GetMapping("/{theaterId}/movies")
    public List<Movie> getMoviesInTheater(@PathVariable("theaterId") String theaterId) {
        return theaterService.getMoviesInTheater(theaterId);
    }

    @GetMapping("/{theaterId}/shows")
    public List<MovieShow> getShowsInTheater(@PathVariable("theaterId") String theaterId) {
        return theaterService.getShowsInTheater(theaterId);
    }

    @GetMapping("/{cityId}")
public ResponseEntity<List<Theater>> getTheatersInCity(@PathVariable("cityId") String cityId) {
    try { 
        Optional<City> optionalCity = cityService.getCityById(cityId);
        if (optionalCity.isPresent()) {
            City city = optionalCity.get();
            List<Theater> theaters = theaterService.getTheatersByCity(city);
            logger.info("Retrieved theaters for city: {}", city.getName());
            return ResponseEntity.ok(theaters);
        } else {
            logger.error("City not found for ID: {}", cityId);
            return ResponseEntity.notFound().build();
        }
    } catch (Exception e) {
        logger.error("Failed to retrieve theaters for city: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
    }
}

//postman-api-8
@PutMapping("/{theaterId}/movies")
public ResponseEntity<?> addMovieToTheater(@RequestBody Movie movie, @PathVariable("theaterId") String theaterId) {
    try {
        Theater theater = theaterService.getTheaterById(theaterId).get();
        if (theater == null) {
            return ResponseEntity.notFound().build();
        }
        movieService.addMovieToTheater(movie, theater);
        return ResponseEntity.ok().build();
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding movie to theater: " + e.getMessage());
    }
}


}
