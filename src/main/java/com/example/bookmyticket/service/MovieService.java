package com.example.bookmyticket.service;

import java.util.List;

import com.example.bookmyticket.models.Movie;
import com.example.bookmyticket.models.MovieShow;
import com.example.bookmyticket.models.Theater;

public interface MovieService {

    List<Movie> getAllMovies();

    Movie addMovie(Movie movie);

    Movie getMovieById(String movieId);

    List<MovieShow> getShowsForMovie(String movieId);

    void addMovieToTheater(Movie movie, Theater theater);

}