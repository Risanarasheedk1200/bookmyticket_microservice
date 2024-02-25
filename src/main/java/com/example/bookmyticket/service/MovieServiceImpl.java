package com.example.bookmyticket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookmyticket.models.Movie;
import com.example.bookmyticket.models.MovieShow;
import com.example.bookmyticket.models.Theater;
import com.example.bookmyticket.repository.MovieRepository;
import com.example.bookmyticket.repository.MovieShowRepository;

@Service
public class MovieServiceImpl implements MovieService {

     @Autowired
    private MovieShowRepository movieShowRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovieById(String movieId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        return movieOptional.orElse(null);
    }

    @Override
    public List<MovieShow> getShowsForMovie(String movieId) {
        return movieShowRepository.findByMovieId(movieId);
    }

    @Override
    public void addMovieToTheater(Movie movie, Theater theater) {
        movie.getTheaters().add(theater);
        movieRepository.save(movie);
    }
}
