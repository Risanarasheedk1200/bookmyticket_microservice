package com.example.bookmyticket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmyticket.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
    Optional<City> findById(String id);
}
