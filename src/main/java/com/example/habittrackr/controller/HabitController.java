package com.example.habittrackr.controller;

import com.example.habittrackr.storage.Habit;
import com.example.habittrackr.service.HabitService;
import com.example.habittrackr.storage.HabitKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitController {
    private final HabitService habitService;

    @Autowired
    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }


    @GetMapping
    public ResponseEntity<List<Habit>> getAllHabits() {
        List<Habit> habits = habitService.getAllHabits();
        return ResponseEntity.ok(habits);
    }

    @GetMapping("/{userId}/{habitId}")
    public ResponseEntity<Habit> getHabitById(@PathVariable Long userId, @PathVariable Long habitId) {
        return habitService.getHabitById(userId, habitId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}/{habitId}")
    public ResponseEntity<Habit> updateHabit(@PathVariable long userId,@PathVariable Long habitId, @RequestBody Habit habit) {
        if (habitService.getHabitById(userId, habitId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        habit.setId(new HabitKey(userId, habitId));
        Habit updateHabit = habitService.createOrUpdateHabit(habit);
        return ResponseEntity.ok(updateHabit);
    }

    @DeleteMapping("/{userId}/{habitId}")
    public ResponseEntity<Void> deleteHabit(@PathVariable long userId,@PathVariable Long habitId) {
        if (habitService.getHabitById(userId, habitId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        habitService.deleteHabitById(userId, habitId);
        return ResponseEntity.ok().build();
    }
}
