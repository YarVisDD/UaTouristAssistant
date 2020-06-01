package com.main.uatouristassistant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.uatouristassistant.entity.City;
import com.main.uatouristassistant.service.CityService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CityControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    HttpServletRequest request;

    @MockBean
    private CityService cityService;

    @MockBean
    private CityController cityController;

    @Autowired
    private MockMvc mvc;

    @Test
    void allCity() throws Exception {
        mvc.perform(get("/city/listCity", "cityName"))
                .andExpect(status().isOk())
                .andExpect(redirectedUrl("/listCity"));
    }
    @Test
    void saveCity() throws Exception {
        City city = new City();
        mvc.perform(post("/city/save-city")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(city)))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(redirectedUrl("/city/show-cities"));
    }

    @Test
    void addCity() throws Exception {
        City city = new City();
            Mockito.when(cityController.addCity(city,  request))
                    .thenReturn(String.valueOf(true));

            URI uri = UriComponentsBuilder.fromPath("/city/add-City")
                    .queryParam("cityName", "city")
                    .queryParam(String.valueOf(request))
                    .build().toUri();

        mvc.perform(post(uri))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(forwardedUrl("/add-city.jsp"));
    }
    @Test
    void showAllCityPage() throws Exception {
        mvc.perform(get("/city/show-cities"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/city/show-cities.jsp"));
    }

    @Test
    void updateCity() throws Exception {
        mvc.perform(get("/city/update-city"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/city/update-city.jsp"));
    }

    @Test
    void deleteCity() throws Exception {
        Mockito.when(cityService.deleteCity("cityName")).thenReturn(true);

        mvc.perform(get("/city/delete-city/{cityName}", "cityName"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("city/show-cities"));
    }

    @Test
    void deleteCityFail() throws Exception {
        Mockito.when(cityService.deleteCity("cityName")).thenReturn(false);

        mvc.perform(get("/city/delete-city/{cityName}", "cityName"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/city/delete-city/cityName.jsp"));
        // /error.jsp  ???
    }
}
