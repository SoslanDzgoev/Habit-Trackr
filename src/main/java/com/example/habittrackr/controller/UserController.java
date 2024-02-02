package com.example.habittrackr.controller;

import com.example.habittrackr.service.UserService;
import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.storage.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{userId}/habits")
    public ResponseEntity<User> addHabitToUser(@PathVariable long userId, @RequestBody List<Habit> habit){
        User user = userService.getUserById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        user.addHabits(habit);
        userService.createOrUpdateUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/habits")
    public ResponseEntity<List<Habit>> getUserHabits(@PathVariable long userId){
        User user = userService.getUserById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        List<Habit> habits = user.getHabits();
        return ResponseEntity.ok(habits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createOrUpdateUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (userService.getUserById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User updateUser = userService.createOrUpdateUser(user);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        if (userService.getUserById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

}
