package com.example.bookmyticket.service;

import java.util.List;

import com.example.bookmyticket.dto.MovieShowRequest;
import com.example.bookmyticket.models.MovieShow;
import com.example.bookmyticket.models.Seat;

public interface MovieShowService {

    List<MovieShow> getAllMovieShows();

    List<Seat> getAvailableSeats(String showId);

    MovieShow createMovieShow(MovieShowRequest movieShowRequest);

    void createSeatsForMovieShow(MovieShow movieShow);

    List<MovieShow> getMovieShowsForMovieAndTheater(String movieId, String theaterId);

}