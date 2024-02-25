package com.example.bookmyticket.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmyticket.models.City;
import com.example.bookmyticket.service.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    //postman- api-4
    @GetMapping("/")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }
    
    //postman- api-3
    @PostMapping("/")
    public ResponseEntity<String> addCity(@RequestBody City city) {
        try {
            City savedCity = cityService.addCity(city);
            return ResponseEntity.ok("City added successfully. City ID: " + savedCity.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add city: " + e.getMessage());
        }
    }

    //postman-api -5
    @GetMapping("/{cityId}")
    public ResponseEntity<City> getCityById(@PathVariable("cityId") String cityId) {
        Optional<City> cityOptional = cityService.getCityById(cityId);
        if (cityOptional.isPresent()) {
            return ResponseEntity.ok(cityOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
}
