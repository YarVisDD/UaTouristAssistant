package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.User;
import com.main.uatouristassistant.entity.UserRoles;
import com.main.uatouristassistant.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping(path = "/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/registration")
    public String registrationPage(HttpServletRequest request) {
        return "registration";
    }

    @PostMapping(path = "/addUser")
    public String addUser(@ModelAttribute User user, HttpServletRequest request) {
        boolean registration = userService.addUser(
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getUserRole(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth());
        if (registration) return "redirect:/login";
        else {
            request.setAttribute("error", "Username or Email already registered");
            return "registration";
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
        request.setAttribute("users", userService.getAllUsers());
        return "/show-users";
    }

    @GetMapping("/listUser/{login}")
    public @ResponseBody
    User showUser(@PathVariable String login) {
        return userService.getUser(login);
    }

    @GetMapping("/delete-user/{login}")
    public String deleteUser(@PathVariable String login) {
        if (userService.deleteUser(login)) return "redirect:/user/show-users";
        else return "/error";
    }

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request) {
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        userService.saveUser(user);
        return "redirect:show-users";
    }

    @RequestMapping("/update-user/{login}")
    public String updateUser(@PathVariable String login, HttpServletRequest request) {
        String sessionLogin = String.valueOf(request.getSession().getAttribute("userLogin"));
        if (!userService.existsByLogin(login) || !userService.existsByLogin(sessionLogin)) return "protected";
        UserRoles sessionUserRole = userService.findUserByLogin(sessionLogin).getUserRole();
        UserRoles userRole = userService.findUserByLogin(login).getUserRole();
        if (!login.equals(sessionLogin) || !userRole.equals(sessionUserRole)) {
            if (!sessionUserRole.equals(UserRoles.ADMIN)) return "protected";
        }
        request.setAttribute("user", userService.findUserByLogin(login));
        return "/update-user";
    }
}
