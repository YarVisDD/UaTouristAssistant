package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.*;
import com.main.uatouristassistant.repository.AddressRepository;
import com.main.uatouristassistant.repository.CityRepository;
import com.main.uatouristassistant.repository.PlaceRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class PlaceServiceTest {

    @Autowired
    private PlaceService placeService;

    @MockBean
    private UserService mockUserService;

    @MockBean
    private CityService mockCityService;

    @MockBean
    private AddressService mockAddressService;

    @MockBean
    private PlaceImagesService mockPlaceImagesService;

    @MockBean
    private PlaceRepository mockPlaceRepository;

    @MockBean
    private CityRepository mockCityRepository;

    @MockBean
    private AddressRepository mockAddressRepository;

    @MockBean
    private Place mockPlace;

    @MockBean
    private City mockCity;

    @MockBean
    private User mockUser;

    @MockBean
    private Address mockAddress;

    private List<Place> placeList;

    private MultipartFile[] images;

    @Test
    void savePlace_CityAndAddressExist() {
        String placeName = "palceName";
        String placeDesc = "PlaceDesc";
        PlaceType placeType = PlaceType.HOTELS;
        String login = "login";
        String cityName = "cityName";
        String streetName = "streetName";
        String numberHouse = "numberHouse";

        City city = new City();
        Mockito.when(mockCityRepository.existsByCityName(Mockito.anyString()))
                .thenReturn(true);
        Mockito.when(mockCityService.findCity(Mockito.anyString()))
                .thenReturn(city);

        Address address = new Address();
        Mockito.when(mockAddressRepository.existsByStreetAndNumberHouse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(true);
        Mockito.when(mockAddressService.getAddressByStreetAndNumberHouse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(address);

        User user = new User();
        Mockito.when(mockUserService.getUser(Mockito.anyString()))
                .thenReturn(user);

        Place place = new Place();
        place.setPlaceName(placeName);
        place.setPlaceDescription(placeDesc);
        place.setPlaceType(placeType);
        place.setUser(user);
        place.setAddress(address);

        Mockito.when(mockPlaceRepository.save(Mockito.any(Place.class)))
                .thenReturn(Mockito.any(Place.class));

        placeService.savePlace(placeName, placeDesc, placeType, images, login, cityName, streetName, numberHouse);

        mockPlaceImagesService.saveImage(place, images);

        Mockito.verify(mockPlaceRepository, Mockito.times(1))
                .save(Mockito.any(Place.class));
    }

    @Test
    void savePlace_CityAndAddressNotExist() {
        String placeName = "palceName";
        String placeDesc = "PlaceDesc";
        PlaceType placeType = PlaceType.HOTELS;
        String login = "login";
        String cityName = "cityName";
        String streetName = "streetName";
        String numberHouse = "numberHouse";

        City city = new City();
        Mockito.when(mockCityRepository.existsByCityName(Mockito.anyString()))
                .thenReturn(false);
        Mockito.when(mockCityRepository.save(Mockito.any(City.class)))
                .thenReturn(city);

        Address address = new Address();
        Mockito.when(mockAddressRepository.existsByStreetAndNumberHouse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(false);
        Mockito.when(mockAddressRepository.save(Mockito.any(Address.class)))
                .thenReturn(address);

        User user = new User();
        Mockito.when(mockUserService.getUser(Mockito.anyString()))
                .thenReturn(user);

        Place place = new Place();
        place.setPlaceName(placeName);
        place.setPlaceDescription(placeDesc);
        place.setPlaceType(placeType);
        place.setUser(user);
        place.setAddress(address);

        Mockito.when(mockPlaceRepository.save(Mockito.any(Place.class)))
                .thenReturn(Mockito.any(Place.class));

        placeService.savePlace(placeName, placeDesc, placeType, images, login, cityName, streetName, numberHouse);

        mockPlaceImagesService.saveImage(place, images);

        Mockito.verify(mockCityRepository, Mockito.times(1))
                .save(Mockito.any(City.class));
        Mockito.verify(mockAddressRepository, Mockito.times(1))
                .save(Mockito.any(Address.class));
        Mockito.verify(mockPlaceRepository, Mockito.times(1))
                .save(Mockito.any(Place.class));
    }

    @Test
    void findAll() {
        Mockito.when(mockPlaceRepository.findAll()).thenReturn(placeList);
        placeService.findAll();
    }

    @Test
    void findByPlaceTypeAndAddress_City_CityName() {
        String str = "TestStr";
        PlaceType placeType = PlaceType.HOTELS;
        Mockito.when(mockPlaceRepository.findByPlaceTypeAndAddress_City_CityName(placeType, str)).thenReturn(placeList);
        placeService.findByPlaceTypeAndAddress_City_CityName(placeType, str);
    }

    @Test
    void findByAddress_City_CityName() {
        Mockito.when(mockPlaceRepository.findByAddress_City_CityName(Mockito.anyString()))
                .thenReturn(placeList);
        placeService.findByAddress_City_CityName(Mockito.anyString());
    }

    @Test
    void getPlaceById() {
        Mockito.when(mockPlaceRepository.findPlaceByIdPlace(Mockito.anyLong())).
                thenReturn(mockPlace);
        placeService.getPlaceById(Mockito.anyLong());
    }

    @Test
    void deletePlace() {
        Mockito.when(mockPlaceRepository.existsById(Mockito.anyLong()))
                .thenReturn(true);
        Mockito.when(mockPlaceRepository.findPlaceByIdPlace(mockPlace.getIdPlace()))
                .thenReturn(mockPlace);
        Mockito.doNothing().when(mockPlaceRepository).delete(Mockito.any(Place.class));
        Assert.assertTrue(placeService.deletePlace(mockPlace.getIdPlace()));
        Mockito.verify(mockPlaceRepository, Mockito.times(1))
                .delete(mockPlaceRepository.findPlaceByIdPlace(mockPlace.getIdPlace()));
    }

    @Test
    void deletePLaceFail() {
        Mockito.when(mockPlaceRepository.existsById(Mockito.anyLong()))
                .thenReturn(false);
        Assert.assertFalse(placeService.deletePlace(mockPlace.getIdPlace()));
        Mockito.verify(mockPlaceRepository, Mockito.times(0))
                .delete(mockPlaceRepository.findPlaceByIdPlace(mockPlace.getIdPlace()));
    }
}