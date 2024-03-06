package com.example.habittrackr.controllers;

import com.example.habittrackr.dto.HabitDTO;
import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.services.HabitServiceImpl;
import com.example.habittrackr.services.UserServiceImpl;
import com.example.habittrackr.storage.Habit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/habits")
public class HabitExecutorController {

    private final HabitServiceImpl habitServiceImpl;
    private final Mapper mapper;

    @Autowired
    public HabitExecutorController(HabitServiceImpl habitServiceImpl, UserServiceImpl userServiceImpl, Mapper mapper) {
        this.habitServiceImpl = habitServiceImpl;
        this.mapper = mapper;
    }

    @PutMapping("/{userId}/{habitId}/execute")
    public ResponseEntity<HabitDTO> executeHabit(@PathVariable Long userId, @PathVariable Long habitId) {
        Habit habit = habitServiceImpl.getHabitById(userId, habitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (habit.getNumberOfTimes() == null) {
            habit.setNumberOfTimes(1L);
        } else {
            Long currentNumberOfTime = habit.getNumberOfTimes();
            habit.setNumberOfTimes(currentNumberOfTime + 1);

        }

        HabitDTO orUpdateHabit = habitServiceImpl.createOrUpdateHabit(habit);
        return ResponseEntity.ok(orUpdateHabit);

    }
}
