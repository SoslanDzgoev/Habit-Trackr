package com.example.habittrackr.controllers;

import com.example.habittrackr.dto.HabitDTO;
import com.example.habittrackr.dto.UserDTO;
import com.example.habittrackr.dto.UserWithHabitsDTO;
import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.services.UserServiceImpl;
import com.example.habittrackr.storage.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    private final Mapper mapper;


    @Autowired
    public UserController(UserServiceImpl userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) {
        User user = mapper.toUser(userDTO);
        userService.createOrUpdateUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok().body(mapper.toUserDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/{userId}/habits")
    public ResponseEntity<UserWithHabitsDTO> getUserHabits(@PathVariable long userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        UserWithHabitsDTO userWithHabitsDTO = new UserWithHabitsDTO();
        userWithHabitsDTO.setId(userId);
        userWithHabitsDTO.setUsername(user.getUsername());

        List<HabitDTO> habitDTOs = user.getHabits().stream()
                .map(mapper::toHabitDTO)
                .collect(Collectors.toList());

        userWithHabitsDTO.setHabits(habitDTOs);
        return ResponseEntity.ok(userWithHabitsDTO);
    }

    @GetMapping("/users-with-habits")
    public ResponseEntity<List<UserWithHabitsDTO>> getAllUsersWithHabits() {
        List<User> users = userService.getAllUsers();
        List<UserWithHabitsDTO> userWithHabits = new ArrayList<>();

        for (User user : users) {
            UserWithHabitsDTO userWithHabitsDTO = new UserWithHabitsDTO();
            userWithHabitsDTO.setId(user.getId());
            userWithHabitsDTO.setUsername(user.getUsername());

            List<HabitDTO> habitDTOS = user.getHabits().stream()
                    .map(mapper::toHabitDTO).toList();

            userWithHabitsDTO.setHabits(habitDTOS);
            userWithHabits.add(userWithHabitsDTO);
        }
        return ResponseEntity.ok(userWithHabits);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userService.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());

        userService.createOrUpdateUser(existingUser);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
