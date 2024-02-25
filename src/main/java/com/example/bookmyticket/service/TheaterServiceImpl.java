package com.example.bookmyticket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookmyticket.models.City;
import com.example.bookmyticket.models.Movie;
import com.example.bookmyticket.models.MovieShow;
import com.example.bookmyticket.models.Theater;
import com.example.bookmyticket.repository.CityRepository;
import com.example.bookmyticket.repository.MovieShowRepository;
import com.example.bookmyticket.repository.TheaterRepository;

@Service
public class TheaterServiceImpl implements TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private MovieShowRepository movieShowRepository;

    @Override
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public Theater addTheater(Theater theater) {
         return theaterRepository.save(theater);
    }

    @Override
    public List<Movie> getMoviesInTheater(String theaterId) {
        return theaterRepository.findMoviesById(theaterId);
    }

    @Override
    public List<MovieShow> getShowsInTheater(String theaterId) {
        return movieShowRepository.findByTheaterId(theaterId);
    }

    @Override
    public List<Theater> getTheatersByCity(City city) {
        return theaterRepository.findByCity(city);
    }
    @Override
    public Optional<Theater> getTheaterById(String theaterId) {
       return theaterRepository.findById(theaterId);
    }

    
}