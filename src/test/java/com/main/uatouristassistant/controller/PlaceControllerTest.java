package com.main.uatouristassistant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.uatouristassistant.entity.Place;
import com.main.uatouristassistant.entity.PlaceImage;
import com.main.uatouristassistant.entity.PlaceType;
import com.main.uatouristassistant.service.PlaceImagesService;
import com.main.uatouristassistant.service.PlaceService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PlaceControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    HttpServletRequest request;

    @MockBean
    private PlaceService placeService;

    @MockBean
    private PlaceImagesService imagesService;

    @Autowired
    private MockMvc mvc;

    private MultipartFile[] images;

    private PlaceType placeType;

    private List<Place> placeList;

    private List<PlaceImage> placeImages;

    @Test
    void addPlace() throws Exception {
        URI uri = UriComponentsBuilder.fromPath("/place/save-place")
                .queryParam("placeName", "place name")
                .queryParam("placeDescription", "place description")
                .queryParam("placeType", PlaceType.MUSEUMS)
                .queryParam("image", images)
                .queryParam("login", "login")
                .queryParam("cityName", "city name")
                .queryParam("streetName", "street name")
                .queryParam("numberHouse", "number house")
                .build().toUri();

        mvc.perform(post(uri))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(redirectedUrl("/place/show-places"));
    }

    @Test
    void addPlacePage() throws Exception {
        mvc.perform(get("place/add-place"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/place/add-place.jsp"));
    }

    @Test
    void showPlaceByType_ALL() throws Exception {
        Mockito.when(placeType.equals(PlaceType.ALL)).thenReturn(true);

        Mockito.when(placeService.findByAddress_City_CityName(Mockito.anyString()))
                .thenReturn(placeList);

        Mockito.when(imagesService.getAllImage())
                .thenReturn(placeImages);

        URI uri = UriComponentsBuilder.fromPath("place//show-places-by-type")
                .queryParam("placeType", PlaceType.ALL)
                .queryParam("city", "city")
                .build().toUri();

        mvc.perform(post(uri))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(forwardedUrl("/place/show-places-by-type.jsp"));
    }

    @Test
    void showPlaceByType() throws Exception {
        Mockito.when(placeType.equals(PlaceType.HOTELS)).thenReturn(true);

        Mockito.when(placeService.findByPlaceTypeAndAddress_City_CityName(PlaceType.HOTELS, Mockito.anyString()))
                .thenReturn(placeList);

        Mockito.when(imagesService.getAllImage())
                .thenReturn(placeImages);

        URI uri = UriComponentsBuilder.fromPath("place//show-places-by-type")
                .queryParam("placeType", PlaceType.ALL)
                .queryParam("city", "city")
                .build().toUri();

        mvc.perform(post(uri))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(forwardedUrl("/place/show-places-by-type.jsp"));
    }

    @Test
    void showAllPlacesPage() throws Exception {
        mvc.perform(get("place/show-places"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/place/show-places.jsp"));
    }

    @Test
    void getPlace() throws Exception {
        mvc.perform(get("show-place/{idPlace}", 123))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/place/show-place.jsp"));
    }

    @Test
    void deletePlace() throws Exception {
        Mockito.when(placeService.deletePlace(Mockito.anyLong()))
                .thenReturn(true);

        mvc.perform(get("/place/delete-place", 123))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("place/show-places"));
    }

    @Test
    void deletePlaceFail() throws Exception {
        Mockito.when(placeService.deletePlace(Mockito.anyLong()))
                .thenReturn(false);

        mvc.perform(get("/place/delete-place", Mockito.anyLong()))
                .andExpect(status().isOk())
                .andExpect(redirectedUrl("/error.jsp"));
    }
}