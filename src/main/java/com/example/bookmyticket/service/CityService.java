package com.example.bookmyticket.service;

import java.util.List;
import java.util.Optional;

import com.example.bookmyticket.models.City;

public interface CityService {

    List<City> getAllCities();

    City addCity(City city);

    Optional<City> getCityById(String id);

}