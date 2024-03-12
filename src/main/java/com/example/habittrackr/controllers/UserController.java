package com.example.habittrackr.controllers;

import com.example.habittrackr.dto.HabitDTO;
import com.example.habittrackr.dto.UserDTO;
import com.example.habittrackr.dto.UserInfoDTO;
import com.example.habittrackr.dto.UserWithHabitsDTO;
import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.services.HabitServiceImpl;
import com.example.habittrackr.services.UserServiceImpl;
import com.example.habittrackr.storage.habits.Habit;
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

    private final UserServiceImpl userServiceImpl;

    private final HabitServiceImpl habitServiceImpl;

    private final Mapper mapper;


    @Autowired
    public UserController(UserServiceImpl userServiceImpl, HabitServiceImpl habitServiceImpl, Mapper mapper) {
        this.userServiceImpl = userServiceImpl;
        this.habitServiceImpl = habitServiceImpl;
        this.mapper = mapper;
    }

    @GetMapping("/users-with-habits")
    public ResponseEntity <List<UserWithHabitsDTO>> allUsersWithHabits(){
        List<User> users = userServiceImpl.getAllUsers();
        List<UserWithHabitsDTO> userWithHabits = new ArrayList<>();

        for (User user : users){
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

    @GetMapping
    public ResponseEntity<List<UserInfoDTO>> getAllUsers() {
        List<User> users = userServiceImpl.getAllUsers();
        return ResponseEntity.ok(mapper.toUserDTOList(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userServiceImpl.getUserById(id)
                .map(user -> ResponseEntity.ok().body(mapper.toUserDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}/habits")
    public ResponseEntity<UserWithHabitsDTO> addHabitsToUser(@PathVariable long userId, @RequestBody List<HabitDTO> habits) {
        User user = userServiceImpl.getUserById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<HabitDTO> habitDTOs = new ArrayList<>();
        for (HabitDTO habitDTO : habits) {
            Habit habit = mapper.toHabit(habitDTO);
            habit.setUser(user);
            habitServiceImpl.createOrUpdateHabit(habit);

            HabitDTO habitDTOWithId = mapper.toHabitDTO(habit);
            habitDTOs.add(habitDTOWithId);

        }
        UserWithHabitsDTO userWithHabitsDTO = new UserWithHabitsDTO();
        userWithHabitsDTO.setId(userId);
        userWithHabitsDTO.setUsername(user.getUsername());
        userWithHabitsDTO.setHabits(habitDTOs);

        return ResponseEntity.ok(userWithHabitsDTO);
    }


    @GetMapping("/{userId}/habits")
    public ResponseEntity<UserWithHabitsDTO> getUserHabits(@PathVariable long userId) {
        User user = userServiceImpl.getUserById(userId)
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


    @PostMapping
    public ResponseEntity<UserInfoDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = mapper.toUser(userDTO);
        UserDTO newUser = userServiceImpl.createOrUpdateUser(user);
        return ResponseEntity.ok(mapper.toUserInfoDTO(newUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserInfoDTO> updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userServiceImpl.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());

        UserDTO update = userServiceImpl.createOrUpdateUser(existingUser);

        return ResponseEntity.ok(mapper.toUserInfoDTO(update));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userServiceImpl.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userServiceImpl.deleteUserById(id);
        return ResponseEntity.ok().build();
    }
}
