package com.example.habittrackr.controller;

import com.example.habittrackr.service.HabitServiceImpl;
import com.example.habittrackr.service.UserServiceImpl;
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

    private final UserServiceImpl userServiceImpl;

    private final HabitServiceImpl habitServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, HabitServiceImpl habitServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.habitServiceImpl = habitServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userServiceImpl.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{userId}/habits")
    public ResponseEntity<User> addHabitToUser(@PathVariable long userId, @RequestBody List<Habit> habits) {
        Optional<User> userOptional = userServiceImpl.getUserById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOptional.get();
        for (Habit habit : habits) {
            habit.setUser(user);
            HabitKey habitKey = new HabitKey(userId, new Random().nextLong());
            habit.setId(habitKey);
            habitServiceImpl.createOrUpdateHabit(habit);
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/habits")
    public ResponseEntity<List<Habit>> getUserHabits(@PathVariable long userId){
        User user = userServiceImpl.getUserById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        List<Habit> habits = user.getHabits();
        return ResponseEntity.ok(habits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userServiceImpl.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userServiceImpl.createOrUpdateUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> existingUser = userServiceImpl.getUserById(id);

        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User existingUserData = existingUser.get();
        existingUserData.setUsername(user.getUsername());
        existingUserData.setPassword(user.getPassword());
        existingUserData.setEmail(user.getEmail());

        User update = userServiceImpl.createOrUpdateUser(existingUserData);

        return ResponseEntity.ok(update);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        Optional<User> existingUser = userServiceImpl.getUserById(id);
        if (userServiceImpl.getUserById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userServiceImpl.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

}
