package com.example.habittrackr.controllers;

import com.example.habittrackr.dto.HabitDTO;
import com.example.habittrackr.mapper.Mapper;
import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.services.HabitServiceImpl;
import com.example.habittrackr.storage.HabitKey;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


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

    @GetMapping("/{userId}/{habitId}")
    public HabitDTO getHabitById(@PathVariable Long userId, @PathVariable Long habitId) {
        return habitServiceImpl.getHabitById(userId, habitId)
                .map(mapper::toHabitDTO)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Habit not found with userId: " + userId + " and habitId " + habitId));
    }

    @PutMapping("/{userId}/{habitId}")
    public HabitDTO updateHabit(@PathVariable long userId,@PathVariable Long habitId, @RequestBody Habit habit) {
        Habit existingHabit = habitServiceImpl.getHabitById(userId, habitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        habit.setId(new HabitKey(userId, habitId));
        Habit updateHabit = habitServiceImpl.createOrUpdateHabit(habit);

        return mapper.toHabitDTO(updateHabit);
    }

    @DeleteMapping("/{userId}/{habitId}")
    public void deleteHabit(@PathVariable long userId,@PathVariable Long habitId) {
        if (habitServiceImpl.getHabitById(userId, habitId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        habitServiceImpl.deleteHabitById(userId, habitId);
    }
}
