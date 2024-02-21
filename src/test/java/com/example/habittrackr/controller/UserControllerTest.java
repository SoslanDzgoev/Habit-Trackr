package com.example.habittrackr.controller;

import com.example.habittrackr.service.HabitService;
import com.example.habittrackr.service.UserService;
import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.storage.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

//@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private HabitService habitService;

    @Test
    public void getAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void addHabitToUserTest() throws Exception {
        User user = new User();
        user.setUsername("Soslan");
        user.setPassword("12345");
        user.setEmail("gmail.com");
        user.setId(1L);
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        Habit habit = new Habit();
        habit.setUser(null);
        habit.setName("Habit");
        habit.setIdentity("Habit Identity");
        habit.setInitialComplexity(1L);
        habit.setContract("Habit Contract");
        habit.setHowToPrepareEvn("Habit Preparation");
        List<Habit> habitList = Collections.singletonList(habit);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        String habitJson = objectMapper.writeValueAsString(habitList);

        mockMvc.perform(post("/users/1/habits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(habitJson))
                .andExpect(status().isOk());
    }

    @Test
    void getUserHabits() {
    }

    @Test
    void testGetUserById() {
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}