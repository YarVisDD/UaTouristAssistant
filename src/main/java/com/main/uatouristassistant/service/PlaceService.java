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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityRepository cityRepository;

    @Value("${upload.path}")
    private String imagePath;

    String projectDir = new File("").getAbsolutePath();

    public void savePlace(String placeName,
                          String placeDescription,
                          PlaceType placeType,
                          MultipartFile image,
                          String userName,
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

        if (image != null && !image.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(projectDir + imagePath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidImage = UUID.randomUUID().toString();
            String resultImageName = uuidImage + "." + image.getOriginalFilename();

            try {
                image.transferTo(new File(projectDir + imagePath + "/" + resultImageName));
            } catch (IOException e) {
                log.error(e.getMessage());
            }

            place.setImagePath(resultImageName);
        }

        place.setUserName(userName);
        place.setAddress(address);
        placeRepository.save(place);
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
            File file = new File(projectDir + imagePath + "/" + place.getImagePath());
            if (file.delete()) {
                log.info("INFO!!! File {} deleted successfully", file);
            } else {
                log.error("ERROR!!! File not found!");
            }
            placeRepository.delete(place);
            log.info("INFO!!! Place has ben delete: {}", place);
        } catch (Exception ex) {
            log.error("ERROR!!! Tried to delete user which does not exist: {}", idPlace);
        }
    }
}
