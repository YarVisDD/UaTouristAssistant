package com.main.uatouristassistant.service;

import com.main.uatouristassistant.entity.User;
import com.main.uatouristassistant.entity.UserRoles;
import com.main.uatouristassistant.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
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
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void addUser() {
        String login = "testUser";
        String password = "testUserPassword";
        String email = "user@test.email";
        UserRoles userRole = UserRoles.AUTHOR;
        String firstName = "";
        String lastName = "";
        String dateOfBirth = "";

        User user = new User();
        user.setLogin(login);
        user.setPassword(DigestUtils.sha256Hex(password));
        user.setEmail(email);
        user.setUserRole(userRole);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(dateOfBirth);

        Mockito.doReturn(false)
                .when(userRepository)
                .existsByLoginOrEmail("testUser", "user@test.email");

        boolean isUserCreated = userService.addUser(login, password, email, userRole, firstName, lastName, dateOfBirth);


        Assert.assertTrue(isUserCreated);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void addUserWhenUserExist() {
        String login = "testUser";
        String password = "testUserPassword";
        String email = "user@test.email";
        UserRoles userRole = UserRoles.AUTHOR;
        String firstName = "";
        String lastName = "";
        String dateOfBirth = "";

        User user = new User();
        user.setLogin(login);
        user.setPassword(DigestUtils.sha256Hex(password));
        user.setEmail(email);
        user.setUserRole(userRole);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateOfBirth(dateOfBirth);

        Mockito.doReturn(true)
                .when(userRepository)
                .existsByLoginOrEmail("testUser", "user@test.email");

        boolean isUserCreated = userService.addUser(login, password, email, userRole, firstName, lastName, dateOfBirth);

        Assert.assertFalse(isUserCreated);
        Mockito.verify(userRepository, Mockito.times(0)).save(user);

    }

    @Test
    void addUserFailWithEmptyUser() {
        User user = new User();
        user.setLogin("");
        user.setPassword(DigestUtils.sha256Hex("testUserPassword"));
        user.setEmail("user@test.email");
        user.setUserRole(UserRoles.AUTHOR);

        boolean isUserCreated = userService.addUser(
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getUserRole(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth()
        );

        Assert.assertFalse(isUserCreated);
        Mockito.verify(userRepository, Mockito.times(0)).save(user);
    }

    @Test
    void addUserFailWithEmptyPassword() {
        User user = new User();
        user.setLogin("testUser");
        user.setPassword("");
        user.setEmail("user@test.email");
        user.setUserRole(UserRoles.AUTHOR);

        boolean isUserCreated = userService.addUser(
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getUserRole(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth()
        );

        Assert.assertFalse(isUserCreated);
        Mockito.verify(userRepository, Mockito.times(0)).save(user);
    }

    @Test
    void addUserFailWithEmptyEmail() {
        User user = new User();
        user.setLogin("testUser");
        user.setPassword(DigestUtils.sha256Hex("testUserPassword"));
        user.setEmail("");
        user.setUserRole(UserRoles.AUTHOR);

        boolean isUserCreated = userService.addUser(
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getUserRole(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth()
        );

        Assert.assertFalse(isUserCreated);
        Mockito.verify(userRepository, Mockito.times(0)).save(user);
    }

    @Test
    void userLogin() {
        String login = "userTest";
        String password = "userPass";

        Mockito.doReturn(true)
                .when(userRepository)
                .existsByLoginAndPassword(login, DigestUtils.sha256Hex(password));

        boolean isUserLogin = userService.userLogin(login, password);
        Assert.assertTrue(isUserLogin);
    }

    @Test
    void userLoginFail() {
        String login = "userTest";
        String password = "userPass";

        Mockito.doReturn(false)
                .when(userRepository)
                .existsByLoginAndPassword(login, DigestUtils.sha256Hex(password));

        boolean isUserLogin = userService.userLogin(login, password);
        Assert.assertFalse(isUserLogin);
    }

    @Test
    void getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();

        user1.setLogin("user1");
        user1.setPassword(DigestUtils.sha256Hex("pass1"));
        user1.setEmail("email1@test.com");
        user1.setUserRole(UserRoles.AUTHOR);

        user2.setLogin("user2");
        user2.setPassword(DigestUtils.sha256Hex("pass2"));
        user2.setEmail("email2@test.com");
        user2.setUserRole(UserRoles.AUTHOR);

        allUsers.add(user1);
        allUsers.add(user2);

        Mockito.doReturn(allUsers).when(userRepository).findAll();
        List<User> expectedList = userService.getAllUsers();

        Assert.assertEquals(expectedList, allUsers);
    }

    @Test
    void getUser() {
        User user = new User();
        user.setLogin("userLogin");
        user.setPassword(DigestUtils.sha256Hex("userPass"));
        user.setEmail("email@test.email");
        user.setUserRole(UserRoles.AUTHOR);

        Mockito.doReturn(user).when(userRepository).findByLogin(user.getLogin());
        User expectedUser = userService.getUser(user.getLogin());

        Assert.assertEquals(expectedUser, user);
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setLogin("userLogin");
        user.setPassword("userPass");
        user.setEmail("email@test.email");
        user.setUserRole(UserRoles.AUTHOR);

        Mockito.doReturn(user).when(userRepository).save(user);
        User expectedUser = userService.saveUser(user);

        Assert.assertEquals(expectedUser, user);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void findUserByLogin() {
        User user = new User();
        user.setLogin("userLogin");
        user.setPassword("userPass");
        user.setEmail("email@test.email");
        user.setUserRole(UserRoles.AUTHOR);

        Mockito.doReturn(user)
                .when(userRepository).findByLogin(user.getLogin());
        User expectedUser = userService.findUserByLogin(user.getLogin());

        Assert.assertEquals(expectedUser, user);
        Mockito.verify(userRepository, Mockito.times(1)).findByLogin(user.getLogin());
    }

    @Test
    void existsByLogin() {
        User user = new User();
        user.setLogin("userLogin");
        user.setPassword("userPass");
        user.setEmail("email@test.email");
        user.setUserRole(UserRoles.AUTHOR);

        Mockito.doReturn(true)
                .when(userRepository).existsByLogin(user.getLogin());
        boolean isUserExist = userService.existsByLogin(user.getLogin());

        Assert.assertTrue(isUserExist);
        Mockito.verify(userRepository, Mockito.times(1)).existsByLogin(user.getLogin());
    }

    @Test
    void existsByLoginFalse() {
        User user = new User();
        user.setLogin("userLogin");
        user.setPassword("userPass");
        user.setEmail("email@test.email");
        user.setUserRole(UserRoles.AUTHOR);

        Mockito.doReturn(false)
                .when(userRepository).existsByLogin(user.getLogin());
        boolean isUserExist = userService.existsByLogin(user.getLogin());

        Assert.assertFalse(isUserExist);
        Mockito.verify(userRepository, Mockito.times(1)).existsByLogin(user.getLogin());
    }

    @Test
    void deleteUser() {
        User user = new User();
        user.setLogin("userLogin");
        user.setPassword("userPass");
        user.setEmail("email@test.email");
        user.setUserRole(UserRoles.AUTHOR);

        Mockito.doReturn(true)
                .when(userRepository).existsByLogin(user.getLogin());
        Mockito.doReturn(user)
                .when(userRepository).findByLogin(user.getLogin());
        boolean isUserDeleted = userService.deleteUser(user.getLogin());

        Assert.assertTrue(isUserDeleted);
        Mockito.verify(userRepository, Mockito.times(1)).delete(user);
    }

    @Test
    void deleteUserFail() {
        User user = new User();
        user.setLogin("userLogin");
        user.setPassword("userPass");
        user.setEmail("email@test.email");
        user.setUserRole(UserRoles.AUTHOR);

        Mockito.doReturn(false)
                .when(userRepository).existsByLogin(user.getLogin());
        boolean isUserDeleted = userService.deleteUser(user.getLogin());

        Assert.assertFalse(isUserDeleted);
        Mockito.verify(userRepository, Mockito.times(0)).delete(user);
    }
}
