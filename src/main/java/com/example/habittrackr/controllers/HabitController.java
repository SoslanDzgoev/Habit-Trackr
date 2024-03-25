package com.example.habittrackr.controllers;

import com.example.habittrackr.dto.HabitDTO;

import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.storage.habits.Habit;
import com.example.habittrackr.services.HabitServiceImpl;
import com.example.habittrackr.storage.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;


@RestController
@RequestMapping("/habits")
public class HabitController {
    private final HabitServiceImpl habitServiceImpl;
    private final Mapper mapper;

    @Autowired
    public HabitController(HabitServiceImpl habitServiceImpl, Mapper mapper) {
        this.habitServiceImpl = habitServiceImpl;
        this.mapper = mapper;
    }

    @PostMapping("/{userId}/habits")
    public ResponseEntity<Void> addHabitsToUser(@PathVariable long userId, @RequestBody List<HabitDTO> habits) {
        User user = new User();
        user.setId(userId);
        for (HabitDTO habitDTO : habits) {
            Habit habit = mapper.toHabit(habitDTO);
            habit.setUser(user);
            habitServiceImpl.createOrUpdateHabit(habit);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{habitId}")
    public ResponseEntity<HabitDTO> getHabitById(@PathVariable Long habitId) {
        HabitDTO habitDTO = habitServiceImpl.getHabitById(habitId)
                .map(mapper::toHabitDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(habitDTO);
    }

    @PutMapping("/{habitId}")
    public ResponseEntity<Void> updateHabit(@PathVariable Long habitId, @RequestBody HabitDTO habitDTO) {
        Habit existingHabit = habitServiceImpl.getHabitById(habitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        existingHabit.setName(habitDTO.getName());
        existingHabit.setInitialComplexity(habitDTO.getInitialComplexity());
        existingHabit.setContract(habitDTO.getContract());
        existingHabit.setHowToPrepareEvn(habitDTO.getHowToPrepareEvn());
        existingHabit.setExecutionCount(habitDTO.getExecutionCount());

        habitServiceImpl.createOrUpdateHabit(existingHabit);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{habitId}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long habitId) {
        habitServiceImpl.getHabitById(habitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        habitServiceImpl.deleteHabitById(habitId);
        return ResponseEntity.ok().build();
    }
}
