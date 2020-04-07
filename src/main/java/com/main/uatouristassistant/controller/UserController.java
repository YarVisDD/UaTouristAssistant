package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.User;
import com.main.uatouristassistant.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequestMapping(path = "/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping(path = "/addUser")
    public String addUser(@ModelAttribute User user, HttpServletRequest request) {
        if (user.getLogin().isEmpty()) {
            request.setAttribute("error", "Username is empty");
            return "registration";
        } else if (user.getPassword().isEmpty()) {
            request.setAttribute("error", "Password is empty");
            return "registration";
        } else if (user.getEmail().isEmpty()) {
            request.setAttribute("error", "Email is empty");
            return "registration";
        } else {
            boolean registration = userService.addUser(
                    user.getLogin(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getUserRole(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getDateOfBirth());
            if (registration) {
                return "redirect:/login";
            } else {
                request.setAttribute("error", "Username or Email already registered");
                return "registration";
            }
        }
    }

    @PostMapping("/login-user")
    public String loginUser(@ModelAttribute User user, HttpServletRequest request) {
        if (userService.userLogin(user.getLogin(), user.getPassword())) {
            return "homepage";
        } else {
            request.setAttribute("error", "Invalid Username or Password");
            return "login";
        }
    }

    @GetMapping("/show-users")
    public String showAllUsersPage(HttpServletRequest request) {
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        return "show-users";
    }

    @GetMapping("/listUser/{login}")
    public @ResponseBody
    User showUser(@PathVariable String login) {
        return userService.getUser(login);
    }

    @GetMapping("/delete-user/{login}")
    public String deleteUser(@PathVariable String login) {
        if (userService.deleteUser(login)) return "redirect:/user/show-users";
        else return "error";
    }

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request) {
        userService.saveUser(user);
        return "redirect:/user/show-users";
    }

    @GetMapping("/update-user/{login}")
    public String updateUser(@PathVariable String login, HttpServletRequest request) {
        if (userService.updateUser(login, request)) {
            return "update-user";
        } else {
            return "protected";
        }
    }
}
