package com.example.habittrackr.controller;

import com.example.habittrackr.service.UserService;
import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.storage.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;


@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Mock
    private UserService userService;


    @Test
    public void getAllUsers() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void addHabitToUserTest() throws Exception {
        User user = new User("Sos", "12345", "@gmail");
        user.setId(1L);

        Habit habit = new Habit(user, "Name", "Habit Identity", 1L,
                "Habit Contract", "Habit Preparation");

        ObjectMapper objectMapper = new ObjectMapper();
        String habitJson = objectMapper.writeValueAsString(habit);

        when(userService.getUserById(1L)).thenReturn(Optional.of(new User()));



        mockMvc.perform(MockMvcRequestBuilders.post("/users/{userId}/habits", 1L)
                        .content(habitJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.habits[0].name").value("Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.habits[0].identity").value("Habit Identity"));


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