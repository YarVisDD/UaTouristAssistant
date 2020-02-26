package com.main.uatouristassistant.controller;

import com.main.uatouristassistant.entity.User;
import com.main.uatouristassistant.entity.UserAdminRoles;
import com.main.uatouristassistant.entity.UserRoles;
import com.main.uatouristassistant.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/addUser")
    public @ResponseBody
    String addUser(@RequestParam String login,
                   @RequestParam String password,
                   @RequestParam String email,
                   @RequestParam UserRoles userRole,
                   String firstName,
                   String lastName,
                   String dateOfBirth) {
        String addUserInfo;
        boolean checkLogin = false;
        User user = new User();
        User userLoginDb;
        try {
            userLoginDb = userRepository.findByLogin(login);
            if (userLoginDb.getLogin().equals(login)) {
                checkLogin = true;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        if (checkLogin) {
            addUserInfo = login + " - already REGISTERED. Please try with another LOGIN";
            log.warn("WARNING!!! User tried to be registered with existing login. Login: {}", login);
        } else {
            user.setLogin(login);
            user.setPassword(DigestUtils.sha256Hex(password));
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setDateOfBirth(dateOfBirth);
            user.setUserRole(userRole);
            userRepository.save(user);
            addUserInfo = login + " has been REGISTERED!";
            log.info("INFO!!! User has been created. User: {}", user);
        }
        return addUserInfo;
    }

    @PostMapping(path = "/addAdmin")
    public @ResponseBody
    String addAdmin(@RequestParam String login,
                    @RequestParam String password,
                    @RequestParam String email,
                    @RequestParam UserAdminRoles userAdmin,
                    String firstName,
                    String lastName,
                    String dateOfBirth) {
        String addUserInfo;
        boolean checkLogin = false;
        User user = new User();
        User userLoginDb;
        try {
            userLoginDb = userRepository.findByLogin(login);
            if (userLoginDb.getLogin().equals(login)) {
                checkLogin = true;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            log.error("ERROR!!! User: " + user +
                    "Exception: " + ex);
        }

        if (checkLogin) {
            addUserInfo = login + " - already REGISTERED. Please try with another LOGIN";
            log.warn("WARNING!!! User tried to be registered with existing login. Login: {}", login);
        } else {
            user.setLogin(login);
            user.setPassword(DigestUtils.sha256Hex(password));
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setDateOfBirth(dateOfBirth);
            user.setUserAdmin(userAdmin);
            userRepository.save(user);
            addUserInfo = login + " has been REGISTERED!";
            log.info("INFO!!! User has been created. User: {}", user);
        }
        return addUserInfo;
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
                log.info("INFO!!! User logged in: {}", login);
            } else {
                System.out.println("User with the login " + login + " is not registered");
                log.warn("WARNING!!! Failed login. User is not registered. Login: {}", login);
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            log.error("Exception: {}", ex);
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
            log.info("INFO!!! Password has been updated for user Id {}", userId);
            return "Password updated";
        } catch (NullPointerException ex) {
            log.error("ERROR!!! Update password for incorrect User Id {}", userId);
            return "The user with id " + userId + " does not exist!";
        }
    }

    @DeleteMapping(path = "/delUser")
    public @ResponseBody
    String deleteUser(@RequestParam Long userId) {
        try {
            User user = userRepository.findByUserId(userId);
            userRepository.delete(user);
            log.info("INFO!!! User has ben deleted: {}", user);
            return "The user with userId " + userId + " has been deleted";
        } catch (Exception ex) {
            log.error("ERROR!!! Tried to delete user which does not exist: {}", userId);
            return "The user with userId " + userId + " does not exist!";
        }
    }
}
