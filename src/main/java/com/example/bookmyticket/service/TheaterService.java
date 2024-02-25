package com.example.bookmyticket.service;

import java.util.List;
import java.util.Optional;

import com.example.bookmyticket.models.City;
import com.example.bookmyticket.models.Movie;
import com.example.bookmyticket.models.MovieShow;
import com.example.bookmyticket.models.Theater;

public interface TheaterService {

    List<Theater> getAllTheaters();

    Theater addTheater(Theater theater);

    List<Movie> getMoviesInTheater(String theaterId);

    List<MovieShow> getShowsInTheater(String theaterId);

    List<Theater> getTheatersByCity(City city);

    Optional<Theater> getTheaterById(String theaterId);

}