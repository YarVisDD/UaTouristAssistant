package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.Address;
import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.repository.AddressRepository;
import com.main.uatouristassistant.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService<list> {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityRepository cityRepository;

    public void saveAddress( String cityName,
                               String streetName,
                               String numberHouse) {

        City city;
        if (cityRepository.findByCityName(cityName) == null) {
            city = new City();
            city.setCityName(cityName);
            cityRepository.save(city);
        } else {
            city = cityRepository.findByCityName(cityName);
        }

        Address address = new Address();
        address.setCity(city);
        address.setStreet(streetName);
        address.setNumberHouse(numberHouse);
        addressRepository.save(address);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }
}