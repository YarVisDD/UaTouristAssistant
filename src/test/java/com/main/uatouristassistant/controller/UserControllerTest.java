package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    void addUser() throws Exception {
      // Mockito.when(userRepository.existsByLogin(eq("testUser")))
        //        .thenReturn(true);

        URI uri = UriComponentsBuilder.fromPath("/user/addUser")
                .queryParam("login", "testUser")
                .queryParam("password", "")
                .queryParam("email", "")
                .queryParam("userRole", "")
                .build().toUri();

        mvc.perform(MockMvcRequestBuilders.post(uri))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
//                .andExpect( MockMvcResultMatchers.jsonPath("$", MockMvcResultMatchers.redirectedUrl("/login.jsp")));
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is("testUser - already REGISTERED. Please try with another LOGIN")));
    }
}
