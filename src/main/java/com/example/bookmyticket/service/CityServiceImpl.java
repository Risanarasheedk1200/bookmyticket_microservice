package com.example.bookmyticket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookmyticket.models.City;
import com.example.bookmyticket.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    @SuppressWarnings("null")
    public City addCity(City city) {
        
        return cityRepository.save(city);
    }

    @Override
    public Optional<City> getCityById(String id) {
        return cityRepository.findById(id);
    }
    
    
   
}
