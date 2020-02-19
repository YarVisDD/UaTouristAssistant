package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.User;
import com.main.uatouristassistant.entity.UserAdminRoles;
import com.main.uatouristassistant.entity.UserRoles;
import com.main.uatouristassistant.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/addUser")
    public @ResponseBody
    boolean addUser(@RequestParam String login,
                    @RequestParam String password,
                    @RequestParam String email,
                    @RequestParam UserRoles userRole,
                    String firstName,
                    String lastName,
                    String dateOfBirth) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(DigestUtils.sha256Hex(password));
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(dateOfBirth);
        user.setUserRole(userRole);
        userRepository.save(user);
        return true;
    }

    @PostMapping(path = "/addAdmin")
    public @ResponseBody
    boolean addAdmin(@RequestParam String login,
                     @RequestParam String password,
                     @RequestParam String email,
                     @RequestParam UserAdminRoles userAdmin,
                     String firstName,
                     String lastName,
                     String dateOfBirth) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(DigestUtils.sha256Hex(password));
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(dateOfBirth);
        user.setUserAdmin(userAdmin);
        userRepository.save(user);
        return true;
    }

    @PostMapping(path = "/login")
    public @ResponseBody
    boolean userLogin(@RequestParam String login, @RequestParam String password) {
        boolean loginInfo = false;
        User user;
        try {
            user = userRepository.findByLogin(login);
            String loginFromDb = user.getLogin();
            String passFromDb = user.getPassword();
            if ((login.equals(loginFromDb)) && (DigestUtils.sha256Hex(password).equals(passFromDb))) {
                loginInfo = true;
            }
        } catch (NullPointerException ex) {
            System.out.println("User with the login " + login + " is not registered");
        }
        return loginInfo;
    }

    @GetMapping(path = "/list")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/listUser")
    public @ResponseBody
    User getUser(@RequestParam Long userId) {
        return userRepository.findByUserId(userId);
    }

    @PostMapping(path = "/updatePass")
    public @ResponseBody
    String updatePassword(@RequestParam Long userId, @RequestParam String password) {
        try {
            User user = userRepository.findByUserId(userId);
            user.setPassword(DigestUtils.sha256Hex(password));
            userRepository.save(user);
            return "Password updated";
        } catch (NullPointerException ex) {
            return "The user with id " + userId + " does not exist!";
        }
    }

    @DeleteMapping(path = "/delUser")
    public @ResponseBody
    String deleteUser(@RequestParam Long userId) {
        try {
            User user = userRepository.findByUserId(userId);
            userRepository.delete(user);
            return "The user with userId " + userId + " has been deleted";
        } catch (Exception ex) {
            return "The user with userId " + userId + " does not exist!";
        }
    }
}
