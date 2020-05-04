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
import org.springframework.mock.web.MockMultipartFile;
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
    private PlaceRepository mockPlaceRepository;

    @MockBean
    private CityRepository mockCityRepository;

    @MockBean
    private AddressRepository mockAddressRepository;

    @MockBean
    private Place mockPlace;

    @MockBean
    private PlaceType mockPlaceType;

    @MockBean
    private City mockCity;

    @MockBean
    private User mockUser;

    @MockBean
    private Address mockAddress;

    private List<Place> placeList;

    private MultipartFile[] images;

    @Test
    void savePlace() {
        String placeName = "placeName";
        String placeDesc = "placeDesc";
        PlaceType placeType = PlaceType.HOTELS;
        MockMultipartFile image = new MockMultipartFile("file", "test.jpg",
                "image/jpeg", "test image content".getBytes());
        String login = "login";
        String cityName = "cityName";
        String streetName = "streetName";
        String numberHouse = "numberHouse";

        User user = new User();
//        City city = new City();
//        Address address = new Address();
//
//        Mockito.when(mockUserService.getUser(Mockito.anyString())).thenReturn(user);
//
//        placeService.savePlace(placeName, placeDesc, placeType, images, user.getLogin(), city.getCityName(),
//                address.getStreet(), address.getNumberHouse());

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