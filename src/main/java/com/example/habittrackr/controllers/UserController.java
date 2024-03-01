package com.example.habittrackr.controllers;

import com.example.habittrackr.dto.HabitDTO;
import com.example.habittrackr.dto.UserDTO;
import com.example.habittrackr.dto.UserWithHabitsDTO;
import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.services.HabitServiceImpl;
import com.example.habittrackr.services.UserServiceImpl;
import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.storage.HabitKey;
import com.example.habittrackr.storage.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    private final HabitServiceImpl habitServiceImpl;

    private final Mapper mapper;


    @Autowired
    public UserController(UserServiceImpl userServiceImpl, HabitServiceImpl habitServiceImpl, Mapper mapper) {
        this.userServiceImpl = userServiceImpl;
        this.habitServiceImpl = habitServiceImpl;
        this.mapper = mapper;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        List<User> users = userServiceImpl.getAllUsers();
        return mapper.toUserDTOList(users);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userServiceImpl.getUserById(id)
                .map(mapper::toUserDTO)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @PostMapping("/{userId}/habits")
    public UserWithHabitsDTO addHabitsToUser(@PathVariable long userId, @RequestBody List<Habit> habits) {
        Optional<User> userOptional = userServiceImpl.getUserById(userId);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        for (Habit habit : habits) {
            habit.setUser(user);
            HabitKey habitKey = new HabitKey(userId, new Random().nextLong());
            habit.setId(habitKey);
            habitServiceImpl.createOrUpdateHabit(habit);
        }
        UserWithHabitsDTO userWithHabitsDTO = new UserWithHabitsDTO();
        userWithHabitsDTO.setId(userId);
        userWithHabitsDTO.setUsername(user.getUsername());
        List<HabitDTO> habitDTOs = habits.stream().map(mapper::toHabitDTO).toList();
        userWithHabitsDTO.setHabits(habitDTOs);
        return userWithHabitsDTO;
    }


    @GetMapping("/{userId}/habits")
    public UserWithHabitsDTO getUserHabits(@PathVariable long userId) {
        User user = userServiceImpl.getUserById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        UserWithHabitsDTO userWithHabitsDTO = new UserWithHabitsDTO();
        userWithHabitsDTO.setId(userId);
        userWithHabitsDTO.setUsername(user.getUsername());

        List<HabitDTO> habitDTOs = user.getHabits().stream()
                .map(mapper::toHabitDTO)
                .collect(Collectors.toList());

        userWithHabitsDTO.setHabits(habitDTOs);
        return userWithHabitsDTO;
    }


    @PostMapping
    public UserDTO createUser(@RequestBody User user) {
        User newUser = userServiceImpl.createOrUpdateUser(user);
        return mapper.toUserDTO(user);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userServiceImpl.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());

        User update = userServiceImpl.createOrUpdateUser(existingUser);

        return mapper.toUserDTO(update);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        Optional<User> existingUser = userServiceImpl.getUserById(id);
        if (existingUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        userServiceImpl.deleteUserById(id);
    }
}
