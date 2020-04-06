package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.Address;
import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.repository.AddressRepository;
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

@RunWith(SpringRunner.class)
@SpringBootTest
class AddressServiceTest {
    @Autowired
    private AddressService addressService;

    @Autowired
    private CityService cityService;

    @MockBean
    private CityRepository cityRepository;

    @MockBean
    private AddressRepository addressRepository;

    private List<Address> addressList;
    private List<City> cityList;

    @Test
    void saveAddress() {
        City city = new City();
        city.setCityName("city1");

        Mockito.doReturn(true).when(cityRepository).existsByCityName(city.getCityName());
        Mockito.doReturn(city).when(cityRepository).save(city);

        Address address = new Address();
        address.setCity(city);
        address.setStreet("street1");
        address.setNumberHouse("numberHouse 1");

        Mockito.doReturn(address).when(addressRepository).save(address);
        boolean expectedSaveAddress = addressService.saveAddress(address.getCity().getCityName(), address.getStreet(), address.getNumberHouse());

        Assert.assertTrue(expectedSaveAddress);
        Mockito.verify(addressRepository, Mockito.times(1)).save(address);
    }

    @Test
    void saveAddressFail() {
        City city = new City();
        city.setCityName("");

        Mockito.doReturn(false).when(cityRepository).existsByCityName(city.getCityName());
        Mockito.doReturn(city).when(cityRepository).save(city);

        Address address = new Address();
        address.setCity(city);
        address.setStreet("street1");
        address.setNumberHouse("numberHouse 1");

        Mockito.doReturn(address).when(addressRepository).save(address);
        boolean expectedSaveAddress = addressService.saveAddress(address.getCity().getCityName(), address.getStreet(), address.getNumberHouse());

        Assert.assertFalse(expectedSaveAddress);
        Mockito.verify(addressRepository, Mockito.times(0)).save(address);
    }

    @Test
    void getAllAddresses() {

        List<City> allCities = new ArrayList<>();
        City city1 = new City();
        City city2 = new City();
        city1.setCityName("city1");
        city2.setCityName("city2");

        allCities.add(city1);
        allCities.add(city2);

        List<Address> allAddress = new ArrayList<>();
        Address address1 = new Address();
        Address address2 = new Address();

        address1.setCity(city1);
        address1.setStreet("streetName1");
        address1.setNumberHouse("1");

        address2.setCity(city2);
        address2.setStreet("streetName2");
        address2.setNumberHouse("2");


        allAddress.add(address1);
        allAddress.add(address2);


        Mockito.doReturn(allAddress).when(addressRepository).findAll();
        List<Address> expectedList = addressService.getAllAddresses();

        Assert.assertEquals(expectedList, allAddress);
    }
}
