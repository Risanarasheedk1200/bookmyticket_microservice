package com.example.bookmyticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bookmyticket.models.City;
import com.example.bookmyticket.models.Movie;
import com.example.bookmyticket.models.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,String> {
     @Query("SELECT t.movies FROM Theater t WHERE t.id = :theaterId")
    List<Movie> findMoviesById(@Param("theaterId") String theaterId);

    List<Theater> findByCity(City city);
}
