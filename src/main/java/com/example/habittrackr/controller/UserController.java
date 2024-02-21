package com.example.habittrackr.controller;

import com.example.habittrackr.service.HabitService;
import com.example.habittrackr.service.UserService;
import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.storage.HabitKey;
import com.example.habittrackr.storage.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final HabitService habitService;

    @Autowired
    public UserController(UserService userService, HabitService habitService) {
        this.userService = userService;
        this.habitService = habitService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{userId}/habits")
    public ResponseEntity<User> addHabitToUser(@PathVariable long userId, @RequestBody List<Habit> habits) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOptional.get();
        for (Habit habit : habits) {
            habit.setUser(user);
            HabitKey habitKey = new HabitKey(userId, new Random().nextLong());
            habit.setId(habitKey);
            habitService.createOrUpdateHabit(habit);
        }

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
