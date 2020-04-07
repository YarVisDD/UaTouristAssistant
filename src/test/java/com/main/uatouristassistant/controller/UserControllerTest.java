package com.main.uatouristassistant.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.uatouristassistant.entity.User;
import com.main.uatouristassistant.entity.UserRoles;
import com.main.uatouristassistant.service.UserService;
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
class UserControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    HttpServletRequest request;

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mvc;

    @Test
    void registration() throws Exception {
        mvc.perform(get("/user/registration"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/registration.jsp"));
    }

    @Test
    void addUser() throws Exception {
        Mockito.when(userService.addUser("user", "pass", "user@email.com",
                UserRoles.AUTHOR, "FName", "LName", ""))
                .thenReturn(true);

        URI uri = UriComponentsBuilder.fromPath("/user/addUser")
                .queryParam("login", "user")
                .queryParam("password", "pass")
                .queryParam("email", "user@email.com")
                .queryParam("userRole", UserRoles.AUTHOR)
                .queryParam("firstName", "FName")
                .queryParam("lastName", "LName")
                .queryParam("dateOfBirth", "")
                .build().toUri();

        mvc.perform(post(uri))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void addUserAlreadyRegistered() throws Exception {
        Mockito.when(userService.addUser("user", "pass", "user@email.com",
                UserRoles.AUTHOR, "FName", "LName", ""))
                .thenReturn(false);

        URI uri = UriComponentsBuilder.fromPath("/user/addUser")
                .queryParam("login", "user")
                .queryParam("password", "pass")
                .queryParam("email", "user@email.com")
                .queryParam("userRole", UserRoles.AUTHOR)
                .queryParam("firstName", "FName")
                .queryParam("lastName", "LName")
                .queryParam("dateOfBirth", "")
                .build().toUri();

        mvc.perform(post(uri))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(forwardedUrl("/registration.jsp"));
    }

    @Test
    void addUserLoginEmpty() throws Exception {
        Mockito.when(userService.addUser("", "pass", "user@email.com",
                UserRoles.AUTHOR, "FName", "LName", ""))
                .thenReturn(false);

        URI uri = UriComponentsBuilder.fromPath("/user/addUser")
                .queryParam("login", "")
                .queryParam("password", "pass")
                .queryParam("email", "user@email.com")
                .queryParam("userRole", UserRoles.AUTHOR)
                .queryParam("firstName", "FName")
                .queryParam("lastName", "LName")
                .queryParam("dateOfBirth", "")
                .build().toUri();

        mvc.perform(post(uri))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(forwardedUrl("/registration.jsp"));
    }

    @Test
    void addUserPasswordEmpty() throws Exception {
        Mockito.when(userService.addUser("user", "", "user@email.com",
                UserRoles.AUTHOR, "FName", "LName", ""))
                .thenReturn(false);

        URI uri = UriComponentsBuilder.fromPath("/user/addUser")
                .queryParam("login", "user")
                .queryParam("password", "")
                .queryParam("email", "user@email.com")
                .queryParam("userRole", UserRoles.AUTHOR)
                .queryParam("firstName", "FName")
                .queryParam("lastName", "LName")
                .queryParam("dateOfBirth", "")
                .build().toUri();

        mvc.perform(post(uri))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(forwardedUrl("/registration.jsp"));
    }

    @Test
    void addUserEmailEmpty() throws Exception {
        Mockito.when(userService.addUser("user", "pass", "",
                UserRoles.AUTHOR, "FName", "LName", ""))
                .thenReturn(false);

        URI uri = UriComponentsBuilder.fromPath("/user/addUser")
                .queryParam("login", "user")
                .queryParam("password", "pass")
                .queryParam("email", "")
                .queryParam("userRole", UserRoles.AUTHOR)
                .queryParam("firstName", "FName")
                .queryParam("lastName", "LName")
                .queryParam("dateOfBirth", "")
                .build().toUri();

        mvc.perform(post(uri))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(forwardedUrl("/registration.jsp"));
    }

    @Test
    void loginUser() throws Exception {
        Mockito.when(userService.userLogin("userLogin", "userPass"))
                .thenReturn(true);
        URI uri = UriComponentsBuilder.fromPath("/user/login-user")
                .queryParam("login", "userLogin")
                .queryParam("password", "userPass")
                .build().toUri();
        mvc.perform(post(uri))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(forwardedUrl("/homepage.jsp"));
    }

    @Test
    void loginUserFail() throws Exception {
        Mockito.when(userService.userLogin("userLogin", "userPass"))
                .thenReturn(false);
        URI uri = UriComponentsBuilder.fromPath("/user/login-user")
                .queryParam("login", "userLogin")
                .queryParam("password", "userPass")
                .build().toUri();
        mvc.perform(post(uri))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(forwardedUrl("/login.jsp"));
    }

    @Test
    void showAllUsersPage() throws Exception {
        mvc.perform(get("/user/show-users"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/show-users.jsp"));
    }

    @Test
    void saveUser() throws Exception {
        User user = new User();
        mvc.perform(post("/user/save-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/show-users"));
    }

    @Test
    void showUser() throws Exception {
        mvc.perform(get("/user/listUser/{login}", "userLogin"))
                .andExpect(status().isOk());

    }

    @Test
    void deleteUser() throws Exception {
        Mockito.when(userService.deleteUser("userLogin")).thenReturn(true);

        mvc.perform(get("/user/delete-user/{login}", "userLogin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/show-users"));
    }

    @Test
    void deleteUserFail() throws Exception {
        Mockito.when(userService.deleteUser("userLogin")).thenReturn(false);

        mvc.perform(get("/user/delete-user/{login}", "userLogin"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/error.jsp"));
    }
}
