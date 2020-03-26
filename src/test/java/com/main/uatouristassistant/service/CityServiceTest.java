package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.repository.CityRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
class CityServiceTest {

    @Autowired
    private CityService cityService;

    @MockBean
    private CityRepository cityRepository;

    private List<City> cityList;

    @Test
    void getAllCity() {
        List<City> allCities = new ArrayList<>();
        City city1 = new City();
        City city2 = new City();

        city1.setCityName("city1");
        city2.setCityName("city2");

        allCities.add(city1);
        allCities.add(city2);

        Mockito.doReturn(allCities).when(cityRepository).findAll();
        List<City> expectedList = cityService.getAllCity();

        Assert.assertEquals(expectedList, allCities);
    }

    @Test
    void deleteCity() {
        City city = new City();
        city.setCityName("cityName");

        Mockito.doReturn(true)
                .when(cityRepository).existsByCityName(city.getCityName());
        Mockito.doReturn(city)
                .when(cityRepository).findByCityName(city.getCityName());
        boolean isCityDeleted = cityService.deleteCity(city.getCityName());

        Assert.assertTrue(isCityDeleted);
        Mockito.verify(cityRepository, Mockito.times(1)).delete(city);
    }

    @Test
    void deleteCityFail() {
        City city = new City();
        city.setCityName("cityName");

        Mockito.doReturn(false)
                .when(cityRepository).existsByCityName(city.getCityName());
        boolean isCityDeleted = cityService.deleteCity(city.getCityName());

        Assert.assertFalse(isCityDeleted);
        Mockito.verify(cityRepository, Mockito.times(0)).delete(city);
    }

    @Test
    void saveCity() {
        City city = new City();
        city.setCityName("cityName");

        Mockito.doReturn(city).when(cityRepository).save(city);
        City expectedCity = cityService.saveCity(city);

        Assert.assertEquals(expectedCity, city);
        Mockito.verify(cityRepository, Mockito.times(1)).save(city);
    }

    @Test
    void findCity() {
        City city = new City();
        city.setCityName("cityName");

        Mockito.doReturn(city)
                .when(cityRepository).findByCityName(city.getCityName());
        City expectedCity = cityService.findCity(city.getCityName());

        Assert.assertEquals(expectedCity, city);
        Mockito.verify(cityRepository, Mockito.times(1)).findByCityName(city.getCityName());
    }

    @Test
    void checkCity() {
        City city = new City();
        city.setCityName("cityName");

        Mockito.doReturn(true)
                .when(cityRepository).existsByCityName(city.getCityName());
        boolean checkCity = cityService.checkCity(city.getCityName());

        Assert.assertTrue(checkCity);

    }

    @Test
    void checkCityFail() {
        City city = new City();
        city.setCityName("cityName");

        Mockito.doReturn(false)
                .when(cityRepository).existsByCityName(city.getCityName());
        boolean checkCity = cityService.checkCity(city.getCityName());

        assertFalse(checkCity);
    }
}
