package com.example.habittrackr.service;

import com.example.habittrackr.storage.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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
        user = new User();
        user.setUsername("Soslan");
        user.setPassword("12345");
        user.setEmail("gmail.com");
        user.setHabits(new ArrayList<>());
        userService.createOrUpdateUser(user);
    }

    @Test
    void getAllUsers() {

        assertNotNull(userService.getUserById(user.getId()));

        User user2 = new User();
        user.setUsername("Aslan");
        user.setPassword("54321");
        user.setEmail("gmail.com");
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

        assertEquals("Soslan", newUser.getUsername());
    }

    @Test
    void createOrUpdateUser() {
        User createUser = userService.createOrUpdateUser(user);

        assertNotNull(createUser);
        assertEquals("Soslan", createUser.getUsername());

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