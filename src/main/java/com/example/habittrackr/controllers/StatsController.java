package com.example.habittrackr.controllers;

import com.example.habittrackr.dto.UserInfoDTO;
import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.services.HabitExecutionServiceImpl;
import com.example.habittrackr.services.HabitServiceImpl;
import com.example.habittrackr.services.UserServiceImpl;
import com.example.habittrackr.storage.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/stats")
public class StatsController {

    private final UserServiceImpl userService;
    private final HabitServiceImpl habitService;
    private final HabitExecutionServiceImpl habitExecutionService;
    private final Mapper mapper;

    @Autowired
    public StatsController(UserServiceImpl userService, HabitServiceImpl habitService, HabitExecutionServiceImpl habitExecutionService, Mapper mapper) {
        this.userService = userService;
        this.habitService = habitService;
        this.habitExecutionService = habitExecutionService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UserInfoDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(mapper.toUserDTOList(users));
    }







}
