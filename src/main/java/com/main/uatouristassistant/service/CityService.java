package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCity() {
        return cityRepository.findAll();
    }
}
