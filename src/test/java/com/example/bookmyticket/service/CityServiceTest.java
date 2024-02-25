package com.example.bookmyticket.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.bookmyticket.models.City;
import com.example.bookmyticket.repository.CityRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @Test
    void testAddCity() {
        City mockCity = new City();
        when(cityRepository.save(any(City.class))).thenReturn(mockCity);
        City result = cityService.addCity(new City());
        verify(cityRepository, times(1)).save(any(City.class));
        assertEquals(mockCity, result);
    }

    @Test
    void testGetAllCities() {
    List<City> mockCities = new ArrayList<>();
       when(cityRepository.findAll()).thenReturn(mockCities);
       List<City> result = cityService.getAllCities();
       verify(cityRepository, times(1)).findAll();
       assertEquals(mockCities, result);
    }

    @Test
    void testGetCityById() {
       Optional<City> mockCity= Optional.of(new City());
       when(cityRepository.findById(anyString())).thenReturn(mockCity);
       Optional<City> result= cityService.getCityById(anyString());
       verify(cityRepository, times(1)).findById(anyString());
       assertEquals(mockCity, result);
       
    }
}
