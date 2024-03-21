package com.example.habittrackr.controllers;

import com.example.habittrackr.dto.HabitDTO;
import com.example.habittrackr.dto.UserWithHabitsDTO;
import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.services.UserServiceImpl;
import com.example.habittrackr.storage.habits.Habit;
import com.example.habittrackr.services.HabitServiceImpl;
import com.example.habittrackr.storage.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/habits")
public class HabitController {
    private final HabitServiceImpl habitServiceImpl;
    private final UserServiceImpl userService;
    private final Mapper mapper;

    @Autowired
    public HabitController(HabitServiceImpl habitServiceImpl, UserServiceImpl userService, Mapper mapper) {
        this.habitServiceImpl = habitServiceImpl;
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/{userId}/habits")
    public ResponseEntity<UserWithHabitsDTO> addHabitToUser(@PathVariable long userId, @RequestBody HabitDTO habitDTO) {
        User user = new User();
        user.setId(userId);

        Habit habit = mapper.toHabit(habitDTO);
        habit.setUser(user);
        HabitDTO orUpdateHabit = habitServiceImpl.createOrUpdateHabit(habit);

        List<HabitDTO> habitList = new ArrayList<>();

        habitList.add(orUpdateHabit);
        UserWithHabitsDTO userWithHabitsDTO = new UserWithHabitsDTO();
        userWithHabitsDTO.setId(userId);
        userWithHabitsDTO.setUsername(user.getUsername());
        userWithHabitsDTO.setHabits(habitList);

        return ResponseEntity.ok(userWithHabitsDTO);
    }

//    @PostMapping("/{userId}/habits")
//    public ResponseEntity<UserWithHabitsDTO> addHabitsToUser(@PathVariable long userId, @RequestBody List<HabitDTO> habits) {
//        User user = userService.getUserById(userId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        List<HabitDTO> habitDTOs = new ArrayList<>();
//        for (HabitDTO habitDTO : habits) {
//            Habit habit = mapper.toHabit(habitDTO);
//            habit.setUser(user);
//            habitServiceImpl.createOrUpdateHabit(habit);
//
//            HabitDTO habitDTOWithId = mapper.toHabitDTO(habit);
//            habitDTOs.add(habitDTOWithId);
//        }
//        UserWithHabitsDTO userWithHabitsDTO = new UserWithHabitsDTO();
//        userWithHabitsDTO.setId(userId);
//        userWithHabitsDTO.setUsername(user.getUsername());
//        userWithHabitsDTO.setHabits(habitDTOs);
//
//        return ResponseEntity.ok(userWithHabitsDTO);
//    }

    @GetMapping("/{habitId}")
    public ResponseEntity<HabitDTO> getHabitById(@PathVariable Long habitId) {
        HabitDTO habitDTO = habitServiceImpl.getHabitById(habitId)
                .map(mapper::toHabitDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(habitDTO);
    }

    @PutMapping("/{habitId}")
    public ResponseEntity<HabitDTO> updateHabit(@PathVariable Long habitId, @RequestBody HabitDTO habitDTO) {
        Habit existingHabit = habitServiceImpl.getHabitById(habitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        existingHabit.setName(habitDTO.getName());
        existingHabit.setInitialComplexity(habitDTO.getInitialComplexity());
        existingHabit.setContract(habitDTO.getContract());
        existingHabit.setHowToPrepareEvn(habitDTO.getHowToPrepareEvn());
        existingHabit.setExecutionCount(habitDTO.getExecutionCount());

        HabitDTO updateHabit = habitServiceImpl.createOrUpdateHabit(existingHabit);

        return ResponseEntity.ok(updateHabit);
    }

    @DeleteMapping("/{habitId}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long habitId) {
        habitServiceImpl.getHabitById(habitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        habitServiceImpl.deleteHabitById(habitId);
        return ResponseEntity.ok().build();
    }
}
