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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void savePlace(String placeName, String placeDescription, PlaceType placeType, MultipartFile[] images,
                          String login, String cityName, String streetName, String numberHouse) {
        City city;

        if (!cityRepository.existsByCityName(cityName)) {
            city = new City();
            city.setCityName(cityName);
            cityRepository.save(city);
            log.info("INFO!!! City has been created. City: {}", cityName);
        } else {
            city = cityRepository.findByCityName(cityName);
        }

        Address address;
        if (!addressRepository.existsByStreetAndNumberHouse(streetName, numberHouse)) {
            address = new Address();
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

    public List<Place> findByPlaceTypeAndAddress_City_CityName(PlaceType placeType, String city) {
        return placeRepository.findByPlaceTypeAndAddress_City_CityName(placeType, city);
    }

    public List<Place> findByAddress_City_CityName(String city) {
        return placeRepository.findByAddress_City_CityName(city);
    }

    public Place getPlaceById(Long idPlace) {
        return placeRepository.findPlaceByIdPlace(idPlace);
    }

    public boolean deletePlace(Long idPlace) {
        boolean deleteImage = imagesService.deleteImages(idPlace);

        if (deleteImage && placeRepository.existsById(idPlace)) {
            Place place = placeRepository.findPlaceByIdPlace(idPlace);
            placeRepository.delete(place);
            log.info("INFO!!! Place has ben delete: {}", place);
            System.out.println("INFO!!! Place has ben delete: " + place.getPlaceName());
            return true;
        } else {
            log.error("ERROR!!! Tried to delete place which does not exist: {}", idPlace);
            System.out.println("Place not delete");
            return false;
        }
    }

    public String errorMessage(String placeName, String placeDescription, MultipartFile[] images,
                               String cityName, String streetName, String numberHouse) {
        String placeNameOrCityOrStreetRegex = "^[A-zА-яІі0-9 -]{2,30}$";
        String placeDescriptionRegex = "^[A-zА-яІі0-9-\" ]{10,2000}$";
        String numberHouseRegex = "^[0-9]{1,3}[A-zА-яІі]?$";

        Pattern placeNameOrCityOrStreetPattern = Pattern.compile(placeNameOrCityOrStreetRegex);
        Pattern placeDescriptionPattern = Pattern.compile(placeDescriptionRegex);
        Pattern numberHousePattern = Pattern.compile(numberHouseRegex);

        Matcher placeNameMatcher = placeNameOrCityOrStreetPattern.matcher(placeName);
        Matcher cityMatcher = placeNameOrCityOrStreetPattern.matcher(cityName);
        Matcher streetNameMatcher = placeNameOrCityOrStreetPattern.matcher(streetName);
        Matcher placeDescriptionMatcher = placeDescriptionPattern.matcher(placeDescription);
        Matcher numberHouseMatcher = numberHousePattern.matcher(numberHouse);

        if (!placeNameMatcher.matches()) {
            return "Incorrect Place name. Valid characters A-z, А-я, ' ' and '-'";
        } else if (!placeDescriptionMatcher.matches()) {
            return "Incorrect Place description. Minimum length 10 characters. Valid characters A-z, А-я, ' ', '-' and '\"'";
        } else if (!imagesService.isValidFileName(images)) {
            return "Incorrect file extension. Available file extensions: 'png', 'jpg' and 'jpeg'";
        } else if (!cityMatcher.matches()) {
            return "Incorrect City name. Valid characters A-z, А-я, ' ' and '-'";
        } else if (!streetNameMatcher.matches()) {
            return "Incorrect Street name. Valid characters A-z, А-я, ' ' and '-'";
        } else if (!numberHouseMatcher.matches()) {
            return "Incorrect Number house.";
        } else {
            return "";
        }
    }
}
