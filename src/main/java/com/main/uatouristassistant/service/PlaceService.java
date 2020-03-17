package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.Address;
import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.entity.Place;
import com.main.uatouristassistant.entity.PlaceType;
import com.main.uatouristassistant.repository.AddressRepository;
import com.main.uatouristassistant.repository.CityRepository;
import com.main.uatouristassistant.repository.PlaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PlaceImagesService imagesService;

    public void savePlace(String placeName,
                          String placeDescription,
                          PlaceType placeType,
                          MultipartFile[] images,
                          String login,
                          String cityName,
                          String streetName,
                          String numberHouse) {
        City city;

        if (cityRepository.findByCityName(cityName) == null) {
            city = new City();
            city.setCityName(cityName);
            cityRepository.save(city);
            log.info("INFO!!! City has been created. City: {}", cityName);
        } else {
            city = cityRepository.findByCityName(cityName);
        }

        Address address;
        if (addressRepository.findByStreetAndNumberHouse(streetName, numberHouse) == null) {
            address = new Address();
            address.setStreet(streetName);
            address.setNumberHouse(numberHouse);
            address.setStreet(streetName);
            address.setNumberHouse(numberHouse);
            address.setCity(city);
            addressRepository.save(address);
        } else {
            address = addressRepository.findByStreetAndNumberHouse(streetName, numberHouse);
        }

        Place place = new Place();
        place.setPlaceName(placeName);
        place.setPlaceDescription(placeDescription);
        place.setPlaceType(placeType);

        place.setUser(userService.getUser(login));
        place.setAddress(address);
        placeRepository.save(place);

        imagesService.saveImage(place, images);
    }

    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    public Place getPlaceById(Long idPlace) {
        return placeRepository.findPlaceByIdPlace(idPlace);
    }

    public void deletePlace(Long idPlace) {
        try {
            Place place = placeRepository.findPlaceByIdPlace(idPlace);
            placeRepository.delete(place);
            log.info("INFO!!! Place has ben delete: {}", place);
        } catch (Exception ex) {
            log.error("ERROR!!! Tried to delete user which does not exist: {}", idPlace);
        }
    }
}
