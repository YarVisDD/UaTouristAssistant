package com.main.uatouristassistant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.uatouristassistant.service.AddressService;
import com.main.uatouristassistant.service.CityService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    HttpServletRequest request;

    @MockBean
    private AddressService addressService;

   /* @MockBean
    private CityController cityController;*/

    @Autowired
    private MockMvc mvc;
    @Test
    void addAddress() {
    }

    @Test
    void addAddressPage() {
    }

    @Test
    void showAllPlacesPage() throws Exception {
        mvc.perform(get("/address/show-addresses"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/addres/show-addresses.jsp"));
    }

    @Test
    void allAddreses() {
    }
}