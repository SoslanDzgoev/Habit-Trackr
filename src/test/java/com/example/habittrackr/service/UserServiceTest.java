package com.example.habittrackr.service;

import com.example.habittrackr.storage.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("soslan", "12345", "@gmail");
        userService.createOrUpdateUser(user);
    }

    @Test
    void getAllUsers() {

        assertNotNull(userService.getUserById(user.getId()));

        User user2 = new User("Аслан", "12345", "@gmail");
        userService.createOrUpdateUser(user2);
        assertNotNull(userService.getUserById(user2.getId()));

        List<User> userList = userService.getAllUsers();
        assertFalse(userList.isEmpty());
        assertEquals(2, userList.size());

    }

    @Test
    void getUserById() {
        Long id = user.getId();

        Optional<User> fetchedUser = userService.getUserById(id);

        assertNotNull(fetchedUser);
        assertTrue(fetchedUser.isPresent());

        User newUser = fetchedUser.get();

        assertEquals("soslan", newUser.getUsername());
    }

    @Test
    void createOrUpdateUser() {
        User createUser = userService.createOrUpdateUser(user);

        assertNotNull(createUser);
        assertEquals("soslan", createUser.getUsername());

        createUser.setUsername("Aslan");
        createUser.setPassword("54321");

        User updateUser = userService.createOrUpdateUser(createUser);

        assertEquals("Aslan", updateUser.getUsername());
        assertEquals("54321", updateUser.getPassword());
    }

    @Test
    void deleteUserById() {
        User createUser = userService.createOrUpdateUser(user);
        assertNotNull(createUser);
        Long id = createUser.getId();

        userService.deleteUserById(id);

        assertFalse(userService.getUserById(id).isPresent());
    }
}