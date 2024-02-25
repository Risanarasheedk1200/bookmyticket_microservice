package com.example.bookmyticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmyticket.models.MovieShow;


@Repository
public interface MovieShowRepository extends JpaRepository<MovieShow, String> {
    List<MovieShow> findByTheaterId(String theaterId);
    
    List<MovieShow> findByMovieIdAndTheaterId(String movieId, String theaterId);
    
    List<MovieShow>findByMovieId(String movieId);
}